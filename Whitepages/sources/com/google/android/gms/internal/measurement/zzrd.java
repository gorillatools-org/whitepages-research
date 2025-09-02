package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzrd implements Supplier {
    private static final zzrd zza = new zzrd();
    private final Supplier zzb = Suppliers.ofInstance(new zzrf());

    public static boolean zzb() {
        zza.get().zza();
        return true;
    }

    public static boolean zzc() {
        return zza.get().zzb();
    }

    /* renamed from: zza */
    public final zzre get() {
        return (zzre) this.zzb.get();
    }
}
