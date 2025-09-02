package com.zoontek.rnpermissions;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.SparseArray;
import androidx.core.app.NotificationManagerCompat;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class RNPermissionsModuleImpl {
    public static final RNPermissionsModuleImpl INSTANCE = new RNPermissionsModuleImpl();
    private static final Map minimumApi;
    private static int requestCode;

    private RNPermissionsModuleImpl() {
    }

    static {
        Pair pair = TuplesKt.to("android.permission.ACCEPT_HANDOVER", 28);
        Pair pair2 = TuplesKt.to("android.permission.ACCESS_BACKGROUND_LOCATION", 29);
        Pair pair3 = TuplesKt.to("android.permission.ACCESS_MEDIA_LOCATION", 29);
        Pair pair4 = TuplesKt.to("android.permission.ACTIVITY_RECOGNITION", 29);
        Pair pair5 = TuplesKt.to("android.permission.ANSWER_PHONE_CALLS", 26);
        Pair pair6 = TuplesKt.to("android.permission.BLUETOOTH_ADVERTISE", 31);
        Pair pair7 = TuplesKt.to("android.permission.BLUETOOTH_CONNECT", 31);
        Pair pair8 = TuplesKt.to("android.permission.BLUETOOTH_SCAN", 31);
        Pair pair9 = TuplesKt.to("android.permission.BODY_SENSORS_BACKGROUND", 33);
        Pair pair10 = TuplesKt.to("android.permission.NEARBY_WIFI_DEVICES", 33);
        Pair pair11 = TuplesKt.to("android.permission.READ_MEDIA_AUDIO", 33);
        Pair pair12 = TuplesKt.to("android.permission.READ_MEDIA_IMAGES", 33);
        Pair pair13 = TuplesKt.to("android.permission.READ_MEDIA_VIDEO", 33);
        Pair pair14 = pair9;
        Pair pair15 = pair10;
        Pair pair16 = pair11;
        Pair pair17 = pair12;
        Pair pair18 = pair13;
        minimumApi = MapsKt.mapOf(pair, pair2, pair3, pair4, pair5, pair6, pair7, pair8, pair14, pair15, pair16, pair17, pair18, TuplesKt.to("android.permission.READ_MEDIA_VISUAL_USER_SELECTED", 34), TuplesKt.to("android.permission.READ_PHONE_NUMBERS", 26), TuplesKt.to("android.permission.UWB_RANGING", 31));
    }

    private final boolean isPermissionAvailable(String str) {
        int i = Build.VERSION.SDK_INT;
        Integer num = (Integer) minimumApi.get(str);
        return i >= (num != null ? num.intValue() : 1);
    }

    public final void openSettings(ReactApplicationContext reactApplicationContext, String str, Promise promise) {
        Intent intent;
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        try {
            String packageName = reactApplicationContext.getPackageName();
            if (Build.VERSION.SDK_INT >= 31) {
                if (Intrinsics.areEqual((Object) str, (Object) "alarms")) {
                    intent = new Intent();
                    intent.setAction("android.settings.REQUEST_SCHEDULE_EXACT_ALARM");
                    intent.setData(Uri.parse("package:" + packageName));
                    intent.addFlags(268435456);
                    reactApplicationContext.startActivity(intent);
                    promise.resolve(Boolean.TRUE);
                }
            }
            if (Intrinsics.areEqual((Object) str, (Object) "notifications")) {
                intent = new Intent();
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("android.provider.extra.APP_PACKAGE", packageName);
            } else {
                intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.parse("package:" + packageName));
            }
            intent.addFlags(268435456);
            reactApplicationContext.startActivity(intent);
            promise.resolve(Boolean.TRUE);
        } catch (Exception e) {
            promise.reject("E_INVALID_ACTIVITY", (Throwable) e);
        }
    }

    public final void canScheduleExactAlarms(ReactApplicationContext reactApplicationContext, Promise promise) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        if (Build.VERSION.SDK_INT < 31) {
            promise.resolve(Boolean.TRUE);
            return;
        }
        Object systemService = reactApplicationContext.getSystemService("alarm");
        AlarmManager alarmManager = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
        promise.resolve(Boolean.valueOf(alarmManager != null ? alarmManager.canScheduleExactAlarms() : false));
    }

    public final void check(ReactApplicationContext reactApplicationContext, String str, Promise promise) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        Intrinsics.checkNotNullParameter(str, "permission");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        if (!isPermissionAvailable(str)) {
            promise.resolve("unavailable");
        } else if (reactApplicationContext.getBaseContext().checkSelfPermission(str) == 0) {
            promise.resolve("granted");
        } else {
            promise.resolve("denied");
        }
    }

    public final void checkNotifications(ReactApplicationContext reactApplicationContext, Promise promise) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        boolean areNotificationsEnabled = NotificationManagerCompat.from(reactApplicationContext).areNotificationsEnabled();
        WritableMap createMap = Arguments.createMap();
        createMap.putString("status", areNotificationsEnabled ? "granted" : "denied");
        createMap.putMap("settings", Arguments.createMap());
        promise.resolve(createMap);
    }

    public final void checkMultiple(ReactApplicationContext reactApplicationContext, ReadableArray readableArray, Promise promise) {
        String str;
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        Intrinsics.checkNotNullParameter(readableArray, "permissions");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        Context baseContext = reactApplicationContext.getBaseContext();
        int size = readableArray.size();
        for (int i = 0; i < size; i++) {
            String string = readableArray.getString(i);
            if (string != null && !StringsKt.isBlank(string)) {
                if (!isPermissionAvailable(string)) {
                    str = "unavailable";
                } else if (baseContext.checkSelfPermission(string) == 0) {
                    str = "granted";
                } else {
                    str = "denied";
                }
                writableNativeMap.putString(string, str);
            }
        }
        promise.resolve(writableNativeMap);
    }

    public final void request(ReactApplicationContext reactApplicationContext, PermissionListener permissionListener, SparseArray sparseArray, String str, Promise promise) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        Intrinsics.checkNotNullParameter(permissionListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Intrinsics.checkNotNullParameter(sparseArray, "callbacks");
        Intrinsics.checkNotNullParameter(str, "permission");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        if (!isPermissionAvailable(str)) {
            promise.resolve("unavailable");
        } else if (reactApplicationContext.getBaseContext().checkSelfPermission(str) == 0) {
            promise.resolve("granted");
        } else {
            try {
                PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity(reactApplicationContext);
                sparseArray.put(requestCode, new RNPermissionsModuleImpl$$ExternalSyntheticLambda0(promise, str));
                permissionAwareActivity.requestPermissions(new String[]{str}, requestCode, permissionListener);
                requestCode++;
            } catch (IllegalStateException e) {
                promise.reject("E_INVALID_ACTIVITY", (Throwable) e);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void request$lambda$5(Promise promise, String str, Object[] objArr) {
        String str2;
        Intrinsics.checkNotNullParameter(objArr, "args");
        int[] iArr = objArr[0];
        Intrinsics.checkNotNull(iArr, "null cannot be cast to non-null type kotlin.IntArray");
        PermissionAwareActivity permissionAwareActivity = objArr[1];
        Intrinsics.checkNotNull(permissionAwareActivity, "null cannot be cast to non-null type com.facebook.react.modules.core.PermissionAwareActivity");
        PermissionAwareActivity permissionAwareActivity2 = permissionAwareActivity;
        Integer orNull = ArraysKt.getOrNull(iArr, 0);
        if (orNull != null && orNull.intValue() == 0) {
            str2 = "granted";
        } else if (permissionAwareActivity2.shouldShowRequestPermissionRationale(str)) {
            str2 = "denied";
        } else {
            str2 = "blocked";
        }
        promise.resolve(str2);
    }

    public final void requestNotifications(ReactApplicationContext reactApplicationContext, Promise promise) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        boolean areNotificationsEnabled = NotificationManagerCompat.from(reactApplicationContext).areNotificationsEnabled();
        WritableMap createMap = Arguments.createMap();
        createMap.putString("status", areNotificationsEnabled ? "granted" : "blocked");
        createMap.putMap("settings", Arguments.createMap());
        promise.resolve(createMap);
    }

    public final void requestMultiple(ReactApplicationContext reactApplicationContext, PermissionListener permissionListener, SparseArray sparseArray, ReadableArray readableArray, Promise promise) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        Intrinsics.checkNotNullParameter(permissionListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Intrinsics.checkNotNullParameter(sparseArray, "callbacks");
        Intrinsics.checkNotNullParameter(readableArray, "permissions");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        ArrayList arrayList = new ArrayList();
        Context baseContext = reactApplicationContext.getBaseContext();
        int size = readableArray.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            String string = readableArray.getString(i2);
            if (string != null && !StringsKt.isBlank(string)) {
                if (!isPermissionAvailable(string)) {
                    writableNativeMap.putString(string, "unavailable");
                } else if (baseContext.checkSelfPermission(string) == 0) {
                    writableNativeMap.putString(string, "granted");
                } else {
                    arrayList.add(string);
                }
                i++;
            }
        }
        if (readableArray.size() == i) {
            promise.resolve(writableNativeMap);
            return;
        }
        try {
            PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity(reactApplicationContext);
            sparseArray.put(requestCode, new RNPermissionsModuleImpl$$ExternalSyntheticLambda1(arrayList, promise, writableNativeMap));
            permissionAwareActivity.requestPermissions((String[]) arrayList.toArray(new String[0]), requestCode, permissionListener);
            requestCode++;
        } catch (IllegalStateException e) {
            promise.reject("E_INVALID_ACTIVITY", (Throwable) e);
        }
    }

    /* access modifiers changed from: private */
    public static final void requestMultiple$lambda$8(ArrayList arrayList, Promise promise, WritableMap writableMap, Object[] objArr) {
        String str;
        Intrinsics.checkNotNullParameter(objArr, "args");
        int i = 0;
        int[] iArr = objArr[0];
        Intrinsics.checkNotNull(iArr, "null cannot be cast to non-null type kotlin.IntArray");
        int[] iArr2 = iArr;
        PermissionAwareActivity permissionAwareActivity = objArr[1];
        Intrinsics.checkNotNull(permissionAwareActivity, "null cannot be cast to non-null type com.facebook.react.modules.core.PermissionAwareActivity");
        PermissionAwareActivity permissionAwareActivity2 = permissionAwareActivity;
        for (Object next : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str2 = (String) next;
            Integer orNull = ArraysKt.getOrNull(iArr2, i);
            if (orNull != null && orNull.intValue() == 0) {
                str = "granted";
            } else if (permissionAwareActivity2.shouldShowRequestPermissionRationale(str2)) {
                str = "denied";
            } else {
                str = "blocked";
            }
            writableMap.putString(str2, str);
            i = i2;
        }
        promise.resolve(writableMap);
    }

    public final void shouldShowRequestRationale(ReactApplicationContext reactApplicationContext, String str, Promise promise) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        Intrinsics.checkNotNullParameter(str, "permission");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        try {
            promise.resolve(Boolean.valueOf(getPermissionAwareActivity(reactApplicationContext).shouldShowRequestPermissionRationale(str)));
        } catch (IllegalStateException e) {
            promise.reject("E_INVALID_ACTIVITY", (Throwable) e);
        }
    }

    private final PermissionAwareActivity getPermissionAwareActivity(ReactApplicationContext reactApplicationContext) {
        Activity currentActivity = reactApplicationContext.getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
        } else if (currentActivity instanceof PermissionAwareActivity) {
            return (PermissionAwareActivity) currentActivity;
        } else {
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
        }
    }

    public final void openPhotoPicker(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        promise.reject("Permissions:openPhotoPicker", "openPhotoPicker is not supported on Android");
    }

    public final void checkLocationAccuracy(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        promise.reject("Permissions:checkLocationAccuracy", "checkLocationAccuracy is not supported on Android");
    }

    public final void requestLocationAccuracy(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        promise.reject("Permissions:requestLocationAccuracy", "requestLocationAccuracy is not supported on Android");
    }

    public final boolean onRequestPermissionsResult(ReactApplicationContext reactApplicationContext, SparseArray sparseArray, int i, int[] iArr) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        Intrinsics.checkNotNullParameter(sparseArray, "callbacks");
        Intrinsics.checkNotNullParameter(iArr, "grantResults");
        try {
            Callback callback = (Callback) sparseArray.get(i);
            if (callback != null) {
                callback.invoke(iArr, getPermissionAwareActivity(reactApplicationContext));
                sparseArray.remove(i);
            } else {
                FLog.w("PermissionsModule", "Unable to find callback with requestCode %d", Integer.valueOf(i));
            }
            if (sparseArray.size() == 0) {
                return true;
            }
            return false;
        } catch (IllegalStateException e) {
            FLog.e("PermissionsModule", (Throwable) e, "Unexpected invocation of `onRequestPermissionsResult`", new Object[0]);
            return false;
        }
    }
}
