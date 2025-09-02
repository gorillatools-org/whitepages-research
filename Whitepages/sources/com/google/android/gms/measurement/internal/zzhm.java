package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.internal.Preconditions;

final class zzhm extends BroadcastReceiver {
    /* access modifiers changed from: private */
    public final zzpv zza;
    private boolean zzb;
    private boolean zzc;

    zzhm(zzpv zzpv) {
        Preconditions.checkNotNull(zzpv);
        this.zza = zzpv;
    }

    public final void onReceive(Context context, Intent intent) {
        zzpv zzpv = this.zza;
        zzpv.zzM();
        String action = intent.getAction();
        zzpv.zzaW().zzj().zzb("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean zzd = zzpv.zzp().zzd();
            if (this.zzc != zzd) {
                this.zzc = zzd;
                zzpv.zzaX().zzq(new zzhl(this, zzd));
                return;
            }
            return;
        }
        zzpv.zzaW().zzk().zzb("NetworkBroadcastReceiver received unknown action", action);
    }

    public final void zzb() {
        zzpv zzpv = this.zza;
        zzpv.zzM();
        zzpv.zzaX().zzg();
        if (!this.zzb) {
            zzpv.zzaT().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.zzc = zzpv.zzp().zzd();
            zzpv.zzaW().zzj().zzb("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzc));
            this.zzb = true;
        }
    }

    public final void zzc() {
        zzpv zzpv = this.zza;
        zzpv.zzM();
        zzpv.zzaX().zzg();
        zzpv.zzaX().zzg();
        if (this.zzb) {
            zzpv.zzaW().zzj().zza("Unregistering connectivity change receiver");
            this.zzb = false;
            this.zzc = false;
            try {
                zzpv.zzaT().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                this.zza.zzaW().zze().zzb("Failed to unregister the network broadcast receiver", e);
            }
        }
    }
}
