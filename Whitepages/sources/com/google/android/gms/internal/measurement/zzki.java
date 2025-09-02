package com.google.android.gms.internal.measurement;

import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzki {
    public static final /* synthetic */ int zzc = 0;
    private static final Object zzd = new Object();
    private static volatile zzkg zze = null;
    private static volatile boolean zzf = false;
    private static final AtomicInteger zzg = new AtomicInteger();
    final zzkf zza;
    final String zzb;
    private Object zzh;
    private volatile int zzi = -1;
    private volatile Object zzj;
    private volatile boolean zzk;

    static {
        new AtomicReference();
        Preconditions.checkNotNull(new zzka(), "BuildInfo must be non-null");
    }

    /* synthetic */ zzki(zzkf zzkf, String str, Object obj, boolean z, zzkh zzkh) {
        if (zzkf.zza != null) {
            this.zza = zzkf;
            this.zzb = str;
            this.zzh = obj;
            this.zzk = false;
            return;
        }
        throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
    }

    public static void zzc() {
        zzg.incrementAndGet();
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
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
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
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public static void zzd(android.content.Context r3) {
        /*
            com.google.android.gms.internal.measurement.zzkg r0 = zze
            if (r0 != 0) goto L_0x004d
            if (r3 != 0) goto L_0x0007
            goto L_0x004d
        L_0x0007:
            java.lang.Object r0 = zzd
            monitor-enter(r0)
            com.google.android.gms.internal.measurement.zzkg r1 = zze     // Catch:{ all -> 0x0047 }
            if (r1 != 0) goto L_0x0049
            monitor-enter(r0)     // Catch:{ all -> 0x0047 }
            com.google.android.gms.internal.measurement.zzkg r1 = zze     // Catch:{ all -> 0x0021 }
            android.content.Context r2 = r3.getApplicationContext()     // Catch:{ all -> 0x0021 }
            if (r2 == 0) goto L_0x0018
            r3 = r2
        L_0x0018:
            if (r1 == 0) goto L_0x0023
            android.content.Context r2 = r1.zza()     // Catch:{ all -> 0x0021 }
            if (r2 == r3) goto L_0x0043
            goto L_0x0023
        L_0x0021:
            r3 = move-exception
            goto L_0x0045
        L_0x0023:
            if (r1 == 0) goto L_0x002e
            com.google.android.gms.internal.measurement.zzjm.zze()     // Catch:{ all -> 0x0021 }
            com.google.android.gms.internal.measurement.zzkk.zzd()     // Catch:{ all -> 0x0021 }
            com.google.android.gms.internal.measurement.zzju.zze()     // Catch:{ all -> 0x0021 }
        L_0x002e:
            com.google.android.gms.internal.measurement.zzjz r1 = new com.google.android.gms.internal.measurement.zzjz     // Catch:{ all -> 0x0021 }
            r1.<init>(r3)     // Catch:{ all -> 0x0021 }
            com.google.common.base.Supplier r1 = com.google.common.base.Suppliers.memoize(r1)     // Catch:{ all -> 0x0021 }
            com.google.android.gms.internal.measurement.zzjj r2 = new com.google.android.gms.internal.measurement.zzjj     // Catch:{ all -> 0x0021 }
            r2.<init>(r3, r1)     // Catch:{ all -> 0x0021 }
            zze = r2     // Catch:{ all -> 0x0021 }
            java.util.concurrent.atomic.AtomicInteger r3 = zzg     // Catch:{ all -> 0x0021 }
            r3.incrementAndGet()     // Catch:{ all -> 0x0021 }
        L_0x0043:
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            goto L_0x0049
        L_0x0045:
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            throw r3     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r3 = move-exception
            goto L_0x004b
        L_0x0049:
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            return
        L_0x004b:
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            throw r3
        L_0x004d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzki.zzd(android.content.Context):void");
    }

    /* access modifiers changed from: package-private */
    public abstract Object zza(Object obj);

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004d A[Catch:{ all -> 0x0047 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004f A[Catch:{ all -> 0x0047 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005b A[Catch:{ all -> 0x0047 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0079 A[Catch:{ all -> 0x0047 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0096 A[Catch:{ all -> 0x0047 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009b A[Catch:{ all -> 0x0047 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009e A[Catch:{ all -> 0x0047 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009f A[Catch:{ all -> 0x0047 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c3 A[Catch:{ all -> 0x0047 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzb() {
        /*
            r9 = this;
            java.util.concurrent.atomic.AtomicInteger r0 = zzg
            int r0 = r0.get()
            int r1 = r9.zzi
            if (r1 >= r0) goto L_0x00d4
            monitor-enter(r9)
            int r1 = r9.zzi     // Catch:{ all -> 0x0047 }
            if (r1 >= r0) goto L_0x00d0
            com.google.android.gms.internal.measurement.zzkg r1 = zze     // Catch:{ all -> 0x0047 }
            com.google.common.base.Optional r2 = com.google.common.base.Optional.absent()     // Catch:{ all -> 0x0047 }
            r3 = 0
            if (r1 == 0) goto L_0x004a
            com.google.common.base.Supplier r4 = r1.zzb()     // Catch:{ all -> 0x0047 }
            if (r4 == 0) goto L_0x004a
            com.google.common.base.Supplier r2 = r1.zzb()     // Catch:{ all -> 0x0047 }
            java.lang.Object r2 = com.google.common.base.Preconditions.checkNotNull(r2)     // Catch:{ all -> 0x0047 }
            com.google.common.base.Supplier r2 = (com.google.common.base.Supplier) r2     // Catch:{ all -> 0x0047 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0047 }
            com.google.common.base.Optional r2 = (com.google.common.base.Optional) r2     // Catch:{ all -> 0x0047 }
            boolean r4 = r2.isPresent()     // Catch:{ all -> 0x0047 }
            if (r4 == 0) goto L_0x004a
            java.lang.Object r4 = r2.get()     // Catch:{ all -> 0x0047 }
            com.google.android.gms.internal.measurement.zzjo r4 = (com.google.android.gms.internal.measurement.zzjo) r4     // Catch:{ all -> 0x0047 }
            com.google.android.gms.internal.measurement.zzkf r5 = r9.zza     // Catch:{ all -> 0x0047 }
            android.net.Uri r6 = r5.zza     // Catch:{ all -> 0x0047 }
            java.lang.String r5 = r5.zzc     // Catch:{ all -> 0x0047 }
            java.lang.String r7 = r9.zzb     // Catch:{ all -> 0x0047 }
            java.lang.String r4 = r4.zza(r6, r3, r5, r7)     // Catch:{ all -> 0x0047 }
            goto L_0x004b
        L_0x0047:
            r0 = move-exception
            goto L_0x00d2
        L_0x004a:
            r4 = r3
        L_0x004b:
            if (r1 == 0) goto L_0x004f
            r5 = 1
            goto L_0x0050
        L_0x004f:
            r5 = 0
        L_0x0050:
            java.lang.String r6 = "Must call PhenotypeFlagInitializer.maybeInit() first"
            com.google.common.base.Preconditions.checkState(r5, r6)     // Catch:{ all -> 0x0047 }
            com.google.android.gms.internal.measurement.zzkf r5 = r9.zza     // Catch:{ all -> 0x0047 }
            android.net.Uri r6 = r5.zza     // Catch:{ all -> 0x0047 }
            if (r6 == 0) goto L_0x0079
            android.content.Context r7 = r1.zza()     // Catch:{ all -> 0x0047 }
            boolean r7 = com.google.android.gms.internal.measurement.zzjw.zza(r7, r6)     // Catch:{ all -> 0x0047 }
            if (r7 == 0) goto L_0x0077
            android.content.Context r7 = r1.zza()     // Catch:{ all -> 0x0047 }
            android.content.ContentResolver r7 = r7.getContentResolver()     // Catch:{ all -> 0x0047 }
            com.google.android.gms.internal.measurement.zzjy r8 = new com.google.android.gms.internal.measurement.zzjy     // Catch:{ all -> 0x0047 }
            r8.<init>()     // Catch:{ all -> 0x0047 }
            com.google.android.gms.internal.measurement.zzjm r6 = com.google.android.gms.internal.measurement.zzjm.zza(r7, r6, r8)     // Catch:{ all -> 0x0047 }
            goto L_0x008c
        L_0x0077:
            r6 = r3
            goto L_0x008c
        L_0x0079:
            android.content.Context r6 = r1.zza()     // Catch:{ all -> 0x0047 }
            java.lang.Object r7 = com.google.common.base.Preconditions.checkNotNull(r3)     // Catch:{ all -> 0x0047 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x0047 }
            com.google.android.gms.internal.measurement.zzjy r8 = new com.google.android.gms.internal.measurement.zzjy     // Catch:{ all -> 0x0047 }
            r8.<init>()     // Catch:{ all -> 0x0047 }
            com.google.android.gms.internal.measurement.zzkk r6 = com.google.android.gms.internal.measurement.zzkk.zza(r6, r7, r8)     // Catch:{ all -> 0x0047 }
        L_0x008c:
            if (r6 == 0) goto L_0x009b
            java.lang.String r7 = r9.zzb     // Catch:{ all -> 0x0047 }
            java.lang.Object r6 = r6.zzb(r7)     // Catch:{ all -> 0x0047 }
            if (r6 == 0) goto L_0x009b
            java.lang.Object r6 = r9.zza(r6)     // Catch:{ all -> 0x0047 }
            goto L_0x009c
        L_0x009b:
            r6 = r3
        L_0x009c:
            if (r6 == 0) goto L_0x009f
            goto L_0x00bd
        L_0x009f:
            boolean r5 = r5.zzd     // Catch:{ all -> 0x0047 }
            if (r5 != 0) goto L_0x00b7
            android.content.Context r1 = r1.zza()     // Catch:{ all -> 0x0047 }
            com.google.android.gms.internal.measurement.zzju r1 = com.google.android.gms.internal.measurement.zzju.zza(r1)     // Catch:{ all -> 0x0047 }
            java.lang.String r5 = r9.zzb     // Catch:{ all -> 0x0047 }
            java.lang.String r1 = r1.zzb(r5)     // Catch:{ all -> 0x0047 }
            if (r1 == 0) goto L_0x00b7
            java.lang.Object r3 = r9.zza(r1)     // Catch:{ all -> 0x0047 }
        L_0x00b7:
            if (r3 != 0) goto L_0x00bc
            java.lang.Object r6 = r9.zzh     // Catch:{ all -> 0x0047 }
            goto L_0x00bd
        L_0x00bc:
            r6 = r3
        L_0x00bd:
            boolean r1 = r2.isPresent()     // Catch:{ all -> 0x0047 }
            if (r1 == 0) goto L_0x00cc
            if (r4 != 0) goto L_0x00c8
            java.lang.Object r6 = r9.zzh     // Catch:{ all -> 0x0047 }
            goto L_0x00cc
        L_0x00c8:
            java.lang.Object r6 = r9.zza(r4)     // Catch:{ all -> 0x0047 }
        L_0x00cc:
            r9.zzj = r6     // Catch:{ all -> 0x0047 }
            r9.zzi = r0     // Catch:{ all -> 0x0047 }
        L_0x00d0:
            monitor-exit(r9)     // Catch:{ all -> 0x0047 }
            goto L_0x00d4
        L_0x00d2:
            monitor-exit(r9)     // Catch:{ all -> 0x0047 }
            throw r0
        L_0x00d4:
            java.lang.Object r0 = r9.zzj
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzki.zzb():java.lang.Object");
    }
}
