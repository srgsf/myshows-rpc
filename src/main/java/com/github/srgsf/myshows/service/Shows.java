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
 * documentation: <a href="https://api.myshows.me/shared/doc/#!/shows">shows</a>
 * @author srgsf
 * @since 0.1
 */
public interface Shows {

    @POST(RPC_URL)
    @RpcMethod("shows.GetById")
    Call<Show> getById(@Body ShowRequest param);

    @POST(RPC_URL)
    @RpcMethod("shows.GetByExternalId")
    Call<Show> getByExternalId(@Body ShowExternalId param);

    @POST(RPC_URL)
    @RpcMethod("shows.Count")
    Call<Integer> count(@Body ShowSearchRequest param);

    @POST(RPC_URL)
    @RpcMethod("shows.Episode")
    Call<Episode> episode(@Body Identity param);

    @POST(RPC_URL)
    @RpcMethod("shows.Filters")
    Call<List<ShowFilter>> filters(@Body ShowSearchRequest param);

    @POST(RPC_URL)
    @RpcMethod("shows.Genres")
    Call<List<Genre>> genres();

    @POST(RPC_URL)
    @RpcMethod("shows.Get")
    Call<List<ShowSummary>> get(@Body ShowSearchRequestPaged param);

    @POST(RPC_URL)
    @RpcMethod("shows.Ids")
    Call<List<Integer>> showsIds(@Body ShowIdsRange param);

    @POST(RPC_URL)
    @RpcMethod("shows.SearchByFile")
    Call<ShowSearchMatch> searchByFile(@Body FileName param);

    @POST(RPC_URL)
    @RpcMethod("shows.Search")
    Call<List<Show>> search(@Body Query param);

    @POST(RPC_URL)
    @RpcMethod("shows.Top")
    Call<List<ShowRank>> top(@Body TopShows param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("shows.PostEpisodeComment")
    Call<Comment> postComment(@Body EpisodeComment param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("shows.EpisodeComments")
    Call<Comments> comments(@Body EpisodeId param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("shows.TrackEpisodeComments")
    Call<Boolean> trackComments(@Body TrackEpisodeComments param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("shows.TranslateEpisodeComment")
    Call<String> translateComment(@Body CommentTranslate param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("shows.UpdateEpisodeComment")
    Call<Boolean> updateComment(@Body CommentEdit param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("shows.ViewEpisodeComments")
    Call<Boolean> viewComments(@Body EpisodeId param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("shows.VoteEpisodeComment")
    Call<Integer> voteComment(@Body CommentVote param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("shows.DeleteEpisodeComment")
    Call<Boolean> deleteComment(@Body CommentId param);

}
