package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzqi implements Supplier {
    private static final zzqi zza = new zzqi();
    private final Supplier zzb = Suppliers.ofInstance(new zzqk());

    public static boolean zzb() {
        return zza.get().zza();
    }

    /* renamed from: zza */
    public final zzqj get() {
        return (zzqj) this.zzb.get();
    }
}
