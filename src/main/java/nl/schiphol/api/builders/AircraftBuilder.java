package nl.schiphol.api.builders;

import nl.schiphol.api.models.aircraft.AircraftTypes;

import javax.annotation.Nonnull;

/**
 * Created by Thomas on 22-3-2017.
 */
public class AircraftBuilder extends RequestBuilder<AircraftTypes, AircraftBuilder> {

    public AircraftBuilder() {
        super(AircraftTypes.class, "/public-flights/aircrafttypes");
        resourceVersion("v1");
    }

    /**
     * Search for aircrafttypes with the giwven IATA main code.
     *
     * @param iatamain IATA main code.
     */
    public AircraftBuilder IATAMain(@Nonnull final String iatamain) {
        return addParameter("iatamain", iatamain);
    }

    /**
     * Search for aircrafttypes with the giwven IATA sub code.
     *
     * @param iatasub IATA sub code.
     */
    public AircraftBuilder IATASub(@Nonnull final String iatasub) {
        return addParameter("iatasub", iatasub);
    }

    @Override
    protected AircraftBuilder getThis() {
        return this;
    }
}
