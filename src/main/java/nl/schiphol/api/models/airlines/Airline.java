package nl.schiphol.api.models.airlines;

import nl.schiphol.api.models.Response;

/**
 * Created by Thomas on 22-3-2017.
 */
public class Airline extends Response<Airline> {

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

    @Override
    protected Airline get() {
        return this;
    }
}
