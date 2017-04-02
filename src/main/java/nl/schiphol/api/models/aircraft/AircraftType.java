package nl.schiphol.api.models.aircraft;

import lombok.Data;
import nl.schiphol.api.models.Response;

/**
 * Created by Thomas on 22-3-2017.
 */
@Data
public class AircraftType extends Response<AircraftType> {

    private String longDescription;

    private String shortDescription;

    private String iatamain;

    private String iatasub;

    AircraftType() { }

    @Override
    protected AircraftType get() {
        return this;
    }
}
