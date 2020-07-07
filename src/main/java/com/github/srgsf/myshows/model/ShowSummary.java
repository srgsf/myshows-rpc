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
public class ShowSummary {
    @Json(name = "id")
    public final Integer id;

    @Json(name = "title")
    public final String title;

    @Json(name = "titleOriginal")
    public final String titleOriginal;

    @Json(name = "status")
    public final String status;

    @Json(name = "totalSeasons")
    public final Integer totalSeasons;

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

    @Json(name = "onlineCount")
    public final Integer onlineCount;

    @Json(name = "promoUrl")
    public final String promoUrl;

    @Json(name = "description")
    public final String description;

    ShowSummary(Integer id, String title, String titleOriginal, String status, Integer totalSeasons, Integer year,
                Integer watching, Integer voted, Double rating, String image, Integer onlineCount, String promoUrl,
                String description) {
        this.id = id;
        this.title = title;
        this.titleOriginal = titleOriginal;
        this.status = status;
        this.totalSeasons = totalSeasons;
        this.year = year;
        this.watching = watching;
        this.voted = voted;
        this.rating = rating;
        this.image = image;
        this.onlineCount = onlineCount;
        this.promoUrl = promoUrl;
        this.description = description;
    }
}
