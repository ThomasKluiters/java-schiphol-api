package nl.schiphol.api.builders.flights;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightsResults {

    private FlightsResult[]  flights;

    private String schemaVersion;

    FlightsResults() { }

    public FlightsResult[] getFlights() {
        return flights;
    }
}
