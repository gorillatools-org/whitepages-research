package com.facebook.react.modules.reactdevtoolssettings;

import android.content.SharedPreferences;
import com.facebook.fbreact.specs.NativeReactDevToolsSettingsManagerSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "ReactDevToolsSettingsManager")
public final class ReactDevToolsSettingsManagerModule extends NativeReactDevToolsSettingsManagerSpec {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String KEY_HOOK_SETTINGS = "HookSettings";
    public static final String NAME = "ReactDevToolsSettingsManager";
    private static final String SHARED_PREFERENCES_PREFIX = "ReactNative__DevToolsSettings";
    private final SharedPreferences sharedPreferences;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactDevToolsSettingsManagerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        SharedPreferences sharedPreferences2 = reactApplicationContext.getSharedPreferences(SHARED_PREFERENCES_PREFIX, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences2, "getSharedPreferences(...)");
        this.sharedPreferences = sharedPreferences2;
    }

    public void setGlobalHookSettings(String str) {
        Intrinsics.checkNotNullParameter(str, "settings");
        this.sharedPreferences.edit().putString(KEY_HOOK_SETTINGS, str).apply();
    }

    public String getGlobalHookSettings() {
        return this.sharedPreferences.getString(KEY_HOOK_SETTINGS, (String) null);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
