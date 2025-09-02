package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzqu implements Supplier {
    private static final zzqu zza = new zzqu();
    private final Supplier zzb = Suppliers.ofInstance(new zzqw());

    public static boolean zzb() {
        return zza.get().zza();
    }

    /* renamed from: zza */
    public final zzqv get() {
        return (zzqv) this.zzb.get();
    }
}
