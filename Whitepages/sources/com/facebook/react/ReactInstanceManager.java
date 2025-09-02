package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BridgeReactContext;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.CatalystInstanceImpl;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSExceptionHandler;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.NativeModuleRegistry;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.ProxyJavaScriptExecutor;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactCxxErrorHandler;
import com.facebook.react.bridge.ReactInstanceManagerInspectorTarget;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerProvider;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.devsupport.InspectorFlags;
import com.facebook.react.devsupport.ReactInstanceDevHelper;
import com.facebook.react.devsupport.inspector.InspectorNetworkHelper;
import com.facebook.react.devsupport.inspector.InspectorNetworkRequestListener;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.devsupport.interfaces.PausedInDebuggerOverlayManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.interfaces.TaskInterface;
import com.facebook.react.internal.AndroidChoreographerProvider;
import com.facebook.react.internal.ChoreographerProvider;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.internal.turbomodule.core.TurboModuleManager;
import com.facebook.react.modules.appearance.AppearanceModule;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.facebook.react.packagerconnection.RequestHandler;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.ReactRoot;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.soloader.SoLoader;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReactInstanceManager {
    private static final String TAG = "ReactInstanceManager";
    /* access modifiers changed from: private */
    public final Context mApplicationContext;
    private final Set<ReactRoot> mAttachedReactRoots = Collections.synchronizedSet(new HashSet());
    private final NotThreadSafeBridgeIdleDebugListener mBridgeIdleDebugListener;
    private final JSBundleLoader mBundleLoader;
    private volatile Thread mCreateReactContextThread;
    /* access modifiers changed from: private */
    public Activity mCurrentActivity;
    private volatile ReactContext mCurrentReactContext;
    private DefaultHardwareBackBtnHandler mDefaultBackButtonImpl;
    /* access modifiers changed from: private */
    public final DevSupportManager mDevSupportManager;
    private volatile boolean mHasStartedCreatingInitialContext = false;
    private volatile Boolean mHasStartedDestroying = Boolean.FALSE;
    /* access modifiers changed from: private */
    public ReactInstanceManagerInspectorTarget mInspectorTarget;
    /* access modifiers changed from: private */
    public volatile boolean mInstanceManagerInvalidated = false;
    private final JSExceptionHandler mJSExceptionHandler;
    private final String mJSMainModulePath;
    private final JavaScriptExecutorFactory mJavaScriptExecutorFactory;
    private final boolean mKeepActivity;
    private volatile LifecycleState mLifecycleState;
    private final MemoryPressureRouter mMemoryPressureRouter;
    private final List<ReactPackage> mPackages;
    private ReactContextInitParams mPendingReactContextInitParams;
    private final Object mReactContextLock = new Object();
    private final Collection<ReactInstanceEventListener> mReactInstanceEventListeners = Collections.synchronizedList(new ArrayList());
    private final boolean mRequireActivity;
    private final ReactPackageTurboModuleManagerDelegate.Builder mTMMDelegateBuilder;
    private final UIManagerProvider mUIManagerProvider;
    private final boolean mUseDeveloperSupport;
    /* access modifiers changed from: private */
    public boolean mUseFallbackBundle = true;
    private Collection<String> mViewManagerNames = null;
    private List<ViewManager> mViewManagers;

    @Deprecated
    public interface ReactInstanceEventListener extends ReactInstanceEventListener {
    }

    private class ReactContextInitParams {
        private final JSBundleLoader mJsBundleLoader;
        private final JavaScriptExecutorFactory mJsExecutorFactory;

        public ReactContextInitParams(JavaScriptExecutorFactory javaScriptExecutorFactory, JSBundleLoader jSBundleLoader) {
            this.mJsExecutorFactory = (JavaScriptExecutorFactory) Assertions.assertNotNull(javaScriptExecutorFactory);
            this.mJsBundleLoader = (JSBundleLoader) Assertions.assertNotNull(jSBundleLoader);
        }

        public JavaScriptExecutorFactory getJsExecutorFactory() {
            return this.mJsExecutorFactory;
        }

        public JSBundleLoader getJsBundleLoader() {
            return this.mJsBundleLoader;
        }
    }

    public static ReactInstanceManagerBuilder builder() {
        return new ReactInstanceManagerBuilder();
    }

    ReactInstanceManager(Context context, Activity activity, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler, JavaScriptExecutorFactory javaScriptExecutorFactory, JSBundleLoader jSBundleLoader, String str, List<ReactPackage> list, boolean z, DevSupportManagerFactory devSupportManagerFactory, boolean z2, boolean z3, NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener, LifecycleState lifecycleState, JSExceptionHandler jSExceptionHandler, RedBoxHandler redBoxHandler, boolean z4, DevBundleDownloadListener devBundleDownloadListener, int i, int i2, UIManagerProvider uIManagerProvider, Map<String, RequestHandler> map, ReactPackageTurboModuleManagerDelegate.Builder builder, SurfaceDelegateFactory surfaceDelegateFactory, DevLoadingViewManager devLoadingViewManager, ChoreographerProvider choreographerProvider, PausedInDebuggerOverlayManager pausedInDebuggerOverlayManager) {
        ChoreographerProvider choreographerProvider2;
        Context context2 = context;
        boolean z5 = z;
        FLog.d(TAG, "ReactInstanceManager.ctor()");
        initializeSoLoaderIfNecessary(context);
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(context);
        this.mApplicationContext = context2;
        this.mCurrentActivity = activity;
        this.mDefaultBackButtonImpl = defaultHardwareBackBtnHandler;
        this.mJavaScriptExecutorFactory = javaScriptExecutorFactory;
        this.mBundleLoader = jSBundleLoader;
        String str2 = str;
        this.mJSMainModulePath = str2;
        ArrayList arrayList = new ArrayList();
        this.mPackages = arrayList;
        this.mUseDeveloperSupport = z5;
        this.mRequireActivity = z2;
        this.mKeepActivity = z3;
        Systrace.beginSection(0, "ReactInstanceManager.initDevSupportManager");
        DevSupportManager create = devSupportManagerFactory.create(context, createDevHelperInterface(), str2, z, redBoxHandler, devBundleDownloadListener, i, map, surfaceDelegateFactory, devLoadingViewManager, pausedInDebuggerOverlayManager);
        this.mDevSupportManager = create;
        Systrace.endSection(0);
        this.mBridgeIdleDebugListener = notThreadSafeBridgeIdleDebugListener;
        this.mLifecycleState = lifecycleState;
        this.mMemoryPressureRouter = new MemoryPressureRouter(context2);
        this.mJSExceptionHandler = jSExceptionHandler;
        this.mTMMDelegateBuilder = builder;
        synchronized (arrayList) {
            try {
                PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: Use Split Packages");
                arrayList.add(new CoreModulesPackage(this, new DefaultHardwareBackBtnHandler() {
                    public void invokeDefaultOnBackPressed() {
                        ReactInstanceManager.this.invokeDefaultOnBackPressed();
                    }
                }, z4, i2));
                if (z5) {
                    arrayList.add(new DebugCorePackage());
                }
                arrayList.addAll(list);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.mUIManagerProvider = uIManagerProvider;
        if (choreographerProvider != null) {
            choreographerProvider2 = choreographerProvider;
        } else {
            choreographerProvider2 = AndroidChoreographerProvider.getInstance();
        }
        ReactChoreographer.initialize(choreographerProvider2);
        if (z5) {
            create.startInspector();
        }
        registerCxxErrorHandlerFunc();
    }

    private ReactInstanceDevHelper createDevHelperInterface() {
        return new ReactInstanceDevHelper() {
            public ReactContext getCurrentReactContext() {
                return null;
            }

            public TaskInterface<Boolean> loadBundle(JSBundleLoader jSBundleLoader) {
                return null;
            }

            public void reload(String str) {
            }

            public void onReloadWithJSDebugger(JavaJSExecutor.Factory factory) {
                ReactInstanceManager.this.onReloadWithJSDebugger(factory);
            }

            public void onJSBundleLoadedFromServer() {
                ReactInstanceManager.this.onJSBundleLoadedFromServer();
            }

            public void toggleElementInspector() {
                ReactInstanceManager.this.toggleElementInspector();
            }

            public Activity getCurrentActivity() {
                return ReactInstanceManager.this.mCurrentActivity;
            }

            public JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
                return ReactInstanceManager.this.getJSExecutorFactory();
            }

            public View createRootView(String str) {
                Activity currentActivity = getCurrentActivity();
                if (currentActivity == null) {
                    return null;
                }
                ReactRootView reactRootView = new ReactRootView(currentActivity);
                reactRootView.setIsFabric(ReactNativeFeatureFlags.enableFabricRenderer());
                reactRootView.startReactApplication(ReactInstanceManager.this, str, new Bundle());
                return reactRootView;
            }

            public void destroyRootView(View view) {
                if (view instanceof ReactRootView) {
                    ((ReactRootView) view).unmountReactApplication();
                }
            }
        };
    }

    public synchronized void setUseFallbackBundle(boolean z) {
        this.mUseFallbackBundle = z;
    }

    /* access modifiers changed from: private */
    public JavaScriptExecutorFactory getJSExecutorFactory() {
        return this.mJavaScriptExecutorFactory;
    }

    public DevSupportManager getDevSupportManager() {
        return this.mDevSupportManager;
    }

    public MemoryPressureRouter getMemoryPressureRouter() {
        return this.mMemoryPressureRouter;
    }

    public List<ReactPackage> getPackages() {
        return new ArrayList(this.mPackages);
    }

    public void handleCxxError(Exception exc) {
        this.mDevSupportManager.handleException(exc);
    }

    private void registerCxxErrorHandlerFunc() {
        Method method;
        try {
            method = ReactInstanceManager.class.getMethod("handleCxxError", new Class[]{Exception.class});
        } catch (NoSuchMethodException e) {
            FLog.e("ReactInstanceHolder", "Failed to set cxx error handler function", (Throwable) e);
            method = null;
        }
        ReactCxxErrorHandler.setHandleErrorFunc(this, method);
    }

    private void unregisterCxxErrorHandlerFunc() {
        ReactCxxErrorHandler.setHandleErrorFunc((Object) null, (Method) null);
    }

    static void initializeSoLoaderIfNecessary(Context context) {
        SoLoader.init(context, false);
    }

    public void createReactContextInBackground() {
        FLog.d(TAG, "ReactInstanceManager.createReactContextInBackground()");
        UiThreadUtil.assertOnUiThread();
        if (!this.mHasStartedCreatingInitialContext) {
            this.mHasStartedCreatingInitialContext = true;
            recreateReactContextInBackgroundInner();
        }
    }

    public void recreateReactContextInBackground() {
        Assertions.assertCondition(this.mHasStartedCreatingInitialContext, "recreateReactContextInBackground should only be called after the initial createReactContextInBackground call.");
        recreateReactContextInBackgroundInner();
    }

    private void recreateReactContextInBackgroundInner() {
        FLog.d(TAG, "ReactInstanceManager.recreateReactContextInBackgroundInner()");
        PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: recreateReactContextInBackground");
        UiThreadUtil.assertOnUiThread();
        if (this.mUseDeveloperSupport && this.mJSMainModulePath != null) {
            final DeveloperSettings devSettings = this.mDevSupportManager.getDevSettings();
            if (!Systrace.isTracing(0)) {
                if (this.mBundleLoader == null) {
                    this.mDevSupportManager.handleReloadJS();
                    return;
                } else {
                    this.mDevSupportManager.isPackagerRunning(new PackagerStatusCallback() {
                        public void onPackagerStatusFetched(boolean z) {
                            UiThreadUtil.runOnUiThread(new ReactInstanceManager$3$$ExternalSyntheticLambda0(this, z, devSettings));
                        }

                        /* access modifiers changed from: private */
                        public /* synthetic */ void lambda$onPackagerStatusFetched$0(boolean z, DeveloperSettings developerSettings) {
                            if (!ReactInstanceManager.this.mInstanceManagerInvalidated) {
                                if (z) {
                                    ReactInstanceManager.this.mDevSupportManager.handleReloadJS();
                                } else if (!ReactInstanceManager.this.mDevSupportManager.hasUpToDateJSBundleInCache() || developerSettings.isRemoteJSDebugEnabled() || ReactInstanceManager.this.mUseFallbackBundle) {
                                    developerSettings.setRemoteJSDebugEnabled(false);
                                    ReactInstanceManager.this.recreateReactContextInBackgroundFromBundleLoader();
                                } else {
                                    ReactInstanceManager.this.onJSBundleLoadedFromServer();
                                }
                            }
                        }
                    });
                    return;
                }
            }
        }
        recreateReactContextInBackgroundFromBundleLoader();
    }

    /* access modifiers changed from: private */
    public void recreateReactContextInBackgroundFromBundleLoader() {
        FLog.d(TAG, "ReactInstanceManager.recreateReactContextInBackgroundFromBundleLoader()");
        PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: load from BundleLoader");
        recreateReactContextInBackground(this.mJavaScriptExecutorFactory, this.mBundleLoader);
    }

    public boolean hasStartedCreatingInitialContext() {
        return this.mHasStartedCreatingInitialContext;
    }

    public void onBackPressed() {
        UiThreadUtil.assertOnUiThread();
        ReactContext reactContext = this.mCurrentReactContext;
        if (reactContext == null) {
            FLog.w(TAG, "Instance detached from instance manager");
            invokeDefaultOnBackPressed();
            return;
        }
        DeviceEventManagerModule deviceEventManagerModule = (DeviceEventManagerModule) reactContext.getNativeModule(DeviceEventManagerModule.class);
        if (deviceEventManagerModule != null) {
            deviceEventManagerModule.emitHardwareBackPressed();
        }
    }

    /* access modifiers changed from: private */
    public void invokeDefaultOnBackPressed() {
        UiThreadUtil.assertOnUiThread();
        DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler = this.mDefaultBackButtonImpl;
        if (defaultHardwareBackBtnHandler != null) {
            defaultHardwareBackBtnHandler.invokeDefaultOnBackPressed();
        }
    }

    public void onNewIntent(Intent intent) {
        DeviceEventManagerModule deviceEventManagerModule;
        UiThreadUtil.assertOnUiThread();
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext == null) {
            FLog.w(TAG, "Instance detached from instance manager");
            return;
        }
        String action = intent.getAction();
        Uri data = intent.getData();
        if (data != null && (("android.intent.action.VIEW".equals(action) || "android.nfc.action.NDEF_DISCOVERED".equals(action)) && (deviceEventManagerModule = (DeviceEventManagerModule) currentReactContext.getNativeModule(DeviceEventManagerModule.class)) != null)) {
            deviceEventManagerModule.emitNewIntentReceived(data);
        }
        currentReactContext.onNewIntent(this.mCurrentActivity, intent);
    }

    /* access modifiers changed from: private */
    public void toggleElementInspector() {
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext == null || !currentReactContext.hasActiveReactInstance()) {
            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Cannot toggleElementInspector, CatalystInstance not available"));
        } else {
            currentReactContext.emitDeviceEvent("toggleElementInspector");
        }
    }

    @Deprecated
    public void onHostPause() {
        UiThreadUtil.assertOnUiThread();
        this.mDefaultBackButtonImpl = null;
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
        }
        moveToBeforeResumeLifecycleState();
    }

    public void onUserLeaveHint(Activity activity) {
        Activity activity2 = this.mCurrentActivity;
        if (activity2 != null && activity == activity2) {
            UiThreadUtil.assertOnUiThread();
            ReactContext currentReactContext = getCurrentReactContext();
            if (currentReactContext != null) {
                currentReactContext.onUserLeaveHint(activity);
            }
        }
    }

    public void onHostPause(Activity activity) {
        boolean z = true;
        if (this.mRequireActivity) {
            if (this.mCurrentActivity == null) {
                FLog.e(TAG, "ReactInstanceManager.onHostPause called with null activity, expected:" + this.mCurrentActivity.getClass().getSimpleName());
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                int length = stackTrace.length;
                for (int i = 0; i < length; i++) {
                    FLog.e(TAG, stackTrace[i].toString());
                }
            }
            Assertions.assertCondition(this.mCurrentActivity != null);
        }
        Activity activity2 = this.mCurrentActivity;
        if (activity2 != null) {
            if (activity != activity2) {
                z = false;
            }
            Assertions.assertCondition(z, "Pausing an activity that is not the current activity, this is incorrect! Current activity: " + this.mCurrentActivity.getClass().getSimpleName() + " Paused activity: " + activity.getClass().getSimpleName());
        }
        onHostPause();
    }

    public void onHostResume(Activity activity, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        UiThreadUtil.assertOnUiThread();
        this.mDefaultBackButtonImpl = defaultHardwareBackBtnHandler;
        onHostResume(activity);
    }

    public void onHostResume(Activity activity) {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentActivity = activity;
        if (this.mUseDeveloperSupport) {
            if (activity != null) {
                final View decorView = activity.getWindow().getDecorView();
                if (!ViewCompat.isAttachedToWindow(decorView)) {
                    decorView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                        public void onViewDetachedFromWindow(View view) {
                        }

                        public void onViewAttachedToWindow(View view) {
                            decorView.removeOnAttachStateChangeListener(this);
                            ReactInstanceManager.this.mDevSupportManager.setDevSupportEnabled(true);
                        }
                    });
                } else {
                    this.mDevSupportManager.setDevSupportEnabled(true);
                }
            } else if (!this.mRequireActivity) {
                this.mDevSupportManager.setDevSupportEnabled(true);
            }
        }
        moveToResumedLifecycleState(false);
    }

    @Deprecated
    public void onHostDestroy() {
        UiThreadUtil.assertOnUiThread();
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
        }
        moveToBeforeCreateLifecycleState();
        if (!this.mKeepActivity) {
            this.mCurrentActivity = null;
        }
    }

    public void onHostDestroy(Activity activity) {
        if (activity == this.mCurrentActivity) {
            onHostDestroy();
        }
    }

    private void logOnDestroy() {
        FLog.d(TAG, "ReactInstanceManager.destroy called", (Throwable) new RuntimeException("ReactInstanceManager.destroy called"));
    }

    public void destroy() {
        ReactInstanceManagerInspectorTarget reactInstanceManagerInspectorTarget;
        UiThreadUtil.assertOnUiThread();
        PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: Destroy");
        logOnDestroy();
        if (this.mHasStartedDestroying.booleanValue()) {
            FLog.e(ReactConstants.TAG, "ReactInstanceManager.destroy called: bail out, already destroying");
            return;
        }
        this.mHasStartedDestroying = Boolean.TRUE;
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
            this.mDevSupportManager.stopInspector();
        }
        moveToBeforeCreateLifecycleState();
        this.mMemoryPressureRouter.destroy(this.mApplicationContext);
        unregisterCxxErrorHandlerFunc();
        this.mCreateReactContextThread = null;
        synchronized (this.mAttachedReactRoots) {
            synchronized (this.mReactContextLock) {
                try {
                    if (this.mCurrentReactContext != null) {
                        for (ReactRoot next : this.mAttachedReactRoots) {
                            if (next.getUIManagerType() == 2) {
                                detachRootViewFromInstance(next, this.mCurrentReactContext);
                            }
                        }
                        this.mCurrentReactContext.destroy();
                        this.mCurrentReactContext = null;
                    }
                } finally {
                    while (true) {
                    }
                }
            }
        }
        if (this.mInstanceManagerInvalidated && (reactInstanceManagerInspectorTarget = this.mInspectorTarget) != null) {
            reactInstanceManagerInspectorTarget.close();
            this.mInspectorTarget = null;
        }
        this.mHasStartedCreatingInitialContext = false;
        if (!this.mKeepActivity) {
            this.mCurrentActivity = null;
        }
        ResourceDrawableIdHelper.getInstance().clear();
        this.mHasStartedDestroying = Boolean.FALSE;
        synchronized (this.mHasStartedDestroying) {
            this.mHasStartedDestroying.notifyAll();
        }
        synchronized (this.mPackages) {
            this.mViewManagerNames = null;
        }
        FLog.d(ReactConstants.TAG, "ReactInstanceManager has been destroyed");
    }

    private synchronized void moveToResumedLifecycleState(boolean z) {
        try {
            ReactContext currentReactContext = getCurrentReactContext();
            if (currentReactContext != null) {
                if (!z && this.mLifecycleState != LifecycleState.BEFORE_RESUME) {
                    if (this.mLifecycleState == LifecycleState.BEFORE_CREATE) {
                    }
                }
                currentReactContext.onHostResume(this.mCurrentActivity);
            }
            this.mLifecycleState = LifecycleState.RESUMED;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private synchronized void moveToBeforeResumeLifecycleState() {
        try {
            ReactContext currentReactContext = getCurrentReactContext();
            if (currentReactContext != null) {
                if (this.mLifecycleState == LifecycleState.BEFORE_CREATE) {
                    currentReactContext.onHostResume(this.mCurrentActivity);
                    currentReactContext.onHostPause();
                } else if (this.mLifecycleState == LifecycleState.RESUMED) {
                    currentReactContext.onHostPause();
                }
            }
            this.mLifecycleState = LifecycleState.BEFORE_RESUME;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private synchronized void moveToBeforeCreateLifecycleState() {
        try {
            ReactContext currentReactContext = getCurrentReactContext();
            if (currentReactContext != null) {
                if (this.mLifecycleState == LifecycleState.RESUMED) {
                    currentReactContext.onHostPause();
                    this.mLifecycleState = LifecycleState.BEFORE_RESUME;
                }
                if (this.mLifecycleState == LifecycleState.BEFORE_RESUME) {
                    currentReactContext.onHostDestroy(this.mKeepActivity);
                }
            }
            this.mLifecycleState = LifecycleState.BEFORE_CREATE;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private synchronized void moveReactContextToCurrentLifecycleState() {
        if (this.mLifecycleState == LifecycleState.RESUMED) {
            moveToResumedLifecycleState(true);
        }
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            currentReactContext.onActivityResult(activity, i, i2, intent);
        }
    }

    public void onWindowFocusChange(boolean z) {
        UiThreadUtil.assertOnUiThread();
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            currentReactContext.onWindowFocusChange(z);
        }
    }

    public void onConfigurationChanged(Context context, Configuration configuration) {
        AppearanceModule appearanceModule;
        UiThreadUtil.assertOnUiThread();
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null && (appearanceModule = (AppearanceModule) currentReactContext.getNativeModule(AppearanceModule.class)) != null) {
            appearanceModule.onConfigurationChanged(context);
        }
    }

    public void showDevOptionsDialog() {
        UiThreadUtil.assertOnUiThread();
        this.mDevSupportManager.showDevOptionsDialog();
    }

    private void clearReactRoot(ReactRoot reactRoot) {
        UiThreadUtil.assertOnUiThread();
        reactRoot.getState().compareAndSet(1, 0);
        ViewGroup rootViewGroup = reactRoot.getRootViewGroup();
        rootViewGroup.removeAllViews();
        rootViewGroup.setId(-1);
    }

    @Deprecated
    public void attachRootView(ReactRoot reactRoot) {
        UiThreadUtil.assertOnUiThread();
        synchronized (this.mAttachedReactRoots) {
            try {
                if (this.mAttachedReactRoots.add(reactRoot)) {
                    clearReactRoot(reactRoot);
                } else {
                    FLog.e(ReactConstants.TAG, "ReactRoot was attached multiple times");
                }
                ReactContext currentReactContext = getCurrentReactContext();
                if (this.mCreateReactContextThread == null && currentReactContext != null) {
                    attachRootViewToInstance(reactRoot);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Deprecated
    public void detachRootView(ReactRoot reactRoot) {
        ReactContext reactContext;
        UiThreadUtil.assertOnUiThread();
        if (this.mAttachedReactRoots.remove(reactRoot) && (reactContext = this.mCurrentReactContext) != null && reactContext.hasActiveReactInstance()) {
            detachRootViewFromInstance(reactRoot, reactContext);
        }
    }

    public List<ViewManager> getOrCreateViewManagers(ReactApplicationContext reactApplicationContext) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_START);
        Systrace.beginSection(0, "createAllViewManagers");
        try {
            if (this.mViewManagers == null) {
                synchronized (this.mPackages) {
                    if (this.mViewManagers == null) {
                        ArrayList arrayList = new ArrayList();
                        for (ReactPackage createViewManagers : this.mPackages) {
                            arrayList.addAll(createViewManagers.createViewManagers(reactApplicationContext));
                        }
                        this.mViewManagers = arrayList;
                        Systrace.endSection(0);
                        ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_END);
                        return arrayList;
                    }
                }
            }
            List<ViewManager> list = this.mViewManagers;
            Systrace.endSection(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_END);
            return list;
        } catch (Throwable th) {
            Systrace.endSection(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_END);
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0 = r6.mPackages.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        if (r0.hasNext() == false) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        r4 = r0.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        if ((r4 instanceof com.facebook.react.ViewManagerOnDemandReactPackage) == false) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
        r4 = ((com.facebook.react.ViewManagerOnDemandReactPackage) r4).createViewManager(r1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0033, code lost:
        if (r4 == null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0035, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0036, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0039, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003a, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003c, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        r3 = r6.mPackages;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.uimanager.ViewManager createViewManager(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mReactContextLock
            monitor-enter(r0)
            com.facebook.react.bridge.ReactContext r1 = r6.getCurrentReactContext()     // Catch:{ all -> 0x003d }
            com.facebook.react.bridge.ReactApplicationContext r1 = (com.facebook.react.bridge.ReactApplicationContext) r1     // Catch:{ all -> 0x003d }
            r2 = 0
            if (r1 == 0) goto L_0x003f
            boolean r3 = r1.hasActiveReactInstance()     // Catch:{ all -> 0x003d }
            if (r3 != 0) goto L_0x0013
            goto L_0x003f
        L_0x0013:
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            java.util.List<com.facebook.react.ReactPackage> r3 = r6.mPackages
            monitor-enter(r3)
            java.util.List<com.facebook.react.ReactPackage> r0 = r6.mPackages     // Catch:{ all -> 0x0037 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0037 }
        L_0x001d:
            boolean r4 = r0.hasNext()     // Catch:{ all -> 0x0037 }
            if (r4 == 0) goto L_0x0039
            java.lang.Object r4 = r0.next()     // Catch:{ all -> 0x0037 }
            com.facebook.react.ReactPackage r4 = (com.facebook.react.ReactPackage) r4     // Catch:{ all -> 0x0037 }
            boolean r5 = r4 instanceof com.facebook.react.ViewManagerOnDemandReactPackage     // Catch:{ all -> 0x0037 }
            if (r5 == 0) goto L_0x001d
            com.facebook.react.ViewManagerOnDemandReactPackage r4 = (com.facebook.react.ViewManagerOnDemandReactPackage) r4     // Catch:{ all -> 0x0037 }
            com.facebook.react.uimanager.ViewManager r4 = r4.createViewManager(r1, r7)     // Catch:{ all -> 0x0037 }
            if (r4 == 0) goto L_0x001d
            monitor-exit(r3)     // Catch:{ all -> 0x0037 }
            return r4
        L_0x0037:
            r7 = move-exception
            goto L_0x003b
        L_0x0039:
            monitor-exit(r3)     // Catch:{ all -> 0x0037 }
            return r2
        L_0x003b:
            monitor-exit(r3)     // Catch:{ all -> 0x0037 }
            throw r7
        L_0x003d:
            r7 = move-exception
            goto L_0x0041
        L_0x003f:
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            return r2
        L_0x0041:
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.ReactInstanceManager.createViewManager(java.lang.String):com.facebook.react.uimanager.ViewManager");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0 = r10.mPackages;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0024, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0027, code lost:
        if (r10.mViewManagerNames != null) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0029, code lost:
        r4 = new java.util.HashSet();
        r5 = r10.mPackages.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0038, code lost:
        if (r5.hasNext() == false) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003a, code lost:
        r6 = r5.next();
        com.facebook.systrace.SystraceMessage.beginSection(0, "ReactInstanceManager.getViewManagerName").arg("Package", (java.lang.Object) r6.getClass().getSimpleName()).flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0059, code lost:
        if ((r6 instanceof com.facebook.react.ViewManagerOnDemandReactPackage) == false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005b, code lost:
        r6 = ((com.facebook.react.ViewManagerOnDemandReactPackage) r6).getViewManagerNames(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0061, code lost:
        if (r6 == null) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0063, code lost:
        r4.addAll(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0069, code lost:
        com.facebook.common.logging.FLog.w(com.facebook.react.common.ReactConstants.TAG, "Package %s is not a ViewManagerOnDemandReactPackage, view managers will not be loaded", r6.getClass().getSimpleName());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007c, code lost:
        com.facebook.systrace.Systrace.endSection(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0080, code lost:
        r10.mViewManagerNames = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0082, code lost:
        r3 = r10.mViewManagerNames;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0084, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0085, code lost:
        com.facebook.systrace.Systrace.endSection(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0088, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Collection<java.lang.String> getViewManagerNames() {
        /*
            r10 = this;
            java.lang.String r0 = "ReactInstanceManager.getViewManagerNames"
            r1 = 0
            com.facebook.systrace.Systrace.beginSection(r1, r0)
            java.util.Collection<java.lang.String> r0 = r10.mViewManagerNames     // Catch:{ all -> 0x008b }
            if (r0 == 0) goto L_0x000f
            com.facebook.systrace.Systrace.endSection(r1)
            return r0
        L_0x000f:
            java.lang.Object r0 = r10.mReactContextLock     // Catch:{ all -> 0x008b }
            monitor-enter(r0)     // Catch:{ all -> 0x008b }
            com.facebook.react.bridge.ReactContext r3 = r10.getCurrentReactContext()     // Catch:{ all -> 0x008d }
            com.facebook.react.bridge.ReactApplicationContext r3 = (com.facebook.react.bridge.ReactApplicationContext) r3     // Catch:{ all -> 0x008d }
            if (r3 == 0) goto L_0x008f
            boolean r4 = r3.hasActiveReactInstance()     // Catch:{ all -> 0x008d }
            if (r4 != 0) goto L_0x0021
            goto L_0x008f
        L_0x0021:
            monitor-exit(r0)     // Catch:{ all -> 0x008d }
            java.util.List<com.facebook.react.ReactPackage> r0 = r10.mPackages     // Catch:{ all -> 0x008b }
            monitor-enter(r0)     // Catch:{ all -> 0x008b }
            java.util.Collection<java.lang.String> r4 = r10.mViewManagerNames     // Catch:{ all -> 0x0067 }
            if (r4 != 0) goto L_0x0082
            java.util.HashSet r4 = new java.util.HashSet     // Catch:{ all -> 0x0067 }
            r4.<init>()     // Catch:{ all -> 0x0067 }
            java.util.List<com.facebook.react.ReactPackage> r5 = r10.mPackages     // Catch:{ all -> 0x0067 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0067 }
        L_0x0034:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x0067 }
            if (r6 == 0) goto L_0x0080
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x0067 }
            com.facebook.react.ReactPackage r6 = (com.facebook.react.ReactPackage) r6     // Catch:{ all -> 0x0067 }
            java.lang.String r7 = "ReactInstanceManager.getViewManagerName"
            com.facebook.systrace.SystraceMessage$Builder r7 = com.facebook.systrace.SystraceMessage.beginSection(r1, r7)     // Catch:{ all -> 0x0067 }
            java.lang.String r8 = "Package"
            java.lang.Class r9 = r6.getClass()     // Catch:{ all -> 0x0067 }
            java.lang.String r9 = r9.getSimpleName()     // Catch:{ all -> 0x0067 }
            com.facebook.systrace.SystraceMessage$Builder r7 = r7.arg((java.lang.String) r8, (java.lang.Object) r9)     // Catch:{ all -> 0x0067 }
            r7.flush()     // Catch:{ all -> 0x0067 }
            boolean r7 = r6 instanceof com.facebook.react.ViewManagerOnDemandReactPackage     // Catch:{ all -> 0x0067 }
            if (r7 == 0) goto L_0x0069
            com.facebook.react.ViewManagerOnDemandReactPackage r6 = (com.facebook.react.ViewManagerOnDemandReactPackage) r6     // Catch:{ all -> 0x0067 }
            java.util.Collection r6 = r6.getViewManagerNames(r3)     // Catch:{ all -> 0x0067 }
            if (r6 == 0) goto L_0x007c
            r4.addAll(r6)     // Catch:{ all -> 0x0067 }
            goto L_0x007c
        L_0x0067:
            r3 = move-exception
            goto L_0x0089
        L_0x0069:
            java.lang.String r7 = "ReactNative"
            java.lang.String r8 = "Package %s is not a ViewManagerOnDemandReactPackage, view managers will not be loaded"
            java.lang.Class r6 = r6.getClass()     // Catch:{ all -> 0x0067 }
            java.lang.String r6 = r6.getSimpleName()     // Catch:{ all -> 0x0067 }
            java.lang.Object[] r6 = new java.lang.Object[]{r6}     // Catch:{ all -> 0x0067 }
            com.facebook.common.logging.FLog.w((java.lang.String) r7, (java.lang.String) r8, (java.lang.Object[]) r6)     // Catch:{ all -> 0x0067 }
        L_0x007c:
            com.facebook.systrace.Systrace.endSection(r1)     // Catch:{ all -> 0x0067 }
            goto L_0x0034
        L_0x0080:
            r10.mViewManagerNames = r4     // Catch:{ all -> 0x0067 }
        L_0x0082:
            java.util.Collection<java.lang.String> r3 = r10.mViewManagerNames     // Catch:{ all -> 0x0067 }
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            com.facebook.systrace.Systrace.endSection(r1)
            return r3
        L_0x0089:
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            throw r3     // Catch:{ all -> 0x008b }
        L_0x008b:
            r0 = move-exception
            goto L_0x00a1
        L_0x008d:
            r3 = move-exception
            goto L_0x009f
        L_0x008f:
            java.lang.String r3 = "ReactNative"
            java.lang.String r4 = "Calling getViewManagerNames without active context"
            com.facebook.common.logging.FLog.w((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x008d }
            java.util.List r3 = java.util.Collections.emptyList()     // Catch:{ all -> 0x008d }
            monitor-exit(r0)     // Catch:{ all -> 0x008d }
            com.facebook.systrace.Systrace.endSection(r1)
            return r3
        L_0x009f:
            monitor-exit(r0)     // Catch:{ all -> 0x008d }
            throw r3     // Catch:{ all -> 0x008b }
        L_0x00a1:
            com.facebook.systrace.Systrace.endSection(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.ReactInstanceManager.getViewManagerNames():java.util.Collection");
    }

    public void addReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener) {
        this.mReactInstanceEventListeners.add(reactInstanceEventListener);
    }

    public void removeReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener) {
        this.mReactInstanceEventListeners.remove(reactInstanceEventListener);
    }

    @VisibleForTesting
    public ReactContext getCurrentReactContext() {
        ReactContext reactContext;
        synchronized (this.mReactContextLock) {
            reactContext = this.mCurrentReactContext;
        }
        return reactContext;
    }

    public LifecycleState getLifecycleState() {
        return this.mLifecycleState;
    }

    public String getJsExecutorName() {
        return this.mJavaScriptExecutorFactory.toString();
    }

    public void invalidate() {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.invalidate()");
        this.mInstanceManagerInvalidated = true;
        destroy();
    }

    /* access modifiers changed from: private */
    public void onReloadWithJSDebugger(JavaJSExecutor.Factory factory) {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.onReloadWithJSDebugger()");
        recreateReactContextInBackground(new ProxyJavaScriptExecutor.Factory(factory), JSBundleLoader.createRemoteDebuggerBundleLoader(this.mDevSupportManager.getJSBundleURLForRemoteDebugging(), this.mDevSupportManager.getSourceUrl()));
    }

    /* access modifiers changed from: private */
    public void onJSBundleLoadedFromServer() {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.onJSBundleLoadedFromServer()");
        recreateReactContextInBackground(this.mJavaScriptExecutorFactory, JSBundleLoader.createCachedBundleFromNetworkLoader(this.mDevSupportManager.getSourceUrl(), this.mDevSupportManager.getDownloadedJSBundleFile()));
    }

    private void recreateReactContextInBackground(JavaScriptExecutorFactory javaScriptExecutorFactory, JSBundleLoader jSBundleLoader) {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.recreateReactContextInBackground()");
        UiThreadUtil.assertOnUiThread();
        ReactContextInitParams reactContextInitParams = new ReactContextInitParams(javaScriptExecutorFactory, jSBundleLoader);
        if (this.mCreateReactContextThread == null) {
            runCreateReactContextOnNewThread(reactContextInitParams);
        } else {
            this.mPendingReactContextInitParams = reactContextInitParams;
        }
    }

    private void runCreateReactContextOnNewThread(ReactContextInitParams reactContextInitParams) {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.runCreateReactContextOnNewThread()");
        UiThreadUtil.assertOnUiThread();
        Assertions.assertCondition(!this.mInstanceManagerInvalidated, "Cannot create a new React context on an invalidated ReactInstanceManager");
        ReactMarker.logMarker(ReactMarkerConstants.REACT_BRIDGE_LOADING_START);
        synchronized (this.mAttachedReactRoots) {
            synchronized (this.mReactContextLock) {
                try {
                    if (this.mCurrentReactContext != null) {
                        tearDownReactContext(this.mCurrentReactContext);
                        this.mCurrentReactContext = null;
                    }
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
        }
        this.mCreateReactContextThread = new Thread((ThreadGroup) null, new ReactInstanceManager$$ExternalSyntheticLambda1(this, reactContextInitParams), "create_react_context");
        ReactMarker.logMarker(ReactMarkerConstants.REACT_CONTEXT_THREAD_START);
        this.mCreateReactContextThread.start();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0008 */
    /* JADX WARNING: Removed duplicated region for block: B:2:0x0008 A[LOOP:0: B:2:0x0008->B:23:0x0008, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$runCreateReactContextOnNewThread$2(com.facebook.react.ReactInstanceManager.ReactContextInitParams r3) {
        /*
            r2 = this;
            com.facebook.react.bridge.ReactMarkerConstants r0 = com.facebook.react.bridge.ReactMarkerConstants.REACT_CONTEXT_THREAD_END
            com.facebook.react.bridge.ReactMarker.logMarker((com.facebook.react.bridge.ReactMarkerConstants) r0)
            java.lang.Boolean r0 = r2.mHasStartedDestroying
            monitor-enter(r0)
        L_0x0008:
            java.lang.Boolean r1 = r2.mHasStartedDestroying     // Catch:{ all -> 0x0016 }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x0016 }
            if (r1 == 0) goto L_0x0018
            java.lang.Boolean r1 = r2.mHasStartedDestroying     // Catch:{ InterruptedException -> 0x0008 }
            r1.wait()     // Catch:{ InterruptedException -> 0x0008 }
            goto L_0x0008
        L_0x0016:
            r3 = move-exception
            goto L_0x0061
        L_0x0018:
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            r0 = 1
            r2.mHasStartedCreatingInitialContext = r0
            r0 = -4
            r1 = 0
            android.os.Process.setThreadPriority(r0)     // Catch:{ Exception -> 0x0055 }
            com.facebook.react.bridge.ReactMarkerConstants r0 = com.facebook.react.bridge.ReactMarkerConstants.VM_INIT     // Catch:{ Exception -> 0x0055 }
            com.facebook.react.bridge.ReactMarker.logMarker((com.facebook.react.bridge.ReactMarkerConstants) r0)     // Catch:{ Exception -> 0x0055 }
            com.facebook.react.bridge.JavaScriptExecutorFactory r0 = r3.getJsExecutorFactory()     // Catch:{ Exception -> 0x0055 }
            com.facebook.react.bridge.JavaScriptExecutor r0 = r0.create()     // Catch:{ Exception -> 0x0055 }
            com.facebook.react.bridge.JSBundleLoader r3 = r3.getJsBundleLoader()     // Catch:{ Exception -> 0x0055 }
            com.facebook.react.bridge.ReactApplicationContext r3 = r2.createReactContext(r0, r3)     // Catch:{ Exception -> 0x0055 }
            r2.mCreateReactContextThread = r1     // Catch:{ Exception -> 0x004e }
            com.facebook.react.bridge.ReactMarkerConstants r0 = com.facebook.react.bridge.ReactMarkerConstants.PRE_SETUP_REACT_CONTEXT_START     // Catch:{ Exception -> 0x004e }
            com.facebook.react.bridge.ReactMarker.logMarker((com.facebook.react.bridge.ReactMarkerConstants) r0)     // Catch:{ Exception -> 0x004e }
            com.facebook.react.ReactInstanceManager$$ExternalSyntheticLambda2 r0 = new com.facebook.react.ReactInstanceManager$$ExternalSyntheticLambda2     // Catch:{ Exception -> 0x004e }
            r0.<init>(r2)     // Catch:{ Exception -> 0x004e }
            com.facebook.react.ReactInstanceManager$$ExternalSyntheticLambda3 r1 = new com.facebook.react.ReactInstanceManager$$ExternalSyntheticLambda3     // Catch:{ Exception -> 0x004e }
            r1.<init>(r2, r3)     // Catch:{ Exception -> 0x004e }
            r3.runOnNativeModulesQueueThread(r1)     // Catch:{ Exception -> 0x004e }
            com.facebook.react.bridge.UiThreadUtil.runOnUiThread(r0)     // Catch:{ Exception -> 0x004e }
            goto L_0x0054
        L_0x004e:
            r3 = move-exception
            com.facebook.react.devsupport.interfaces.DevSupportManager r0 = r2.mDevSupportManager
            r0.handleException(r3)
        L_0x0054:
            return
        L_0x0055:
            r3 = move-exception
            r0 = 0
            r2.mHasStartedCreatingInitialContext = r0
            r2.mCreateReactContextThread = r1
            com.facebook.react.devsupport.interfaces.DevSupportManager r0 = r2.mDevSupportManager
            r0.handleException(r3)
            return
        L_0x0061:
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.ReactInstanceManager.lambda$runCreateReactContextOnNewThread$2(com.facebook.react.ReactInstanceManager$ReactContextInitParams):void");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$runCreateReactContextOnNewThread$0() {
        ReactContextInitParams reactContextInitParams = this.mPendingReactContextInitParams;
        if (reactContextInitParams != null) {
            runCreateReactContextOnNewThread(reactContextInitParams);
            this.mPendingReactContextInitParams = null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$runCreateReactContextOnNewThread$1(ReactApplicationContext reactApplicationContext) {
        try {
            setupReactContext(reactApplicationContext);
        } catch (Exception e) {
            this.mDevSupportManager.handleException(e);
        }
    }

    private void setupReactContext(ReactApplicationContext reactApplicationContext) {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.setupReactContext()");
        ReactMarker.logMarker(ReactMarkerConstants.PRE_SETUP_REACT_CONTEXT_END);
        ReactMarker.logMarker(ReactMarkerConstants.SETUP_REACT_CONTEXT_START);
        Systrace.beginSection(0, "setupReactContext");
        synchronized (this.mAttachedReactRoots) {
            try {
                synchronized (this.mReactContextLock) {
                    this.mCurrentReactContext = (ReactContext) Assertions.assertNotNull(reactApplicationContext);
                }
                CatalystInstance catalystInstance = (CatalystInstance) Assertions.assertNotNull(reactApplicationContext.getCatalystInstance());
                catalystInstance.initialize();
                this.mDevSupportManager.onNewReactContextCreated(reactApplicationContext);
                this.mMemoryPressureRouter.addMemoryPressureListener(catalystInstance);
                ReactMarker.logMarker(ReactMarkerConstants.ATTACH_MEASURED_ROOT_VIEWS_START);
                for (ReactRoot attachRootViewToInstance : this.mAttachedReactRoots) {
                    attachRootViewToInstance(attachRootViewToInstance);
                }
                ReactMarker.logMarker(ReactMarkerConstants.ATTACH_MEASURED_ROOT_VIEWS_END);
            } catch (Throwable th) {
                throw th;
            }
        }
        UiThreadUtil.runOnUiThread(new ReactInstanceManager$$ExternalSyntheticLambda4(this, (ReactInstanceEventListener[]) this.mReactInstanceEventListeners.toArray(new ReactInstanceEventListener[this.mReactInstanceEventListeners.size()]), reactApplicationContext));
        reactApplicationContext.runOnJSQueueThread(new ReactInstanceManager$$ExternalSyntheticLambda5());
        reactApplicationContext.runOnNativeModulesQueueThread(new ReactInstanceManager$$ExternalSyntheticLambda6());
        Systrace.endSection(0);
        ReactMarker.logMarker(ReactMarkerConstants.SETUP_REACT_CONTEXT_END);
        ReactMarker.logMarker(ReactMarkerConstants.REACT_BRIDGE_LOADING_END);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setupReactContext$3(ReactInstanceEventListener[] reactInstanceEventListenerArr, ReactApplicationContext reactApplicationContext) {
        moveReactContextToCurrentLifecycleState();
        for (ReactInstanceEventListener reactInstanceEventListener : reactInstanceEventListenerArr) {
            if (reactInstanceEventListener != null) {
                reactInstanceEventListener.onReactContextInitialized(reactApplicationContext);
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setupReactContext$4() {
        Process.setThreadPriority(0);
        ReactMarker.logMarker(ReactMarkerConstants.CHANGE_THREAD_PRIORITY, "js_default");
    }

    private void attachRootViewToInstance(ReactRoot reactRoot) {
        int i;
        WritableMap writableMap;
        WritableMap fromBundle;
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.attachRootViewToInstance()");
        if (reactRoot.getState().compareAndSet(0, 1)) {
            Systrace.beginSection(0, "attachRootViewToInstance");
            UIManager uIManager = UIManagerHelper.getUIManager(this.mCurrentReactContext, reactRoot.getUIManagerType());
            if (uIManager != null) {
                Bundle appProperties = reactRoot.getAppProperties();
                if (reactRoot.getUIManagerType() == 2) {
                    ViewGroup rootViewGroup = reactRoot.getRootViewGroup();
                    String jSModuleName = reactRoot.getJSModuleName();
                    if (appProperties == null) {
                        fromBundle = new WritableNativeMap();
                    } else {
                        fromBundle = Arguments.fromBundle(appProperties);
                    }
                    i = uIManager.startSurface(rootViewGroup, jSModuleName, fromBundle, reactRoot.getWidthMeasureSpec(), reactRoot.getHeightMeasureSpec());
                    reactRoot.setShouldLogContentAppeared(true);
                } else {
                    ViewGroup rootViewGroup2 = reactRoot.getRootViewGroup();
                    if (appProperties == null) {
                        writableMap = new WritableNativeMap();
                    } else {
                        writableMap = Arguments.fromBundle(appProperties);
                    }
                    i = uIManager.addRootView(rootViewGroup2, writableMap);
                    reactRoot.setRootViewTag(i);
                    reactRoot.runApplication();
                }
                Systrace.beginAsyncSection(0, "pre_rootView.onAttachedToReactInstance", i);
                UiThreadUtil.runOnUiThread(new ReactInstanceManager$$ExternalSyntheticLambda0(i, reactRoot));
                Systrace.endSection(0);
                return;
            }
            throw new IllegalStateException("Unable to attach a rootView to ReactInstance when UIManager is not properly initialized.");
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$attachRootViewToInstance$6(int i, ReactRoot reactRoot) {
        Systrace.endAsyncSection(0, "pre_rootView.onAttachedToReactInstance", i);
        reactRoot.onStage(101);
    }

    private void detachRootViewFromInstance(ReactRoot reactRoot, ReactContext reactContext) {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.detachRootViewFromInstance()");
        UiThreadUtil.assertOnUiThread();
        if (reactRoot.getState().compareAndSet(1, 0)) {
            int uIManagerType = reactRoot.getUIManagerType();
            if (uIManagerType == 2) {
                int rootViewTag = reactRoot.getRootViewTag();
                if (rootViewTag != -1) {
                    UIManager uIManager = UIManagerHelper.getUIManager(reactContext, uIManagerType);
                    if (uIManager != null) {
                        uIManager.stopSurface(rootViewTag);
                    } else {
                        FLog.w(ReactConstants.TAG, "Failed to stop surface, UIManager has already gone away");
                    }
                } else {
                    ReactSoftExceptionLogger.logSoftException(TAG, new RuntimeException("detachRootViewFromInstance called with ReactRootView with invalid id"));
                }
                clearReactRoot(reactRoot);
                return;
            }
            ((AppRegistry) reactContext.getCatalystInstance().getJSModule(AppRegistry.class)).unmountApplicationComponentAtRootTag(reactRoot.getRootViewTag());
        }
    }

    private void tearDownReactContext(ReactContext reactContext) {
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.tearDownReactContext()");
        UiThreadUtil.assertOnUiThread();
        if (this.mLifecycleState == LifecycleState.RESUMED) {
            reactContext.onHostPause();
        }
        synchronized (this.mAttachedReactRoots) {
            try {
                for (ReactRoot detachRootViewFromInstance : this.mAttachedReactRoots) {
                    detachRootViewFromInstance(detachRootViewFromInstance, reactContext);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.mMemoryPressureRouter.removeMemoryPressureListener(reactContext.getCatalystInstance());
        reactContext.destroy();
        this.mDevSupportManager.onReactInstanceDestroyed(reactContext);
    }

    /* JADX INFO: finally extract failed */
    private ReactApplicationContext createReactContext(JavaScriptExecutor javaScriptExecutor, JSBundleLoader jSBundleLoader) {
        UIManager createUIManager;
        ReactPackageTurboModuleManagerDelegate.Builder builder;
        FLog.d(ReactConstants.TAG, "ReactInstanceManager.createReactContext()");
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_REACT_CONTEXT_START, javaScriptExecutor.getName());
        BridgeReactContext bridgeReactContext = new BridgeReactContext(this.mApplicationContext);
        JSExceptionHandler jSExceptionHandler = this.mJSExceptionHandler;
        if (jSExceptionHandler == null) {
            jSExceptionHandler = this.mDevSupportManager;
        }
        bridgeReactContext.setJSExceptionHandler(jSExceptionHandler);
        CatalystInstanceImpl.Builder inspectorTarget = new CatalystInstanceImpl.Builder().setReactQueueConfigurationSpec(ReactQueueConfigurationSpec.createDefault()).setJSExecutor(javaScriptExecutor).setRegistry(processPackages(bridgeReactContext, this.mPackages)).setJSBundleLoader(jSBundleLoader).setJSExceptionHandler(jSExceptionHandler).setInspectorTarget(getOrCreateInspectorTarget());
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_START);
        Systrace.beginSection(0, "createCatalystInstance");
        try {
            CatalystInstanceImpl build = inspectorTarget.build();
            Systrace.endSection(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_END);
            bridgeReactContext.initializeWithInstance(build);
            build.getRuntimeScheduler();
            if (ReactNativeFeatureFlags.useTurboModules() && (builder = this.mTMMDelegateBuilder) != null) {
                TurboModuleManager turboModuleManager = new TurboModuleManager(build.getRuntimeExecutor(), builder.setPackages(this.mPackages).setReactApplicationContext(bridgeReactContext).build(), build.getJSCallInvokerHolder(), build.getNativeMethodCallInvokerHolder());
                build.setTurboModuleRegistry(turboModuleManager);
                for (String module : turboModuleManager.getEagerInitModuleNames()) {
                    turboModuleManager.getModule(module);
                }
            }
            UIManagerProvider uIManagerProvider = this.mUIManagerProvider;
            if (!(uIManagerProvider == null || (createUIManager = uIManagerProvider.createUIManager(bridgeReactContext)) == null)) {
                build.setFabricUIManager(createUIManager);
                createUIManager.initialize();
                build.setFabricUIManager(createUIManager);
            }
            NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener = this.mBridgeIdleDebugListener;
            if (notThreadSafeBridgeIdleDebugListener != null) {
                build.addBridgeIdleDebugListener(notThreadSafeBridgeIdleDebugListener);
            }
            if (Systrace.isTracing(0)) {
                build.setGlobalVariable("__RCTProfileIsProfiling", "true");
            }
            ReactMarker.logMarker(ReactMarkerConstants.PRE_RUN_JS_BUNDLE_START);
            Systrace.beginSection(0, "runJSBundle");
            build.runJSBundle();
            Systrace.endSection(0);
            return bridgeReactContext;
        } catch (Throwable th) {
            Systrace.endSection(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_END);
            throw th;
        }
    }

    private NativeModuleRegistry processPackages(ReactApplicationContext reactApplicationContext, List<ReactPackage> list) {
        NativeModuleRegistryBuilder nativeModuleRegistryBuilder = new NativeModuleRegistryBuilder(reactApplicationContext);
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_PACKAGES_START);
        synchronized (this.mPackages) {
            try {
                for (ReactPackage next : list) {
                    Systrace.beginSection(0, "createAndProcessCustomReactPackage");
                    processPackage(next, nativeModuleRegistryBuilder);
                    Systrace.endSection(0);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_PACKAGES_END);
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_START);
        Systrace.beginSection(0, "buildNativeModuleRegistry");
        try {
            return nativeModuleRegistryBuilder.build();
        } finally {
            Systrace.endSection(0);
            ReactMarker.logMarker(ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_END);
        }
    }

    private void processPackage(ReactPackage reactPackage, NativeModuleRegistryBuilder nativeModuleRegistryBuilder) {
        SystraceMessage.beginSection(0, "processPackage").arg("className", (Object) reactPackage.getClass().getSimpleName()).flush();
        boolean z = reactPackage instanceof ReactPackageLogger;
        if (z) {
            ((ReactPackageLogger) reactPackage).startProcessPackage();
        }
        nativeModuleRegistryBuilder.processPackage(reactPackage);
        if (z) {
            ((ReactPackageLogger) reactPackage).endProcessPackage();
        }
        SystraceMessage.endSection(0).flush();
    }

    private static class InspectorTargetDelegateImpl implements ReactInstanceManagerInspectorTarget.TargetDelegate {
        private WeakReference<ReactInstanceManager> mReactInstanceManagerWeak;

        public InspectorTargetDelegateImpl(ReactInstanceManager reactInstanceManager) {
            this.mReactInstanceManagerWeak = new WeakReference<>(reactInstanceManager);
        }

        public Map<String, String> getMetadata() {
            ReactInstanceManager reactInstanceManager = this.mReactInstanceManagerWeak.get();
            return AndroidInfoHelpers.getInspectorHostMetadata(reactInstanceManager != null ? reactInstanceManager.mApplicationContext : null);
        }

        public void onReload() {
            UiThreadUtil.runOnUiThread(new ReactInstanceManager$InspectorTargetDelegateImpl$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onReload$0() {
            ReactInstanceManager reactInstanceManager = this.mReactInstanceManagerWeak.get();
            if (reactInstanceManager != null) {
                reactInstanceManager.mDevSupportManager.handleReloadJS();
            }
        }

        public void onSetPausedInDebuggerMessage(String str) {
            final ReactInstanceManager reactInstanceManager = this.mReactInstanceManagerWeak.get();
            if (reactInstanceManager != null) {
                if (str == null) {
                    reactInstanceManager.mDevSupportManager.hidePausedInDebuggerOverlay();
                } else {
                    reactInstanceManager.mDevSupportManager.showPausedInDebuggerOverlay(str, new DevSupportManager.PausedInDebuggerOverlayCommandListener() {
                        public void onResume() {
                            UiThreadUtil.assertOnUiThread();
                            if (reactInstanceManager.mInspectorTarget != null) {
                                reactInstanceManager.mInspectorTarget.sendDebuggerResumeCommand();
                            }
                        }
                    });
                }
            }
        }

        public void loadNetworkResource(String str, InspectorNetworkRequestListener inspectorNetworkRequestListener) {
            InspectorNetworkHelper.loadNetworkResource(str, inspectorNetworkRequestListener);
        }
    }

    private ReactInstanceManagerInspectorTarget getOrCreateInspectorTarget() {
        if (this.mInspectorTarget == null && InspectorFlags.getFuseboxEnabled()) {
            this.mInspectorTarget = new ReactInstanceManagerInspectorTarget(new InspectorTargetDelegateImpl(this));
        }
        return this.mInspectorTarget;
    }
}
