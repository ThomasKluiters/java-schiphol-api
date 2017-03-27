package nl.schiphol.api.builders;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thomas on 22-3-2017.
 */
public class SortBuilder {

    private final List<SortField> fields;

    private SortField sortField = null;

    /**
     * Constructs a SortBuilder with an empty list of fields.
     */
    public SortBuilder() {
        this(new ArrayList<SortField>());
    }

    /**
     * Constructs a SortBuilder with the given fields.
     *
     * @param fields the fields to sort by.
     */
    public SortBuilder(SortField...fields) {
        this(Arrays.asList(fields));
    }

    /**
     * Constructs a SortBuilder with the given fields.
     *
     * @param fields the fields to sort by.
     */
    public SortBuilder(List<SortField> fields) {
        this.fields = fields;
    }

    /**
     * Adds the field to sort on to the list of fields to sort on.
     *
     * @param name the name of the field to sort on
     */
    public SortBuilder field(@Nonnull final String name) {
        this.fields.add(new SortField(name));
        return this;
    }

    public SortBuilder ascending() {
        Iterables.getLast(getFields()).setAscending(true);
        return this;
    }

    public SortBuilder descending() {
        Iterables.getLast(getFields()).setAscending(false);
        return this;
    }

    public List<SortField> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return Joiner.on(",").join(getFields());
    }

    public static class SortField {

        private final String name;

        private boolean ascending;

        public SortField(String name) {
            this(name, true);
        }

        public SortField(String name, boolean ascending) {
            this.name = name;
            this.ascending = ascending;
        }

        String getName() {
            return name;
        }

        boolean isAscending() {
            return ascending;
        }

        public void setAscending(boolean ascending) {
            this.ascending = ascending;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SortField)) return false;

            SortField sortField = (SortField) o;

            if (ascending != sortField.ascending) return false;
            return name != null ? name.equals(sortField.name) : sortField.name == null;
        }

        @Override
        public String toString() {
            return (isAscending() ? "+" : "-") + getName();
        }
    }

}
