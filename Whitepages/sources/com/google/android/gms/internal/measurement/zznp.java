package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zznp {
    public static final /* synthetic */ int zza = 0;
    private static final zznp zzb = new zznp();
    private final zznt zzc = new zzmz();
    private final ConcurrentMap zzd = new ConcurrentHashMap();

    private zznp() {
    }

    public static zznp zza() {
        return zzb;
    }

    public final zzns zzb(Class cls) {
        zzmk.zzc(cls, "messageType");
        ConcurrentMap concurrentMap = this.zzd;
        zzns zzns = (zzns) concurrentMap.get(cls);
        if (zzns == null) {
            zzns = this.zzc.zza(cls);
            zzmk.zzc(cls, "messageType");
            zzns zzns2 = (zzns) concurrentMap.putIfAbsent(cls, zzns);
            if (zzns2 != null) {
                return zzns2;
            }
        }
        return zzns;
    }
}
