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

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.ToJson;

public enum Gender {
    @Json(name = "m")
    Male,
    @Json(name = "f")
    Female,
    @Json(name = "x")
    Unknown;

    public static class Adapter {
        @ToJson
        String toJson(Gender gender) {
            if (gender == null) {
                return null;
            }
            switch (gender) {
                case Male:
                    return "m";
                case Female:
                    return "f";
                case Unknown:
                    return "x";
            }
            throw new JsonDataException("unknown gender: " + gender);
        }

        @FromJson
        Gender fromJson(String s) {
            if (s == null || s.trim().length() == 0) {
                return Unknown;
            }
            switch (s) {
                case "m":
                    return Male;
                case "f":
                    return Female;
                case "x":
                    return Unknown;
            }
            throw new JsonDataException("unknown gender: " + s);
        }
    }
}
