package com.facebook.react.modules.permissions;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativePermissionsAndroidSpec;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "PermissionsAndroid")
public final class PermissionsModule extends NativePermissionsAndroidSpec implements PermissionListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String ERROR_INVALID_ACTIVITY = "E_INVALID_ACTIVITY";
    public static final String NAME = "PermissionsAndroid";
    /* access modifiers changed from: private */
    public final String DENIED = "denied";
    /* access modifiers changed from: private */
    public final String GRANTED = "granted";
    /* access modifiers changed from: private */
    public final String NEVER_ASK_AGAIN = "never_ask_again";
    private final SparseArray<Callback> callbacks = new SparseArray<>();
    private int requestCode;

    public PermissionsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public void checkPermission(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "permission");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        promise.resolve(Boolean.valueOf(getReactApplicationContext().getBaseContext().checkSelfPermission(str) == 0));
    }

    public void shouldShowRequestPermissionRationale(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "permission");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        try {
            promise.resolve(Boolean.valueOf(getPermissionAwareActivity().shouldShowRequestPermissionRationale(str)));
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, (Throwable) e);
        }
    }

    public void requestPermission(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "permission");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        if (getReactApplicationContext().getBaseContext().checkSelfPermission(str) == 0) {
            promise.resolve(this.GRANTED);
            return;
        }
        try {
            PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity();
            this.callbacks.put(this.requestCode, new PermissionsModule$requestPermission$1(promise, this, str));
            permissionAwareActivity.requestPermissions(new String[]{str}, this.requestCode, this);
            this.requestCode++;
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, (Throwable) e);
        }
    }

    public void requestMultiplePermissions(ReadableArray readableArray, Promise promise) {
        Intrinsics.checkNotNullParameter(readableArray, "permissions");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        ArrayList arrayList = new ArrayList();
        Context baseContext = getReactApplicationContext().getBaseContext();
        int size = readableArray.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            String string = readableArray.getString(i2);
            if (string != null) {
                if (baseContext.checkSelfPermission(string) == 0) {
                    writableNativeMap.putString(string, this.GRANTED);
                    i++;
                } else {
                    arrayList.add(string);
                }
            }
        }
        if (readableArray.size() == i) {
            promise.resolve(writableNativeMap);
            return;
        }
        try {
            PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity();
            this.callbacks.put(this.requestCode, new PermissionsModule$requestMultiplePermissions$1(arrayList, writableNativeMap, this, promise));
            permissionAwareActivity.requestPermissions((String[]) arrayList.toArray(new String[0]), this.requestCode, this);
            this.requestCode++;
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, (Throwable) e);
        }
    }

    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Intrinsics.checkNotNullParameter(strArr, "permissions");
        Intrinsics.checkNotNullParameter(iArr, "grantResults");
        try {
            Callback callback = this.callbacks.get(i);
            if (callback != null) {
                callback.invoke(iArr, getPermissionAwareActivity());
                this.callbacks.remove(i);
            } else {
                FLog.w("PermissionsModule", "Unable to find callback with requestCode %d", Integer.valueOf(i));
            }
            if (this.callbacks.size() == 0) {
                return true;
            }
            return false;
        } catch (IllegalStateException e) {
            FLog.e("PermissionsModule", (Throwable) e, "Unexpected invocation of `onRequestPermissionsResult` with invalid current activity", new Object[0]);
            return false;
        }
    }

    private final PermissionAwareActivity getPermissionAwareActivity() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
        } else if (currentActivity instanceof PermissionAwareActivity) {
            return (PermissionAwareActivity) currentActivity;
        } else {
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
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
