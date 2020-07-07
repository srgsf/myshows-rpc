/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.srgsf.myshows.service;

import com.github.srgsf.myshows.model.*;
import com.github.srgsf.myshows.rpc.RpcMethod;
import com.github.srgsf.myshows.rpc.Secured;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

import static com.github.srgsf.myshows.rpc.RpcClient.RPC_URL;

/**
 * documentation: <a href="https://api.myshows.me/shared/doc/#!/profile">profile</a>
 * @author srgsf
 * @since 0.1
 */
public interface Profile {

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.Achievement")
    Call<Achievement> achievement(@Body AchievementRequest param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.Achievements")
    Call<List<AchievementSummary>> achievements(@Body AchievementUserRequest param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.Counters")
    Call<Counters> counters();

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.EpisodeCommentsCount")
    Call<Integer> episodeCommentsCount(@Body UserLogin param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.EpisodeComments")
    Call<List<Comment>> episodeComments(@Body UserLoginPaged param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.Episode")
    Call<EpisodeInfo> episode(@Body EpisodeId param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.Episodes")
    Call<List<EpisodeInfoSummary>> episodes(@Body ShowId param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.Feed")
    Call<List<Feed>> feed(@Body UserLogin param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.Followers")
    Call<List<User>> followers(@Body UserLogin param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.FriendsFeed")
    Call<List<Feed>> friendsFeed();

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.Friends")
    Call<List<User>> friends(@Body UserLogin param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.Friendship")
    Call<Relation> friendship(@Body UserLogin param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.Get")
    Call<UserProfile> get(@Body UserLogin param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.MarkCommentsAsViewed")
    Call<Boolean> markCommentsAsViewed();

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.NewCommentReplies")
    Call<List<NewComment>> newCommentReplies();

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.NewComments")
    Call<List<NewComment>> newComments();

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.NewNewsCommentReplies")
    Call<List<NewNewsComment>> newNewsCommentReplies();

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.NewNewsComments")
    Call<List<NewNewsComment>> newNewsComments();

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.NewsCommentsCount")
    Call<Integer> newsCommentsCount(@Body UserLogin param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.NewsComments")
    Call<List<NewsComment>> newsComments(@Body UserLoginPaged param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.SaveSettings")
    Call<Boolean> saveSettings(@Body Settings param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.Settings")
    Call<List<ProfileSettingInfo>> settings();

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.Show")
    Call<ShowInfo> show(@Body ShowId param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.ShowStatuses")
    Call<List<ShowStatusSummary>> showStatuses(@Body ShowIds param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("profile.Shows")
    Call<List<UserShowSummary>> shows(@Body UserLogin param);
}
