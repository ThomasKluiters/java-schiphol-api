package nl.schiphol.api.builders;

import nl.schiphol.api.models.flights.Flights;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightsBuilder extends RequestBuilder<Flights, FlightsBuilder> {

    /**
     * The format for the scheduled date.
     */
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * The format for the scheduled time.
     */
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

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
        super(Flights.class, "/public-flights/flights");
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
        addParameter("scheduledate", scheduleDate.format(dateFormat));
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
        addParameter("scheduletime", scheduleTime.format(timeFormat));
        return this;
    }

    /**
     * Search for flights with the given flightname.
     *
     * @param flightName flight number as printed on the ticket.
     */
    public FlightsBuilder flightName(@Nonnull final String flightName) {
        addParameter("flightname", flightName);
        return this;
    }

    /**
     * Search for flights going in the given direction.
     *
     * The direction of the flight.
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
     *
     * @param direction the FlightDirection of the flight.
     */
    public FlightsBuilder direction(@Nonnull final FlightDirection direction) {
        addParameter("flightdireection", direction.toString());
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
        addParameter("airline", airline);
        return this;
    }

    /**
     * Search for flights with the given NVLS code.
     *
     * @param nvlscode NVLS code of a airliner.
     */
    public FlightsBuilder nvlsCode(@Nonnull final String nvlscode) {
        addParameter("nvlscode", nvlscode);
        return this;
    }

    /**
     * Search for flights with the given route.
     *
     * @param route the IATA or ICAO code of airport in route, comma separated.
     */
    public FlightsBuilder route(final String route) {
        addParameter("route", route);
        return this;
    }

    /**
     * Includes delayed flights in the results.
     */
    public FlightsBuilder includeDelayed() {
        addParameter("includedelays", "true");
        return this;
    }

    /**
     * Sets the from date for the flights to be requested.
     *
     * The expected format is "yyy-MM-dd".
     *
     * @param raw the string representation of the from date.
     */
    public FlightsBuilder from(@Nonnull final String raw) {
        return from(LocalDate.parse(raw, dateFormat));
    }

    /**
     * Sets the from date for the flights to be requested.
     *
     * @param fromDate the LocalDate representation of the date.
     */
    public FlightsBuilder from(@Nonnull final LocalDate fromDate) {
        addParameter("fromdate", fromDate.format(dateFormat));
        return this;
    }


    /**
     * Sets the from date for the flights to be requested.
     *
     * The expected format is "yyy-MM-dd".
     *
     * @param raw the string representation of the from date.
     */
    public FlightsBuilder to(@Nonnull final String raw) {
        return to(LocalDate.parse(raw, dateFormat));
    }

    /**
     * Sets the from date for the flights to be requested.
     *
     * @param toDate the LocalDate representation of the date.
     */
    public FlightsBuilder to(@Nonnull final LocalDate toDate) {
        addParameter("todate", toDate.format(dateFormat));
        return this;
    }

    @Override
    protected FlightsBuilder getThis() {
        return this;
    }
}
