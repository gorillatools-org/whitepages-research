package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzrj implements Supplier {
    private static final zzrj zza = new zzrj();
    private final Supplier zzb = Suppliers.ofInstance(new zzrl());

    public static boolean zzb() {
        return zza.get().zza();
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

    /* renamed from: zza */
    public final zzrk get() {
        return (zzrk) this.zzb.get();
    }
}
