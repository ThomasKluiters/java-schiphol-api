package nl.schiphol.api.models.airlines;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thomas on 22-3-2017.
 */
public class Airlines implements Iterable {

    private List<Airline> airlines;

    private String schemaVersion;

    Airlines() { }

    @Nonnull
    public List<Airline> getAirlines() {
        return airlines;
    }

    @Nonnull
    public String getSchemaVersion() {
        return schemaVersion;
    }

    @Override
    @Nonnull
    public Iterator iterator() {
        return getAirlines().iterator();
    }
}
