package com.swmansion.rnscreens;

import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.JSExceptionHandler;
import com.facebook.react.uimanager.UIManagerModule;

public final class Screen$updateScreenSizePaper$1 extends GuardedRunnable {
    final /* synthetic */ int $height;
    final /* synthetic */ int $width;
    final /* synthetic */ Screen this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Screen$updateScreenSizePaper$1(Screen screen, int i, int i2, JSExceptionHandler jSExceptionHandler) {
        super(jSExceptionHandler);
        this.this$0 = screen;
        this.$width = i;
        this.$height = i2;
    }

    public void runGuarded() {
        UIManagerModule uIManagerModule = (UIManagerModule) this.this$0.getReactContext().getNativeModule(UIManagerModule.class);
        if (uIManagerModule != null) {
            uIManagerModule.updateNodeSize(this.this$0.getId(), this.$width, this.$height);
        }
    }
}
