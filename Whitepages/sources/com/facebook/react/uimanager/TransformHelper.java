package com.facebook.react.uimanager;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.ReactConstants;

public class TransformHelper {
    private static ThreadLocal<double[]> sHelperMatrix = new ThreadLocal<double[]>() {
        /* access modifiers changed from: protected */
        public double[] initialValue() {
            return new double[16];
        }
    };

    private static double convertToRadians(ReadableMap readableMap, String str) {
        double d;
        boolean z = true;
        if (readableMap.getType(str) == ReadableType.String) {
            String string = readableMap.getString(str);
            if (string.endsWith("rad")) {
                string = string.substring(0, string.length() - 3);
            } else if (string.endsWith("deg")) {
                string = string.substring(0, string.length() - 3);
                z = false;
            }
            d = (double) Float.parseFloat(string);
        } else {
            d = readableMap.getDouble(str);
        }
        return z ? d : MatrixMathHelper.degreesToRadians(d);
    }

    @Deprecated(forRemoval = true, since = "0.75")
    public static void processTransform(ReadableArray readableArray, double[] dArr) {
        processTransform(readableArray, dArr, 0.0f, 0.0f, (ReadableArray) null, false);
    }

    @Deprecated(forRemoval = true, since = "0.75")
    public static void processTransform(ReadableArray readableArray, double[] dArr, float f, float f2, ReadableArray readableArray2) {
        processTransform(readableArray, dArr, f, f2, readableArray2, false);
    }

    public static void processTransform(ReadableArray readableArray, double[] dArr, float f, float f2, ReadableArray readableArray2, boolean z) {
        int i;
        int i2;
        int i3;
        double d;
        double d2;
        double d3;
        double d4;
        ReadableArray readableArray3 = readableArray;
        double[] dArr2 = dArr;
        float f3 = f;
        float f4 = f2;
        double[] dArr3 = sHelperMatrix.get();
        MatrixMathHelper.resetIdentityMatrix(dArr);
        float[] translateForTransformOrigin = getTranslateForTransformOrigin(f, f2, readableArray2, z);
        int i4 = 1;
        if (translateForTransformOrigin != null) {
            MatrixMathHelper.resetIdentityMatrix(dArr3);
            MatrixMathHelper.applyTranslate3D(dArr3, (double) translateForTransformOrigin[0], (double) translateForTransformOrigin[1], (double) translateForTransformOrigin[2]);
            MatrixMathHelper.multiplyInto(dArr2, dArr2, dArr3);
        }
        int i5 = 16;
        if (readableArray.size() == 16 && readableArray3.getType(0) == ReadableType.Number) {
            MatrixMathHelper.resetIdentityMatrix(dArr3);
            for (int i6 = 0; i6 < readableArray.size(); i6++) {
                dArr3[i6] = readableArray3.getDouble(i6);
            }
            MatrixMathHelper.multiplyInto(dArr2, dArr2, dArr3);
        } else {
            int size = readableArray.size();
            int i7 = 0;
            while (i7 < size) {
                ReadableMap map = readableArray3.getMap(i7);
                String nextKey = map.keySetIterator().nextKey();
                MatrixMathHelper.resetIdentityMatrix(dArr3);
                if ("matrix".equals(nextKey)) {
                    ReadableArray array = map.getArray(nextKey);
                    for (int i8 = 0; i8 < i5; i8++) {
                        dArr3[i8] = array.getDouble(i8);
                    }
                } else if ("perspective".equals(nextKey)) {
                    MatrixMathHelper.applyPerspective(dArr3, map.getDouble(nextKey));
                } else if ("rotateX".equals(nextKey)) {
                    MatrixMathHelper.applyRotateX(dArr3, convertToRadians(map, nextKey));
                } else if ("rotateY".equals(nextKey)) {
                    MatrixMathHelper.applyRotateY(dArr3, convertToRadians(map, nextKey));
                } else if ("rotate".equals(nextKey) || "rotateZ".equals(nextKey)) {
                    i = i7;
                    i3 = i5;
                    i2 = size;
                    MatrixMathHelper.applyRotateZ(dArr3, convertToRadians(map, nextKey));
                    MatrixMathHelper.multiplyInto(dArr2, dArr2, dArr3);
                    i7 = i + 1;
                    i5 = i3;
                    size = i2;
                    i4 = 1;
                } else if ("scale".equals(nextKey)) {
                    double d5 = map.getDouble(nextKey);
                    MatrixMathHelper.applyScaleX(dArr3, d5);
                    MatrixMathHelper.applyScaleY(dArr3, d5);
                } else if (ViewProps.SCALE_X.equals(nextKey)) {
                    MatrixMathHelper.applyScaleX(dArr3, map.getDouble(nextKey));
                } else if (ViewProps.SCALE_Y.equals(nextKey)) {
                    MatrixMathHelper.applyScaleY(dArr3, map.getDouble(nextKey));
                } else {
                    int i9 = size;
                    if ("translate".equals(nextKey)) {
                        ReadableArray array2 = map.getArray(nextKey);
                        ReadableType type = array2.getType(0);
                        ReadableType readableType = ReadableType.String;
                        if (type != readableType || !z) {
                            d3 = array2.getDouble(0);
                        } else {
                            d3 = parseTranslateValue(array2.getString(0), (double) f3);
                        }
                        if (array2.getType(i4) != readableType || !z) {
                            d4 = array2.getDouble(i4);
                        } else {
                            d4 = parseTranslateValue(array2.getString(i4), (double) f4);
                        }
                        i = i7;
                        double d6 = d4;
                        i2 = i9;
                        i3 = 16;
                        MatrixMathHelper.applyTranslate3D(dArr3, d3, d6, array2.size() > 2 ? array2.getDouble(2) : 0.0d);
                    } else {
                        i = i7;
                        i2 = i9;
                        i3 = 16;
                        if (ViewProps.TRANSLATE_X.equals(nextKey)) {
                            if (map.getType(nextKey) != ReadableType.String || !z) {
                                d2 = map.getDouble(nextKey);
                            } else {
                                d2 = parseTranslateValue(map.getString(nextKey), (double) f3);
                            }
                            MatrixMathHelper.applyTranslate2D(dArr3, d2, 0.0d);
                        } else if (ViewProps.TRANSLATE_Y.equals(nextKey)) {
                            if (map.getType(nextKey) != ReadableType.String || !z) {
                                d = map.getDouble(nextKey);
                            } else {
                                d = parseTranslateValue(map.getString(nextKey), (double) f4);
                            }
                            MatrixMathHelper.applyTranslate2D(dArr3, 0.0d, d);
                        } else if ("skewX".equals(nextKey)) {
                            MatrixMathHelper.applySkewX(dArr3, convertToRadians(map, nextKey));
                        } else if ("skewY".equals(nextKey)) {
                            MatrixMathHelper.applySkewY(dArr3, convertToRadians(map, nextKey));
                        } else {
                            FLog.w(ReactConstants.TAG, "Unsupported transform type: " + nextKey);
                        }
                    }
                    MatrixMathHelper.multiplyInto(dArr2, dArr2, dArr3);
                    i7 = i + 1;
                    i5 = i3;
                    size = i2;
                    i4 = 1;
                }
                i = i7;
                i3 = i5;
                i2 = size;
                MatrixMathHelper.multiplyInto(dArr2, dArr2, dArr3);
                i7 = i + 1;
                i5 = i3;
                size = i2;
                i4 = 1;
            }
        }
        if (translateForTransformOrigin != null) {
            MatrixMathHelper.resetIdentityMatrix(dArr3);
            MatrixMathHelper.applyTranslate3D(dArr3, (double) (-translateForTransformOrigin[0]), (double) (-translateForTransformOrigin[1]), (double) (-translateForTransformOrigin[2]));
            MatrixMathHelper.multiplyInto(dArr2, dArr2, dArr3);
        }
    }

    private static double parseTranslateValue(String str, double d) {
        try {
            if (str.endsWith("%")) {
                return (Double.parseDouble(str.substring(0, str.length() - 1)) * d) / 100.0d;
            }
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            FLog.w(ReactConstants.TAG, "Invalid translate value: " + str);
            return 0.0d;
        }
    }

    private static float[] getTranslateForTransformOrigin(float f, float f2, ReadableArray readableArray, boolean z) {
        if (readableArray == null) {
            return null;
        }
        if (f2 == 0.0f && f == 0.0f) {
            return null;
        }
        float f3 = f / 2.0f;
        float f4 = f2 / 2.0f;
        float[] fArr = {f3, f4, 0.0f};
        int i = 0;
        while (i < readableArray.size() && i < 3) {
            int i2 = AnonymousClass2.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()];
            if (i2 == 1) {
                fArr[i] = (float) readableArray.getDouble(i);
            } else if (i2 == 2 && z) {
                String string = readableArray.getString(i);
                if (string.endsWith("%")) {
                    fArr[i] = ((i == 0 ? f : f2) * Float.parseFloat(string.substring(0, string.length() - 1))) / 100.0f;
                }
            }
            i++;
        }
        return new float[]{(-f3) + fArr[0], (-f4) + fArr[1], fArr[2]};
    }

    /* renamed from: com.facebook.react.uimanager.TransformHelper$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$bridge$ReadableType = r0
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.TransformHelper.AnonymousClass2.<clinit>():void");
        }
    }
}
