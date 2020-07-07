/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.srgsf.myshows;

import com.github.srgsf.myshows.auth.AccessToken;
import com.github.srgsf.myshows.auth.Credentials;
import com.github.srgsf.myshows.auth.MyShowsAuthClient;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;

public class AuthClientTest {

    private static MockWebServer webServer;
    private static final Credentials clientCredentials = new Credentials("API_CLIENT_USER", "API_CLIENT_SECRET");
    private static MyShowsAuthClient client;


    @BeforeAll
    static void before() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
        webServer.setDispatcher(new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) {
                MockResponse response = new MockResponse().setResponseCode(400);
                HashMap<String, String> form = parseForm(request.getBody().clone().readUtf8());
                if ("password".equals(form.get("grant_type"))) {
                    response.setResponseCode(HttpURLConnection.HTTP_OK)
                            .setBody(Utils.readFromFile("json/authPassword.json"));
                } else if ("authorization_code".equals(form.get("grant_type"))) {
                    response.setResponseCode(HttpURLConnection.HTTP_OK)
                            .setBody(Utils.readFromFile("json/authCode.json"));
                } else if ("refresh_token".equals(form.get("grant_type"))) {
                    response.setResponseCode(HttpURLConnection.HTTP_OK)
                            .setBody(Utils.readFromFile("json/authRefresh.json"));
                }
                return response;
            }
        });
        client = new MyShowsAuthClient
                .Builder()
                .baseUrl(webServer.url("/").toString())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .clientCredentials(clientCredentials).build();
    }

    @AfterAll
    static void after() throws IOException {
        webServer.shutdown();
    }

    @Test
    void code() {
        try {
            String code = "code";
            String redirectUri = "urn::urn";
            Response<AccessToken> token = client.accessToken(code, redirectUri);
            if (!token.isSuccessful()) {
                Assertions.fail();
            }
            RecordedRequest request = webServer.takeRequest();
            HashMap<String, String> form = parseForm(request.getBody().readUtf8());
            testCommon(request, form);
            Assertions.assertEquals("urn%3A%3Aurn", form.get("redirect_uri"));
            Assertions.assertEquals(code, form.get("code"));
            AccessToken t = token.body();
            Assertions.assertNotNull(t);
            Assertions.assertEquals("03accessToken03", t.accessToken);
            Assertions.assertEquals("03refreshToken03", t.refreshToken);
            Assertions.assertEquals(1209600L, t.expiresIn.longValue());
            Assertions.assertEquals("Bearer", t.tokenType);
            Assertions.assertEquals("basic", t.scope);
        } catch (Exception ex) {
            ex.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void password() {
        try {
            Credentials userCredentials = new Credentials("MYSHOWS_CLIENT_USER", "MYSHOWS_CLIENT_PASSWORD");
            Response<AccessToken> token = client.accessToken(userCredentials);
            if (!token.isSuccessful()) {
                Assertions.fail();
            }
            RecordedRequest request = webServer.takeRequest();
            HashMap<String, String> form = parseForm(request.getBody().readUtf8());
            testCommon(request, form);
            Assertions.assertEquals(userCredentials.user, form.get("username"));
            Assertions.assertEquals(userCredentials.secret, form.get("password"));
            AccessToken t = token.body();
            Assertions.assertNotNull(t);
            Assertions.assertEquals("01accessToken01", t.accessToken);
            Assertions.assertEquals("01refreshToken01", t.refreshToken);
            Assertions.assertEquals(1209600L, t.expiresIn.longValue());
            Assertions.assertEquals("Bearer", t.tokenType);
            Assertions.assertEquals("basic", t.scope);
        } catch (Exception ex) {
            ex.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void refresh() {
        try {
            String refreshToken = "01refreshToken01";
            Response<AccessToken> token = client.refreshToken(refreshToken);
            if (!token.isSuccessful()) {
                Assertions.fail();
            }
            RecordedRequest request = webServer.takeRequest();
            HashMap<String, String> form = parseForm(request.getBody().readUtf8());
            testCommon(request, form);
            Assertions.assertEquals(refreshToken, form.get("refresh_token"));
            AccessToken t = token.body();
            Assertions.assertNotNull(t);
            Assertions.assertEquals("02accessToken02", t.accessToken);
            Assertions.assertEquals("02refreshToken02", t.refreshToken);
            Assertions.assertEquals(1L, t.expiresIn.longValue());
            Assertions.assertEquals("Bearer", t.tokenType);
            Assertions.assertEquals("basic", t.scope);
        } catch (Exception ex) {
            ex.printStackTrace();
            Assertions.fail();
        }
    }


    @Test
    void authUrl() {
        HttpUrl url = MyShowsAuthClient.authorizationRequestUrl("clientId", "urn::urn");
        Assertions.assertEquals("clientId", url.queryParameter("client_id"));
        Assertions.assertEquals("urn::urn", url.queryParameter("redirect_uri"));
        Assertions.assertEquals("code", url.queryParameter("response_type"));
        Assertions.assertEquals("/oauth/authorize", url.encodedPath());
        Assertions.assertEquals("myshows.me", url.host());
        Assertions.assertEquals("https", url.scheme());
    }


    private void testCommon(RecordedRequest request, HashMap<String, String> form) {
        Assertions.assertEquals("/token", request.getPath());
        Assertions.assertEquals("POST", request.getMethod());
        Assertions.assertEquals("application/x-www-form-urlencoded", request.getHeader("Content-Type"));
        Assertions.assertEquals("API_CLIENT_USER", form.get("client_id"));
        Assertions.assertEquals("API_CLIENT_SECRET", form.get("client_secret"));
    }

    private static HashMap<String, String> parseForm(String form) {
        HashMap<String, String> retVal = new HashMap<>();
        String[] pairs = form.split("&");
        for (String pair : pairs) {
            String[] fields = pair.split("=");
            retVal.put(fields[0], fields[1]);
        }
        return retVal;
    }
}
