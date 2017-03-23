package nl.schiphol.api.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Thomas on 22-3-2017.
 */
public abstract class SimpleRequestBuilder<T, B extends RequestBuilder> extends JsonRequestBuilder<T, B> {

    /**
     * Path to the API endpoint.
     */
    private String path;

    /**
     * Constructs a SimpleRequestBuilder.
     *
     * @param clazz the Class of the object to map to.
     * @param path the path of the API endpoint.
     * @param resourceVersion the resource version to use.
     */
    public SimpleRequestBuilder(Class<T> clazz, String path, String resourceVersion) {
        super(clazz);
        this.path = path;

        this.resourceVersion(resourceVersion);
    }

    @Override
    protected void prepare(URIBuilder builder) {
        builder.setPath(path);
    }
}
