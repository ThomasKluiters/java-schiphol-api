package nl.schiphol.api.builders;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;

/**
 * Created by Thomas on 28-3-2017.
 */
public class DestinationBuilderTest extends RequestBuilderTest<DestinationBuilder> {

    private final String testIATA = "iataTest";

    @Test
    public void IATAPathParameterTest() {
        DestinationBuilder builder = new DestinationBuilder();
        builder.IATA(testIATA);

        assertEquals(testIATA, builder.getPathParameter("iata"));
    }

    @Test
    public void verifyIATAPathParameterTest() throws IOException {
        mockedBuilder
                .IATA(testIATA)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIPathMatcher("/public-flights/destinations/" + testIATA)));
    }

    @Override
    DestinationBuilder getInstance() {
        return new DestinationBuilder();
    }
}
