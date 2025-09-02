package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

public class RuntimeExecutor {
    @DoNotStrip
    private HybridData mHybridData;

    public RuntimeExecutor(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
