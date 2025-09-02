package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzcy;

final class zznh implements Runnable {
    final /* synthetic */ zzbh zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzcy zzc;
    final /* synthetic */ zzny zzd;

    zznh(zzny zzny, zzbh zzbh, String str, zzcy zzcy) {
        this.zza = zzbh;
        this.zzb = str;
        this.zzc = zzcy;
        this.zzd = zzny;
    }

    public final void run() {
        zzcy zzcy;
        zzqf zzw;
        byte[] bArr = null;
        try {
            zzny zzny = this.zzd;
            zzgl zzi = zzny.zzb;
            if (zzi == null) {
                zzio zzio = zzny.zzu;
                zzio.zzaW().zze().zza("Discarding data. Failed to send event to service to bundle");
                zzw = zzio.zzw();
                zzcy = this.zzc;
                zzw.zzW(zzcy, bArr);
            }
            bArr = zzi.zzD(this.zza, this.zzb);
            zzny.zzag();
            zzny zzny2 = this.zzd;
            zzcy = this.zzc;
            zzw = zzny2.zzu.zzw();
            zzw.zzW(zzcy, bArr);
        } catch (RemoteException e) {
            this.zzd.zzu.zzaW().zze().zzb("Failed to send event to the service to bundle", e);
        } catch (Throwable th) {
            zzny zzny3 = this.zzd;
            zzny3.zzu.zzw().zzW(this.zzc, bArr);
            throw th;
        }
    }
}
