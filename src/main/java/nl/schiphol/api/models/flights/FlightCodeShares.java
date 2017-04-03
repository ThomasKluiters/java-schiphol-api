package nl.schiphol.api.models.flights;

import lombok.Data;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

/**
 * Created by
 * Thomas on 22-3-2017.
 */
@Data
public class FlightCodeShares implements Iterable<String> {

    private List<String> codeshares;

    FlightCodeShares() { }

    @Override
    @Nonnull
    public Iterator<String> iterator() {
        return getCodeshares().iterator();
    }
}
