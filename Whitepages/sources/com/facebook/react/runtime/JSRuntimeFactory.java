package com.facebook.react.runtime;

import com.facebook.jni.HybridData;
import com.facebook.soloader.SoLoader;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class JSRuntimeFactory {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final HybridData mHybridData;

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public JSRuntimeFactory(HybridData hybridData) {
        Intrinsics.checkNotNullParameter(hybridData, "mHybridData");
        this.mHybridData = hybridData;
    }

    static {
        SoLoader.loadLibrary("rninstance");
    }
}
