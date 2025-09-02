package com.google.android.gms.measurement.internal;

import android.content.pm.PackageManager;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public final class zzoa extends zzpg {
    public final zzhp zza;
    public final zzhp zzb;
    public final zzhp zzc;
    public final zzhp zzd;
    public final zzhp zze;
    public final zzhp zzf;
    private final Map zzh = new HashMap();

    zzoa(zzpv zzpv) {
        super(zzpv);
        zzht zzm = this.zzu.zzm();
        Objects.requireNonNull(zzm);
        this.zza = new zzhp(zzm, "last_delete_stale", 0);
        zzht zzm2 = this.zzu.zzm();
        Objects.requireNonNull(zzm2);
        this.zzb = new zzhp(zzm2, "last_delete_stale_batch", 0);
        zzht zzm3 = this.zzu.zzm();
        Objects.requireNonNull(zzm3);
        this.zzc = new zzhp(zzm3, "backoff", 0);
        zzht zzm4 = this.zzu.zzm();
        Objects.requireNonNull(zzm4);
        this.zzd = new zzhp(zzm4, "last_upload", 0);
        zzht zzm5 = this.zzu.zzm();
        Objects.requireNonNull(zzm5);
        this.zze = new zzhp(zzm5, "last_upload_attempt", 0);
        zzht zzm6 = this.zzu.zzm();
        Objects.requireNonNull(zzm6);
        this.zzf = new zzhp(zzm6, "midnight_offset", 0);
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public final Pair zza(String str) {
        zznz zznz;
        AdvertisingIdClient.Info info;
        zzg();
        zzio zzio = this.zzu;
        long elapsedRealtime = zzio.zzaU().elapsedRealtime();
        zznz zznz2 = (zznz) this.zzh.get(str);
        if (zznz2 != null && elapsedRealtime < zznz2.zzc) {
            return new Pair(zznz2.zza, Boolean.valueOf(zznz2.zzb));
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        long zzk = zzio.zzf().zzk(str, zzgi.zza) + elapsedRealtime;
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(zzio.zzaT());
        } catch (PackageManager.NameNotFoundException unused) {
            info = null;
            if (zznz2 != null) {
                try {
                    if (elapsedRealtime < zznz2.zzc + this.zzu.zzf().zzk(str, zzgi.zzb)) {
                        return new Pair(zznz2.zza, Boolean.valueOf(zznz2.zzb));
                    }
                } catch (Exception e) {
                    this.zzu.zzaW().zzd().zzb("Unable to get advertising id", e);
                    zznz = new zznz("", false, zzk);
                }
            }
        }
        if (info == null) {
            return new Pair("00000000-0000-0000-0000-000000000000", Boolean.FALSE);
        }
        String id = info.getId();
        if (id != null) {
            zznz = new zznz(id, info.isLimitAdTrackingEnabled(), zzk);
        } else {
            zznz = new zznz("", info.isLimitAdTrackingEnabled(), zzk);
        }
        this.zzh.put(str, zznz);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair(zznz.zza, Boolean.valueOf(zznz.zzb));
    }

    /* access modifiers changed from: protected */
    public final boolean zzb() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final Pair zzd(String str, zzjx zzjx) {
        if (zzjx.zzr(zzjw.AD_STORAGE)) {
            return zza(str);
        }
        return new Pair("", Boolean.FALSE);
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public final String zzf(String str, boolean z) {
        String str2;
        zzg();
        if (z) {
            str2 = (String) zza(str).first;
        } else {
            str2 = "00000000-0000-0000-0000-000000000000";
        }
        MessageDigest zzI = zzqf.zzI();
        if (zzI == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzI.digest(str2.getBytes()))});
    }
}
