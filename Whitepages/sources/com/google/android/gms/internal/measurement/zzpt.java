package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzpt implements Supplier {
    private static final zzpt zza = new zzpt();
    private final Supplier zzb = Suppliers.ofInstance(new zzpv());

    public static boolean zzb() {
        return zza.get().zza();
    }

    /* renamed from: zza */
    public final zzpu get() {
        return (zzpu) this.zzb.get();
    }
}
