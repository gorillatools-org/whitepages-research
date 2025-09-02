package com.horcrux.svg;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import java.util.ArrayList;

class SVGLength {
    final UnitType unit;
    final double value;

    public enum UnitType {
        UNKNOWN,
        NUMBER,
        PERCENTAGE,
        EMS,
        EXS,
        PX,
        CM,
        MM,
        IN,
        PT,
        PC
    }

    private SVGLength() {
        this.value = 0.0d;
        this.unit = UnitType.UNKNOWN;
    }

    SVGLength(double d) {
        this.value = d;
        this.unit = UnitType.NUMBER;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008b, code lost:
        if (r5.equals("ex") == false) goto L_0x004b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    SVGLength(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 2
            r1 = 0
            r8.<init>()
            java.lang.String r9 = r9.trim()
            int r2 = r9.length()
            r3 = 1
            int r4 = r2 + -1
            if (r2 == 0) goto L_0x00f1
            java.lang.String r5 = "normal"
            boolean r5 = r9.equals(r5)
            if (r5 == 0) goto L_0x001c
            goto L_0x00f1
        L_0x001c:
            int r5 = r9.codePointAt(r4)
            r6 = 37
            if (r5 != r6) goto L_0x0038
            com.horcrux.svg.SVGLength$UnitType r0 = com.horcrux.svg.SVGLength.UnitType.PERCENTAGE
            r8.unit = r0
            java.lang.String r9 = r9.substring(r1, r4)
            java.lang.Double r9 = java.lang.Double.valueOf(r9)
            double r0 = r9.doubleValue()
            r8.value = r0
            goto L_0x00f9
        L_0x0038:
            int r4 = r2 + -2
            if (r4 <= 0) goto L_0x00e2
            java.lang.String r5 = r9.substring(r4)
            r5.hashCode()
            r6 = -1
            int r7 = r5.hashCode()
            switch(r7) {
                case 3178: goto L_0x0099;
                case 3240: goto L_0x008e;
                case 3251: goto L_0x0085;
                case 3365: goto L_0x007a;
                case 3488: goto L_0x006f;
                case 3571: goto L_0x0064;
                case 3588: goto L_0x0059;
                case 3592: goto L_0x004e;
                default: goto L_0x004b;
            }
        L_0x004b:
            r0 = r6
            goto L_0x00a3
        L_0x004e:
            java.lang.String r0 = "px"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0057
            goto L_0x004b
        L_0x0057:
            r0 = 7
            goto L_0x00a3
        L_0x0059:
            java.lang.String r0 = "pt"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0062
            goto L_0x004b
        L_0x0062:
            r0 = 6
            goto L_0x00a3
        L_0x0064:
            java.lang.String r0 = "pc"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x006d
            goto L_0x004b
        L_0x006d:
            r0 = 5
            goto L_0x00a3
        L_0x006f:
            java.lang.String r0 = "mm"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0078
            goto L_0x004b
        L_0x0078:
            r0 = 4
            goto L_0x00a3
        L_0x007a:
            java.lang.String r0 = "in"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0083
            goto L_0x004b
        L_0x0083:
            r0 = 3
            goto L_0x00a3
        L_0x0085:
            java.lang.String r3 = "ex"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x00a3
            goto L_0x004b
        L_0x008e:
            java.lang.String r0 = "em"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x0097
            goto L_0x004b
        L_0x0097:
            r0 = r3
            goto L_0x00a3
        L_0x0099:
            java.lang.String r0 = "cm"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x00a2
            goto L_0x004b
        L_0x00a2:
            r0 = r1
        L_0x00a3:
            switch(r0) {
                case 0: goto L_0x00ce;
                case 1: goto L_0x00c9;
                case 2: goto L_0x00c4;
                case 3: goto L_0x00bf;
                case 4: goto L_0x00ba;
                case 5: goto L_0x00b5;
                case 6: goto L_0x00b0;
                case 7: goto L_0x00ab;
                default: goto L_0x00a6;
            }
        L_0x00a6:
            com.horcrux.svg.SVGLength$UnitType r0 = com.horcrux.svg.SVGLength.UnitType.NUMBER
            r8.unit = r0
            goto L_0x00d3
        L_0x00ab:
            com.horcrux.svg.SVGLength$UnitType r0 = com.horcrux.svg.SVGLength.UnitType.NUMBER
            r8.unit = r0
            goto L_0x00d2
        L_0x00b0:
            com.horcrux.svg.SVGLength$UnitType r0 = com.horcrux.svg.SVGLength.UnitType.PT
            r8.unit = r0
            goto L_0x00d2
        L_0x00b5:
            com.horcrux.svg.SVGLength$UnitType r0 = com.horcrux.svg.SVGLength.UnitType.PC
            r8.unit = r0
            goto L_0x00d2
        L_0x00ba:
            com.horcrux.svg.SVGLength$UnitType r0 = com.horcrux.svg.SVGLength.UnitType.MM
            r8.unit = r0
            goto L_0x00d2
        L_0x00bf:
            com.horcrux.svg.SVGLength$UnitType r0 = com.horcrux.svg.SVGLength.UnitType.IN
            r8.unit = r0
            goto L_0x00d2
        L_0x00c4:
            com.horcrux.svg.SVGLength$UnitType r0 = com.horcrux.svg.SVGLength.UnitType.EXS
            r8.unit = r0
            goto L_0x00d2
        L_0x00c9:
            com.horcrux.svg.SVGLength$UnitType r0 = com.horcrux.svg.SVGLength.UnitType.EMS
            r8.unit = r0
            goto L_0x00d2
        L_0x00ce:
            com.horcrux.svg.SVGLength$UnitType r0 = com.horcrux.svg.SVGLength.UnitType.CM
            r8.unit = r0
        L_0x00d2:
            r2 = r4
        L_0x00d3:
            java.lang.String r9 = r9.substring(r1, r2)
            java.lang.Double r9 = java.lang.Double.valueOf(r9)
            double r0 = r9.doubleValue()
            r8.value = r0
            goto L_0x00f9
        L_0x00e2:
            com.horcrux.svg.SVGLength$UnitType r0 = com.horcrux.svg.SVGLength.UnitType.NUMBER
            r8.unit = r0
            java.lang.Double r9 = java.lang.Double.valueOf(r9)
            double r0 = r9.doubleValue()
            r8.value = r0
            goto L_0x00f9
        L_0x00f1:
            com.horcrux.svg.SVGLength$UnitType r9 = com.horcrux.svg.SVGLength.UnitType.UNKNOWN
            r8.unit = r9
            r0 = 0
            r8.value = r0
        L_0x00f9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.SVGLength.<init>(java.lang.String):void");
    }

    /* renamed from: com.horcrux.svg.SVGLength$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
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
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Array     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.SVGLength.AnonymousClass1.<clinit>():void");
        }
    }

    static SVGLength from(Dynamic dynamic) {
        int i = AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[dynamic.getType().ordinal()];
        if (i == 1) {
            return new SVGLength(dynamic.asDouble());
        }
        if (i != 2) {
            return new SVGLength();
        }
        return new SVGLength(dynamic.asString());
    }

    static String toString(Dynamic dynamic) {
        int i = AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[dynamic.getType().ordinal()];
        if (i == 1) {
            return String.valueOf(dynamic.asDouble());
        }
        if (i != 2) {
            return null;
        }
        return dynamic.asString();
    }

    static ArrayList arrayFrom(Dynamic dynamic) {
        int i = AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[dynamic.getType().ordinal()];
        if (i != 1) {
            int i2 = 0;
            if (i == 2) {
                String[] split = dynamic.asString().trim().replaceAll(",", " ").split(" ");
                ArrayList arrayList = new ArrayList(split.length);
                int length = split.length;
                while (i2 < length) {
                    arrayList.add(new SVGLength(split[i2]));
                    i2++;
                }
                return arrayList;
            } else if (i != 3) {
                return null;
            } else {
                ReadableArray asArray = dynamic.asArray();
                int size = asArray.size();
                ArrayList arrayList2 = new ArrayList(size);
                while (i2 < size) {
                    arrayList2.add(from(asArray.getDynamic(i2)));
                    i2++;
                }
                return arrayList2;
            }
        } else {
            ArrayList arrayList3 = new ArrayList(1);
            arrayList3.add(new SVGLength(dynamic.asDouble()));
            return arrayList3;
        }
    }
}
