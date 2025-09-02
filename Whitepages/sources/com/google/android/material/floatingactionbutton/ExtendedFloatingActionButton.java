package com.google.android.material.floatingactionbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.DescendantOffsetUtils;
import java.util.List;

public abstract class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.AttachedBehavior {
    private static final int DEF_STYLE_RES = R$style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    static final Property HEIGHT;
    static final Property PADDING_END;
    static final Property PADDING_START;
    static final Property WIDTH;

    public static abstract class OnChangedCallback {
    }

    static /* synthetic */ MotionStrategy access$200(ExtendedFloatingActionButton extendedFloatingActionButton) {
        throw null;
    }

    static /* synthetic */ MotionStrategy access$300(ExtendedFloatingActionButton extendedFloatingActionButton) {
        throw null;
    }

    static /* synthetic */ void access$400(ExtendedFloatingActionButton extendedFloatingActionButton, MotionStrategy motionStrategy, OnChangedCallback onChangedCallback) {
        throw null;
    }

    static /* synthetic */ MotionStrategy access$500(ExtendedFloatingActionButton extendedFloatingActionButton) {
        throw null;
    }

    static /* synthetic */ MotionStrategy access$600(ExtendedFloatingActionButton extendedFloatingActionButton) {
        throw null;
    }

    static {
        Class<Float> cls = Float.class;
        WIDTH = new Property(cls, "width") {
            public void set(View view, Float f) {
                view.getLayoutParams().width = f.intValue();
                view.requestLayout();
            }

            public Float get(View view) {
                return Float.valueOf((float) view.getLayoutParams().width);
            }
        };
        HEIGHT = new Property(cls, "height") {
            public void set(View view, Float f) {
                view.getLayoutParams().height = f.intValue();
                view.requestLayout();
            }

            public Float get(View view) {
                return Float.valueOf((float) view.getLayoutParams().height);
            }
        };
        PADDING_START = new Property(cls, ViewProps.PADDING_START) {
            public void set(View view, Float f) {
                ViewCompat.setPaddingRelative(view, f.intValue(), view.getPaddingTop(), ViewCompat.getPaddingEnd(view), view.getPaddingBottom());
            }

            public Float get(View view) {
                return Float.valueOf((float) ViewCompat.getPaddingStart(view));
            }
        };
        PADDING_END = new Property(cls, ViewProps.PADDING_END) {
            public void set(View view, Float f) {
                ViewCompat.setPaddingRelative(view, ViewCompat.getPaddingStart(view), view.getPaddingTop(), f.intValue(), view.getPaddingBottom());
            }

            public Float get(View view) {
                return Float.valueOf((float) ViewCompat.getPaddingEnd(view));
            }
        };
    }

    protected static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.Behavior {
        private boolean autoHideEnabled;
        private boolean autoShrinkEnabled;
        private Rect tmpRect;

        public /* bridge */ /* synthetic */ boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, View view, Rect rect) {
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(view);
            return getInsetDodgeRect(coordinatorLayout, (ExtendedFloatingActionButton) null, rect);
        }

        public /* bridge */ /* synthetic */ boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(view);
            return onDependentViewChanged(coordinatorLayout, (ExtendedFloatingActionButton) null, view2);
        }

        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(view);
            return onLayoutChild(coordinatorLayout, (ExtendedFloatingActionButton) null, i);
        }

        public ExtendedFloatingActionButtonBehavior() {
            this.autoHideEnabled = false;
            this.autoShrinkEnabled = true;
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = obtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.autoShrinkEnabled = obtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            obtainStyledAttributes.recycle();
        }

        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, Rect rect) {
            return super.getInsetDodgeRect(coordinatorLayout, extendedFloatingActionButton, rect);
        }

        public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
            if (layoutParams.dodgeInsetEdges == 0) {
                layoutParams.dodgeInsetEdges = 80;
            }
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton);
                return false;
            } else if (!isBottomSheet(view)) {
                return false;
            } else {
                updateFabVisibilityForBottomSheet(view, extendedFloatingActionButton);
                return false;
            }
        }

        private static boolean isBottomSheet(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                return ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private boolean shouldUpdateVisibility(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            throw null;
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!shouldUpdateVisibility(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                shrinkOrHide(extendedFloatingActionButton);
                return true;
            }
            extendOrShow(extendedFloatingActionButton);
            return true;
        }

        private boolean updateFabVisibilityForBottomSheet(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!shouldUpdateVisibility(view, extendedFloatingActionButton)) {
                return false;
            }
            throw null;
        }

        /* access modifiers changed from: protected */
        public void shrinkOrHide(ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (this.autoShrinkEnabled) {
                ExtendedFloatingActionButton.access$200(extendedFloatingActionButton);
            } else {
                ExtendedFloatingActionButton.access$300(extendedFloatingActionButton);
            }
            ExtendedFloatingActionButton.access$400(extendedFloatingActionButton, (MotionStrategy) null, (OnChangedCallback) null);
        }

        /* access modifiers changed from: protected */
        public void extendOrShow(ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (this.autoShrinkEnabled) {
                ExtendedFloatingActionButton.access$500(extendedFloatingActionButton);
            } else {
                ExtendedFloatingActionButton.access$600(extendedFloatingActionButton);
            }
            ExtendedFloatingActionButton.access$400(extendedFloatingActionButton, (MotionStrategy) null, (OnChangedCallback) null);
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, int i) {
            List dependencies = coordinatorLayout.getDependencies(extendedFloatingActionButton);
            int size = dependencies.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = (View) dependencies.get(i2);
                if (!(view instanceof AppBarLayout)) {
                    if (isBottomSheet(view) && updateFabVisibilityForBottomSheet(view, extendedFloatingActionButton)) {
                        break;
                    }
                } else if (updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton)) {
                    break;
                }
            }
            coordinatorLayout.onLayoutChild(extendedFloatingActionButton, i);
            return true;
        }
    }
}
