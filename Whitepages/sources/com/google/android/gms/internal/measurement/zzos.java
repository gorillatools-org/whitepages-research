package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzos implements Supplier {
    private static final zzos zza = new zzos();
    private final Supplier zzb = Suppliers.ofInstance(new zzou());

    public static boolean zzb() {
        return zza.get().zza();
    }

    /* renamed from: zza */
    public final zzot get() {
        return (zzot) this.zzb.get();
    }
}
