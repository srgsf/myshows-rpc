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


import com.github.srgsf.myshows.auth.AccessTokenProvider;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Invocation;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Optional;

/**
 * RPC authentication interceptor. Json-RPC always returns 200 response code,
 * so okhttp {@link okhttp3.Authenticator} can't be used.
 *
 * @author srgsf
 * @since 0.1
 */
class AuthenticationInterceptor implements Interceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTH_SCHEME = "Bearer";
    private final AccessTokenProvider tokenProvider;

    AuthenticationInterceptor(AccessTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!Optional.ofNullable(request.tag(Invocation.class)).map(Invocation::method)
                .map(method -> method.getAnnotation(Secured.class)).isPresent()) {
            return chain.proceed(request);
        }

        String token = tokenProvider.peekToken();
        request = buildNewRequest(request, token);
        Response response = chain.proceed(request);
        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            synchronized (tokenProvider) {
                String newToken = tokenProvider.peekToken();
                token = newToken.equals(token) ? tokenProvider.getToken() : newToken;
            }
            return chain.proceed(buildNewRequest(response.request(), token));
        }
        return response;
    }

    private Request buildNewRequest(Request request, String token) {
        return request.newBuilder().header(
                AUTHORIZATION_HEADER, String.join(" ", AUTH_SCHEME, token)).build();
    }
}
