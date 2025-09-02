package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzim;
import java.util.Collections;
import java.util.Map;

public final class zzph {
    private final String zza;
    private final Map zzb;
    private final zzmf zzc;
    private final zzim zzd;

    zzph(String str, Map map, zzmf zzmf, zzim zzim) {
        this.zza = str;
        this.zzb = map;
        this.zzc = zzmf;
        this.zzd = zzim;
    }

    public final zzmf zza() {
        return this.zzc;
    }

    public final zzim zzb() {
        return this.zzd;
    }

    public final String zzc() {
        return this.zza;
    }

    public final Map zzd() {
        Map map = this.zzb;
        return map == null ? Collections.emptyMap() : map;
    }
}
