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
import java.util.List;

@SuppressWarnings("unused")
public class Episode extends EpisodeSummary {

    @Json(name = "productionNumber")
    public final String productionNumber;

    @Json(name = "sequenceNumber")
    public final Integer sequenceNumber;

    @Json(name = "externalUrl")
    public final String externalUrl;

    @Json(name = "totalWatched")
    public final Integer totalWatched;

    @Json(name = "totalWatchedPercent")
    public final Double totalWatchedPercent;

    @Json(name = "rating")
    public final EpisodeRating rating;

    @Json(name = "onlineLinks")
    public final List<OnlineLink> onlineLinks;

    @Json(name = "onlineLinkExclusive")
    public final OnlineLink onlineLinkExclusive;

    @Json(name = "onlineEmbed")
    public final OnlineEmbed onlineEmbed;

    Episode(Integer id, String title, Integer showId, Integer seasonNumber, Integer episodeNumber,
            OffsetDateTime airDate, OffsetDateTime airDateUTC, String image, String shortName, Integer commentsCount,
            String productionNumber, Integer sequenceNumber, String externalUrl, Integer totalWatched,
            Double totalWatchedPercent, EpisodeRating rating, List<OnlineLink> onlineLinks,
            OnlineLink onlineLinkExclusive, OnlineEmbed onlineEmbed) {
        super(id, title, showId, seasonNumber, episodeNumber, airDate, airDateUTC, image, shortName, commentsCount);
        this.productionNumber = productionNumber;
        this.sequenceNumber = sequenceNumber;
        this.externalUrl = externalUrl;
        this.totalWatched = totalWatched;
        this.totalWatchedPercent = totalWatchedPercent;
        this.rating = rating;
        this.onlineLinks = onlineLinks;
        this.onlineLinkExclusive = onlineLinkExclusive;
        this.onlineEmbed = onlineEmbed;
    }
}
