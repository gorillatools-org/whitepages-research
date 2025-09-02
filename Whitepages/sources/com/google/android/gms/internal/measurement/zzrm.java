package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzrm implements Supplier {
    private static final zzrm zza = new zzrm();
    private final Supplier zzb = Suppliers.ofInstance(new zzro());

    public static boolean zzb() {
        return zza.get().zza();
    }

    /* renamed from: zza */
    public final zzrn get() {
        return (zzrn) this.zzb.get();
    }
}
