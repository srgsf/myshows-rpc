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

@SuppressWarnings("unused")
public class EpisodeRating {

    @Json(name = "showId")
    public final Integer showId;

    @Json(name = "episodeId")
    public final Integer episodeId;

    @Json(name = "r1")
    public final Integer lowest;

    @Json(name = "r2")
    public final Integer low;

    @Json(name = "r3")
    public final Integer average;

    @Json(name = "r4")
    public final Integer high;

    @Json(name = "r5")
    public final Integer highest;

    @Json(name = "votes")
    public final Integer votes;

    @Json(name = "rating")
    public final Double rating;

    EpisodeRating(Integer showId, Integer episodeId, Integer lowest, Integer low, Integer average, Integer high, Integer highest, Integer votes, Double rating) {
        this.showId = showId;
        this.episodeId = episodeId;
        this.lowest = lowest;
        this.low = low;
        this.average = average;
        this.high = high;
        this.highest = highest;
        this.votes = votes;
        this.rating = rating;
    }
}
