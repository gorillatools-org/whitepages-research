package com.swmansion.rnscreens;

import android.view.View;
import androidx.customview.widget.ExploreByTouchHelper;
import com.facebook.react.modules.core.ChoreographerCompat;

public final class CustomToolbar$layoutCallback$1 extends ChoreographerCompat.FrameCallback {
    final /* synthetic */ CustomToolbar this$0;

    CustomToolbar$layoutCallback$1(CustomToolbar customToolbar) {
        this.this$0 = customToolbar;
    }

    public void doFrame(long j) {
        this.this$0.isLayoutEnqueued = false;
        CustomToolbar customToolbar = this.this$0;
        customToolbar.measure(View.MeasureSpec.makeMeasureSpec(customToolbar.getWidth(), ExploreByTouchHelper.INVALID_ID), View.MeasureSpec.makeMeasureSpec(this.this$0.getHeight(), ExploreByTouchHelper.INVALID_ID));
        CustomToolbar customToolbar2 = this.this$0;
        customToolbar2.layout(customToolbar2.getLeft(), this.this$0.getTop(), this.this$0.getRight(), this.this$0.getBottom());
    }
}
