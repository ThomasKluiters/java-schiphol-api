package nl.schiphol.api.models.flights;

import lombok.Data;
import nl.schiphol.api.models.Response;
import nl.schiphol.api.models.destinations.Destination;

/**
 * Created by Thomas on 22-3-2017.
 */
@Data
public class Flight extends Response<Flight> {

    private long id;

    private String flightName;

    private String scheduleDate;

    private String flightDirection;

    private String flightNumber;

    private String prefixIATA;

    private String prefixICAO;

    private String scheduleTime;

    private String serviceType;

    private String mainFlight;

    private FlightCodeShares codeshares;

    private String estimatedLandingTime;

    private String actualLandingTime;

    private String publicEstimatedOffBlockTime;

    private String actualOffBlockTime;

    private FlightState publicFlightState;

    private FlightRoute route;

    private int terminal;

    private String gate;

    private FlightBaggageClaim baggageClaim;

    private String expectedTimeOnBelt;

    private FlightCheckinAllocations checkinAllocations;

    private FlightTransferPositions transferPositions;

    private FlightAircraftType aircraftType;

    private String aircraftRegistration;

    private String airlineCode;

    private String expectedTimeGateOpen;

    private String expectedTimeBoarding;

    private String expectedTimeGateClosing;

    private String schemaVersion;

    /**
     * Default constructor for Object Mapper
     */
    Flight() { }

    @Override
    protected Flight get() {
        return this;
    }
}
