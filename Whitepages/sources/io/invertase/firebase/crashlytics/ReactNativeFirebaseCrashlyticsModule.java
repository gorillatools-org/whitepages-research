package io.invertase.firebase.crashlytics;

import android.os.Handler;
import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.devsupport.StackTraceHelper;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.salesforce.marketingcloud.messages.iam.j;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import io.invertase.firebase.common.ReactNativeFirebasePreferences;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ReactNativeFirebaseCrashlyticsModule extends ReactNativeFirebaseModule {
    private static final String TAG = "Crashlytics";

    ReactNativeFirebaseCrashlyticsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, TAG);
    }

    @ReactMethod
    public void checkForUnsentReports(Promise promise) {
        FirebaseCrashlytics.getInstance().checkForUnsentReports().addOnCompleteListener(new ReactNativeFirebaseCrashlyticsModule$$ExternalSyntheticLambda0(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$checkForUnsentReports$0(Promise promise, Task task) {
        String str;
        if (!task.isSuccessful()) {
            if (task.getException() != null) {
                str = task.getException().getMessage();
            } else {
                str = "checkForUnsentReports() request error";
            }
            ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, j.h, str);
        } else if (task.getResult() != null) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, j.h, "Unknown result of check for unsent reports");
        }
    }

    @ReactMethod
    public void crashWithStackPromise(ReadableMap readableMap, Promise promise) {
        if (ReactNativeFirebaseCrashlyticsInitProvider.isCrashlyticsCollectionEnabled()) {
            recordJavaScriptError(readableMap).printStackTrace(System.err);
            Log.e(TAG, "Crash logged. Terminating app.");
            System.exit(0);
        } else {
            Log.i(TAG, "crashlytics collection is not enabled, not crashing.");
        }
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void crash() {
        if (ReactNativeFirebaseCrashlyticsInitProvider.isCrashlyticsCollectionEnabled()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    throw new RuntimeException("Crash Test");
                }
            }, 50);
        } else {
            Log.i(TAG, "crashlytics collection is not enabled, not crashing.");
        }
    }

    @ReactMethod
    public void deleteUnsentReports() {
        FirebaseCrashlytics.getInstance().deleteUnsentReports();
    }

    @ReactMethod
    public void didCrashOnPreviousExecution(Promise promise) {
        promise.resolve(Boolean.valueOf(FirebaseCrashlytics.getInstance().didCrashOnPreviousExecution()));
    }

    @ReactMethod
    public void log(String str) {
        if (ReactNativeFirebaseCrashlyticsInitProvider.isCrashlyticsCollectionEnabled()) {
            FirebaseCrashlytics.getInstance().log(str);
        }
    }

    @ReactMethod
    public void logPromise(String str, Promise promise) {
        if (ReactNativeFirebaseCrashlyticsInitProvider.isCrashlyticsCollectionEnabled()) {
            FirebaseCrashlytics.getInstance().log(str);
        }
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void setAttribute(String str, String str2, Promise promise) {
        if (ReactNativeFirebaseCrashlyticsInitProvider.isCrashlyticsCollectionEnabled()) {
            FirebaseCrashlytics.getInstance().setCustomKey(str, str2);
        }
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void setAttributes(ReadableMap readableMap, Promise promise) {
        if (ReactNativeFirebaseCrashlyticsInitProvider.isCrashlyticsCollectionEnabled()) {
            ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
            FirebaseCrashlytics instance = FirebaseCrashlytics.getInstance();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                instance.setCustomKey(nextKey, readableMap.getString(nextKey));
            }
        }
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void sendUnsentReports() {
        FirebaseCrashlytics.getInstance().sendUnsentReports();
    }

    @ReactMethod
    public void setUserId(String str, Promise promise) {
        if (ReactNativeFirebaseCrashlyticsInitProvider.isCrashlyticsCollectionEnabled()) {
            FirebaseCrashlytics.getInstance().setUserId(str);
        }
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void setCrashlyticsCollectionEnabled(Boolean bool, Promise promise) {
        ReactNativeFirebasePreferences.getSharedInstance().setBooleanValue("crashlytics_auto_collection_enabled", bool.booleanValue());
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void recordError(ReadableMap readableMap) {
        if (ReactNativeFirebaseCrashlyticsInitProvider.isCrashlyticsCollectionEnabled()) {
            recordJavaScriptError(readableMap);
        } else {
            Log.i(TAG, "crashlytics collection is not enabled, not crashing.");
        }
    }

    @ReactMethod
    public void recordErrorPromise(ReadableMap readableMap, Promise promise) {
        if (ReactNativeFirebaseCrashlyticsInitProvider.isCrashlyticsCollectionEnabled()) {
            recordJavaScriptError(readableMap);
        } else {
            Log.i(TAG, "crashlytics collection is not enabled, not crashing.");
        }
        promise.resolve((Object) null);
    }

    private Exception recordJavaScriptError(ReadableMap readableMap) {
        Exception exc;
        String string = readableMap.getString(StackTraceHelper.MESSAGE_KEY);
        ReadableArray array = readableMap.getArray("frames");
        Objects.requireNonNull(array);
        ReadableArray readableArray = array;
        if (readableMap.getBoolean("isUnhandledRejection")) {
            exc = new UnhandledPromiseRejection(string);
        } else {
            exc = new JavaScriptError(string);
        }
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[readableArray.size()];
        for (int i = 0; i < readableArray.size(); i++) {
            ReadableMap map = readableArray.getMap(i);
            Objects.requireNonNull(map);
            ReadableMap readableMap2 = map;
            stackTraceElementArr[i] = new StackTraceElement("", readableMap2.getString("fn"), readableMap2.getString("file"), -1);
        }
        exc.setStackTrace(stackTraceElementArr);
        FirebaseCrashlytics.getInstance().recordException(exc);
        return exc;
    }

    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("isCrashlyticsCollectionEnabled", Boolean.valueOf(ReactNativeFirebaseCrashlyticsInitProvider.isCrashlyticsCollectionEnabled()));
        hashMap.put("isErrorGenerationOnJSCrashEnabled", Boolean.valueOf(ReactNativeFirebaseCrashlyticsInitProvider.isErrorGenerationOnJSCrashEnabled()));
        hashMap.put("isCrashlyticsJavascriptExceptionHandlerChainingEnabled", Boolean.valueOf(ReactNativeFirebaseCrashlyticsInitProvider.isCrashlyticsJavascriptExceptionHandlerChainingEnabled()));
        return hashMap;
    }
}
