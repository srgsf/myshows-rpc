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
 * documentation: <a href="https://api.myshows.me/shared/doc/#!/users">users</a>
 * @author srgsf
 * @since 0.1
 */
public interface Users {

    @POST(RPC_URL)
    @RpcMethod("users.Count")
    Call<Integer> count(@Body UserSearchRequest param);

    @POST(RPC_URL)
    @RpcMethod("users.Filters")
    Call<List<UserFilter>> filters(@Body UserSearchRequest param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("users.Follow")
    Call<Boolean> follow(@Body UserLogin param);

    @POST(RPC_URL)
    @RpcMethod("users.Search")
    Call<List<User>> search(@Body UserSearchRequestPaged param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("users.UnFollow")
    Call<Boolean> unFollow(@Body UserLogin param);
}
