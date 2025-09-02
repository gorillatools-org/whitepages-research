package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.math.BigInteger;
import java.util.List;
import java.util.Locale;

public final class zzgs extends zzg {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private final long zzg;
    private final long zzh;
    private List zzi;
    private String zzj;
    private int zzk;
    private String zzl;
    private String zzm;
    private String zzn;
    private long zzo = 0;
    private String zzp = null;

    zzgs(zzio zzio, long j, long j2) {
        super(zzio);
        this.zzg = j;
        this.zzh = j2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0189  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x01a3 A[Catch:{ IllegalStateException -> 0x01da }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01a4 A[Catch:{ IllegalStateException -> 0x01da }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01cc A[Catch:{ IllegalStateException -> 0x01da }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01cd A[Catch:{ IllegalStateException -> 0x01da }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01de A[Catch:{ IllegalStateException -> 0x01da }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0222  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0257  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0262  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd() {
        /*
            r12 = this;
            com.google.android.gms.measurement.internal.zzio r0 = r12.zzu
            com.google.android.gms.measurement.internal.zzhe r1 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzj()
            long r2 = r12.zzh
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            long r3 = r12.zzg
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            java.lang.String r4 = "sdkVersion bundled with app, dynamiteVersion"
            r1.zzc(r4, r2, r3)
            android.content.Context r1 = r0.zzaT()
            java.lang.String r1 = r1.getPackageName()
            android.content.Context r2 = r0.zzaT()
            android.content.pm.PackageManager r2 = r2.getPackageManager()
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            java.lang.String r4 = ""
            r5 = 0
            java.lang.String r6 = "Unknown"
            java.lang.String r7 = "unknown"
            if (r2 != 0) goto L_0x004a
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzhe.zzn(r1)
            java.lang.String r9 = "PackageManager is null, app identity information might be inaccurate. appId"
            r0.zzb(r9, r8)
        L_0x0047:
            r8 = r6
            goto L_0x00b1
        L_0x004a:
            java.lang.String r7 = r2.getInstallerPackageName(r1)     // Catch:{ IllegalArgumentException -> 0x004f }
            goto L_0x0062
        L_0x004f:
            com.google.android.gms.measurement.internal.zzio r0 = r12.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzhe.zzn(r1)
            java.lang.String r9 = "Error retrieving app installer package name. appId"
            r0.zzb(r9, r8)
        L_0x0062:
            if (r7 != 0) goto L_0x0068
            java.lang.String r0 = "manual_install"
            r7 = r0
            goto L_0x0071
        L_0x0068:
            java.lang.String r0 = "com.android.vending"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x0071
            r7 = r4
        L_0x0071:
            com.google.android.gms.measurement.internal.zzio r0 = r12.zzu     // Catch:{ NameNotFoundException -> 0x009b }
            android.content.Context r0 = r0.zzaT()     // Catch:{ NameNotFoundException -> 0x009b }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ NameNotFoundException -> 0x009b }
            android.content.pm.PackageInfo r0 = r2.getPackageInfo(r0, r5)     // Catch:{ NameNotFoundException -> 0x009b }
            if (r0 == 0) goto L_0x0047
            android.content.pm.ApplicationInfo r8 = r0.applicationInfo     // Catch:{ NameNotFoundException -> 0x009b }
            java.lang.CharSequence r8 = r2.getApplicationLabel(r8)     // Catch:{ NameNotFoundException -> 0x009b }
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch:{ NameNotFoundException -> 0x009b }
            if (r9 != 0) goto L_0x0092
            java.lang.String r8 = r8.toString()     // Catch:{ NameNotFoundException -> 0x009b }
            goto L_0x0093
        L_0x0092:
            r8 = r6
        L_0x0093:
            java.lang.String r6 = r0.versionName     // Catch:{ NameNotFoundException -> 0x0098 }
            int r3 = r0.versionCode     // Catch:{ NameNotFoundException -> 0x0098 }
            goto L_0x00b1
        L_0x0098:
            r0 = r6
            r6 = r8
            goto L_0x009c
        L_0x009b:
            r0 = r6
        L_0x009c:
            com.google.android.gms.measurement.internal.zzio r8 = r12.zzu
            com.google.android.gms.measurement.internal.zzhe r8 = r8.zzaW()
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zze()
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzhe.zzn(r1)
            java.lang.String r10 = "Error retrieving package info. appId, appName"
            r8.zzc(r10, r9, r6)
            r8 = r6
            r6 = r0
        L_0x00b1:
            r12.zza = r1
            r12.zzd = r7
            r12.zzb = r6
            r12.zzc = r3
            r12.zze = r8
            r6 = 0
            r12.zzf = r6
            com.google.android.gms.measurement.internal.zzio r0 = r12.zzu
            com.google.android.gms.measurement.internal.zzam r3 = r0.zzf()
            com.google.android.gms.measurement.internal.zzgg r6 = com.google.android.gms.measurement.internal.zzgi.zzbp
            r7 = 0
            boolean r3 = r3.zzx(r7, r6)
            r8 = 1
            if (r3 != 0) goto L_0x00e9
            com.google.android.gms.measurement.internal.zzio r3 = r12.zzu
            java.lang.String r9 = r3.zzx()
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 != 0) goto L_0x00e9
            java.lang.String r3 = r3.zzy()
            java.lang.String r9 = "am"
            boolean r3 = r9.equals(r3)
            if (r3 == 0) goto L_0x00e9
            r3 = r8
            goto L_0x00ea
        L_0x00e9:
            r3 = r5
        L_0x00ea:
            com.google.android.gms.measurement.internal.zzio r9 = r12.zzu
            int r10 = r9.zza()
            if (r10 == 0) goto L_0x0173
            if (r10 == r8) goto L_0x0165
            r8 = 3
            if (r10 == r8) goto L_0x0157
            r8 = 4
            if (r10 == r8) goto L_0x0149
            r8 = 6
            if (r10 == r8) goto L_0x013b
            r8 = 7
            if (r10 == r8) goto L_0x012d
            r8 = 8
            if (r10 == r8) goto L_0x011f
            com.google.android.gms.measurement.internal.zzhe r8 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zzi()
            java.lang.String r11 = "App measurement disabled"
            r8.zza(r11)
            com.google.android.gms.measurement.internal.zzhe r8 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zzh()
            java.lang.String r11 = "Invalid scion state in identity"
            r8.zza(r11)
            goto L_0x0180
        L_0x011f:
            com.google.android.gms.measurement.internal.zzhe r8 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zzi()
            java.lang.String r11 = "App measurement disabled due to denied storage consent"
            r8.zza(r11)
            goto L_0x0180
        L_0x012d:
            com.google.android.gms.measurement.internal.zzhe r8 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zzi()
            java.lang.String r11 = "App measurement disabled via the global data collection setting"
            r8.zza(r11)
            goto L_0x0180
        L_0x013b:
            com.google.android.gms.measurement.internal.zzhe r8 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zzl()
            java.lang.String r11 = "App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics"
            r8.zza(r11)
            goto L_0x0180
        L_0x0149:
            com.google.android.gms.measurement.internal.zzhe r8 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zzi()
            java.lang.String r11 = "App measurement disabled via the manifest"
            r8.zza(r11)
            goto L_0x0180
        L_0x0157:
            com.google.android.gms.measurement.internal.zzhe r8 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zzi()
            java.lang.String r11 = "App measurement disabled by setAnalyticsCollectionEnabled(false)"
            r8.zza(r11)
            goto L_0x0180
        L_0x0165:
            com.google.android.gms.measurement.internal.zzhe r8 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zzi()
            java.lang.String r11 = "App measurement deactivated via the manifest"
            r8.zza(r11)
            goto L_0x0180
        L_0x0173:
            com.google.android.gms.measurement.internal.zzhe r8 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zzj()
            java.lang.String r11 = "App measurement collection enabled"
            r8.zza(r11)
        L_0x0180:
            r12.zzl = r4
            r12.zzm = r4
            r0.zzaV()
            if (r3 == 0) goto L_0x018f
            java.lang.String r3 = r9.zzx()
            r12.zzm = r3
        L_0x018f:
            android.content.Context r3 = r0.zzaT()     // Catch:{ IllegalStateException -> 0x01da }
            java.lang.String r8 = r9.zzA()     // Catch:{ IllegalStateException -> 0x01da }
            java.lang.String r11 = "google_app_id"
            java.lang.String r3 = com.google.android.gms.measurement.internal.zzmg.zzc(r3, r11, r8)     // Catch:{ IllegalStateException -> 0x01da }
            boolean r8 = android.text.TextUtils.isEmpty(r3)     // Catch:{ IllegalStateException -> 0x01da }
            if (r8 == 0) goto L_0x01a4
            goto L_0x01a5
        L_0x01a4:
            r4 = r3
        L_0x01a5:
            r12.zzl = r4     // Catch:{ IllegalStateException -> 0x01da }
            com.google.android.gms.measurement.internal.zzam r4 = r0.zzf()     // Catch:{ IllegalStateException -> 0x01da }
            boolean r4 = r4.zzx(r7, r6)     // Catch:{ IllegalStateException -> 0x01da }
            if (r4 != 0) goto L_0x01dc
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ IllegalStateException -> 0x01da }
            if (r3 != 0) goto L_0x01dc
            android.content.Context r3 = r0.zzaT()     // Catch:{ IllegalStateException -> 0x01da }
            java.lang.String r4 = r9.zzA()     // Catch:{ IllegalStateException -> 0x01da }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ IllegalStateException -> 0x01da }
            android.content.res.Resources r6 = r3.getResources()     // Catch:{ IllegalStateException -> 0x01da }
            boolean r8 = android.text.TextUtils.isEmpty(r4)     // Catch:{ IllegalStateException -> 0x01da }
            if (r8 != 0) goto L_0x01cd
            goto L_0x01d1
        L_0x01cd:
            java.lang.String r4 = com.google.android.gms.measurement.internal.zzig.zza(r3)     // Catch:{ IllegalStateException -> 0x01da }
        L_0x01d1:
            java.lang.String r3 = "admob_app_id"
            java.lang.String r3 = com.google.android.gms.measurement.internal.zzig.zzb(r3, r6, r4)     // Catch:{ IllegalStateException -> 0x01da }
            r12.zzm = r3     // Catch:{ IllegalStateException -> 0x01da }
            goto L_0x01dc
        L_0x01da:
            r0 = move-exception
            goto L_0x01fb
        L_0x01dc:
            if (r10 != 0) goto L_0x020e
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ IllegalStateException -> 0x01da }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()     // Catch:{ IllegalStateException -> 0x01da }
            java.lang.String r3 = "App measurement enabled for app package, google app id"
            java.lang.String r4 = r12.zza     // Catch:{ IllegalStateException -> 0x01da }
            java.lang.String r6 = r12.zzl     // Catch:{ IllegalStateException -> 0x01da }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ IllegalStateException -> 0x01da }
            if (r6 == 0) goto L_0x01f5
            java.lang.String r6 = r12.zzm     // Catch:{ IllegalStateException -> 0x01da }
            goto L_0x01f7
        L_0x01f5:
            java.lang.String r6 = r12.zzl     // Catch:{ IllegalStateException -> 0x01da }
        L_0x01f7:
            r0.zzc(r3, r4, r6)     // Catch:{ IllegalStateException -> 0x01da }
            goto L_0x020e
        L_0x01fb:
            com.google.android.gms.measurement.internal.zzio r3 = r12.zzu
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()
            java.lang.Object r1 = com.google.android.gms.measurement.internal.zzhe.zzn(r1)
            java.lang.String r4 = "Fetching Google App Id failed with exception. appId"
            r3.zzc(r4, r1, r0)
        L_0x020e:
            r12.zzi = r7
            com.google.android.gms.measurement.internal.zzio r0 = r12.zzu
            r0.zzaV()
            com.google.android.gms.measurement.internal.zzam r1 = r0.zzf()
            java.lang.String r3 = "analytics.safelisted_events"
            java.util.List r1 = r1.zzt(r3)
            if (r1 != 0) goto L_0x0222
            goto L_0x0253
        L_0x0222:
            boolean r3 = r1.isEmpty()
            if (r3 == 0) goto L_0x0236
            com.google.android.gms.measurement.internal.zzhe r1 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzl()
            java.lang.String r3 = "Safelisted event list is empty. Ignoring"
            r1.zza(r3)
            goto L_0x0255
        L_0x0236:
            java.util.Iterator r3 = r1.iterator()
        L_0x023a:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0253
            java.lang.Object r4 = r3.next()
            java.lang.String r4 = (java.lang.String) r4
            com.google.android.gms.measurement.internal.zzqf r6 = r0.zzw()
            java.lang.String r7 = "safelisted event"
            boolean r4 = r6.zzag(r7, r4)
            if (r4 != 0) goto L_0x023a
            goto L_0x0255
        L_0x0253:
            r12.zzi = r1
        L_0x0255:
            if (r2 == 0) goto L_0x0262
            android.content.Context r0 = r0.zzaT()
            boolean r0 = com.google.android.gms.common.wrappers.InstantApps.isInstantApp(r0)
            r12.zzk = r0
            return
        L_0x0262:
            r12.zzk = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgs.zzd():void");
    }

    /* access modifiers changed from: protected */
    public final boolean zzf() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public final int zzh() {
        zza();
        return this.zzk;
    }

    /* access modifiers changed from: package-private */
    public final int zzi() {
        zza();
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final long zzj() {
        return this.zzh;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x01b8  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01ef  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01f7  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x024d  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0251 A[SYNTHETIC, Splitter:B:75:0x0251] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x02a6  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x02b1  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x02c0  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0306  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzr zzk(java.lang.String r52) {
        /*
            r51 = this;
            r1 = r51
            r51.zzg()
            com.google.android.gms.measurement.internal.zzr r44 = new com.google.android.gms.measurement.internal.zzr
            java.lang.String r3 = r51.zzm()
            java.lang.String r4 = r51.zzo()
            r51.zza()
            java.lang.String r5 = r1.zzb
            r51.zza()
            int r0 = r1.zzc
            long r6 = (long) r0
            r51.zza()
            java.lang.String r0 = r1.zzd
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)
            java.lang.String r8 = r1.zzd
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu
            com.google.android.gms.measurement.internal.zzam r2 = r0.zzf()
            r2.zzj()
            r51.zza()
            r51.zzg()
            long r9 = r1.zzf
            r11 = 0
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            r13 = 0
            if (r2 != 0) goto L_0x00cc
            com.google.android.gms.measurement.internal.zzio r2 = r1.zzu
            com.google.android.gms.measurement.internal.zzqf r2 = r2.zzw()
            android.content.Context r9 = r0.zzaT()
            android.content.Context r0 = r0.zzaT()
            java.lang.String r0 = r0.getPackageName()
            r2.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r9)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r0)
            android.content.pm.PackageManager r10 = r9.getPackageManager()
            java.security.MessageDigest r14 = com.google.android.gms.measurement.internal.zzqf.zzI()
            if (r14 != 0) goto L_0x0073
            com.google.android.gms.measurement.internal.zzio r0 = r2.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()
            java.lang.String r2 = "Could not get MD5 instance"
            r0.zza(r2)
            r9 = -1
            goto L_0x00ca
        L_0x0073:
            if (r10 == 0) goto L_0x00c9
            boolean r0 = r2.zzam(r9, r0)     // Catch:{ NameNotFoundException -> 0x00a5 }
            if (r0 != 0) goto L_0x00b7
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r9)     // Catch:{ NameNotFoundException -> 0x00a5 }
            com.google.android.gms.measurement.internal.zzio r9 = r2.zzu     // Catch:{ NameNotFoundException -> 0x00a5 }
            android.content.Context r10 = r9.zzaT()     // Catch:{ NameNotFoundException -> 0x00a5 }
            java.lang.String r10 = r10.getPackageName()     // Catch:{ NameNotFoundException -> 0x00a5 }
            r15 = 64
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r10, r15)     // Catch:{ NameNotFoundException -> 0x00a5 }
            android.content.pm.Signature[] r0 = r0.signatures     // Catch:{ NameNotFoundException -> 0x00a5 }
            if (r0 == 0) goto L_0x00a7
            int r10 = r0.length     // Catch:{ NameNotFoundException -> 0x00a5 }
            if (r10 <= 0) goto L_0x00a7
            r0 = r0[r13]     // Catch:{ NameNotFoundException -> 0x00a5 }
            byte[] r0 = r0.toByteArray()     // Catch:{ NameNotFoundException -> 0x00a5 }
            byte[] r0 = r14.digest(r0)     // Catch:{ NameNotFoundException -> 0x00a5 }
            long r15 = com.google.android.gms.measurement.internal.zzqf.zzr(r0)     // Catch:{ NameNotFoundException -> 0x00a5 }
            goto L_0x00b8
        L_0x00a5:
            r0 = move-exception
            goto L_0x00ba
        L_0x00a7:
            com.google.android.gms.measurement.internal.zzhe r0 = r9.zzaW()     // Catch:{ NameNotFoundException -> 0x00a5 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzk()     // Catch:{ NameNotFoundException -> 0x00a5 }
            java.lang.String r9 = "Could not get signatures"
            r0.zza(r9)     // Catch:{ NameNotFoundException -> 0x00a5 }
            r15 = -1
            goto L_0x00b8
        L_0x00b7:
            r15 = r11
        L_0x00b8:
            r9 = r15
            goto L_0x00ca
        L_0x00ba:
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()
            java.lang.String r9 = "Package name not found"
            r2.zzb(r9, r0)
        L_0x00c9:
            r9 = r11
        L_0x00ca:
            r1.zzf = r9
        L_0x00cc:
            r14 = r9
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu
            boolean r16 = r0.zzJ()
            com.google.android.gms.measurement.internal.zzht r2 = r0.zzm()
            boolean r2 = r2.zzm
            r9 = 1
            r17 = r2 ^ 1
            r51.zzg()
            boolean r2 = r0.zzJ()
            r10 = 0
            if (r2 != 0) goto L_0x00e9
        L_0x00e6:
            r0 = r10
            goto L_0x0163
        L_0x00e9:
            com.google.android.gms.internal.measurement.zzrp.zzb()
            com.google.android.gms.measurement.internal.zzam r2 = r0.zzf()
            com.google.android.gms.measurement.internal.zzgg r9 = com.google.android.gms.measurement.internal.zzgi.zzaG
            boolean r2 = r2.zzx(r10, r9)
            if (r2 == 0) goto L_0x0108
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()
            java.lang.String r2 = "Disabled IID for tests."
            r0.zza(r2)
            goto L_0x00e6
        L_0x0108:
            android.content.Context r0 = r0.zzaT()     // Catch:{ ClassNotFoundException -> 0x00e6 }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x00e6 }
            java.lang.String r2 = "com.google.firebase.analytics.FirebaseAnalytics"
            java.lang.Class r0 = r0.loadClass(r2)     // Catch:{ ClassNotFoundException -> 0x00e6 }
            if (r0 != 0) goto L_0x0119
            goto L_0x00e6
        L_0x0119:
            java.lang.String r2 = "getInstance"
            java.lang.Class<android.content.Context> r9 = android.content.Context.class
            java.lang.Class[] r9 = new java.lang.Class[]{r9}     // Catch:{ Exception -> 0x0153 }
            java.lang.reflect.Method r2 = r0.getDeclaredMethod(r2, r9)     // Catch:{ Exception -> 0x0153 }
            com.google.android.gms.measurement.internal.zzio r9 = r1.zzu     // Catch:{ Exception -> 0x0153 }
            android.content.Context r9 = r9.zzaT()     // Catch:{ Exception -> 0x0153 }
            java.lang.Object[] r9 = new java.lang.Object[]{r9}     // Catch:{ Exception -> 0x0153 }
            java.lang.Object r2 = r2.invoke(r10, r9)     // Catch:{ Exception -> 0x0153 }
            if (r2 != 0) goto L_0x0136
            goto L_0x00e6
        L_0x0136:
            java.lang.String r9 = "getFirebaseInstanceId"
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r9, r10)     // Catch:{ Exception -> 0x0143 }
            java.lang.Object r0 = r0.invoke(r2, r10)     // Catch:{ Exception -> 0x0143 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x0143 }
            goto L_0x0163
        L_0x0143:
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzl()
            java.lang.String r2 = "Failed to retrieve Firebase Instance Id"
            r0.zza(r2)
            goto L_0x00e6
        L_0x0153:
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzm()
            java.lang.String r2 = "Failed to obtain Firebase Analytics instance"
            r0.zza(r2)
            goto L_0x00e6
        L_0x0163:
            com.google.android.gms.measurement.internal.zzio r2 = r1.zzu
            com.google.android.gms.measurement.internal.zzht r9 = r2.zzm()
            com.google.android.gms.measurement.internal.zzhp r9 = r9.zzc
            r20 = r14
            long r13 = r9.zza()
            int r9 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r9 != 0) goto L_0x017a
            long r13 = r2.zza
            r24 = r13
            goto L_0x0182
        L_0x017a:
            long r10 = r2.zza
            long r10 = java.lang.Math.min(r10, r13)
            r24 = r10
        L_0x0182:
            r51.zza()
            int r15 = r1.zzk
            com.google.android.gms.measurement.internal.zzio r2 = r1.zzu
            com.google.android.gms.measurement.internal.zzam r10 = r2.zzf()
            boolean r26 = r10.zzw()
            com.google.android.gms.measurement.internal.zzht r10 = r2.zzm()
            r10.zzg()
            android.content.SharedPreferences r10 = r10.zzb()
            java.lang.String r11 = "deferred_analytics_collection"
            r12 = 0
            boolean r28 = r10.getBoolean(r11, r12)
            java.lang.String r29 = r51.zzl()
            com.google.android.gms.measurement.internal.zzam r10 = r2.zzf()
            java.lang.String r11 = "google_analytics_default_allow_ad_personalization_signals"
            r12 = 1
            com.google.android.gms.measurement.internal.zzju r10 = r10.zzm(r11, r12)
            com.google.android.gms.measurement.internal.zzju r12 = com.google.android.gms.measurement.internal.zzju.GRANTED
            if (r10 == r12) goto L_0x01b8
            r12 = 1
            goto L_0x01b9
        L_0x01b8:
            r12 = 0
        L_0x01b9:
            long r13 = r1.zzg
            java.lang.Boolean r30 = java.lang.Boolean.valueOf(r12)
            java.util.List r12 = r1.zzi
            com.google.android.gms.measurement.internal.zzht r10 = r2.zzm()
            com.google.android.gms.measurement.internal.zzjx r10 = r10.zzh()
            java.lang.String r31 = r10.zzq()
            java.lang.String r10 = r1.zzj
            if (r10 != 0) goto L_0x01db
            com.google.android.gms.measurement.internal.zzqf r10 = r2.zzw()
            java.lang.String r10 = r10.zzF()
            r1.zzj = r10
        L_0x01db:
            java.lang.String r10 = r1.zzj
            com.google.android.gms.measurement.internal.zzht r27 = r2.zzm()
            com.google.android.gms.measurement.internal.zzjx r9 = r27.zzh()
            r27 = r10
            com.google.android.gms.measurement.internal.zzjw r10 = com.google.android.gms.measurement.internal.zzjw.ANALYTICS_STORAGE
            boolean r9 = r9.zzr(r10)
            if (r9 != 0) goto L_0x01f7
            r33 = r13
            r22 = 0
            r35 = 0
            r14 = r12
            goto L_0x0231
        L_0x01f7:
            r51.zzg()
            long r9 = r1.zzo
            r22 = 0
            int r9 = (r9 > r22 ? 1 : (r9 == r22 ? 0 : -1))
            if (r9 != 0) goto L_0x0206
            r33 = r13
            r14 = r12
            goto L_0x0226
        L_0x0206:
            com.google.android.gms.common.util.Clock r9 = r2.zzaU()
            long r9 = r9.currentTimeMillis()
            r33 = r13
            r14 = r12
            long r12 = r1.zzo
            long r9 = r9 - r12
            java.lang.String r12 = r1.zzn
            if (r12 == 0) goto L_0x0226
            r12 = 86400000(0x5265c00, double:4.2687272E-316)
            int r9 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r9 <= 0) goto L_0x0226
            java.lang.String r9 = r1.zzp
            if (r9 != 0) goto L_0x0226
            r51.zzq()
        L_0x0226:
            java.lang.String r9 = r1.zzn
            if (r9 != 0) goto L_0x022d
            r51.zzq()
        L_0x022d:
            java.lang.String r9 = r1.zzn
            r35 = r9
        L_0x0231:
            com.google.android.gms.measurement.internal.zzam r9 = r2.zzf()
            boolean r36 = r9.zzE()
            com.google.android.gms.measurement.internal.zzqf r2 = r2.zzw()
            java.lang.String r9 = r51.zzm()
            com.google.android.gms.measurement.internal.zzio r10 = r2.zzu
            android.content.Context r12 = r10.zzaT()
            android.content.pm.PackageManager r12 = r12.getPackageManager()
            if (r12 != 0) goto L_0x0251
            r37 = r22
            r12 = 0
            goto L_0x027c
        L_0x0251:
            android.content.Context r10 = r10.zzaT()     // Catch:{ NameNotFoundException -> 0x0265 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r10 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r10)     // Catch:{ NameNotFoundException -> 0x0265 }
            r12 = 0
            android.content.pm.ApplicationInfo r10 = r10.getApplicationInfo(r9, r12)     // Catch:{ NameNotFoundException -> 0x0266 }
            if (r10 == 0) goto L_0x0263
            int r2 = r10.targetSdkVersion     // Catch:{ NameNotFoundException -> 0x0266 }
            goto L_0x0279
        L_0x0263:
            r2 = r12
            goto L_0x0279
        L_0x0265:
            r12 = 0
        L_0x0266:
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu
            r2.zzaV()
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzi()
            java.lang.String r10 = "PackageManager failed to find running app: app_id"
            r2.zzb(r10, r9)
            goto L_0x0263
        L_0x0279:
            long r9 = (long) r2
            r37 = r9
        L_0x027c:
            com.google.android.gms.measurement.internal.zzio r2 = r1.zzu
            com.google.android.gms.measurement.internal.zzht r9 = r2.zzm()
            com.google.android.gms.measurement.internal.zzjx r9 = r9.zzh()
            int r39 = r9.zzb()
            com.google.android.gms.measurement.internal.zzht r9 = r2.zzm()
            com.google.android.gms.measurement.internal.zzba r9 = r9.zzf()
            java.lang.String r40 = r9.zzj()
            com.google.android.gms.internal.measurement.zzqr.zzb()
            com.google.android.gms.measurement.internal.zzam r9 = r2.zzf()
            com.google.android.gms.measurement.internal.zzgg r10 = com.google.android.gms.measurement.internal.zzgi.zzaW
            r13 = 0
            boolean r9 = r9.zzx(r13, r10)
            if (r9 == 0) goto L_0x02b1
            com.google.android.gms.measurement.internal.zzqf r9 = r2.zzw()
            int r9 = r9.zzl()
            r41 = r9
            goto L_0x02b3
        L_0x02b1:
            r41 = r12
        L_0x02b3:
            com.google.android.gms.internal.measurement.zzqr.zzb()
            com.google.android.gms.measurement.internal.zzam r9 = r2.zzf()
            boolean r10 = r9.zzx(r13, r10)
            if (r10 == 0) goto L_0x02c8
            com.google.android.gms.measurement.internal.zzqf r10 = r2.zzw()
            long r22 = r10.zzq()
        L_0x02c8:
            r42 = r22
            com.google.android.gms.measurement.internal.zzam r10 = r2.zzf()
            java.lang.String r45 = r10.zzs()
            com.google.android.gms.measurement.internal.zzam r10 = r2.zzf()
            r13 = 1
            com.google.android.gms.measurement.internal.zzju r10 = r10.zzm(r11, r13)
            com.google.android.gms.measurement.internal.zze r11 = new com.google.android.gms.measurement.internal.zze
            r11.<init>(r10)
            java.lang.String r46 = r11.zzc()
            com.google.android.gms.measurement.internal.zzio r10 = r1.zzu
            long r10 = r10.zza
            com.google.android.gms.measurement.internal.zzam r2 = r2.zzf()
            com.google.android.gms.measurement.internal.zzgg r13 = com.google.android.gms.measurement.internal.zzgi.zzaR
            r9 = 0
            boolean r2 = r2.zzx(r9, r13)
            if (r2 == 0) goto L_0x0306
            com.google.android.gms.measurement.internal.zzio r2 = r1.zzu
            com.google.android.gms.measurement.internal.zzmd r2 = r2.zzs()
            com.google.android.gms.internal.measurement.zzih r2 = r2.zzi()
            int r2 = r2.zza()
            r47 = r2
            goto L_0x0308
        L_0x0306:
            r47 = r12
        L_0x0308:
            r12 = 119002(0x1d0da, double:5.8795E-319)
            r48 = r10
            r32 = r27
            r9 = r12
            r27 = 0
            r2 = r44
            r50 = r14
            r11 = r20
            r13 = r52
            r14 = r16
            r19 = r15
            r15 = r17
            r16 = r0
            r17 = r24
            r20 = r26
            r21 = r28
            r22 = r29
            r23 = r30
            r24 = r33
            r26 = r50
            r28 = r31
            r29 = r32
            r30 = r35
            r31 = r36
            r32 = r37
            r34 = r39
            r35 = r40
            r36 = r41
            r37 = r42
            r39 = r45
            r40 = r46
            r41 = r48
            r43 = r47
            r2.<init>((java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r5, (long) r6, (java.lang.String) r8, (long) r9, (long) r11, (java.lang.String) r13, (boolean) r14, (boolean) r15, (java.lang.String) r16, (long) r17, (int) r19, (boolean) r20, (boolean) r21, (java.lang.String) r22, (java.lang.Boolean) r23, (long) r24, (java.util.List) r26, (java.lang.String) r27, (java.lang.String) r28, (java.lang.String) r29, (java.lang.String) r30, (boolean) r31, (long) r32, (int) r34, (java.lang.String) r35, (int) r36, (long) r37, (java.lang.String) r39, (java.lang.String) r40, (long) r41, (int) r43)
            return r44
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgs.zzk(java.lang.String):com.google.android.gms.measurement.internal.zzr");
    }

    /* access modifiers changed from: package-private */
    public final String zzl() {
        zza();
        if (this.zzu.zzf().zzx((String) null, zzgi.zzbp)) {
            return null;
        }
        return this.zzm;
    }

    /* access modifiers changed from: package-private */
    public final String zzm() {
        zza();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final String zzn() {
        zza();
        Preconditions.checkNotNull(this.zze);
        return this.zze;
    }

    /* access modifiers changed from: package-private */
    public final String zzo() {
        zzg();
        zza();
        Preconditions.checkNotNull(this.zzl);
        return this.zzl;
    }

    /* access modifiers changed from: package-private */
    public final List zzp() {
        return this.zzi;
    }

    /* access modifiers changed from: package-private */
    public final void zzq() {
        String str;
        String str2;
        zzg();
        zzio zzio = this.zzu;
        if (!zzio.zzm().zzh().zzr(zzjw.ANALYTICS_STORAGE)) {
            zzio.zzaW().zzd().zza("Analytics Storage consent is not granted");
            str = null;
        } else {
            byte[] bArr = new byte[16];
            zzio.zzw().zzJ().nextBytes(bArr);
            str = String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
        }
        zzhc zzd2 = zzio.zzaW().zzd();
        if (str == null) {
            str2 = "null";
        } else {
            str2 = "not null";
        }
        zzd2.zza(String.format("Resetting session stitching token to %s", new Object[]{str2}));
        this.zzn = str;
        this.zzo = zzio.zzaU().currentTimeMillis();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzr(String str) {
        String str2 = this.zzp;
        boolean z = false;
        if (str2 != null && !str2.equals(str)) {
            z = true;
        }
        this.zzp = str;
        return z;
    }
}
