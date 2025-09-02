package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzrg implements Supplier {
    private static final zzrg zza = new zzrg();
    private final Supplier zzb = Suppliers.ofInstance(new zzri());

    public static boolean zzb() {
        return zza.get().zza();
    }

    /* renamed from: zza */
    public final zzrh get() {
        return (zzrh) this.zzb.get();
    }
}
