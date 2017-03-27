package nl.schiphol.api.models.aircraft;

import nl.schiphol.api.models.Response;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thomas on 22-3-2017.
 */
public class AircraftTypes extends Response<AircraftTypes> implements Iterable<AircraftType> {

    private List<AircraftType> aircraftTypes;

    private String schemaVersion;

    AircraftTypes() { }

    @Nonnull
    public List<AircraftType> getAircraftTypes() {
        return aircraftTypes;
    }

    @Nonnull
    public String getSchemaVersion() {
        return schemaVersion;
    }

    @Nonnull
    @Override
    public Iterator<AircraftType> iterator() {
        return getAircraftTypes().iterator();
    }
}
