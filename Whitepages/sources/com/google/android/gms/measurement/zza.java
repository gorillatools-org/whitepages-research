package com.google.android.gms.measurement;

import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzio;
import com.google.android.gms.measurement.internal.zzkb;
import com.google.android.gms.measurement.internal.zzkc;
import com.google.android.gms.measurement.internal.zzlw;
import com.google.android.gms.measurement.internal.zzqb;
import java.util.List;
import java.util.Map;

final class zza extends zzc {
    private final zzio zza;
    private final zzlw zzb;

    public zza(zzio zzio) {
        super((zzd) null);
        Preconditions.checkNotNull(zzio);
        this.zza = zzio;
        this.zzb = zzio.zzq();
    }

    public final int zza(String str) {
        this.zzb.zzi(str);
        return 25;
    }

    public final long zzb() {
        return this.zza.zzw().zzs();
    }

    public final Boolean zzc() {
        return this.zzb.zzl();
    }

    public final Double zzd() {
        return this.zzb.zzm();
    }

    public final Integer zze() {
        return this.zzb.zzp();
    }

    public final Long zzf() {
        return this.zzb.zzq();
    }

    public final String zzh() {
        return this.zzb.zzr();
    }

    public final String zzi() {
        return this.zzb.zzs();
    }

    public final String zzj() {
        return this.zzb.zzt();
    }

    public final String zzk() {
        return this.zzb.zzr();
    }

    public final String zzl() {
        return this.zzb.zzu();
    }

    public final List zzm(String str, String str2) {
        return this.zzb.zzv(str, str2);
    }

    public final Map zzn(boolean z) {
        List<zzqb> zzw = this.zzb.zzw(z);
        ArrayMap arrayMap = new ArrayMap(zzw.size());
        for (zzqb zzqb : zzw) {
            Object zza2 = zzqb.zza();
            if (zza2 != null) {
                arrayMap.put(zzqb.zzb, zza2);
            }
        }
        return arrayMap;
    }

    public final Map zzo(String str, String str2, boolean z) {
        return this.zzb.zzx(str, str2, z);
    }

    public final void zzp(String str) {
        zzio zzio = this.zza;
        zzio.zzd().zzd(str, zzio.zzaU().elapsedRealtime());
    }

    public final void zzq(String str, String str2, Bundle bundle) {
        this.zza.zzq().zzJ(str, str2, bundle);
    }

    public final void zzr(String str) {
        zzio zzio = this.zza;
        zzio.zzd().zze(str, zzio.zzaU().elapsedRealtime());
    }

    public final void zzs(String str, String str2, Bundle bundle) {
        this.zzb.zzO(str, str2, bundle);
    }

    public final void zzt(String str, String str2, Bundle bundle, long j) {
        this.zzb.zzP(str, str2, bundle, true, false, j);
    }

    public final void zzu(zzkc zzkc) {
        this.zzb.zzV(zzkc);
    }

    public final void zzv(Bundle bundle) {
        this.zzb.zzad(bundle);
    }

    public final void zzw(zzkb zzkb) {
        this.zzb.zzah(zzkb);
    }

    public final void zzx(zzkc zzkc) {
        this.zzb.zzao(zzkc);
    }

    public final Object zzg(int i) {
        if (i == 0) {
            return this.zzb.zzu();
        }
        if (i == 1) {
            return this.zzb.zzq();
        }
        if (i == 2) {
            return this.zzb.zzm();
        }
        if (i != 3) {
            return this.zzb.zzl();
        }
        return this.zzb.zzp();
    }
}
