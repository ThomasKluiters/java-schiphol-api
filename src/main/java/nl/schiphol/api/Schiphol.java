package nl.schiphol.api;

import com.sun.istack.internal.NotNull;

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



}
