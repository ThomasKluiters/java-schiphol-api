package nl.schiphol.api.models.aircraft;

/**
 * Created by Thomas on 22-3-2017.
 */
public class AircraftTypes {

    private AircraftType[] aircraftTypes;

    private String schemaVersion;

    AircraftTypes() { }

    public AircraftType[] getAircraftTypes() {
        return aircraftTypes;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }
}
