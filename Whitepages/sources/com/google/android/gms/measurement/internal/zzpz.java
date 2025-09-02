package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzhv;
import com.google.android.gms.internal.measurement.zzim;
import java.util.Map;

public final class zzpz {
    private final long zza;
    private final zzhv zzb;
    private final String zzc;
    private final Map zzd;
    private final zzmf zze;
    private final long zzf;
    private final long zzg;
    private final int zzh;

    /* synthetic */ zzpz(long j, zzhv zzhv, String str, Map map, zzmf zzmf, long j2, long j3, long j4, int i, zzpy zzpy) {
        this.zza = j;
        this.zzb = zzhv;
        this.zzc = str;
        this.zzd = map;
        this.zze = zzmf;
        this.zzf = j3;
        this.zzg = j4;
        this.zzh = i;
    }

    public final int zza() {
        return this.zzh;
    }

    public final long zzb() {
        return this.zzg;
    }

    public final long zzc() {
        return this.zza;
    }

    public final zzmf zzd() {
        return this.zze;
    }

    public final zzpa zze() {
        Bundle bundle = new Bundle();
        for (Map.Entry entry : this.zzd.entrySet()) {
            bundle.putString((String) entry.getKey(), (String) entry.getValue());
        }
        long j = this.zza;
        zzhv zzhv = this.zzb;
        String str = this.zzc;
        zzmf zzmf = this.zze;
        long j2 = this.zzf;
        return new zzpa(j, zzhv.zzcd(), str, bundle, zzmf.zza(), j2, "");
    }

    public final zzph zzf() {
        return new zzph(this.zzc, this.zzd, this.zze, (zzim) null);
    }

    public final zzhv zzg() {
        return this.zzb;
    }

    public final String zzh() {
        return this.zzc;
    }
}
