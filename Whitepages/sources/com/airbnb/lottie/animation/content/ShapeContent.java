package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ShapeKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.ShapePath;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

public class ShapeContent implements PathContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    private final boolean hidden;
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final Path path = new Path();
    private final ShapeKeyframeAnimation shapeAnimation;
    private final CompoundTrimPathContent trimPaths = new CompoundTrimPathContent();

    public ShapeContent(LottieDrawable lottieDrawable2, BaseLayer baseLayer, ShapePath shapePath) {
        this.name = shapePath.getName();
        this.hidden = shapePath.isHidden();
        this.lottieDrawable = lottieDrawable2;
        ShapeKeyframeAnimation createAnimation = shapePath.getShapePath().createAnimation();
        this.shapeAnimation = createAnimation;
        baseLayer.addAnimation(createAnimation);
        createAnimation.addUpdateListener(this);
    }

    public void onValueChanged() {
        invalidate();
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    public void setContents(List list, List list2) {
        ArrayList arrayList = null;
        for (int i = 0; i < list.size(); i++) {
            Content content = (Content) list.get(i);
            if (content instanceof TrimPathContent) {
                TrimPathContent trimPathContent = (TrimPathContent) content;
                if (trimPathContent.getType() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                    this.trimPaths.addTrimPath(trimPathContent);
                    trimPathContent.addListener(this);
                }
            }
            if (content instanceof ShapeModifierContent) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add((ShapeModifierContent) content);
            }
        }
        this.shapeAnimation.setShapeModifiers(arrayList);
    }

    public Path getPath() {
        if (this.isPathValid && !this.shapeAnimation.hasValueCallback()) {
            return this.path;
        }
        this.path.reset();
        if (this.hidden) {
            this.isPathValid = true;
            return this.path;
        }
        Path path2 = (Path) this.shapeAnimation.getValue();
        if (path2 == null) {
            return this.path;
        }
        this.path.set(path2);
        this.path.setFillType(Path.FillType.EVEN_ODD);
        this.trimPaths.apply(this.path);
        this.isPathValid = true;
        return this.path;
    }

    public String getName() {
        return this.name;
    }

    public void resolveKeyPath(KeyPath keyPath, int i, List list, KeyPath keyPath2) {
        MiscUtils.resolveKeyPath(keyPath, i, list, keyPath2, this);
    }

    public void addValueCallback(Object obj, LottieValueCallback lottieValueCallback) {
        if (obj == LottieProperty.PATH) {
            this.shapeAnimation.setValueCallback(lottieValueCallback);
        }
    }
}
