package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class FloatKeyframeAnimation extends KeyframeAnimation {
    public FloatKeyframeAnimation(List list) {
        super(list);
    }

    /* access modifiers changed from: package-private */
    public Float getValue(Keyframe keyframe, float f) {
        return Float.valueOf(getFloatValue(keyframe, f));
    }

    /* access modifiers changed from: package-private */
    public float getFloatValue(Keyframe keyframe, float f) {
        if (keyframe.startValue == null || keyframe.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        LottieValueCallback lottieValueCallback = this.valueCallback;
        if (lottieValueCallback != null) {
            Float f2 = (Float) lottieValueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), (Float) keyframe.startValue, (Float) keyframe.endValue, f, getLinearCurrentKeyframeProgress(), getProgress());
            if (f2 != null) {
                return f2.floatValue();
            }
        }
        return MiscUtils.lerp(keyframe.getStartValueFloat(), keyframe.getEndValueFloat(), f);
    }

    public float getFloatValue() {
        return getFloatValue(getCurrentKeyframe(), getInterpolatedCurrentKeyframeProgress());
    }
}
