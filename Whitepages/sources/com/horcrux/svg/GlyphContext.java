package com.horcrux.svg;

import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;

class GlyphContext {
    private double mDX;
    private int mDXIndex;
    private final ArrayList mDXIndices;
    private SVGLength[] mDXs;
    private final ArrayList mDXsContext;
    private int mDXsIndex;
    private final ArrayList mDXsIndices = new ArrayList();
    private double mDY;
    private int mDYIndex;
    private final ArrayList mDYIndices;
    private SVGLength[] mDYs;
    private final ArrayList mDYsContext;
    private int mDYsIndex;
    private final ArrayList mDYsIndices = new ArrayList();
    final ArrayList mFontContext;
    private double mFontSize = 12.0d;
    private final float mHeight;
    private int mRIndex;
    private final ArrayList mRIndices;
    private double[] mRs;
    private final ArrayList mRsContext;
    private int mRsIndex;
    private final ArrayList mRsIndices = new ArrayList();
    private final float mScale;
    private int mTop;
    private final float mWidth;
    private double mX;
    private int mXIndex;
    private final ArrayList mXIndices;
    private SVGLength[] mXs;
    private final ArrayList mXsContext;
    private int mXsIndex;
    private final ArrayList mXsIndices = new ArrayList();
    private double mY;
    private int mYIndex;
    private final ArrayList mYIndices;
    private SVGLength[] mYs;
    private final ArrayList mYsContext;
    private int mYsIndex;
    private final ArrayList mYsIndices = new ArrayList();
    private FontData topFont = FontData.Defaults;

    private void pushIndices() {
        this.mXsIndices.add(Integer.valueOf(this.mXsIndex));
        this.mYsIndices.add(Integer.valueOf(this.mYsIndex));
        this.mDXsIndices.add(Integer.valueOf(this.mDXsIndex));
        this.mDYsIndices.add(Integer.valueOf(this.mDYsIndex));
        this.mRsIndices.add(Integer.valueOf(this.mRsIndex));
    }

    GlyphContext(float f, float f2, float f3) {
        ArrayList arrayList = new ArrayList();
        this.mFontContext = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.mXsContext = arrayList2;
        ArrayList arrayList3 = new ArrayList();
        this.mYsContext = arrayList3;
        ArrayList arrayList4 = new ArrayList();
        this.mDXsContext = arrayList4;
        ArrayList arrayList5 = new ArrayList();
        this.mDYsContext = arrayList5;
        ArrayList arrayList6 = new ArrayList();
        this.mRsContext = arrayList6;
        ArrayList arrayList7 = new ArrayList();
        this.mXIndices = arrayList7;
        ArrayList arrayList8 = new ArrayList();
        this.mYIndices = arrayList8;
        ArrayList arrayList9 = new ArrayList();
        this.mDXIndices = arrayList9;
        ArrayList arrayList10 = new ArrayList();
        this.mDYIndices = arrayList10;
        ArrayList arrayList11 = new ArrayList();
        this.mRIndices = arrayList11;
        SVGLength[] sVGLengthArr = new SVGLength[0];
        this.mXs = sVGLengthArr;
        this.mYs = new SVGLength[0];
        this.mDXs = new SVGLength[0];
        this.mDYs = new SVGLength[0];
        this.mRs = new double[]{0.0d};
        this.mXIndex = -1;
        this.mYIndex = -1;
        this.mDXIndex = -1;
        this.mDYIndex = -1;
        this.mRIndex = -1;
        this.mScale = f;
        this.mWidth = f2;
        this.mHeight = f3;
        arrayList2.add(sVGLengthArr);
        arrayList3.add(this.mYs);
        arrayList4.add(this.mDXs);
        arrayList5.add(this.mDYs);
        arrayList6.add(this.mRs);
        arrayList7.add(Integer.valueOf(this.mXIndex));
        arrayList8.add(Integer.valueOf(this.mYIndex));
        arrayList9.add(Integer.valueOf(this.mDXIndex));
        arrayList10.add(Integer.valueOf(this.mDYIndex));
        arrayList11.add(Integer.valueOf(this.mRIndex));
        arrayList.add(this.topFont);
        pushIndices();
    }

    private void reset() {
        this.mRsIndex = 0;
        this.mDYsIndex = 0;
        this.mDXsIndex = 0;
        this.mYsIndex = 0;
        this.mXsIndex = 0;
        this.mRIndex = -1;
        this.mDYIndex = -1;
        this.mDXIndex = -1;
        this.mYIndex = -1;
        this.mXIndex = -1;
        this.mDY = 0.0d;
        this.mDX = 0.0d;
        this.mY = 0.0d;
        this.mX = 0.0d;
    }

    /* access modifiers changed from: package-private */
    public FontData getFont() {
        return this.topFont;
    }

    private FontData getTopOrParentFont(GroupView groupView) {
        if (this.mTop > 0) {
            return this.topFont;
        }
        for (GroupView parentTextRoot = groupView.getParentTextRoot(); parentTextRoot != null; parentTextRoot = parentTextRoot.getParentTextRoot()) {
            FontData font = parentTextRoot.getGlyphContext().getFont();
            if (font != FontData.Defaults) {
                return font;
            }
        }
        return FontData.Defaults;
    }

    private void pushNodeAndFont(GroupView groupView, ReadableMap readableMap) {
        FontData topOrParentFont = getTopOrParentFont(groupView);
        this.mTop++;
        if (readableMap == null) {
            this.mFontContext.add(topOrParentFont);
            return;
        }
        FontData fontData = new FontData(readableMap, topOrParentFont, (double) this.mScale);
        this.mFontSize = fontData.fontSize;
        this.mFontContext.add(fontData);
        this.topFont = fontData;
    }

    /* access modifiers changed from: package-private */
    public void pushContext(GroupView groupView, ReadableMap readableMap) {
        pushNodeAndFont(groupView, readableMap);
        pushIndices();
    }

    private SVGLength[] getStringArrayFromReadableArray(ArrayList arrayList) {
        int size = arrayList.size();
        SVGLength[] sVGLengthArr = new SVGLength[size];
        for (int i = 0; i < size; i++) {
            sVGLengthArr[i] = (SVGLength) arrayList.get(i);
        }
        return sVGLengthArr;
    }

    private double[] getDoubleArrayFromReadableArray(ArrayList arrayList) {
        int size = arrayList.size();
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = ((SVGLength) arrayList.get(i)).value;
        }
        return dArr;
    }

    /* access modifiers changed from: package-private */
    public void pushContext(boolean z, TextView textView, ReadableMap readableMap, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4, ArrayList arrayList5) {
        if (z) {
            reset();
        }
        pushNodeAndFont(textView, readableMap);
        if (!(arrayList == null || arrayList.size() == 0)) {
            this.mXsIndex++;
            this.mXIndex = -1;
            this.mXIndices.add(-1);
            SVGLength[] stringArrayFromReadableArray = getStringArrayFromReadableArray(arrayList);
            this.mXs = stringArrayFromReadableArray;
            this.mXsContext.add(stringArrayFromReadableArray);
        }
        if (!(arrayList2 == null || arrayList2.size() == 0)) {
            this.mYsIndex++;
            this.mYIndex = -1;
            this.mYIndices.add(-1);
            SVGLength[] stringArrayFromReadableArray2 = getStringArrayFromReadableArray(arrayList2);
            this.mYs = stringArrayFromReadableArray2;
            this.mYsContext.add(stringArrayFromReadableArray2);
        }
        if (!(arrayList3 == null || arrayList3.size() == 0)) {
            this.mDXsIndex++;
            this.mDXIndex = -1;
            this.mDXIndices.add(-1);
            SVGLength[] stringArrayFromReadableArray3 = getStringArrayFromReadableArray(arrayList3);
            this.mDXs = stringArrayFromReadableArray3;
            this.mDXsContext.add(stringArrayFromReadableArray3);
        }
        if (!(arrayList4 == null || arrayList4.size() == 0)) {
            this.mDYsIndex++;
            this.mDYIndex = -1;
            this.mDYIndices.add(-1);
            SVGLength[] stringArrayFromReadableArray4 = getStringArrayFromReadableArray(arrayList4);
            this.mDYs = stringArrayFromReadableArray4;
            this.mDYsContext.add(stringArrayFromReadableArray4);
        }
        if (!(arrayList5 == null || arrayList5.size() == 0)) {
            this.mRsIndex++;
            this.mRIndex = -1;
            this.mRIndices.add(-1);
            double[] doubleArrayFromReadableArray = getDoubleArrayFromReadableArray(arrayList5);
            this.mRs = doubleArrayFromReadableArray;
            this.mRsContext.add(doubleArrayFromReadableArray);
        }
        pushIndices();
    }

    /* access modifiers changed from: package-private */
    public void popContext() {
        this.mFontContext.remove(this.mTop);
        this.mXsIndices.remove(this.mTop);
        this.mYsIndices.remove(this.mTop);
        this.mDXsIndices.remove(this.mTop);
        this.mDYsIndices.remove(this.mTop);
        this.mRsIndices.remove(this.mTop);
        int i = this.mTop - 1;
        this.mTop = i;
        int i2 = this.mXsIndex;
        int i3 = this.mYsIndex;
        int i4 = this.mDXsIndex;
        int i5 = this.mDYsIndex;
        int i6 = this.mRsIndex;
        this.topFont = (FontData) this.mFontContext.get(i);
        this.mXsIndex = ((Integer) this.mXsIndices.get(this.mTop)).intValue();
        this.mYsIndex = ((Integer) this.mYsIndices.get(this.mTop)).intValue();
        this.mDXsIndex = ((Integer) this.mDXsIndices.get(this.mTop)).intValue();
        this.mDYsIndex = ((Integer) this.mDYsIndices.get(this.mTop)).intValue();
        this.mRsIndex = ((Integer) this.mRsIndices.get(this.mTop)).intValue();
        if (i2 != this.mXsIndex) {
            this.mXsContext.remove(i2);
            this.mXs = (SVGLength[]) this.mXsContext.get(this.mXsIndex);
            this.mXIndex = ((Integer) this.mXIndices.get(this.mXsIndex)).intValue();
        }
        if (i3 != this.mYsIndex) {
            this.mYsContext.remove(i3);
            this.mYs = (SVGLength[]) this.mYsContext.get(this.mYsIndex);
            this.mYIndex = ((Integer) this.mYIndices.get(this.mYsIndex)).intValue();
        }
        if (i4 != this.mDXsIndex) {
            this.mDXsContext.remove(i4);
            this.mDXs = (SVGLength[]) this.mDXsContext.get(this.mDXsIndex);
            this.mDXIndex = ((Integer) this.mDXIndices.get(this.mDXsIndex)).intValue();
        }
        if (i5 != this.mDYsIndex) {
            this.mDYsContext.remove(i5);
            this.mDYs = (SVGLength[]) this.mDYsContext.get(this.mDYsIndex);
            this.mDYIndex = ((Integer) this.mDYIndices.get(this.mDYsIndex)).intValue();
        }
        if (i6 != this.mRsIndex) {
            this.mRsContext.remove(i6);
            this.mRs = (double[]) this.mRsContext.get(this.mRsIndex);
            this.mRIndex = ((Integer) this.mRIndices.get(this.mRsIndex)).intValue();
        }
    }

    private static void incrementIndices(ArrayList arrayList, int i) {
        while (i >= 0) {
            arrayList.set(i, Integer.valueOf(((Integer) arrayList.get(i)).intValue() + 1));
            i--;
        }
    }

    /* access modifiers changed from: package-private */
    public double getFontSize() {
        return this.mFontSize;
    }

    /* access modifiers changed from: package-private */
    public double nextX(double d) {
        incrementIndices(this.mXIndices, this.mXsIndex);
        int i = this.mXIndex + 1;
        SVGLength[] sVGLengthArr = this.mXs;
        if (i < sVGLengthArr.length) {
            this.mDX = 0.0d;
            this.mXIndex = i;
            this.mX = PropHelper.fromRelative(sVGLengthArr[i], (double) this.mWidth, 0.0d, (double) this.mScale, this.mFontSize);
        }
        double d2 = this.mX + d;
        this.mX = d2;
        return d2;
    }

    /* access modifiers changed from: package-private */
    public double nextY() {
        incrementIndices(this.mYIndices, this.mYsIndex);
        int i = this.mYIndex + 1;
        SVGLength[] sVGLengthArr = this.mYs;
        if (i < sVGLengthArr.length) {
            this.mDY = 0.0d;
            this.mYIndex = i;
            this.mY = PropHelper.fromRelative(sVGLengthArr[i], (double) this.mHeight, 0.0d, (double) this.mScale, this.mFontSize);
        }
        return this.mY;
    }

    /* access modifiers changed from: package-private */
    public double nextDeltaX() {
        incrementIndices(this.mDXIndices, this.mDXsIndex);
        int i = this.mDXIndex + 1;
        SVGLength[] sVGLengthArr = this.mDXs;
        if (i < sVGLengthArr.length) {
            this.mDXIndex = i;
            this.mDX += PropHelper.fromRelative(sVGLengthArr[i], (double) this.mWidth, 0.0d, (double) this.mScale, this.mFontSize);
        }
        return this.mDX;
    }

    /* access modifiers changed from: package-private */
    public double nextDeltaY() {
        incrementIndices(this.mDYIndices, this.mDYsIndex);
        int i = this.mDYIndex + 1;
        SVGLength[] sVGLengthArr = this.mDYs;
        if (i < sVGLengthArr.length) {
            this.mDYIndex = i;
            this.mDY += PropHelper.fromRelative(sVGLengthArr[i], (double) this.mHeight, 0.0d, (double) this.mScale, this.mFontSize);
        }
        return this.mDY;
    }

    /* access modifiers changed from: package-private */
    public double nextRotation() {
        incrementIndices(this.mRIndices, this.mRsIndex);
        int min = Math.min(this.mRIndex + 1, this.mRs.length - 1);
        this.mRIndex = min;
        return this.mRs[min];
    }

    /* access modifiers changed from: package-private */
    public float getWidth() {
        return this.mWidth;
    }

    /* access modifiers changed from: package-private */
    public float getHeight() {
        return this.mHeight;
    }
}
