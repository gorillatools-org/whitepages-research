package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import com.google.android.gms.internal.measurement.zzcj;
import com.google.android.gms.internal.measurement.zzck;
import com.salesforce.marketingcloud.UrlHandler;

public final class zzoy extends zzpg {
    private final AlarmManager zza = ((AlarmManager) this.zzu.zzaT().getSystemService("alarm"));
    private zzaz zzb;
    private Integer zzc;

    protected zzoy(zzpv zzpv) {
        super(zzpv);
    }

    private final int zzf() {
        if (this.zzc == null) {
            this.zzc = Integer.valueOf("measurement".concat(String.valueOf(this.zzu.zzaT().getPackageName())).hashCode());
        }
        return this.zzc.intValue();
    }

    private final PendingIntent zzh() {
        Context zzaT = this.zzu.zzaT();
        return PendingIntent.getBroadcast(zzaT, 0, new Intent().setClassName(zzaT, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), zzcj.zza);
    }

    private final zzaz zzi() {
        if (this.zzb == null) {
            this.zzb = new zzox(this, this.zzg.zzt());
        }
        return this.zzb;
    }

    @TargetApi(24)
    private final void zzj() {
        JobScheduler jobScheduler = (JobScheduler) this.zzu.zzaT().getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.cancel(zzf());
        }
    }

    public final void zza() {
        zzav();
        this.zzu.zzaW().zzj().zza("Unscheduling upload");
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzh());
        }
        zzi().zzb();
        zzj();
    }

    /* access modifiers changed from: protected */
    public final boolean zzb() {
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzh());
        }
        zzj();
        return false;
    }

    public final void zzd(long j) {
        zzav();
        zzio zzio = this.zzu;
        zzio.zzaV();
        Context zzaT = zzio.zzaT();
        if (!zzqf.zzar(zzaT)) {
            zzio.zzaW().zzd().zza("Receiver not registered/enabled");
        }
        if (!zzqf.zzat(zzaT, false)) {
            zzio.zzaW().zzd().zza("Service not registered/enabled");
        }
        zza();
        zzio.zzaW().zzj().zzb("Scheduling upload, millis", Long.valueOf(j));
        zzio.zzaU().elapsedRealtime();
        zzio.zzf();
        if (j < Math.max(0, ((Long) zzgi.zzK.zza((Object) null)).longValue()) && !zzi().zze()) {
            zzi().zzd(j);
        }
        zzio.zzaV();
        Context zzaT2 = zzio.zzaT();
        ComponentName componentName = new ComponentName(zzaT2, "com.google.android.gms.measurement.AppMeasurementJobService");
        int zzf = zzf();
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString(UrlHandler.ACTION, "com.google.android.gms.measurement.UPLOAD");
        zzck.zza(zzaT2, new JobInfo.Builder(zzf, componentName).setMinimumLatency(j).setOverrideDeadline(j + j).setExtras(persistableBundle).build(), "com.google.android.gms", "UploadAlarm");
    }
}
