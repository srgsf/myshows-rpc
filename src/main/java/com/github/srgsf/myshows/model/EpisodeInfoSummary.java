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
public class EpisodeInfoSummary {
    @Json(name = "id")
    public final Integer id;

    @Json(name = "watchDate")
    public final OffsetDateTime watchDate;

    @Json(name = "rating")
    public final Rating rating;

    @Json(name = "isMoveAble")
    public final Boolean isMoveAble;

    EpisodeInfoSummary(Integer id, OffsetDateTime watchDate, Rating rating, Boolean isMoveAble) {
        this.id = id;
        this.watchDate = watchDate;
        this.rating = rating;
        this.isMoveAble = isMoveAble;
    }
}
