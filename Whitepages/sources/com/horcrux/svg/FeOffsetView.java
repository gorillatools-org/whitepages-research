package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import java.util.HashMap;

class FeOffsetView extends FilterPrimitiveView {
    SVGLength mDx;
    SVGLength mDy;
    String mIn1;

    public FeOffsetView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setIn1(String str) {
        this.mIn1 = str;
        invalidate();
    }

    public void setDx(Dynamic dynamic) {
        this.mDx = SVGLength.from(dynamic);
        invalidate();
    }

    public void setDy(Dynamic dynamic) {
        this.mDy = SVGLength.from(dynamic);
        invalidate();
    }

    public Bitmap applyFilter(HashMap hashMap, Bitmap bitmap) {
        Bitmap source = FilterPrimitiveView.getSource(hashMap, bitmap, this.mIn1);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        SVGLength sVGLength = this.mDx;
        float relativeOnWidth = sVGLength != null ? (float) relativeOnWidth(sVGLength) : 0.0f;
        SVGLength sVGLength2 = this.mDy;
        RectF rectF = new RectF(0.0f, 0.0f, relativeOnWidth, sVGLength2 != null ? (float) relativeOnHeight(sVGLength2) : 0.0f);
        getSvgView().getCtm().mapRect(rectF);
        float f = rectF.left;
        if (f >= 0.0f) {
            f = rectF.width();
        }
        float f2 = rectF.top;
        if (f2 >= 0.0f) {
            f2 = rectF.height();
        }
        canvas.drawBitmap(source, f, f2, (Paint) null);
        return createBitmap;
    }
}
