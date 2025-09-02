package com.airbnb.lottie.parser;

import android.graphics.Color;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GradientColorParser implements ValueParser {
    private int colorPoints;

    public GradientColorParser(int i) {
        this.colorPoints = i;
    }

    public GradientColor parse(JsonReader jsonReader, float f) {
        ArrayList arrayList = new ArrayList();
        boolean z = jsonReader.peek() == JsonReader.Token.BEGIN_ARRAY;
        if (z) {
            jsonReader.beginArray();
        }
        while (jsonReader.hasNext()) {
            arrayList.add(Float.valueOf((float) jsonReader.nextDouble()));
        }
        if (arrayList.size() == 4 && ((Float) arrayList.get(0)).floatValue() == 1.0f) {
            arrayList.set(0, Float.valueOf(0.0f));
            arrayList.add(Float.valueOf(1.0f));
            arrayList.add((Float) arrayList.get(1));
            arrayList.add((Float) arrayList.get(2));
            arrayList.add((Float) arrayList.get(3));
            this.colorPoints = 2;
        }
        if (z) {
            jsonReader.endArray();
        }
        if (this.colorPoints == -1) {
            this.colorPoints = arrayList.size() / 4;
        }
        int i = this.colorPoints;
        float[] fArr = new float[i];
        int[] iArr = new int[i];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < this.colorPoints * 4; i4++) {
            int i5 = i4 / 4;
            double floatValue = (double) ((Float) arrayList.get(i4)).floatValue();
            int i6 = i4 % 4;
            if (i6 == 0) {
                if (i5 > 0) {
                    float f2 = (float) floatValue;
                    if (fArr[i5 - 1] >= f2) {
                        fArr[i5] = f2 + 0.01f;
                    }
                }
                fArr[i5] = (float) floatValue;
            } else if (i6 == 1) {
                i2 = (int) (floatValue * 255.0d);
            } else if (i6 == 2) {
                i3 = (int) (floatValue * 255.0d);
            } else if (i6 == 3) {
                iArr[i5] = Color.argb(255, i2, i3, (int) (floatValue * 255.0d));
            }
        }
        return addOpacityStopsToGradientIfNeeded(new GradientColor(fArr, iArr), arrayList);
    }

    private GradientColor addOpacityStopsToGradientIfNeeded(GradientColor gradientColor, List list) {
        int i = this.colorPoints * 4;
        if (list.size() <= i) {
            return gradientColor;
        }
        float[] positions = gradientColor.getPositions();
        int[] colors = gradientColor.getColors();
        int size = (list.size() - i) / 2;
        float[] fArr = new float[size];
        float[] fArr2 = new float[size];
        int i2 = 0;
        while (i < list.size()) {
            if (i % 2 == 0) {
                fArr[i2] = ((Float) list.get(i)).floatValue();
            } else {
                fArr2[i2] = ((Float) list.get(i)).floatValue();
                i2++;
            }
            i++;
        }
        float[] mergeUniqueElements = mergeUniqueElements(gradientColor.getPositions(), fArr);
        int length = mergeUniqueElements.length;
        int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            float f = mergeUniqueElements[i3];
            int binarySearch = Arrays.binarySearch(positions, f);
            int binarySearch2 = Arrays.binarySearch(fArr, f);
            if (binarySearch < 0 || binarySearch2 > 0) {
                if (binarySearch2 < 0) {
                    binarySearch2 = -(binarySearch2 + 1);
                }
                iArr[i3] = getColorInBetweenColorStops(f, fArr2[binarySearch2], positions, colors);
            } else {
                iArr[i3] = getColorInBetweenOpacityStops(f, colors[binarySearch], fArr, fArr2);
            }
        }
        return new GradientColor(mergeUniqueElements, iArr);
    }

    /* access modifiers changed from: package-private */
    public int getColorInBetweenColorStops(float f, float f2, float[] fArr, int[] iArr) {
        if (iArr.length < 2 || f == fArr[0]) {
            return iArr[0];
        }
        int i = 1;
        while (i < fArr.length) {
            float f3 = fArr[i];
            if (f3 < f && i != fArr.length - 1) {
                i++;
            } else if (i == fArr.length - 1 && f >= f3) {
                return Color.argb((int) (f2 * 255.0f), Color.red(iArr[i]), Color.green(iArr[i]), Color.blue(iArr[i]));
            } else {
                int i2 = i - 1;
                float f4 = fArr[i2];
                int evaluate = GammaEvaluator.evaluate((f - f4) / (f3 - f4), iArr[i2], iArr[i]);
                return Color.argb((int) (f2 * 255.0f), Color.red(evaluate), Color.green(evaluate), Color.blue(evaluate));
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }

    private int getColorInBetweenOpacityStops(float f, int i, float[] fArr, float[] fArr2) {
        float lerp;
        if (fArr2.length < 2 || f <= fArr[0]) {
            return Color.argb((int) (fArr2[0] * 255.0f), Color.red(i), Color.green(i), Color.blue(i));
        }
        for (int i2 = 1; i2 < fArr.length; i2++) {
            float f2 = fArr[i2];
            int i3 = (f2 > f ? 1 : (f2 == f ? 0 : -1));
            if (i3 >= 0 || i2 == fArr.length - 1) {
                if (i3 <= 0) {
                    lerp = fArr2[i2];
                } else {
                    int i4 = i2 - 1;
                    float f3 = fArr[i4];
                    lerp = MiscUtils.lerp(fArr2[i4], fArr2[i2], (f - f3) / (f2 - f3));
                }
                return Color.argb((int) (lerp * 255.0f), Color.red(i), Color.green(i), Color.blue(i));
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }

    protected static float[] mergeUniqueElements(float[] fArr, float[] fArr2) {
        if (fArr.length == 0) {
            return fArr2;
        }
        if (fArr2.length == 0) {
            return fArr;
        }
        int length = fArr.length + fArr2.length;
        float[] fArr3 = new float[length];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            float f = Float.NaN;
            float f2 = i2 < fArr.length ? fArr[i2] : Float.NaN;
            if (i3 < fArr2.length) {
                f = fArr2[i3];
            }
            if (Float.isNaN(f) || f2 < f) {
                fArr3[i4] = f2;
                i2++;
            } else if (Float.isNaN(f2) || f < f2) {
                fArr3[i4] = f;
                i3++;
            } else {
                fArr3[i4] = f2;
                i2++;
                i3++;
                i++;
            }
        }
        return i == 0 ? fArr3 : Arrays.copyOf(fArr3, length - i);
    }
}
