package com.facebook.react.modules.appstate;

import com.facebook.fbreact.specs.NativeAppStateSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WindowFocusChangeListener;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.module.annotations.ReactModule;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "AppState")
public final class AppStateModule extends NativeAppStateSpec implements LifecycleEventListener, WindowFocusChangeListener {
    public static final String APP_STATE_ACTIVE = "active";
    public static final String APP_STATE_BACKGROUND = "background";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String INITIAL_STATE = "initialAppState";
    public static final String NAME = "AppState";
    private String appState;

    public void addListener(String str) {
    }

    public void onHostDestroy() {
    }

    public void removeListeners(double d) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppStateModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        String str;
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        reactApplicationContext.addLifecycleEventListener(this);
        reactApplicationContext.addWindowFocusChangeListener(this);
        if (reactApplicationContext.getLifecycleState() == LifecycleState.RESUMED) {
            str = "active";
        } else {
            str = APP_STATE_BACKGROUND;
        }
        this.appState = str;
    }

    public Map<String, Object> getTypedExportedConstants() {
        return MapsKt.mapOf(TuplesKt.to(INITIAL_STATE, this.appState));
    }

    public void getCurrentAppState(Callback callback, Callback callback2) {
        Intrinsics.checkNotNullParameter(callback, FirebaseAnalytics.Param.SUCCESS);
        callback.invoke(createAppStateEventMap());
    }

    public void onHostResume() {
        this.appState = "active";
        sendAppStateChangeEvent();
    }

    public void onHostPause() {
        this.appState = APP_STATE_BACKGROUND;
        sendAppStateChangeEvent();
    }

    public void onWindowFocusChange(boolean z) {
        sendEvent("appStateFocusChange", Boolean.valueOf(z));
    }

    private final WritableMap createAppStateEventMap() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("app_state", this.appState);
        Intrinsics.checkNotNullExpressionValue(createMap, "apply(...)");
        return createMap;
    }

    private final void sendEvent(String str, Object obj) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        if (reactApplicationContext != null && reactApplicationContext.hasActiveReactInstance()) {
            reactApplicationContext.emitDeviceEvent(str, obj);
        }
    }

    private final void sendAppStateChangeEvent() {
        sendEvent("appStateDidChange", createAppStateEventMap());
    }

    public void invalidate() {
        super.invalidate();
        getReactApplicationContext().removeLifecycleEventListener(this);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
