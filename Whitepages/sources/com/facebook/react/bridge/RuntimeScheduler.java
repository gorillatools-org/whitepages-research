package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

public class RuntimeScheduler {
    @DoNotStrip
    private HybridData mHybridData;

    public RuntimeScheduler(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
