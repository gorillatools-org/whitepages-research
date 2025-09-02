package com.facebook.react.internal;

import com.facebook.systrace.Systrace;
import kotlin.jvm.internal.Intrinsics;

public final class SystraceSection implements AutoCloseable {
    public SystraceSection(String str) {
        Intrinsics.checkNotNullParameter(str, "sectionName");
        Systrace.beginSection(0, str);
    }

    public void close() {
        Systrace.endSection(0);
    }
}
