package com.swmansion.rnscreens.bottomsheet;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.swmansion.rnscreens.Screen;
import com.swmansion.rnscreens.ScreenStackFragment;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

public final class DimmingViewManager {
    private final DimmingView dimmingView;
    private BottomSheetBehavior.BottomSheetCallback dimmingViewCallback;
    private final float maxAlpha = 0.3f;
    private final ThemedReactContext reactContext;

    public DimmingViewManager(ThemedReactContext themedReactContext, Screen screen) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        Intrinsics.checkNotNullParameter(screen, "screen");
        this.reactContext = themedReactContext;
        this.dimmingView = createDimmingView(screen);
    }

    public final DimmingView getDimmingView$react_native_screens_release() {
        return this.dimmingView;
    }

    public final float getMaxAlpha$react_native_screens_release() {
        return this.maxAlpha;
    }

    public final void onViewHierarchyCreated(Screen screen, ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        Intrinsics.checkNotNullParameter(viewGroup, "root");
        viewGroup.addView(this.dimmingView, 0);
        if (!willDimForDetentIndex(screen, screen.getSheetInitialDetentIndex())) {
            this.dimmingView.setAlpha(0.0f);
        } else {
            this.dimmingView.setAlpha(this.maxAlpha);
        }
    }

    public final void onBehaviourAttached(Screen screen, BottomSheetBehavior bottomSheetBehavior) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        Intrinsics.checkNotNullParameter(bottomSheetBehavior, "behavior");
        bottomSheetBehavior.addBottomSheetCallback(requireBottomSheetCallback(screen, true));
    }

    public final boolean willDimForDetentIndex(Screen screen, int i) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        return i > screen.getSheetLargestUndimmedDetentIndex();
    }

    public final void invalidate(BottomSheetBehavior bottomSheetBehavior) {
        BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = this.dimmingViewCallback;
        if (bottomSheetCallback != null && bottomSheetBehavior != null) {
            bottomSheetBehavior.removeBottomSheetCallback(bottomSheetCallback);
        }
    }

    private static final class AnimateDimmingViewCallback extends BottomSheetBehavior.BottomSheetCallback {
        private final ValueAnimator animator;
        private float firstDimmedOffset;
        private float intervalLength;
        private float largestUndimmedOffset;
        private final float maxAlpha;
        private final Screen screen;
        private final View viewToAnimate;

        public AnimateDimmingViewCallback(Screen screen2, View view, float f) {
            Intrinsics.checkNotNullParameter(screen2, "screen");
            Intrinsics.checkNotNullParameter(view, "viewToAnimate");
            this.screen = screen2;
            this.viewToAnimate = view;
            this.maxAlpha = f;
            this.largestUndimmedOffset = computeOffsetFromDetentIndex(screen2.getSheetLargestUndimmedDetentIndex());
            float computeOffsetFromDetentIndex = computeOffsetFromDetentIndex(RangesKt.coerceIn(screen2.getSheetLargestUndimmedDetentIndex() + 1, 0, screen2.getSheetDetents().size() - 1));
            this.firstDimmedOffset = computeOffsetFromDetentIndex;
            this.intervalLength = computeOffsetFromDetentIndex - this.largestUndimmedOffset;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, f});
            ofFloat.setDuration(1);
            ofFloat.addUpdateListener(new DimmingViewManager$AnimateDimmingViewCallback$$ExternalSyntheticLambda0(this));
            this.animator = ofFloat;
        }

        /* access modifiers changed from: private */
        public static final void animator$lambda$1$lambda$0(AnimateDimmingViewCallback animateDimmingViewCallback, ValueAnimator valueAnimator) {
            Intrinsics.checkNotNullParameter(valueAnimator, "it");
            View view = animateDimmingViewCallback.viewToAnimate;
            Object animatedValue = valueAnimator.getAnimatedValue();
            Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
            view.setAlpha(((Float) animatedValue).floatValue());
        }

        public void onStateChanged(View view, int i) {
            Intrinsics.checkNotNullParameter(view, "bottomSheet");
            if (i == 1 || i == 2) {
                this.largestUndimmedOffset = computeOffsetFromDetentIndex(this.screen.getSheetLargestUndimmedDetentIndex());
                float computeOffsetFromDetentIndex = computeOffsetFromDetentIndex(RangesKt.coerceIn(this.screen.getSheetLargestUndimmedDetentIndex() + 1, 0, this.screen.getSheetDetents().size() - 1));
                this.firstDimmedOffset = computeOffsetFromDetentIndex;
                this.intervalLength = computeOffsetFromDetentIndex - this.largestUndimmedOffset;
            }
        }

        public void onSlide(View view, float f) {
            Intrinsics.checkNotNullParameter(view, "bottomSheet");
            float f2 = this.largestUndimmedOffset;
            if (f2 < f && f < this.firstDimmedOffset) {
                this.animator.setCurrentFraction((f - f2) / this.intervalLength);
            }
        }

        private final float computeOffsetFromDetentIndex(int i) {
            int size = this.screen.getSheetDetents().size();
            if (size != 1) {
                if (size != 2) {
                    if (size == 3 && i != -1) {
                        if (i != 0) {
                            if (i == 1) {
                                BottomSheetBehavior<Screen> sheetBehavior = this.screen.getSheetBehavior();
                                Intrinsics.checkNotNull(sheetBehavior);
                                return sheetBehavior.getHalfExpandedRatio();
                            } else if (i == 2) {
                                return 1.0f;
                            }
                        }
                    }
                } else if (i != -1) {
                    if (i != 0) {
                        if (i == 1) {
                            return 1.0f;
                        }
                    }
                }
                return 0.0f;
            } else if (i != -1 && i == 0) {
                return 1.0f;
            }
            return -1.0f;
        }
    }

    private final DimmingView createDimmingView(Screen screen) {
        DimmingView dimmingView2 = new DimmingView(this.reactContext, this.maxAlpha);
        dimmingView2.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        dimmingView2.setOnClickListener(new DimmingViewManager$$ExternalSyntheticLambda0(screen));
        return dimmingView2;
    }

    /* access modifiers changed from: private */
    public static final void createDimmingView$lambda$2$lambda$1(Screen screen, View view) {
        if (screen.getSheetClosesOnTouchOutside()) {
            Fragment fragment = screen.getFragment();
            Intrinsics.checkNotNull(fragment, "null cannot be cast to non-null type com.swmansion.rnscreens.ScreenStackFragment");
            ((ScreenStackFragment) fragment).dismissSelf$react_native_screens_release();
        }
    }

    private final BottomSheetBehavior.BottomSheetCallback requireBottomSheetCallback(Screen screen, boolean z) {
        if (this.dimmingViewCallback == null || z) {
            this.dimmingViewCallback = new AnimateDimmingViewCallback(screen, this.dimmingView, this.maxAlpha);
        }
        BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = this.dimmingViewCallback;
        Intrinsics.checkNotNull(bottomSheetCallback);
        return bottomSheetCallback;
    }
}
