package com.swmansion.rnscreens;

import android.view.View;
import com.facebook.react.modules.core.ChoreographerCompat;

public final class ScreenContainer$layoutCallback$1 extends ChoreographerCompat.FrameCallback {
    final /* synthetic */ ScreenContainer this$0;

    ScreenContainer$layoutCallback$1(ScreenContainer screenContainer) {
        this.this$0 = screenContainer;
    }

    public void doFrame(long j) {
        this.this$0.isLayoutEnqueued = false;
        ScreenContainer screenContainer = this.this$0;
        screenContainer.measure(View.MeasureSpec.makeMeasureSpec(screenContainer.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.this$0.getHeight(), 1073741824));
        ScreenContainer screenContainer2 = this.this$0;
        screenContainer2.layout(screenContainer2.getLeft(), this.this$0.getTop(), this.this$0.getRight(), this.this$0.getBottom());
    }
}
