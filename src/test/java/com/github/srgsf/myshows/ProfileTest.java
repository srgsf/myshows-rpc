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

import com.github.srgsf.myshows.model.*;
import com.github.srgsf.myshows.service.Profile;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class ProfileTest {

    private static MockWebServer webServer;
    private static Profile rpc;

    @BeforeAll
    static void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
        rpc = Utils.setupRpcClient(webServer.url("/").toString()).profile();
    }

    @AfterAll
    static void tearDown() throws IOException {
        webServer.shutdown();
    }

    @Test
    void achievement() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/achievementResponse.json")));
        Response<Achievement> response = rpc.achievement(
                new AchievementRequest("alias", "key")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void achievements() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/achievementsResponse.json")));
        Response<List<AchievementSummary>> response = rpc.achievements(
                new AchievementUserRequest()
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(5, response.body().size());
    }

    @Test
    void counters() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/counters.json")));
        Response<Counters> response = rpc.counters().execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void episodeCommentsCount() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/intResponse.json")));
        Response<Integer> response = rpc.episodeCommentsCount(
                new UserLogin("login")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void episodeComments() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/comments.json")));
        Response<List<Comment>> response = rpc.episodeComments(
                new UserLoginPaged().login("user").pageSize(15)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(15, response.body().size());
    }

    @Test
    void episode() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/episodeInfo.json")));
        Response<EpisodeInfo> response = rpc.episode(
                new EpisodeId(1)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void episodes() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/episodeInfoSummaryList.json")));
        Response<List<EpisodeInfoSummary>> response = rpc.episodes(
                new ShowId(1)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(176, response.body().size());
    }

    @Test
    void feed() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/feed.json")));
        Response<List<Feed>> response = rpc.feed(
                new UserLogin("user")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(25, response.body().size());
    }

    @Test
    void followers() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/usersSearchResponse.json")));
        Response<List<User>> response = rpc.followers(
                new UserLogin("user")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(2, response.body().size());
    }

    @Test
    void friendsFeed() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/feed.json")));
        Response<List<Feed>> response = rpc.friendsFeed(

        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(25, response.body().size());
    }

    @Test
    void friends() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/usersSearchResponse.json")));
        Response<List<User>> response = rpc.friends(
                new UserLogin("user")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(2, response.body().size());
    }

    @Test
    void friendShip() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/relation.json")));
        Response<Relation> response = rpc.friendship(
                new UserLogin("user")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void profile() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/userProfile.json")));
        Response<UserProfile> response = rpc.get(
                new UserLogin("user")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void markComments() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.markCommentsAsViewed().execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void newComments() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/newComments.json")));
        Response<List<NewComment>> response = rpc.newComments().execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());

    }

    @Test
    void newCommentsReplies() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/newComments.json")));
        Response<List<NewComment>> response = rpc.newCommentReplies().execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());

    }

    @Test
    void newNewsCommentsReplies() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/newNewsComments.json")));
        Response<List<NewNewsComment>> response = rpc.newNewsCommentReplies().execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());

    }

    @Test
    void newNewsComments() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/newNewsComments.json")));
        Response<List<NewNewsComment>> response = rpc.newNewsComments().execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void newsCommentsCount() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/intResponse.json")));
        Response<Integer> response = rpc.newsCommentsCount(
                new UserLogin("user")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void newsComments() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/newsComments.json")));
        Response<List<NewsComment>> response = rpc.newsComments(
                new UserLoginPaged().login("login")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void settings() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/settingsInfo.json")));
        Response<List<ProfileSettingInfo>> response = rpc.settings(
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void saveSettings() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.saveSettings(
                new Settings().add(new Setting("x", "y"))
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void show() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/showInfo.json")));
        Response<ShowInfo> response = rpc.show(
                new ShowId(1)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());

    }

    @Test
    void showStatuses() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/showStatuses.json")));
        Response<List<ShowStatusSummary>> response = rpc.showStatuses(
                new ShowIds().add(1)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());

    }

    @Test
    void shows() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/userShows.json")));
        Response<List<UserShowSummary>> response = rpc.shows(
                new UserLogin("login")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());

    }


}
