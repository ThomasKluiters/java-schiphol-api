package nl.schiphol.api.models.flights;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightAircraftType {

    private String iatamain;

    private String iatasub;

    FlightAircraftType() { }

    public String getIatamain() {
        return iatamain;
    }

    public String getIatasub() {
        return iatasub;
    }
}
