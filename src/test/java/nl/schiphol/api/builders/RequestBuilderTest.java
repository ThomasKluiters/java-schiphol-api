package nl.schiphol.api.builders;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import static org.junit.Assert.assertEquals;

/**
 * Created by Thomas on 23-3-2017.
 */
public abstract class RequestBuilderTest {

    private final Long testPage = 1l;

    private final SortBuilder testSortBuilder = new SortBuilder(new SortBuilder.SortField("test", true));

    private final String testAppId = "testId";

    private final String testAppKey = "testKey";

    private final String testResourceVersion = "testVersion";


    abstract RequestBuilder getInstance();

    @Test
    public void pageTest() {
        RequestBuilder builder = getInstance();
        builder.page(testPage);

        assertEquals(testPage, Long.valueOf(builder.getParameter("page")));
    }

    @Test
    public void sortTest() {
        RequestBuilder builder = getInstance();
        builder.sort(testSortBuilder);

        assertEquals(testSortBuilder.toString(), builder.getParameter("sort"));
    }

    @Test
    public void appIdTest() {
        RequestBuilder builder = getInstance();
        builder.appId(testAppId);

        assertEquals(testAppId, builder.getParameter("app_id"));
    }

    @Test
    public void appKeyTest() {
        RequestBuilder builder = getInstance();
        builder.appKey(testAppKey);

        assertEquals(testAppKey, builder.getParameter("app_key"));
    }

    @Test
    public void resourceVersionTest() {
        RequestBuilder builder = getInstance();
        builder.resourceVersion(testResourceVersion);

        assertEquals(testResourceVersion, builder.getHeader("ResourceVersion"));
    }

    protected class URIMatcher extends ArgumentMatcher<HttpUriRequest> {

        private final String name;

        private final String value;

        public URIMatcher(String name, String value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public boolean matches(Object o) {
            HttpUriRequest request = (HttpUriRequest)o;
            return new URIBuilder(request.getURI())
                    .getQueryParams()
                    .contains(new BasicNameValuePair(name, value));
        }
    }

}
