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
public class UserSocial {

    @Json(name = "vkontakte")
    public final String vkontakte;

    @Json(name = "twitter")
    public final String twitter;

    UserSocial(String vkontakte, String twitter) {
        this.vkontakte = vkontakte;
        this.twitter = twitter;
    }
}
