package nl.schiphol.api.builders;

import nl.schiphol.api.models.destinations.Destinations;

/**
 * Created by Thomas on 22-3-2017.
 */
public class DestinationsBuilder extends RequestBuilder<Destinations, DestinationsBuilder> {

    public DestinationsBuilder() {
        super(Destinations.class, "/public-flights/destinations");
        resourceVersion("v1");
    }

    @Override
    protected DestinationsBuilder getThis() {
        return this;
    }
}
