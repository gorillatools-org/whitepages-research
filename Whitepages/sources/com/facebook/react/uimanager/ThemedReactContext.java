package com.facebook.react.uimanager;

import android.app.Activity;
import android.content.Context;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import java.util.Collection;

public class ThemedReactContext extends ReactContext {
    private final String mModuleName;
    private final ReactApplicationContext mReactApplicationContext;
    private final int mSurfaceId;

    @Deprecated
    public ThemedReactContext(ReactApplicationContext reactApplicationContext, Context context) {
        this(reactApplicationContext, context, (String) null, -1);
    }

    @Deprecated
    public ThemedReactContext(ReactApplicationContext reactApplicationContext, Context context, String str) {
        this(reactApplicationContext, context, str, -1);
    }

    public ThemedReactContext(ReactApplicationContext reactApplicationContext, Context context, String str, int i) {
        super(context);
        initializeFromOther(reactApplicationContext);
        this.mReactApplicationContext = reactApplicationContext;
        this.mModuleName = str;
        this.mSurfaceId = i;
    }

    public void addLifecycleEventListener(LifecycleEventListener lifecycleEventListener) {
        this.mReactApplicationContext.addLifecycleEventListener(lifecycleEventListener);
    }

    public void removeLifecycleEventListener(LifecycleEventListener lifecycleEventListener) {
        this.mReactApplicationContext.removeLifecycleEventListener(lifecycleEventListener);
    }

    public boolean hasCurrentActivity() {
        return this.mReactApplicationContext.hasCurrentActivity();
    }

    public Activity getCurrentActivity() {
        return this.mReactApplicationContext.getCurrentActivity();
    }

    public <T extends JavaScriptModule> T getJSModule(Class<T> cls) {
        return this.mReactApplicationContext.getJSModule(cls);
    }

    public <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        return this.mReactApplicationContext.hasNativeModule(cls);
    }

    public Collection<NativeModule> getNativeModules() {
        return this.mReactApplicationContext.getNativeModules();
    }

    public <T extends NativeModule> T getNativeModule(Class<T> cls) {
        return this.mReactApplicationContext.getNativeModule(cls);
    }

    public NativeModule getNativeModule(String str) {
        return this.mReactApplicationContext.getNativeModule(str);
    }

    public CatalystInstance getCatalystInstance() {
        return this.mReactApplicationContext.getCatalystInstance();
    }

    @Deprecated
    public boolean hasActiveCatalystInstance() {
        return this.mReactApplicationContext.hasActiveCatalystInstance();
    }

    public boolean hasActiveReactInstance() {
        return this.mReactApplicationContext.hasActiveCatalystInstance();
    }

    public boolean hasCatalystInstance() {
        return this.mReactApplicationContext.hasCatalystInstance();
    }

    public boolean hasReactInstance() {
        return this.mReactApplicationContext.hasReactInstance();
    }

    public void destroy() {
        this.mReactApplicationContext.destroy();
    }

    @Deprecated
    public String getSurfaceID() {
        return this.mModuleName;
    }

    public String getModuleName() {
        return this.mModuleName;
    }

    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    public ReactApplicationContext getReactApplicationContext() {
        return this.mReactApplicationContext;
    }

    public void handleException(Exception exc) {
        this.mReactApplicationContext.handleException(exc);
    }

    @Deprecated
    public boolean isBridgeless() {
        return this.mReactApplicationContext.isBridgeless();
    }

    public JavaScriptContextHolder getJavaScriptContextHolder() {
        return this.mReactApplicationContext.getJavaScriptContextHolder();
    }

    public CallInvokerHolder getJSCallInvokerHolder() {
        return this.mReactApplicationContext.getJSCallInvokerHolder();
    }

    public UIManager getFabricUIManager() {
        return this.mReactApplicationContext.getFabricUIManager();
    }

    public String getSourceURL() {
        return this.mReactApplicationContext.getSourceURL();
    }

    public void registerSegment(int i, String str, Callback callback) {
        this.mReactApplicationContext.registerSegment(i, str, callback);
    }
}
