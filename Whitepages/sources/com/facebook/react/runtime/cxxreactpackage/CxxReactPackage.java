package com.facebook.react.runtime.cxxreactpackage;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

public abstract class CxxReactPackage {
    @DoNotStrip
    private HybridData mHybridData;

    private static /* synthetic */ void getMHybridData$annotations() {
    }

    protected CxxReactPackage(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
