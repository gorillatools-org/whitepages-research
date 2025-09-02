package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdj;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class zzmo extends zzg {
    protected zzmh zza;
    private volatile zzmh zzb;
    private volatile zzmh zzc;
    private final Map zzd = new ConcurrentHashMap();
    private zzdj zze;
    private volatile boolean zzf;
    private volatile zzmh zzg;
    /* access modifiers changed from: private */
    public zzmh zzh;
    private boolean zzi;
    private final Object zzj = new Object();

    public zzmo(zzio zzio) {
        super(zzio);
    }

    private final void zzA(String str, zzmh zzmh, boolean z) {
        zzmh zzmh2;
        zzmh zzmh3;
        String str2 = str;
        zzmh zzmh4 = zzmh;
        if (this.zzb == null) {
            zzmh2 = this.zzc;
        } else {
            zzmh2 = this.zzb;
        }
        zzmh zzmh5 = zzmh2;
        if (zzmh4.zzb == null) {
            zzmh3 = new zzmh(zzmh4.zza, str2 != null ? zzl(str2, "Activity") : null, zzmh4.zzc, zzmh4.zze, zzmh4.zzf);
        } else {
            zzmh3 = zzmh4;
        }
        this.zzc = this.zzb;
        this.zzb = zzmh3;
        zzio zzio = this.zzu;
        zzio.zzaX().zzq(new zzmj(this, zzmh3, zzmh5, zzio.zzaU().elapsedRealtime(), z));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00d1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzB(com.google.android.gms.measurement.internal.zzmh r16, com.google.android.gms.measurement.internal.zzmh r17, long r18, boolean r20, android.os.Bundle r21) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            r5 = r21
            r15.zzg()
            r6 = 0
            r7 = 1
            if (r2 == 0) goto L_0x002c
            long r8 = r1.zzc
            long r10 = r2.zzc
            int r8 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r8 != 0) goto L_0x002c
            java.lang.String r8 = r2.zzb
            java.lang.String r9 = r1.zzb
            boolean r8 = java.util.Objects.equals(r8, r9)
            if (r8 == 0) goto L_0x002c
            java.lang.String r8 = r2.zza
            java.lang.String r9 = r1.zza
            boolean r8 = java.util.Objects.equals(r8, r9)
            if (r8 != 0) goto L_0x002e
        L_0x002c:
            r8 = r7
            goto L_0x002f
        L_0x002e:
            r8 = r6
        L_0x002f:
            if (r20 == 0) goto L_0x0036
            com.google.android.gms.measurement.internal.zzmh r9 = r0.zza
            if (r9 == 0) goto L_0x0036
            r6 = r7
        L_0x0036:
            if (r8 == 0) goto L_0x00c4
            if (r5 == 0) goto L_0x0041
            android.os.Bundle r8 = new android.os.Bundle
            r8.<init>(r5)
        L_0x003f:
            r14 = r8
            goto L_0x0047
        L_0x0041:
            android.os.Bundle r8 = new android.os.Bundle
            r8.<init>()
            goto L_0x003f
        L_0x0047:
            com.google.android.gms.measurement.internal.zzqf.zzN(r1, r14, r7)
            if (r2 == 0) goto L_0x0065
            java.lang.String r5 = r2.zza
            if (r5 == 0) goto L_0x0055
            java.lang.String r8 = "_pn"
            r14.putString(r8, r5)
        L_0x0055:
            java.lang.String r5 = r2.zzb
            if (r5 == 0) goto L_0x005e
            java.lang.String r8 = "_pc"
            r14.putString(r8, r5)
        L_0x005e:
            long r8 = r2.zzc
            java.lang.String r2 = "_pi"
            r14.putLong(r2, r8)
        L_0x0065:
            r8 = 0
            if (r6 == 0) goto L_0x0084
            com.google.android.gms.measurement.internal.zzio r2 = r0.zzu
            com.google.android.gms.measurement.internal.zzop r2 = r2.zzv()
            com.google.android.gms.measurement.internal.zzon r2 = r2.zzb
            long r10 = r2.zzb
            long r10 = r3 - r10
            r2.zzb = r3
            int r2 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r2 <= 0) goto L_0x0084
            com.google.android.gms.measurement.internal.zzio r2 = r0.zzu
            com.google.android.gms.measurement.internal.zzqf r2 = r2.zzw()
            r2.zzL(r14, r10)
        L_0x0084:
            com.google.android.gms.measurement.internal.zzio r2 = r0.zzu
            com.google.android.gms.measurement.internal.zzam r5 = r2.zzf()
            boolean r5 = r5.zzz()
            if (r5 != 0) goto L_0x0097
            java.lang.String r5 = "_mst"
            r10 = 1
            r14.putLong(r5, r10)
        L_0x0097:
            boolean r5 = r1.zze
            if (r7 == r5) goto L_0x009e
            java.lang.String r10 = "auto"
            goto L_0x00a0
        L_0x009e:
            java.lang.String r10 = "app"
        L_0x00a0:
            com.google.android.gms.common.util.Clock r2 = r2.zzaU()
            long r11 = r2.currentTimeMillis()
            if (r5 == 0) goto L_0x00b5
            r20 = r11
            long r11 = r1.zzf
            int r2 = (r11 > r8 ? 1 : (r11 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x00b3
            goto L_0x00b7
        L_0x00b3:
            r12 = r11
            goto L_0x00b9
        L_0x00b5:
            r20 = r11
        L_0x00b7:
            r12 = r20
        L_0x00b9:
            com.google.android.gms.measurement.internal.zzio r2 = r0.zzu
            java.lang.String r11 = "_vs"
            com.google.android.gms.measurement.internal.zzlw r9 = r2.zzq()
            r9.zzS(r10, r11, r12, r14)
        L_0x00c4:
            if (r6 == 0) goto L_0x00cb
            com.google.android.gms.measurement.internal.zzmh r2 = r0.zza
            r15.zzC(r2, r7, r3)
        L_0x00cb:
            r0.zza = r1
            boolean r2 = r1.zze
            if (r2 == 0) goto L_0x00d3
            r0.zzh = r1
        L_0x00d3:
            com.google.android.gms.measurement.internal.zzio r2 = r0.zzu
            com.google.android.gms.measurement.internal.zzny r2 = r2.zzu()
            r2.zzS(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzmo.zzB(com.google.android.gms.measurement.internal.zzmh, com.google.android.gms.measurement.internal.zzmh, long, boolean, android.os.Bundle):void");
    }

    /* access modifiers changed from: private */
    public final void zzC(zzmh zzmh, boolean z, long j) {
        zzio zzio = this.zzu;
        zzio.zzd().zzf(zzio.zzaU().elapsedRealtime());
        if (zzio.zzv().zzb.zzd(zzmh != null && zzmh.zzd, z, j) && zzmh != null) {
            zzmh.zzd = false;
        }
    }

    static /* bridge */ /* synthetic */ void zzq(zzmo zzmo, Bundle bundle, zzmh zzmh, zzmh zzmh2, long j) {
        bundle.remove("screen_name");
        bundle.remove(FirebaseAnalytics.Param.SCREEN_CLASS);
        zzmo.zzB(zzmh, zzmh2, j, true, zzmo.zzu.zzw().zzA((String) null, FirebaseAnalytics.Event.SCREEN_VIEW, bundle, (List) null, false));
    }

    private final zzmh zzz(zzdj zzdj) {
        Preconditions.checkNotNull(zzdj);
        Integer valueOf = Integer.valueOf(zzdj.zza);
        Map map = this.zzd;
        zzmh zzmh = (zzmh) map.get(valueOf);
        if (zzmh == null) {
            zzmh zzmh2 = new zzmh((String) null, zzl(zzdj.zzb, "Activity"), this.zzu.zzw().zzs());
            map.put(valueOf, zzmh2);
            zzmh = zzmh2;
        }
        return this.zzg != null ? this.zzg : zzmh;
    }

    /* access modifiers changed from: protected */
    public final boolean zzf() {
        return false;
    }

    public final zzmh zzi() {
        return this.zzb;
    }

    public final zzmh zzj(boolean z) {
        zza();
        zzg();
        if (!z) {
            return this.zza;
        }
        zzmh zzmh = this.zza;
        return zzmh != null ? zzmh : this.zzh;
    }

    /* access modifiers changed from: package-private */
    public final String zzl(String str, String str2) {
        String str3;
        if (str == null) {
            return "Activity";
        }
        String[] split = str.split("\\.");
        int length = split.length;
        if (length > 0) {
            str3 = split[length - 1];
        } else {
            str3 = "";
        }
        zzio zzio = this.zzu;
        return str3.length() > zzio.zzf().zzc((String) null, false) ? str3.substring(0, zzio.zzf().zzc((String) null, false)) : str3;
    }

    public final void zzs(zzdj zzdj, Bundle bundle) {
        Bundle bundle2;
        if (this.zzu.zzf().zzz() && bundle != null && (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service")) != null) {
            this.zzd.put(Integer.valueOf(zzdj.zza), new zzmh(bundle2.getString("name"), bundle2.getString("referrer_name"), bundle2.getLong("id")));
        }
    }

    public final void zzt(zzdj zzdj) {
        synchronized (this.zzj) {
            try {
                if (Objects.equals(this.zze, zzdj)) {
                    this.zze = null;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (this.zzu.zzf().zzz()) {
            this.zzd.remove(Integer.valueOf(zzdj.zza));
        }
    }

    public final void zzu(zzdj zzdj) {
        synchronized (this.zzj) {
            this.zzi = false;
            this.zzf = true;
        }
        zzio zzio = this.zzu;
        long elapsedRealtime = zzio.zzaU().elapsedRealtime();
        if (!zzio.zzf().zzz()) {
            this.zzb = null;
            zzio.zzaX().zzq(new zzml(this, elapsedRealtime));
            return;
        }
        zzmh zzz = zzz(zzdj);
        this.zzc = this.zzb;
        this.zzb = null;
        zzio.zzaX().zzq(new zzmm(this, zzz, elapsedRealtime));
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public final void zzv(com.google.android.gms.internal.measurement.zzdj r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.zzj
            monitor-enter(r0)
            r1 = 1
            r4.zzi = r1     // Catch:{ all -> 0x0031 }
            com.google.android.gms.internal.measurement.zzdj r1 = r4.zze     // Catch:{ all -> 0x0031 }
            boolean r1 = java.util.Objects.equals(r5, r1)     // Catch:{ all -> 0x0031 }
            r2 = 0
            if (r1 != 0) goto L_0x0036
            monitor-enter(r0)     // Catch:{ all -> 0x0031 }
            r4.zze = r5     // Catch:{ all -> 0x0033 }
            r4.zzf = r2     // Catch:{ all -> 0x0033 }
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            com.google.android.gms.measurement.internal.zzio r1 = r4.zzu     // Catch:{ all -> 0x0031 }
            com.google.android.gms.measurement.internal.zzam r3 = r1.zzf()     // Catch:{ all -> 0x0031 }
            boolean r3 = r3.zzz()     // Catch:{ all -> 0x0031 }
            if (r3 == 0) goto L_0x0036
            r3 = 0
            r4.zzg = r3     // Catch:{ all -> 0x0031 }
            com.google.android.gms.measurement.internal.zzil r1 = r1.zzaX()     // Catch:{ all -> 0x0031 }
            com.google.android.gms.measurement.internal.zzmn r3 = new com.google.android.gms.measurement.internal.zzmn     // Catch:{ all -> 0x0031 }
            r3.<init>(r4)     // Catch:{ all -> 0x0031 }
            r1.zzq(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x0036
        L_0x0031:
            r5 = move-exception
            goto L_0x007a
        L_0x0033:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            throw r5     // Catch:{ all -> 0x0031 }
        L_0x0036:
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            com.google.android.gms.measurement.internal.zzio r0 = r4.zzu
            com.google.android.gms.measurement.internal.zzam r1 = r0.zzf()
            boolean r1 = r1.zzz()
            if (r1 != 0) goto L_0x0054
            com.google.android.gms.measurement.internal.zzmh r5 = r4.zzg
            r4.zzb = r5
            com.google.android.gms.measurement.internal.zzil r5 = r0.zzaX()
            com.google.android.gms.measurement.internal.zzmk r0 = new com.google.android.gms.measurement.internal.zzmk
            r0.<init>(r4)
            r5.zzq(r0)
            return
        L_0x0054:
            com.google.android.gms.measurement.internal.zzmh r0 = r4.zzz(r5)
            java.lang.String r5 = r5.zzb
            r4.zzA(r5, r0, r2)
            com.google.android.gms.measurement.internal.zzio r5 = r4.zzu
            com.google.android.gms.measurement.internal.zzd r5 = r5.zzd()
            com.google.android.gms.measurement.internal.zzio r0 = r5.zzu
            com.google.android.gms.common.util.Clock r1 = r0.zzaU()
            long r1 = r1.elapsedRealtime()
            com.google.android.gms.measurement.internal.zzil r0 = r0.zzaX()
            com.google.android.gms.measurement.internal.zzc r3 = new com.google.android.gms.measurement.internal.zzc
            r3.<init>(r5, r1)
            r0.zzq(r3)
            return
        L_0x007a:
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzmo.zzv(com.google.android.gms.internal.measurement.zzdj):void");
    }

    public final void zzw(zzdj zzdj, Bundle bundle) {
        zzmh zzmh;
        if (this.zzu.zzf().zzz() && bundle != null && (zzmh = (zzmh) this.zzd.get(Integer.valueOf(zzdj.zza))) != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putLong("id", zzmh.zzc);
            bundle2.putString("name", zzmh.zza);
            bundle2.putString("referrer_name", zzmh.zzb);
            bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
        }
    }

    @Deprecated
    public final void zzx(zzdj zzdj, String str, String str2) {
        zzio zzio = this.zzu;
        if (!zzio.zzf().zzz()) {
            zzio.zzaW().zzl().zza("setCurrentScreen cannot be called while screen reporting is disabled.");
            return;
        }
        zzmh zzmh = this.zzb;
        if (zzmh == null) {
            zzio.zzaW().zzl().zza("setCurrentScreen cannot be called while no activity active");
            return;
        }
        Map map = this.zzd;
        Integer valueOf = Integer.valueOf(zzdj.zza);
        if (map.get(valueOf) == null) {
            zzio.zzaW().zzl().zza("setCurrentScreen must be called with an activity in the activity lifecycle");
            return;
        }
        if (str2 == null) {
            str2 = zzl(zzdj.zzb, "Activity");
        }
        String str3 = zzmh.zzb;
        String str4 = zzmh.zza;
        boolean equals = Objects.equals(str3, str2);
        boolean equals2 = Objects.equals(str4, str);
        if (equals && equals2) {
            zzio.zzaW().zzl().zza("setCurrentScreen cannot be called with the same class and name");
        } else if (str != null && (str.length() <= 0 || str.length() > zzio.zzf().zzc((String) null, false))) {
            zzio.zzaW().zzl().zzb("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
        } else if (str2 == null || (str2.length() > 0 && str2.length() <= zzio.zzf().zzc((String) null, false))) {
            zzio.zzaW().zzj().zzc("Setting current screen to name, class", str == null ? "null" : str, str2);
            zzmh zzmh2 = new zzmh(str, str2, zzio.zzw().zzs());
            map.put(valueOf, zzmh2);
            zzA(zzdj.zzb, zzmh2, true);
        } else {
            zzio.zzaW().zzl().zzb("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ca, code lost:
        r0 = r12.zzu;
        r1 = r0.zzaW().zzj();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d4, code lost:
        if (r3 != null) goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d6, code lost:
        r2 = "null";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d9, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00da, code lost:
        if (r4 != null) goto L_0x00df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00dc, code lost:
        r5 = "null";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00df, code lost:
        r5 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e0, code lost:
        r1.zzc("Logging screen view with name, class", r2, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e7, code lost:
        if (r12.zzb != null) goto L_0x00ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e9, code lost:
        r1 = r12.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ec, code lost:
        r1 = r12.zzb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ee, code lost:
        r2 = new com.google.android.gms.measurement.internal.zzmh(r3, r4, r0.zzw().zzs(), true, r14);
        r12.zzb = r2;
        r12.zzc = r1;
        r12.zzg = r2;
        r0.zzaX().zzq(new com.google.android.gms.measurement.internal.zzmi(r12, r13, r2, r1, r0.zzaU().elapsedRealtime()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x011e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzy(android.os.Bundle r13, long r14) {
        /*
            r12 = this;
            java.lang.Object r0 = r12.zzj
            monitor-enter(r0)
            boolean r1 = r12.zzi     // Catch:{ all -> 0x0018 }
            if (r1 != 0) goto L_0x001b
            com.google.android.gms.measurement.internal.zzio r13 = r12.zzu     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhe r13 = r13.zzaW()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhc r13 = r13.zzl()     // Catch:{ all -> 0x0018 }
            java.lang.String r14 = "Cannot log screen view event when the app is in the background."
            r13.zza(r14)     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return
        L_0x0018:
            r13 = move-exception
            goto L_0x011f
        L_0x001b:
            java.lang.String r1 = "screen_name"
            java.lang.String r3 = r13.getString(r1)     // Catch:{ all -> 0x0018 }
            r1 = 0
            r2 = 0
            if (r3 == 0) goto L_0x0054
            int r4 = r3.length()     // Catch:{ all -> 0x0018 }
            if (r4 <= 0) goto L_0x003b
            int r4 = r3.length()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzio r5 = r12.zzu     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzam r5 = r5.zzf()     // Catch:{ all -> 0x0018 }
            int r5 = r5.zzc(r1, r2)     // Catch:{ all -> 0x0018 }
            if (r4 <= r5) goto L_0x0054
        L_0x003b:
            com.google.android.gms.measurement.internal.zzio r13 = r12.zzu     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhe r13 = r13.zzaW()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhc r13 = r13.zzl()     // Catch:{ all -> 0x0018 }
            java.lang.String r14 = "Invalid screen name length for screen view. Length"
            int r15 = r3.length()     // Catch:{ all -> 0x0018 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ all -> 0x0018 }
            r13.zzb(r14, r15)     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return
        L_0x0054:
            java.lang.String r4 = "screen_class"
            java.lang.String r4 = r13.getString(r4)     // Catch:{ all -> 0x0018 }
            if (r4 == 0) goto L_0x008b
            int r5 = r4.length()     // Catch:{ all -> 0x0018 }
            if (r5 <= 0) goto L_0x0072
            int r5 = r4.length()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzio r6 = r12.zzu     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzam r6 = r6.zzf()     // Catch:{ all -> 0x0018 }
            int r1 = r6.zzc(r1, r2)     // Catch:{ all -> 0x0018 }
            if (r5 <= r1) goto L_0x008b
        L_0x0072:
            com.google.android.gms.measurement.internal.zzio r13 = r12.zzu     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhe r13 = r13.zzaW()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhc r13 = r13.zzl()     // Catch:{ all -> 0x0018 }
            java.lang.String r14 = "Invalid screen class length for screen view. Length"
            int r15 = r4.length()     // Catch:{ all -> 0x0018 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ all -> 0x0018 }
            r13.zzb(r14, r15)     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return
        L_0x008b:
            if (r4 != 0) goto L_0x009e
            com.google.android.gms.internal.measurement.zzdj r1 = r12.zze     // Catch:{ all -> 0x0018 }
            if (r1 == 0) goto L_0x009b
            java.lang.String r1 = r1.zzb     // Catch:{ all -> 0x0018 }
            java.lang.String r4 = "Activity"
            java.lang.String r1 = r12.zzl(r1, r4)     // Catch:{ all -> 0x0018 }
        L_0x0099:
            r4 = r1
            goto L_0x009e
        L_0x009b:
            java.lang.String r1 = "Activity"
            goto L_0x0099
        L_0x009e:
            com.google.android.gms.measurement.internal.zzmh r1 = r12.zzb     // Catch:{ all -> 0x0018 }
            boolean r5 = r12.zzf     // Catch:{ all -> 0x0018 }
            if (r5 == 0) goto L_0x00c9
            if (r1 == 0) goto L_0x00c9
            r12.zzf = r2     // Catch:{ all -> 0x0018 }
            java.lang.String r2 = r1.zzb     // Catch:{ all -> 0x0018 }
            boolean r2 = java.util.Objects.equals(r2, r4)     // Catch:{ all -> 0x0018 }
            java.lang.String r1 = r1.zza     // Catch:{ all -> 0x0018 }
            boolean r1 = java.util.Objects.equals(r1, r3)     // Catch:{ all -> 0x0018 }
            if (r2 == 0) goto L_0x00c9
            if (r1 == 0) goto L_0x00c9
            com.google.android.gms.measurement.internal.zzio r13 = r12.zzu     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhe r13 = r13.zzaW()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhc r13 = r13.zzl()     // Catch:{ all -> 0x0018 }
            java.lang.String r14 = "Ignoring call to log screen view event with duplicate parameters."
            r13.zza(r14)     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return
        L_0x00c9:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzio r0 = r12.zzu
            com.google.android.gms.measurement.internal.zzhe r1 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzj()
            if (r3 != 0) goto L_0x00d9
            java.lang.String r2 = "null"
            goto L_0x00da
        L_0x00d9:
            r2 = r3
        L_0x00da:
            if (r4 != 0) goto L_0x00df
            java.lang.String r5 = "null"
            goto L_0x00e0
        L_0x00df:
            r5 = r4
        L_0x00e0:
            java.lang.String r6 = "Logging screen view with name, class"
            r1.zzc(r6, r2, r5)
            com.google.android.gms.measurement.internal.zzmh r1 = r12.zzb
            if (r1 != 0) goto L_0x00ec
            com.google.android.gms.measurement.internal.zzmh r1 = r12.zzc
            goto L_0x00ee
        L_0x00ec:
            com.google.android.gms.measurement.internal.zzmh r1 = r12.zzb
        L_0x00ee:
            com.google.android.gms.measurement.internal.zzmh r10 = new com.google.android.gms.measurement.internal.zzmh
            com.google.android.gms.measurement.internal.zzqf r2 = r0.zzw()
            long r5 = r2.zzs()
            r7 = 1
            r2 = r10
            r8 = r14
            r2.<init>(r3, r4, r5, r7, r8)
            r12.zzb = r10
            r12.zzc = r1
            r12.zzg = r10
            com.google.android.gms.common.util.Clock r14 = r0.zzaU()
            long r14 = r14.elapsedRealtime()
            com.google.android.gms.measurement.internal.zzil r0 = r0.zzaX()
            com.google.android.gms.measurement.internal.zzmi r2 = new com.google.android.gms.measurement.internal.zzmi
            r5 = r2
            r6 = r12
            r7 = r13
            r8 = r10
            r9 = r1
            r10 = r14
            r5.<init>(r6, r7, r8, r9, r10)
            r0.zzq(r2)
            return
        L_0x011f:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzmo.zzy(android.os.Bundle, long):void");
    }
}
