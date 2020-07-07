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

import com.github.srgsf.myshows.model.EpisodeListItem;
import com.github.srgsf.myshows.model.ListRequest;
import com.github.srgsf.myshows.model.ModifyListRequest;
import com.github.srgsf.myshows.model.ShowSummary;
import com.github.srgsf.myshows.rpc.RpcMethod;
import com.github.srgsf.myshows.rpc.Secured;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

import static com.github.srgsf.myshows.rpc.RpcClient.RPC_URL;

/**
 * documentation: <a href="https://api.myshows.me/shared/doc/#!/lists">lists</a>
 *
 * @author srgsf
 * @since 0.1
 */
public interface Lists {

    @POST(RPC_URL)
    @Secured
    @RpcMethod("lists.AddEpisode")
    Call<Boolean> addEpisode(@Body ModifyListRequest param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("lists.AddShow")
    Call<Boolean> addShow(@Body ModifyListRequest param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("lists.Episodes")
    Call<List<EpisodeListItem>> episodes(@Body ListRequest param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("lists.RemoveEpisode")
    Call<Boolean> removeEpisode(@Body ModifyListRequest param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("lists.RemoveShow")
    Call<Boolean> removeShow(@Body ModifyListRequest param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("lists.Shows")
    Call<List<ShowSummary>> shows(@Body ListRequest param);
}
