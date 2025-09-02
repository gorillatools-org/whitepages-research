package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ColorKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.ShapeFill;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

public class FillContent implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    private BaseKeyframeAnimation blurAnimation;
    float blurMaskFilterRadius;
    private final BaseKeyframeAnimation colorAnimation;
    private BaseKeyframeAnimation colorFilterAnimation;
    private DropShadowKeyframeAnimation dropShadowAnimation;
    private final boolean hidden;
    private final BaseLayer layer;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation opacityAnimation;
    private final Paint paint = new LPaint(1);
    private final Path path;
    private final List paths = new ArrayList();

    public FillContent(LottieDrawable lottieDrawable2, BaseLayer baseLayer, ShapeFill shapeFill) {
        Path path2 = new Path();
        this.path = path2;
        this.layer = baseLayer;
        this.name = shapeFill.getName();
        this.hidden = shapeFill.isHidden();
        this.lottieDrawable = lottieDrawable2;
        if (baseLayer.getBlurEffect() != null) {
            FloatKeyframeAnimation createAnimation = baseLayer.getBlurEffect().getBlurriness().createAnimation();
            this.blurAnimation = createAnimation;
            createAnimation.addUpdateListener(this);
            baseLayer.addAnimation(this.blurAnimation);
        }
        if (baseLayer.getDropShadowEffect() != null) {
            this.dropShadowAnimation = new DropShadowKeyframeAnimation(this, baseLayer, baseLayer.getDropShadowEffect());
        }
        if (shapeFill.getColor() == null || shapeFill.getOpacity() == null) {
            this.colorAnimation = null;
            this.opacityAnimation = null;
            return;
        }
        path2.setFillType(shapeFill.getFillType());
        BaseKeyframeAnimation createAnimation2 = shapeFill.getColor().createAnimation();
        this.colorAnimation = createAnimation2;
        createAnimation2.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation2);
        BaseKeyframeAnimation createAnimation3 = shapeFill.getOpacity().createAnimation();
        this.opacityAnimation = createAnimation3;
        createAnimation3.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation3);
    }

    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    public void setContents(List list, List list2) {
        for (int i = 0; i < list2.size(); i++) {
            Content content = (Content) list2.get(i);
            if (content instanceof PathContent) {
                this.paths.add((PathContent) content);
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public void draw(Canvas canvas, Matrix matrix, int i) {
        if (!this.hidden) {
            if (L.isTraceEnabled()) {
                L.beginSection("FillContent#draw");
            }
            int intValue = ((ColorKeyframeAnimation) this.colorAnimation).getIntValue();
            int intValue2 = (int) ((((((float) i) / 255.0f) * ((float) ((Integer) this.opacityAnimation.getValue()).intValue())) / 100.0f) * 255.0f);
            this.paint.setColor((intValue & 16777215) | (MiscUtils.clamp(intValue2, 0, 255) << 24));
            BaseKeyframeAnimation baseKeyframeAnimation = this.colorFilterAnimation;
            if (baseKeyframeAnimation != null) {
                this.paint.setColorFilter((ColorFilter) baseKeyframeAnimation.getValue());
            }
            BaseKeyframeAnimation baseKeyframeAnimation2 = this.blurAnimation;
            if (baseKeyframeAnimation2 != null) {
                float floatValue = ((Float) baseKeyframeAnimation2.getValue()).floatValue();
                if (floatValue == 0.0f) {
                    this.paint.setMaskFilter((MaskFilter) null);
                } else if (floatValue != this.blurMaskFilterRadius) {
                    this.paint.setMaskFilter(this.layer.getBlurMaskFilter(floatValue));
                }
                this.blurMaskFilterRadius = floatValue;
            }
            DropShadowKeyframeAnimation dropShadowKeyframeAnimation = this.dropShadowAnimation;
            if (dropShadowKeyframeAnimation != null) {
                dropShadowKeyframeAnimation.applyTo(this.paint, matrix, Utils.mixOpacities(i, intValue2));
            }
            this.path.reset();
            for (int i2 = 0; i2 < this.paths.size(); i2++) {
                this.path.addPath(((PathContent) this.paths.get(i2)).getPath(), matrix);
            }
            canvas.drawPath(this.path, this.paint);
            if (L.isTraceEnabled()) {
                L.endSection("FillContent#draw");
            }
        }
    }

    public void getBounds(RectF rectF, Matrix matrix, boolean z) {
        this.path.reset();
        for (int i = 0; i < this.paths.size(); i++) {
            this.path.addPath(((PathContent) this.paths.get(i)).getPath(), matrix);
        }
        this.path.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    public void resolveKeyPath(KeyPath keyPath, int i, List list, KeyPath keyPath2) {
        MiscUtils.resolveKeyPath(keyPath, i, list, keyPath2, this);
    }

    public void addValueCallback(Object obj, LottieValueCallback lottieValueCallback) {
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation2;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation3;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation4;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation5;
        if (obj == LottieProperty.COLOR) {
            this.colorAnimation.setValueCallback(lottieValueCallback);
        } else if (obj == LottieProperty.OPACITY) {
            this.opacityAnimation.setValueCallback(lottieValueCallback);
        } else if (obj == LottieProperty.COLOR_FILTER) {
            BaseKeyframeAnimation baseKeyframeAnimation = this.colorFilterAnimation;
            if (baseKeyframeAnimation != null) {
                this.layer.removeAnimation(baseKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.colorFilterAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.colorFilterAnimation = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.addUpdateListener(this);
            this.layer.addAnimation(this.colorFilterAnimation);
        } else if (obj == LottieProperty.BLUR_RADIUS) {
            BaseKeyframeAnimation baseKeyframeAnimation2 = this.blurAnimation;
            if (baseKeyframeAnimation2 != null) {
                baseKeyframeAnimation2.setValueCallback(lottieValueCallback);
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.blurAnimation = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.addUpdateListener(this);
            this.layer.addAnimation(this.blurAnimation);
        } else if (obj == LottieProperty.DROP_SHADOW_COLOR && (dropShadowKeyframeAnimation5 = this.dropShadowAnimation) != null) {
            dropShadowKeyframeAnimation5.setColorCallback(lottieValueCallback);
        } else if (obj == LottieProperty.DROP_SHADOW_OPACITY && (dropShadowKeyframeAnimation4 = this.dropShadowAnimation) != null) {
            dropShadowKeyframeAnimation4.setOpacityCallback(lottieValueCallback);
        } else if (obj == LottieProperty.DROP_SHADOW_DIRECTION && (dropShadowKeyframeAnimation3 = this.dropShadowAnimation) != null) {
            dropShadowKeyframeAnimation3.setDirectionCallback(lottieValueCallback);
        } else if (obj == LottieProperty.DROP_SHADOW_DISTANCE && (dropShadowKeyframeAnimation2 = this.dropShadowAnimation) != null) {
            dropShadowKeyframeAnimation2.setDistanceCallback(lottieValueCallback);
        } else if (obj == LottieProperty.DROP_SHADOW_RADIUS && (dropShadowKeyframeAnimation = this.dropShadowAnimation) != null) {
            dropShadowKeyframeAnimation.setRadiusCallback(lottieValueCallback);
        }
    }
}
