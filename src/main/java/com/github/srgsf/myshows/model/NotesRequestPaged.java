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

public class NotesRequestPaged {
    @Json(name = "search")
    public final NotesFilter search;

    @Json(name = "page")
    public Integer page = 0;

    @Json(name = "pageSize")
    public Integer pageSize;

    public NotesRequestPaged(NotesFilter search) {
        this.search = search;
    }

    public NotesRequestPaged page(Integer page) {
        this.page = page;
        return this;
    }

    public NotesRequestPaged pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
