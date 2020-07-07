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
public class NoteSummary {

    @Json(name = "id")
    public final Integer id;

    @Json(name = "text")
    public final String text;

    @Json(name = "createdAt")
    public final OffsetDateTime createdAt;

    @Json(name = "show")
    public final ShowSummary show;

    @Json(name = "episode")
    public final EpisodeSummary episode;

    NoteSummary(Integer id, String text, OffsetDateTime createdAt, ShowSummary show, EpisodeSummary episode) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.show = show;
        this.episode = episode;
    }
}
