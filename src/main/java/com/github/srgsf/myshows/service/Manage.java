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

import static com.github.srgsf.myshows.rpc.RpcClient.RPC_URL;

/**
 * documentation: <a href="https://api.myshows.me/shared/doc/#!/manage">manage</a>
 * @author srgsf
 * @since 0.1
 */
public interface Manage {
    @POST(RPC_URL)
    @Secured
    @RpcMethod("manage.CheckEpisode")
    Call<Boolean> checkEpisode(@Body CheckEpisode param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("manage.MoveEpisodeDate")
    Call<Boolean> moveEpisodeDate(@Body MoveEpisodeDate param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("manage.RateEpisode")
    Call<Boolean> rateEpisode(@Body RateItem param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("manage.RateEpisodesBulk")
    Call<Boolean> rateEpisodesBulk(@Body RateEpisodesBulk param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("manage.RateShow")
    Call<Boolean> rateShow(@Body RateItem param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("manage.SetShowStatus")
    Call<Boolean> setShowStatus(@Body ChangeShowStatus param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("manage.SyncEpisodesDelta")
    Call<Boolean> syncEpisodesDelta(@Body SyncEpisodeDelta param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("manage.SyncEpisodes")
    Call<Boolean> syncEpisodes(@Body SyncEpisodes param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("manage.UnCheckEpisode")
    Call<Boolean> unCheckEpisode(@Body Identity param);
}
