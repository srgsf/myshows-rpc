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

import com.squareup.moshi.Types;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Translates models into proper Json-Rpc request/response structures.
 *
 * @author srgsf
 * @since 0.1
 */
class JsonRpcConverterFactory extends Converter.Factory {

    static Converter.Factory create() {
        return new JsonRpcConverterFactory();
    }

    private JsonRpcConverterFactory() {
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                            Annotation[] annotations,
                                                            Retrofit retrofit) {
        if (findMethod(annotations) == null) {
            return null;
        }
        Type rpcType = Types.newParameterizedType(Response.class, type);
        Converter<ResponseBody, Response<?>> delegate =
                retrofit.nextResponseBodyConverter(this, rpcType, annotations);
        return new JsonRpcResponseBodyConverter(delegate);
    }

    private RpcMethod findMethod(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof RpcMethod) {
                return (RpcMethod) annotation;
            }
        }
        return null;
    }

    private static class JsonRpcResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        final Converter<ResponseBody, Response<T>> delegate;

        JsonRpcResponseBodyConverter(Converter<ResponseBody, Response<T>> delegate) {
            this.delegate = delegate;
        }

        @Override
        public T convert(ResponseBody responseBody) throws IOException {
            Response<T> r = delegate.convert(responseBody);
            if (r == null) {
                return null;
            }
            return r.result;
        }
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {
        RpcMethod methodAnnotation = findMethod(methodAnnotations);
        if (methodAnnotation == null) {
            return null;
        }
        String method = methodAnnotation.value();

        Converter<Request, RequestBody> delegate =
                retrofit.nextRequestBodyConverter(this, Request.class, annotations,
                        methodAnnotations);
        return new JsonRpcRequestBodyConverter(method, delegate);
    }

    private static class JsonRpcRequestBodyConverter implements Converter<Object, RequestBody> {
        final String method;
        final Converter<Request, RequestBody> delegate;
        private static AtomicInteger idsSupplier;

        JsonRpcRequestBodyConverter(String method, Converter<Request, RequestBody> delegate) {
            this.method = method;
            this.delegate = delegate;
            synchronized (JsonRpcRequestBodyConverter.class) {
                if (idsSupplier == null) {
                    idsSupplier = new AtomicInteger(1);
                }
            }
        }

        @Override
        public RequestBody convert(Object value) throws IOException {
            final Request request = new Request();
            request.method = method;
            request.params = value;
            request.id = idsSupplier.getAndUpdate(i -> i + 1 < 0 ? 1 : i + 1);
            return delegate.convert(request);
        }
    }
}