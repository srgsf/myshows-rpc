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

import com.github.srgsf.myshows.auth.AccessTokenProvider;
import com.github.srgsf.myshows.model.Achievement;
import com.github.srgsf.myshows.model.AchievementRequest;
import com.github.srgsf.myshows.model.Show;
import com.github.srgsf.myshows.model.ShowRequest;
import com.github.srgsf.myshows.rpc.RpcClient;
import com.github.srgsf.myshows.service.Profile;
import com.github.srgsf.myshows.service.Shows;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

public class RpcClientTest {

    private MockWebServer webServer;


    @BeforeEach
    void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
    }

    @AfterEach
    void tearDown() throws IOException {
        webServer.shutdown();
    }

    @Test
    void noAuthentication() throws IOException, InterruptedException {
        RpcClient client = new RpcClient.Builder()
                .baseUrl(webServer.url("/").toString())
                .build();
        Assertions.assertEquals(webServer.url("/"), client.getBaseUrl());
        callAchievement(client.profile());
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));

        client = client.newBuilder().build();
        Assertions.assertEquals(webServer.url("/"), client.getBaseUrl());
        callAchievement(client.profile());
        req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
    }

    @Test
    void authentication() throws IOException, InterruptedException {
        RpcClient client = new RpcClient.Builder()
                .baseUrl(webServer.url("/").toString())
                .tokenProvider(new AccessTokenProvider() {
                    @Override
                    public String getToken() throws IOException {
                        return "TOKEN";
                    }

                    @Override
                    public String peekToken() {
                        return "TOKEN";
                    }
                })
                .build();
        callAchievement(client.profile());
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        String authHeader = req.getHeader("Authorization");
        Assertions.assertNotNull(authHeader);
        Assertions.assertEquals("Bearer TOKEN", authHeader);
        client = client.newBuilder().build();
        callAchievement(client.profile());
        req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        authHeader = req.getHeader("Authorization");
        Assertions.assertNotNull(authHeader);
        Assertions.assertEquals("Bearer TOKEN", authHeader);
    }

    @Test
    void customAuthentication() throws IOException, InterruptedException {
        RpcClient client = new RpcClient.Builder()
                .baseUrl(webServer.url("/").toString())
                .tokenProvider(new AccessTokenProvider() {
                    @Override
                    public String getToken() throws IOException {
                        return "TOKEN";
                    }

                    @Override
                    public String peekToken() {
                        return "TOKEN";
                    }
                })
                .build();
        callAchievement(client.profile());
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        String authHeader = req.getHeader("Authorization");
        Assertions.assertNotNull(authHeader);
        Assertions.assertEquals("Bearer TOKEN", authHeader);
        client = client.newBuilder().useCustomAuthentication().build();
        callAchievement(client.profile());
        req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        authHeader = req.getHeader("Authorization");
        Assertions.assertNull(authHeader);
    }

    @Test
    void dates() throws IOException {
        RpcClient client = new RpcClient.Builder()
                .baseUrl(webServer.url("/").toString())
                .build();
        Show show = callShows(client.shows());
        Assertions.assertNotNull(show.started);
        Assertions.assertNotNull(show.ended);
        Assertions.assertNotNull(show.episodes.get(0).airDate);
        Assertions.assertNotNull(show.episodes.get(0).airDateUTC);

        client = client.newBuilder().build();
        show = callShows(client.shows());
        Assertions.assertNotNull(show.started);
        Assertions.assertNotNull(show.ended);
        Assertions.assertNotNull(show.episodes.get(0).airDate);
        Assertions.assertNotNull(show.episodes.get(0).airDateUTC);
    }


    private Show callShows(Shows shows) throws IOException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/show.json")));
        Response<Show> response = shows.getById(
                new ShowRequest(1)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        return response.body();
    }

    private void callAchievement(Profile profile) throws IOException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/achievementResponse.json")));
        Response<Achievement> response = profile.achievement(
                new AchievementRequest("alias", "key")
        ).execute();
        Assertions.assertNotNull(response.body());
    }
}
