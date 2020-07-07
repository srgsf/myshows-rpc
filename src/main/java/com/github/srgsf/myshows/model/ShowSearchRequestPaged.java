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

public class ShowSearchRequestPaged {
    @Json(name = "search")
    public final ShowSearch search;

    @Json(name = "page")
    public Integer page;

    @Json(name = "pageSize")
    public Integer pageSize;

    public ShowSearchRequestPaged(ShowSearch search) {
        this.search = search;
    }

    public ShowSearchRequestPaged page(Integer page) {
        this.page = page;
        return this;
    }

    public ShowSearchRequestPaged pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
