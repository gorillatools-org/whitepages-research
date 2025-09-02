package com.horcrux.svg;

import android.graphics.Bitmap;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import java.util.HashMap;

abstract class FilterPrimitiveView extends DefinitionView {
    public final FilterRegion mFilterSubregion = new FilterRegion();
    private String mResult;

    public abstract Bitmap applyFilter(HashMap hashMap, Bitmap bitmap);

    /* access modifiers changed from: package-private */
    public void saveDefinition() {
    }

    public FilterPrimitiveView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setX(Dynamic dynamic) {
        this.mFilterSubregion.setX(dynamic);
        invalidate();
    }

    public void setY(Dynamic dynamic) {
        this.mFilterSubregion.setY(dynamic);
        invalidate();
    }

    public void setWidth(Dynamic dynamic) {
        this.mFilterSubregion.setWidth(dynamic);
        invalidate();
    }

    public void setHeight(Dynamic dynamic) {
        this.mFilterSubregion.setHeight(dynamic);
        invalidate();
    }

    public void setResult(String str) {
        this.mResult = str;
        invalidate();
    }

    public String getResult() {
        return this.mResult;
    }

    protected static Bitmap getSource(HashMap hashMap, Bitmap bitmap, String str) {
        Bitmap bitmap2 = str != null ? (Bitmap) hashMap.get(str) : null;
        return bitmap2 != null ? bitmap2 : bitmap;
    }
}
