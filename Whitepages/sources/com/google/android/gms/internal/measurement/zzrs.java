package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzrs implements Supplier {
    private static final zzrs zza = new zzrs();
    private final Supplier zzb = Suppliers.ofInstance(new zzru());

    public static boolean zzb() {
        return zza.get().zza();
    }

    /* renamed from: zza */
    public final zzrt get() {
        return (zzrt) this.zzb.get();
    }
}
