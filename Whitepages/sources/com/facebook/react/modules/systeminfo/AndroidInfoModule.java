package com.facebook.react.modules.systeminfo;

import android.annotation.SuppressLint;
import android.app.UiModeManager;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import com.facebook.fbreact.specs.NativePlatformConstantsAndroidSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.module.annotations.ReactModule;
import com.salesforce.marketingcloud.messages.iam.j;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@SuppressLint({"HardwareIds"})
@ReactModule(name = "PlatformConstants")
public final class AndroidInfoModule extends NativePlatformConstantsAndroidSpec {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String IS_DISABLE_ANIMATIONS = "IS_DISABLE_ANIMATIONS";
    private static final String IS_TESTING = "IS_TESTING";

    public void invalidate() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AndroidInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final String uiMode() {
        Object systemService = getReactApplicationContext().getSystemService("uimode");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.UiModeManager");
        int currentModeType = ((UiModeManager) systemService).getCurrentModeType();
        if (currentModeType == 1) {
            return "normal";
        }
        if (currentModeType == 2) {
            return "desk";
        }
        if (currentModeType == 3) {
            return "car";
        }
        if (currentModeType == 4) {
            return "tv";
        }
        if (currentModeType == 6) {
            return "watch";
        }
        if (currentModeType != 7) {
            return j.h;
        }
        return "vrheadset";
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> getTypedExportedConstants() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Version", Integer.valueOf(Build.VERSION.SDK_INT));
        linkedHashMap.put("Release", Build.VERSION.RELEASE);
        linkedHashMap.put("Serial", Build.SERIAL);
        linkedHashMap.put("Fingerprint", Build.FINGERPRINT);
        linkedHashMap.put("Model", Build.MODEL);
        linkedHashMap.put("Manufacturer", Build.MANUFACTURER);
        linkedHashMap.put("Brand", Build.BRAND);
        if (ReactBuildConfig.DEBUG) {
            Context applicationContext = getReactApplicationContext().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            linkedHashMap.put("ServerHost", AndroidInfoHelpers.getServerHost(applicationContext));
        }
        linkedHashMap.put("isTesting", Boolean.valueOf(Intrinsics.areEqual((Object) "true", (Object) System.getProperty(IS_TESTING)) || isRunningScreenshotTest()));
        String property = System.getProperty(IS_DISABLE_ANIMATIONS);
        if (property != null) {
            linkedHashMap.put("isDisableAnimations", Boolean.valueOf(Intrinsics.areEqual((Object) "true", (Object) property)));
        }
        linkedHashMap.put("reactNativeVersion", ReactNativeVersion.VERSION);
        linkedHashMap.put("uiMode", uiMode());
        return linkedHashMap;
    }

    public String getAndroidID() {
        String string = Settings.Secure.getString(getReactApplicationContext().getContentResolver(), "android_id");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    private final boolean isRunningScreenshotTest() {
        try {
            Class.forName("com.facebook.testing.react.screenshots.ReactAppScreenshotTestActivity");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
