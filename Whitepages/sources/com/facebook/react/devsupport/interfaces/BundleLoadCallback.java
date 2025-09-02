package com.facebook.react.devsupport.interfaces;

import kotlin.jvm.internal.Intrinsics;

public interface BundleLoadCallback {
    void onError(Exception exc) {
        Intrinsics.checkNotNullParameter(exc, "cause");
    }

    void onSuccess();
}
