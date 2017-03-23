package nl.schiphol.api.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.schiphol.api.builders.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Thomas on 23-3-2017.
 */
abstract class JsonRequestBuilder<T, B extends RequestBuilder> extends RequestBuilder<T, B> {

    /**
     * The class to which the ObjectMapper maps the JSON to.
     */
    private final Class<T> clazz;

    JsonRequestBuilder(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    protected final T process(InputStream is) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(is, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
