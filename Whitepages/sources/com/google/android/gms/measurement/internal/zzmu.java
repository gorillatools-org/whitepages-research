package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcy;
import java.util.List;

final class zzmu implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzr zzc;
    final /* synthetic */ boolean zzd;
    final /* synthetic */ zzcy zze;
    final /* synthetic */ zzny zzf;

    zzmu(zzny zzny, String str, String str2, zzr zzr, boolean z, zzcy zzcy) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzr;
        this.zzd = z;
        this.zze = zzcy;
        this.zzf = zzny;
    }

    public final void run() {
        Throwable th;
        Bundle bundle;
        RemoteException e;
        Bundle bundle2 = new Bundle();
        try {
            zzny zzny = this.zzf;
            zzgl zzi = zzny.zzb;
            if (zzi == null) {
                zzio zzio = zzny.zzu;
                zzio.zzaW().zze().zzc("Failed to get user properties; not connected to service", this.zza, this.zzb);
                zzio.zzw().zzV(this.zze, bundle2);
                return;
            }
            zzr zzr = this.zzc;
            Preconditions.checkNotNull(zzr);
            List<zzqb> zzk = zzi.zzk(this.zza, this.zzb, this.zzd, zzr);
            int i = zzqf.zza;
            bundle = new Bundle();
            if (zzk != null) {
                for (zzqb zzqb : zzk) {
                    String str = zzqb.zze;
                    if (str != null) {
                        bundle.putString(zzqb.zzb, str);
                    } else {
                        Long l = zzqb.zzd;
                        if (l != null) {
                            bundle.putLong(zzqb.zzb, l.longValue());
                        } else {
                            Double d = zzqb.zzg;
                            if (d != null) {
                                bundle.putDouble(zzqb.zzb, d.doubleValue());
                            }
                        }
                    }
                }
            }
            try {
                zzny.zzag();
                zzio zzio2 = zzny.zzu;
                zzio2.zzw().zzV(this.zze, bundle);
            } catch (RemoteException e2) {
                e = e2;
                try {
                    this.zzf.zzu.zzaW().zze().zzc("Failed to get user properties; remote exception", this.zza, e);
                    zzny zzny2 = this.zzf;
                    zzny2.zzu.zzw().zzV(this.zze, bundle);
                } catch (Throwable th2) {
                    th = th2;
                    bundle2 = bundle;
                    zzny zzny3 = this.zzf;
                    zzny3.zzu.zzw().zzV(this.zze, bundle2);
                    throw th;
                }
            }
        } catch (RemoteException e3) {
            bundle = bundle2;
            e = e3;
            this.zzf.zzu.zzaW().zze().zzc("Failed to get user properties; remote exception", this.zza, e);
            zzny zzny22 = this.zzf;
            zzny22.zzu.zzw().zzV(this.zze, bundle);
        } catch (Throwable th3) {
            th = th3;
            zzny zzny32 = this.zzf;
            zzny32.zzu.zzw().zzV(this.zze, bundle2);
            throw th;
        }
    }
}
