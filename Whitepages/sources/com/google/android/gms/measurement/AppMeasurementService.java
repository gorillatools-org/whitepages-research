package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.internal.zzof;
import com.google.android.gms.measurement.internal.zzog;

public final class AppMeasurementService extends Service implements zzof {
    private zzog zza;

    private final zzog zzd() {
        if (this.zza == null) {
            this.zza = new zzog(this);
        }
        return this.zza;
    }

    public IBinder onBind(Intent intent) {
        return zzd().zzb(intent);
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

    public int onStartCommand(Intent intent, int i, int i2) {
        zzd().zza(intent, i, i2);
        return 2;
    }

    public boolean onUnbind(Intent intent) {
        zzd();
        zzog.zzj(intent);
        return true;
    }

    public final void zza(Intent intent) {
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }

    public final void zzb(JobParameters jobParameters, boolean z) {
        throw new UnsupportedOperationException();
    }

    public final boolean zzc(int i) {
        return stopSelfResult(i);
    }
}
