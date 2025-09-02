package com.google.android.gms.internal.auth;

import com.facebook.react.uimanager.drawable.InsetBoxShadowDrawableKt;
import com.facebook.react.uimanager.drawable.OutsetBoxShadowDrawableKt;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.fido.u2f.api.common.RegisterRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

final class zzga<T> implements zzgi<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzhj.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzfx zzg;
    private final int[] zzh;
    private final int zzi;
    private final int zzj;
    private final zzfl zzk;
    private final zzgz zzl;
    private final zzem zzm;
    private final zzgc zzn;
    private final zzfs zzo;

    private zzga(int[] iArr, Object[] objArr, int i, int i2, zzfx zzfx, int i3, boolean z, int[] iArr2, int i4, int i5, zzgc zzgc, zzfl zzfl, zzgz zzgz, zzem zzem, zzfs zzfs) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzh = iArr2;
        this.zzi = i4;
        this.zzj = i5;
        this.zzn = zzgc;
        this.zzk = zzfl;
        this.zzl = zzgz;
        this.zzm = zzem;
        this.zzg = zzfx;
        this.zzo = zzfs;
    }

    private final void zzA(Object obj, int i, int i2) {
        zzhj.zzn(obj, (long) (zzl(i2) & 1048575), i);
    }

    private final void zzB(Object obj, int i, Object obj2) {
        zzb.putObject(obj, (long) (zzo(i) & 1048575), obj2);
        zzz(obj, i);
    }

    private final void zzC(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, (long) (zzo(i2) & 1048575), obj2);
        zzA(obj, i, i2);
    }

    private final boolean zzD(Object obj, Object obj2, int i) {
        return zzE(obj, i) == zzE(obj2, i);
    }

    private final boolean zzE(Object obj, int i) {
        int zzl2 = zzl(i);
        long j = (long) (zzl2 & 1048575);
        if (j == 1048575) {
            int zzo2 = zzo(i);
            long j2 = (long) (zzo2 & 1048575);
            switch (zzn(zzo2)) {
                case 0:
                    return Double.doubleToRawLongBits(zzhj.zza(obj, j2)) != 0;
                case 1:
                    return Float.floatToRawIntBits(zzhj.zzb(obj, j2)) != 0;
                case 2:
                    return zzhj.zzd(obj, j2) != 0;
                case 3:
                    return zzhj.zzd(obj, j2) != 0;
                case 4:
                    return zzhj.zzc(obj, j2) != 0;
                case 5:
                    return zzhj.zzd(obj, j2) != 0;
                case 6:
                    return zzhj.zzc(obj, j2) != 0;
                case 7:
                    return zzhj.zzt(obj, j2);
                case 8:
                    Object zzf2 = zzhj.zzf(obj, j2);
                    if (zzf2 instanceof String) {
                        return !((String) zzf2).isEmpty();
                    }
                    if (zzf2 instanceof zzef) {
                        return !zzef.zzb.equals(zzf2);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzhj.zzf(obj, j2) != null;
                case 10:
                    return !zzef.zzb.equals(zzhj.zzf(obj, j2));
                case 11:
                    return zzhj.zzc(obj, j2) != 0;
                case 12:
                    return zzhj.zzc(obj, j2) != 0;
                case 13:
                    return zzhj.zzc(obj, j2) != 0;
                case 14:
                    return zzhj.zzd(obj, j2) != 0;
                case 15:
                    return zzhj.zzc(obj, j2) != 0;
                case 16:
                    return zzhj.zzd(obj, j2) != 0;
                case 17:
                    return zzhj.zzf(obj, j2) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            return (zzhj.zzc(obj, j) & (1 << (zzl2 >>> 20))) != 0;
        }
    }

    private final boolean zzF(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzE(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzG(Object obj, int i, zzgi zzgi) {
        return zzgi.zzi(zzhj.zzf(obj, (long) (i & 1048575)));
    }

    private static boolean zzH(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzev) {
            return ((zzev) obj).zzm();
        }
        return true;
    }

    private final boolean zzI(Object obj, int i, int i2) {
        return zzhj.zzc(obj, (long) (zzl(i2) & 1048575)) == i;
    }

    static zzha zzc(Object obj) {
        zzev zzev = (zzev) obj;
        zzha zzha = zzev.zzc;
        if (zzha != zzha.zza()) {
            return zzha;
        }
        zzha zzd2 = zzha.zzd();
        zzev.zzc = zzd2;
        return zzd2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:120:0x0250  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0256  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x026c  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x026f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.gms.internal.auth.zzga zzj(java.lang.Class r31, com.google.android.gms.internal.auth.zzfu r32, com.google.android.gms.internal.auth.zzgc r33, com.google.android.gms.internal.auth.zzfl r34, com.google.android.gms.internal.auth.zzgz r35, com.google.android.gms.internal.auth.zzem r36, com.google.android.gms.internal.auth.zzfs r37) {
        /*
            r0 = r32
            boolean r1 = r0 instanceof com.google.android.gms.internal.auth.zzgh
            if (r1 == 0) goto L_0x03ee
            com.google.android.gms.internal.auth.zzgh r0 = (com.google.android.gms.internal.auth.zzgh) r0
            java.lang.String r1 = r0.zzd()
            int r2 = r1.length()
            r3 = 0
            char r4 = r1.charAt(r3)
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r5) goto L_0x0025
            r4 = 1
        L_0x001b:
            int r7 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x0026
            r4 = r7
            goto L_0x001b
        L_0x0025:
            r7 = 1
        L_0x0026:
            int r4 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r5) goto L_0x0045
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0032:
            int r10 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x0042
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r9
            r7 = r7 | r4
            int r9 = r9 + 13
            r4 = r10
            goto L_0x0032
        L_0x0042:
            int r4 = r4 << r9
            r7 = r7 | r4
            r4 = r10
        L_0x0045:
            if (r7 != 0) goto L_0x0057
            int[] r7 = zza
            r11 = r3
            r12 = r11
            r13 = r12
            r14 = r13
            r16 = r14
            r18 = r16
            r17 = r7
            r7 = r18
            goto L_0x0167
        L_0x0057:
            int r7 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x0076
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0063:
            int r10 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r5) goto L_0x0073
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r9
            r4 = r4 | r7
            int r9 = r9 + 13
            r7 = r10
            goto L_0x0063
        L_0x0073:
            int r7 = r7 << r9
            r4 = r4 | r7
            r7 = r10
        L_0x0076:
            int r9 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r5) goto L_0x0095
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0082:
            int r11 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r5) goto L_0x0092
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r10
            r7 = r7 | r9
            int r10 = r10 + 13
            r9 = r11
            goto L_0x0082
        L_0x0092:
            int r9 = r9 << r10
            r7 = r7 | r9
            r9 = r11
        L_0x0095:
            int r10 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r5) goto L_0x00b4
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r11 = 13
        L_0x00a1:
            int r12 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r5) goto L_0x00b1
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r11
            r9 = r9 | r10
            int r11 = r11 + 13
            r10 = r12
            goto L_0x00a1
        L_0x00b1:
            int r10 = r10 << r11
            r9 = r9 | r10
            r10 = r12
        L_0x00b4:
            int r11 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r5) goto L_0x00d3
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00c0:
            int r13 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r5) goto L_0x00d0
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r12
            r10 = r10 | r11
            int r12 = r12 + 13
            r11 = r13
            goto L_0x00c0
        L_0x00d0:
            int r11 = r11 << r12
            r10 = r10 | r11
            r11 = r13
        L_0x00d3:
            int r12 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r5) goto L_0x00f2
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00df:
            int r14 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r5) goto L_0x00ef
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r11 = r11 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00df
        L_0x00ef:
            int r12 = r12 << r13
            r11 = r11 | r12
            r12 = r14
        L_0x00f2:
            int r13 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r5) goto L_0x0111
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00fe:
            int r15 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r5) goto L_0x010e
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x00fe
        L_0x010e:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
        L_0x0111:
            int r14 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r5) goto L_0x0132
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x011d:
            int r16 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r5) goto L_0x012e
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r15
            r13 = r13 | r14
            int r15 = r15 + 13
            r14 = r16
            goto L_0x011d
        L_0x012e:
            int r14 = r14 << r15
            r13 = r13 | r14
            r14 = r16
        L_0x0132:
            int r15 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r5) goto L_0x0155
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x013e:
            int r17 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r5) goto L_0x0150
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r14 = r14 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x013e
        L_0x0150:
            int r15 = r15 << r16
            r14 = r14 | r15
            r15 = r17
        L_0x0155:
            int r16 = r14 + r12
            int r13 = r16 + r13
            int r16 = r4 + r4
            int r16 = r16 + r7
            int[] r7 = new int[r13]
            r17 = r7
            r13 = r9
            r18 = r14
            r7 = r4
            r14 = r10
            r4 = r15
        L_0x0167:
            sun.misc.Unsafe r9 = zzb
            java.lang.Object[] r10 = r0.zze()
            com.google.android.gms.internal.auth.zzfx r15 = r0.zza()
            java.lang.Class r15 = r15.getClass()
            int r19 = r18 + r12
            int r12 = r11 + r11
            int r11 = r11 * 3
            int[] r11 = new int[r11]
            java.lang.Object[] r12 = new java.lang.Object[r12]
            r20 = r3
            r21 = r20
            r22 = r18
            r23 = r19
        L_0x0187:
            if (r4 >= r2) goto L_0x03c9
            int r24 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x01af
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r3 = r24
            r24 = 13
        L_0x0197:
            int r25 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r5) goto L_0x01a9
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r24
            r4 = r4 | r3
            int r24 = r24 + 13
            r3 = r25
            goto L_0x0197
        L_0x01a9:
            int r3 = r3 << r24
            r4 = r4 | r3
            r3 = r25
            goto L_0x01b1
        L_0x01af:
            r3 = r24
        L_0x01b1:
            int r24 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r5) goto L_0x01d7
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r8 = r24
            r24 = 13
        L_0x01bf:
            int r25 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r5) goto L_0x01d1
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r24
            r3 = r3 | r8
            int r24 = r24 + 13
            r8 = r25
            goto L_0x01bf
        L_0x01d1:
            int r8 = r8 << r24
            r3 = r3 | r8
            r8 = r25
            goto L_0x01d9
        L_0x01d7:
            r8 = r24
        L_0x01d9:
            r6 = r3 & 1024(0x400, float:1.435E-42)
            if (r6 == 0) goto L_0x01e3
            int r6 = r20 + 1
            r17[r20] = r21
            r20 = r6
        L_0x01e3:
            r6 = r3 & 255(0xff, float:3.57E-43)
            r5 = 51
            if (r6 < r5) goto L_0x0289
            int r5 = r8 + 1
            char r8 = r1.charAt(r8)
            r26 = r2
            r2 = 55296(0xd800, float:7.7486E-41)
            if (r8 < r2) goto L_0x0214
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r29 = 13
        L_0x01fa:
            int r30 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r2) goto L_0x020f
            r2 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r29
            r8 = r8 | r2
            int r29 = r29 + 13
            r5 = r30
            r2 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01fa
        L_0x020f:
            int r2 = r5 << r29
            r8 = r8 | r2
            r5 = r30
        L_0x0214:
            int r2 = r6 + -51
            r29 = r5
            r5 = 9
            if (r2 == r5) goto L_0x023d
            r5 = 17
            if (r2 != r5) goto L_0x0221
            goto L_0x023d
        L_0x0221:
            r5 = 12
            if (r2 != r5) goto L_0x0249
            int r2 = r0.zzc()
            r5 = 1
            if (r2 == r5) goto L_0x0230
            r2 = r3 & 2048(0x800, float:2.87E-42)
            if (r2 == 0) goto L_0x0249
        L_0x0230:
            int r2 = r21 / 3
            int r2 = r2 + r2
            int r2 = r2 + r5
            int r5 = r16 + 1
            r16 = r10[r16]
            r12[r2] = r16
        L_0x023a:
            r16 = r5
            goto L_0x0249
        L_0x023d:
            int r2 = r21 / 3
            int r2 = r2 + r2
            r5 = 1
            int r2 = r2 + r5
            int r5 = r16 + 1
            r16 = r10[r16]
            r12[r2] = r16
            goto L_0x023a
        L_0x0249:
            int r8 = r8 + r8
            r2 = r10[r8]
            boolean r5 = r2 instanceof java.lang.reflect.Field
            if (r5 == 0) goto L_0x0256
            java.lang.reflect.Field r2 = (java.lang.reflect.Field) r2
        L_0x0252:
            r5 = r13
            r30 = r14
            goto L_0x025f
        L_0x0256:
            java.lang.String r2 = (java.lang.String) r2
            java.lang.reflect.Field r2 = zzv(r15, r2)
            r10[r8] = r2
            goto L_0x0252
        L_0x025f:
            long r13 = r9.objectFieldOffset(r2)
            int r2 = (int) r13
            int r8 = r8 + 1
            r13 = r10[r8]
            boolean r14 = r13 instanceof java.lang.reflect.Field
            if (r14 == 0) goto L_0x026f
            java.lang.reflect.Field r13 = (java.lang.reflect.Field) r13
            goto L_0x0277
        L_0x026f:
            java.lang.String r13 = (java.lang.String) r13
            java.lang.reflect.Field r13 = zzv(r15, r13)
            r10[r8] = r13
        L_0x0277:
            long r13 = r9.objectFieldOffset(r13)
            int r8 = (int) r13
            r28 = r1
            r27 = r5
            r14 = r16
            r25 = r29
            r16 = r8
            r8 = 0
            goto L_0x0389
        L_0x0289:
            r26 = r2
            r5 = r13
            r30 = r14
            int r2 = r16 + 1
            r13 = r10[r16]
            java.lang.String r13 = (java.lang.String) r13
            java.lang.reflect.Field r13 = zzv(r15, r13)
            r14 = 9
            if (r6 == r14) goto L_0x02a0
            r14 = 17
            if (r6 != r14) goto L_0x02a5
        L_0x02a0:
            r27 = r5
            r5 = 1
            goto L_0x030e
        L_0x02a5:
            r14 = 27
            if (r6 == r14) goto L_0x02ad
            r14 = 49
            if (r6 != r14) goto L_0x02b1
        L_0x02ad:
            r27 = r5
            r5 = 1
            goto L_0x0303
        L_0x02b1:
            r14 = 12
            if (r6 == r14) goto L_0x02e9
            r14 = 30
            if (r6 == r14) goto L_0x02e9
            r14 = 44
            if (r6 != r14) goto L_0x02be
            goto L_0x02e9
        L_0x02be:
            r14 = 50
            if (r6 != r14) goto L_0x02e6
            int r14 = r22 + 1
            r17[r22] = r21
            int r22 = r21 / 3
            int r27 = r16 + 2
            r2 = r10[r2]
            int r22 = r22 + r22
            r12[r22] = r2
            r2 = r3 & 2048(0x800, float:2.87E-42)
            if (r2 == 0) goto L_0x02e2
            int r22 = r22 + 1
            int r2 = r16 + 3
            r16 = r10[r27]
            r12[r22] = r16
            r27 = r5
            r22 = r14
        L_0x02e0:
            r5 = 1
            goto L_0x0318
        L_0x02e2:
            r22 = r14
            r2 = r27
        L_0x02e6:
            r27 = r5
            goto L_0x02e0
        L_0x02e9:
            int r14 = r0.zzc()
            r27 = r5
            r5 = 1
            if (r14 == r5) goto L_0x02f6
            r14 = r3 & 2048(0x800, float:2.87E-42)
            if (r14 == 0) goto L_0x0318
        L_0x02f6:
            int r14 = r21 / 3
            int r14 = r14 + r14
            int r14 = r14 + r5
            int r16 = r16 + 2
            r2 = r10[r2]
            r12[r14] = r2
        L_0x0300:
            r2 = r16
            goto L_0x0318
        L_0x0303:
            int r14 = r21 / 3
            int r14 = r14 + r14
            int r14 = r14 + r5
            int r16 = r16 + 2
            r2 = r10[r2]
            r12[r14] = r2
            goto L_0x0300
        L_0x030e:
            int r14 = r21 / 3
            int r14 = r14 + r14
            int r14 = r14 + r5
            java.lang.Class r16 = r13.getType()
            r12[r14] = r16
        L_0x0318:
            long r13 = r9.objectFieldOffset(r13)
            int r13 = (int) r13
            r14 = r3 & 4096(0x1000, float:5.74E-42)
            r16 = 1048575(0xfffff, float:1.469367E-39)
            if (r14 == 0) goto L_0x0374
            r14 = 17
            if (r6 > r14) goto L_0x0374
            int r14 = r8 + 1
            char r8 = r1.charAt(r8)
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r8 < r5) goto L_0x034d
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x0337:
            int r25 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r5) goto L_0x0349
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r16
            r8 = r8 | r14
            int r16 = r16 + 13
            r14 = r25
            goto L_0x0337
        L_0x0349:
            int r14 = r14 << r16
            r8 = r8 | r14
            goto L_0x034f
        L_0x034d:
            r25 = r14
        L_0x034f:
            int r14 = r7 + r7
            int r16 = r8 / 32
            int r14 = r14 + r16
            r5 = r10[r14]
            r28 = r1
            boolean r1 = r5 instanceof java.lang.reflect.Field
            if (r1 == 0) goto L_0x0361
            java.lang.reflect.Field r5 = (java.lang.reflect.Field) r5
        L_0x035f:
            r14 = r2
            goto L_0x036a
        L_0x0361:
            java.lang.String r5 = (java.lang.String) r5
            java.lang.reflect.Field r5 = zzv(r15, r5)
            r10[r14] = r5
            goto L_0x035f
        L_0x036a:
            long r1 = r9.objectFieldOffset(r5)
            int r1 = (int) r1
            int r8 = r8 % 32
            r16 = r1
            goto L_0x037a
        L_0x0374:
            r28 = r1
            r14 = r2
            r25 = r8
            r8 = 0
        L_0x037a:
            r1 = 18
            if (r6 < r1) goto L_0x0388
            r1 = 49
            if (r6 > r1) goto L_0x0388
            int r1 = r23 + 1
            r17[r23] = r13
            r23 = r1
        L_0x0388:
            r2 = r13
        L_0x0389:
            int r1 = r21 + 1
            r11[r21] = r4
            int r4 = r21 + 2
            r5 = r3 & 512(0x200, float:7.175E-43)
            if (r5 == 0) goto L_0x0396
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x0397
        L_0x0396:
            r5 = 0
        L_0x0397:
            r13 = r3 & 256(0x100, float:3.59E-43)
            if (r13 == 0) goto L_0x039e
            r13 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x039f
        L_0x039e:
            r13 = 0
        L_0x039f:
            r3 = r3 & 2048(0x800, float:2.87E-42)
            if (r3 == 0) goto L_0x03a6
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x03a7
        L_0x03a6:
            r3 = 0
        L_0x03a7:
            int r6 = r6 << 20
            r5 = r5 | r13
            r3 = r3 | r5
            r3 = r3 | r6
            r2 = r2 | r3
            r11[r1] = r2
            int r21 = r21 + 3
            int r1 = r8 << 20
            r1 = r1 | r16
            r11[r4] = r1
            r16 = r14
            r4 = r25
            r2 = r26
            r13 = r27
            r1 = r28
            r14 = r30
            r3 = 0
            r5 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0187
        L_0x03c9:
            r27 = r13
            r30 = r14
            com.google.android.gms.internal.auth.zzga r1 = new com.google.android.gms.internal.auth.zzga
            com.google.android.gms.internal.auth.zzfx r14 = r0.zza()
            int r15 = r0.zzc()
            r16 = 0
            r9 = r1
            r10 = r11
            r11 = r12
            r12 = r27
            r13 = r30
            r20 = r33
            r21 = r34
            r22 = r35
            r23 = r36
            r24 = r37
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
            return r1
        L_0x03ee:
            com.google.android.gms.internal.auth.zzgw r0 = (com.google.android.gms.internal.auth.zzgw) r0
            r0 = 0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzga.zzj(java.lang.Class, com.google.android.gms.internal.auth.zzfu, com.google.android.gms.internal.auth.zzgc, com.google.android.gms.internal.auth.zzfl, com.google.android.gms.internal.auth.zzgz, com.google.android.gms.internal.auth.zzem, com.google.android.gms.internal.auth.zzfs):com.google.android.gms.internal.auth.zzga");
    }

    private static int zzk(Object obj, long j) {
        return ((Integer) zzhj.zzf(obj, j)).intValue();
    }

    private final int zzl(int i) {
        return this.zzc[i + 2];
    }

    private final int zzm(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzn(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzo(int i) {
        return this.zzc[i + 1];
    }

    private static long zzp(Object obj, long j) {
        return ((Long) zzhj.zzf(obj, j)).longValue();
    }

    private final zzey zzq(int i) {
        int i2 = i / 3;
        return (zzey) this.zzd[i2 + i2 + 1];
    }

    private final zzgi zzr(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzgi zzgi = (zzgi) this.zzd[i3];
        if (zzgi != null) {
            return zzgi;
        }
        zzgi zzb2 = zzgf.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzs(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzt(Object obj, int i) {
        zzgi zzr = zzr(i);
        int zzo2 = zzo(i) & 1048575;
        if (!zzE(obj, i)) {
            return zzr.zzd();
        }
        Object object = zzb.getObject(obj, (long) zzo2);
        if (zzH(object)) {
            return object;
        }
        Object zzd2 = zzr.zzd();
        if (object != null) {
            zzr.zzf(zzd2, object);
        }
        return zzd2;
    }

    private final Object zzu(Object obj, int i, int i2) {
        zzgi zzr = zzr(i2);
        if (!zzI(obj, i, i2)) {
            return zzr.zzd();
        }
        Object object = zzb.getObject(obj, (long) (zzo(i2) & 1048575));
        if (zzH(object)) {
            return object;
        }
        Object zzd2 = zzr.zzd();
        if (object != null) {
            zzr.zzf(zzd2, object);
        }
        return zzd2;
    }

    private static Field zzv(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzw(Object obj) {
        if (!zzH(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(obj)));
        }
    }

    private final void zzx(Object obj, Object obj2, int i) {
        if (zzE(obj2, i)) {
            Unsafe unsafe = zzb;
            long zzo2 = (long) (zzo(i) & 1048575);
            Object object = unsafe.getObject(obj2, zzo2);
            if (object != null) {
                zzgi zzr = zzr(i);
                if (!zzE(obj, i)) {
                    if (!zzH(object)) {
                        unsafe.putObject(obj, zzo2, object);
                    } else {
                        Object zzd2 = zzr.zzd();
                        zzr.zzf(zzd2, object);
                        unsafe.putObject(obj, zzo2, zzd2);
                    }
                    zzz(obj, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, zzo2);
                if (!zzH(object2)) {
                    Object zzd3 = zzr.zzd();
                    zzr.zzf(zzd3, object2);
                    unsafe.putObject(obj, zzo2, zzd3);
                    object2 = zzd3;
                }
                zzr.zzf(object2, object);
                return;
            }
            int i2 = this.zzc[i];
            String obj3 = obj2.toString();
            throw new IllegalStateException("Source subfield " + i2 + " is present but null: " + obj3);
        }
    }

    private final void zzy(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzI(obj2, i2, i)) {
            Unsafe unsafe = zzb;
            long zzo2 = (long) (zzo(i) & 1048575);
            Object object = unsafe.getObject(obj2, zzo2);
            if (object != null) {
                zzgi zzr = zzr(i);
                if (!zzI(obj, i2, i)) {
                    if (!zzH(object)) {
                        unsafe.putObject(obj, zzo2, object);
                    } else {
                        Object zzd2 = zzr.zzd();
                        zzr.zzf(zzd2, object);
                        unsafe.putObject(obj, zzo2, zzd2);
                    }
                    zzA(obj, i2, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, zzo2);
                if (!zzH(object2)) {
                    Object zzd3 = zzr.zzd();
                    zzr.zzf(zzd3, object2);
                    unsafe.putObject(obj, zzo2, zzd3);
                    object2 = zzd3;
                }
                zzr.zzf(object2, object);
                return;
            }
            int i3 = this.zzc[i];
            String obj3 = obj2.toString();
            throw new IllegalStateException("Source subfield " + i3 + " is present but null: " + obj3);
        }
    }

    private final void zzz(Object obj, int i) {
        int zzl2 = zzl(i);
        long j = (long) (1048575 & zzl2);
        if (j != 1048575) {
            zzhj.zzn(obj, j, (1 << (zzl2 >>> 20)) | zzhj.zzc(obj, j));
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0042, code lost:
        r3 = (int) (r3 ^ (r3 >>> 32));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0178, code lost:
        r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0031, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x022a, code lost:
        r1 = r1 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(java.lang.Object r10) {
        /*
            r9 = this;
            int[] r0 = r9.zzc
            int r0 = r0.length
            r1 = 0
            r2 = r1
        L_0x0005:
            if (r1 >= r0) goto L_0x022e
            int r3 = r9.zzo(r1)
            int[] r4 = r9.zzc
            r4 = r4[r1]
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r3
            int r3 = zzn(r3)
            long r5 = (long) r5
            r7 = 37
            r8 = 32
            switch(r3) {
                case 0: goto L_0x021c;
                case 1: goto L_0x0210;
                case 2: goto L_0x0206;
                case 3: goto L_0x01fc;
                case 4: goto L_0x01f4;
                case 5: goto L_0x01ea;
                case 6: goto L_0x01e2;
                case 7: goto L_0x01d6;
                case 8: goto L_0x01c8;
                case 9: goto L_0x01bd;
                case 10: goto L_0x01b1;
                case 11: goto L_0x01a9;
                case 12: goto L_0x01a1;
                case 13: goto L_0x0199;
                case 14: goto L_0x018f;
                case 15: goto L_0x0187;
                case 16: goto L_0x017d;
                case 17: goto L_0x016e;
                case 18: goto L_0x0162;
                case 19: goto L_0x0162;
                case 20: goto L_0x0162;
                case 21: goto L_0x0162;
                case 22: goto L_0x0162;
                case 23: goto L_0x0162;
                case 24: goto L_0x0162;
                case 25: goto L_0x0162;
                case 26: goto L_0x0162;
                case 27: goto L_0x0162;
                case 28: goto L_0x0162;
                case 29: goto L_0x0162;
                case 30: goto L_0x0162;
                case 31: goto L_0x0162;
                case 32: goto L_0x0162;
                case 33: goto L_0x0162;
                case 34: goto L_0x0162;
                case 35: goto L_0x0162;
                case 36: goto L_0x0162;
                case 37: goto L_0x0162;
                case 38: goto L_0x0162;
                case 39: goto L_0x0162;
                case 40: goto L_0x0162;
                case 41: goto L_0x0162;
                case 42: goto L_0x0162;
                case 43: goto L_0x0162;
                case 44: goto L_0x0162;
                case 45: goto L_0x0162;
                case 46: goto L_0x0162;
                case 47: goto L_0x0162;
                case 48: goto L_0x0162;
                case 49: goto L_0x0162;
                case 50: goto L_0x0156;
                case 51: goto L_0x013c;
                case 52: goto L_0x0124;
                case 53: goto L_0x0114;
                case 54: goto L_0x0104;
                case 55: goto L_0x00f6;
                case 56: goto L_0x00e6;
                case 57: goto L_0x00d8;
                case 58: goto L_0x00c0;
                case 59: goto L_0x00ac;
                case 60: goto L_0x009b;
                case 61: goto L_0x008a;
                case 62: goto L_0x007d;
                case 63: goto L_0x0070;
                case 64: goto L_0x0063;
                case 65: goto L_0x0054;
                case 66: goto L_0x0047;
                case 67: goto L_0x0034;
                case 68: goto L_0x0021;
                default: goto L_0x001f;
            }
        L_0x001f:
            goto L_0x022a
        L_0x0021:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
        L_0x0031:
            int r2 = r2 + r3
            goto L_0x022a
        L_0x0034:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            long r3 = zzp(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
        L_0x0042:
            long r5 = r3 >>> r8
            long r3 = r3 ^ r5
            int r3 = (int) r3
            goto L_0x0031
        L_0x0047:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            int r3 = zzk(r10, r5)
            goto L_0x0031
        L_0x0054:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            long r3 = zzp(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0042
        L_0x0063:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            int r3 = zzk(r10, r5)
            goto L_0x0031
        L_0x0070:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            int r3 = zzk(r10, r5)
            goto L_0x0031
        L_0x007d:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            int r3 = zzk(r10, r5)
            goto L_0x0031
        L_0x008a:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0031
        L_0x009b:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0031
        L_0x00ac:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0031
        L_0x00c0:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            int r3 = com.google.android.gms.internal.auth.zzfa.zza(r3)
            goto L_0x0031
        L_0x00d8:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            int r3 = zzk(r10, r5)
            goto L_0x0031
        L_0x00e6:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            long r3 = zzp(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0042
        L_0x00f6:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            int r3 = zzk(r10, r5)
            goto L_0x0031
        L_0x0104:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            long r3 = zzp(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0042
        L_0x0114:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            long r3 = zzp(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0042
        L_0x0124:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0031
        L_0x013c:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            java.lang.Double r3 = (java.lang.Double) r3
            double r3 = r3.doubleValue()
            long r3 = java.lang.Double.doubleToLongBits(r3)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0042
        L_0x0156:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0031
        L_0x0162:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0031
        L_0x016e:
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            if (r3 == 0) goto L_0x0178
            int r7 = r3.hashCode()
        L_0x0178:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x022a
        L_0x017d:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.auth.zzhj.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0042
        L_0x0187:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.auth.zzhj.zzc(r10, r5)
            goto L_0x0031
        L_0x018f:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.auth.zzhj.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0042
        L_0x0199:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.auth.zzhj.zzc(r10, r5)
            goto L_0x0031
        L_0x01a1:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.auth.zzhj.zzc(r10, r5)
            goto L_0x0031
        L_0x01a9:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.auth.zzhj.zzc(r10, r5)
            goto L_0x0031
        L_0x01b1:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0031
        L_0x01bd:
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            if (r3 == 0) goto L_0x0178
            int r7 = r3.hashCode()
            goto L_0x0178
        L_0x01c8:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0031
        L_0x01d6:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.auth.zzhj.zzt(r10, r5)
            int r3 = com.google.android.gms.internal.auth.zzfa.zza(r3)
            goto L_0x0031
        L_0x01e2:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.auth.zzhj.zzc(r10, r5)
            goto L_0x0031
        L_0x01ea:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.auth.zzhj.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0042
        L_0x01f4:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.auth.zzhj.zzc(r10, r5)
            goto L_0x0031
        L_0x01fc:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.auth.zzhj.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0042
        L_0x0206:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.auth.zzhj.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0042
        L_0x0210:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.auth.zzhj.zzb(r10, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0031
        L_0x021c:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.auth.zzhj.zza(r10, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0042
        L_0x022a:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x022e:
            int r2 = r2 * 53
            com.google.android.gms.internal.auth.zzgz r0 = r9.zzl
            java.lang.Object r10 = r0.zzb(r10)
            int r10 = r10.hashCode()
            int r2 = r2 + r10
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzga.zza(java.lang.Object):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v37, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v39, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v40, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v41, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v34, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v47, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v36, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v48, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v37, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v39, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v51, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v40, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v41, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v37, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v52, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v44, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v39, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v53, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v31, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v54, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v40, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v42, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v43, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v44, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v41, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v55, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v45, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v56, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v42, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v47, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v48, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v39, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v57, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v43, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v42, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v43, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v44, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v48, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v36, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v51, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v44, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v49, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v58, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v50, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v38, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v51, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v39, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v53, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v45, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v53, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v54, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v55, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v57, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v40, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v54, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v62, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v63, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v65, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v67, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v41, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v55, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v47, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v70, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v71, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v72, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v74, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v76, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v48, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v42, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v56, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v72, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v49, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v43, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v57, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v77, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v73, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v44, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v58, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v50, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v80, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v82, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v83, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v84, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v87, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v89, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v91, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v92, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v93, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v68, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v95, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v96, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v45, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v52, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v100, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v82, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v46, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v75, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v47, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v48, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v104, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v49, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v107, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r44v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v50, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v109, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r44v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r44v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v112, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r44v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v31, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v59, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v67, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v36, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v122, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v123, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v124, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v126, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v128, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v61, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v34, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v83, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v53, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v35, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v62, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v36, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v37, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v63, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v37, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v39, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v129, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v64, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v41, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v38, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v130, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v131, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v132, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v134, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v136, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v39, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v65, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v137, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v40, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v84, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v54, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v41, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v66, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v138, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v67, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v43, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v85, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v55, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v45, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v141, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v142, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v144, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v74, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v41, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v87, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v47, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v81, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v48, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v44, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v49, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v80, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v83, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v78, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v49, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v51, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v148, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v52, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v50, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v84, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v53, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v91, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v51, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v54, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v154, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v52, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v55, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v65, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v57, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v94, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v54, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v87, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v55, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v58, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v158, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v36, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v56, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v57, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v60, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v68, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v62, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v59, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v91, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v97, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v35, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v60, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v64, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v164, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v65, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v165, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v92, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v36, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v61, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v62, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v67, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v166, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v63, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v68, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v37, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v64, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v37, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v95, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v167, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v69, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v102, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v70, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v65, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v38, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v71, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v97, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v171, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v66, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v98, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v38, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v39, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v72, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v172, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v174, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v73, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v181, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v40, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v74, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v183, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v42, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v81, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v85, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v87, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v47, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v88, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v101, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v90, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v78, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v48, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v40, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v111, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v79, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v105, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v104, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v49, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v41, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v80, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v92, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v107, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v81, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v93, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v107, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v82, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v95, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v50, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v83, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v42, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v110, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v96, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v115, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v51, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v84, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v43, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v204, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v97, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v111, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v110, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v206, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v52, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v44, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v85, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v53, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v45, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v118, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v86, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v99, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v113, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v113, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v88, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v101, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v54, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v119, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v102, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v115, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v89, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v90, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v45, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v103, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v55, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v91, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v47, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v117, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v104, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v120, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v117, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v48, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v92, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v106, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v50, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v83, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v108, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v119, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v49, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v124, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v94, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v125, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v95, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v109, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v121, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v122, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v52, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v51, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v97, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v112, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v34, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v126, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v123, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v54, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v84, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v85, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v55, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v87, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v89, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v124, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v125, resolved type: byte} */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x0868  */
    /* JADX WARNING: Removed duplicated region for block: B:324:0x08bd  */
    /* JADX WARNING: Removed duplicated region for block: B:635:0x088e A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:640:0x088e A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x02a1  */
    final int zzb(java.lang.Object r42, byte[] r43, int r44, int r45, int r46, com.google.android.gms.internal.auth.zzdt r47) throws java.io.IOException {
        /*
            r41 = this;
            r0 = r41
            r7 = r42
            r15 = r43
            r5 = r45
            r6 = r47
            r3 = 3
            r2 = 1
            zzw(r42)
            sun.misc.Unsafe r1 = zzb
            r14 = 0
            r13 = -1
            r12 = 1048575(0xfffff, float:1.469367E-39)
            r8 = r44
            r11 = r12
            r9 = r13
            r10 = r14
            r16 = r10
            r17 = r16
        L_0x001f:
            r18 = 0
            if (r8 >= r5) goto L_0x0e1b
            int r4 = r8 + 1
            byte r8 = r15[r8]
            if (r8 >= 0) goto L_0x002f
            int r4 = com.google.android.gms.internal.auth.zzdu.zzi(r8, r15, r4, r6)
            int r8 = r6.zza
        L_0x002f:
            r17 = r8
            int r8 = r17 >>> 3
            if (r8 <= r9) goto L_0x0046
            int r10 = r10 / r3
            int r9 = r0.zze
            if (r8 < r9) goto L_0x0043
            int r9 = r0.zzf
            if (r8 > r9) goto L_0x0043
            int r9 = r0.zzm(r8, r10)
            goto L_0x0044
        L_0x0043:
            r9 = r13
        L_0x0044:
            r10 = r9
            goto L_0x0054
        L_0x0046:
            int r9 = r0.zze
            if (r8 < r9) goto L_0x0053
            int r9 = r0.zzf
            if (r8 > r9) goto L_0x0053
            int r9 = r0.zzm(r8, r14)
            goto L_0x0044
        L_0x0053:
            r10 = r13
        L_0x0054:
            if (r10 != r13) goto L_0x0069
            r37 = r1
            r19 = r3
            r5 = r6
            r22 = r11
            r20 = r13
            r10 = r14
            r21 = r10
            r3 = r17
            r6 = r46
            r11 = r8
            goto L_0x0de0
        L_0x0069:
            r9 = r17 & 7
            int[] r13 = r0.zzc
            int r21 = r10 + 1
            r14 = r13[r21]
            int r3 = zzn(r14)
            r2 = r14 & r12
            r44 = r13
            long r12 = (long) r2
            r25 = 536870912(0x20000000, float:1.0842022E-19)
            r27 = 0
            java.lang.String r2 = ""
            r5 = 17
            if (r3 > r5) goto L_0x04f0
            r5 = 2
            int r19 = r10 + 2
            r19 = r44[r19]
            int r26 = r19 >>> 20
            r23 = 1
            int r26 = r23 << r26
            r24 = r2
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r19 & r5
            if (r2 == r11) goto L_0x00b1
            if (r11 == r5) goto L_0x00a3
            long r5 = (long) r11
            r11 = r16
            r1.putInt(r7, r5, r11)
            r5 = 1048575(0xfffff, float:1.469367E-39)
        L_0x00a3:
            if (r2 != r5) goto L_0x00a7
            r5 = 0
            goto L_0x00ac
        L_0x00a7:
            long r5 = (long) r2
            int r5 = r1.getInt(r7, r5)
        L_0x00ac:
            r16 = r2
            r19 = r5
            goto L_0x00b5
        L_0x00b1:
            r19 = r16
            r16 = r11
        L_0x00b5:
            switch(r3) {
                case 0: goto L_0x04c3;
                case 1: goto L_0x048a;
                case 2: goto L_0x044e;
                case 3: goto L_0x044e;
                case 4: goto L_0x0424;
                case 5: goto L_0x03e4;
                case 6: goto L_0x03cb;
                case 7: goto L_0x039c;
                case 8: goto L_0x0257;
                case 9: goto L_0x021b;
                case 10: goto L_0x01ef;
                case 11: goto L_0x0424;
                case 12: goto L_0x0186;
                case 13: goto L_0x03cb;
                case 14: goto L_0x03e4;
                case 15: goto L_0x014a;
                case 16: goto L_0x0100;
                default: goto L_0x00b8;
            }
        L_0x00b8:
            r3 = 3
            if (r9 != r3) goto L_0x00f1
            java.lang.Object r2 = r0.zzt(r7, r10)
            int r5 = r8 << 3
            r13 = r5 | 4
            com.google.android.gms.internal.auth.zzgi r9 = r0.zzr(r10)
            r5 = r8
            r8 = r2
            r6 = r10
            r10 = r43
            r11 = r4
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r12 = r45
            r20 = -1
            r14 = r47
            int r8 = com.google.android.gms.internal.auth.zzdu.zzl(r8, r9, r10, r11, r12, r13, r14)
            r0.zzB(r7, r6, r2)
            r2 = r19 | r26
            r12 = r4
            r9 = r5
            r10 = r6
            r11 = r16
            r13 = r20
            r14 = 0
            r5 = r45
            r6 = r47
            r16 = r2
            r2 = r23
            goto L_0x001f
        L_0x00f1:
            r20 = -1
            r11 = r1
            r14 = r4
            r4 = r8
            r5 = r17
            r6 = r23
            r21 = 0
            r8 = r47
            goto L_0x04e0
        L_0x0100:
            r5 = r8
            r6 = r10
            r3 = 3
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r20 = -1
            r8 = r47
            if (r9 != 0) goto L_0x013e
            int r9 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r4, r8)
            long r3 = r8.zzb
            long r24 = com.google.android.gms.internal.auth.zzej.zzc(r3)
            r11 = r1
            r14 = r23
            r2 = r42
            r10 = 2
            r3 = r12
            r13 = r5
            r10 = r6
            r12 = r17
            r5 = r24
            r1.putLong(r2, r3, r5)
            r1 = r19 | r26
            r5 = r45
            r6 = r8
            r8 = r9
            r9 = r13
            r2 = r14
            r13 = r20
            r3 = 3
            r12 = 1048575(0xfffff, float:1.469367E-39)
            r14 = 0
        L_0x0135:
            r40 = r16
            r16 = r1
            r1 = r11
            r11 = r40
            goto L_0x001f
        L_0x013e:
            r11 = r1
            r10 = r6
            r14 = r4
            r4 = r5
            r5 = r17
            r6 = r23
        L_0x0146:
            r21 = 0
            goto L_0x04e0
        L_0x014a:
            r11 = r1
            r5 = r8
            r6 = r17
            r14 = r23
            r20 = -1
            r8 = r47
            if (r9 != 0) goto L_0x017b
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r4, r8)
            int r2 = r8.zza
            int r2 = com.google.android.gms.internal.auth.zzej.zzb(r2)
            r11.putInt(r7, r12, r2)
            r2 = r19 | r26
            r9 = r5
            r17 = r6
            r6 = r8
            r13 = r20
            r3 = 3
            r12 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r45
            r8 = r1
            r1 = r11
            r11 = r16
            r16 = r2
            r2 = r14
            r14 = 0
            goto L_0x001f
        L_0x017b:
            r21 = 0
            r40 = r14
            r14 = r4
            r4 = r5
            r5 = r6
            r6 = r40
            goto L_0x04e0
        L_0x0186:
            r11 = r1
            r5 = r8
            r6 = r17
            r3 = r23
            r20 = -1
            r8 = r47
            if (r9 != 0) goto L_0x01e7
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r4, r8)
            int r2 = r8.zza
            com.google.android.gms.internal.auth.zzey r4 = r0.zzq(r10)
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r9 = r9 & r14
            if (r9 == 0) goto L_0x01cc
            if (r4 == 0) goto L_0x01cc
            boolean r4 = r4.zza()
            if (r4 == 0) goto L_0x01aa
            goto L_0x01cc
        L_0x01aa:
            com.google.android.gms.internal.auth.zzha r4 = zzc(r42)
            long r12 = (long) r2
            java.lang.Long r2 = java.lang.Long.valueOf(r12)
            r4.zzh(r6, r2)
            r2 = r3
            r9 = r5
            r17 = r6
            r6 = r8
            r13 = r20
            r3 = 3
            r12 = 1048575(0xfffff, float:1.469367E-39)
            r14 = 0
            r5 = r45
            r8 = r1
            r1 = r11
            r11 = r16
            r16 = r19
            goto L_0x001f
        L_0x01cc:
            r11.putInt(r7, r12, r2)
            r2 = r19 | r26
            r9 = r5
            r17 = r6
            r6 = r8
            r13 = r20
            r12 = 1048575(0xfffff, float:1.469367E-39)
            r14 = 0
        L_0x01db:
            r5 = r45
            r8 = r1
            r1 = r11
            r11 = r16
            r16 = r2
            r2 = r3
        L_0x01e4:
            r3 = 3
            goto L_0x001f
        L_0x01e7:
            r14 = r4
            r4 = r5
            r5 = r6
            r21 = 0
        L_0x01ec:
            r6 = r3
            goto L_0x04e0
        L_0x01ef:
            r11 = r1
            r5 = r8
            r6 = r17
            r3 = r23
            r1 = 2
            r20 = -1
            r8 = r47
            if (r9 != r1) goto L_0x01e7
            int r2 = com.google.android.gms.internal.auth.zzdu.zza(r15, r4, r8)
            java.lang.Object r4 = r8.zzc
            r11.putObject(r7, r12, r4)
            r4 = r19 | r26
            r9 = r5
            r17 = r6
            r6 = r8
            r1 = r11
            r11 = r16
            r13 = r20
            r12 = 1048575(0xfffff, float:1.469367E-39)
            r14 = 0
            r5 = r45
            r8 = r2
            r2 = r3
            r16 = r4
            goto L_0x01e4
        L_0x021b:
            r11 = r1
            r5 = r8
            r6 = r17
            r3 = r23
            r1 = 2
            r20 = -1
            r8 = r47
            if (r9 != r1) goto L_0x01e7
            java.lang.Object r9 = r0.zzt(r7, r10)
            com.google.android.gms.internal.auth.zzgi r2 = r0.zzr(r10)
            r1 = r9
            r12 = r3
            r3 = r43
            r13 = r5
            r5 = r45
            r14 = r6
            r6 = r47
            int r1 = com.google.android.gms.internal.auth.zzdu.zzm(r1, r2, r3, r4, r5, r6)
            r0.zzB(r7, r10, r9)
            r2 = r19 | r26
            r6 = r8
            r9 = r13
            r17 = r14
            r13 = r20
            r3 = 3
            r14 = 0
            r8 = r1
            r1 = r11
            r11 = r16
            r16 = r2
            r2 = r12
            r12 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x001f
        L_0x0257:
            r11 = r1
            r6 = r8
            r5 = r17
            r3 = r23
            r1 = 2
            r20 = -1
            r8 = r47
            if (r9 != r1) goto L_0x0397
            r1 = r14 & r25
            if (r1 == 0) goto L_0x035a
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r4, r8)
            int r2 = r8.zza
            if (r2 < 0) goto L_0x0355
            if (r2 != 0) goto L_0x027c
            r9 = r24
            r8.zzc = r9
            r17 = r5
            r3 = 0
            r5 = 3
            goto L_0x0377
        L_0x027c:
            int r4 = com.google.android.gms.internal.auth.zzhn.zza
            int r4 = r15.length
            int r9 = r4 - r1
            r14 = r1 | r2
            int r9 = r9 - r2
            r9 = r9 | r14
            if (r9 < 0) goto L_0x0339
            int r4 = r1 + r2
            char[] r2 = new char[r2]
            r14 = 0
        L_0x028c:
            if (r1 >= r4) goto L_0x029f
            byte r9 = r15[r1]
            boolean r17 = com.google.android.gms.internal.auth.zzhk.zzd(r9)
            if (r17 == 0) goto L_0x029f
            int r1 = r1 + r3
            int r17 = r14 + 1
            char r9 = (char) r9
            r2[r14] = r9
            r14 = r17
            goto L_0x028c
        L_0x029f:
            if (r1 >= r4) goto L_0x032c
            int r9 = r1 + 1
            byte r3 = r15[r1]
            boolean r17 = com.google.android.gms.internal.auth.zzhk.zzd(r3)
            if (r17 == 0) goto L_0x02cb
            r17 = 1
            int r1 = r14 + 1
            char r3 = (char) r3
            r2[r14] = r3
            r14 = r1
            r1 = r9
        L_0x02b4:
            if (r1 >= r4) goto L_0x02c9
            byte r3 = r15[r1]
            boolean r9 = com.google.android.gms.internal.auth.zzhk.zzd(r3)
            if (r9 == 0) goto L_0x02c9
            int r1 = r1 + 1
            int r9 = r14 + 1
            char r3 = (char) r3
            r2[r14] = r3
            r14 = r9
            r17 = 1
            goto L_0x02b4
        L_0x02c9:
            r3 = 1
            goto L_0x029f
        L_0x02cb:
            r17 = r5
            r5 = -32
            if (r3 >= r5) goto L_0x02e8
            if (r9 >= r4) goto L_0x02e3
            r5 = 2
            int r1 = r1 + r5
            byte r9 = r15[r9]
            r18 = 1
            int r21 = r14 + 1
            com.google.android.gms.internal.auth.zzhk.zzc(r3, r9, r2, r14)
        L_0x02de:
            r5 = r17
            r14 = r21
            goto L_0x02c9
        L_0x02e3:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzb()
            throw r1
        L_0x02e8:
            r5 = -16
            if (r3 >= r5) goto L_0x0306
            int r5 = r4 + -1
            if (r9 >= r5) goto L_0x0301
            r5 = 2
            int r18 = r1 + 2
            byte r5 = r15[r9]
            r9 = 3
            int r1 = r1 + r9
            byte r9 = r15[r18]
            r18 = 1
            int r21 = r14 + 1
            com.google.android.gms.internal.auth.zzhk.zzb(r3, r5, r9, r2, r14)
            goto L_0x02de
        L_0x0301:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzb()
            throw r1
        L_0x0306:
            r5 = 3
            int r5 = r4 + -2
            if (r9 >= r5) goto L_0x0327
            r5 = 2
            int r18 = r1 + 2
            byte r32 = r15[r9]
            r5 = 3
            int r9 = r1 + 3
            byte r33 = r15[r18]
            int r1 = r1 + 4
            byte r34 = r15[r9]
            r31 = r3
            r35 = r2
            r36 = r14
            com.google.android.gms.internal.auth.zzhk.zza(r31, r32, r33, r34, r35, r36)
            r3 = 2
            int r14 = r14 + r3
            r5 = r17
            goto L_0x02c9
        L_0x0327:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzb()
            throw r1
        L_0x032c:
            r17 = r5
            r5 = 3
            java.lang.String r1 = new java.lang.String
            r3 = 0
            r1.<init>(r2, r3, r14)
            r8.zzc = r1
            r1 = r4
            goto L_0x0377
        L_0x0339:
            java.lang.ArrayIndexOutOfBoundsException r3 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object[] r1 = new java.lang.Object[]{r4, r1, r2}
            java.lang.String r2 = "buffer length=%d, index=%d, size=%d"
            java.lang.String r1 = java.lang.String.format(r2, r1)
            r3.<init>(r1)
            throw r3
        L_0x0355:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r1
        L_0x035a:
            r17 = r5
            r9 = r24
            r3 = 0
            r5 = 3
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r4, r8)
            int r2 = r8.zza
            if (r2 < 0) goto L_0x0392
            if (r2 != 0) goto L_0x036d
            r8.zzc = r9
            goto L_0x0377
        L_0x036d:
            java.lang.String r4 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.auth.zzfa.zzb
            r4.<init>(r15, r1, r2, r9)
            r8.zzc = r4
            int r1 = r1 + r2
        L_0x0377:
            java.lang.Object r2 = r8.zzc
            r11.putObject(r7, r12, r2)
            r2 = r19 | r26
            r14 = r3
            r3 = r5
            r9 = r6
            r6 = r8
            r13 = r20
            r12 = 1048575(0xfffff, float:1.469367E-39)
        L_0x0387:
            r5 = r45
            r8 = r1
            r1 = r11
            r11 = r16
            r16 = r2
            r2 = 1
            goto L_0x001f
        L_0x0392:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r1
        L_0x0397:
            r14 = r4
            r4 = r6
        L_0x0399:
            r6 = 1
            goto L_0x0146
        L_0x039c:
            r11 = r1
            r6 = r8
            r3 = 0
            r5 = 3
            r20 = -1
            r8 = r47
            if (r9 != 0) goto L_0x03c2
            int r1 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r4, r8)
            long r3 = r8.zzb
            int r2 = (r3 > r27 ? 1 : (r3 == r27 ? 0 : -1))
            if (r2 == 0) goto L_0x03b2
            r2 = 1
            goto L_0x03b3
        L_0x03b2:
            r2 = 0
        L_0x03b3:
            com.google.android.gms.internal.auth.zzhj.zzk(r7, r12, r2)
        L_0x03b6:
            r2 = r19 | r26
            r3 = r5
            r9 = r6
            r6 = r8
            r13 = r20
            r12 = 1048575(0xfffff, float:1.469367E-39)
            r14 = 0
            goto L_0x0387
        L_0x03c2:
            r21 = r3
            r14 = r4
            r4 = r6
            r5 = r17
            r6 = 1
            goto L_0x04e0
        L_0x03cb:
            r11 = r1
            r6 = r8
            r1 = 5
            r5 = 3
            r20 = -1
            r8 = r47
            if (r9 != r1) goto L_0x03df
            int r1 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r4)
            r11.putInt(r7, r12, r1)
            int r1 = r4 + 4
            goto L_0x03b6
        L_0x03df:
            r14 = r4
            r4 = r6
            r5 = r17
            goto L_0x0399
        L_0x03e4:
            r11 = r1
            r6 = r8
            r3 = r23
            r5 = 3
            r20 = -1
            r8 = r47
            if (r9 != r3) goto L_0x041c
            long r23 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r4)
            r1 = r11
            r2 = r42
            r9 = r3
            r14 = r4
            r21 = 0
            r3 = r12
            r13 = r6
            r12 = r17
            r5 = r23
            r1.putLong(r2, r3, r5)
            int r1 = r14 + 8
            r2 = r19 | r26
            r5 = r45
            r6 = r8
            r14 = r21
            r3 = 3
            r12 = 1048575(0xfffff, float:1.469367E-39)
            r8 = r1
            r1 = r11
            r11 = r16
            r16 = r2
            r2 = r9
            r9 = r13
            r13 = r20
            goto L_0x001f
        L_0x041c:
            r14 = r4
            r21 = 0
            r4 = r6
            r5 = r17
            goto L_0x01ec
        L_0x0424:
            r11 = r1
            r14 = r4
            r6 = r8
            r5 = r17
            r3 = r23
            r20 = -1
            r21 = 0
            r8 = r47
            if (r9 != 0) goto L_0x044b
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r14, r8)
            int r2 = r8.zza
            r11.putInt(r7, r12, r2)
            r2 = r19 | r26
            r17 = r5
            r9 = r6
            r6 = r8
            r13 = r20
            r14 = r21
            r12 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x01db
        L_0x044b:
            r4 = r6
            goto L_0x01ec
        L_0x044e:
            r11 = r1
            r14 = r4
            r6 = r8
            r5 = r17
            r3 = r23
            r20 = -1
            r21 = 0
            r8 = r47
            if (r9 != 0) goto L_0x0485
            int r9 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r14, r8)
            long r1 = r8.zzb
            r17 = r1
            r1 = r11
            r2 = r42
            r14 = r3
            r3 = r12
            r12 = r5
            r13 = r6
            r5 = r17
            r1.putLong(r2, r3, r5)
            r1 = r19 | r26
            r5 = r45
            r6 = r8
            r8 = r9
            r17 = r12
            r9 = r13
            r2 = r14
            r13 = r20
            r14 = r21
            r3 = 3
            r12 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0135
        L_0x0485:
            r13 = r6
            r6 = r3
            r4 = r13
            goto L_0x04e0
        L_0x048a:
            r11 = r1
            r14 = r4
            r4 = r8
            r5 = r17
            r6 = r23
            r1 = 5
            r20 = -1
            r21 = 0
            r8 = r47
            if (r9 != r1) goto L_0x04e0
            int r1 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r14)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            com.google.android.gms.internal.auth.zzhj.zzm(r7, r12, r1)
            int r1 = r14 + 4
        L_0x04a7:
            r2 = r19 | r26
            r9 = r4
            r17 = r5
            r13 = r20
            r14 = r21
            r3 = 3
            r12 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r45
            r40 = r8
            r8 = r1
            r1 = r11
            r11 = r16
            r16 = r2
            r2 = r6
            r6 = r40
            goto L_0x001f
        L_0x04c3:
            r11 = r1
            r14 = r4
            r4 = r8
            r5 = r17
            r6 = r23
            r20 = -1
            r21 = 0
            r8 = r47
            if (r9 != r6) goto L_0x04e0
            long r1 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r14)
            double r1 = java.lang.Double.longBitsToDouble(r1)
            com.google.android.gms.internal.auth.zzhj.zzl(r7, r12, r1)
            int r1 = r14 + 8
            goto L_0x04a7
        L_0x04e0:
            r6 = r46
            r3 = r5
            r5 = r8
            r37 = r11
            r22 = r16
            r16 = r19
            r19 = 3
            r11 = r4
            r4 = r14
            goto L_0x0de0
        L_0x04f0:
            r5 = r17
            r20 = -1
            r21 = 0
            r17 = r8
            r8 = r6
            r6 = r2
            r2 = r1
            r1 = r4
            r4 = 27
            r19 = 10
            if (r3 != r4) goto L_0x0565
            r4 = 2
            if (r9 != r4) goto L_0x0552
            java.lang.Object r3 = r2.getObject(r7, r12)
            com.google.android.gms.internal.auth.zzez r3 = (com.google.android.gms.internal.auth.zzez) r3
            boolean r6 = r3.zzc()
            if (r6 != 0) goto L_0x0524
            int r6 = r3.size()
            if (r6 != 0) goto L_0x051a
        L_0x0517:
            r6 = r19
            goto L_0x051d
        L_0x051a:
            int r19 = r6 + r6
            goto L_0x0517
        L_0x051d:
            com.google.android.gms.internal.auth.zzez r3 = r3.zzd(r6)
            r2.putObject(r7, r12, r3)
        L_0x0524:
            r13 = r3
            com.google.android.gms.internal.auth.zzgi r3 = r0.zzr(r10)
            r8 = r3
            r9 = r5
            r6 = r4
            r3 = r10
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r10 = r43
            r22 = r11
            r11 = r1
            r12 = r45
            r14 = r47
            int r8 = com.google.android.gms.internal.auth.zzdu.zze(r8, r9, r10, r11, r12, r13, r14)
            r6 = r47
            r1 = r2
            r10 = r3
            r12 = r4
            r9 = r17
            r13 = r20
            r14 = r21
            r11 = r22
            r2 = 1
            r3 = 3
            r17 = r5
            r5 = r45
            goto L_0x001f
        L_0x0552:
            r6 = r4
            r22 = r11
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r11 = r1
            r37 = r2
            r2 = r6
            r4 = r10
            r39 = r17
            r10 = 1
            r6 = r5
            r5 = r47
            goto L_0x0b8c
        L_0x0565:
            r22 = r11
            r11 = r45
            r40 = r10
            r10 = r8
            r8 = r40
            r4 = 49
            if (r3 > r4) goto L_0x0b52
            r26 = r1
            r4 = r2
            long r1 = (long) r14
            sun.misc.Unsafe r14 = zzb
            java.lang.Object r25 = r14.getObject(r7, r12)
            r29 = r4
            r4 = r25
            com.google.android.gms.internal.auth.zzez r4 = (com.google.android.gms.internal.auth.zzez) r4
            boolean r25 = r4.zzc()
            if (r25 != 0) goto L_0x059f
            int r25 = r4.size()
            if (r25 != 0) goto L_0x0593
        L_0x058e:
            r30 = r6
            r6 = r19
            goto L_0x0596
        L_0x0593:
            int r19 = r25 + r25
            goto L_0x058e
        L_0x0596:
            com.google.android.gms.internal.auth.zzez r4 = r4.zzd(r6)
            r14.putObject(r7, r12, r4)
        L_0x059d:
            r13 = r4
            goto L_0x05a2
        L_0x059f:
            r30 = r6
            goto L_0x059d
        L_0x05a2:
            switch(r3) {
                case 18: goto L_0x0ad2;
                case 19: goto L_0x0a76;
                case 20: goto L_0x0a29;
                case 21: goto L_0x0a29;
                case 22: goto L_0x09ff;
                case 23: goto L_0x09b2;
                case 24: goto L_0x0964;
                case 25: goto L_0x08ff;
                case 26: goto L_0x0833;
                case 27: goto L_0x07fe;
                case 28: goto L_0x0784;
                case 29: goto L_0x09ff;
                case 30: goto L_0x06bb;
                case 31: goto L_0x0964;
                case 32: goto L_0x09b2;
                case 33: goto L_0x0669;
                case 34: goto L_0x0604;
                case 35: goto L_0x0ad2;
                case 36: goto L_0x0a76;
                case 37: goto L_0x0a29;
                case 38: goto L_0x0a29;
                case 39: goto L_0x09ff;
                case 40: goto L_0x09b2;
                case 41: goto L_0x0964;
                case 42: goto L_0x08ff;
                case 43: goto L_0x09ff;
                case 44: goto L_0x06bb;
                case 45: goto L_0x0964;
                case 46: goto L_0x09b2;
                case 47: goto L_0x0669;
                case 48: goto L_0x0604;
                default: goto L_0x05a5;
            }
        L_0x05a5:
            r14 = 3
            if (r9 != r14) goto L_0x05f6
            com.google.android.gms.internal.auth.zzgi r9 = r0.zzr(r8)
            r1 = r5 & -8
            r12 = r1 | 4
            r6 = r26
            r1 = r9
            r4 = r29
            r2 = r43
            r3 = r6
            r37 = r4
            r14 = r17
            r4 = r45
            r7 = r5
            r5 = r12
            r14 = r6
            r6 = r47
            int r1 = com.google.android.gms.internal.auth.zzdu.zzc(r1, r2, r3, r4, r5, r6)
            java.lang.Object r2 = r10.zzc
            r13.add(r2)
        L_0x05cc:
            if (r1 >= r11) goto L_0x05e8
            int r3 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r10)
            int r2 = r10.zza
            if (r7 != r2) goto L_0x05e8
            r1 = r9
            r2 = r43
            r4 = r45
            r5 = r12
            r6 = r47
            int r1 = com.google.android.gms.internal.auth.zzdu.zzc(r1, r2, r3, r4, r5, r6)
            java.lang.Object r2 = r10.zzc
            r13.add(r2)
            goto L_0x05cc
        L_0x05e8:
            r6 = r7
            r12 = r10
            r39 = r17
            r7 = 2
        L_0x05ed:
            r10 = 1
        L_0x05ee:
            r40 = r14
            r14 = r8
            r8 = r11
            r11 = r40
            goto L_0x0b2b
        L_0x05f6:
            r37 = r29
            r6 = r5
            r14 = r8
            r12 = r10
            r8 = r11
            r39 = r17
            r11 = r26
            r7 = 2
            r10 = 1
            goto L_0x0b2a
        L_0x0604:
            r7 = r5
            r14 = r26
            r37 = r29
            r12 = 2
            if (r9 != r12) goto L_0x0632
            com.google.android.gms.internal.auth.zzfm r13 = (com.google.android.gms.internal.auth.zzfm) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r14, r10)
            int r2 = r10.zza
            int r2 = r2 + r1
        L_0x0615:
            if (r1 >= r2) goto L_0x0625
            int r1 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r1, r10)
            long r3 = r10.zzb
            long r3 = com.google.android.gms.internal.auth.zzej.zzc(r3)
            r13.zze(r3)
            goto L_0x0615
        L_0x0625:
            if (r1 != r2) goto L_0x062d
        L_0x0627:
            r6 = r7
            r7 = r12
            r39 = r17
            r12 = r10
            goto L_0x05ed
        L_0x062d:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x0632:
            if (r9 != 0) goto L_0x065b
            com.google.android.gms.internal.auth.zzfm r13 = (com.google.android.gms.internal.auth.zzfm) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r14, r10)
            long r2 = r10.zzb
            long r2 = com.google.android.gms.internal.auth.zzej.zzc(r2)
            r13.zze(r2)
        L_0x0643:
            if (r1 >= r11) goto L_0x0627
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r10)
            int r3 = r10.zza
            if (r7 != r3) goto L_0x0627
            int r1 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r2, r10)
            long r2 = r10.zzb
            long r2 = com.google.android.gms.internal.auth.zzej.zzc(r2)
            r13.zze(r2)
            goto L_0x0643
        L_0x065b:
            r6 = r7
            r7 = r12
            r39 = r17
        L_0x065f:
            r12 = r10
            r10 = 1
        L_0x0661:
            r40 = r14
            r14 = r8
            r8 = r11
            r11 = r40
            goto L_0x0b2a
        L_0x0669:
            r7 = r5
            r14 = r26
            r37 = r29
            r12 = 2
            if (r9 != r12) goto L_0x0692
            com.google.android.gms.internal.auth.zzew r13 = (com.google.android.gms.internal.auth.zzew) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r14, r10)
            int r2 = r10.zza
            int r2 = r2 + r1
        L_0x067a:
            if (r1 >= r2) goto L_0x068a
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r10)
            int r3 = r10.zza
            int r3 = com.google.android.gms.internal.auth.zzej.zzb(r3)
            r13.zze(r3)
            goto L_0x067a
        L_0x068a:
            if (r1 != r2) goto L_0x068d
            goto L_0x0627
        L_0x068d:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x0692:
            if (r9 != 0) goto L_0x065b
            com.google.android.gms.internal.auth.zzew r13 = (com.google.android.gms.internal.auth.zzew) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r14, r10)
            int r2 = r10.zza
            int r2 = com.google.android.gms.internal.auth.zzej.zzb(r2)
            r13.zze(r2)
        L_0x06a3:
            if (r1 >= r11) goto L_0x0627
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r10)
            int r3 = r10.zza
            if (r7 != r3) goto L_0x0627
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r2, r10)
            int r2 = r10.zza
            int r2 = com.google.android.gms.internal.auth.zzej.zzb(r2)
            r13.zze(r2)
            goto L_0x06a3
        L_0x06bb:
            r7 = r5
            r14 = r26
            r37 = r29
            r12 = 2
            if (r9 != r12) goto L_0x06c8
            int r1 = com.google.android.gms.internal.auth.zzdu.zzf(r15, r14, r13, r10)
            goto L_0x06d7
        L_0x06c8:
            if (r9 != 0) goto L_0x0779
            r1 = r7
            r2 = r43
            r3 = r14
            r4 = r45
            r5 = r13
            r6 = r47
            int r1 = com.google.android.gms.internal.auth.zzdu.zzj(r1, r2, r3, r4, r5, r6)
        L_0x06d7:
            com.google.android.gms.internal.auth.zzey r2 = r0.zzq(r8)
            com.google.android.gms.internal.auth.zzgz r3 = r0.zzl
            int r4 = com.google.android.gms.internal.auth.zzgk.zza
            if (r2 == 0) goto L_0x0765
            if (r13 == 0) goto L_0x0738
            int r4 = r13.size()
            r9 = r18
            r5 = r21
            r6 = r5
        L_0x06ec:
            if (r5 >= r4) goto L_0x0725
            java.lang.Object r19 = r13.get(r5)
            r12 = r19
            java.lang.Integer r12 = (java.lang.Integer) r12
            r44 = r1
            int r1 = r12.intValue()
            boolean r19 = r2.zza()
            if (r19 == 0) goto L_0x0711
            if (r5 == r6) goto L_0x0707
            r13.set(r6, r12)
        L_0x0707:
            r12 = 1
            int r6 = r6 + r12
            r38 = r7
            r1 = r12
            r12 = r17
            r7 = r42
            goto L_0x071c
        L_0x0711:
            r38 = r7
            r12 = r17
            r7 = r42
            java.lang.Object r9 = com.google.android.gms.internal.auth.zzgk.zzc(r7, r12, r1, r9, r3)
            r1 = 1
        L_0x071c:
            int r5 = r5 + r1
            r1 = r44
            r17 = r12
            r7 = r38
            r12 = 2
            goto L_0x06ec
        L_0x0725:
            r44 = r1
            r38 = r7
            r12 = r17
            r1 = 1
            r7 = r42
            if (r6 == r4) goto L_0x076e
            java.util.List r2 = r13.subList(r6, r4)
            r2.clear()
            goto L_0x076e
        L_0x0738:
            r44 = r1
            r38 = r7
            r12 = r17
            r1 = 1
            r7 = r42
            java.util.Iterator r4 = r13.iterator()
            r5 = r18
        L_0x0747:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x076e
            java.lang.Object r6 = r4.next()
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            boolean r9 = r2.zza()
            if (r9 != 0) goto L_0x0747
            java.lang.Object r5 = com.google.android.gms.internal.auth.zzgk.zzc(r7, r12, r6, r5, r3)
            r4.remove()
            goto L_0x0747
        L_0x0765:
            r44 = r1
            r38 = r7
            r12 = r17
            r1 = 1
            r7 = r42
        L_0x076e:
            r39 = r12
            r6 = r38
            r7 = 2
            r12 = r10
            r10 = r1
            r1 = r44
            goto L_0x05ee
        L_0x0779:
            r38 = r7
            r7 = r42
            r7 = r12
            r39 = r17
            r6 = r38
            goto L_0x065f
        L_0x0784:
            r38 = r5
            r12 = r17
            r14 = r26
            r37 = r29
            r1 = 1
            r2 = 2
            if (r9 != r2) goto L_0x07f5
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r14, r10)
            int r3 = r10.zza
            if (r3 < 0) goto L_0x07f0
            int r4 = r15.length
            int r4 = r4 - r2
            if (r3 > r4) goto L_0x07eb
            if (r3 != 0) goto L_0x07a4
            com.google.android.gms.internal.auth.zzef r3 = com.google.android.gms.internal.auth.zzef.zzb
            r13.add(r3)
            goto L_0x07ac
        L_0x07a4:
            com.google.android.gms.internal.auth.zzef r4 = com.google.android.gms.internal.auth.zzef.zzk(r15, r2, r3)
            r13.add(r4)
            int r2 = r2 + r3
        L_0x07ac:
            if (r2 >= r11) goto L_0x07e1
            int r3 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r2, r10)
            int r4 = r10.zza
            r6 = r38
            if (r6 != r4) goto L_0x07e3
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r3, r10)
            int r3 = r10.zza
            if (r3 < 0) goto L_0x07dc
            int r4 = r15.length
            int r4 = r4 - r2
            if (r3 > r4) goto L_0x07d7
            if (r3 != 0) goto L_0x07ce
            com.google.android.gms.internal.auth.zzef r3 = com.google.android.gms.internal.auth.zzef.zzb
            r13.add(r3)
        L_0x07cb:
            r38 = r6
            goto L_0x07ac
        L_0x07ce:
            com.google.android.gms.internal.auth.zzef r4 = com.google.android.gms.internal.auth.zzef.zzk(r15, r2, r3)
            r13.add(r4)
            int r2 = r2 + r3
            goto L_0x07cb
        L_0x07d7:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x07dc:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r1
        L_0x07e1:
            r6 = r38
        L_0x07e3:
            r39 = r12
            r7 = 2
            r12 = r10
            r10 = r1
            r1 = r2
            goto L_0x05ee
        L_0x07eb:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x07f0:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r1
        L_0x07f5:
            r7 = r2
            r39 = r12
            r6 = r38
            r12 = r10
            r10 = r1
            goto L_0x0661
        L_0x07fe:
            r6 = r5
            r12 = r17
            r14 = r26
            r37 = r29
            r1 = 1
            r2 = 2
            if (r9 != r2) goto L_0x082b
            com.google.android.gms.internal.auth.zzgi r3 = r0.zzr(r8)
            r5 = r8
            r8 = r3
            r9 = r6
            r4 = r10
            r10 = r43
            r3 = r11
            r11 = r14
            r7 = r2
            r2 = r1
            r1 = r12
            r12 = r45
            r39 = r1
            r1 = r14
            r14 = r47
            int r8 = com.google.android.gms.internal.auth.zzdu.zze(r8, r9, r10, r11, r12, r13, r14)
            r11 = r1
            r10 = r2
            r12 = r4
            r14 = r5
            r1 = r8
            r8 = r3
            goto L_0x0b2b
        L_0x082b:
            r7 = r2
            r39 = r12
            r2 = r1
            r12 = r10
            r10 = r2
            goto L_0x0661
        L_0x0833:
            r6 = r5
            r5 = r8
            r4 = r10
            r3 = r11
            r39 = r17
            r11 = r26
            r37 = r29
            r7 = 2
            r10 = 1
            if (r9 != r7) goto L_0x08fa
            r8 = 536870912(0x20000000, double:2.652494739E-315)
            long r1 = r1 & r8
            int r1 = (r1 > r27 ? 1 : (r1 == r27 ? 0 : -1))
            if (r1 != 0) goto L_0x0898
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r11, r4)
            int r2 = r4.zza
            if (r2 < 0) goto L_0x0893
            if (r2 != 0) goto L_0x0859
            r8 = r30
            r13.add(r8)
            goto L_0x0866
        L_0x0859:
            r8 = r30
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r12 = com.google.android.gms.internal.auth.zzfa.zzb
            r9.<init>(r15, r1, r2, r12)
            r13.add(r9)
        L_0x0865:
            int r1 = r1 + r2
        L_0x0866:
            if (r1 >= r3) goto L_0x088e
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r4)
            int r9 = r4.zza
            if (r6 != r9) goto L_0x088e
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r2, r4)
            int r2 = r4.zza
            if (r2 < 0) goto L_0x0889
            if (r2 != 0) goto L_0x087e
            r13.add(r8)
            goto L_0x0866
        L_0x087e:
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r12 = com.google.android.gms.internal.auth.zzfa.zzb
            r9.<init>(r15, r1, r2, r12)
            r13.add(r9)
            goto L_0x0865
        L_0x0889:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r1
        L_0x088e:
            r8 = r3
            r12 = r4
            r14 = r5
            goto L_0x0b2b
        L_0x0893:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r1
        L_0x0898:
            r8 = r30
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r11, r4)
            int r2 = r4.zza
            if (r2 < 0) goto L_0x08f5
            if (r2 != 0) goto L_0x08a8
            r13.add(r8)
            goto L_0x08bb
        L_0x08a8:
            int r9 = r1 + r2
            boolean r12 = com.google.android.gms.internal.auth.zzhn.zzc(r15, r1, r9)
            if (r12 == 0) goto L_0x08f0
            java.lang.String r12 = new java.lang.String
            java.nio.charset.Charset r14 = com.google.android.gms.internal.auth.zzfa.zzb
            r12.<init>(r15, r1, r2, r14)
            r13.add(r12)
        L_0x08ba:
            r1 = r9
        L_0x08bb:
            if (r1 >= r3) goto L_0x088e
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r4)
            int r9 = r4.zza
            if (r6 != r9) goto L_0x088e
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r2, r4)
            int r2 = r4.zza
            if (r2 < 0) goto L_0x08eb
            if (r2 != 0) goto L_0x08d3
            r13.add(r8)
            goto L_0x08bb
        L_0x08d3:
            int r9 = r1 + r2
            boolean r12 = com.google.android.gms.internal.auth.zzhn.zzc(r15, r1, r9)
            if (r12 == 0) goto L_0x08e6
            java.lang.String r12 = new java.lang.String
            java.nio.charset.Charset r14 = com.google.android.gms.internal.auth.zzfa.zzb
            r12.<init>(r15, r1, r2, r14)
            r13.add(r12)
            goto L_0x08ba
        L_0x08e6:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzb()
            throw r1
        L_0x08eb:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r1
        L_0x08f0:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzb()
            throw r1
        L_0x08f5:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r1
        L_0x08fa:
            r8 = r3
            r12 = r4
            r14 = r5
            goto L_0x0b2a
        L_0x08ff:
            r6 = r5
            r5 = r8
            r4 = r10
            r3 = r11
            r39 = r17
            r11 = r26
            r37 = r29
            r7 = 2
            r10 = 1
            if (r9 != r7) goto L_0x0933
            com.google.android.gms.internal.auth.zzdv r13 = (com.google.android.gms.internal.auth.zzdv) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r11, r4)
            int r2 = r4.zza
            int r2 = r2 + r1
        L_0x0916:
            if (r1 >= r2) goto L_0x092a
            int r1 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r1, r4)
            long r8 = r4.zzb
            int r8 = (r8 > r27 ? 1 : (r8 == r27 ? 0 : -1))
            if (r8 == 0) goto L_0x0924
            r8 = r10
            goto L_0x0926
        L_0x0924:
            r8 = r21
        L_0x0926:
            r13.zze(r8)
            goto L_0x0916
        L_0x092a:
            if (r1 != r2) goto L_0x092e
            goto L_0x088e
        L_0x092e:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x0933:
            if (r9 != 0) goto L_0x08fa
            com.google.android.gms.internal.auth.zzdv r13 = (com.google.android.gms.internal.auth.zzdv) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r11, r4)
            long r8 = r4.zzb
            int r2 = (r8 > r27 ? 1 : (r8 == r27 ? 0 : -1))
            if (r2 == 0) goto L_0x0943
            r2 = r10
            goto L_0x0945
        L_0x0943:
            r2 = r21
        L_0x0945:
            r13.zze(r2)
        L_0x0948:
            if (r1 >= r3) goto L_0x088e
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r4)
            int r8 = r4.zza
            if (r6 != r8) goto L_0x088e
            int r1 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r2, r4)
            long r8 = r4.zzb
            int r2 = (r8 > r27 ? 1 : (r8 == r27 ? 0 : -1))
            if (r2 == 0) goto L_0x095e
            r2 = r10
            goto L_0x0960
        L_0x095e:
            r2 = r21
        L_0x0960:
            r13.zze(r2)
            goto L_0x0948
        L_0x0964:
            r6 = r5
            r5 = r8
            r4 = r10
            r3 = r11
            r39 = r17
            r11 = r26
            r37 = r29
            r7 = 2
            r10 = 1
            if (r9 != r7) goto L_0x0990
            com.google.android.gms.internal.auth.zzew r13 = (com.google.android.gms.internal.auth.zzew) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r11, r4)
            int r2 = r4.zza
            int r2 = r2 + r1
        L_0x097b:
            if (r1 >= r2) goto L_0x0987
            int r8 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r1)
            r13.zze(r8)
            int r1 = r1 + 4
            goto L_0x097b
        L_0x0987:
            if (r1 != r2) goto L_0x098b
            goto L_0x088e
        L_0x098b:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x0990:
            r1 = 5
            if (r9 != r1) goto L_0x08fa
            com.google.android.gms.internal.auth.zzew r13 = (com.google.android.gms.internal.auth.zzew) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r11)
            r13.zze(r1)
            int r1 = r11 + 4
        L_0x099e:
            if (r1 >= r3) goto L_0x088e
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r4)
            int r8 = r4.zza
            if (r6 != r8) goto L_0x088e
            int r1 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r2)
            r13.zze(r1)
            int r1 = r2 + 4
            goto L_0x099e
        L_0x09b2:
            r6 = r5
            r5 = r8
            r4 = r10
            r3 = r11
            r39 = r17
            r11 = r26
            r37 = r29
            r7 = 2
            r10 = 1
            if (r9 != r7) goto L_0x09de
            com.google.android.gms.internal.auth.zzfm r13 = (com.google.android.gms.internal.auth.zzfm) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r11, r4)
            int r2 = r4.zza
            int r2 = r2 + r1
        L_0x09c9:
            if (r1 >= r2) goto L_0x09d5
            long r8 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r1)
            r13.zze(r8)
            int r1 = r1 + 8
            goto L_0x09c9
        L_0x09d5:
            if (r1 != r2) goto L_0x09d9
            goto L_0x088e
        L_0x09d9:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x09de:
            if (r9 != r10) goto L_0x08fa
            com.google.android.gms.internal.auth.zzfm r13 = (com.google.android.gms.internal.auth.zzfm) r13
            long r1 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r11)
            r13.zze(r1)
            int r1 = r11 + 8
        L_0x09eb:
            if (r1 >= r3) goto L_0x088e
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r4)
            int r8 = r4.zza
            if (r6 != r8) goto L_0x088e
            long r8 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r2)
            r13.zze(r8)
            int r1 = r2 + 8
            goto L_0x09eb
        L_0x09ff:
            r6 = r5
            r5 = r8
            r4 = r10
            r3 = r11
            r39 = r17
            r11 = r26
            r37 = r29
            r7 = 2
            r10 = 1
            if (r9 != r7) goto L_0x0a13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzf(r15, r11, r13, r4)
            goto L_0x088e
        L_0x0a13:
            if (r9 != 0) goto L_0x08fa
            r1 = r6
            r2 = r43
            r8 = r3
            r3 = r11
            r12 = r4
            r4 = r45
            r14 = r5
            r5 = r13
            r9 = r6
            r6 = r47
            int r1 = com.google.android.gms.internal.auth.zzdu.zzj(r1, r2, r3, r4, r5, r6)
            r6 = r9
            goto L_0x0b2b
        L_0x0a29:
            r6 = r5
            r14 = r8
            r12 = r10
            r8 = r11
            r39 = r17
            r11 = r26
            r37 = r29
            r7 = 2
            r10 = 1
            if (r9 != r7) goto L_0x0a55
            com.google.android.gms.internal.auth.zzfm r13 = (com.google.android.gms.internal.auth.zzfm) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r11, r12)
            int r2 = r12.zza
            int r2 = r2 + r1
        L_0x0a40:
            if (r1 >= r2) goto L_0x0a4c
            int r1 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r1, r12)
            long r3 = r12.zzb
            r13.zze(r3)
            goto L_0x0a40
        L_0x0a4c:
            if (r1 != r2) goto L_0x0a50
            goto L_0x0b2b
        L_0x0a50:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x0a55:
            if (r9 != 0) goto L_0x0b2a
            com.google.android.gms.internal.auth.zzfm r13 = (com.google.android.gms.internal.auth.zzfm) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r11, r12)
            long r2 = r12.zzb
            r13.zze(r2)
        L_0x0a62:
            if (r1 >= r8) goto L_0x0b2b
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r3 = r12.zza
            if (r6 != r3) goto L_0x0b2b
            int r1 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r2, r12)
            long r2 = r12.zzb
            r13.zze(r2)
            goto L_0x0a62
        L_0x0a76:
            r6 = r5
            r14 = r8
            r12 = r10
            r8 = r11
            r39 = r17
            r11 = r26
            r37 = r29
            r7 = 2
            r10 = 1
            if (r9 != r7) goto L_0x0aa6
            com.google.android.gms.internal.auth.zzer r13 = (com.google.android.gms.internal.auth.zzer) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r11, r12)
            int r2 = r12.zza
            int r2 = r2 + r1
        L_0x0a8d:
            if (r1 >= r2) goto L_0x0a9d
            int r3 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r1)
            float r3 = java.lang.Float.intBitsToFloat(r3)
            r13.zze(r3)
            int r1 = r1 + 4
            goto L_0x0a8d
        L_0x0a9d:
            if (r1 != r2) goto L_0x0aa1
            goto L_0x0b2b
        L_0x0aa1:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x0aa6:
            r1 = 5
            if (r9 != r1) goto L_0x0b2a
            com.google.android.gms.internal.auth.zzer r13 = (com.google.android.gms.internal.auth.zzer) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r11)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            r13.zze(r1)
            int r4 = r11 + 4
        L_0x0ab8:
            if (r4 >= r8) goto L_0x0ad0
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r4, r12)
            int r2 = r12.zza
            if (r6 != r2) goto L_0x0ad0
            int r2 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r1)
            float r2 = java.lang.Float.intBitsToFloat(r2)
            r13.zze(r2)
            int r4 = r1 + 4
            goto L_0x0ab8
        L_0x0ad0:
            r1 = r4
            goto L_0x0b2b
        L_0x0ad2:
            r6 = r5
            r14 = r8
            r12 = r10
            r8 = r11
            r39 = r17
            r11 = r26
            r37 = r29
            r7 = 2
            r10 = 1
            if (r9 != r7) goto L_0x0b01
            com.google.android.gms.internal.auth.zzek r13 = (com.google.android.gms.internal.auth.zzek) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r11, r12)
            int r2 = r12.zza
            int r2 = r2 + r1
        L_0x0ae9:
            if (r1 >= r2) goto L_0x0af9
            long r3 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r1)
            double r3 = java.lang.Double.longBitsToDouble(r3)
            r13.zze(r3)
            int r1 = r1 + 8
            goto L_0x0ae9
        L_0x0af9:
            if (r1 != r2) goto L_0x0afc
            goto L_0x0b2b
        L_0x0afc:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x0b01:
            if (r9 != r10) goto L_0x0b2a
            com.google.android.gms.internal.auth.zzek r13 = (com.google.android.gms.internal.auth.zzek) r13
            long r1 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r11)
            double r1 = java.lang.Double.longBitsToDouble(r1)
            r13.zze(r1)
            int r4 = r11 + 8
        L_0x0b12:
            if (r4 >= r8) goto L_0x0ad0
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r4, r12)
            int r2 = r12.zza
            if (r6 != r2) goto L_0x0ad0
            long r2 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r1)
            double r2 = java.lang.Double.longBitsToDouble(r2)
            r13.zze(r2)
            int r4 = r1 + 8
            goto L_0x0b12
        L_0x0b2a:
            r1 = r11
        L_0x0b2b:
            r7 = r42
            if (r1 == r11) goto L_0x0b46
            r17 = r6
            r5 = r8
            r2 = r10
            r6 = r12
            r10 = r14
            r13 = r20
            r14 = r21
            r11 = r22
            r9 = r39
            r3 = 3
            r12 = 1048575(0xfffff, float:1.469367E-39)
        L_0x0b41:
            r8 = r1
            r1 = r37
            goto L_0x001f
        L_0x0b46:
            r4 = r1
            r3 = r6
            r5 = r12
            r10 = r14
        L_0x0b4a:
            r11 = r39
            r19 = 3
            r6 = r46
            goto L_0x0de0
        L_0x0b52:
            r11 = r1
            r37 = r2
            r4 = r8
            r39 = r17
            r7 = 2
            r8 = r6
            r6 = r5
            r5 = r10
            r10 = 1
            r1 = 50
            if (r3 != r1) goto L_0x0b90
            if (r9 != r7) goto L_0x0b89
            sun.misc.Unsafe r1 = zzb
            java.lang.Object r2 = r0.zzs(r4)
            r7 = r42
            java.lang.Object r3 = r1.getObject(r7, r12)
            r4 = r3
            com.google.android.gms.internal.auth.zzfr r4 = (com.google.android.gms.internal.auth.zzfr) r4
            boolean r4 = r4.zze()
            if (r4 != 0) goto L_0x0b86
            com.google.android.gms.internal.auth.zzfr r4 = com.google.android.gms.internal.auth.zzfr.zza()
            com.google.android.gms.internal.auth.zzfr r4 = r4.zzb()
            com.google.android.gms.internal.auth.zzfs.zza(r4, r3)
            r1.putObject(r7, r12, r4)
        L_0x0b86:
            com.google.android.gms.internal.auth.zzfq r2 = (com.google.android.gms.internal.auth.zzfq) r2
            throw r18
        L_0x0b89:
            r2 = r7
            r7 = r42
        L_0x0b8c:
            r10 = r4
            r3 = r6
            r4 = r11
            goto L_0x0b4a
        L_0x0b90:
            r2 = r7
            r7 = r42
            int r1 = r4 + 2
            sun.misc.Unsafe r10 = zzb
            r1 = r44[r1]
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r2
            long r1 = (long) r1
            switch(r3) {
                case 51: goto L_0x0d9d;
                case 52: goto L_0x0d79;
                case 53: goto L_0x0d57;
                case 54: goto L_0x0d57;
                case 55: goto L_0x0d38;
                case 56: goto L_0x0d19;
                case 57: goto L_0x0cfb;
                case 58: goto L_0x0cd4;
                case 59: goto L_0x0c98;
                case 60: goto L_0x0c60;
                case 61: goto L_0x0c49;
                case 62: goto L_0x0d38;
                case 63: goto L_0x0c19;
                case 64: goto L_0x0cfb;
                case 65: goto L_0x0d19;
                case 66: goto L_0x0bff;
                case 67: goto L_0x0bda;
                case 68: goto L_0x0bad;
                default: goto L_0x0ba1;
            }
        L_0x0ba1:
            r17 = r4
            r3 = r6
            r4 = r11
            r11 = r39
            r19 = 3
        L_0x0ba9:
            r6 = r46
            goto L_0x0dc0
        L_0x0bad:
            r3 = 3
            if (r9 != r3) goto L_0x0ba1
            r1 = r39
            java.lang.Object r2 = r0.zzu(r7, r1, r4)
            r8 = r6 & -8
            r13 = r8 | 4
            com.google.android.gms.internal.auth.zzgi r9 = r0.zzr(r4)
            r8 = r2
            r14 = 1
            r10 = r43
            r12 = r11
            r3 = r12
            r12 = r45
            r14 = r47
            int r8 = com.google.android.gms.internal.auth.zzdu.zzl(r8, r9, r10, r11, r12, r13, r14)
            r0.zzC(r7, r1, r4, r2)
            r11 = r1
        L_0x0bd0:
            r17 = r4
        L_0x0bd2:
            r19 = 3
            r4 = r3
            r3 = r6
            r6 = r46
            goto L_0x0dc1
        L_0x0bda:
            r3 = r11
            r11 = r39
            if (r9 != 0) goto L_0x0bf8
            int r8 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r3, r5)
            r44 = r8
            long r8 = r5.zzb
            long r8 = com.google.android.gms.internal.auth.zzej.zzc(r8)
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            r10.putObject(r7, r12, r8)
            r10.putInt(r7, r1, r11)
            r8 = r44
            goto L_0x0bd0
        L_0x0bf8:
            r17 = r4
            r19 = 3
        L_0x0bfc:
            r4 = r3
            r3 = r6
            goto L_0x0ba9
        L_0x0bff:
            r3 = r11
            r11 = r39
            if (r9 != 0) goto L_0x0bf8
            int r8 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r3, r5)
            int r9 = r5.zza
            int r9 = com.google.android.gms.internal.auth.zzej.zzb(r9)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r10.putObject(r7, r12, r9)
            r10.putInt(r7, r1, r11)
            goto L_0x0bd0
        L_0x0c19:
            r3 = r11
            r11 = r39
            if (r9 != 0) goto L_0x0bf8
            int r8 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r3, r5)
            int r9 = r5.zza
            com.google.android.gms.internal.auth.zzey r14 = r0.zzq(r4)
            if (r14 == 0) goto L_0x0c3e
            boolean r14 = r14.zza()
            if (r14 == 0) goto L_0x0c31
            goto L_0x0c3e
        L_0x0c31:
            com.google.android.gms.internal.auth.zzha r1 = zzc(r42)
            long r9 = (long) r9
            java.lang.Long r2 = java.lang.Long.valueOf(r9)
            r1.zzh(r6, r2)
            goto L_0x0bd0
        L_0x0c3e:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r10.putObject(r7, r12, r9)
            r10.putInt(r7, r1, r11)
            goto L_0x0bd0
        L_0x0c49:
            r3 = r11
            r11 = r39
            r8 = 2
            if (r9 != r8) goto L_0x0bf8
            int r9 = com.google.android.gms.internal.auth.zzdu.zza(r15, r3, r5)
            java.lang.Object r14 = r5.zzc
            r10.putObject(r7, r12, r14)
            r10.putInt(r7, r1, r11)
            r17 = r4
            r8 = r9
            goto L_0x0bd2
        L_0x0c60:
            r3 = r11
            r11 = r39
            r8 = 2
            if (r9 != r8) goto L_0x0c90
            java.lang.Object r9 = r0.zzu(r7, r11, r4)
            com.google.android.gms.internal.auth.zzgi r2 = r0.zzr(r4)
            r1 = r9
            r10 = r8
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r12 = r3
            r19 = 3
            r3 = r43
            r13 = r4
            r4 = r12
            r5 = r45
            r14 = r6
            r6 = r47
            int r1 = com.google.android.gms.internal.auth.zzdu.zzm(r1, r2, r3, r4, r5, r6)
            r0.zzC(r7, r11, r13, r9)
            r6 = r46
            r5 = r47
            r8 = r1
            r17 = r13
            r3 = r14
            goto L_0x0dc1
        L_0x0c90:
            r19 = 3
            r5 = r47
            r17 = r4
            goto L_0x0bfc
        L_0x0c98:
            r17 = r4
            r3 = r6
            r4 = r11
            r11 = r39
            r0 = 2
            r19 = 3
            r6 = r46
            if (r9 != r0) goto L_0x0dc0
            int r9 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r4, r5)
            int r0 = r5.zza
            if (r0 != 0) goto L_0x0cb1
            r10.putObject(r7, r12, r8)
            goto L_0x0cce
        L_0x0cb1:
            r8 = r14 & r25
            if (r8 == 0) goto L_0x0cc3
            int r8 = r9 + r0
            boolean r8 = com.google.android.gms.internal.auth.zzhn.zzc(r15, r9, r8)
            if (r8 == 0) goto L_0x0cbe
            goto L_0x0cc3
        L_0x0cbe:
            com.google.android.gms.internal.auth.zzfb r0 = com.google.android.gms.internal.auth.zzfb.zzb()
            throw r0
        L_0x0cc3:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r14 = com.google.android.gms.internal.auth.zzfa.zzb
            r8.<init>(r15, r9, r0, r14)
            r10.putObject(r7, r12, r8)
            int r9 = r9 + r0
        L_0x0cce:
            r10.putInt(r7, r1, r11)
            r8 = r9
            goto L_0x0dc1
        L_0x0cd4:
            r17 = r4
            r3 = r6
            r4 = r11
            r11 = r39
            r19 = 3
            r6 = r46
            if (r9 != 0) goto L_0x0dc0
            int r0 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r4, r5)
            long r8 = r5.zzb
            int r8 = (r8 > r27 ? 1 : (r8 == r27 ? 0 : -1))
            if (r8 == 0) goto L_0x0cec
            r8 = 1
            goto L_0x0cee
        L_0x0cec:
            r8 = r21
        L_0x0cee:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)
            r10.putObject(r7, r12, r8)
            r10.putInt(r7, r1, r11)
        L_0x0cf8:
            r8 = r0
            goto L_0x0dc1
        L_0x0cfb:
            r17 = r4
            r3 = r6
            r4 = r11
            r11 = r39
            r0 = 5
            r19 = 3
            r6 = r46
            if (r9 != r0) goto L_0x0dc0
            int r0 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r4)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r10.putObject(r7, r12, r0)
            int r0 = r4 + 4
            r10.putInt(r7, r1, r11)
            goto L_0x0cf8
        L_0x0d19:
            r17 = r4
            r3 = r6
            r4 = r11
            r11 = r39
            r0 = 1
            r19 = 3
            r6 = r46
            if (r9 != r0) goto L_0x0dc0
            long r8 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r4)
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            r10.putObject(r7, r12, r8)
            int r8 = r4 + 8
            r10.putInt(r7, r1, r11)
            goto L_0x0dc1
        L_0x0d38:
            r17 = r4
            r3 = r6
            r4 = r11
            r11 = r39
            r0 = 1
            r19 = 3
            r6 = r46
            if (r9 != 0) goto L_0x0dc0
            int r8 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r4, r5)
            int r9 = r5.zza
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r10.putObject(r7, r12, r9)
            r10.putInt(r7, r1, r11)
            goto L_0x0dc1
        L_0x0d57:
            r17 = r4
            r3 = r6
            r4 = r11
            r11 = r39
            r0 = 1
            r19 = 3
            r6 = r46
            if (r9 != 0) goto L_0x0dc0
            int r8 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r4, r5)
            r25 = r1
            long r0 = r5.zzb
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r10.putObject(r7, r12, r0)
            r0 = r25
            r10.putInt(r7, r0, r11)
            goto L_0x0dc1
        L_0x0d79:
            r0 = r1
            r17 = r4
            r3 = r6
            r4 = r11
            r11 = r39
            r2 = 5
            r19 = 3
            r6 = r46
            if (r9 != r2) goto L_0x0dc0
            int r2 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r4)
            float r2 = java.lang.Float.intBitsToFloat(r2)
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            r10.putObject(r7, r12, r2)
            int r2 = r4 + 4
            r10.putInt(r7, r0, r11)
        L_0x0d9b:
            r8 = r2
            goto L_0x0dc1
        L_0x0d9d:
            r0 = r1
            r17 = r4
            r3 = r6
            r4 = r11
            r11 = r39
            r2 = 1
            r19 = 3
            r6 = r46
            if (r9 != r2) goto L_0x0dc0
            long r8 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r4)
            double r8 = java.lang.Double.longBitsToDouble(r8)
            java.lang.Double r2 = java.lang.Double.valueOf(r8)
            r10.putObject(r7, r12, r2)
            int r2 = r4 + 8
            r10.putInt(r7, r0, r11)
            goto L_0x0d9b
        L_0x0dc0:
            r8 = r4
        L_0x0dc1:
            if (r8 == r4) goto L_0x0ddd
            r0 = r41
            r6 = r5
            r9 = r11
            r10 = r17
            r13 = r20
            r14 = r21
            r11 = r22
            r1 = r37
            r2 = 1
            r12 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r45
            r17 = r3
            r3 = r19
            goto L_0x001f
        L_0x0ddd:
            r4 = r8
            r10 = r17
        L_0x0de0:
            if (r3 != r6) goto L_0x0df1
            if (r6 != 0) goto L_0x0de5
            goto L_0x0df1
        L_0x0de5:
            r0 = r45
            r8 = r4
            r12 = r6
            r1 = r16
            r11 = r22
            r9 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0e27
        L_0x0df1:
            com.google.android.gms.internal.auth.zzha r0 = zzc(r42)
            r1 = r3
            r2 = r43
            r8 = r3
            r3 = r4
            r9 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r45
            r5 = r0
            r0 = r45
            r12 = r6
            r6 = r47
            int r1 = com.google.android.gms.internal.auth.zzdu.zzg(r1, r2, r3, r4, r5, r6)
            r5 = r0
            r17 = r8
            r12 = r9
            r9 = r11
            r3 = r19
            r13 = r20
            r14 = r21
            r11 = r22
            r2 = 1
            r0 = r41
            goto L_0x0b41
        L_0x0e1b:
            r37 = r1
            r0 = r5
            r22 = r11
            r9 = r12
            r12 = r46
            r1 = r16
            r3 = r17
        L_0x0e27:
            if (r11 == r9) goto L_0x0e2f
            long r4 = (long) r11
            r2 = r37
            r2.putInt(r7, r4, r1)
        L_0x0e2f:
            r1 = r41
            int r2 = r1.zzi
        L_0x0e33:
            int r4 = r1.zzj
            if (r2 >= r4) goto L_0x0e5f
            int[] r4 = r1.zzh
            r4 = r4[r2]
            int[] r5 = r1.zzc
            r5 = r5[r4]
            int r5 = r1.zzo(r4)
            r5 = r5 & r9
            long r5 = (long) r5
            java.lang.Object r5 = com.google.android.gms.internal.auth.zzhj.zzf(r7, r5)
            if (r5 != 0) goto L_0x0e4d
        L_0x0e4b:
            r6 = 1
            goto L_0x0e54
        L_0x0e4d:
            com.google.android.gms.internal.auth.zzey r6 = r1.zzq(r4)
            if (r6 != 0) goto L_0x0e56
            goto L_0x0e4b
        L_0x0e54:
            int r2 = r2 + r6
            goto L_0x0e33
        L_0x0e56:
            com.google.android.gms.internal.auth.zzfr r5 = (com.google.android.gms.internal.auth.zzfr) r5
            java.lang.Object r0 = r1.zzs(r4)
            com.google.android.gms.internal.auth.zzfq r0 = (com.google.android.gms.internal.auth.zzfq) r0
            throw r18
        L_0x0e5f:
            if (r12 != 0) goto L_0x0e69
            if (r8 != r0) goto L_0x0e64
            goto L_0x0e6d
        L_0x0e64:
            com.google.android.gms.internal.auth.zzfb r0 = com.google.android.gms.internal.auth.zzfb.zzd()
            throw r0
        L_0x0e69:
            if (r8 > r0) goto L_0x0e6e
            if (r3 != r12) goto L_0x0e6e
        L_0x0e6d:
            return r8
        L_0x0e6e:
            com.google.android.gms.internal.auth.zzfb r0 = com.google.android.gms.internal.auth.zzfb.zzd()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzga.zzb(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.auth.zzdt):int");
    }

    public final Object zzd() {
        return ((zzev) this.zzg).zzc();
    }

    public final void zze(Object obj) {
        if (zzH(obj)) {
            if (obj instanceof zzev) {
                zzev zzev = (zzev) obj;
                zzev.zzl(Integer.MAX_VALUE);
                zzev.zza = 0;
                zzev.zzj();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzo2 = zzo(i);
                int i2 = 1048575 & zzo2;
                int zzn2 = zzn(zzo2);
                long j = (long) i2;
                if (zzn2 != 9) {
                    if (zzn2 == 60 || zzn2 == 68) {
                        if (zzI(obj, this.zzc[i], i)) {
                            zzr(i).zze(zzb.getObject(obj, j));
                        }
                    } else {
                        switch (zzn2) {
                            case 17:
                                break;
                            case 18:
                            case 19:
                            case 20:
                            case CommonStatusCodes.RECONNECTION_TIMED_OUT_DURING_UPDATE:
                            case 22:
                            case ConnectionResult.API_DISABLED:
                            case ConnectionResult.API_DISABLED_FOR_CONNECTION:
                            case 25:
                            case 26:
                            case 27:
                            case OutsetBoxShadowDrawableKt.MIN_OUTSET_BOX_SHADOW_SDK_VERSION:
                            case InsetBoxShadowDrawableKt.MIN_INSET_BOX_SHADOW_SDK_VERSION:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                                this.zzk.zza(obj, j);
                                continue;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(obj, j);
                                if (object != null) {
                                    ((zzfr) object).zzc();
                                    unsafe.putObject(obj, j, object);
                                    break;
                                } else {
                                    continue;
                                }
                        }
                    }
                }
                if (zzE(obj, i)) {
                    zzr(i).zze(zzb.getObject(obj, j));
                }
            }
            this.zzl.zze(obj);
        }
    }

    public final void zzf(Object obj, Object obj2) {
        zzw(obj);
        obj2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzo2 = zzo(i);
            int i2 = this.zzc[i];
            long j = (long) (1048575 & zzo2);
            switch (zzn(zzo2)) {
                case 0:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzl(obj, j, zzhj.zza(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 1:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzm(obj, j, zzhj.zzb(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 2:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzo(obj, j, zzhj.zzd(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 3:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzo(obj, j, zzhj.zzd(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 4:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 5:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzo(obj, j, zzhj.zzd(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 6:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 7:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzk(obj, j, zzhj.zzt(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 8:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzp(obj, j, zzhj.zzf(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 9:
                    zzx(obj, obj2, i);
                    break;
                case 10:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzp(obj, j, zzhj.zzf(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 11:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 12:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 13:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 14:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzo(obj, j, zzhj.zzd(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 15:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 16:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzo(obj, j, zzhj.zzd(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 17:
                    zzx(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case CommonStatusCodes.RECONNECTION_TIMED_OUT_DURING_UPDATE:
                case 22:
                case ConnectionResult.API_DISABLED:
                case ConnectionResult.API_DISABLED_FOR_CONNECTION:
                case 25:
                case 26:
                case 27:
                case OutsetBoxShadowDrawableKt.MIN_OUTSET_BOX_SHADOW_SDK_VERSION:
                case InsetBoxShadowDrawableKt.MIN_INSET_BOX_SHADOW_SDK_VERSION:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzk.zzb(obj, obj2, j);
                    break;
                case 50:
                    int i3 = zzgk.zza;
                    zzhj.zzp(obj, j, zzfs.zza(zzhj.zzf(obj, j), zzhj.zzf(obj2, j)));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (!zzI(obj2, i2, i)) {
                        break;
                    } else {
                        zzhj.zzp(obj, j, zzhj.zzf(obj2, j));
                        zzA(obj, i2, i);
                        break;
                    }
                case 60:
                    zzy(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case RegisterRequest.U2F_V1_CHALLENGE_BYTE_LENGTH:
                case 66:
                case 67:
                    if (!zzI(obj2, i2, i)) {
                        break;
                    } else {
                        zzhj.zzp(obj, j, zzhj.zzf(obj2, j));
                        zzA(obj, i2, i);
                        break;
                    }
                case 68:
                    zzy(obj, obj2, i);
                    break;
            }
        }
        zzgk.zzd(this.zzl, obj, obj2);
    }

    public final void zzg(Object obj, byte[] bArr, int i, int i2, zzdt zzdt) throws IOException {
        zzb(obj, bArr, i, i2, 0, zzdt);
    }

    public final boolean zzh(Object obj, Object obj2) {
        boolean z;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzo2 = zzo(i);
            long j = (long) (zzo2 & 1048575);
            switch (zzn(zzo2)) {
                case 0:
                    if (zzD(obj, obj2, i) && Double.doubleToLongBits(zzhj.zza(obj, j)) == Double.doubleToLongBits(zzhj.zza(obj2, j))) {
                        continue;
                    }
                case 1:
                    if (zzD(obj, obj2, i) && Float.floatToIntBits(zzhj.zzb(obj, j)) == Float.floatToIntBits(zzhj.zzb(obj2, j))) {
                        continue;
                    }
                case 2:
                    if (zzD(obj, obj2, i) && zzhj.zzd(obj, j) == zzhj.zzd(obj2, j)) {
                        continue;
                    }
                case 3:
                    if (zzD(obj, obj2, i) && zzhj.zzd(obj, j) == zzhj.zzd(obj2, j)) {
                        continue;
                    }
                case 4:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                case 5:
                    if (zzD(obj, obj2, i) && zzhj.zzd(obj, j) == zzhj.zzd(obj2, j)) {
                        continue;
                    }
                case 6:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                case 7:
                    if (zzD(obj, obj2, i) && zzhj.zzt(obj, j) == zzhj.zzt(obj2, j)) {
                        continue;
                    }
                case 8:
                    if (zzD(obj, obj2, i) && zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j))) {
                        continue;
                    }
                case 9:
                    if (zzD(obj, obj2, i) && zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j))) {
                        continue;
                    }
                case 10:
                    if (zzD(obj, obj2, i) && zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j))) {
                        continue;
                    }
                case 11:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                case 12:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                case 13:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                case 14:
                    if (zzD(obj, obj2, i) && zzhj.zzd(obj, j) == zzhj.zzd(obj2, j)) {
                        continue;
                    }
                case 15:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                case 16:
                    if (zzD(obj, obj2, i) && zzhj.zzd(obj, j) == zzhj.zzd(obj2, j)) {
                        continue;
                    }
                case 17:
                    if (zzD(obj, obj2, i) && zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j))) {
                        continue;
                    }
                case 18:
                case 19:
                case 20:
                case CommonStatusCodes.RECONNECTION_TIMED_OUT_DURING_UPDATE:
                case 22:
                case ConnectionResult.API_DISABLED:
                case ConnectionResult.API_DISABLED_FOR_CONNECTION:
                case 25:
                case 26:
                case 27:
                case OutsetBoxShadowDrawableKt.MIN_OUTSET_BOX_SHADOW_SDK_VERSION:
                case InsetBoxShadowDrawableKt.MIN_INSET_BOX_SHADOW_SDK_VERSION:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    z = zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j));
                    break;
                case 50:
                    z = zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case RegisterRequest.U2F_V1_CHALLENGE_BYTE_LENGTH:
                case 66:
                case 67:
                case 68:
                    long zzl2 = (long) (zzl(i) & 1048575);
                    if (zzhj.zzc(obj, zzl2) == zzhj.zzc(obj2, zzl2) && zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j))) {
                        continue;
                    }
            }
            if (!z) {
                return false;
            }
        }
        if (!this.zzl.zzb(obj).equals(this.zzl.zzb(obj2))) {
            return false;
        }
        return true;
    }

    public final boolean zzi(Object obj) {
        int i;
        int i2;
        Object obj2 = obj;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1048575;
        while (i4 < this.zzi) {
            int i6 = this.zzh[i4];
            int i7 = this.zzc[i6];
            int zzo2 = zzo(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i5) {
                if (i9 != 1048575) {
                    i3 = zzb.getInt(obj2, (long) i9);
                }
                i = i3;
                i2 = i9;
            } else {
                i2 = i5;
                i = i3;
            }
            if ((268435456 & zzo2) != 0 && !zzF(obj, i6, i2, i, i10)) {
                return false;
            }
            int zzn2 = zzn(zzo2);
            if (zzn2 != 9 && zzn2 != 17) {
                if (zzn2 != 27) {
                    if (zzn2 == 60 || zzn2 == 68) {
                        if (zzI(obj2, i7, i6) && !zzG(obj2, zzo2, zzr(i6))) {
                            return false;
                        }
                    } else if (zzn2 != 49) {
                        if (zzn2 == 50 && !((zzfr) zzhj.zzf(obj2, (long) (zzo2 & 1048575))).isEmpty()) {
                            zzfq zzfq = (zzfq) zzs(i6);
                            throw null;
                        }
                    }
                }
                List list = (List) zzhj.zzf(obj2, (long) (zzo2 & 1048575));
                if (!list.isEmpty()) {
                    zzgi zzr = zzr(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzr.zzi(list.get(i11))) {
                            return false;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            } else if (zzF(obj, i6, i2, i, i10) && !zzG(obj2, zzo2, zzr(i6))) {
                return false;
            }
            i4++;
            i5 = i2;
            i3 = i;
        }
        return true;
    }
}
