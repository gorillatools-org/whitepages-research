package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzqx implements Supplier {
    private static final zzqx zza = new zzqx();
    private final Supplier zzb = Suppliers.ofInstance(new zzqz());

    public static boolean zzb() {
        return zza.get().zza();
    }

    /* renamed from: zza */
    public final zzqy get() {
        return (zzqy) this.zzb.get();
    }
}
