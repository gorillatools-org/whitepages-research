package com.facebook.react;

import android.app.Application;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.JSExceptionHandler;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerProvider;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import com.facebook.react.devsupport.interfaces.PausedInDebuggerOverlayManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.internal.ChoreographerProvider;
import java.util.List;

public abstract class ReactNativeHost {
    private final Application mApplication;
    private ReactInstanceManager mReactInstanceManager;

    /* access modifiers changed from: private */
    public static /* synthetic */ UIManager lambda$getUIManagerProvider$0(ReactApplicationContext reactApplicationContext) {
        return null;
    }

    /* access modifiers changed from: protected */
    public ChoreographerProvider getChoreographerProvider() {
        return null;
    }

    /* access modifiers changed from: protected */
    public DevLoadingViewManager getDevLoadingViewManager() {
        return null;
    }

    /* access modifiers changed from: protected */
    public DevSupportManagerFactory getDevSupportManagerFactory() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getJSBundleFile() {
        return null;
    }

    /* access modifiers changed from: protected */
    public JSEngineResolutionAlgorithm getJSEngineResolutionAlgorithm() {
        return null;
    }

    /* access modifiers changed from: protected */
    public JSExceptionHandler getJSExceptionHandler() {
        return null;
    }

    /* access modifiers changed from: protected */
    public JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
        return null;
    }

    public boolean getLazyViewManagersEnabled() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract List<ReactPackage> getPackages();

    /* access modifiers changed from: protected */
    public PausedInDebuggerOverlayManager getPausedInDebuggerOverlayManager() {
        return null;
    }

    /* access modifiers changed from: protected */
    public ReactPackageTurboModuleManagerDelegate.Builder getReactPackageTurboModuleManagerDelegateBuilder() {
        return null;
    }

    /* access modifiers changed from: protected */
    public RedBoxHandler getRedBoxHandler() {
        return null;
    }

    public boolean getShouldRequireActivity() {
        return true;
    }

    public abstract boolean getUseDeveloperSupport();

    protected ReactNativeHost(Application application) {
        this.mApplication = application;
    }

    public synchronized ReactInstanceManager getReactInstanceManager() {
        try {
            if (this.mReactInstanceManager == null) {
                ReactMarker.logMarker(ReactMarkerConstants.INIT_REACT_RUNTIME_START);
                ReactMarker.logMarker(ReactMarkerConstants.GET_REACT_INSTANCE_MANAGER_START);
                this.mReactInstanceManager = createReactInstanceManager();
                ReactMarker.logMarker(ReactMarkerConstants.GET_REACT_INSTANCE_MANAGER_END);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.mReactInstanceManager;
    }

    public synchronized boolean hasInstance() {
        return this.mReactInstanceManager != null;
    }

    public synchronized void clear() {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.invalidate();
            this.mReactInstanceManager = null;
        }
    }

    /* access modifiers changed from: protected */
    public ReactInstanceManager createReactInstanceManager() {
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_REACT_INSTANCE_MANAGER_START);
        ReactInstanceManagerBuilder baseReactInstanceManagerBuilder = getBaseReactInstanceManagerBuilder();
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_REACT_INSTANCE_MANAGER_END);
        return baseReactInstanceManagerBuilder.build();
    }

    /* access modifiers changed from: protected */
    public ReactInstanceManagerBuilder getBaseReactInstanceManagerBuilder() {
        ReactInstanceManagerBuilder pausedInDebuggerOverlayManager = ReactInstanceManager.builder().setApplication(this.mApplication).setJSMainModulePath(getJSMainModuleName()).setUseDeveloperSupport(getUseDeveloperSupport()).setDevSupportManagerFactory(getDevSupportManagerFactory()).setDevLoadingViewManager(getDevLoadingViewManager()).setRequireActivity(getShouldRequireActivity()).setSurfaceDelegateFactory(getSurfaceDelegateFactory()).setJSExceptionHandler(getJSExceptionHandler()).setLazyViewManagersEnabled(getLazyViewManagersEnabled()).setRedBoxHandler(getRedBoxHandler()).setJavaScriptExecutorFactory(getJavaScriptExecutorFactory()).setUIManagerProvider(getUIManagerProvider()).setInitialLifecycleState(LifecycleState.BEFORE_CREATE).setReactPackageTurboModuleManagerDelegateBuilder(getReactPackageTurboModuleManagerDelegateBuilder()).setJSEngineResolutionAlgorithm(getJSEngineResolutionAlgorithm()).setChoreographerProvider(getChoreographerProvider()).setPausedInDebuggerOverlayManager(getPausedInDebuggerOverlayManager());
        for (ReactPackage addPackage : getPackages()) {
            pausedInDebuggerOverlayManager.addPackage(addPackage);
        }
        String jSBundleFile = getJSBundleFile();
        if (jSBundleFile != null) {
            pausedInDebuggerOverlayManager.setJSBundleFile(jSBundleFile);
        } else {
            pausedInDebuggerOverlayManager.setBundleAssetName((String) Assertions.assertNotNull(getBundleAssetName()));
        }
        return pausedInDebuggerOverlayManager;
    }

    /* access modifiers changed from: protected */
    public final Application getApplication() {
        return this.mApplication;
    }

    /* access modifiers changed from: protected */
    public UIManagerProvider getUIManagerProvider() {
        return new ReactNativeHost$$ExternalSyntheticLambda0();
    }

    public SurfaceDelegateFactory getSurfaceDelegateFactory() {
        return new SurfaceDelegateFactory() {
            public SurfaceDelegate createSurfaceDelegate(String str) {
                return null;
            }
        };
    }

    /* access modifiers changed from: protected */
    public String getJSMainModuleName() {
        return "index.android";
    }

    /* access modifiers changed from: protected */
    public String getBundleAssetName() {
        return "index.android.bundle";
    }
}
