package nl.schiphol.api.builders;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Thomas on 22-3-2017.
 */
public class SortBuilderTest {

    private final SortField simpleAscendingField = new SortField("simple", true);

    private final SortField simpleDescendingField = new SortField("simple", false);

    @Test
    public void varargConstructorTest() {
        SortBuilder builder = new SortBuilder(simpleAscendingField, simpleDescendingField);

        assertEquals(Arrays.asList(simpleAscendingField, simpleDescendingField), builder.getFields());
    }

    @Test
    public void listConstructorTest() {
        final List<SortField> list = new ArrayList<SortField>();
        list.add(simpleAscendingField);
        list.add(simpleDescendingField);

        SortBuilder builder = new SortBuilder(list);

        assertEquals(Arrays.asList(simpleAscendingField, simpleDescendingField), builder.getFields());
    }

    @Test
    public void addSortFieldTest() {
        SortBuilder builder = new SortBuilder();
        builder.field("simple").ascending();

        assertEquals(Collections.singletonList(simpleAscendingField), builder.getFields());
    }

    /**
     * Ensure that the order in which we add fields to the builder matters.
     */
    @Test
    public void orderTest() {
        SortBuilder builder = new SortBuilder();
        builder.field("simple").ascending()
               .field("simple").descending();

        assertEquals(Arrays.asList(simpleAscendingField, simpleDescendingField), builder.getFields());
    }

    @Test
    public void toStringSingleFieldTest() {
        SortBuilder builder = new SortBuilder(Collections.singletonList(simpleAscendingField));
        final String string = builder.toString();

        assertEquals(simpleAscendingField.toString(), string);
    }

    @Test
    public void toStringMultipleFieldTest() {
        SortBuilder builder = new SortBuilder(Arrays.asList(simpleAscendingField, simpleDescendingField));
        final String string = builder.toString();

        assertEquals(simpleAscendingField.toString() + "," + simpleDescendingField.toString(), string);
    }

}
