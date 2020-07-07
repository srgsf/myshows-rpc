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
public class NewsSummary {
    @Json(name = "id")
    public final Integer id;

    @Json(name = "title")
    public final String title;

    @Json(name = "alias")
    public final String alias;

    @Json(name = "foreword")
    public final String foreword;

    @Json(name = "publishedAt")
    public final OffsetDateTime publishedAt;

    @Json(name = "image")
    public final String image;

    @Json(name = "author")
    public final NewsAuthor author;

    @Json(name = "video")
    public final String video;

    @Json(name = "commentsTotal")
    public final Integer commentsTotal;

    @Json(name = "commentsNew")
    public final Integer commentsNew;

    NewsSummary(Integer id, String title, String alias, String foreword, OffsetDateTime publishedAt, String image,
                NewsAuthor author, String video, Integer commentsTotal, Integer commentsNew) {
        this.id = id;
        this.title = title;
        this.alias = alias;
        this.foreword = foreword;
        this.publishedAt = publishedAt;
        this.image = image;
        this.author = author;
        this.video = video;
        this.commentsTotal = commentsTotal;
        this.commentsNew = commentsNew;
    }
}
