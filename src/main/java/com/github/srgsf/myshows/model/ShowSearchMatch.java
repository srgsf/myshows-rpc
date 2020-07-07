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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class ShowSearchMatch {

    @Json(name = "match")
    public final Integer match;

    @Json(name = "filename")
    public final String filename;

    @Json(name = "filesize")
    public final Integer filesize;

    @Json(name = "show")
    public final MatchedShow show;

    ShowSearchMatch(Integer match, String filename, Integer filesize, MatchedShow show) {
        this.match = match;
        this.filename = filename;
        this.filesize = filesize;
        this.show = show;
    }

    @SuppressWarnings("unused")
    public static class MatchedShow {
        @Json(name = "id")
        public final Integer id;

        @Json(name = "title")
        public final String title;

        @Json(name = "ruTitle")
        public final String ruTitle;

        @Json(name = "status")
        public final String status;

        @Json(name = "country")
        public final String country;

        @Json(name = "started")
        public final LocalDate started;

        @Json(name = "ended")
        public final LocalDate ended;

        @Json(name = "kinopoiskId")
        public final Integer kinopoiskId;

        @Json(name = "tvrageId")
        public final Integer tvrageId;

        @Json(name = "imdbId")
        public final String imdbId;

        @Json(name = "runtime")
        public final Integer runtime;

        @Json(name = "genres")
        public final List<Integer> genreIds = new ArrayList<>();

        @Json(name = "year")
        public final Integer year;

        @Json(name = "watching")
        public final Integer watching;

        @Json(name = "voted")
        public final Integer voted;

        @Json(name = "rating")
        public final Double rating;

        @Json(name = "image")
        public final String image;

        @Json(name = "images")
        public final List<String> images = new ArrayList<>();

        @Json(name = "description")
        public final String description;

        @Json(name = "episodes")
        public final Map<String, MatchedEpisode> episodes = new HashMap<>();

        MatchedShow(Integer id, String title, String ruTitle, String status, String country, LocalDate started,
                    LocalDate ended, Integer kinopoiskId, Integer tvrageId, String imdbId, Integer runtime,
                    Integer year, Integer watching, Integer voted, Double rating, String image,
                    String description) {
            this.id = id;
            this.title = title;
            this.ruTitle = ruTitle;
            this.status = status;
            this.country = country;
            this.started = started;
            this.ended = ended;
            this.kinopoiskId = kinopoiskId;
            this.tvrageId = tvrageId;
            this.imdbId = imdbId;
            this.runtime = runtime;
            this.year = year;
            this.watching = watching;
            this.voted = voted;
            this.rating = rating;
            this.image = image;
            this.description = description;
        }
    }

    @SuppressWarnings("unused")
    public static class MatchedEpisode {
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
        public final String airDate;

        @Json(name = "image")
        public final String image;

        @Json(name = "shortName")
        public final String shortName;

        @Json(name = "sequenceNumber")
        public final Integer sequenceNumber;

        @Json(name = "tvrageLink")
        public final String tvrageLink;

        @Json(name = "productionNumber")
        public final String productionNumber;

        MatchedEpisode(Integer id, String title, Integer showId, Integer seasonNumber, Integer episodeNumber,
                       String airDate, String image, String shortName, Integer sequenceNumber,
                       String tvrageLink, String productionNumber) {
            this.id = id;
            this.title = title;
            this.showId = showId;
            this.seasonNumber = seasonNumber;
            this.episodeNumber = episodeNumber;
            this.airDate = airDate;
            this.image = image;
            this.shortName = shortName;
            this.sequenceNumber = sequenceNumber;
            this.tvrageLink = tvrageLink;
            this.productionNumber = productionNumber;
        }
    }
}
