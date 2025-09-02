package com.facebook.react.fabric;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStripAny;
import kotlin.jvm.internal.DefaultConstructorMarker;

@DoNotStripAny
public final class ComponentFactory {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final HybridData mHybridData = Companion.initHybrid();

    private static /* synthetic */ void getMHybridData$annotations() {
    }

    /* access modifiers changed from: private */
    public static final native HybridData initHybrid();

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final HybridData initHybrid() {
            return ComponentFactory.initHybrid();
        }

        private Companion() {
        }
    }

    static {
        FabricSoLoader.staticInit();
    }
}
