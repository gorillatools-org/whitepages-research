package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzqr implements Supplier {
    private static final zzqr zza = new zzqr();
    private final Supplier zzb = Suppliers.ofInstance(new zzqt());

    public static boolean zzb() {
        zza.get().zza();
        return true;
    }

    public static boolean zzc() {
        return zza.get().zzb();
    }

    public static boolean zzd() {
        return zza.get().zzc();
    }

    public static boolean zze() {
        return zza.get().zzd();
    }

    public static boolean zzf() {
        return zza.get().zze();
    }

    public static boolean zzg() {
        return zza.get().zzf();
    }

    public static boolean zzh() {
        return zza.get().zzg();
    }

    public static boolean zzi() {
        return zza.get().zzh();
    }

    public static boolean zzj() {
        return zza.get().zzi();
    }

    /* renamed from: zza */
    public final zzqs get() {
        return (zzqs) this.zzb.get();
    }
}
