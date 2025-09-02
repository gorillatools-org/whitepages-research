package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzql implements Supplier {
    private static final zzql zza = new zzql();
    private final Supplier zzb = Suppliers.ofInstance(new zzqn());

    public static boolean zzb() {
        return zza.get().zza();
    }

    /* renamed from: zza */
    public final zzqm get() {
        return (zzqm) this.zzb.get();
    }
}
