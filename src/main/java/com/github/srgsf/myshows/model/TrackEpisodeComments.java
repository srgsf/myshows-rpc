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

public class TrackEpisodeComments {

    @Json(name = "episodeId")
    public final Integer episodeId;

    @Json(name = "isTracked")
    public final boolean isTracked;

    public TrackEpisodeComments(Integer episodeId, boolean isTracked) {
        this.episodeId = episodeId;
        this.isTracked = isTracked;
    }
}
