package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.internal.measurement.zzbq;
import com.google.android.gms.internal.measurement.zzbr;

public final class zzhv implements ServiceConnection {
    final /* synthetic */ zzhw zza;
    /* access modifiers changed from: private */
    public final String zzb;

    zzhv(zzhw zzhw, String str) {
        this.zza = zzhw;
        this.zzb = str;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder != null) {
            try {
                zzbr zzb2 = zzbq.zzb(iBinder);
                if (zzb2 == null) {
                    this.zza.zza.zzaW().zzk().zza("Install Referrer Service implementation was not found");
                    return;
                }
                zzio zzio = this.zza.zza;
                zzio.zzaW().zzj().zza("Install Referrer Service connected");
                zzio.zzaX().zzq(new zzhu(this, zzb2, this));
            } catch (RuntimeException e) {
                this.zza.zza.zzaW().zzk().zzb("Exception occurred while calling Install Referrer API", e);
            }
        } else {
            this.zza.zza.zzaW().zzk().zza("Install Referrer connection returned with null binder");
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.zza.zza.zzaW().zzj().zza("Install Referrer Service disconnected");
    }
}
