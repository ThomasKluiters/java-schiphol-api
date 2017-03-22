package nl.schiphol.api.builders;

/**
 * Created by Thomas on 22-3-2017.
 */
public class SortField {

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
