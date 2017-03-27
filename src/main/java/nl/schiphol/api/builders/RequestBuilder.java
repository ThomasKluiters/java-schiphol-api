package nl.schiphol.api.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.schiphol.api.builders.exceptions.RequiredHeaderException;
import nl.schiphol.api.builders.exceptions.RequiredParameterException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 22-3-2017.
 */
public abstract class RequestBuilder<T, B extends RequestBuilder> {

    private HttpClient httpClient;

    private final Class<T> mappedClass;

    private final String endpoint;

    private List<NameValuePair> pathParameters;

    private List<NameValuePair> parameters;

    private List<Header> headers;

    public RequestBuilder(final Class<T> mappedClass, String endpoint) {
        this.mappedClass = mappedClass;
        this.endpoint = endpoint;
    }

    public B appId(final String appId) {
        addParameter("app_id", appId);
        return getThis();
    }

    public B appKey(final String appKey) {
        addParameter("app_key", appKey);
        return getThis();
    }

    public B resourceVersion(final String resourceVersion) {
        addHeader("ResourceVersion", resourceVersion);
        return getThis();
    }

    public B page(final long page) {
        addParameter("page", String.valueOf(page));
        return getThis();
    }

    public B sort(final SortBuilder sort) {
        addParameter("sort", sort.toString());
        return getThis();
    }

    public B withClient(final HttpClient httpClient) {
        this.httpClient = httpClient;
        return getThis();
    }

    public T execute() {
        for (String requiredHeaderName : requiredHeaders()) {
            if(!hasHeader(requiredHeaderName)) throw new RequiredHeaderException(requiredHeaderName);
        }

        for (String requiredParameterName : requiredParameters()) {
            if(!hasParameter(requiredParameterName)) throw new RequiredParameterException(requiredParameterName);
        }

        String path = getEndpoint();

        if(pathParameters != null) {
            for (NameValuePair pathParameter : getPathParameters()) {
                final String pathParameterName = pathParameter.getName();
                final String pathParameterValue = pathParameter.getValue();
                path = path.replace("{" + pathParameterName + "}", pathParameterValue);
            }
            path = path.replaceAll("\\{.+}", "");
        }

        URIBuilder builder = new URIBuilder()
                .setScheme("https")
                .setHost("api.schiphol.nl")
            .addParameters(getParameters())
            .setPath(path);

        InputStream src = null;
        try {
            HttpGet get = new HttpGet(builder.build());
            for (Header header : headers) get.addHeader(header);
            HttpResponse response = getHttpClient().execute(get);
            src = response.getEntity().getContent();
            return new ObjectMapper().readValue(src, getMappedClass());
        } catch (URISyntaxException | IOException e) {
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

    protected boolean hasHeader(final String name) {
        if(headers == null) return false;

        for (Header header : getHeaders()) {
            if(header.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    protected boolean hasParameter(final String name) {
        if(parameters == null) return false;

        for (NameValuePair parameter: getParameters()) {
            if(parameter.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    protected void addParameter(final String name, final String value) {
        final List<NameValuePair> parameters = getParameters();

        parameters.removeIf(parameter -> parameter.getName().equals(name));
        parameters.add(new BasicNameValuePair(name, value));
    }

    protected void addHeader(final String name, final String value) {
        final List<Header> headers = getHeaders();

        headers.removeIf(header -> header.getName().equals(name));
        headers.add(new BasicHeader(name, value));
    }

    protected void addPathParameter(final String name, final String value) {
        addPathParameter(name, null, value);
    }

    protected void addPathParameter(final String name, final String extra, final String value) {
        final List<NameValuePair> pathParameters = getPathParameters();

        pathParameters.removeIf(parameter -> parameter.getName().equals(name));
        pathParameters.add(new BasicNameValuePair(name, (extra == null ? "" : extra + "/") + value));
    }


    public List<NameValuePair> getParameters() {
        if(parameters == null) {
            parameters = new ArrayList<>();
        }
        return parameters;
    }

    public List<Header> getHeaders() {
        if(headers == null) {
            headers = new ArrayList<>();
        }
        return headers;
    }

    public List<NameValuePair> getPathParameters() {
        if(pathParameters == null) {
            pathParameters = new ArrayList<>();
        }
        return pathParameters;
    }

    String getPathParameter(final String name) {
        for (NameValuePair parameter : pathParameters) {
            if(parameter.getName().equals(name)) {
                return parameter.getValue();
            }
        }
        return null;
    }

    String getHeader(final String name) {
        for (Header header : getHeaders()) {
            if(header.getName().equals(name)) {
                return header.getValue();
            }
        }
        return null;
    }

    String getParameter(final String name) {
        for (NameValuePair parameter : getParameters()) {
            if(parameter.getName().equals(name)) {
                return parameter.getValue();
            }
        }
        return null;
    }

    protected String[] requiredParameters() {
        return new String[]{ "app_id", "app_key" };
    }

    protected String[] requiredHeaders() {
        return new String[]{ "ResourceVersion" };
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public Class<T> getMappedClass() {
        return mappedClass;
    }

    public String getEndpoint() {
        return endpoint;
    }

    protected abstract B getThis();

}
