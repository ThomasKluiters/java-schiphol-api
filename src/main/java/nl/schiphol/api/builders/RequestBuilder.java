package nl.schiphol.api.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.schiphol.api.builders.flights.Flights;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Thomas on 22-3-2017.
 */
public abstract class RequestBuilder<T, B extends RequestBuilder> {

    /**
     * Resource version.
     */
    private String resourceVersion = "v3";

    private String appId;

    private String appKey;

    private SortBuilder sort;

    private HttpClient httpClient;

    /**
     * Page number.
     */
    private Long page = null;

    public B appId(final String appId) {
        this.appId = appId;
        return getThis();
    }

    public B appKey(final String appKey) {
        this.appKey = appKey;
        return getThis();
    }

    public B resourceVersion(final String resourceVersion) {
        this.resourceVersion = resourceVersion;
        return getThis();
    }

    public B page(final long page) {
        this.page = page;
        return getThis();
    }

    public B sort(final SortBuilder sort) {
        this.sort = sort;
        return getThis();
    }

    public B withClient(final HttpClient httpClient) {
        this.httpClient = httpClient;
        return getThis();
    }

    public T execute() {
        if(getAppKey() == null) {
            throw new IllegalArgumentException("App key must be provided.");
        }

        if(getAppId() == null) {
            throw new IllegalArgumentException("App id must be provided.");
        }

        URIBuilder builder = new URIBuilder()
                .setScheme("https")
                .setHost("api.schiphol.nl")
            .addParameter("app_id", getAppId())
            .addParameter("app_key", getAppKey());

        if(getSort() != null) {
            builder.addParameter("sort", getSort().toString());
        }

        if(getPage() != null) {
            builder.addParameter("page", getPage().toString());
        }

        prepare(builder);

         try {
             final URI uri = builder.build();
             HttpGet get = new HttpGet(uri);
             get.addHeader("Accept", "application/json");
             get.addHeader("ResourceVersion", getResourceVersion());

             HttpClient client = getHttpClient();
             HttpResponse response = client.execute(get);

             final InputStream is = response.getEntity().getContent();
             T result = process(is);
             HttpClientUtils.closeQuietly(response);
             return result;
         } catch (IOException | URISyntaxException e) {
             e.printStackTrace();
         }
        return null;
    }

    protected abstract B getThis();

    protected abstract T process(InputStream is);

    protected abstract void prepare(URIBuilder builder);

    HttpClient getHttpClient() {
        return httpClient;
    }

    String getResourceVersion() {
        return resourceVersion;
    }

    String getAppId() {
        return appId;
    }

    String getAppKey() {
        return appKey;
    }

    SortBuilder getSort() {
        return sort;
    }

    Long getPage() {
        return page;
    }
}
