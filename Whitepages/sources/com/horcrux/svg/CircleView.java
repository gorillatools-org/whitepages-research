package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import java.util.ArrayList;

class CircleView extends RenderableView {
    private SVGLength mCx;
    private SVGLength mCy;
    private SVGLength mR;

    public CircleView(ReactContext reactContext) {
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

    public void setR(Dynamic dynamic) {
        this.mR = SVGLength.from(dynamic);
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        double relativeOnWidth = relativeOnWidth(this.mCx);
        double relativeOnHeight = relativeOnHeight(this.mCy);
        double relativeOnOther = relativeOnOther(this.mR);
        path.addCircle((float) relativeOnWidth, (float) relativeOnHeight, (float) relativeOnOther, Path.Direction.CW);
        ArrayList<PathElement> arrayList = new ArrayList<>();
        this.elements = arrayList;
        double d = relativeOnHeight - relativeOnOther;
        arrayList.add(new PathElement(ElementType.kCGPathElementMoveToPoint, new Point[]{new Point(relativeOnWidth, d)}));
        ArrayList<PathElement> arrayList2 = this.elements;
        ElementType elementType = ElementType.kCGPathElementAddLineToPoint;
        Point point = new Point(relativeOnWidth, d);
        double d2 = d;
        double d3 = relativeOnWidth + relativeOnOther;
        arrayList2.add(new PathElement(elementType, new Point[]{point, new Point(d3, relativeOnHeight)}));
        double d4 = relativeOnHeight + relativeOnOther;
        this.elements.add(new PathElement(elementType, new Point[]{new Point(d3, relativeOnHeight), new Point(relativeOnWidth, d4)}));
        double d5 = relativeOnWidth - relativeOnOther;
        this.elements.add(new PathElement(elementType, new Point[]{new Point(relativeOnWidth, d4), new Point(d5, relativeOnHeight)}));
        this.elements.add(new PathElement(elementType, new Point[]{new Point(d5, relativeOnHeight), new Point(relativeOnWidth, d2)}));
        return path;
    }
}
