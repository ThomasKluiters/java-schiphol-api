package nl.schiphol.api.models.airlines;

/**
 * Created by Thomas on 22-3-2017.
 */
public class Airlines {

    private Airline[] airlines;

    private String schemaVersion;

    Airlines() { }

    public Airline[] getAirlines() {
        return airlines;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }
}
