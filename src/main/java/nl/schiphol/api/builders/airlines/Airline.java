package nl.schiphol.api.builders.airlines;

/**
 * Created by Thomas on 22-3-2017.
 */
public class Airline {

    private String iata;

    private String icao;

    private int nvls;

    private String publicName;

    private String schemaVersion;

    Airline() { }

    public String getIata() {
        return iata;
    }

    public String getIcao() {
        return icao;
    }

    public int getNvls() {
        return nvls;
    }

    public String getPublicName() {
        return publicName;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }
}
