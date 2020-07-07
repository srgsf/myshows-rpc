/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.srgsf.myshows;

import com.github.srgsf.myshows.auth.*;
import com.github.srgsf.myshows.rpc.RpcClient;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.RecordedRequest;
import okio.Buffer;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

class Utils {
    private static MyShowsTokenProvider tokenProvider;

    static RpcClient setupRpcClient(String url) {
        return new RpcClient.Builder()
                .baseUrl(url)
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .tokenProvider(new AccessTokenProvider() {
                    @Override
                    public String getToken() {
                        return "TOKEN";
                    }

                    @Override
                    public String peekToken() {
                        return "TOKEN";
                    }
                })
                .build();
    }

    static Buffer readFromFile(String path) {
        Buffer rv = new Buffer();
        try (InputStream is = Utils.class.getClassLoader().getResourceAsStream(path)) {
            rv.readFrom(Objects.requireNonNull(is));
        } catch (IOException ex) {
            Assertions.fail("Unable to read " + path);
        }
        return rv;
    }

    static boolean checkAuthorization(RecordedRequest request) {
        String retVal = request.getHeader("Authorization");
        if (retVal == null) {
            return false;
        }
        Assertions.assertEquals("Bearer TOKEN", retVal);
        return true;
    }

    static RpcClient setupRpcClient() throws IOException {
        if (tokenProvider == null) {
            Credentials userCredentials = userCredentials();
            MyShowsAuthClient client = authClient();
            AccessToken accessToken = client.accessToken(userCredentials).body();
            tokenProvider = new MyShowsTokenProvider(client, accessToken);
        }
        return new RpcClient.Builder()
                .tokenProvider(tokenProvider)
                .build();
    }

    static Credentials userCredentials() {
        return new Credentials(System.getProperty("username"),
                System.getProperty("password"));
    }

    static MyShowsAuthClient authClient() {
        Credentials clientCredentials = new Credentials(System.getProperty("client_id"),
                System.getProperty("client_secret"));
        return new MyShowsAuthClient
                .Builder()
                .clientCredentials(clientCredentials).build();
    }
}
