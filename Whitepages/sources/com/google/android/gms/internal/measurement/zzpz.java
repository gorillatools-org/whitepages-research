package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzpz implements Supplier {
    private static final zzpz zza = new zzpz();
    private final Supplier zzb = Suppliers.ofInstance(new zzqb());

    public static boolean zzb() {
        return zza.get().zza();
    }

    /* renamed from: zza */
    public final zzqa get() {
        return (zzqa) this.zzb.get();
    }
}
