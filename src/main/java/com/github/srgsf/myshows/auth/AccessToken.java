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

import com.squareup.moshi.Json;

/**
 * OAuth 2.0 access token.
 *
 * @author srgsf
 * @since 0.1
 */
public final class AccessToken {
    @Json(name = "access_token")
    public final String accessToken;

    @Json(name = "token_type")
    public final String tokenType;

    @Json(name = "scope")
    public final String scope;

    @Json(name = "refresh_token")
    public final String refreshToken;

    @Json(name = "expires_in")
    public final Long expiresIn;

    @SuppressWarnings("unused")
    public AccessToken(String accessToken, String tokenType, String scope, String refreshToken, Long expiresIn) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.scope = scope;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }
}
