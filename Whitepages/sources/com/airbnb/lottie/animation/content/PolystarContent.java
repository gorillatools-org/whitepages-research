package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class PolystarContent implements PathContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    private final boolean hidden;
    private final BaseKeyframeAnimation innerRadiusAnimation;
    private final BaseKeyframeAnimation innerRoundednessAnimation;
    private boolean isPathValid;
    private final boolean isReversed;
    private final Path lastSegmentPath = new Path();
    private final PathMeasure lastSegmentPathMeasure = new PathMeasure();
    private final float[] lastSegmentPosition = new float[2];
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation outerRadiusAnimation;
    private final BaseKeyframeAnimation outerRoundednessAnimation;
    private final Path path = new Path();
    private final BaseKeyframeAnimation pointsAnimation;
    private final BaseKeyframeAnimation positionAnimation;
    private final BaseKeyframeAnimation rotationAnimation;
    private final CompoundTrimPathContent trimPaths = new CompoundTrimPathContent();
    private final PolystarShape.Type type;

    public PolystarContent(LottieDrawable lottieDrawable2, BaseLayer baseLayer, PolystarShape polystarShape) {
        this.lottieDrawable = lottieDrawable2;
        this.name = polystarShape.getName();
        PolystarShape.Type type2 = polystarShape.getType();
        this.type = type2;
        this.hidden = polystarShape.isHidden();
        this.isReversed = polystarShape.isReversed();
        FloatKeyframeAnimation createAnimation = polystarShape.getPoints().createAnimation();
        this.pointsAnimation = createAnimation;
        BaseKeyframeAnimation createAnimation2 = polystarShape.getPosition().createAnimation();
        this.positionAnimation = createAnimation2;
        FloatKeyframeAnimation createAnimation3 = polystarShape.getRotation().createAnimation();
        this.rotationAnimation = createAnimation3;
        FloatKeyframeAnimation createAnimation4 = polystarShape.getOuterRadius().createAnimation();
        this.outerRadiusAnimation = createAnimation4;
        FloatKeyframeAnimation createAnimation5 = polystarShape.getOuterRoundedness().createAnimation();
        this.outerRoundednessAnimation = createAnimation5;
        PolystarShape.Type type3 = PolystarShape.Type.STAR;
        if (type2 == type3) {
            this.innerRadiusAnimation = polystarShape.getInnerRadius().createAnimation();
            this.innerRoundednessAnimation = polystarShape.getInnerRoundedness().createAnimation();
        } else {
            this.innerRadiusAnimation = null;
            this.innerRoundednessAnimation = null;
        }
        baseLayer.addAnimation(createAnimation);
        baseLayer.addAnimation(createAnimation2);
        baseLayer.addAnimation(createAnimation3);
        baseLayer.addAnimation(createAnimation4);
        baseLayer.addAnimation(createAnimation5);
        if (type2 == type3) {
            baseLayer.addAnimation(this.innerRadiusAnimation);
            baseLayer.addAnimation(this.innerRoundednessAnimation);
        }
        createAnimation.addUpdateListener(this);
        createAnimation2.addUpdateListener(this);
        createAnimation3.addUpdateListener(this);
        createAnimation4.addUpdateListener(this);
        createAnimation5.addUpdateListener(this);
        if (type2 == type3) {
            this.innerRadiusAnimation.addUpdateListener(this);
            this.innerRoundednessAnimation.addUpdateListener(this);
        }
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
        }
    }

    public Path getPath() {
        if (this.isPathValid) {
            return this.path;
        }
        this.path.reset();
        if (this.hidden) {
            this.isPathValid = true;
            return this.path;
        }
        int i = AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type[this.type.ordinal()];
        if (i == 1) {
            createStarPath();
        } else if (i == 2) {
            createPolygonPath();
        }
        this.path.close();
        this.trimPaths.apply(this.path);
        this.isPathValid = true;
        return this.path;
    }

    /* renamed from: com.airbnb.lottie.animation.content.PolystarContent$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.airbnb.lottie.model.content.PolystarShape$Type[] r0 = com.airbnb.lottie.model.content.PolystarShape.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type = r0
                com.airbnb.lottie.model.content.PolystarShape$Type r1 = com.airbnb.lottie.model.content.PolystarShape.Type.STAR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.airbnb.lottie.model.content.PolystarShape$Type r1 = com.airbnb.lottie.model.content.PolystarShape.Type.POLYGON     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.content.PolystarContent.AnonymousClass1.<clinit>():void");
        }
    }

    public String getName() {
        return this.name;
    }

    private void createStarPath() {
        int i;
        float f;
        float f2;
        float f3;
        float f4;
        double d;
        float f5;
        float f6;
        double d2;
        float f7;
        double d3;
        float f8;
        float f9;
        float floatValue = ((Float) this.pointsAnimation.getValue()).floatValue();
        BaseKeyframeAnimation baseKeyframeAnimation = this.rotationAnimation;
        double radians = Math.toRadians((baseKeyframeAnimation == null ? 0.0d : (double) ((Float) baseKeyframeAnimation.getValue()).floatValue()) - 90.0d);
        double d4 = (double) floatValue;
        float f10 = (float) (6.283185307179586d / d4);
        if (this.isReversed) {
            f10 *= -1.0f;
        }
        float f11 = f10 / 2.0f;
        float f12 = floatValue - ((float) ((int) floatValue));
        int i2 = (f12 > 0.0f ? 1 : (f12 == 0.0f ? 0 : -1));
        if (i2 != 0) {
            radians += (double) ((1.0f - f12) * f11);
        }
        float floatValue2 = ((Float) this.outerRadiusAnimation.getValue()).floatValue();
        float floatValue3 = ((Float) this.innerRadiusAnimation.getValue()).floatValue();
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.innerRoundednessAnimation;
        float floatValue4 = baseKeyframeAnimation2 != null ? ((Float) baseKeyframeAnimation2.getValue()).floatValue() / 100.0f : 0.0f;
        BaseKeyframeAnimation baseKeyframeAnimation3 = this.outerRoundednessAnimation;
        float floatValue5 = baseKeyframeAnimation3 != null ? ((Float) baseKeyframeAnimation3.getValue()).floatValue() / 100.0f : 0.0f;
        if (i2 != 0) {
            f = ((floatValue2 - floatValue3) * f12) + floatValue3;
            i = i2;
            double d5 = (double) f;
            float f13 = f11;
            float cos = (float) (d5 * Math.cos(radians));
            f3 = (float) (d5 * Math.sin(radians));
            this.path.moveTo(cos, f3);
            d = radians + ((double) ((f10 * f12) / 2.0f));
            f2 = cos;
            f4 = f13;
        } else {
            float f14 = f11;
            i = i2;
            double d6 = (double) floatValue2;
            float cos2 = (float) (Math.cos(radians) * d6);
            float sin = (float) (d6 * Math.sin(radians));
            this.path.moveTo(cos2, sin);
            f2 = cos2;
            f4 = f14;
            d = radians + ((double) f4);
            f3 = sin;
            f = 0.0f;
        }
        double ceil = Math.ceil(d4) * 2.0d;
        int i3 = 0;
        float f15 = f4;
        float f16 = f2;
        float f17 = floatValue2;
        float f18 = floatValue3;
        boolean z = false;
        while (true) {
            double d7 = (double) i3;
            if (d7 < ceil) {
                float f19 = z ? f17 : f18;
                int i4 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
                if (i4 == 0 || d7 != ceil - 2.0d) {
                    f5 = f10;
                    f6 = f15;
                } else {
                    f5 = f10;
                    f6 = (f10 * f12) / 2.0f;
                }
                if (i4 == 0 || d7 != ceil - 1.0d) {
                    d2 = d7;
                    f7 = f;
                    f = f19;
                } else {
                    d2 = d7;
                    f7 = f;
                }
                double d8 = (double) f;
                double d9 = ceil;
                float cos3 = (float) (d8 * Math.cos(d));
                float sin2 = (float) (d8 * Math.sin(d));
                if (floatValue4 == 0.0f && floatValue5 == 0.0f) {
                    this.path.lineTo(cos3, sin2);
                    d3 = d;
                    f8 = floatValue4;
                    f9 = floatValue5;
                } else {
                    f8 = floatValue4;
                    double atan2 = (double) ((float) (Math.atan2((double) f3, (double) f16) - 1.5707963267948966d));
                    float cos4 = (float) Math.cos(atan2);
                    float sin3 = (float) Math.sin(atan2);
                    f9 = floatValue5;
                    d3 = d;
                    double atan22 = (double) ((float) (Math.atan2((double) sin2, (double) cos3) - 1.5707963267948966d));
                    float cos5 = (float) Math.cos(atan22);
                    float sin4 = (float) Math.sin(atan22);
                    float f20 = z ? f8 : f9;
                    float f21 = z ? f9 : f8;
                    float f22 = (z ? f18 : f17) * f20 * 0.47829f;
                    float f23 = cos4 * f22;
                    float f24 = f22 * sin3;
                    float f25 = (z ? f17 : f18) * f21 * 0.47829f;
                    float f26 = cos5 * f25;
                    float f27 = f25 * sin4;
                    if (i != 0) {
                        if (i3 == 0) {
                            f23 *= f12;
                            f24 *= f12;
                        } else if (d2 == d9 - 1.0d) {
                            f26 *= f12;
                            f27 *= f12;
                        }
                    }
                    this.path.cubicTo(f16 - f23, f3 - f24, cos3 + f26, sin2 + f27, cos3, sin2);
                }
                d = d3 + ((double) f6);
                z = !z;
                i3++;
                f16 = cos3;
                f3 = sin2;
                floatValue5 = f9;
                floatValue4 = f8;
                f = f7;
                f10 = f5;
                ceil = d9;
            } else {
                PointF pointF = (PointF) this.positionAnimation.getValue();
                this.path.offset(pointF.x, pointF.y);
                this.path.close();
                return;
            }
        }
    }

    private void createPolygonPath() {
        double d;
        PolystarContent polystarContent;
        float f;
        PolystarContent polystarContent2 = this;
        int floor = (int) Math.floor((double) ((Float) polystarContent2.pointsAnimation.getValue()).floatValue());
        BaseKeyframeAnimation baseKeyframeAnimation = polystarContent2.rotationAnimation;
        double radians = Math.toRadians((baseKeyframeAnimation == null ? 0.0d : (double) ((Float) baseKeyframeAnimation.getValue()).floatValue()) - 90.0d);
        double d2 = (double) floor;
        float floatValue = ((Float) polystarContent2.outerRoundednessAnimation.getValue()).floatValue() / 100.0f;
        float floatValue2 = ((Float) polystarContent2.outerRadiusAnimation.getValue()).floatValue();
        double d3 = (double) floatValue2;
        float cos = (float) (Math.cos(radians) * d3);
        float sin = (float) (Math.sin(radians) * d3);
        polystarContent2.path.moveTo(cos, sin);
        double d4 = (double) ((float) (6.283185307179586d / d2));
        double ceil = Math.ceil(d2);
        double d5 = radians + d4;
        int i = 0;
        while (true) {
            double d6 = (double) i;
            if (d6 < ceil) {
                int i2 = i;
                float cos2 = (float) (d3 * Math.cos(d5));
                double d7 = d4;
                float sin2 = (float) (d3 * Math.sin(d5));
                if (floatValue != 0.0f) {
                    d = d3;
                    double d8 = d6;
                    double atan2 = (double) ((float) (Math.atan2((double) sin, (double) cos) - 1.5707963267948966d));
                    float cos3 = (float) Math.cos(atan2);
                    f = sin2;
                    double atan22 = (double) ((float) (Math.atan2((double) sin2, (double) cos2) - 1.5707963267948966d));
                    float f2 = floatValue2 * floatValue * 0.25f;
                    float f3 = cos3 * f2;
                    float sin3 = ((float) Math.sin(atan2)) * f2;
                    float cos4 = ((float) Math.cos(atan22)) * f2;
                    float sin4 = f2 * ((float) Math.sin(atan22));
                    if (d8 == ceil - 1.0d) {
                        polystarContent = this;
                        polystarContent.lastSegmentPath.reset();
                        polystarContent.lastSegmentPath.moveTo(cos, sin);
                        float f4 = cos - f3;
                        float f5 = sin - sin3;
                        float f6 = cos2 + cos4;
                        float f7 = sin4 + f;
                        polystarContent.lastSegmentPath.cubicTo(f4, f5, f6, f7, cos2, f);
                        polystarContent.lastSegmentPathMeasure.setPath(polystarContent.lastSegmentPath, false);
                        PathMeasure pathMeasure = polystarContent.lastSegmentPathMeasure;
                        pathMeasure.getPosTan(pathMeasure.getLength() * 0.9999f, polystarContent.lastSegmentPosition, (float[]) null);
                        Path path2 = polystarContent.path;
                        float[] fArr = polystarContent.lastSegmentPosition;
                        path2.cubicTo(f4, f5, f6, f7, fArr[0], fArr[1]);
                    } else {
                        polystarContent = this;
                        polystarContent.path.cubicTo(cos - f3, sin - sin3, cos2 + cos4, f + sin4, cos2, f);
                    }
                } else {
                    double d9 = d6;
                    d = d3;
                    f = sin2;
                    polystarContent = polystarContent2;
                    if (d9 == ceil - 1.0d) {
                        sin = f;
                        cos = cos2;
                        d4 = d7;
                        i = i2 + 1;
                        polystarContent2 = polystarContent;
                        d3 = d;
                    } else {
                        polystarContent.path.lineTo(cos2, f);
                    }
                }
                d5 += d7;
                sin = f;
                cos = cos2;
                d4 = d7;
                i = i2 + 1;
                polystarContent2 = polystarContent;
                d3 = d;
            } else {
                PolystarContent polystarContent3 = polystarContent2;
                PointF pointF = (PointF) polystarContent3.positionAnimation.getValue();
                polystarContent3.path.offset(pointF.x, pointF.y);
                polystarContent3.path.close();
                return;
            }
        }
    }

    public void resolveKeyPath(KeyPath keyPath, int i, List list, KeyPath keyPath2) {
        MiscUtils.resolveKeyPath(keyPath, i, list, keyPath2, this);
    }

    public void addValueCallback(Object obj, LottieValueCallback lottieValueCallback) {
        BaseKeyframeAnimation baseKeyframeAnimation;
        BaseKeyframeAnimation baseKeyframeAnimation2;
        if (obj == LottieProperty.POLYSTAR_POINTS) {
            this.pointsAnimation.setValueCallback(lottieValueCallback);
        } else if (obj == LottieProperty.POLYSTAR_ROTATION) {
            this.rotationAnimation.setValueCallback(lottieValueCallback);
        } else if (obj == LottieProperty.POSITION) {
            this.positionAnimation.setValueCallback(lottieValueCallback);
        } else if (obj == LottieProperty.POLYSTAR_INNER_RADIUS && (baseKeyframeAnimation2 = this.innerRadiusAnimation) != null) {
            baseKeyframeAnimation2.setValueCallback(lottieValueCallback);
        } else if (obj == LottieProperty.POLYSTAR_OUTER_RADIUS) {
            this.outerRadiusAnimation.setValueCallback(lottieValueCallback);
        } else if (obj == LottieProperty.POLYSTAR_INNER_ROUNDEDNESS && (baseKeyframeAnimation = this.innerRoundednessAnimation) != null) {
            baseKeyframeAnimation.setValueCallback(lottieValueCallback);
        } else if (obj == LottieProperty.POLYSTAR_OUTER_ROUNDEDNESS) {
            this.outerRoundednessAnimation.setValueCallback(lottieValueCallback);
        }
    }
}
