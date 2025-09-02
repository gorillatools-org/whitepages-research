package com.horcrux.svg;

import com.facebook.react.bridge.ReadableArray;
import com.horcrux.svg.SVGLength;

abstract class PropHelper {
    static int toMatrixData(ReadableArray readableArray, float[] fArr, float f) {
        int size = readableArray.size();
        if (size != 6) {
            return size;
        }
        fArr[0] = (float) readableArray.getDouble(0);
        fArr[1] = (float) readableArray.getDouble(2);
        fArr[2] = ((float) readableArray.getDouble(4)) * f;
        fArr[3] = (float) readableArray.getDouble(1);
        fArr[4] = (float) readableArray.getDouble(3);
        fArr[5] = ((float) readableArray.getDouble(5)) * f;
        return 6;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007b, code lost:
        if (r10.equals("in") == false) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0096, code lost:
        r13 = 1.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009c, code lost:
        r2 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b3, code lost:
        r8 = java.lang.Double.valueOf(r8.substring(0, r2)).doubleValue() * r13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static double fromRelative(java.lang.String r8, double r9, double r11, double r13) {
        /*
            r0 = 2
            r1 = 0
            java.lang.String r8 = r8.trim()
            int r2 = r8.length()
            r3 = 1
            int r4 = r2 + -1
            if (r2 == 0) goto L_0x00cb
            java.lang.String r5 = "normal"
            boolean r5 = r8.equals(r5)
            if (r5 == 0) goto L_0x0019
            goto L_0x00cb
        L_0x0019:
            int r5 = r8.codePointAt(r4)
            r6 = 37
            if (r5 != r6) goto L_0x0032
            java.lang.String r8 = r8.substring(r1, r4)
            java.lang.Double r8 = java.lang.Double.valueOf(r8)
            double r11 = r8.doubleValue()
            r13 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r11 = r11 / r13
            double r11 = r11 * r9
            return r11
        L_0x0032:
            int r9 = r2 + -2
            if (r9 <= 0) goto L_0x00c2
            java.lang.String r10 = r8.substring(r9)
            r10.hashCode()
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r6 = -1
            int r7 = r10.hashCode()
            switch(r7) {
                case 3178: goto L_0x0089;
                case 3240: goto L_0x007e;
                case 3365: goto L_0x0075;
                case 3488: goto L_0x006a;
                case 3571: goto L_0x005f;
                case 3588: goto L_0x0054;
                case 3592: goto L_0x0049;
                default: goto L_0x0047;
            }
        L_0x0047:
            r0 = r6
            goto L_0x0093
        L_0x0049:
            java.lang.String r0 = "px"
            boolean r10 = r10.equals(r0)
            if (r10 != 0) goto L_0x0052
            goto L_0x0047
        L_0x0052:
            r0 = 6
            goto L_0x0093
        L_0x0054:
            java.lang.String r0 = "pt"
            boolean r10 = r10.equals(r0)
            if (r10 != 0) goto L_0x005d
            goto L_0x0047
        L_0x005d:
            r0 = 5
            goto L_0x0093
        L_0x005f:
            java.lang.String r0 = "pc"
            boolean r10 = r10.equals(r0)
            if (r10 != 0) goto L_0x0068
            goto L_0x0047
        L_0x0068:
            r0 = 4
            goto L_0x0093
        L_0x006a:
            java.lang.String r0 = "mm"
            boolean r10 = r10.equals(r0)
            if (r10 != 0) goto L_0x0073
            goto L_0x0047
        L_0x0073:
            r0 = 3
            goto L_0x0093
        L_0x0075:
            java.lang.String r3 = "in"
            boolean r10 = r10.equals(r3)
            if (r10 != 0) goto L_0x0093
            goto L_0x0047
        L_0x007e:
            java.lang.String r0 = "em"
            boolean r10 = r10.equals(r0)
            if (r10 != 0) goto L_0x0087
            goto L_0x0047
        L_0x0087:
            r0 = r3
            goto L_0x0093
        L_0x0089:
            java.lang.String r0 = "cm"
            boolean r10 = r10.equals(r0)
            if (r10 != 0) goto L_0x0092
            goto L_0x0047
        L_0x0092:
            r0 = r1
        L_0x0093:
            switch(r0) {
                case 0: goto L_0x00ad;
                case 1: goto L_0x009c;
                case 2: goto L_0x00a7;
                case 3: goto L_0x00a1;
                case 4: goto L_0x009e;
                case 5: goto L_0x009a;
                case 6: goto L_0x0098;
                default: goto L_0x0096;
            }
        L_0x0096:
            r13 = r4
            goto L_0x00b3
        L_0x0098:
            r2 = r9
            goto L_0x0096
        L_0x009a:
            r13 = 4608308318706860032(0x3ff4000000000000, double:1.25)
        L_0x009c:
            r2 = r9
            goto L_0x00b3
        L_0x009e:
            r13 = 4624633867356078080(0x402e000000000000, double:15.0)
            goto L_0x009c
        L_0x00a1:
            r13 = 4615161236842447043(0x400c58b1572580c3, double:3.543307)
            goto L_0x009c
        L_0x00a7:
            r13 = 4636033603912859648(0x4056800000000000, double:90.0)
            goto L_0x009c
        L_0x00ad:
            r13 = 4630183578586017914(0x4041b76ed677707a, double:35.43307)
            goto L_0x009c
        L_0x00b3:
            java.lang.String r8 = r8.substring(r1, r2)
            java.lang.Double r8 = java.lang.Double.valueOf(r8)
            double r8 = r8.doubleValue()
            double r8 = r8 * r13
        L_0x00c0:
            double r8 = r8 * r11
            return r8
        L_0x00c2:
            java.lang.Double r8 = java.lang.Double.valueOf(r8)
            double r8 = r8.doubleValue()
            goto L_0x00c0
        L_0x00cb:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PropHelper.fromRelative(java.lang.String, double, double, double):double");
    }

    static double fromRelative(SVGLength sVGLength, double d, double d2, double d3, double d4) {
        double d5;
        if (sVGLength == null) {
            return d2;
        }
        SVGLength.UnitType unitType = sVGLength.unit;
        double d6 = sVGLength.value;
        switch (AnonymousClass1.$SwitchMap$com$horcrux$svg$SVGLength$UnitType[unitType.ordinal()]) {
            case 1:
            case 2:
                d4 = 1.0d;
                break;
            case 3:
                d5 = (d6 / 100.0d) * d;
                break;
            case 4:
                break;
            case 5:
                d4 /= 2.0d;
                break;
            case 6:
                d4 = 35.43307d;
                break;
            case 7:
                d4 = 3.543307d;
                break;
            case 8:
                d4 = 90.0d;
                break;
            case 9:
                d4 = 1.25d;
                break;
            case 10:
                d4 = 15.0d;
                break;
        }
        d6 *= d4;
        d5 = d6 * d3;
        return d5 + d2;
    }

    /* renamed from: com.horcrux.svg.PropHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$SVGLength$UnitType;

        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|24) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.horcrux.svg.SVGLength$UnitType[] r0 = com.horcrux.svg.SVGLength.UnitType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType = r0
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.NUMBER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.PX     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.PERCENTAGE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.EMS     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.EXS     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.CM     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.MM     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.IN     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x006c }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.PT     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.PC     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PropHelper.AnonymousClass1.<clinit>():void");
        }
    }
}
