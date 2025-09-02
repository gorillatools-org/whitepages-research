package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzqc implements Supplier {
    private static final zzqc zza = new zzqc();
    private final Supplier zzb = Suppliers.ofInstance(new zzqe());

    public static boolean zzb() {
        return zza.get().zza();
    }

    /* renamed from: zza */
    public final zzqd get() {
        return (zzqd) this.zzb.get();
    }
}
