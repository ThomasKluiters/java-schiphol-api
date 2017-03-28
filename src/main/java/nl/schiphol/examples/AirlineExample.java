package nl.schiphol.examples;

import nl.schiphol.api.Schiphol;
import nl.schiphol.api.SchipholCredentials;
import nl.schiphol.api.SchipholCredentialsUtil;
import nl.schiphol.api.models.airlines.Airline;
import nl.schiphol.api.models.airlines.Airlines;

/**
 * Created by Thomas on 27-3-2017.
 */
public class AirlineExample {

    public static void main(String[] args) {
        SchipholCredentials credentials = SchipholCredentialsUtil.loadFrom("secrets.json");
        Schiphol schiphol = new Schiphol(credentials);

        for (Airlines airlines : schiphol.airlines().execute().all()) {
            for (Airline airline : airlines) {
                System.out.println(airline.getPublicName());
            }
        }
    }

}
