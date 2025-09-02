package com.google.android.gms.internal.play_billing;

import java.io.IOException;
import java.util.List;

final class zzeq {
    public static final /* synthetic */ int zza = 0;
    private static final Class zzb;
    private static final zzff zzc;
    private static final zzff zzd = new zzfh();

    static {
        Class<?> cls;
        Class<?> cls2;
        zzff zzff = null;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zzb = cls;
        try {
            cls2 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused2) {
            cls2 = null;
        }
        if (cls2 != null) {
            try {
                zzff = (zzff) cls2.getConstructor((Class[]) null).newInstance((Object[]) null);
            } catch (Throwable unused3) {
            }
        }
        zzc = zzff;
    }

    public static void zzA(int i, List list, zzfx zzfx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfx.zzy(i, list, z);
        }
    }

    public static void zzB(int i, List list, zzfx zzfx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfx.zzA(i, list, z);
        }
    }

    public static void zzC(int i, List list, zzfx zzfx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfx.zzC(i, list, z);
        }
    }

    public static void zzD(int i, List list, zzfx zzfx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfx.zzE(i, list, z);
        }
    }

    public static void zzE(int i, List list, zzfx zzfx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfx.zzJ(i, list, z);
        }
    }

    public static void zzF(int i, List list, zzfx zzfx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfx.zzL(i, list, z);
        }
    }

    static boolean zzG(Object obj, Object obj2) {
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
        if (list instanceof zzct) {
            zzct zzct = (zzct) list;
            i = 0;
            while (i2 < size) {
                i += zzby.zzx((long) zzct.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzby.zzx((long) ((Integer) list.get(i2)).intValue());
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
        return size * (zzby.zzw(i << 3) + 4);
    }

    static int zzc(List list) {
        return list.size() * 4;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzby.zzw(i << 3) + 8);
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
        if (list instanceof zzct) {
            zzct zzct = (zzct) list;
            i = 0;
            while (i2 < size) {
                i += zzby.zzx((long) zzct.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzby.zzx((long) ((Integer) list.get(i2)).intValue());
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
        if (list instanceof zzdr) {
            zzdr zzdr = (zzdr) list;
            i = 0;
            while (i2 < size) {
                i += zzby.zzx(zzdr.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzby.zzx(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzh(int i, Object obj, zzeo zzeo) {
        int i2 = i << 3;
        if (!(obj instanceof zzdi)) {
            return zzby.zzw(i2) + zzby.zzu((zzec) obj, zzeo);
        }
        int zzw = zzby.zzw(i2);
        int zza2 = ((zzdi) obj).zza();
        return zzw + zzby.zzw(zza2) + zza2;
    }

    static int zzi(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzct) {
            zzct zzct = (zzct) list;
            i = 0;
            while (i2 < size) {
                int zze = zzct.zze(i2);
                i += zzby.zzw((zze >> 31) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i3 = i + zzby.zzw((intValue >> 31) ^ (intValue + intValue));
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
        if (list instanceof zzdr) {
            zzdr zzdr = (zzdr) list;
            i = 0;
            while (i2 < size) {
                long zze = zzdr.zze(i2);
                i += zzby.zzx((zze >> 63) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i3 = i + zzby.zzx((longValue >> 63) ^ (longValue + longValue));
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
        if (list instanceof zzct) {
            zzct zzct = (zzct) list;
            i = 0;
            while (i2 < size) {
                i += zzby.zzw(zzct.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzby.zzw(((Integer) list.get(i2)).intValue());
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
        if (list instanceof zzdr) {
            zzdr zzdr = (zzdr) list;
            i = 0;
            while (i2 < size) {
                i += zzby.zzx(zzdr.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzby.zzx(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzff zzm() {
        return zzc;
    }

    public static zzff zzn() {
        return zzd;
    }

    static Object zzo(Object obj, int i, int i2, Object obj2, zzff zzff) {
        if (obj2 == null) {
            obj2 = zzff.zzc(obj);
        }
        zzff.zzf(obj2, i, (long) i2);
        return obj2;
    }

    static void zzp(zzce zzce, Object obj, Object obj2) {
        zzci zzb2 = zzce.zzb(obj2);
        if (!zzb2.zza.isEmpty()) {
            zzce.zzc(obj).zzh(zzb2);
        }
    }

    static void zzq(zzff zzff, Object obj, Object obj2) {
        zzff.zzh(obj, zzff.zze(zzff.zzd(obj), zzff.zzd(obj2)));
    }

    public static void zzr(Class cls) {
        Class cls2;
        if (!zzcs.class.isAssignableFrom(cls) && (cls2 = zzb) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzs(int i, List list, zzfx zzfx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfx.zzc(i, list, z);
        }
    }

    public static void zzt(int i, List list, zzfx zzfx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfx.zzg(i, list, z);
        }
    }

    public static void zzu(int i, List list, zzfx zzfx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfx.zzj(i, list, z);
        }
    }

    public static void zzv(int i, List list, zzfx zzfx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfx.zzl(i, list, z);
        }
    }

    public static void zzw(int i, List list, zzfx zzfx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfx.zzn(i, list, z);
        }
    }

    public static void zzx(int i, List list, zzfx zzfx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfx.zzp(i, list, z);
        }
    }

    public static void zzy(int i, List list, zzfx zzfx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfx.zzs(i, list, z);
        }
    }

    public static void zzz(int i, List list, zzfx zzfx, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzfx.zzu(i, list, z);
        }
    }
}
