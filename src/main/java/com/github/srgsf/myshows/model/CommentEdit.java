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
public class CommentEdit {
    @Json(name = "commentId")
    public final Integer commentId;

    @Json(name = "text")
    public final String text;

    @Json(name = "image")
    public String image;

    @Json(name = "deleteImage")
    public Boolean deleteImage;

    public CommentEdit(Integer commentId, String text) {
        this.commentId = commentId;
        this.text = text;
    }

    public CommentEdit image(String image) {
        this.image = image;
        return this;
    }

    public CommentEdit deleteImage(Boolean deleteImage) {
        this.deleteImage = deleteImage;
        return this;
    }
}
