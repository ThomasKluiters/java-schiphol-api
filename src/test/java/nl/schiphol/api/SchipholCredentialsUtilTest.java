package nl.schiphol.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Thomas on 22-3-2017.
 */
public class SchipholCredentialsUtilTest {

    private final String testId = "myTestId";

    private final String testKey = "myTestKey";

    @Test
    public void testLoadFrom() {
        SchipholCredentials credentials = SchipholCredentialsUtil.loadFrom("test_secrets.json");

        assertEquals(testId, credentials.getId());
        assertEquals(testKey, credentials.getKey());
    }

}
