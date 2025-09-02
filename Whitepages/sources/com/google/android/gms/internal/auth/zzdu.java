package com.google.android.gms.internal.auth;

import java.io.IOException;

final class zzdu {
    static int zza(byte[] bArr, int i, zzdt zzdt) throws zzfb {
        int zzh = zzh(bArr, i, zzdt);
        int i2 = zzdt.zza;
        if (i2 < 0) {
            throw zzfb.zzc();
        } else if (i2 > bArr.length - zzh) {
            throw zzfb.zzf();
        } else if (i2 == 0) {
            zzdt.zzc = zzef.zzb;
            return zzh;
        } else {
            zzdt.zzc = zzef.zzk(bArr, zzh, i2);
            return zzh + i2;
        }
    }

    static int zzb(byte[] bArr, int i) {
        int i2 = (bArr[i + 1] & 255) << 8;
        return ((bArr[i + 3] & 255) << 24) | i2 | (bArr[i] & 255) | ((bArr[i + 2] & 255) << 16);
    }

    static int zzc(zzgi zzgi, byte[] bArr, int i, int i2, int i3, zzdt zzdt) throws IOException {
        Object zzd = zzgi.zzd();
        int zzl = zzl(zzd, zzgi, bArr, i, i2, i3, zzdt);
        zzgi.zze(zzd);
        zzdt.zzc = zzd;
        return zzl;
    }

    static int zzd(zzgi zzgi, byte[] bArr, int i, int i2, zzdt zzdt) throws IOException {
        Object zzd = zzgi.zzd();
        int zzm = zzm(zzd, zzgi, bArr, i, i2, zzdt);
        zzgi.zze(zzd);
        zzdt.zzc = zzd;
        return zzm;
    }

    static int zze(zzgi zzgi, int i, byte[] bArr, int i2, int i3, zzez zzez, zzdt zzdt) throws IOException {
        int zzd = zzd(zzgi, bArr, i2, i3, zzdt);
        zzez.add(zzdt.zzc);
        while (zzd < i3) {
            int zzh = zzh(bArr, zzd, zzdt);
            if (i != zzdt.zza) {
                break;
            }
            zzd = zzd(zzgi, bArr, zzh, i3, zzdt);
            zzez.add(zzdt.zzc);
        }
        return zzd;
    }

    static int zzf(byte[] bArr, int i, zzez zzez, zzdt zzdt) throws IOException {
        zzew zzew = (zzew) zzez;
        int zzh = zzh(bArr, i, zzdt);
        int i2 = zzdt.zza + zzh;
        while (zzh < i2) {
            zzh = zzh(bArr, zzh, zzdt);
            zzew.zze(zzdt.zza);
        }
        if (zzh == i2) {
            return zzh;
        }
        throw zzfb.zzf();
    }

    static int zzg(int i, byte[] bArr, int i2, int i3, zzha zzha, zzdt zzdt) throws zzfb {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzk = zzk(bArr, i2, zzdt);
                zzha.zzh(i, Long.valueOf(zzdt.zzb));
                return zzk;
            } else if (i4 == 1) {
                zzha.zzh(i, Long.valueOf(zzn(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zzh = zzh(bArr, i2, zzdt);
                int i5 = zzdt.zza;
                if (i5 < 0) {
                    throw zzfb.zzc();
                } else if (i5 <= bArr.length - zzh) {
                    if (i5 == 0) {
                        zzha.zzh(i, zzef.zzb);
                    } else {
                        zzha.zzh(i, zzef.zzk(bArr, zzh, i5));
                    }
                    return zzh + i5;
                } else {
                    throw zzfb.zzf();
                }
            } else if (i4 == 3) {
                int i6 = (i & -8) | 4;
                zzha zzd = zzha.zzd();
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zzh2 = zzh(bArr, i2, zzdt);
                    int i8 = zzdt.zza;
                    i7 = i8;
                    if (i8 == i6) {
                        i2 = zzh2;
                        break;
                    }
                    int zzg = zzg(i7, bArr, zzh2, i3, zzd, zzdt);
                    i7 = i8;
                    i2 = zzg;
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzfb.zzd();
                }
                zzha.zzh(i, zzd);
                return i2;
            } else if (i4 == 5) {
                zzha.zzh(i, Integer.valueOf(zzb(bArr, i2)));
                return i2 + 4;
            } else {
                throw zzfb.zza();
            }
        } else {
            throw zzfb.zza();
        }
    }

    static int zzh(byte[] bArr, int i, zzdt zzdt) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzi(b, bArr, i2, zzdt);
        }
        zzdt.zza = b;
        return i2;
    }

    static int zzi(int i, byte[] bArr, int i2, zzdt zzdt) {
        byte b = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & 127;
        if (b >= 0) {
            zzdt.zza = i4 | (b << 7);
            return i3;
        }
        int i5 = i4 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i2 + 2;
        byte b2 = bArr[i3];
        if (b2 >= 0) {
            zzdt.zza = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i2 + 3;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzdt.zza = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i2 + 4;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzdt.zza = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] < 0) {
                i10 = i12;
            } else {
                zzdt.zza = i11;
                return i12;
            }
        }
    }

    static int zzj(int i, byte[] bArr, int i2, int i3, zzez zzez, zzdt zzdt) {
        zzew zzew = (zzew) zzez;
        int zzh = zzh(bArr, i2, zzdt);
        zzew.zze(zzdt.zza);
        while (zzh < i3) {
            int zzh2 = zzh(bArr, zzh, zzdt);
            if (i != zzdt.zza) {
                break;
            }
            zzh = zzh(bArr, zzh2, zzdt);
            zzew.zze(zzdt.zza);
        }
        return zzh;
    }

    static int zzk(byte[] bArr, int i, zzdt zzdt) {
        long j = (long) bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            zzdt.zzb = j;
            return i2;
        }
        int i3 = i + 2;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & Byte.MAX_VALUE)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & Byte.MAX_VALUE)) << i4;
            int i6 = i5;
            b = b2;
            i3 = i6;
        }
        zzdt.zzb = j2;
        return i3;
    }

    static int zzl(Object obj, zzgi zzgi, byte[] bArr, int i, int i2, int i3, zzdt zzdt) throws IOException {
        int zzb = ((zzga) zzgi).zzb(obj, bArr, i, i2, i3, zzdt);
        zzdt.zzc = obj;
        return zzb;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int zzm(java.lang.Object r6, com.google.android.gms.internal.auth.zzgi r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.auth.zzdt r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = zzi(r9, r8, r0, r11)
            int r9 = r11.zza
        L_0x000c:
            r3 = r0
            if (r9 < 0) goto L_0x001e
            int r10 = r10 - r3
            if (r9 > r10) goto L_0x001e
            int r9 = r9 + r3
            r0 = r7
            r1 = r6
            r2 = r8
            r4 = r9
            r5 = r11
            r0.zzg(r1, r2, r3, r4, r5)
            r11.zzc = r6
            return r9
        L_0x001e:
            com.google.android.gms.internal.auth.zzfb r6 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzdu.zzm(java.lang.Object, com.google.android.gms.internal.auth.zzgi, byte[], int, int, com.google.android.gms.internal.auth.zzdt):int");
    }

    static long zzn(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }
}
