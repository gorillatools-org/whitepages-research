package com.facebook.react.jscexecutor;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.soloader.SoLoader;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@DoNotStrip
public final class JSCExecutor extends JavaScriptExecutor {
    private static final Companion Companion;

    /* access modifiers changed from: private */
    public static final native HybridData initHybrid(ReadableNativeMap readableNativeMap);

    public static final void loadLibrary() throws UnsatisfiedLinkError {
        Companion.loadLibrary();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JSCExecutor(ReadableNativeMap readableNativeMap) {
        super(Companion.initHybrid(readableNativeMap));
        Intrinsics.checkNotNullParameter(readableNativeMap, "jscConfig");
    }

    public String getName() {
        return "JSCExecutor";
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final HybridData initHybrid(ReadableNativeMap readableNativeMap) {
            return JSCExecutor.initHybrid(readableNativeMap);
        }

        private Companion() {
        }

        public final void loadLibrary() throws UnsatisfiedLinkError {
            SoLoader.loadLibrary("jscexecutor");
        }
    }

    static {
        Companion companion = new Companion((DefaultConstructorMarker) null);
        Companion = companion;
        companion.loadLibrary();
    }
}
