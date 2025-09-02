package com.facebook.react.uimanager;

import android.os.SystemClock;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.facebook.yoga.YogaDirection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UIImplementation {
    protected final EventDispatcher mEventDispatcher;
    private long mLastCalculateLayoutTime;
    protected LayoutUpdateListener mLayoutUpdateListener;
    private final int[] mMeasureBuffer;
    private final NativeViewHierarchyOptimizer mNativeViewHierarchyOptimizer;
    private final UIViewOperationQueue mOperationsQueue;
    protected final ReactApplicationContext mReactContext;
    protected final ShadowNodeRegistry mShadowNodeRegistry;
    private final ViewManagerRegistry mViewManagers;
    private volatile boolean mViewOperationsEnabled;
    protected Object uiImplementationThreadLock;

    public interface LayoutUpdateListener {
        void onLayoutUpdated(ReactShadowNode reactShadowNode);
    }

    public void onHostDestroy() {
    }

    UIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, EventDispatcher eventDispatcher, int i) {
        this(reactApplicationContext, viewManagerRegistry, new UIViewOperationQueue(reactApplicationContext, new NativeViewHierarchyManager(viewManagerRegistry), i), eventDispatcher);
    }

    protected UIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, UIViewOperationQueue uIViewOperationQueue, EventDispatcher eventDispatcher) {
        this.uiImplementationThreadLock = new Object();
        ShadowNodeRegistry shadowNodeRegistry = new ShadowNodeRegistry();
        this.mShadowNodeRegistry = shadowNodeRegistry;
        this.mMeasureBuffer = new int[4];
        this.mLastCalculateLayoutTime = 0;
        this.mViewOperationsEnabled = true;
        this.mReactContext = reactApplicationContext;
        this.mViewManagers = viewManagerRegistry;
        this.mOperationsQueue = uIViewOperationQueue;
        this.mNativeViewHierarchyOptimizer = new NativeViewHierarchyOptimizer(uIViewOperationQueue, shadowNodeRegistry);
        this.mEventDispatcher = eventDispatcher;
    }

    /* access modifiers changed from: protected */
    public ReactShadowNode createRootShadowNode() {
        ReactShadowNodeImpl reactShadowNodeImpl = new ReactShadowNodeImpl();
        if (I18nUtil.getInstance().isRTL(this.mReactContext)) {
            reactShadowNodeImpl.setLayoutDirection(YogaDirection.RTL);
        }
        reactShadowNodeImpl.setViewClassName("Root");
        return reactShadowNodeImpl;
    }

    /* access modifiers changed from: protected */
    public ReactShadowNode createShadowNode(String str) {
        return this.mViewManagers.get(str).createShadowNodeInstance(this.mReactContext);
    }

    public final ReactShadowNode resolveShadowNode(int i) {
        return this.mShadowNodeRegistry.getNode(i);
    }

    /* access modifiers changed from: protected */
    public final ViewManager resolveViewManager(String str) {
        return this.mViewManagers.getViewManagerIfExists(str);
    }

    /* access modifiers changed from: package-private */
    public UIViewOperationQueue getUIViewOperationQueue() {
        return this.mOperationsQueue;
    }

    public void updateRootView(int i, int i2, int i3) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        if (node == null) {
            FLog.w(ReactConstants.TAG, "Tried to update non-existent root tag: " + i);
            return;
        }
        updateRootView(node, i2, i3);
    }

    public void updateRootView(ReactShadowNode reactShadowNode, int i, int i2) {
        reactShadowNode.setMeasureSpecs(i, i2);
    }

    public <T extends View> void registerRootView(T t, int i, ThemedReactContext themedReactContext) {
        synchronized (this.uiImplementationThreadLock) {
            final ReactShadowNode createRootShadowNode = createRootShadowNode();
            createRootShadowNode.setReactTag(i);
            createRootShadowNode.setThemedContext(themedReactContext);
            themedReactContext.runOnNativeModulesQueueThread(new Runnable() {
                public void run() {
                    UIImplementation.this.mShadowNodeRegistry.addRootNode(createRootShadowNode);
                }
            });
            this.mOperationsQueue.addRootView(i, t);
        }
    }

    public void removeRootView(int i) {
        removeRootShadowNode(i);
        this.mOperationsQueue.enqueueRemoveRootView(i);
    }

    public int getRootViewNum() {
        return this.mOperationsQueue.getNativeViewHierarchyManager().getRootViewNum();
    }

    public void removeRootShadowNode(int i) {
        synchronized (this.uiImplementationThreadLock) {
            this.mShadowNodeRegistry.removeRootNode(i);
        }
    }

    public void updateNodeSize(int i, int i2, int i3) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        if (node == null) {
            FLog.w(ReactConstants.TAG, "Tried to update size of non-existent tag: " + i);
            return;
        }
        node.setStyleWidth((float) i2);
        node.setStyleHeight((float) i3);
        dispatchViewUpdatesIfNeeded();
    }

    public void updateInsetsPadding(int i, int i2, int i3, int i4, int i5) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        if (node == null) {
            FLog.w(ReactConstants.TAG, "Tried to update size of non-existent tag: " + i);
            return;
        }
        node.setPadding(4, (float) i3);
        node.setPadding(1, (float) i2);
        node.setPadding(5, (float) i5);
        node.setPadding(3, (float) i4);
        dispatchViewUpdatesIfNeeded();
    }

    public void setViewLocalData(int i, Object obj) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        if (node == null) {
            FLog.w(ReactConstants.TAG, "Attempt to set local data for view with unknown tag: " + i);
            return;
        }
        node.setLocalData(obj);
        dispatchViewUpdatesIfNeeded();
    }

    public void profileNextBatch() {
        this.mOperationsQueue.profileNextBatch();
    }

    public Map<String, Long> getProfiledBatchPerfCounters() {
        return this.mOperationsQueue.getProfiledBatchPerfCounters();
    }

    public void createView(int i, String str, int i2, ReadableMap readableMap) {
        ReactStylesDiffMap reactStylesDiffMap;
        if (this.mViewOperationsEnabled) {
            synchronized (this.uiImplementationThreadLock) {
                try {
                    ReactShadowNode createShadowNode = createShadowNode(str);
                    ReactShadowNode node = this.mShadowNodeRegistry.getNode(i2);
                    Assertions.assertNotNull(node, "Root node with tag " + i2 + " doesn't exist");
                    createShadowNode.setReactTag(i);
                    createShadowNode.setViewClassName(str);
                    createShadowNode.setRootTag(node.getReactTag());
                    createShadowNode.setThemedContext(node.getThemedContext());
                    this.mShadowNodeRegistry.addNode(createShadowNode);
                    if (readableMap != null) {
                        reactStylesDiffMap = new ReactStylesDiffMap(readableMap);
                        createShadowNode.updateProperties(reactStylesDiffMap);
                    } else {
                        reactStylesDiffMap = null;
                    }
                    handleCreateView(createShadowNode, i2, reactStylesDiffMap);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void handleCreateView(ReactShadowNode reactShadowNode, int i, ReactStylesDiffMap reactStylesDiffMap) {
        if (!reactShadowNode.isVirtual()) {
            this.mNativeViewHierarchyOptimizer.handleCreateView(reactShadowNode, reactShadowNode.getThemedContext(), reactStylesDiffMap);
        }
    }

    public void updateView(int i, String str, ReadableMap readableMap) {
        if (this.mViewOperationsEnabled) {
            if (this.mViewManagers.get(str) != null) {
                ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
                if (node == null) {
                    throw new IllegalViewOperationException("Trying to update non-existent view with tag " + i);
                } else if (readableMap != null) {
                    ReactStylesDiffMap reactStylesDiffMap = new ReactStylesDiffMap(readableMap);
                    node.updateProperties(reactStylesDiffMap);
                    handleUpdateView(node, str, reactStylesDiffMap);
                }
            } else {
                throw new IllegalViewOperationException("Got unknown view type: " + str);
            }
        }
    }

    public void synchronouslyUpdateViewOnUIThread(int i, ReactStylesDiffMap reactStylesDiffMap) {
        UiThreadUtil.assertOnUiThread();
        this.mOperationsQueue.getNativeViewHierarchyManager().updateProperties(i, reactStylesDiffMap);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateView(ReactShadowNode reactShadowNode, String str, ReactStylesDiffMap reactStylesDiffMap) {
        if (!reactShadowNode.isVirtual()) {
            this.mNativeViewHierarchyOptimizer.handleUpdateView(reactShadowNode, str, reactStylesDiffMap);
        }
    }

    public void manageChildren(int i, ReadableArray readableArray, ReadableArray readableArray2, ReadableArray readableArray3, ReadableArray readableArray4, ReadableArray readableArray5) {
        int i2;
        int i3;
        int i4;
        int i5 = i;
        ReadableArray readableArray6 = readableArray;
        ReadableArray readableArray7 = readableArray2;
        ReadableArray readableArray8 = readableArray3;
        ReadableArray readableArray9 = readableArray4;
        ReadableArray readableArray10 = readableArray5;
        if (this.mViewOperationsEnabled) {
            synchronized (this.uiImplementationThreadLock) {
                try {
                    ReactShadowNode node = this.mShadowNodeRegistry.getNode(i5);
                    if (readableArray6 == null) {
                        i2 = 0;
                    } else {
                        i2 = readableArray.size();
                    }
                    if (readableArray8 == null) {
                        i3 = 0;
                    } else {
                        i3 = readableArray3.size();
                    }
                    if (readableArray10 == null) {
                        i4 = 0;
                    } else {
                        i4 = readableArray5.size();
                    }
                    if (i2 != 0) {
                        if (readableArray7 == null || i2 != readableArray2.size()) {
                            throw new IllegalViewOperationException("Size of moveFrom != size of moveTo!");
                        }
                    }
                    if (i3 != 0) {
                        if (readableArray9 == null || i3 != readableArray4.size()) {
                            throw new IllegalViewOperationException("Size of addChildTags != size of addAtIndices!");
                        }
                    }
                    int i6 = i2 + i3;
                    ViewAtIndex[] viewAtIndexArr = new ViewAtIndex[i6];
                    int i7 = i2 + i4;
                    int[] iArr = new int[i7];
                    try {
                        int[] iArr2 = new int[i7];
                        int i8 = i6;
                        int[] iArr3 = new int[i4];
                        if (i2 > 0) {
                            Assertions.assertNotNull(readableArray);
                            Assertions.assertNotNull(readableArray2);
                            int i9 = 0;
                            while (i9 < i2) {
                                int i10 = i7;
                                int i11 = readableArray6.getInt(i9);
                                int reactTag = node.getChildAt(i11).getReactTag();
                                viewAtIndexArr[i9] = new ViewAtIndex(reactTag, readableArray7.getInt(i9));
                                iArr[i9] = i11;
                                iArr2[i9] = reactTag;
                                i9++;
                                readableArray6 = readableArray;
                                i7 = i10;
                                iArr3 = iArr3;
                                node = node;
                            }
                        }
                        ReactShadowNode reactShadowNode = node;
                        int[] iArr4 = iArr3;
                        int i12 = i7;
                        if (i3 > 0) {
                            Assertions.assertNotNull(readableArray3);
                            Assertions.assertNotNull(readableArray4);
                            for (int i13 = 0; i13 < i3; i13++) {
                                viewAtIndexArr[i2 + i13] = new ViewAtIndex(readableArray8.getInt(i13), readableArray9.getInt(i13));
                            }
                        }
                        if (i4 > 0) {
                            Assertions.assertNotNull(readableArray5);
                            int i14 = 0;
                            while (i14 < i4) {
                                int i15 = readableArray10.getInt(i14);
                                ReactShadowNode reactShadowNode2 = reactShadowNode;
                                int reactTag2 = reactShadowNode2.getChildAt(i15).getReactTag();
                                int i16 = i2 + i14;
                                iArr[i16] = i15;
                                iArr2[i16] = reactTag2;
                                iArr4[i14] = reactTag2;
                                i14++;
                                reactShadowNode = reactShadowNode2;
                            }
                        }
                        ReactShadowNode reactShadowNode3 = reactShadowNode;
                        Arrays.sort(viewAtIndexArr, ViewAtIndex.COMPARATOR);
                        Arrays.sort(iArr);
                        int i17 = i12 - 1;
                        int i18 = -1;
                        while (i17 >= 0) {
                            int i19 = iArr[i17];
                            if (i19 != i18) {
                                reactShadowNode3.removeChildAt(i19);
                                i18 = iArr[i17];
                                i17--;
                            } else {
                                throw new IllegalViewOperationException("Repeated indices in Removal list for view tag: " + i);
                            }
                        }
                        int i20 = i8;
                        int i21 = 0;
                        while (i21 < i20) {
                            ViewAtIndex viewAtIndex = viewAtIndexArr[i21];
                            int[] iArr5 = iArr2;
                            ReactShadowNode node2 = this.mShadowNodeRegistry.getNode(viewAtIndex.mTag);
                            if (node2 != null) {
                                reactShadowNode3.addChildAt(node2, viewAtIndex.mIndex);
                                i21++;
                                iArr2 = iArr5;
                            } else {
                                throw new IllegalViewOperationException("Trying to add unknown view tag: " + viewAtIndex.mTag);
                            }
                        }
                        int[] iArr6 = iArr2;
                        this.mNativeViewHierarchyOptimizer.handleManageChildren(reactShadowNode3, iArr, iArr6, viewAtIndexArr, iArr4);
                        for (int i22 = 0; i22 < i4; i22++) {
                            removeShadowNode(this.mShadowNodeRegistry.getNode(iArr4[i22]));
                        }
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            }
        }
    }

    public void setChildren(int i, ReadableArray readableArray) {
        if (this.mViewOperationsEnabled) {
            synchronized (this.uiImplementationThreadLock) {
                try {
                    ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
                    int i2 = 0;
                    while (i2 < readableArray.size()) {
                        ReactShadowNode node2 = this.mShadowNodeRegistry.getNode(readableArray.getInt(i2));
                        if (node2 != null) {
                            node.addChildAt(node2, i2);
                            i2++;
                        } else {
                            throw new IllegalViewOperationException("Trying to add unknown view tag: " + readableArray.getInt(i2));
                        }
                    }
                    this.mNativeViewHierarchyOptimizer.handleSetChildren(node, readableArray);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void replaceExistingNonRootView(int i, int i2) {
        if (this.mShadowNodeRegistry.isRootNode(i) || this.mShadowNodeRegistry.isRootNode(i2)) {
            throw new IllegalViewOperationException("Trying to add or replace a root tag!");
        }
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        if (node != null) {
            ReactShadowNode parent = node.getParent();
            if (parent != null) {
                int indexOf = parent.indexOf(node);
                if (indexOf >= 0) {
                    WritableArray createArray = Arguments.createArray();
                    createArray.pushInt(i2);
                    WritableArray createArray2 = Arguments.createArray();
                    createArray2.pushInt(indexOf);
                    WritableArray createArray3 = Arguments.createArray();
                    createArray3.pushInt(indexOf);
                    manageChildren(parent.getReactTag(), (ReadableArray) null, (ReadableArray) null, createArray, createArray2, createArray3);
                    return;
                }
                throw new IllegalStateException("Didn't find child tag in parent");
            }
            throw new IllegalViewOperationException("Node is not attached to a parent: " + i);
        }
        throw new IllegalViewOperationException("Trying to replace unknown view tag: " + i);
    }

    public void findSubviewIn(int i, float f, float f2, Callback callback) {
        this.mOperationsQueue.enqueueFindTargetForTouch(i, f, f2, callback);
    }

    @Deprecated
    public void viewIsDescendantOf(int i, int i2, Callback callback) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        ReactShadowNode node2 = this.mShadowNodeRegistry.getNode(i2);
        if (node == null || node2 == null) {
            callback.invoke(Boolean.FALSE);
        } else {
            callback.invoke(Boolean.valueOf(node.isDescendantOf(node2)));
        }
    }

    public void measure(int i, Callback callback) {
        if (this.mViewOperationsEnabled) {
            this.mOperationsQueue.enqueueMeasure(i, callback);
        }
    }

    public void measureInWindow(int i, Callback callback) {
        if (this.mViewOperationsEnabled) {
            this.mOperationsQueue.enqueueMeasureInWindow(i, callback);
        }
    }

    public void measureLayout(int i, int i2, Callback callback, Callback callback2) {
        if (this.mViewOperationsEnabled) {
            try {
                measureLayout(i, i2, this.mMeasureBuffer);
                callback2.invoke(Float.valueOf(PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[0])), Float.valueOf(PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[1])), Float.valueOf(PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[3])));
            } catch (IllegalViewOperationException e) {
                callback.invoke(e.getMessage());
            }
        }
    }

    public void measureLayoutRelativeToParent(int i, Callback callback, Callback callback2) {
        if (this.mViewOperationsEnabled) {
            try {
                measureLayoutRelativeToParent(i, this.mMeasureBuffer);
                callback2.invoke(Float.valueOf(PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[0])), Float.valueOf(PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[1])), Float.valueOf(PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[3])));
            } catch (IllegalViewOperationException e) {
                callback.invoke(e.getMessage());
            }
        }
    }

    public void dispatchViewUpdates(int i) {
        SystraceMessage.beginSection(0, "UIImplementation.dispatchViewUpdates").arg("batchId", i).flush();
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            updateViewHierarchy();
            this.mNativeViewHierarchyOptimizer.onBatchComplete();
            this.mOperationsQueue.dispatchViewUpdates(i, uptimeMillis, this.mLastCalculateLayoutTime);
        } finally {
            Systrace.endSection(0);
        }
    }

    private void dispatchViewUpdatesIfNeeded() {
        if (this.mOperationsQueue.isEmpty()) {
            dispatchViewUpdates(-1);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a6, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateViewHierarchy() {
        /*
            r14 = this;
            java.lang.String r0 = "rootTag"
            java.lang.String r1 = "UIImplementation.updateViewHierarchy"
            r2 = 0
            com.facebook.systrace.Systrace.beginSection(r2, r1)
            r1 = 0
        L_0x000a:
            com.facebook.react.uimanager.ShadowNodeRegistry r4 = r14.mShadowNodeRegistry     // Catch:{ all -> 0x009c }
            int r4 = r4.getRootNodeCount()     // Catch:{ all -> 0x009c }
            if (r1 >= r4) goto L_0x00ab
            com.facebook.react.uimanager.ShadowNodeRegistry r4 = r14.mShadowNodeRegistry     // Catch:{ all -> 0x009c }
            int r4 = r4.getRootTag(r1)     // Catch:{ all -> 0x009c }
            com.facebook.react.uimanager.ShadowNodeRegistry r5 = r14.mShadowNodeRegistry     // Catch:{ all -> 0x009c }
            com.facebook.react.uimanager.ReactShadowNode r4 = r5.getNode(r4)     // Catch:{ all -> 0x009c }
            java.lang.Integer r5 = r4.getWidthMeasureSpec()     // Catch:{ all -> 0x009c }
            if (r5 == 0) goto L_0x00a7
            java.lang.Integer r5 = r4.getHeightMeasureSpec()     // Catch:{ all -> 0x009c }
            if (r5 == 0) goto L_0x00a7
            java.lang.String r5 = "UIImplementation.notifyOnBeforeLayoutRecursive"
            com.facebook.systrace.SystraceMessage$Builder r5 = com.facebook.systrace.SystraceMessage.beginSection(r2, r5)     // Catch:{ all -> 0x009c }
            int r6 = r4.getReactTag()     // Catch:{ all -> 0x009c }
            com.facebook.systrace.SystraceMessage$Builder r5 = r5.arg((java.lang.String) r0, (int) r6)     // Catch:{ all -> 0x009c }
            r5.flush()     // Catch:{ all -> 0x009c }
            r14.notifyOnBeforeLayoutRecursive(r4)     // Catch:{ all -> 0x00a2 }
            com.facebook.systrace.Systrace.endSection(r2)     // Catch:{ all -> 0x009c }
            r14.calculateRootLayout(r4)     // Catch:{ all -> 0x009c }
            java.lang.String r5 = "UIImplementation.applyUpdatesRecursive"
            com.facebook.systrace.SystraceMessage$Builder r5 = com.facebook.systrace.SystraceMessage.beginSection(r2, r5)     // Catch:{ all -> 0x009c }
            int r6 = r4.getReactTag()     // Catch:{ all -> 0x009c }
            com.facebook.systrace.SystraceMessage$Builder r5 = r5.arg((java.lang.String) r0, (int) r6)     // Catch:{ all -> 0x009c }
            r5.flush()     // Catch:{ all -> 0x009c }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x008d }
            r5.<init>()     // Catch:{ all -> 0x008d }
            r6 = 0
            r14.applyUpdatesRecursive(r4, r6, r6, r5)     // Catch:{ all -> 0x008d }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x008d }
        L_0x0062:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x008d }
            if (r6 == 0) goto L_0x008f
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x008d }
            com.facebook.react.uimanager.ReactShadowNode r6 = (com.facebook.react.uimanager.ReactShadowNode) r6     // Catch:{ all -> 0x008d }
            com.facebook.react.uimanager.events.EventDispatcher r7 = r14.mEventDispatcher     // Catch:{ all -> 0x008d }
            int r9 = r6.getReactTag()     // Catch:{ all -> 0x008d }
            int r10 = r6.getScreenX()     // Catch:{ all -> 0x008d }
            int r11 = r6.getScreenY()     // Catch:{ all -> 0x008d }
            int r12 = r6.getScreenWidth()     // Catch:{ all -> 0x008d }
            int r13 = r6.getScreenHeight()     // Catch:{ all -> 0x008d }
            r8 = -1
            com.facebook.react.uimanager.OnLayoutEvent r6 = com.facebook.react.uimanager.OnLayoutEvent.obtain(r8, r9, r10, r11, r12, r13)     // Catch:{ all -> 0x008d }
            r7.dispatchEvent(r6)     // Catch:{ all -> 0x008d }
            goto L_0x0062
        L_0x008d:
            r0 = move-exception
            goto L_0x009e
        L_0x008f:
            com.facebook.systrace.Systrace.endSection(r2)     // Catch:{ all -> 0x009c }
            com.facebook.react.uimanager.UIImplementation$LayoutUpdateListener r5 = r14.mLayoutUpdateListener     // Catch:{ all -> 0x009c }
            if (r5 == 0) goto L_0x00a7
            com.facebook.react.uimanager.UIViewOperationQueue r6 = r14.mOperationsQueue     // Catch:{ all -> 0x009c }
            r6.enqueueLayoutUpdateFinished(r4, r5)     // Catch:{ all -> 0x009c }
            goto L_0x00a7
        L_0x009c:
            r0 = move-exception
            goto L_0x00af
        L_0x009e:
            com.facebook.systrace.Systrace.endSection(r2)     // Catch:{ all -> 0x009c }
            throw r0     // Catch:{ all -> 0x009c }
        L_0x00a2:
            r0 = move-exception
            com.facebook.systrace.Systrace.endSection(r2)     // Catch:{ all -> 0x009c }
            throw r0     // Catch:{ all -> 0x009c }
        L_0x00a7:
            int r1 = r1 + 1
            goto L_0x000a
        L_0x00ab:
            com.facebook.systrace.Systrace.endSection(r2)
            return
        L_0x00af:
            com.facebook.systrace.Systrace.endSection(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.UIImplementation.updateViewHierarchy():void");
    }

    public void setLayoutAnimationEnabledExperimental(boolean z) {
        this.mOperationsQueue.enqueueSetLayoutAnimationEnabled(z);
    }

    public void configureNextLayoutAnimation(ReadableMap readableMap, Callback callback) {
        this.mOperationsQueue.enqueueConfigureLayoutAnimation(readableMap, callback);
    }

    public void setJSResponder(int i, boolean z) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        if (node != null) {
            while (node.getNativeKind() == NativeKind.NONE) {
                node = node.getParent();
            }
            this.mOperationsQueue.enqueueSetJSResponder(node.getReactTag(), i, z);
        }
    }

    public void clearJSResponder() {
        this.mOperationsQueue.enqueueClearJSResponder();
    }

    @Deprecated
    public void dispatchViewManagerCommand(int i, int i2, ReadableArray readableArray) {
        if (checkOrAssertViewExists(i, "dispatchViewManagerCommand: " + i2)) {
            this.mOperationsQueue.enqueueDispatchCommand(i, i2, readableArray);
        }
    }

    public void dispatchViewManagerCommand(int i, String str, ReadableArray readableArray) {
        if (checkOrAssertViewExists(i, "dispatchViewManagerCommand: " + str)) {
            this.mOperationsQueue.enqueueDispatchCommand(i, str, readableArray);
        }
    }

    public void sendAccessibilityEvent(int i, int i2) {
        this.mOperationsQueue.enqueueSendAccessibilityEvent(i, i2);
    }

    public void onHostResume() {
        this.mOperationsQueue.resumeFrameCallback();
    }

    public void onHostPause() {
        this.mOperationsQueue.pauseFrameCallback();
    }

    public void onCatalystInstanceDestroyed() {
        this.mViewOperationsEnabled = false;
        this.mViewManagers.invalidate();
    }

    public void setViewHierarchyUpdateDebugListener(NotThreadSafeViewHierarchyUpdateDebugListener notThreadSafeViewHierarchyUpdateDebugListener) {
        this.mOperationsQueue.setViewHierarchyUpdateDebugListener(notThreadSafeViewHierarchyUpdateDebugListener);
    }

    /* access modifiers changed from: protected */
    public final void removeShadowNode(ReactShadowNode reactShadowNode) {
        removeShadowNodeRecursive(reactShadowNode);
        reactShadowNode.dispose();
    }

    private void removeShadowNodeRecursive(ReactShadowNode reactShadowNode) {
        NativeViewHierarchyOptimizer.handleRemoveNode(reactShadowNode);
        this.mShadowNodeRegistry.removeNode(reactShadowNode.getReactTag());
        for (int childCount = reactShadowNode.getChildCount() - 1; childCount >= 0; childCount--) {
            removeShadowNodeRecursive(reactShadowNode.getChildAt(childCount));
        }
        reactShadowNode.removeAndDisposeAllChildren();
    }

    private void measureLayout(int i, int i2, int[] iArr) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        ReactShadowNode node2 = this.mShadowNodeRegistry.getNode(i2);
        if (node == null || node2 == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Tag ");
            if (node != null) {
                i = i2;
            }
            sb.append(i);
            sb.append(" does not exist");
            throw new IllegalViewOperationException(sb.toString());
        }
        if (node != node2) {
            ReactShadowNode parent = node.getParent();
            while (parent != node2) {
                if (parent != null) {
                    parent = parent.getParent();
                } else {
                    throw new IllegalViewOperationException("Tag " + i2 + " is not an ancestor of tag " + i);
                }
            }
        }
        measureLayoutRelativeToVerifiedAncestor(node, node2, iArr);
    }

    private void measureLayoutRelativeToParent(int i, int[] iArr) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        if (node != null) {
            ReactShadowNode parent = node.getParent();
            if (parent != null) {
                measureLayoutRelativeToVerifiedAncestor(node, parent, iArr);
                return;
            }
            throw new IllegalViewOperationException("View with tag " + i + " doesn't have a parent!");
        }
        throw new IllegalViewOperationException("No native view for tag " + i + " exists!");
    }

    private void measureLayoutRelativeToVerifiedAncestor(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int[] iArr) {
        int i;
        int i2;
        if (reactShadowNode == reactShadowNode2 || reactShadowNode.isVirtual()) {
            i2 = 0;
            i = 0;
        } else {
            i2 = Math.round(reactShadowNode.getLayoutX());
            i = Math.round(reactShadowNode.getLayoutY());
            for (ReactShadowNode parent = reactShadowNode.getParent(); parent != reactShadowNode2; parent = parent.getParent()) {
                Assertions.assertNotNull(parent);
                assertNodeDoesNotNeedCustomLayoutForChildren(parent);
                i2 += Math.round(parent.getLayoutX());
                i += Math.round(parent.getLayoutY());
            }
            assertNodeDoesNotNeedCustomLayoutForChildren(reactShadowNode2);
        }
        iArr[0] = i2;
        iArr[1] = i;
        iArr[2] = reactShadowNode.getScreenWidth();
        iArr[3] = reactShadowNode.getScreenHeight();
    }

    private boolean checkOrAssertViewExists(int i, String str) {
        if (this.mShadowNodeRegistry.getNode(i) != null) {
            return true;
        }
        String str2 = "Unable to execute operation " + str + " on view with tag: " + i + ", since the view does not exist";
        if (!ReactBuildConfig.DEBUG) {
            FLog.w(ReactConstants.TAG, str2);
            return false;
        }
        throw new IllegalViewOperationException(str2);
    }

    private void assertNodeDoesNotNeedCustomLayoutForChildren(ReactShadowNode reactShadowNode) {
        ViewManager viewManager = (ViewManager) Assertions.assertNotNull(this.mViewManagers.get(reactShadowNode.getViewClass()));
        if (viewManager instanceof IViewManagerWithChildren) {
            IViewManagerWithChildren iViewManagerWithChildren = (IViewManagerWithChildren) viewManager;
            if (iViewManagerWithChildren != null && iViewManagerWithChildren.needsCustomLayoutForChildren()) {
                throw new IllegalViewOperationException("Trying to measure a view using measureLayout/measureLayoutRelativeToParent relative to an ancestor that requires custom layout for it's children (" + reactShadowNode.getViewClass() + "). Use measure instead.");
            }
            return;
        }
        throw new IllegalViewOperationException("Trying to use view " + reactShadowNode.getViewClass() + " as a parent, but its Manager doesn't extends ViewGroupManager");
    }

    private void notifyOnBeforeLayoutRecursive(ReactShadowNode reactShadowNode) {
        if (reactShadowNode.hasUpdates()) {
            for (int i = 0; i < reactShadowNode.getChildCount(); i++) {
                notifyOnBeforeLayoutRecursive(reactShadowNode.getChildAt(i));
            }
            reactShadowNode.onBeforeLayout(this.mNativeViewHierarchyOptimizer);
        }
    }

    /* access modifiers changed from: protected */
    public void calculateRootLayout(ReactShadowNode reactShadowNode) {
        float f;
        SystraceMessage.beginSection(0, "cssRoot.calculateLayout").arg("rootTag", reactShadowNode.getReactTag()).flush();
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            int intValue = reactShadowNode.getWidthMeasureSpec().intValue();
            int intValue2 = reactShadowNode.getHeightMeasureSpec().intValue();
            float f2 = Float.NaN;
            if (View.MeasureSpec.getMode(intValue) == 0) {
                f = Float.NaN;
            } else {
                f = (float) View.MeasureSpec.getSize(intValue);
            }
            if (View.MeasureSpec.getMode(intValue2) != 0) {
                f2 = (float) View.MeasureSpec.getSize(intValue2);
            }
            reactShadowNode.calculateLayout(f, f2);
        } finally {
            Systrace.endSection(0);
            this.mLastCalculateLayoutTime = SystemClock.uptimeMillis() - uptimeMillis;
        }
    }

    /* access modifiers changed from: protected */
    public void applyUpdatesRecursive(ReactShadowNode reactShadowNode, float f, float f2, List<ReactShadowNode> list) {
        if (reactShadowNode.hasUpdates()) {
            if (reactShadowNode.dispatchUpdatesWillChangeLayout(f, f2) && reactShadowNode.shouldNotifyOnLayout() && !this.mShadowNodeRegistry.isRootNode(reactShadowNode.getReactTag())) {
                list.add(reactShadowNode);
            }
            Iterable<? extends ReactShadowNode> calculateLayoutOnChildren = reactShadowNode.calculateLayoutOnChildren();
            if (calculateLayoutOnChildren != null) {
                for (ReactShadowNode applyUpdatesRecursive : calculateLayoutOnChildren) {
                    applyUpdatesRecursive(applyUpdatesRecursive, reactShadowNode.getLayoutX() + f, reactShadowNode.getLayoutY() + f2, list);
                }
            }
            reactShadowNode.dispatchUpdates(f, f2, this.mOperationsQueue, this.mNativeViewHierarchyOptimizer);
            reactShadowNode.markUpdateSeen();
            this.mNativeViewHierarchyOptimizer.onViewUpdatesCompleted(reactShadowNode);
        }
    }

    public void addUIBlock(UIBlock uIBlock) {
        this.mOperationsQueue.enqueueUIBlock(uIBlock);
    }

    public void prependUIBlock(UIBlock uIBlock) {
        this.mOperationsQueue.prependUIBlock(uIBlock);
    }

    public int resolveRootTagFromReactTag(int i) {
        if (this.mShadowNodeRegistry.isRootNode(i)) {
            return i;
        }
        ReactShadowNode resolveShadowNode = resolveShadowNode(i);
        if (resolveShadowNode != null) {
            return resolveShadowNode.getRootTag();
        }
        FLog.w(ReactConstants.TAG, "Warning : attempted to resolve a non-existent react shadow node. reactTag=" + i);
        return 0;
    }

    public void setLayoutUpdateListener(LayoutUpdateListener layoutUpdateListener) {
        this.mLayoutUpdateListener = layoutUpdateListener;
    }

    public void removeLayoutUpdateListener() {
        this.mLayoutUpdateListener = null;
    }
}
