package com.swmansion.rnscreens;

import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class ScreenFooter$insetsAnimation$1 extends WindowInsetsAnimationCompat.Callback {
    final /* synthetic */ ScreenFooter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ScreenFooter$insetsAnimation$1(ScreenFooter screenFooter) {
        super(0);
        this.this$0 = screenFooter;
    }

    public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat windowInsetsAnimationCompat, WindowInsetsAnimationCompat.BoundsCompat boundsCompat) {
        Intrinsics.checkNotNullParameter(windowInsetsAnimationCompat, "animation");
        Intrinsics.checkNotNullParameter(boundsCompat, "bounds");
        this.this$0.isAnimationControlledByKeyboard = true;
        WindowInsetsAnimationCompat.BoundsCompat onStart = super.onStart(windowInsetsAnimationCompat, boundsCompat);
        Intrinsics.checkNotNullExpressionValue(onStart, "onStart(...)");
        return onStart;
    }

    public WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat, List list) {
        Intrinsics.checkNotNullParameter(windowInsetsCompat, "insets");
        Intrinsics.checkNotNullParameter(list, "runningAnimations");
        this.this$0.lastBottomInset = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.ime()).bottom - windowInsetsCompat.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
        ScreenFooter screenFooter = this.this$0;
        int access$getLastContainerHeight$p = screenFooter.lastContainerHeight;
        int access$getReactHeight = this.this$0.getReactHeight();
        ScreenFooter screenFooter2 = this.this$0;
        screenFooter.layoutFooterOnYAxis(access$getLastContainerHeight$p, access$getReactHeight, screenFooter2.sheetTopWhileDragging(screenFooter2.lastSlideOffset), this.this$0.lastBottomInset);
        return windowInsetsCompat;
    }

    public void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        Intrinsics.checkNotNullParameter(windowInsetsAnimationCompat, "animation");
        this.this$0.isAnimationControlledByKeyboard = false;
    }
}
