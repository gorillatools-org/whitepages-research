package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.IntegerKeyframeAnimation;
import java.util.List;

public class AnimatableIntegerValue extends BaseAnimatableValue {
    public /* bridge */ /* synthetic */ List getKeyframes() {
        return super.getKeyframes();
    }

    public /* bridge */ /* synthetic */ boolean isStatic() {
        return super.isStatic();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public AnimatableIntegerValue(List list) {
        super(list);
    }

    public BaseKeyframeAnimation createAnimation() {
        return new IntegerKeyframeAnimation(this.keyframes);
    }
}
