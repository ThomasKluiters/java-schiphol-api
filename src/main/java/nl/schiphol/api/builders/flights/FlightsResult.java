package nl.schiphol.api.builders.flights;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightsResult {

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

    private int[] transferPositions;

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
    FlightsResult() { }

    public long getId() {
        return id;
    }

    public String getFlightName() {
        return flightName;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public String getFlightDirection() {
        return flightDirection;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getPrefixIATA() {
        return prefixIATA;
    }

    public String getPrefixICAO() {
        return prefixICAO;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getMainFlight() {
        return mainFlight;
    }

    public FlightCodeShares getCodeshares() {
        return codeshares;
    }

    public String getEstimatedLandingTime() {
        return estimatedLandingTime;
    }

    public String getActualLandingTime() {
        return actualLandingTime;
    }

    public String getPublicEstimatedOffBlockTime() {
        return publicEstimatedOffBlockTime;
    }

    public String getActualOffBlockTime() {
        return actualOffBlockTime;
    }

    public FlightState getPublicFlightState() {
        return publicFlightState;
    }

    public FlightRoute getRoute() {
        return route;
    }

    public int getTerminal() {
        return terminal;
    }

    public String getGate() {
        return gate;
    }

    public FlightBaggageClaim getBaggageClaim() {
        return baggageClaim;
    }

    public String getExpectedTimeOnBelt() {
        return expectedTimeOnBelt;
    }

    public FlightCheckinAllocations getCheckinAllocations() {
        return checkinAllocations;
    }

    public int[] getTransferPositions() {
        return transferPositions;
    }

    public FlightAircraftType getAircraftType() {
        return aircraftType;
    }

    public String getAircraftRegistration() {
        return aircraftRegistration;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public String getExpectedTimeGateOpen() {
        return expectedTimeGateOpen;
    }

    public String getExpectedTimeBoarding() {
        return expectedTimeBoarding;
    }

    public String getExpectedTimeGateClosing() {
        return expectedTimeGateClosing;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }
}
