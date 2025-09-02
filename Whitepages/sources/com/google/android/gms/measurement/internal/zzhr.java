package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;

public final class zzhr {
    final /* synthetic */ zzht zza;
    private final String zzb;
    private boolean zzc;
    private String zzd;

    public zzhr(zzht zzht, String str, String str2) {
        this.zza = zzht;
        Preconditions.checkNotEmpty(str);
        this.zzb = str;
    }

    public final String zza() {
        if (!this.zzc) {
            this.zzc = true;
            zzht zzht = this.zza;
            this.zzd = zzht.zzb().getString(this.zzb, (String) null);
        }
        return this.zzd;
    }

    public final void zzb(String str) {
        SharedPreferences.Editor edit = this.zza.zzb().edit();
        edit.putString(this.zzb, str);
        edit.apply();
        this.zzd = str;
    }
}
