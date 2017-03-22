package nl.schiphol.api.builders;

/**
 * Created by Thomas on 22-3-2017.
 */
public abstract class RequestBuilder<T> {

    /**
     * Resource version.
     */
    private String resourceVersion = "v3";

    private String appId;

    private String appKey;

    private SortBuilder sort;

    /**
     * Page number.
     */
    private Long page = null;

    public RequestBuilder<T> appId(final String appId) {
        this.appId = appId;
        return this;
    }

    public RequestBuilder<T> appKey(final String appKey) {
        this.appKey = appKey;
        return this;
    }

    public RequestBuilder<T> resourceVersion(final String resourceVersion) {
        this.resourceVersion = resourceVersion;
        return this;
    }

    public RequestBuilder<T> page(final long page) {
        this.page = page;
        return this;
    }

    public RequestBuilder<T> sort(final SortBuilder sort) {
        this.sort = sort;
        return this;
    }

    public abstract T execute();

    String getResourceVersion() {
        return resourceVersion;
    }

    String getAppId() {
        return appId;
    }

    String getAppKey() {
        return appKey;
    }

    SortBuilder getSort() {
        return sort;
    }

    Long getPage() {
        return page;
    }
}
