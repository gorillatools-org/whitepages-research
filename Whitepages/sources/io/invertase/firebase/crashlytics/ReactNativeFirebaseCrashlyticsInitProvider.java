package io.invertase.firebase.crashlytics;

import android.util.Log;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import io.invertase.firebase.common.ReactNativeFirebaseInitProvider;
import io.invertase.firebase.common.ReactNativeFirebaseJSON;
import io.invertase.firebase.common.ReactNativeFirebaseMeta;
import io.invertase.firebase.common.ReactNativeFirebasePreferences;

public class ReactNativeFirebaseCrashlyticsInitProvider extends ReactNativeFirebaseInitProvider {
    private static final String TAG = "RNFBCrashlyticsInit";

    static boolean isCrashlyticsCollectionEnabled() {
        boolean z;
        ReactNativeFirebaseJSON sharedInstance = ReactNativeFirebaseJSON.getSharedInstance();
        ReactNativeFirebaseMeta sharedInstance2 = ReactNativeFirebaseMeta.getSharedInstance();
        ReactNativeFirebasePreferences sharedInstance3 = ReactNativeFirebasePreferences.getSharedInstance();
        if (sharedInstance3.contains("crashlytics_auto_collection_enabled")) {
            z = sharedInstance3.getBooleanValue("crashlytics_auto_collection_enabled", true);
            Log.d(TAG, "isCrashlyticsCollectionEnabled via RNFBPreferences: " + z);
        } else if (sharedInstance.contains("crashlytics_auto_collection_enabled")) {
            z = sharedInstance.getBooleanValue("crashlytics_auto_collection_enabled", true);
            Log.d(TAG, "isCrashlyticsCollectionEnabled via RNFBJSON: " + z);
        } else {
            z = sharedInstance2.getBooleanValue("crashlytics_auto_collection_enabled", true);
            Log.d(TAG, "isCrashlyticsCollectionEnabled via RNFBMeta: " + z);
        }
        Log.d(TAG, "isCrashlyticsCollectionEnabled final value: " + z);
        return z;
    }

    static boolean isErrorGenerationOnJSCrashEnabled() {
        boolean z;
        ReactNativeFirebaseJSON sharedInstance = ReactNativeFirebaseJSON.getSharedInstance();
        ReactNativeFirebaseMeta sharedInstance2 = ReactNativeFirebaseMeta.getSharedInstance();
        ReactNativeFirebasePreferences sharedInstance3 = ReactNativeFirebasePreferences.getSharedInstance();
        if (sharedInstance3.contains("crashlytics_is_error_generation_on_js_crash_enabled")) {
            z = sharedInstance3.getBooleanValue("crashlytics_is_error_generation_on_js_crash_enabled", true);
            Log.d(TAG, "isErrorGenerationOnJSCrashEnabled via RNFBPreferences: " + z);
        } else if (sharedInstance.contains("crashlytics_is_error_generation_on_js_crash_enabled")) {
            z = sharedInstance.getBooleanValue("crashlytics_is_error_generation_on_js_crash_enabled", true);
            Log.d(TAG, "isErrorGenerationOnJSCrashEnabled via RNFBJSON: " + z);
        } else {
            z = sharedInstance2.getBooleanValue("crashlytics_is_error_generation_on_js_crash_enabled", true);
            Log.d(TAG, "isErrorGenerationOnJSCrashEnabled via RNFBMeta: " + z);
        }
        Log.d(TAG, "isErrorGenerationOnJSCrashEnabled final value: " + z);
        return z;
    }

    static boolean isCrashlyticsJavascriptExceptionHandlerChainingEnabled() {
        boolean z;
        ReactNativeFirebaseJSON sharedInstance = ReactNativeFirebaseJSON.getSharedInstance();
        ReactNativeFirebaseMeta sharedInstance2 = ReactNativeFirebaseMeta.getSharedInstance();
        ReactNativeFirebasePreferences sharedInstance3 = ReactNativeFirebasePreferences.getSharedInstance();
        if (sharedInstance3.contains("crashlytics_javascript_exception_handler_chaining_enabled")) {
            z = sharedInstance3.getBooleanValue("crashlytics_javascript_exception_handler_chaining_enabled", true);
            Log.d(TAG, "isCrashlyticsJavascriptExceptionHandlerChainingEnabled via RNFBPreferences: " + z);
        } else if (sharedInstance.contains("crashlytics_javascript_exception_handler_chaining_enabled")) {
            z = sharedInstance.getBooleanValue("crashlytics_javascript_exception_handler_chaining_enabled", true);
            Log.d(TAG, "isCrashlyticsJavascriptExceptionHandlerChainingEnabled via RNFBJSON: " + z);
        } else {
            z = sharedInstance2.getBooleanValue("crashlytics_javascript_exception_handler_chaining_enabled", true);
            Log.d(TAG, "isCrashlyticsJavascriptExceptionHandlerChainingEnabled via RNFBMeta: " + z);
        }
        Log.d(TAG, "isCrashlyticsJavascriptExceptionHandlerChainingEnabled final value: " + z);
        return z;
    }

    public boolean onCreate() {
        super.onCreate();
        try {
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(isCrashlyticsCollectionEnabled());
            Log.i(TAG, "initialization successful");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "initialization failed", e);
            return false;
        }
    }
}
