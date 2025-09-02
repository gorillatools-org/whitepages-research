package io.invertase.firebase.perf;

import android.app.Activity;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.tasks.Task;
import com.salesforce.marketingcloud.storage.db.k;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import java.util.Map;

public class ReactNativeFirebasePerfModule extends ReactNativeFirebaseModule {
    private static final String SERVICE_NAME = "Perf";
    private final UniversalFirebasePerfModule module;

    ReactNativeFirebasePerfModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, SERVICE_NAME);
        this.module = new UniversalFirebasePerfModule(reactApplicationContext, SERVICE_NAME);
    }

    public void invalidate() {
        super.invalidate();
        this.module.onTearDown();
    }

    @ReactMethod
    public void setPerformanceCollectionEnabled(Boolean bool, Promise promise) {
        this.module.setPerformanceCollectionEnabled(bool).addOnCompleteListener(new ReactNativeFirebasePerfModule$$ExternalSyntheticLambda3(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setPerformanceCollectionEnabled$0(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void startTrace(int i, String str, Promise promise) {
        this.module.startTrace(i, str).addOnCompleteListener(new ReactNativeFirebasePerfModule$$ExternalSyntheticLambda1(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$startTrace$1(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void stopTrace(int i, ReadableMap readableMap, Promise promise) {
        this.module.stopTrace(i, Arguments.toBundle(readableMap.getMap("metrics")), Arguments.toBundle(readableMap.getMap(k.a.h))).addOnCompleteListener(new ReactNativeFirebasePerfModule$$ExternalSyntheticLambda0(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$stopTrace$2(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void startScreenTrace(int i, String str, Promise promise) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            promise.resolve((Object) null);
        } else {
            this.module.startScreenTrace(currentActivity, i, str).addOnCompleteListener(new ReactNativeFirebasePerfModule$$ExternalSyntheticLambda6(promise));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$startScreenTrace$3(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void stopScreenTrace(int i, Promise promise) {
        this.module.stopScreenTrace(i).addOnCompleteListener(new ReactNativeFirebasePerfModule$$ExternalSyntheticLambda4(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$stopScreenTrace$4(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void startHttpMetric(int i, String str, String str2, Promise promise) {
        this.module.startHttpMetric(i, str, str2).addOnCompleteListener(new ReactNativeFirebasePerfModule$$ExternalSyntheticLambda5(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$startHttpMetric$5(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void stopHttpMetric(int i, ReadableMap readableMap, Promise promise) {
        this.module.stopHttpMetric(i, Arguments.toBundle(readableMap), Arguments.toBundle(readableMap.getMap(k.a.h))).addOnCompleteListener(new ReactNativeFirebasePerfModule$$ExternalSyntheticLambda2(promise));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$stopHttpMetric$6(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    public Map<String, Object> getConstants() {
        return this.module.getConstants();
    }
}
