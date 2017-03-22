package nl.schiphol.api.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.schiphol.api.builders.flights.Flights;
import org.apache.http.client.ClientProtocolException;
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
abstract class RequestBuilder<T> {

    /**
     * Resource version.
     */
    private String resourceVersion = "v3";

    private String appId;

    private String appKey;

    private SortBuilder sort;

    /**
     * Page number.
     */
    private Long page = null;

    public RequestBuilder<T> appId(final String appId) {
        this.appId = appId;
        return this;
    }

    public RequestBuilder<T> appKey(final String appKey) {
        this.appKey = appKey;
        return this;
    }

    public RequestBuilder<T> resourceVersion(final String resourceVersion) {
        this.resourceVersion = resourceVersion;
        return this;
    }

    public RequestBuilder<T> page(final long page) {
        this.page = page;
        return this;
    }

    public RequestBuilder<T> sort(final SortBuilder sort) {
        this.sort = sort;
        return this;
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

             final CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(get);

             final InputStream is = response.getEntity().getContent();
             T result = process(is);
             HttpClientUtils.closeQuietly(client);
             return result;
         } catch (IOException | URISyntaxException e) {
             e.printStackTrace();
         }
        return null;
    }

    protected abstract T process(InputStream is);

    protected abstract void prepare(URIBuilder builder);

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
