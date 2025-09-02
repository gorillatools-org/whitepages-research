package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzo;
import java.util.Map;

final class zzie implements zzo {
    final /* synthetic */ String zza;
    final /* synthetic */ zzif zzb;

    zzie(zzif zzif, String str) {
        this.zza = str;
        this.zzb = zzif;
    }

    public final String zza(String str) {
        Map map = (Map) this.zzb.zzf.get(this.zza);
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return (String) map.get(str);
    }
}
