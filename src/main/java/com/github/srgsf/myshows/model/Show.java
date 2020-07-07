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
import java.util.List;

@SuppressWarnings("unused")
public class Show extends ShowSummary {

    @Json(name = "country")
    public final String country;

    @Json(name = "countryTitle")
    public final String countryTitle;

    @Json(name = "started")
    public final LocalDate started;

    @Json(name = "ended")
    public final LocalDate ended;

    @Json(name = "kinopoiskId")
    public final Integer kinopoiskId;

    @Json(name = "kinopoiskRating")
    public final Double kinopoiskRating;

    @Json(name = "kinopoiskVoted")
    public final Integer kinopoiskVoted;

    @Json(name = "kinopoiskUrl")
    public final String kinopoiskUrl;

    @Json(name = "tvrageId")
    public final Integer tvrageId;

    @Json(name = "imdbId")
    public final String imdbId;

    @Json(name = "imdbRating")
    public final Double imdbRating;

    @Json(name = "imdbVoted")
    public final Integer imdbVoted;

    @Json(name = "imdbUrl")
    public final String imdbUrl;

    @Json(name = "watchingTotal")
    public final Integer watchingTotal;

    @Json(name = "runtime")
    public final Integer runtime;

    @Json(name = "runtimeTotal")
    public final String runtimeTotal;

    @Json(name = "genreIds")
    public final List<Integer> genreIds = new ArrayList<>();

    @Json(name = "network")
    public final Network network;

    @Json(name = "episodes")
    public final List<EpisodeSummary> episodes = new ArrayList<>();

    @Json(name = "onlineLinks")
    public final List<OnlineLink> onlineLinks = new ArrayList<>();

    @Json(name = "onlineLinkExclusive")
    public final OnlineLink onlineLinkExclusive;

    Show(Integer id, String title, String titleOriginal, String status, Integer totalSeasons, Integer year,
         Integer watching, Integer voted, Double rating, String image, Integer onlineCount, String promoUrl,
         String description, String country, String countryTitle, LocalDate started, LocalDate ended, Integer kinopoiskId,
         Double kinopoiskRating, Integer kinopoiskVoted, String kinopoiskUrl, Integer tvrageId, String imdbId,
         Double imdbRating, Integer imdbVoted, String imdbUrl, Integer watchingTotal, Integer runtime,
         String runtimeTotal, Network network, OnlineLink onlineLinkExclusive) {
        super(id, title, titleOriginal, status, totalSeasons, year, watching, voted, rating, image, onlineCount,
                promoUrl, description);
        this.country = country;
        this.countryTitle = countryTitle;
        this.started = started;
        this.ended = ended;
        this.kinopoiskId = kinopoiskId;
        this.kinopoiskRating = kinopoiskRating;
        this.kinopoiskVoted = kinopoiskVoted;
        this.kinopoiskUrl = kinopoiskUrl;
        this.tvrageId = tvrageId;
        this.imdbId = imdbId;
        this.imdbRating = imdbRating;
        this.imdbVoted = imdbVoted;
        this.imdbUrl = imdbUrl;
        this.watchingTotal = watchingTotal;
        this.runtime = runtime;
        this.runtimeTotal = runtimeTotal;
        this.network = network;
        this.onlineLinkExclusive = onlineLinkExclusive;
    }
}
