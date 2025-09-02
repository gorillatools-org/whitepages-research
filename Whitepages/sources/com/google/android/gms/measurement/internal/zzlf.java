package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;

final class zzlf implements Runnable {
    final /* synthetic */ Bundle zza;
    final /* synthetic */ zzlw zzb;

    zzlf(zzlw zzlw, Bundle bundle) {
        this.zza = bundle;
        this.zzb = zzlw;
    }

    public final void run() {
        zzlw zzlw = this.zzb;
        zzlw.zzg();
        zzlw.zza();
        Bundle bundle = this.zza;
        Preconditions.checkNotNull(bundle);
        String string = bundle.getString("name");
        String string2 = bundle.getString("origin");
        Preconditions.checkNotEmpty(string);
        Preconditions.checkNotEmpty(string2);
        Preconditions.checkNotNull(bundle.get("value"));
        if (!zzlw.zzu.zzJ()) {
            zzlw.zzu.zzaW().zzj().zza("Conditional property not set since app measurement is disabled");
            return;
        }
        zzqb zzqb = new zzqb(string, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP), bundle.get("value"), string2);
        try {
            zzio zzio = zzlw.zzu;
            zzbh zzC = zzio.zzw().zzC(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS), string2, 0, true, true);
            zzbh zzC2 = zzio.zzw().zzC(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS), string2, 0, true, true);
            zzbh zzC3 = zzio.zzw().zzC(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), string2, 0, true, true);
            zzlw.zzu.zzu().zzQ(new zzai(bundle.getString("app_id"), string2, zzqb, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), false, bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), zzC2, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), zzC, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzC3));
        } catch (IllegalArgumentException unused) {
        }
    }
}
