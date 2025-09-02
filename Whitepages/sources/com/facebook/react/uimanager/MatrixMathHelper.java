package com.facebook.react.uimanager;

import com.facebook.infer.annotation.Assertions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class MatrixMathHelper {
    private static final double EPSILON = 1.0E-5d;
    public static final MatrixMathHelper INSTANCE = new MatrixMathHelper();

    public static final double degreesToRadians(double d) {
        return (d * 3.141592653589793d) / ((double) 180);
    }

    private MatrixMathHelper() {
    }

    private final boolean isZero(double d) {
        if (!Double.isNaN(d) && Math.abs(d) < EPSILON) {
            return true;
        }
        return false;
    }

    public static final void multiplyInto(double[] dArr, double[] dArr2, double[] dArr3) {
        double[] dArr4 = dArr;
        double[] dArr5 = dArr2;
        double[] dArr6 = dArr3;
        Intrinsics.checkNotNullParameter(dArr4, "out");
        Intrinsics.checkNotNullParameter(dArr5, "a");
        Intrinsics.checkNotNullParameter(dArr6, "b");
        double d = dArr5[0];
        double d2 = dArr5[1];
        double d3 = dArr5[2];
        double d4 = dArr5[3];
        double d5 = dArr5[4];
        double d6 = dArr5[5];
        double d7 = dArr5[6];
        double d8 = dArr5[7];
        double d9 = dArr5[8];
        double d10 = dArr5[9];
        double d11 = dArr5[10];
        double d12 = dArr5[11];
        double d13 = dArr5[12];
        double d14 = dArr5[13];
        double d15 = dArr5[14];
        double d16 = dArr5[15];
        double d17 = dArr6[0];
        double d18 = dArr6[1];
        double d19 = dArr6[2];
        double d20 = dArr6[3];
        dArr4[0] = (d17 * d) + (d18 * d5) + (d19 * d9) + (d20 * d13);
        dArr4[1] = (d17 * d2) + (d18 * d6) + (d19 * d10) + (d20 * d14);
        dArr4[2] = (d17 * d3) + (d18 * d7) + (d19 * d11) + (d20 * d15);
        dArr4[3] = (d17 * d4) + (d18 * d8) + (d19 * d12) + (d20 * d16);
        double d21 = dArr6[4];
        double d22 = dArr6[5];
        double d23 = dArr6[6];
        double d24 = dArr6[7];
        dArr4[4] = (d21 * d) + (d22 * d5) + (d23 * d9) + (d24 * d13);
        dArr4[5] = (d21 * d2) + (d22 * d6) + (d23 * d10) + (d24 * d14);
        dArr4[6] = (d21 * d3) + (d22 * d7) + (d23 * d11) + (d24 * d15);
        dArr4[7] = (d21 * d4) + (d22 * d8) + (d23 * d12) + (d24 * d16);
        double d25 = dArr6[8];
        double d26 = dArr6[9];
        double d27 = dArr6[10];
        double d28 = dArr6[11];
        dArr4[8] = (d25 * d) + (d26 * d5) + (d27 * d9) + (d28 * d13);
        dArr4[9] = (d25 * d2) + (d26 * d6) + (d27 * d10) + (d28 * d14);
        dArr4[10] = (d25 * d3) + (d26 * d7) + (d27 * d11) + (d28 * d15);
        dArr4[11] = (d25 * d4) + (d26 * d8) + (d27 * d12) + (d28 * d16);
        double d29 = dArr6[12];
        double d30 = dArr6[13];
        double d31 = dArr6[14];
        double d32 = dArr6[15];
        dArr4[12] = (d * d29) + (d5 * d30) + (d9 * d31) + (d13 * d32);
        dArr4[13] = (d2 * d29) + (d6 * d30) + (d10 * d31) + (d14 * d32);
        dArr4[14] = (d3 * d29) + (d7 * d30) + (d11 * d31) + (d15 * d32);
        dArr4[15] = (d29 * d4) + (d30 * d8) + (d31 * d12) + (d32 * d16);
    }

    public static final void decomposeMatrix(double[] dArr, MatrixDecompositionContext matrixDecompositionContext) {
        double[] dArr2 = dArr;
        MatrixDecompositionContext matrixDecompositionContext2 = matrixDecompositionContext;
        Intrinsics.checkNotNullParameter(dArr2, "transformMatrix");
        Intrinsics.checkNotNullParameter(matrixDecompositionContext2, "ctx");
        Assertions.assertCondition(dArr2.length == 16);
        double[] dArr3 = matrixDecompositionContext2.perspective;
        double[] dArr4 = matrixDecompositionContext2.scale;
        double[] dArr5 = matrixDecompositionContext2.skew;
        double[] dArr6 = matrixDecompositionContext2.translation;
        double[] dArr7 = matrixDecompositionContext2.rotationDegrees;
        if (!INSTANCE.isZero(dArr2[15])) {
            double[][] dArr8 = new double[4][];
            for (int i = 0; i < 4; i++) {
                dArr8[i] = new double[4];
            }
            double[] dArr9 = new double[16];
            for (int i2 = 0; i2 < 4; i2++) {
                for (int i3 = 0; i3 < 4; i3++) {
                    int i4 = (i2 * 4) + i3;
                    double d = dArr2[i4] / dArr2[15];
                    dArr8[i2][i3] = d;
                    if (i3 == 3) {
                        d = 0.0d;
                    }
                    dArr9[i4] = d;
                }
            }
            dArr9[15] = 1.0d;
            MatrixMathHelper matrixMathHelper = INSTANCE;
            if (!matrixMathHelper.isZero(determinant(dArr9))) {
                if (!matrixMathHelper.isZero(dArr8[0][3]) || !matrixMathHelper.isZero(dArr8[1][3]) || !matrixMathHelper.isZero(dArr8[2][3])) {
                    multiplyVectorByMatrix(new double[]{dArr8[0][3], dArr8[1][3], dArr8[2][3], dArr8[3][3]}, transpose(inverse(dArr9)), dArr3);
                } else {
                    dArr3[2] = 0.0d;
                    dArr3[1] = 0.0d;
                    dArr3[0] = 0.0d;
                    dArr3[3] = 1.0d;
                }
                for (int i5 = 0; i5 < 3; i5++) {
                    dArr6[i5] = dArr8[3][i5];
                }
                double[][] dArr10 = new double[3][];
                for (int i6 = 0; i6 < 3; i6++) {
                    dArr10[i6] = new double[3];
                }
                for (int i7 = 0; i7 < 3; i7++) {
                    double[] dArr11 = dArr10[i7];
                    double[] dArr12 = dArr8[i7];
                    dArr11[0] = dArr12[0];
                    dArr11[1] = dArr12[1];
                    dArr11[2] = dArr12[2];
                }
                double v3Length = v3Length(dArr10[0]);
                dArr4[0] = v3Length;
                double[] v3Normalize = v3Normalize(dArr10[0], v3Length);
                dArr10[0] = v3Normalize;
                double v3Dot = v3Dot(v3Normalize, dArr10[1]);
                dArr5[0] = v3Dot;
                double[] v3Combine = v3Combine(dArr10[1], dArr10[0], 1.0d, -v3Dot);
                dArr10[1] = v3Combine;
                double v3Length2 = v3Length(v3Combine);
                dArr4[1] = v3Length2;
                dArr10[1] = v3Normalize(dArr10[1], v3Length2);
                dArr5[0] = dArr5[0] / dArr4[1];
                double v3Dot2 = v3Dot(dArr10[0], dArr10[2]);
                dArr5[1] = v3Dot2;
                double[] v3Combine2 = v3Combine(dArr10[2], dArr10[0], 1.0d, -v3Dot2);
                dArr10[2] = v3Combine2;
                double v3Dot3 = v3Dot(dArr10[1], v3Combine2);
                dArr5[2] = v3Dot3;
                double[] v3Combine3 = v3Combine(dArr10[2], dArr10[1], 1.0d, -v3Dot3);
                dArr10[2] = v3Combine3;
                double v3Length3 = v3Length(v3Combine3);
                dArr4[2] = v3Length3;
                double[] v3Normalize2 = v3Normalize(dArr10[2], v3Length3);
                dArr10[2] = v3Normalize2;
                double d2 = dArr5[1];
                double d3 = dArr4[2];
                dArr5[1] = d2 / d3;
                dArr5[2] = dArr5[2] / d3;
                if (v3Dot(dArr10[0], v3Cross(dArr10[1], v3Normalize2)) < 0.0d) {
                    for (int i8 = 0; i8 < 3; i8++) {
                        dArr4[i8] = dArr4[i8] * -1.0d;
                        double[] dArr13 = dArr10[i8];
                        dArr13[0] = dArr13[0] * -1.0d;
                        dArr13[1] = dArr13[1] * -1.0d;
                        dArr13[2] = dArr13[2] * -1.0d;
                    }
                }
                double[] dArr14 = dArr10[2];
                dArr7[0] = roundTo3Places((-Math.atan2(dArr14[1], dArr14[2])) * 57.29577951308232d);
                double[] dArr15 = dArr10[2];
                double d4 = dArr15[1];
                double d5 = dArr15[2];
                dArr7[1] = roundTo3Places((-Math.atan2(-dArr15[0], Math.sqrt((d4 * d4) + (d5 * d5)))) * 57.29577951308232d);
                dArr7[2] = roundTo3Places((-Math.atan2(dArr10[1][0], dArr10[0][0])) * 57.29577951308232d);
            }
        }
    }

    public static final double determinant(double[] dArr) {
        double[] dArr2 = dArr;
        Intrinsics.checkNotNullParameter(dArr2, "matrix");
        double d = dArr2[0];
        double d2 = dArr2[1];
        double d3 = dArr2[2];
        double d4 = dArr2[3];
        double d5 = dArr2[4];
        double d6 = dArr2[5];
        double d7 = dArr2[6];
        double d8 = dArr2[7];
        double d9 = dArr2[8];
        double d10 = dArr2[9];
        double d11 = dArr2[10];
        double d12 = dArr2[11];
        double d13 = dArr2[12];
        double d14 = dArr2[13];
        double d15 = dArr2[14];
        double d16 = dArr2[15];
        double d17 = d4 * d7;
        double d18 = d3 * d8;
        double d19 = d4 * d6;
        double d20 = d2 * d8;
        double d21 = d3 * d6;
        double d22 = d2 * d7;
        double d23 = d4 * d5;
        double d24 = d8 * d;
        double d25 = d3 * d5;
        double d26 = d7 * d;
        double d27 = d2 * d5;
        double d28 = d * d6;
        return ((((((((((((((((((((((((d17 * d10) * d13) - ((d18 * d10) * d13)) - ((d19 * d11) * d13)) + ((d20 * d11) * d13)) + ((d21 * d12) * d13)) - ((d22 * d12) * d13)) - ((d17 * d9) * d14)) + ((d18 * d9) * d14)) + ((d23 * d11) * d14)) - ((d24 * d11) * d14)) - ((d25 * d12) * d14)) + ((d26 * d12) * d14)) + ((d19 * d9) * d15)) - ((d20 * d9) * d15)) - ((d23 * d10) * d15)) + ((d24 * d10) * d15)) + ((d27 * d12) * d15)) - ((d12 * d28) * d15)) - ((d21 * d9) * d16)) + ((d22 * d9) * d16)) + ((d25 * d10) * d16)) - ((d26 * d10) * d16)) - ((d27 * d11) * d16)) + (d28 * d11 * d16);
    }

    public static final double[] inverse(double[] dArr) {
        double[] dArr2 = dArr;
        Intrinsics.checkNotNullParameter(dArr2, "matrix");
        double determinant = determinant(dArr);
        if (INSTANCE.isZero(determinant)) {
            return dArr2;
        }
        double d = dArr2[0];
        double d2 = dArr2[1];
        double d3 = dArr2[2];
        double d4 = dArr2[3];
        double d5 = dArr2[4];
        double d6 = dArr2[5];
        double d7 = dArr2[6];
        double d8 = dArr2[7];
        double d9 = dArr2[8];
        double d10 = dArr2[9];
        double d11 = dArr2[10];
        double d12 = dArr2[11];
        double d13 = dArr2[12];
        double d14 = dArr2[13];
        double d15 = dArr2[14];
        double d16 = dArr2[15];
        double d17 = d7 * d12;
        double d18 = d8 * d11;
        double d19 = d8 * d10;
        double d20 = d6 * d12;
        double d21 = d7 * d10;
        double d22 = d6 * d11;
        double d23 = d4 * d11;
        double d24 = d3 * d12;
        double d25 = d4 * d10;
        double d26 = d2 * d12;
        double d27 = d3 * d10;
        double d28 = d2 * d11;
        double d29 = d3 * d8;
        double d30 = d4 * d7;
        double d31 = d4 * d6;
        double d32 = d2 * d8;
        double d33 = d3 * d6;
        double d34 = d2 * d7;
        double d35 = (d18 * d13) - (d17 * d13);
        double d36 = d8 * d9;
        double d37 = d5 * d12;
        double d38 = d7 * d9;
        double d39 = d5 * d11;
        double d40 = (d24 * d13) - (d23 * d13);
        double d41 = d4 * d9;
        double d42 = d * d12;
        double d43 = d3 * d9;
        double d44 = d * d11;
        double d45 = d4 * d5;
        double d46 = d8 * d;
        double d47 = d3 * d5;
        double d48 = d7 * d;
        double d49 = d6 * d9;
        double d50 = ((((d20 * d13) - (d19 * d13)) + (d36 * d14)) - (d37 * d14)) - (d49 * d16);
        double d51 = d5 * d10;
        double d52 = d2 * d9;
        double d53 = (((d25 * d13) - (d26 * d13)) - (d41 * d14)) + (d42 * d14) + (d52 * d16);
        double d54 = d * d10;
        double d55 = d2 * d5;
        double d56 = d * d6;
        return new double[]{((((((d17 * d14) - (d18 * d14)) + (d19 * d15)) - (d20 * d15)) - (d21 * d16)) + (d22 * d16)) / determinant, ((((((d23 * d14) - (d24 * d14)) - (d25 * d15)) + (d26 * d15)) + (d27 * d16)) - (d28 * d16)) / determinant, ((((((d29 * d14) - (d30 * d14)) + (d31 * d15)) - (d32 * d15)) - (d33 * d16)) + (d34 * d16)) / determinant, ((((((d30 * d10) - (d29 * d10)) - (d31 * d11)) + (d32 * d11)) + (d33 * d12)) - (d34 * d12)) / determinant, ((((d35 - (d36 * d15)) + (d37 * d15)) + (d38 * d16)) - (d39 * d16)) / determinant, ((((d40 + (d41 * d15)) - (d42 * d15)) - (d43 * d16)) + (d44 * d16)) / determinant, ((((((d30 * d13) - (d29 * d13)) - (d45 * d15)) + (d46 * d15)) + (d47 * d16)) - (d48 * d16)) / determinant, ((((((d29 * d9) - (d30 * d9)) + (d45 * d11)) - (d46 * d11)) - (d47 * d12)) + (d48 * d12)) / determinant, (d50 + (d51 * d16)) / determinant, (d53 - (d54 * d16)) / determinant, ((((((d32 * d13) - (d31 * d13)) + (d45 * d14)) - (d46 * d14)) - (d55 * d16)) + (d16 * d56)) / determinant, ((((((d31 * d9) - (d32 * d9)) - (d45 * d10)) + (d46 * d10)) + (d55 * d12)) - (d12 * d56)) / determinant, ((((((d21 * d13) - (d22 * d13)) - (d38 * d14)) + (d39 * d14)) + (d49 * d15)) - (d51 * d15)) / determinant, ((((((d28 * d13) - (d27 * d13)) + (d43 * d14)) - (d44 * d14)) - (d52 * d15)) + (d54 * d15)) / determinant, ((((((d33 * d13) - (d13 * d34)) - (d47 * d14)) + (d14 * d48)) + (d55 * d15)) - (d15 * d56)) / determinant, ((((((d34 * d9) - (d33 * d9)) + (d47 * d10)) - (d48 * d10)) - (d55 * d11)) + (d56 * d11)) / determinant};
    }

    public static final double[] transpose(double[] dArr) {
        double[] dArr2 = dArr;
        Intrinsics.checkNotNullParameter(dArr2, "m");
        return new double[]{dArr2[0], dArr2[4], dArr2[8], dArr2[12], dArr2[1], dArr2[5], dArr2[9], dArr2[13], dArr2[2], dArr2[6], dArr2[10], dArr2[14], dArr2[3], dArr2[7], dArr2[11], dArr2[15]};
    }

    public static final void multiplyVectorByMatrix(double[] dArr, double[] dArr2, double[] dArr3) {
        double[] dArr4 = dArr;
        double[] dArr5 = dArr2;
        double[] dArr6 = dArr3;
        Intrinsics.checkNotNullParameter(dArr4, "v");
        Intrinsics.checkNotNullParameter(dArr5, "m");
        Intrinsics.checkNotNullParameter(dArr6, "result");
        double d = dArr4[0];
        double d2 = dArr4[1];
        double d3 = dArr4[2];
        double d4 = dArr4[3];
        dArr6[0] = (dArr5[0] * d) + (dArr5[4] * d2) + (dArr5[8] * d3) + (dArr5[12] * d4);
        dArr6[1] = (dArr5[1] * d) + (dArr5[5] * d2) + (dArr5[9] * d3) + (dArr5[13] * d4);
        dArr6[2] = (dArr5[2] * d) + (dArr5[6] * d2) + (dArr5[10] * d3) + (dArr5[14] * d4);
        dArr6[3] = (d * dArr5[3]) + (d2 * dArr5[7]) + (d3 * dArr5[11]) + (d4 * dArr5[15]);
    }

    public static final double v3Length(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "a");
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = (d * d) + (d2 * d2);
        double d4 = dArr[2];
        return Math.sqrt(d3 + (d4 * d4));
    }

    public static final double[] v3Normalize(double[] dArr, double d) {
        Intrinsics.checkNotNullParameter(dArr, "vector");
        double d2 = (double) 1;
        if (INSTANCE.isZero(d)) {
            d = v3Length(dArr);
        }
        double d3 = d2 / d;
        return new double[]{dArr[0] * d3, dArr[1] * d3, dArr[2] * d3};
    }

    public static final double v3Dot(double[] dArr, double[] dArr2) {
        Intrinsics.checkNotNullParameter(dArr, "a");
        Intrinsics.checkNotNullParameter(dArr2, "b");
        return (dArr[0] * dArr2[0]) + (dArr[1] * dArr2[1]) + (dArr[2] * dArr2[2]);
    }

    public static final double[] v3Combine(double[] dArr, double[] dArr2, double d, double d2) {
        Intrinsics.checkNotNullParameter(dArr, "a");
        Intrinsics.checkNotNullParameter(dArr2, "b");
        return new double[]{(dArr[0] * d) + (dArr2[0] * d2), (dArr[1] * d) + (dArr2[1] * d2), (d * dArr[2]) + (d2 * dArr2[2])};
    }

    public static final double[] v3Cross(double[] dArr, double[] dArr2) {
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        Intrinsics.checkNotNullParameter(dArr3, "a");
        Intrinsics.checkNotNullParameter(dArr4, "b");
        double d = dArr3[1];
        double d2 = dArr4[2];
        double d3 = dArr3[2];
        double d4 = dArr4[1];
        double d5 = dArr4[0];
        double d6 = dArr3[0];
        return new double[]{(d * d2) - (d3 * d4), (d3 * d5) - (d2 * d6), (d6 * d4) - (d * d5)};
    }

    public static final double roundTo3Places(double d) {
        return ((double) Math.round(d * 1000.0d)) * 0.001d;
    }

    public static final double[] createIdentityMatrix() {
        double[] dArr = new double[16];
        resetIdentityMatrix(dArr);
        return dArr;
    }

    public static final void resetIdentityMatrix(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "matrix");
        dArr[14] = 0.0d;
        dArr[13] = 0.0d;
        dArr[12] = 0.0d;
        dArr[11] = 0.0d;
        dArr[9] = 0.0d;
        dArr[8] = 0.0d;
        dArr[7] = 0.0d;
        dArr[6] = 0.0d;
        dArr[4] = 0.0d;
        dArr[3] = 0.0d;
        dArr[2] = 0.0d;
        dArr[1] = 0.0d;
        dArr[15] = 1.0d;
        dArr[10] = 1.0d;
        dArr[5] = 1.0d;
        dArr[0] = 1.0d;
    }

    public static final void applyPerspective(double[] dArr, double d) {
        Intrinsics.checkNotNullParameter(dArr, "m");
        dArr[11] = ((double) -1) / d;
    }

    public static final void applyScaleX(double[] dArr, double d) {
        Intrinsics.checkNotNullParameter(dArr, "m");
        dArr[0] = d;
    }

    public static final void applyScaleY(double[] dArr, double d) {
        Intrinsics.checkNotNullParameter(dArr, "m");
        dArr[5] = d;
    }

    public final void applyScaleZ(double[] dArr, double d) {
        Intrinsics.checkNotNullParameter(dArr, "m");
        dArr[10] = d;
    }

    public static final void applyTranslate2D(double[] dArr, double d, double d2) {
        Intrinsics.checkNotNullParameter(dArr, "m");
        dArr[12] = d;
        dArr[13] = d2;
    }

    public static final void applyTranslate3D(double[] dArr, double d, double d2, double d3) {
        Intrinsics.checkNotNullParameter(dArr, "m");
        dArr[12] = d;
        dArr[13] = d2;
        dArr[14] = d3;
    }

    public static final void applySkewX(double[] dArr, double d) {
        Intrinsics.checkNotNullParameter(dArr, "m");
        dArr[4] = Math.tan(d);
    }

    public static final void applySkewY(double[] dArr, double d) {
        Intrinsics.checkNotNullParameter(dArr, "m");
        dArr[1] = Math.tan(d);
    }

    public static final void applyRotateX(double[] dArr, double d) {
        Intrinsics.checkNotNullParameter(dArr, "m");
        dArr[5] = Math.cos(d);
        dArr[6] = Math.sin(d);
        dArr[9] = -Math.sin(d);
        dArr[10] = Math.cos(d);
    }

    public static final void applyRotateY(double[] dArr, double d) {
        Intrinsics.checkNotNullParameter(dArr, "m");
        dArr[0] = Math.cos(d);
        dArr[2] = -Math.sin(d);
        dArr[8] = Math.sin(d);
        dArr[10] = Math.cos(d);
    }

    public static final void applyRotateZ(double[] dArr, double d) {
        Intrinsics.checkNotNullParameter(dArr, "m");
        dArr[0] = Math.cos(d);
        dArr[1] = Math.sin(d);
        dArr[4] = -Math.sin(d);
        dArr[5] = Math.cos(d);
    }

    public static class MatrixDecompositionContext {
        private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public double[] perspective = new double[4];
        public double[] rotationDegrees = new double[3];
        public double[] scale = new double[3];
        public double[] skew = new double[3];
        public double[] translation = new double[3];

        public final void reset() {
            Companion companion = Companion;
            companion.resetArray(this.perspective);
            companion.resetArray(this.scale);
            companion.resetArray(this.skew);
            companion.resetArray(this.translation);
            companion.resetArray(this.rotationDegrees);
        }

        private static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* access modifiers changed from: private */
            public final void resetArray(double[] dArr) {
                int length = dArr.length;
                for (int i = 0; i < length; i++) {
                    dArr[i] = 0.0d;
                }
            }
        }
    }
}
