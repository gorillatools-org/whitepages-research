package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;

public final class zzhp {
    final /* synthetic */ zzht zza;
    private final String zzb;
    private final long zzc;
    private boolean zzd;
    private long zze;

    public zzhp(zzht zzht, String str, long j) {
        this.zza = zzht;
        Preconditions.checkNotEmpty(str);
        this.zzb = str;
        this.zzc = j;
    }

    public final long zza() {
        if (!this.zzd) {
            this.zzd = true;
            zzht zzht = this.zza;
            this.zze = zzht.zzb().getLong(this.zzb, this.zzc);
        }
        return this.zze;
    }

    public final void zzb(long j) {
        SharedPreferences.Editor edit = this.zza.zzb().edit();
        edit.putLong(this.zzb, j);
        edit.apply();
        this.zze = j;
    }
}
