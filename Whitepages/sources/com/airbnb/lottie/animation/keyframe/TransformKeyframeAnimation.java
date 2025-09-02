package com.airbnb.lottie.animation.keyframe;

import android.graphics.Matrix;
import android.graphics.PointF;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.ScaleXY;
import java.util.Collections;

public class TransformKeyframeAnimation {
    private BaseKeyframeAnimation anchorPoint;
    private final boolean autoOrient;
    private BaseKeyframeAnimation endOpacity;
    private final Matrix matrix = new Matrix();
    private BaseKeyframeAnimation opacity;
    private BaseKeyframeAnimation position;
    private BaseKeyframeAnimation rotation;
    private BaseKeyframeAnimation scale;
    private FloatKeyframeAnimation skew;
    private FloatKeyframeAnimation skewAngle;
    private final Matrix skewMatrix1;
    private final Matrix skewMatrix2;
    private final Matrix skewMatrix3;
    private final float[] skewValues;
    private BaseKeyframeAnimation startOpacity;

    public TransformKeyframeAnimation(AnimatableTransform animatableTransform) {
        this.anchorPoint = animatableTransform.getAnchorPoint() == null ? null : animatableTransform.getAnchorPoint().createAnimation();
        this.position = animatableTransform.getPosition() == null ? null : animatableTransform.getPosition().createAnimation();
        this.scale = animatableTransform.getScale() == null ? null : animatableTransform.getScale().createAnimation();
        this.rotation = animatableTransform.getRotation() == null ? null : animatableTransform.getRotation().createAnimation();
        this.skew = animatableTransform.getSkew() == null ? null : animatableTransform.getSkew().createAnimation();
        this.autoOrient = animatableTransform.isAutoOrient();
        if (this.skew != null) {
            this.skewMatrix1 = new Matrix();
            this.skewMatrix2 = new Matrix();
            this.skewMatrix3 = new Matrix();
            this.skewValues = new float[9];
        } else {
            this.skewMatrix1 = null;
            this.skewMatrix2 = null;
            this.skewMatrix3 = null;
            this.skewValues = null;
        }
        this.skewAngle = animatableTransform.getSkewAngle() == null ? null : animatableTransform.getSkewAngle().createAnimation();
        if (animatableTransform.getOpacity() != null) {
            this.opacity = animatableTransform.getOpacity().createAnimation();
        }
        if (animatableTransform.getStartOpacity() != null) {
            this.startOpacity = animatableTransform.getStartOpacity().createAnimation();
        } else {
            this.startOpacity = null;
        }
        if (animatableTransform.getEndOpacity() != null) {
            this.endOpacity = animatableTransform.getEndOpacity().createAnimation();
        } else {
            this.endOpacity = null;
        }
    }

    public void addAnimationsToLayer(BaseLayer baseLayer) {
        baseLayer.addAnimation(this.opacity);
        baseLayer.addAnimation(this.startOpacity);
        baseLayer.addAnimation(this.endOpacity);
        baseLayer.addAnimation(this.anchorPoint);
        baseLayer.addAnimation(this.position);
        baseLayer.addAnimation(this.scale);
        baseLayer.addAnimation(this.rotation);
        baseLayer.addAnimation(this.skew);
        baseLayer.addAnimation(this.skewAngle);
    }

    public void addListener(BaseKeyframeAnimation.AnimationListener animationListener) {
        BaseKeyframeAnimation baseKeyframeAnimation = this.opacity;
        if (baseKeyframeAnimation != null) {
            baseKeyframeAnimation.addUpdateListener(animationListener);
        }
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.startOpacity;
        if (baseKeyframeAnimation2 != null) {
            baseKeyframeAnimation2.addUpdateListener(animationListener);
        }
        BaseKeyframeAnimation baseKeyframeAnimation3 = this.endOpacity;
        if (baseKeyframeAnimation3 != null) {
            baseKeyframeAnimation3.addUpdateListener(animationListener);
        }
        BaseKeyframeAnimation baseKeyframeAnimation4 = this.anchorPoint;
        if (baseKeyframeAnimation4 != null) {
            baseKeyframeAnimation4.addUpdateListener(animationListener);
        }
        BaseKeyframeAnimation baseKeyframeAnimation5 = this.position;
        if (baseKeyframeAnimation5 != null) {
            baseKeyframeAnimation5.addUpdateListener(animationListener);
        }
        BaseKeyframeAnimation baseKeyframeAnimation6 = this.scale;
        if (baseKeyframeAnimation6 != null) {
            baseKeyframeAnimation6.addUpdateListener(animationListener);
        }
        BaseKeyframeAnimation baseKeyframeAnimation7 = this.rotation;
        if (baseKeyframeAnimation7 != null) {
            baseKeyframeAnimation7.addUpdateListener(animationListener);
        }
        FloatKeyframeAnimation floatKeyframeAnimation = this.skew;
        if (floatKeyframeAnimation != null) {
            floatKeyframeAnimation.addUpdateListener(animationListener);
        }
        FloatKeyframeAnimation floatKeyframeAnimation2 = this.skewAngle;
        if (floatKeyframeAnimation2 != null) {
            floatKeyframeAnimation2.addUpdateListener(animationListener);
        }
    }

    public void setProgress(float f) {
        BaseKeyframeAnimation baseKeyframeAnimation = this.opacity;
        if (baseKeyframeAnimation != null) {
            baseKeyframeAnimation.setProgress(f);
        }
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.startOpacity;
        if (baseKeyframeAnimation2 != null) {
            baseKeyframeAnimation2.setProgress(f);
        }
        BaseKeyframeAnimation baseKeyframeAnimation3 = this.endOpacity;
        if (baseKeyframeAnimation3 != null) {
            baseKeyframeAnimation3.setProgress(f);
        }
        BaseKeyframeAnimation baseKeyframeAnimation4 = this.anchorPoint;
        if (baseKeyframeAnimation4 != null) {
            baseKeyframeAnimation4.setProgress(f);
        }
        BaseKeyframeAnimation baseKeyframeAnimation5 = this.position;
        if (baseKeyframeAnimation5 != null) {
            baseKeyframeAnimation5.setProgress(f);
        }
        BaseKeyframeAnimation baseKeyframeAnimation6 = this.scale;
        if (baseKeyframeAnimation6 != null) {
            baseKeyframeAnimation6.setProgress(f);
        }
        BaseKeyframeAnimation baseKeyframeAnimation7 = this.rotation;
        if (baseKeyframeAnimation7 != null) {
            baseKeyframeAnimation7.setProgress(f);
        }
        FloatKeyframeAnimation floatKeyframeAnimation = this.skew;
        if (floatKeyframeAnimation != null) {
            floatKeyframeAnimation.setProgress(f);
        }
        FloatKeyframeAnimation floatKeyframeAnimation2 = this.skewAngle;
        if (floatKeyframeAnimation2 != null) {
            floatKeyframeAnimation2.setProgress(f);
        }
    }

    public BaseKeyframeAnimation getOpacity() {
        return this.opacity;
    }

    public BaseKeyframeAnimation getStartOpacity() {
        return this.startOpacity;
    }

    public BaseKeyframeAnimation getEndOpacity() {
        return this.endOpacity;
    }

    public Matrix getMatrix() {
        PointF pointF;
        ScaleXY scaleXY;
        float f;
        PointF pointF2;
        this.matrix.reset();
        BaseKeyframeAnimation baseKeyframeAnimation = this.position;
        if (!(baseKeyframeAnimation == null || (pointF2 = (PointF) baseKeyframeAnimation.getValue()) == null)) {
            float f2 = pointF2.x;
            if (!(f2 == 0.0f && pointF2.y == 0.0f)) {
                this.matrix.preTranslate(f2, pointF2.y);
            }
        }
        if (!this.autoOrient) {
            BaseKeyframeAnimation baseKeyframeAnimation2 = this.rotation;
            if (baseKeyframeAnimation2 != null) {
                if (baseKeyframeAnimation2 instanceof ValueCallbackKeyframeAnimation) {
                    f = ((Float) baseKeyframeAnimation2.getValue()).floatValue();
                } else {
                    f = ((FloatKeyframeAnimation) baseKeyframeAnimation2).getFloatValue();
                }
                if (f != 0.0f) {
                    this.matrix.preRotate(f);
                }
            }
        } else if (baseKeyframeAnimation != null) {
            float progress = baseKeyframeAnimation.getProgress();
            PointF pointF3 = (PointF) baseKeyframeAnimation.getValue();
            float f3 = pointF3.x;
            float f4 = pointF3.y;
            baseKeyframeAnimation.setProgress(1.0E-4f + progress);
            PointF pointF4 = (PointF) baseKeyframeAnimation.getValue();
            baseKeyframeAnimation.setProgress(progress);
            this.matrix.preRotate((float) Math.toDegrees(Math.atan2((double) (pointF4.y - f4), (double) (pointF4.x - f3))));
        }
        FloatKeyframeAnimation floatKeyframeAnimation = this.skew;
        if (floatKeyframeAnimation != null) {
            FloatKeyframeAnimation floatKeyframeAnimation2 = this.skewAngle;
            float cos = floatKeyframeAnimation2 == null ? 0.0f : (float) Math.cos(Math.toRadians((double) ((-floatKeyframeAnimation2.getFloatValue()) + 90.0f)));
            FloatKeyframeAnimation floatKeyframeAnimation3 = this.skewAngle;
            float sin = floatKeyframeAnimation3 == null ? 1.0f : (float) Math.sin(Math.toRadians((double) ((-floatKeyframeAnimation3.getFloatValue()) + 90.0f)));
            float tan = (float) Math.tan(Math.toRadians((double) floatKeyframeAnimation.getFloatValue()));
            clearSkewValues();
            float[] fArr = this.skewValues;
            fArr[0] = cos;
            fArr[1] = sin;
            float f5 = -sin;
            fArr[3] = f5;
            fArr[4] = cos;
            fArr[8] = 1.0f;
            this.skewMatrix1.setValues(fArr);
            clearSkewValues();
            float[] fArr2 = this.skewValues;
            fArr2[0] = 1.0f;
            fArr2[3] = tan;
            fArr2[4] = 1.0f;
            fArr2[8] = 1.0f;
            this.skewMatrix2.setValues(fArr2);
            clearSkewValues();
            float[] fArr3 = this.skewValues;
            fArr3[0] = cos;
            fArr3[1] = f5;
            fArr3[3] = sin;
            fArr3[4] = cos;
            fArr3[8] = 1.0f;
            this.skewMatrix3.setValues(fArr3);
            this.skewMatrix2.preConcat(this.skewMatrix1);
            this.skewMatrix3.preConcat(this.skewMatrix2);
            this.matrix.preConcat(this.skewMatrix3);
        }
        BaseKeyframeAnimation baseKeyframeAnimation3 = this.scale;
        if (!(baseKeyframeAnimation3 == null || (scaleXY = (ScaleXY) baseKeyframeAnimation3.getValue()) == null || (scaleXY.getScaleX() == 1.0f && scaleXY.getScaleY() == 1.0f))) {
            this.matrix.preScale(scaleXY.getScaleX(), scaleXY.getScaleY());
        }
        BaseKeyframeAnimation baseKeyframeAnimation4 = this.anchorPoint;
        if (!(baseKeyframeAnimation4 == null || (pointF = (PointF) baseKeyframeAnimation4.getValue()) == null)) {
            float f6 = pointF.x;
            if (!(f6 == 0.0f && pointF.y == 0.0f)) {
                this.matrix.preTranslate(-f6, -pointF.y);
            }
        }
        return this.matrix;
    }

    private void clearSkewValues() {
        for (int i = 0; i < 9; i++) {
            this.skewValues[i] = 0.0f;
        }
    }

    public Matrix getMatrixForRepeater(float f) {
        BaseKeyframeAnimation baseKeyframeAnimation = this.position;
        PointF pointF = null;
        PointF pointF2 = baseKeyframeAnimation == null ? null : (PointF) baseKeyframeAnimation.getValue();
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.scale;
        ScaleXY scaleXY = baseKeyframeAnimation2 == null ? null : (ScaleXY) baseKeyframeAnimation2.getValue();
        this.matrix.reset();
        if (pointF2 != null) {
            this.matrix.preTranslate(pointF2.x * f, pointF2.y * f);
        }
        if (scaleXY != null) {
            double d = (double) f;
            this.matrix.preScale((float) Math.pow((double) scaleXY.getScaleX(), d), (float) Math.pow((double) scaleXY.getScaleY(), d));
        }
        BaseKeyframeAnimation baseKeyframeAnimation3 = this.rotation;
        if (baseKeyframeAnimation3 != null) {
            float floatValue = ((Float) baseKeyframeAnimation3.getValue()).floatValue();
            BaseKeyframeAnimation baseKeyframeAnimation4 = this.anchorPoint;
            if (baseKeyframeAnimation4 != null) {
                pointF = (PointF) baseKeyframeAnimation4.getValue();
            }
            Matrix matrix2 = this.matrix;
            float f2 = floatValue * f;
            float f3 = 0.0f;
            float f4 = pointF == null ? 0.0f : pointF.x;
            if (pointF != null) {
                f3 = pointF.y;
            }
            matrix2.preRotate(f2, f4, f3);
        }
        return this.matrix;
    }

    public boolean applyValueCallback(Object obj, LottieValueCallback lottieValueCallback) {
        if (obj == LottieProperty.TRANSFORM_ANCHOR_POINT) {
            BaseKeyframeAnimation baseKeyframeAnimation = this.anchorPoint;
            if (baseKeyframeAnimation == null) {
                this.anchorPoint = new ValueCallbackKeyframeAnimation(lottieValueCallback, new PointF());
                return true;
            }
            baseKeyframeAnimation.setValueCallback(lottieValueCallback);
            return true;
        } else if (obj == LottieProperty.TRANSFORM_POSITION) {
            BaseKeyframeAnimation baseKeyframeAnimation2 = this.position;
            if (baseKeyframeAnimation2 == null) {
                this.position = new ValueCallbackKeyframeAnimation(lottieValueCallback, new PointF());
                return true;
            }
            baseKeyframeAnimation2.setValueCallback(lottieValueCallback);
            return true;
        } else {
            if (obj == LottieProperty.TRANSFORM_POSITION_X) {
                BaseKeyframeAnimation baseKeyframeAnimation3 = this.position;
                if (baseKeyframeAnimation3 instanceof SplitDimensionPathKeyframeAnimation) {
                    ((SplitDimensionPathKeyframeAnimation) baseKeyframeAnimation3).setXValueCallback(lottieValueCallback);
                    return true;
                }
            }
            if (obj == LottieProperty.TRANSFORM_POSITION_Y) {
                BaseKeyframeAnimation baseKeyframeAnimation4 = this.position;
                if (baseKeyframeAnimation4 instanceof SplitDimensionPathKeyframeAnimation) {
                    ((SplitDimensionPathKeyframeAnimation) baseKeyframeAnimation4).setYValueCallback(lottieValueCallback);
                    return true;
                }
            }
            if (obj == LottieProperty.TRANSFORM_SCALE) {
                BaseKeyframeAnimation baseKeyframeAnimation5 = this.scale;
                if (baseKeyframeAnimation5 == null) {
                    this.scale = new ValueCallbackKeyframeAnimation(lottieValueCallback, new ScaleXY());
                    return true;
                }
                baseKeyframeAnimation5.setValueCallback(lottieValueCallback);
                return true;
            } else if (obj == LottieProperty.TRANSFORM_ROTATION) {
                BaseKeyframeAnimation baseKeyframeAnimation6 = this.rotation;
                if (baseKeyframeAnimation6 == null) {
                    this.rotation = new ValueCallbackKeyframeAnimation(lottieValueCallback, Float.valueOf(0.0f));
                    return true;
                }
                baseKeyframeAnimation6.setValueCallback(lottieValueCallback);
                return true;
            } else if (obj == LottieProperty.TRANSFORM_OPACITY) {
                BaseKeyframeAnimation baseKeyframeAnimation7 = this.opacity;
                if (baseKeyframeAnimation7 == null) {
                    this.opacity = new ValueCallbackKeyframeAnimation(lottieValueCallback, 100);
                    return true;
                }
                baseKeyframeAnimation7.setValueCallback(lottieValueCallback);
                return true;
            } else if (obj == LottieProperty.TRANSFORM_START_OPACITY) {
                BaseKeyframeAnimation baseKeyframeAnimation8 = this.startOpacity;
                if (baseKeyframeAnimation8 == null) {
                    this.startOpacity = new ValueCallbackKeyframeAnimation(lottieValueCallback, Float.valueOf(100.0f));
                    return true;
                }
                baseKeyframeAnimation8.setValueCallback(lottieValueCallback);
                return true;
            } else if (obj == LottieProperty.TRANSFORM_END_OPACITY) {
                BaseKeyframeAnimation baseKeyframeAnimation9 = this.endOpacity;
                if (baseKeyframeAnimation9 == null) {
                    this.endOpacity = new ValueCallbackKeyframeAnimation(lottieValueCallback, Float.valueOf(100.0f));
                    return true;
                }
                baseKeyframeAnimation9.setValueCallback(lottieValueCallback);
                return true;
            } else if (obj == LottieProperty.TRANSFORM_SKEW) {
                if (this.skew == null) {
                    this.skew = new FloatKeyframeAnimation(Collections.singletonList(new Keyframe(Float.valueOf(0.0f))));
                }
                this.skew.setValueCallback(lottieValueCallback);
                return true;
            } else if (obj != LottieProperty.TRANSFORM_SKEW_ANGLE) {
                return false;
            } else {
                if (this.skewAngle == null) {
                    this.skewAngle = new FloatKeyframeAnimation(Collections.singletonList(new Keyframe(Float.valueOf(0.0f))));
                }
                this.skewAngle.setValueCallback(lottieValueCallback);
                return true;
            }
        }
    }
}
