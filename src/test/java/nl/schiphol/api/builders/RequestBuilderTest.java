package nl.schiphol.api.builders;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Thomas on 23-3-2017.
 */
public abstract class RequestBuilderTest {

    private final Long testPage = 1l;

    private final SortBuilder testSortBuilder = new SortBuilder(new SortBuilder.SortField("test", true));

    private final String testAppId = "testId";

    private final String testAppKey = "testKey";

    private final String testResourceVersion = "testVersion";

    HttpClient mockedHttpClient;

    FlightsBuilder mockedFlightsBuilder;

    abstract RequestBuilder getInstance();

    @Before
    public void setUp() throws Exception {
        mockedHttpClient = mock(HttpClient.class);

        mockedFlightsBuilder = new FlightsBuilder()
                .appId("")
                .appKey("")
            .withClient(mockedHttpClient);

        HttpResponse mockedHttpResponse = mock(HttpResponse.class);

        HttpEntity mockedHttpEntity = mock(HttpEntity.class);

        StatusLine mockedStatusLine = mock(StatusLine.class);

        when(mockedHttpClient.execute(any()))
                .thenReturn(mockedHttpResponse);

        when(mockedHttpResponse.getStatusLine())
                .thenReturn(mockedStatusLine);

        when(mockedStatusLine.getStatusCode())
                .thenReturn(200);

        when(mockedHttpResponse.getEntity())
                .thenReturn(mockedHttpEntity);

        when(mockedHttpEntity.getContent())
                .thenReturn(new ByteArrayInputStream("{}".getBytes()));
    }


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
    public void verifySortTest() throws IOException {
        RequestBuilder builder = getInstance();
        builder.sort(testSortBuilder);

        verify(mockedHttpClient).execute(argThat(new URIMatcher("sort", testSortBuilder.toString())));
    }

    @Test
    public void appIdTest() {
        RequestBuilder builder = getInstance();
        builder.appId(testAppId);

        assertEquals(testAppId, builder.getParameter("app_id"));
    }

    @Test
    public void verifyAppIdTest() throws IOException {
        RequestBuilder builder = getInstance();
        builder.appKey(testAppId);

        verify(mockedHttpClient).execute(argThat(new URIMatcher("app_id", testAppId)));
    }

    @Test
    public void appKeyTest() {
        RequestBuilder builder = getInstance();
        builder.appKey(testAppKey);

        assertEquals(testAppKey, builder.getParameter("app_key"));
    }

    @Test
    public void verifyAppKeyTest() throws IOException {
        RequestBuilder builder = getInstance();
        builder.appKey(testAppKey);

        verify(mockedHttpClient).execute(argThat(new URIMatcher("app_key", testAppKey)));
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

        URIMatcher(String name, String value) {
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
