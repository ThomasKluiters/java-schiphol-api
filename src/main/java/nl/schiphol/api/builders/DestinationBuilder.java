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
public class DestinationBuilder extends JsonRequestBuilder<Destination, DestinationBuilder> {

    /**
     * IATA code.
     */
    private String iata;

    public DestinationBuilder() {
        super(Destination.class);

        resourceVersion("v1");
    }

    /**
     * Search for destination with the given IATA code.
     *
     * @param iata IATA code.
     */
    public DestinationBuilder iata(@NotNull final String iata) {
        this.iata = iata;
        return this;
    }

    @Override
    protected DestinationBuilder getThis() {
        return this;
    }

    @Override
    protected void prepare(URIBuilder builder) {
        if(getIata() == null) {
            throw new IllegalArgumentException();
        }
        builder.setPath("/public-flights/destinations" + "/" + getIata());
    }

    String getIata() {
        return iata;
    }
}
