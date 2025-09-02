package com.facebook.react.modules.deviceinfo;

import android.content.Context;
import com.facebook.fbreact.specs.NativeDeviceInfoSpec;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "DeviceInfo")
public final class DeviceInfoModule extends NativeDeviceInfoSpec implements LifecycleEventListener {
    private float fontScale;
    private ReadableMap previousDisplayMetrics;
    private ReactApplicationContext reactApplicationContext;

    public void onHostDestroy() {
    }

    public void onHostPause() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DeviceInfoModule(ReactApplicationContext reactApplicationContext2) {
        super(reactApplicationContext2);
        Intrinsics.checkNotNullParameter(reactApplicationContext2, "reactContext");
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(reactApplicationContext2);
        this.fontScale = reactApplicationContext2.getResources().getConfiguration().fontScale;
        reactApplicationContext2.addLifecycleEventListener(this);
        this.reactApplicationContext = reactApplicationContext2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DeviceInfoModule(Context context) {
        super((ReactApplicationContext) null);
        Intrinsics.checkNotNullParameter(context, "context");
        this.reactApplicationContext = null;
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(context);
        this.fontScale = context.getResources().getConfiguration().fontScale;
    }

    public Map<String, Object> getTypedExportedConstants() {
        WritableMap displayMetricsWritableMap = DisplayMetricsHolder.getDisplayMetricsWritableMap((double) this.fontScale);
        this.previousDisplayMetrics = displayMetricsWritableMap.copy();
        return MapsKt.mapOf(TuplesKt.to("Dimensions", displayMetricsWritableMap.toHashMap()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r0 = (r0 = r0.getResources()).getConfiguration();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onHostResume() {
        /*
            r2 = this;
            com.facebook.react.bridge.ReactApplicationContext r0 = r2.reactApplicationContext
            if (r0 == 0) goto L_0x0017
            android.content.res.Resources r0 = r0.getResources()
            if (r0 == 0) goto L_0x0017
            android.content.res.Configuration r0 = r0.getConfiguration()
            if (r0 == 0) goto L_0x0017
            float r0 = r0.fontScale
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            goto L_0x0018
        L_0x0017:
            r0 = 0
        L_0x0018:
            if (r0 == 0) goto L_0x002b
            float r1 = r2.fontScale
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Float) r0, (float) r1)
            if (r1 != 0) goto L_0x002b
            float r0 = r0.floatValue()
            r2.fontScale = r0
            r2.emitUpdateDimensionsEvent()
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.deviceinfo.DeviceInfoModule.onHostResume():void");
    }

    public final void emitUpdateDimensionsEvent() {
        ReactApplicationContext reactApplicationContext2 = this.reactApplicationContext;
        if (reactApplicationContext2 == null) {
            return;
        }
        if (reactApplicationContext2.hasActiveReactInstance()) {
            WritableMap displayMetricsWritableMap = DisplayMetricsHolder.getDisplayMetricsWritableMap((double) this.fontScale);
            ReadableMap readableMap = this.previousDisplayMetrics;
            if (readableMap == null) {
                this.previousDisplayMetrics = displayMetricsWritableMap.copy();
            } else if (!Intrinsics.areEqual((Object) displayMetricsWritableMap, (Object) readableMap)) {
                this.previousDisplayMetrics = displayMetricsWritableMap.copy();
                reactApplicationContext2.emitDeviceEvent("didUpdateDimensions", displayMetricsWritableMap);
            }
        } else {
            ReactSoftExceptionLogger.logSoftException(NativeDeviceInfoSpec.NAME, new ReactNoCrashSoftException("No active CatalystInstance, cannot emitUpdateDimensionsEvent"));
        }
    }

    public void invalidate() {
        super.invalidate();
        ReactApplicationContext reactApplicationContext2 = this.reactApplicationContext;
        if (reactApplicationContext2 != null) {
            reactApplicationContext2.removeLifecycleEventListener(this);
        }
    }
}
