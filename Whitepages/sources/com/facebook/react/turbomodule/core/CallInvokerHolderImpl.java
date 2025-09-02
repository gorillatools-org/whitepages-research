package com.facebook.react.turbomodule.core;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.common.annotations.FrameworkAPI;
import com.facebook.react.internal.turbomodule.core.NativeModuleSoLoader;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import kotlin.jvm.internal.DefaultConstructorMarker;

@FrameworkAPI
public final class CallInvokerHolderImpl implements CallInvokerHolder {
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

    private CallInvokerHolderImpl(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    static {
        NativeModuleSoLoader.Companion.maybeLoadSoLibrary();
    }
}
