package com.facebook.react.views.scroll;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.HorizontalScrollView;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.BackgroundStyleApplicator;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.LengthPercentageType;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.ReactOverflowViewWithInset;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.BorderStyle;
import com.facebook.react.uimanager.style.LogicalEdge;
import com.facebook.react.uimanager.style.Overflow;
import com.facebook.react.views.scroll.MaintainVisibleScrollPositionHelper;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.facebook.systrace.Systrace;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReactHorizontalScrollView extends HorizontalScrollView implements ReactClippingViewGroup, ViewGroup.OnHierarchyChangeListener, View.OnLayoutChangeListener, ReactOverflowViewWithInset, ReactScrollViewHelper.HasScrollState, ReactScrollViewHelper.HasStateWrapper, ReactScrollViewHelper.HasFlingAnimator, ReactScrollViewHelper.HasScrollEventThrottle, ReactScrollViewHelper.HasSmoothScroll {
    private static boolean DEBUG_MODE = false;
    private static int NO_SCROLL_POSITION = Integer.MIN_VALUE;
    private static String TAG = "ReactHorizontalScrollView";
    private static final int UNSET_CONTENT_OFFSET = -1;
    private static Field sScrollerField = null;
    private static boolean sTriedToGetScrollerField = false;
    private final ValueAnimator DEFAULT_FLING_ANIMATOR;
    /* access modifiers changed from: private */
    public boolean mActivelyScrolling;
    private Rect mClippingRect;
    private View mContentView;
    private boolean mDisableIntervalMomentum;
    private boolean mDragging;
    private Drawable mEndBackground;
    private int mEndFillColor;
    private FpsListener mFpsListener;
    private long mLastScrollDispatchTime;
    private MaintainVisibleScrollPositionHelper mMaintainVisibleContentPositionHelper;
    private final OnScrollDispatchHelper mOnScrollDispatchHelper;
    private Overflow mOverflow;
    private final Rect mOverflowInset;
    private boolean mPagedArrowScrolling;
    /* access modifiers changed from: private */
    public boolean mPagingEnabled;
    private PointerEvents mPointerEvents;
    /* access modifiers changed from: private */
    public Runnable mPostTouchRunnable;
    private final ReactScrollViewHelper.ReactScrollViewScrollState mReactScrollViewScrollState;
    private boolean mRemoveClippedSubviews;
    private boolean mScrollEnabled;
    private int mScrollEventThrottle;
    private String mScrollPerfTag;
    private int mScrollXAfterMeasure;
    private final OverScroller mScroller;
    /* access modifiers changed from: private */
    public boolean mSendMomentumEvents;
    private int mSnapInterval;
    private List<Integer> mSnapOffsets;
    private int mSnapToAlignment;
    private boolean mSnapToEnd;
    private boolean mSnapToStart;
    private StateWrapper mStateWrapper;
    private final Rect mTempRect;
    private final VelocityHelper mVelocityHelper;
    private int pendingContentOffsetX;
    private int pendingContentOffsetY;

    public ReactHorizontalScrollView(Context context) {
        this(context, (FpsListener) null);
    }

    public ReactHorizontalScrollView(Context context, FpsListener fpsListener) {
        super(context);
        this.mScrollXAfterMeasure = NO_SCROLL_POSITION;
        this.mOnScrollDispatchHelper = new OnScrollDispatchHelper();
        this.mVelocityHelper = new VelocityHelper();
        this.mOverflowInset = new Rect();
        this.mOverflow = Overflow.SCROLL;
        this.mPagingEnabled = false;
        this.mScrollEnabled = true;
        this.mFpsListener = null;
        this.mEndFillColor = 0;
        this.mDisableIntervalMomentum = false;
        this.mSnapInterval = 0;
        this.mSnapToStart = true;
        this.mSnapToEnd = true;
        this.mSnapToAlignment = 0;
        this.mPagedArrowScrolling = false;
        this.pendingContentOffsetX = -1;
        this.pendingContentOffsetY = -1;
        this.mStateWrapper = null;
        this.DEFAULT_FLING_ANIMATOR = ObjectAnimator.ofInt(this, "scrollX", new int[]{0, 0});
        this.mPointerEvents = PointerEvents.AUTO;
        this.mLastScrollDispatchTime = 0;
        this.mScrollEventThrottle = 0;
        this.mTempRect = new Rect();
        this.mFpsListener = fpsListener;
        ViewCompat.setAccessibilityDelegate(this, new ReactScrollViewAccessibilityDelegate());
        this.mScroller = getOverScrollerFromParent();
        this.mReactScrollViewScrollState = new ReactScrollViewHelper.ReactScrollViewScrollState();
        setOnHierarchyChangeListener(this);
        setClipChildren(false);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        String str = (String) getTag(R.id.react_test_id);
        if (str != null) {
            accessibilityNodeInfo.setViewIdResourceName(str);
        }
    }

    public boolean getScrollEnabled() {
        return this.mScrollEnabled;
    }

    public boolean canScrollHorizontally(int i) {
        return this.mScrollEnabled && super.canScrollHorizontally(i);
    }

    private OverScroller getOverScrollerFromParent() {
        if (!sTriedToGetScrollerField) {
            sTriedToGetScrollerField = true;
            try {
                Field declaredField = HorizontalScrollView.class.getDeclaredField("mScroller");
                sScrollerField = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                FLog.w(TAG, "Failed to get mScroller field for HorizontalScrollView! This app will exhibit the bounce-back scrolling bug :(");
            }
        }
        Field field = sScrollerField;
        if (field == null) {
            return null;
        }
        try {
            Object obj = field.get(this);
            if (obj instanceof OverScroller) {
                return (OverScroller) obj;
            }
            FLog.w(TAG, "Failed to cast mScroller field in HorizontalScrollView (probably due to OEM changes to AOSP)! This app will exhibit the bounce-back scrolling bug :(");
            return null;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to get mScroller from HorizontalScrollView!", e);
        }
    }

    public void setScrollPerfTag(String str) {
        this.mScrollPerfTag = str;
    }

    public void setRemoveClippedSubviews(boolean z) {
        if (z && this.mClippingRect == null) {
            this.mClippingRect = new Rect();
        }
        this.mRemoveClippedSubviews = z;
        updateClippingRect();
    }

    public boolean getRemoveClippedSubviews() {
        return this.mRemoveClippedSubviews;
    }

    public void setDisableIntervalMomentum(boolean z) {
        this.mDisableIntervalMomentum = z;
    }

    public void setSendMomentumEvents(boolean z) {
        this.mSendMomentumEvents = z;
    }

    public void setScrollEnabled(boolean z) {
        this.mScrollEnabled = z;
    }

    public void setPagingEnabled(boolean z) {
        this.mPagingEnabled = z;
    }

    public void setDecelerationRate(float f) {
        getReactScrollViewScrollState().setDecelerationRate(f);
        OverScroller overScroller = this.mScroller;
        if (overScroller != null) {
            overScroller.setFriction(1.0f - f);
        }
    }

    public void abortAnimation() {
        OverScroller overScroller = this.mScroller;
        if (overScroller != null && !overScroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
    }

    public void setSnapInterval(int i) {
        this.mSnapInterval = i;
    }

    public void setSnapOffsets(List<Integer> list) {
        this.mSnapOffsets = list;
    }

    public void setSnapToStart(boolean z) {
        this.mSnapToStart = z;
    }

    public void setSnapToEnd(boolean z) {
        this.mSnapToEnd = z;
    }

    public void setSnapToAlignment(int i) {
        this.mSnapToAlignment = i;
    }

    public void flashScrollIndicators() {
        awakenScrollBars();
    }

    public void setOverflow(String str) {
        if (str == null) {
            this.mOverflow = Overflow.SCROLL;
        } else {
            Overflow fromString = Overflow.fromString(str);
            if (fromString == null) {
                fromString = Overflow.SCROLL;
            }
            this.mOverflow = fromString;
        }
        invalidate();
    }

    public void setMaintainVisibleContentPosition(MaintainVisibleScrollPositionHelper.Config config) {
        MaintainVisibleScrollPositionHelper maintainVisibleScrollPositionHelper;
        if (config != null && this.mMaintainVisibleContentPositionHelper == null) {
            MaintainVisibleScrollPositionHelper maintainVisibleScrollPositionHelper2 = new MaintainVisibleScrollPositionHelper(this, true);
            this.mMaintainVisibleContentPositionHelper = maintainVisibleScrollPositionHelper2;
            maintainVisibleScrollPositionHelper2.start();
        } else if (config == null && (maintainVisibleScrollPositionHelper = this.mMaintainVisibleContentPositionHelper) != null) {
            maintainVisibleScrollPositionHelper.stop();
            this.mMaintainVisibleContentPositionHelper = null;
        }
        MaintainVisibleScrollPositionHelper maintainVisibleScrollPositionHelper3 = this.mMaintainVisibleContentPositionHelper;
        if (maintainVisibleScrollPositionHelper3 != null) {
            maintainVisibleScrollPositionHelper3.setConfig(config);
        }
    }

    /* renamed from: com.facebook.react.views.scroll.ReactHorizontalScrollView$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$uimanager$style$Overflow;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.facebook.react.uimanager.style.Overflow[] r0 = com.facebook.react.uimanager.style.Overflow.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$uimanager$style$Overflow = r0
                com.facebook.react.uimanager.style.Overflow r1 = com.facebook.react.uimanager.style.Overflow.HIDDEN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$style$Overflow     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.uimanager.style.Overflow r1 = com.facebook.react.uimanager.style.Overflow.SCROLL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$style$Overflow     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.react.uimanager.style.Overflow r1 = com.facebook.react.uimanager.style.Overflow.VISIBLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.ReactHorizontalScrollView.AnonymousClass2.<clinit>():void");
        }
    }

    public String getOverflow() {
        int i = AnonymousClass2.$SwitchMap$com$facebook$react$uimanager$style$Overflow[this.mOverflow.ordinal()];
        if (i == 1) {
            return ViewProps.HIDDEN;
        }
        if (i == 2) {
            return ViewProps.SCROLL;
        }
        if (i != 3) {
            return null;
        }
        return ViewProps.VISIBLE;
    }

    public void setOverflowInset(int i, int i2, int i3, int i4) {
        this.mOverflowInset.set(i, i2, i3, i4);
    }

    public Rect getOverflowInset() {
        return this.mOverflowInset;
    }

    public void onDraw(Canvas canvas) {
        if (this.mOverflow != Overflow.VISIBLE) {
            BackgroundStyleApplicator.clipToPaddingBox(this, canvas);
        }
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        OverScroller overScroller;
        MeasureSpecAssertions.assertExplicitMeasureSpec(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (DEBUG_MODE) {
            FLog.i(TAG, "onMeasure[%d] measured width: %d measured height: %d", Integer.valueOf(getId()), Integer.valueOf(size), Integer.valueOf(size2));
        }
        boolean z = getMeasuredHeight() != size2;
        setMeasuredDimension(size, size2);
        if (z && (overScroller = this.mScroller) != null) {
            this.mScrollXAfterMeasure = overScroller.getCurrX();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        OverScroller overScroller;
        if (DEBUG_MODE) {
            FLog.i(TAG, "onLayout[%d] l %d t %d r %d b %d", Integer.valueOf(getId()), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        }
        int i5 = this.mScrollXAfterMeasure;
        if (!(i5 == NO_SCROLL_POSITION || (overScroller = this.mScroller) == null || i5 == overScroller.getFinalX() || this.mScroller.isFinished())) {
            if (DEBUG_MODE) {
                FLog.i(TAG, "onLayout[%d] scroll hack enabled: reset to previous scrollX position of %d", Integer.valueOf(getId()), Integer.valueOf(this.mScrollXAfterMeasure));
            }
            OverScroller overScroller2 = this.mScroller;
            overScroller2.startScroll(this.mScrollXAfterMeasure, overScroller2.getFinalY(), 0, 0);
            this.mScroller.forceFinished(true);
            this.mScrollXAfterMeasure = NO_SCROLL_POSITION;
        }
        if (isContentReady()) {
            int i6 = this.pendingContentOffsetX;
            if (i6 == -1) {
                i6 = getScrollX();
            }
            int i7 = this.pendingContentOffsetY;
            if (i7 == -1) {
                i7 = getScrollY();
            }
            scrollTo(i6, i7);
        }
        ReactScrollViewHelper.emitLayoutEvent(this);
    }

    public void requestChildFocus(View view, View view2) {
        if (view2 != null && !this.mPagingEnabled) {
            scrollToChild(view2);
        }
        super.requestChildFocus(view, view2);
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        if (!this.mPagingEnabled || this.mPagedArrowScrolling) {
            super.addFocusables(arrayList, i, i2);
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        super.addFocusables(arrayList2, i, i2);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            View view = (View) it.next();
            if (isScrolledInView(view) || isPartiallyScrolledInView(view) || view.isFocused()) {
                arrayList.add(view);
            }
        }
    }

    private int getScrollDelta(View view) {
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        return computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
    }

    private boolean isScrolledInView(View view) {
        return getScrollDelta(view) == 0;
    }

    public boolean isPartiallyScrolledInView(View view) {
        int scrollDelta = getScrollDelta(view);
        view.getDrawingRect(this.mTempRect);
        return scrollDelta != 0 && Math.abs(scrollDelta) < this.mTempRect.width();
    }

    private boolean isMostlyScrolledInView(View view) {
        int scrollDelta = getScrollDelta(view);
        view.getDrawingRect(this.mTempRect);
        return scrollDelta != 0 && Math.abs(scrollDelta) < this.mTempRect.width() / 2;
    }

    private void scrollToChild(View view) {
        int scrollDelta = getScrollDelta(view);
        if (scrollDelta != 0) {
            scrollBy(scrollDelta, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        if (DEBUG_MODE) {
            FLog.i(TAG, "onScrollChanged[%d] x %d y %d oldx %d oldy %d", Integer.valueOf(getId()), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        }
        Systrace.beginSection(0, "ReactHorizontalScrollView.onScrollChanged");
        try {
            super.onScrollChanged(i, i2, i3, i4);
            this.mActivelyScrolling = true;
            if (this.mOnScrollDispatchHelper.onScrollChanged(i, i2)) {
                if (this.mRemoveClippedSubviews) {
                    updateClippingRect();
                }
                ReactScrollViewHelper.updateStateOnScrollChanged(this, this.mOnScrollDispatchHelper.getXFlingVelocity(), this.mOnScrollDispatchHelper.getYFlingVelocity());
            }
            Systrace.endSection(0);
        } catch (Throwable th) {
            Systrace.endSection(0);
            throw th;
        }
    }

    private static HorizontalScrollView findDeepestScrollViewForMotionEvent(View view, MotionEvent motionEvent) {
        return findDeepestScrollViewForMotionEvent(view, motionEvent, true);
    }

    private static HorizontalScrollView findDeepestScrollViewForMotionEvent(View view, MotionEvent motionEvent, boolean z) {
        if (view == null) {
            return null;
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        if (!rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
            return null;
        }
        if (!z && (view instanceof HorizontalScrollView) && ViewCompat.isNestedScrollingEnabled(view) && (view instanceof ReactHorizontalScrollView) && ((ReactHorizontalScrollView) view).mScrollEnabled) {
            return (HorizontalScrollView) view;
        }
        if (view instanceof ViewGroup) {
            int i = 0;
            while (true) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (i >= viewGroup.getChildCount()) {
                    break;
                }
                HorizontalScrollView findDeepestScrollViewForMotionEvent = findDeepestScrollViewForMotionEvent(viewGroup.getChildAt(i), motionEvent, false);
                if (findDeepestScrollViewForMotionEvent != null) {
                    return findDeepestScrollViewForMotionEvent;
                }
                i++;
            }
        }
        return null;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollEnabled) {
            return false;
        }
        if (motionEvent.getAction() == 0 && findDeepestScrollViewForMotionEvent(this, motionEvent) != null) {
            return false;
        }
        if (!PointerEvents.canChildrenBeTouchTarget(this.mPointerEvents)) {
            return true;
        }
        try {
            if (super.onInterceptTouchEvent(motionEvent)) {
                handleInterceptedTouchEvent(motionEvent);
                return true;
            }
        } catch (IllegalArgumentException e) {
            FLog.w(ReactConstants.TAG, "Error intercepting touch event.", (Throwable) e);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void handleInterceptedTouchEvent(MotionEvent motionEvent) {
        NativeGestureUtil.notifyNativeGestureStarted(this, motionEvent);
        ReactScrollViewHelper.emitScrollBeginDragEvent(this);
        this.mDragging = true;
        enableFpsListener();
        getFlingAnimator().cancel();
    }

    public boolean pageScroll(int i) {
        boolean pageScroll = super.pageScroll(i);
        if (this.mPagingEnabled && pageScroll) {
            handlePostTouchScrolling(0, 0);
        }
        return pageScroll;
    }

    public boolean arrowScroll(int i) {
        if (!this.mPagingEnabled) {
            return super.arrowScroll(i);
        }
        boolean z = true;
        this.mPagedArrowScrolling = true;
        if (getChildCount() > 0) {
            View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus(), i);
            View contentView = getContentView();
            if (contentView == null || findNextFocus == null || findNextFocus.getParent() != contentView) {
                smoothScrollToNextPage(i);
            } else {
                if (!isScrolledInView(findNextFocus) && !isMostlyScrolledInView(findNextFocus)) {
                    smoothScrollToNextPage(i);
                }
                findNextFocus.requestFocus();
            }
        } else {
            z = false;
        }
        this.mPagedArrowScrolling = false;
        return z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollEnabled || !PointerEvents.canBeTouchTarget(this.mPointerEvents)) {
            return false;
        }
        this.mVelocityHelper.calculateVelocity(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 && this.mDragging) {
            ReactScrollViewHelper.updateFabricScrollState(this);
            float xVelocity = this.mVelocityHelper.getXVelocity();
            float yVelocity = this.mVelocityHelper.getYVelocity();
            ReactScrollViewHelper.emitScrollEndDragEvent(this, xVelocity, yVelocity);
            NativeGestureUtil.notifyNativeGestureEnded(this, motionEvent);
            this.mDragging = false;
            handlePostTouchScrolling(Math.round(xVelocity), Math.round(yVelocity));
        }
        if (actionMasked == 0) {
            cancelPostTouchScrolling();
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if (!PointerEvents.canChildrenBeTouchTarget(this.mPointerEvents)) {
            return false;
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (this.mScrollEnabled || (keyCode != 21 && keyCode != 22)) {
            return super.executeKeyEvent(keyEvent);
        }
        return false;
    }

    public void fling(int i) {
        if (DEBUG_MODE) {
            FLog.i(TAG, "fling[%d] velocityX %d", Integer.valueOf(getId()), Integer.valueOf(i));
        }
        if (Build.VERSION.SDK_INT == 28) {
            i = (int) (((float) Math.abs(i)) * Math.signum(this.mOnScrollDispatchHelper.getXFlingVelocity()));
        }
        if (this.mPagingEnabled) {
            flingAndSnap(i);
        } else if (this.mScroller != null) {
            int width = (getWidth() - ViewCompat.getPaddingStart(this)) - ViewCompat.getPaddingEnd(this);
            this.mScroller.fling(getScrollX(), getScrollY(), i, 0, 0, Integer.MAX_VALUE, 0, 0, width / 2, 0);
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            super.fling(i);
        }
        handlePostTouchScrolling(i, 0);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
        MaintainVisibleScrollPositionHelper maintainVisibleScrollPositionHelper = this.mMaintainVisibleContentPositionHelper;
        if (maintainVisibleScrollPositionHelper != null) {
            maintainVisibleScrollPositionHelper.start();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MaintainVisibleScrollPositionHelper maintainVisibleScrollPositionHelper = this.mMaintainVisibleContentPositionHelper;
        if (maintainVisibleScrollPositionHelper != null) {
            maintainVisibleScrollPositionHelper.stop();
        }
    }

    public void updateClippingRect() {
        if (this.mRemoveClippedSubviews) {
            Systrace.beginSection(0, "ReactHorizontalScrollView.updateClippingRect");
            try {
                Assertions.assertNotNull(this.mClippingRect);
                ReactClippingViewGroupHelper.calculateClippingRect(this, this.mClippingRect);
                View contentView = getContentView();
                if (contentView instanceof ReactClippingViewGroup) {
                    ((ReactClippingViewGroup) contentView).updateClippingRect();
                }
            } finally {
                Systrace.endSection(0);
            }
        }
    }

    public void getClippingRect(Rect rect) {
        rect.set((Rect) Assertions.assertNotNull(this.mClippingRect));
    }

    public boolean getChildVisibleRect(View view, Rect rect, Point point) {
        return super.getChildVisibleRect(view, rect, point);
    }

    private int getSnapInterval() {
        int i = this.mSnapInterval;
        if (i != 0) {
            return i;
        }
        return getWidth();
    }

    private View getContentView() {
        return getChildAt(0);
    }

    public void setEndFillColor(int i) {
        if (i != this.mEndFillColor) {
            this.mEndFillColor = i;
            this.mEndBackground = new ColorDrawable(this.mEndFillColor);
        }
    }

    /* access modifiers changed from: protected */
    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        int max;
        if (DEBUG_MODE) {
            FLog.i(TAG, "onOverScrolled[%d] scrollX %d scrollY %d clampedX %b clampedY %b", Integer.valueOf(getId()), Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z), Boolean.valueOf(z2));
        }
        OverScroller overScroller = this.mScroller;
        if (overScroller != null && !overScroller.isFinished() && this.mScroller.getCurrX() != this.mScroller.getFinalX() && i >= (max = Math.max(computeHorizontalScrollRange() - getWidth(), 0))) {
            this.mScroller.abortAnimation();
            i = max;
        }
        super.onOverScrolled(i, i2, z, z2);
    }

    public void onChildViewAdded(View view, View view2) {
        this.mContentView = view2;
        view2.addOnLayoutChangeListener(this);
    }

    public void onChildViewRemoved(View view, View view2) {
        View view3 = this.mContentView;
        if (view3 != null) {
            view3.removeOnLayoutChangeListener(this);
        }
        this.mContentView = null;
    }

    private void enableFpsListener() {
        if (isScrollPerfLoggingEnabled()) {
            Assertions.assertNotNull(this.mFpsListener);
            Assertions.assertNotNull(this.mScrollPerfTag);
            this.mFpsListener.enable(this.mScrollPerfTag);
        }
    }

    /* access modifiers changed from: private */
    public void disableFpsListener() {
        if (isScrollPerfLoggingEnabled()) {
            Assertions.assertNotNull(this.mFpsListener);
            Assertions.assertNotNull(this.mScrollPerfTag);
            this.mFpsListener.disable(this.mScrollPerfTag);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.mScrollPerfTag;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isScrollPerfLoggingEnabled() {
        /*
            r1 = this;
            com.facebook.react.views.scroll.FpsListener r0 = r1.mFpsListener
            if (r0 == 0) goto L_0x0010
            java.lang.String r0 = r1.mScrollPerfTag
            if (r0 == 0) goto L_0x0010
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0010
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.ReactHorizontalScrollView.isScrollPerfLoggingEnabled():boolean");
    }

    public void draw(Canvas canvas) {
        if (this.mEndFillColor != 0) {
            View contentView = getContentView();
            if (!(this.mEndBackground == null || contentView == null || contentView.getRight() >= getWidth())) {
                this.mEndBackground.setBounds(contentView.getRight(), 0, getWidth(), getHeight());
                this.mEndBackground.draw(canvas);
            }
        }
        super.draw(canvas);
    }

    private void handlePostTouchScrolling(int i, int i2) {
        if (DEBUG_MODE) {
            FLog.i(TAG, "handlePostTouchScrolling[%d] velocityX %d velocityY %d", Integer.valueOf(getId()), Integer.valueOf(i), Integer.valueOf(i2));
        }
        if (this.mPostTouchRunnable == null) {
            if (this.mSendMomentumEvents) {
                ReactScrollViewHelper.emitScrollMomentumBeginEvent(this, i, i2);
            }
            this.mActivelyScrolling = false;
            AnonymousClass1 r6 = new Runnable() {
                private boolean mSnappingToPage = false;
                private int mStableFrames = 0;

                public void run() {
                    NativeAnimatedModule nativeAnimatedModule;
                    if (ReactHorizontalScrollView.this.mActivelyScrolling) {
                        ReactHorizontalScrollView.this.mActivelyScrolling = false;
                        this.mStableFrames = 0;
                        ViewCompat.postOnAnimationDelayed(ReactHorizontalScrollView.this, this, 20);
                        return;
                    }
                    ReactScrollViewHelper.updateFabricScrollState(ReactHorizontalScrollView.this);
                    int i = this.mStableFrames + 1;
                    this.mStableFrames = i;
                    if (i >= 3) {
                        ReactHorizontalScrollView.this.mPostTouchRunnable = null;
                        if (ReactHorizontalScrollView.this.mSendMomentumEvents) {
                            ReactScrollViewHelper.emitScrollMomentumEndEvent(ReactHorizontalScrollView.this);
                        }
                        ReactContext reactContext = (ReactContext) ReactHorizontalScrollView.this.getContext();
                        if (!(reactContext == null || (nativeAnimatedModule = (NativeAnimatedModule) reactContext.getNativeModule(NativeAnimatedModule.class)) == null)) {
                            nativeAnimatedModule.userDrivenScrollEnded(ReactHorizontalScrollView.this.getId());
                        }
                        ReactHorizontalScrollView.this.disableFpsListener();
                        return;
                    }
                    if (ReactHorizontalScrollView.this.mPagingEnabled && !this.mSnappingToPage) {
                        this.mSnappingToPage = true;
                        ReactHorizontalScrollView.this.flingAndSnap(0);
                    }
                    ViewCompat.postOnAnimationDelayed(ReactHorizontalScrollView.this, this, 20);
                }
            };
            this.mPostTouchRunnable = r6;
            ViewCompat.postOnAnimationDelayed(this, r6, 20);
        }
    }

    private void cancelPostTouchScrolling() {
        Runnable runnable = this.mPostTouchRunnable;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.mPostTouchRunnable = null;
            getFlingAnimator().cancel();
        }
    }

    private int predictFinalScrollPosition(int i) {
        int max = Math.max(0, computeHorizontalScrollRange() - getWidth());
        if (getFlingAnimator() == this.DEFAULT_FLING_ANIMATOR) {
            return ReactScrollViewHelper.predictFinalScrollPosition(this, i, 0, max, 0).x;
        }
        return getFlingExtrapolatedDistance(i) + ReactScrollViewHelper.getNextFlingStartValue(this, getScrollX(), getReactScrollViewScrollState().getFinalAnimatedPositionScroll().x, i);
    }

    private void smoothScrollAndSnap(int i) {
        if (DEBUG_MODE) {
            FLog.i(TAG, "smoothScrollAndSnap[%d] velocity %d", Integer.valueOf(getId()), Integer.valueOf(i));
        }
        double snapInterval = (double) getSnapInterval();
        double nextFlingStartValue = (double) ReactScrollViewHelper.getNextFlingStartValue(this, getScrollX(), getReactScrollViewScrollState().getFinalAnimatedPositionScroll().x, i);
        double d = nextFlingStartValue / snapInterval;
        int floor = (int) Math.floor(d);
        int ceil = (int) Math.ceil(d);
        int round = (int) Math.round(d);
        int round2 = (int) Math.round(((double) predictFinalScrollPosition(i)) / snapInterval);
        if (i > 0 && ceil == floor) {
            ceil++;
        } else if (i < 0 && floor == ceil) {
            floor--;
        }
        if (i > 0 && round < ceil && round2 > floor) {
            round = ceil;
        } else if (i < 0 && round > floor && round2 < ceil) {
            round = floor;
        }
        double d2 = ((double) round) * snapInterval;
        if (d2 != nextFlingStartValue) {
            this.mActivelyScrolling = true;
            reactSmoothScrollTo((int) d2, getScrollY());
        }
    }

    /* access modifiers changed from: private */
    public void flingAndSnap(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        OverScroller overScroller;
        int i7;
        if (DEBUG_MODE) {
            FLog.i(TAG, "smoothScrollAndSnap[%d] velocityX %d", Integer.valueOf(getId()), Integer.valueOf(i));
        }
        if (getChildCount() > 0) {
            if (this.mSnapInterval == 0 && this.mSnapOffsets == null && this.mSnapToAlignment == 0) {
                smoothScrollAndSnap(i);
                return;
            }
            boolean z = getFlingAnimator() != this.DEFAULT_FLING_ANIMATOR;
            int max = Math.max(0, computeHorizontalScrollRange() - getWidth());
            int predictFinalScrollPosition = predictFinalScrollPosition(i);
            if (this.mDisableIntervalMomentum) {
                predictFinalScrollPosition = getScrollX();
            }
            int width = (getWidth() - ViewCompat.getPaddingStart(this)) - ViewCompat.getPaddingEnd(this);
            if (getLayoutDirection() == 1) {
                predictFinalScrollPosition = max - predictFinalScrollPosition;
                i2 = -i;
            } else {
                i2 = i;
            }
            List<Integer> list = this.mSnapOffsets;
            if (list == null || list.isEmpty()) {
                int i8 = this.mSnapToAlignment;
                if (i8 != 0) {
                    int i9 = this.mSnapInterval;
                    if (i9 > 0) {
                        double d = ((double) predictFinalScrollPosition) / ((double) i9);
                        double floor = Math.floor(d);
                        int i10 = this.mSnapInterval;
                        int max2 = Math.max(getItemStartOffset(i8, (int) (floor * ((double) i10)), i10, width), 0);
                        int i11 = this.mSnapToAlignment;
                        double ceil = Math.ceil(d);
                        int i12 = this.mSnapInterval;
                        i3 = Math.min(getItemStartOffset(i11, (int) (ceil * ((double) i12)), i12, width), max);
                        i5 = max;
                        i4 = max2;
                        i6 = 0;
                    } else {
                        ViewGroup viewGroup = (ViewGroup) getContentView();
                        int i13 = max;
                        int i14 = i13;
                        int i15 = 0;
                        int i16 = 0;
                        for (int i17 = 0; i17 < viewGroup.getChildCount(); i17++) {
                            View childAt = viewGroup.getChildAt(i17);
                            int itemStartOffset = getItemStartOffset(this.mSnapToAlignment, childAt.getLeft(), childAt.getWidth(), width);
                            if (itemStartOffset <= predictFinalScrollPosition && predictFinalScrollPosition - itemStartOffset < predictFinalScrollPosition - i15) {
                                i15 = itemStartOffset;
                            }
                            if (itemStartOffset >= predictFinalScrollPosition && itemStartOffset - predictFinalScrollPosition < i14 - predictFinalScrollPosition) {
                                i14 = itemStartOffset;
                            }
                            i13 = Math.min(i13, itemStartOffset);
                            i16 = Math.max(i16, itemStartOffset);
                        }
                        i4 = Math.max(i15, i13);
                        i3 = Math.min(i14, i16);
                        i5 = max;
                    }
                } else {
                    double snapInterval = (double) getSnapInterval();
                    double d2 = ((double) predictFinalScrollPosition) / snapInterval;
                    int floor2 = (int) (Math.floor(d2) * snapInterval);
                    i3 = Math.min((int) (Math.ceil(d2) * snapInterval), max);
                    i5 = max;
                    i4 = floor2;
                }
                i6 = 0;
            } else {
                i6 = this.mSnapOffsets.get(0).intValue();
                List<Integer> list2 = this.mSnapOffsets;
                i5 = list2.get(list2.size() - 1).intValue();
                i3 = max;
                i4 = 0;
                for (int i18 = 0; i18 < this.mSnapOffsets.size(); i18++) {
                    int intValue = this.mSnapOffsets.get(i18).intValue();
                    if (intValue <= predictFinalScrollPosition && predictFinalScrollPosition - intValue < predictFinalScrollPosition - i4) {
                        i4 = intValue;
                    }
                    if (intValue >= predictFinalScrollPosition && intValue - predictFinalScrollPosition < i3 - predictFinalScrollPosition) {
                        i3 = intValue;
                    }
                }
            }
            int i19 = predictFinalScrollPosition - i4;
            int i20 = i3 - predictFinalScrollPosition;
            int i21 = Math.abs(i19) < Math.abs(i20) ? i4 : i3;
            int scrollX = getScrollX();
            if (getLayoutDirection() == 1) {
                scrollX = max - scrollX;
            }
            if (this.mSnapToEnd || predictFinalScrollPosition < i5) {
                if (this.mSnapToStart || predictFinalScrollPosition > i6) {
                    if (i2 > 0) {
                        if (!z) {
                            i2 += (int) (((double) i20) * 10.0d);
                        }
                        predictFinalScrollPosition = i3;
                    } else if (i2 < 0) {
                        if (!z) {
                            i2 -= (int) (((double) i19) * 10.0d);
                        }
                        predictFinalScrollPosition = i4;
                    } else {
                        predictFinalScrollPosition = i21;
                    }
                } else if (scrollX > i6) {
                    predictFinalScrollPosition = i6;
                }
            } else if (scrollX < i5) {
                predictFinalScrollPosition = i5;
            }
            int i22 = 0;
            int min = Math.min(Math.max(0, predictFinalScrollPosition), max);
            if (getLayoutDirection() == 1) {
                min = max - min;
                i2 = -i2;
            }
            int i23 = min;
            if (z || (overScroller = this.mScroller) == null) {
                reactSmoothScrollTo(i23, getScrollY());
                return;
            }
            this.mActivelyScrolling = true;
            int scrollX2 = getScrollX();
            int scrollY = getScrollY();
            if (i2 != 0) {
                i7 = i2;
            } else {
                i7 = i23 - getScrollX();
            }
            if (i23 == 0 || i23 == max) {
                i22 = width / 2;
            }
            overScroller.fling(scrollX2, scrollY, i7, 0, i23, i23, 0, 0, i22, 0);
            postInvalidateOnAnimation();
        }
    }

    private int getItemStartOffset(int i, int i2, int i3, int i4) {
        int i5;
        if (i == 1) {
            return i2;
        }
        if (i == 2) {
            i5 = (i4 - i3) / 2;
        } else if (i == 3) {
            i5 = i4 - i3;
        } else {
            throw new IllegalStateException("Invalid SnapToAlignment value: " + this.mSnapToAlignment);
        }
        return i2 - i5;
    }

    private void smoothScrollToNextPage(int i) {
        if (DEBUG_MODE) {
            FLog.i(TAG, "smoothScrollToNextPage[%d] direction %d", Integer.valueOf(getId()), Integer.valueOf(i));
        }
        int width = getWidth();
        int scrollX = getScrollX();
        int i2 = scrollX / width;
        if (scrollX % width != 0) {
            i2++;
        }
        int i3 = i == 17 ? i2 - 1 : i2 + 1;
        if (i3 < 0) {
            i3 = 0;
        }
        reactSmoothScrollTo(i3 * width, getScrollY());
        handlePostTouchScrolling(0, 0);
    }

    public void setBackgroundColor(int i) {
        BackgroundStyleApplicator.setBackgroundColor(this, Integer.valueOf(i));
    }

    public void setBorderWidth(int i, float f) {
        BackgroundStyleApplicator.setBorderWidth(this, LogicalEdge.values()[i], Float.valueOf(PixelUtil.toDIPFromPixel(f)));
    }

    public void setBorderColor(int i, Integer num) {
        BackgroundStyleApplicator.setBorderColor(this, LogicalEdge.values()[i], num);
    }

    public void setBorderRadius(float f) {
        setBorderRadius(f, BorderRadiusProp.BORDER_RADIUS.ordinal());
    }

    public void setBorderRadius(float f, int i) {
        LengthPercentage lengthPercentage;
        if (Float.isNaN(f)) {
            lengthPercentage = null;
        } else {
            lengthPercentage = new LengthPercentage(PixelUtil.toDIPFromPixel(f), LengthPercentageType.POINT);
        }
        BackgroundStyleApplicator.setBorderRadius(this, BorderRadiusProp.values()[i], lengthPercentage);
    }

    public void setBorderStyle(String str) {
        BackgroundStyleApplicator.setBorderStyle(this, str == null ? null : BorderStyle.fromString(str));
    }

    public void reactSmoothScrollTo(int i, int i2) {
        ReactScrollViewHelper.smoothScrollTo(this, i, i2);
        setPendingContentOffsets(i, i2);
    }

    public void scrollTo(int i, int i2) {
        if (DEBUG_MODE) {
            FLog.i(TAG, "scrollTo[%d] x %d y %d", Integer.valueOf(getId()), Integer.valueOf(i), Integer.valueOf(i2));
        }
        super.scrollTo(i, i2);
        ReactScrollViewHelper.updateFabricScrollState(this);
        setPendingContentOffsets(i, i2);
    }

    public void scrollToPreservingMomentum(int i, int i2) {
        scrollTo(i, i2);
        recreateFlingAnimation(i, Integer.MAX_VALUE);
    }

    private boolean isContentReady() {
        View contentView = getContentView();
        return (contentView == null || contentView.getWidth() == 0 || contentView.getHeight() == 0) ? false : true;
    }

    private void setPendingContentOffsets(int i, int i2) {
        if (DEBUG_MODE) {
            FLog.i(TAG, "setPendingContentOffsets[%d] x %d y %d", Integer.valueOf(getId()), Integer.valueOf(i), Integer.valueOf(i2));
        }
        if (isContentReady()) {
            this.pendingContentOffsetX = -1;
            this.pendingContentOffsetY = -1;
            return;
        }
        this.pendingContentOffsetX = i;
        this.pendingContentOffsetY = i2;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.mContentView != null) {
            if (view.getLayoutDirection() == 1) {
                adjustPositionForContentChangeRTL(i, i3, i5, i7);
            } else {
                MaintainVisibleScrollPositionHelper maintainVisibleScrollPositionHelper = this.mMaintainVisibleContentPositionHelper;
                if (maintainVisibleScrollPositionHelper != null) {
                    maintainVisibleScrollPositionHelper.updateScrollPosition();
                }
            }
            ReactScrollViewHelper.emitLayoutChangeEvent(this);
        }
    }

    private void recreateFlingAnimation(int i, int i2) {
        if (getFlingAnimator().isRunning()) {
            getFlingAnimator().cancel();
        }
        OverScroller overScroller = this.mScroller;
        if (overScroller != null && !overScroller.isFinished()) {
            int currX = this.mScroller.getCurrX();
            boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            this.mScroller.forceFinished(true);
            if (computeScrollOffset) {
                int i3 = i;
                this.mScroller.fling(i3, getScrollY(), (int) (this.mScroller.getCurrVelocity() * Math.signum((float) (this.mScroller.getFinalX() - this.mScroller.getStartX()))), 0, 0, i2, 0, 0);
                return;
            }
            scrollTo(i + (this.mScroller.getCurrX() - currX), getScrollY());
        }
    }

    private void adjustPositionForContentChangeRTL(int i, int i2, int i3, int i4) {
        if (getFlingAnimator().isRunning()) {
            getFlingAnimator().end();
        }
        int i5 = i2 - i;
        int scrollX = i5 - (i4 - getScrollX());
        scrollTo(scrollX, getScrollY());
        recreateFlingAnimation(scrollX, i5 - getWidth());
    }

    public StateWrapper getStateWrapper() {
        return this.mStateWrapper;
    }

    public void setStateWrapper(StateWrapper stateWrapper) {
        this.mStateWrapper = stateWrapper;
    }

    public ReactScrollViewHelper.ReactScrollViewScrollState getReactScrollViewScrollState() {
        return this.mReactScrollViewScrollState;
    }

    public void startFlingAnimator(int i, int i2) {
        this.DEFAULT_FLING_ANIMATOR.cancel();
        int defaultScrollAnimationDuration = ReactScrollViewHelper.getDefaultScrollAnimationDuration(getContext());
        this.DEFAULT_FLING_ANIMATOR.setDuration((long) defaultScrollAnimationDuration).setIntValues(new int[]{i, i2});
        this.DEFAULT_FLING_ANIMATOR.start();
        if (this.mSendMomentumEvents) {
            ReactScrollViewHelper.emitScrollMomentumBeginEvent(this, defaultScrollAnimationDuration > 0 ? (i2 - i) / defaultScrollAnimationDuration : 0, 0);
            ReactScrollViewHelper.dispatchMomentumEndOnAnimationEnd(this);
        }
    }

    public ValueAnimator getFlingAnimator() {
        return this.DEFAULT_FLING_ANIMATOR;
    }

    public int getFlingExtrapolatedDistance(int i) {
        return ReactScrollViewHelper.predictFinalScrollPosition(this, i, 0, Math.max(0, computeHorizontalScrollRange() - getWidth()), 0).x;
    }

    public void setPointerEvents(PointerEvents pointerEvents) {
        this.mPointerEvents = pointerEvents;
    }

    public PointerEvents getPointerEvents() {
        return this.mPointerEvents;
    }

    public void setScrollEventThrottle(int i) {
        this.mScrollEventThrottle = i;
    }

    public int getScrollEventThrottle() {
        return this.mScrollEventThrottle;
    }

    public void setLastScrollDispatchTime(long j) {
        this.mLastScrollDispatchTime = j;
    }

    public long getLastScrollDispatchTime() {
        return this.mLastScrollDispatchTime;
    }
}
