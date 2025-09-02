package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.common.ViewUtil;
import com.salesforce.marketingcloud.config.a;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReactEventEmitter implements RCTModernEventEmitter {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "ReactEventEmitter";
    private RCTEventEmitter defaultEventEmitter;
    private RCTModernEventEmitter fabricEventEmitter;
    private final ReactApplicationContext reactContext;

    public ReactEventEmitter(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        this.reactContext = reactApplicationContext;
    }

    public final void register(int i, RCTModernEventEmitter rCTModernEventEmitter) {
        if (i == 2) {
            this.fabricEventEmitter = rCTModernEventEmitter;
            return;
        }
        throw new IllegalStateException("Check failed.");
    }

    public final void register(int i, RCTEventEmitter rCTEventEmitter) {
        if (i == 1) {
            this.defaultEventEmitter = rCTEventEmitter;
            return;
        }
        throw new IllegalStateException("Check failed.");
    }

    public final void unregister(int i) {
        if (i == 1) {
            this.defaultEventEmitter = null;
        } else {
            this.fabricEventEmitter = null;
        }
    }

    public void receiveEvent(int i, String str, WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(str, a.h);
        receiveEvent(-1, i, str, writableMap);
    }

    public void receiveEvent(int i, int i2, String str, WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(str, a.h);
        receiveEvent(i, i2, str, false, 0, writableMap, 2);
    }

    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        RCTEventEmitter ensureDefaultEventEmitter;
        Intrinsics.checkNotNullParameter(str, a.h);
        Intrinsics.checkNotNullParameter(writableArray, "touches");
        Intrinsics.checkNotNullParameter(writableArray2, "changedIndices");
        if (writableArray.size() > 0) {
            int i = 0;
            ReadableMap map = writableArray.getMap(0);
            if (map != null) {
                i = map.getInt(TouchesHelper.TARGET_KEY);
            }
            if (ViewUtil.getUIManagerType(i) == 1 && (ensureDefaultEventEmitter = ensureDefaultEventEmitter()) != null) {
                ensureDefaultEventEmitter.receiveTouches(str, writableArray, writableArray2);
                return;
            }
            return;
        }
        throw new IllegalStateException("Check failed.");
    }

    public void receiveTouches(TouchEvent touchEvent) {
        RCTModernEventEmitter rCTModernEventEmitter;
        Intrinsics.checkNotNullParameter(touchEvent, "event");
        int uIManagerType = ViewUtil.getUIManagerType(touchEvent.getViewTag(), touchEvent.getSurfaceId());
        if (uIManagerType == 1) {
            RCTEventEmitter ensureDefaultEventEmitter = ensureDefaultEventEmitter();
            if (ensureDefaultEventEmitter != null) {
                TouchesHelper.sendTouchesLegacy(ensureDefaultEventEmitter, touchEvent);
            }
        } else if (uIManagerType == 2 && (rCTModernEventEmitter = this.fabricEventEmitter) != null) {
            TouchesHelper.sendTouchEvent(rCTModernEventEmitter, touchEvent);
        }
    }

    private final RCTEventEmitter ensureDefaultEventEmitter() {
        if (this.defaultEventEmitter == null) {
            if (this.reactContext.hasActiveReactInstance()) {
                this.defaultEventEmitter = (RCTEventEmitter) this.reactContext.getJSModule(RCTEventEmitter.class);
            } else {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Cannot get RCTEventEmitter from Context, no active Catalyst instance!"));
            }
        }
        return this.defaultEventEmitter;
    }

    public void receiveEvent(int i, int i2, String str, boolean z, int i3, WritableMap writableMap, int i4) {
        int i5 = i2;
        String str2 = str;
        Intrinsics.checkNotNullParameter(str, a.h);
        int i6 = i;
        int uIManagerType = ViewUtil.getUIManagerType(i2, i);
        if (uIManagerType == 1) {
            RCTEventEmitter ensureDefaultEventEmitter = ensureDefaultEventEmitter();
            if (ensureDefaultEventEmitter != null) {
                WritableMap writableMap2 = writableMap;
                ensureDefaultEventEmitter.receiveEvent(i2, str, writableMap);
            }
        } else if (uIManagerType != 2) {
        } else {
            RCTModernEventEmitter rCTModernEventEmitter = this.fabricEventEmitter;
            if (rCTModernEventEmitter != null) {
                rCTModernEventEmitter.receiveEvent(i, i2, str, z, i3, writableMap, i4);
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
