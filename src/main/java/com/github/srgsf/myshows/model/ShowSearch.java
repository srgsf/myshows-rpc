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

public class ShowSearch {
    @Json(name = "network")
    public Integer network;

    @Json(name = "genre")
    public Integer genre;

    @Json(name = "country")
    public String country;

    @Json(name = "year")
    public Integer year;

    @Json(name = "watching")
    public Integer watching;

    @Json(name = "category")
    public String category;

    @Json(name = "status")
    public String status;

    @Json(name = "sort")
    public String sort;

    @Json(name = "query")
    public String query;

    public ShowSearch network(Integer network) {
        this.network = network;
        return this;
    }

    public ShowSearch genre(Integer genre) {
        this.genre = genre;
        return this;
    }

    public ShowSearch country(String country) {
        this.country = country;
        return this;
    }

    public ShowSearch year(Integer year) {
        this.year = year;
        return this;
    }

    public ShowSearch watching(Integer watching) {
        this.watching = watching;
        return this;
    }

    public ShowSearch category(String category) {
        this.category = category;
        return this;
    }

    public ShowSearch status(String status) {
        this.status = status;
        return this;
    }

    public ShowSearch sort(String sort) {
        this.sort = sort;
        return this;
    }

    public ShowSearch query(String query) {
        this.query = query;
        return this;
    }
}
