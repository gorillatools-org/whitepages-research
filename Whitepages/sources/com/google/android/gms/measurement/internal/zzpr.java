package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzhm;
import com.google.android.gms.internal.measurement.zzhx;
import java.util.ArrayList;
import java.util.List;

final class zzpr {
    zzhx zza;
    List zzb;
    List zzc;
    long zzd;
    final /* synthetic */ zzpv zze;

    /* synthetic */ zzpr(zzpv zzpv, zzpu zzpu) {
        this.zze = zzpv;
    }

    private static final long zzb(zzhm zzhm) {
        return ((zzhm.zzd() / 1000) / 60) / 60;
    }

    public final boolean zza(long j, zzhm zzhm) {
        Preconditions.checkNotNull(zzhm);
        if (this.zzc == null) {
            this.zzc = new ArrayList();
        }
        if (this.zzb == null) {
            this.zzb = new ArrayList();
        }
        if (!this.zzc.isEmpty() && zzb((zzhm) this.zzc.get(0)) != zzb(zzhm)) {
            return false;
        }
        long zzcf = this.zzd + ((long) zzhm.zzcf());
        zzpv zzpv = this.zze;
        if (!zzpv.zzi().zzx((String) null, zzgi.zzbq)) {
            zzpv.zzi();
            if (zzcf >= ((long) zzam.zzG())) {
                return false;
            }
        } else if (!this.zzc.isEmpty()) {
            zzpv.zzi();
            if (zzcf >= ((long) zzam.zzG())) {
                return false;
            }
        }
        this.zzd = zzcf;
        this.zzc.add(zzhm);
        this.zzb.add(Long.valueOf(j));
        int size = this.zzc.size();
        zzpv.zzi();
        if (size >= Math.max(1, ((Integer) zzgi.zzj.zza((Object) null)).intValue())) {
            return false;
        }
        return true;
    }
}
