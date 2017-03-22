package nl.schiphol.api.builders;

/**
 * Created by Thomas on 22-3-2017.
 */
public abstract class RequestBuilder {

    /**
     * Resource version.
     */
    private String resourceVersion;

    private String appId;

    private String appKey;

    /**
     * Page number.
     */
    private long page;

    public RequestBuilder resourceVersion(final String resourceVersion) {
        this.resourceVersion = resourceVersion;
        return this;
    }

    public RequestBuilder page(final long page) {
        this.page = page;
        return this;
    }

}
