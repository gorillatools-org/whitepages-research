package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;

class TextPathView extends TextView {
    private String mHref;
    private TextProperties$TextPathMethod mMethod = TextProperties$TextPathMethod.align;
    private TextProperties$TextPathMidLine mMidLine;
    private TextProperties$TextPathSide mSide;
    private TextProperties$TextPathSpacing mSpacing = TextProperties$TextPathSpacing.exact;
    private SVGLength mStartOffset;

    /* access modifiers changed from: package-private */
    public void popGlyphContext() {
    }

    /* access modifiers changed from: package-private */
    public void pushGlyphContext() {
    }

    public TextPathView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setHref(String str) {
        this.mHref = str;
        invalidate();
    }

    public void setStartOffset(Dynamic dynamic) {
        this.mStartOffset = SVGLength.from(dynamic);
        invalidate();
    }

    public void setMethod(String str) {
        this.mMethod = TextProperties$TextPathMethod.valueOf(str);
        invalidate();
    }

    public void setSpacing(String str) {
        this.mSpacing = TextProperties$TextPathSpacing.valueOf(str);
        invalidate();
    }

    public void setSide(String str) {
        this.mSide = TextProperties$TextPathSide.valueOf(str);
        invalidate();
    }

    public void setSharp(String str) {
        this.mMidLine = TextProperties$TextPathMidLine.valueOf(str);
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public TextProperties$TextPathSide getSide() {
        return this.mSide;
    }

    /* access modifiers changed from: package-private */
    public TextProperties$TextPathMidLine getMidLine() {
        return this.mMidLine;
    }

    /* access modifiers changed from: package-private */
    public SVGLength getStartOffset() {
        return this.mStartOffset;
    }

    /* access modifiers changed from: package-private */
    public void draw(Canvas canvas, Paint paint, float f) {
        drawGroup(canvas, paint, f);
    }

    /* access modifiers changed from: package-private */
    public Path getTextPath(Canvas canvas, Paint paint) {
        VirtualView definedTemplate = getSvgView().getDefinedTemplate(this.mHref);
        if (!(definedTemplate instanceof RenderableView)) {
            return null;
        }
        return ((RenderableView) definedTemplate).getPath(canvas, paint);
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        return getGroupPath(canvas, paint);
    }
}
