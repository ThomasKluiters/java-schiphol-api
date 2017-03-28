package nl.schiphol.api.models.destinations;

import nl.schiphol.api.models.Response;
import nl.schiphol.api.models.airlines.Airline;

/**
 * Created by Thomas on 22-3-2017.
 */
public class Destination extends Response<Destination> {

    private String city;

    private String country;

    private String iata;

    private PublicName publicName;

    private String schemaVersion;

    Destination() { }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getIata() {
        return iata;
    }

    public PublicName getPublicName() {
        return publicName;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    @Override
    protected Destination get() {
        return this;
    }

    public static class PublicName {

        private String dutch;

        private String english;

        PublicName() { }

        public String getDutch() {
            return dutch;
        }

        public String getEnglish() {
            return english;
        }
    }

}
