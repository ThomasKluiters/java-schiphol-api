package nl.schiphol.api.builders;

import nl.schiphol.api.models.flights.Flights;
import org.apache.http.client.utils.URIBuilder;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightsBuilder extends JsonRequestBuilder<Flights, FlightsBuilder> {

    private final String[] VALID_SORT_FIELDS = {
        "flightname",
        "scheduledate",
        "scheduletime",
        "flightdirection" ,
        "mainflight",
        "codeshare",
        "airline",
        "nvlscode",
        "destination",
        "id"
    };

    /**
     * Scheduled date for the flights to depart, format "yyyy-MM-dd".
     */
    private LocalDate scheduleDate;

    /**
     * The format for the scheduled date.
     */
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Scheduled time for the flight to depart, format "HH:mm.
     */
    private LocalTime scheduleTime;

    /**
     * The format for the scheduled time.
     */
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * The name of the flight as printed on the ticket.
     */
    private String flightName;

    /**
     * Direction of the flight, either "A" for Arriving or "D" for Departing.
     */
    private FlightDirection flightDirection;

    /**
     * The name of the airline, a 2-3 character code.
     */
    private String airline;

    /**
     * NVLS code of a airliner.
     */
    private String nvlscode;

    /**
     * The route of the flight, IATA or ICAO code separated by commas.
     */
    private String route;

    /**
     * If true will also include delayed flights in results.
     */
    private boolean includeDelays;

    /**
     * 	From date of search period. Format: yyyy-MM-dd.
     */
    private LocalDate fromDate;

    /**
     * 	To date of search period (inclusive). Format: yyyy-MM-dd.
     */
    private LocalDate toDate;

    public enum FlightDirection {
        ARRIVING,
        DEPARTING;

        @Override
        public String toString() {
            switch (this) {
                case ARRIVING:
                    return "A";
                case DEPARTING:
                    return "D";
                default:
                    throw new RuntimeException();
            }
        }
    }

    public FlightsBuilder() {
        super(Flights.class);

        resourceVersion("v3");
    }

    /**
     * Search for flights that are scheduled to arrive/depart at the given time.
     *
     * @param raw scheduled date to get flights from represented as a String with the format yyyy-MM-dd.
     */
    public FlightsBuilder scheduleDate(@Nonnull final String raw) {
        return scheduleDate(LocalDate.parse(raw, dateFormat));
    }

    /**
     * Search for flights that are scheduled to arrive/depart on the given date.
     *
     * @param scheduleDate scheduled date to get flights for.
     */
    public FlightsBuilder scheduleDate(@Nonnull final LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
        return this;
    }

    /**
     * Search for flights that are scheduled to arrive/depart at the given time.
     *
     * @param raw scheduled time to get flights from represented as a String with the format HH:mm.
     */
    public FlightsBuilder scheduleTime(@Nonnull final String raw) {
        return scheduleTime(LocalTime.parse(raw, timeFormat));
    }

    /**
     * Search for flights that are scheduled at the given schedule time.
     *
     * @param scheduleTime scheduled time to get flights from.
     */
    public FlightsBuilder scheduleTime(@Nonnull final LocalTime scheduleTime) {
        this.scheduleTime = scheduleTime;
        return this;
    }

    /**
     * Search for flights with the given flightname.
     *
     * @param flightName flight number as printed on the ticket.
     */
    public FlightsBuilder flightName(@Nonnull final String flightName) {
        this.flightName = flightName;
        return this;
    }

    /**
     * Search for flights going in the given direction.
     *
     * The direction of the flight, more information: {@link #flightDirection}
     *
     * @param raw a String with either "A" or "B" representing the flight direction.
     */
    public FlightsBuilder direction(@Nonnull final String raw) {
        if(raw.length() != 1) {
            throw new IllegalArgumentException();
        }
        return direction(raw.charAt(0));
    }

    /**
     * Search for flights going in the given direction.
     *
     * The direction of the flight, more information: {@link #flightDirection}
     *
     * @param raw a character with either the value 'A' or 'D' representing the flight direction.
     */
    public FlightsBuilder direction(@Nonnull final char raw) {
        switch (raw) {
            case 'A':
                return direction(FlightDirection.ARRIVING);
            case 'D':
                return direction(FlightDirection.DEPARTING);

            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Search for flights going in the given direction.
     *
     * The direction of the flight, more information: {@link #flightDirection}
     *
     * @param direction the FlightDirection of the flight.
     */
    public FlightsBuilder direction(@Nonnull final FlightDirection direction) {
        this.flightDirection = direction;
        return this;
    }


    /**
     * Search for flights with the given airline code.
     *
     * @param airline prefix in flight number as printed on the ticket (size: 2 or 3 characters).
     */
    public FlightsBuilder airline(@Nonnull final String airline) {
        if(airline.length() != 2 && airline.length() != 3) {
            throw new IllegalArgumentException();
        }
        this.airline = airline;
        return this;
    }

    /**
     * Search for flights with the given NVLS code.
     *
     * @param nvlscode NVLS code of a airliner.
     */
    public FlightsBuilder nvlsCode(@Nonnull final String nvlscode) {
        this.nvlscode = nvlscode;
        return this;
    }

    /**
     * Search for flights with the given route.
     *
     * @param route the IATA or ICAO code of airport in route, comma separated.
     */
    public FlightsBuilder route(final String route) {
        this.route = route;
        return this;
    }

    /**
     * Includes delayed flights in the results.
     */
    public FlightsBuilder includeDelayed() {
        this.includeDelays = true;
        return this;
    }

    /**
     * Sets the from date for the flights to be requested, more information: {@link #fromDate}
     *
     * The expected format is "yyy-MM-dd".
     *
     * @param raw the string representation of the from date.
     */
    public FlightsBuilder from(@Nonnull final String raw) {
        return from(LocalDate.parse(raw, dateFormat));
    }

    /**
     * Sets the from date for the flights to be requested, more information: {@link #fromDate}
     *
     * @param fromDate the LocalDate representation of the date.
     */
    public FlightsBuilder from(@Nonnull final LocalDate fromDate) {
        this.fromDate = fromDate;
        return this;
    }


    /**
     * Sets the from date for the flights to be requested, more information: {@link #fromDate}
     *
     * The expected format is "yyy-MM-dd".
     *
     * @param raw the string representation of the from date.
     */
    public FlightsBuilder to(@Nonnull final String raw) {
        return to(LocalDate.parse(raw, dateFormat));
    }

    /**
     * Sets the from date for the flights to be requested, more information: {@link #toDate}
     *
     * @param toDate the LocalDate representation of the date.
     */
    public FlightsBuilder to(@Nonnull final LocalDate toDate) {
        this.toDate = toDate;
        return this;
    }

    @Override
    public void prepare(URIBuilder builder) {
        builder.setPath("/public-flights/flights");

        if(getScheduleDate() != null) {
            builder.addParameter("scheduledate", getScheduleDate().format(dateFormat));
        }

        if(getScheduleTime() != null) {
            builder.addParameter("scheduletime", getScheduleTime().format(timeFormat));
        }

        if(getFlightName() != null) {
            builder.addParameter("flightname", getFlightName());
        }

        if(getFlightDirection() != null) {
            builder.addParameter("flightdirection", getFlightDirection().toString());
        }

        if(getAirline() != null) {
            builder.addParameter("airline", getAirline());
        }

        if(getNvlscode() != null) {
            builder.addParameter("nvlscode", getNvlscode());
        }

        if(getRoute() != null) {
            builder.addParameter("route", getRoute());
        }

        if(getFromDate() != null) {
            builder.addParameter("fromdate", getFromDate().format(dateFormat));
        }

        if(getToDate() != null) {
            builder.addParameter("todate", getToDate().format(dateFormat));
        }

        if(isIncludeDelays()) {
            builder.addParameter("includedelays", String.valueOf(true));
        }

    }

    @Override
    protected FlightsBuilder getThis() {
        return this;
    }

    LocalDate getScheduleDate() {
        return scheduleDate;
    }

    DateTimeFormatter getDateFormat() {
        return dateFormat;
    }

    LocalTime getScheduleTime() {
        return scheduleTime;
    }

    DateTimeFormatter getTimeFormat() {
        return timeFormat;
    }

    String getFlightName() {
        return flightName;
    }

    FlightDirection getFlightDirection() {
        return flightDirection;
    }

    String getAirline() {
        return airline;
    }

    String getNvlscode() {
        return nvlscode;
    }

    String getRoute() {
        return route;
    }

    boolean isIncludeDelays() {
        return includeDelays;
    }

    LocalDate getFromDate() {
        return fromDate;
    }

    LocalDate getToDate() {
        return toDate;
    }
}
