package nl.schiphol.examples;

import nl.schiphol.api.Schiphol;
import nl.schiphol.api.SchipholCredentials;
import nl.schiphol.api.SchipholCredentialsUtil;
import nl.schiphol.api.models.aircraft.AircraftType;
import nl.schiphol.api.models.aircraft.AircraftTypes;

/**
 * Created by Thomas on 22-3-2017.
 */
public class AircraftBuilderExample {

    public static void main(String[] args) {
        // initialize api
        SchipholCredentials credentials = SchipholCredentialsUtil.loadFrom("secrets.json");
        Schiphol schiphol = new Schiphol(credentials);

        AircraftTypes types = schiphol.aircraft().execute();

        for (AircraftType type : types) {
            System.out.println(type.getLongDescription());
        }
    }

}
