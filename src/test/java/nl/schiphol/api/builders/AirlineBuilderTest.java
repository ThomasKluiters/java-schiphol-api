package nl.schiphol.api.builders;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;

/**
 * Created by Thomas on 27-3-2017.
 */
public class AirlineBuilderTest extends RequestBuilderTest<AirlineBuilder> {

    private final String airlineCodeTest = "testCode";

    @Test
    public void airlineCodePathParameterTest() {
        AirlineBuilder builder = new AirlineBuilder();
        builder.airlineCode(airlineCodeTest);

        assertEquals(builder.getPathParameter("airlineCode"), airlineCodeTest);
    }

    @Test
    public void verifyIATAPathParameterTest() throws IOException {
        mockedBuilder
                .airlineCode(airlineCodeTest)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIPathMatcher("/public-flights/airline/" + airlineCodeTest)));
    }

    @Override
    AirlineBuilder getInstance() {
        return new AirlineBuilder();
    }

}
