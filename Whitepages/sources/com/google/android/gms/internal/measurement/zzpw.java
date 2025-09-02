package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzpw implements Supplier {
    private static final zzpw zza = new zzpw();
    private final Supplier zzb = Suppliers.ofInstance(new zzpy());

    public static boolean zzb() {
        return zza.get().zza();
    }

    /* renamed from: zza */
    public final zzpx get() {
        return (zzpx) this.zzb.get();
    }
}
