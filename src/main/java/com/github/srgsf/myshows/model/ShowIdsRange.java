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

public class ShowIdsRange {

    @Json(name = "fromId")
    public final Integer fromId;

    @Json(name = "count")
    public Integer count;

    public ShowIdsRange(Integer fromId) {
        this.fromId = fromId;
    }

    public ShowIdsRange count(Integer count) {
        this.count = count;
        return this;
    }
}
