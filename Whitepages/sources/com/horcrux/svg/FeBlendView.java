package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import com.facebook.react.bridge.ReactContext;
import java.util.HashMap;

class FeBlendView extends FilterPrimitiveView {
    String mIn1;
    String mIn2;
    FilterProperties$FeBlendMode mMode;

    public FeBlendView(ReactContext reactContext) {
        super(reactContext);
        this.mFilterSubregion.mX = new SVGLength(0.0d);
        this.mFilterSubregion.mY = new SVGLength(0.0d);
        this.mFilterSubregion.mW = new SVGLength("100%");
        this.mFilterSubregion.mH = new SVGLength("100%");
    }

    public void setIn1(String str) {
        this.mIn1 = str;
        invalidate();
    }

    public void setIn2(String str) {
        this.mIn2 = str;
        invalidate();
    }

    public void setMode(String str) {
        this.mMode = FilterProperties$FeBlendMode.getEnum(str);
        invalidate();
    }

    public Bitmap applyFilter(HashMap hashMap, Bitmap bitmap) {
        Bitmap source = FilterPrimitiveView.getSource(hashMap, bitmap, this.mIn1);
        Bitmap source2 = FilterPrimitiveView.getSource(hashMap, bitmap, this.mIn2);
        if (this.mMode == FilterProperties$FeBlendMode.MULTIPLY) {
            return CustomFilter.apply(source, source2, new FeBlendView$$ExternalSyntheticLambda0());
        }
        Bitmap createBitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        canvas.drawBitmap(source, 0.0f, 0.0f, paint);
        int i = AnonymousClass1.$SwitchMap$com$horcrux$svg$FilterProperties$FeBlendMode[this.mMode.ordinal()];
        if (i == 1 || i == 2) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        } else if (i == 3) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
        } else if (i == 4) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
        } else if (i == 5) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
        }
        canvas.drawBitmap(source2, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ float[] lambda$applyFilter$0(float[] fArr, float[] fArr2) {
        float f = fArr[0];
        float f2 = fArr2[0];
        float f3 = fArr[1];
        float f4 = fArr2[1];
        float f5 = fArr[2];
        float f6 = fArr2[2];
        float f7 = fArr[3];
        float f8 = fArr2[3];
        float f9 = (f7 * f * (1.0f - f2)) + (f8 * f2 * (1.0f - f)) + (f7 * f * f8 * f2);
        return new float[]{1.0f - ((1.0f - f) * (1.0f - f2)), (f3 * f * (1.0f - f2)) + (f4 * f2 * (1.0f - f)) + (f3 * f * f4 * f2), (f5 * f * (1.0f - f2)) + (f6 * f2 * (1.0f - f)) + (f5 * f * f6 * f2), f9};
    }

    /* renamed from: com.horcrux.svg.FeBlendView$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$FilterProperties$FeBlendMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.horcrux.svg.FilterProperties$FeBlendMode[] r0 = com.horcrux.svg.FilterProperties$FeBlendMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$horcrux$svg$FilterProperties$FeBlendMode = r0
                com.horcrux.svg.FilterProperties$FeBlendMode r1 = com.horcrux.svg.FilterProperties$FeBlendMode.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$horcrux$svg$FilterProperties$FeBlendMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.horcrux.svg.FilterProperties$FeBlendMode r1 = com.horcrux.svg.FilterProperties$FeBlendMode.NORMAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$horcrux$svg$FilterProperties$FeBlendMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.horcrux.svg.FilterProperties$FeBlendMode r1 = com.horcrux.svg.FilterProperties$FeBlendMode.SCREEN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$horcrux$svg$FilterProperties$FeBlendMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.horcrux.svg.FilterProperties$FeBlendMode r1 = com.horcrux.svg.FilterProperties$FeBlendMode.LIGHTEN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$horcrux$svg$FilterProperties$FeBlendMode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.horcrux.svg.FilterProperties$FeBlendMode r1 = com.horcrux.svg.FilterProperties$FeBlendMode.DARKEN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$horcrux$svg$FilterProperties$FeBlendMode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.horcrux.svg.FilterProperties$FeBlendMode r1 = com.horcrux.svg.FilterProperties$FeBlendMode.MULTIPLY     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.FeBlendView.AnonymousClass1.<clinit>():void");
        }
    }
}
