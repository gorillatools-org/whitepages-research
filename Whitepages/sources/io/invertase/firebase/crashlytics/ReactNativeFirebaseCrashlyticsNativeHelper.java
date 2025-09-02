package io.invertase.firebase.crashlytics;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

public class ReactNativeFirebaseCrashlyticsNativeHelper {
    public static void recordNativeException(Throwable th) {
        FirebaseCrashlytics.getInstance().recordException(th);
    }

    public static void log(String str) {
        FirebaseCrashlytics.getInstance().log(str);
    }

    public static void setCustomKey(String str, String str2) {
        FirebaseCrashlytics.getInstance().setCustomKey(str, str2);
    }
}
