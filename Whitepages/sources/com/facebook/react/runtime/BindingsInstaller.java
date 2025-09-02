package com.facebook.react.runtime;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.proguard.annotations.DoNotStripAny;
import com.facebook.soloader.SoLoader;
import kotlin.jvm.internal.DefaultConstructorMarker;

@DoNotStripAny
public abstract class BindingsInstaller {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @DoNotStrip
    private final HybridData mHybridData;

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public BindingsInstaller(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    static {
        SoLoader.loadLibrary("rninstance");
    }
}
