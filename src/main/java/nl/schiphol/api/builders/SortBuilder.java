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



}
