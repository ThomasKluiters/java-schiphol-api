package nl.schiphol.api.builders;

import nl.schiphol.api.builders.destinations.Destinations;

/**
 * Created by Thomas on 22-3-2017.
 */
public class DestinationsBuilder extends SimpleRequestBuilder<Destinations, DestinationsBuilder> {
    public DestinationsBuilder() {
        super(Destinations.class, "/public-flights/destinations", "v1");
    }

    @Override
    protected DestinationsBuilder getThis() {
        return this;
    }
}
