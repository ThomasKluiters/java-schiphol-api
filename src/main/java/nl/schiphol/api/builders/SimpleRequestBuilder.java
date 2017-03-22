package nl.schiphol.api.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Thomas on 22-3-2017.
 */
class SimpleRequestBuilder<T> extends RequestBuilder<T> {

    /**
     * The class to which the ObjectMapper maps the JSON to.
     */
    private Class<T> clazz;

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
        this.clazz = clazz;
        this.path = path;

        this.resourceVersion(resourceVersion);
    }


    @Override
    protected T process(InputStream is) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(is, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void prepare(URIBuilder builder) {
        builder.setPath(path);
    }
}
