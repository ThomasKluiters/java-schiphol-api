package nl.schiphol.api.builders;

import nl.schiphol.api.builders.airlines.Airline;
import org.apache.http.client.utils.URIBuilder;

import java.io.InputStream;

/**
 * Created by Thomas on 22-3-2017.
 */
public class AirlineBuilder extends JsonRequestBuilder<Airline, AirlineBuilder> {

    /**
      * The IATA / ICAO code of the airline.
     */
    private String airlineCode;

    public AirlineBuilder() {
        super(Airline.class);

        this.resourceVersion("v1");
    }


    /**
     * Search for an airline with the given airline code.
     *
     * @param airlineCode the IATAO / ICAO code of the airline.
     */
    public AirlineBuilder airlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
        return this;
    }

    @Override
    protected AirlineBuilder getThis() {
        return this;
    }

    @Override
    protected void prepare(URIBuilder builder) {
        if(getAirlineCode() == null) {
            throw new IllegalArgumentException();
        }

        builder.setPath("/public-flights/destinations" + "/" + getAirlineCode());
    }

    String getAirlineCode() {
        return airlineCode;
    }
}
