package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzpk implements Supplier {
    private static final zzpk zza = new zzpk();
    private final Supplier zzb = Suppliers.ofInstance(new zzpm());

    public static boolean zzb() {
        return zza.get().zza();
    }

    public static boolean zzc() {
        return zza.get().zzb();
    }

    /* renamed from: zza */
    public final zzpl get() {
        return (zzpl) this.zzb.get();
    }
}
