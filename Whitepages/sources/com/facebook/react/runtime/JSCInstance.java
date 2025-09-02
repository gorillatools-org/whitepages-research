package com.facebook.react.runtime;

import com.facebook.jni.HybridData;
import com.facebook.jni.annotations.DoNotStrip;
import com.facebook.jni.annotations.DoNotStripAny;
import com.facebook.soloader.SoLoader;
import kotlin.jvm.internal.DefaultConstructorMarker;

@DoNotStripAny
public final class JSCInstance extends JSRuntimeFactory {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    /* access modifiers changed from: private */
    @DoNotStrip
    public static final native HybridData initHybrid();

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        @DoNotStrip
        public final HybridData initHybrid() {
            return JSCInstance.initHybrid();
        }

        private Companion() {
        }
    }

    public JSCInstance() {
        super(Companion.initHybrid());
    }

    static {
        SoLoader.loadLibrary("jscinstance");
    }
}
