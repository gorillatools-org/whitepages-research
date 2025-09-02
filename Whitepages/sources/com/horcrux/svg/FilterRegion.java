package com.horcrux.svg;

import android.graphics.Rect;
import android.graphics.RectF;
import com.facebook.react.bridge.Dynamic;
import com.horcrux.svg.SVGLength;

public class FilterRegion {
    SVGLength mH;
    SVGLength mW;
    SVGLength mX;
    SVGLength mY;

    public void setX(Dynamic dynamic) {
        this.mX = SVGLength.from(dynamic);
    }

    public void setY(Dynamic dynamic) {
        this.mY = SVGLength.from(dynamic);
    }

    public void setWidth(Dynamic dynamic) {
        this.mW = SVGLength.from(dynamic);
    }

    public void setHeight(Dynamic dynamic) {
        this.mH = SVGLength.from(dynamic);
    }

    private double getRelativeOrDefault(VirtualView virtualView, SVGLength sVGLength, float f, double d) {
        return (sVGLength == null || sVGLength.unit == SVGLength.UnitType.UNKNOWN) ? d : virtualView.relativeOn(sVGLength, f);
    }

    public Rect getCropRect(VirtualView virtualView, FilterProperties$Units filterProperties$Units, RectF rectF) {
        double d;
        double d2;
        double d3;
        double d4;
        VirtualView virtualView2 = virtualView;
        RectF rectF2 = rectF;
        if (rectF2 == null) {
            return new Rect(0, 0, 0, 0);
        }
        if (filterProperties$Units == FilterProperties$Units.OBJECT_BOUNDING_BOX) {
            d4 = ((double) rectF2.left) + virtualView2.relativeOnFraction(this.mX, rectF.width());
            d3 = ((double) rectF2.top) + virtualView2.relativeOnFraction(this.mY, rectF.height());
            d2 = virtualView2.relativeOnFraction(this.mW, rectF.width());
            d = virtualView2.relativeOnFraction(this.mH, rectF.height());
        } else {
            float canvasWidth = virtualView.getSvgView().getCanvasWidth();
            float canvasHeight = virtualView.getSvgView().getCanvasHeight();
            VirtualView virtualView3 = virtualView;
            double relativeOrDefault = getRelativeOrDefault(virtualView3, this.mX, canvasWidth, (double) rectF2.left);
            double relativeOrDefault2 = getRelativeOrDefault(virtualView3, this.mY, canvasHeight, (double) rectF2.top);
            double relativeOrDefault3 = getRelativeOrDefault(virtualView3, this.mW, canvasWidth, (double) rectF.width());
            d = getRelativeOrDefault(virtualView3, this.mH, canvasHeight, (double) rectF.height());
            d4 = relativeOrDefault;
            d3 = relativeOrDefault2;
            d2 = relativeOrDefault3;
        }
        return new Rect((int) d4, (int) d3, (int) (d4 + d2), (int) (d3 + d));
    }
}
