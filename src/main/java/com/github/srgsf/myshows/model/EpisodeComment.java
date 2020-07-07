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

public class EpisodeComment {
    @Json(name = "episodeId")
    public final Integer episodeId;

    @Json(name = "text")
    public final String text;

    @Json(name = "image")
    public String image;

    @Json(name = "parentCommentId")
    public Integer parentCommentId;

    public EpisodeComment(Integer episodeId, String text) {
        this.episodeId = episodeId;
        this.text = text;
    }

    public EpisodeComment image(String image) {
        this.image = image;
        return this;
    }

    public EpisodeComment parentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
        return this;
    }
}
