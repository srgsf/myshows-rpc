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

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ProfileSettingInfo {
    @Json(name = "title")
    public final String title;

    @Json(name = "alias")
    public final String alias;

    @Json(name = "isPro")
    public final Boolean isPro;

    @Json(name = "value")
    public final String value;

    @Json(name = "options")
    public final List<SettingValueInfo> options = new ArrayList<>();

    ProfileSettingInfo(String title, String alias, Boolean isPro, String value) {
        this.title = title;
        this.alias = alias;
        this.isPro = isPro;
        this.value = value;
    }
}
