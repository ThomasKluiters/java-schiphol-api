package nl.schiphol.examples;

import nl.schiphol.api.Schiphol;
import nl.schiphol.api.SchipholCredentials;
import nl.schiphol.api.SchipholCredentialsUtil;
import nl.schiphol.api.builders.flights.Flight;
import nl.schiphol.api.builders.flights.Flights;
import nl.schiphol.api.builders.SortBuilder;

import java.time.LocalDate;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightsBuilderExample {

    public static void main(String[] args) {
        // initialize api
        SchipholCredentials credentials = SchipholCredentialsUtil.loadFrom("secrets.json");
        Schiphol schiphol = new Schiphol(credentials);

        // sort on date, then sort on time
        SortBuilder sort = new SortBuilder()
                .field("scheduledate").descending()
                .field("scheduletime").descending();

        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = LocalDate.now().plusWeeks(1);

        // search for all KL flights, departing between today and a week
        Flights result = schiphol.flights()
                .from(fromDate)
                .to(toDate)
                .airline("KL")
                .direction("D")
                .page(0)
                .sort(sort)
                .execute();
        
    }

}
