package androidx.core.os;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.Method;

public abstract class TraceCompat {
    private static Method sAsyncTraceBeginMethod;
    private static Method sAsyncTraceEndMethod;
    private static Method sIsTagEnabledMethod;
    private static Method sTraceCounterMethod;
    private static long sTraceTagApp;

    static {
        Class<String> cls = String.class;
        Class<Trace> cls2 = Trace.class;
        if (Build.VERSION.SDK_INT < 29) {
            try {
                sTraceTagApp = cls2.getField("TRACE_TAG_APP").getLong((Object) null);
                Class cls3 = Long.TYPE;
                sIsTagEnabledMethod = cls2.getMethod("isTagEnabled", new Class[]{cls3});
                Class cls4 = Integer.TYPE;
                sAsyncTraceBeginMethod = cls2.getMethod("asyncTraceBegin", new Class[]{cls3, cls, cls4});
                sAsyncTraceEndMethod = cls2.getMethod("asyncTraceEnd", new Class[]{cls3, cls, cls4});
                sTraceCounterMethod = cls2.getMethod("traceCounter", new Class[]{cls3, cls, cls4});
            } catch (Exception e) {
                Log.i("TraceCompat", "Unable to initialize via reflection.", e);
            }
        }
    }

    public static void beginSection(String str) {
        Trace.beginSection(str);
    }

    public static void endSection() {
        Trace.endSection();
    }
}
