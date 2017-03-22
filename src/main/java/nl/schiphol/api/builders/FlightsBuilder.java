package nl.schiphol.api.builders;

import com.sun.istack.internal.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightsBuilder extends RequestBuilder {

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

    enum FlightDirection {
        ARRIVING,
        DEPARTING
    }

    /**
     * The direction of the flight, more information: {@link #flightDirection}
     *
     * @param raw a String with either "A" or "B".
     */
    public FlightsBuilder direction(@NotNull final String raw) {
        if(raw.length() != 1) {
            throw new IllegalArgumentException();
        }
        return direction(raw.charAt(0));
    }

    /**
     * The direction of the flight, more information: {@link #flightDirection}
     *
     * @param raw a character with either the value 'A' or 'D'
     */
    public FlightsBuilder direction(@NotNull final char raw) {
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
     * The direction of the flight, more information: {@link #flightDirection}
     *
     * @param direction the FlightDirection of the flight.
     */
    public FlightsBuilder direction(@NotNull final FlightDirection direction) {
        this.flightDirection = direction;
        return this;
    }

    /**
     * Sets the from date for the flights to be requested, more information: {@link #fromDate}
     *
     * The expected format is "yyy-MM-dd".
     *
     * @param raw the string representation of the from date.
     */
    public FlightsBuilder from(@NotNull final String raw) {
        return from(LocalDate.parse(raw, dateFormat));
    }

    /**
     * Sets the from date for the flights to be requested, more information: {@link #fromDate}
     *
     * @param fromDate the LocalDate representation of the date.
     */
    public FlightsBuilder from(@NotNull final LocalDate fromDate) {
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
    public FlightsBuilder to(@NotNull final String raw) {
        return from(LocalDate.parse(raw, dateFormat));
    }

    /**
     * Sets the from date for the flights to be requested, more information: {@link #toDate}
     *
     * @param toDate the LocalDate representation of the date.
     */
    public FlightsBuilder to(@NotNull final LocalDate toDate) {
        this.toDate = toDate;
        return this;
    }

}
