package com.reactnativecommunity.blurview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.uimanager.ThemedReactContext;
import eightbitlab.com.blurview.BlurView;
import java.util.Objects;

abstract class BlurViewManagerImpl {
    public static BlurView createViewInstance(ThemedReactContext themedReactContext) {
        BlurView blurView = new BlurView(themedReactContext);
        Activity currentActivity = themedReactContext.getCurrentActivity();
        Objects.requireNonNull(currentActivity);
        View decorView = currentActivity.getWindow().getDecorView();
        blurView.setupWith((ViewGroup) decorView.findViewById(16908290)).setFrameClearDrawable(decorView.getBackground()).setBlurRadius(10.0f);
        return blurView;
    }

    public static void setRadius(BlurView blurView, int i) {
        blurView.setBlurRadius((float) i);
        blurView.invalidate();
    }

    public static void setColor(BlurView blurView, int i) {
        blurView.setOverlayColor(i);
        blurView.invalidate();
    }

    public static void setAutoUpdate(BlurView blurView, boolean z) {
        blurView.setBlurAutoUpdate(z);
        blurView.invalidate();
    }

    public static void setBlurEnabled(BlurView blurView, boolean z) {
        blurView.setBlurEnabled(z);
    }
}
