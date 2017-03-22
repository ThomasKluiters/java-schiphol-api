package nl.schiphol.api;

import com.sun.istack.internal.NotNull;
import nl.schiphol.api.builders.AircraftBuilder;
import nl.schiphol.api.builders.FlightsBuilder;

/**
 * Created by Thomas on 22-3-2017.
 */
public class Schiphol {

    /**
     * The API app id, must be supplied by the user to identify API requests.
     */
    private final String applicationId;

    /**
     * The API app key, the secret key used to authenticate API requests.
     */
    private final String applicationKey;

    /**
     * Initializes a Schiphol object on which API requests can be made.
     *
     * It is necessary to supply  both the app id, and app key in order to perform an requests.
     *
     * @param applicationId your API app id
     * @param applicationKey your API app key
     */
    public Schiphol(@NotNull final String applicationId, @NotNull final String applicationKey) {
        this.applicationId = applicationId;
        this.applicationKey = applicationKey;
    }

    /**
     * @see #Schiphol(String, String)
     *
     * @param credentials your application credentials.
     */
    public Schiphol(@NotNull SchipholCredentials credentials) {
        this(credentials.getId(), credentials.getKey());
    }

    String getApplicationId() {
        return applicationId;
    }

    String getApplicationKey() {
        return applicationKey;
    }

    public FlightsBuilder flights() {
        return (FlightsBuilder) new FlightsBuilder()
                .appId(applicationId)
                .appKey(applicationKey);
    }

    public AircraftBuilder aircraft() {
        return (AircraftBuilder) new AircraftBuilder()
                .appId(applicationId)
                .appKey(applicationKey);
    }

}
