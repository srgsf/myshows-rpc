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

import com.github.srgsf.myshows.model.*;
import com.github.srgsf.myshows.service.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class UsersIT {

    private static Users rpc;

    @BeforeAll
    static void setup() throws IOException {
        rpc = Utils.setupRpcClient().users();
    }

    @Test
    void search() throws IOException {
        Response<List<User>> response = rpc.search(
                new UserSearchRequestPaged().search(new UserSearch()
                        .gender(Gender.Female)
                        .wasted(Wasted.Hours)).pageSize(2)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(2, response.body().size());
    }

    @Test
    void count() throws IOException {

        Response<Integer> response = rpc.count(
                new UserSearchRequest().search(new UserSearch()
                        .gender(Gender.Female)
                        .wasted(Wasted.Hours))
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void filters() throws IOException {
        Response<List<UserFilter>> response = rpc.filters(
                new UserSearchRequest().search(new UserSearch()
                        .gender(Gender.Female)
                        .wasted(Wasted.Hours))
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void follow() throws IOException {
        Response<Boolean> response = rpc.follow(
                new UserLogin("vk488443")
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void unFollow() throws IOException {
        Response<Boolean> response = rpc.unFollow(
                new UserLogin("vk488443")
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }

}
