package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzr;
import java.util.List;

final class zzid implements zzr {
    final /* synthetic */ zzif zza;

    zzid(zzif zzif) {
        this.zza = zzif;
    }

    public final void zza(int i, String str, List list, boolean z, boolean z2) {
        zzhc zzhc;
        int i2 = i - 1;
        if (i2 == 0) {
            zzhc = this.zza.zzu.zzaW().zzd();
        } else if (i2 != 1) {
            if (i2 == 3) {
                zzhc = this.zza.zzu.zzaW().zzj();
            } else if (i2 != 4) {
                zzhc = this.zza.zzu.zzaW().zzi();
            } else if (z) {
                zzhc = this.zza.zzu.zzaW().zzm();
            } else if (!z2) {
                zzhc = this.zza.zzu.zzaW().zzl();
            } else {
                zzhc = this.zza.zzu.zzaW().zzk();
            }
        } else if (z) {
            zzhc = this.zza.zzu.zzaW().zzh();
        } else if (!z2) {
            zzhc = this.zza.zzu.zzaW().zzf();
        } else {
            zzhc = this.zza.zzu.zzaW().zze();
        }
        int size = list.size();
        if (size == 1) {
            zzhc.zzb(str, list.get(0));
        } else if (size == 2) {
            zzhc.zzc(str, list.get(0), list.get(1));
        } else if (size != 3) {
            zzhc.zza(str);
        } else {
            zzhc.zzd(str, list.get(0), list.get(1), list.get(2));
        }
    }
}
