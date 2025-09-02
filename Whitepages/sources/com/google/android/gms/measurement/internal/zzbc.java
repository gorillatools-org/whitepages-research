package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

public final class zzbc {
    final String zza;
    final String zzb;
    final String zzc;
    final long zzd;
    final long zze;
    final zzbf zzf;

    zzbc(zzio zzio, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzbf zzbf;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.zza = str2;
        this.zzb = str3;
        this.zzc = true == TextUtils.isEmpty(str) ? null : str;
        this.zzd = j;
        this.zze = j2;
        if (j2 != 0 && j2 > j) {
            zzio.zzaW().zzk().zzb("Event created with reverse previous/current timestamps. appId", zzhe.zzn(str2));
        }
        if (bundle == null || bundle.isEmpty()) {
            zzbf = new zzbf(new Bundle());
        } else {
            Bundle bundle2 = new Bundle(bundle);
            Iterator<String> it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    zzio.zzaW().zze().zza("Param name can't be null");
                    it.remove();
                } else {
                    Object zzD = zzio.zzw().zzD(next, bundle2.get(next));
                    if (zzD == null) {
                        zzio.zzaW().zzk().zzb("Param value can't be null", zzio.zzj().zze(next));
                        it.remove();
                    } else {
                        zzio.zzw().zzS(bundle2, next, zzD);
                    }
                }
            }
            zzbf = new zzbf(bundle2);
        }
        this.zzf = zzbf;
    }

    public final String toString() {
        String obj = this.zzf.toString();
        return "Event{appId='" + this.zza + "', name='" + this.zzb + "', params=" + obj + "}";
    }

    /* access modifiers changed from: package-private */
    public final zzbc zza(zzio zzio, long j) {
        return new zzbc(zzio, this.zzc, this.zza, this.zzb, this.zzd, j, this.zzf);
    }

    private zzbc(zzio zzio, String str, String str2, String str3, long j, long j2, zzbf zzbf) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzbf);
        this.zza = str2;
        this.zzb = str3;
        this.zzc = true == TextUtils.isEmpty(str) ? null : str;
        this.zzd = j;
        this.zze = j2;
        if (j2 != 0 && j2 > j) {
            zzio.zzaW().zzk().zzc("Event created with reverse previous/current timestamps. appId, name", zzhe.zzn(str2), zzhe.zzn(str3));
        }
        this.zzf = zzbf;
    }
}
