package com.swmansion.rnscreens;

import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.swmansion.rnscreens.bottomsheet.SheetUtils;
import kotlin.jvm.internal.Intrinsics;

public final class ScreenFooter$footerCallback$1 extends BottomSheetBehavior.BottomSheetCallback {
    final /* synthetic */ ScreenFooter this$0;

    ScreenFooter$footerCallback$1(ScreenFooter screenFooter) {
        this.this$0 = screenFooter;
    }

    public void onStateChanged(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "bottomSheet");
        if (SheetUtils.INSTANCE.isStateStable(i)) {
            if (i == 3 || i == 4 || i == 6) {
                ScreenFooter screenFooter = this.this$0;
                screenFooter.layoutFooterOnYAxis(screenFooter.lastContainerHeight, this.this$0.getReactHeight(), this.this$0.sheetTopInStableState(i), this.this$0.lastBottomInset);
            }
            this.this$0.lastStableSheetState = i;
        }
    }

    public void onSlide(View view, float f) {
        Intrinsics.checkNotNullParameter(view, "bottomSheet");
        this.this$0.lastSlideOffset = Math.max(f, 0.0f);
        if (!this.this$0.isAnimationControlledByKeyboard) {
            ScreenFooter screenFooter = this.this$0;
            int access$getLastContainerHeight$p = screenFooter.lastContainerHeight;
            int access$getReactHeight = this.this$0.getReactHeight();
            ScreenFooter screenFooter2 = this.this$0;
            screenFooter.layoutFooterOnYAxis(access$getLastContainerHeight$p, access$getReactHeight, screenFooter2.sheetTopWhileDragging(screenFooter2.lastSlideOffset), this.this$0.lastBottomInset);
        }
    }
}
