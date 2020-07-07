/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.srgsf.myshows.model;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SyncEpisodes {
    @Json(name = "showId")
    public final Integer showId;

    @Json(name = "episodeIds")
    public final List<Integer> episodeIds = new ArrayList<>();

    public SyncEpisodes(Integer showId) {
        this.showId = showId;
    }


    public SyncEpisodes addEpisodes(Integer... ids) {
        episodeIds.addAll(Arrays.asList(ids));
        return this;
    }

    public SyncEpisodes addEpisodes(Collection<Integer> ids) {
        episodeIds.addAll(ids);
        return this;
    }
}
