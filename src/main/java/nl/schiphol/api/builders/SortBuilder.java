package nl.schiphol.api.builders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thomas on 22-3-2017.
 */
public class SortBuilder {

    private final List<String> fields;

    /**
     * Constructs a SortBuilder with an empty list of fields.
     */
    public SortBuilder() {
        this(new ArrayList<String>());
    }

    /**
     * Constructs a SortBuilder with the given fields.
     *
     * @param fields the fields to sort by.
     */
    public SortBuilder(String...fields) {
        this(Arrays.asList(fields));
    }

    /**
     * Constructs a SortBuilder with the given fields.
     *
     * @param fields the fields to sort by.
     */
    public SortBuilder(List<String> fields) {
        this.fields = fields;
    }

    /**
     * Adds the field to sort on to the list of fields to sort on.
     *
     * @param field the additional field to sort on.
     */
    public SortBuilder field(final String field) {
        this.fields.add(field);
        return this;
    }

}
