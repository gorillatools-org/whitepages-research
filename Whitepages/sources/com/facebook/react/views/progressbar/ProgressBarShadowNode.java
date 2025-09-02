package com.facebook.react.views.progressbar;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ProgressBar;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNode;
import java.util.HashSet;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

public final class ProgressBarShadowNode extends LayoutShadowNode implements YogaMeasureFunction {
    private final SparseIntArray height = new SparseIntArray();
    private final Set<Integer> measured = new HashSet();
    private String style;
    private final SparseIntArray width = new SparseIntArray();

    public ProgressBarShadowNode() {
        setMeasureFunction(this);
        this.style = ReactProgressBarViewManager.DEFAULT_STYLE;
    }

    public final String getStyle() {
        return this.style;
    }

    @ReactProp(name = "styleAttr")
    public final void setStyle(String str) {
        if (str == null) {
            str = ReactProgressBarViewManager.DEFAULT_STYLE;
        }
        this.style = str;
    }

    public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        Intrinsics.checkNotNullParameter(yogaNode, "node");
        Intrinsics.checkNotNullParameter(yogaMeasureMode, "widthMode");
        Intrinsics.checkNotNullParameter(yogaMeasureMode2, "heightMode");
        ReactProgressBarViewManager.Companion companion = ReactProgressBarViewManager.Companion;
        int styleFromString$ReactAndroid_release = companion.getStyleFromString$ReactAndroid_release(this.style);
        if (!this.measured.contains(Integer.valueOf(styleFromString$ReactAndroid_release))) {
            ProgressBar createProgressBar = companion.createProgressBar(getThemedContext(), styleFromString$ReactAndroid_release);
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(-2, 0);
            createProgressBar.measure(makeMeasureSpec, makeMeasureSpec);
            this.height.put(styleFromString$ReactAndroid_release, createProgressBar.getMeasuredHeight());
            this.width.put(styleFromString$ReactAndroid_release, createProgressBar.getMeasuredWidth());
            this.measured.add(Integer.valueOf(styleFromString$ReactAndroid_release));
        }
        return YogaMeasureOutput.make(this.width.get(styleFromString$ReactAndroid_release), this.height.get(styleFromString$ReactAndroid_release));
    }
}
