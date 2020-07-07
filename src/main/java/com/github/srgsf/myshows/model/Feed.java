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
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Feed {
    @Json(name = "id")
    public final Integer id;

    @Json(name = "user")
    public final User user;

    @Json(name = "createdAt")
    public final OffsetDateTime createdAt;

    @Json(name = "type")
    public final String type;

    @Json(name = "show")
    public final ShowSummary show;

    @Json(name = "episodes")
    public final List<EpisodeSummary> episodes = new ArrayList<>();

    @Json(name = "rating")
    public final Rating rating;

    @Json(name = "showStatus")
    public final ShowStatus showStatus;

    @Json(name = "commentId")
    public final Integer commentId;

    @Json(name = "achievement")
    public final AchievementSummary achievement;

    @Json(name = "affectedUser")
    public final User affectedUser;

    Feed(Integer id, User user, OffsetDateTime createdAt, String type, ShowSummary show,
         Rating rating, ShowStatus showStatus, Integer commentId, AchievementSummary achievement, User affectedUser) {
        this.id = id;
        this.user = user;
        this.createdAt = createdAt;
        this.type = type;
        this.show = show;
        this.rating = rating;
        this.showStatus = showStatus;
        this.commentId = commentId;
        this.achievement = achievement;
        this.affectedUser = affectedUser;
    }
}
