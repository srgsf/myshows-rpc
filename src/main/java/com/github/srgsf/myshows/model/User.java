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
public class User {

    @Json(name = "login")
    public final String login;

    @Json(name = "avatar")
    public final String avatar;

    @Json(name = "wastedTime")
    public final Integer wastedTimeHours;

    @Json(name = "gender")
    public final Gender gender;

    @Json(name = "isPro")
    public final Boolean isPro;

    User(String login, String avatar, Integer wastedTimeHours, Gender gender, Boolean isPro) {
        this.login = login;
        this.avatar = avatar;
        this.wastedTimeHours = wastedTimeHours;
        this.gender = gender;
        this.isPro = isPro;
    }
}
