package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdh;

public final class zzke {
    final Context zza;
    String zzb;
    String zzc;
    String zzd;
    Boolean zze;
    long zzf;
    zzdh zzg;
    boolean zzh = true;
    final Long zzi;
    String zzj;

    public zzke(Context context, zzdh zzdh, Long l) {
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zza = applicationContext;
        this.zzi = l;
        if (zzdh != null) {
            this.zzg = zzdh;
            this.zzb = zzdh.zzf;
            this.zzc = zzdh.zze;
            this.zzd = zzdh.zzd;
            this.zzh = zzdh.zzc;
            this.zzf = zzdh.zzb;
            this.zzj = zzdh.zzh;
            Bundle bundle = zzdh.zzg;
            if (bundle != null) {
                this.zze = Boolean.valueOf(bundle.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
