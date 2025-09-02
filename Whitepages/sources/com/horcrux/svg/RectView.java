package com.horcrux.svg;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;

class RectView extends RenderableView {
    private SVGLength mH;
    private SVGLength mRx;
    private SVGLength mRy;
    private SVGLength mW;
    private SVGLength mX;
    private SVGLength mY;

    public RectView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setX(Dynamic dynamic) {
        this.mX = SVGLength.from(dynamic);
        invalidate();
    }

    public void setY(Dynamic dynamic) {
        this.mY = SVGLength.from(dynamic);
        invalidate();
    }

    public void setWidth(Dynamic dynamic) {
        this.mW = SVGLength.from(dynamic);
        invalidate();
    }

    public void setHeight(Dynamic dynamic) {
        this.mH = SVGLength.from(dynamic);
        invalidate();
    }

    public void setRx(Dynamic dynamic) {
        this.mRx = SVGLength.from(dynamic);
        invalidate();
    }

    public void setRy(Dynamic dynamic) {
        this.mRy = SVGLength.from(dynamic);
        invalidate();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0068  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Path getPath(android.graphics.Canvas r20, android.graphics.Paint r21) {
        /*
            r19 = this;
            r0 = r19
            android.graphics.Path r9 = new android.graphics.Path
            r9.<init>()
            com.horcrux.svg.SVGLength r1 = r0.mX
            double r10 = r0.relativeOnWidth(r1)
            com.horcrux.svg.SVGLength r1 = r0.mY
            double r12 = r0.relativeOnHeight(r1)
            com.horcrux.svg.SVGLength r1 = r0.mW
            double r14 = r0.relativeOnWidth(r1)
            com.horcrux.svg.SVGLength r1 = r0.mH
            double r16 = r0.relativeOnHeight(r1)
            com.horcrux.svg.SVGLength r1 = r0.mRx
            if (r1 != 0) goto L_0x003c
            com.horcrux.svg.SVGLength r2 = r0.mRy
            if (r2 == 0) goto L_0x0028
            goto L_0x003c
        L_0x0028:
            float r2 = (float) r10
            float r3 = (float) r12
            double r4 = r10 + r14
            float r4 = (float) r4
            double r5 = r12 + r16
            float r5 = (float) r5
            android.graphics.Path$Direction r6 = android.graphics.Path.Direction.CW
            r1 = r9
            r1.addRect(r2, r3, r4, r5, r6)
            r9.close()
            r20 = r14
            goto L_0x0083
        L_0x003c:
            if (r1 != 0) goto L_0x0046
            com.horcrux.svg.SVGLength r1 = r0.mRy
            double r1 = r0.relativeOnHeight(r1)
        L_0x0044:
            r3 = r1
            goto L_0x0059
        L_0x0046:
            com.horcrux.svg.SVGLength r2 = r0.mRy
            if (r2 != 0) goto L_0x004f
            double r1 = r0.relativeOnWidth(r1)
            goto L_0x0044
        L_0x004f:
            double r1 = r0.relativeOnWidth(r1)
            com.horcrux.svg.SVGLength r3 = r0.mRy
            double r3 = r0.relativeOnHeight(r3)
        L_0x0059:
            r5 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r7 = r14 / r5
            int r18 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r18 <= 0) goto L_0x0062
            r1 = r7
        L_0x0062:
            double r5 = r16 / r5
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0069
            r3 = r5
        L_0x0069:
            float r5 = (float) r10
            float r6 = (float) r12
            double r7 = r10 + r14
            float r7 = (float) r7
            r20 = r14
            double r14 = r12 + r16
            float r8 = (float) r14
            float r14 = (float) r1
            float r15 = (float) r3
            android.graphics.Path$Direction r18 = android.graphics.Path.Direction.CW
            r1 = r9
            r2 = r5
            r3 = r6
            r4 = r7
            r5 = r8
            r6 = r14
            r7 = r15
            r8 = r18
            r1.addRoundRect(r2, r3, r4, r5, r6, r7, r8)
        L_0x0083:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r0.elements = r1
            com.horcrux.svg.PathElement r2 = new com.horcrux.svg.PathElement
            com.horcrux.svg.ElementType r3 = com.horcrux.svg.ElementType.kCGPathElementMoveToPoint
            com.horcrux.svg.Point r4 = new com.horcrux.svg.Point
            r4.<init>(r10, r12)
            com.horcrux.svg.Point[] r4 = new com.horcrux.svg.Point[]{r4}
            r2.<init>(r3, r4)
            r1.add(r2)
            java.util.ArrayList<com.horcrux.svg.PathElement> r1 = r0.elements
            com.horcrux.svg.PathElement r2 = new com.horcrux.svg.PathElement
            com.horcrux.svg.ElementType r3 = com.horcrux.svg.ElementType.kCGPathElementAddLineToPoint
            com.horcrux.svg.Point r4 = new com.horcrux.svg.Point
            r5 = r20
            double r14 = r10 + r5
            r4.<init>(r14, r12)
            com.horcrux.svg.Point[] r4 = new com.horcrux.svg.Point[]{r4}
            r2.<init>(r3, r4)
            r1.add(r2)
            java.util.ArrayList<com.horcrux.svg.PathElement> r1 = r0.elements
            com.horcrux.svg.PathElement r2 = new com.horcrux.svg.PathElement
            com.horcrux.svg.Point r4 = new com.horcrux.svg.Point
            double r5 = r12 + r16
            r4.<init>(r14, r5)
            com.horcrux.svg.Point[] r4 = new com.horcrux.svg.Point[]{r4}
            r2.<init>(r3, r4)
            r1.add(r2)
            java.util.ArrayList<com.horcrux.svg.PathElement> r1 = r0.elements
            com.horcrux.svg.PathElement r2 = new com.horcrux.svg.PathElement
            com.horcrux.svg.Point r4 = new com.horcrux.svg.Point
            r4.<init>(r10, r5)
            com.horcrux.svg.Point[] r4 = new com.horcrux.svg.Point[]{r4}
            r2.<init>(r3, r4)
            r1.add(r2)
            java.util.ArrayList<com.horcrux.svg.PathElement> r1 = r0.elements
            com.horcrux.svg.PathElement r2 = new com.horcrux.svg.PathElement
            com.horcrux.svg.Point r4 = new com.horcrux.svg.Point
            r4.<init>(r10, r12)
            com.horcrux.svg.Point[] r4 = new com.horcrux.svg.Point[]{r4}
            r2.<init>(r3, r4)
            r1.add(r2)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.RectView.getPath(android.graphics.Canvas, android.graphics.Paint):android.graphics.Path");
    }
}
