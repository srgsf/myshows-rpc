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
public class Counters {

    @Json(name = "unwatchedEpisodes")
    public final Integer unwatchedEpisodes;

    @Json(name = "newAchievements")
    public final Integer newAchievements;

    @Json(name = "newCommentReplies")
    public final Integer newCommentReplies;

    Counters(Integer unwatchedEpisodes, Integer newAchievements, Integer newCommentReplies) {
        this.unwatchedEpisodes = unwatchedEpisodes;
        this.newAchievements = newAchievements;
        this.newCommentReplies = newCommentReplies;
    }
}
