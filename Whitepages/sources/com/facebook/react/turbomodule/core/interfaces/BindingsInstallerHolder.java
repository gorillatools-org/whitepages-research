package com.facebook.react.turbomodule.core.interfaces;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import kotlin.jvm.internal.Intrinsics;

@DoNotStrip
public final class BindingsInstallerHolder {
    @DoNotStrip
    private final HybridData mHybridData;

    public BindingsInstallerHolder(HybridData hybridData) {
        Intrinsics.checkNotNullParameter(hybridData, "mHybridData");
        this.mHybridData = hybridData;
    }
}
