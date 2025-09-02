package com.airbnb.lottie.animation.content;

import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.content.RoundedCorners;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;

public class RoundedCornersContent implements ShapeModifierContent, BaseKeyframeAnimation.AnimationListener {
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation roundedCorners;
    private ShapeData shapeData;

    public void setContents(List list, List list2) {
    }

    public RoundedCornersContent(LottieDrawable lottieDrawable2, BaseLayer baseLayer, RoundedCorners roundedCorners2) {
        this.lottieDrawable = lottieDrawable2;
        this.name = roundedCorners2.getName();
        BaseKeyframeAnimation createAnimation = roundedCorners2.getCornerRadius().createAnimation();
        this.roundedCorners = createAnimation;
        baseLayer.addAnimation(createAnimation);
        createAnimation.addUpdateListener(this);
    }

    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    public BaseKeyframeAnimation getRoundedCorners() {
        return this.roundedCorners;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x009c, code lost:
        if (r7 != (r0.size() - 1)) goto L_0x009f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.airbnb.lottie.model.content.ShapeData modifyShape(com.airbnb.lottie.model.content.ShapeData r19) {
        /*
            r18 = this;
            java.util.List r0 = r19.getCurves()
            int r1 = r0.size()
            r2 = 2
            if (r1 > r2) goto L_0x000c
            return r19
        L_0x000c:
            r1 = r18
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r2 = r1.roundedCorners
            java.lang.Object r2 = r2.getValue()
            java.lang.Float r2 = (java.lang.Float) r2
            float r2 = r2.floatValue()
            r3 = 0
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 != 0) goto L_0x0020
            return r19
        L_0x0020:
            com.airbnb.lottie.model.content.ShapeData r3 = r18.getShapeData(r19)
            android.graphics.PointF r4 = r19.getInitialPoint()
            float r4 = r4.x
            android.graphics.PointF r5 = r19.getInitialPoint()
            float r5 = r5.y
            r3.setInitialPoint(r4, r5)
            java.util.List r4 = r3.getCurves()
            boolean r5 = r19.isClosed()
            r7 = 0
            r8 = 0
        L_0x003d:
            int r9 = r0.size()
            if (r7 >= r9) goto L_0x0195
            java.lang.Object r9 = r0.get(r7)
            com.airbnb.lottie.model.CubicCurveData r9 = (com.airbnb.lottie.model.CubicCurveData) r9
            int r10 = r7 + -1
            int r11 = r0.size()
            int r10 = floorMod(r10, r11)
            java.lang.Object r10 = r0.get(r10)
            com.airbnb.lottie.model.CubicCurveData r10 = (com.airbnb.lottie.model.CubicCurveData) r10
            int r11 = r7 + -2
            int r12 = r0.size()
            int r11 = floorMod(r11, r12)
            java.lang.Object r11 = r0.get(r11)
            com.airbnb.lottie.model.CubicCurveData r11 = (com.airbnb.lottie.model.CubicCurveData) r11
            if (r7 != 0) goto L_0x0072
            if (r5 != 0) goto L_0x0072
            android.graphics.PointF r12 = r19.getInitialPoint()
            goto L_0x0076
        L_0x0072:
            android.graphics.PointF r12 = r10.getVertex()
        L_0x0076:
            if (r7 != 0) goto L_0x007c
            if (r5 != 0) goto L_0x007c
            r13 = r12
            goto L_0x0080
        L_0x007c:
            android.graphics.PointF r13 = r10.getControlPoint2()
        L_0x0080:
            android.graphics.PointF r14 = r9.getControlPoint1()
            android.graphics.PointF r11 = r11.getVertex()
            android.graphics.PointF r15 = r9.getVertex()
            boolean r16 = r19.isClosed()
            if (r16 != 0) goto L_0x009f
            r16 = 1
            if (r7 == 0) goto L_0x00a1
            int r17 = r0.size()
            int r6 = r17 + -1
            if (r7 != r6) goto L_0x009f
            goto L_0x00a1
        L_0x009f:
            r16 = 0
        L_0x00a1:
            boolean r6 = r13.equals(r12)
            if (r6 == 0) goto L_0x0146
            boolean r6 = r14.equals(r12)
            if (r6 == 0) goto L_0x0146
            if (r16 != 0) goto L_0x0146
            float r6 = r12.x
            float r9 = r11.x
            float r9 = r6 - r9
            float r10 = r12.y
            float r13 = r11.y
            float r13 = r10 - r13
            float r14 = r15.x
            float r14 = r14 - r6
            float r6 = r15.y
            float r6 = r6 - r10
            double r9 = (double) r9
            r16 = r0
            double r0 = (double) r13
            double r0 = java.lang.Math.hypot(r9, r0)
            float r0 = (float) r0
            double r9 = (double) r14
            double r13 = (double) r6
            double r9 = java.lang.Math.hypot(r9, r13)
            float r1 = (float) r9
            float r0 = r2 / r0
            r6 = 1056964608(0x3f000000, float:0.5)
            float r0 = java.lang.Math.min(r0, r6)
            float r1 = r2 / r1
            float r1 = java.lang.Math.min(r1, r6)
            float r6 = r12.x
            float r9 = r11.x
            float r9 = r9 - r6
            float r9 = r9 * r0
            float r9 = r9 + r6
            float r10 = r12.y
            float r11 = r11.y
            float r11 = r11 - r10
            float r11 = r11 * r0
            float r11 = r11 + r10
            float r0 = r15.x
            float r0 = r0 - r6
            float r0 = r0 * r1
            float r0 = r0 + r6
            float r12 = r15.y
            float r12 = r12 - r10
            float r12 = r12 * r1
            float r12 = r12 + r10
            float r1 = r9 - r6
            r13 = 1057835346(0x3f0d4952, float:0.5519)
            float r1 = r1 * r13
            float r1 = r9 - r1
            float r14 = r11 - r10
            float r14 = r14 * r13
            float r14 = r11 - r14
            float r6 = r0 - r6
            float r6 = r6 * r13
            float r6 = r0 - r6
            float r10 = r12 - r10
            float r10 = r10 * r13
            float r10 = r12 - r10
            int r13 = r8 + -1
            int r15 = r4.size()
            int r13 = floorMod(r13, r15)
            java.lang.Object r13 = r4.get(r13)
            com.airbnb.lottie.model.CubicCurveData r13 = (com.airbnb.lottie.model.CubicCurveData) r13
            java.lang.Object r15 = r4.get(r8)
            com.airbnb.lottie.model.CubicCurveData r15 = (com.airbnb.lottie.model.CubicCurveData) r15
            r13.setControlPoint2(r9, r11)
            r13.setVertex(r9, r11)
            if (r7 != 0) goto L_0x012f
            r3.setInitialPoint(r9, r11)
        L_0x012f:
            r15.setControlPoint1(r1, r14)
            int r1 = r8 + 1
            java.lang.Object r1 = r4.get(r1)
            com.airbnb.lottie.model.CubicCurveData r1 = (com.airbnb.lottie.model.CubicCurveData) r1
            r15.setControlPoint2(r6, r10)
            r15.setVertex(r0, r12)
            r1.setControlPoint1(r0, r12)
            int r8 = r8 + 2
            goto L_0x018d
        L_0x0146:
            r16 = r0
            int r0 = r8 + -1
            int r1 = r4.size()
            int r0 = floorMod(r0, r1)
            java.lang.Object r0 = r4.get(r0)
            com.airbnb.lottie.model.CubicCurveData r0 = (com.airbnb.lottie.model.CubicCurveData) r0
            java.lang.Object r1 = r4.get(r8)
            com.airbnb.lottie.model.CubicCurveData r1 = (com.airbnb.lottie.model.CubicCurveData) r1
            android.graphics.PointF r6 = r10.getControlPoint2()
            float r6 = r6.x
            android.graphics.PointF r11 = r10.getControlPoint2()
            float r11 = r11.y
            r0.setControlPoint2(r6, r11)
            android.graphics.PointF r6 = r10.getVertex()
            float r6 = r6.x
            android.graphics.PointF r10 = r10.getVertex()
            float r10 = r10.y
            r0.setVertex(r6, r10)
            android.graphics.PointF r0 = r9.getControlPoint1()
            float r0 = r0.x
            android.graphics.PointF r6 = r9.getControlPoint1()
            float r6 = r6.y
            r1.setControlPoint1(r0, r6)
            int r8 = r8 + 1
        L_0x018d:
            int r7 = r7 + 1
            r1 = r18
            r0 = r16
            goto L_0x003d
        L_0x0195:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.content.RoundedCornersContent.modifyShape(com.airbnb.lottie.model.content.ShapeData):com.airbnb.lottie.model.content.ShapeData");
    }

    private ShapeData getShapeData(ShapeData shapeData2) {
        PointF pointF;
        List curves = shapeData2.getCurves();
        boolean isClosed = shapeData2.isClosed();
        int size = curves.size() - 1;
        int i = 0;
        while (size >= 0) {
            CubicCurveData cubicCurveData = (CubicCurveData) curves.get(size);
            CubicCurveData cubicCurveData2 = (CubicCurveData) curves.get(floorMod(size - 1, curves.size()));
            PointF vertex = (size != 0 || isClosed) ? cubicCurveData2.getVertex() : shapeData2.getInitialPoint();
            if (size != 0 || isClosed) {
                pointF = cubicCurveData2.getControlPoint2();
            } else {
                pointF = vertex;
            }
            i = (!pointF.equals(vertex) || !cubicCurveData.getControlPoint1().equals(vertex) || (!shapeData2.isClosed() && (size == 0 || size == curves.size() - 1))) ? i + 1 : i + 2;
            size--;
        }
        ShapeData shapeData3 = this.shapeData;
        if (shapeData3 == null || shapeData3.getCurves().size() != i) {
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(new CubicCurveData());
            }
            this.shapeData = new ShapeData(new PointF(0.0f, 0.0f), false, arrayList);
        }
        this.shapeData.setClosed(isClosed);
        return this.shapeData;
    }

    private static int floorMod(int i, int i2) {
        return i - (floorDiv(i, i2) * i2);
    }

    private static int floorDiv(int i, int i2) {
        int i3 = i / i2;
        return ((i ^ i2) >= 0 || i2 * i3 == i) ? i3 : i3 - 1;
    }
}
