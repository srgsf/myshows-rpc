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

public class MoveEpisodeDate {

    public enum Days {
        @Json(name = "1")
        one,
        @Json(name = "2")
        two,
        @Json(name = "3")
        three
    }

    @Json(name = "episodeId")
    public final Integer episodeId;

    @Json(name = "shiftDays")
    public final Days shiftDays;

    public MoveEpisodeDate(Integer episodeId, Days shiftDays) {
        this.episodeId = episodeId;
        this.shiftDays = shiftDays;
    }
}
