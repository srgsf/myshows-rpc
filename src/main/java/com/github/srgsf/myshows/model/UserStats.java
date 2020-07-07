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
public class UserStats {

    @Json(name = "watchedEpisodes")
    public final Integer watchedEpisodes;

    @Json(name = "remainingEpisodes")
    public final Integer remainingEpisodes;

    @Json(name = "totalEpisodes")
    public final Integer totalEpisodes;

    @Json(name = "watchedHours")
    public final Double watchedHours;

    @Json(name = "remainingHours")
    public final Double remainingHours;

    @Json(name = "totalHours")
    public final Double totalHours;

    @Json(name = "watchedDays")
    public final Double watchedDays;

    @Json(name = "remainingDays")
    public final Double remainingDays;

    @Json(name = "totalDays")
    public final Double totalDays;

    UserStats(Integer watchedEpisodes, Integer remainingEpisodes, Integer totalEpisodes, Double watchedHours,
              Double remainingHours, Double totalHours, Double watchedDays, Double remainingDays, Double totalDays) {
        this.watchedEpisodes = watchedEpisodes;
        this.remainingEpisodes = remainingEpisodes;
        this.totalEpisodes = totalEpisodes;
        this.watchedHours = watchedHours;
        this.remainingHours = remainingHours;
        this.totalHours = totalHours;
        this.watchedDays = watchedDays;
        this.remainingDays = remainingDays;
        this.totalDays = totalDays;
    }
}
