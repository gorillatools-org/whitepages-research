package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzbr;
import com.google.firebase.messaging.Constants;

final class zzhu implements Runnable {
    final /* synthetic */ zzbr zza;
    final /* synthetic */ ServiceConnection zzb;
    final /* synthetic */ zzhv zzc;

    zzhu(zzhv zzhv, zzbr zzbr, ServiceConnection serviceConnection) {
        this.zza = zzbr;
        this.zzb = serviceConnection;
        this.zzc = zzhv;
    }

    public final void run() {
        zzhv zzhv = this.zzc;
        String zza2 = zzhv.zzb;
        zzhw zzhw = zzhv.zza;
        zzio zzio = zzhw.zza;
        zzio.zzaX().zzg();
        Bundle bundle = new Bundle();
        bundle.putString("package_name", zza2);
        Bundle bundle2 = null;
        try {
            Bundle zze = this.zza.zze(bundle);
            if (zze == null) {
                zzio.zzaW().zze().zza("Install Referrer Service returned a null response");
            } else {
                bundle2 = zze;
            }
        } catch (Exception e) {
            zzhw.zza.zzaW().zze().zzb("Exception occurred while retrieving the Install Referrer", e.getMessage());
        }
        zzio zzio2 = zzhw.zza;
        zzio2.zzaX().zzg();
        zzio.zzP();
        if (bundle2 != null) {
            long j = bundle2.getLong("install_begin_timestamp_seconds", 0) * 1000;
            if (j == 0) {
                zzio2.zzaW().zzk().zza("Service response is missing Install Referrer install timestamp");
            } else {
                String string = bundle2.getString("install_referrer");
                if (string == null || string.isEmpty()) {
                    zzio2.zzaW().zze().zza("No referrer defined in Install Referrer response");
                } else {
                    zzio2.zzaW().zzj().zzb("InstallReferrer API result", string);
                    Bundle zzu = zzio2.zzw().zzu(Uri.parse("?".concat(string)));
                    if (zzu == null) {
                        zzio2.zzaW().zze().zza("No campaign params defined in Install Referrer result");
                    } else {
                        if (zzu.containsKey("gclid") || zzu.containsKey("gbraid")) {
                            long j2 = bundle2.getLong("referrer_click_timestamp_server_seconds", 0) * 1000;
                            if (j2 > 0) {
                                zzu.putLong("click_timestamp", j2);
                            }
                        }
                        if (j == zzio2.zzm().zzd.zza()) {
                            zzio2.zzaW().zzj().zza("Logging Install Referrer campaign from module while it may have already been logged.");
                        }
                        if (zzio2.zzJ()) {
                            zzio2.zzm().zzd.zzb(j);
                            zzio2.zzaW().zzj().zzb("Logging Install Referrer campaign from gmscore with ", "referrer API v2");
                            zzu.putString("_cis", "referrer API v2");
                            zzio2.zzq().zzQ("auto", Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, zzu, zza2);
                        }
                    }
                }
            }
        }
        ConnectionTracker.getInstance().unbindService(zzio2.zzaT(), this.zzb);
    }
}
