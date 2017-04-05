package nl.schiphol.api.builders;

import nl.schiphol.api.models.destinations.Destination;

import javax.annotation.Nonnull;

/**
 * Created by Thomas on 22-3-2017.
 */
public class DestinationBuilder extends RequestBuilder<Destination, DestinationBuilder> {


    public DestinationBuilder() {
        super(Destination.class, "/public-flights/destinations/{iata}");
        resourceVersion("v1");
    }

    /**
     * Search for destination with the given IATA code.
     *
     * @param iata IATA code.
     */
    public DestinationBuilder IATA(@Nonnull final String iata) {
        return addPathParameter("iata", iata);
    }

    @Override
    protected DestinationBuilder getThis() {
        return this;
    }

}
