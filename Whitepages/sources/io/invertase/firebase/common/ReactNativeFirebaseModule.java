package io.invertase.firebase.common;

import android.app.Activity;
import android.content.Context;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.devsupport.StackTraceHelper;
import io.invertase.firebase.interfaces.ContextProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class ReactNativeFirebaseModule extends ReactContextBaseJavaModule implements ContextProvider {
    private final TaskExecutorService executorService = new TaskExecutorService(getName());
    private String moduleName;

    public ReactNativeFirebaseModule(ReactApplicationContext reactApplicationContext, String str) {
        super(reactApplicationContext);
        this.moduleName = str;
    }

    public static void rejectPromiseWithExceptionMap(Promise promise, Exception exc) {
        promise.reject((Throwable) exc, SharedUtils.getExceptionMap(exc));
    }

    public static void rejectPromiseWithCodeAndMessage(Promise promise, String str, String str2, ReadableMap readableMap) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("code", str);
        createMap.putString(StackTraceHelper.MESSAGE_KEY, str2);
        if (readableMap != null) {
            createMap.putMap("resolver", readableMap);
        }
        promise.reject(str, str2, createMap);
    }

    public static void rejectPromiseWithCodeAndMessage(Promise promise, String str, String str2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("code", str);
        createMap.putString(StackTraceHelper.MESSAGE_KEY, str2);
        promise.reject(str, str2, createMap);
    }

    public static void rejectPromiseWithCodeAndMessage(Promise promise, String str, String str2, String str3) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("code", str);
        createMap.putString(StackTraceHelper.MESSAGE_KEY, str2);
        createMap.putString("nativeErrorMessage", str3);
        promise.reject(str, str2, createMap);
    }

    public void initialize() {
        super.initialize();
    }

    public ReactContext getContext() {
        return getReactApplicationContext();
    }

    public final ExecutorService getExecutor() {
        return this.executorService.getExecutor();
    }

    public final ExecutorService getTransactionalExecutor() {
        return this.executorService.getTransactionalExecutor();
    }

    public final ExecutorService getTransactionalExecutor(String str) {
        return this.executorService.getTransactionalExecutor(str);
    }

    @Deprecated
    public void onCatalystInstanceDestroy() {
        invalidate();
    }

    public void invalidate() {
        this.executorService.shutdown();
    }

    public final void removeEventListeningExecutor(String str) {
        this.executorService.removeExecutor(this.executorService.getExecutorName(true, str));
    }

    public Context getApplicationContext() {
        return getReactApplicationContext().getApplicationContext();
    }

    public Activity getActivity() {
        return getCurrentActivity();
    }

    public String getName() {
        return "RNFB" + this.moduleName + "Module";
    }

    public Map<String, Object> getConstants() {
        return new HashMap();
    }
}
