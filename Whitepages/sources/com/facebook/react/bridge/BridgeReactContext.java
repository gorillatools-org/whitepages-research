package com.facebook.react.bridge;

import android.content.Context;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.interop.InteropModuleRegistry;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.FrameworkAPI;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import java.util.Collection;

@VisibleForTesting
public class BridgeReactContext extends ReactApplicationContext {
    private static final String EARLY_JS_ACCESS_EXCEPTION_MESSAGE = "Tried to access a JS module before the React instance was fully set up. Calls to ReactContext#getJSModule should only happen once initialize() has been called on your native module.";
    private static final String EARLY_NATIVE_MODULE_EXCEPTION_MESSAGE = "Trying to call native module before CatalystInstance has been set!";
    private static final String LATE_JS_ACCESS_EXCEPTION_MESSAGE = "Tried to access a JS module after the React instance was destroyed.";
    private static final String LATE_NATIVE_MODULE_EXCEPTION_MESSAGE = "Trying to call native module after CatalystInstance has been destroyed!";
    private static final String TAG = "BridgeReactContext";
    private CatalystInstance mCatalystInstance;
    private volatile boolean mDestroyed = false;

    @DoNotStrip
    public interface RCTDeviceEventEmitter extends JavaScriptModule {
        void emit(String str, Object obj);
    }

    @Deprecated
    public boolean isBridgeless() {
        return false;
    }

    public BridgeReactContext(Context context) {
        super(context);
    }

    public void initializeWithInstance(CatalystInstance catalystInstance) {
        if (catalystInstance == null) {
            throw new IllegalArgumentException("CatalystInstance cannot be null.");
        } else if (this.mCatalystInstance == null) {
            if (this.mDestroyed) {
                ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("Cannot initialize ReactContext after it has been destroyed."));
            }
            this.mCatalystInstance = catalystInstance;
            initializeMessageQueueThreads(catalystInstance.getReactQueueConfiguration());
            initializeInteropModules();
        } else {
            throw new IllegalStateException("ReactContext has been already initialized");
        }
    }

    private void raiseCatalystInstanceMissingException() {
        throw new IllegalStateException(this.mDestroyed ? LATE_NATIVE_MODULE_EXCEPTION_MESSAGE : EARLY_NATIVE_MODULE_EXCEPTION_MESSAGE);
    }

    public <T extends JavaScriptModule> T getJSModule(Class<T> cls) {
        if (this.mCatalystInstance != null) {
            InteropModuleRegistry interopModuleRegistry = this.mInteropModuleRegistry;
            if (interopModuleRegistry == null || !interopModuleRegistry.shouldReturnInteropModule(cls)) {
                return this.mCatalystInstance.getJSModule(cls);
            }
            return this.mInteropModuleRegistry.getInteropModule(cls);
        } else if (this.mDestroyed) {
            throw new IllegalStateException(LATE_JS_ACCESS_EXCEPTION_MESSAGE);
        } else {
            throw new IllegalStateException(EARLY_JS_ACCESS_EXCEPTION_MESSAGE);
        }
    }

    public <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        if (this.mCatalystInstance == null) {
            raiseCatalystInstanceMissingException();
        }
        return this.mCatalystInstance.hasNativeModule(cls);
    }

    public Collection<NativeModule> getNativeModules() {
        if (this.mCatalystInstance == null) {
            raiseCatalystInstanceMissingException();
        }
        return this.mCatalystInstance.getNativeModules();
    }

    public <T extends NativeModule> T getNativeModule(Class<T> cls) {
        if (this.mCatalystInstance == null) {
            raiseCatalystInstanceMissingException();
        }
        return this.mCatalystInstance.getNativeModule(cls);
    }

    public NativeModule getNativeModule(String str) {
        if (this.mCatalystInstance == null) {
            raiseCatalystInstanceMissingException();
        }
        return this.mCatalystInstance.getNativeModule(str);
    }

    public CatalystInstance getCatalystInstance() {
        return (CatalystInstance) Assertions.assertNotNull(this.mCatalystInstance);
    }

    @Deprecated
    public boolean hasActiveCatalystInstance() {
        return hasActiveReactInstance();
    }

    public boolean hasActiveReactInstance() {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        return catalystInstance != null && !catalystInstance.isDestroyed();
    }

    @Deprecated
    public boolean hasCatalystInstance() {
        return this.mCatalystInstance != null;
    }

    public boolean hasReactInstance() {
        return this.mCatalystInstance != null;
    }

    public void destroy() {
        UiThreadUtil.assertOnUiThread();
        this.mDestroyed = true;
        CatalystInstance catalystInstance = this.mCatalystInstance;
        if (catalystInstance != null) {
            catalystInstance.destroy();
        }
    }

    public void handleException(Exception exc) {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        boolean z = false;
        boolean z2 = catalystInstance != null;
        boolean z3 = z2 && !catalystInstance.isDestroyed();
        if (getJSExceptionHandler() != null) {
            z = true;
        }
        if (!z3 || !z) {
            FLog.e(ReactConstants.TAG, "Unable to handle Exception - catalystInstanceVariableExists: " + z2 + " - isCatalystInstanceAlive: " + z3 + " - hasExceptionHandler: " + z, (Throwable) exc);
            throw new IllegalStateException(exc);
        }
        getJSExceptionHandler().handleException(exc);
    }

    @UnstableReactNativeAPI
    @FrameworkAPI
    public JavaScriptContextHolder getJavaScriptContextHolder() {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        if (catalystInstance != null) {
            return catalystInstance.getJavaScriptContextHolder();
        }
        return null;
    }

    public CallInvokerHolder getJSCallInvokerHolder() {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        if (catalystInstance != null) {
            return catalystInstance.getJSCallInvokerHolder();
        }
        return null;
    }

    public UIManager getFabricUIManager() {
        return this.mCatalystInstance.getFabricUIManager();
    }

    public String getSourceURL() {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        if (catalystInstance == null) {
            return null;
        }
        return catalystInstance.getSourceURL();
    }

    public void registerSegment(int i, String str, Callback callback) {
        ((CatalystInstance) Assertions.assertNotNull(this.mCatalystInstance)).registerSegment(i, str);
        ((Callback) Assertions.assertNotNull(callback)).invoke(new Object[0]);
    }
}
