package nl.schiphol.api;

import nl.schiphol.api.builders.*;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.annotation.Nonnull;

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
     * The HttpClient to perform requests with.
     */
    private final HttpClient httpClient;

    /**
     * Initializes a Schiphol object on which API requests can be made.
     *
     * It is necessary to supply  both the app id, and app key in order to perform an requests.
     *
     * @param applicationId your API app id.
     * @param applicationKey your API app key.
     * @param httpClient the HttpClient to perform requests with.
     */
    public Schiphol(String applicationId, String applicationKey, HttpClient httpClient) {
        this.applicationId = applicationId;
        this.applicationKey = applicationKey;
        this.httpClient = httpClient;
    }

    /**
     * Initializes a Schiphol object on which API requests can be made.
     *
     * It is necessary to supply  both the app id, and app key in order to perform an requests.
     *
     * @param applicationId your API app id
     * @param applicationKey your API app key
     */
    public Schiphol(@Nonnull final String applicationId, @Nonnull final String applicationKey) {
        this(applicationId, applicationKey, HttpClients.createDefault());
    }

    /**
     * @see #Schiphol(String, String)
     *
     * @param credentials your application credentials.
     */
    public Schiphol(@Nonnull SchipholCredentials credentials) {
        this(credentials.getId(), credentials.getKey());
    }

    String getApplicationId() {
        return applicationId;
    }

    String getApplicationKey() {
        return applicationKey;
    }

    private <T extends RequestBuilder<?, T>> T prepare(final T builder) {
        return builder
                .appId(applicationId)
                .appKey(applicationKey)
                .withClient(httpClient);
    }

    public FlightsBuilder flights() {
        return prepare(new FlightsBuilder());
    }

    public FlightBuilder flight() {
        return prepare(new FlightBuilder());
    }

    public AircraftBuilder aircraft() {
        return prepare(new AircraftBuilder());
    }

    public DestinationsBuilder destinations() {
        return prepare(new DestinationsBuilder());
    }

    public DestinationBuilder destination() {
        return prepare(new DestinationBuilder());
    }
}
