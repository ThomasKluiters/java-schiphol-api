package nl.schiphol.api.builders;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicStatusLine;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightsBuilderTest extends RequestBuilderTest {

    private final int testYear = 2017;

    private final int testMonth = 4;

    private final int testDay = 22;

    private final String rawTestDate = "2017-04-22";

    private final LocalDate testDate = LocalDate.of(testYear, testMonth, testDay);

    private final int testHour = 13;

    private final int testMinute = 37;

    private final String rawTestTime = "13:37";

    private final LocalTime testTime = LocalTime.of(testHour, testMinute);

    private HttpClient mockedHttpClient;

    private FlightsBuilder mockedFlightsBuilder;

    @Before
    public void setUp() throws Exception {
        InputStream is = new FileInputStream("example_flights.json");

        mockedHttpClient = mock(HttpClient.class);

        mockedFlightsBuilder = new FlightsBuilder()
                .appId("")
                .appKey("")
                .withClient(mockedHttpClient);

        HttpResponse mockedHttpResponse = mock(HttpResponse.class);

        HttpEntity mockedHttpEntity = mock(HttpEntity.class);

        StatusLine mockedStatusLine = mock(StatusLine.class);

        when(mockedHttpClient.execute(any()))
                .thenReturn(mockedHttpResponse);

        when(mockedHttpResponse.getStatusLine())
                .thenReturn(mockedStatusLine);

        when(mockedStatusLine.getStatusCode())
                .thenReturn(200);

        when(mockedHttpResponse.getEntity())
                .thenReturn(mockedHttpEntity);

        when(mockedHttpEntity.getContent())
                .thenReturn(is);
    }

    @Test
    public void notIncludeDelayedTest() {
        FlightsBuilder builder = new FlightsBuilder();

        assertEquals(null, builder.getParameter("includedelays"));
    }

    @Test
    public void includeDelayedTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.includeDelayed();

        assertEquals(true, Boolean.valueOf(builder.getParameter("includedelays")));
    }

    @Test
    public void verifyIncludeDelayedFlightsTest() throws IOException {
        mockedFlightsBuilder
                .includeDelayed()
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIMatcher("includedelays", "true")));
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
        mockedFlightsBuilder
                .scheduleDate(testDate)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIMatcher("scheduledate", rawTestDate)));
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
        mockedFlightsBuilder
                .scheduleTime(testTime)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIMatcher("scheduletime", rawTestTime)));
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
        mockedFlightsBuilder
                .direction(FlightsBuilder.FlightDirection.ARRIVING)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIMatcher("flightdirection", "A")));
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
        mockedFlightsBuilder
                .from(testDate)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIMatcher("fromdate", rawTestDate)));
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
        mockedFlightsBuilder
                .to(testDate)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIMatcher("todate", rawTestDate)));
    }

    @Override
    RequestBuilder getInstance() {
        return new FlightBuilder();
    }
}
