package com.facebook.react.fabric;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.SystemClock;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.proguard.annotations.DoNotStripAny;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerListener;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.common.mapbuffer.ReadableMapBuffer;
import com.facebook.react.fabric.DevToolsReactPerfLogger;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.events.FabricEventEmitter;
import com.facebook.react.fabric.internal.interop.InteropUIBlockListener;
import com.facebook.react.fabric.interop.UIBlock;
import com.facebook.react.fabric.interop.UIBlockViewResolver;
import com.facebook.react.fabric.mounting.LayoutMetricsConversions;
import com.facebook.react.fabric.mounting.MountItemDispatcher;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.SurfaceMountingManager;
import com.facebook.react.fabric.mounting.mountitems.BatchMountItem;
import com.facebook.react.fabric.mounting.mountitems.DispatchCommandMountItem;
import com.facebook.react.fabric.mounting.mountitems.MountItem;
import com.facebook.react.fabric.mounting.mountitems.MountItemFactory;
import com.facebook.react.interfaces.fabric.SurfaceHandler;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.internal.interop.InteropEventEmitter;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.GuardedFrameCallback;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactRoot;
import com.facebook.react.uimanager.ReactRootViewTagGenerator;
import com.facebook.react.uimanager.RootViewUtil;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewManagerPropertyUpdater;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.BatchEventDispatchedListener;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.FabricEventDispatcher;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.RCTModernEventEmitter;
import com.facebook.react.uimanager.events.SynchronousEventReceiver;
import com.facebook.react.views.text.TextLayoutManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@DoNotStripAny
@SuppressLint({"MissingNativeLoadLibrary"})
public class FabricUIManager implements UIManager, LifecycleEventListener, UIBlockViewResolver, SynchronousEventReceiver {
    private static final DevToolsReactPerfLogger.DevToolsReactPerfLoggerListener FABRIC_PERF_LOGGER = new FabricUIManager$$ExternalSyntheticLambda0();
    public static final boolean IS_DEVELOPMENT_ENVIRONMENT = false;
    public static final String TAG = "FabricUIManager";
    private final BatchEventDispatchedListener mBatchEventDispatchedListener;
    /* access modifiers changed from: private */
    public FabricUIManagerBinding mBinding;
    private long mCommitStartTime = 0;
    private int mCurrentSynchronousCommitNumber = 10000;
    /* access modifiers changed from: private */
    public volatile boolean mDestroyed = false;
    public DevToolsReactPerfLogger mDevToolsReactPerfLogger;
    private final DispatchUIFrameCallback mDispatchUIFrameCallback;
    private long mDispatchViewUpdatesTime = 0;
    /* access modifiers changed from: private */
    public boolean mDriveCxxAnimations = false;
    private final EventDispatcher mEventDispatcher;
    private long mFinishTransactionCPPTime = 0;
    private long mFinishTransactionTime = 0;
    private InteropUIBlockListener mInteropUIBlockListener;
    private long mLayoutTime = 0;
    /* access modifiers changed from: private */
    public final CopyOnWriteArrayList<UIManagerListener> mListeners = new CopyOnWriteArrayList<>();
    /* access modifiers changed from: private */
    public final MountItemDispatcher mMountItemDispatcher;
    private final MountingManager.MountItemExecutor mMountItemExecutor;
    /* access modifiers changed from: private */
    public boolean mMountNotificationScheduled = false;
    private final MountingManager mMountingManager;
    private final ReactApplicationContext mReactApplicationContext;
    /* access modifiers changed from: private */
    public List<Integer> mSurfaceIdsWithPendingMountNotification = new ArrayList();
    /* access modifiers changed from: private */
    public final Set<SynchronousEvent> mSynchronousEvents = new HashSet();
    private final ViewManagerRegistry mViewManagerRegistry;

    public void onHostDestroy() {
    }

    public void profileNextBatch() {
    }

    static {
        FabricSoLoader.staticInit();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$0(DevToolsReactPerfLogger.FabricCommitPoint fabricCommitPoint) {
        long commitDuration = fabricCommitPoint.getCommitDuration();
        long layoutDuration = fabricCommitPoint.getLayoutDuration();
        long diffDuration = fabricCommitPoint.getDiffDuration();
        long transactionEndDuration = fabricCommitPoint.getTransactionEndDuration();
        long batchExecutionDuration = fabricCommitPoint.getBatchExecutionDuration();
        LongStreamingStats longStreamingStats = DevToolsReactPerfLogger.mStreamingCommitStats;
        longStreamingStats.add(commitDuration);
        LongStreamingStats longStreamingStats2 = DevToolsReactPerfLogger.mStreamingLayoutStats;
        longStreamingStats2.add(layoutDuration);
        LongStreamingStats longStreamingStats3 = DevToolsReactPerfLogger.mStreamingDiffStats;
        longStreamingStats3.add(diffDuration);
        LongStreamingStats longStreamingStats4 = DevToolsReactPerfLogger.mStreamingTransactionEndStats;
        longStreamingStats4.add(transactionEndDuration);
        LongStreamingStats longStreamingStats5 = DevToolsReactPerfLogger.mStreamingBatchExecutionStats;
        longStreamingStats5.add(batchExecutionDuration);
        FLog.i(TAG, "Statistics of Fabric commit #%d:\n - Total commit time: %d ms. Avg: %.2f. Median: %.2f ms. Max: %d ms.\n - Layout time: %d ms. Avg: %.2f. Median: %.2f ms. Max: %d ms.\n - Diffing time: %d ms. Avg: %.2f. Median: %.2f ms. Max: %d ms.\n - FinishTransaction (Diffing + JNI serialization): %d ms. Avg: %.2f. Median: %.2f ms. Max: %d ms.\n - Mounting: %d ms. Avg: %.2f. Median: %.2f ms. Max: %d ms.\n", Long.valueOf(fabricCommitPoint.getCommitNumber()), Long.valueOf(commitDuration), Double.valueOf(longStreamingStats.getAverage()), Double.valueOf(longStreamingStats.getMedian()), Long.valueOf(longStreamingStats.getMax()), Long.valueOf(layoutDuration), Double.valueOf(longStreamingStats2.getAverage()), Double.valueOf(longStreamingStats2.getMedian()), Long.valueOf(longStreamingStats2.getMax()), Long.valueOf(diffDuration), Double.valueOf(longStreamingStats3.getAverage()), Double.valueOf(longStreamingStats3.getMedian()), Long.valueOf(longStreamingStats3.getMax()), Long.valueOf(transactionEndDuration), Double.valueOf(longStreamingStats4.getAverage()), Double.valueOf(longStreamingStats4.getMedian()), Long.valueOf(longStreamingStats4.getMax()), Long.valueOf(batchExecutionDuration), Double.valueOf(longStreamingStats5.getAverage()), Double.valueOf(longStreamingStats5.getMedian()), Long.valueOf(longStreamingStats5.getMax()));
    }

    public FabricUIManager(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, BatchEventDispatchedListener batchEventDispatchedListener) {
        AnonymousClass1 r0 = new MountingManager.MountItemExecutor() {
            public void executeItems(Queue<MountItem> queue) {
                FabricUIManager.this.mMountItemDispatcher.dispatchMountItems(queue);
            }
        };
        this.mMountItemExecutor = r0;
        this.mDispatchUIFrameCallback = new DispatchUIFrameCallback(reactApplicationContext);
        this.mReactApplicationContext = reactApplicationContext;
        MountingManager mountingManager = new MountingManager(viewManagerRegistry, r0);
        this.mMountingManager = mountingManager;
        this.mMountItemDispatcher = new MountItemDispatcher(mountingManager, new MountItemDispatchListener());
        this.mEventDispatcher = new FabricEventDispatcher(reactApplicationContext);
        this.mBatchEventDispatchedListener = batchEventDispatchedListener;
        reactApplicationContext.addLifecycleEventListener(this);
        this.mViewManagerRegistry = viewManagerRegistry;
        reactApplicationContext.registerComponentCallbacks(viewManagerRegistry);
    }

    @Deprecated
    public <T extends View> int addRootView(T t, WritableMap writableMap) {
        String str = TAG;
        ReactSoftExceptionLogger.logSoftException(str, new IllegalViewOperationException("Do not call addRootView in Fabric; it is unsupported. Call startSurface instead."));
        ReactRoot reactRoot = (ReactRoot) t;
        int rootViewTag = reactRoot.getRootViewTag();
        this.mMountingManager.startSurface(rootViewTag, new ThemedReactContext(this.mReactApplicationContext, t.getContext(), reactRoot.getSurfaceID(), rootViewTag), t);
        String jSModuleName = reactRoot.getJSModuleName();
        if (ReactNativeFeatureFlags.enableFabricLogs()) {
            FLog.d(str, "Starting surface for module: %s and reactTag: %d", jSModuleName, Integer.valueOf(rootViewTag));
        }
        this.mBinding.startSurface(rootViewTag, jSModuleName, (NativeMap) writableMap);
        return rootViewTag;
    }

    public <T extends View> int startSurface(T t, String str, WritableMap writableMap, int i, int i2) {
        T t2 = t;
        String str2 = str;
        int rootViewTag = ((ReactRoot) t2).getRootViewTag();
        Context context = t.getContext();
        ThemedReactContext themedReactContext = new ThemedReactContext(this.mReactApplicationContext, context, str2, rootViewTag);
        if (ReactNativeFeatureFlags.enableFabricLogs()) {
            FLog.d(TAG, "Starting surface for module: %s and reactTag: %d", str2, Integer.valueOf(rootViewTag));
        }
        this.mMountingManager.startSurface(rootViewTag, themedReactContext, t2);
        Point viewportOffset = UiThreadUtil.isOnUiThread() ? RootViewUtil.getViewportOffset(t) : new Point(0, 0);
        this.mBinding.startSurfaceWithConstraints(rootViewTag, str, (NativeMap) writableMap, LayoutMetricsConversions.getMinSize(i), LayoutMetricsConversions.getMaxSize(i), LayoutMetricsConversions.getMinSize(i2), LayoutMetricsConversions.getMaxSize(i2), (float) viewportOffset.x, (float) viewportOffset.y, I18nUtil.getInstance().isRTL(context), I18nUtil.getInstance().doLeftAndRightSwapInRTL(context));
        return rootViewTag;
    }

    public void startSurface(SurfaceHandler surfaceHandler, Context context, View view) {
        int nextRootViewTag = ReactRootViewTagGenerator.getNextRootViewTag();
        this.mMountingManager.startSurface(nextRootViewTag, new ThemedReactContext(this.mReactApplicationContext, context, surfaceHandler.getModuleName(), nextRootViewTag), view);
        if (surfaceHandler instanceof SurfaceHandlerBinding) {
            this.mBinding.startSurfaceWithSurfaceHandler(nextRootViewTag, (SurfaceHandlerBinding) surfaceHandler, view != null);
            return;
        }
        throw new IllegalArgumentException("Invalid SurfaceHandler");
    }

    public void attachRootView(SurfaceHandler surfaceHandler, View view) {
        this.mMountingManager.attachRootView(surfaceHandler.getSurfaceId(), view, new ThemedReactContext(this.mReactApplicationContext, view.getContext(), surfaceHandler.getModuleName(), surfaceHandler.getSurfaceId()));
        surfaceHandler.setMountable(true);
    }

    public void stopSurface(SurfaceHandler surfaceHandler) {
        if (!surfaceHandler.isRunning()) {
            ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("Trying to stop surface that hasn't started yet"));
            return;
        }
        this.mMountingManager.stopSurface(surfaceHandler.getSurfaceId());
        if (surfaceHandler instanceof SurfaceHandlerBinding) {
            this.mBinding.stopSurfaceWithSurfaceHandler((SurfaceHandlerBinding) surfaceHandler);
            return;
        }
        throw new IllegalArgumentException("Invalid SurfaceHandler");
    }

    public void onRequestEventBeat() {
        this.mEventDispatcher.dispatchAllEvents();
    }

    public void stopSurface(int i) {
        this.mMountingManager.stopSurface(i);
        this.mBinding.stopSurface(i);
    }

    public void initialize() {
        this.mEventDispatcher.registerEventEmitter(2, (RCTModernEventEmitter) new FabricEventEmitter(this));
        this.mEventDispatcher.addBatchEventDispatchedListener(this.mBatchEventDispatchedListener);
        if (ReactNativeFeatureFlags.enableFabricLogs()) {
            DevToolsReactPerfLogger devToolsReactPerfLogger = new DevToolsReactPerfLogger();
            this.mDevToolsReactPerfLogger = devToolsReactPerfLogger;
            devToolsReactPerfLogger.addDevToolsReactPerfLoggerListener(FABRIC_PERF_LOGGER);
            ReactMarker.addFabricListener(this.mDevToolsReactPerfLogger);
        }
        if (ReactNativeFeatureFlags.useFabricInterop()) {
            this.mReactApplicationContext.internal_registerInteropModule(RCTEventEmitter.class, new InteropEventEmitter(this.mReactApplicationContext));
        }
    }

    public void invalidate() {
        String str = TAG;
        FLog.i(str, "FabricUIManager.invalidate");
        DevToolsReactPerfLogger devToolsReactPerfLogger = this.mDevToolsReactPerfLogger;
        if (devToolsReactPerfLogger != null) {
            devToolsReactPerfLogger.removeDevToolsReactPerfLoggerListener(FABRIC_PERF_LOGGER);
            ReactMarker.removeFabricListener(this.mDevToolsReactPerfLogger);
        }
        if (this.mDestroyed) {
            ReactSoftExceptionLogger.logSoftException(str, new IllegalStateException("Cannot double-destroy FabricUIManager"));
            return;
        }
        this.mDestroyed = true;
        this.mEventDispatcher.removeBatchEventDispatchedListener(this.mBatchEventDispatchedListener);
        this.mEventDispatcher.unregisterEventEmitter(2);
        this.mReactApplicationContext.unregisterComponentCallbacks(this.mViewManagerRegistry);
        this.mViewManagerRegistry.invalidate();
        this.mReactApplicationContext.removeLifecycleEventListener(this);
        onHostPause();
        this.mBinding.unregister();
        this.mBinding = null;
        ViewManagerPropertyUpdater.clear();
        if (!ReactNativeFeatureFlags.enableBridgelessArchitecture()) {
            this.mEventDispatcher.onCatalystInstanceDestroyed();
        }
    }

    public void markActiveTouchForTag(int i, int i2) {
        SurfaceMountingManager surfaceManager = this.mMountingManager.getSurfaceManager(i);
        if (surfaceManager != null) {
            surfaceManager.markActiveTouchForTag(i2);
        }
    }

    public void sweepActiveTouchForTag(int i, int i2) {
        SurfaceMountingManager surfaceManager = this.mMountingManager.getSurfaceManager(i);
        if (surfaceManager != null) {
            surfaceManager.sweepActiveTouchForTag(i2);
        }
    }

    public void addUIBlock(UIBlock uIBlock) {
        if (ReactNativeFeatureFlags.useFabricInterop()) {
            getInteropUIBlockListener().addUIBlock(uIBlock);
        }
    }

    public void prependUIBlock(UIBlock uIBlock) {
        if (ReactNativeFeatureFlags.useFabricInterop()) {
            getInteropUIBlockListener().prependUIBlock(uIBlock);
        }
    }

    private InteropUIBlockListener getInteropUIBlockListener() {
        if (this.mInteropUIBlockListener == null) {
            InteropUIBlockListener interopUIBlockListener = new InteropUIBlockListener();
            this.mInteropUIBlockListener = interopUIBlockListener;
            addUIManagerEventListener(interopUIBlockListener);
        }
        return this.mInteropUIBlockListener;
    }

    private NativeArray measureLines(ReadableMapBuffer readableMapBuffer, ReadableMapBuffer readableMapBuffer2, float f, float f2) {
        return (NativeArray) TextLayoutManager.measureLines(this.mReactApplicationContext, readableMapBuffer, readableMapBuffer2, PixelUtil.toPixelFromDIP(f), PixelUtil.toPixelFromDIP(f2));
    }

    private long measure(int i, String str, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, float f2, float f3, float f4) {
        return measure(i, str, readableMap, readableMap2, readableMap3, f, f2, f3, f4, (float[]) null);
    }

    public int getColor(int i, String[] strArr) {
        ThemedReactContext context = this.mMountingManager.getSurfaceManagerEnforced(i, "getColor").getContext();
        if (context == null) {
            return 0;
        }
        for (String resolveResourcePath : strArr) {
            Integer resolveResourcePath2 = ColorPropConverter.resolveResourcePath(context, resolveResourcePath);
            if (resolveResourcePath2 != null) {
                return resolveResourcePath2.intValue();
            }
        }
        return 0;
    }

    private long measure(int i, String str, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, float f2, float f3, float f4, float[] fArr) {
        ReactContext reactContext;
        if (i > 0) {
            SurfaceMountingManager surfaceManagerEnforced = this.mMountingManager.getSurfaceManagerEnforced(i, "measure");
            if (surfaceManagerEnforced.isStopped()) {
                return 0;
            }
            reactContext = surfaceManagerEnforced.getContext();
        } else {
            reactContext = this.mReactApplicationContext;
        }
        return this.mMountingManager.measure(reactContext, str, readableMap, readableMap2, readableMap3, LayoutMetricsConversions.getYogaSize(f, f2), LayoutMetricsConversions.getYogaMeasureMode(f, f2), LayoutMetricsConversions.getYogaSize(f3, f4), LayoutMetricsConversions.getYogaMeasureMode(f3, f4), fArr);
    }

    private long measureMapBuffer(int i, String str, ReadableMapBuffer readableMapBuffer, ReadableMapBuffer readableMapBuffer2, ReadableMapBuffer readableMapBuffer3, float f, float f2, float f3, float f4, float[] fArr) {
        ReactContext reactContext;
        if (i > 0) {
            SurfaceMountingManager surfaceManagerEnforced = this.mMountingManager.getSurfaceManagerEnforced(i, "measure");
            if (surfaceManagerEnforced.isStopped()) {
                return 0;
            }
            reactContext = surfaceManagerEnforced.getContext();
        } else {
            reactContext = this.mReactApplicationContext;
        }
        return this.mMountingManager.measureMapBuffer(reactContext, str, readableMapBuffer, readableMapBuffer2, readableMapBuffer3, LayoutMetricsConversions.getYogaSize(f, f2), LayoutMetricsConversions.getYogaMeasureMode(f, f2), LayoutMetricsConversions.getYogaSize(f3, f4), LayoutMetricsConversions.getYogaMeasureMode(f3, f4), fArr);
    }

    public boolean getThemeData(int i, float[] fArr) {
        SurfaceMountingManager surfaceManager = this.mMountingManager.getSurfaceManager(i);
        ThemedReactContext context = surfaceManager != null ? surfaceManager.getContext() : null;
        if (context == null) {
            FLog.w(TAG, "Couldn't get context for surfaceId %d in getThemeData", Integer.valueOf(i));
            return false;
        }
        float[] defaultTextInputPadding = UIManagerHelper.getDefaultTextInputPadding(context);
        fArr[0] = defaultTextInputPadding[0];
        fArr[1] = defaultTextInputPadding[1];
        fArr[2] = defaultTextInputPadding[2];
        fArr[3] = defaultTextInputPadding[3];
        return true;
    }

    public void addUIManagerEventListener(UIManagerListener uIManagerListener) {
        this.mListeners.add(uIManagerListener);
    }

    public void removeUIManagerEventListener(UIManagerListener uIManagerListener) {
        this.mListeners.remove(uIManagerListener);
    }

    public void synchronouslyUpdateViewOnUIThread(final int i, final ReadableMap readableMap) {
        UiThreadUtil.assertOnUiThread();
        int i2 = this.mCurrentSynchronousCommitNumber;
        this.mCurrentSynchronousCommitNumber = i2 + 1;
        AnonymousClass2 r1 = new MountItem() {
            public int getSurfaceId() {
                return -1;
            }

            public void execute(MountingManager mountingManager) {
                try {
                    mountingManager.updateProps(i, readableMap);
                } catch (Exception unused) {
                }
            }

            public String toString() {
                return String.format("SYNC UPDATE PROPS [%d]: %s", new Object[]{Integer.valueOf(i), FabricUIManager.IS_DEVELOPMENT_ENVIRONMENT ? readableMap.toHashMap().toString() : "<hidden>"});
            }
        };
        if (!this.mMountingManager.getViewExists(i)) {
            this.mMountItemDispatcher.addMountItem(r1);
            return;
        }
        ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_UPDATE_UI_MAIN_THREAD_START, (String) null, i2);
        if (ReactNativeFeatureFlags.enableFabricLogs()) {
            FLog.d(TAG, "SynchronouslyUpdateViewOnUIThread for tag %d: %s", Integer.valueOf(i), IS_DEVELOPMENT_ENVIRONMENT ? readableMap.toHashMap().toString() : "<hidden>");
        }
        r1.execute(this.mMountingManager);
        ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_UPDATE_UI_MAIN_THREAD_END, (String) null, i2);
    }

    private void preallocateView(int i, int i2, String str, Object obj, Object obj2, boolean z) {
        this.mMountItemDispatcher.addPreAllocateMountItem(MountItemFactory.createPreAllocateViewMountItem(i, i2, str, (ReadableMap) obj, (StateWrapper) obj2, z));
    }

    private void destroyUnmountedView(int i, int i2) {
        this.mMountItemDispatcher.addMountItem(MountItemFactory.createDestroyViewMountItem(i, i2));
    }

    @SuppressLint({"NotInvokedPrivateMethod"})
    private boolean isOnMainThread() {
        return UiThreadUtil.isOnUiThread();
    }

    private MountItem createIntBufferBatchMountItem(int i, int[] iArr, Object[] objArr, int i2) {
        if (iArr == null) {
            iArr = new int[0];
        }
        if (objArr == null) {
            objArr = new Object[0];
        }
        return MountItemFactory.createIntBufferBatchMountItem(i, iArr, objArr, i2);
    }

    private void scheduleMountItem(MountItem mountItem, int i, long j, long j2, long j3, long j4, long j5, long j6, long j7, int i2) {
        MountItem mountItem2 = mountItem;
        int i3 = i;
        long j8 = j;
        long j9 = j4;
        long j10 = j5;
        long j11 = j6;
        long j12 = j7;
        long uptimeMillis = SystemClock.uptimeMillis();
        boolean z = mountItem2 instanceof BatchMountItem;
        boolean z2 = (z && !((BatchMountItem) mountItem2).isBatchEmpty()) || (!z && mountItem2 != null);
        for (Iterator<UIManagerListener> it = this.mListeners.iterator(); it.hasNext(); it = it) {
            it.next().didScheduleMountItems(this);
        }
        if (z) {
            this.mCommitStartTime = j8;
            this.mLayoutTime = j10 - j9;
            this.mFinishTransactionCPPTime = j12 - j11;
            this.mFinishTransactionTime = uptimeMillis - j11;
            this.mDispatchViewUpdatesTime = SystemClock.uptimeMillis();
        }
        if (z2) {
            this.mMountItemDispatcher.addMountItem(mountItem2);
            AnonymousClass3 r1 = new GuardedRunnable(this.mReactApplicationContext) {
                public void runGuarded() {
                    FabricUIManager.this.mMountItemDispatcher.tryDispatchMountItems();
                }
            };
            if (UiThreadUtil.isOnUiThread()) {
                r1.run();
            }
        }
        if (z) {
            int i4 = i;
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_COMMIT_START, (String) null, i4, j);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_FINISH_TRANSACTION_START, (String) null, i4, j11);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_FINISH_TRANSACTION_END, (String) null, i4, j12);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_DIFF_START, (String) null, i4, j2);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_DIFF_END, (String) null, i4, j3);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_LAYOUT_START, (String) null, i4, j9);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_LAYOUT_END, (String) null, i4, j10);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_LAYOUT_AFFECTED_NODES, (String) null, i, j5, i2);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_COMMIT_END, (String) null, i4);
        }
    }

    @UnstableReactNativeAPI
    public void experimental_prefetchResource(String str, int i, int i2, ReadableMapBuffer readableMapBuffer) {
        this.mMountingManager.experimental_prefetchResource(this.mReactApplicationContext, str, i, i2, readableMapBuffer);
    }

    public void setBinding(FabricUIManagerBinding fabricUIManagerBinding) {
        this.mBinding = fabricUIManagerBinding;
    }

    public void updateRootLayoutSpecs(int i, int i2, int i3, int i4, int i5) {
        boolean z;
        boolean z2;
        int i6 = i;
        if (ReactNativeFeatureFlags.enableFabricLogs()) {
            FLog.d(TAG, "Updating Root Layout Specs for [%d]", (Object) Integer.valueOf(i));
        }
        SurfaceMountingManager surfaceManager = this.mMountingManager.getSurfaceManager(i);
        if (surfaceManager == null) {
            ReactSoftExceptionLogger.logSoftException(TAG, new IllegalViewOperationException("Cannot updateRootLayoutSpecs on surfaceId that does not exist: " + i));
            return;
        }
        ThemedReactContext context = surfaceManager.getContext();
        if (context != null) {
            boolean isRTL = I18nUtil.getInstance().isRTL(context);
            z = I18nUtil.getInstance().doLeftAndRightSwapInRTL(context);
            z2 = isRTL;
        } else {
            z2 = false;
            z = false;
        }
        this.mBinding.setConstraints(i, LayoutMetricsConversions.getMinSize(i2), LayoutMetricsConversions.getMaxSize(i2), LayoutMetricsConversions.getMinSize(i3), LayoutMetricsConversions.getMaxSize(i3), (float) i4, (float) i5, z2, z);
    }

    public View resolveView(int i) {
        UiThreadUtil.assertOnUiThread();
        SurfaceMountingManager surfaceManagerForView = this.mMountingManager.getSurfaceManagerForView(i);
        if (surfaceManagerForView == null) {
            return null;
        }
        return surfaceManagerForView.getView(i);
    }

    public void receiveEvent(int i, String str, WritableMap writableMap) {
        receiveEvent(-1, i, str, false, writableMap, 2);
    }

    public void receiveEvent(int i, int i2, String str, WritableMap writableMap) {
        receiveEvent(i, i2, str, false, writableMap, 2);
    }

    public void receiveEvent(int i, int i2, String str, boolean z, WritableMap writableMap, int i3) {
        receiveEvent(i, i2, str, z, writableMap, i3, false);
    }

    public void receiveEvent(int i, int i2, String str, boolean z, WritableMap writableMap, int i3, boolean z2) {
        if (ReactBuildConfig.DEBUG && i == -1) {
            FLog.d(TAG, "Emitted event without surfaceId: [%d] %s", Integer.valueOf(i2), str);
        }
        if (this.mDestroyed) {
            FLog.e(TAG, "Attempted to receiveEvent after destruction");
            return;
        }
        EventEmitterWrapper eventEmitter = this.mMountingManager.getEventEmitter(i, i2);
        if (eventEmitter == null) {
            if (this.mMountingManager.getViewExists(i2)) {
                this.mMountingManager.enqueuePendingEvent(i, i2, str, z, writableMap, i3);
                return;
            }
            String str2 = TAG;
            FLog.d(str2, "Unable to invoke event: " + str + " for reactTag: " + i2);
        } else if (z2) {
            if (this.mSynchronousEvents.add(new SynchronousEvent(i, i2, str))) {
                eventEmitter.dispatchEventSynchronously(str, writableMap);
            }
        } else if (z) {
            eventEmitter.dispatchUnique(str, writableMap);
        } else {
            eventEmitter.dispatch(str, writableMap, i3);
        }
    }

    public void onHostResume() {
        this.mDispatchUIFrameCallback.resume();
    }

    public EventDispatcher getEventDispatcher() {
        return this.mEventDispatcher;
    }

    public void onHostPause() {
        this.mDispatchUIFrameCallback.pause();
    }

    @Deprecated
    public void dispatchCommand(int i, int i2, ReadableArray readableArray) {
        throw new UnsupportedOperationException("dispatchCommand called without surfaceId - Fabric dispatchCommand must be called through Fabric JSI API");
    }

    @Deprecated
    public void dispatchCommand(int i, String str, ReadableArray readableArray) {
        throw new UnsupportedOperationException("dispatchCommand called without surfaceId - Fabric dispatchCommand must be called through Fabric JSI API");
    }

    @Deprecated
    public void dispatchCommand(int i, int i2, int i3, ReadableArray readableArray) {
        this.mMountItemDispatcher.addViewCommandMountItem(MountItemFactory.createDispatchCommandMountItem(i, i2, i3, readableArray));
    }

    public void dispatchCommand(int i, int i2, String str, ReadableArray readableArray) {
        if (ReactNativeFeatureFlags.useFabricInterop()) {
            this.mMountItemDispatcher.addViewCommandMountItem(createDispatchCommandMountItemForInterop(i, i2, str, readableArray));
        } else {
            this.mMountItemDispatcher.addViewCommandMountItem(MountItemFactory.createDispatchCommandMountItem(i, i2, str, readableArray));
        }
    }

    public void sendAccessibilityEvent(int i, int i2) {
        this.mMountItemDispatcher.addMountItem(MountItemFactory.createSendAccessibilityEventMountItem(-1, i, i2));
    }

    public void sendAccessibilityEventFromJS(int i, int i2, String str) {
        int i3;
        if ("focus".equals(str)) {
            i3 = 8;
        } else if ("windowStateChange".equals(str)) {
            i3 = 32;
        } else if ("click".equals(str)) {
            i3 = 1;
        } else if ("viewHoverEnter".equals(str)) {
            i3 = 128;
        } else {
            throw new IllegalArgumentException("sendAccessibilityEventFromJS: invalid eventType " + str);
        }
        this.mMountItemDispatcher.addMountItem(MountItemFactory.createSendAccessibilityEventMountItem(i, i2, i3));
    }

    public void setJSResponder(int i, int i2, int i3, boolean z) {
        final int i4 = i;
        final int i5 = i2;
        final int i6 = i3;
        final boolean z2 = z;
        this.mMountItemDispatcher.addMountItem(new MountItem() {
            public void execute(MountingManager mountingManager) {
                SurfaceMountingManager surfaceManager = mountingManager.getSurfaceManager(i4);
                if (surfaceManager != null) {
                    surfaceManager.setJSResponder(i5, i6, z2);
                    return;
                }
                String str = FabricUIManager.TAG;
                FLog.e(str, "setJSResponder skipped, surface no longer available [" + i4 + "]");
            }

            public int getSurfaceId() {
                return i4;
            }

            @SuppressLint({"DefaultLocale"})
            public String toString() {
                return String.format("SET_JS_RESPONDER [%d] [surface:%d]", new Object[]{Integer.valueOf(i5), Integer.valueOf(i4)});
            }
        });
    }

    public void clearJSResponder() {
        this.mMountItemDispatcher.addMountItem(new MountItem() {
            public int getSurfaceId() {
                return -1;
            }

            public void execute(MountingManager mountingManager) {
                mountingManager.clearJSResponder();
            }

            public String toString() {
                return "CLEAR_JS_RESPONDER";
            }
        });
    }

    @Deprecated
    public String resolveCustomDirectEventName(String str) {
        if (str == null) {
            return null;
        }
        if (!str.startsWith(ViewProps.TOP)) {
            return str;
        }
        return ViewProps.ON + str.substring(3);
    }

    public void onAnimationStarted() {
        this.mDriveCxxAnimations = true;
    }

    public void onAllAnimationsComplete() {
        this.mDriveCxxAnimations = false;
    }

    public Map<String, Long> getPerformanceCounters() {
        HashMap hashMap = new HashMap();
        hashMap.put("CommitStartTime", Long.valueOf(this.mCommitStartTime));
        hashMap.put("LayoutTime", Long.valueOf(this.mLayoutTime));
        hashMap.put("DispatchViewUpdatesTime", Long.valueOf(this.mDispatchViewUpdatesTime));
        hashMap.put("RunStartTime", Long.valueOf(this.mMountItemDispatcher.getRunStartTime()));
        hashMap.put("BatchedExecutionTime", Long.valueOf(this.mMountItemDispatcher.getBatchedExecutionTime()));
        hashMap.put("FinishFabricTransactionTime", Long.valueOf(this.mFinishTransactionTime));
        hashMap.put("FinishFabricTransactionCPPTime", Long.valueOf(this.mFinishTransactionCPPTime));
        return hashMap;
    }

    private class MountItemDispatchListener implements MountItemDispatcher.ItemDispatchListener {
        private MountItemDispatchListener() {
        }

        public void willMountItems(List<MountItem> list) {
            Iterator it = FabricUIManager.this.mListeners.iterator();
            while (it.hasNext()) {
                ((UIManagerListener) it.next()).willMountItems(FabricUIManager.this);
            }
        }

        public void didMountItems(List<MountItem> list) {
            Iterator it = FabricUIManager.this.mListeners.iterator();
            while (it.hasNext()) {
                ((UIManagerListener) it.next()).didMountItems(FabricUIManager.this);
            }
            if (list != null && !list.isEmpty()) {
                for (MountItem next : list) {
                    if (next != null && !FabricUIManager.this.mSurfaceIdsWithPendingMountNotification.contains(Integer.valueOf(next.getSurfaceId()))) {
                        FabricUIManager.this.mSurfaceIdsWithPendingMountNotification.add(Integer.valueOf(next.getSurfaceId()));
                    }
                }
                if (!FabricUIManager.this.mMountNotificationScheduled && !FabricUIManager.this.mSurfaceIdsWithPendingMountNotification.isEmpty()) {
                    FabricUIManager.this.mMountNotificationScheduled = true;
                    UiThreadUtil.getUiThreadHandler().postAtFrontOfQueue(new Runnable() {
                        public void run() {
                            FabricUIManager.this.mMountNotificationScheduled = false;
                            List<Integer> r0 = FabricUIManager.this.mSurfaceIdsWithPendingMountNotification;
                            FabricUIManager.this.mSurfaceIdsWithPendingMountNotification = new ArrayList();
                            FabricUIManagerBinding r1 = FabricUIManager.this.mBinding;
                            if (r1 != null && !FabricUIManager.this.mDestroyed) {
                                for (Integer intValue : r0) {
                                    r1.reportMount(intValue.intValue());
                                }
                            }
                        }
                    });
                }
            }
        }

        public void didDispatchMountItems() {
            Iterator it = FabricUIManager.this.mListeners.iterator();
            while (it.hasNext()) {
                ((UIManagerListener) it.next()).didDispatchMountItems(FabricUIManager.this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public DispatchCommandMountItem createDispatchCommandMountItemForInterop(int i, int i2, String str, ReadableArray readableArray) {
        try {
            return MountItemFactory.createDispatchCommandMountItem(i, i2, Integer.parseInt(str), readableArray);
        } catch (NumberFormatException unused) {
            return MountItemFactory.createDispatchCommandMountItem(i, i2, str, readableArray);
        }
    }

    private class DispatchUIFrameCallback extends GuardedFrameCallback {
        private volatile boolean mIsMountingEnabled;
        private boolean mIsScheduled;
        private boolean mShouldSchedule;

        private DispatchUIFrameCallback(ReactContext reactContext) {
            super(reactContext);
            this.mIsMountingEnabled = true;
            this.mShouldSchedule = false;
            this.mIsScheduled = false;
        }

        private void schedule() {
            if (!this.mIsScheduled && this.mShouldSchedule) {
                this.mIsScheduled = true;
                ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this);
            }
        }

        /* access modifiers changed from: package-private */
        public void resume() {
            this.mShouldSchedule = true;
            schedule();
        }

        /* access modifiers changed from: package-private */
        public void pause() {
            ReactChoreographer.getInstance().removeFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this);
            this.mShouldSchedule = false;
            this.mIsScheduled = false;
        }

        public void doFrameGuarded(long j) {
            this.mIsScheduled = false;
            if (!this.mIsMountingEnabled) {
                FLog.w(FabricUIManager.TAG, "Not flushing pending UI operations: exception was previously thrown");
            } else if (FabricUIManager.this.mDestroyed) {
                FLog.w(FabricUIManager.TAG, "Not flushing pending UI operations: FabricUIManager is destroyed");
            } else {
                if (FabricUIManager.this.mDriveCxxAnimations && FabricUIManager.this.mBinding != null) {
                    FabricUIManager.this.mBinding.driveCxxAnimations();
                }
                if (ReactNativeFeatureFlags.useOptimisedViewPreallocationOnAndroid() && FabricUIManager.this.mBinding != null) {
                    FabricUIManager.this.mBinding.drainPreallocateViewsQueue();
                }
                try {
                    FabricUIManager.this.mMountItemDispatcher.dispatchPreMountItems(j);
                    FabricUIManager.this.mMountItemDispatcher.tryDispatchMountItems();
                    schedule();
                    FabricUIManager.this.mSynchronousEvents.clear();
                } catch (Exception e) {
                    FLog.e(FabricUIManager.TAG, "Exception thrown when executing UIFrameGuarded", (Throwable) e);
                    this.mIsMountingEnabled = false;
                    throw e;
                } catch (Throwable th) {
                    schedule();
                    throw th;
                }
            }
        }
    }
}
