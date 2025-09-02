package io.invertase.firebase.analytics;

import android.os.Bundle;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import java.util.ArrayList;
import java.util.Iterator;

public class ReactNativeFirebaseAnalyticsModule extends ReactNativeFirebaseModule {
    private static final String SERVICE_NAME = "Analytics";
    private final UniversalFirebaseAnalyticsModule module;

    ReactNativeFirebaseAnalyticsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, SERVICE_NAME);
        this.module = new UniversalFirebaseAnalyticsModule(reactApplicationContext, SERVICE_NAME);
    }

    @ReactMethod
    public void logEvent(String str, ReadableMap readableMap, Promise promise) {
        this.module.logEvent(str, toBundle(readableMap)).addOnCompleteListener(new ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda3(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$logEvent$0(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setAnalyticsCollectionEnabled(Boolean bool, Promise promise) {
        this.module.setAnalyticsCollectionEnabled(bool).addOnCompleteListener(new ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda2(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setAnalyticsCollectionEnabled$1(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setSessionTimeoutDuration(double d, Promise promise) {
        this.module.setSessionTimeoutDuration((long) d).addOnCompleteListener(new ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda8(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setSessionTimeoutDuration$2(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void getAppInstanceId(Promise promise) {
        this.module.getAppInstanceId().addOnCompleteListener(new ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda0(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getAppInstanceId$3(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void getSessionId(Promise promise) {
        this.module.getSessionId().addOnCompleteListener(new ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda4(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getSessionId$4(Promise promise, Task task) {
        if (task.isSuccessful()) {
            Long l = (Long) task.getResult();
            promise.resolve(l != null ? Double.valueOf(l.doubleValue()) : null);
            return;
        }
        ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
    }

    @ReactMethod
    public void setUserId(String str, Promise promise) {
        this.module.setUserId(str).addOnCompleteListener(new ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda9(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setUserId$5(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setUserProperty(String str, String str2, Promise promise) {
        this.module.setUserProperty(str, str2).addOnCompleteListener(new ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda10(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setUserProperty$6(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setUserProperties(ReadableMap readableMap, Promise promise) {
        this.module.setUserProperties(Arguments.toBundle(readableMap)).addOnCompleteListener(new ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda7(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setUserProperties$7(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void resetAnalyticsData(Promise promise) {
        this.module.resetAnalyticsData().addOnCompleteListener(new ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda5(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$resetAnalyticsData$8(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setDefaultEventParameters(ReadableMap readableMap, Promise promise) {
        this.module.setDefaultEventParameters(toBundle(readableMap)).addOnCompleteListener(new ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda6(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setDefaultEventParameters$9(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setConsent(ReadableMap readableMap, Promise promise) {
        this.module.setConsent(Arguments.toBundle(readableMap)).addOnCompleteListener(new ReactNativeFirebaseAnalyticsModule$$ExternalSyntheticLambda1(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setConsent$10(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    private Bundle toBundle(ReadableMap readableMap) {
        Bundle bundle = Arguments.toBundle(readableMap);
        if (bundle == null) {
            return null;
        }
        ArrayList arrayList = (ArrayList) bundle.getSerializable(FirebaseAnalytics.Param.ITEMS);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof Bundle) {
                Bundle bundle2 = (Bundle) next;
                if (bundle2.containsKey(FirebaseAnalytics.Param.QUANTITY)) {
                    bundle2.putInt(FirebaseAnalytics.Param.QUANTITY, (int) bundle2.getDouble(FirebaseAnalytics.Param.QUANTITY));
                }
            }
        }
        if (bundle.containsKey(FirebaseAnalytics.Param.EXTEND_SESSION)) {
            bundle.putLong(FirebaseAnalytics.Param.EXTEND_SESSION, (long) bundle.getDouble(FirebaseAnalytics.Param.EXTEND_SESSION));
        }
        return bundle;
    }
}
