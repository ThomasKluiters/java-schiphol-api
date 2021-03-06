package nl.schiphol.api.models.flights;

import lombok.Data;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thomas on 22-3-2017.
 */
@Data
public class FlightTransferPositions implements Iterable<Integer> {

    private List<Integer> transferPositions;

    FlightTransferPositions() { }

    @Override
    @Nonnull
    public Iterator<Integer> iterator() {
        return transferPositions.iterator();
    }
}
