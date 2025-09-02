package com.swmansion.rnscreens;

import android.app.Activity;
import android.view.View;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.views.view.ReactViewGroup;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.math.MathUtils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ScreenFooter extends ReactViewGroup {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private ScreenFooter$footerCallback$1 footerCallback;
    private final ScreenFooter$insetsAnimation$1 insetsAnimation;
    /* access modifiers changed from: private */
    public boolean isAnimationControlledByKeyboard;
    private boolean isCallbackRegistered;
    /* access modifiers changed from: private */
    public int lastBottomInset;
    /* access modifiers changed from: private */
    public int lastContainerHeight;
    /* access modifiers changed from: private */
    public float lastSlideOffset;
    /* access modifiers changed from: private */
    public int lastStableSheetState = 5;
    private final ReactContext reactContext;

    public final ReactContext getReactContext() {
        return this.reactContext;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ScreenFooter(ReactContext reactContext2) {
        super(reactContext2);
        Intrinsics.checkNotNullParameter(reactContext2, "reactContext");
        this.reactContext = reactContext2;
        ScreenFooter$insetsAnimation$1 screenFooter$insetsAnimation$1 = new ScreenFooter$insetsAnimation$1(this);
        this.insetsAnimation = screenFooter$insetsAnimation$1;
        Activity currentActivity = reactContext2.getCurrentActivity();
        if (currentActivity != null) {
            View decorView = currentActivity.getWindow().getDecorView();
            Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
            ViewCompat.setWindowInsetsAnimationCallback(decorView, screenFooter$insetsAnimation$1);
            this.footerCallback = new ScreenFooter$footerCallback$1(this);
            return;
        }
        throw new IllegalStateException("[RNScreens] Context detached from activity while creating ScreenFooter");
    }

    private final Screen getScreenParent() {
        ViewParent parent = getParent();
        if (parent instanceof Screen) {
            return (Screen) parent;
        }
        return null;
    }

    private final BottomSheetBehavior<Screen> getSheetBehavior() {
        return requireScreenParent().getSheetBehavior();
    }

    private final boolean getHasReceivedInitialLayoutFromParent() {
        return this.lastContainerHeight > 0;
    }

    /* access modifiers changed from: private */
    public final int getReactHeight() {
        return getMeasuredHeight();
    }

    private final int getReactWidth() {
        return getMeasuredWidth();
    }

    private final Screen requireScreenParent() {
        Screen screenParent = getScreenParent();
        if (screenParent != null) {
            return screenParent;
        }
        throw new IllegalArgumentException("Required value was null.");
    }

    private final BottomSheetBehavior requireSheetBehavior() {
        BottomSheetBehavior<Screen> sheetBehavior = getSheetBehavior();
        if (sheetBehavior != null) {
            return sheetBehavior;
        }
        throw new IllegalArgumentException("Required value was null.");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (getHasReceivedInitialLayoutFromParent()) {
            layoutFooterOnYAxis(this.lastContainerHeight, i4 - i2, sheetTopInStableState(requireSheetBehavior().getState()), this.lastBottomInset);
        }
    }

    public final void registerWithSheetBehavior(BottomSheetBehavior bottomSheetBehavior) {
        Intrinsics.checkNotNullParameter(bottomSheetBehavior, "behavior");
        if (!this.isCallbackRegistered) {
            bottomSheetBehavior.addBottomSheetCallback(this.footerCallback);
            this.isCallbackRegistered = true;
        }
    }

    public final void unregisterWithSheetBehavior(BottomSheetBehavior bottomSheetBehavior) {
        Intrinsics.checkNotNullParameter(bottomSheetBehavior, "behavior");
        if (this.isCallbackRegistered) {
            bottomSheetBehavior.removeBottomSheetCallback(this.footerCallback);
            this.isCallbackRegistered = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        BottomSheetBehavior<Screen> sheetBehavior = getSheetBehavior();
        if (sheetBehavior != null) {
            registerWithSheetBehavior(sheetBehavior);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BottomSheetBehavior<Screen> sheetBehavior = getSheetBehavior();
        if (sheetBehavior != null) {
            unregisterWithSheetBehavior(sheetBehavior);
        }
    }

    /* access modifiers changed from: private */
    public final int sheetTopInStableState(int i) {
        BottomSheetBehavior requireSheetBehavior = requireSheetBehavior();
        if (i == 3) {
            return requireSheetBehavior.getExpandedOffset();
        }
        if (i == 4) {
            return this.lastContainerHeight - requireSheetBehavior.getPeekHeight();
        }
        if (i == 5) {
            return this.lastContainerHeight;
        }
        if (i == 6) {
            return (int) (((float) this.lastContainerHeight) * (((float) 1) - requireSheetBehavior.getHalfExpandedRatio()));
        }
        throw new IllegalArgumentException("[RNScreens] use of stable-state method for unstable state");
    }

    /* access modifiers changed from: private */
    public final int sheetTopWhileDragging(float f) {
        Screen screenParent = getScreenParent();
        if (screenParent != null) {
            return screenParent.getTop();
        }
        return (int) MathUtils.lerp((float) sheetTopInStableState(4), (float) sheetTopInStableState(3), f);
    }

    public final void onParentLayout(boolean z, int i, int i2, int i3, int i4, int i5) {
        this.lastContainerHeight = i5;
        layoutFooterOnYAxis$default(this, i5, getReactHeight(), sheetTopInStableState(requireSheetBehavior().getState()), 0, 8, (Object) null);
    }

    public static /* synthetic */ void layoutFooterOnYAxis$default(ScreenFooter screenFooter, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            i4 = 0;
        }
        screenFooter.layoutFooterOnYAxis(i, i2, i3, i4);
    }

    public final void layoutFooterOnYAxis(int i, int i2, int i3, int i4) {
        int max = ((i - i2) - i3) - Math.max(i4, 0);
        int reactHeight = getReactHeight();
        setTop(Math.max(max, 0));
        setBottom(getTop() + reactHeight);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
