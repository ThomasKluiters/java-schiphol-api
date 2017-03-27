package nl.schiphol.api.builders;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Thomas on 27-3-2017.
 */
public class AirlineBuilderTest extends RequestBuilderTest {

    private final String airlineCodeTest = "testCode";

    @Test
    public void airlineCodeTest() {
        AirlineBuilder builder = new AirlineBuilder();
        builder.airlineCode(airlineCodeTest);

        assertEquals(builder.getPathParameter("airlineCode"), airlineCodeTest);
    }

    @Override
    RequestBuilder getInstance() {
        return new AirlineBuilder();
    }

}
