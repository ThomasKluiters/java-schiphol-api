package nl.schiphol.api.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.NotNull;
import nl.schiphol.api.builders.destinations.Destination;
import nl.schiphol.api.builders.destinations.Destinations;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Thomas on 22-3-2017.
 */
public class DestinationsBuilder extends RequestBuilder<Destinations> {

    public DestinationsBuilder() {
        resourceVersion("v1");
    }

    @Override
    protected Destinations process(InputStream is) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(is, Destinations.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void prepare(URIBuilder builder) {
        builder.setPath("/public-flights/destinations");
    }

}
