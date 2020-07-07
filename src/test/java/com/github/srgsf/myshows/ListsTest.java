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
import com.github.srgsf.myshows.service.Lists;
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

public class ListsTest {

    private static MockWebServer webServer;
    private static Lists rpc;

    @BeforeAll
    static void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
        rpc = Utils.setupRpcClient(webServer.url("/").toString()).lists();
    }

    @AfterAll
    static void tearDown() throws IOException {
        webServer.shutdown();
    }

    @Test
    void addEpisode() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.addEpisode(
                new ModifyListRequest(ListName.favorites, 1)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void removeEpisode() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.removeEpisode(
                new ModifyListRequest(ListName.favorites, 1)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void addShow() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.addShow(
                new ModifyListRequest(ListName.favorites, 1)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void removeShow() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/trueResponse.json")));
        Response<Boolean> response = rpc.removeShow(
                new ModifyListRequest(ListName.favorites, 1)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void shows() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/showsListResponse.json")));
        Response<List<ShowSummary>> response = rpc.shows(
                new ListRequest(ListName.favorites)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertFalse(response.body().isEmpty());
    }

    @Test
    void episodes() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.readFromFile("json/episodeListResponse.json")));
        Response<List<EpisodeListItem>> response = rpc.episodes(
                new ListRequest(ListName.favorites)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertFalse(response.body().isEmpty());
    }


}
