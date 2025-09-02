package okhttp3.internal.http;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import okhttp3.internal.Util;

public final class DatesKt$STANDARD_DATE_FORMAT$1 extends ThreadLocal<DateFormat> {
    DatesKt$STANDARD_DATE_FORMAT$1() {
    }

    /* access modifiers changed from: protected */
    public DateFormat initialValue() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setLenient(false);
        simpleDateFormat.setTimeZone(Util.UTC);
        return simpleDateFormat;
    }
}
