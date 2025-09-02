package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

public final /* synthetic */ class zzob implements Runnable {
    public final /* synthetic */ zzog zza;
    public final /* synthetic */ JobParameters zzb;

    public /* synthetic */ zzob(zzog zzog, JobParameters jobParameters) {
        this.zza = zzog;
        this.zzb = jobParameters;
    }

    public final void run() {
        zzog.zzc(this.zza, this.zzb);
    }
}
