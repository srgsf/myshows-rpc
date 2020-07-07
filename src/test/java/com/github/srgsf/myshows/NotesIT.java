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
import com.github.srgsf.myshows.service.Notes;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NotesIT {

    private static Notes rpc;

    @BeforeAll
    static void setup() throws IOException {
        rpc = Utils.setupRpcClient().notes();
    }

    @AfterAll
    static void cleanup() throws IOException {
        List<NoteSummary> notes = rpc.get(
                new NotesRequestPaged(new NotesFilter().episode(true))
        ).execute().body();
        if (notes != null) {
            notes.stream()
                    .filter(n -> "testNote".equals(n.text) || "testNoteToDelete".equals(n.text))
                    .map(n -> n.id).forEach(id -> {
                try {
                    rpc.delete(
                            new Identity(id)).execute();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    @Test
    @Order(1)
    void save() throws IOException {
        Response<NoteSummary> response = rpc.save(
                new Note(1, "testNote").episodeId(1)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
    }


    @Test
    @Order(2)
    void count() throws IOException {
        Response<Integer> response = rpc.count(
                new NotesRequest(new NotesFilter().episode(true))
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body() > 0);
    }

    @Test
    @Order(3)
    void get() throws IOException {
        Response<List<NoteSummary>> response = rpc.get(
                new NotesRequestPaged(new NotesFilter().episode(true)).pageSize(5)
        ).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body().size() > 0);
    }

    @Test
    @Order(4)
    void delete() throws IOException {
        Integer noteId = Optional.of(rpc.save(
                new Note(1, "testNoteToDelete").episodeId(1)
        ).execute()).map(Response::body).map(s -> s.id).orElse(null);
        Assertions.assertNotNull(noteId);
        Response<Boolean> response = rpc.delete(
                new Identity(noteId)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }

    @Test
    @Order(5)
    void restore() throws IOException {
        Integer noteId = Optional.of(rpc.save(
                new Note(1, "testNoteToDelete").episodeId(1)
        ).execute()).map(Response::body).map(s -> s.id).orElse(null);
        Assertions.assertNotNull(noteId);
        rpc.delete(
                new Identity(noteId)).execute();
        Response<Boolean> response = rpc.restore(
                new Identity(noteId)).execute();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        Assertions.assertTrue(response.body());
    }
}
