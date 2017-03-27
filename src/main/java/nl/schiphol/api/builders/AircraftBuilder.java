package nl.schiphol.api.builders;

import nl.schiphol.api.models.aircraft.AircraftTypes;

import javax.annotation.Nonnull;

/**
 * Created by Thomas on 22-3-2017.
 */
public class AircraftBuilder extends RequestBuilder<AircraftTypes, AircraftBuilder> {

    public AircraftBuilder() {
        super(AircraftTypes.class, "/public-flights/aircrafttypes");

        this.resourceVersion("v1");
    }

    /**
     * Search for aircrafttypes with the giwven IATA main code.
     *
     * @param iatamain IATA main code.
     */
    public AircraftBuilder iatamain(@Nonnull final String iatamain) {
        addParameter("iatamain", iatamain);
        return this;
    }

    /**
     * Search for aircrafttypes with the giwven IATA sub code.
     *
     * @param iatasub IATA sub code.
     */
    public AircraftBuilder iatasub(@Nonnull final String iatasub) {
        addParameter("iatasub", iatasub);
        return this;
    }

    @Override
    protected AircraftBuilder getThis() {
        return this;
    }
}
