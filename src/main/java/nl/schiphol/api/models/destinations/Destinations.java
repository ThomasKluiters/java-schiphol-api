package nl.schiphol.api.models.destinations;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thomas on 22-3-2017.
 */
public class Destinations implements Iterable<Destination> {

    private List<Destination> destinations;

    private String schemaVersion;

    Destinations() { }

    @Nonnull
    public List<Destination> getDestinations() {
        return destinations;
    }

    @Nonnull
    public String getSchemaVersion() {
        return schemaVersion;
    }

    @Override
    @Nonnull
    public Iterator<Destination> iterator() {
        return getDestinations().iterator();
    }
}
