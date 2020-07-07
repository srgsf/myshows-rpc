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

import com.github.srgsf.myshows.model.CountRequest;
import com.github.srgsf.myshows.model.Identity;
import com.github.srgsf.myshows.service.Recommendation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class RecommendationIT {
    private static Recommendation rpc;

    @BeforeAll
    static void setup() throws IOException {
        rpc = Utils.setupRpcClient().recommendation();
    }

    @Test
    void reject() throws IOException {
        Response<Boolean> response = rpc.reject(
                new Identity(1)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void undoReject() throws IOException {
        Response<Boolean> response = rpc.undoReject(
                new Identity(1)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void recommendation() throws IOException {
        Response<List<com.github.srgsf.myshows.model.Recommendation>> response = rpc.get(
                new CountRequest().count(10)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(10, response.body().size());
    }

}
