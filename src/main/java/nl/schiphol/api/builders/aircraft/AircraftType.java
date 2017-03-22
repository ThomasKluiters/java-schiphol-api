package nl.schiphol.api.builders.aircraft;

/**
 * Created by Thomas on 22-3-2017.
 */
public class AircraftType {

    private String longDescription;

    private String shortDescription;

    private String iatamain;

    private String iatasub;

    private String schemaVersion;

    AircraftType() { }

    public String getLongDescription() {
        return longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getIatamain() {
        return iatamain;
    }

    public String getIatasub() {
        return iatasub;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }
}
