package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.Arrays;
import java.util.List;

public class ShapeGroup implements ContentModel {
    private final boolean hidden;
    private final List items;
    private final String name;

    public ShapeGroup(String str, List list, boolean z) {
        this.name = str;
        this.items = list;
        this.hidden = z;
    }

    public String getName() {
        return this.name;
    }

    public List getItems() {
        return this.items;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public Content toContent(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new ContentGroup(lottieDrawable, baseLayer, this, lottieComposition);
    }

    public String toString() {
        return "ShapeGroup{name='" + this.name + "' Shapes: " + Arrays.toString(this.items.toArray()) + '}';
    }
}
