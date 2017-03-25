package nl.schiphol.api.models.destinations;

/**
 * Created by Thomas on 22-3-2017.
 */
public class Destinations {

    private Destination[] destinations;

    private String schemaVersion;

    Destinations() { }

    public Destination[] getDestinations() {
        return destinations;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }
}
