package com.zoontek.rnpermissions;

import android.util.SparseArray;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.PermissionListener;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RNPermissions")
public final class RNPermissionsModule extends ReactContextBaseJavaModule implements PermissionListener {
    private final SparseArray<Callback> callbacks = new SparseArray<>();

    public RNPermissionsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return "RNPermissions";
    }

    @ReactMethod
    public final void openSettings(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.openSettings(reactApplicationContext, str, promise);
    }

    @ReactMethod
    public final void canScheduleExactAlarms(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.canScheduleExactAlarms(reactApplicationContext, promise);
    }

    @ReactMethod
    public final void check(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "permission");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.check(reactApplicationContext, str, promise);
    }

    @ReactMethod
    public final void checkNotifications(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.checkNotifications(reactApplicationContext, promise);
    }

    @ReactMethod
    public final void checkMultiple(ReadableArray readableArray, Promise promise) {
        Intrinsics.checkNotNullParameter(readableArray, "permissions");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.checkMultiple(reactApplicationContext, readableArray, promise);
    }

    @ReactMethod
    public final void request(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "permission");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.request(reactApplicationContext, this, this.callbacks, str, promise);
    }

    @ReactMethod
    public final void requestNotifications(ReadableArray readableArray, Promise promise) {
        Intrinsics.checkNotNullParameter(readableArray, "options");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.requestNotifications(reactApplicationContext, promise);
    }

    @ReactMethod
    public final void requestMultiple(ReadableArray readableArray, Promise promise) {
        Intrinsics.checkNotNullParameter(readableArray, "permissions");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.requestMultiple(reactApplicationContext, this, this.callbacks, readableArray, promise);
    }

    @ReactMethod
    public final void shouldShowRequestRationale(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "permission");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        rNPermissionsModuleImpl.shouldShowRequestRationale(reactApplicationContext, str, promise);
    }

    @ReactMethod
    public final void checkLocationAccuracy(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        RNPermissionsModuleImpl.INSTANCE.checkLocationAccuracy(promise);
    }

    @ReactMethod
    public final void requestLocationAccuracy(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "purposeKey");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        RNPermissionsModuleImpl.INSTANCE.requestLocationAccuracy(promise);
    }

    @ReactMethod
    public final void openPhotoPicker(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        RNPermissionsModuleImpl.INSTANCE.openPhotoPicker(promise);
    }

    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Intrinsics.checkNotNullParameter(strArr, "permissions");
        Intrinsics.checkNotNullParameter(iArr, "grantResults");
        RNPermissionsModuleImpl rNPermissionsModuleImpl = RNPermissionsModuleImpl.INSTANCE;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        return rNPermissionsModuleImpl.onRequestPermissionsResult(reactApplicationContext, this.callbacks, i, iArr);
    }
}
