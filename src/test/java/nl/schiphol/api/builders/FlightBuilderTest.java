package nl.schiphol.api.builders;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;

/**
 * Created by Thomas on 28-3-2017.
 */
public class FlightBuilderTest extends RequestBuilderTest<FlightBuilder> {

    private final long testId = 1L;

    private final String testFlightName = "flightNameTest";

    @Test
    public void idPathParameterTest() {
        FlightBuilder builder = new FlightBuilder();
        builder.id(testId);

        assertEquals("1", builder.getPathParameter("id"));
    }

    @Test
    public void verifyIdPathParameterTest() throws IOException {
        mockedBuilder
                .id(testId)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIPathMatcher("/public-flights/flights/" + testId)));
    }

    @Test
    public void flightNamePathParameterTest() {
        FlightBuilder builder = new FlightBuilder();
        builder.flightName(testFlightName);

        assertEquals("/codeshares/" + testFlightName, builder.getPathParameter("flightName"));
    }

    @Test
    public void verifyIdAndFlightNamePathParametersTest() throws IOException {
        mockedBuilder
                .id(testId)
                .flightName(testFlightName)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIPathMatcher("/public-flights/flights/" + testId + "/codeshares/" + testFlightName)));
    }

    @Override
    FlightBuilder getInstance() {
        return new FlightBuilder();
    }
}
