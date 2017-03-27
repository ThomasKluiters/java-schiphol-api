package nl.schiphol.api.builders;

import nl.schiphol.api.models.flights.Flight;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightBuilder extends RequestBuilder<Flight, FlightBuilder> {

    public FlightBuilder() {
        super(Flight.class, "/public-flights/{id}");
        resourceVersion("v3");
    }

    public FlightBuilder id(Long id) {
        addPathParameter("id", String.valueOf(id));
        return this;
    }

    @Override
    protected FlightBuilder getThis() {
        return this;
    }
}
