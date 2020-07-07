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
public class UserProfile {
    @Json(name = "user")
    public final User user;

    @Json(name = "stats")
    public final UserStats stats;

    @Json(name = "ranks")
    public final UserRanks ranks;

    @Json(name = "commentsCount")
    public final Integer commentsCount;

    @Json(name = "commentsRating")
    public final Integer commentsRating;

    @Json(name = "social")
    public final UserSocial social;


    UserProfile(User user, UserStats stats, UserRanks ranks, Integer commentsCount, Integer commentsRating, UserSocial social) {
        this.user = user;
        this.stats = stats;
        this.ranks = ranks;
        this.commentsCount = commentsCount;
        this.commentsRating = commentsRating;
        this.social = social;
    }
}
