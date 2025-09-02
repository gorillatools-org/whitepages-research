package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.PersistableBundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzih;
import com.salesforce.marketingcloud.UrlHandler;

public final class zzmd extends zzg {
    private JobScheduler zza;

    public zzmd(zzio zzio) {
        super(zzio);
    }

    /* access modifiers changed from: protected */
    @TargetApi(24)
    public final void zzd() {
        this.zza = (JobScheduler) this.zzu.zzaT().getSystemService("jobscheduler");
    }

    /* access modifiers changed from: protected */
    public final boolean zzf() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public final int zzh() {
        return "measurement-client".concat(String.valueOf(this.zzu.zzaT().getPackageName())).hashCode();
    }

    /* access modifiers changed from: package-private */
    public final zzih zzi() {
        zza();
        zzg();
        zzio zzio = this.zzu;
        if (!zzio.zzf().zzx((String) null, zzgi.zzaR)) {
            return zzih.CLIENT_FLAG_OFF;
        }
        if (this.zza == null) {
            return zzih.MISSING_JOB_SCHEDULER;
        }
        if (!zzio.zzf().zzE()) {
            return zzih.NOT_ENABLED_IN_MANIFEST;
        }
        zzio zzio2 = this.zzu;
        if (zzio2.zzh().zzj() < 119000) {
            return zzih.SDK_TOO_OLD;
        }
        if (!zzqf.zzas(zzio.zzaT(), "com.google.android.gms.measurement.AppMeasurementJobService")) {
            return zzih.MEASUREMENT_SERVICE_NOT_ENABLED;
        }
        return !zzio2.zzu().zzad() ? zzih.NON_PLAY_MODE : zzih.CLIENT_UPLOAD_ELIGIBLE;
    }

    @TargetApi(24)
    public final void zzj(long j) {
        String str;
        zza();
        zzg();
        JobScheduler jobScheduler = this.zza;
        if (jobScheduler == null || jobScheduler.getPendingJob(zzh()) == null) {
            zzih zzi = zzi();
            if (zzi == zzih.CLIENT_UPLOAD_ELIGIBLE) {
                zzio zzio = this.zzu;
                zzio.zzaW().zzj().zzb("[sgtm] Scheduling Scion upload, millis", Long.valueOf(j));
                PersistableBundle persistableBundle = new PersistableBundle();
                persistableBundle.putString(UrlHandler.ACTION, "com.google.android.gms.measurement.SCION_UPLOAD");
                int schedule = ((JobScheduler) Preconditions.checkNotNull(this.zza)).schedule(new JobInfo.Builder(zzh(), new ComponentName(zzio.zzaT(), "com.google.android.gms.measurement.AppMeasurementJobService")).setRequiredNetworkType(1).setMinimumLatency(j).setOverrideDeadline(j + j).setExtras(persistableBundle).build());
                zzhc zzj = zzio.zzaW().zzj();
                if (schedule == 1) {
                    str = "SUCCESS";
                } else {
                    str = "FAILURE";
                }
                zzj.zzb("[sgtm] Scion upload job scheduled with result", str);
                return;
            }
            this.zzu.zzaW().zzj().zzb("[sgtm] Not eligible for Scion upload", zzi.name());
            return;
        }
        this.zzu.zzaW().zzj().zza("[sgtm] There's an existing pending job, skip this schedule.");
    }
}
