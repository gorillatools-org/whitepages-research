package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.value.Keyframe;
import java.util.Arrays;
import java.util.List;

abstract class BaseAnimatableValue implements AnimatableValue {
    final List keyframes;

    BaseAnimatableValue(List list) {
        this.keyframes = list;
    }

    public List getKeyframes() {
        return this.keyframes;
    }

    public boolean isStatic() {
        if (!this.keyframes.isEmpty()) {
            return this.keyframes.size() == 1 && ((Keyframe) this.keyframes.get(0)).isStatic();
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.keyframes.isEmpty()) {
            sb.append("values=");
            sb.append(Arrays.toString(this.keyframes.toArray()));
        }
        return sb.toString();
    }
}
