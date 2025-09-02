package com.google.android.material.bottomappbar;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$style;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import java.lang.ref.WeakReference;

public abstract class BottomAppBar extends Toolbar implements CoordinatorLayout.AttachedBehavior {
    private static final int DEF_STYLE_RES = R$style.Widget_MaterialComponents_BottomAppBar;

    static /* synthetic */ void access$1300(BottomAppBar bottomAppBar) {
        throw null;
    }

    static /* synthetic */ View access$3100(BottomAppBar bottomAppBar) {
        throw null;
    }

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {
        private final Rect fabContentRect = new Rect();
        private final View.OnLayoutChangeListener fabLayoutListener = new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(Behavior.this.viewRef.get());
                view.removeOnLayoutChangeListener(this);
            }
        };
        private int originalBottomMargin;
        /* access modifiers changed from: private */
        public WeakReference viewRef;

        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(view);
            return onLayoutChild(coordinatorLayout, (BottomAppBar) null, i);
        }

        public /* bridge */ /* synthetic */ boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(view);
            return onStartNestedScroll(coordinatorLayout, (BottomAppBar) null, view2, view3, i, i2);
        }

        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, int i) {
            this.viewRef = new WeakReference(bottomAppBar);
            View access$3100 = BottomAppBar.access$3100(bottomAppBar);
            if (access$3100 != null && !ViewCompat.isLaidOut(access$3100)) {
                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) access$3100.getLayoutParams();
                layoutParams.anchorGravity = 49;
                this.originalBottomMargin = layoutParams.bottomMargin;
                BottomAppBar.access$1300(bottomAppBar);
            }
            coordinatorLayout.onLayoutChild(bottomAppBar, i);
            return super.onLayoutChild(coordinatorLayout, bottomAppBar, i);
        }

        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, View view, View view2, int i, int i2) {
            throw null;
        }
    }
}
