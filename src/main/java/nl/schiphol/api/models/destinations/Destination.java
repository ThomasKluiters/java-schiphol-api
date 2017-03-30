package nl.schiphol.api.models.destinations;

import lombok.Data;
import nl.schiphol.api.models.Response;
import nl.schiphol.api.models.airlines.Airline;

/**
 * Created by Thomas on 22-3-2017.
 */
@Data
public class Destination extends Response<Destination> {

    private String city;

    private String country;

    private String iata;

    private PublicName publicName;

    private String schemaVersion;

    Destination() { }

    @Override
    protected Destination get() {
        return this;
    }

    @Data
    public static class PublicName {

        private String dutch;

        private String english;

        PublicName() { }

    }

}
