package com.google.android.gms.internal.play_billing;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzel {
    private static final zzel zza = new zzel();
    private final zzep zzb = new zzdu();
    private final ConcurrentMap zzc = new ConcurrentHashMap();

    private zzel() {
    }

    public static zzel zza() {
        return zza;
    }

    public final zzeo zzb(Class cls) {
        zzda.zzc(cls, "messageType");
        zzeo zzeo = (zzeo) this.zzc.get(cls);
        if (zzeo == null) {
            zzeo = this.zzb.zza(cls);
            zzda.zzc(cls, "messageType");
            zzeo zzeo2 = (zzeo) this.zzc.putIfAbsent(cls, zzeo);
            return zzeo2 == null ? zzeo : zzeo2;
        }
    }
}
