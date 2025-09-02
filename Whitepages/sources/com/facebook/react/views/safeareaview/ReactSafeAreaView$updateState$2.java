package com.facebook.react.views.safeareaview;

import androidx.core.graphics.Insets;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;

public final class ReactSafeAreaView$updateState$2 extends GuardedRunnable {
    final /* synthetic */ Insets $insets;
    final /* synthetic */ ReactSafeAreaView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ReactSafeAreaView$updateState$2(ReactSafeAreaView reactSafeAreaView, Insets insets, ThemedReactContext themedReactContext) {
        super((ReactContext) themedReactContext);
        this.this$0 = reactSafeAreaView;
        this.$insets = insets;
    }

    public void runGuarded() {
        UIManagerModule uIManagerModule = (UIManagerModule) this.this$0.getReactContext().getReactApplicationContext().getNativeModule(UIManagerModule.class);
        if (uIManagerModule != null) {
            int id = this.this$0.getId();
            Insets insets = this.$insets;
            uIManagerModule.updateInsetsPadding(id, insets.top, insets.left, insets.bottom, insets.right);
        }
    }
}
