package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;

public final class zzd extends zzf {
    private final Map zza = new ArrayMap();
    private final Map zzb = new ArrayMap();
    private long zzc;

    public zzd(zzio zzio) {
        super(zzio);
    }

    static /* synthetic */ void zza(zzd zzd, String str, long j) {
        zzd.zzg();
        Preconditions.checkNotEmpty(str);
        Map map = zzd.zzb;
        if (map.isEmpty()) {
            zzd.zzc = j;
        }
        Integer num = (Integer) map.get(str);
        if (num != null) {
            map.put(str, Integer.valueOf(num.intValue() + 1));
        } else if (map.size() >= 100) {
            zzd.zzu.zzaW().zzk().zza("Too many ads visible");
        } else {
            map.put(str, 1);
            zzd.zza.put(str, Long.valueOf(j));
        }
    }

    static /* synthetic */ void zzb(zzd zzd, String str, long j) {
        zzd.zzg();
        Preconditions.checkNotEmpty(str);
        Map map = zzd.zzb;
        Integer num = (Integer) map.get(str);
        if (num != null) {
            zzmh zzj = zzd.zzu.zzt().zzj(false);
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                map.remove(str);
                Map map2 = zzd.zza;
                Long l = (Long) map2.get(str);
                if (l == null) {
                    zzd.zzu.zzaW().zze().zza("First ad unit exposure time was never set");
                } else {
                    map2.remove(str);
                    zzd.zzi(str, j - l.longValue(), zzj);
                }
                if (map.isEmpty()) {
                    long j2 = zzd.zzc;
                    if (j2 == 0) {
                        zzd.zzu.zzaW().zze().zza("First ad exposure time was never set");
                        return;
                    }
                    zzd.zzh(j - j2, zzj);
                    zzd.zzc = 0;
                    return;
                }
                return;
            }
            map.put(str, Integer.valueOf(intValue));
            return;
        }
        zzd.zzu.zzaW().zze().zzb("Call to endAdUnitExposure for unknown ad unit id", str);
    }

    private final void zzh(long j, zzmh zzmh) {
        if (zzmh == null) {
            this.zzu.zzaW().zzj().zza("Not logging ad exposure. No active activity");
        } else if (j < 1000) {
            this.zzu.zzaW().zzj().zzb("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putLong("_xt", j);
            zzqf.zzN(zzmh, bundle, true);
            this.zzu.zzq().zzR("am", "_xa", bundle);
        }
    }

    private final void zzi(String str, long j, zzmh zzmh) {
        if (zzmh == null) {
            this.zzu.zzaW().zzj().zza("Not logging ad unit exposure. No active activity");
        } else if (j < 1000) {
            this.zzu.zzaW().zzj().zzb("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("_ai", str);
            bundle.putLong("_xt", j);
            zzqf.zzN(zzmh, bundle, true);
            this.zzu.zzq().zzR("am", "_xu", bundle);
        }
    }

    /* access modifiers changed from: private */
    public final void zzj(long j) {
        Map map = this.zza;
        for (String put : map.keySet()) {
            map.put(put, Long.valueOf(j));
        }
        if (!map.isEmpty()) {
            this.zzc = j;
        }
    }

    public final void zzd(String str, long j) {
        if (str == null || str.length() == 0) {
            this.zzu.zzaW().zze().zza("Ad unit id must be a non-empty string");
        } else {
            this.zzu.zzaX().zzq(new zza(this, str, j));
        }
    }

    public final void zze(String str, long j) {
        if (str == null || str.length() == 0) {
            this.zzu.zzaW().zze().zza("Ad unit id must be a non-empty string");
        } else {
            this.zzu.zzaX().zzq(new zzb(this, str, j));
        }
    }

    public final void zzf(long j) {
        zzmh zzj = this.zzu.zzt().zzj(false);
        Map map = this.zza;
        for (String str : map.keySet()) {
            zzi(str, j - ((Long) map.get(str)).longValue(), zzj);
        }
        if (!map.isEmpty()) {
            zzh(j - this.zzc, zzj);
        }
        zzj(j);
    }
}
