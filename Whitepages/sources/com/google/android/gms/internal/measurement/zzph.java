package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzph implements Supplier {
    private static final zzph zza = new zzph();
    private final Supplier zzb = Suppliers.ofInstance(new zzpj());

    public static boolean zzb() {
        return zza.get().zza();
    }

    public static boolean zzc() {
        return zza.get().zzb();
    }

    public static boolean zzd() {
        return zza.get().zzc();
    }

    /* renamed from: zza */
    public final zzpi get() {
        return (zzpi) this.zzb.get();
    }
}
