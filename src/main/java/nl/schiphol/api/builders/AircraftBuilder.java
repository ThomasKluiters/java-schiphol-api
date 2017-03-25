package nl.schiphol.api.builders;

import nl.schiphol.api.models.aircraft.AircraftTypes;
import org.apache.http.client.utils.URIBuilder;

import javax.annotation.Nonnull;

/**
 * Created by Thomas on 22-3-2017.
 */
public class AircraftBuilder extends JsonRequestBuilder<AircraftTypes, AircraftBuilder> {

    /**
     * IATA main code.
     */
    private String iatamain;

    /**
     * IATA sub code.
     */
    private String iatasub;

    public AircraftBuilder() {
        super(AircraftTypes.class);

        this.resourceVersion("v1");
    }

    /**
     * Search for aircrafttypes with the giwven IATA main code.
     *
     * @param iatamain IATA main code.
     */
    public AircraftBuilder iatamain(@Nonnull final String iatamain) {
        this.iatamain = iatamain;
        return this;
    }

    /**
     * Search for aircrafttypes with the giwven IATA sub code.
     *
     * @param iatasub IATA sub code.
     */
    public AircraftBuilder iatasub(@Nonnull final String iatasub) {
        this.iatasub = iatasub;
        return this;
    }

    @Override
    protected AircraftBuilder getThis() {
        return this;
    }

    @Override
    protected void prepare(URIBuilder builder) {
        builder.setPath("/public-flights/aircrafttypes");

        if(getIatamain() != null) {
            builder.setParameter("iatamain", getIatamain());
        }

        if(getIatasub() != null) {
            builder.setParameter("iatasub", getIatasub());
        }
    }

    String getIatamain() {
        return iatamain;
    }

    String getIatasub() {
        return iatasub;
    }
}
