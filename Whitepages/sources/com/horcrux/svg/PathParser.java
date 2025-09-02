package com.horcrux.svg;

import android.graphics.Path;
import android.graphics.RectF;
import com.google.android.gms.fido.u2f.api.common.RegisterRequest;
import java.util.ArrayList;

abstract class PathParser {
    static ArrayList elements;
    private static int i;
    private static int l;
    private static Path mPath;
    private static boolean mPenDown;
    private static float mPenDownX;
    private static float mPenDownY;
    private static float mPenX;
    private static float mPenY;
    private static float mPivotX;
    private static float mPivotY;
    static float mScale;
    private static String s;

    private static boolean is_cmd(char c) {
        switch (c) {
            case RegisterRequest.U2F_V1_CHALLENGE_BYTE_LENGTH:
            case 'C':
            case 'H':
            case 'L':
            case 'M':
            case 'Q':
            case 'S':
            case 'T':
            case 'V':
            case 'Z':
            case 'a':
            case 'c':
            case 'h':
            case 'l':
            case 'm':
            case 'q':
            case 's':
            case 't':
            case 'v':
            case 'z':
                return true;
            default:
                return false;
        }
    }

    private static boolean is_number_start(char c) {
        return (c >= '0' && c <= '9') || c == '.' || c == '-' || c == '+';
    }

    static Path parse(String str) {
        elements = new ArrayList();
        Path path = new Path();
        mPath = path;
        if (str == null) {
            return path;
        }
        l = str.length();
        s = str;
        i = 0;
        mPenX = 0.0f;
        mPenY = 0.0f;
        mPivotX = 0.0f;
        mPivotY = 0.0f;
        mPenDownX = 0.0f;
        mPenDownY = 0.0f;
        mPenDown = false;
        char c = ' ';
        while (i < l) {
            skip_spaces();
            int i2 = i;
            if (i2 >= l) {
                return mPath;
            }
            boolean z = true;
            boolean z2 = c != ' ';
            char charAt = s.charAt(i2);
            if (z2 || charAt == 'M' || charAt == 'm') {
                if (is_cmd(charAt)) {
                    i++;
                    z = false;
                    c = charAt;
                } else if (!is_number_start(charAt) || !z2) {
                    throw new IllegalArgumentException(String.format("Unexpected character '%c' (i=%d, s=%s)", new Object[]{Character.valueOf(charAt), Integer.valueOf(i), s}));
                } else if (c == 'Z' || c == 'z') {
                    throw new IllegalArgumentException(String.format("Unexpected number after 'z' (s=%s)", new Object[]{s}));
                } else if (c == 'M' || c == 'm') {
                    c = is_absolute(c) ? 'L' : 'l';
                } else {
                    z = false;
                }
                boolean is_absolute = is_absolute(c);
                switch (c) {
                    case RegisterRequest.U2F_V1_CHALLENGE_BYTE_LENGTH:
                        arcTo(parse_list_number(), parse_list_number(), parse_list_number(), parse_flag(), parse_flag(), parse_list_number(), parse_list_number());
                        break;
                    case 'C':
                        curveTo(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 'H':
                        lineTo(parse_list_number(), mPenY);
                        break;
                    case 'L':
                        lineTo(parse_list_number(), parse_list_number());
                        break;
                    case 'M':
                        moveTo(parse_list_number(), parse_list_number());
                        break;
                    case 'Q':
                        quadraticBezierCurveTo(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 'S':
                        smoothCurveTo(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 'T':
                        smoothQuadraticBezierCurveTo(parse_list_number(), parse_list_number());
                        break;
                    case 'V':
                        lineTo(mPenX, parse_list_number());
                        break;
                    case 'Z':
                    case 'z':
                        close();
                        break;
                    case 'a':
                        arc(parse_list_number(), parse_list_number(), parse_list_number(), parse_flag(), parse_flag(), parse_list_number(), parse_list_number());
                        break;
                    case 'c':
                        curve(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 'h':
                        line(parse_list_number(), 0.0f);
                        break;
                    case 'l':
                        line(parse_list_number(), parse_list_number());
                        break;
                    case 'm':
                        move(parse_list_number(), parse_list_number());
                        break;
                    case 'q':
                        quadraticBezierCurve(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 's':
                        smoothCurve(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 't':
                        smoothQuadraticBezierCurve(parse_list_number(), parse_list_number());
                        break;
                    case 'v':
                        line(0.0f, parse_list_number());
                        break;
                    default:
                        throw new IllegalArgumentException(String.format("Unexpected comand '%c' (s=%s)", new Object[]{Character.valueOf(c), s}));
                }
                if (z) {
                    c = is_absolute ? 'M' : 'm';
                }
            } else {
                throw new IllegalArgumentException(String.format("Unexpected character '%c' (i=%d, s=%s)", new Object[]{Character.valueOf(charAt), Integer.valueOf(i), s}));
            }
        }
        return mPath;
    }

    private static void move(float f, float f2) {
        moveTo(f + mPenX, f2 + mPenY);
    }

    private static void moveTo(float f, float f2) {
        mPenX = f;
        mPivotX = f;
        mPenDownX = f;
        mPenY = f2;
        mPivotY = f2;
        mPenDownY = f2;
        Path path = mPath;
        float f3 = mScale;
        path.moveTo(f * f3, f3 * f2);
        elements.add(new PathElement(ElementType.kCGPathElementMoveToPoint, new Point[]{new Point((double) f, (double) f2)}));
    }

    private static void line(float f, float f2) {
        lineTo(f + mPenX, f2 + mPenY);
    }

    private static void lineTo(float f, float f2) {
        setPenDown();
        mPenX = f;
        mPivotX = f;
        mPenY = f2;
        mPivotY = f2;
        Path path = mPath;
        float f3 = mScale;
        path.lineTo(f * f3, f3 * f2);
        elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point((double) f, (double) f2)}));
    }

    private static void curve(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = mPenX;
        float f8 = mPenY;
        curveTo(f + f7, f2 + f8, f3 + f7, f4 + f8, f5 + f7, f6 + f8);
    }

    private static void curveTo(float f, float f2, float f3, float f4, float f5, float f6) {
        mPivotX = f3;
        mPivotY = f4;
        cubicTo(f, f2, f3, f4, f5, f6);
    }

    private static void cubicTo(float f, float f2, float f3, float f4, float f5, float f6) {
        setPenDown();
        mPenX = f5;
        mPenY = f6;
        Path path = mPath;
        float f7 = mScale;
        path.cubicTo(f * f7, f2 * f7, f3 * f7, f4 * f7, f5 * f7, f6 * f7);
        elements.add(new PathElement(ElementType.kCGPathElementAddCurveToPoint, new Point[]{new Point((double) f, (double) f2), new Point((double) f3, (double) f4), new Point((double) f5, (double) f6)}));
    }

    private static void smoothCurve(float f, float f2, float f3, float f4) {
        float f5 = mPenX;
        float f6 = mPenY;
        smoothCurveTo(f + f5, f2 + f6, f3 + f5, f4 + f6);
    }

    private static void smoothCurveTo(float f, float f2, float f3, float f4) {
        mPivotX = f;
        mPivotY = f2;
        cubicTo((mPenX * 2.0f) - mPivotX, (mPenY * 2.0f) - mPivotY, f, f2, f3, f4);
    }

    private static void quadraticBezierCurve(float f, float f2, float f3, float f4) {
        float f5 = mPenX;
        float f6 = mPenY;
        quadraticBezierCurveTo(f + f5, f2 + f6, f3 + f5, f4 + f6);
    }

    private static void quadraticBezierCurveTo(float f, float f2, float f3, float f4) {
        mPivotX = f;
        mPivotY = f2;
        float f5 = f * 2.0f;
        float f6 = f2 * 2.0f;
        float f7 = (mPenX + f5) / 3.0f;
        float f8 = (mPenY + f6) / 3.0f;
        cubicTo(f7, f8, (f3 + f5) / 3.0f, (f4 + f6) / 3.0f, f3, f4);
    }

    private static void smoothQuadraticBezierCurve(float f, float f2) {
        smoothQuadraticBezierCurveTo(f + mPenX, f2 + mPenY);
    }

    private static void smoothQuadraticBezierCurveTo(float f, float f2) {
        quadraticBezierCurveTo((mPenX * 2.0f) - mPivotX, (mPenY * 2.0f) - mPivotY, f, f2);
    }

    private static void arc(float f, float f2, float f3, boolean z, boolean z2, float f4, float f5) {
        arcTo(f, f2, f3, z, z2, f4 + mPenX, f5 + mPenY);
    }

    private static void arcTo(float f, float f2, float f3, boolean z, boolean z2, float f4, float f5) {
        float f6;
        float f7;
        float f8;
        boolean z3 = z;
        boolean z4 = z2;
        float f9 = mPenX;
        float f10 = mPenY;
        float abs = Math.abs(f2 == 0.0f ? f == 0.0f ? f5 - f10 : f : f2);
        float abs2 = Math.abs(f == 0.0f ? f4 - f9 : f);
        if (abs2 == 0.0f || abs == 0.0f || (f4 == f9 && f5 == f10)) {
            lineTo(f4, f5);
            return;
        }
        float radians = (float) Math.toRadians((double) f3);
        double d = (double) radians;
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        float f11 = f4 - f9;
        float f12 = f5 - f10;
        float f13 = ((cos * f11) / 2.0f) + ((sin * f12) / 2.0f);
        float f14 = -sin;
        float f15 = ((f14 * f11) / 2.0f) + ((cos * f12) / 2.0f);
        float f16 = abs2 * abs2;
        float f17 = f16 * abs * abs;
        float f18 = abs * abs * f13 * f13;
        float f19 = f16 * f15 * f15;
        float f20 = (f17 - f19) - f18;
        if (f20 < 0.0f) {
            f6 = f14;
            float sqrt = (float) Math.sqrt((double) (1.0f - (f20 / f17)));
            abs2 *= sqrt;
            abs *= sqrt;
            f7 = f11 / 2.0f;
            f8 = f12 / 2.0f;
        } else {
            f6 = f14;
            float sqrt2 = (float) Math.sqrt((double) (f20 / (f19 + f18)));
            if (z3 == z4) {
                sqrt2 = -sqrt2;
            }
            float f21 = (((-sqrt2) * f15) * abs2) / abs;
            float f22 = ((sqrt2 * f13) * abs) / abs2;
            f7 = ((cos * f21) - (sin * f22)) + (f11 / 2.0f);
            f8 = (f12 / 2.0f) + (f21 * sin) + (f22 * cos);
        }
        float f23 = cos / abs2;
        float f24 = sin / abs2;
        float f25 = f6 / abs;
        float f26 = cos / abs;
        float f27 = -f7;
        float f28 = -f8;
        float f29 = abs;
        float f30 = abs2;
        float f31 = radians;
        float atan2 = (float) Math.atan2((double) ((f25 * f27) + (f26 * f28)), (double) ((f27 * f23) + (f28 * f24)));
        float f32 = f11 - f7;
        float f33 = f12 - f8;
        float atan22 = (float) Math.atan2((double) ((f25 * f32) + (f26 * f33)), (double) ((f23 * f32) + (f24 * f33)));
        float f34 = f7 + f9;
        float f35 = f8 + f10;
        float f36 = f11 + f9;
        float f37 = f12 + f10;
        setPenDown();
        mPivotX = f36;
        mPenX = f36;
        mPivotY = f37;
        mPenY = f37;
        if (f30 == f29 && f31 == 0.0f) {
            float degrees = (float) Math.toDegrees((double) atan2);
            float abs3 = Math.abs((degrees - ((float) Math.toDegrees((double) atan22))) % 360.0f);
            if (!z ? abs3 > 180.0f : abs3 < 180.0f) {
                abs3 = 360.0f - abs3;
            }
            if (!z2) {
                abs3 = -abs3;
            }
            float f38 = mScale;
            mPath.arcTo(new RectF((f34 - f30) * f38, (f35 - f30) * f38, (f34 + f30) * f38, (f35 + f30) * f38), degrees, abs3);
            elements.add(new PathElement(ElementType.kCGPathElementAddCurveToPoint, new Point[]{new Point((double) f36, (double) f37)}));
            return;
        }
        arcToBezier(f34, f35, f30, f29, atan2, atan22, z2, f31);
    }

    private static void close() {
        if (mPenDown) {
            mPenX = mPenDownX;
            mPenY = mPenDownY;
            mPenDown = false;
            mPath.close();
            elements.add(new PathElement(ElementType.kCGPathElementCloseSubpath, new Point[]{new Point((double) mPenX, (double) mPenY)}));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0067 A[LOOP:0: B:10:0x0065->B:11:0x0067, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void arcToBezier(float r24, float r25, float r26, float r27, float r28, float r29, boolean r30, float r31) {
        /*
            r0 = r28
            r1 = r31
            double r1 = (double) r1
            double r3 = java.lang.Math.cos(r1)
            float r3 = (float) r3
            double r1 = java.lang.Math.sin(r1)
            float r1 = (float) r1
            float r2 = r3 * r26
            float r4 = -r1
            float r4 = r4 * r27
            float r1 = r1 * r26
            float r3 = r3 * r27
            float r5 = r29 - r0
            r6 = 0
            int r7 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            r8 = 4618760256179416344(0x401921fb54442d18, double:6.283185307179586)
            if (r7 >= 0) goto L_0x002a
            if (r30 == 0) goto L_0x002a
            double r5 = (double) r5
            double r5 = r5 + r8
        L_0x0028:
            float r5 = (float) r5
            goto L_0x0033
        L_0x002a:
            int r6 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x0033
            if (r30 != 0) goto L_0x0033
            double r5 = (double) r5
            double r5 = r5 - r8
            goto L_0x0028
        L_0x0033:
            double r6 = (double) r5
            r8 = 4609753056924675352(0x3ff921fb54442d18, double:1.5707963267948966)
            double r6 = r6 / r8
            double r6 = round(r6)
            double r6 = java.lang.Math.abs(r6)
            double r6 = java.lang.Math.ceil(r6)
            int r6 = (int) r6
            float r7 = (float) r6
            float r5 = r5 / r7
            r7 = 1082130432(0x40800000, float:4.0)
            float r7 = r5 / r7
            double r7 = (double) r7
            double r7 = java.lang.Math.tan(r7)
            r9 = 4608683618675807573(0x3ff5555555555555, double:1.3333333333333333)
            double r7 = r7 * r9
            float r7 = (float) r7
            double r8 = (double) r0
            double r10 = java.lang.Math.cos(r8)
            float r10 = (float) r10
            double r8 = java.lang.Math.sin(r8)
            float r8 = (float) r8
            r9 = 0
        L_0x0065:
            if (r9 >= r6) goto L_0x0113
            float r11 = r7 * r8
            float r11 = r10 - r11
            float r10 = r10 * r7
            float r8 = r8 + r10
            float r0 = r0 + r5
            double r12 = (double) r0
            double r14 = java.lang.Math.cos(r12)
            float r10 = (float) r14
            double r12 = java.lang.Math.sin(r12)
            float r12 = (float) r12
            float r13 = r7 * r12
            float r13 = r13 + r10
            float r14 = r7 * r10
            float r14 = r12 - r14
            float r15 = r2 * r11
            float r15 = r24 + r15
            float r16 = r4 * r8
            float r15 = r15 + r16
            float r11 = r11 * r1
            float r11 = r25 + r11
            float r8 = r8 * r3
            float r11 = r11 + r8
            float r8 = r2 * r13
            float r8 = r24 + r8
            float r16 = r4 * r14
            float r8 = r8 + r16
            float r13 = r13 * r1
            float r13 = r25 + r13
            float r14 = r14 * r3
            float r13 = r13 + r14
            float r14 = r2 * r10
            float r14 = r24 + r14
            float r16 = r4 * r12
            float r14 = r14 + r16
            float r16 = r1 * r10
            float r16 = r25 + r16
            float r17 = r3 * r12
            r26 = r0
            float r0 = r16 + r17
            android.graphics.Path r16 = mPath
            float r17 = mScale
            float r18 = r15 * r17
            float r19 = r11 * r17
            float r20 = r8 * r17
            float r21 = r13 * r17
            float r22 = r14 * r17
            float r23 = r0 * r17
            r17 = r18
            r18 = r19
            r19 = r20
            r20 = r21
            r21 = r22
            r22 = r23
            r16.cubicTo(r17, r18, r19, r20, r21, r22)
            r31 = r1
            java.util.ArrayList r1 = elements
            r16 = r2
            com.horcrux.svg.PathElement r2 = new com.horcrux.svg.PathElement
            r27 = r3
            com.horcrux.svg.ElementType r3 = com.horcrux.svg.ElementType.kCGPathElementAddCurveToPoint
            r17 = r4
            com.horcrux.svg.Point r4 = new com.horcrux.svg.Point
            r29 = r5
            r18 = r6
            double r5 = (double) r15
            r15 = r10
            double r10 = (double) r11
            r4.<init>(r5, r10)
            com.horcrux.svg.Point r5 = new com.horcrux.svg.Point
            double r10 = (double) r8
            r8 = r7
            double r6 = (double) r13
            r5.<init>(r10, r6)
            com.horcrux.svg.Point r6 = new com.horcrux.svg.Point
            double r10 = (double) r14
            double r13 = (double) r0
            r6.<init>(r10, r13)
            com.horcrux.svg.Point[] r0 = new com.horcrux.svg.Point[]{r4, r5, r6}
            r2.<init>(r3, r0)
            r1.add(r2)
            int r9 = r9 + 1
            r0 = r26
            r3 = r27
            r5 = r29
            r1 = r31
            r7 = r8
            r8 = r12
            r10 = r15
            r2 = r16
            r4 = r17
            r6 = r18
            goto L_0x0065
        L_0x0113:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PathParser.arcToBezier(float, float, float, float, float, float, boolean, float):void");
    }

    private static void setPenDown() {
        if (!mPenDown) {
            mPenDownX = mPenX;
            mPenDownY = mPenY;
            mPenDown = true;
        }
    }

    private static double round(double d) {
        double pow = Math.pow(10.0d, 4.0d);
        return ((double) Math.round(d * pow)) / pow;
    }

    private static void skip_spaces() {
        while (true) {
            int i2 = i;
            if (i2 < l && Character.isWhitespace(s.charAt(i2))) {
                i++;
            } else {
                return;
            }
        }
    }

    private static boolean is_absolute(char c) {
        return Character.isUpperCase(c);
    }

    private static boolean parse_flag() {
        skip_spaces();
        char charAt = s.charAt(i);
        if (charAt == '0' || charAt == '1') {
            int i2 = i + 1;
            i = i2;
            if (i2 < l && s.charAt(i2) == ',') {
                i++;
            }
            skip_spaces();
            if (charAt == '1') {
                return true;
            }
            return false;
        }
        throw new Error(String.format("Unexpected flag '%c' (i=%d, s=%s)", new Object[]{Character.valueOf(charAt), Integer.valueOf(i), s}));
    }

    private static float parse_list_number() {
        if (i != l) {
            float parse_number = parse_number();
            skip_spaces();
            parse_list_separator();
            return parse_number;
        }
        throw new Error(String.format("Unexpected end (s=%s)", new Object[]{s}));
    }

    private static float parse_number() {
        char charAt;
        skip_spaces();
        int i2 = i;
        if (i2 != l) {
            char charAt2 = s.charAt(i2);
            if (charAt2 == '-' || charAt2 == '+') {
                int i3 = i + 1;
                i = i3;
                charAt2 = s.charAt(i3);
            }
            if (charAt2 >= '0' && charAt2 <= '9') {
                skip_digits();
                int i4 = i;
                if (i4 < l) {
                    charAt2 = s.charAt(i4);
                }
            } else if (charAt2 != '.') {
                throw new IllegalArgumentException(String.format("Invalid number formating character '%c' (i=%d, s=%s)", new Object[]{Character.valueOf(charAt2), Integer.valueOf(i), s}));
            }
            if (charAt2 == '.') {
                i++;
                skip_digits();
                int i5 = i;
                if (i5 < l) {
                    charAt2 = s.charAt(i5);
                }
            }
            if (charAt2 == 'e' || charAt2 == 'E') {
                int i6 = i;
                if (!(i6 + 1 >= l || (charAt = s.charAt(i6 + 1)) == 'm' || charAt == 'x')) {
                    int i7 = i + 1;
                    i = i7;
                    char charAt3 = s.charAt(i7);
                    if (charAt3 == '+' || charAt3 == '-') {
                        i++;
                        skip_digits();
                    } else if (charAt3 < '0' || charAt3 > '9') {
                        throw new IllegalArgumentException(String.format("Invalid number formating character '%c' (i=%d, s=%s)", new Object[]{Character.valueOf(charAt3), Integer.valueOf(i), s}));
                    } else {
                        skip_digits();
                    }
                }
            }
            String substring = s.substring(i2, i);
            float parseFloat = Float.parseFloat(substring);
            if (!Float.isInfinite(parseFloat) && !Float.isNaN(parseFloat)) {
                return parseFloat;
            }
            throw new IllegalArgumentException(String.format("Invalid number '%s' (start=%d, i=%d, s=%s)", new Object[]{substring, Integer.valueOf(i2), Integer.valueOf(i), s}));
        }
        throw new Error(String.format("Unexpected end (s=%s)", new Object[]{s}));
    }

    private static void parse_list_separator() {
        int i2 = i;
        if (i2 < l && s.charAt(i2) == ',') {
            i++;
        }
    }

    private static void skip_digits() {
        while (true) {
            int i2 = i;
            if (i2 < l && Character.isDigit(s.charAt(i2))) {
                i++;
            } else {
                return;
            }
        }
    }
}
