package com.facebook.react.runtime;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.devsupport.ReactInstanceDevHelper;
import com.facebook.react.interfaces.TaskInterface;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.runtime.internal.bolts.Task;
import kotlin.jvm.internal.Intrinsics;

public final class ReactHostImplDevHelper implements ReactInstanceDevHelper {
    private final ReactHostImpl delegate;

    public void destroyRootView(View view) {
        Intrinsics.checkNotNullParameter(view, "rootView");
    }

    public void onJSBundleLoadedFromServer() {
    }

    public void onReloadWithJSDebugger(JavaJSExecutor.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "proxyExecutorFactory");
    }

    public ReactHostImplDevHelper(ReactHostImpl reactHostImpl) {
        Intrinsics.checkNotNullParameter(reactHostImpl, "delegate");
        this.delegate = reactHostImpl;
    }

    public void toggleElementInspector() {
        DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter;
        ReactContext currentReactContext = this.delegate.getCurrentReactContext();
        if (currentReactContext != null && (rCTDeviceEventEmitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)) != null) {
            rCTDeviceEventEmitter.emit("toggleElementInspector", (Object) null);
        }
    }

    public Activity getCurrentActivity() {
        return this.delegate.getLastUsedActivity();
    }

    public JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
        throw new IllegalStateException("Not implemented for bridgeless mode");
    }

    public View createRootView(String str) {
        Intrinsics.checkNotNullParameter(str, "appKey");
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null || this.delegate.isSurfaceWithModuleNameAttached(str)) {
            return null;
        }
        ReactSurfaceImpl createWithView = ReactSurfaceImpl.createWithView(currentActivity, str, new Bundle());
        Intrinsics.checkNotNullExpressionValue(createWithView, "createWithView(...)");
        createWithView.attach(this.delegate);
        createWithView.start();
        return createWithView.getView();
    }

    public void reload(String str) {
        Intrinsics.checkNotNullParameter(str, "s");
        this.delegate.reload(str);
    }

    public TaskInterface<Boolean> loadBundle(JSBundleLoader jSBundleLoader) {
        Intrinsics.checkNotNullParameter(jSBundleLoader, "bundleLoader");
        Task<Boolean> loadBundle = this.delegate.loadBundle(jSBundleLoader);
        Intrinsics.checkNotNullExpressionValue(loadBundle, "loadBundle(...)");
        return loadBundle;
    }

    public ReactContext getCurrentReactContext() {
        return this.delegate.getCurrentReactContext();
    }
}
