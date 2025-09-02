package com.airbnb.lottie.animation.keyframe;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.parser.DropShadowEffect;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;

public class DropShadowKeyframeAnimation implements BaseKeyframeAnimation.AnimationListener {
    private final BaseKeyframeAnimation color;
    private final FloatKeyframeAnimation direction;
    private final FloatKeyframeAnimation distance;
    private final BaseLayer layer;
    private final BaseKeyframeAnimation.AnimationListener listener;
    private final float[] matrixValues = new float[9];
    private final FloatKeyframeAnimation opacity;
    private int paintColor = 0;
    private float paintRadius = Float.NaN;
    private float paintX = Float.NaN;
    private float paintY = Float.NaN;
    private final FloatKeyframeAnimation radius;

    public DropShadowKeyframeAnimation(BaseKeyframeAnimation.AnimationListener animationListener, BaseLayer baseLayer, DropShadowEffect dropShadowEffect) {
        this.listener = animationListener;
        this.layer = baseLayer;
        BaseKeyframeAnimation createAnimation = dropShadowEffect.getColor().createAnimation();
        this.color = createAnimation;
        createAnimation.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation);
        FloatKeyframeAnimation createAnimation2 = dropShadowEffect.getOpacity().createAnimation();
        this.opacity = createAnimation2;
        createAnimation2.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation2);
        FloatKeyframeAnimation createAnimation3 = dropShadowEffect.getDirection().createAnimation();
        this.direction = createAnimation3;
        createAnimation3.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation3);
        FloatKeyframeAnimation createAnimation4 = dropShadowEffect.getDistance().createAnimation();
        this.distance = createAnimation4;
        createAnimation4.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation4);
        FloatKeyframeAnimation createAnimation5 = dropShadowEffect.getRadius().createAnimation();
        this.radius = createAnimation5;
        createAnimation5.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation5);
    }

    public void onValueChanged() {
        this.listener.onValueChanged();
    }

    public void applyTo(Paint paint, Matrix matrix, int i) {
        float floatValue = ((Float) this.distance.getValue()).floatValue();
        double floatValue2 = (double) (this.direction.getFloatValue() * 0.017453292f);
        float sin = ((float) Math.sin(floatValue2)) * floatValue;
        float cos = ((float) Math.cos(floatValue2 + 3.141592653589793d)) * floatValue;
        this.layer.transform.getMatrix().getValues(this.matrixValues);
        float[] fArr = this.matrixValues;
        float f = fArr[0];
        float f2 = fArr[4];
        matrix.getValues(fArr);
        float[] fArr2 = this.matrixValues;
        float f3 = fArr2[0] / f;
        float f4 = sin * f3;
        float f5 = cos * (fArr2[4] / f2);
        int intValue = ((Integer) this.color.getValue()).intValue();
        int argb = Color.argb(Math.round((((Float) this.opacity.getValue()).floatValue() * ((float) i)) / 255.0f), Color.red(intValue), Color.green(intValue), Color.blue(intValue));
        float max = Math.max(((Float) this.radius.getValue()).floatValue() * f3 * 0.33f, Float.MIN_VALUE);
        if (this.paintRadius != max || this.paintX != f4 || this.paintY != f5 || this.paintColor != argb) {
            this.paintRadius = max;
            this.paintX = f4;
            this.paintY = f5;
            this.paintColor = argb;
            paint.setShadowLayer(max, f4, f5, argb);
        }
    }

    public void setColorCallback(LottieValueCallback lottieValueCallback) {
        this.color.setValueCallback(lottieValueCallback);
    }

    public void setOpacityCallback(final LottieValueCallback lottieValueCallback) {
        if (lottieValueCallback == null) {
            this.opacity.setValueCallback((LottieValueCallback) null);
        } else {
            this.opacity.setValueCallback(new LottieValueCallback() {
                public Float getValue(LottieFrameInfo lottieFrameInfo) {
                    Float f = (Float) lottieValueCallback.getValue(lottieFrameInfo);
                    if (f == null) {
                        return null;
                    }
                    return Float.valueOf(f.floatValue() * 2.55f);
                }
            });
        }
    }

    public void setDirectionCallback(LottieValueCallback lottieValueCallback) {
        this.direction.setValueCallback(lottieValueCallback);
    }

    public void setDistanceCallback(LottieValueCallback lottieValueCallback) {
        this.distance.setValueCallback(lottieValueCallback);
    }

    public void setRadiusCallback(LottieValueCallback lottieValueCallback) {
        this.radius.setValueCallback(lottieValueCallback);
    }
}
