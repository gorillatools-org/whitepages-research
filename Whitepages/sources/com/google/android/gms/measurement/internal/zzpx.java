package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzhv;
import java.util.Map;

final class zzpx {
    private long zza;
    private zzhv zzb;
    private String zzc;
    private Map zzd;
    private zzmf zze;
    private long zzf;
    private long zzg;
    private long zzh;
    private int zzi;

    zzpx() {
    }

    public final zzpx zza(long j) {
        this.zzg = j;
        return this;
    }

    public final zzpx zzb(long j) {
        this.zzf = j;
        return this;
    }

    public final zzpx zzc(long j) {
        this.zzh = j;
        return this;
    }

    public final zzpx zzd(zzhv zzhv) {
        this.zzb = zzhv;
        return this;
    }

    public final zzpx zze(int i) {
        this.zzi = i;
        return this;
    }

    public final zzpx zzf(long j) {
        this.zza = j;
        return this;
    }

    public final zzpx zzg(Map map) {
        this.zzd = map;
        return this;
    }

    public final zzpx zzh(zzmf zzmf) {
        this.zze = zzmf;
        return this;
    }

    public final zzpx zzi(String str) {
        this.zzc = str;
        return this;
    }

    public final zzpz zzj() {
        return new zzpz(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, (zzpy) null);
    }
}
