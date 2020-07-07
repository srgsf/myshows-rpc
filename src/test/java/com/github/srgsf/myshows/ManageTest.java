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
import com.github.srgsf.myshows.rpc.RpcClient;
import com.github.srgsf.myshows.service.Manage;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

public class ManageTest {

    private static MockWebServer webServer;
    private static Manage rpc;
    private static RpcClient client;

    @BeforeAll
    static void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
        client = Utils.setupRpcClient(webServer.url("/").toString());
        rpc = client.manage();
    }

    @AfterAll
    static void tearDown() throws IOException {
        webServer.shutdown();
    }

    @Test
    void checkEpisode() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.checkEpisode(
                new CheckEpisode(1).rating(Rating.highest)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void unCheckEpisode() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.unCheckEpisode(
                new Identity(1)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void rateEpisode() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.rateEpisode(
                new RateItem(1, Rating.highest)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void rateShow() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.rateShow(
                new RateItem(1, Rating.highest)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void moveEpisodeDate() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.moveEpisodeDate(
                new MoveEpisodeDate(1, MoveEpisodeDate.Days.one)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void moveEpisodeDateError() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(402)
                .setBody(Utils.readFromFile("json/error.json")));
        Response<Boolean> response = rpc.moveEpisodeDate(
                new MoveEpisodeDate(1, MoveEpisodeDate.Days.one)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertFalse(response.isSuccessful());
        Assertions.assertNotNull(response.errorBody());
        RpcError error = client.error(response.errorBody());
        Assertions.assertNotNull(error);
        Assertions.assertEquals(402, error.code);
    }

    @Test
    void setShowStatus() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.setShowStatus(
                new ChangeShowStatus(1, ShowStatus.watching)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void syncEpisodes() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.syncEpisodes(
                new SyncEpisodes(1).addEpisodes(1).addEpisodes(2, 3)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void rateEpisodesBulk() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.rateEpisodesBulk(
                new RateEpisodesBulk().addLowest(1)
                        .addHigh(2)
                        .addAvg(3)
                        .addHighest(4).addLow(5)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void syncEpisodesDelta() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.syncEpisodesDelta(
                new SyncEpisodeDelta(1)
                        .addChecked(1).addChecked(2, 3)
                        .addUnChecked(4).addUnChecked(5, 6)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }


}
