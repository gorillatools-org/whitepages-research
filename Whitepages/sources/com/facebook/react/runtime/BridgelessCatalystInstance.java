package com.facebook.react.runtime;

import android.content.res.AssetManager;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeArrayInterface;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.NativeModuleRegistry;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.bridge.RuntimeScheduler;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.internal.turbomodule.core.interfaces.TurboModuleRegistry;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import com.facebook.react.turbomodule.core.interfaces.NativeMethodCallInvokerHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.config.a;
import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;

@DoNotStrip
public final class BridgelessCatalystInstance implements CatalystInstance {
    private final ReactHostImpl reactHost;

    public static /* synthetic */ void getJSCallInvokerHolder$annotations() {
    }

    public BridgelessCatalystInstance(ReactHostImpl reactHostImpl) {
        Intrinsics.checkNotNullParameter(reactHostImpl, "reactHost");
        this.reactHost = reactHostImpl;
    }

    public void handleMemoryPressure(int i) {
        throw new UnsupportedOperationException("Unimplemented method 'handleMemoryPressure'");
    }

    public void loadScriptFromAssets(AssetManager assetManager, String str, boolean z) {
        Intrinsics.checkNotNullParameter(assetManager, "assetManager");
        Intrinsics.checkNotNullParameter(str, "assetURL");
        throw new UnsupportedOperationException("Unimplemented method 'loadScriptFromAssets'");
    }

    public void loadScriptFromFile(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "fileName");
        Intrinsics.checkNotNullParameter(str2, "sourceURL");
        throw new UnsupportedOperationException("Unimplemented method 'loadScriptFromFile'");
    }

    public void loadSplitBundleFromFile(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "fileName");
        Intrinsics.checkNotNullParameter(str2, "sourceURL");
        throw new UnsupportedOperationException("Unimplemented method 'loadSplitBundleFromFile'");
    }

    public void setSourceURLs(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "deviceURL");
        Intrinsics.checkNotNullParameter(str2, "remoteURL");
        throw new UnsupportedOperationException("Unimplemented method 'setSourceURLs'");
    }

    public void runJSBundle() {
        throw new UnsupportedOperationException("Unimplemented method 'runJSBundle'");
    }

    public boolean hasRunJSBundle() {
        throw new UnsupportedOperationException("Unimplemented method 'hasRunJSBundle'");
    }

    @DoNotStrip
    public void invokeCallback(int i, NativeArrayInterface nativeArrayInterface) {
        Intrinsics.checkNotNullParameter(nativeArrayInterface, "arguments");
        throw new UnsupportedOperationException("Unimplemented method 'invokeCallback'");
    }

    public void callFunction(String str, String str2, NativeArray nativeArray) {
        Intrinsics.checkNotNullParameter(str, "module");
        Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
        throw new UnsupportedOperationException("Unimplemented method 'callFunction'");
    }

    public void destroy() {
        throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }

    public boolean isDestroyed() {
        throw new UnsupportedOperationException("Unimplemented method 'isDestroyed'");
    }

    @VisibleForTesting
    public void initialize() {
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    public <T extends JavaScriptModule> T getJSModule(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "jsInterface");
        ReactContext currentReactContext = this.reactHost.getCurrentReactContext();
        if (currentReactContext != null) {
            return currentReactContext.getJSModule(cls);
        }
        return null;
    }

    public JavaScriptContextHolder getJavaScriptContextHolder() {
        JavaScriptContextHolder javaScriptContextHolder = this.reactHost.getJavaScriptContextHolder();
        Intrinsics.checkNotNull(javaScriptContextHolder);
        return javaScriptContextHolder;
    }

    public CallInvokerHolder getJSCallInvokerHolder() {
        CallInvokerHolder jSCallInvokerHolder = this.reactHost.getJSCallInvokerHolder();
        Intrinsics.checkNotNull(jSCallInvokerHolder);
        return jSCallInvokerHolder;
    }

    public NativeMethodCallInvokerHolder getNativeMethodCallInvokerHolder() {
        throw new UnsupportedOperationException("Unimplemented method 'getNativeMethodCallInvokerHolder'");
    }

    public <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "nativeModuleInterface");
        return this.reactHost.hasNativeModule(cls);
    }

    public <T extends NativeModule> T getNativeModule(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "nativeModuleInterface");
        return this.reactHost.getNativeModule(cls);
    }

    public NativeModule getNativeModule(String str) {
        Intrinsics.checkNotNullParameter(str, "moduleName");
        return this.reactHost.getNativeModule(str);
    }

    public Collection<NativeModule> getNativeModules() {
        Collection<NativeModule> nativeModules = this.reactHost.getNativeModules();
        Intrinsics.checkNotNullExpressionValue(nativeModules, "getNativeModules(...)");
        return nativeModules;
    }

    public ReactQueueConfiguration getReactQueueConfiguration() {
        ReactQueueConfiguration reactQueueConfiguration = this.reactHost.getReactQueueConfiguration();
        Intrinsics.checkNotNull(reactQueueConfiguration);
        return reactQueueConfiguration;
    }

    public RuntimeExecutor getRuntimeExecutor() {
        return this.reactHost.getRuntimeExecutor();
    }

    public RuntimeScheduler getRuntimeScheduler() {
        throw new UnsupportedOperationException("Unimplemented method 'getRuntimeScheduler'");
    }

    public void extendNativeModules(NativeModuleRegistry nativeModuleRegistry) {
        Intrinsics.checkNotNullParameter(nativeModuleRegistry, "modules");
        throw new UnsupportedOperationException("Unimplemented method 'extendNativeModules'");
    }

    public String getSourceURL() {
        throw new UnsupportedOperationException("Unimplemented method 'getSourceURL'");
    }

    public void addBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener) {
        Intrinsics.checkNotNullParameter(notThreadSafeBridgeIdleDebugListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        throw new UnsupportedOperationException("Unimplemented method 'addBridgeIdleDebugListener'");
    }

    public void removeBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener) {
        Intrinsics.checkNotNullParameter(notThreadSafeBridgeIdleDebugListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        throw new UnsupportedOperationException("Unimplemented method 'removeBridgeIdleDebugListener'");
    }

    public void registerSegment(int i, String str) {
        Intrinsics.checkNotNullParameter(str, a.j);
        throw new UnsupportedOperationException("Unimplemented method 'registerSegment'");
    }

    @VisibleForTesting
    public void setGlobalVariable(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "propName");
        Intrinsics.checkNotNullParameter(str2, "jsonValue");
        throw new UnsupportedOperationException("Unimplemented method 'setGlobalVariable'");
    }

    public void setTurboModuleRegistry(TurboModuleRegistry turboModuleRegistry) {
        Intrinsics.checkNotNullParameter(turboModuleRegistry, "turboModuleRegistry");
        throw new UnsupportedOperationException("Unimplemented method 'setTurboModuleRegistry'");
    }

    public void setFabricUIManager(UIManager uIManager) {
        Intrinsics.checkNotNullParameter(uIManager, "fabricUIManager");
        throw new UnsupportedOperationException("Unimplemented method 'setFabricUIManager'");
    }

    public UIManager getFabricUIManager() {
        throw new UnsupportedOperationException("Unimplemented method 'getFabricUIManager'");
    }
}
