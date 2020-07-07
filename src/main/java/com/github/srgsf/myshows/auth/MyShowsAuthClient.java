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

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.io.IOException;

/**
 * OAuth 2.0 token request client.
 * Use {@link com.github.srgsf.myshows.auth.MyShowsAuthClient.Builder} to configure and create instances.
 * Share retrofit and okhttp client across all the application to save resources.
 *
 * @author srgsf
 * @since 0.1
 */
public class MyShowsAuthClient {
    /**
     * <a href="https://myshows.me">myshows.me</a> OAuth 2.0 base url.
     */
    public static final String AUTH_URL = "https://myshows.me/oauth/";
    private Authentication authentication;
    private final Retrofit retrofit;
    private final OkHttpClient httpClient;
    private final Credentials clientCredentials;
    /**
     * base url that is used with current instance.
     */
    public final HttpUrl baseUrl;

    /**
     * Builder is used to configure and create instances of {@link MyShowsAuthClient}.
     * To reconfigure call {@link com.github.srgsf.myshows.auth.MyShowsAuthClient#newBuilder()}
     *
     * @author srgsf
     * @since 0.1
     */
    public static class Builder {
        private String url;
        private OkHttpClient client;
        private Credentials clientCredentials;
        private Retrofit retrofit;

        public Builder() {
        }

        Builder(MyShowsAuthClient client) {
            url = client.baseUrl.toString();
            this.client = client.httpClient;
            retrofit = client.retrofit;
            clientCredentials = client.clientCredentials;
        }

        public Builder baseUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder retrofit(Retrofit retrofit) {
            this.retrofit = retrofit;
            return this;
        }

        public Builder client(OkHttpClient client) {
            this.client = client;
            return this;
        }

        /**
         * @param clientCredentials MyShows API client credentials.
         * @return {@link Builder}
         */
        public Builder clientCredentials(Credentials clientCredentials) {
            this.clientCredentials = clientCredentials;
            return this;
        }

        /**
         * Creates {@link MyShowsAuthClient} instance.
         *
         * @return {@link MyShowsAuthClient}
         * @throws IllegalStateException if API client credentials are not provided.
         */
        public MyShowsAuthClient build() {
            final HttpUrl baseUrl = HttpUrl.get(url == null ? AUTH_URL : url);
            if (clientCredentials == null) {
                throw new IllegalStateException("clientCredentials must be provided.");
            }
            Retrofit.Builder retrofitBuilder = retrofit == null ? new Retrofit.Builder() :
                    retrofit.newBuilder();
            retrofitBuilder.baseUrl(baseUrl);

            if (retrofitBuilder.converterFactories().stream().noneMatch(c -> c instanceof MoshiConverterFactory)) {
                retrofitBuilder.addConverterFactory(MoshiConverterFactory.create());
            }

            if (client != null) {
                retrofitBuilder.client(client);
            }
            return new MyShowsAuthClient(baseUrl, clientCredentials, client, retrofitBuilder.build());
        }
    }

    private MyShowsAuthClient(HttpUrl baseUrl,
                              Credentials clientCredentials,
                              OkHttpClient httpClient,
                              Retrofit retrofit) {
        this.httpClient = httpClient;
        this.retrofit = retrofit;
        this.baseUrl = baseUrl;
        this.clientCredentials = clientCredentials;
    }

    private Authentication authentication() {
        if (authentication == null) {
            authentication = retrofit.create(Authentication.class);
        }
        return authentication;
    }

    /**
     * Retrieves OAuth 2.0 access token using <code>authorization_code</code> grant type.
     *
     * @param authorizationCode code received as a result of authorization request.
     * @param redirectUri       uri that was used in a authorization request.
     * @return OAuth 2.0 acees token.
     * @throws IOException if token request fails.
     */
    public Response<AccessToken> accessToken(String authorizationCode, String redirectUri) throws IOException {
        return authentication().tokenViaAuthorizationCode(
                "authorization_code",
                authorizationCode,
                clientCredentials.user,
                clientCredentials.secret,
                redirectUri).execute();
    }

    /**
     * Retrieves OAuth 2.0 access token using <code>password</code> grant type.
     *
     * @param userCredentials <a href="https://myshows.me">myshows.me</a> user credentials.
     * @return OAuth 2.0 acees token.
     * @throws IOException if token request fails.
     */
    public Response<AccessToken> accessToken(Credentials userCredentials) throws IOException {
        return authentication().tokenViaPassword(
                "password",
                clientCredentials.user,
                clientCredentials.secret,
                userCredentials.user,
                userCredentials.secret).execute();
    }

    /**
     * Exchanges OAuth 2.0 refresh token to access token.
     *
     * @param refreshToken refresh token
     * @return OAuth 2.0 acees token.
     * @throws IOException if token request fails.
     */
    public Response<AccessToken> refreshToken(String refreshToken) throws IOException {
        return authentication().refreshToken(
                "refresh_token",
                refreshToken,
                clientCredentials.user,
                clientCredentials.secret).execute();
    }

    /**
     * Use this if you need to reconfigure auth client.
     *
     * @return copy of {@link Builder} that was used to create current auth client instance.
     */
    public Builder newBuilder() {
        return new Builder(this);
    }

    /**
     * Helper method for authorization request generation.
     *
     * @param clientId    Api client id.
     * @param redirectUri Api redirect uri.
     * @return authorization request url.
     */
    public static HttpUrl authorizationRequestUrl(String clientId, String redirectUri) {
        return HttpUrl.get(AUTH_URL).newBuilder().addPathSegment("authorize")
                .addQueryParameter("client_id", clientId)
                .addQueryParameter("redirect_uri", redirectUri)
                .addQueryParameter("response_type", "code").build();

    }

    private interface Authentication {
        @FormUrlEncoded
        @POST("token")
        Call<AccessToken> tokenViaAuthorizationCode(@Field("grant_type") String grantType,
                                                    @Field("code") String code,
                                                    @Field("client_id") String clientId,
                                                    @Field("client_secret") String clientSecret,
                                                    @Field("redirect_uri") String redirectUri);

        @FormUrlEncoded
        @POST("token")
        Call<AccessToken> tokenViaPassword(@Field("grant_type") String grantType,
                                           @Field("client_id") String clientId,
                                           @Field("client_secret") String clientSecret,
                                           @Field("username") String username,
                                           @Field("password") String password);

        @FormUrlEncoded
        @POST("token")
        Call<AccessToken> refreshToken(@Field("grant_type") String grantType,
                                       @Field("refresh_token") String refreshToken,
                                       @Field("client_id") String clientId,
                                       @Field("client_secret") String clientSecret);

    }
}
