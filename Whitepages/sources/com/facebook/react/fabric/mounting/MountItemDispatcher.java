package com.facebook.react.fabric.mounting;

import android.os.SystemClock;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactIgnorableMountingException;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.mounting.mountitems.DispatchCommandMountItem;
import com.facebook.react.fabric.mounting.mountitems.MountItem;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.systrace.Systrace;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MountItemDispatcher {
    private static final long FRAME_TIME_NS = 16666666;
    private static final String TAG = "MountItemDispatcher";
    private long mBatchedExecutionTime = 0;
    private boolean mInDispatch = false;
    private boolean mIsPremountScheduled = false;
    private final ItemDispatchListener mItemDispatchListener;
    private long mLastFrameTimeNanos = 0;
    private final ConcurrentLinkedQueue<MountItem> mMountItems = new ConcurrentLinkedQueue<>();
    private final MountingManager mMountingManager;
    private final ConcurrentLinkedQueue<MountItem> mPreMountItems = new ConcurrentLinkedQueue<>();
    private final Runnable mPremountRunnable = new MountItemDispatcher$$ExternalSyntheticLambda0(this);
    private long mRunStartTime = 0;
    private final ConcurrentLinkedQueue<DispatchCommandMountItem> mViewCommandMountItems = new ConcurrentLinkedQueue<>();

    public interface ItemDispatchListener {
        void didDispatchMountItems();

        void didMountItems(List<MountItem> list);

        void willMountItems(List<MountItem> list);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        this.mIsPremountScheduled = false;
        if (!this.mPreMountItems.isEmpty()) {
            dispatchPreMountItemsImpl(this.mLastFrameTimeNanos + 8333333);
        }
    }

    public MountItemDispatcher(MountingManager mountingManager, ItemDispatchListener itemDispatchListener) {
        this.mMountingManager = mountingManager;
        this.mItemDispatchListener = itemDispatchListener;
    }

    public void addViewCommandMountItem(DispatchCommandMountItem dispatchCommandMountItem) {
        this.mViewCommandMountItems.add(dispatchCommandMountItem);
    }

    public void addMountItem(MountItem mountItem) {
        this.mMountItems.add(mountItem);
    }

    public void addPreAllocateMountItem(MountItem mountItem) {
        if (!this.mMountingManager.surfaceIsStopped(mountItem.getSurfaceId())) {
            this.mPreMountItems.add(mountItem);
        } else if (FabricUIManager.IS_DEVELOPMENT_ENVIRONMENT) {
            FLog.e(TAG, "Not queueing PreAllocateMountItem: surfaceId stopped: [%d] - %s", Integer.valueOf(mountItem.getSurfaceId()), mountItem.toString());
        }
    }

    /* JADX INFO: finally extract failed */
    public void tryDispatchMountItems() {
        if (!this.mInDispatch) {
            this.mInDispatch = true;
            try {
                dispatchMountItems();
                this.mInDispatch = false;
                this.mItemDispatchListener.didDispatchMountItems();
            } catch (Throwable th) {
                this.mInDispatch = false;
                throw th;
            }
        }
    }

    public void dispatchMountItems(Queue<MountItem> queue) {
        while (!queue.isEmpty()) {
            MountItem poll = queue.poll();
            try {
                poll.execute(this.mMountingManager);
            } catch (RetryableMountingLayerException e) {
                if (poll instanceof DispatchCommandMountItem) {
                    DispatchCommandMountItem dispatchCommandMountItem = (DispatchCommandMountItem) poll;
                    if (dispatchCommandMountItem.getRetries() == 0) {
                        dispatchCommandMountItem.incrementRetries();
                        addViewCommandMountItem(dispatchCommandMountItem);
                    }
                } else {
                    printMountItem(poll, "dispatchExternalMountItems: mounting failed with " + e.getMessage());
                }
            }
        }
    }

    private void dispatchMountItems() {
        SurfaceMountingManager surfaceManager;
        this.mBatchedExecutionTime = 0;
        this.mRunStartTime = SystemClock.uptimeMillis();
        List<DispatchCommandMountItem> andResetViewCommandMountItems = getAndResetViewCommandMountItems();
        List<MountItem> andResetMountItems = getAndResetMountItems();
        if (andResetMountItems != null || andResetViewCommandMountItems != null) {
            this.mItemDispatchListener.willMountItems(andResetMountItems);
            if (andResetViewCommandMountItems != null) {
                Systrace.beginSection(0, "MountItemDispatcher::mountViews viewCommandMountItems");
                for (DispatchCommandMountItem next : andResetViewCommandMountItems) {
                    if (ReactNativeFeatureFlags.enableFabricLogs()) {
                        printMountItem(next, "dispatchMountItems: Executing viewCommandMountItem");
                    }
                    try {
                        executeOrEnqueue(next);
                    } catch (RetryableMountingLayerException e) {
                        if (next.getRetries() == 0) {
                            next.incrementRetries();
                            addViewCommandMountItem(next);
                        } else {
                            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Caught exception executing ViewCommand: " + next.toString(), e));
                        }
                    } catch (Throwable th) {
                        ReactSoftExceptionLogger.logSoftException(TAG, new RuntimeException("Caught exception executing ViewCommand: " + next.toString(), th));
                    }
                }
                Systrace.endSection(0);
            }
            List<MountItem> andResetPreMountItems = getAndResetPreMountItems();
            if (andResetPreMountItems != null) {
                Systrace.beginSection(0, "MountItemDispatcher::mountViews preMountItems");
                for (MountItem next2 : andResetPreMountItems) {
                    if (ReactNativeFeatureFlags.enableFabricLogs()) {
                        printMountItem(next2, "dispatchMountItems: Executing preMountItem");
                    }
                    executeOrEnqueue(next2);
                }
                Systrace.endSection(0);
            }
            if (andResetMountItems != null) {
                Systrace.beginSection(0, "MountItemDispatcher::mountViews mountItems to execute");
                long uptimeMillis = SystemClock.uptimeMillis();
                for (MountItem next3 : andResetMountItems) {
                    if (ReactNativeFeatureFlags.enableFabricLogs()) {
                        printMountItem(next3, "dispatchMountItems: Executing mountItem");
                    }
                    try {
                        executeOrEnqueue(next3);
                    } catch (Throwable th2) {
                        FLog.e(TAG, "dispatchMountItems: caught exception, displaying mount state", th2);
                        for (MountItem next4 : andResetMountItems) {
                            if (next4 == next3) {
                                FLog.e(TAG, "dispatchMountItems: mountItem: next mountItem triggered exception!");
                            }
                            printMountItem(next4, "dispatchMountItems: mountItem");
                        }
                        if (!(next3.getSurfaceId() == -1 || (surfaceManager = this.mMountingManager.getSurfaceManager(next3.getSurfaceId())) == null)) {
                            surfaceManager.printSurfaceState();
                        }
                        if (ReactIgnorableMountingException.isIgnorable(th2)) {
                            ReactSoftExceptionLogger.logSoftException(TAG, th2);
                        } else {
                            throw th2;
                        }
                    }
                }
                this.mBatchedExecutionTime += SystemClock.uptimeMillis() - uptimeMillis;
                Systrace.endSection(0);
            }
            this.mItemDispatchListener.didMountItems(andResetMountItems);
        }
    }

    public void dispatchPreMountItems(long j) {
        this.mLastFrameTimeNanos = j;
        if (!this.mPreMountItems.isEmpty()) {
            if (!ReactNativeFeatureFlags.enablePreciseSchedulingForPremountItemsOnAndroid()) {
                dispatchPreMountItemsImpl(this.mLastFrameTimeNanos + 8333333);
            } else if (!this.mIsPremountScheduled) {
                this.mIsPremountScheduled = true;
                UiThreadUtil.getUiThreadHandler().post(this.mPremountRunnable);
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private void dispatchPreMountItemsImpl(long j) {
        Systrace.beginSection(0, "MountItemDispatcher::premountViews");
        this.mInDispatch = true;
        while (true) {
            try {
                if (System.nanoTime() > j) {
                    break;
                }
                MountItem poll = this.mPreMountItems.poll();
                if (poll == null) {
                    break;
                }
                if (ReactNativeFeatureFlags.enableFabricLogs()) {
                    printMountItem(poll, "dispatchPreMountItems");
                }
                executeOrEnqueue(poll);
            } catch (Throwable th) {
                this.mInDispatch = false;
                throw th;
            }
        }
        this.mInDispatch = false;
        Systrace.endSection(0);
    }

    private void executeOrEnqueue(MountItem mountItem) {
        if (this.mMountingManager.isWaitingForViewAttach(mountItem.getSurfaceId())) {
            if (ReactNativeFeatureFlags.enableFabricLogs()) {
                FLog.e(TAG, "executeOrEnqueue: Item execution delayed, surface %s is not ready yet", Integer.valueOf(mountItem.getSurfaceId()));
            }
            this.mMountingManager.getSurfaceManager(mountItem.getSurfaceId()).scheduleMountItemOnViewAttach(mountItem);
            return;
        }
        mountItem.execute(this.mMountingManager);
    }

    private static <E> List<E> drainConcurrentItemQueue(ConcurrentLinkedQueue<E> concurrentLinkedQueue) {
        if (concurrentLinkedQueue.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        do {
            E poll = concurrentLinkedQueue.poll();
            if (poll != null) {
                arrayList.add(poll);
            }
        } while (!concurrentLinkedQueue.isEmpty());
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    private List<DispatchCommandMountItem> getAndResetViewCommandMountItems() {
        return drainConcurrentItemQueue(this.mViewCommandMountItems);
    }

    private List<MountItem> getAndResetMountItems() {
        return drainConcurrentItemQueue(this.mMountItems);
    }

    private List<MountItem> getAndResetPreMountItems() {
        return drainConcurrentItemQueue(this.mPreMountItems);
    }

    public long getBatchedExecutionTime() {
        return this.mBatchedExecutionTime;
    }

    public long getRunStartTime() {
        return this.mRunStartTime;
    }

    private static void printMountItem(MountItem mountItem, String str) {
        for (String str2 : mountItem.toString().split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE)) {
            FLog.e(TAG, str + ": " + str2);
        }
    }
}
