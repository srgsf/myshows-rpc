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

import java.util.List;

@SuppressWarnings("unused")
public class Comments {
    @Json(name = "isTracking")
    public final Boolean isTracking;

    @Json(name = "count")
    public final Integer count;

    @Json(name = "newCount")
    public final Integer newCount;

    @Json(name = "hasSpoilers")
    public final Boolean hasSpoilers;

    @Json(name = "comments")
    public final List<Comment> comments;

    Comments(Boolean isTracking, Integer count, Integer newCount, Boolean hasSpoilers, List<Comment> comments) {
        this.isTracking = isTracking;
        this.count = count;
        this.newCount = newCount;
        this.hasSpoilers = hasSpoilers;
        this.comments = comments;
    }
}
