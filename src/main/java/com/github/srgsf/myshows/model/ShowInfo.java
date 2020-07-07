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
public class ShowInfo {
    @Json(name = "id")
    public final Integer id;

    @Json(name = "watchStatus")
    public final ShowStatus watchStatus;

    @Json(name = "rating")
    public final Integer rating;

    @Json(name = "isFavorite")
    public final Boolean isFavorite;

    @Json(name = "note")
    public final NoteSummary note;

    ShowInfo(Integer id, ShowStatus watchStatus, Integer rating, Boolean isFavorite, NoteSummary note) {
        this.id = id;
        this.watchStatus = watchStatus;
        this.rating = rating;
        this.isFavorite = isFavorite;
        this.note = note;
    }
}
