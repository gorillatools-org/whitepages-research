package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.Collections;

public class SplitDimensionPathKeyframeAnimation extends BaseKeyframeAnimation {
    private final PointF point = new PointF();
    private final PointF pointWithCallbackValues = new PointF();
    private final BaseKeyframeAnimation xAnimation;
    protected LottieValueCallback xValueCallback;
    private final BaseKeyframeAnimation yAnimation;
    protected LottieValueCallback yValueCallback;

    public SplitDimensionPathKeyframeAnimation(BaseKeyframeAnimation baseKeyframeAnimation, BaseKeyframeAnimation baseKeyframeAnimation2) {
        super(Collections.emptyList());
        this.xAnimation = baseKeyframeAnimation;
        this.yAnimation = baseKeyframeAnimation2;
        setProgress(getProgress());
    }

    public void setXValueCallback(LottieValueCallback lottieValueCallback) {
        LottieValueCallback lottieValueCallback2 = this.xValueCallback;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.setAnimation((BaseKeyframeAnimation) null);
        }
        this.xValueCallback = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation(this);
        }
    }

    public void setYValueCallback(LottieValueCallback lottieValueCallback) {
        LottieValueCallback lottieValueCallback2 = this.yValueCallback;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.setAnimation((BaseKeyframeAnimation) null);
        }
        this.yValueCallback = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation(this);
        }
    }

    public void setProgress(float f) {
        this.xAnimation.setProgress(f);
        this.yAnimation.setProgress(f);
        this.point.set(((Float) this.xAnimation.getValue()).floatValue(), ((Float) this.yAnimation.getValue()).floatValue());
        for (int i = 0; i < this.listeners.size(); i++) {
            ((BaseKeyframeAnimation.AnimationListener) this.listeners.get(i)).onValueChanged();
        }
    }

    public PointF getValue() {
        return getValue((Keyframe) null, 0.0f);
    }

    /* access modifiers changed from: package-private */
    public PointF getValue(Keyframe keyframe, float f) {
        Float f2;
        Keyframe currentKeyframe;
        float f3;
        Keyframe currentKeyframe2;
        float f4;
        Float f5 = null;
        if (this.xValueCallback == null || (currentKeyframe2 = this.xAnimation.getCurrentKeyframe()) == null) {
            f2 = null;
        } else {
            Float f6 = currentKeyframe2.endFrame;
            LottieValueCallback lottieValueCallback = this.xValueCallback;
            float f7 = currentKeyframe2.startFrame;
            if (f6 == null) {
                f4 = f7;
            } else {
                f4 = f6.floatValue();
            }
            f2 = (Float) lottieValueCallback.getValueInternal(f7, f4, (Float) currentKeyframe2.startValue, (Float) currentKeyframe2.endValue, this.xAnimation.getInterpolatedCurrentKeyframeProgress(), this.xAnimation.getLinearCurrentKeyframeProgress(), this.xAnimation.getProgress());
        }
        if (!(this.yValueCallback == null || (currentKeyframe = this.yAnimation.getCurrentKeyframe()) == null)) {
            Float f8 = currentKeyframe.endFrame;
            LottieValueCallback lottieValueCallback2 = this.yValueCallback;
            float f9 = currentKeyframe.startFrame;
            if (f8 == null) {
                f3 = f9;
            } else {
                f3 = f8.floatValue();
            }
            f5 = (Float) lottieValueCallback2.getValueInternal(f9, f3, (Float) currentKeyframe.startValue, (Float) currentKeyframe.endValue, this.yAnimation.getInterpolatedCurrentKeyframeProgress(), this.yAnimation.getLinearCurrentKeyframeProgress(), this.yAnimation.getProgress());
        }
        if (f2 == null) {
            this.pointWithCallbackValues.set(this.point.x, 0.0f);
        } else {
            this.pointWithCallbackValues.set(f2.floatValue(), 0.0f);
        }
        if (f5 == null) {
            PointF pointF = this.pointWithCallbackValues;
            pointF.set(pointF.x, this.point.y);
        } else {
            PointF pointF2 = this.pointWithCallbackValues;
            pointF2.set(pointF2.x, f5.floatValue());
        }
        return this.pointWithCallbackValues;
    }
}
