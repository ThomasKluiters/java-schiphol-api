package nl.schiphol.api.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import nl.schiphol.api.builders.exceptions.RequiredHeaderException;
import nl.schiphol.api.builders.exceptions.RequiredParameterException;
import nl.schiphol.api.models.Response;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
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
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    @Setter
    private String endpoint;

    @Getter
    private HashMap<String, String> pathParameters = new HashMap<>();

    @Getter
    private HashMap<String, String> parameters = new HashMap<>();

    @Getter
    private HashMap<String, String> headers = new HashMap<>();

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
            for (Map.Entry<String, String> header : getHeaders().entrySet()) {
                get.addHeader(header.getKey(), header.getValue());
            }
            HttpResponse response = getHttpClient().execute(get);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                src = response.getEntity().getContent();
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                T object = mapper.readValue(src, getMappedClass());
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

        String path = getPathParameters().entrySet().stream()
                .reduce(getEndpoint(), (p, param) -> p.replace("{" + param.getKey() + "}", param.getValue()), String::concat);

        List<NameValuePair> params = getParameters().entrySet().stream()
                .map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        URIBuilder builder = new URIBuilder()
                .setScheme("https")
                .setHost("api.schiphol.nl")
                .setPort(443)
            .addParameters(params)
            .setPath(path);

        return execute(builder);
    }

    private boolean hasHeader(@Nonnull final String name) {
        return getHeaders().containsKey(name);
    }

    private boolean hasParameter(@Nonnull final String name) {
        return getParameters().containsKey(name);
    }

    B addParameter(final String name, final String value) {
        getParameters().put(name, value);
        return getThis();
    }

    private B addHeader(final String name, final String value) {
        getHeaders().put(name, value);
        return getThis();
    }

    B addPathParameter(final String name, final String value) {
        getPathParameters().put(name, value);
        return getThis();
    }

    @Nonnull
    String getParameter(@Nonnull final String name) {
        return getParameters().computeIfAbsent(name, s -> {
            throw new IllegalArgumentException(String.format("Parameter %s not set!", name));
        });
    }

    @Nonnull
    String getPathParameter(@Nonnull final String name) {
        return getPathParameters().computeIfAbsent(name, s -> {
            throw new IllegalArgumentException(String.format("Path parameter %s not set!", name));
        });
    }

    @Nonnull
    String getHeader(@Nonnull final String name) {
        return getHeaders().computeIfAbsent(name, s -> {
            throw new IllegalArgumentException(String.format("Header %s not set!", name));
        });
    }

    private String[] requiredParameters() {
        return new String[]{ "app_id", "app_key" };
    }

    private String[] requiredHeaders() {
        return new String[]{ "ResourceVersion" };
    }

    protected abstract B getThis();

}