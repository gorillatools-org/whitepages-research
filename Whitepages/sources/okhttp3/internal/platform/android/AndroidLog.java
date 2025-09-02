package okhttp3.internal.platform.android;

import android.util.Log;
import com.facebook.react.devsupport.StackTraceHelper;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.internal.SuppressSignatureCheck;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http2.Http2;

@SuppressSignatureCheck
public final class AndroidLog {
    public static final AndroidLog INSTANCE = new AndroidLog();
    private static final int MAX_LOG_LENGTH = 4000;
    private static final CopyOnWriteArraySet<Logger> configuredLoggers = new CopyOnWriteArraySet<>();
    private static final Map<String, String> knownLoggers;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Class<OkHttpClient> cls = OkHttpClient.class;
        Package packageR = cls.getPackage();
        String name = packageR != null ? packageR.getName() : null;
        if (name != null) {
            linkedHashMap.put(name, "OkHttp");
        }
        String name2 = cls.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "OkHttpClient::class.java.name");
        linkedHashMap.put(name2, "okhttp.OkHttpClient");
        String name3 = Http2.class.getName();
        Intrinsics.checkNotNullExpressionValue(name3, "Http2::class.java.name");
        linkedHashMap.put(name3, "okhttp.Http2");
        String name4 = TaskRunner.class.getName();
        Intrinsics.checkNotNullExpressionValue(name4, "TaskRunner::class.java.name");
        linkedHashMap.put(name4, "okhttp.TaskRunner");
        linkedHashMap.put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
        knownLoggers = MapsKt.toMap((Map) linkedHashMap);
    }

    private AndroidLog() {
    }

    public final void androidLog$okhttp(String str, int i, String str2, Throwable th) {
        int min;
        Intrinsics.checkNotNullParameter(str, "loggerName");
        Intrinsics.checkNotNullParameter(str2, StackTraceHelper.MESSAGE_KEY);
        String loggerTag = loggerTag(str);
        if (Log.isLoggable(loggerTag, i)) {
            if (th != null) {
                str2 = str2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + Log.getStackTraceString(th);
            }
            int length = str2.length();
            int i2 = 0;
            while (i2 < length) {
                int indexOf$default = StringsKt.indexOf$default((CharSequence) str2, 10, i2, false, 4, (Object) null);
                if (indexOf$default == -1) {
                    indexOf$default = length;
                }
                while (true) {
                    min = Math.min(indexOf$default, i2 + MAX_LOG_LENGTH);
                    String substring = str2.substring(i2, min);
                    Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    Log.println(i, loggerTag, substring);
                    if (min >= indexOf$default) {
                        break;
                    }
                    i2 = min;
                }
                i2 = min + 1;
            }
        }
    }

    private final String loggerTag(String str) {
        String str2 = knownLoggers.get(str);
        return str2 != null ? str2 : StringsKt.take(str, 23);
    }

    public final void enable() {
        for (Map.Entry next : knownLoggers.entrySet()) {
            enableLogging((String) next.getKey(), (String) next.getValue());
        }
    }

    private final void enableLogging(String str, String str2) {
        Level level;
        Logger logger = Logger.getLogger(str);
        if (configuredLoggers.add(logger)) {
            Intrinsics.checkNotNullExpressionValue(logger, "logger");
            logger.setUseParentHandlers(false);
            if (Log.isLoggable(str2, 3)) {
                level = Level.FINE;
            } else if (Log.isLoggable(str2, 4)) {
                level = Level.INFO;
            } else {
                level = Level.WARNING;
            }
            logger.setLevel(level);
            logger.addHandler(AndroidLogHandler.INSTANCE);
        }
    }
}
