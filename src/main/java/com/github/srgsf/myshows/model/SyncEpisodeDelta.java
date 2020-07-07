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

public class SyncEpisodeDelta {
    @Json(name = "showId")
    public final Integer showId;

    @Json(name = "checkedIds")
    public final List<Integer> checkedIds = new ArrayList<>();

    @Json(name = "unCheckedIds")
    public final List<Integer> unCheckedIds = new ArrayList<>();

    public SyncEpisodeDelta(Integer showId) {
        this.showId = showId;
    }

    public SyncEpisodeDelta addChecked(Integer... episodeIds) {
        checkedIds.addAll(Arrays.asList(episodeIds));
        return this;
    }

    public SyncEpisodeDelta addUnChecked(Integer... episodeIds) {
        unCheckedIds.addAll(Arrays.asList(episodeIds));
        return this;
    }

    public SyncEpisodeDelta addChecked(Collection<Integer> episodeIds) {
        checkedIds.addAll(episodeIds);
        return this;
    }

    public SyncEpisodeDelta addUnChecked(Collection<Integer> episodeIds) {
        unCheckedIds.addAll(episodeIds);
        return this;
    }
}
