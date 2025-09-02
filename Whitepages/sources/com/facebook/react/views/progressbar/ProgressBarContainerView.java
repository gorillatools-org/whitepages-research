package com.facebook.react.views.progressbar;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import app.notifee.core.event.LogEvent;
import com.facebook.react.R;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ProgressBarContainerView extends FrameLayout {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @Deprecated
    public static final int MAX_PROGRESS = 1000;
    private boolean animating = true;
    private Integer color;
    private boolean indeterminate = true;
    private double progress;
    private ProgressBar progressBar;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ProgressBarContainerView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final Integer getColor$ReactAndroid_release() {
        return this.color;
    }

    public final void setColor$ReactAndroid_release(Integer num) {
        this.color = num;
    }

    public final boolean getIndeterminate$ReactAndroid_release() {
        return this.indeterminate;
    }

    public final void setIndeterminate$ReactAndroid_release(boolean z) {
        this.indeterminate = z;
    }

    public final boolean getAnimating$ReactAndroid_release() {
        return this.animating;
    }

    public final void setAnimating$ReactAndroid_release(boolean z) {
        this.animating = z;
    }

    public final double getProgress$ReactAndroid_release() {
        return this.progress;
    }

    public final void setProgress$ReactAndroid_release(double d) {
        this.progress = d;
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        Intrinsics.checkNotNullParameter(accessibilityNodeInfo, LogEvent.LEVEL_INFO);
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        String str = (String) getTag(R.id.react_test_id);
        if (str != null) {
            accessibilityNodeInfo.setViewIdResourceName(str);
        }
    }

    public final void apply$ReactAndroid_release() {
        ProgressBar progressBar2 = this.progressBar;
        if (progressBar2 != null) {
            progressBar2.setIndeterminate(this.indeterminate);
            setColor(progressBar2);
            progressBar2.setProgress((int) (this.progress * ((double) 1000)));
            progressBar2.setVisibility(this.animating ? 0 : 4);
            return;
        }
        throw new JSApplicationIllegalArgumentException("setStyle() not called");
    }

    public final void setStyle$ReactAndroid_release(String str) {
        ReactProgressBarViewManager.Companion companion = ReactProgressBarViewManager.Companion;
        ProgressBar createProgressBar = companion.createProgressBar(getContext(), companion.getStyleFromString$ReactAndroid_release(str));
        createProgressBar.setMax(1000);
        this.progressBar = createProgressBar;
        removeAllViews();
        addView(this.progressBar, new ViewGroup.LayoutParams(-1, -1));
    }

    private final void setColor(ProgressBar progressBar2) {
        Drawable drawable;
        if (progressBar2.isIndeterminate()) {
            drawable = progressBar2.getIndeterminateDrawable();
        } else {
            drawable = progressBar2.getProgressDrawable();
        }
        if (drawable != null) {
            Integer num = this.color;
            if (num != null) {
                drawable.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
            } else {
                drawable.clearColorFilter();
            }
        }
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
