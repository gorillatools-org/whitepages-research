package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzpe implements Supplier {
    private static final zzpe zza = new zzpe();
    private final Supplier zzb = Suppliers.ofInstance(new zzpg());

    public static long zza() {
        return zza.get().zza();
    }

    /* renamed from: zzb */
    public final zzpf get() {
        return (zzpf) this.zzb.get();
    }
}
