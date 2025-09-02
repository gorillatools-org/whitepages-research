package com.facebook.react.devsupport;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.packagerconnection.PackagerConnectionSettings;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class DevInternalSettings implements DeveloperSettings, SharedPreferences.OnSharedPreferenceChangeListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PREFS_ANIMATIONS_DEBUG_KEY = "animations_debug";
    private static final String PREFS_FPS_DEBUG_KEY = "fps_debug";
    private static final String PREFS_HOT_MODULE_REPLACEMENT_KEY = "hot_module_replacement";
    private static final String PREFS_INSPECTOR_DEBUG_KEY = "inspector_debug";
    private static final String PREFS_JS_DEV_MODE_DEBUG_KEY = "js_dev_mode_debug";
    private static final String PREFS_JS_MINIFY_DEBUG_KEY = "js_minify_debug";
    private static final String PREFS_REMOTE_JS_DEBUG_KEY = "remote_js_debug";
    private boolean isDeviceDebugEnabled = ReactBuildConfig.DEBUG;
    private boolean isStartSamplingProfilerOnInit;
    private final Listener listener;
    private final PackagerConnectionSettings packagerConnectionSettings;
    private final SharedPreferences preferences;

    public interface Listener {
        void onInternalSettingsChanged();
    }

    public static /* synthetic */ void isStartSamplingProfilerOnInit$annotations() {
    }

    public void addMenuItem(String str) {
        Intrinsics.checkNotNullParameter(str, "title");
    }

    public void setAnimationFpsDebugEnabled(boolean z) {
    }

    public void setJSMinifyEnabled(boolean z) {
    }

    public DevInternalSettings(Context context, Listener listener2) {
        Intrinsics.checkNotNullParameter(context, "applicationContext");
        this.listener = listener2;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(...)");
        this.preferences = defaultSharedPreferences;
        this.packagerConnectionSettings = new PackagerConnectionSettings(context);
        defaultSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    public PackagerConnectionSettings getPackagerConnectionSettings() {
        return this.packagerConnectionSettings;
    }

    public boolean isFpsDebugEnabled() {
        return this.preferences.getBoolean(PREFS_FPS_DEBUG_KEY, false);
    }

    public void setFpsDebugEnabled(boolean z) {
        this.preferences.edit().putBoolean(PREFS_FPS_DEBUG_KEY, z).apply();
    }

    public boolean isAnimationFpsDebugEnabled() {
        return this.preferences.getBoolean(PREFS_ANIMATIONS_DEBUG_KEY, false);
    }

    public boolean isJSDevModeEnabled() {
        return this.preferences.getBoolean(PREFS_JS_DEV_MODE_DEBUG_KEY, true);
    }

    public void setJSDevModeEnabled(boolean z) {
        this.preferences.edit().putBoolean(PREFS_JS_DEV_MODE_DEBUG_KEY, z).apply();
    }

    public boolean isJSMinifyEnabled() {
        return this.preferences.getBoolean(PREFS_JS_MINIFY_DEBUG_KEY, false);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        if (this.listener == null) {
            return;
        }
        if (Intrinsics.areEqual((Object) PREFS_FPS_DEBUG_KEY, (Object) str) || Intrinsics.areEqual((Object) PREFS_JS_DEV_MODE_DEBUG_KEY, (Object) str) || Intrinsics.areEqual((Object) PREFS_JS_MINIFY_DEBUG_KEY, (Object) str)) {
            this.listener.onInternalSettingsChanged();
        }
    }

    public boolean isElementInspectorEnabled() {
        return this.preferences.getBoolean(PREFS_INSPECTOR_DEBUG_KEY, false);
    }

    public void setElementInspectorEnabled(boolean z) {
        this.preferences.edit().putBoolean(PREFS_INSPECTOR_DEBUG_KEY, z).apply();
    }

    public boolean isDeviceDebugEnabled() {
        return this.isDeviceDebugEnabled;
    }

    public void setDeviceDebugEnabled(boolean z) {
        this.isDeviceDebugEnabled = z;
    }

    public boolean isRemoteJSDebugEnabled() {
        return this.preferences.getBoolean(PREFS_REMOTE_JS_DEBUG_KEY, false);
    }

    public void setRemoteJSDebugEnabled(boolean z) {
        this.preferences.edit().putBoolean(PREFS_REMOTE_JS_DEBUG_KEY, z).apply();
    }

    public boolean isStartSamplingProfilerOnInit() {
        return this.isStartSamplingProfilerOnInit;
    }

    public void setStartSamplingProfilerOnInit(boolean z) {
        this.isStartSamplingProfilerOnInit = z;
    }

    public boolean isHotModuleReplacementEnabled() {
        return this.preferences.getBoolean(PREFS_HOT_MODULE_REPLACEMENT_KEY, true);
    }

    public void setHotModuleReplacementEnabled(boolean z) {
        this.preferences.edit().putBoolean(PREFS_HOT_MODULE_REPLACEMENT_KEY, z).apply();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
