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

public class UserSearch {
    @Json(name = "query")
    public String query;

    @Json(name = "wasted")
    public Wasted wasted;

    @Json(name = "year")
    public Integer year;

    @Json(name = "gender")
    public Gender gender;

    public UserSearch query(String query) {
        this.query = query;
        return this;
    }

    public UserSearch wasted(Wasted wasted) {
        this.wasted = wasted;
        return this;
    }

    public UserSearch year(Integer year) {
        this.year = year;
        return this;
    }

    public UserSearch gender(Gender gender) {
        this.gender = gender;
        return this;
    }
}
