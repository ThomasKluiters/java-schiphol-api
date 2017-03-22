package nl.schiphol.api.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.NotNull;
import nl.schiphol.api.builders.aircraft.AircraftTypes;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Thomas on 22-3-2017.
 */
public class AircraftBuilder extends RequestBuilder<AircraftTypes> {

    /**
     * IATA main code.
     */
    private String iatamain;

    /**
     * IATA sub code.
     */
    private String iatasub;

    public AircraftBuilder() {
        this.resourceVersion("v1");
    }

    /**
     * Search for aircrafttypes with the giwven IATA main code.
     *
     * @param iatamain IATA main code.
     */
    public AircraftBuilder iatamain(@NotNull final String iatamain) {
        this.iatamain = iatamain;
        return this;
    }

    /**
     * Search for aircrafttypes with the giwven IATA sub code.
     *
     * @param iatasub IATA sub code.
     */
    public AircraftBuilder iatasub(@NotNull final String iatasub) {
        this.iatasub = iatasub;
        return this;
    }

    @Override
    protected AircraftTypes process(InputStream is) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(is, AircraftTypes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
