package com.facebook.react.uimanager;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationController;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.facebook.yoga.YogaDirection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class NativeViewHierarchyManager {
    private static final String TAG = "NativeViewHierarchyManager";
    private final boolean DEBUG_MODE;
    private final RectF mBoundingBox;
    private final JSResponderHandler mJSResponderHandler;
    private volatile boolean mLayoutAnimationEnabled;
    private final LayoutAnimationController mLayoutAnimator;
    /* access modifiers changed from: private */
    public HashMap<Integer, Set<Integer>> mPendingDeletionsForTag;
    private final SparseBooleanArray mRootTags;
    private final RootViewManager mRootViewManager;
    private final SparseArray<ViewManager> mTagsToViewManagers;
    private final SparseArray<View> mTagsToViews;
    private final ViewManagerRegistry mViewManagers;

    public NativeViewHierarchyManager(ViewManagerRegistry viewManagerRegistry) {
        this(viewManagerRegistry, new RootViewManager());
    }

    public NativeViewHierarchyManager(ViewManagerRegistry viewManagerRegistry, RootViewManager rootViewManager) {
        ReactBuildConfig reactBuildConfig = ReactBuildConfig.INSTANCE;
        this.DEBUG_MODE = false;
        this.mJSResponderHandler = new JSResponderHandler();
        this.mLayoutAnimator = new LayoutAnimationController();
        this.mBoundingBox = new RectF();
        this.mViewManagers = viewManagerRegistry;
        this.mTagsToViews = new SparseArray<>();
        this.mTagsToViewManagers = new SparseArray<>();
        this.mRootTags = new SparseBooleanArray();
        this.mRootViewManager = rootViewManager;
    }

    public final synchronized View resolveView(int i) {
        View view;
        view = this.mTagsToViews.get(i);
        if (view == null) {
            throw new IllegalViewOperationException("Trying to resolve view with tag " + i + " which doesn't exist");
        }
        return view;
    }

    public final synchronized ViewManager resolveViewManager(int i) {
        ViewManager viewManager;
        viewManager = this.mTagsToViewManagers.get(i);
        if (viewManager == null) {
            throw new IllegalViewOperationException("ViewManager for tag " + i + " could not be found.\n");
        }
        return viewManager;
    }

    public void setLayoutAnimationEnabled(boolean z) {
        this.mLayoutAnimationEnabled = z;
    }

    public synchronized void updateInstanceHandle(int i, long j) {
        UiThreadUtil.assertOnUiThread();
        try {
            updateInstanceHandle(resolveView(i), j);
        } catch (IllegalViewOperationException e) {
            String str = TAG;
            FLog.e(str, "Unable to update properties for view tag " + i, (Throwable) e);
        }
    }

    public synchronized void updateProperties(int i, ReactStylesDiffMap reactStylesDiffMap) {
        try {
            if (this.DEBUG_MODE) {
                FLog.d(TAG, "updateProperties[%d]: %s", Integer.valueOf(i), reactStylesDiffMap.toString());
            }
            UiThreadUtil.assertOnUiThread();
            ViewManager resolveViewManager = resolveViewManager(i);
            View resolveView = resolveView(i);
            if (reactStylesDiffMap != null) {
                resolveViewManager.updateProperties(resolveView, reactStylesDiffMap);
            }
        } catch (IllegalViewOperationException e) {
            String str = TAG;
            FLog.e(str, "Unable to update properties for view tag " + i, (Throwable) e);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized void updateViewExtraData(int i, Object obj) {
        try {
            if (this.DEBUG_MODE) {
                FLog.d(TAG, "updateViewExtraData[%d]: %s", Integer.valueOf(i), obj.toString());
            }
            UiThreadUtil.assertOnUiThread();
            resolveViewManager(i).updateExtraData(resolveView(i), obj);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    @Deprecated
    public void updateLayout(int i, int i2, int i3, int i4, int i5) {
        updateLayout(i, i, i2, i3, i4, i5, YogaDirection.INHERIT);
    }

    /* JADX INFO: finally extract failed */
    public synchronized void updateLayout(int i, int i2, int i3, int i4, int i5, int i6, YogaDirection yogaDirection) {
        int i7 = i;
        int i8 = i2;
        synchronized (this) {
            try {
                if (this.DEBUG_MODE) {
                    FLog.d(TAG, "updateLayout[%d]->[%d]: %d %d %d %d", Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6));
                }
                UiThreadUtil.assertOnUiThread();
                SystraceMessage.beginSection(0, "NativeViewHierarchyManager_updateLayout").arg("parentTag", i).arg("tag", i8).flush();
                View resolveView = resolveView(i8);
                resolveView.setLayoutDirection(LayoutDirectionUtil.toAndroidFromYoga(yogaDirection));
                resolveView.measure(View.MeasureSpec.makeMeasureSpec(i5, 1073741824), View.MeasureSpec.makeMeasureSpec(i6, 1073741824));
                ViewParent parent = resolveView.getParent();
                if (parent instanceof RootView) {
                    parent.requestLayout();
                }
                if (!this.mRootTags.get(i)) {
                    ViewManager viewManager = this.mTagsToViewManagers.get(i);
                    if (viewManager instanceof IViewManagerWithChildren) {
                        IViewManagerWithChildren iViewManagerWithChildren = (IViewManagerWithChildren) viewManager;
                        if (iViewManagerWithChildren != null && !iViewManagerWithChildren.needsCustomLayoutForChildren()) {
                            updateLayout(resolveView, i3, i4, i5, i6);
                        }
                    } else {
                        throw new IllegalViewOperationException("Trying to use view with tag " + i + " as a parent, but its Manager doesn't implement IViewManagerWithChildren");
                    }
                } else {
                    updateLayout(resolveView, i3, i4, i5, i6);
                }
                Systrace.endSection(0);
            } catch (Throwable th) {
                Systrace.endSection(0);
                throw th;
            } finally {
            }
        }
    }

    private void updateInstanceHandle(View view, long j) {
        UiThreadUtil.assertOnUiThread();
        view.setTag(R.id.view_tag_instance_handle, Long.valueOf(j));
    }

    public synchronized long getInstanceHandle(int i) {
        Long l;
        View view = this.mTagsToViews.get(i);
        if (view != null) {
            l = (Long) view.getTag(R.id.view_tag_instance_handle);
            if (l != null) {
            } else {
                throw new IllegalViewOperationException("Unable to find instanceHandle for tag: " + i);
            }
        } else {
            throw new IllegalViewOperationException("Unable to find view for tag: " + i);
        }
        return l.longValue();
    }

    private void updateLayout(View view, int i, int i2, int i3, int i4) {
        if (!this.mLayoutAnimationEnabled || !this.mLayoutAnimator.shouldAnimateLayout(view)) {
            view.layout(i, i2, i3 + i, i4 + i2);
        } else {
            this.mLayoutAnimator.applyLayoutUpdate(view, i, i2, i3, i4);
        }
    }

    public synchronized void createView(ThemedReactContext themedReactContext, int i, String str, ReactStylesDiffMap reactStylesDiffMap) {
        String str2;
        try {
            if (this.DEBUG_MODE) {
                String str3 = TAG;
                Integer valueOf = Integer.valueOf(i);
                if (reactStylesDiffMap != null) {
                    str2 = reactStylesDiffMap.toString();
                } else {
                    str2 = "<null>";
                }
                FLog.d(str3, "createView[%d]: %s %s", valueOf, str, str2);
            }
            UiThreadUtil.assertOnUiThread();
            SystraceMessage.beginSection(0, "NativeViewHierarchyManager_createView").arg("tag", i).arg("className", (Object) str).flush();
            ViewManager viewManager = this.mViewManagers.get(str);
            this.mTagsToViews.put(i, viewManager.createView(i, themedReactContext, reactStylesDiffMap, (StateWrapper) null, this.mJSResponderHandler));
            this.mTagsToViewManagers.put(i, viewManager);
            Systrace.endSection(0);
        } catch (Throwable th) {
            throw th;
        }
    }

    private static String constructManageChildrenErrorMessage(ViewGroup viewGroup, ViewGroupManager viewGroupManager, int[] iArr, ViewAtIndex[] viewAtIndexArr, int[] iArr2) {
        StringBuilder sb = new StringBuilder();
        if (viewGroup != null) {
            sb.append("View tag:" + viewGroup.getId() + " View Type:" + viewGroup.getClass().toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  children(");
            sb2.append(viewGroupManager.getChildCount(viewGroup));
            sb2.append("): [\n");
            sb.append(sb2.toString());
            for (int i = 0; viewGroupManager.getChildAt(viewGroup, i) != null; i += 16) {
                int i2 = 0;
                while (true) {
                    int i3 = i + i2;
                    if (viewGroupManager.getChildAt(viewGroup, i3) == null || i2 >= 16) {
                        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    } else {
                        sb.append(viewGroupManager.getChildAt(viewGroup, i3).getId() + ",");
                        i2++;
                    }
                }
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            sb.append(" ],\n");
        }
        if (iArr != null) {
            sb.append("  indicesToRemove(" + iArr.length + "): [\n");
            for (int i4 = 0; i4 < iArr.length; i4 += 16) {
                int i5 = 0;
                while (true) {
                    int i6 = i4 + i5;
                    if (i6 >= iArr.length || i5 >= 16) {
                        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    } else {
                        sb.append(iArr[i6] + ",");
                        i5++;
                    }
                }
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            sb.append(" ],\n");
        }
        if (viewAtIndexArr != null) {
            sb.append("  viewsToAdd(" + viewAtIndexArr.length + "): [\n");
            for (int i7 = 0; i7 < viewAtIndexArr.length; i7 += 16) {
                int i8 = 0;
                while (true) {
                    int i9 = i7 + i8;
                    if (i9 >= viewAtIndexArr.length || i8 >= 16) {
                        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    } else {
                        sb.append("[" + viewAtIndexArr[i9].mIndex + "," + viewAtIndexArr[i9].mTag + "],");
                        i8++;
                    }
                }
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            sb.append(" ],\n");
        }
        if (iArr2 != null) {
            sb.append("  tagsToDelete(" + iArr2.length + "): [\n");
            for (int i10 = 0; i10 < iArr2.length; i10 += 16) {
                int i11 = 0;
                while (true) {
                    int i12 = i10 + i11;
                    if (i12 >= iArr2.length || i11 >= 16) {
                        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    } else {
                        sb.append(iArr2[i12] + ",");
                        i11++;
                    }
                }
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            sb.append(" ]\n");
        }
        return sb.toString();
    }

    private Set<Integer> getPendingDeletionsForTag(int i) {
        if (this.mPendingDeletionsForTag == null) {
            this.mPendingDeletionsForTag = new HashMap<>();
        }
        if (!this.mPendingDeletionsForTag.containsKey(Integer.valueOf(i))) {
            this.mPendingDeletionsForTag.put(Integer.valueOf(i), new HashSet());
        }
        return this.mPendingDeletionsForTag.get(Integer.valueOf(i));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:95:0x020e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void manageChildren(int r18, int[] r19, com.facebook.react.uimanager.ViewAtIndex[] r20, int[] r21) {
        /*
            r17 = this;
            r8 = r17
            r0 = r18
            r9 = r19
            r10 = r20
            r11 = r21
            monitor-enter(r17)
            boolean r1 = r8.DEBUG_MODE     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x003e
            java.lang.String r2 = TAG     // Catch:{ all -> 0x001f }
            java.lang.String r3 = "createView[%d]: %s %s %s"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r18)     // Catch:{ all -> 0x001f }
            if (r9 == 0) goto L_0x0022
            java.lang.String r1 = r19.toString()     // Catch:{ all -> 0x001f }
        L_0x001d:
            r5 = r1
            goto L_0x0025
        L_0x001f:
            r0 = move-exception
            goto L_0x0235
        L_0x0022:
            java.lang.String r1 = "<null>"
            goto L_0x001d
        L_0x0025:
            if (r10 == 0) goto L_0x002d
            java.lang.String r1 = r20.toString()     // Catch:{ all -> 0x001f }
        L_0x002b:
            r6 = r1
            goto L_0x0030
        L_0x002d:
            java.lang.String r1 = "<null>"
            goto L_0x002b
        L_0x0030:
            if (r11 == 0) goto L_0x0038
            java.lang.String r1 = r21.toString()     // Catch:{ all -> 0x001f }
        L_0x0036:
            r7 = r1
            goto L_0x003b
        L_0x0038:
            java.lang.String r1 = "<null>"
            goto L_0x0036
        L_0x003b:
            com.facebook.common.logging.FLog.d(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x001f }
        L_0x003e:
            com.facebook.react.bridge.UiThreadUtil.assertOnUiThread()     // Catch:{ all -> 0x001f }
            java.util.Set r12 = r17.getPendingDeletionsForTag(r18)     // Catch:{ all -> 0x001f }
            android.util.SparseArray<android.view.View> r1 = r8.mTagsToViews     // Catch:{ all -> 0x001f }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x001f }
            r13 = r1
            android.view.ViewGroup r13 = (android.view.ViewGroup) r13     // Catch:{ all -> 0x001f }
            com.facebook.react.uimanager.ViewManager r1 = r17.resolveViewManager(r18)     // Catch:{ all -> 0x001f }
            r14 = r1
            com.facebook.react.uimanager.ViewGroupManager r14 = (com.facebook.react.uimanager.ViewGroupManager) r14     // Catch:{ all -> 0x001f }
            if (r13 == 0) goto L_0x020f
            int r1 = r14.getChildCount(r13)     // Catch:{ all -> 0x001f }
            if (r9 == 0) goto L_0x0121
            int r2 = r9.length     // Catch:{ all -> 0x001f }
            int r2 = r2 + -1
        L_0x0060:
            if (r2 < 0) goto L_0x0121
            r3 = r9[r2]     // Catch:{ all -> 0x001f }
            if (r3 < 0) goto L_0x00f6
            android.view.View r4 = r14.getChildAt(r13, (int) r3)     // Catch:{ all -> 0x001f }
            if (r4 != 0) goto L_0x00a7
            android.util.SparseBooleanArray r1 = r8.mRootTags     // Catch:{ all -> 0x001f }
            boolean r1 = r1.get(r0)     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x007c
            int r1 = r14.getChildCount(r13)     // Catch:{ all -> 0x001f }
            if (r1 != 0) goto L_0x007c
            monitor-exit(r17)
            return
        L_0x007c:
            com.facebook.react.uimanager.IllegalViewOperationException r1 = new com.facebook.react.uimanager.IllegalViewOperationException     // Catch:{ all -> 0x001f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x001f }
            r2.<init>()     // Catch:{ all -> 0x001f }
            java.lang.String r4 = "Trying to remove a view index above child count "
            r2.append(r4)     // Catch:{ all -> 0x001f }
            r2.append(r3)     // Catch:{ all -> 0x001f }
            java.lang.String r3 = " view tag: "
            r2.append(r3)     // Catch:{ all -> 0x001f }
            r2.append(r0)     // Catch:{ all -> 0x001f }
            java.lang.String r0 = "\n detail: "
            r2.append(r0)     // Catch:{ all -> 0x001f }
            java.lang.String r0 = constructManageChildrenErrorMessage(r13, r14, r9, r10, r11)     // Catch:{ all -> 0x001f }
            r2.append(r0)     // Catch:{ all -> 0x001f }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x001f }
            r1.<init>(r0)     // Catch:{ all -> 0x001f }
            throw r1     // Catch:{ all -> 0x001f }
        L_0x00a7:
            if (r3 >= r1) goto L_0x00cb
            android.view.View r1 = r14.getChildAt(r13, (int) r3)     // Catch:{ all -> 0x001f }
            boolean r4 = r8.mLayoutAnimationEnabled     // Catch:{ all -> 0x001f }
            if (r4 == 0) goto L_0x00c4
            com.facebook.react.uimanager.layoutanimation.LayoutAnimationController r4 = r8.mLayoutAnimator     // Catch:{ all -> 0x001f }
            boolean r4 = r4.shouldAnimateLayout(r1)     // Catch:{ all -> 0x001f }
            if (r4 == 0) goto L_0x00c4
            int r1 = r1.getId()     // Catch:{ all -> 0x001f }
            boolean r1 = r8.arrayContains(r11, r1)     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x00c4
            goto L_0x00c7
        L_0x00c4:
            r14.removeViewAt(r13, (int) r3)     // Catch:{ all -> 0x001f }
        L_0x00c7:
            int r2 = r2 + -1
            r1 = r3
            goto L_0x0060
        L_0x00cb:
            com.facebook.react.uimanager.IllegalViewOperationException r1 = new com.facebook.react.uimanager.IllegalViewOperationException     // Catch:{ all -> 0x001f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x001f }
            r2.<init>()     // Catch:{ all -> 0x001f }
            java.lang.String r4 = "Trying to remove an out of order view index:"
            r2.append(r4)     // Catch:{ all -> 0x001f }
            r2.append(r3)     // Catch:{ all -> 0x001f }
            java.lang.String r3 = " view tag: "
            r2.append(r3)     // Catch:{ all -> 0x001f }
            r2.append(r0)     // Catch:{ all -> 0x001f }
            java.lang.String r0 = "\n detail: "
            r2.append(r0)     // Catch:{ all -> 0x001f }
            java.lang.String r0 = constructManageChildrenErrorMessage(r13, r14, r9, r10, r11)     // Catch:{ all -> 0x001f }
            r2.append(r0)     // Catch:{ all -> 0x001f }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x001f }
            r1.<init>(r0)     // Catch:{ all -> 0x001f }
            throw r1     // Catch:{ all -> 0x001f }
        L_0x00f6:
            com.facebook.react.uimanager.IllegalViewOperationException r1 = new com.facebook.react.uimanager.IllegalViewOperationException     // Catch:{ all -> 0x001f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x001f }
            r2.<init>()     // Catch:{ all -> 0x001f }
            java.lang.String r4 = "Trying to remove a negative view index:"
            r2.append(r4)     // Catch:{ all -> 0x001f }
            r2.append(r3)     // Catch:{ all -> 0x001f }
            java.lang.String r3 = " view tag: "
            r2.append(r3)     // Catch:{ all -> 0x001f }
            r2.append(r0)     // Catch:{ all -> 0x001f }
            java.lang.String r0 = "\n detail: "
            r2.append(r0)     // Catch:{ all -> 0x001f }
            java.lang.String r0 = constructManageChildrenErrorMessage(r13, r14, r9, r10, r11)     // Catch:{ all -> 0x001f }
            r2.append(r0)     // Catch:{ all -> 0x001f }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x001f }
            r1.<init>(r0)     // Catch:{ all -> 0x001f }
            throw r1     // Catch:{ all -> 0x001f }
        L_0x0121:
            if (r11 == 0) goto L_0x0192
            r7 = 0
        L_0x0124:
            int r1 = r11.length     // Catch:{ all -> 0x001f }
            if (r7 >= r1) goto L_0x0192
            r1 = r11[r7]     // Catch:{ all -> 0x001f }
            android.util.SparseArray<android.view.View> r2 = r8.mTagsToViews     // Catch:{ all -> 0x001f }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x001f }
            r6 = r2
            android.view.View r6 = (android.view.View) r6     // Catch:{ all -> 0x001f }
            if (r6 == 0) goto L_0x016d
            boolean r2 = r8.mLayoutAnimationEnabled     // Catch:{ all -> 0x001f }
            if (r2 == 0) goto L_0x0160
            com.facebook.react.uimanager.layoutanimation.LayoutAnimationController r2 = r8.mLayoutAnimator     // Catch:{ all -> 0x001f }
            boolean r2 = r2.shouldAnimateLayout(r6)     // Catch:{ all -> 0x001f }
            if (r2 == 0) goto L_0x0160
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x001f }
            r12.add(r1)     // Catch:{ all -> 0x001f }
            com.facebook.react.uimanager.layoutanimation.LayoutAnimationController r5 = r8.mLayoutAnimator     // Catch:{ all -> 0x001f }
            com.facebook.react.uimanager.NativeViewHierarchyManager$1 r4 = new com.facebook.react.uimanager.NativeViewHierarchyManager$1     // Catch:{ all -> 0x001f }
            r1 = r4
            r2 = r17
            r3 = r14
            r15 = r4
            r4 = r13
            r0 = r5
            r5 = r6
            r9 = r6
            r6 = r12
            r16 = r7
            r7 = r18
            r1.<init>(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x001f }
            r0.deleteView(r9, r15)     // Catch:{ all -> 0x001f }
            goto L_0x0166
        L_0x0160:
            r9 = r6
            r16 = r7
            r8.dropView(r9)     // Catch:{ all -> 0x001f }
        L_0x0166:
            int r7 = r16 + 1
            r0 = r18
            r9 = r19
            goto L_0x0124
        L_0x016d:
            com.facebook.react.uimanager.IllegalViewOperationException r0 = new com.facebook.react.uimanager.IllegalViewOperationException     // Catch:{ all -> 0x001f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x001f }
            r2.<init>()     // Catch:{ all -> 0x001f }
            java.lang.String r3 = "Trying to destroy unknown view tag: "
            r2.append(r3)     // Catch:{ all -> 0x001f }
            r2.append(r1)     // Catch:{ all -> 0x001f }
            java.lang.String r1 = "\n detail: "
            r2.append(r1)     // Catch:{ all -> 0x001f }
            r1 = r19
            java.lang.String r1 = constructManageChildrenErrorMessage(r13, r14, r1, r10, r11)     // Catch:{ all -> 0x001f }
            r2.append(r1)     // Catch:{ all -> 0x001f }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x001f }
            r0.<init>(r1)     // Catch:{ all -> 0x001f }
            throw r0     // Catch:{ all -> 0x001f }
        L_0x0192:
            r1 = r9
            if (r10 == 0) goto L_0x01fe
            r0 = 0
        L_0x0196:
            int r2 = r10.length     // Catch:{ all -> 0x001f }
            if (r0 >= r2) goto L_0x01fe
            r2 = r10[r0]     // Catch:{ all -> 0x001f }
            android.util.SparseArray<android.view.View> r3 = r8.mTagsToViews     // Catch:{ all -> 0x001f }
            int r4 = r2.mTag     // Catch:{ all -> 0x001f }
            java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x001f }
            android.view.View r3 = (android.view.View) r3     // Catch:{ all -> 0x001f }
            if (r3 == 0) goto L_0x01d9
            int r4 = r2.mIndex     // Catch:{ all -> 0x001f }
            boolean r5 = r12.isEmpty()     // Catch:{ all -> 0x001f }
            if (r5 != 0) goto L_0x01d3
            r4 = 0
            r5 = 0
        L_0x01b1:
            int r6 = r13.getChildCount()     // Catch:{ all -> 0x001f }
            if (r4 >= r6) goto L_0x01d3
            int r6 = r2.mIndex     // Catch:{ all -> 0x001f }
            if (r5 != r6) goto L_0x01bc
            goto L_0x01d3
        L_0x01bc:
            android.view.View r6 = r13.getChildAt(r4)     // Catch:{ all -> 0x001f }
            int r6 = r6.getId()     // Catch:{ all -> 0x001f }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x001f }
            boolean r6 = r12.contains(r6)     // Catch:{ all -> 0x001f }
            if (r6 != 0) goto L_0x01d0
            int r5 = r5 + 1
        L_0x01d0:
            int r4 = r4 + 1
            goto L_0x01b1
        L_0x01d3:
            r14.addView(r13, (android.view.View) r3, (int) r4)     // Catch:{ all -> 0x001f }
            int r0 = r0 + 1
            goto L_0x0196
        L_0x01d9:
            com.facebook.react.uimanager.IllegalViewOperationException r0 = new com.facebook.react.uimanager.IllegalViewOperationException     // Catch:{ all -> 0x001f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x001f }
            r3.<init>()     // Catch:{ all -> 0x001f }
            java.lang.String r4 = "Trying to add unknown view tag: "
            r3.append(r4)     // Catch:{ all -> 0x001f }
            int r2 = r2.mTag     // Catch:{ all -> 0x001f }
            r3.append(r2)     // Catch:{ all -> 0x001f }
            java.lang.String r2 = "\n detail: "
            r3.append(r2)     // Catch:{ all -> 0x001f }
            java.lang.String r1 = constructManageChildrenErrorMessage(r13, r14, r1, r10, r11)     // Catch:{ all -> 0x001f }
            r3.append(r1)     // Catch:{ all -> 0x001f }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x001f }
            r0.<init>(r1)     // Catch:{ all -> 0x001f }
            throw r0     // Catch:{ all -> 0x001f }
        L_0x01fe:
            boolean r0 = r12.isEmpty()     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x020d
            java.util.HashMap<java.lang.Integer, java.util.Set<java.lang.Integer>> r0 = r8.mPendingDeletionsForTag     // Catch:{ all -> 0x001f }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r18)     // Catch:{ all -> 0x001f }
            r0.remove(r1)     // Catch:{ all -> 0x001f }
        L_0x020d:
            monitor-exit(r17)
            return
        L_0x020f:
            r1 = r9
            com.facebook.react.uimanager.IllegalViewOperationException r0 = new com.facebook.react.uimanager.IllegalViewOperationException     // Catch:{ all -> 0x001f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x001f }
            r2.<init>()     // Catch:{ all -> 0x001f }
            java.lang.String r3 = "Trying to manageChildren view with tag "
            r2.append(r3)     // Catch:{ all -> 0x001f }
            r3 = r18
            r2.append(r3)     // Catch:{ all -> 0x001f }
            java.lang.String r3 = " which doesn't exist\n detail: "
            r2.append(r3)     // Catch:{ all -> 0x001f }
            java.lang.String r1 = constructManageChildrenErrorMessage(r13, r14, r1, r10, r11)     // Catch:{ all -> 0x001f }
            r2.append(r1)     // Catch:{ all -> 0x001f }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x001f }
            r0.<init>(r1)     // Catch:{ all -> 0x001f }
            throw r0     // Catch:{ all -> 0x001f }
        L_0x0235:
            monitor-exit(r17)     // Catch:{ all -> 0x001f }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.NativeViewHierarchyManager.manageChildren(int, int[], com.facebook.react.uimanager.ViewAtIndex[], int[]):void");
    }

    private boolean arrayContains(int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private static String constructSetChildrenErrorMessage(ViewGroup viewGroup, ViewGroupManager viewGroupManager, ReadableArray readableArray) {
        ViewAtIndex[] viewAtIndexArr = new ViewAtIndex[readableArray.size()];
        for (int i = 0; i < readableArray.size(); i++) {
            viewAtIndexArr[i] = new ViewAtIndex(readableArray.getInt(i), i);
        }
        return constructManageChildrenErrorMessage(viewGroup, viewGroupManager, (int[]) null, viewAtIndexArr, (int[]) null);
    }

    public synchronized void setChildren(int i, ReadableArray readableArray) {
        try {
            if (this.DEBUG_MODE) {
                FLog.d(TAG, "setChildren[%d]: %s", Integer.valueOf(i), readableArray != null ? readableArray.toString() : "<null>");
            }
            UiThreadUtil.assertOnUiThread();
            ViewGroup viewGroup = (ViewGroup) this.mTagsToViews.get(i);
            ViewGroupManager viewGroupManager = (ViewGroupManager) resolveViewManager(i);
            int i2 = 0;
            while (i2 < readableArray.size()) {
                View view = this.mTagsToViews.get(readableArray.getInt(i2));
                if (view != null) {
                    viewGroupManager.addView(viewGroup, view, i2);
                    i2++;
                } else {
                    throw new IllegalViewOperationException("Trying to add unknown view tag: " + readableArray.getInt(i2) + "\n detail: " + constructSetChildrenErrorMessage(viewGroup, viewGroupManager, readableArray));
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized void addRootView(int i, View view) {
        addRootViewGroup(i, view);
    }

    /* access modifiers changed from: protected */
    public final synchronized void addRootViewGroup(int i, View view) {
        try {
            if (this.DEBUG_MODE) {
                FLog.d(TAG, "addRootViewGroup[%d]: %s", Integer.valueOf(i), view != null ? view.toString() : "<null>");
            }
            if (view.getId() != -1) {
                String str = TAG;
                FLog.e(str, "Trying to add a root view with an explicit id (" + view.getId() + ") already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addRootView.");
            }
            this.mTagsToViews.put(i, view);
            this.mTagsToViewManagers.put(i, this.mRootViewManager);
            this.mRootTags.put(i, true);
            view.setId(i);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void dropView(View view) {
        try {
            if (this.DEBUG_MODE) {
                FLog.d(TAG, "dropView[%d]", (Object) Integer.valueOf(view != null ? view.getId() : -1));
            }
            UiThreadUtil.assertOnUiThread();
            if (view != null) {
                if (this.mTagsToViewManagers.get(view.getId()) != null) {
                    if (!this.mRootTags.get(view.getId())) {
                        resolveViewManager(view.getId()).onDropViewInstance(view);
                    }
                    ViewManager viewManager = this.mTagsToViewManagers.get(view.getId());
                    if ((view instanceof ViewGroup) && (viewManager instanceof ViewGroupManager)) {
                        ViewGroup viewGroup = (ViewGroup) view;
                        ViewGroupManager viewGroupManager = (ViewGroupManager) viewManager;
                        for (int childCount = viewGroupManager.getChildCount(viewGroup) - 1; childCount >= 0; childCount--) {
                            View childAt = viewGroupManager.getChildAt(viewGroup, childCount);
                            if (childAt == null) {
                                FLog.e(TAG, "Unable to drop null child view");
                            } else if (this.mTagsToViews.get(childAt.getId()) != null) {
                                dropView(childAt);
                            }
                        }
                        viewGroupManager.removeAllViews(viewGroup);
                    }
                    this.mTagsToViews.remove(view.getId());
                    this.mTagsToViewManagers.remove(view.getId());
                }
            }
        } finally {
            while (true) {
            }
        }
    }

    public synchronized void removeRootView(int i) {
        try {
            if (this.DEBUG_MODE) {
                FLog.d(TAG, "removeRootView[%d]", (Object) Integer.valueOf(i));
            }
            UiThreadUtil.assertOnUiThread();
            if (!this.mRootTags.get(i)) {
                SoftAssertions.assertUnreachable("View with tag " + i + " is not registered as a root view");
            }
            View view = this.mTagsToViews.get(i);
            dropView(view);
            this.mRootTags.delete(i);
            if (view != null) {
                view.setId(-1);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized int getRootViewNum() {
        return this.mRootTags.size();
    }

    public synchronized void measure(int i, int[] iArr) {
        try {
            if (this.DEBUG_MODE) {
                FLog.d(TAG, "measure[%d]", (Object) Integer.valueOf(i));
            }
            UiThreadUtil.assertOnUiThread();
            View view = this.mTagsToViews.get(i);
            if (view != null) {
                View view2 = (View) RootViewUtil.getRootView(view);
                if (view2 != null) {
                    computeBoundingBox(view2, iArr);
                    int i2 = iArr[0];
                    int i3 = iArr[1];
                    computeBoundingBox(view, iArr);
                    iArr[0] = iArr[0] - i2;
                    iArr[1] = iArr[1] - i3;
                } else {
                    throw new NoSuchNativeViewException("Native view " + i + " is no longer on screen");
                }
            } else {
                throw new NoSuchNativeViewException("No native view for " + i + " currently exists");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private void computeBoundingBox(View view, int[] iArr) {
        this.mBoundingBox.set(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
        mapRectFromViewToWindowCoords(view, this.mBoundingBox);
        iArr[0] = Math.round(this.mBoundingBox.left);
        iArr[1] = Math.round(this.mBoundingBox.top);
        RectF rectF = this.mBoundingBox;
        iArr[2] = Math.round(rectF.right - rectF.left);
        RectF rectF2 = this.mBoundingBox;
        iArr[3] = Math.round(rectF2.bottom - rectF2.top);
    }

    private void mapRectFromViewToWindowCoords(View view, RectF rectF) {
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            matrix.mapRect(rectF);
        }
        rectF.offset((float) view.getLeft(), (float) view.getTop());
        ViewParent parent = view.getParent();
        while (parent instanceof View) {
            View view2 = (View) parent;
            rectF.offset((float) (-view2.getScrollX()), (float) (-view2.getScrollY()));
            Matrix matrix2 = view2.getMatrix();
            if (!matrix2.isIdentity()) {
                matrix2.mapRect(rectF);
            }
            rectF.offset((float) view2.getLeft(), (float) view2.getTop());
            parent = view2.getParent();
        }
    }

    public synchronized void measureInWindow(int i, int[] iArr) {
        try {
            if (this.DEBUG_MODE) {
                FLog.d(TAG, "measureInWindow[%d]", (Object) Integer.valueOf(i));
            }
            UiThreadUtil.assertOnUiThread();
            View view = this.mTagsToViews.get(i);
            if (view != null) {
                view.getLocationOnScreen(iArr);
                Rect rect = new Rect();
                view.getWindowVisibleDisplayFrame(rect);
                iArr[0] = iArr[0] - rect.left;
                iArr[1] = iArr[1] - rect.top;
                iArr[2] = view.getWidth();
                iArr[3] = view.getHeight();
            } else {
                throw new NoSuchNativeViewException("No native view for " + i + " currently exists");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized int findTargetTagForTouch(int i, float f, float f2) {
        View view;
        try {
            if (this.DEBUG_MODE) {
                FLog.d(TAG, "findTargetTagForTouch[%d]: %f %f", Integer.valueOf(i), Float.valueOf(f), Float.valueOf(f2));
            }
            UiThreadUtil.assertOnUiThread();
            view = this.mTagsToViews.get(i);
            if (view != null) {
            } else {
                throw new JSApplicationIllegalArgumentException("Could not find view with tag " + i);
            }
        } catch (Throwable th) {
            throw th;
        }
        return TouchTargetHelper.findTargetTagForTouch(f, f2, (ViewGroup) view);
    }

    public synchronized void setJSResponder(int i, int i2, boolean z) {
        if (!z) {
            this.mJSResponderHandler.setJSResponder(i2, (ViewParent) null);
            return;
        }
        View view = this.mTagsToViews.get(i);
        if (i2 == i || !(view instanceof ViewParent)) {
            if (this.mRootTags.get(i)) {
                SoftAssertions.assertUnreachable("Cannot block native responder on " + i + " that is a root view");
            }
            this.mJSResponderHandler.setJSResponder(i2, view.getParent());
            return;
        }
        this.mJSResponderHandler.setJSResponder(i2, (ViewParent) view);
    }

    public synchronized void clearJSResponder() {
        this.mJSResponderHandler.clearJSResponder();
    }

    /* access modifiers changed from: package-private */
    public synchronized void configureLayoutAnimation(ReadableMap readableMap, Callback callback) {
        this.mLayoutAnimator.initializeFromConfig(readableMap, callback);
    }

    /* access modifiers changed from: package-private */
    public synchronized void clearLayoutAnimation() {
        this.mLayoutAnimator.reset();
    }

    @Deprecated
    public synchronized void dispatchCommand(int i, int i2, ReadableArray readableArray) {
        String str;
        try {
            if (this.DEBUG_MODE) {
                String str2 = TAG;
                Integer valueOf = Integer.valueOf(i);
                Integer valueOf2 = Integer.valueOf(i2);
                if (readableArray != null) {
                    str = readableArray.toString();
                } else {
                    str = "<null>";
                }
                FLog.d(str2, "dispatchCommand[%d]: %d %s", valueOf, valueOf2, str);
            }
            UiThreadUtil.assertOnUiThread();
            View view = this.mTagsToViews.get(i);
            if (view != null) {
                resolveViewManager(i).receiveCommand(view, i2, readableArray);
            } else {
                throw new RetryableMountingLayerException("Trying to send command to a non-existing view with tag [" + i + "] and command " + i2);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void dispatchCommand(int i, String str, ReadableArray readableArray) {
        String str2;
        try {
            if (this.DEBUG_MODE) {
                String str3 = TAG;
                Integer valueOf = Integer.valueOf(i);
                if (readableArray != null) {
                    str2 = readableArray.toString();
                } else {
                    str2 = "<null>";
                }
                FLog.d(str3, "dispatchCommand[%d]: %s %s", valueOf, str, str2);
            }
            UiThreadUtil.assertOnUiThread();
            View view = this.mTagsToViews.get(i);
            if (view != null) {
                resolveViewManager(i).receiveCommand(view, str, readableArray);
            } else {
                throw new RetryableMountingLayerException("Trying to send command to a non-existing view with tag [" + i + "] and command " + str);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private ThemedReactContext getReactContextForView(int i) {
        View view = this.mTagsToViews.get(i);
        if (view != null) {
            return (ThemedReactContext) view.getContext();
        }
        throw new JSApplicationIllegalArgumentException("Could not find view with tag " + i);
    }

    public synchronized void sendAccessibilityEvent(int i, int i2) {
        View view = this.mTagsToViews.get(i);
        if (view != null) {
            view.sendAccessibilityEvent(i2);
        } else {
            throw new RetryableMountingLayerException("Could not find view with tag " + i);
        }
    }
}
