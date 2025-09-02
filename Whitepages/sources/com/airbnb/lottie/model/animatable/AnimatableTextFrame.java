package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation;
import java.util.List;

public class AnimatableTextFrame extends BaseAnimatableValue {
    public /* bridge */ /* synthetic */ List getKeyframes() {
        return super.getKeyframes();
    }

    public /* bridge */ /* synthetic */ boolean isStatic() {
        return super.isStatic();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public AnimatableTextFrame(List list) {
        super(list);
    }

    public TextKeyframeAnimation createAnimation() {
        return new TextKeyframeAnimation(this.keyframes);
    }
}
