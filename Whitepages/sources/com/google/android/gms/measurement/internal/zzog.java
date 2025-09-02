package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdh;
import com.google.android.gms.internal.measurement.zzff;
import com.salesforce.marketingcloud.UrlHandler;
import java.util.Objects;

public final class zzog {
    private final Context zza;

    public zzog(Context context) {
        Preconditions.checkNotNull(context);
        this.zza = context;
    }

    public static /* synthetic */ void zzc(zzog zzog, JobParameters jobParameters) {
        Log.v("FA", "[sgtm] AppMeasurementJobService processed last Scion upload request.");
        ((zzof) zzog.zza).zzb(jobParameters, false);
    }

    public static /* synthetic */ void zzd(zzog zzog, zzhe zzhe, JobParameters jobParameters) {
        zzhe.zzj().zza("AppMeasurementJobService processed last upload request.");
        ((zzof) zzog.zza).zzb(jobParameters, false);
    }

    public static /* synthetic */ void zze(zzog zzog, int i, zzhe zzhe, Intent intent) {
        Context context = zzog.zza;
        zzof zzof = (zzof) context;
        if (zzof.zzc(i)) {
            zzhe.zzj().zzb("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            zzio.zzp(context, (zzdh) null, (Long) null).zzaW().zzj().zza("Completed wakeful intent.");
            zzof.zza(intent);
        }
    }

    public static final void zzi(Intent intent) {
        if (intent == null) {
            Log.e("FA", "onRebind called with null intent");
        } else {
            Log.v("FA", "onRebind called. action: ".concat(String.valueOf(intent.getAction())));
        }
    }

    public static final boolean zzj(Intent intent) {
        if (intent == null) {
            Log.e("FA", "onUnbind called with null intent");
            return true;
        }
        Log.v("FA", "onUnbind called for intent. action: ".concat(String.valueOf(intent.getAction())));
        return true;
    }

    private final void zzk(zzpv zzpv, Runnable runnable) {
        zzpv.zzaX().zzq(new zzoe(this, zzpv, runnable));
    }

    public final int zza(Intent intent, int i, int i2) {
        if (intent == null) {
            Log.w("FA", "AppMeasurementService started with null intent");
            return 2;
        }
        Context context = this.zza;
        zzio zzp = zzio.zzp(context, (zzdh) null, (Long) null);
        zzhe zzaW = zzp.zzaW();
        String action = intent.getAction();
        zzp.zzaV();
        zzaW.zzj().zzc("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zzk(zzpv.zzz(context), new zzoc(this, i2, zzaW, intent));
        }
        return 2;
    }

    public final IBinder zzb(Intent intent) {
        if (intent == null) {
            Log.e("FA", "onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzjp(zzpv.zzz(this.zza), (String) null);
        }
        Log.w("FA", "onBind received unknown action: ".concat(String.valueOf(action)));
        return null;
    }

    public final void zzf() {
        Log.v("FA", this.zza.getClass().getSimpleName().concat(" is starting up."));
    }

    public final void zzg() {
        Log.v("FA", this.zza.getClass().getSimpleName().concat(" is shutting down."));
    }

    @TargetApi(24)
    public final boolean zzh(JobParameters jobParameters) {
        String string = jobParameters.getExtras().getString(UrlHandler.ACTION);
        Log.v("FA", "onStartJob received action: ".concat(String.valueOf(string)));
        if (Objects.equals(string, "com.google.android.gms.measurement.UPLOAD")) {
            zzpv zzz = zzpv.zzz(this.zza);
            zzhe zzaW = zzz.zzaW();
            zzz.zzaV();
            zzaW.zzj().zzb("Local AppMeasurementJobService called. action", (String) Preconditions.checkNotNull(string));
            zzk(zzz, new zzod(this, zzaW, jobParameters));
        }
        if (!Objects.equals(string, "com.google.android.gms.measurement.SCION_UPLOAD")) {
            return true;
        }
        String str = (String) Preconditions.checkNotNull(string);
        zzff zzg = zzff.zzg(this.zza, (String) null, (String) null, (String) null, (Bundle) null);
        if (!((Boolean) zzgi.zzaT.zza((Object) null)).booleanValue()) {
            return true;
        }
        zzg.zzE(new zzob(this, jobParameters));
        return true;
    }
}
