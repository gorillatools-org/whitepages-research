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
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
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
import java.util.List;

public class ReactScrollView extends ScrollView implements ReactClippingViewGroup, ViewGroup.OnHierarchyChangeListener, View.OnLayoutChangeListener, ReactOverflowViewWithInset, ReactScrollViewHelper.HasScrollState, ReactScrollViewHelper.HasStateWrapper, ReactScrollViewHelper.HasFlingAnimator, ReactScrollViewHelper.HasScrollEventThrottle, ReactScrollViewHelper.HasSmoothScroll {
    private static final int UNSET_CONTENT_OFFSET = -1;
    private static Field sScrollerField = null;
    private static boolean sTriedToGetScrollerField = false;
    private final ValueAnimator DEFAULT_FLING_ANIMATOR;
    /* access modifiers changed from: private */
    public boolean mActivelyScrolling;
    private Rect mClippingRect;
    private View mContentView;
    private ReadableMap mCurrentContentOffset;
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

    public ReactScrollView(Context context) {
        this(context, (FpsListener) null);
    }

    public ReactScrollView(Context context, FpsListener fpsListener) {
        super(context);
        this.mOnScrollDispatchHelper = new OnScrollDispatchHelper();
        this.mVelocityHelper = new VelocityHelper();
        this.mTempRect = new Rect();
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
        this.mCurrentContentOffset = null;
        this.pendingContentOffsetX = -1;
        this.pendingContentOffsetY = -1;
        this.mStateWrapper = null;
        this.mReactScrollViewScrollState = new ReactScrollViewHelper.ReactScrollViewScrollState();
        this.DEFAULT_FLING_ANIMATOR = ObjectAnimator.ofInt(this, "scrollY", new int[]{0, 0});
        this.mPointerEvents = PointerEvents.AUTO;
        this.mLastScrollDispatchTime = 0;
        this.mScrollEventThrottle = 0;
        this.mMaintainVisibleContentPositionHelper = null;
        this.mFpsListener = fpsListener;
        this.mScroller = getOverScrollerFromParent();
        setOnHierarchyChangeListener(this);
        setScrollBarStyle(33554432);
        setClipChildren(false);
        ViewCompat.setAccessibilityDelegate(this, new ReactScrollViewAccessibilityDelegate());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        String str = (String) getTag(R.id.react_test_id);
        if (str != null) {
            accessibilityNodeInfo.setViewIdResourceName(str);
        }
    }

    private OverScroller getOverScrollerFromParent() {
        if (!sTriedToGetScrollerField) {
            sTriedToGetScrollerField = true;
            try {
                Field declaredField = ScrollView.class.getDeclaredField("mScroller");
                sScrollerField = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                FLog.w(ReactConstants.TAG, "Failed to get mScroller field for ScrollView! This app will exhibit the bounce-back scrolling bug :(");
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
            FLog.w(ReactConstants.TAG, "Failed to cast mScroller field in ScrollView (probably due to OEM changes to AOSP)! This app will exhibit the bounce-back scrolling bug :(");
            return null;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to get mScroller from ScrollView!", e);
        }
    }

    public void setDisableIntervalMomentum(boolean z) {
        this.mDisableIntervalMomentum = z;
    }

    public void setSendMomentumEvents(boolean z) {
        this.mSendMomentumEvents = z;
    }

    public void setScrollPerfTag(String str) {
        this.mScrollPerfTag = str;
    }

    public void setScrollEnabled(boolean z) {
        this.mScrollEnabled = z;
    }

    public boolean getScrollEnabled() {
        return this.mScrollEnabled;
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
            MaintainVisibleScrollPositionHelper maintainVisibleScrollPositionHelper2 = new MaintainVisibleScrollPositionHelper(this, false);
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

    /* renamed from: com.facebook.react.views.scroll.ReactScrollView$2  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.ReactScrollView.AnonymousClass2.<clinit>():void");
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

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (isContentReady()) {
            int i5 = this.pendingContentOffsetX;
            if (i5 == -1) {
                i5 = getScrollX();
            }
            int i6 = this.pendingContentOffsetY;
            if (i6 == -1) {
                i6 = getScrollY();
            }
            scrollTo(i5, i6);
        }
        ReactScrollViewHelper.emitLayoutEvent(this);
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

    public void requestChildFocus(View view, View view2) {
        if (view2 != null) {
            scrollToChild(view2);
        }
        super.requestChildFocus(view, view2);
    }

    private int getScrollDelta(View view) {
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        return computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
    }

    /* access modifiers changed from: package-private */
    public boolean isPartiallyScrolledInView(View view) {
        int scrollDelta = getScrollDelta(view);
        view.getDrawingRect(this.mTempRect);
        return scrollDelta != 0 && Math.abs(scrollDelta) < this.mTempRect.width();
    }

    private void scrollToChild(View view) {
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(view, rect);
        int computeScrollDeltaToGetChildRectOnScreen = computeScrollDeltaToGetChildRectOnScreen(rect);
        if (computeScrollDeltaToGetChildRectOnScreen != 0) {
            scrollBy(0, computeScrollDeltaToGetChildRectOnScreen);
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        Systrace.beginSection(0, "ReactScrollView.onScrollChanged");
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

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollEnabled) {
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
        if (this.mScrollEnabled || (keyCode != 19 && keyCode != 20)) {
            return super.executeKeyEvent(keyEvent);
        }
        return false;
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

    public void updateClippingRect() {
        if (this.mRemoveClippedSubviews) {
            Systrace.beginSection(0, "ReactScrollView.updateClippingRect");
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

    public void fling(int i) {
        int correctFlingVelocityY = correctFlingVelocityY(i);
        if (this.mPagingEnabled) {
            flingAndSnap(correctFlingVelocityY);
        } else if (this.mScroller != null) {
            int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
            this.mScroller.fling(getScrollX(), getScrollY(), 0, correctFlingVelocityY, 0, 0, 0, Integer.MAX_VALUE, 0, height / 2);
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            super.fling(correctFlingVelocityY);
        }
        handlePostTouchScrolling(0, correctFlingVelocityY);
    }

    private int correctFlingVelocityY(int i) {
        if (Build.VERSION.SDK_INT != 28) {
            return i;
        }
        float signum = Math.signum(this.mOnScrollDispatchHelper.getYFlingVelocity());
        if (signum == 0.0f) {
            signum = Math.signum((float) i);
        }
        return (int) (((float) Math.abs(i)) * signum);
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
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.ReactScrollView.isScrollPerfLoggingEnabled():boolean");
    }

    private int getMaxScrollY() {
        View view = this.mContentView;
        return Math.max(0, (view == null ? 0 : view.getHeight()) - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
    }

    public StateWrapper getStateWrapper() {
        return this.mStateWrapper;
    }

    public void setStateWrapper(StateWrapper stateWrapper) {
        this.mStateWrapper = stateWrapper;
    }

    public void draw(Canvas canvas) {
        if (this.mEndFillColor != 0) {
            View contentView = getContentView();
            if (!(this.mEndBackground == null || contentView == null || contentView.getBottom() >= getHeight())) {
                this.mEndBackground.setBounds(0, contentView.getBottom(), getWidth(), getHeight());
                this.mEndBackground.draw(canvas);
            }
        }
        super.draw(canvas);
    }

    public void onDraw(Canvas canvas) {
        if (this.mOverflow != Overflow.VISIBLE) {
            BackgroundStyleApplicator.clipToPaddingBox(this, canvas);
        }
        super.onDraw(canvas);
    }

    private void handlePostTouchScrolling(int i, int i2) {
        if (this.mPostTouchRunnable == null) {
            if (this.mSendMomentumEvents) {
                enableFpsListener();
                ReactScrollViewHelper.emitScrollMomentumBeginEvent(this, i, i2);
            }
            this.mActivelyScrolling = false;
            AnonymousClass1 r3 = new Runnable() {
                private boolean mSnappingToPage = false;
                private int mStableFrames = 0;

                public void run() {
                    NativeAnimatedModule nativeAnimatedModule;
                    if (ReactScrollView.this.mActivelyScrolling) {
                        ReactScrollView.this.mActivelyScrolling = false;
                        this.mStableFrames = 0;
                        ViewCompat.postOnAnimationDelayed(ReactScrollView.this, this, 20);
                        return;
                    }
                    ReactScrollViewHelper.updateFabricScrollState(ReactScrollView.this);
                    int i = this.mStableFrames + 1;
                    this.mStableFrames = i;
                    if (i >= 3) {
                        ReactScrollView.this.mPostTouchRunnable = null;
                        if (ReactScrollView.this.mSendMomentumEvents) {
                            ReactScrollViewHelper.emitScrollMomentumEndEvent(ReactScrollView.this);
                        }
                        ReactContext reactContext = (ReactContext) ReactScrollView.this.getContext();
                        if (!(reactContext == null || (nativeAnimatedModule = (NativeAnimatedModule) reactContext.getNativeModule(NativeAnimatedModule.class)) == null)) {
                            nativeAnimatedModule.userDrivenScrollEnded(ReactScrollView.this.getId());
                        }
                        ReactScrollView.this.disableFpsListener();
                        return;
                    }
                    if (ReactScrollView.this.mPagingEnabled && !this.mSnappingToPage) {
                        this.mSnappingToPage = true;
                        ReactScrollView.this.flingAndSnap(0);
                    }
                    ViewCompat.postOnAnimationDelayed(ReactScrollView.this, this, 20);
                }
            };
            this.mPostTouchRunnable = r3;
            ViewCompat.postOnAnimationDelayed(this, r3, 20);
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
        if (getFlingAnimator() == this.DEFAULT_FLING_ANIMATOR) {
            return ReactScrollViewHelper.predictFinalScrollPosition(this, 0, i, 0, getMaxScrollY()).y;
        }
        return getFlingExtrapolatedDistance(i) + ReactScrollViewHelper.getNextFlingStartValue(this, getScrollY(), getReactScrollViewScrollState().getFinalAnimatedPositionScroll().y, i);
    }

    private View getContentView() {
        return getChildAt(0);
    }

    private void smoothScrollAndSnap(int i) {
        double snapInterval = (double) getSnapInterval();
        double nextFlingStartValue = (double) ReactScrollViewHelper.getNextFlingStartValue(this, getScrollY(), getReactScrollViewScrollState().getFinalAnimatedPositionScroll().y, i);
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
            reactSmoothScrollTo(getScrollX(), (int) d2);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01f6  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01bf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void flingAndSnap(int r28) {
        /*
            r27 = this;
            r0 = r27
            int r1 = r27.getChildCount()
            if (r1 > 0) goto L_0x0009
            return
        L_0x0009:
            int r1 = r0.mSnapInterval
            if (r1 != 0) goto L_0x0019
            java.util.List<java.lang.Integer> r1 = r0.mSnapOffsets
            if (r1 != 0) goto L_0x0019
            int r1 = r0.mSnapToAlignment
            if (r1 != 0) goto L_0x0019
            r27.smoothScrollAndSnap(r28)
            return
        L_0x0019:
            android.animation.ValueAnimator r1 = r27.getFlingAnimator()
            android.animation.ValueAnimator r2 = r0.DEFAULT_FLING_ANIMATOR
            r3 = 1
            r4 = 0
            if (r1 == r2) goto L_0x0025
            r1 = r3
            goto L_0x0026
        L_0x0025:
            r1 = r4
        L_0x0026:
            int r2 = r27.getMaxScrollY()
            int r5 = r27.predictFinalScrollPosition(r28)
            boolean r6 = r0.mDisableIntervalMomentum
            if (r6 == 0) goto L_0x0036
            int r5 = r27.getScrollY()
        L_0x0036:
            int r6 = r27.getHeight()
            int r7 = r27.getPaddingBottom()
            int r6 = r6 - r7
            int r7 = r27.getPaddingTop()
            int r6 = r6 - r7
            java.util.List<java.lang.Integer> r7 = r0.mSnapOffsets
            r8 = 2
            if (r7 == 0) goto L_0x0090
            java.lang.Object r7 = r7.get(r4)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            java.util.List<java.lang.Integer> r9 = r0.mSnapOffsets
            int r10 = r9.size()
            int r10 = r10 - r3
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r12 = r2
            r10 = r4
            r11 = r10
        L_0x0067:
            java.util.List<java.lang.Integer> r13 = r0.mSnapOffsets
            int r13 = r13.size()
            if (r10 >= r13) goto L_0x015a
            java.util.List<java.lang.Integer> r13 = r0.mSnapOffsets
            java.lang.Object r13 = r13.get(r10)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            if (r13 > r5) goto L_0x0084
            int r14 = r5 - r13
            int r15 = r5 - r11
            if (r14 >= r15) goto L_0x0084
            r11 = r13
        L_0x0084:
            if (r13 < r5) goto L_0x008d
            int r14 = r13 - r5
            int r15 = r12 - r5
            if (r14 >= r15) goto L_0x008d
            r12 = r13
        L_0x008d:
            int r10 = r10 + 1
            goto L_0x0067
        L_0x0090:
            int r7 = r0.mSnapToAlignment
            if (r7 == 0) goto L_0x013f
            int r9 = r0.mSnapInterval
            if (r9 <= 0) goto L_0x00c4
            double r10 = (double) r5
            double r12 = (double) r9
            double r10 = r10 / r12
            double r12 = java.lang.Math.floor(r10)
            int r9 = r0.mSnapInterval
            double r14 = (double) r9
            double r12 = r12 * r14
            int r12 = (int) r12
            int r7 = r0.getItemStartOffset(r7, r12, r9, r6)
            int r7 = java.lang.Math.max(r7, r4)
            int r9 = r0.mSnapToAlignment
            double r10 = java.lang.Math.ceil(r10)
            int r12 = r0.mSnapInterval
            double r13 = (double) r12
            double r10 = r10 * r13
            int r10 = (int) r10
            int r9 = r0.getItemStartOffset(r9, r10, r12, r6)
            int r12 = java.lang.Math.min(r9, r2)
            r9 = r2
            r11 = r7
        L_0x00c1:
            r7 = r4
            goto L_0x015a
        L_0x00c4:
            android.view.View r7 = r27.getContentView()
            android.view.ViewGroup r7 = (android.view.ViewGroup) r7
            r11 = r2
            r12 = r11
            r9 = r4
            r10 = r9
            r13 = r10
        L_0x00cf:
            int r14 = r7.getChildCount()
            if (r9 >= r14) goto L_0x0135
            android.view.View r14 = r7.getChildAt(r9)
            int r15 = r0.mSnapToAlignment
            if (r15 == r3) goto L_0x0113
            if (r15 == r8) goto L_0x0107
            r3 = 3
            if (r15 != r3) goto L_0x00ee
            int r3 = r14.getTop()
            int r14 = r14.getHeight()
            int r14 = r6 - r14
        L_0x00ec:
            int r3 = r3 - r14
            goto L_0x0117
        L_0x00ee:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Invalid SnapToAlignment value: "
            r2.append(r3)
            int r3 = r0.mSnapToAlignment
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0107:
            int r3 = r14.getTop()
            int r14 = r14.getHeight()
            int r14 = r6 - r14
            int r14 = r14 / r8
            goto L_0x00ec
        L_0x0113:
            int r3 = r14.getTop()
        L_0x0117:
            if (r3 > r5) goto L_0x0120
            int r14 = r5 - r3
            int r15 = r5 - r10
            if (r14 >= r15) goto L_0x0120
            r10 = r3
        L_0x0120:
            if (r3 < r5) goto L_0x0129
            int r14 = r3 - r5
            int r15 = r12 - r5
            if (r14 >= r15) goto L_0x0129
            r12 = r3
        L_0x0129:
            int r11 = java.lang.Math.min(r11, r3)
            int r13 = java.lang.Math.max(r13, r3)
            int r9 = r9 + 1
            r3 = 1
            goto L_0x00cf
        L_0x0135:
            int r11 = java.lang.Math.max(r10, r11)
            int r12 = java.lang.Math.min(r12, r13)
            r9 = r2
            goto L_0x00c1
        L_0x013f:
            int r3 = r27.getSnapInterval()
            double r9 = (double) r3
            double r11 = (double) r5
            double r11 = r11 / r9
            double r13 = java.lang.Math.floor(r11)
            double r13 = r13 * r9
            int r3 = (int) r13
            double r11 = java.lang.Math.ceil(r11)
            double r11 = r11 * r9
            int r7 = (int) r11
            int r12 = java.lang.Math.min(r7, r2)
            r9 = r2
            r11 = r3
            goto L_0x00c1
        L_0x015a:
            int r3 = r5 - r11
            int r10 = java.lang.Math.abs(r3)
            int r13 = r12 - r5
            int r14 = java.lang.Math.abs(r13)
            if (r10 >= r14) goto L_0x016a
            r10 = r11
            goto L_0x016b
        L_0x016a:
            r10 = r12
        L_0x016b:
            boolean r14 = r0.mSnapToEnd
            if (r14 != 0) goto L_0x017c
            if (r5 < r9) goto L_0x017c
            int r3 = r27.getScrollY()
            if (r3 < r9) goto L_0x0178
            goto L_0x0188
        L_0x0178:
            r3 = r28
            r5 = r9
            goto L_0x01b0
        L_0x017c:
            boolean r9 = r0.mSnapToStart
            if (r9 != 0) goto L_0x018f
            if (r5 > r7) goto L_0x018f
            int r3 = r27.getScrollY()
            if (r3 > r7) goto L_0x018b
        L_0x0188:
            r3 = r28
            goto L_0x01b0
        L_0x018b:
            r3 = r28
            r5 = r7
            goto L_0x01b0
        L_0x018f:
            r14 = 4621819117588971520(0x4024000000000000, double:10.0)
            if (r28 <= 0) goto L_0x019f
            if (r1 != 0) goto L_0x019b
            double r9 = (double) r13
            double r9 = r9 * r14
            int r3 = (int) r9
            int r3 = r28 + r3
            goto L_0x019d
        L_0x019b:
            r3 = r28
        L_0x019d:
            r5 = r12
            goto L_0x01b0
        L_0x019f:
            if (r28 >= 0) goto L_0x01ad
            if (r1 != 0) goto L_0x01a9
            double r9 = (double) r3
            double r9 = r9 * r14
            int r3 = (int) r9
            int r3 = r28 - r3
            goto L_0x01ab
        L_0x01a9:
            r3 = r28
        L_0x01ab:
            r5 = r11
            goto L_0x01b0
        L_0x01ad:
            r3 = r28
            r5 = r10
        L_0x01b0:
            int r5 = java.lang.Math.max(r4, r5)
            int r5 = java.lang.Math.min(r5, r2)
            if (r1 != 0) goto L_0x01f6
            android.widget.OverScroller r1 = r0.mScroller
            if (r1 != 0) goto L_0x01bf
            goto L_0x01f6
        L_0x01bf:
            r7 = 1
            r0.mActivelyScrolling = r7
            int r17 = r27.getScrollX()
            int r18 = r27.getScrollY()
            if (r3 == 0) goto L_0x01cf
        L_0x01cc:
            r20 = r3
            goto L_0x01d6
        L_0x01cf:
            int r3 = r27.getScrollY()
            int r3 = r5 - r3
            goto L_0x01cc
        L_0x01d6:
            if (r5 == 0) goto L_0x01de
            if (r5 != r2) goto L_0x01db
            goto L_0x01de
        L_0x01db:
            r26 = r4
            goto L_0x01e1
        L_0x01de:
            int r4 = r6 / 2
            goto L_0x01db
        L_0x01e1:
            r19 = 0
            r21 = 0
            r22 = 0
            r25 = 0
            r16 = r1
            r23 = r5
            r24 = r5
            r16.fling(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            r27.postInvalidateOnAnimation()
            goto L_0x01fd
        L_0x01f6:
            int r1 = r27.getScrollX()
            r0.reactSmoothScrollTo(r1, r5)
        L_0x01fd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.ReactScrollView.flingAndSnap(int):void");
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

    private int getSnapInterval() {
        int i = this.mSnapInterval;
        if (i != 0) {
            return i;
        }
        return getHeight();
    }

    public void setEndFillColor(int i) {
        if (i != this.mEndFillColor) {
            this.mEndFillColor = i;
            this.mEndBackground = new ColorDrawable(this.mEndFillColor);
        }
    }

    /* access modifiers changed from: protected */
    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        int maxScrollY;
        OverScroller overScroller = this.mScroller;
        if (!(overScroller == null || this.mContentView == null || overScroller.isFinished() || this.mScroller.getCurrY() == this.mScroller.getFinalY() || i2 < (maxScrollY = getMaxScrollY()))) {
            this.mScroller.abortAnimation();
            i2 = maxScrollY;
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
            this.mContentView = null;
        }
    }

    public void setContentOffset(ReadableMap readableMap) {
        ReadableMap readableMap2 = this.mCurrentContentOffset;
        if (readableMap2 == null || !readableMap2.equals(readableMap)) {
            this.mCurrentContentOffset = readableMap;
            if (readableMap != null) {
                double d = 0.0d;
                double d2 = readableMap.hasKey("x") ? readableMap.getDouble("x") : 0.0d;
                if (readableMap.hasKey("y")) {
                    d = readableMap.getDouble("y");
                }
                scrollTo((int) PixelUtil.toPixelFromDIP(d2), (int) PixelUtil.toPixelFromDIP(d));
                return;
            }
            scrollTo(0, 0);
        }
    }

    public void reactSmoothScrollTo(int i, int i2) {
        ReactScrollViewHelper.smoothScrollTo(this, i, i2);
        setPendingContentOffsets(i, i2);
    }

    public void scrollTo(int i, int i2) {
        super.scrollTo(i, i2);
        ReactScrollViewHelper.updateFabricScrollState(this);
        setPendingContentOffsets(i, i2);
    }

    private void recreateFlingAnimation(int i) {
        if (getFlingAnimator().isRunning()) {
            getFlingAnimator().cancel();
        }
        OverScroller overScroller = this.mScroller;
        if (overScroller != null && !overScroller.isFinished()) {
            int currY = this.mScroller.getCurrY();
            boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            this.mScroller.forceFinished(true);
            if (computeScrollOffset) {
                int i2 = i;
                this.mScroller.fling(getScrollX(), i2, 0, (int) (this.mScroller.getCurrVelocity() * Math.signum((float) (this.mScroller.getFinalY() - this.mScroller.getStartY()))), 0, 0, 0, Integer.MAX_VALUE);
                return;
            }
            scrollTo(getScrollX(), i + (this.mScroller.getCurrX() - currY));
        }
    }

    public void scrollToPreservingMomentum(int i, int i2) {
        scrollTo(i, i2);
        recreateFlingAnimation(i2);
    }

    private boolean isContentReady() {
        View contentView = getContentView();
        return (contentView == null || contentView.getWidth() == 0 || contentView.getHeight() == 0) ? false : true;
    }

    private void setPendingContentOffsets(int i, int i2) {
        if (isContentReady()) {
            this.pendingContentOffsetX = -1;
            this.pendingContentOffsetY = -1;
            return;
        }
        this.pendingContentOffsetX = i;
        this.pendingContentOffsetY = i2;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int maxScrollY;
        if (this.mContentView != null) {
            MaintainVisibleScrollPositionHelper maintainVisibleScrollPositionHelper = this.mMaintainVisibleContentPositionHelper;
            if (maintainVisibleScrollPositionHelper != null) {
                maintainVisibleScrollPositionHelper.updateScrollPosition();
            }
            if (isShown() && isContentReady() && getScrollY() > (maxScrollY = getMaxScrollY())) {
                scrollTo(getScrollX(), maxScrollY);
            }
            ReactScrollViewHelper.emitLayoutChangeEvent(this);
        }
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

    public void setScrollAwayTopPaddingEnabledUnstable(int i) {
        int childCount = getChildCount();
        boolean z = true;
        if (childCount > 1) {
            z = false;
        }
        Assertions.assertCondition(z, "React Native ScrollView should not have more than one child, it should have exactly 1 child; a content View");
        if (childCount > 0) {
            for (int i2 = 0; i2 < childCount; i2++) {
                getChildAt(i2).setTranslationY((float) i);
            }
            setPadding(0, 0, 0, i);
        }
        updateScrollAwayState(i);
        setRemoveClippedSubviews(this.mRemoveClippedSubviews);
    }

    private void updateScrollAwayState(int i) {
        getReactScrollViewScrollState().setScrollAwayPaddingTop(i);
        ReactScrollViewHelper.forceUpdateState(this);
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
            ReactScrollViewHelper.emitScrollMomentumBeginEvent(this, 0, defaultScrollAnimationDuration > 0 ? (i2 - i) / defaultScrollAnimationDuration : 0);
            ReactScrollViewHelper.dispatchMomentumEndOnAnimationEnd(this);
        }
    }

    public ValueAnimator getFlingAnimator() {
        return this.DEFAULT_FLING_ANIMATOR;
    }

    public int getFlingExtrapolatedDistance(int i) {
        return ReactScrollViewHelper.predictFinalScrollPosition(this, 0, i, 0, getMaxScrollY()).y;
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
