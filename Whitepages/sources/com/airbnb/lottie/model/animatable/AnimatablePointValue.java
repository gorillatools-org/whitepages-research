package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.PointKeyframeAnimation;
import java.util.List;

public class AnimatablePointValue extends BaseAnimatableValue {
    public /* bridge */ /* synthetic */ List getKeyframes() {
        return super.getKeyframes();
    }

    public /* bridge */ /* synthetic */ boolean isStatic() {
        return super.isStatic();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public AnimatablePointValue(List list) {
        super(list);
    }

    public BaseKeyframeAnimation createAnimation() {
        return new PointKeyframeAnimation(this.keyframes);
    }
}
