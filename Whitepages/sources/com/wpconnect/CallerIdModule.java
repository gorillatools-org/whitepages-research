package com.wpconnect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telecom.TelecomManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "CallerIdModule")
public final class CallerIdModule extends ReactContextBaseJavaModule {
    private static final int CALL_SCREENING_ROLE_REQUEST_CODE = 5678;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int OVERLAY_PERMISSION_REQ_CODE = 1234;
    private static final int PERMISSION_REQUEST_CODE = 9012;
    /* access modifiers changed from: private */
    public static final String[] REQUIRED_PERMISSIONS = {"android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW"};
    private static final String TAG = "CallerIdModule";
    private final CallerIdPreferences preferences;
    private final ReactApplicationContext reactContext;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CallerIdModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        this.reactContext = reactApplicationContext;
        this.preferences = CallerIdPreferences.Companion.getInstance(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(new BaseActivityEventListener(this) {
            final /* synthetic */ CallerIdModule this$0;

            {
                this.this$0 = r1;
            }

            public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                if (i == CallerIdModule.OVERLAY_PERMISSION_REQ_CODE || i == CallerIdModule.CALL_SCREENING_ROLE_REQUEST_CODE) {
                    this.this$0.handleActivityResult(i);
                }
            }
        });
    }

    public enum PermissionType {
        READ_PHONE_STATE,
        SYSTEM_ALERT_WINDOW,
        CALL_SCREENING_ROLE;

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        static {
            PermissionType[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static final class PermissionHelper {
            public static final PermissionHelper INSTANCE = new PermissionHelper();

            private PermissionHelper() {
            }

            public static final boolean hasOverlayPermission(Context context) {
                Intrinsics.checkNotNullParameter(context, "context");
                return Settings.canDrawOverlays(context);
            }

            public static final boolean hasPermission(Activity activity, String str) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                Intrinsics.checkNotNullParameter(str, "permission");
                if (Intrinsics.areEqual((Object) str, (Object) "android.permission.SYSTEM_ALERT_WINDOW")) {
                    return hasOverlayPermission(activity);
                }
                return ContextCompat.checkSelfPermission(activity, str) == 0;
            }

            public static final boolean hasAllRequiredPermissions(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                for (String hasPermission : CallerIdModule.REQUIRED_PERMISSIONS) {
                    if (!hasPermission(activity, hasPermission)) {
                        return false;
                    }
                }
                return true;
            }

            public static final String[] getPermissionsToRequest(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                String[] access$getREQUIRED_PERMISSIONS$cp = CallerIdModule.REQUIRED_PERMISSIONS;
                ArrayList arrayList = new ArrayList();
                for (String str : access$getREQUIRED_PERMISSIONS$cp) {
                    if (!Intrinsics.areEqual((Object) str, (Object) "android.permission.SYSTEM_ALERT_WINDOW")) {
                        arrayList.add(str);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                for (Object next : arrayList) {
                    if (!hasPermission(activity, (String) next)) {
                        arrayList2.add(next);
                    }
                }
                return (String[]) arrayList2.toArray(new String[0]);
            }
        }
    }

    public String getName() {
        return TAG;
    }

    public final boolean isCallScreeningRoleGranted() {
        if (Build.VERSION.SDK_INT >= 29) {
            Object systemService = this.reactContext.getSystemService(ViewProps.ROLE);
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.role.RoleManager");
            return CallerIdModule$$ExternalSyntheticApiModelOutline0.m(systemService).isRoleHeld("android.app.role.CALL_SCREENING");
        }
        Object systemService2 = this.reactContext.getSystemService("telecom");
        Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.telecom.TelecomManager");
        return Intrinsics.areEqual((Object) this.reactContext.getPackageName(), (Object) ((TelecomManager) systemService2).getDefaultDialerPackage());
    }

    @ReactMethod
    public final void requestCallScreeningRole() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            if (Build.VERSION.SDK_INT >= 29) {
                Object systemService = this.reactContext.getSystemService(ViewProps.ROLE);
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.role.RoleManager");
                Intent m = CallerIdModule$$ExternalSyntheticApiModelOutline0.m(systemService).createRequestRoleIntent("android.app.role.CALL_SCREENING");
                Intrinsics.checkNotNullExpressionValue(m, "createRequestRoleIntent(...)");
                currentActivity.startActivityForResult(m, CALL_SCREENING_ROLE_REQUEST_CODE);
                return;
            }
            Intent putExtra = new Intent("android.telecom.action.CHANGE_DEFAULT_DIALER").putExtra("android.telecom.extra.CHANGE_DEFAULT_DIALER_PACKAGE_NAME", currentActivity.getPackageName());
            Intrinsics.checkNotNullExpressionValue(putExtra, "putExtra(...)");
            currentActivity.startActivityForResult(putExtra, CALL_SCREENING_ROLE_REQUEST_CODE);
        }
    }

    @ReactMethod
    public final void requestPermissions() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            if (!Companion.PermissionHelper.hasOverlayPermission(currentActivity)) {
                currentActivity.startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + currentActivity.getPackageName())), OVERLAY_PERMISSION_REQ_CODE);
            }
            String[] permissionsToRequest = Companion.PermissionHelper.getPermissionsToRequest(currentActivity);
            if (!(permissionsToRequest.length == 0)) {
                ActivityCompat.requestPermissions(currentActivity, permissionsToRequest, PERMISSION_REQUEST_CODE);
            }
        }
    }

    @ReactMethod
    public final void checkPermissionsGranted(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            callback.invoke(Boolean.FALSE);
        } else if (!isCallScreeningRoleGranted()) {
            callback.invoke(Boolean.FALSE);
        } else {
            if (!(Companion.PermissionHelper.getPermissionsToRequest(currentActivity).length == 0)) {
                callback.invoke(Boolean.FALSE);
            } else if (!Companion.PermissionHelper.hasOverlayPermission(currentActivity)) {
                callback.invoke(Boolean.FALSE);
            } else {
                callback.invoke(Boolean.TRUE);
            }
        }
    }

    @ReactMethod
    public final void updateCallerIdDisplayedText(String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        CallerIdOverlayService.Companion.getInstance().updateCallerIdDisplayedText(str);
    }

    @ReactMethod
    public final void displayCallerSpam() {
        CallerIdOverlayService.Companion.getInstance().displayCallerSpam();
    }

    private final void sendPermissionGrantedEvent(PermissionType permissionType) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("permission", permissionType.toString());
        Unit unit = Unit.INSTANCE;
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onPermissionGranted", createMap);
    }

    public final void handleActivityResult(int i) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            if (i == OVERLAY_PERMISSION_REQ_CODE) {
                if (Companion.PermissionHelper.hasOverlayPermission(currentActivity)) {
                    sendPermissionGrantedEvent(PermissionType.SYSTEM_ALERT_WINDOW);
                } else {
                    this.preferences.setServiceEnabled(false);
                    this.preferences.setPermissionsGranted(false);
                }
            }
            if (i != CALL_SCREENING_ROLE_REQUEST_CODE) {
                return;
            }
            if (isCallScreeningRoleGranted()) {
                sendPermissionGrantedEvent(PermissionType.CALL_SCREENING_ROLE);
            } else {
                this.preferences.setServiceEnabled(false);
            }
        }
    }

    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        boolean z;
        Intrinsics.checkNotNullParameter(strArr, "permissions");
        Intrinsics.checkNotNullParameter(iArr, "grantResults");
        if (i == PERMISSION_REQUEST_CODE) {
            int length = iArr.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    if (iArr[i2] != 0) {
                        z = false;
                        break;
                    }
                    i2++;
                } else {
                    z = true;
                    break;
                }
            }
            int indexOf = ArraysKt.indexOf(strArr, "android.permission.READ_PHONE_STATE");
            if (indexOf != -1 && iArr[indexOf] == 0) {
                sendPermissionGrantedEvent(PermissionType.READ_PHONE_STATE);
            }
            this.preferences.setPermissionsGranted(z);
            if (!z) {
                this.preferences.setServiceEnabled(false);
            }
        }
    }
}
