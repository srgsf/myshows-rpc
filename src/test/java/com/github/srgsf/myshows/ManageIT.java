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
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;
import java.net.HttpURLConnection;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ManageIT {

    private static Manage rpc;
    private static RpcClient client;

    @BeforeAll
    static void setup() throws IOException {
        client = Utils.setupRpcClient();
        rpc = client.manage();
    }

    @Test
    @Order(1)
    void checkEpisode() throws IOException {
        Response<Boolean> response = rpc.checkEpisode(
                new CheckEpisode(1).rating(Rating.high)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    @Order(2)
    void rateEpisode() throws IOException {
        Response<Boolean> response = rpc.rateEpisode(
                new RateItem(1, Rating.highest)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }


    @Test
    @Order(3)
    void setShowStatus() throws IOException {
        Response<Boolean> response = rpc.setShowStatus(
                new ChangeShowStatus(1, ShowStatus.watching)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    @Order(4)
    void rateShow() throws IOException {
        Response<Boolean> response = rpc.rateShow(
                new RateItem(1, Rating.highest)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    @Order(5)
    void moveEpisodeDate() throws IOException {
        Response<Boolean> response = rpc.moveEpisodeDate(
                new MoveEpisodeDate(1, MoveEpisodeDate.Days.one)).execute();
        Assertions.assertFalse(response.isSuccessful());
        Assertions.assertNotNull(response.errorBody());
        RpcError error = client.error(response.errorBody());
        Assertions.assertEquals(HttpURLConnection.HTTP_PAYMENT_REQUIRED, error.code);
    }


    @Test
    @Order(6)
    void syncEpisodes() throws IOException {
        Response<Boolean> response = rpc.syncEpisodes(
                new SyncEpisodes(1).addEpisodes(4).addEpisodes(2, 3)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    @Order(7)
    void rateEpisodesBulk() throws IOException {
        Response<Boolean> response = rpc.rateEpisodesBulk(
                new RateEpisodesBulk().addLowest(1)
                        .addHigh(2)
                        .addAvg(3)
                        .addHighest(4).addLow(5)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    @Order(8)
    void syncEpisodesDelta() throws IOException {
        Response<Boolean> response = rpc.syncEpisodesDelta(
                new SyncEpisodeDelta(1)
                        .addChecked(7).addChecked(8, 9)
                        .addUnChecked(1).addUnChecked(2, 3)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    @Order(9)
    void unCheckEpisode() throws IOException {
        Response<Boolean> response = rpc.unCheckEpisode(
                new Identity(7)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }
}
