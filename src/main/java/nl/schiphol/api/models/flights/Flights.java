package nl.schiphol.api.models.flights;

import nl.schiphol.api.models.Response;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thomas on 22-3-2017.
 */
public class Flights extends Response<Flights> implements Iterable<Flight> {

    private List<Flight> flights;

    private String schemaVersion;

    Flights() { }

    @Nonnull
    public List<Flight> getFlights() {
        return flights;
    }

    @Nonnull
    public String getSchemaVersion() {
        return schemaVersion;
    }

    @Override
    @Nonnull
    public Iterator<Flight> iterator() {
        return getFlights().iterator();
    }

    @Override
    protected Flights get() {
        return this;
    }
}
