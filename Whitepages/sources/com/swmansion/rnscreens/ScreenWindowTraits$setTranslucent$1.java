package com.swmansion.rnscreens;

import android.app.Activity;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.JSExceptionHandler;
import kotlin.jvm.internal.Intrinsics;

public final class ScreenWindowTraits$setTranslucent$1 extends GuardedRunnable {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ boolean $translucent;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ScreenWindowTraits$setTranslucent$1(Activity activity, boolean z, JSExceptionHandler jSExceptionHandler) {
        super(jSExceptionHandler);
        this.$activity = activity;
        this.$translucent = z;
    }

    public void runGuarded() {
        View decorView = this.$activity.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
        if (this.$translucent) {
            InsetsObserverProxy insetsObserverProxy = InsetsObserverProxy.INSTANCE;
            insetsObserverProxy.registerOnView(decorView);
            insetsObserverProxy.addOnApplyWindowInsetsListener(ScreenWindowTraits.windowInsetsListener);
        } else {
            InsetsObserverProxy.INSTANCE.removeOnApplyWindowInsetsListener(ScreenWindowTraits.windowInsetsListener);
        }
        ViewCompat.requestApplyInsets(decorView);
    }
}
