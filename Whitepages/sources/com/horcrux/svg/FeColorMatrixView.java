package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import java.util.HashMap;

class FeColorMatrixView extends FilterPrimitiveView {
    String mIn1;
    FilterProperties$FeColorMatrixType mType;
    ReadableArray mValues;

    public FeColorMatrixView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setIn1(String str) {
        this.mIn1 = str;
        invalidate();
    }

    public void setType(String str) {
        this.mType = FilterProperties$FeColorMatrixType.getEnum(str);
        invalidate();
    }

    public void setValues(ReadableArray readableArray) {
        this.mValues = readableArray;
        invalidate();
    }

    public Bitmap applyFilter(HashMap hashMap, Bitmap bitmap) {
        Bitmap source = FilterPrimitiveView.getSource(hashMap, bitmap, this.mIn1);
        ColorMatrix colorMatrix = new ColorMatrix();
        int i = AnonymousClass1.$SwitchMap$com$horcrux$svg$FilterProperties$FeColorMatrixType[this.mType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        colorMatrix.set(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.2125f, 0.7154f, 0.0721f, 0.0f, 0.0f});
                    }
                } else if (this.mValues.size() != 1) {
                    return source;
                } else {
                    double d = (((double) ((float) this.mValues.getDouble(0))) * 3.141592653589793d) / 180.0d;
                    float cos = (float) Math.cos(d);
                    float sin = (float) Math.sin(d);
                    float f = 0.715f - (cos * 0.715f);
                    float f2 = sin * 0.715f;
                    float f3 = 0.072f - (cos * 0.072f);
                    float f4 = 0.213f - (cos * 0.213f);
                    colorMatrix.set(new float[]{((cos * 0.787f) + 0.213f) - (sin * 0.213f), f - f2, f3 + (sin * 0.928f), 0.0f, 0.0f, f4 + (0.143f * sin), (0.285f * cos) + 0.715f + (0.14f * sin), f3 - (0.283f * sin), 0.0f, 0.0f, f4 - (0.787f * sin), f + f2, (cos * 0.928f) + 0.072f + (sin * 0.072f), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
                }
            } else if (this.mValues.size() != 1) {
                return source;
            } else {
                colorMatrix.setSaturation((float) this.mValues.getDouble(0));
            }
        } else if (this.mValues.size() < 20) {
            return source;
        } else {
            float[] fArr = new float[this.mValues.size()];
            for (int i2 = 0; i2 < this.mValues.size(); i2++) {
                fArr[i2] = ((float) this.mValues.getDouble(i2)) * ((float) (i2 % 5 == 4 ? 255 : 1));
            }
            colorMatrix.set(fArr);
        }
        return FilterUtils.getBitmapWithColorMatrix(colorMatrix, source);
    }

    /* renamed from: com.horcrux.svg.FeColorMatrixView$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$FilterProperties$FeColorMatrixType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.horcrux.svg.FilterProperties$FeColorMatrixType[] r0 = com.horcrux.svg.FilterProperties$FeColorMatrixType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$horcrux$svg$FilterProperties$FeColorMatrixType = r0
                com.horcrux.svg.FilterProperties$FeColorMatrixType r1 = com.horcrux.svg.FilterProperties$FeColorMatrixType.MATRIX     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$horcrux$svg$FilterProperties$FeColorMatrixType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.horcrux.svg.FilterProperties$FeColorMatrixType r1 = com.horcrux.svg.FilterProperties$FeColorMatrixType.SATURATE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$horcrux$svg$FilterProperties$FeColorMatrixType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.horcrux.svg.FilterProperties$FeColorMatrixType r1 = com.horcrux.svg.FilterProperties$FeColorMatrixType.HUE_ROTATE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$horcrux$svg$FilterProperties$FeColorMatrixType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.horcrux.svg.FilterProperties$FeColorMatrixType r1 = com.horcrux.svg.FilterProperties$FeColorMatrixType.LUMINANCE_TO_ALPHA     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.FeColorMatrixView.AnonymousClass1.<clinit>():void");
        }
    }
}
