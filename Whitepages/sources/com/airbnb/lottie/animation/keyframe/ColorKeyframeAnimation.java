package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class ColorKeyframeAnimation extends KeyframeAnimation {
    public ColorKeyframeAnimation(List list) {
        super(list);
    }

    /* access modifiers changed from: package-private */
    public Integer getValue(Keyframe keyframe, float f) {
        return Integer.valueOf(getIntValue(keyframe, f));
    }

    public int getIntValue(Keyframe keyframe, float f) {
        Float f2;
        if (keyframe.startValue == null || keyframe.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        LottieValueCallback lottieValueCallback = this.valueCallback;
        if (!(lottieValueCallback == null || (f2 = keyframe.endFrame) == null)) {
            Integer num = (Integer) lottieValueCallback.getValueInternal(keyframe.startFrame, f2.floatValue(), (Integer) keyframe.startValue, (Integer) keyframe.endValue, f, getLinearCurrentKeyframeProgress(), getProgress());
            if (num != null) {
                return num.intValue();
            }
        }
        return GammaEvaluator.evaluate(MiscUtils.clamp(f, 0.0f, 1.0f), ((Integer) keyframe.startValue).intValue(), ((Integer) keyframe.endValue).intValue());
    }

    public int getIntValue() {
        return getIntValue(getCurrentKeyframe(), getInterpolatedCurrentKeyframeProgress());
    }
}
