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

import com.github.srgsf.myshows.auth.AccessToken;
import com.github.srgsf.myshows.auth.Credentials;
import com.github.srgsf.myshows.auth.MyShowsAuthClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

public class AuthClientIT {

    @Test
    void passwordAndRefreshSuccess() {
        Credentials userCredentials = Utils.userCredentials();
        MyShowsAuthClient client = Utils.authClient();
        try {
            Response<AccessToken> token = client.accessToken(userCredentials);
            Assertions.assertTrue(token.isSuccessful());
            AccessToken t = token.body();
            Assertions.assertNotNull(t);
            token = client.refreshToken(t.refreshToken);
            Assertions.assertTrue(token.isSuccessful());
            t = token.body();
            Assertions.assertNotNull(t);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assertions.fail();
        }
    }
}
