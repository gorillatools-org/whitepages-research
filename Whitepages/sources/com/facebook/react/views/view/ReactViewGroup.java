package com.facebook.react.views.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.animation.Animation;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.ReactRootView$$ExternalSyntheticApiModelOutline0;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.touch.OnInterceptTouchEventListener;
import com.facebook.react.touch.ReactHitSlopView;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.uimanager.BackgroundStyleApplicator;
import com.facebook.react.uimanager.BlendModeHelper;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.LengthPercentageType;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactClippingProhibitedView;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.ReactOverflowViewWithInset;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.ReactZIndexedViewGroup;
import com.facebook.react.uimanager.ViewGroupDrawingOrderHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.BorderStyle;
import com.facebook.react.uimanager.style.LogicalEdge;
import com.facebook.react.uimanager.style.Overflow;
import java.util.HashSet;
import java.util.Set;

public class ReactViewGroup extends ViewGroup implements ReactInterceptingViewGroup, ReactClippingViewGroup, ReactPointerEventsView, ReactHitSlopView, ReactZIndexedViewGroup, ReactOverflowViewWithInset {
    private static final int ARRAY_CAPACITY_INCREMENT = 12;
    private static final ViewGroup.LayoutParams sDefaultLayoutParam = new ViewGroup.LayoutParams(0, 0);
    private View[] mAllChildren;
    private int mAllChildrenCount;
    private float mBackfaceOpacity;
    private boolean mBackfaceVisible;
    private ChildrenLayoutChangeListener mChildrenLayoutChangeListener;
    private Set<Integer> mChildrenRemovedWhileTransitioning;
    private Rect mClippingRect;
    private ViewGroupDrawingOrderHelper mDrawingOrderHelper;
    private Rect mHitSlopRect;
    private volatile boolean mInSubviewClippingLoop;
    private boolean mNeedsOffscreenAlphaCompositing;
    private OnInterceptTouchEventListener mOnInterceptTouchEventListener;
    private Overflow mOverflow;
    private final Rect mOverflowInset = new Rect();
    private PointerEvents mPointerEvents = PointerEvents.AUTO;
    private int mRecycleCount = 0;
    private boolean mRemoveClippedSubviews;

    /* access modifiers changed from: protected */
    public void dispatchSetPressed(boolean z) {
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @SuppressLint({"MissingSuperCall"})
    public void requestLayout() {
    }

    private static final class ChildrenLayoutChangeListener implements View.OnLayoutChangeListener {
        private ReactViewGroup mParent;

        private ChildrenLayoutChangeListener(ReactViewGroup reactViewGroup) {
            this.mParent = reactViewGroup;
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            ReactViewGroup reactViewGroup = this.mParent;
            if (reactViewGroup != null && reactViewGroup.getRemoveClippedSubviews()) {
                this.mParent.updateSubviewClipStatus(view);
            }
        }

        public void shutdown() {
            this.mParent = null;
        }
    }

    public ReactViewGroup(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        setClipChildren(false);
        this.mRemoveClippedSubviews = false;
        this.mInSubviewClippingLoop = false;
        this.mAllChildren = null;
        this.mAllChildrenCount = 0;
        this.mClippingRect = null;
        this.mHitSlopRect = null;
        this.mOverflow = Overflow.VISIBLE;
        this.mPointerEvents = PointerEvents.AUTO;
        this.mChildrenLayoutChangeListener = null;
        this.mOnInterceptTouchEventListener = null;
        this.mNeedsOffscreenAlphaCompositing = false;
        this.mDrawingOrderHelper = null;
        this.mBackfaceOpacity = 1.0f;
        this.mBackfaceVisible = true;
        this.mChildrenRemovedWhileTransitioning = null;
    }

    /* access modifiers changed from: package-private */
    public void recycleView() {
        ChildrenLayoutChangeListener childrenLayoutChangeListener;
        this.mRecycleCount++;
        if (!(this.mAllChildren == null || (childrenLayoutChangeListener = this.mChildrenLayoutChangeListener) == null)) {
            childrenLayoutChangeListener.shutdown();
            for (int i = 0; i < this.mAllChildrenCount; i++) {
                this.mAllChildren[i].removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
            }
        }
        initView();
        this.mOverflowInset.setEmpty();
        removeAllViews();
        updateBackgroundDrawable((Drawable) null);
        resetPointerEvents();
    }

    private ViewGroupDrawingOrderHelper getDrawingOrderHelper() {
        if (this.mDrawingOrderHelper == null) {
            this.mDrawingOrderHelper = new ViewGroupDrawingOrderHelper(this);
        }
        return this.mDrawingOrderHelper;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }

    @TargetApi(23)
    public void dispatchProvideStructure(ViewStructure viewStructure) {
        try {
            super.dispatchProvideStructure(viewStructure);
        } catch (NullPointerException e) {
            FLog.e(ReactConstants.TAG, "NullPointerException when executing dispatchProvideStructure", (Throwable) e);
        }
    }

    public void setBackgroundColor(int i) {
        BackgroundStyleApplicator.setBackgroundColor(this, Integer.valueOf(i));
    }

    @Deprecated(forRemoval = true, since = "0.76.0")
    public void setTranslucentBackgroundDrawable(Drawable drawable) {
        BackgroundStyleApplicator.setFeedbackUnderlay(this, drawable);
    }

    public void setOnInterceptTouchEventListener(OnInterceptTouchEventListener onInterceptTouchEventListener) {
        this.mOnInterceptTouchEventListener = onInterceptTouchEventListener;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        OnInterceptTouchEventListener onInterceptTouchEventListener = this.mOnInterceptTouchEventListener;
        if ((onInterceptTouchEventListener == null || !onInterceptTouchEventListener.onInterceptTouchEvent(this, motionEvent)) && PointerEvents.canChildrenBeTouchTarget(this.mPointerEvents)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return PointerEvents.canBeTouchTarget(this.mPointerEvents);
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (ReactFeatureFlags.dispatchPointerEvents) {
            return PointerEvents.canBeTouchTarget(this.mPointerEvents);
        }
        return super.onHoverEvent(motionEvent);
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if (!PointerEvents.canChildrenBeTouchTarget(this.mPointerEvents)) {
            return false;
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    public boolean hasOverlappingRendering() {
        return this.mNeedsOffscreenAlphaCompositing;
    }

    public void setNeedsOffscreenAlphaCompositing(boolean z) {
        this.mNeedsOffscreenAlphaCompositing = z;
    }

    public void setBorderWidth(int i, float f) {
        BackgroundStyleApplicator.setBorderWidth(this, LogicalEdge.values()[i], Float.valueOf(PixelUtil.toDIPFromPixel(f)));
    }

    public void setBorderColor(int i, Integer num) {
        BackgroundStyleApplicator.setBorderColor(this, LogicalEdge.values()[i], num);
    }

    @Deprecated(forRemoval = true, since = "0.75.0")
    public void setBorderRadius(float f) {
        setBorderRadius(f, BorderRadiusProp.BORDER_RADIUS.ordinal());
    }

    @Deprecated(forRemoval = true, since = "0.75.0")
    public void setBorderRadius(float f, int i) {
        LengthPercentage lengthPercentage;
        if (Float.isNaN(f)) {
            lengthPercentage = null;
        } else {
            lengthPercentage = new LengthPercentage(f, LengthPercentageType.POINT);
        }
        BackgroundStyleApplicator.setBorderRadius(this, BorderRadiusProp.values()[i], lengthPercentage);
    }

    public void setBorderRadius(BorderRadiusProp borderRadiusProp, LengthPercentage lengthPercentage) {
        BackgroundStyleApplicator.setBorderRadius(this, borderRadiusProp, lengthPercentage);
    }

    public void setBorderStyle(String str) {
        BackgroundStyleApplicator.setBorderStyle(this, str == null ? null : BorderStyle.fromString(str));
    }

    public void setRemoveClippedSubviews(boolean z) {
        if (z != this.mRemoveClippedSubviews) {
            this.mRemoveClippedSubviews = z;
            this.mChildrenRemovedWhileTransitioning = null;
            if (z) {
                Rect rect = new Rect();
                this.mClippingRect = rect;
                ReactClippingViewGroupHelper.calculateClippingRect(this, rect);
                int childCount = getChildCount();
                this.mAllChildrenCount = childCount;
                this.mAllChildren = new View[Math.max(12, childCount)];
                this.mChildrenLayoutChangeListener = new ChildrenLayoutChangeListener();
                for (int i = 0; i < this.mAllChildrenCount; i++) {
                    View childAt = getChildAt(i);
                    this.mAllChildren[i] = childAt;
                    childAt.addOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
                    setViewClipped(childAt, false);
                }
                updateClippingRect();
                return;
            }
            Assertions.assertNotNull(this.mClippingRect);
            Assertions.assertNotNull(this.mAllChildren);
            Assertions.assertNotNull(this.mChildrenLayoutChangeListener);
            for (int i2 = 0; i2 < this.mAllChildrenCount; i2++) {
                this.mAllChildren[i2].removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
            }
            getDrawingRect(this.mClippingRect);
            updateClippingToRect(this.mClippingRect);
            this.mAllChildren = null;
            this.mClippingRect = null;
            this.mAllChildrenCount = 0;
            this.mChildrenLayoutChangeListener = null;
        }
    }

    public boolean getRemoveClippedSubviews() {
        return this.mRemoveClippedSubviews;
    }

    public void getClippingRect(Rect rect) {
        rect.set((Rect) Assertions.nullsafeFIXME(this.mClippingRect, "Fix in Kotlin"));
    }

    public void updateClippingRect() {
        if (this.mRemoveClippedSubviews) {
            Assertions.assertNotNull(this.mClippingRect);
            Assertions.assertNotNull(this.mAllChildren);
            ReactClippingViewGroupHelper.calculateClippingRect(this, this.mClippingRect);
            updateClippingToRect(this.mClippingRect);
        }
    }

    public void endViewTransition(View view) {
        super.endViewTransition(view);
        Set<Integer> set = this.mChildrenRemovedWhileTransitioning;
        if (set != null) {
            set.remove(Integer.valueOf(view.getId()));
        }
    }

    private void trackChildViewTransition(int i) {
        if (this.mChildrenRemovedWhileTransitioning == null) {
            this.mChildrenRemovedWhileTransitioning = new HashSet();
        }
        this.mChildrenRemovedWhileTransitioning.add(Integer.valueOf(i));
    }

    private boolean isChildRemovedWhileTransitioning(View view) {
        Set<Integer> set = this.mChildrenRemovedWhileTransitioning;
        return set != null && set.contains(Integer.valueOf(view.getId()));
    }

    private void updateClippingToRect(Rect rect) {
        Assertions.assertNotNull(this.mAllChildren);
        this.mInSubviewClippingLoop = true;
        int i = 0;
        int i2 = 0;
        while (i < this.mAllChildrenCount) {
            try {
                updateSubviewClipStatus(rect, i, i2);
                if (isViewClipped(this.mAllChildren[i], Integer.valueOf(i))) {
                    i2++;
                }
                i++;
            } catch (IndexOutOfBoundsException e) {
                HashSet hashSet = new HashSet();
                int i3 = 0;
                for (int i4 = 0; i4 < i; i4++) {
                    i3 += isViewClipped(this.mAllChildren[i4], (Integer) null) ? 1 : 0;
                    hashSet.add(this.mAllChildren[i4]);
                }
                throw new IllegalStateException("Invalid clipping state. i=" + i + " clippedSoFar=" + i2 + " count=" + getChildCount() + " allChildrenCount=" + this.mAllChildrenCount + " recycleCount=" + this.mRecycleCount + " realClippedSoFar=" + i3 + " uniqueViewsCount=" + hashSet.size(), e);
            }
        }
        this.mInSubviewClippingLoop = false;
    }

    private void updateSubviewClipStatus(Rect rect, int i, int i2) {
        UiThreadUtil.assertOnUiThread();
        View view = ((View[]) Assertions.assertNotNull(this.mAllChildren))[i];
        boolean intersects = rect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        Animation animation = view.getAnimation();
        boolean z = animation != null && !animation.hasEnded();
        if (!intersects && !isViewClipped(view, Integer.valueOf(i)) && !z) {
            setViewClipped(view, true);
            removeViewInLayout(view);
        } else if (intersects && isViewClipped(view, Integer.valueOf(i))) {
            int i3 = i - i2;
            Assertions.assertCondition(i3 >= 0);
            setViewClipped(view, false);
            addViewInLayout(view, i3, sDefaultLayoutParam, true);
            invalidate();
        } else if (!intersects) {
            return;
        }
        if (view instanceof ReactClippingViewGroup) {
            ReactClippingViewGroup reactClippingViewGroup = (ReactClippingViewGroup) view;
            if (reactClippingViewGroup.getRemoveClippedSubviews()) {
                reactClippingViewGroup.updateClippingRect();
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateSubviewClipStatus(View view) {
        if (this.mRemoveClippedSubviews && getParent() != null) {
            Assertions.assertNotNull(this.mClippingRect);
            Assertions.assertNotNull(this.mAllChildren);
            if (this.mClippingRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()) != (!isViewClipped(view, (Integer) null))) {
                this.mInSubviewClippingLoop = true;
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i >= this.mAllChildrenCount) {
                        break;
                    }
                    View view2 = this.mAllChildren[i];
                    if (view2 == view) {
                        updateSubviewClipStatus(this.mClippingRect, i, i2);
                        break;
                    }
                    if (isViewClipped(view2, Integer.valueOf(i))) {
                        i2++;
                    }
                    i++;
                }
                this.mInSubviewClippingLoop = false;
            }
        }
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
    }

    private boolean customDrawOrderDisabled() {
        if (getId() != -1 && ViewUtil.getUIManagerType(getId()) == 2) {
            return true;
        }
        return false;
    }

    public void onViewAdded(View view) {
        UiThreadUtil.assertOnUiThread();
        checkViewClippingTag(view, Boolean.FALSE);
        if (!customDrawOrderDisabled()) {
            getDrawingOrderHelper().handleAddView(view);
            setChildrenDrawingOrderEnabled(getDrawingOrderHelper().shouldEnableCustomDrawingOrder());
        } else {
            setChildrenDrawingOrderEnabled(false);
        }
        super.onViewAdded(view);
    }

    public void onViewRemoved(View view) {
        UiThreadUtil.assertOnUiThread();
        checkViewClippingTag(view, Boolean.TRUE);
        if (!customDrawOrderDisabled()) {
            getDrawingOrderHelper().handleRemoveView(view);
            setChildrenDrawingOrderEnabled(getDrawingOrderHelper().shouldEnableCustomDrawingOrder());
        } else {
            setChildrenDrawingOrderEnabled(false);
        }
        if (view.getParent() != null) {
            trackChildViewTransition(view.getId());
        }
        super.onViewRemoved(view);
    }

    private void checkViewClippingTag(View view, Boolean bool) {
        if (this.mInSubviewClippingLoop) {
            Object tag = view.getTag(R.id.view_clipped);
            if (!bool.equals(tag)) {
                ReactSoftExceptionLogger.logSoftException(ReactSoftExceptionLogger.Categories.RVG_ON_VIEW_REMOVED, new ReactNoCrashSoftException("View clipping tag mismatch: tag=" + tag + " expected=" + bool));
            }
        }
        if (this.mRemoveClippedSubviews) {
            view.setTag(R.id.view_clipped, bool);
        }
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i, int i2) {
        UiThreadUtil.assertOnUiThread();
        return !customDrawOrderDisabled() ? getDrawingOrderHelper().getChildDrawingOrder(i, i2) : i2;
    }

    public int getZIndexMappedChildIndex(int i) {
        UiThreadUtil.assertOnUiThread();
        return (customDrawOrderDisabled() || !getDrawingOrderHelper().shouldEnableCustomDrawingOrder()) ? i : getDrawingOrderHelper().getChildDrawingOrder(getChildCount(), i);
    }

    public void updateDrawingOrder() {
        if (!customDrawOrderDisabled()) {
            getDrawingOrderHelper().update();
            setChildrenDrawingOrderEnabled(getDrawingOrderHelper().shouldEnableCustomDrawingOrder());
            invalidate();
        }
    }

    public PointerEvents getPointerEvents() {
        return this.mPointerEvents;
    }

    public void setPointerEvents(PointerEvents pointerEvents) {
        this.mPointerEvents = pointerEvents;
    }

    /* access modifiers changed from: package-private */
    public void resetPointerEvents() {
        this.mPointerEvents = PointerEvents.AUTO;
    }

    /* access modifiers changed from: package-private */
    public int getAllChildrenCount() {
        return this.mAllChildrenCount;
    }

    /* access modifiers changed from: package-private */
    public View getChildAtWithSubviewClippingEnabled(int i) {
        if (i < 0 || i >= this.mAllChildrenCount) {
            return null;
        }
        return ((View[]) Assertions.assertNotNull(this.mAllChildren))[i];
    }

    /* access modifiers changed from: package-private */
    public void addViewWithSubviewClippingEnabled(View view, int i) {
        addViewWithSubviewClippingEnabled(view, i, sDefaultLayoutParam);
    }

    /* access modifiers changed from: package-private */
    public void addViewWithSubviewClippingEnabled(final View view, int i, ViewGroup.LayoutParams layoutParams) {
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        setViewClipped(view, true);
        addInArray(view, i);
        Rect rect = (Rect) Assertions.assertNotNull(this.mClippingRect);
        View[] viewArr = (View[]) Assertions.assertNotNull(this.mAllChildren);
        this.mInSubviewClippingLoop = true;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (isViewClipped(viewArr[i3], Integer.valueOf(i3))) {
                i2++;
            }
        }
        updateSubviewClipStatus(rect, i, i2);
        this.mInSubviewClippingLoop = false;
        view.addOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        if (view instanceof ReactClippingProhibitedView) {
            UiThreadUtil.runOnUiThread(new Runnable() {
                public void run() {
                    if (!view.isShown()) {
                        ReactSoftExceptionLogger.logSoftException(ReactConstants.TAG, new ReactNoCrashSoftException("Child view has been added to Parent view in which it is clipped and not visible. This is not legal for this particular child view. Child: [" + view.getId() + "] " + view.toString() + " Parent: [" + ReactViewGroup.this.getId() + "] " + toString()));
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void removeViewWithSubviewClippingEnabled(View view) {
        UiThreadUtil.assertOnUiThread();
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mClippingRect);
        View[] viewArr = (View[]) Assertions.assertNotNull(this.mAllChildren);
        view.removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        int indexOfChildInAllChildren = indexOfChildInAllChildren(view);
        if (!isViewClipped(viewArr[indexOfChildInAllChildren], Integer.valueOf(indexOfChildInAllChildren))) {
            int i = 0;
            for (int i2 = 0; i2 < indexOfChildInAllChildren; i2++) {
                if (isViewClipped(viewArr[i2], Integer.valueOf(i2))) {
                    i++;
                }
            }
            removeViewsInLayout(indexOfChildInAllChildren - i, 1);
            invalidate();
        }
        removeFromArray(indexOfChildInAllChildren);
    }

    /* access modifiers changed from: package-private */
    public void removeAllViewsWithSubviewClippingEnabled() {
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        View[] viewArr = (View[]) Assertions.assertNotNull(this.mAllChildren);
        for (int i = 0; i < this.mAllChildrenCount; i++) {
            viewArr[i].removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        }
        removeAllViewsInLayout();
        this.mAllChildrenCount = 0;
    }

    private boolean isViewClipped(View view, Integer num) {
        Object tag = view.getTag(R.id.view_clipped);
        if (tag != null) {
            return ((Boolean) tag).booleanValue();
        }
        ViewParent parent = view.getParent();
        boolean isChildRemovedWhileTransitioning = isChildRemovedWhileTransitioning(view);
        boolean z = true;
        if (num != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("View missing clipping tag: index=");
            sb.append(num);
            sb.append(" parentNull=");
            sb.append(parent == null);
            sb.append(" parentThis=");
            sb.append(parent == this);
            sb.append(" transitioning=");
            sb.append(isChildRemovedWhileTransitioning);
            ReactSoftExceptionLogger.logSoftException(ReactSoftExceptionLogger.Categories.RVG_IS_VIEW_CLIPPED, new ReactNoCrashSoftException(sb.toString()));
        }
        if (parent == null || isChildRemovedWhileTransitioning) {
            return true;
        }
        if (parent != this) {
            z = false;
        }
        Assertions.assertCondition(z);
        return false;
    }

    private static void setViewClipped(View view, boolean z) {
        view.setTag(R.id.view_clipped, Boolean.valueOf(z));
    }

    private int indexOfChildInAllChildren(View view) {
        int i = this.mAllChildrenCount;
        View[] viewArr = (View[]) Assertions.assertNotNull(this.mAllChildren);
        for (int i2 = 0; i2 < i; i2++) {
            if (viewArr[i2] == view) {
                return i2;
            }
        }
        return -1;
    }

    private void addInArray(View view, int i) {
        View[] viewArr = (View[]) Assertions.assertNotNull(this.mAllChildren);
        int i2 = this.mAllChildrenCount;
        int length = viewArr.length;
        if (i == i2) {
            if (length == i2) {
                View[] viewArr2 = new View[(length + 12)];
                this.mAllChildren = viewArr2;
                System.arraycopy(viewArr, 0, viewArr2, 0, length);
                viewArr = this.mAllChildren;
            }
            int i3 = this.mAllChildrenCount;
            this.mAllChildrenCount = i3 + 1;
            viewArr[i3] = view;
        } else if (i < i2) {
            if (length == i2) {
                View[] viewArr3 = new View[(length + 12)];
                this.mAllChildren = viewArr3;
                System.arraycopy(viewArr, 0, viewArr3, 0, i);
                System.arraycopy(viewArr, i, this.mAllChildren, i + 1, i2 - i);
                viewArr = this.mAllChildren;
            } else {
                System.arraycopy(viewArr, i, viewArr, i + 1, i2 - i);
            }
            viewArr[i] = view;
            this.mAllChildrenCount++;
        } else {
            throw new IndexOutOfBoundsException("index=" + i + " count=" + i2);
        }
    }

    private void removeFromArray(int i) {
        View[] viewArr = (View[]) Assertions.assertNotNull(this.mAllChildren);
        int i2 = this.mAllChildrenCount;
        if (i == i2 - 1) {
            int i3 = i2 - 1;
            this.mAllChildrenCount = i3;
            viewArr[i3] = null;
        } else if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException();
        } else {
            System.arraycopy(viewArr, i + 1, viewArr, i, (i2 - i) - 1);
            int i4 = this.mAllChildrenCount - 1;
            this.mAllChildrenCount = i4;
            viewArr[i4] = null;
        }
    }

    public Rect getHitSlopRect() {
        return this.mHitSlopRect;
    }

    public void setHitSlopRect(Rect rect) {
        this.mHitSlopRect = rect;
    }

    public void setOverflow(String str) {
        if (str == null) {
            this.mOverflow = Overflow.VISIBLE;
        } else {
            Overflow fromString = Overflow.fromString(str);
            if (fromString == null) {
                fromString = Overflow.VISIBLE;
            }
            this.mOverflow = fromString;
        }
        invalidate();
    }

    /* renamed from: com.facebook.react.views.view.ReactViewGroup$2  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.view.ReactViewGroup.AnonymousClass2.<clinit>():void");
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
        if (BlendModeHelper.needsIsolatedLayer(this)) {
            Rect rect = this.mOverflowInset;
            if (!(rect.left == i && rect.top == i2 && rect.right == i3 && rect.bottom == i4)) {
                invalidate();
            }
        }
        this.mOverflowInset.set(i, i2, i3, i4);
    }

    public Rect getOverflowInset() {
        return this.mOverflowInset;
    }

    /* access modifiers changed from: package-private */
    public void updateBackgroundDrawable(Drawable drawable) {
        super.setBackground(drawable);
    }

    public void draw(Canvas canvas) {
        if (Build.VERSION.SDK_INT < 29 || ViewUtil.getUIManagerType((View) this) != 2 || !BlendModeHelper.needsIsolatedLayer(this)) {
            super.draw(canvas);
            return;
        }
        Rect overflowInset = getOverflowInset();
        canvas.saveLayer((float) overflowInset.left, (float) overflowInset.top, (float) (getWidth() + (-overflowInset.right)), (float) (getHeight() + (-overflowInset.bottom)), (Paint) null);
        super.draw(canvas);
        canvas.restore();
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (!(this.mOverflow == Overflow.VISIBLE && getTag(R.id.filter) == null)) {
            BackgroundStyleApplicator.clipToPaddingBox(this, canvas);
        }
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        BlendMode blendMode;
        boolean z = view.getElevation() > 0.0f;
        if (z) {
            CanvasUtil.enableZ(canvas, true);
        }
        if (Build.VERSION.SDK_INT < 29 || ViewUtil.getUIManagerType((View) this) != 2 || !BlendModeHelper.needsIsolatedLayer(this)) {
            blendMode = null;
        } else {
            blendMode = ReactRootView$$ExternalSyntheticApiModelOutline0.m(view.getTag(R.id.mix_blend_mode));
            if (blendMode != null) {
                Paint paint = new Paint();
                paint.setBlendMode(blendMode);
                Rect overflowInset = getOverflowInset();
                canvas.saveLayer((float) overflowInset.left, (float) overflowInset.top, (float) (getWidth() + (-overflowInset.right)), (float) (getHeight() + (-overflowInset.bottom)), paint);
            }
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        if (blendMode != null) {
            canvas.restore();
        }
        if (z) {
            CanvasUtil.enableZ(canvas, false);
        }
        return drawChild;
    }

    public void setOpacityIfPossible(float f) {
        this.mBackfaceOpacity = f;
        setBackfaceVisibilityDependantOpacity();
    }

    public void setBackfaceVisibility(String str) {
        this.mBackfaceVisible = ViewProps.VISIBLE.equals(str);
        setBackfaceVisibilityDependantOpacity();
    }

    public void setBackfaceVisibilityDependantOpacity() {
        if (this.mBackfaceVisible) {
            setAlpha(this.mBackfaceOpacity);
            return;
        }
        float rotationX = getRotationX();
        float rotationY = getRotationY();
        if (rotationX < -90.0f || rotationX >= 90.0f || rotationY < -90.0f || rotationY >= 90.0f) {
            setAlpha(0.0f);
        } else {
            setAlpha(this.mBackfaceOpacity);
        }
    }
}
