package nl.schiphol.api.models.aircraft;

import lombok.Data;
import nl.schiphol.api.models.Response;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thomas on 22-3-2017.
 */
@Data
public class AircraftTypes extends Response<AircraftTypes> implements Iterable<AircraftType> {

    private List<AircraftType> aircraftTypes;

    private String schemaVersion;

    AircraftTypes() { }

    @Nonnull
    @Override
    public Iterator<AircraftType> iterator() {
        return getAircraftTypes().iterator();
    }

    @Override
    protected AircraftTypes get() {
        return this;
    }
}
