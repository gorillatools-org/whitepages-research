package com.facebook.react.fabric.events;

import android.annotation.SuppressLint;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.fabric.FabricSoLoader;
import com.facebook.react.uimanager.events.BatchEventDispatchedListener;
import kotlin.jvm.internal.DefaultConstructorMarker;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
public final class EventBeatManager implements BatchEventDispatchedListener {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @DoNotStrip
    private final HybridData mHybridData;

    private static /* synthetic */ void getMHybridData$annotations() {
    }

    /* access modifiers changed from: private */
    public static final native HybridData initHybrid();

    private final native void tick();

    public EventBeatManager() {
        this.mHybridData = Companion.initHybrid();
    }

    public EventBeatManager(ReactApplicationContext reactApplicationContext) {
        this();
    }

    public void onBatchEventDispatched() {
        tick();
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final HybridData initHybrid() {
            return EventBeatManager.initHybrid();
        }

        private Companion() {
        }
    }

    static {
        FabricSoLoader.staticInit();
    }
}
