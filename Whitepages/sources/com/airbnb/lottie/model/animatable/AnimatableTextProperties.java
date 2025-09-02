package com.airbnb.lottie.model.animatable;

public class AnimatableTextProperties {
    public final AnimatableTextRangeSelector rangeSelector;
    public final AnimatableTextStyle textStyle;

    public AnimatableTextProperties(AnimatableTextStyle animatableTextStyle, AnimatableTextRangeSelector animatableTextRangeSelector) {
        this.textStyle = animatableTextStyle;
        this.rangeSelector = animatableTextRangeSelector;
    }
}
