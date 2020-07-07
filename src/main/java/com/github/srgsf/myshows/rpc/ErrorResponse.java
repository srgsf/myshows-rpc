/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.srgsf.myshows.rpc;

import com.github.srgsf.myshows.model.RpcError;
import com.squareup.moshi.Json;

/**
 * Json-Rpc error response structure.
 *
 * @author srgsf
 * @since 0.1
 */
class ErrorResponse {
    @Json(name = "jsonrpc")
    String jsonrpc;

    @Json(name = "error")
    RpcError error;

    @Json(name = "id")
    Integer id;
}
