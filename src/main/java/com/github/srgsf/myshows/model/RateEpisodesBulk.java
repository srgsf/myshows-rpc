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

@SuppressWarnings("unused")
public class RateEpisodesBulk {
    @Json(name = "r1")
    public final List<Integer> lowest = new ArrayList<>();

    @Json(name = "r2")
    public final List<Integer> low = new ArrayList<>();

    @Json(name = "r3")
    public final List<Integer> average = new ArrayList<>();

    @Json(name = "r4")
    public final List<Integer> high = new ArrayList<>();

    @Json(name = "r5")
    public final List<Integer> highest = new ArrayList<>();

    public RateEpisodesBulk addLowest(Integer... episode) {
        lowest.addAll(Arrays.asList(episode));
        return this;
    }

    public RateEpisodesBulk addLow(Integer... episode) {
        low.addAll(Arrays.asList(episode));
        return this;
    }

    public RateEpisodesBulk addAvg(Integer... episode) {
        average.addAll(Arrays.asList(episode));
        return this;
    }

    public RateEpisodesBulk addHigh(Integer... episode) {
        high.addAll(Arrays.asList(episode));
        return this;
    }

    public RateEpisodesBulk addHighest(Integer... episode) {
        highest.addAll(Arrays.asList(episode));
        return this;
    }

    public RateEpisodesBulk addLowest(Collection<Integer> episode) {
        lowest.addAll(episode);
        return this;
    }

    public RateEpisodesBulk addLow(Collection<Integer> episode) {
        low.addAll(episode);
        return this;
    }

    public RateEpisodesBulk addAvg(Collection<Integer> episode) {
        average.addAll(episode);
        return this;
    }

    public RateEpisodesBulk addHigh(Collection<Integer> episode) {
        high.addAll(episode);
        return this;
    }

    public RateEpisodesBulk addHighest(Collection<Integer> episode) {
        highest.addAll(episode);
        return this;
    }
}
