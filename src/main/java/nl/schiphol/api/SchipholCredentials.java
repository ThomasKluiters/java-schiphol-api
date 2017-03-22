package nl.schiphol.api;

/**
 * Created by Thomas on 22-3-2017.
 */
public class SchipholCredentials {

    /**
     * The app id.
     */
    private final String id;

    /**
     * The app key.
     */
    private final String key;

    /**
     * Initializes the Credentials object with the given key and id.
     *
     * @param id your app id.
     * @param key your app key.
     */
    public SchipholCredentials(String id, String key) {
        this.id = id;
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }
}
