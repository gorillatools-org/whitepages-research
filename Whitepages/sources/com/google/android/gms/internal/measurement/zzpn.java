package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class zzpn implements Supplier {
    private static final zzpn zza = new zzpn();
    private final Supplier zzb = Suppliers.ofInstance(new zzpp());

    public static boolean zzb() {
        zza.get().zza();
        return true;
    }

    public static boolean zzc() {
        return zza.get().zzb();
    }

    public static boolean zzd() {
        return zza.get().zzc();
    }

    /* renamed from: zza */
    public final zzpo get() {
        return (zzpo) this.zzb.get();
    }
}
