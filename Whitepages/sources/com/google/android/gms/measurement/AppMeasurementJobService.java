package com.google.android.gms.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import com.google.android.gms.measurement.internal.zzof;
import com.google.android.gms.measurement.internal.zzog;

@TargetApi(24)
public final class AppMeasurementJobService extends JobService implements zzof {
    private zzog zza;

    private final zzog zzd() {
        if (this.zza == null) {
            this.zza = new zzog(this);
        }
        return this.zza;
    }

    public void onCreate() {
        super.onCreate();
        zzd().zzf();
    }

    public void onDestroy() {
        zzd().zzg();
        super.onDestroy();
    }

    public void onRebind(Intent intent) {
        zzd();
        zzog.zzi(intent);
    }

    public boolean onStartJob(JobParameters jobParameters) {
        zzd().zzh(jobParameters);
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public boolean onUnbind(Intent intent) {
        zzd();
        zzog.zzj(intent);
        return true;
    }

    public final void zza(Intent intent) {
    }

    @TargetApi(24)
    public final void zzb(JobParameters jobParameters, boolean z) {
        jobFinished(jobParameters, false);
    }

    public final boolean zzc(int i) {
        throw new UnsupportedOperationException();
    }
}
