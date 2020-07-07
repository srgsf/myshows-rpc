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

import com.github.srgsf.myshows.model.CountRequest;
import com.github.srgsf.myshows.model.Identity;
import com.github.srgsf.myshows.rpc.RpcMethod;
import com.github.srgsf.myshows.rpc.Secured;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

import static com.github.srgsf.myshows.rpc.RpcClient.RPC_URL;

/**
 * documentation: <a href="https://api.myshows.me/shared/doc/#!/recommendation">recommendation</a>
 * @author srgsf
 * @since 0.1
 */
public interface Recommendation {


    @POST(RPC_URL)
    @Secured
    @RpcMethod("recommendation.Get")
    Call<List<com.github.srgsf.myshows.model.Recommendation>>
    get(@Body CountRequest param);


    @POST(RPC_URL)
    @Secured
    @RpcMethod("recommendation.Reject")
    Call<Boolean> reject(@Body Identity param);


    @POST(RPC_URL)
    @Secured
    @RpcMethod("recommendation.UndoReject")
    Call<Boolean> undoReject(@Body Identity param);

}
