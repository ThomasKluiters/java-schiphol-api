package nl.schiphol.api.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.schiphol.api.builders.flights.Flight;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightBuilder extends JsonRequestBuilder<Flight, FlightBuilder> {

    /**
     * Id of the flight.
     */
    private Long id;

    /**
     * Name of the flight.
     */
    private String flightName;

    public FlightBuilder() {
        super(Flight.class);

        resourceVersion("v1");
    }

    public FlightBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public FlightBuilder flightName(String flightName) {
        this.flightName = flightName;
        return this;
    }

    @Override
    protected FlightBuilder getThis() {
        return this;
    }


    @Override
    protected void prepare(URIBuilder builder) {
        if(getId() == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder path = new StringBuilder("/public-flights/flights/").append(getId());

        if(getFlightName() != null) {
            path.append("/codeshares/").append(getFlightName());
        }

        builder.setPath(path.toString());
    }

    Long getId() {
        return id;
    }

    String getFlightName() {
        return flightName;
    }
}
