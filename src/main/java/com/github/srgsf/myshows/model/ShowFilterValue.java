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
public class ShowFilterValue {
    @Json(name = "title")
    public final String title;

    @Json(name = "alias")
    public final String alias;

    @Json(name = "count")
    public final Integer count;

    @Json(name = "isActive")
    public final Boolean isActive;

    ShowFilterValue(String title, String alias, Integer count, Boolean isActive) {
        this.title = title;
        this.alias = alias;
        this.count = count;
        this.isActive = isActive;
    }
}
