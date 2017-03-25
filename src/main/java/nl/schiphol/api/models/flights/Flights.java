package nl.schiphol.api.models.flights;

/**
 * Created by Thomas on 22-3-2017.
 */
public class Flights {

    private Flight[]  flights;

    private String schemaVersion;

    Flights() { }

    public Flight[] getFlights() {
        return flights;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }
}
