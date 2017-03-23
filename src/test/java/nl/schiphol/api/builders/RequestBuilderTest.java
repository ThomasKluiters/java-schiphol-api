package nl.schiphol.api.builders;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Thomas on 23-3-2017.
 */
public abstract class RequestBuilderTest {

    private final Long testPage = 1l;

    private final SortBuilder testSortBuilder = new SortBuilder(new SortField("test", true));

    private final String testAppId = "testId";

    private final String testAppKey = "testKey";

    private final String testResourceVersion = "testVersion";


    abstract RequestBuilder getInstance();

    @Test
    public void pageTest() {
        RequestBuilder builder = getInstance();
        builder.page(testPage);

        assertEquals(testPage, builder.getPage());
    }

    @Test
    public void sortTest() {
        RequestBuilder builder = getInstance();
        builder.sort(testSortBuilder);

        assertEquals(testSortBuilder.getFields(), builder.getSort().getFields());
    }

    @Test
    public void appIdTest() {
        RequestBuilder builder = getInstance();
        builder.appId(testAppId);

        assertEquals(testAppId, builder.getAppId());
    }

    @Test
    public void appKeyTest() {
        RequestBuilder builder = getInstance();
        builder.appKey(testAppKey);

        assertEquals(testAppKey, builder.getAppKey());
    }

    @Test
    public void resourceVersionTest() {
        RequestBuilder builder = getInstance();
        builder.resourceVersion(testResourceVersion);

        assertEquals(testResourceVersion, builder.getResourceVersion());
    }

}
