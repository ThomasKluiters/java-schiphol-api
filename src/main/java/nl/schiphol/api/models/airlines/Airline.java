package nl.schiphol.api.models.airlines;

import lombok.Data;
import nl.schiphol.api.models.Response;

/**
 * Created by Thomas on 22-3-2017.
 */
@Data
public class Airline extends Response<Airline> {

    private String iata;

    private String icao;

    private int nvls;

    private String publicName;

    private String schemaVersion;

    Airline() { }

    @Override
    protected Airline get() {
        return this;
    }
}
