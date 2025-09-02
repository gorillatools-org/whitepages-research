package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzde;

final class zzq implements zzkc {
    public final zzde zza;
    final /* synthetic */ AppMeasurementDynamiteService zzb;

    zzq(AppMeasurementDynamiteService appMeasurementDynamiteService, zzde zzde) {
        this.zzb = appMeasurementDynamiteService;
        this.zza = zzde;
    }

    public final void onEvent(String str, String str2, Bundle bundle, long j) {
        try {
            this.zza.zzf(str, str2, bundle, j);
        } catch (RemoteException e) {
            zzio zzio = this.zzb.zza;
            if (zzio != null) {
                zzio.zzaW().zzk().zzb("Event listener threw exception", e);
            }
        }
    }
}
