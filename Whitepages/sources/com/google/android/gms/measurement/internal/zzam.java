package com.google.android.gms.measurement.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.perf.util.Constants;
import java.lang.reflect.InvocationTargetException;

public final class zzam extends zzjq {
    private Boolean zza;
    private String zzb;
    private zzal zzc = new zzak();
    private Boolean zzd;

    zzam(zzio zzio) {
        super(zzio);
    }

    public static final long zzF() {
        return ((Long) zzgi.zzd.zza((Object) null)).longValue();
    }

    public static final int zzG() {
        return Math.max(0, ((Integer) zzgi.zzi.zza((Object) null)).intValue());
    }

    public static final long zzH() {
        return (long) ((Integer) zzgi.zzk.zza((Object) null)).intValue();
    }

    public static final long zzI() {
        return ((Long) zzgi.zzQ.zza((Object) null)).longValue();
    }

    public static final long zzJ() {
        return ((Long) zzgi.zzL.zza((Object) null)).longValue();
    }

    private final String zzK(String str, String str2) {
        Class<String> cls = String.class;
        try {
            String str3 = (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{cls, cls}).invoke((Object) null, new Object[]{str, ""});
            Preconditions.checkNotNull(str3);
            return str3;
        } catch (ClassNotFoundException e) {
            this.zzu.zzaW().zze().zzb("Could not find SystemProperties class", e);
            return "";
        } catch (NoSuchMethodException e2) {
            this.zzu.zzaW().zze().zzb("Could not find SystemProperties.get() method", e2);
            return "";
        } catch (IllegalAccessException e3) {
            this.zzu.zzaW().zze().zzb("Could not access SystemProperties.get()", e3);
            return "";
        } catch (InvocationTargetException e4) {
            this.zzu.zzaW().zze().zzb("SystemProperties.get() threw an exception", e4);
            return "";
        }
    }

    public final boolean zzA() {
        this.zzu.zzaV();
        Boolean zzn = zzn("firebase_analytics_collection_deactivated");
        return zzn != null && zzn.booleanValue();
    }

    public final boolean zzB(String str) {
        return "1".equals(this.zzc.zza(str, "measurement.event_sampling_enabled"));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzC() {
        if (this.zza == null) {
            Boolean zzn = zzn("app_measurement_lite");
            this.zza = zzn;
            if (zzn == null) {
                this.zza = Boolean.FALSE;
            }
        }
        return this.zza.booleanValue() || !this.zzu.zzN();
    }

    public final boolean zzD() {
        if (this.zzd == null) {
            synchronized (this) {
                try {
                    if (this.zzd == null) {
                        zzio zzio = this.zzu;
                        ApplicationInfo applicationInfo = zzio.zzaT().getApplicationInfo();
                        String myProcessName = ProcessUtils.getMyProcessName();
                        if (applicationInfo != null) {
                            String str = applicationInfo.processName;
                            boolean z = false;
                            if (str != null && str.equals(myProcessName)) {
                                z = true;
                            }
                            this.zzd = Boolean.valueOf(z);
                        }
                        if (this.zzd == null) {
                            this.zzd = Boolean.TRUE;
                            zzio.zzaW().zze().zza("My process not in the list of running processes");
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.zzd.booleanValue();
    }

    public final boolean zzE() {
        Boolean zzn = zzn("google_analytics_sgtm_upload_enabled");
        if (zzn == null) {
            return false;
        }
        return zzn.booleanValue();
    }

    public final double zza(String str, zzgg zzgg) {
        if (TextUtils.isEmpty(str)) {
            return ((Double) zzgg.zza((Object) null)).doubleValue();
        }
        String zza2 = this.zzc.zza(str, zzgg.zzb());
        if (TextUtils.isEmpty(zza2)) {
            return ((Double) zzgg.zza((Object) null)).doubleValue();
        }
        try {
            return ((Double) zzgg.zza(Double.valueOf(Double.parseDouble(zza2)))).doubleValue();
        } catch (NumberFormatException unused) {
            return ((Double) zzgg.zza((Object) null)).doubleValue();
        }
    }

    /* access modifiers changed from: package-private */
    public final int zzb(String str) {
        return zzi(str, zzgi.zzV, 500, Constants.MAX_URL_LENGTH);
    }

    /* access modifiers changed from: package-private */
    public final int zzc(String str, boolean z) {
        if (z) {
            return zzi(str, zzgi.zzag, 100, 500);
        }
        return 500;
    }

    /* access modifiers changed from: package-private */
    public final int zzd(String str, boolean z) {
        return Math.max(zzc(str, z), 256);
    }

    public final int zze() {
        return this.zzu.zzw().zzao(201500000, true) ? 100 : 25;
    }

    public final int zzf(String str) {
        return zzi(str, zzgi.zzW, 25, 100);
    }

    public final int zzh(String str, zzgg zzgg) {
        if (TextUtils.isEmpty(str)) {
            return ((Integer) zzgg.zza((Object) null)).intValue();
        }
        String zza2 = this.zzc.zza(str, zzgg.zzb());
        if (TextUtils.isEmpty(zza2)) {
            return ((Integer) zzgg.zza((Object) null)).intValue();
        }
        try {
            return ((Integer) zzgg.zza(Integer.valueOf(Integer.parseInt(zza2)))).intValue();
        } catch (NumberFormatException unused) {
            return ((Integer) zzgg.zza((Object) null)).intValue();
        }
    }

    public final int zzi(String str, zzgg zzgg, int i, int i2) {
        return Math.max(Math.min(zzh(str, zzgg), i2), i);
    }

    public final long zzj() {
        this.zzu.zzaV();
        return 119002;
    }

    public final long zzk(String str, zzgg zzgg) {
        if (TextUtils.isEmpty(str)) {
            return ((Long) zzgg.zza((Object) null)).longValue();
        }
        String zza2 = this.zzc.zza(str, zzgg.zzb());
        if (TextUtils.isEmpty(zza2)) {
            return ((Long) zzgg.zza((Object) null)).longValue();
        }
        try {
            return ((Long) zzgg.zza(Long.valueOf(Long.parseLong(zza2)))).longValue();
        } catch (NumberFormatException unused) {
            return ((Long) zzgg.zza((Object) null)).longValue();
        }
    }

    /* access modifiers changed from: package-private */
    public final Bundle zzl() {
        try {
            zzio zzio = this.zzu;
            if (zzio.zzaT().getPackageManager() == null) {
                zzio.zzaW().zze().zza("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(zzio.zzaT()).getApplicationInfo(zzio.zzaT().getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.metaData;
            }
            zzio.zzaW().zze().zza("Failed to load metadata: ApplicationInfo is null");
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            this.zzu.zzaW().zze().zzb("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    public final zzju zzm(String str, boolean z) {
        Object obj;
        Preconditions.checkNotEmpty(str);
        zzio zzio = this.zzu;
        Bundle zzl = zzl();
        if (zzl == null) {
            zzio.zzaW().zze().zza("Failed to load metadata: Metadata bundle is null");
            obj = null;
        } else {
            obj = zzl.get(str);
        }
        if (obj == null) {
            return zzju.UNINITIALIZED;
        }
        if (Boolean.TRUE.equals(obj)) {
            return zzju.GRANTED;
        }
        if (Boolean.FALSE.equals(obj)) {
            return zzju.DENIED;
        }
        if (z && "eu_consent_policy".equals(obj)) {
            return zzju.POLICY;
        }
        zzio.zzaW().zzk().zzb("Invalid manifest metadata for", str);
        return zzju.UNINITIALIZED;
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzn(String str) {
        Preconditions.checkNotEmpty(str);
        Bundle zzl = zzl();
        if (zzl == null) {
            this.zzu.zzaW().zze().zza("Failed to load metadata: Metadata bundle is null");
            return null;
        } else if (!zzl.containsKey(str)) {
            return null;
        } else {
            return Boolean.valueOf(zzl.getBoolean(str));
        }
    }

    public final String zzo() {
        return zzK("debug.firebase.analytics.app", "");
    }

    public final String zzp() {
        return zzK("debug.deferred.deeplink", "");
    }

    /* access modifiers changed from: package-private */
    public final String zzq() {
        this.zzu.zzaV();
        return "FA";
    }

    public final String zzr(String str, zzgg zzgg) {
        if (TextUtils.isEmpty(str)) {
            return (String) zzgg.zza((Object) null);
        }
        return (String) zzgg.zza(this.zzc.zza(str, zzgg.zzb()));
    }

    public final String zzs() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e A[SYNTHETIC, Splitter:B:8:0x002e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List zzt(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.String r4 = "analytics.safelisted_events"
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)
            android.os.Bundle r0 = r3.zzl()
            r1 = 0
            if (r0 != 0) goto L_0x001d
            com.google.android.gms.measurement.internal.zzio r4 = r3.zzu
            com.google.android.gms.measurement.internal.zzhe r4 = r4.zzaW()
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zze()
            java.lang.String r0 = "Failed to load metadata: Metadata bundle is null"
            r4.zza(r0)
        L_0x001b:
            r4 = r1
            goto L_0x002c
        L_0x001d:
            boolean r2 = r0.containsKey(r4)
            if (r2 != 0) goto L_0x0024
            goto L_0x001b
        L_0x0024:
            int r4 = r0.getInt(r4)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
        L_0x002c:
            if (r4 == 0) goto L_0x0058
            com.google.android.gms.measurement.internal.zzio r0 = r3.zzu     // Catch:{ NotFoundException -> 0x0048 }
            android.content.Context r0 = r0.zzaT()     // Catch:{ NotFoundException -> 0x0048 }
            android.content.res.Resources r0 = r0.getResources()     // Catch:{ NotFoundException -> 0x0048 }
            int r4 = r4.intValue()     // Catch:{ NotFoundException -> 0x0048 }
            java.lang.String[] r4 = r0.getStringArray(r4)     // Catch:{ NotFoundException -> 0x0048 }
            if (r4 != 0) goto L_0x0043
            return r1
        L_0x0043:
            java.util.List r4 = java.util.Arrays.asList(r4)     // Catch:{ NotFoundException -> 0x0048 }
            return r4
        L_0x0048:
            r4 = move-exception
            com.google.android.gms.measurement.internal.zzio r0 = r3.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()
            java.lang.String r2 = "Failed to load string array from metadata: resource not found"
            r0.zzb(r2, r4)
        L_0x0058:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzt(java.lang.String):java.util.List");
    }

    /* access modifiers changed from: package-private */
    public final void zzu(zzal zzal) {
        this.zzc = zzal;
    }

    public final void zzv(String str) {
        this.zzb = str;
    }

    public final boolean zzw() {
        Boolean zzn = zzn("google_analytics_adid_collection_enabled");
        return zzn == null || zzn.booleanValue();
    }

    public final boolean zzx(String str, zzgg zzgg) {
        if (TextUtils.isEmpty(str)) {
            return ((Boolean) zzgg.zza((Object) null)).booleanValue();
        }
        String zza2 = this.zzc.zza(str, zzgg.zzb());
        if (TextUtils.isEmpty(zza2)) {
            return ((Boolean) zzgg.zza((Object) null)).booleanValue();
        }
        return ((Boolean) zzgg.zza(Boolean.valueOf("1".equals(zza2)))).booleanValue();
    }

    public final boolean zzy(String str) {
        return "1".equals(this.zzc.zza(str, "gaia_collection_enabled"));
    }

    public final boolean zzz() {
        Boolean zzn = zzn("google_analytics_automatic_screen_reporting_enabled");
        return zzn == null || zzn.booleanValue();
    }
}
