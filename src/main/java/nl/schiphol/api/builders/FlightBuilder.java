package nl.schiphol.api.builders;

import nl.schiphol.api.models.flights.Flight;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightBuilder extends RequestBuilder<Flight, FlightBuilder> {

    public FlightBuilder() {
        super(Flight.class, "/public-flights/flights/{id}{flightName}");
        resourceVersion("v3");
    }

    public FlightBuilder id(Integer id) {
        return addPathParameter("id", String.valueOf(id));
    }

    public FlightBuilder id(Long id) {
        return addPathParameter("id", String.valueOf(id));
    }

    public FlightBuilder flightName(final String flightName) {
        return addPathParameter("flightName", "/codeshares", flightName);
    }


    @Override
    protected FlightBuilder getThis() {
        return this;
    }
}
