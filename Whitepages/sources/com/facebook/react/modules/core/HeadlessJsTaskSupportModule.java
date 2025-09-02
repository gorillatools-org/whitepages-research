package com.facebook.react.modules.core;

import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeHeadlessJsTaskSupportSpec;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import com.facebook.react.module.annotations.ReactModule;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "HeadlessJsTaskSupport")
public class HeadlessJsTaskSupportModule extends NativeHeadlessJsTaskSupportSpec {
    public HeadlessJsTaskSupportModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public void notifyTaskRetry(double d, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        int i = (int) d;
        HeadlessJsTaskContext.Companion companion = HeadlessJsTaskContext.Companion;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        HeadlessJsTaskContext instance = companion.getInstance(reactApplicationContext);
        if (instance.isTaskRunning(i)) {
            promise.resolve(Boolean.valueOf(instance.retryTask(i)));
            return;
        }
        FLog.w(HeadlessJsTaskSupportModule.class, "Tried to retry non-active task with id %d. Did it time out?", Integer.valueOf(i));
        promise.resolve(Boolean.FALSE);
    }

    public void notifyTaskFinished(double d) {
        int i = (int) d;
        HeadlessJsTaskContext.Companion companion = HeadlessJsTaskContext.Companion;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        HeadlessJsTaskContext instance = companion.getInstance(reactApplicationContext);
        if (instance.isTaskRunning(i)) {
            instance.finishTask(i);
            return;
        }
        FLog.w(HeadlessJsTaskSupportModule.class, "Tried to finish non-active task with id %d. Did it time out?", Integer.valueOf(i));
    }
}
