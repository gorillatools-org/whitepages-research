package com.facebook.react.fabric.mounting;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.collection.SparseArrayCompat;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.mountitems.MountItem;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.IViewGroupManager;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.ReactOverflowViewWithInset;
import com.facebook.react.uimanager.ReactRoot;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.RootViewManager;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.systrace.Systrace;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SurfaceMountingManager {
    private static final boolean SHOW_CHANGED_VIEW_HIERARCHIES = false;
    public static final String TAG = "SurfaceMountingManager";
    private final Set<Integer> mErroneouslyReaddedReactTags = new HashSet();
    private volatile boolean mIsStopped = false;
    private JSResponderHandler mJSResponderHandler;
    private MountingManager.MountItemExecutor mMountItemExecutor;
    private Queue<MountItem> mOnViewAttachMountItems = new ArrayDeque();
    private volatile boolean mRootViewAttached = false;
    private RootViewManager mRootViewManager;
    private final int mSurfaceId;
    private SparseArrayCompat mTagSetForStoppedSurface;
    private ConcurrentHashMap<Integer, ViewState> mTagToViewState = new ConcurrentHashMap<>();
    private ThemedReactContext mThemedReactContext;
    private ViewManagerRegistry mViewManagerRegistry;
    private final Set<Integer> mViewsToDeleteAfterTouchFinishes = new HashSet();
    private final Set<Integer> mViewsWithActiveTouches = new HashSet();

    static {
        ReactBuildConfig reactBuildConfig = ReactBuildConfig.INSTANCE;
    }

    public SurfaceMountingManager(int i, JSResponderHandler jSResponderHandler, ViewManagerRegistry viewManagerRegistry, RootViewManager rootViewManager, MountingManager.MountItemExecutor mountItemExecutor, ThemedReactContext themedReactContext) {
        this.mSurfaceId = i;
        this.mJSResponderHandler = jSResponderHandler;
        this.mViewManagerRegistry = viewManagerRegistry;
        this.mRootViewManager = rootViewManager;
        this.mMountItemExecutor = mountItemExecutor;
        this.mThemedReactContext = themedReactContext;
    }

    public boolean isStopped() {
        return this.mIsStopped;
    }

    public void attachRootView(View view, ThemedReactContext themedReactContext) {
        this.mThemedReactContext = themedReactContext;
        addRootView(view);
    }

    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    public boolean isRootViewAttached() {
        return this.mRootViewAttached;
    }

    public ThemedReactContext getContext() {
        return this.mThemedReactContext;
    }

    /* access modifiers changed from: private */
    public static void logViewHierarchy(ViewGroup viewGroup, boolean z) {
        int i;
        int id = viewGroup.getId();
        String str = TAG;
        FLog.e(str, "  <ViewGroup tag=" + id + " class=" + viewGroup.getClass().toString() + ">");
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            String str2 = TAG;
            FLog.e(str2, "     <View idx=" + i2 + " tag=" + viewGroup.getChildAt(i2).getId() + " class=" + viewGroup.getChildAt(i2).getClass().toString() + ">");
        }
        String str3 = TAG;
        FLog.e(str3, "  </ViewGroup tag=" + id + ">");
        if (z) {
            FLog.e(str3, "Displaying Ancestors:");
            for (ViewParent parent = viewGroup.getParent(); parent != null; parent = parent.getParent()) {
                ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                if (viewGroup2 == null) {
                    i = -1;
                } else {
                    i = viewGroup2.getId();
                }
                String str4 = TAG;
                FLog.e(str4, "<ViewParent tag=" + i + " class=" + parent.getClass().toString() + ">");
            }
        }
    }

    public boolean getViewExists(int i) {
        SparseArrayCompat sparseArrayCompat = this.mTagSetForStoppedSurface;
        if (sparseArrayCompat != null && sparseArrayCompat.containsKey(i)) {
            return true;
        }
        ConcurrentHashMap<Integer, ViewState> concurrentHashMap = this.mTagToViewState;
        if (concurrentHashMap == null) {
            return false;
        }
        return concurrentHashMap.containsKey(Integer.valueOf(i));
    }

    public void scheduleMountItemOnViewAttach(MountItem mountItem) {
        this.mOnViewAttachMountItems.add(mountItem);
    }

    private void addRootView(View view) {
        if (!isStopped()) {
            this.mTagToViewState.put(Integer.valueOf(this.mSurfaceId), new ViewState(this.mSurfaceId, view, this.mRootViewManager, true));
            SurfaceMountingManager$$ExternalSyntheticLambda0 surfaceMountingManager$$ExternalSyntheticLambda0 = new SurfaceMountingManager$$ExternalSyntheticLambda0(this, view);
            if (UiThreadUtil.isOnUiThread()) {
                surfaceMountingManager$$ExternalSyntheticLambda0.run();
            } else {
                UiThreadUtil.runOnUiThread(surfaceMountingManager$$ExternalSyntheticLambda0);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addRootView$0(View view) {
        if (!isStopped()) {
            if (view.getId() == this.mSurfaceId) {
                String str = TAG;
                ReactSoftExceptionLogger.logSoftException(str, new IllegalViewOperationException("Race condition in addRootView detected. Trying to set an id of [" + this.mSurfaceId + "] on the RootView, but that id has already been set. "));
            } else if (view.getId() != -1) {
                String str2 = TAG;
                FLog.e(str2, "Trying to add RootTag to RootView that already has a tag: existing tag: [%d] new tag: [%d]", Integer.valueOf(view.getId()), Integer.valueOf(this.mSurfaceId));
                ReactSoftExceptionLogger.logSoftException(str2, new IllegalViewOperationException("Trying to add a root view with an explicit id already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addRootView."));
            }
            view.setId(this.mSurfaceId);
            if (view instanceof ReactRoot) {
                ((ReactRoot) view).setRootViewTag(this.mSurfaceId);
            }
            executeMountItemsOnViewAttach();
            this.mRootViewAttached = true;
        }
    }

    private void executeMountItemsOnViewAttach() {
        this.mMountItemExecutor.executeItems(this.mOnViewAttachMountItems);
    }

    public void stopSurface() {
        String str = TAG;
        FLog.e(str, "Stopping surface [" + this.mSurfaceId + "]");
        if (!isStopped()) {
            this.mIsStopped = true;
            for (ViewState next : this.mTagToViewState.values()) {
                StateWrapper stateWrapper = next.mStateWrapper;
                if (stateWrapper != null) {
                    stateWrapper.destroyState();
                    next.mStateWrapper = null;
                }
                EventEmitterWrapper eventEmitterWrapper = next.mEventEmitter;
                if (eventEmitterWrapper != null) {
                    eventEmitterWrapper.destroy();
                    next.mEventEmitter = null;
                }
            }
            SurfaceMountingManager$$ExternalSyntheticLambda1 surfaceMountingManager$$ExternalSyntheticLambda1 = new SurfaceMountingManager$$ExternalSyntheticLambda1(this);
            if (UiThreadUtil.isOnUiThread()) {
                surfaceMountingManager$$ExternalSyntheticLambda1.run();
            } else {
                UiThreadUtil.runOnUiThread(surfaceMountingManager$$ExternalSyntheticLambda1);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$stopSurface$1() {
        if (ReactNativeFeatureFlags.enableViewRecycling()) {
            this.mViewManagerRegistry.onSurfaceStopped(this.mSurfaceId);
        }
        this.mTagSetForStoppedSurface = new SparseArrayCompat();
        for (Map.Entry next : this.mTagToViewState.entrySet()) {
            this.mTagSetForStoppedSurface.put(((Integer) next.getKey()).intValue(), this);
            onViewStateDeleted((ViewState) next.getValue());
        }
        this.mTagToViewState = null;
        this.mJSResponderHandler = null;
        this.mRootViewManager = null;
        this.mMountItemExecutor = null;
        this.mThemedReactContext = null;
        this.mOnViewAttachMountItems.clear();
        String str = TAG;
        FLog.e(str, "Surface [" + this.mSurfaceId + "] was stopped on SurfaceMountingManager.");
    }

    public void addViewAt(int i, int i2, int i3) {
        UiThreadUtil.assertOnUiThread();
        if (!isStopped()) {
            ViewState viewState = getViewState(i);
            View view = viewState.mView;
            if (view instanceof ViewGroup) {
                final ViewGroup viewGroup = (ViewGroup) view;
                ViewState viewState2 = getViewState(i2);
                View view2 = viewState2.mView;
                if (view2 != null) {
                    boolean z = SHOW_CHANGED_VIEW_HIERARCHIES;
                    if (z) {
                        FLog.e(TAG, "addViewAt: [" + i2 + "] -> [" + i + "] idx: " + i3 + " BEFORE");
                        logViewHierarchy(viewGroup, false);
                    }
                    ViewParent parent = view2.getParent();
                    if (parent != null) {
                        boolean z2 = parent instanceof ViewGroup;
                        int id = z2 ? ((ViewGroup) parent).getId() : -1;
                        ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("addViewAt: cannot insert view [" + i2 + "] into parent [" + i + "]: View already has a parent: [" + id + "]  Parent: " + parent.getClass().getSimpleName() + " View: " + view2.getClass().getSimpleName()));
                        if (z2) {
                            ((ViewGroup) parent).removeView(view2);
                        }
                        this.mErroneouslyReaddedReactTags.add(Integer.valueOf(i2));
                    }
                    try {
                        getViewGroupManager(viewState).addView(viewGroup, view2, i3);
                        if (z) {
                            final int i4 = i2;
                            final int i5 = i;
                            final int i6 = i3;
                            UiThreadUtil.runOnUiThread(new Runnable() {
                                public void run() {
                                    String str = SurfaceMountingManager.TAG;
                                    FLog.e(str, "addViewAt: [" + i4 + "] -> [" + i5 + "] idx: " + i6 + " AFTER");
                                    SurfaceMountingManager.logViewHierarchy(viewGroup, false);
                                }
                            });
                        }
                    } catch (IllegalStateException | IndexOutOfBoundsException e) {
                        throw new IllegalStateException("addViewAt: failed to insert view [" + i2 + "] into parent [" + i + "] at index " + i3, e);
                    }
                } else {
                    throw new IllegalStateException("Unable to find view for viewState " + viewState2 + " and tag " + i2);
                }
            } else {
                String str = "Unable to add a view into a view that is not a ViewGroup. ParentTag: " + i + " - Tag: " + i2 + " - Index: " + i3;
                FLog.e(TAG, str);
                throw new IllegalStateException(str);
            }
        }
    }

    public void removeViewAt(int i, int i2, int i3) {
        final int i4;
        if (!isStopped()) {
            if (this.mErroneouslyReaddedReactTags.contains(Integer.valueOf(i))) {
                ReactSoftExceptionLogger.logSoftException(TAG, new IllegalViewOperationException("removeViewAt tried to remove a React View that was actually reused. This indicates a bug in the Differ (specifically instruction ordering). [" + i + "]"));
                return;
            }
            UiThreadUtil.assertOnUiThread();
            ViewState nullableViewState = getNullableViewState(i2);
            if (nullableViewState == null) {
                ReactSoftExceptionLogger.logSoftException(MountingManager.TAG, new IllegalStateException("Unable to find viewState for tag: [" + i2 + "] for removeViewAt"));
                return;
            }
            View view = nullableViewState.mView;
            if (view instanceof ViewGroup) {
                final ViewGroup viewGroup = (ViewGroup) view;
                if (viewGroup != null) {
                    int i5 = 0;
                    if (SHOW_CHANGED_VIEW_HIERARCHIES) {
                        FLog.e(TAG, "removeViewAt: [" + i + "] -> [" + i2 + "] idx: " + i3 + " BEFORE");
                        logViewHierarchy(viewGroup, false);
                    }
                    IViewGroupManager<ViewGroup> viewGroupManager = getViewGroupManager(nullableViewState);
                    View childAt = viewGroupManager.getChildAt(viewGroup, i3);
                    int id = childAt != null ? childAt.getId() : -1;
                    if (id != i) {
                        int childCount = viewGroup.getChildCount();
                        while (true) {
                            if (i5 >= childCount) {
                                i5 = -1;
                                break;
                            } else if (viewGroup.getChildAt(i5).getId() == i) {
                                break;
                            } else {
                                i5++;
                            }
                        }
                        if (i5 == -1) {
                            FLog.e(TAG, "removeViewAt: [" + i + "] -> [" + i2 + "] @" + i3 + ": view already removed from parent! Children in parent: " + childCount);
                            return;
                        }
                        logViewHierarchy(viewGroup, true);
                        ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("Tried to remove view [" + i + "] of parent [" + i2 + "] at index " + i3 + ", but got view tag " + id + " - actual index of view: " + i5));
                        i4 = i5;
                    } else {
                        i4 = i3;
                    }
                    try {
                        viewGroupManager.removeViewAt(viewGroup, i4);
                        if (SHOW_CHANGED_VIEW_HIERARCHIES) {
                            final int i6 = i;
                            final int i7 = i2;
                            UiThreadUtil.runOnUiThread(new Runnable() {
                                public void run() {
                                    String str = SurfaceMountingManager.TAG;
                                    FLog.e(str, "removeViewAt: [" + i6 + "] -> [" + i7 + "] idx: " + i4 + " AFTER");
                                    SurfaceMountingManager.logViewHierarchy(viewGroup, false);
                                }
                            });
                        }
                    } catch (RuntimeException e) {
                        int childCount2 = viewGroupManager.getChildCount(viewGroup);
                        logViewHierarchy(viewGroup, true);
                        throw new IllegalStateException("Cannot remove child at index " + i4 + " from parent ViewGroup [" + viewGroup.getId() + "], only " + childCount2 + " children in parent. Warning: childCount may be incorrect!", e);
                    }
                } else {
                    throw new IllegalStateException("Unable to find view for tag [" + i2 + "]");
                }
            } else {
                String str = "Unable to remove a view from a view that is not a ViewGroup. ParentTag: " + i2 + " - Tag: " + i + " - Index: " + i3;
                FLog.e(TAG, str);
                throw new IllegalStateException(str);
            }
        }
    }

    public void createView(String str, int i, ReadableMap readableMap, StateWrapper stateWrapper, EventEmitterWrapper eventEmitterWrapper, boolean z) {
        if (!isStopped()) {
            ViewState nullableViewState = getNullableViewState(i);
            if (nullableViewState == null || nullableViewState.mView == null) {
                createViewUnsafe(str, i, readableMap, stateWrapper, eventEmitterWrapper, z);
            }
        }
    }

    public void createViewUnsafe(String str, int i, ReadableMap readableMap, StateWrapper stateWrapper, EventEmitterWrapper eventEmitterWrapper, boolean z) {
        Systrace.beginSection(0, "SurfaceMountingManager::createViewUnsafe(" + str + ")");
        try {
            ReactStylesDiffMap reactStylesDiffMap = new ReactStylesDiffMap(readableMap);
            ViewState viewState = new ViewState(i);
            viewState.mCurrentProps = reactStylesDiffMap;
            viewState.mStateWrapper = stateWrapper;
            viewState.mEventEmitter = eventEmitterWrapper;
            this.mTagToViewState.put(Integer.valueOf(i), viewState);
            if (z) {
                ViewManager viewManager = this.mViewManagerRegistry.get(str);
                viewState.mView = viewManager.createView(i, this.mThemedReactContext, reactStylesDiffMap, stateWrapper, this.mJSResponderHandler);
                viewState.mViewManager = viewManager;
            }
        } finally {
            Systrace.endSection(0);
        }
    }

    public void updateProps(int i, ReadableMap readableMap) {
        if (!isStopped()) {
            ViewState viewState = getViewState(i);
            viewState.mCurrentProps = new ReactStylesDiffMap(readableMap);
            View view = viewState.mView;
            if (view != null) {
                ((ViewManager) Assertions.assertNotNull(viewState.mViewManager)).updateProperties(view, viewState.mCurrentProps);
                return;
            }
            throw new IllegalStateException("Unable to find view for tag [" + i + "]");
        }
    }

    @Deprecated
    public void receiveCommand(int i, int i2, ReadableArray readableArray) {
        if (!isStopped()) {
            ViewState nullableViewState = getNullableViewState(i);
            if (nullableViewState != null) {
                ViewManager viewManager = nullableViewState.mViewManager;
                if (viewManager != null) {
                    View view = nullableViewState.mView;
                    if (view != null) {
                        viewManager.receiveCommand(view, i2, readableArray);
                        return;
                    }
                    throw new RetryableMountingLayerException("Unable to find viewState view for tag " + i);
                }
                throw new RetryableMountingLayerException("Unable to find viewManager for tag " + i);
            }
            throw new RetryableMountingLayerException("Unable to find viewState for tag: [" + i + "] for commandId: " + i2);
        }
    }

    public void receiveCommand(int i, String str, ReadableArray readableArray) {
        if (!isStopped()) {
            ViewState nullableViewState = getNullableViewState(i);
            if (nullableViewState != null) {
                ViewManager viewManager = nullableViewState.mViewManager;
                if (viewManager != null) {
                    View view = nullableViewState.mView;
                    if (view != null) {
                        viewManager.receiveCommand(view, str, readableArray);
                        return;
                    }
                    throw new RetryableMountingLayerException("Unable to find viewState view for tag " + i);
                }
                throw new RetryableMountingLayerException("Unable to find viewState manager for tag " + i);
            }
            throw new RetryableMountingLayerException("Unable to find viewState for tag: " + i + " for commandId: " + str);
        }
    }

    public void sendAccessibilityEvent(int i, int i2) {
        if (!isStopped()) {
            ViewState viewState = getViewState(i);
            if (viewState.mViewManager != null) {
                View view = viewState.mView;
                if (view != null) {
                    view.sendAccessibilityEvent(i2);
                    return;
                }
                throw new RetryableMountingLayerException("Unable to find viewState view for tag " + i);
            }
            throw new RetryableMountingLayerException("Unable to find viewState manager for tag " + i);
        }
    }

    public void updateLayout(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (!isStopped()) {
            ViewState viewState = getViewState(i);
            if (!viewState.mIsRoot) {
                View view = viewState.mView;
                if (view != null) {
                    int i9 = 0;
                    int i10 = 1;
                    if (i8 == 1) {
                        i10 = 0;
                    } else if (i8 != 2) {
                        i10 = 2;
                    }
                    view.setLayoutDirection(i10);
                    view.measure(View.MeasureSpec.makeMeasureSpec(i5, 1073741824), View.MeasureSpec.makeMeasureSpec(i6, 1073741824));
                    ViewParent parent = view.getParent();
                    if (parent instanceof RootView) {
                        parent.requestLayout();
                    }
                    ViewManager viewManager = getViewState(i2).mViewManager;
                    IViewGroupManager iViewGroupManager = viewManager != null ? (IViewGroupManager) viewManager : null;
                    if (iViewGroupManager == null || !iViewGroupManager.needsCustomLayoutForChildren()) {
                        view.layout(i3, i4, i5 + i3, i6 + i4);
                    }
                    if (i7 == 0) {
                        i9 = 4;
                    }
                    if (view.getVisibility() != i9) {
                        view.setVisibility(i9);
                        return;
                    }
                    return;
                }
                throw new IllegalStateException("Unable to find View for tag: " + i);
            }
        }
    }

    public void updatePadding(int i, int i2, int i3, int i4, int i5) {
        UiThreadUtil.assertOnUiThread();
        if (!isStopped()) {
            ViewState viewState = getViewState(i);
            if (!viewState.mIsRoot) {
                View view = viewState.mView;
                if (view != null) {
                    ViewManager viewManager = viewState.mViewManager;
                    if (viewManager != null) {
                        viewManager.setPadding(view, i2, i3, i4, i5);
                        return;
                    }
                    throw new IllegalStateException("Unable to find ViewManager for view: " + viewState);
                }
                throw new IllegalStateException("Unable to find View for tag: " + i);
            }
        }
    }

    public void updateOverflowInset(int i, int i2, int i3, int i4, int i5) {
        if (!isStopped()) {
            ViewState viewState = getViewState(i);
            if (!viewState.mIsRoot) {
                View view = viewState.mView;
                if (view == null) {
                    throw new IllegalStateException("Unable to find View for tag: " + i);
                } else if (view instanceof ReactOverflowViewWithInset) {
                    ((ReactOverflowViewWithInset) view).setOverflowInset(i2, i3, i4, i5);
                }
            }
        }
    }

    public void updateState(int i, StateWrapper stateWrapper) {
        UiThreadUtil.assertOnUiThread();
        if (!isStopped()) {
            ViewState viewState = getViewState(i);
            StateWrapper stateWrapper2 = viewState.mStateWrapper;
            viewState.mStateWrapper = stateWrapper;
            ViewManager viewManager = viewState.mViewManager;
            if (viewManager != null) {
                Object updateState = viewManager.updateState(viewState.mView, viewState.mCurrentProps, stateWrapper);
                if (updateState != null) {
                    viewManager.updateExtraData(viewState.mView, updateState);
                }
                if (stateWrapper2 != null) {
                    stateWrapper2.destroyState();
                    return;
                }
                return;
            }
            throw new IllegalStateException("Unable to find ViewManager for tag: " + i);
        }
    }

    public void updateEventEmitter(int i, EventEmitterWrapper eventEmitterWrapper) {
        UiThreadUtil.assertOnUiThread();
        if (!isStopped()) {
            ViewState viewState = this.mTagToViewState.get(Integer.valueOf(i));
            if (viewState == null) {
                viewState = new ViewState(i);
                this.mTagToViewState.put(Integer.valueOf(i), viewState);
            }
            EventEmitterWrapper eventEmitterWrapper2 = viewState.mEventEmitter;
            viewState.mEventEmitter = eventEmitterWrapper;
            if (!(eventEmitterWrapper2 == eventEmitterWrapper || eventEmitterWrapper2 == null)) {
                eventEmitterWrapper2.destroy();
            }
            Queue<PendingViewEvent> queue = viewState.mPendingEventQueue;
            if (queue != null) {
                for (PendingViewEvent dispatch : queue) {
                    dispatch.dispatch(eventEmitterWrapper);
                }
                viewState.mPendingEventQueue = null;
            }
        }
    }

    public synchronized void setJSResponder(int i, int i2, boolean z) {
        UiThreadUtil.assertOnUiThread();
        if (!isStopped()) {
            if (!z) {
                this.mJSResponderHandler.setJSResponder(i2, (ViewParent) null);
                return;
            }
            ViewState viewState = getViewState(i);
            View view = viewState.mView;
            if (i2 != i && (view instanceof ViewParent)) {
                this.mJSResponderHandler.setJSResponder(i2, (ViewParent) view);
            } else if (view == null) {
                SoftAssertions.assertUnreachable("Cannot find view for tag [" + i + "].");
            } else {
                if (viewState.mIsRoot) {
                    SoftAssertions.assertUnreachable("Cannot block native responder on [" + i + "] that is a root view");
                }
                this.mJSResponderHandler.setJSResponder(i2, view.getParent());
            }
        }
    }

    private void onViewStateDeleted(ViewState viewState) {
        StateWrapper stateWrapper = viewState.mStateWrapper;
        if (stateWrapper != null) {
            stateWrapper.destroyState();
            viewState.mStateWrapper = null;
        }
        EventEmitterWrapper eventEmitterWrapper = viewState.mEventEmitter;
        if (eventEmitterWrapper != null) {
            eventEmitterWrapper.destroy();
            viewState.mEventEmitter = null;
        }
        ViewManager viewManager = viewState.mViewManager;
        if (!viewState.mIsRoot && viewManager != null) {
            viewManager.onDropViewInstance(viewState.mView);
        }
    }

    public void deleteView(int i) {
        UiThreadUtil.assertOnUiThread();
        if (!isStopped()) {
            ViewState nullableViewState = getNullableViewState(i);
            if (nullableViewState == null) {
                String str = MountingManager.TAG;
                ReactSoftExceptionLogger.logSoftException(str, new IllegalStateException("Unable to find viewState for tag: " + i + " for deleteView"));
            } else if (!ReactNativeFeatureFlags.enableEventEmitterRetentionDuringGesturesOnAndroid() || !this.mViewsWithActiveTouches.contains(Integer.valueOf(i))) {
                this.mTagToViewState.remove(Integer.valueOf(i));
                onViewStateDeleted(nullableViewState);
            } else {
                this.mViewsToDeleteAfterTouchFinishes.add(Integer.valueOf(i));
            }
        }
    }

    public void preallocateView(String str, int i, ReadableMap readableMap, StateWrapper stateWrapper, boolean z) {
        UiThreadUtil.assertOnUiThread();
        if (!isStopped() && getNullableViewState(i) == null) {
            createViewUnsafe(str, i, readableMap, stateWrapper, (EventEmitterWrapper) null, z);
        }
    }

    public EventEmitterWrapper getEventEmitter(int i) {
        ViewState nullableViewState = getNullableViewState(i);
        if (nullableViewState == null) {
            return null;
        }
        return nullableViewState.mEventEmitter;
    }

    public View getView(int i) {
        View view;
        ViewState nullableViewState = getNullableViewState(i);
        if (nullableViewState == null) {
            view = null;
        } else {
            view = nullableViewState.mView;
        }
        if (view != null) {
            return view;
        }
        throw new IllegalViewOperationException("Trying to resolve view with tag " + i + " which doesn't exist");
    }

    private ViewState getViewState(int i) {
        ViewState viewState = this.mTagToViewState.get(Integer.valueOf(i));
        if (viewState != null) {
            return viewState;
        }
        throw new RetryableMountingLayerException("Unable to find viewState for tag " + i + ". Surface stopped: " + isStopped());
    }

    private ViewState getNullableViewState(int i) {
        ConcurrentHashMap<Integer, ViewState> concurrentHashMap = this.mTagToViewState;
        if (concurrentHashMap == null) {
            return null;
        }
        return concurrentHashMap.get(Integer.valueOf(i));
    }

    private static IViewGroupManager<ViewGroup> getViewGroupManager(ViewState viewState) {
        ViewManager viewManager = viewState.mViewManager;
        if (viewManager != null) {
            return (IViewGroupManager) viewManager;
        }
        throw new IllegalStateException("Unable to find ViewManager for view: " + viewState);
    }

    public void printSurfaceState() {
        FLog.e(TAG, "Views created for surface {%d}:", Integer.valueOf(getSurfaceId()));
        for (ViewState next : this.mTagToViewState.values()) {
            ViewManager viewManager = next.mViewManager;
            Integer num = null;
            String name = viewManager != null ? viewManager.getName() : null;
            View view = next.mView;
            View view2 = view != null ? (View) view.getParent() : null;
            if (view2 != null) {
                num = Integer.valueOf(view2.getId());
            }
            FLog.e(TAG, "<%s id=%d parentTag=%s isRoot=%b />", name, Integer.valueOf(next.mReactTag), num, Boolean.valueOf(next.mIsRoot));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0005, code lost:
        r2 = r0.get(java.lang.Integer.valueOf(r2));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void enqueuePendingEvent(int r2, java.lang.String r3, boolean r4, com.facebook.react.bridge.WritableMap r5, int r6) {
        /*
            r1 = this;
            java.util.concurrent.ConcurrentHashMap<java.lang.Integer, com.facebook.react.fabric.mounting.SurfaceMountingManager$ViewState> r0 = r1.mTagToViewState
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object r2 = r0.get(r2)
            com.facebook.react.fabric.mounting.SurfaceMountingManager$ViewState r2 = (com.facebook.react.fabric.mounting.SurfaceMountingManager.ViewState) r2
            if (r2 != 0) goto L_0x0012
            return
        L_0x0012:
            com.facebook.react.fabric.mounting.SurfaceMountingManager$PendingViewEvent r0 = new com.facebook.react.fabric.mounting.SurfaceMountingManager$PendingViewEvent
            r0.<init>(r3, r5, r6, r4)
            com.facebook.react.fabric.mounting.SurfaceMountingManager$3 r3 = new com.facebook.react.fabric.mounting.SurfaceMountingManager$3
            r3.<init>(r2, r0)
            com.facebook.react.bridge.UiThreadUtil.runOnUiThread(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.fabric.mounting.SurfaceMountingManager.enqueuePendingEvent(int, java.lang.String, boolean, com.facebook.react.bridge.WritableMap, int):void");
    }

    public void markActiveTouchForTag(int i) {
        if (ReactNativeFeatureFlags.enableEventEmitterRetentionDuringGesturesOnAndroid()) {
            this.mViewsWithActiveTouches.add(Integer.valueOf(i));
        }
    }

    public void sweepActiveTouchForTag(int i) {
        if (ReactNativeFeatureFlags.enableEventEmitterRetentionDuringGesturesOnAndroid()) {
            this.mViewsWithActiveTouches.remove(Integer.valueOf(i));
            if (this.mViewsToDeleteAfterTouchFinishes.contains(Integer.valueOf(i))) {
                this.mViewsToDeleteAfterTouchFinishes.remove(Integer.valueOf(i));
                deleteView(i);
            }
        }
    }

    private static class ViewState {
        ReadableMap mCurrentLocalData;
        ReactStylesDiffMap mCurrentProps;
        EventEmitterWrapper mEventEmitter;
        final boolean mIsRoot;
        Queue<PendingViewEvent> mPendingEventQueue;
        final int mReactTag;
        StateWrapper mStateWrapper;
        View mView;
        ViewManager mViewManager;

        private ViewState(int i) {
            this(i, (View) null, (ViewManager) null, false);
        }

        private ViewState(int i, View view, ViewManager viewManager, boolean z) {
            this.mCurrentProps = null;
            this.mCurrentLocalData = null;
            this.mStateWrapper = null;
            this.mEventEmitter = null;
            this.mPendingEventQueue = null;
            this.mReactTag = i;
            this.mView = view;
            this.mIsRoot = z;
            this.mViewManager = viewManager;
        }

        public String toString() {
            boolean z = this.mViewManager == null;
            return "ViewState [" + this.mReactTag + "] - isRoot: " + this.mIsRoot + " - props: " + this.mCurrentProps + " - localData: " + this.mCurrentLocalData + " - viewManager: " + this.mViewManager + " - isLayoutOnly: " + z;
        }
    }

    private static class PendingViewEvent {
        private final boolean mCanCoalesceEvent;
        private final int mEventCategory;
        private final String mEventName;
        private final WritableMap mParams;

        public PendingViewEvent(String str, WritableMap writableMap, int i, boolean z) {
            this.mEventName = str;
            this.mParams = writableMap;
            this.mEventCategory = i;
            this.mCanCoalesceEvent = z;
        }

        public void dispatch(EventEmitterWrapper eventEmitterWrapper) {
            if (this.mCanCoalesceEvent) {
                eventEmitterWrapper.dispatchUnique(this.mEventName, this.mParams);
            } else {
                eventEmitterWrapper.dispatch(this.mEventName, this.mParams, this.mEventCategory);
            }
        }
    }
}
