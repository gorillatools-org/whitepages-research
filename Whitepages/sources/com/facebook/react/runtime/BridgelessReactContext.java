package com.facebook.react.runtime;

import android.content.Context;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.JavaScriptModuleRegistry;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.interop.InteropModuleRegistry;
import com.facebook.react.common.annotations.FrameworkAPI;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.EventDispatcherProvider;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

class BridgelessReactContext extends ReactApplicationContext implements EventDispatcherProvider {
    private final String TAG = getClass().getSimpleName();
    private final ReactHostImpl mReactHost;
    private final AtomicReference<String> mSourceURL = new AtomicReference<>();

    public void destroy() {
    }

    public boolean hasCatalystInstance() {
        return false;
    }

    public boolean isBridgeless() {
        return true;
    }

    BridgelessReactContext(Context context, ReactHostImpl reactHostImpl) {
        super(context);
        this.mReactHost = reactHostImpl;
        if (ReactNativeFeatureFlags.useFabricInterop()) {
            initializeInteropModules();
        }
    }

    public EventDispatcher getEventDispatcher() {
        return this.mReactHost.getEventDispatcher();
    }

    public void setSourceURL(String str) {
        this.mSourceURL.set(str);
    }

    public String getSourceURL() {
        return this.mSourceURL.get();
    }

    public UIManager getFabricUIManager() {
        return this.mReactHost.getUIManager();
    }

    public CatalystInstance getCatalystInstance() {
        Log.w(this.TAG, "[WARNING] Bridgeless doesn't support CatalystInstance. Accessing an API that's not part of the new architecture is not encouraged usage.");
        return new BridgelessCatalystInstance(this.mReactHost);
    }

    @Deprecated
    public boolean hasActiveCatalystInstance() {
        return hasActiveReactInstance();
    }

    public boolean hasActiveReactInstance() {
        return this.mReactHost.isInstanceInitialized();
    }

    public boolean hasReactInstance() {
        return this.mReactHost.isInstanceInitialized();
    }

    /* access modifiers changed from: package-private */
    public DevSupportManager getDevSupportManager() {
        return this.mReactHost.getDevSupportManager();
    }

    public void registerSegment(int i, String str, Callback callback) {
        this.mReactHost.registerSegment(i, str, callback);
    }

    private static class BridgelessJSModuleInvocationHandler implements InvocationHandler {
        private final Class<? extends JavaScriptModule> mJSModuleInterface;
        private final ReactHostImpl mReactHost;

        public BridgelessJSModuleInvocationHandler(ReactHostImpl reactHostImpl, Class<? extends JavaScriptModule> cls) {
            this.mReactHost = reactHostImpl;
            this.mJSModuleInterface = cls;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) {
            this.mReactHost.callFunctionOnModule(JavaScriptModuleRegistry.getJSModuleName(this.mJSModuleInterface), method.getName(), objArr != null ? Arguments.fromJavaArgs(objArr) : new WritableNativeArray());
            return null;
        }
    }

    public <T extends JavaScriptModule> T getJSModule(Class<T> cls) {
        InteropModuleRegistry interopModuleRegistry = this.mInteropModuleRegistry;
        if (interopModuleRegistry == null || !interopModuleRegistry.shouldReturnInteropModule(cls)) {
            return (JavaScriptModule) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new BridgelessJSModuleInvocationHandler(this.mReactHost, cls));
        }
        return this.mInteropModuleRegistry.getInteropModule(cls);
    }

    public void emitDeviceEvent(String str, Object obj) {
        this.mReactHost.callFunctionOnModule("RCTDeviceEventEmitter", "emit", Arguments.fromJavaArgs(new Object[]{str, obj}));
    }

    public <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        return this.mReactHost.hasNativeModule(cls);
    }

    public Collection<NativeModule> getNativeModules() {
        return this.mReactHost.getNativeModules();
    }

    public <T extends NativeModule> T getNativeModule(Class<T> cls) {
        return this.mReactHost.getNativeModule(cls);
    }

    public NativeModule getNativeModule(String str) {
        return this.mReactHost.getNativeModule(str);
    }

    @UnstableReactNativeAPI
    @FrameworkAPI
    public JavaScriptContextHolder getJavaScriptContextHolder() {
        return this.mReactHost.getJavaScriptContextHolder();
    }

    public void handleException(Exception exc) {
        this.mReactHost.handleHostException(exc);
    }

    public CallInvokerHolder getJSCallInvokerHolder() {
        return this.mReactHost.getJSCallInvokerHolder();
    }

    /* access modifiers changed from: package-private */
    public DefaultHardwareBackBtnHandler getDefaultHardwareBackBtnHandler() {
        return this.mReactHost.getDefaultBackButtonHandler();
    }
}
