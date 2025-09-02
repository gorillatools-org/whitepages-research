package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.RoundedCornersContent;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;

public class RoundedCorners implements ContentModel {
    private final AnimatableValue cornerRadius;
    private final String name;

    public RoundedCorners(String str, AnimatableValue animatableValue) {
        this.name = str;
        this.cornerRadius = animatableValue;
    }

    public String getName() {
        return this.name;
    }

    public AnimatableValue getCornerRadius() {
        return this.cornerRadius;
    }

    public Content toContent(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new RoundedCornersContent(lottieDrawable, baseLayer, this);
    }
}
