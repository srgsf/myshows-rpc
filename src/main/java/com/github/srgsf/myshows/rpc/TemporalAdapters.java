/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.srgsf.myshows.rpc;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

/**
 * Helper for date/time formats handling during serialization.
 *
 * @author srgsf
 * @since 0.1
 */
@SuppressWarnings("unused")
class TemporalAdapters {

    /**
     * Date and time adapter.
     *
     * @author srgsf
     * @since 0.1
     */
    static class OffsetDateTimeAdapter {
        private final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(ISO_LOCAL_DATE_TIME)
                .appendOffset("+HHMM", "+0000").toFormatter();

        @ToJson
        String toJson(OffsetDateTime dt) {
            if (dt == null) {
                return null;
            }
            return dt.format(formatter);
        }

        @FromJson
        OffsetDateTime fromJson(String s) {
            if (s == null || s.trim().length() == 0) {
                return null;
            }
            return OffsetDateTime.parse(s, formatter);
        }
    }

    /**
     * Date only adapter.
     *
     * @author srgsf
     * @since 0.1
     */
    static class LocalDateAdapter {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/dd/uuuu");

        @ToJson
        String toJson(LocalDate d) {
            if (d == null) {
                return null;
            }
            return d.format(formatter);
        }

        @FromJson
        LocalDate fromJson(String s) {
            if (s == null || s.trim().length() == 0) {
                return null;
            }
            return LocalDate.parse(s, formatter);
        }
    }
}
