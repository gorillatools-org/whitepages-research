package com.facebook.react.views.scroll;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class ReactScrollViewHelper {
    public static final String AUTO = "auto";
    private static final String CONTENT_OFFSET_LEFT = "contentOffsetLeft";
    private static final String CONTENT_OFFSET_TOP = "contentOffsetTop";
    private static final boolean DEBUG_MODE = false;
    public static final ReactScrollViewHelper INSTANCE = new ReactScrollViewHelper();
    public static final long MOMENTUM_DELAY = 20;
    public static final String OVER_SCROLL_ALWAYS = "always";
    public static final String OVER_SCROLL_NEVER = "never";
    private static final String SCROLL_AWAY_PADDING_TOP = "scrollAwayPaddingTop";
    private static int SMOOTH_SCROLL_DURATION = 250;
    public static final int SNAP_ALIGNMENT_CENTER = 2;
    public static final int SNAP_ALIGNMENT_DISABLED = 0;
    public static final int SNAP_ALIGNMENT_END = 3;
    public static final int SNAP_ALIGNMENT_START = 1;
    private static final String TAG = ReactScrollView.class.getSimpleName();
    private static final CopyOnWriteArrayList<WeakReference<LayoutChangeListener>> layoutChangeListeners = new CopyOnWriteArrayList<>();
    private static final CopyOnWriteArrayList<WeakReference<ScrollListener>> scrollListeners = new CopyOnWriteArrayList<>();
    private static boolean smoothScrollDurationInitialized;

    public interface HasFlingAnimator {
        ValueAnimator getFlingAnimator();

        int getFlingExtrapolatedDistance(int i);

        void startFlingAnimator(int i, int i2);
    }

    public interface HasScrollEventThrottle {
        long getLastScrollDispatchTime();

        int getScrollEventThrottle();

        void setLastScrollDispatchTime(long j);

        void setScrollEventThrottle(int i);
    }

    public interface HasScrollState {
        ReactScrollViewScrollState getReactScrollViewScrollState();
    }

    public interface HasSmoothScroll {
        void reactSmoothScrollTo(int i, int i2);

        void scrollToPreservingMomentum(int i, int i2);
    }

    public interface HasStateWrapper {
        StateWrapper getStateWrapper();
    }

    public interface LayoutChangeListener {
        void onLayoutChange(ViewGroup viewGroup);
    }

    public interface ScrollListener {
        void onLayout(ViewGroup viewGroup);

        void onScroll(ViewGroup viewGroup, ScrollEventType scrollEventType, float f, float f2);
    }

    private ReactScrollViewHelper() {
    }

    public static final <T extends ViewGroup & HasScrollEventThrottle> void emitScrollEvent(T t, float f, float f2) {
        INSTANCE.emitScrollEvent(t, ScrollEventType.SCROLL, f, f2);
    }

    public static final <T extends ViewGroup & HasScrollEventThrottle> void emitScrollBeginDragEvent(T t) {
        INSTANCE.emitScrollEvent(t, ScrollEventType.BEGIN_DRAG);
    }

    public static final <T extends ViewGroup & HasScrollEventThrottle> void emitScrollEndDragEvent(T t, float f, float f2) {
        INSTANCE.emitScrollEvent(t, ScrollEventType.END_DRAG, f, f2);
    }

    public static final <T extends ViewGroup & HasScrollEventThrottle> void emitScrollMomentumBeginEvent(T t, int i, int i2) {
        INSTANCE.emitScrollEvent(t, ScrollEventType.MOMENTUM_BEGIN, (float) i, (float) i2);
    }

    public static final <T extends ViewGroup & HasScrollEventThrottle> void emitScrollMomentumEndEvent(T t) {
        INSTANCE.emitScrollEvent(t, ScrollEventType.MOMENTUM_END);
    }

    private final <T extends ViewGroup & HasScrollEventThrottle> void emitScrollEvent(T t, ScrollEventType scrollEventType) {
        emitScrollEvent(t, scrollEventType, 0.0f, 0.0f);
    }

    private final <T extends ViewGroup & HasScrollEventThrottle> void emitScrollEvent(T t, ScrollEventType scrollEventType, float f, float f2) {
        View childAt;
        T t2 = t;
        long currentTimeMillis = System.currentTimeMillis();
        HasScrollEventThrottle hasScrollEventThrottle = (HasScrollEventThrottle) t2;
        if (((long) hasScrollEventThrottle.getScrollEventThrottle()) < Math.max(17, currentTimeMillis - hasScrollEventThrottle.getLastScrollDispatchTime()) && (childAt = t2.getChildAt(0)) != null) {
            Iterator<WeakReference<ScrollListener>> it = scrollListeners.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                ScrollListener scrollListener = (ScrollListener) it.next().get();
                ScrollEventType scrollEventType2 = scrollEventType;
                float f3 = f;
                float f4 = f2;
                if (scrollListener != null) {
                    scrollListener.onScroll(t2, scrollEventType2, f3, f4);
                }
            }
            ScrollEventType scrollEventType3 = scrollEventType;
            float f5 = f;
            float f6 = f2;
            Context context = t.getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            ReactContext reactContext = (ReactContext) context;
            int surfaceId = UIManagerHelper.getSurfaceId((Context) reactContext);
            EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, t.getId());
            if (eventDispatcherForReactTag != null) {
                eventDispatcherForReactTag.dispatchEvent(ScrollEvent.Companion.obtain(surfaceId, t.getId(), scrollEventType, (float) t.getScrollX(), (float) t.getScrollY(), f, f2, childAt.getWidth(), childAt.getHeight(), t.getWidth(), t.getHeight()));
                ((HasScrollEventThrottle) t2).setLastScrollDispatchTime(currentTimeMillis);
            }
        }
    }

    public static final void emitLayoutEvent(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "scrollView");
        Iterator<WeakReference<ScrollListener>> it = scrollListeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            ScrollListener scrollListener = (ScrollListener) it.next().get();
            if (scrollListener != null) {
                scrollListener.onLayout(viewGroup);
            }
        }
    }

    public static final void emitLayoutChangeEvent(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "scrollView");
        Iterator<WeakReference<LayoutChangeListener>> it = layoutChangeListeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            LayoutChangeListener layoutChangeListener = (LayoutChangeListener) it.next().get();
            if (layoutChangeListener != null) {
                layoutChangeListener.onLayoutChange(viewGroup);
            }
        }
    }

    public static final int parseOverScrollMode(String str) {
        if (str == null) {
            return 1;
        }
        int hashCode = str.hashCode();
        if (hashCode != -1414557169) {
            if (hashCode != 3005871) {
                if (hashCode == 104712844 && str.equals(OVER_SCROLL_NEVER)) {
                    return 2;
                }
            } else if (str.equals("auto")) {
                return 1;
            }
        } else if (str.equals(OVER_SCROLL_ALWAYS)) {
            return 0;
        }
        FLog.w(ReactConstants.TAG, "wrong overScrollMode: " + str);
        return 1;
    }

    public static final int parseSnapToAlignment(String str) {
        if (str == null) {
            return 0;
        }
        if (StringsKt.equals(ViewProps.START, str, true)) {
            return 1;
        }
        if (StringsKt.equals("center", str, true)) {
            return 2;
        }
        if (Intrinsics.areEqual((Object) ViewProps.END, (Object) str)) {
            return 3;
        }
        FLog.w(ReactConstants.TAG, "wrong snap alignment value: " + str);
        return 0;
    }

    public static final int getDefaultScrollAnimationDuration(Context context) {
        if (!smoothScrollDurationInitialized) {
            smoothScrollDurationInitialized = true;
            try {
                SMOOTH_SCROLL_DURATION = new OverScrollerDurationGetter(context).getScrollAnimationDuration();
            } catch (Throwable unused) {
            }
        }
        return SMOOTH_SCROLL_DURATION;
    }

    public static final void addScrollListener(ScrollListener scrollListener) {
        Intrinsics.checkNotNullParameter(scrollListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        scrollListeners.add(new WeakReference(scrollListener));
    }

    public static final void removeScrollListener(ScrollListener scrollListener) {
        Intrinsics.checkNotNullParameter(scrollListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        scrollListeners.remove(new WeakReference(scrollListener));
    }

    public static final void addLayoutChangeListener(LayoutChangeListener layoutChangeListener) {
        Intrinsics.checkNotNullParameter(layoutChangeListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        layoutChangeListeners.add(new WeakReference(layoutChangeListener));
    }

    public static final void removeLayoutChangeListener(LayoutChangeListener layoutChangeListener) {
        Intrinsics.checkNotNullParameter(layoutChangeListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        layoutChangeListeners.remove(new WeakReference(layoutChangeListener));
    }

    public static final <T extends ViewGroup & HasFlingAnimator & HasScrollState & HasStateWrapper> void smoothScrollTo(T t, int i, int i2) {
        if (DEBUG_MODE) {
            FLog.i(TAG, "smoothScrollTo[%d] x %d y %d", Integer.valueOf(t.getId()), Integer.valueOf(i), Integer.valueOf(i2));
        }
        HasFlingAnimator hasFlingAnimator = (HasFlingAnimator) t;
        ValueAnimator flingAnimator = hasFlingAnimator.getFlingAnimator();
        if (flingAnimator.getListeners() == null || flingAnimator.getListeners().size() == 0) {
            INSTANCE.registerFlingAnimator(t);
        }
        ((HasScrollState) t).getReactScrollViewScrollState().setFinalAnimatedPositionScroll(i, i2);
        int scrollX = t.getScrollX();
        int scrollY = t.getScrollY();
        if (scrollX != i) {
            hasFlingAnimator.startFlingAnimator(scrollX, i);
        }
        if (scrollY != i2) {
            hasFlingAnimator.startFlingAnimator(scrollY, i2);
        }
    }

    public static final <T extends ViewGroup & HasFlingAnimator & HasScrollState> int getNextFlingStartValue(T t, int i, int i2, int i3) {
        ReactScrollViewScrollState reactScrollViewScrollState = ((HasScrollState) t).getReactScrollViewScrollState();
        boolean z = false;
        if ((i3 != 0 ? i3 / Math.abs(i3) : 0) * (i2 - i) > 0) {
            z = true;
        }
        return (!reactScrollViewScrollState.isFinished() || (reactScrollViewScrollState.isCanceled() && z)) ? i2 : i;
    }

    public static final <T extends ViewGroup & HasFlingAnimator & HasScrollState & HasStateWrapper> void updateFabricScrollState(T t) {
        INSTANCE.updateFabricScrollState(t, t.getScrollX(), t.getScrollY());
    }

    public final <T extends ViewGroup & HasScrollState & HasStateWrapper> void updateFabricScrollState(T t, int i, int i2) {
        if (DEBUG_MODE) {
            FLog.i(TAG, "updateFabricScrollState[%d] scrollX %d scrollY %d", Integer.valueOf(t.getId()), Integer.valueOf(i), Integer.valueOf(i2));
        }
        if (ViewUtil.getUIManagerType(t.getId()) != 1) {
            ReactScrollViewScrollState reactScrollViewScrollState = ((HasScrollState) t).getReactScrollViewScrollState();
            if (!reactScrollViewScrollState.getLastStateUpdateScroll().equals(i, i2)) {
                reactScrollViewScrollState.setLastStateUpdateScroll(i, i2);
                forceUpdateState(t);
            }
        }
    }

    public static final <T extends ViewGroup & HasScrollState & HasStateWrapper> void forceUpdateState(T t) {
        ReactScrollViewScrollState reactScrollViewScrollState = ((HasScrollState) t).getReactScrollViewScrollState();
        int scrollAwayPaddingTop = reactScrollViewScrollState.getScrollAwayPaddingTop();
        Point lastStateUpdateScroll = reactScrollViewScrollState.getLastStateUpdateScroll();
        int i = lastStateUpdateScroll.x;
        int i2 = lastStateUpdateScroll.y;
        if (DEBUG_MODE) {
            FLog.i(TAG, "updateFabricScrollState[%d] scrollX %d scrollY %d", Integer.valueOf(t.getId()), Integer.valueOf(i), Integer.valueOf(i2));
        }
        StateWrapper stateWrapper = ((HasStateWrapper) t).getStateWrapper();
        if (stateWrapper != null) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putDouble(CONTENT_OFFSET_LEFT, (double) PixelUtil.toDIPFromPixel((float) i));
            writableNativeMap.putDouble(CONTENT_OFFSET_TOP, (double) PixelUtil.toDIPFromPixel((float) i2));
            writableNativeMap.putDouble(SCROLL_AWAY_PADDING_TOP, (double) PixelUtil.toDIPFromPixel((float) scrollAwayPaddingTop));
            stateWrapper.updateState(writableNativeMap);
        }
    }

    public static final <T extends ViewGroup & HasFlingAnimator & HasScrollEventThrottle & HasScrollState & HasStateWrapper> void updateStateOnScrollChanged(T t, float f, float f2) {
        INSTANCE.updateFabricScrollState(t, t.getScrollX(), t.getScrollY());
        emitScrollEvent(t, f, f2);
    }

    public final <T extends ViewGroup & HasFlingAnimator & HasScrollState & HasStateWrapper> void registerFlingAnimator(T t) {
        ((HasFlingAnimator) t).getFlingAnimator().addListener(new ReactScrollViewHelper$registerFlingAnimator$1(t));
    }

    public static final <T extends ViewGroup & HasFlingAnimator & HasScrollEventThrottle> void dispatchMomentumEndOnAnimationEnd(T t) {
        ((HasFlingAnimator) t).getFlingAnimator().addListener(new ReactScrollViewHelper$dispatchMomentumEndOnAnimationEnd$1(t));
    }

    public static final <T extends ViewGroup & HasFlingAnimator & HasScrollState> Point predictFinalScrollPosition(T t, int i, int i2, int i3, int i4) {
        ReactScrollViewScrollState reactScrollViewScrollState = ((HasScrollState) t).getReactScrollViewScrollState();
        OverScroller overScroller = new OverScroller(t.getContext());
        overScroller.setFriction(1.0f - reactScrollViewScrollState.getDecelerationRate());
        int width = (t.getWidth() - t.getPaddingStart()) - t.getPaddingEnd();
        int height = (t.getHeight() - t.getPaddingBottom()) - t.getPaddingTop();
        Point finalAnimatedPositionScroll = reactScrollViewScrollState.getFinalAnimatedPositionScroll();
        int i5 = i;
        overScroller.fling(getNextFlingStartValue(t, t.getScrollX(), finalAnimatedPositionScroll.x, i), getNextFlingStartValue(t, t.getScrollY(), finalAnimatedPositionScroll.y, i2), i, i2, 0, i3, 0, i4, width / 2, height / 2);
        return new Point(overScroller.getFinalX(), overScroller.getFinalY());
    }

    private static final class OverScrollerDurationGetter extends OverScroller {
        private int currentScrollAnimationDuration = 250;

        public OverScrollerDurationGetter(Context context) {
            super(context);
        }

        public final int getScrollAnimationDuration() {
            super.startScroll(0, 0, 0, 0);
            return this.currentScrollAnimationDuration;
        }

        public void startScroll(int i, int i2, int i3, int i4, int i5) {
            this.currentScrollAnimationDuration = i5;
        }
    }

    public static final class ReactScrollViewScrollState {
        private float decelerationRate = 0.985f;
        private final Point finalAnimatedPositionScroll = new Point();
        private boolean isCanceled;
        private boolean isFinished = true;
        private final Point lastStateUpdateScroll = new Point(-1, -1);
        private int scrollAwayPaddingTop;

        public final Point getFinalAnimatedPositionScroll() {
            return this.finalAnimatedPositionScroll;
        }

        public final int getScrollAwayPaddingTop() {
            return this.scrollAwayPaddingTop;
        }

        public final void setScrollAwayPaddingTop(int i) {
            this.scrollAwayPaddingTop = i;
        }

        public final Point getLastStateUpdateScroll() {
            return this.lastStateUpdateScroll;
        }

        public final boolean isCanceled() {
            return this.isCanceled;
        }

        public final void setCanceled(boolean z) {
            this.isCanceled = z;
        }

        public final boolean isFinished() {
            return this.isFinished;
        }

        public final void setFinished(boolean z) {
            this.isFinished = z;
        }

        public final float getDecelerationRate() {
            return this.decelerationRate;
        }

        public final void setDecelerationRate(float f) {
            this.decelerationRate = f;
        }

        public final ReactScrollViewScrollState setFinalAnimatedPositionScroll(int i, int i2) {
            this.finalAnimatedPositionScroll.set(i, i2);
            return this;
        }

        public final ReactScrollViewScrollState setLastStateUpdateScroll(int i, int i2) {
            this.lastStateUpdateScroll.set(i, i2);
            return this;
        }
    }
}
