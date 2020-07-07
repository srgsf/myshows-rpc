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
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShowsIT {

    public static final String COMMENT_TEXT = "test comment";
    public static final String UPDATE_TEST_COMMENT = "update test comment";
    private static Shows rpc;
    private static String userName;

    @BeforeAll
    static void setup() throws IOException {
        rpc = Utils.setupRpcClient().shows();
        userName = Utils.userCredentials().user;
    }

    @Test
    void getById() throws IOException {
        Response<Show> response = rpc.getById(
                new ShowRequest(1)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void getByExternalId() throws IOException {
        Response<Show> response = rpc.getByExternalId(
                new ShowExternalId(178710, "kinopoisk")
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void count() throws IOException {
        Response<Integer> response = rpc.count(
                new ShowSearchRequest(new ShowSearch().country("us"))
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void episode() throws IOException {
        Response<Episode> response = rpc.episode(
                new Identity(1384290)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void filters() throws IOException {
        Response<List<ShowFilter>> response = rpc.filters(
                new ShowSearchRequest(new ShowSearch().country("us"))
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void genres() throws IOException {
        Response<List<Genre>> response = rpc.genres().execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void get() throws IOException {
        Response<List<ShowSummary>> response = rpc.get(
                new ShowSearchRequestPaged(new ShowSearch().query("House")).page(0).pageSize(3)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(3, response.body().size());
    }

    @Test
    void showIds() throws IOException {
        Response<List<Integer>> response = rpc.showsIds(
                new ShowIdsRange(1).count(10)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(10, response.body().size());
    }

    @Test
    void searchByFile() throws IOException {
        Response<ShowSearchMatch> response = rpc.searchByFile(
                new FileName("house.s01e01.avi")
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void search() throws IOException {
        Response<List<Show>> response = rpc.search(
                new Query("House")
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void top() throws IOException {
        Response<List<ShowRank>> response = rpc.top(
                new TopShows().mode("all").count(10)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(10, response.body().size());
    }

    @Test
    @Order(1)
    void postComment() throws IOException {
        Response<Comment> response = rpc.postComment(
                new EpisodeComment(1, COMMENT_TEXT)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    @Order(2)
    void comments() throws IOException {
        Response<Comments> response = rpc.comments(
                new EpisodeId(1)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    @Order(3)
    void trackComments() throws IOException {
        Response<Boolean> response = rpc.trackComments(
                new TrackEpisodeComments(1, true)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    @Order(4)
    void translateComment() throws IOException {
        Response<String> response = rpc.translateComment(
                new CommentTranslate(1).language("en")
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    @Order(5)
    void updateComment() throws IOException {
        Response<Comments> responseComment = rpc.comments(
                new EpisodeId(1)).execute();
        Assertions.assertTrue(responseComment.isSuccessful());
        Assertions.assertNotNull(responseComment.body());
        responseComment.body().comments.stream()
                .filter(c -> COMMENT_TEXT.equals(c.comment) && userName.equals(c.user.login)).forEach(comment -> {
            try {
                Response<Boolean> response = rpc.updateComment(
                        new CommentEdit(comment.id, UPDATE_TEST_COMMENT)
                ).execute();
                Assertions.assertTrue(response.isSuccessful());
                Assertions.assertNotNull(response.body());
            } catch (IOException ex) {
                Assertions.fail();
            }
        });
    }

    @Test
    @Order(6)
    void viewComments() throws IOException {
        Response<Boolean> response = rpc.viewComments(
                new EpisodeId(1)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    @Order(7)
    void voteComment() throws IOException {
        Response<Integer> response = rpc.voteComment(
                new CommentVote(1, true)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        response = rpc.voteComment(
                new CommentVote(1,false)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    @Order(8)
    void deleteComment() throws IOException {
        Response<Comments> responseComment = rpc.comments(
                new EpisodeId(1)).execute();
        Assertions.assertTrue(responseComment.isSuccessful());
        Assertions.assertNotNull(responseComment.body());
        responseComment.body().comments.stream()
                .filter(c -> UPDATE_TEST_COMMENT.equals(c.comment) && userName.equals(c.user.login)).forEach(comment -> {
            try {
                Response<Boolean> response = rpc.deleteComment(
                        new CommentId(comment.id)
                ).execute();
                Assertions.assertTrue(response.isSuccessful());
                Assertions.assertNotNull(response.body());
            } catch (IOException ex) {
                Assertions.fail();
            }
        });
    }
}
