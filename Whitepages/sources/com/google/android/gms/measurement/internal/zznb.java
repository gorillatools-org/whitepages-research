package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcy;

final class zznb implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zzcy zzb;
    final /* synthetic */ zzny zzc;

    zznb(zzny zzny, zzr zzr, zzcy zzcy) {
        this.zza = zzr;
        this.zzb = zzcy;
        this.zzc = zzny;
    }

    public final void run() {
        zzcy zzcy;
        zzqf zzw;
        String str = null;
        try {
            zzny zzny = this.zzc;
            zzio zzio = zzny.zzu;
            if (!zzio.zzm().zzh().zzr(zzjw.ANALYTICS_STORAGE)) {
                zzio.zzaW().zzl().zza("Analytics storage consent denied; will not get app instance id");
                zzny.zzu.zzq().zzac((String) null);
                zzio.zzm().zze.zzb((String) null);
            } else {
                zzgl zzi = zzny.zzb;
                if (zzi == null) {
                    zzio.zzaW().zze().zza("Failed to get app instance id");
                } else {
                    zzr zzr = this.zza;
                    Preconditions.checkNotNull(zzr);
                    str = zzi.zzf(zzr);
                    if (str != null) {
                        zzny.zzu.zzq().zzac(str);
                        zzio.zzm().zze.zzb(str);
                    }
                    zzny.zzag();
                    zzny zzny2 = this.zzc;
                    zzcy = this.zzb;
                    zzw = zzny2.zzu.zzw();
                    zzw.zzZ(zzcy, str);
                }
            }
            zzw = zzio.zzw();
            zzcy = this.zzb;
        } catch (RemoteException e) {
            this.zzc.zzu.zzaW().zze().zzb("Failed to get app instance id", e);
        } catch (Throwable th) {
            zzny zzny3 = this.zzc;
            zzny3.zzu.zzw().zzZ(this.zzb, (String) null);
            throw th;
        }
        zzw.zzZ(zzcy, str);
    }
}
