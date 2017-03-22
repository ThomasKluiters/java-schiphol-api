package nl.schiphol.examples;

import nl.schiphol.api.Schiphol;
import nl.schiphol.api.SchipholCredentials;
import nl.schiphol.api.SchipholCredentialsUtil;
import nl.schiphol.api.builders.SortBuilder;
import nl.schiphol.api.builders.aircraft.AircraftType;
import nl.schiphol.api.builders.aircraft.AircraftTypes;
import nl.schiphol.api.builders.flights.Flights;

import java.time.LocalDate;

/**
 * Created by Thomas on 22-3-2017.
 */
public class AircraftBuilderExample {

    public static void main(String[] args) {
        // initialize api
        SchipholCredentials credentials = SchipholCredentialsUtil.loadFrom("secrets.json");
        Schiphol schiphol = new Schiphol(credentials);

        AircraftTypes types = schiphol.aircraft().execute();

        for (AircraftType aircraftType : types.getAircraftTypes()) {
            System.out.println(aircraftType.getLongDescription());
        }
    }

}
