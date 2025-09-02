package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzoy implements Supplier {
    private static final zzoy zza = new zzoy();
    private final Supplier zzb = Suppliers.ofInstance(new zzpa());

    public static boolean zzb() {
        zza.get().zza();
        return true;
    }

    public static boolean zzc() {
        return zza.get().zzb();
    }

    /* renamed from: zza */
    public final zzoz get() {
        return (zzoz) this.zzb.get();
    }
}
