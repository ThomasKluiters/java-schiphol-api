package nl.schiphol.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Thomas on 22-3-2017.
 */
public class SchipholTest {

    private final String testId = "myTestId";

    private final String testKey = "myTestKey";

    private final SchipholCredentials testCredentials = new SchipholCredentials(testId, testKey);

    @Test
    public void defaultConstructorTest() {
        final Schiphol schiphol = new Schiphol(testId, testKey);

        assertEquals(testId, schiphol.getApplicationId());
        assertEquals(testKey, schiphol.getApplicationKey());
    }

    @Test
    public void manualCredentialsTest() {
        final Schiphol schiphol = new Schiphol(testCredentials);

        assertEquals(testId, schiphol.getApplicationId());
        assertEquals(testKey, schiphol.getApplicationKey());
    }

}
