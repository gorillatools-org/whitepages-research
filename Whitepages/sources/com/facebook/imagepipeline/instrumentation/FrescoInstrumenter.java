package com.facebook.imagepipeline.instrumentation;

import kotlin.jvm.internal.Intrinsics;

public final class FrescoInstrumenter {
    public static final FrescoInstrumenter INSTANCE = new FrescoInstrumenter();

    public static final Runnable decorateRunnable(Runnable runnable, String str) {
        return runnable;
    }

    public static final boolean isTracing() {
        return false;
    }

    public static final void markFailure(Object obj, Throwable th) {
        Intrinsics.checkNotNullParameter(th, "th");
    }

    public static final Object onBeforeSubmitWork(String str) {
        return null;
    }

    public static final Object onBeginWork(Object obj, String str) {
        return null;
    }

    public static final void onEndWork(Object obj) {
    }

    private FrescoInstrumenter() {
    }
}
