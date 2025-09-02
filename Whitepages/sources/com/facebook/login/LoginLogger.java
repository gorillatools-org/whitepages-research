package com.facebook.login;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.facebook.login.LoginClient;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class LoginLogger {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
    private final String applicationId;
    private String facebookVersion;
    private final InternalAppEventsLogger logger;

    public LoginLogger(Context context, String str) {
        PackageInfo packageInfo;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "applicationId");
        this.applicationId = str;
        this.logger = new InternalAppEventsLogger(context, str);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (packageInfo = packageManager.getPackageInfo("com.facebook.katana", 0)) != null) {
                this.facebookVersion = packageInfo.versionName;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public final String getApplicationId() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return this.applicationId;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public final void logAuthorizationMethodStart(String str, String str2, String str3) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Bundle access$newAuthorizationLoggingBundle = Companion.newAuthorizationLoggingBundle(str);
                access$newAuthorizationLoggingBundle.putString("3_method", str2);
                this.logger.logEventImplicitly(str3, access$newAuthorizationLoggingBundle);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final void logAuthorizationMethodComplete(String str, String str2, String str3, String str4, String str5, Map map, String str6) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Bundle access$newAuthorizationLoggingBundle = Companion.newAuthorizationLoggingBundle(str);
                if (str3 != null) {
                    access$newAuthorizationLoggingBundle.putString("2_result", str3);
                }
                if (str4 != null) {
                    access$newAuthorizationLoggingBundle.putString("5_error_message", str4);
                }
                if (str5 != null) {
                    access$newAuthorizationLoggingBundle.putString("4_error_code", str5);
                }
                if (map != null && !map.isEmpty()) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    for (Map.Entry entry : map.entrySet()) {
                        if (((String) entry.getKey()) != null) {
                            linkedHashMap.put(entry.getKey(), entry.getValue());
                        }
                    }
                    access$newAuthorizationLoggingBundle.putString("6_extras", new JSONObject(linkedHashMap).toString());
                }
                access$newAuthorizationLoggingBundle.putString("3_method", str2);
                this.logger.logEventImplicitly(str6, access$newAuthorizationLoggingBundle);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final void logAuthorizationMethodNotTried(String str, String str2, String str3) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Bundle access$newAuthorizationLoggingBundle = Companion.newAuthorizationLoggingBundle(str);
                access$newAuthorizationLoggingBundle.putString("3_method", str2);
                this.logger.logEventImplicitly(str3, access$newAuthorizationLoggingBundle);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final void logUnexpectedError(String str, String str2, String str3) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Bundle access$newAuthorizationLoggingBundle = Companion.newAuthorizationLoggingBundle("");
                access$newAuthorizationLoggingBundle.putString("2_result", LoginClient.Result.Code.ERROR.getLoggingValue());
                access$newAuthorizationLoggingBundle.putString("5_error_message", str2);
                access$newAuthorizationLoggingBundle.putString("3_method", str3);
                this.logger.logEventImplicitly(str, access$newAuthorizationLoggingBundle);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final Bundle newAuthorizationLoggingBundle(String str) {
            Bundle bundle = new Bundle();
            bundle.putLong("1_timestamp_ms", System.currentTimeMillis());
            bundle.putString("0_auth_logger_id", str);
            bundle.putString("3_method", "");
            bundle.putString("2_result", "");
            bundle.putString("5_error_message", "");
            bundle.putString("4_error_code", "");
            bundle.putString("6_extras", "");
            return bundle;
        }
    }
}
