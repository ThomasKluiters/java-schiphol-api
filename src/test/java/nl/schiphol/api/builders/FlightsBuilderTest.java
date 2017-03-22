package nl.schiphol.api.builders;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightsBuilderTest {

    private final int testYear = 2017;

    private final int testMonth = 4;

    private final int testDay = 22;

    private final String rawTestDate = "2017-04-22";

    private final LocalDate testDate = LocalDate.of(testYear, testMonth, testDay);

    private final int testHour = 13;

    private final int testMinute = 37;

    private final String rawTestTime = "13:37";

    private final LocalTime testTime = LocalTime.of(testHour, testMinute);

    @Test
    public void notIncludeDelayedTest() {
        FlightsBuilder builder = new FlightsBuilder();

        assertEquals(false, builder.isIncludeDelays());
    }

    @Test
    public void includeDelayedTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.includeDelayed();

        assertEquals(true, builder.isIncludeDelays());
    }

    @Test
    public void rawScheduleDateTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.scheduleDate(rawTestDate);

        assertEquals(testDate, builder.getScheduleDate());
    }

    @Test
    public void scheduleDateTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.scheduleDate(testDate);

        assertEquals(testDate, builder.getScheduleDate());
    }

    @Test
    public void rawScheduleTimeTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.scheduleTime(rawTestTime);

        assertEquals(testTime, builder.getScheduleTime());
    }


    @Test
    public void scheduleTimeTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.scheduleTime(testTime);

        assertEquals(testTime, builder.getScheduleTime());
    }

    @Test
    public void rawCharacterArrivingFlightDirectionTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.direction('A');

        assertEquals(FlightsBuilder.FlightDirection.ARRIVING, builder.getFlightDirection());
    }

    @Test
    public void rawCharacterDepartingFlightDirectionTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.direction('D');

        assertEquals(FlightsBuilder.FlightDirection.DEPARTING, builder.getFlightDirection());
    }

    @Test
    public void rawStringArrivingFlightDirectionTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.direction("A");

        assertEquals(FlightsBuilder.FlightDirection.ARRIVING, builder.getFlightDirection());
    }

    @Test
    public void rawStringDepartingFlightDirectionTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.direction("D");

        assertEquals(FlightsBuilder.FlightDirection.DEPARTING, builder.getFlightDirection());
    }


    @Test
    public void fromDateRawTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.from(rawTestDate);

        assertEquals(testDate, builder.getFromDate());
    }

    @Test
    public void fromDateTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.from(testDate);

        assertEquals(testDate, builder.getFromDate());
    }

    @Test
    public void toDateRawTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.to("2017-04-22");

        assertEquals(testDate, builder.getToDate());
    }

    @Test
    public void toDateTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.to(testDate);

        assertEquals(testDate, builder.getToDate());
    }



}
