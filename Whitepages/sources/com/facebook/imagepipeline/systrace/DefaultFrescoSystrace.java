package com.facebook.imagepipeline.systrace;

import android.os.Trace;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import kotlin.jvm.internal.Intrinsics;

public final class DefaultFrescoSystrace implements FrescoSystrace.Systrace {
    public boolean isTracing() {
        return false;
    }

    public void beginSection(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        if (isTracing()) {
            Trace.beginSection(str);
        }
    }

    public void endSection() {
        if (isTracing()) {
            Trace.endSection();
        }
    }
}
