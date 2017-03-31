package nl.schiphol.api.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import nl.schiphol.api.builders.exceptions.RequiredHeaderException;
import nl.schiphol.api.builders.exceptions.RequiredParameterException;
import nl.schiphol.api.models.Response;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Thomas on 22-3-2017.
 */
public abstract class RequestBuilder<T extends Response<T>, B extends RequestBuilder> {

    private final Pattern pattern = Pattern.compile("<(.+)>; rel=\"(.+)\"");

    private static final String FIRST = "first";

    private static final String NEXT = "next";

    private static final String LAST = "last";

    private static final String PREVIOUS = "prev";

    @Getter
    private HttpClient httpClient;

    @Getter
    private final Class<T> mappedClass;

    @Getter
    private final String endpoint;

    @Getter
    private List<NameValuePair> pathParameters = new ArrayList<>();

    @Getter
    private List<NameValuePair> parameters = new ArrayList<>();

    @Getter
    private List<Header> headers = new ArrayList<>();

    public RequestBuilder(final Class<T> mappedClass, String endpoint) {
        this.mappedClass = mappedClass;
        this.endpoint = endpoint;

        addHeader("Accept", "Application/Json");
    }

    public B appId(final String appId) {
        return addParameter("app_id", appId);
    }

    public B appKey(final String appKey) {
        return addParameter("app_key", appKey);
    }

    B resourceVersion(final String resourceVersion) {
        return addHeader("ResourceVersion", resourceVersion);
    }

    public B page(final long page) {
        return addParameter("page", String.valueOf(page));
    }

    public B sort(final SortBuilder sort) {
        return addParameter("sort", sort.toString());
    }

    public B withClient(final HttpClient httpClient) {
        this.httpClient = httpClient;
        return getThis();
    }

    public T execute(String uri) {
        try {
            return execute(new URIBuilder(uri));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T execute(URIBuilder builder) {
        InputStream src = null;
        try {
            URI uri = builder.build();
            HttpGet get = new HttpGet(uri);
            for (Header header : headers) get.addHeader(header);
            HttpResponse response = getHttpClient().execute(get);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                src = response.getEntity().getContent();
                T object = new ObjectMapper().readValue(src, getMappedClass());
                extractPaginationLinks(response, object);
                object.setBuilder(this);

                for (NameValuePair pair : builder.getQueryParams()) {
                    if(pair.getName().equals("page")) {
                        final long page = Long.valueOf(pair.getValue());
                        object.setPage(page);
                    }
                }

                return object;
            } else {
                System.err.println(response.getStatusLine());
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            try {
                if(src != null) src.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private void extractPaginationLinks(HttpResponse response, T object) {
        if(response.getHeaders("Link") != null) {
            for (Header link : response.getHeaders("Link")) {
                for (String value : link.getValue().split(", ")) {
                    Matcher matcher = pattern.matcher(value);
                    if (matcher.find()) {
                        String url = matcher.group(1);
                        String position = matcher.group(2);

                        if (FIRST.equals(position))
                            object.setFirst(url);
                        else if (LAST.equals(position))
                            object.setLast(url);
                        else if (PREVIOUS.equals(position))
                            object.setPrevious(url);
                        else if (NEXT.equals(position))
                            object.setNext(url);
                    }
                }
            }
        }
    }

    public T execute() {
        for (String requiredHeaderName : requiredHeaders()) {
            if(!hasHeader(requiredHeaderName)) throw new RequiredHeaderException(requiredHeaderName);
        }

        for (String requiredParameterName : requiredParameters()) {
            if(!hasParameter(requiredParameterName)) throw new RequiredParameterException(requiredParameterName);
        }

        String path = getEndpoint();

        for (NameValuePair pathParameter : getPathParameters()) {
            final String pathParameterName = pathParameter.getName();
            final String pathParameterValue = pathParameter.getValue();
            path = path.replace("{" + pathParameterName + "}", pathParameterValue);
        }
        path = path.replaceAll("\\{.+}", "");

        URIBuilder builder = new URIBuilder()
                .setScheme("https")
                .setHost("api.schiphol.nl")
                .setPort(443)
            .addParameters(getParameters())
            .setPath(path);

        return execute(builder);
    }

    private boolean hasHeader(@Nonnull final String name) {
        return headers.stream().anyMatch(header -> header.getName().equals(name));
    }

    private boolean hasParameter(@Nonnull final String name) {
        return parameters.stream().anyMatch(parameter -> parameter.getName().equals(name));
    }

    B addParameter(final String name, final String value) {
        final List<NameValuePair> parameters = getParameters();

        parameters.removeIf(parameter -> parameter.getName().equals(name));
        parameters.add(new BasicNameValuePair(name, value));
        return getThis();
    }

    private B addHeader(final String name, final String value) {
        final List<Header> headers = getHeaders();

        headers.removeIf(header -> header.getName().equals(name));
        headers.add(new BasicHeader(name, value));
        return getThis();
    }

    B addPathParameter(final String name, final String value) {
        return addPathParameter(name, null, value);
    }

    B addPathParameter(final String name, final String extra, final String value) {
        final List<NameValuePair> pathParameters = getPathParameters();

        pathParameters.removeIf(parameter -> parameter.getName().equals(name));
        pathParameters.add(new BasicNameValuePair(name, (extra == null ? "" : extra + "/") + value));
        return getThis();
    }

    @Nonnull
    String getPathParameter(@Nonnull final String name) {
        return getPathParameters().stream()
                .filter(parameter -> parameter.getName().equals(name))
                .findFirst()
                .orElseThrow(()
                        -> new IllegalArgumentException(String.format("Path parameter %s not set!", name)))
                .getValue();
    }

    @Nonnull
    String getHeader(@Nonnull final String name) {
        return getHeaders().stream()
                .filter(header -> header.getName().equals(name))
                .findFirst()
                .orElseThrow(()
                        -> new IllegalArgumentException(String.format("Header %s not set!", name)))
                .getValue();
    }

    @Nonnull
    String getParameter(@Nonnull final String name) {
        return getParameters().stream()
                .filter(parameter -> parameter.getName().equals(name))
                .findFirst()
                .orElseThrow(()
                        -> new IllegalArgumentException(String.format("Parameter %s not set!", name)))
                .getValue();
    }

    private String[] requiredParameters() {
        return new String[]{ "app_id", "app_key" };
    }

    private String[] requiredHeaders() {
        return new String[]{ "ResourceVersion" };
    }

    protected abstract B getThis();

}