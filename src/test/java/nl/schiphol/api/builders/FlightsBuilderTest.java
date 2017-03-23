package nl.schiphol.api.builders;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.booleanThat;
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

        when(mockedHttpClient.execute(any()))
                .thenReturn(mockedHttpResponse);

        when(mockedHttpResponse.getEntity())
                .thenReturn(mockedHttpEntity);

        when(mockedHttpEntity.getContent())
                .thenReturn(is);
    }

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

        assertEquals(testDate, builder.getScheduleDate());
    }

    @Test
    public void scheduleDateTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.scheduleDate(testDate);

        assertEquals(testDate, builder.getScheduleDate());
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

        assertEquals(testTime, builder.getScheduleTime());
    }


    @Test
    public void scheduleTimeTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.scheduleTime(testTime);

        assertEquals(testTime, builder.getScheduleTime());
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
    public void departingFlightDirection() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.direction(FlightsBuilder.FlightDirection.DEPARTING);

        assertEquals(FlightsBuilder.FlightDirection.DEPARTING, builder.getFlightDirection());
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

        assertEquals(testDate, builder.getFromDate());
    }

    @Test
    public void fromDateTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.from(testDate);

        assertEquals(testDate, builder.getFromDate());
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

        assertEquals(testDate, builder.getToDate());
    }

    @Test
    public void toDateTest() {
        FlightsBuilder builder = new FlightsBuilder();
        builder.to(testDate);

        assertEquals(testDate, builder.getToDate());
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
