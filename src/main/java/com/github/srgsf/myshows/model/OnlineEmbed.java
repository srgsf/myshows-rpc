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
public class OnlineEmbed {
    @Json(name = "embedDesktop")
    public final String embedDesktop;

    @Json(name = "embedMobile")
    public final String embedMobile;

    @Json(name = "sourceName")
    public final String sourceName;

    @Json(name = "sourceUrl")
    public final String sourceUrl;

    OnlineEmbed(String embedDesktop, String embedMobile, String sourceName, String sourceUrl) {
        this.embedDesktop = embedDesktop;
        this.embedMobile = embedMobile;
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
    }
}
