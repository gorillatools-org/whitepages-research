package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.util.SparseArray;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.internal.Preconditions;

final class zzht extends zzjr {
    static final Pair zza = new Pair("", 0L);
    private long zzA;
    public zzhq zzb;
    public final zzhp zzc = new zzhp(this, "first_open_time", 0);
    public final zzhp zzd = new zzhp(this, "app_install_time", 0);
    public final zzhr zze = new zzhr(this, "app_instance_id", (String) null);
    public final zzhp zzf = new zzhp(this, "session_timeout", 1800000);
    public final zzhn zzg = new zzhn(this, "start_new_session", true);
    public final zzhr zzh = new zzhr(this, "non_personalized_ads", (String) null);
    public final zzho zzi = new zzho(this, "last_received_uri_timestamps_by_source", (Bundle) null);
    public final zzhn zzj = new zzhn(this, "allow_remote_dynamite", false);
    public final zzhp zzk = new zzhp(this, "last_pause_time", 0);
    public final zzhp zzl = new zzhp(this, "session_id", 0);
    public boolean zzm;
    public final zzhn zzn = new zzhn(this, "app_backgrounded", false);
    public final zzhn zzo = new zzhn(this, "deep_link_retrieval_complete", false);
    public final zzhp zzp = new zzhp(this, "deep_link_retrieval_attempts", 0);
    public final zzhr zzq = new zzhr(this, "firebase_feature_rollouts", (String) null);
    public final zzhr zzr = new zzhr(this, "deferred_attribution_cache", (String) null);
    public final zzhp zzs = new zzhp(this, "deferred_attribution_cache_timestamp", 0);
    public final zzho zzt = new zzho(this, "default_event_parameters", (Bundle) null);
    private SharedPreferences zzv;
    private final Object zzw = new Object();
    private SharedPreferences zzx;
    private String zzy;
    private boolean zzz;

    zzht(zzio zzio) {
        super(zzio);
    }

    /* access modifiers changed from: protected */
    public final SharedPreferences zza() {
        zzg();
        zzv();
        if (this.zzx == null) {
            synchronized (this.zzw) {
                try {
                    if (this.zzx == null) {
                        zzio zzio = this.zzu;
                        String str = zzio.zzaT().getPackageName() + "_preferences";
                        zzio.zzaW().zzj().zzb("Default prefs file", str);
                        this.zzx = zzio.zzaT().getSharedPreferences(str, 0);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.zzx;
    }

    /* access modifiers changed from: protected */
    public final void zzaZ() {
        zzio zzio = this.zzu;
        SharedPreferences sharedPreferences = zzio.zzaT().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzv = sharedPreferences;
        boolean z = sharedPreferences.getBoolean("has_been_opened", false);
        this.zzm = z;
        if (!z) {
            SharedPreferences.Editor edit = this.zzv.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        zzio.zzf();
        this.zzb = new zzhq(this, "health_monitor", Math.max(0, ((Long) zzgi.zzc.zza((Object) null)).longValue()), (zzhs) null);
    }

    /* access modifiers changed from: protected */
    public final SharedPreferences zzb() {
        zzg();
        zzv();
        Preconditions.checkNotNull(this.zzv);
        return this.zzv;
    }

    /* access modifiers changed from: protected */
    public final boolean zzc() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public final Pair zzd(String str) {
        zzg();
        if (!zzh().zzr(zzjw.AD_STORAGE)) {
            return new Pair("", Boolean.FALSE);
        }
        zzio zzio = this.zzu;
        long elapsedRealtime = zzio.zzaU().elapsedRealtime();
        String str2 = this.zzy;
        if (str2 != null && elapsedRealtime < this.zzA) {
            return new Pair(str2, Boolean.valueOf(this.zzz));
        }
        this.zzA = elapsedRealtime + zzio.zzf().zzk(str, zzgi.zza);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(zzio.zzaT());
            this.zzy = "";
            String id = advertisingIdInfo.getId();
            if (id != null) {
                this.zzy = id;
            }
            this.zzz = advertisingIdInfo.isLimitAdTrackingEnabled();
        } catch (Exception e) {
            this.zzu.zzaW().zzd().zzb("Unable to get advertising id", e);
            this.zzy = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair(this.zzy, Boolean.valueOf(this.zzz));
    }

    /* access modifiers changed from: package-private */
    public final SparseArray zze() {
        Bundle zza2 = this.zzi.zza();
        int[] intArray = zza2.getIntArray("uriSources");
        long[] longArray = zza2.getLongArray("uriTimestamps");
        if (intArray == null || longArray == null) {
            return new SparseArray();
        }
        if (intArray.length != longArray.length) {
            this.zzu.zzaW().zze().zza("Trigger URI source and timestamp array lengths do not match");
            return new SparseArray();
        }
        SparseArray sparseArray = new SparseArray();
        for (int i = 0; i < intArray.length; i++) {
            sparseArray.put(intArray[i], Long.valueOf(longArray[i]));
        }
        return sparseArray;
    }

    /* access modifiers changed from: package-private */
    public final zzba zzf() {
        zzg();
        return zzba.zze(zzb().getString("dma_consent_settings", (String) null));
    }

    /* access modifiers changed from: package-private */
    public final zzjx zzh() {
        zzg();
        return zzjx.zzk(zzb().getString("consent_settings", "G1"), zzb().getInt("consent_source", 100));
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzi() {
        zzg();
        if (zzb().contains("measurement_enabled")) {
            return Boolean.valueOf(zzb().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final String zzj() {
        zzg();
        return zzb().getString("gmp_app_id", (String) null);
    }

    /* access modifiers changed from: package-private */
    public final void zzl(String str) {
        zzg();
        SharedPreferences.Editor edit = zzb().edit();
        edit.putString("admob_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final void zzm(Boolean bool) {
        zzg();
        SharedPreferences.Editor edit = zzb().edit();
        if (bool != null) {
            edit.putBoolean("measurement_enabled", bool.booleanValue());
        } else {
            edit.remove("measurement_enabled");
        }
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final void zzn(boolean z) {
        zzg();
        this.zzu.zzaW().zzj().zzb("App measurement setting deferred collection", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzb().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzo() {
        SharedPreferences sharedPreferences = this.zzv;
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.contains("deferred_analytics_collection");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzp(long j) {
        return j - this.zzf.zza() > this.zzk.zza();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzq(int i) {
        return zzjx.zzs(i, zzb().getInt("consent_source", 100));
    }

    /* access modifiers changed from: protected */
    public final boolean zzr(zzoq zzoq) {
        zzg();
        String string = zzb().getString("stored_tcf_param", "");
        String zze2 = zzoq.zze();
        if (zze2.equals(string)) {
            return false;
        }
        SharedPreferences.Editor edit = zzb().edit();
        edit.putString("stored_tcf_param", zze2);
        edit.apply();
        return true;
    }
}
