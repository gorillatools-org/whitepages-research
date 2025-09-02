package com.facebook.react.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.MemoryPressureRouter;
import com.facebook.react.ReactHost;
import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.MemoryPressureListener;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactNoCrashBridgeNotAllowedSoftException;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.devsupport.DefaultDevSupportManagerFactory;
import com.facebook.react.devsupport.DevSupportManagerBase;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.devsupport.InspectorFlags;
import com.facebook.react.devsupport.inspector.InspectorNetworkHelper;
import com.facebook.react.devsupport.inspector.InspectorNetworkRequestListener;
import com.facebook.react.devsupport.interfaces.BundleLoadCallback;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.PausedInDebuggerOverlayManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.interfaces.TaskInterface;
import com.facebook.react.interfaces.fabric.ReactSurface;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.modules.appearance.AppearanceModule;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.facebook.react.packagerconnection.RequestHandler;
import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;
import com.facebook.react.runtime.internal.bolts.TaskCompletionSource;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.BlackHoleEventDispatcher;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@DoNotStrip
public class ReactHostImpl implements ReactHost {
    private static final int BRIDGELESS_MARKER_INSTANCE_KEY = 1;
    private static final String TAG = "ReactHost";
    private static final AtomicInteger mCounter = new AtomicInteger(0);
    private final AtomicReference<Activity> mActivity;
    private final boolean mAllowPackagerServerAccess;
    private final Set<ReactSurfaceImpl> mAttachedSurfaces;
    private final Executor mBGExecutor;
    private final List<Function0> mBeforeDestroyListeners;
    private final BridgelessAtomicRef<BridgelessReactContext> mBridgelessReactContextRef;
    private final BridgelessReactStateTracker mBridgelessReactStateTracker;
    private final ComponentFactory mComponentFactory;
    private final Context mContext;
    private final BridgelessAtomicRef<Task<ReactInstance>> mCreateReactInstanceTaskRef;
    private DefaultHardwareBackBtnHandler mDefaultHardwareBackBtnHandler;
    private Task<Void> mDestroyTask;
    private DevSupportManager mDevSupportManager;
    private volatile boolean mHostInvalidated;
    private final int mId;
    private final AtomicReference<WeakReference<Activity>> mLastUsedActivity;
    private MemoryPressureListener mMemoryPressureListener;
    private final MemoryPressureRouter mMemoryPressureRouter;
    private final ReactHostDelegate mReactHostDelegate;
    /* access modifiers changed from: private */
    public ReactHostInspectorTarget mReactHostInspectorTarget;
    private ReactInstance mReactInstance;
    private final List<ReactInstanceEventListener> mReactInstanceEventListeners;
    private final ReactLifecycleStateManager mReactLifecycleStateManager;
    private Task<ReactInstance> mReloadTask;
    private Task<Void> mStartTask;
    private final Executor mUIExecutor;
    private final boolean mUseDevSupport;

    private interface ReactInstanceCalback {
        void then(ReactInstance reactInstance);
    }

    private interface ReactInstanceTaskUnwrapper {
        ReactInstance unwrap(Task<ReactInstance> task, String str);
    }

    public ReactHostImpl(Context context, ReactHostDelegate reactHostDelegate, ComponentFactory componentFactory, boolean z, boolean z2) {
        this(context, reactHostDelegate, componentFactory, Executors.newSingleThreadExecutor(), Task.UI_THREAD_EXECUTOR, z, z2);
    }

    public ReactHostImpl(Context context, ReactHostDelegate reactHostDelegate, ComponentFactory componentFactory, Executor executor, Executor executor2, boolean z, boolean z2) {
        this(context, reactHostDelegate, componentFactory, executor, executor2, z, z2, (DevSupportManagerFactory) null);
    }

    public ReactHostImpl(Context context, ReactHostDelegate reactHostDelegate, ComponentFactory componentFactory, Executor executor, Executor executor2, boolean z, boolean z2, DevSupportManagerFactory devSupportManagerFactory) {
        Context context2 = context;
        this.mAttachedSurfaces = new HashSet();
        this.mCreateReactInstanceTaskRef = new BridgelessAtomicRef<>(Task.forResult(null));
        this.mBridgelessReactContextRef = new BridgelessAtomicRef<>();
        this.mActivity = new AtomicReference<>();
        this.mLastUsedActivity = new AtomicReference<>(new WeakReference((Object) null));
        BridgelessReactStateTracker bridgelessReactStateTracker = new BridgelessReactStateTracker(ReactBuildConfig.DEBUG);
        this.mBridgelessReactStateTracker = bridgelessReactStateTracker;
        this.mReactLifecycleStateManager = new ReactLifecycleStateManager(bridgelessReactStateTracker);
        this.mId = mCounter.getAndIncrement();
        this.mReactInstanceEventListeners = new CopyOnWriteArrayList();
        this.mBeforeDestroyListeners = new CopyOnWriteArrayList();
        this.mHostInvalidated = false;
        this.mStartTask = null;
        this.mReloadTask = null;
        this.mDestroyTask = null;
        this.mContext = context2;
        this.mReactHostDelegate = reactHostDelegate;
        this.mComponentFactory = componentFactory;
        this.mBGExecutor = executor;
        this.mUIExecutor = executor2;
        this.mMemoryPressureRouter = new MemoryPressureRouter(context2);
        this.mAllowPackagerServerAccess = z;
        this.mUseDevSupport = z2;
        this.mDevSupportManager = (devSupportManagerFactory == null ? new DefaultDevSupportManagerFactory() : devSupportManagerFactory).create(context.getApplicationContext(), new ReactHostImplDevHelper(this), reactHostDelegate.getJsMainModulePath(), true, (RedBoxHandler) null, (DevBundleDownloadListener) null, 2, (Map<String, RequestHandler>) null, (SurfaceDelegateFactory) null, (DevLoadingViewManager) null, (PausedInDebuggerOverlayManager) null, z2);
    }

    public LifecycleState getLifecycleState() {
        return this.mReactLifecycleStateManager.getLifecycleState();
    }

    public TaskInterface<Void> start() {
        return Task.call(new ReactHostImpl$$ExternalSyntheticLambda44(this), this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda1());
    }

    /* access modifiers changed from: package-private */
    public TaskInterface<Void> prerenderSurface(ReactSurfaceImpl reactSurfaceImpl) {
        String str = "prerenderSurface(surfaceId = " + reactSurfaceImpl.getSurfaceID() + ")";
        log(str, "Schedule");
        attachSurface(reactSurfaceImpl);
        return callAfterGetOrCreateReactInstance(str, new ReactHostImpl$$ExternalSyntheticLambda4(this, str, reactSurfaceImpl), this.mBGExecutor);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prerenderSurface$0(String str, ReactSurfaceImpl reactSurfaceImpl, ReactInstance reactInstance) {
        log(str, "Execute");
        reactInstance.prerenderSurface(reactSurfaceImpl);
    }

    /* access modifiers changed from: package-private */
    public TaskInterface<Void> startSurface(ReactSurfaceImpl reactSurfaceImpl) {
        String str = "startSurface(surfaceId = " + reactSurfaceImpl.getSurfaceID() + ")";
        log(str, "Schedule");
        attachSurface(reactSurfaceImpl);
        return callAfterGetOrCreateReactInstance(str, new ReactHostImpl$$ExternalSyntheticLambda11(this, str, reactSurfaceImpl), this.mBGExecutor);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startSurface$1(String str, ReactSurfaceImpl reactSurfaceImpl, ReactInstance reactInstance) {
        log(str, "Execute");
        reactInstance.startSurface(reactSurfaceImpl);
    }

    /* access modifiers changed from: package-private */
    public TaskInterface<Void> stopSurface(ReactSurfaceImpl reactSurfaceImpl) {
        String str = "stopSurface(surfaceId = " + reactSurfaceImpl.getSurfaceID() + ")";
        log(str, "Schedule");
        detachSurface(reactSurfaceImpl);
        return callWithExistingReactInstance(str, new ReactHostImpl$$ExternalSyntheticLambda17(this, str, reactSurfaceImpl), this.mBGExecutor).makeVoid();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$stopSurface$2(String str, ReactSurfaceImpl reactSurfaceImpl, ReactInstance reactInstance) {
        log(str, "Execute");
        reactInstance.stopSurface(reactSurfaceImpl);
    }

    public void onHostResume(Activity activity, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        this.mDefaultHardwareBackBtnHandler = defaultHardwareBackBtnHandler;
        onHostResume(activity);
    }

    public void onHostResume(Activity activity) {
        log("onHostResume(activity)");
        setCurrentActivity(activity);
        ReactContext currentReactContext = getCurrentReactContext();
        maybeEnableDevSupport(true);
        this.mReactLifecycleStateManager.moveToOnHostResume(currentReactContext, getCurrentActivity());
    }

    public void onHostLeaveHint(Activity activity) {
        log("onUserLeaveHint(activity)");
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            currentReactContext.onUserLeaveHint(activity);
        }
    }

    public void onHostPause(Activity activity) {
        log("onHostPause(activity)");
        ReactContext currentReactContext = getCurrentReactContext();
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            String simpleName = currentActivity.getClass().getSimpleName();
            String simpleName2 = activity == null ? "null" : activity.getClass().getSimpleName();
            boolean z = activity == currentActivity;
            Assertions.assertCondition(z, "Pausing an activity that is not the current activity, this is incorrect! Current activity: " + simpleName + " Paused activity: " + simpleName2);
        }
        maybeEnableDevSupport(false);
        this.mDefaultHardwareBackBtnHandler = null;
        this.mReactLifecycleStateManager.moveToOnHostPause(currentReactContext, currentActivity);
    }

    public void onHostPause() {
        log("onHostPause()");
        ReactContext currentReactContext = getCurrentReactContext();
        maybeEnableDevSupport(false);
        this.mDefaultHardwareBackBtnHandler = null;
        this.mReactLifecycleStateManager.moveToOnHostPause(currentReactContext, getCurrentActivity());
    }

    public void onHostDestroy() {
        log("onHostDestroy()");
        maybeEnableDevSupport(false);
        moveToHostDestroy(getCurrentReactContext());
    }

    public void onHostDestroy(Activity activity) {
        log("onHostDestroy(activity)");
        if (getCurrentActivity() == activity) {
            maybeEnableDevSupport(false);
            moveToHostDestroy(getCurrentReactContext());
        }
    }

    private void maybeEnableDevSupport(boolean z) {
        if (this.mUseDevSupport) {
            this.mDevSupportManager.setDevSupportEnabled(z);
        }
    }

    public ReactContext getCurrentReactContext() {
        return this.mBridgelessReactContextRef.getNullable();
    }

    public DevSupportManager getDevSupportManager() {
        return (DevSupportManager) Assertions.assertNotNull(this.mDevSupportManager);
    }

    public ReactSurface createSurface(Context context, String str, Bundle bundle) {
        ReactSurfaceImpl reactSurfaceImpl = new ReactSurfaceImpl(context, str, bundle);
        ReactSurfaceView reactSurfaceView = new ReactSurfaceView(context, reactSurfaceImpl);
        reactSurfaceView.setShouldLogContentAppeared(true);
        reactSurfaceImpl.attachView(reactSurfaceView);
        reactSurfaceImpl.attach(this);
        return reactSurfaceImpl;
    }

    public MemoryPressureRouter getMemoryPressureRouter() {
        return this.mMemoryPressureRouter;
    }

    /* access modifiers changed from: package-private */
    public boolean isInstanceInitialized() {
        return this.mReactInstance != null;
    }

    public boolean onBackPressed() {
        DeviceEventManagerModule deviceEventManagerModule;
        UiThreadUtil.assertOnUiThread();
        ReactInstance reactInstance = this.mReactInstance;
        if (reactInstance == null || (deviceEventManagerModule = (DeviceEventManagerModule) reactInstance.getNativeModule(DeviceEventManagerModule.class)) == null) {
            return false;
        }
        deviceEventManagerModule.emitHardwareBackPressed();
        return true;
    }

    public ReactQueueConfiguration getReactQueueConfiguration() {
        ReactInstance reactInstance = this.mReactInstance;
        if (reactInstance != null) {
            return reactInstance.getReactQueueConfiguration();
        }
        return null;
    }

    public void addReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener) {
        this.mReactInstanceEventListeners.add(reactInstanceEventListener);
    }

    public void removeReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener) {
        this.mReactInstanceEventListeners.remove(reactInstanceEventListener);
    }

    public TaskInterface<Void> reload(String str) {
        return Task.call(new ReactHostImpl$$ExternalSyntheticLambda0(this, str), this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda1());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$reload$5(String str) throws Exception {
        Task<Void> task;
        if (this.mDestroyTask != null) {
            log("reload()", "Waiting for destroy to finish, before reloading React Native.");
            task = this.mDestroyTask.continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda30(this, str), this.mBGExecutor).makeVoid();
        } else {
            task = getOrCreateReloadTask(str).makeVoid();
        }
        return task.continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda31(this), this.mBGExecutor);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$reload$3(String str, Task task) throws Exception {
        return getOrCreateReloadTask(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$reload$4(Task task) throws Exception {
        if (!task.isFaulted()) {
            return task;
        }
        Exception error = task.getError();
        if (this.mUseDevSupport) {
            this.mDevSupportManager.handleException(error);
        } else {
            this.mReactHostDelegate.handleInstanceException(error);
        }
        return getOrCreateDestroyTask("Reload failed", error);
    }

    @DoNotStrip
    private void setPausedInDebuggerMessage(String str) {
        if (str == null) {
            this.mDevSupportManager.hidePausedInDebuggerOverlay();
        } else {
            this.mDevSupportManager.showPausedInDebuggerOverlay(str, new DevSupportManager.PausedInDebuggerOverlayCommandListener() {
                public void onResume() {
                    UiThreadUtil.assertOnUiThread();
                    if (ReactHostImpl.this.mReactHostInspectorTarget != null) {
                        ReactHostImpl.this.mReactHostInspectorTarget.sendDebuggerResumeCommand();
                    }
                }
            });
        }
    }

    @DoNotStrip
    private Map<String, String> getHostMetadata() {
        return AndroidInfoHelpers.getInspectorHostMetadata(this.mContext);
    }

    @DoNotStrip
    private void loadNetworkResource(String str, InspectorNetworkRequestListener inspectorNetworkRequestListener) {
        InspectorNetworkHelper.loadNetworkResource(str, inspectorNetworkRequestListener);
    }

    public TaskInterface<Void> destroy(String str, Exception exc, final Function1 function1) {
        return ((Task) destroy(str, exc)).continueWith(new Continuation<Void, Void>() {
            public Void then(Task<Void> task) throws Exception {
                function1.invoke(Boolean.valueOf(task.isCompleted() && !task.isFaulted()));
                return null;
            }
        });
    }

    public TaskInterface<Void> destroy(String str, Exception exc) {
        return Task.call(new ReactHostImpl$$ExternalSyntheticLambda41(this, str, exc), this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda1());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$destroy$7(String str, Exception exc) throws Exception {
        if (this.mReloadTask == null) {
            return getOrCreateDestroyTask(str, exc);
        }
        log("destroy()", "Reloading React Native. Waiting for reload to finish before destroying React Native.");
        return this.mReloadTask.continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda5(this, str, exc), this.mBGExecutor);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$destroy$6(String str, Exception exc, Task task) throws Exception {
        return getOrCreateDestroyTask(str, exc);
    }

    private MemoryPressureListener createMemoryPressureListener(ReactInstance reactInstance) {
        return new ReactHostImpl$$ExternalSyntheticLambda32(this, new WeakReference(reactInstance));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createMemoryPressureListener$9(WeakReference weakReference, int i) {
        this.mBGExecutor.execute(new ReactHostImpl$$ExternalSyntheticLambda10(weakReference, i));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$createMemoryPressureListener$8(WeakReference weakReference, int i) {
        ReactInstance reactInstance = (ReactInstance) weakReference.get();
        if (reactInstance != null) {
            reactInstance.handleMemoryPressure(i);
        }
    }

    /* access modifiers changed from: package-private */
    public Activity getCurrentActivity() {
        return this.mActivity.get();
    }

    /* access modifiers changed from: package-private */
    public Activity getLastUsedActivity() {
        WeakReference weakReference = this.mLastUsedActivity.get();
        if (weakReference != null) {
            return (Activity) weakReference.get();
        }
        return null;
    }

    private void setCurrentActivity(Activity activity) {
        this.mActivity.set(activity);
        if (activity != null) {
            this.mLastUsedActivity.set(new WeakReference(activity));
        }
    }

    /* access modifiers changed from: package-private */
    public EventDispatcher getEventDispatcher() {
        ReactInstance reactInstance = this.mReactInstance;
        if (reactInstance == null) {
            return BlackHoleEventDispatcher.get();
        }
        return reactInstance.getEventDispatcher();
    }

    /* access modifiers changed from: package-private */
    public FabricUIManager getUIManager() {
        ReactInstance reactInstance = this.mReactInstance;
        if (reactInstance == null) {
            return null;
        }
        return reactInstance.getUIManager();
    }

    /* access modifiers changed from: package-private */
    public <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        ReactInstance reactInstance = this.mReactInstance;
        if (reactInstance != null) {
            return reactInstance.hasNativeModule(cls);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public Collection<NativeModule> getNativeModules() {
        ReactInstance reactInstance = this.mReactInstance;
        if (reactInstance != null) {
            return reactInstance.getNativeModules();
        }
        return new ArrayList();
    }

    /* access modifiers changed from: package-private */
    public <T extends NativeModule> T getNativeModule(Class<T> cls) {
        if (cls == UIManagerModule.class) {
            ReactSoftExceptionLogger.logSoftExceptionVerbose(TAG, new ReactNoCrashBridgeNotAllowedSoftException("getNativeModule(UIManagerModule.class) cannot be called when the bridge is disabled"));
        }
        ReactInstance reactInstance = this.mReactInstance;
        if (reactInstance != null) {
            return reactInstance.getNativeModule(cls);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public NativeModule getNativeModule(String str) {
        ReactInstance reactInstance = this.mReactInstance;
        if (reactInstance != null) {
            return reactInstance.getNativeModule(str);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public RuntimeExecutor getRuntimeExecutor() {
        ReactInstance reactInstance = this.mReactInstance;
        if (reactInstance != null) {
            return reactInstance.getBufferedRuntimeExecutor();
        }
        raiseSoftException("getRuntimeExecutor()", "Tried to get runtime executor while instance is not ready");
        return null;
    }

    /* access modifiers changed from: package-private */
    public CallInvokerHolder getJSCallInvokerHolder() {
        ReactInstance reactInstance = this.mReactInstance;
        if (reactInstance != null) {
            return reactInstance.getJSCallInvokerHolder();
        }
        raiseSoftException("getJSCallInvokerHolder()", "Tried to get JSCallInvokerHolder while instance is not ready");
        return null;
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        String str = "onActivityResult(activity = \"" + activity + "\", requestCode = \"" + i + "\", resultCode = \"" + i2 + "\", data = \"" + intent + "\")";
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            currentReactContext.onActivityResult(activity, i, i2, intent);
        } else {
            raiseSoftException(str, "Tried to access onActivityResult while context is not ready");
        }
    }

    public void onWindowFocusChange(boolean z) {
        String str = "onWindowFocusChange(hasFocus = \"" + z + "\")";
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            currentReactContext.onWindowFocusChange(z);
        } else {
            raiseSoftException(str, "Tried to access onWindowFocusChange while context is not ready");
        }
    }

    public void onNewIntent(Intent intent) {
        DeviceEventManagerModule deviceEventManagerModule;
        String str = "onNewIntent(intent = \"" + intent + "\")";
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            String action = intent.getAction();
            Uri data = intent.getData();
            if (data != null && (("android.intent.action.VIEW".equals(action) || "android.nfc.action.NDEF_DISCOVERED".equals(action)) && (deviceEventManagerModule = (DeviceEventManagerModule) currentReactContext.getNativeModule(DeviceEventManagerModule.class)) != null)) {
                deviceEventManagerModule.emitNewIntentReceived(data);
            }
            currentReactContext.onNewIntent(getCurrentActivity(), intent);
            return;
        }
        raiseSoftException(str, "Tried to access onNewIntent while context is not ready");
    }

    public void onConfigurationChanged(Context context) {
        AppearanceModule appearanceModule;
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null && (appearanceModule = (AppearanceModule) currentReactContext.getNativeModule(AppearanceModule.class)) != null) {
            appearanceModule.onConfigurationChanged(context);
        }
    }

    /* access modifiers changed from: package-private */
    public JavaScriptContextHolder getJavaScriptContextHolder() {
        ReactInstance reactInstance = this.mReactInstance;
        if (reactInstance != null) {
            return reactInstance.getJavaScriptContextHolder();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public DefaultHardwareBackBtnHandler getDefaultBackButtonHandler() {
        return new ReactHostImpl$$ExternalSyntheticLambda9(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getDefaultBackButtonHandler$10() {
        UiThreadUtil.assertOnUiThread();
        DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler = this.mDefaultHardwareBackBtnHandler;
        if (defaultHardwareBackBtnHandler != null) {
            defaultHardwareBackBtnHandler.invokeDefaultOnBackPressed();
        }
    }

    /* access modifiers changed from: package-private */
    public Task<Boolean> loadBundle(JSBundleLoader jSBundleLoader) {
        log("loadBundle()", "Schedule");
        return callWithExistingReactInstance("loadBundle()", new ReactHostImpl$$ExternalSyntheticLambda13(this, jSBundleLoader), (Executor) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadBundle$11(JSBundleLoader jSBundleLoader, ReactInstance reactInstance) {
        log("loadBundle()", "Execute");
        reactInstance.loadJSBundle(jSBundleLoader);
    }

    /* access modifiers changed from: package-private */
    public Task<Boolean> registerSegment(int i, String str, Callback callback) {
        String str2 = "registerSegment(segmentId = \"" + i + "\", path = \"" + str + "\")";
        log(str2, "Schedule");
        return callWithExistingReactInstance(str2, new ReactHostImpl$$ExternalSyntheticLambda18(this, str2, i, str, callback), (Executor) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$registerSegment$12(String str, int i, String str2, Callback callback, ReactInstance reactInstance) {
        log(str, "Execute");
        reactInstance.registerSegment(i, str2);
        ((Callback) Assertions.assertNotNull(callback)).invoke(new Object[0]);
    }

    /* access modifiers changed from: package-private */
    public void handleHostException(Exception exc) {
        String str = "handleHostException(message = \"" + exc.getMessage() + "\")";
        log(str);
        if (this.mUseDevSupport) {
            this.mDevSupportManager.handleException(exc);
        } else {
            this.mReactHostDelegate.handleInstanceException(exc);
        }
        destroy(str, exc);
    }

    /* access modifiers changed from: package-private */
    public Task<Boolean> callFunctionOnModule(String str, String str2, NativeArray nativeArray) {
        return callWithExistingReactInstance("callFunctionOnModule(\"" + str + "\", \"" + str2 + "\")", new ReactHostImpl$$ExternalSyntheticLambda27(str, str2, nativeArray), (Executor) null);
    }

    /* access modifiers changed from: package-private */
    public void attachSurface(ReactSurfaceImpl reactSurfaceImpl) {
        log("attachSurface(surfaceId = " + reactSurfaceImpl.getSurfaceID() + ")");
        synchronized (this.mAttachedSurfaces) {
            this.mAttachedSurfaces.add(reactSurfaceImpl);
        }
    }

    /* access modifiers changed from: package-private */
    public void detachSurface(ReactSurfaceImpl reactSurfaceImpl) {
        log("detachSurface(surfaceId = " + reactSurfaceImpl.getSurfaceID() + ")");
        synchronized (this.mAttachedSurfaces) {
            this.mAttachedSurfaces.remove(reactSurfaceImpl);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isSurfaceAttached(ReactSurfaceImpl reactSurfaceImpl) {
        boolean contains;
        synchronized (this.mAttachedSurfaces) {
            contains = this.mAttachedSurfaces.contains(reactSurfaceImpl);
        }
        return contains;
    }

    /* access modifiers changed from: package-private */
    public boolean isSurfaceWithModuleNameAttached(String str) {
        synchronized (this.mAttachedSurfaces) {
            try {
                for (ReactSurfaceImpl moduleName : this.mAttachedSurfaces) {
                    if (moduleName.getModuleName().equals(str)) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void addBeforeDestroyListener(Function0 function0) {
        this.mBeforeDestroyListeners.add(function0);
    }

    public void removeBeforeDestroyListener(Function0 function0) {
        this.mBeforeDestroyListeners.remove(function0);
    }

    /* access modifiers changed from: private */
    public Task<Void> getOrCreateStartTask() {
        if (this.mStartTask == null) {
            log("getOrCreateStartTask()", "Schedule");
            if (ReactBuildConfig.DEBUG) {
                Assertions.assertCondition(ReactNativeFeatureFlags.enableBridgelessArchitecture(), "enableBridgelessArchitecture FeatureFlag must be set to start ReactNative.");
                Assertions.assertCondition(ReactNativeFeatureFlags.enableFabricRenderer(), "enableFabricRenderer FeatureFlag must be set to start ReactNative.");
                Assertions.assertCondition(ReactNativeFeatureFlags.useTurboModules(), "useTurboModules FeatureFlag must be set to start ReactNative.");
            }
            this.mStartTask = waitThenCallGetOrCreateReactInstanceTask().continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda33(this), this.mBGExecutor);
        }
        return this.mStartTask;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateStartTask$15(Task task) throws Exception {
        if (!task.isFaulted()) {
            return task.makeVoid();
        }
        Exception error = task.getError();
        if (this.mUseDevSupport) {
            this.mDevSupportManager.handleException(error);
        } else {
            this.mReactHostDelegate.handleInstanceException(error);
        }
        return getOrCreateDestroyTask("getOrCreateStartTask() failure: " + task.getError().getMessage(), task.getError()).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda39(task)).makeVoid();
    }

    private void moveToHostDestroy(ReactContext reactContext) {
        this.mReactLifecycleStateManager.moveToOnHostDestroy(reactContext);
        setCurrentActivity((Activity) null);
    }

    private void raiseSoftException(String str, String str2) {
        raiseSoftException(str, str2, (Throwable) null);
    }

    private void raiseSoftException(String str, String str2, Throwable th) {
        String str3 = "raiseSoftException(" + str + ")";
        log(str3, str2);
        ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException(str3 + ": " + str2, th));
    }

    private Executor getDefaultReactInstanceExecutor() {
        if (ReactNativeFeatureFlags.useImmediateExecutorInAndroidBridgeless()) {
            return Task.IMMEDIATE_EXECUTOR;
        }
        return this.mBGExecutor;
    }

    private Task<Boolean> callWithExistingReactInstance(String str, ReactInstanceCalback reactInstanceCalback, Executor executor) {
        String str2 = "callWithExistingReactInstance(" + str + ")";
        if (executor == null) {
            executor = getDefaultReactInstanceExecutor();
        }
        return this.mCreateReactInstanceTaskRef.get().onSuccess(new ReactHostImpl$$ExternalSyntheticLambda26(this, str2, reactInstanceCalback), executor);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$callWithExistingReactInstance$16(String str, ReactInstanceCalback reactInstanceCalback, Task task) throws Exception {
        ReactInstance reactInstance;
        if (ReactNativeFeatureFlags.completeReactInstanceCreationOnBgThreadOnAndroid()) {
            reactInstance = (ReactInstance) task.getResult();
        } else {
            reactInstance = this.mReactInstance;
        }
        if (reactInstance == null) {
            raiseSoftException(str, "Execute: reactInstance is null. Dropping work.");
            return Boolean.FALSE;
        }
        reactInstanceCalback.then(reactInstance);
        return Boolean.TRUE;
    }

    private Task<Void> callAfterGetOrCreateReactInstance(String str, ReactInstanceCalback reactInstanceCalback, Executor executor) {
        String str2 = "callAfterGetOrCreateReactInstance(" + str + ")";
        if (executor == null) {
            executor = getDefaultReactInstanceExecutor();
        }
        return getOrCreateReactInstance().onSuccess(new ReactHostImpl$$ExternalSyntheticLambda2(this, str2, reactInstanceCalback), executor).continueWith(new ReactHostImpl$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$callAfterGetOrCreateReactInstance$17(String str, ReactInstanceCalback reactInstanceCalback, Task task) throws Exception {
        ReactInstance reactInstance;
        if (ReactNativeFeatureFlags.completeReactInstanceCreationOnBgThreadOnAndroid()) {
            reactInstance = (ReactInstance) task.getResult();
        } else {
            reactInstance = this.mReactInstance;
        }
        if (reactInstance == null) {
            raiseSoftException(str, "Execute: reactInstance is null. Dropping work.");
            return null;
        }
        reactInstanceCalback.then(reactInstance);
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$callAfterGetOrCreateReactInstance$18(Task task) throws Exception {
        if (!task.isFaulted()) {
            return null;
        }
        handleHostException(task.getError());
        return null;
    }

    private BridgelessReactContext getOrCreateReactContext() {
        return this.mBridgelessReactContextRef.getOrCreate(new ReactHostImpl$$ExternalSyntheticLambda12(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ BridgelessReactContext lambda$getOrCreateReactContext$19() {
        log("getOrCreateReactContext()", "Creating BridgelessReactContext");
        return new BridgelessReactContext(this.mContext, this);
    }

    private Task<ReactInstance> getOrCreateReactInstance() {
        return Task.call(new ReactHostImpl$$ExternalSyntheticLambda16(this), this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda1());
    }

    /* access modifiers changed from: private */
    public Task<ReactInstance> waitThenCallGetOrCreateReactInstanceTask() {
        return waitThenCallGetOrCreateReactInstanceTaskWithRetries(0, 4);
    }

    private Task<ReactInstance> waitThenCallGetOrCreateReactInstanceTaskWithRetries(int i, int i2) {
        if (this.mReloadTask != null) {
            log("waitThenCallGetOrCreateReactInstanceTaskWithRetries", "React Native is reloading. Return reload task.");
            return this.mReloadTask;
        }
        if (this.mDestroyTask != null) {
            if (i < i2) {
                log("waitThenCallGetOrCreateReactInstanceTaskWithRetries", "React Native is tearing down.Wait for teardown to finish, before trying again (try count = " + i + ").");
                return this.mDestroyTask.onSuccessTask(new ReactHostImpl$$ExternalSyntheticLambda19(this, i, i2), this.mBGExecutor);
            }
            raiseSoftException("waitThenCallGetOrCreateReactInstanceTaskWithRetries", "React Native is tearing down. Not wait for teardown to finish: reached max retries.");
        }
        return getOrCreateReactInstanceTask();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$waitThenCallGetOrCreateReactInstanceTaskWithRetries$20(int i, int i2, Task task) throws Exception {
        return waitThenCallGetOrCreateReactInstanceTaskWithRetries(i + 1, i2);
    }

    private static class CreationResult {
        final ReactContext mContext;
        /* access modifiers changed from: package-private */
        public final ReactInstance mInstance;
        final boolean mIsReloading;

        private CreationResult(ReactInstance reactInstance, ReactContext reactContext, boolean z) {
            this.mInstance = reactInstance;
            this.mContext = reactContext;
            this.mIsReloading = z;
        }
    }

    private Task<ReactInstance> getOrCreateReactInstanceTask() {
        log("getOrCreateReactInstanceTask()");
        return this.mCreateReactInstanceTaskRef.getOrCreate(new ReactHostImpl$$ExternalSyntheticLambda29(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateReactInstanceTask$25() {
        log("getOrCreateReactInstanceTask()", "Start");
        Assertions.assertCondition(!this.mHostInvalidated, "Cannot start a new ReactInstance on an invalidated ReactHost");
        ReactMarker.logMarker(ReactMarkerConstants.REACT_BRIDGELESS_LOADING_START, 1);
        Task<TContinuationResult> onSuccess = getJsBundleLoader().onSuccess(new ReactHostImpl$$ExternalSyntheticLambda6(this), this.mBGExecutor);
        ReactHostImpl$$ExternalSyntheticLambda7 reactHostImpl$$ExternalSyntheticLambda7 = new ReactHostImpl$$ExternalSyntheticLambda7(this);
        if (!ReactNativeFeatureFlags.completeReactInstanceCreationOnBgThreadOnAndroid()) {
            return onSuccess.onSuccess(reactHostImpl$$ExternalSyntheticLambda7, this.mUIExecutor);
        }
        onSuccess.onSuccess(reactHostImpl$$ExternalSyntheticLambda7, this.mUIExecutor);
        return onSuccess.onSuccess(new ReactHostImpl$$ExternalSyntheticLambda8(), Task.IMMEDIATE_EXECUTOR);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ CreationResult lambda$getOrCreateReactInstanceTask$22(Task task) throws Exception {
        BridgelessReactContext orCreateReactContext = getOrCreateReactContext();
        DevSupportManager devSupportManager = getDevSupportManager();
        orCreateReactContext.setJSExceptionHandler(devSupportManager);
        log("getOrCreateReactInstanceTask()", "Creating ReactInstance");
        ReactInstance reactInstance = new ReactInstance(orCreateReactContext, this.mReactHostDelegate, this.mComponentFactory, devSupportManager, new ReactHostImpl$$ExternalSyntheticLambda42(this), this.mUseDevSupport, getOrCreateReactHostInspectorTarget());
        this.mReactInstance = reactInstance;
        MemoryPressureListener createMemoryPressureListener = createMemoryPressureListener(reactInstance);
        this.mMemoryPressureListener = createMemoryPressureListener;
        this.mMemoryPressureRouter.addMemoryPressureListener(createMemoryPressureListener);
        reactInstance.initializeEagerTurboModules();
        log("getOrCreateReactInstanceTask()", "Loading JS Bundle");
        reactInstance.loadJSBundle((JSBundleLoader) task.getResult());
        log("getOrCreateReactInstanceTask()", "Calling DevSupportManagerBase.onNewReactContextCreated(reactContext)");
        devSupportManager.onNewReactContextCreated(orCreateReactContext);
        orCreateReactContext.runOnJSQueueThread(new ReactHostImpl$$ExternalSyntheticLambda43());
        return new CreationResult(reactInstance, orCreateReactContext, this.mReloadTask != null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ReactInstance lambda$getOrCreateReactInstanceTask$23(Task task) throws Exception {
        ReactInstance reactInstance = ((CreationResult) task.getResult()).mInstance;
        ReactContext reactContext = ((CreationResult) task.getResult()).mContext;
        boolean z = ((CreationResult) task.getResult()).mIsReloading;
        boolean z2 = this.mReactLifecycleStateManager.getLifecycleState() == LifecycleState.RESUMED;
        if (!z || z2) {
            this.mReactLifecycleStateManager.resumeReactContextIfHostResumed(reactContext, getCurrentActivity());
        } else {
            this.mReactLifecycleStateManager.moveToOnHostResume(reactContext, getCurrentActivity());
        }
        log("getOrCreateReactInstanceTask()", "Executing ReactInstanceEventListeners");
        for (ReactInstanceEventListener next : this.mReactInstanceEventListeners) {
            if (next != null) {
                next.onReactContextInitialized(reactContext);
            }
        }
        return reactInstance;
    }

    private Task<JSBundleLoader> getJsBundleLoader() {
        log("getJSBundleLoader()");
        if (this.mUseDevSupport && this.mAllowPackagerServerAccess) {
            return isMetroRunning().onSuccessTask(new ReactHostImpl$$ExternalSyntheticLambda14(this), this.mBGExecutor);
        }
        if (ReactBuildConfig.DEBUG) {
            FLog.d(TAG, "Packager server access is disabled in this environment");
        }
        return Task.call(new ReactHostImpl$$ExternalSyntheticLambda15(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$getJsBundleLoader$26(Task task) throws Exception {
        if (((Boolean) task.getResult()).booleanValue()) {
            return loadJSBundleFromMetro();
        }
        return Task.forResult(this.mReactHostDelegate.getJsBundleLoader());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ JSBundleLoader lambda$getJsBundleLoader$27() throws Exception {
        return this.mReactHostDelegate.getJsBundleLoader();
    }

    private Task<Boolean> isMetroRunning() {
        log("isMetroRunning()");
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        getDevSupportManager().isPackagerRunning(new ReactHostImpl$$ExternalSyntheticLambda40(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$isMetroRunning$28(TaskCompletionSource taskCompletionSource, boolean z) {
        log("isMetroRunning()", "Async result = " + z);
        taskCompletionSource.setResult(Boolean.valueOf(z));
    }

    private Task<JSBundleLoader> loadJSBundleFromMetro() {
        log("loadJSBundleFromMetro()");
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final DevSupportManagerBase devSupportManagerBase = (DevSupportManagerBase) getDevSupportManager();
        final String devServerBundleURL = devSupportManagerBase.getDevServerHelper().getDevServerBundleURL((String) Assertions.assertNotNull(devSupportManagerBase.getJSAppBundleName()));
        devSupportManagerBase.reloadJSFromServer(devServerBundleURL, new BundleLoadCallback() {
            public void onSuccess() {
                ReactHostImpl.this.log("loadJSBundleFromMetro()", "Creating BundleLoader");
                taskCompletionSource.setResult(JSBundleLoader.createCachedBundleFromNetworkLoader(devServerBundleURL, devSupportManagerBase.getDownloadedJSBundleFile()));
            }

            public void onError(Exception exc) {
                taskCompletionSource.setError(exc);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: private */
    public void log(String str, String str2) {
        BridgelessReactStateTracker bridgelessReactStateTracker = this.mBridgelessReactStateTracker;
        bridgelessReactStateTracker.enterState("ReactHost{" + this.mId + "}." + str + ": " + str2);
    }

    private void log(String str) {
        BridgelessReactStateTracker bridgelessReactStateTracker = this.mBridgelessReactStateTracker;
        bridgelessReactStateTracker.enterState("ReactHost{" + this.mId + "}." + str);
    }

    private void stopAttachedSurfaces(String str, ReactInstance reactInstance) {
        log(str, "Stopping all React Native surfaces");
        synchronized (this.mAttachedSurfaces) {
            try {
                for (ReactSurfaceImpl next : this.mAttachedSurfaces) {
                    reactInstance.stopSurface(next);
                    next.clear();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void startAttachedSurfaces(String str, ReactInstance reactInstance) {
        log(str, "Restarting previously running React Native Surfaces");
        synchronized (this.mAttachedSurfaces) {
            try {
                for (ReactSurfaceImpl startSurface : this.mAttachedSurfaces) {
                    reactInstance.startSurface(startSurface);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private ReactInstanceTaskUnwrapper createReactInstanceUnwrapper(String str, String str2, String str3) {
        return new ReactHostImpl$$ExternalSyntheticLambda28(this, str, str3, str2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ReactInstance lambda$createReactInstanceUnwrapper$29(String str, String str2, String str3, Task task, String str4) {
        ReactInstance reactInstance = (ReactInstance) task.getResult();
        ReactInstance reactInstance2 = this.mReactInstance;
        String str5 = "Stage: " + str4;
        String str6 = str + " reason: " + str2;
        if (task.isFaulted()) {
            Exception error = task.getError();
            raiseSoftException(str3, str + ": ReactInstance task faulted. " + str5 + ". " + ("Fault reason: " + error.getMessage()) + ". " + str6);
            return reactInstance2;
        } else if (task.isCancelled()) {
            raiseSoftException(str3, str + ": ReactInstance task cancelled. " + str5 + ". " + str6);
            return reactInstance2;
        } else if (reactInstance == null) {
            raiseSoftException(str3, str + ": ReactInstance task returned null. " + str5 + ". " + str6);
            return reactInstance2;
        } else {
            if (!(reactInstance2 == null || reactInstance == reactInstance2)) {
                raiseSoftException(str3, str + ": Detected two different ReactInstances. Returning old. " + str5 + ". " + str6);
            }
            return reactInstance;
        }
    }

    private Task<ReactInstance> getOrCreateReloadTask(String str) {
        Task task;
        log("getOrCreateReloadTask()");
        raiseSoftException("getOrCreateReloadTask()", str);
        ReactInstanceTaskUnwrapper createReactInstanceUnwrapper = createReactInstanceUnwrapper("Reload", "getOrCreateReloadTask()", str);
        if (this.mReloadTask == null) {
            if (ReactNativeFeatureFlags.completeReactInstanceCreationOnBgThreadOnAndroid()) {
                task = this.mCreateReactInstanceTaskRef.getAndReset();
            } else {
                task = this.mCreateReactInstanceTaskRef.get();
            }
            this.mReloadTask = task.continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda20(this, createReactInstanceUnwrapper, str), this.mUIExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda21(this, createReactInstanceUnwrapper), this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda22(this, createReactInstanceUnwrapper), this.mUIExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda23(this, createReactInstanceUnwrapper), this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda24(this, createReactInstanceUnwrapper), this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda25(this, str), this.mBGExecutor);
        }
        return this.mReloadTask;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateReloadTask$30(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, String str, Task task) throws Exception {
        log("getOrCreateReloadTask()", "Starting React Native reload");
        ReactInstance unwrap = reactInstanceTaskUnwrapper.unwrap(task, "1: Starting reload");
        unregisterInstanceFromInspector(unwrap);
        ReactContext nullable = this.mBridgelessReactContextRef.getNullable();
        if (nullable == null) {
            raiseSoftException("getOrCreateReloadTask()", "ReactContext is null. Reload reason: " + str);
        }
        if (nullable != null && this.mReactLifecycleStateManager.getLifecycleState() == LifecycleState.RESUMED) {
            log("getOrCreateReloadTask()", "Calling ReactContext.onHostPause()");
            nullable.onHostPause();
        }
        return Task.forResult(unwrap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateReloadTask$31(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        ReactInstance unwrap = reactInstanceTaskUnwrapper.unwrap(task, "2: Surface shutdown");
        if (unwrap == null) {
            raiseSoftException("getOrCreateReloadTask()", "Skipping surface shutdown: ReactInstance null");
            return task;
        }
        stopAttachedSurfaces("getOrCreateReloadTask()", unwrap);
        return task;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateReloadTask$32(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        reactInstanceTaskUnwrapper.unwrap(task, "3: Destroying ReactContext");
        for (Function0 invoke : this.mBeforeDestroyListeners) {
            invoke.invoke();
        }
        if (this.mMemoryPressureListener != null) {
            log("getOrCreateReloadTask()", "Removing memory pressure listener");
            this.mMemoryPressureRouter.removeMemoryPressureListener(this.mMemoryPressureListener);
        }
        ReactContext nullable = this.mBridgelessReactContextRef.getNullable();
        if (nullable != null) {
            log("getOrCreateReloadTask()", "Resetting ReactContext ref");
            this.mBridgelessReactContextRef.reset();
            log("getOrCreateReloadTask()", "Destroying ReactContext");
            nullable.destroy();
        }
        if (this.mUseDevSupport && nullable != null) {
            log("getOrCreateReloadTask()", "Calling DevSupportManager.onReactInstanceDestroyed(reactContext)");
            this.mDevSupportManager.onReactInstanceDestroyed(nullable);
        }
        return task;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateReloadTask$33(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        ReactInstance unwrap = reactInstanceTaskUnwrapper.unwrap(task, "4: Destroying ReactInstance");
        if (unwrap == null) {
            raiseSoftException("getOrCreateReloadTask()", "Skipping ReactInstance.destroy(): ReactInstance null");
        } else {
            log("getOrCreateReloadTask()", "Resetting ReactInstance ptr");
            this.mReactInstance = null;
            log("getOrCreateReloadTask()", "Destroying ReactInstance");
            unwrap.destroy();
        }
        if (!ReactNativeFeatureFlags.completeReactInstanceCreationOnBgThreadOnAndroid()) {
            log("getOrCreateReloadTask()", "Resetting createReactInstance task ref");
            this.mCreateReactInstanceTaskRef.reset();
        }
        log("getOrCreateReloadTask()", "Resetting start task ref");
        this.mStartTask = null;
        return getOrCreateReactInstanceTask();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateReloadTask$34(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        ReactInstance unwrap = reactInstanceTaskUnwrapper.unwrap(task, "5: Restarting surfaces");
        if (unwrap == null) {
            raiseSoftException("getOrCreateReloadTask()", "Skipping surface restart: ReactInstance null");
            return task;
        }
        startAttachedSurfaces("getOrCreateReloadTask()", unwrap);
        return task;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateReloadTask$35(String str, Task task) throws Exception {
        if (task.isFaulted()) {
            Exception error = task.getError();
            raiseSoftException("getOrCreateReloadTask()", "Error during reload. ReactInstance task faulted. Fault reason: " + error.getMessage() + ". Reload reason: " + str, task.getError());
        }
        if (task.isCancelled()) {
            raiseSoftException("getOrCreateReloadTask()", "Error during reload. ReactInstance task cancelled. Reload reason: " + str);
        }
        log("getOrCreateReloadTask()", "Resetting reload task ref");
        this.mReloadTask = null;
        return task;
    }

    private Task<Void> getOrCreateDestroyTask(String str, Exception exc) {
        Task task;
        log("getOrCreateDestroyTask()");
        raiseSoftException("getOrCreateDestroyTask()", str, exc);
        ReactInstanceTaskUnwrapper createReactInstanceUnwrapper = createReactInstanceUnwrapper("Destroy", "getOrCreateDestroyTask()", str);
        if (this.mDestroyTask == null) {
            if (ReactNativeFeatureFlags.completeReactInstanceCreationOnBgThreadOnAndroid()) {
                task = this.mCreateReactInstanceTaskRef.getAndReset();
            } else {
                task = this.mCreateReactInstanceTaskRef.get();
            }
            this.mDestroyTask = task.continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda34(this, createReactInstanceUnwrapper, str), this.mUIExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda35(this, createReactInstanceUnwrapper), this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda36(this, createReactInstanceUnwrapper, str), this.mUIExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda37(this, createReactInstanceUnwrapper), this.mBGExecutor).continueWith(new ReactHostImpl$$ExternalSyntheticLambda38(this, str));
        }
        return this.mDestroyTask;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateDestroyTask$36(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, String str, Task task) throws Exception {
        ReactHostInspectorTarget reactHostInspectorTarget;
        log("getOrCreateDestroyTask()", "Starting React Native destruction");
        ReactInstance unwrap = reactInstanceTaskUnwrapper.unwrap(task, "1: Starting destroy");
        unregisterInstanceFromInspector(unwrap);
        if (this.mHostInvalidated && (reactHostInspectorTarget = this.mReactHostInspectorTarget) != null) {
            reactHostInspectorTarget.close();
            this.mReactHostInspectorTarget = null;
        }
        if (this.mUseDevSupport) {
            log("getOrCreateDestroyTask()", "DevSupportManager cleanup");
            this.mDevSupportManager.stopInspector();
        }
        ReactContext nullable = this.mBridgelessReactContextRef.getNullable();
        if (nullable == null) {
            raiseSoftException("getOrCreateDestroyTask()", "ReactContext is null. Destroy reason: " + str);
        }
        log("getOrCreateDestroyTask()", "Move ReactHost to onHostDestroy()");
        this.mReactLifecycleStateManager.moveToOnHostDestroy(nullable);
        return Task.forResult(unwrap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateDestroyTask$37(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        ReactInstance unwrap = reactInstanceTaskUnwrapper.unwrap(task, "2: Stopping surfaces");
        if (unwrap == null) {
            raiseSoftException("getOrCreateDestroyTask()", "Skipping surface shutdown: ReactInstance null");
            return task;
        }
        stopAttachedSurfaces("getOrCreateDestroyTask()", unwrap);
        synchronized (this.mAttachedSurfaces) {
            this.mAttachedSurfaces.clear();
        }
        return task;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateDestroyTask$38(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, String str, Task task) throws Exception {
        reactInstanceTaskUnwrapper.unwrap(task, "3: Destroying ReactContext");
        for (Function0 invoke : this.mBeforeDestroyListeners) {
            invoke.invoke();
        }
        ReactContext nullable = this.mBridgelessReactContextRef.getNullable();
        if (nullable == null) {
            raiseSoftException("getOrCreateDestroyTask()", "ReactContext is null. Destroy reason: " + str);
        }
        log("getOrCreateDestroyTask()", "Destroying MemoryPressureRouter");
        this.mMemoryPressureRouter.destroy(this.mContext);
        if (nullable != null) {
            log("getOrCreateDestroyTask()", "Resetting ReactContext ref");
            this.mBridgelessReactContextRef.reset();
            log("getOrCreateDestroyTask()", "Destroying ReactContext");
            nullable.destroy();
        }
        setCurrentActivity((Activity) null);
        ResourceDrawableIdHelper.getInstance().clear();
        return task;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateDestroyTask$39(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        ReactInstance unwrap = reactInstanceTaskUnwrapper.unwrap(task, "4: Destroying ReactInstance");
        if (unwrap == null) {
            raiseSoftException("getOrCreateDestroyTask()", "Skipping ReactInstance.destroy(): ReactInstance null");
        } else {
            log("getOrCreateDestroyTask()", "Resetting ReactInstance ptr");
            this.mReactInstance = null;
            log("getOrCreateDestroyTask()", "Destroying ReactInstance");
            unwrap.destroy();
        }
        if (!ReactNativeFeatureFlags.completeReactInstanceCreationOnBgThreadOnAndroid()) {
            log("getOrCreateDestroyTask()", "Resetting createReactInstance task ref");
            this.mCreateReactInstanceTaskRef.reset();
        }
        log("getOrCreateDestroyTask()", "Resetting start task ref");
        this.mStartTask = null;
        log("getOrCreateDestroyTask()", "Resetting destroy task ref");
        this.mDestroyTask = null;
        return task;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$getOrCreateDestroyTask$40(String str, Task task) throws Exception {
        if (task.isFaulted()) {
            Exception error = task.getError();
            raiseSoftException("getOrCreateDestroyTask()", "React destruction failed. ReactInstance task faulted. Fault reason: " + error.getMessage() + ". Destroy reason: " + str, task.getError());
        }
        if (!task.isCancelled()) {
            return null;
        }
        raiseSoftException("getOrCreateDestroyTask()", "React destruction failed. ReactInstance task cancelled. Destroy reason: " + str);
        return null;
    }

    private ReactHostInspectorTarget getOrCreateReactHostInspectorTarget() {
        if (this.mReactHostInspectorTarget == null && InspectorFlags.getFuseboxEnabled()) {
            this.mReactHostInspectorTarget = new ReactHostInspectorTarget(this);
        }
        return this.mReactHostInspectorTarget;
    }

    private void unregisterInstanceFromInspector(ReactInstance reactInstance) {
        if (reactInstance != null) {
            if (InspectorFlags.getFuseboxEnabled()) {
                ReactHostInspectorTarget reactHostInspectorTarget = this.mReactHostInspectorTarget;
                Assertions.assertCondition(reactHostInspectorTarget != null && reactHostInspectorTarget.isValid(), "Host inspector target destroyed before instance was unregistered");
            }
            reactInstance.unregisterFromInspector();
        }
    }

    public void invalidate() {
        FLog.d(TAG, "ReactHostImpl.invalidate()");
        this.mHostInvalidated = true;
        destroy("ReactHostImpl.invalidate()", (Exception) null);
    }
}
