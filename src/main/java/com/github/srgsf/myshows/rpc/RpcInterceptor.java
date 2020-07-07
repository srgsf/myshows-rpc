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


import com.squareup.moshi.JsonReader;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.*;
import okio.BufferedSource;
import retrofit2.Invocation;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Objects;
import java.util.Optional;

/**
 * Fills common headers and updates response code on Json-Rpc errors.
 *
 * @author srgsf
 * @since 0.1
 */
class RpcInterceptor implements Interceptor {

    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_ACCEPT = "Accept";
    private static final String APPLICATION_JSON = "application/json";
    private static final String ERROR_OBJECT = "error";
    private static final String RESULT_OBJECT = "result";
    private static final String CODE_FILED = "code";
    private static final String JSONRPC_FILED = "jsonrpc";
    private static final String ID_FILED = "id";

    private final HttpUrl rpcUrl;

    public RpcInterceptor(HttpUrl rpcUrl) {
        this.rpcUrl = rpcUrl;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        okhttp3.Request request = chain.request();
        if (!rpcUrl.host().equals(request.url().host())) {
            return chain.proceed(request);
        }

        Request.Builder reqBuilder = null;

        if (request.header(HEADER_CONTENT_TYPE) == null) {
            reqBuilder = request.newBuilder().addHeader(HEADER_CONTENT_TYPE, APPLICATION_JSON);
        }

        if (request.header(HEADER_ACCEPT) == null) {
            if (reqBuilder == null) {
                reqBuilder = request.newBuilder();
            }
            reqBuilder.addHeader(HEADER_ACCEPT, APPLICATION_JSON);
        }

        if (Objects.requireNonNull(request.body()).contentLength() == 0L) {
            if (reqBuilder == null) {
                reqBuilder = request.newBuilder();
            }
            reqBuilder.post(createEmptyRpcBody(request));
        }

        if (reqBuilder != null) {
            request = reqBuilder.build();
        }
        Response response = chain.proceed(request);

        if (!response.isSuccessful()) {
            return response;
        }

        Optional<ResponseBody> body = Optional.ofNullable(response.body());

        if (!body.map(ResponseBody::contentType).map(MediaType::toString)
                .filter(APPLICATION_JSON::equals).isPresent()) {
            return response;
        }

        BufferedSource source = body.map(ResponseBody::source).get();
        source.request(Long.MAX_VALUE);
        JsonReader reader = JsonReader.of(source.getBuffer().clone());
        if (!(reader.hasNext() || reader.peek() == JsonReader.Token.BEGIN_OBJECT)) {
            return response;
        }
        reader.beginObject();
        if (reader.peek() != JsonReader.Token.NAME) {
            return response;
        }
        boolean error = false;
        int level = 0;
        while (reader.hasNext()) {
            JsonReader.Token token = reader.peek();
            switch (token) {
                case BEGIN_OBJECT:
                    reader.beginObject();
                    ++level;
                    break;
                case END_OBJECT:
                    reader.endObject();
                    --level;
                    break;
                case NAME:
                    String name = reader.nextName();
                    if (!error && level == 0 && RESULT_OBJECT.equals(name)) {
                        return response;
                    }

                    if (!error && level == 0 && ERROR_OBJECT.equals(name)) {
                        error = true;
                        break;
                    }

                    if (error && level == 1 && CODE_FILED.equals(name)) {
                        int code = reader.nextInt();
                        if (code < 0) {
                            code = HttpURLConnection.HTTP_INTERNAL_ERROR;
                        }
                        return response.newBuilder().code(code).build();
                    }

                    if (level == 0) {
                        if (!(JSONRPC_FILED.equals(name) || ID_FILED.equals(name))) {
                            return response;
                        }
                    }
                    break;
                default:
                    reader.skipValue();
            }
        }
        return response;
    }

    /**
     * Handles Rpc methods without parameters.
     *
     * @param request current request
     * @return valid Json-Rpc request body.
     */
    private RequestBody createEmptyRpcBody(Request request) {
        String rpcMethod = Optional.ofNullable(request.tag(Invocation.class))
                .filter(i -> i.arguments().isEmpty())
                .map(Invocation::method)
                .map(method -> method.getAnnotation(RpcMethod.class))
                .map(RpcMethod::value).orElse(null);
        if (rpcMethod == null) {
            return request.body();
        }
        String r = com.github.srgsf.myshows.rpc.Request.emptyRequest(rpcMethod);
        return RequestBody.create(MediaType.get(APPLICATION_JSON), r);
    }
}
