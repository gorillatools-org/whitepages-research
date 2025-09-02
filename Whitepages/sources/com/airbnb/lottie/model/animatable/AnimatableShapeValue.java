package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.animation.keyframe.ShapeKeyframeAnimation;
import java.util.List;

public class AnimatableShapeValue extends BaseAnimatableValue {
    public /* bridge */ /* synthetic */ List getKeyframes() {
        return super.getKeyframes();
    }

    public /* bridge */ /* synthetic */ boolean isStatic() {
        return super.isStatic();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public AnimatableShapeValue(List list) {
        super(list);
    }

    public ShapeKeyframeAnimation createAnimation() {
        return new ShapeKeyframeAnimation(this.keyframes);
    }
}
