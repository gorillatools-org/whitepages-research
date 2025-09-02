package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

public class GradientColorKeyframeAnimation extends KeyframeAnimation {
    private final GradientColor gradientColor;

    public GradientColorKeyframeAnimation(List list) {
        super(list);
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            GradientColor gradientColor2 = (GradientColor) ((Keyframe) list.get(i2)).startValue;
            if (gradientColor2 != null) {
                i = Math.max(i, gradientColor2.getSize());
            }
        }
        this.gradientColor = new GradientColor(new float[i], new int[i]);
    }

    /* access modifiers changed from: package-private */
    public GradientColor getValue(Keyframe keyframe, float f) {
        this.gradientColor.lerp((GradientColor) keyframe.startValue, (GradientColor) keyframe.endValue, f);
        return this.gradientColor;
    }
}
