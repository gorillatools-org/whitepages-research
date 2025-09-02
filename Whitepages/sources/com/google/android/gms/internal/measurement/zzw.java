package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public final class zzw extends zzai {
    final Map zza = new HashMap();
    private final zzj zzb;

    public zzw(zzj zzj) {
        super("require");
        this.zzb = zzj;
    }

    public final zzap zza(zzg zzg, List list) {
        zzap zzap;
        zzh.zzh("require", 1, list);
        String zzi = zzg.zzb((zzap) list.get(0)).zzi();
        Map map = this.zza;
        if (map.containsKey(zzi)) {
            return (zzap) map.get(zzi);
        }
        Map map2 = this.zzb.zza;
        if (map2.containsKey(zzi)) {
            try {
                zzap = (zzap) ((Callable) map2.get(zzi)).call();
            } catch (Exception unused) {
                throw new IllegalStateException("Failed to create API implementation: ".concat(String.valueOf(zzi)));
            }
        } else {
            zzap = zzap.zzf;
        }
        if (zzap instanceof zzai) {
            this.zza.put(zzi, (zzai) zzap);
        }
        return zzap;
    }
}
