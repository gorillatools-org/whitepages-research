package com.facebook.react.views.modal;

import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.modal.ReactModalHostView;

public final class ReactModalHostView$DialogRootViewGroup$updateState$2$1 extends GuardedRunnable {
    final /* synthetic */ ReactModalHostView.DialogRootViewGroup $this_run;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ReactModalHostView$DialogRootViewGroup$updateState$2$1(ReactModalHostView.DialogRootViewGroup dialogRootViewGroup, ThemedReactContext themedReactContext) {
        super((ReactContext) themedReactContext);
        this.$this_run = dialogRootViewGroup;
    }

    public void runGuarded() {
        UIManagerModule uIManagerModule = (UIManagerModule) this.$this_run.getReactContext().getReactApplicationContext().getNativeModule(UIManagerModule.class);
        if (uIManagerModule != null) {
            uIManagerModule.updateNodeSize(this.$this_run.getId(), this.$this_run.viewWidth, this.$this_run.viewHeight);
        }
    }
}
