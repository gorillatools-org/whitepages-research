package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzrp implements Supplier {
    private static final zzrp zza = new zzrp();
    private final Supplier zzb = Suppliers.ofInstance(new zzrr());

    public static boolean zzb() {
        zza.get().zza();
        return true;
    }

    public static boolean zzc() {
        return zza.get().zzb();
    }

    /* renamed from: zza */
    public final zzrq get() {
        return (zzrq) this.zzb.get();
    }
}
