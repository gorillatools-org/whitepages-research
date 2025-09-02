package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

abstract class ViewOffsetBehavior extends CoordinatorLayout.Behavior {
    private int tempLeftRightOffset = 0;
    private int tempTopBottomOffset = 0;
    private ViewOffsetHelper viewOffsetHelper;

    public ViewOffsetBehavior() {
    }

    public ViewOffsetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        layoutChild(coordinatorLayout, view, i);
        if (this.viewOffsetHelper == null) {
            this.viewOffsetHelper = new ViewOffsetHelper(view);
        }
        this.viewOffsetHelper.onViewLayout();
        this.viewOffsetHelper.applyOffsets();
        int i2 = this.tempTopBottomOffset;
        if (i2 != 0) {
            this.viewOffsetHelper.setTopAndBottomOffset(i2);
            this.tempTopBottomOffset = 0;
        }
        int i3 = this.tempLeftRightOffset;
        if (i3 == 0) {
            return true;
        }
        this.viewOffsetHelper.setLeftAndRightOffset(i3);
        this.tempLeftRightOffset = 0;
        return true;
    }

    /* access modifiers changed from: protected */
    public void layoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        coordinatorLayout.onLayoutChild(view, i);
    }

    public boolean setTopAndBottomOffset(int i) {
        ViewOffsetHelper viewOffsetHelper2 = this.viewOffsetHelper;
        if (viewOffsetHelper2 != null) {
            return viewOffsetHelper2.setTopAndBottomOffset(i);
        }
        this.tempTopBottomOffset = i;
        return false;
    }

    public int getTopAndBottomOffset() {
        ViewOffsetHelper viewOffsetHelper2 = this.viewOffsetHelper;
        if (viewOffsetHelper2 != null) {
            return viewOffsetHelper2.getTopAndBottomOffset();
        }
        return 0;
    }
}
