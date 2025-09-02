package com.google.android.gms.measurement.internal;

import android.os.Bundle;

public final class zzhf {
    public final String zza;
    public final String zzb;
    public final long zzc;
    public final Bundle zzd;

    public zzhf(String str, String str2, Bundle bundle, long j) {
        this.zza = str;
        this.zzb = str2;
        this.zzd = bundle;
        this.zzc = j;
    }

    public static zzhf zzb(zzbh zzbh) {
        return new zzhf(zzbh.zza, zzbh.zzc, zzbh.zzb.zzc(), zzbh.zzd);
    }

    public final String toString() {
        String obj = this.zzd.toString();
        return "origin=" + this.zzb + ",name=" + this.zza + ",params=" + obj;
    }

    public final zzbh zza() {
        return new zzbh(this.zza, new zzbf(new Bundle(this.zzd)), this.zzb, this.zzc);
    }
}
