package com.facebook.react.bridge;

import android.content.res.AssetManager;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.proguard.annotations.DoNotStripAny;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.bridge.queue.ReactQueueConfigurationImpl;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.internal.turbomodule.core.interfaces.TurboModuleRegistry;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;
import com.facebook.react.turbomodule.core.NativeMethodCallInvokerHolderImpl;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.TraceListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@DoNotStrip
public class CatalystInstanceImpl implements CatalystInstance {
    private static final AtomicInteger sNextInstanceIdForTrace = new AtomicInteger(1);
    private volatile boolean mAcceptCalls;
    private final CopyOnWriteArrayList<NotThreadSafeBridgeIdleDebugListener> mBridgeIdleListeners;
    private volatile boolean mDestroyed;
    private UIManager mFabricUIManager;
    private final HybridData mHybridData;
    private boolean mInitialized;
    private ReactInstanceManagerInspectorTarget mInspectorTarget;
    private boolean mJSBundleHasLoaded;
    private final JSBundleLoader mJSBundleLoader;
    private final ArrayList<PendingJSCall> mJSCallsPendingInit;
    private final Object mJSCallsPendingInitLock;
    private final JSExceptionHandler mJSExceptionHandler;
    private final JavaScriptModuleRegistry mJSModuleRegistry;
    private JavaScriptContextHolder mJavaScriptContextHolder;
    private final String mJsPendingCallsTitleForTrace;
    /* access modifiers changed from: private */
    public final NativeModuleRegistry mNativeModuleRegistry;
    /* access modifiers changed from: private */
    public final MessageQueueThread mNativeModulesQueueThread;
    private final AtomicInteger mPendingJSCalls;
    private final ReactQueueConfigurationImpl mReactQueueConfiguration;
    private String mSourceURL;
    private final TraceListener mTraceListener;
    private TurboModuleRegistry mTurboModuleRegistry;

    private native long getJavaScriptContext();

    private static native HybridData initHybrid();

    private native void initializeBridge(InstanceCallback instanceCallback, JavaScriptExecutor javaScriptExecutor, MessageQueueThread messageQueueThread, MessageQueueThread messageQueueThread2, Collection<JavaModuleWrapper> collection, Collection<ModuleHolder> collection2, ReactInstanceManagerInspectorTarget reactInstanceManagerInspectorTarget);

    private native void jniCallJSCallback(int i, NativeArray nativeArray);

    /* access modifiers changed from: private */
    public native void jniCallJSFunction(String str, String str2, NativeArray nativeArray);

    private native void jniExtendNativeModules(Collection<JavaModuleWrapper> collection, Collection<ModuleHolder> collection2);

    private native void jniHandleMemoryPressure(int i);

    private native void jniLoadScriptFromAssets(AssetManager assetManager, String str, boolean z);

    private native void jniLoadScriptFromFile(String str, String str2, boolean z);

    private native void jniRegisterSegment(int i, String str);

    private native void jniSetSourceURL(String str);

    private native void unregisterFromInspector();

    public native CallInvokerHolderImpl getJSCallInvokerHolder();

    public native NativeMethodCallInvokerHolderImpl getNativeMethodCallInvokerHolder();

    public native RuntimeExecutor getRuntimeExecutor();

    public native RuntimeScheduler getRuntimeScheduler();

    public native void setGlobalVariable(String str, String str2);

    static {
        ReactBridge.staticInit();
    }

    public static class PendingJSCall {
        public NativeArray mArguments;
        public String mMethod;
        public String mModule;

        public PendingJSCall(String str, String str2, NativeArray nativeArray) {
            this.mModule = str;
            this.mMethod = str2;
            this.mArguments = nativeArray;
        }

        /* access modifiers changed from: package-private */
        public void call(CatalystInstanceImpl catalystInstanceImpl) {
            NativeArray nativeArray = this.mArguments;
            if (nativeArray == null) {
                nativeArray = new WritableNativeArray();
            }
            catalystInstanceImpl.jniCallJSFunction(this.mModule, this.mMethod, nativeArray);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mModule);
            sb.append(".");
            sb.append(this.mMethod);
            sb.append("(");
            NativeArray nativeArray = this.mArguments;
            sb.append(nativeArray == null ? "" : nativeArray.toString());
            sb.append(")");
            return sb.toString();
        }
    }

    private CatalystInstanceImpl(ReactQueueConfigurationSpec reactQueueConfigurationSpec, JavaScriptExecutor javaScriptExecutor, NativeModuleRegistry nativeModuleRegistry, JSBundleLoader jSBundleLoader, JSExceptionHandler jSExceptionHandler, ReactInstanceManagerInspectorTarget reactInstanceManagerInspectorTarget) {
        this.mPendingJSCalls = new AtomicInteger(0);
        this.mJsPendingCallsTitleForTrace = "pending_js_calls_instance" + sNextInstanceIdForTrace.getAndIncrement();
        this.mDestroyed = false;
        this.mJSCallsPendingInit = new ArrayList<>();
        this.mJSCallsPendingInitLock = new Object();
        this.mInitialized = false;
        this.mAcceptCalls = false;
        FLog.d(ReactConstants.TAG, "Initializing React Xplat Bridge.");
        Systrace.beginSection(0, "createCatalystInstanceImpl");
        this.mHybridData = initHybrid();
        ReactQueueConfigurationSpec reactQueueConfigurationSpec2 = reactQueueConfigurationSpec;
        ReactQueueConfigurationImpl create = ReactQueueConfigurationImpl.create(reactQueueConfigurationSpec, new NativeExceptionHandler());
        this.mReactQueueConfiguration = create;
        this.mBridgeIdleListeners = new CopyOnWriteArrayList<>();
        this.mNativeModuleRegistry = nativeModuleRegistry;
        this.mJSModuleRegistry = new JavaScriptModuleRegistry();
        this.mJSBundleLoader = jSBundleLoader;
        this.mJSExceptionHandler = jSExceptionHandler;
        MessageQueueThread nativeModulesQueueThread = create.getNativeModulesQueueThread();
        this.mNativeModulesQueueThread = nativeModulesQueueThread;
        this.mTraceListener = new JSProfilerTraceListener(this);
        this.mInspectorTarget = reactInstanceManagerInspectorTarget;
        Systrace.endSection(0);
        FLog.d(ReactConstants.TAG, "Initializing React Xplat Bridge before initializeBridge");
        Systrace.beginSection(0, "initializeCxxBridge");
        initializeBridge(new InstanceCallback(this), javaScriptExecutor, create.getJSQueueThread(), nativeModulesQueueThread, nativeModuleRegistry.getJavaModules(this), nativeModuleRegistry.getCxxModules(), this.mInspectorTarget);
        FLog.d(ReactConstants.TAG, "Initializing React Xplat Bridge after initializeBridge");
        Systrace.endSection(0);
        this.mJavaScriptContextHolder = new JavaScriptContextHolder(getJavaScriptContext());
    }

    @DoNotStripAny
    private static class InstanceCallback {
        private final WeakReference<CatalystInstanceImpl> mOuter;

        InstanceCallback(CatalystInstanceImpl catalystInstanceImpl) {
            this.mOuter = new WeakReference<>(catalystInstanceImpl);
        }

        public void onBatchComplete() {
            CatalystInstanceImpl catalystInstanceImpl = this.mOuter.get();
            if (catalystInstanceImpl != null) {
                catalystInstanceImpl.mNativeModulesQueueThread.runOnQueue(new CatalystInstanceImpl$InstanceCallback$$ExternalSyntheticLambda0(catalystInstanceImpl));
            }
        }

        public void incrementPendingJSCalls() {
            CatalystInstanceImpl catalystInstanceImpl = this.mOuter.get();
            if (catalystInstanceImpl != null) {
                catalystInstanceImpl.incrementPendingJSCalls();
            }
        }

        public void decrementPendingJSCalls() {
            CatalystInstanceImpl catalystInstanceImpl = this.mOuter.get();
            if (catalystInstanceImpl != null) {
                catalystInstanceImpl.decrementPendingJSCalls();
            }
        }
    }

    public void extendNativeModules(NativeModuleRegistry nativeModuleRegistry) {
        this.mNativeModuleRegistry.registerModules(nativeModuleRegistry);
        jniExtendNativeModules(nativeModuleRegistry.getJavaModules(this), nativeModuleRegistry.getCxxModules());
    }

    public void setSourceURLs(String str, String str2) {
        this.mSourceURL = str;
        jniSetSourceURL(str2);
    }

    public void registerSegment(int i, String str) {
        jniRegisterSegment(i, str);
    }

    public void loadScriptFromAssets(AssetManager assetManager, String str, boolean z) {
        this.mSourceURL = str;
        jniLoadScriptFromAssets(assetManager, str, z);
    }

    public void loadScriptFromFile(String str, String str2, boolean z) {
        this.mSourceURL = str2;
        jniLoadScriptFromFile(str, str2, z);
    }

    public void loadSplitBundleFromFile(String str, String str2) {
        jniLoadScriptFromFile(str, str2, false);
    }

    public void runJSBundle() {
        FLog.d(ReactConstants.TAG, "CatalystInstanceImpl.runJSBundle()");
        Assertions.assertCondition(!this.mJSBundleHasLoaded, "JS bundle was already loaded!");
        this.mJSBundleLoader.loadScript(this);
        synchronized (this.mJSCallsPendingInitLock) {
            try {
                this.mAcceptCalls = true;
                Iterator<PendingJSCall> it = this.mJSCallsPendingInit.iterator();
                while (it.hasNext()) {
                    it.next().call(this);
                }
                this.mJSCallsPendingInit.clear();
                this.mJSBundleHasLoaded = true;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        Systrace.registerListener(this.mTraceListener);
    }

    public boolean hasRunJSBundle() {
        boolean z;
        synchronized (this.mJSCallsPendingInitLock) {
            try {
                z = this.mJSBundleHasLoaded && this.mAcceptCalls;
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public String getSourceURL() {
        return this.mSourceURL;
    }

    public void callFunction(String str, String str2, NativeArray nativeArray) {
        callFunction(new PendingJSCall(str, str2, nativeArray));
    }

    public void callFunction(PendingJSCall pendingJSCall) {
        if (this.mDestroyed) {
            String pendingJSCall2 = pendingJSCall.toString();
            FLog.w(ReactConstants.TAG, "Calling JS function after bridge has been destroyed: " + pendingJSCall2);
            return;
        }
        if (!this.mAcceptCalls) {
            synchronized (this.mJSCallsPendingInitLock) {
                try {
                    if (!this.mAcceptCalls) {
                        this.mJSCallsPendingInit.add(pendingJSCall);
                        return;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        pendingJSCall.call(this);
    }

    public void invokeCallback(int i, NativeArrayInterface nativeArrayInterface) {
        if (this.mDestroyed) {
            FLog.w(ReactConstants.TAG, "Invoking JS callback after bridge has been destroyed.");
        } else {
            jniCallJSCallback(i, (NativeArray) nativeArrayInterface);
        }
    }

    /* renamed from: destroy */
    public void lambda$onNativeException$6() {
        FLog.d(ReactConstants.TAG, "CatalystInstanceImpl.destroy() start");
        UiThreadUtil.assertOnUiThread();
        if (!this.mDestroyed) {
            ReactInstanceManagerInspectorTarget reactInstanceManagerInspectorTarget = this.mInspectorTarget;
            if (reactInstanceManagerInspectorTarget != null) {
                Assertions.assertCondition(reactInstanceManagerInspectorTarget.isValid(), "ReactInstanceManager inspector target destroyed before instance was unregistered");
            }
            unregisterFromInspector();
            ReactMarker.logMarker(ReactMarkerConstants.DESTROY_CATALYST_INSTANCE_START);
            this.mDestroyed = true;
            this.mNativeModulesQueueThread.runOnQueue(new CatalystInstanceImpl$$ExternalSyntheticLambda0(this));
            Systrace.unregisterListener(this.mTraceListener);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$destroy$2() {
        this.mNativeModuleRegistry.notifyJSInstanceDestroy();
        UIManager uIManager = this.mFabricUIManager;
        if (uIManager != null) {
            uIManager.invalidate();
        }
        boolean z = false;
        if (this.mPendingJSCalls.getAndSet(0) == 0) {
            z = true;
        }
        if (!this.mBridgeIdleListeners.isEmpty()) {
            Iterator<NotThreadSafeBridgeIdleDebugListener> it = this.mBridgeIdleListeners.iterator();
            while (it.hasNext()) {
                NotThreadSafeBridgeIdleDebugListener next = it.next();
                if (!z) {
                    next.onTransitionToBridgeIdle();
                }
                next.onBridgeDestroyed();
            }
        }
        getReactQueueConfiguration().getJSQueueThread().runOnQueue(new CatalystInstanceImpl$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$destroy$1() {
        TurboModuleRegistry turboModuleRegistry = this.mTurboModuleRegistry;
        if (turboModuleRegistry != null) {
            turboModuleRegistry.invalidate();
        }
        new Thread(new CatalystInstanceImpl$$ExternalSyntheticLambda3(this), "destroy_react_context").start();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$destroy$0() {
        this.mJavaScriptContextHolder.clear();
        this.mHybridData.resetNative();
        getReactQueueConfiguration().destroy();
        FLog.w(ReactConstants.TAG, "CatalystInstanceImpl.destroy() end");
        ReactMarker.logMarker(ReactMarkerConstants.DESTROY_CATALYST_INSTANCE_END);
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    @VisibleForTesting
    public void initialize() {
        FLog.d(ReactConstants.TAG, "CatalystInstanceImpl.initialize()");
        Assertions.assertCondition(!this.mInitialized, "This catalyst instance has already been initialized");
        Assertions.assertCondition(this.mAcceptCalls, "RunJSBundle hasn't completed.");
        this.mInitialized = true;
        this.mNativeModulesQueueThread.runOnQueue(new CatalystInstanceImpl$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initialize$3() {
        this.mNativeModuleRegistry.notifyJSInstanceInitialized();
    }

    public ReactQueueConfiguration getReactQueueConfiguration() {
        return this.mReactQueueConfiguration;
    }

    public <T extends JavaScriptModule> T getJSModule(Class<T> cls) {
        return this.mJSModuleRegistry.getJavaScriptModule(this, cls);
    }

    public <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        String nameFromAnnotation = getNameFromAnnotation(cls);
        if (getTurboModuleRegistry() == null || !getTurboModuleRegistry().hasModule(nameFromAnnotation)) {
            return this.mNativeModuleRegistry.hasModule(nameFromAnnotation);
        }
        return true;
    }

    public <T extends NativeModule> T getNativeModule(Class<T> cls) {
        return getNativeModule(getNameFromAnnotation(cls));
    }

    private TurboModuleRegistry getTurboModuleRegistry() {
        if (ReactNativeFeatureFlags.useTurboModules()) {
            return (TurboModuleRegistry) Assertions.assertNotNull(this.mTurboModuleRegistry, "TurboModules are enabled, but mTurboModuleRegistry hasn't been set.");
        }
        return null;
    }

    public NativeModule getNativeModule(String str) {
        NativeModule module;
        if (getTurboModuleRegistry() != null && (module = getTurboModuleRegistry().getModule(str)) != null) {
            return module;
        }
        if (this.mNativeModuleRegistry.hasModule(str)) {
            return this.mNativeModuleRegistry.getModule(str);
        }
        return null;
    }

    private <T extends NativeModule> String getNameFromAnnotation(Class<T> cls) {
        ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
        if (reactModule != null) {
            return reactModule.name();
        }
        throw new IllegalArgumentException("Could not find @ReactModule annotation in " + cls.getCanonicalName());
    }

    public Collection<NativeModule> getNativeModules() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mNativeModuleRegistry.getAllModules());
        if (getTurboModuleRegistry() != null) {
            for (NativeModule add : getTurboModuleRegistry().getModules()) {
                arrayList.add(add);
            }
        }
        return arrayList;
    }

    public void handleMemoryPressure(int i) {
        if (!this.mDestroyed) {
            jniHandleMemoryPressure(i);
        }
    }

    public void addBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener) {
        this.mBridgeIdleListeners.add(notThreadSafeBridgeIdleDebugListener);
    }

    public void removeBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener) {
        this.mBridgeIdleListeners.remove(notThreadSafeBridgeIdleDebugListener);
    }

    public JavaScriptContextHolder getJavaScriptContextHolder() {
        return this.mJavaScriptContextHolder;
    }

    /* access modifiers changed from: private */
    public void incrementPendingJSCalls() {
        int andIncrement = this.mPendingJSCalls.getAndIncrement();
        boolean z = andIncrement == 0;
        Systrace.traceCounter(0, this.mJsPendingCallsTitleForTrace, andIncrement + 1);
        if (z && !this.mBridgeIdleListeners.isEmpty()) {
            this.mNativeModulesQueueThread.runOnQueue(new CatalystInstanceImpl$$ExternalSyntheticLambda4(this));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$incrementPendingJSCalls$4() {
        Iterator<NotThreadSafeBridgeIdleDebugListener> it = this.mBridgeIdleListeners.iterator();
        while (it.hasNext()) {
            it.next().onTransitionToBridgeBusy();
        }
    }

    public void setTurboModuleRegistry(TurboModuleRegistry turboModuleRegistry) {
        this.mTurboModuleRegistry = turboModuleRegistry;
    }

    public void setFabricUIManager(UIManager uIManager) {
        this.mFabricUIManager = uIManager;
    }

    public UIManager getFabricUIManager() {
        return this.mFabricUIManager;
    }

    /* access modifiers changed from: private */
    public void decrementPendingJSCalls() {
        int decrementAndGet = this.mPendingJSCalls.decrementAndGet();
        boolean z = decrementAndGet == 0;
        Systrace.traceCounter(0, this.mJsPendingCallsTitleForTrace, decrementAndGet);
        if (z && !this.mBridgeIdleListeners.isEmpty()) {
            this.mNativeModulesQueueThread.runOnQueue(new CatalystInstanceImpl$$ExternalSyntheticLambda6(this));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$decrementPendingJSCalls$5() {
        Iterator<NotThreadSafeBridgeIdleDebugListener> it = this.mBridgeIdleListeners.iterator();
        while (it.hasNext()) {
            it.next().onTransitionToBridgeIdle();
        }
    }

    /* access modifiers changed from: private */
    public void onNativeException(Exception exc) {
        this.mJSExceptionHandler.handleException(exc);
        this.mReactQueueConfiguration.getUIQueueThread().runOnQueue(new CatalystInstanceImpl$$ExternalSyntheticLambda2(this));
    }

    private class NativeExceptionHandler implements QueueThreadExceptionHandler {
        private NativeExceptionHandler() {
        }

        public void handleException(Exception exc) {
            CatalystInstanceImpl.this.onNativeException(exc);
        }
    }

    private static class JSProfilerTraceListener implements TraceListener {
        private final WeakReference<CatalystInstanceImpl> mOuter;

        public JSProfilerTraceListener(CatalystInstanceImpl catalystInstanceImpl) {
            this.mOuter = new WeakReference<>(catalystInstanceImpl);
        }

        public void onTraceStarted() {
            CatalystInstanceImpl catalystInstanceImpl = this.mOuter.get();
            if (catalystInstanceImpl != null) {
                ((Systrace) catalystInstanceImpl.getJSModule(Systrace.class)).setEnabled(true);
            }
        }

        public void onTraceStopped() {
            CatalystInstanceImpl catalystInstanceImpl = this.mOuter.get();
            if (catalystInstanceImpl != null) {
                ((Systrace) catalystInstanceImpl.getJSModule(Systrace.class)).setEnabled(false);
            }
        }
    }

    public static class Builder {
        private ReactInstanceManagerInspectorTarget mInspectorTarget;
        private JSBundleLoader mJSBundleLoader;
        private JSExceptionHandler mJSExceptionHandler;
        private JavaScriptExecutor mJSExecutor;
        private ReactQueueConfigurationSpec mReactQueueConfigurationSpec;
        private NativeModuleRegistry mRegistry;

        public Builder setReactQueueConfigurationSpec(ReactQueueConfigurationSpec reactQueueConfigurationSpec) {
            this.mReactQueueConfigurationSpec = reactQueueConfigurationSpec;
            return this;
        }

        public Builder setRegistry(NativeModuleRegistry nativeModuleRegistry) {
            this.mRegistry = nativeModuleRegistry;
            return this;
        }

        public Builder setJSBundleLoader(JSBundleLoader jSBundleLoader) {
            this.mJSBundleLoader = jSBundleLoader;
            return this;
        }

        public Builder setJSExecutor(JavaScriptExecutor javaScriptExecutor) {
            this.mJSExecutor = javaScriptExecutor;
            return this;
        }

        public Builder setJSExceptionHandler(JSExceptionHandler jSExceptionHandler) {
            this.mJSExceptionHandler = jSExceptionHandler;
            return this;
        }

        public Builder setInspectorTarget(ReactInstanceManagerInspectorTarget reactInstanceManagerInspectorTarget) {
            this.mInspectorTarget = reactInstanceManagerInspectorTarget;
            return this;
        }

        public CatalystInstanceImpl build() {
            return new CatalystInstanceImpl((ReactQueueConfigurationSpec) Assertions.assertNotNull(this.mReactQueueConfigurationSpec), (JavaScriptExecutor) Assertions.assertNotNull(this.mJSExecutor), (NativeModuleRegistry) Assertions.assertNotNull(this.mRegistry), (JSBundleLoader) Assertions.assertNotNull(this.mJSBundleLoader), (JSExceptionHandler) Assertions.assertNotNull(this.mJSExceptionHandler), this.mInspectorTarget);
        }
    }
}
