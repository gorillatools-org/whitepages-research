package com.facebook.react.modules.statusbar;

import android.app.Activity;
import android.view.Window;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.views.view.WindowUtilKt;

public final class StatusBarModule$setTranslucent$1 extends GuardedRunnable {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ boolean $translucent;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StatusBarModule$setTranslucent$1(Activity activity, boolean z, ReactApplicationContext reactApplicationContext) {
        super((ReactContext) reactApplicationContext);
        this.$activity = activity;
        this.$translucent = z;
    }

    public void runGuarded() {
        Window window = this.$activity.getWindow();
        if (window != null) {
            WindowUtilKt.setStatusBarTranslucency(window, this.$translucent);
        }
    }
}
