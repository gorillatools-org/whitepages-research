package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import java.util.ArrayList;

class LineView extends RenderableView {
    private SVGLength mX1;
    private SVGLength mX2;
    private SVGLength mY1;
    private SVGLength mY2;

    public LineView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setX1(Dynamic dynamic) {
        this.mX1 = SVGLength.from(dynamic);
        invalidate();
    }

    public void setY1(Dynamic dynamic) {
        this.mY1 = SVGLength.from(dynamic);
        invalidate();
    }

    public void setX2(Dynamic dynamic) {
        this.mX2 = SVGLength.from(dynamic);
        invalidate();
    }

    public void setY2(Dynamic dynamic) {
        this.mY2 = SVGLength.from(dynamic);
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        double relativeOnWidth = relativeOnWidth(this.mX1);
        double relativeOnHeight = relativeOnHeight(this.mY1);
        double relativeOnWidth2 = relativeOnWidth(this.mX2);
        double relativeOnHeight2 = relativeOnHeight(this.mY2);
        path.moveTo((float) relativeOnWidth, (float) relativeOnHeight);
        path.lineTo((float) relativeOnWidth2, (float) relativeOnHeight2);
        ArrayList<PathElement> arrayList = new ArrayList<>();
        this.elements = arrayList;
        arrayList.add(new PathElement(ElementType.kCGPathElementMoveToPoint, new Point[]{new Point(relativeOnWidth, relativeOnHeight)}));
        this.elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(relativeOnWidth2, relativeOnHeight2)}));
        return path;
    }
}
