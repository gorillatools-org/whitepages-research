package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzqf implements Supplier {
    private static final zzqf zza = new zzqf();
    private final Supplier zzb = Suppliers.ofInstance(new zzqh());

    public static boolean zzb() {
        return zza.get().zza();
    }

    /* renamed from: zza */
    public final zzqg get() {
        return (zzqg) this.zzb.get();
    }
}
