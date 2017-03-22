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

    private SortBuilder sort;

    /**
     * Page number.
     */
    private long page;

    public RequestBuilder appId(final String appId) {
        this.appId = appId;
        return this;
    }

    public RequestBuilder appKey(final String appKey) {
        this.appKey = appKey;
        return this;
    }

    public RequestBuilder resourceVersion(final String resourceVersion) {
        this.resourceVersion = resourceVersion;
        return this;
    }

    public RequestBuilder page(final long page) {
        this.page = page;
        return this;
    }

    public RequestBuilder sort(final SortBuilder sort) {
        this.sort = sort;
        return this;
    }

}
