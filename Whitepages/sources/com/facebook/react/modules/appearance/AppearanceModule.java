package com.facebook.react.modules.appearance;

import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;
import com.facebook.fbreact.specs.NativeAppearanceSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.google.firebase.ktx.BuildConfig;
import com.salesforce.marketingcloud.config.a;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "Appearance")
public final class AppearanceModule extends NativeAppearanceSpec {
    private static final String APPEARANCE_CHANGED_EVENT_NAME = "appearanceChanged";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "Appearance";
    private String lastEmittedColorScheme;
    private final OverrideColorScheme overrideColorScheme;

    public interface OverrideColorScheme {
        String getScheme();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppearanceModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, (OverrideColorScheme) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
    }

    public void addListener(String str) {
        Intrinsics.checkNotNullParameter(str, a.h);
    }

    public void removeListeners(double d) {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AppearanceModule(ReactApplicationContext reactApplicationContext, OverrideColorScheme overrideColorScheme2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reactApplicationContext, (i & 2) != 0 ? null : overrideColorScheme2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppearanceModule(ReactApplicationContext reactApplicationContext, OverrideColorScheme overrideColorScheme2) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        this.overrideColorScheme = overrideColorScheme2;
    }

    private final String colorSchemeForCurrentConfiguration(Context context) {
        OverrideColorScheme overrideColorScheme2 = this.overrideColorScheme;
        if (overrideColorScheme2 != null) {
            return overrideColorScheme2.getScheme();
        }
        int i = context.getResources().getConfiguration().uiMode & 48;
        if (i == 16 || i != 32) {
            return "light";
        }
        return "dark";
    }

    public String getColorScheme() {
        Context currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            currentActivity = getReactApplicationContext();
        }
        Intrinsics.checkNotNull(currentActivity);
        return colorSchemeForCurrentConfiguration(currentActivity);
    }

    public void setColorScheme(String str) {
        Intrinsics.checkNotNullParameter(str, "style");
        UiThreadUtil.runOnUiThread(new AppearanceModule$$ExternalSyntheticLambda0(str));
    }

    /* access modifiers changed from: private */
    public static final void setColorScheme$lambda$0(String str) {
        int hashCode = str.hashCode();
        if (hashCode != -1626174665) {
            if (hashCode != 3075958) {
                if (hashCode == 102970646 && str.equals("light")) {
                    AppCompatDelegate.setDefaultNightMode(1);
                }
            } else if (str.equals("dark")) {
                AppCompatDelegate.setDefaultNightMode(2);
            }
        } else if (str.equals(BuildConfig.VERSION_NAME)) {
            AppCompatDelegate.setDefaultNightMode(-1);
        }
    }

    public final void onConfigurationChanged(Context context) {
        Intrinsics.checkNotNullParameter(context, "currentContext");
        String colorSchemeForCurrentConfiguration = colorSchemeForCurrentConfiguration(context);
        if (!Intrinsics.areEqual((Object) this.lastEmittedColorScheme, (Object) colorSchemeForCurrentConfiguration)) {
            this.lastEmittedColorScheme = colorSchemeForCurrentConfiguration;
            emitAppearanceChanged(colorSchemeForCurrentConfiguration);
        }
    }

    public final void emitAppearanceChanged(String str) {
        Intrinsics.checkNotNullParameter(str, "colorScheme");
        WritableMap createMap = Arguments.createMap();
        createMap.putString("colorScheme", str);
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            reactApplicationContextIfActiveOrWarn.emitDeviceEvent(APPEARANCE_CHANGED_EVENT_NAME, createMap);
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
