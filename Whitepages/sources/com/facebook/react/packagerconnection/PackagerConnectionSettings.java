package com.facebook.react.packagerconnection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class PackagerConnectionSettings {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PREFS_DEBUG_SERVER_HOST_KEY = "debug_http_host";
    private static final String TAG = PackagerConnectionSettings.class.getSimpleName();
    private final Map<String, String> _additionalOptionsForPackager = new LinkedHashMap();
    private final Context appContext;
    private final String packageName;
    private final SharedPreferences preferences;

    public PackagerConnectionSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "appContext");
        this.appContext = context;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(...)");
        this.preferences = defaultSharedPreferences;
        String packageName2 = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName2, "getPackageName(...)");
        this.packageName = packageName2;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public String getDebugServerHost() {
        String string = this.preferences.getString(PREFS_DEBUG_SERVER_HOST_KEY, (String) null);
        if (string != null && string.length() > 0) {
            return string;
        }
        String serverHost = AndroidInfoHelpers.getServerHost(this.appContext);
        if (Intrinsics.areEqual((Object) serverHost, (Object) AndroidInfoHelpers.DEVICE_LOCALHOST)) {
            String str = TAG;
            String adbReverseTcpCommand = AndroidInfoHelpers.getAdbReverseTcpCommand(this.appContext);
            FLog.w(str, "You seem to be running on device. Run '" + adbReverseTcpCommand + "' to forward the debug server's port to the device.");
        }
        return serverHost;
    }

    public void setDebugServerHost(String str) {
        Intrinsics.checkNotNullParameter(str, "host");
        this.preferences.edit().putString(PREFS_DEBUG_SERVER_HOST_KEY, str).apply();
    }

    public final void setAdditionalOptionForPackager(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "value");
        this._additionalOptionsForPackager.put(str, str2);
    }

    public final Map<String, String> getAdditionalOptionsForPackager() {
        return this._additionalOptionsForPackager;
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
