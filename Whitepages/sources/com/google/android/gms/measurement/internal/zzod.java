package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

public final /* synthetic */ class zzod implements Runnable {
    public final /* synthetic */ zzog zza;
    public final /* synthetic */ zzhe zzb;
    public final /* synthetic */ JobParameters zzc;

    public /* synthetic */ zzod(zzog zzog, zzhe zzhe, JobParameters jobParameters) {
        this.zza = zzog;
        this.zzb = zzhe;
        this.zzc = jobParameters;
    }

    public final void run() {
        zzog.zzd(this.zza, this.zzb, this.zzc);
    }
}
