package com.facebook.react.runtime.hermes;

import com.facebook.jni.HybridData;
import com.facebook.jni.annotations.DoNotStrip;
import com.facebook.react.runtime.JSRuntimeFactory;
import com.facebook.soloader.SoLoader;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class HermesInstance extends JSRuntimeFactory {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @DoNotStrip
    protected static final native HybridData initHybrid(boolean z);

    public HermesInstance(boolean z) {
        super(initHybrid(z));
    }

    public HermesInstance() {
        this(false);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: protected */
        @DoNotStrip
        public final HybridData initHybrid(boolean z) {
            return HermesInstance.initHybrid(z);
        }

        private Companion() {
        }
    }

    static {
        SoLoader.loadLibrary("hermesinstancejni");
    }
}
