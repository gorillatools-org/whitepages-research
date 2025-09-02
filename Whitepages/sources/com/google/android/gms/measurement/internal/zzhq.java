package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;

public final class zzhq {
    final String zza;
    final /* synthetic */ zzht zzb;
    private final String zzc;
    private final String zzd;
    private final long zze;

    /* synthetic */ zzhq(zzht zzht, String str, long j, zzhs zzhs) {
        this.zzb = zzht;
        Preconditions.checkNotEmpty("health_monitor");
        Preconditions.checkArgument(j > 0);
        this.zza = "health_monitor:start";
        this.zzc = "health_monitor:count";
        this.zzd = "health_monitor:value";
        this.zze = j;
    }

    private final long zzc() {
        return this.zzb.zzb().getLong(this.zza, 0);
    }

    private final void zzd() {
        zzht zzht = this.zzb;
        zzht.zzg();
        long currentTimeMillis = zzht.zzu.zzaU().currentTimeMillis();
        SharedPreferences.Editor edit = zzht.zzb().edit();
        edit.remove(this.zzc);
        edit.remove(this.zzd);
        edit.putLong(this.zza, currentTimeMillis);
        edit.apply();
    }

    public final Pair zza() {
        long j;
        zzht zzht = this.zzb;
        zzht.zzg();
        zzht.zzg();
        long zzc2 = zzc();
        if (zzc2 == 0) {
            zzd();
            j = 0;
        } else {
            j = Math.abs(zzc2 - zzht.zzu.zzaU().currentTimeMillis());
        }
        long j2 = this.zze;
        if (j < j2) {
            return null;
        }
        if (j > j2 + j2) {
            zzd();
            return null;
        }
        String string = zzht.zzb().getString(this.zzd, (String) null);
        long j3 = zzht.zzb().getLong(this.zzc, 0);
        zzd();
        if (string == null || j3 <= 0) {
            return zzht.zza;
        }
        return new Pair(string, Long.valueOf(j3));
    }

    public final void zzb(String str, long j) {
        zzht zzht = this.zzb;
        zzht.zzg();
        if (zzc() == 0) {
            zzd();
        }
        if (str == null) {
            str = "";
        }
        SharedPreferences zzb2 = zzht.zzb();
        String str2 = this.zzc;
        long j2 = zzb2.getLong(str2, 0);
        if (j2 <= 0) {
            SharedPreferences.Editor edit = zzht.zzb().edit();
            edit.putString(this.zzd, str);
            edit.putLong(str2, 1);
            edit.apply();
            return;
        }
        long j3 = j2 + 1;
        SharedPreferences.Editor edit2 = zzht.zzb().edit();
        if ((zzht.zzu.zzw().zzJ().nextLong() & Long.MAX_VALUE) < Long.MAX_VALUE / j3) {
            edit2.putString(this.zzd, str);
        }
        edit2.putLong(str2, j3);
        edit2.apply();
    }
}
