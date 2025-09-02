package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzqo implements Supplier {
    private static final zzqo zza = new zzqo();
    private final Supplier zzb = Suppliers.ofInstance(new zzqq());

    public static double zza() {
        return zza.get().zza();
    }

    public static long zzb() {
        return zza.get().zzb();
    }

    public static long zzc() {
        return zza.get().zzc();
    }

    public static long zzd() {
        return zza.get().zzd();
    }

    public static String zzf() {
        return zza.get().zze();
    }

    public static boolean zzg() {
        return zza.get().zzf();
    }

    /* renamed from: zze */
    public final zzqp get() {
        return (zzqp) this.zzb.get();
    }
}
