package nl.schiphol.api.builders;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Thomas on 22-3-2017.
 */
public class SortFieldTest {

    private String testName = "field";

    private boolean testOrder = false;

    @Test
    public void nameConstructorTest() {
        SortField field = new SortField(testName);

        assertEquals(testName, field.getName());
        assertTrue(field.isAscending());
    }

    @Test
    public void nameOrderConstructorTest() {
        SortField field = new SortField(testName, testOrder);

        assertEquals(testName, field.getName());
        assertEquals(testOrder, field.isAscending());
    }

    @Test
    public void toStringAscendingTest() {
        SortField field = new SortField(testName, true);

        assertEquals("+" + testName, field.toString());
    }

    @Test
    public void toStringDescendingTest() {
        SortField field = new SortField(testName, false);

        assertEquals("-" + testName, field.toString());
    }

}
