package nl.schiphol.examples;

import nl.schiphol.api.Schiphol;
import nl.schiphol.api.SchipholCredentials;
import nl.schiphol.api.SchipholCredentialsUtil;
import nl.schiphol.api.builders.FlightsBuilder;
import nl.schiphol.api.builders.flights.FlightsResult;
import nl.schiphol.api.builders.flights.FlightsResults;
import nl.schiphol.api.builders.SortBuilder;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightsBuilderExample {

    public static void main(String[] args) {
        SchipholCredentials credentials = SchipholCredentialsUtil.loadFrom("secrets.json");
        Schiphol schiphol = new Schiphol(credentials);

        SortBuilder sortBuilder = new SortBuilder()
                .field("scheduletime").descending();

        FlightsResults result = schiphol.flights()
                .direction(FlightsBuilder.FlightDirection.ARRIVING)
                .airline("KL")
                .direction("D")
                .page(0)
                .sort(sortBuilder)
        .execute();

        for (FlightsResult flightsResult : result.getFlights()) {
            System.out.println(flightsResult.getFlightName() + " will depart at " + flightsResult.getScheduleTime());
        }
    }

}
