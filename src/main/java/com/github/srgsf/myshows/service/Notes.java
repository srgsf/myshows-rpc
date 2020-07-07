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
 * documentation: <a href="https://api.myshows.me/shared/doc/#!/notes">notes</a>
 * @author srgsf
 * @since 0.1
 */
public interface Notes {

    @POST(RPC_URL)
    @Secured
    @RpcMethod("notes.Count")
    Call<Integer> count(@Body NotesRequest param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("notes.Delete")
    Call<Boolean> delete(@Body Identity param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("notes.Get")
    Call<List<NoteSummary>> get(@Body NotesRequestPaged param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("notes.Restore")
    Call<Boolean> restore(@Body Identity param);

    @POST(RPC_URL)
    @Secured
    @RpcMethod("notes.Save")
    Call<NoteSummary> save(@Body Note param);

}
