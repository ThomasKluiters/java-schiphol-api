package nl.schiphol.api.builders;

import nl.schiphol.api.models.airlines.Airline;

/**
 * Created by Thomas on 22-3-2017.
 */
public class AirlineBuilder extends RequestBuilder<Airline, AirlineBuilder> {

    public AirlineBuilder() {
        super(Airline.class, "/public-flights/airline/{airlineCode}");
        resourceVersion("v1");
    }

    /**
     * Search for an airline with the given airline code.
     *
     * @param airlineCode the IATAO / ICAO code of the airline.
     */
    public AirlineBuilder airlineCode(String airlineCode) {
        return addPathParameter("airlineCode", airlineCode);
    }

    @Override
    protected AirlineBuilder getThis() {
        return this;
    }

}
