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
public class NewsComment {
    @Json(name = "id")
    public final Integer id;

    @Json(name = "newsId")
    public final Integer newsId;

    @Json(name = "user")
    public final User user;

    @Json(name = "comment")
    public final String comment;

    @Json(name = "image")
    public final String image;

    @Json(name = "parentId")
    public final Integer parentId;

    @Json(name = "createdAt")
    public final OffsetDateTime createdAt;

    @Json(name = "statusId")
    public final Integer statusId;

    @Json(name = "isNew")
    public final Boolean isNew;

    @Json(name = "isMyPlus")
    public final Boolean isMyPlus;

    @Json(name = "isMyMinus")
    public final Boolean isMyMinus;

    @Json(name = "isMyComment")
    public final Boolean isMyComment;

    @Json(name = "rating")
    public final Integer rating;

    @Json(name = "isBad")
    public final Boolean isBad;

    @Json(name = "isEditable")
    public final Boolean isEditable;

    @Json(name = "editableTill")
    public final String editableTill;

    @Json(name = "isDeleted")
    public final Boolean isDeleted;

    @Json(name = "language")
    public final String language;

    @Json(name = "news")
    public final NewsSummary news;

    NewsComment(Integer id, Integer newsId, User user, String comment, String image, Integer parentId,
                OffsetDateTime createdAt, Integer statusId, Boolean isNew, Boolean isMyPlus, Boolean isMyMinus,
                Boolean isMyComment, Integer rating, Boolean isBad, Boolean isEditable, String editableTill,
                Boolean isDeleted, String language, NewsSummary news) {
        this.id = id;
        this.newsId = newsId;
        this.user = user;
        this.comment = comment;
        this.image = image;
        this.parentId = parentId;
        this.createdAt = createdAt;
        this.statusId = statusId;
        this.isNew = isNew;
        this.isMyPlus = isMyPlus;
        this.isMyMinus = isMyMinus;
        this.isMyComment = isMyComment;
        this.rating = rating;
        this.isBad = isBad;
        this.isEditable = isEditable;
        this.editableTill = editableTill;
        this.isDeleted = isDeleted;
        this.language = language;
        this.news = news;
    }
}
