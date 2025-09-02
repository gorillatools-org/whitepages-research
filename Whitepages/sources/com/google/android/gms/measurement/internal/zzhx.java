package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdh;

public final class zzhx {
    private final zza zza;

    public interface zza {
        void doStartService(Context context, Intent intent);
    }

    public zzhx(zza zza2) {
        Preconditions.checkNotNull(zza2);
        this.zza = zza2;
    }

    public final void zza(Context context, Intent intent) {
        zzio zzp = zzio.zzp(context, (zzdh) null, (Long) null);
        zzhe zzaW = zzp.zzaW();
        if (intent == null) {
            zzaW.zzk().zza("Receiver called with null intent");
            return;
        }
        zzp.zzaV();
        String action = intent.getAction();
        zzaW.zzj().zzb("Local receiver got", action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            zzaW.zzj().zza("Starting wakeful intent.");
            this.zza.doStartService(context, className);
        } else if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
            zzaW.zzk().zza("Install Referrer Broadcasts are deprecated");
        }
    }
}
