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
import com.github.srgsf.myshows.model.Gender;
import com.github.srgsf.myshows.model.Rating;
import com.github.srgsf.myshows.model.RpcError;
import com.github.srgsf.myshows.model.Wasted;
import com.github.srgsf.myshows.service.*;
import com.squareup.moshi.Moshi;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * Rpc client for <a href="https://myshows.me">myshows.me</a>.
 * Use {@link com.github.srgsf.myshows.rpc.RpcClient.Builder} to configure and create instances of client.
 *
 * @author srgsf
 * @since 0.1
 */
public class RpcClient {
    /**
     * default API url.
     */
    public static final String API_URL = "https://api.myshows.me/";
    /**
     * RPC url. (Used for all the requests)
     */
    public static final String RPC_URL = "v2/rpc/";

    private final Retrofit retrofit;
    private final OkHttpClient httpClient;
    private final AccessTokenProvider accessTokenProvider;
    private final HttpUrl baseUrl;
    private Converter<ResponseBody, ErrorResponse> converter;

    /**
     * Builder is used to configure and create instances of {@link RpcClient}.
     * To reconfigure call {@link RpcClient#newBuilder()}
     *
     * @author srgsf
     * @since 0.1
     */
    public static class Builder {
        private String url;
        private OkHttpClient client;
        private Retrofit retrofit;
        private AccessTokenProvider accessTokenProvider;
        private final boolean newBuilderCall;

        public Builder() {
            newBuilderCall = false;
        }

        Builder(RpcClient old) {
            newBuilderCall = true;
            url = old.baseUrl.toString();
            client = old.httpClient;
            retrofit = old.retrofit;
            accessTokenProvider = old.accessTokenProvider;
        }

        /**
         * Use custom url for Api.
         * {@link com.github.srgsf.myshows.rpc.RpcClient#API_URL} is used by default.
         *
         * @param url custom url
         * @return {@link Builder}
         */
        public Builder baseUrl(String url) {
            this.url = url;
            return this;
        }

        /**
         * Sets shared retrofit instance. Use this to save resources and improve performance.
         *
         * @param retrofit shared retrofit instance.
         * @return {@link Builder}
         */
        public Builder retrofit(Retrofit retrofit) {
            this.retrofit = retrofit;
            return this;
        }

        /**
         * Sets shared okHttp client instance. Use this to save resources and improve performance.
         *
         * @param client shared okHttp client instance
         * @return {@link Builder}
         */
        public Builder client(OkHttpClient client) {
            this.client = client;
            return this;
        }

        /**
         * Tells builder not to use default implementation of Authorization header handling.
         * AccessTokenProvider will not be used when this method is called.
         *
         * @return {@link Builder}
         */
        public Builder useCustomAuthentication() {
            accessTokenProvider = null;
            return this;
        }

        /**
         * Sets OAuth 2.0 authorization token provider.
         *
         * @param provider access token provider
         * @return {@link Builder}
         */
        public Builder tokenProvider(AccessTokenProvider provider) {
            this.accessTokenProvider = provider;
            return this;
        }

        /**
         * Builds new instance of {@link RpcClient}.
         *
         * @return new instance of {@link RpcClient}
         */
        public RpcClient build() {
            final HttpUrl baseUrl = HttpUrl.get(url == null ? API_URL : url);
            OkHttpClient.Builder clientBuilder = client == null ? new OkHttpClient.Builder() : client.newBuilder();
            if (newBuilderCall) {
                clientBuilder.interceptors().removeIf(i -> i instanceof AuthenticationInterceptor);
            }

            if (accessTokenProvider != null
                    && clientBuilder.interceptors().stream().noneMatch(i -> i instanceof AuthenticationInterceptor)) {
                clientBuilder.addInterceptor(new AuthenticationInterceptor(accessTokenProvider));
            }

            if (clientBuilder.interceptors().stream().noneMatch(i -> i instanceof RpcInterceptor)) {
                clientBuilder.addInterceptor(new RpcInterceptor(baseUrl));
            }

            Retrofit.Builder retrofitBuilder = retrofit == null ? new Retrofit.Builder() :
                    retrofit.newBuilder();
            retrofitBuilder.baseUrl(baseUrl);
            retrofitBuilder.client(clientBuilder.build());
            if (retrofitBuilder.converterFactories().stream().noneMatch(c -> c instanceof JsonRpcConverterFactory)) {
                retrofitBuilder.converterFactories().add(0, JsonRpcConverterFactory.create());
            }
            retrofitBuilder.converterFactories().removeIf(c -> c instanceof MoshiConverterFactory);
            Moshi moshi = new Moshi.Builder()
                    .add(new Rating.Adapter())
                    .add(new Wasted.Adapter())
                    .add(new Gender.Adapter())
                    .add(new TemporalAdapters.OffsetDateTimeAdapter())
                    .add(new TemporalAdapters.LocalDateAdapter())
                    .build();
            retrofitBuilder.addConverterFactory(MoshiConverterFactory.create(moshi));
            return new RpcClient(baseUrl, client, retrofitBuilder.build(), accessTokenProvider);
        }
    }

    private RpcClient(HttpUrl baseUrl,
                      OkHttpClient httpClient,
                      Retrofit retrofit,
                      AccessTokenProvider accessTokenProvider) {
        this.baseUrl = baseUrl;
        this.retrofit = retrofit;
        this.accessTokenProvider = accessTokenProvider;
        this.httpClient = httpClient;
    }

    /**
     * API base url that is configured for this instance.
     *
     * @return API base url.
     */
    public HttpUrl getBaseUrl() {
        return baseUrl;
    }

    /**
     * Returns copy of {@link Builder} that was used to create current instance.
     * Use this to reconfigure {@link RpcClient}.
     *
     * @return {@link Builder}.
     */
    public Builder newBuilder() {
        return new Builder(this);
    }

    /**
     * lists api.
     * documentation: <a href="https://api.myshows.me/shared/doc/#!/lists">lists</a>
     *
     * @return lists api
     */
    public Lists lists() {
        return retrofit.create(Lists.class);
    }

    /**
     * manage api.
     * documentation: <a href="https://api.myshows.me/shared/doc/#!/manage">manage</a>
     *
     * @return manage api
     */
    public Manage manage() {
        return retrofit.create(Manage.class);
    }

    /**
     * notes api.
     * documentation: <a href="https://api.myshows.me/shared/doc/#!/notes">notes</a>
     *
     * @return notes api
     */
    public Notes notes() {
        return retrofit.create(Notes.class);
    }

    /**
     * profile api.
     * documentation: <a href="https://api.myshows.me/shared/doc/#!/profile">profile</a>
     *
     * @return profile api
     */
    public Profile profile() {
        return retrofit.create(Profile.class);
    }

    /**
     * recommendation api.
     * documentation: <a href="https://api.myshows.me/shared/doc/#!/recommendation">recommendation</a>
     *
     * @return recommendation api
     */
    public Recommendation recommendation() {
        return retrofit.create(Recommendation.class);
    }

    /**
     * shows api.
     * documentation: <a href="https://api.myshows.me/shared/doc/#!/shows">shows</a>
     *
     * @return shows api
     */
    public Shows shows() {
        return retrofit.create(Shows.class);
    }

    /**
     * users api.
     * documentation: <a href="https://api.myshows.me/shared/doc/#!/users">users</a>
     *
     * @return users api
     */
    public Users users() {
        return retrofit.create(Users.class);
    }

    /**
     * Helper method to parse Json-Rpc response that contains error.
     *
     * @param errorBody error body.
     * @return parsed error.
     * @throws IOException in case of i/o failure.
     */
    public RpcError error(ResponseBody errorBody) throws IOException {
        if (converter == null) {
            converter = retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[0]);
        }
        return Optional.ofNullable(converter.convert(errorBody)).map(r -> r.error).orElse(null);
    }
}
