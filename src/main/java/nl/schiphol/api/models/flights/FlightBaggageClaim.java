package nl.schiphol.api.models.flights;

import lombok.Data;

/**
 * Created by Thomas on 22-3-2017.
 */
@Data
public class FlightBaggageClaim {

    private String[] belts;

    FlightBaggageClaim() { }

}
