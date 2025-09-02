package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import java.util.List;

public class AnimatableFloatValue extends BaseAnimatableValue {
    public /* bridge */ /* synthetic */ List getKeyframes() {
        return super.getKeyframes();
    }

    public /* bridge */ /* synthetic */ boolean isStatic() {
        return super.isStatic();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public AnimatableFloatValue(List list) {
        super(list);
    }

    public FloatKeyframeAnimation createAnimation() {
        return new FloatKeyframeAnimation(this.keyframes);
    }
}
