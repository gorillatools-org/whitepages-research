package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.collection.LruCache;
import com.amplitude.reactnative.AmplitudeReactNativeModule;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.internal.measurement.zzd;
import com.google.android.gms.internal.measurement.zzfz;
import com.google.android.gms.internal.measurement.zzgb;
import com.google.android.gms.internal.measurement.zzgh;
import com.google.android.gms.internal.measurement.zzgi;
import com.google.android.gms.internal.measurement.zzgk;
import com.google.android.gms.internal.measurement.zzgl;
import com.google.android.gms.internal.measurement.zzgn;
import com.google.android.gms.internal.measurement.zzgo;
import com.google.android.gms.internal.measurement.zzgw;
import com.google.android.gms.internal.measurement.zzit;
import com.google.android.gms.internal.measurement.zziv;
import com.google.android.gms.internal.measurement.zzmm;
import com.google.android.gms.internal.measurement.zzr;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public final class zzif extends zzpg implements zzal {
    final Map zza = new ArrayMap();
    final Map zzb = new ArrayMap();
    final Map zzc = new ArrayMap();
    final LruCache zzd = new zzic(this, 20);
    final zzr zze = new zzid(this);
    /* access modifiers changed from: private */
    public final Map zzf = new ArrayMap();
    private final Map zzh = new ArrayMap();
    private final Map zzi = new ArrayMap();
    private final Map zzj = new ArrayMap();
    private final Map zzk = new ArrayMap();
    private final Map zzl = new ArrayMap();

    zzif(zzpv zzpv) {
        super(zzpv);
    }

    private final zzgo zzG(String str, byte[] bArr) {
        if (bArr == null) {
            return zzgo.zzh();
        }
        try {
            zzgo zzgo = (zzgo) ((zzgn) zzqa.zzp(zzgo.zzf(), bArr)).zzba();
            zzhc zzj2 = this.zzu.zzaW().zzj();
            String str2 = null;
            Long valueOf = zzgo.zzw() ? Long.valueOf(zzgo.zzc()) : null;
            if (zzgo.zzu()) {
                str2 = zzgo.zzj();
            }
            zzj2.zzc("Parsed config. version, gmp_app_id", valueOf, str2);
            return zzgo;
        } catch (zzmm e) {
            this.zzu.zzaW().zzk().zzc("Unable to merge remote config. appId", zzhe.zzn(str), e);
            return zzgo.zzh();
        } catch (RuntimeException e2) {
            this.zzu.zzaW().zzk().zzc("Unable to merge remote config. appId", zzhe.zzn(str), e2);
            return zzgo.zzh();
        }
    }

    private final void zzH(String str, zzgn zzgn) {
        HashSet hashSet = new HashSet();
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        for (zzgk zzb2 : zzgn.zzh()) {
            hashSet.add(zzb2.zzb());
        }
        for (int i = 0; i < zzgn.zza(); i++) {
            zzgl zzgl = (zzgl) zzgn.zzb(i).zzch();
            if (zzgl.zzc().isEmpty()) {
                this.zzu.zzaW().zzk().zza("EventConfig contained null event name");
            } else {
                String zzc2 = zzgl.zzc();
                String zzb3 = zzjy.zzb(zzgl.zzc());
                if (!TextUtils.isEmpty(zzb3)) {
                    zzgl.zzb(zzb3);
                    zzgn.zze(i, zzgl);
                }
                if (zzgl.zzf() && zzgl.zzd()) {
                    arrayMap.put(zzc2, Boolean.TRUE);
                }
                if (zzgl.zzg() && zzgl.zze()) {
                    arrayMap2.put(zzgl.zzc(), Boolean.TRUE);
                }
                if (zzgl.zzh()) {
                    if (zzgl.zza() < 2 || zzgl.zza() > 65535) {
                        this.zzu.zzaW().zzk().zzc("Invalid sampling rate. Event name, sample rate", zzgl.zzc(), Integer.valueOf(zzgl.zza()));
                    } else {
                        arrayMap3.put(zzgl.zzc(), Integer.valueOf(zzgl.zza()));
                    }
                }
            }
        }
        this.zza.put(str, hashSet);
        this.zzb.put(str, arrayMap);
        this.zzc.put(str, arrayMap2);
        this.zzi.put(str, arrayMap3);
    }

    private final void zzI(String str) {
        zzav();
        zzg();
        Preconditions.checkNotEmpty(str);
        Map map = this.zzh;
        if (map.get(str) == null) {
            zzar zzn = this.zzg.zzj().zzn(str);
            if (zzn == null) {
                this.zzf.put(str, (Object) null);
                this.zzb.put(str, (Object) null);
                this.zza.put(str, (Object) null);
                this.zzc.put(str, (Object) null);
                map.put(str, (Object) null);
                this.zzj.put(str, (Object) null);
                this.zzk.put(str, (Object) null);
                this.zzl.put(str, (Object) null);
                this.zzi.put(str, (Object) null);
                return;
            }
            zzgn zzgn = (zzgn) zzG(str, zzn.zza).zzch();
            zzH(str, zzgn);
            this.zzf.put(str, zzK((zzgo) zzgn.zzba()));
            map.put(str, (zzgo) zzgn.zzba());
            zzJ(str, (zzgo) zzgn.zzba());
            this.zzj.put(str, zzgn.zzf());
            this.zzk.put(str, zzn.zzb);
            this.zzl.put(str, zzn.zzc);
        }
    }

    private final void zzJ(String str, zzgo zzgo) {
        if (zzgo.zza() != 0) {
            zzio zzio = this.zzu;
            zzio.zzaW().zzj().zzb("EES programs found", Integer.valueOf(zzgo.zza()));
            zziv zziv = (zziv) zzgo.zzo().get(0);
            try {
                zzc zzc2 = new zzc();
                zzc2.zzd("internal.remoteConfig", new zzhy(this, str));
                zzc2.zzd("internal.appMetadata", new zzhz(this, str));
                zzc2.zzd("internal.logger", new zzia(this));
                zzc2.zzc(zziv);
                this.zzd.put(str, zzc2);
                zzio.zzaW().zzj().zzc("EES program loaded for appId, activities", str, Integer.valueOf(zziv.zza().zza()));
                for (zzit zzb2 : zziv.zza().zzd()) {
                    zzio.zzaW().zzj().zzb("EES program activity", zzb2.zzb());
                }
            } catch (zzd unused) {
                this.zzu.zzaW().zze().zzb("Failed to load EES program. appId", str);
            }
        } else {
            this.zzd.remove(str);
        }
    }

    private static final Map zzK(zzgo zzgo) {
        ArrayMap arrayMap = new ArrayMap();
        if (zzgo != null) {
            for (zzgw zzgw : zzgo.zzp()) {
                arrayMap.put(zzgw.zzb(), zzgw.zzc());
            }
        }
        return arrayMap;
    }

    private static final zzjw zzL(int i) {
        int i2 = i - 1;
        if (i2 == 1) {
            return zzjw.AD_STORAGE;
        }
        if (i2 == 2) {
            return zzjw.ANALYTICS_STORAGE;
        }
        if (i2 == 3) {
            return zzjw.AD_USER_DATA;
        }
        if (i2 != 4) {
            return null;
        }
        return zzjw.AD_PERSONALIZATION;
    }

    static /* bridge */ /* synthetic */ zzc zzd(zzif zzif, String str) {
        zzif.zzav();
        Preconditions.checkNotEmpty(str);
        if (!zzif.zzs(str)) {
            return null;
        }
        Map map = zzif.zzh;
        if (!map.containsKey(str) || map.get(str) == null) {
            zzif.zzI(str);
        } else {
            zzif.zzJ(str, (zzgo) map.get(str));
        }
        return (zzc) zzif.zzd.snapshot().get(str);
    }

    static /* bridge */ /* synthetic */ zzc zze(zzif zzif, String str) {
        zzif.zzav();
        Preconditions.checkNotEmpty(str);
        zzar zzn = zzif.zzg.zzj().zzn(str);
        if (zzn == null) {
            return null;
        }
        zzif.zzu.zzaW().zzj().zzb("Populate EES config from database on cache miss. appId", str);
        zzif.zzJ(str, zzif.zzG(str, zzn.zza));
        return (zzc) zzif.zzd.snapshot().get(str);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzA(String str) {
        zzg();
        zzI(str);
        Map map = this.zza;
        return map.get(str) != null && ((Set) map.get(str)).contains("app_instance_id");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzB(String str) {
        zzg();
        zzI(str);
        Map map = this.zza;
        if (map.get(str) == null) {
            return false;
        }
        if (((Set) map.get(str)).contains("device_model")) {
            return true;
        }
        if (!((Set) map.get(str)).contains("device_info")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzC(String str) {
        zzg();
        zzI(str);
        Map map = this.zza;
        return map.get(str) != null && ((Set) map.get(str)).contains("enhanced_user_id");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzD(String str) {
        zzg();
        zzI(str);
        Map map = this.zza;
        return map.get(str) != null && ((Set) map.get(str)).contains("google_signals");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzE(String str) {
        zzg();
        zzI(str);
        Map map = this.zza;
        if (map.get(str) == null) {
            return false;
        }
        if (((Set) map.get(str)).contains("os_version")) {
            return true;
        }
        if (!((Set) map.get(str)).contains("device_info")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzF(String str) {
        zzg();
        zzI(str);
        Map map = this.zza;
        return map.get(str) != null && ((Set) map.get(str)).contains(AmplitudeReactNativeModule.USER_ID_KEY);
    }

    public final String zza(String str, String str2) {
        zzg();
        zzI(str);
        Map map = (Map) this.zzf.get(str);
        if (map != null) {
            return (String) map.get(str2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final boolean zzb() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final int zzc(String str, String str2) {
        Integer num;
        zzg();
        zzI(str);
        Map map = (Map) this.zzi.get(str);
        if (map == null || (num = (Integer) map.get(str2)) == null) {
            return 1;
        }
        return num.intValue();
    }

    /* access modifiers changed from: package-private */
    public final zzju zzf(String str, zzjw zzjw) {
        zzg();
        zzI(str);
        zzgi zzi2 = zzi(str);
        if (zzi2 == null) {
            return zzju.UNINITIALIZED;
        }
        for (zzfz zzfz : zzi2.zzf()) {
            if (zzL(zzfz.zzc()) == zzjw) {
                int zzb2 = zzfz.zzb() - 1;
                if (zzb2 == 1) {
                    return zzju.GRANTED;
                }
                if (zzb2 != 2) {
                    return zzju.UNINITIALIZED;
                }
                return zzju.DENIED;
            }
        }
        return zzju.UNINITIALIZED;
    }

    /* access modifiers changed from: package-private */
    public final zzjw zzh(String str, zzjw zzjw) {
        zzg();
        zzI(str);
        zzgi zzi2 = zzi(str);
        if (zzi2 == null) {
            return null;
        }
        for (zzgb zzgb : zzi2.zze()) {
            if (zzjw == zzL(zzgb.zzc())) {
                return zzL(zzgb.zzb());
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final zzgi zzi(String str) {
        zzg();
        zzI(str);
        zzgo zzj2 = zzj(str);
        if (zzj2 == null || !zzj2.zzt()) {
            return null;
        }
        return zzj2.zzd();
    }

    /* access modifiers changed from: protected */
    public final zzgo zzj(String str) {
        zzav();
        zzg();
        Preconditions.checkNotEmpty(str);
        zzI(str);
        return (zzgo) this.zzh.get(str);
    }

    /* access modifiers changed from: protected */
    public final String zzk(String str) {
        zzg();
        return (String) this.zzl.get(str);
    }

    /* access modifiers changed from: protected */
    public final String zzl(String str) {
        zzg();
        return (String) this.zzk.get(str);
    }

    /* access modifiers changed from: package-private */
    public final String zzm(String str) {
        zzg();
        zzI(str);
        return (String) this.zzj.get(str);
    }

    /* access modifiers changed from: package-private */
    public final Set zzo(String str) {
        zzg();
        zzI(str);
        return (Set) this.zza.get(str);
    }

    /* access modifiers changed from: package-private */
    public final SortedSet zzp(String str) {
        zzg();
        zzI(str);
        TreeSet treeSet = new TreeSet();
        zzgi zzi2 = zzi(str);
        if (zzi2 != null) {
            for (zzgh zzb2 : zzi2.zzc()) {
                treeSet.add(zzb2.zzb());
            }
        }
        return treeSet;
    }

    /* access modifiers changed from: protected */
    public final void zzq(String str) {
        zzg();
        this.zzk.put(str, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public final void zzr(String str) {
        zzg();
        this.zzh.remove(str);
    }

    public final boolean zzs(String str) {
        zzgo zzgo;
        if (TextUtils.isEmpty(str) || (zzgo = (zzgo) this.zzh.get(str)) == null || zzgo.zza() == 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzt(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_internal"));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzu(String str, zzjw zzjw) {
        zzg();
        zzI(str);
        zzgi zzi2 = zzi(str);
        if (zzi2 == null) {
            return false;
        }
        Iterator it = zzi2.zzd().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            zzfz zzfz = (zzfz) it.next();
            if (zzjw == zzL(zzfz.zzc())) {
                if (zzfz.zzb() == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzv(String str) {
        zzg();
        zzI(str);
        zzgi zzi2 = zzi(str);
        return zzi2 == null || !zzi2.zzh() || zzi2.zzg();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzw(String str, String str2) {
        Boolean bool;
        zzg();
        zzI(str);
        if ("ecommerce_purchase".equals(str2) || FirebaseAnalytics.Event.PURCHASE.equals(str2) || FirebaseAnalytics.Event.REFUND.equals(str2)) {
            return true;
        }
        Map map = (Map) this.zzc.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzx(String str, String str2) {
        Boolean bool;
        zzg();
        zzI(str);
        if (zzt(str) && zzqf.zzap(str2)) {
            return true;
        }
        if (zzy(str) && zzqf.zzaq(str2)) {
            return true;
        }
        Map map = (Map) this.zzb.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzy(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_public"));
    }

    /* access modifiers changed from: protected */
    public final boolean zzz(String str, byte[] bArr, String str2, String str3) {
        zzav();
        zzg();
        Preconditions.checkNotEmpty(str);
        zzgn zzgn = (zzgn) zzG(str, bArr).zzch();
        zzH(str, zzgn);
        zzJ(str, (zzgo) zzgn.zzba());
        this.zzh.put(str, (zzgo) zzgn.zzba());
        this.zzj.put(str, zzgn.zzf());
        this.zzk.put(str, str2);
        this.zzl.put(str, str3);
        this.zzf.put(str, zzK((zzgo) zzgn.zzba()));
        this.zzg.zzj().zzR(str, new ArrayList(zzgn.zzg()));
        try {
            zzgn.zzc();
            bArr = ((zzgo) zzgn.zzba()).zzcd();
        } catch (RuntimeException e) {
            this.zzu.zzaW().zzk().zzc("Unable to serialize reduced-size config. Storing full config instead. appId", zzhe.zzn(str), e);
        }
        zzaw zzj2 = this.zzg.zzj();
        Preconditions.checkNotEmpty(str);
        zzj2.zzg();
        zzj2.zzav();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        contentValues.put("config_last_modified_time", str2);
        contentValues.put("e_tag", str3);
        try {
            if (((long) zzj2.zzj().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                zzj2.zzu.zzaW().zze().zzb("Failed to update remote config (got 0). appId", zzhe.zzn(str));
            }
        } catch (SQLiteException e2) {
            zzj2.zzu.zzaW().zze().zzc("Error storing remote config. appId", zzhe.zzn(str), e2);
        }
        if (this.zzu.zzf().zzx((String) null, zzgi.zzbn)) {
            zzgn.zzd();
        }
        this.zzh.put(str, (zzgo) zzgn.zzba());
        return true;
    }
}
