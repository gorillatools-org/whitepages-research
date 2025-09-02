package androidx.tracing;

import android.os.Trace;

abstract class TraceApi18Impl {
    public static void beginSection(String str) {
        Trace.beginSection(str);
    }

    public static void endSection() {
        Trace.endSection();
    }
}
