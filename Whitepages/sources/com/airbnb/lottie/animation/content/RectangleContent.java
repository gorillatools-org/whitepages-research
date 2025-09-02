package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.RectangleShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class RectangleContent implements BaseKeyframeAnimation.AnimationListener, KeyPathElementContent, PathContent {
    private final BaseKeyframeAnimation cornerRadiusAnimation;
    private final boolean hidden;
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final Path path = new Path();
    private final BaseKeyframeAnimation positionAnimation;
    private final RectF rect = new RectF();
    private BaseKeyframeAnimation roundedCornersAnimation = null;
    private final BaseKeyframeAnimation sizeAnimation;
    private final CompoundTrimPathContent trimPaths = new CompoundTrimPathContent();

    public RectangleContent(LottieDrawable lottieDrawable2, BaseLayer baseLayer, RectangleShape rectangleShape) {
        this.name = rectangleShape.getName();
        this.hidden = rectangleShape.isHidden();
        this.lottieDrawable = lottieDrawable2;
        BaseKeyframeAnimation createAnimation = rectangleShape.getPosition().createAnimation();
        this.positionAnimation = createAnimation;
        BaseKeyframeAnimation createAnimation2 = rectangleShape.getSize().createAnimation();
        this.sizeAnimation = createAnimation2;
        FloatKeyframeAnimation createAnimation3 = rectangleShape.getCornerRadius().createAnimation();
        this.cornerRadiusAnimation = createAnimation3;
        baseLayer.addAnimation(createAnimation);
        baseLayer.addAnimation(createAnimation2);
        baseLayer.addAnimation(createAnimation3);
        createAnimation.addUpdateListener(this);
        createAnimation2.addUpdateListener(this);
        createAnimation3.addUpdateListener(this);
    }

    public String getName() {
        return this.name;
    }

    public void onValueChanged() {
        invalidate();
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    public void setContents(List list, List list2) {
        for (int i = 0; i < list.size(); i++) {
            Content content = (Content) list.get(i);
            if (content instanceof TrimPathContent) {
                TrimPathContent trimPathContent = (TrimPathContent) content;
                if (trimPathContent.getType() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                    this.trimPaths.addTrimPath(trimPathContent);
                    trimPathContent.addListener(this);
                }
            }
            if (content instanceof RoundedCornersContent) {
                this.roundedCornersAnimation = ((RoundedCornersContent) content).getRoundedCorners();
            }
        }
    }

    public Path getPath() {
        float f;
        BaseKeyframeAnimation baseKeyframeAnimation;
        if (this.isPathValid) {
            return this.path;
        }
        this.path.reset();
        if (this.hidden) {
            this.isPathValid = true;
            return this.path;
        }
        PointF pointF = (PointF) this.sizeAnimation.getValue();
        float f2 = pointF.x / 2.0f;
        float f3 = pointF.y / 2.0f;
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.cornerRadiusAnimation;
        if (baseKeyframeAnimation2 == null) {
            f = 0.0f;
        } else {
            f = ((FloatKeyframeAnimation) baseKeyframeAnimation2).getFloatValue();
        }
        if (f == 0.0f && (baseKeyframeAnimation = this.roundedCornersAnimation) != null) {
            f = Math.min(((Float) baseKeyframeAnimation.getValue()).floatValue(), Math.min(f2, f3));
        }
        float min = Math.min(f2, f3);
        if (f > min) {
            f = min;
        }
        PointF pointF2 = (PointF) this.positionAnimation.getValue();
        this.path.moveTo(pointF2.x + f2, (pointF2.y - f3) + f);
        this.path.lineTo(pointF2.x + f2, (pointF2.y + f3) - f);
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        if (i > 0) {
            RectF rectF = this.rect;
            float f4 = pointF2.x;
            float f5 = f * 2.0f;
            float f6 = pointF2.y;
            rectF.set((f4 + f2) - f5, (f6 + f3) - f5, f4 + f2, f6 + f3);
            this.path.arcTo(this.rect, 0.0f, 90.0f, false);
        }
        this.path.lineTo((pointF2.x - f2) + f, pointF2.y + f3);
        if (i > 0) {
            RectF rectF2 = this.rect;
            float f7 = pointF2.x;
            float f8 = pointF2.y;
            float f9 = f * 2.0f;
            rectF2.set(f7 - f2, (f8 + f3) - f9, (f7 - f2) + f9, f8 + f3);
            this.path.arcTo(this.rect, 90.0f, 90.0f, false);
        }
        this.path.lineTo(pointF2.x - f2, (pointF2.y - f3) + f);
        if (i > 0) {
            RectF rectF3 = this.rect;
            float f10 = pointF2.x;
            float f11 = pointF2.y;
            float f12 = f * 2.0f;
            rectF3.set(f10 - f2, f11 - f3, (f10 - f2) + f12, (f11 - f3) + f12);
            this.path.arcTo(this.rect, 180.0f, 90.0f, false);
        }
        this.path.lineTo((pointF2.x + f2) - f, pointF2.y - f3);
        if (i > 0) {
            RectF rectF4 = this.rect;
            float f13 = pointF2.x;
            float f14 = f * 2.0f;
            float f15 = pointF2.y;
            rectF4.set((f13 + f2) - f14, f15 - f3, f13 + f2, (f15 - f3) + f14);
            this.path.arcTo(this.rect, 270.0f, 90.0f, false);
        }
        this.path.close();
        this.trimPaths.apply(this.path);
        this.isPathValid = true;
        return this.path;
    }

    public void resolveKeyPath(KeyPath keyPath, int i, List list, KeyPath keyPath2) {
        MiscUtils.resolveKeyPath(keyPath, i, list, keyPath2, this);
    }

    public void addValueCallback(Object obj, LottieValueCallback lottieValueCallback) {
        if (obj == LottieProperty.RECTANGLE_SIZE) {
            this.sizeAnimation.setValueCallback(lottieValueCallback);
        } else if (obj == LottieProperty.POSITION) {
            this.positionAnimation.setValueCallback(lottieValueCallback);
        } else if (obj == LottieProperty.CORNER_RADIUS) {
            this.cornerRadiusAnimation.setValueCallback(lottieValueCallback);
        }
    }
}
