package com.facebook.react.bridge;

import com.facebook.jni.HybridClassBase;
import com.facebook.proguard.annotations.DoNotStrip;
import kotlin.jvm.internal.DefaultConstructorMarker;

@DoNotStrip
public abstract class NativeArray extends HybridClassBase implements NativeArrayInterface {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public native String toString();

    protected NativeArray() {
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        ReactBridge.staticInit();
    }
}
