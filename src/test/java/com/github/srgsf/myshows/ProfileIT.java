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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class ProfileIT {
    private static Profile rpc;
    private static String userName;

    @BeforeAll
    static void setup() throws IOException {
        rpc = Utils.setupRpcClient().profile();
        userName = Utils.userCredentials().user;
    }

    @Test
    void achievement() throws IOException {
        Response<Achievement> response = rpc.achievement(
                new AchievementRequest("new-school", "e91dcc2ec4")
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void achievements() throws IOException {
        Response<List<AchievementSummary>> response = rpc.achievements(
                new AchievementUserRequest()
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void counters() throws IOException {
        Response<Counters> response = rpc.counters().execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void episodeCommentsCount() throws IOException {
        Response<Integer> response = rpc.episodeCommentsCount(
                new UserLogin(userName)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void episodeComments() throws IOException {
        Response<List<Comment>> response = rpc.episodeComments(
                new UserLoginPaged().login(userName).pageSize(15)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void episode() throws IOException {
        Response<EpisodeInfo> response = rpc.episode(
                new EpisodeId(1)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void episodes() throws IOException {
        Response<List<EpisodeInfoSummary>> response = rpc.episodes(
                new ShowId(1)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void feed() throws IOException {
        Response<List<Feed>> response = rpc.feed(
                new UserLogin(userName)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void followers() throws IOException {
        Response<List<User>> response = rpc.followers(
                new UserLogin(userName)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void friendsFeed() throws IOException {
        Response<List<Feed>> response = rpc.friendsFeed().execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void friends() throws IOException {
        Response<List<User>> response = rpc.friends(
                new UserLogin(userName)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(1, response.body().size());
    }

    @Test
    void friendShip() throws IOException {
        Response<Relation> response = rpc.friendship(
                new UserLogin(userName)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void profile() throws IOException {
        Response<UserProfile> response = rpc.get(
                new UserLogin(userName)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void markComments() throws IOException {
        Response<Boolean> response = rpc.markCommentsAsViewed().execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void newComments() throws IOException {
        Response<List<NewComment>> response = rpc.newComments().execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void newCommentsReplies() throws IOException {
        Response<List<NewComment>> response = rpc.newCommentReplies().execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void newNewsCommentsReplies() throws IOException {
        Response<List<NewNewsComment>> response = rpc.newNewsCommentReplies().execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void newNewsComments() throws IOException {
        Response<List<NewNewsComment>> response = rpc.newNewsComments().execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void newsCommentsCount() throws IOException {
        Response<Integer> response = rpc.newsCommentsCount(
                new UserLogin(userName)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void newsComments() throws IOException {
        Response<List<NewsComment>> response = rpc.newsComments(
                new UserLoginPaged().login(userName)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void settings() throws IOException {
        Response<List<ProfileSettingInfo>> response = rpc.settings(
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void saveSettings() throws IOException {
        Response<Boolean> response = rpc.saveSettings(
                new Settings().add(new Setting("x", "y"))
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    void show() throws IOException {
        Response<ShowInfo> response = rpc.show(
                new ShowId(1)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void showStatuses() throws IOException {
        Response<List<ShowStatusSummary>> response = rpc.showStatuses(
                new ShowIds().add(1)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void shows() throws IOException {
        Response<List<UserShowSummary>> response = rpc.shows(
                new UserLogin(userName)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }
}
