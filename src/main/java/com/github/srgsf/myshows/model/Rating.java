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

public enum Rating {
    none, lowest, low, average, high, highest;

    public static class Adapter {
        @ToJson
        int toJson(Rating rating) {
            if (rating == null) {
                return 0;
            }
            switch (rating) {
                case none:
                    return 0;
                case highest:
                    return 5;
                case high:
                    return 4;
                case average:
                    return 3;
                case low:
                    return 2;
                case lowest:
                    return 1;
            }
            throw new JsonDataException("unknown rating: " + rating);
        }

        @FromJson
        Rating fromJson(int r) {
            switch (r) {
                case 0:
                    return none;
                case 1:
                    return lowest;
                case 2:
                    return low;
                case 3:
                    return average;
                case 4:
                    return high;
                case 5:
                    return highest;
            }
            throw new JsonDataException("unknown rating: " + r);
        }
    }
}
