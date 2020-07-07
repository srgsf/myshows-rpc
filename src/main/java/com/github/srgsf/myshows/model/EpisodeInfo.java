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
public class EpisodeInfo extends EpisodeInfoSummary {

    @Json(name = "isFavorite")
    public final Boolean isFavorite;

    @Json(name = "isIgnored")
    public final Boolean isIgnored;

    @Json(name = "note")
    public final NoteSummary note;

    EpisodeInfo(Integer id, OffsetDateTime watchDate, Rating rating, Boolean isMoveAble, Boolean isFavorite, Boolean isIgnored, NoteSummary note) {
        super(id, watchDate, rating, isMoveAble);
        this.isFavorite = isFavorite;
        this.isIgnored = isIgnored;
        this.note = note;
    }
}
