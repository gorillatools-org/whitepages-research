package com.facebook.react.modules.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.facebook.fbreact.specs.NativeIntentAndroidSpec;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "IntentAndroid")
public class IntentModule extends NativeIntentAndroidSpec {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String EXTRA_MAP_KEY_FOR_VALUE = "value";
    public static final String NAME = "IntentAndroid";
    /* access modifiers changed from: private */
    public LifecycleEventListener initialURLListener;
    /* access modifiers changed from: private */
    public final List<Promise> pendingOpenURLPromises = new ArrayList();

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Boolean     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.intent.IntentModule.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IntentModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
    }

    public void invalidate() {
        synchronized (this) {
            try {
                this.pendingOpenURLPromises.clear();
                LifecycleEventListener lifecycleEventListener = this.initialURLListener;
                if (lifecycleEventListener != null) {
                    getReactApplicationContext().removeLifecycleEventListener(lifecycleEventListener);
                    Unit unit = Unit.INSTANCE;
                }
                this.initialURLListener = null;
            } catch (Throwable th) {
                throw th;
            }
        }
        super.invalidate();
    }

    public void getInitialURL(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        try {
            Activity currentActivity = getCurrentActivity();
            if (currentActivity == null) {
                waitForActivityAndGetInitialURL(promise);
                return;
            }
            Intent intent = currentActivity.getIntent();
            String action = intent.getAction();
            Uri data = intent.getData();
            promise.resolve((data == null || (!Intrinsics.areEqual((Object) "android.intent.action.VIEW", (Object) action) && !Intrinsics.areEqual((Object) "android.nfc.action.NDEF_DISCOVERED", (Object) action))) ? null : data.toString());
        } catch (Exception e) {
            String message = e.getMessage();
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Could not get the initial URL : " + message));
        }
    }

    private final synchronized void waitForActivityAndGetInitialURL(Promise promise) {
        this.pendingOpenURLPromises.add(promise);
        if (this.initialURLListener == null) {
            this.initialURLListener = new IntentModule$waitForActivityAndGetInitialURL$1(this);
            getReactApplicationContext().addLifecycleEventListener(this.initialURLListener);
        }
    }

    public void openURL(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        if (str == null || str.length() == 0) {
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Invalid URL: " + str));
            return;
        }
        try {
            sendOSIntent(new Intent("android.intent.action.VIEW", Uri.parse(str).normalizeScheme()), false);
            promise.resolve(Boolean.TRUE);
        } catch (Exception e) {
            String message = e.getMessage();
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Could not open URL '" + str + "': " + message));
        }
    }

    public void canOpenURL(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        if (str == null || str.length() == 0) {
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Invalid URL: " + str));
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.addFlags(268435456);
            PackageManager packageManager = getReactApplicationContext().getPackageManager();
            promise.resolve(Boolean.valueOf((packageManager == null || intent.resolveActivity(packageManager) == null) ? false : true));
        } catch (Exception e) {
            String message = e.getMessage();
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Could not check if URL '" + str + "' can be opened: " + message));
        }
    }

    public void openSettings(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        try {
            Intent intent = new Intent();
            Activity currentActivity = getCurrentActivity();
            if (currentActivity != null) {
                String packageName = getReactApplicationContext().getPackageName();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse("package:" + packageName));
                intent.addFlags(268435456);
                intent.addFlags(1073741824);
                intent.addFlags(8388608);
                currentActivity.startActivity(intent);
                promise.resolve(Boolean.TRUE);
                return;
            }
            throw new IllegalStateException("Required value was null.");
        } catch (Exception e) {
            String message = e.getMessage();
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Could not open the Settings: " + message));
        }
    }

    public void sendIntent(String str, ReadableArray readableArray, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        if (str == null || str.length() == 0) {
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Invalid Action: " + str + "."));
            return;
        }
        Intent intent = new Intent(str);
        PackageManager packageManager = getReactApplicationContext().getPackageManager();
        if (packageManager == null || intent.resolveActivity(packageManager) == null) {
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Could not launch Intent with action " + str + "."));
            return;
        }
        if (readableArray != null) {
            int size = readableArray.size();
            for (int i = 0; i < size; i++) {
                ReadableMap map = readableArray.getMap(i);
                if (map != null) {
                    String string = map.getString("key");
                    int i2 = WhenMappings.$EnumSwitchMapping$0[map.getType("value").ordinal()];
                    if (i2 == 1) {
                        intent.putExtra(string, map.getString("value"));
                    } else if (i2 == 2) {
                        intent.putExtra(string, map.getDouble("value"));
                    } else if (i2 != 3) {
                        promise.reject((Throwable) new JSApplicationIllegalArgumentException("Extra type for " + string + " not supported."));
                        return;
                    } else {
                        intent.putExtra(string, map.getBoolean("value"));
                    }
                }
            }
        }
        sendOSIntent(intent, true);
    }

    private final void sendOSIntent(Intent intent, boolean z) {
        ComponentName componentName;
        String str;
        Activity currentActivity = getCurrentActivity();
        String packageName = getReactApplicationContext().getPackageName();
        PackageManager packageManager = getReactApplicationContext().getPackageManager();
        if (packageManager == null) {
            componentName = intent.getComponent();
        } else {
            componentName = intent.resolveActivity(packageManager);
        }
        if (componentName == null || (str = componentName.getPackageName()) == null) {
            str = "";
        }
        if (z || currentActivity == null || !Intrinsics.areEqual((Object) packageName, (Object) str)) {
            intent.addFlags(268435456);
        }
        if (currentActivity != null) {
            currentActivity.startActivity(intent);
        } else {
            getReactApplicationContext().startActivity(intent);
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
