package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;

final class zznu {
    public static final /* synthetic */ int zza = 0;
    private static final zzoe zzb = new zzog();

    static {
        int i = zznp.zza;
    }

    public static void zzA(int i, List list, zzor zzor, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzor.zzC(i, list, z);
        }
    }

    public static void zzB(int i, List list, zzor zzor, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzor.zzE(i, list, z);
        }
    }

    public static void zzC(int i, List list, zzor zzor, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzor.zzJ(i, list, z);
        }
    }

    public static void zzD(int i, List list, zzor zzor, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzor.zzL(i, list, z);
        }
    }

    static boolean zzE(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj != null) {
            return obj.equals(obj2);
        }
        return false;
    }

    static int zza(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzme) {
            zzme zzme = (zzme) list;
            i = 0;
            while (i2 < size) {
                i += zzlk.zzA((long) zzme.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzlk.zzA((long) ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzb(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzlk.zzz(i << 3) + 4);
    }

    static int zzc(List list) {
        return list.size() * 4;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzlk.zzz(i << 3) + 8);
    }

    static int zze(List list) {
        return list.size() * 8;
    }

    static int zzf(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzme) {
            zzme zzme = (zzme) list;
            i = 0;
            while (i2 < size) {
                i += zzlk.zzA((long) zzme.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzlk.zzA((long) ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzg(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzmw) {
            zzmw zzmw = (zzmw) list;
            i = 0;
            while (i2 < size) {
                i += zzlk.zzA(zzmw.zza(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzlk.zzA(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzh(int i, Object obj, zzns zzns) {
        int i2 = i << 3;
        if (!(obj instanceof zzms)) {
            return zzlk.zzz(i2) + zzlk.zzx((zznh) obj, zzns);
        }
        int zzz = zzlk.zzz(i2);
        int zza2 = ((zzms) obj).zza();
        return zzz + zzlk.zzz(zza2) + zza2;
    }

    static int zzi(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzme) {
            zzme zzme = (zzme) list;
            i = 0;
            while (i2 < size) {
                int zze = zzme.zze(i2);
                i += zzlk.zzz((zze >> 31) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i3 = i + zzlk.zzz((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    static int zzj(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzmw) {
            zzmw zzmw = (zzmw) list;
            i = 0;
            while (i2 < size) {
                long zza2 = zzmw.zza(i2);
                i += zzlk.zzA((zza2 >> 63) ^ (zza2 + zza2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i3 = i + zzlk.zzA((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    static int zzk(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzme) {
            zzme zzme = (zzme) list;
            i = 0;
            while (i2 < size) {
                i += zzlk.zzz(zzme.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzlk.zzz(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzl(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzmw) {
            zzmw zzmw = (zzmw) list;
            i = 0;
            while (i2 < size) {
                i += zzlk.zzA(zzmw.zza(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzlk.zzA(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzoe zzm() {
        return zzb;
    }

    /* JADX WARNING: Failed to insert additional move for type inference */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Object zzn(java.lang.Object r2, int r3, int r4, java.lang.Object r5, com.google.android.gms.internal.measurement.zzoe r6) {
        /*
            if (r5 != 0) goto L_0x0012
            com.google.android.gms.internal.measurement.zzmd r2 = (com.google.android.gms.internal.measurement.zzmd) r2
            com.google.android.gms.internal.measurement.zzof r5 = r2.zzc
            com.google.android.gms.internal.measurement.zzof r6 = com.google.android.gms.internal.measurement.zzof.zzc()
            if (r5 != r6) goto L_0x0012
            com.google.android.gms.internal.measurement.zzof r5 = com.google.android.gms.internal.measurement.zzof.zzf()
            r2.zzc = r5
        L_0x0012:
            long r0 = (long) r4
            int r2 = r3 << 3
            r3 = r5
            com.google.android.gms.internal.measurement.zzof r3 = (com.google.android.gms.internal.measurement.zzof) r3
            java.lang.Long r4 = java.lang.Long.valueOf(r0)
            r3.zzj(r2, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zznu.zzn(java.lang.Object, int, int, java.lang.Object, com.google.android.gms.internal.measurement.zzoe):java.lang.Object");
    }

    static void zzo(zzlq zzlq, Object obj, Object obj2) {
        if (!((zzma) obj2).zzb.zza.isEmpty()) {
            zzma zzma = (zzma) obj;
            throw null;
        }
    }

    static void zzp(zzoe zzoe, Object obj, Object obj2) {
        zzmd zzmd = (zzmd) obj;
        zzof zzof = zzmd.zzc;
        zzof zzof2 = ((zzmd) obj2).zzc;
        if (!zzof.zzc().equals(zzof2)) {
            if (zzof.zzc().equals(zzof)) {
                zzof = zzof.zze(zzof, zzof2);
            } else {
                zzof.zzd(zzof2);
            }
        }
        zzmd.zzc = zzof;
    }

    public static void zzq(int i, List list, zzor zzor, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzor.zzc(i, list, z);
        }
    }

    public static void zzr(int i, List list, zzor zzor, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzor.zzg(i, list, z);
        }
    }

    public static void zzs(int i, List list, zzor zzor, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzor.zzj(i, list, z);
        }
    }

    public static void zzt(int i, List list, zzor zzor, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzor.zzl(i, list, z);
        }
    }

    public static void zzu(int i, List list, zzor zzor, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzor.zzn(i, list, z);
        }
    }

    public static void zzv(int i, List list, zzor zzor, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzor.zzp(i, list, z);
        }
    }

    public static void zzw(int i, List list, zzor zzor, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzor.zzs(i, list, z);
        }
    }

    public static void zzx(int i, List list, zzor zzor, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzor.zzu(i, list, z);
        }
    }

    public static void zzy(int i, List list, zzor zzor, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzor.zzy(i, list, z);
        }
    }

    public static void zzz(int i, List list, zzor zzor, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzor.zzA(i, list, z);
        }
    }
}
