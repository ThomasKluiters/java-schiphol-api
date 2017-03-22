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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchipholCredentials)) return false;

        SchipholCredentials that = (SchipholCredentials) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return key != null ? key.equals(that.key) : that.key == null;

    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }
}
