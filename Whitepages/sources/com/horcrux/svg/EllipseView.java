package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import java.util.ArrayList;

class EllipseView extends RenderableView {
    private SVGLength mCx;
    private SVGLength mCy;
    private SVGLength mRx;
    private SVGLength mRy;

    public EllipseView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setCx(Dynamic dynamic) {
        this.mCx = SVGLength.from(dynamic);
        invalidate();
    }

    public void setCy(Dynamic dynamic) {
        this.mCy = SVGLength.from(dynamic);
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
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        double relativeOnWidth = relativeOnWidth(this.mCx);
        double relativeOnHeight = relativeOnHeight(this.mCy);
        double relativeOnWidth2 = relativeOnWidth(this.mRx);
        double relativeOnHeight2 = relativeOnHeight(this.mRy);
        double d = relativeOnWidth - relativeOnWidth2;
        double d2 = relativeOnHeight - relativeOnHeight2;
        double d3 = relativeOnWidth2 + relativeOnWidth;
        double d4 = relativeOnHeight2 + relativeOnHeight;
        double d5 = relativeOnHeight;
        path.addOval(new RectF((float) d, (float) d2, (float) d3, (float) d4), Path.Direction.CW);
        ArrayList<PathElement> arrayList = new ArrayList<>();
        this.elements = arrayList;
        arrayList.add(new PathElement(ElementType.kCGPathElementMoveToPoint, new Point[]{new Point(relativeOnWidth, d2)}));
        ArrayList<PathElement> arrayList2 = this.elements;
        ElementType elementType = ElementType.kCGPathElementAddLineToPoint;
        double d6 = d5;
        arrayList2.add(new PathElement(elementType, new Point[]{new Point(relativeOnWidth, d2), new Point(d3, d6)}));
        this.elements.add(new PathElement(elementType, new Point[]{new Point(d3, d6), new Point(relativeOnWidth, d4)}));
        ArrayList<PathElement> arrayList3 = this.elements;
        Point point = new Point(relativeOnWidth, d4);
        double d7 = d;
        arrayList3.add(new PathElement(elementType, new Point[]{point, new Point(d7, d6)}));
        this.elements.add(new PathElement(elementType, new Point[]{new Point(d7, d6), new Point(relativeOnWidth, d2)}));
        return path;
    }
}
