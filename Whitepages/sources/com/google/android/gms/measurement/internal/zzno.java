package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcy;
import java.util.ArrayList;

final class zzno implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzr zzc;
    final /* synthetic */ zzcy zzd;
    final /* synthetic */ zzny zze;

    zzno(zzny zzny, String str, String str2, zzr zzr, zzcy zzcy) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzr;
        this.zzd = zzcy;
        this.zze = zzny;
    }

    public final void run() {
        zzcy zzcy;
        zzqf zzw;
        ArrayList arrayList = new ArrayList();
        try {
            zzny zzny = this.zze;
            zzgl zzi = zzny.zzb;
            if (zzi == null) {
                zzio zzio = zzny.zzu;
                zzio.zzaW().zze().zzc("Failed to get conditional properties; not connected to service", this.zza, this.zzb);
                zzw = zzio.zzw();
                zzcy = this.zzd;
                zzw.zzU(zzcy, arrayList);
            }
            zzr zzr = this.zzc;
            Preconditions.checkNotNull(zzr);
            arrayList = zzqf.zzK(zzi.zzi(this.zza, this.zzb, zzr));
            zzny.zzag();
            zzny zzny2 = this.zze;
            zzcy = this.zzd;
            zzw = zzny2.zzu.zzw();
            zzw.zzU(zzcy, arrayList);
        } catch (RemoteException e) {
            this.zze.zzu.zzaW().zze().zzd("Failed to get conditional properties; remote exception", this.zza, this.zzb, e);
        } catch (Throwable th) {
            zzny zzny3 = this.zze;
            zzny3.zzu.zzw().zzU(this.zzd, arrayList);
            throw th;
        }
    }
}
