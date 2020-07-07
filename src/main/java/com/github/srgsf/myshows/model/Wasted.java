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
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.ToJson;

public enum Wasted {
    None, Hours, Days, Weeks, Months, Years;

    public static class Adapter {
        @ToJson
        int toJson(Wasted wasted) {
            if (wasted == null) {
                return 1;
            }
            switch (wasted) {
                case None:
                    return 1;
                case Hours:
                    return 2;
                case Days:
                    return 3;
                case Weeks:
                    return 4;
                case Months:
                    return 5;
                case Years:
                    return 6;
            }
            throw new JsonDataException("unknown rating: " + wasted);
        }

        @FromJson
        Wasted fromJson(Integer r) {
            if (r == null) {
                return None;
            }
            switch (r) {
                case 1:
                    return None;
                case 2:
                    return Hours;
                case 3:
                    return Days;
                case 4:
                    return Weeks;
                case 5:
                    return Months;
                case 6:
                    return Years;
            }
            throw new JsonDataException("unknown wasted: " + r);
        }
    }
}
