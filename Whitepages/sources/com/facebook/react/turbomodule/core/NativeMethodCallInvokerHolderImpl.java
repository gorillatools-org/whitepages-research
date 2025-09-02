package com.facebook.react.turbomodule.core;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.common.annotations.FrameworkAPI;
import com.facebook.react.internal.turbomodule.core.NativeModuleSoLoader;
import com.facebook.react.turbomodule.core.interfaces.NativeMethodCallInvokerHolder;
import kotlin.jvm.internal.DefaultConstructorMarker;

@FrameworkAPI
public final class NativeMethodCallInvokerHolderImpl implements NativeMethodCallInvokerHolder {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @DoNotStrip
    private final HybridData mHybridData;

    private NativeMethodCallInvokerHolderImpl(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        NativeModuleSoLoader.Companion.maybeLoadSoLibrary();
    }
}
