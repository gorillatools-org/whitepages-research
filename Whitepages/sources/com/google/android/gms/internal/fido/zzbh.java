package com.google.android.gms.internal.fido;

import java.math.RoundingMode;

public final class zzbh {
    public static int zzb(int i, RoundingMode roundingMode) {
        if (i > 0) {
            switch (zzbg.zza[roundingMode.ordinal()]) {
                case 1:
                    zzbi.zza(((i + -1) & i) == 0);
                    break;
                case 2:
                case 3:
                    break;
                case 4:
                case 5:
                    return 32 - Integer.numberOfLeadingZeros(i - 1);
                case 6:
                case 7:
                case 8:
                    int numberOfLeadingZeros = Integer.numberOfLeadingZeros(i);
                    return (31 - numberOfLeadingZeros) + (((-1257966797 >>> numberOfLeadingZeros) - i) >>> 31);
                default:
                    throw new AssertionError();
            }
            return 31 - Integer.numberOfLeadingZeros(i);
        }
        throw new IllegalArgumentException("x (0) must be > 0");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        if (((r0 & 1) & r3) != 0) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
        if (r1 > 0) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
        if (r5 > 0) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        if (r5 < 0) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        return r0 + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004e, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zza(int r5, int r6, java.math.RoundingMode r7) {
        /*
            r7.getClass()
            if (r6 == 0) goto L_0x004f
            int r0 = r5 / r6
            int r1 = r6 * r0
            int r1 = r5 - r1
            if (r1 != 0) goto L_0x000e
            return r0
        L_0x000e:
            r5 = r5 ^ r6
            int[] r2 = com.google.android.gms.internal.fido.zzbg.zza
            int r3 = r7.ordinal()
            r2 = r2[r3]
            int r5 = r5 >> 31
            r3 = 1
            r5 = r5 | r3
            r4 = 0
            switch(r2) {
                case 1: goto L_0x004b;
                case 2: goto L_0x004e;
                case 3: goto L_0x0047;
                case 4: goto L_0x0049;
                case 5: goto L_0x0044;
                case 6: goto L_0x0025;
                case 7: goto L_0x0025;
                case 8: goto L_0x0025;
                default: goto L_0x001f;
            }
        L_0x001f:
            java.lang.AssertionError r5 = new java.lang.AssertionError
            r5.<init>()
            throw r5
        L_0x0025:
            int r1 = java.lang.Math.abs(r1)
            int r6 = java.lang.Math.abs(r6)
            int r6 = r6 - r1
            int r1 = r1 - r6
            if (r1 != 0) goto L_0x0041
            java.math.RoundingMode r6 = java.math.RoundingMode.HALF_UP
            if (r7 == r6) goto L_0x0049
            java.math.RoundingMode r6 = java.math.RoundingMode.HALF_EVEN
            if (r7 != r6) goto L_0x003a
            goto L_0x003b
        L_0x003a:
            r3 = r4
        L_0x003b:
            r6 = r0 & 1
            r6 = r6 & r3
            if (r6 == 0) goto L_0x004e
            goto L_0x0049
        L_0x0041:
            if (r1 <= 0) goto L_0x004e
            goto L_0x0049
        L_0x0044:
            if (r5 <= 0) goto L_0x004e
            goto L_0x0049
        L_0x0047:
            if (r5 >= 0) goto L_0x004e
        L_0x0049:
            int r0 = r0 + r5
            return r0
        L_0x004b:
            com.google.android.gms.internal.fido.zzbi.zza(r4)
        L_0x004e:
            return r0
        L_0x004f:
            java.lang.ArithmeticException r5 = new java.lang.ArithmeticException
            java.lang.String r6 = "/ by zero"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fido.zzbh.zza(int, int, java.math.RoundingMode):int");
    }
}
