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


import retrofit2.Response;

import java.io.IOException;


/**
 * Example implementation of AccessTokenProvider interface.
 *
 * @author srgsf
 * @since 0.1
 */
public class MyShowsTokenProvider implements AccessTokenProvider {

    private final MyShowsAuthClient authClient;
    private AccessToken cachedToken;

    /**
     * @param client       auth client
     * @param initialToken valid token.
     */
    public MyShowsTokenProvider(MyShowsAuthClient client, AccessToken initialToken) {
        authClient = client;
        cachedToken = initialToken;
    }

    /**
     * caches token.
     *
     * @param accessToken valid token.
     */
    public void setToken(AccessToken accessToken) {
        cachedToken = accessToken;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getToken() throws IOException {
        Response<AccessToken> tokenResponse = authClient.refreshToken(cachedToken.refreshToken);
        if (tokenResponse.isSuccessful()) {
            cachedToken = tokenResponse.body();
        }
        return peekToken();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String peekToken() {
        return cachedToken.accessToken;
    }
}
