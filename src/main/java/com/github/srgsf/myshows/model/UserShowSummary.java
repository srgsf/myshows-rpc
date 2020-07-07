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
public class UserShowSummary {
    @Json(name = "show")
    public final ShowSummary show;

    @Json(name = "watchStatus")
    public final String watchStatus;

    @Json(name = "rating")
    public final Integer rating;

    @Json(name = "watchCount")
    public final Integer watchCount;

    @Json(name = "totalEpisodes")
    public final Integer totalEpisodes;

    @Json(name = "watchedEpisodes")
    public final Integer watchedEpisodes;

    UserShowSummary(ShowSummary show, String watchStatus, Integer rating, Integer watchCount, Integer totalEpisodes,
                    Integer watchedEpisodes) {
        this.show = show;
        this.watchStatus = watchStatus;
        this.rating = rating;
        this.watchCount = watchCount;
        this.totalEpisodes = totalEpisodes;
        this.watchedEpisodes = watchedEpisodes;
    }
}
