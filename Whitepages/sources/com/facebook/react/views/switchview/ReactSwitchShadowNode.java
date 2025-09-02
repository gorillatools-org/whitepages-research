package com.facebook.react.views.switchview;

import android.view.View;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNode;
import kotlin.jvm.internal.Intrinsics;

public final class ReactSwitchShadowNode extends LayoutShadowNode implements YogaMeasureFunction {
    private int height;
    private boolean measured;
    private int width;

    public ReactSwitchShadowNode() {
        initMeasureFunction();
    }

    private final void initMeasureFunction() {
        setMeasureFunction(this);
    }

    public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        Intrinsics.checkNotNullParameter(yogaNode, "node");
        Intrinsics.checkNotNullParameter(yogaMeasureMode, "widthMode");
        Intrinsics.checkNotNullParameter(yogaMeasureMode2, "heightMode");
        if (!this.measured) {
            ThemedReactContext themedContext = getThemedContext();
            Intrinsics.checkNotNullExpressionValue(themedContext, "getThemedContext(...)");
            ReactSwitch reactSwitch = new ReactSwitch(themedContext);
            reactSwitch.setShowText(false);
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            reactSwitch.measure(makeMeasureSpec, makeMeasureSpec);
            this.width = reactSwitch.getMeasuredWidth();
            this.height = reactSwitch.getMeasuredHeight();
            this.measured = true;
        }
        return YogaMeasureOutput.make(this.width, this.height);
    }
}
