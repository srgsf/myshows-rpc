/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.srgsf.myshows.auth;


import java.io.IOException;

/**
 * OAuth 2.0 access token provider.
 * Implement this if you don't use custom authentication. See {@link com.github.srgsf.myshows.rpc.RpcClient.Builder#useCustomAuthentication() useCustomAuthentication()} method.
 *
 * @author srgsf
 * @see MyShowsTokenProvider
 * @since 0.1
 */
public interface AccessTokenProvider {

    /**
     * Retrieves and caches access token from authorization server.
     *
     * @return OAuth 2.0 access token
     * @throws IOException on token request fail.
     */
    String getToken() throws IOException;

    /**
     * Returns cached token. Can be invalid or outdated. #getToken() for refresh.
     *
     * @return OAuth 2.0 access token
     */
    String peekToken();
}
