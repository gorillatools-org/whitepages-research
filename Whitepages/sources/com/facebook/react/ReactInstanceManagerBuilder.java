package com.facebook.react;

import android.app.Activity;
import android.app.Application;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSExceptionHandler;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.UIManagerProvider;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.DefaultDevSupportManagerFactory;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import com.facebook.react.devsupport.interfaces.PausedInDebuggerOverlayManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.internal.ChoreographerProvider;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.facebook.react.packagerconnection.RequestHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReactInstanceManagerBuilder {
    private static final String TAG = "ReactInstanceManagerBuilder";
    private Application mApplication;
    private NotThreadSafeBridgeIdleDebugListener mBridgeIdleDebugListener;
    private ChoreographerProvider mChoreographerProvider = null;
    private Activity mCurrentActivity;
    private Map<String, RequestHandler> mCustomPackagerCommandHandlers;
    private DefaultHardwareBackBtnHandler mDefaultHardwareBackBtnHandler;
    private DevBundleDownloadListener mDevBundleDownloadListener;
    private DevLoadingViewManager mDevLoadingViewManager;
    private DevSupportManagerFactory mDevSupportManagerFactory;
    private LifecycleState mInitialLifecycleState;
    private String mJSBundleAssetUrl;
    private JSBundleLoader mJSBundleLoader;
    private JSEngineResolutionAlgorithm mJSEngineResolutionAlgorithm = null;
    private JSExceptionHandler mJSExceptionHandler;
    private String mJSMainModulePath;
    private JavaScriptExecutorFactory mJavaScriptExecutorFactory;
    private boolean mKeepActivity;
    private boolean mLazyViewManagersEnabled;
    private int mMinNumShakes = 1;
    private int mMinTimeLeftInFrameForNonBatchedOperationMs = -1;
    private final List<ReactPackage> mPackages = new ArrayList();
    private PausedInDebuggerOverlayManager mPausedInDebuggerOverlayManager = null;
    private RedBoxHandler mRedBoxHandler;
    private boolean mRequireActivity;
    private SurfaceDelegateFactory mSurfaceDelegateFactory;
    private ReactPackageTurboModuleManagerDelegate.Builder mTMMDelegateBuilder;
    private UIManagerProvider mUIManagerProvider;
    private boolean mUseDeveloperSupport;

    ReactInstanceManagerBuilder() {
    }

    public ReactInstanceManagerBuilder setJavaScriptExecutorFactory(JavaScriptExecutorFactory javaScriptExecutorFactory) {
        this.mJavaScriptExecutorFactory = javaScriptExecutorFactory;
        return this;
    }

    public ReactInstanceManagerBuilder setUIManagerProvider(UIManagerProvider uIManagerProvider) {
        this.mUIManagerProvider = uIManagerProvider;
        return this;
    }

    public ReactInstanceManagerBuilder setBundleAssetName(String str) {
        String str2;
        if (str == null) {
            str2 = null;
        } else {
            str2 = "assets://" + str;
        }
        this.mJSBundleAssetUrl = str2;
        this.mJSBundleLoader = null;
        return this;
    }

    public ReactInstanceManagerBuilder setJSBundleFile(String str) {
        if (!str.startsWith("assets://")) {
            return setJSBundleLoader(JSBundleLoader.createFileLoader(str));
        }
        this.mJSBundleAssetUrl = str;
        this.mJSBundleLoader = null;
        return this;
    }

    public ReactInstanceManagerBuilder setJSBundleLoader(JSBundleLoader jSBundleLoader) {
        this.mJSBundleLoader = jSBundleLoader;
        this.mJSBundleAssetUrl = null;
        return this;
    }

    public ReactInstanceManagerBuilder setJSEngineResolutionAlgorithm(JSEngineResolutionAlgorithm jSEngineResolutionAlgorithm) {
        this.mJSEngineResolutionAlgorithm = jSEngineResolutionAlgorithm;
        return this;
    }

    public ReactInstanceManagerBuilder setJSMainModulePath(String str) {
        this.mJSMainModulePath = str;
        return this;
    }

    public ReactInstanceManagerBuilder addPackage(ReactPackage reactPackage) {
        this.mPackages.add(reactPackage);
        return this;
    }

    public ReactInstanceManagerBuilder addPackages(List<ReactPackage> list) {
        this.mPackages.addAll(list);
        return this;
    }

    public ReactInstanceManagerBuilder setBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener) {
        this.mBridgeIdleDebugListener = notThreadSafeBridgeIdleDebugListener;
        return this;
    }

    public ReactInstanceManagerBuilder setApplication(Application application) {
        this.mApplication = application;
        return this;
    }

    public ReactInstanceManagerBuilder setCurrentActivity(Activity activity) {
        this.mCurrentActivity = activity;
        return this;
    }

    public ReactInstanceManagerBuilder setDefaultHardwareBackBtnHandler(DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        this.mDefaultHardwareBackBtnHandler = defaultHardwareBackBtnHandler;
        return this;
    }

    public ReactInstanceManagerBuilder setUseDeveloperSupport(boolean z) {
        this.mUseDeveloperSupport = z;
        return this;
    }

    public ReactInstanceManagerBuilder setDevSupportManagerFactory(DevSupportManagerFactory devSupportManagerFactory) {
        this.mDevSupportManagerFactory = devSupportManagerFactory;
        return this;
    }

    public ReactInstanceManagerBuilder setRequireActivity(boolean z) {
        this.mRequireActivity = z;
        return this;
    }

    public ReactInstanceManagerBuilder setKeepActivity(boolean z) {
        this.mKeepActivity = z;
        return this;
    }

    public ReactInstanceManagerBuilder setSurfaceDelegateFactory(SurfaceDelegateFactory surfaceDelegateFactory) {
        this.mSurfaceDelegateFactory = surfaceDelegateFactory;
        return this;
    }

    public ReactInstanceManagerBuilder setDevLoadingViewManager(DevLoadingViewManager devLoadingViewManager) {
        this.mDevLoadingViewManager = devLoadingViewManager;
        return this;
    }

    public ReactInstanceManagerBuilder setPausedInDebuggerOverlayManager(PausedInDebuggerOverlayManager pausedInDebuggerOverlayManager) {
        this.mPausedInDebuggerOverlayManager = pausedInDebuggerOverlayManager;
        return this;
    }

    public ReactInstanceManagerBuilder setInitialLifecycleState(LifecycleState lifecycleState) {
        this.mInitialLifecycleState = lifecycleState;
        return this;
    }

    public ReactInstanceManagerBuilder setJSExceptionHandler(JSExceptionHandler jSExceptionHandler) {
        this.mJSExceptionHandler = jSExceptionHandler;
        return this;
    }

    public ReactInstanceManagerBuilder setRedBoxHandler(RedBoxHandler redBoxHandler) {
        this.mRedBoxHandler = redBoxHandler;
        return this;
    }

    public ReactInstanceManagerBuilder setLazyViewManagersEnabled(boolean z) {
        this.mLazyViewManagersEnabled = z;
        return this;
    }

    public ReactInstanceManagerBuilder setDevBundleDownloadListener(DevBundleDownloadListener devBundleDownloadListener) {
        this.mDevBundleDownloadListener = devBundleDownloadListener;
        return this;
    }

    public ReactInstanceManagerBuilder setMinNumShakes(int i) {
        this.mMinNumShakes = i;
        return this;
    }

    public ReactInstanceManagerBuilder setMinTimeLeftInFrameForNonBatchedOperationMs(int i) {
        this.mMinTimeLeftInFrameForNonBatchedOperationMs = i;
        return this;
    }

    public ReactInstanceManagerBuilder setCustomPackagerCommandHandlers(Map<String, RequestHandler> map) {
        this.mCustomPackagerCommandHandlers = map;
        return this;
    }

    public ReactInstanceManagerBuilder setReactPackageTurboModuleManagerDelegateBuilder(ReactPackageTurboModuleManagerDelegate.Builder builder) {
        this.mTMMDelegateBuilder = builder;
        return this;
    }

    public ReactInstanceManagerBuilder setChoreographerProvider(ChoreographerProvider choreographerProvider) {
        this.mChoreographerProvider = choreographerProvider;
        return this;
    }

    public ReactInstanceManager build() {
        String str;
        Assertions.assertNotNull(this.mApplication, "Application property has not been set with this builder");
        if (this.mInitialLifecycleState == LifecycleState.RESUMED) {
            Assertions.assertNotNull(this.mCurrentActivity, "Activity needs to be set if initial lifecycle state is resumed");
        }
        boolean z = true;
        Assertions.assertCondition((!this.mUseDeveloperSupport && this.mJSBundleAssetUrl == null && this.mJSBundleLoader == null) ? false : true, "JS Bundle File or Asset URL has to be provided when dev support is disabled");
        if (this.mJSMainModulePath == null && this.mJSBundleAssetUrl == null && this.mJSBundleLoader == null) {
            z = false;
        }
        Assertions.assertCondition(z, "Either MainModulePath or JS Bundle File needs to be provided");
        String packageName = this.mApplication.getPackageName();
        String friendlyDeviceName = AndroidInfoHelpers.getFriendlyDeviceName();
        Application application = this.mApplication;
        Activity activity = this.mCurrentActivity;
        DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler = this.mDefaultHardwareBackBtnHandler;
        JavaScriptExecutorFactory javaScriptExecutorFactory = this.mJavaScriptExecutorFactory;
        JavaScriptExecutorFactory defaultJSExecutorFactory = javaScriptExecutorFactory == null ? getDefaultJSExecutorFactory(packageName, friendlyDeviceName, application.getApplicationContext()) : javaScriptExecutorFactory;
        JSBundleLoader jSBundleLoader = this.mJSBundleLoader;
        if (jSBundleLoader == null && (str = this.mJSBundleAssetUrl) != null) {
            jSBundleLoader = JSBundleLoader.createAssetLoader(this.mApplication, str, false);
        }
        JSBundleLoader jSBundleLoader2 = jSBundleLoader;
        String str2 = this.mJSMainModulePath;
        List<ReactPackage> list = this.mPackages;
        boolean z2 = this.mUseDeveloperSupport;
        DevSupportManagerFactory devSupportManagerFactory = this.mDevSupportManagerFactory;
        if (devSupportManagerFactory == null) {
            devSupportManagerFactory = new DefaultDevSupportManagerFactory();
        }
        return new ReactInstanceManager(application, activity, defaultHardwareBackBtnHandler, defaultJSExecutorFactory, jSBundleLoader2, str2, list, z2, devSupportManagerFactory, this.mRequireActivity, this.mKeepActivity, this.mBridgeIdleDebugListener, (LifecycleState) Assertions.assertNotNull(this.mInitialLifecycleState, "Initial lifecycle state was not set"), this.mJSExceptionHandler, this.mRedBoxHandler, this.mLazyViewManagersEnabled, this.mDevBundleDownloadListener, this.mMinNumShakes, this.mMinTimeLeftInFrameForNonBatchedOperationMs, this.mUIManagerProvider, this.mCustomPackagerCommandHandlers, this.mTMMDelegateBuilder, this.mSurfaceDelegateFactory, this.mDevLoadingViewManager, this.mChoreographerProvider, this.mPausedInDebuggerOverlayManager);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:5|6|7) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        if (r2.getMessage().contains("__cxa_bad_typeid") == false) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
        com.facebook.react.jscexecutor.JSCExecutor.loadLibrary();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
        return new com.facebook.react.jscexecutor.JSCExecutorFactory(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        com.facebook.common.logging.FLog.e(TAG, "Unable to load neither the Hermes nor the JSC native library. Your application is not built correctly and will fail to execute");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0010 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.react.bridge.JavaScriptExecutorFactory getDefaultJSExecutorFactory(java.lang.String r2, java.lang.String r3, android.content.Context r4) {
        /*
            r1 = this;
            com.facebook.react.ReactInstanceManager.initializeSoLoaderIfNecessary(r4)
            com.facebook.react.JSEngineResolutionAlgorithm r4 = r1.mJSEngineResolutionAlgorithm
            if (r4 != 0) goto L_0x0030
            com.facebook.hermes.reactexecutor.HermesExecutor.loadLibrary()     // Catch:{ UnsatisfiedLinkError -> 0x0010 }
            com.facebook.hermes.reactexecutor.HermesExecutorFactory r4 = new com.facebook.hermes.reactexecutor.HermesExecutorFactory     // Catch:{ UnsatisfiedLinkError -> 0x0010 }
            r4.<init>()     // Catch:{ UnsatisfiedLinkError -> 0x0010 }
            return r4
        L_0x0010:
            com.facebook.react.jscexecutor.JSCExecutor.loadLibrary()     // Catch:{ UnsatisfiedLinkError -> 0x0019 }
            com.facebook.react.jscexecutor.JSCExecutorFactory r4 = new com.facebook.react.jscexecutor.JSCExecutorFactory     // Catch:{ UnsatisfiedLinkError -> 0x0019 }
            r4.<init>(r2, r3)     // Catch:{ UnsatisfiedLinkError -> 0x0019 }
            return r4
        L_0x0019:
            r2 = move-exception
            java.lang.String r3 = TAG
            java.lang.String r4 = "Unable to load neither the Hermes nor the JSC native library. Your application is not built correctly and will fail to execute"
            com.facebook.common.logging.FLog.e((java.lang.String) r3, (java.lang.String) r4)
            java.lang.String r3 = r2.getMessage()
            java.lang.String r4 = "__cxa_bad_typeid"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x002f
            r2 = 0
            return r2
        L_0x002f:
            throw r2
        L_0x0030:
            com.facebook.react.JSEngineResolutionAlgorithm r0 = com.facebook.react.JSEngineResolutionAlgorithm.HERMES
            if (r4 != r0) goto L_0x003d
            com.facebook.hermes.reactexecutor.HermesExecutor.loadLibrary()
            com.facebook.hermes.reactexecutor.HermesExecutorFactory r2 = new com.facebook.hermes.reactexecutor.HermesExecutorFactory
            r2.<init>()
            return r2
        L_0x003d:
            com.facebook.react.jscexecutor.JSCExecutor.loadLibrary()
            com.facebook.react.jscexecutor.JSCExecutorFactory r4 = new com.facebook.react.jscexecutor.JSCExecutorFactory
            r4.<init>(r2, r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.ReactInstanceManagerBuilder.getDefaultJSExecutorFactory(java.lang.String, java.lang.String, android.content.Context):com.facebook.react.bridge.JavaScriptExecutorFactory");
    }
}
