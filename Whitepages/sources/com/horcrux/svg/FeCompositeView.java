package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import com.facebook.react.bridge.ReactContext;
import java.util.HashMap;

class FeCompositeView extends FilterPrimitiveView {
    String mIn1;
    String mIn2;
    float mK1;
    float mK2;
    float mK3;
    float mK4;
    FilterProperties$FeCompositeOperator mOperator;

    public FeCompositeView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setIn1(String str) {
        this.mIn1 = str;
        invalidate();
    }

    public void setIn2(String str) {
        this.mIn2 = str;
        invalidate();
    }

    public void setK1(Float f) {
        this.mK1 = f.floatValue();
        invalidate();
    }

    public void setK2(Float f) {
        this.mK2 = f.floatValue();
        invalidate();
    }

    public void setK3(Float f) {
        this.mK3 = f.floatValue();
        invalidate();
    }

    public void setK4(Float f) {
        this.mK4 = f.floatValue();
        invalidate();
    }

    public void setOperator(String str) {
        this.mOperator = FilterProperties$FeCompositeOperator.getEnum(str);
        invalidate();
    }

    public Bitmap applyFilter(HashMap hashMap, Bitmap bitmap) {
        Canvas canvas;
        Bitmap bitmap2;
        HashMap hashMap2 = hashMap;
        Bitmap bitmap3 = bitmap;
        Bitmap source = FilterPrimitiveView.getSource(hashMap2, bitmap3, this.mIn1);
        Bitmap source2 = FilterPrimitiveView.getSource(hashMap2, bitmap3, this.mIn2);
        Bitmap createBitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        canvas2.drawBitmap(source, 0.0f, 0.0f, paint);
        switch (AnonymousClass1.$SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator[this.mOperator.ordinal()]) {
            case 1:
                bitmap2 = source2;
                canvas = canvas2;
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
                break;
            case 2:
                bitmap2 = source2;
                canvas = canvas2;
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                break;
            case 3:
                bitmap2 = source2;
                canvas = canvas2;
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
                break;
            case 4:
                bitmap2 = source2;
                canvas = canvas2;
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
                break;
            case 5:
                bitmap2 = source2;
                canvas = canvas2;
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
                break;
            case 6:
                int width = createBitmap.getWidth() * createBitmap.getHeight();
                int[] iArr = new int[width];
                int[] iArr2 = new int[width];
                createBitmap.getPixels(iArr, 0, createBitmap.getWidth(), 0, 0, createBitmap.getWidth(), createBitmap.getHeight());
                int[] iArr3 = iArr;
                source2.getPixels(iArr2, 0, createBitmap.getWidth(), 0, 0, createBitmap.getWidth(), createBitmap.getHeight());
                int i = 0;
                while (i < width) {
                    int i2 = iArr3[i];
                    int i3 = iArr2[i];
                    int i4 = width;
                    float f = this.mK1;
                    float f2 = (float) ((i2 >> 16) & 255);
                    float f3 = (float) ((i3 >> 16) & 255);
                    Bitmap bitmap4 = source2;
                    float f4 = this.mK2;
                    float f5 = (f * f2 * f3) + (f2 * f4);
                    float f6 = this.mK3;
                    float f7 = f5 + (f3 * f6);
                    float f8 = this.mK4;
                    Canvas canvas3 = canvas2;
                    float f9 = (float) ((i2 >> 8) & 255);
                    float f10 = (float) ((i3 >> 8) & 255);
                    float f11 = (float) (i2 & 255);
                    float f12 = (float) (i3 & 255);
                    float f13 = (float) (i2 >>> 24);
                    float f14 = (float) (i3 >>> 24);
                    int min = Math.min(255, Math.max(0, (int) (f7 + f8)));
                    int min2 = Math.min(255, Math.max(0, (int) ((f * f9 * f10) + (f9 * f4) + (f10 * f6) + f8)));
                    iArr3[i] = (Math.min(255, Math.max(0, (int) (((((f * f13) * f14) + (f4 * f13)) + (f6 * f14)) + f8))) << 24) | (min << 16) | (min2 << 8) | Math.min(255, Math.max(0, (int) ((f * f11 * f12) + (f11 * f4) + (f12 * f6) + f8)));
                    i++;
                    width = i4;
                    source2 = bitmap4;
                    canvas2 = canvas3;
                }
                bitmap2 = source2;
                canvas = canvas2;
                createBitmap.setPixels(iArr3, 0, createBitmap.getWidth(), 0, 0, createBitmap.getWidth(), createBitmap.getHeight());
                break;
            default:
                bitmap2 = source2;
                canvas = canvas2;
                break;
        }
        if (this.mOperator != FilterProperties$FeCompositeOperator.ARITHMETIC) {
            canvas.drawBitmap(bitmap2, 0.0f, 0.0f, paint);
        }
        return createBitmap;
    }

    /* renamed from: com.horcrux.svg.FeCompositeView$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.horcrux.svg.FilterProperties$FeCompositeOperator[] r0 = com.horcrux.svg.FilterProperties$FeCompositeOperator.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator = r0
                com.horcrux.svg.FilterProperties$FeCompositeOperator r1 = com.horcrux.svg.FilterProperties$FeCompositeOperator.OVER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator     // Catch:{ NoSuchFieldError -> 0x001d }
                com.horcrux.svg.FilterProperties$FeCompositeOperator r1 = com.horcrux.svg.FilterProperties$FeCompositeOperator.IN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.horcrux.svg.FilterProperties$FeCompositeOperator r1 = com.horcrux.svg.FilterProperties$FeCompositeOperator.OUT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.horcrux.svg.FilterProperties$FeCompositeOperator r1 = com.horcrux.svg.FilterProperties$FeCompositeOperator.ATOP     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator     // Catch:{ NoSuchFieldError -> 0x003e }
                com.horcrux.svg.FilterProperties$FeCompositeOperator r1 = com.horcrux.svg.FilterProperties$FeCompositeOperator.XOR     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$horcrux$svg$FilterProperties$FeCompositeOperator     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.horcrux.svg.FilterProperties$FeCompositeOperator r1 = com.horcrux.svg.FilterProperties$FeCompositeOperator.ARITHMETIC     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.FeCompositeView.AnonymousClass1.<clinit>():void");
        }
    }
}
