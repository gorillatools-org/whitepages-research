package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;

final class zzlg implements Runnable {
    final /* synthetic */ Bundle zza;
    final /* synthetic */ zzlw zzb;

    zzlg(zzlw zzlw, Bundle bundle) {
        this.zza = bundle;
        this.zzb = zzlw;
    }

    public final void run() {
        zzlw zzlw = this.zzb;
        zzlw.zzg();
        zzlw.zza();
        Bundle bundle = this.zza;
        Preconditions.checkNotNull(bundle);
        String checkNotEmpty = Preconditions.checkNotEmpty(bundle.getString("name"));
        if (!zzlw.zzu.zzJ()) {
            zzlw.zzu.zzaW().zzj().zza("Conditional property not cleared since app measurement is disabled");
            return;
        }
        zzqb zzqb = new zzqb(checkNotEmpty, 0, (Object) null, "");
        try {
            zzai zzai = r4;
            zzai zzai2 = new zzai(bundle.getString("app_id"), "", zzqb, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), bundle.getBoolean("active"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), (zzbh) null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), (zzbh) null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzlw.zzu.zzw().zzC(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), "", bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), true, true));
            zzlw.zzu.zzu().zzQ(zzai);
        } catch (IllegalArgumentException unused) {
        }
    }
}
