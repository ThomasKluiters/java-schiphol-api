package nl.schiphol.api.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
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

    private List<NameValuePair> parameters;

    private List<Header> headers;

    public RequestBuilder(final Class<T> mappedClass) {
        this.mappedClass = mappedClass;
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
        URIBuilder builder = new URIBuilder()
                .setScheme("https")
                .setHost("api.schiphol.nl");

        for (String requiredHeaderName : requiredHeaders()) {
            if(!hasHeader(requiredHeaderName)) throw new RequiredHeaderException(requiredHeaderName);
        }

        for (String requiredParameterName : requiredParameters()) {
            if(!hasParameter(requiredParameterName)) throw new RequiredParameterException(requiredParameterName);
        }

        builder.addParameters(getParameters());

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
        for (Header header : headers) {
            if(header.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    protected boolean hasParameter(final String name) {
        for (NameValuePair parameter: parameters) {
            if(parameter.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    protected void addParameter(final String name, final String value) {
        getParameters().add(new BasicNameValuePair(name, value));
    }

    protected void addHeader(final String name, final String value) {
        getHeaders().add(new BasicHeader(name, value) {
        });
    }

    public List<NameValuePair> getParameters() {
        if(parameters == null) {
            parameters = new ArrayList<>();
        }
        return parameters;
    }

    public List<Header> getHeaders() {
        if(headers == null) {
            headers = new ArrayList<org.apache.http.Header>();
        }
        return headers;
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

    protected abstract B getThis();

}
