package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.bridge.ReactContext;
import java.util.ArrayList;
import java.util.Iterator;

class PathView extends RenderableView {
    private Path mPath = new Path();

    public PathView(ReactContext reactContext) {
        super(reactContext);
        PathParser.mScale = this.mScale;
    }

    public void setD(String str) {
        this.mPath = PathParser.parse(str);
        ArrayList<PathElement> arrayList = PathParser.elements;
        this.elements = arrayList;
        Iterator<PathElement> it = arrayList.iterator();
        while (it.hasNext()) {
            for (Point point : it.next().points) {
                double d = point.x;
                float f = this.mScale;
                point.x = d * ((double) f);
                point.y *= (double) f;
            }
        }
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        return this.mPath;
    }
}
