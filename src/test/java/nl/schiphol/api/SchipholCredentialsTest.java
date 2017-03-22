package nl.schiphol.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Thomas on 22-3-2017.
 */
public class SchipholCredentialsTest {

    private final String testId = "myTestId";

    private final String testKey = "myTestKey";

    private final SchipholCredentials testCredentials = new SchipholCredentials(testId, testKey);

    @Test
    public void constructorTest() {
        assertEquals(testId, testCredentials.getId());
        assertEquals(testKey, testCredentials.getKey());
    }

    @Test
    public void equalsTest() {
        assertEquals(testCredentials, new SchipholCredentials(testId, testKey));
    }

}
