package nl.schiphol.examples;

import nl.schiphol.api.Schiphol;
import nl.schiphol.api.SchipholCredentials;
import nl.schiphol.api.SchipholCredentialsUtil;
import nl.schiphol.api.builders.SortBuilder;
import nl.schiphol.api.builders.destinations.Destination;
import nl.schiphol.api.builders.destinations.Destinations;

/**
 * Created by Thomas on 22-3-2017.
 */
public class DestinationBuilderExample {

    public static void main(String[] args) {
        // initialize api
        SchipholCredentials credentials = SchipholCredentialsUtil.loadFrom("secrets.json");
        Schiphol schiphol = new Schiphol(credentials);

        // sort by county name, ascending
        SortBuilder sortBuilder = new SortBuilder()
                .field("country ").ascending();

        Destinations destinations = schiphol.destinations()
                .page(1)
                .sort(sortBuilder)
                .execute();

        for (Destination destination : destinations.getDestinations()) {
            System.out.println(destination.getPublicName().getDutch());
        }
    }

}
