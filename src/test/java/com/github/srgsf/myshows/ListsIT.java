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
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ListsIT {

    private static Lists rpc;

    @BeforeAll
    static void setup() throws IOException {
        rpc = Utils.setupRpcClient().lists();
    }

    @Test
    @Order(1)
    void addEpisode() throws IOException {
        Response<Boolean> response = rpc.addEpisode(
                new ModifyListRequest(ListName.favorites, 1)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    @Order(2)
    void addShow() throws IOException {
        Response<Boolean> response = rpc.addShow(
                new ModifyListRequest(ListName.favorites, 1)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    @Order(3)
    void shows() throws IOException {
        Response<List<ShowSummary>> response = rpc.shows(
                new ListRequest(ListName.favorites)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertFalse(response.body().isEmpty());
    }

    @Test
    @Order(4)
    void episodes() throws IOException {
        Response<List<EpisodeListItem>> response = rpc.episodes(
                new ListRequest(ListName.favorites)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertFalse(response.body().isEmpty());
    }

    @Test
    @Order(5)
    void removeEpisode() throws IOException {
        Response<Boolean> response = rpc.removeEpisode(
                new ModifyListRequest(ListName.favorites, 1)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }



    @Test
    @Order(6)
    void removeShow() throws IOException {
        Response<Boolean> response = rpc.removeShow(
                new ModifyListRequest(ListName.favorites, 1)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }


}
