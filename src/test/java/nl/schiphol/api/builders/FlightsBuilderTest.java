package nl.schiphol.api.builders;

import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightsBuilderTest extends RequestBuilderTest<FlightsBuilder> {

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
    public void includeDelayedTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.includeDelayed();

        assertEquals(true, Boolean.valueOf(builder.getParameter("includedelays")));
    }

    @Test
    public void verifyIncludeDelayedFlightsTest() throws IOException {
        mockedBuilder
                .includeDelayed()
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIParameterMatcher("includedelays", "true")));
    }

    @Test
    public void rawScheduleDateTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.scheduleDate(rawTestDate);

        assertEquals(rawTestDate, builder.getParameter("scheduledate"));
    }

    @Test
    public void scheduleDateTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.scheduleDate(testDate);

        assertEquals(rawTestDate, builder.getParameter("scheduledate"));
    }

    @Test
    public void verifyScheduleDateParameterTest() throws IOException {
        mockedBuilder
                .scheduleDate(testDate)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIParameterMatcher("scheduledate", rawTestDate)));
    }

    @Test
    public void rawScheduleTimeTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.scheduleTime(rawTestTime);

        assertEquals(rawTestTime, builder.getParameter("scheduletime"));
    }


    @Test
    public void scheduleTimeTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.scheduleTime(testTime);

        assertEquals(rawTestTime, builder.getParameter("scheduletime"));
    }

    @Test
    public void verifyScheduleTimeParameterTest() throws IOException {
        mockedBuilder
                .scheduleTime(testTime)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIParameterMatcher("scheduletime", rawTestTime)));
    }

    @Test
    public void rawCharacterArrivingFlightDirectionTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.direction('A');

        assertEquals("A", builder.getParameter("flightdirection"));
    }

    @Test
    public void rawCharacterDepartingFlightDirectionTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.direction('D');

        assertEquals("D", builder.getParameter("flightdirection"));
    }

    @Test
    public void rawStringArrivingFlightDirectionTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.direction("A");

        assertEquals("A", builder.getParameter("flightdirection"));
    }

    @Test
    public void rawStringDepartingFlightDirectionTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.direction("D");

        assertEquals("D", builder.getParameter("flightdirection"));
    }

    @Test
    public void departingFlightDirection() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.direction(FlightsBuilder.FlightDirection.DEPARTING);

        assertEquals("D", builder.getParameter("flightdirection"));
    }

    @Test
    public void verifyFlightDirectionTest() throws IOException {
        mockedBuilder
                .direction(FlightsBuilder.FlightDirection.ARRIVING)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIParameterMatcher("flightdirection", "A")));
    }


    @Test
    public void fromDateRawTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.from(rawTestDate);

        assertEquals(rawTestDate, builder.getParameter("fromdate"));
    }

    @Test
    public void fromDateTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.from(testDate);

        assertEquals(rawTestDate, builder.getParameter("fromdate"));
    }

    @Test
    public void verifyFromDateTest() throws IOException {
        mockedBuilder
                .from(testDate)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIParameterMatcher("fromdate", rawTestDate)));
    }

    @Test
    public void toDateRawTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.to("2017-04-22");

        assertEquals(rawTestDate, builder.getParameter("todate"));
    }

    @Test
    public void toDateTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.to(testDate);

        assertEquals(rawTestDate, builder.getParameter("todate"));
    }

    @Test
    public void verifyToDateParameterTest() throws IOException {
        mockedBuilder
                .to(testDate)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIParameterMatcher("todate", rawTestDate)));
    }

    @Override
    FlightsBuilder getInstance() {
        return new FlightsBuilder();
    }
}
