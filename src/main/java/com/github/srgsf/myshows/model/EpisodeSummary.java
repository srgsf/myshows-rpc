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

import java.time.OffsetDateTime;

@SuppressWarnings("unused")
public class EpisodeSummary {
    @Json(name = "id")
    public final Integer id;

    @Json(name = "title")
    public final String title;

    @Json(name = "showId")
    public final Integer showId;

    @Json(name = "seasonNumber")
    public final Integer seasonNumber;

    @Json(name = "episodeNumber")
    public final Integer episodeNumber;

    @Json(name = "airDate")
    public final OffsetDateTime airDate;

    @Json(name = "airDateUTC")
    public final OffsetDateTime airDateUTC;

    @Json(name = "image")
    public final String image;

    @Json(name = "shortName")
    public final String shortName;

    @Json(name = "commentsCount")
    public final Integer commentsCount;

    EpisodeSummary(Integer id, String title, Integer showId, Integer seasonNumber, Integer episodeNumber,
                   OffsetDateTime airDate, OffsetDateTime airDateUTC, String image, String shortName, Integer commentsCount) {
        this.id = id;
        this.title = title;
        this.showId = showId;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.airDate = airDate;
        this.airDateUTC = airDateUTC;
        this.image = image;
        this.shortName = shortName;
        this.commentsCount = commentsCount;
    }
}
