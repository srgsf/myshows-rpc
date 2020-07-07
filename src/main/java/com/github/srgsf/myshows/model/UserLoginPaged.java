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

public class UserLoginPaged {
    @Json(name = "login")
    public String login;

    @Json(name = "page")
    public Integer page;

    @Json(name = "pageSize")
    public Integer pageSize;

    @Json(name = "sort")
    public String sort;

    public UserLoginPaged login(String login) {
        this.login = login;
        return this;
    }

    public UserLoginPaged page(Integer page) {
        this.page = page;
        return this;
    }

    public UserLoginPaged pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public UserLoginPaged sort(String sort) {
        this.sort = sort;
        return this;
    }
}
