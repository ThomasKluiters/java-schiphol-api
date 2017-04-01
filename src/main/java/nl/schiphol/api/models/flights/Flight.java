package nl.schiphol.api.models.flights;

import lombok.Data;
import nl.schiphol.api.models.Response;
import nl.schiphol.api.models.destinations.Destination;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;

/**
 * Created by Thomas on 22-3-2017.
 */
@Data
public class Flight extends Response<Flight> {

    private long id;

    private String flightName;

    private LocalDate scheduleDate;

    private String flightDirection;

    private String flightNumber;

    private String prefixIATA;

    private String prefixICAO;

    private LocalTime scheduleTime;

    private String serviceType;

    private String mainFlight;

    private FlightCodeShares codeshares;

    private LocalDateTime estimatedLandingTime;

    private LocalDateTime actualLandingTime;

    private LocalDateTime publicEstimatedOffBlockTime;

    private LocalDateTime actualOffBlockTime;

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

    private OffsetDateTime expectedTimeGateOpen;

    private OffsetDateTime expectedTimeBoarding;

    private OffsetDateTime expectedTimeGateClosing;

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
