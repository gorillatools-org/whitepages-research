package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class IntegerKeyframeAnimation extends KeyframeAnimation {
    public IntegerKeyframeAnimation(List list) {
        super(list);
    }

    /* access modifiers changed from: package-private */
    public Integer getValue(Keyframe keyframe, float f) {
        return Integer.valueOf(getIntValue(keyframe, f));
    }

    /* access modifiers changed from: package-private */
    public int getIntValue(Keyframe keyframe, float f) {
        if (keyframe.startValue != null) {
            int startValueInt = keyframe.endValue == null ? keyframe.getStartValueInt() : keyframe.getEndValueInt();
            LottieValueCallback lottieValueCallback = this.valueCallback;
            if (lottieValueCallback != null) {
                Integer num = (Integer) lottieValueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), (Integer) keyframe.startValue, Integer.valueOf(startValueInt), f, getLinearCurrentKeyframeProgress(), getProgress());
                if (num != null) {
                    return num.intValue();
                }
            }
            return MiscUtils.lerp(keyframe.getStartValueInt(), startValueInt, f);
        }
        throw new IllegalStateException("Missing values for keyframe.");
    }

    public int getIntValue() {
        return getIntValue(getCurrentKeyframe(), getInterpolatedCurrentKeyframeProgress());
    }
}
