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
public class AchievementSummary {
    @Json(name = "title")
    public final String title;

    @Json(name = "description")
    public final String description;

    @Json(name = "image")
    public final String image;

    @Json(name = "alias")
    public final String alias;

    @Json(name = "key")
    public final String key;

    @Json(name = "achievedAt")
    public final OffsetDateTime achievedAt;

    AchievementSummary(String title, String description, String image, String alias, String key, OffsetDateTime achievedAt) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.alias = alias;
        this.key = key;
        this.achievedAt = achievedAt;
    }
}
