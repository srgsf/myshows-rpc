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
import com.github.srgsf.myshows.service.Shows;
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

public class ShowsTest {

    private static MockWebServer webServer;
    private static Shows rpc;

    @BeforeAll
    static void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
        rpc = Utils.setupRpcClient(webServer.url("/").toString()).shows();
    }

    @AfterAll
    static void tearDown() throws IOException {
        webServer.shutdown();
    }

    @Test
    void getById() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/show.json")));
        Response<Show> response = rpc.getById(
                new ShowRequest(1)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void getByExternalId() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/show.json")));
        Response<Show> response = rpc.getByExternalId(
                new ShowExternalId(178710, "kinopoisk")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void count() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/intResponse.json")));
        Response<Integer> response = rpc.count(
                new ShowSearchRequest(new ShowSearch().country("us"))
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void episode() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/episode.json")));
        Response<Episode> response = rpc.episode(
                new Identity(1384290)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void filters() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/showFilters.json")));
        Response<List<ShowFilter>> response = rpc.filters(
                new ShowSearchRequest(new ShowSearch().country("us"))
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void genres() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/genres.json")));
        Response<List<Genre>> response = rpc.genres().execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void get() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/showSummaries.json")));
        Response<List<ShowSummary>> response = rpc.get(
                new ShowSearchRequestPaged(new ShowSearch().query("House")).page(0).pageSize(3)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(3, response.body().size());
    }

    @Test
    void showIds() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/intList.json")));
        Response<List<Integer>> response = rpc.showsIds(
                new ShowIdsRange(1).count(10)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(10, response.body().size());
    }

    @Test
    void searchByFile() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/searchMatch.json")));
        Response<ShowSearchMatch> response = rpc.searchByFile(
                new FileName("house.md.s01e01.avi")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void search() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/searchResult.json")));
        Response<List<Show>> response = rpc.search(
                new Query("House")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void top() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/top.json")));
        Response<List<ShowRank>> response = rpc.top(
                new TopShows().mode("all").count(10)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(10, response.body().size());
    }

    @Test
    void postComment() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/episodeComment.json")));
        Response<Comment> response = rpc.postComment(
                new EpisodeComment(1, "test comment")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void comments() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/episodeComments.json")));
        Response<Comments> response = rpc.comments(
                new EpisodeId(1)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void trackComments() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.trackComments(
                new TrackEpisodeComments(1, true)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void translateComment() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/translate.json")));
        Response<String> response = rpc.translateComment(
                new CommentTranslate(1).language("en")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void updateComment() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.updateComment(
                new CommentEdit(1, "update")
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void viewComments() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.viewComments(
                new EpisodeId(1)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void voteComment() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/intResponse.json")));
        Response<Integer> response = rpc.voteComment(
                new CommentVote(1, true)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void deleteComment() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.deleteComment(
                new CommentId(1)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }
}
