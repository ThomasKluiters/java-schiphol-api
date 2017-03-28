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
public abstract class RequestBuilderTest<T extends RequestBuilder<?, T>> {

    private final Long testPage = 1l;

    private final SortBuilder testSortBuilder = new SortBuilder(new SortBuilder.SortField("test", true));

    private final String testAppId = "testId";

    private final String testAppKey = "testKey";

    private final String testResourceVersion = "testVersion";

    HttpClient mockedHttpClient;

    T mockedBuilder;

    abstract T getInstance();

    @Before
    public void setUp() throws Exception {
        mockedHttpClient = mock(HttpClient.class);

        mockedBuilder = getInstance()
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
        mockedBuilder
                .sort(testSortBuilder)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIParameterMatcher("sort", testSortBuilder.toString())));
    }

    @Test
    public void appIdTest() {
        RequestBuilder builder = getInstance();
        builder.appId(testAppId);

        assertEquals(testAppId, builder.getParameter("app_id"));
    }

    @Test
    public void verifyAppIdTest() throws IOException {
        mockedBuilder
                .appId(testAppId)
                .execute();


        verify(mockedHttpClient).execute(argThat(new URIParameterMatcher("app_id", testAppId)));
    }

    @Test
    public void appKeyTest() {
        RequestBuilder builder = getInstance();
        builder.appKey(testAppKey);

        assertEquals(testAppKey, builder.getParameter("app_key"));
    }

    @Test
    public void verifyAppKeyTest() throws IOException {
        mockedBuilder
                .appKey(testAppKey)
                .execute();

        verify(mockedHttpClient).execute(argThat(new URIParameterMatcher("app_key", testAppKey)));
    }

    @Test
    public void resourceVersionTest() {
        RequestBuilder builder = getInstance();
        builder.resourceVersion(testResourceVersion);

        assertEquals(testResourceVersion, builder.getHeader("ResourceVersion"));
    }

    protected class URIPathMatcher extends ArgumentMatcher<HttpUriRequest> {

        private final String path;

        public URIPathMatcher(String path) {
            this.path = path;
        }

        @Override
        public boolean matches(Object o) {
            HttpUriRequest request = (HttpUriRequest)o;
            return new URIBuilder(request.getURI())
                    .getPath().equals(path);
        }
    }

    protected class URIParameterMatcher extends ArgumentMatcher<HttpUriRequest> {

        private final String name;

        private final String value;

        URIParameterMatcher(String name, String value) {
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
