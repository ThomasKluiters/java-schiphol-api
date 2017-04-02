package nl.schiphol.api.models.airlines;

import lombok.Data;
import nl.schiphol.api.models.Response;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thomas on 22-3-2017.
 */
@Data
public class Airlines extends Response<Airlines> implements Iterable<Airline> {

    private List<Airline> airlines;

    Airlines() { }

    @Override
    @Nonnull
    public Iterator<Airline> iterator() {
        return getAirlines().iterator();
    }

    @Override
    protected Airlines get() {
        return this;
    }
}
