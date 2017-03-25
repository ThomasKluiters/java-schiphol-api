package nl.schiphol.api.builders;

import nl.schiphol.api.models.destinations.Destination;
import org.apache.http.client.utils.URIBuilder;

import javax.annotation.Nonnull;

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
    public DestinationBuilder iata(@Nonnull final String iata) {
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
