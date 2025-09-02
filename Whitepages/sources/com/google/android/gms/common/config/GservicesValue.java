package com.google.android.gms.common.config;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class GservicesValue<T> {
    private static final Object zzc = new Object();
    protected final String zza;
    protected final Object zzb;
    private Object zzd = null;

    protected GservicesValue(String str, Object obj) {
        this.zza = str;
        this.zzb = obj;
    }

    @KeepForSdk
    public static boolean isInitialized() {
        synchronized (zzc) {
        }
        return false;
    }

    @KeepForSdk
    public static GservicesValue<Float> value(String str, Float f) {
        return new zzd(str, f);
    }

    @KeepForSdk
    public static GservicesValue<Integer> value(String str, Integer num) {
        return new zzc(str, num);
    }

    @KeepForSdk
    public static GservicesValue<Long> value(String str, Long l) {
        return new zzb(str, l);
    }

    @KeepForSdk
    public static GservicesValue<String> value(String str, String str2) {
        return new zze(str, str2);
    }

    @KeepForSdk
    public static GservicesValue<Boolean> value(String str, boolean z) {
        return new zza(str, Boolean.valueOf(z));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0016, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = android.os.Binder.clearCallingIdentity();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3 = zza(r4.zza);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        android.os.Binder.restoreCallingIdentity(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0025, code lost:
        r1 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        android.os.Binder.restoreCallingIdentity(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x002e, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x002f, code lost:
        android.os.StrictMode.setThreadPolicy(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0032, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0018 */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T get() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.zzd
            if (r0 == 0) goto L_0x0005
            return r0
        L_0x0005:
            android.os.StrictMode$ThreadPolicy r0 = android.os.StrictMode.allowThreadDiskReads()
            java.lang.Object r1 = zzc
            monitor-enter(r1)
            monitor-exit(r1)     // Catch:{ all -> 0x0036 }
            monitor-enter(r1)
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            java.lang.String r1 = r4.zza     // Catch:{ SecurityException -> 0x0018 }
            java.lang.Object r1 = r4.zza(r1)     // Catch:{ SecurityException -> 0x0018 }
            goto L_0x0026
        L_0x0016:
            r1 = move-exception
            goto L_0x002f
        L_0x0018:
            long r1 = android.os.Binder.clearCallingIdentity()     // Catch:{ all -> 0x0016 }
            java.lang.String r3 = r4.zza     // Catch:{ all -> 0x002a }
            java.lang.Object r3 = r4.zza(r3)     // Catch:{ all -> 0x002a }
            android.os.Binder.restoreCallingIdentity(r1)     // Catch:{ all -> 0x0016 }
            r1 = r3
        L_0x0026:
            android.os.StrictMode.setThreadPolicy(r0)
            return r1
        L_0x002a:
            r3 = move-exception
            android.os.Binder.restoreCallingIdentity(r1)     // Catch:{ all -> 0x0016 }
            throw r3     // Catch:{ all -> 0x0016 }
        L_0x002f:
            android.os.StrictMode.setThreadPolicy(r0)
            throw r1
        L_0x0033:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0033 }
            throw r0
        L_0x0036:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0036 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.config.GservicesValue.get():java.lang.Object");
    }

    @KeepForSdk
    @Deprecated
    public final T getBinderSafe() {
        return get();
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    @com.google.android.gms.common.annotation.KeepForSdk
    public void override(T r3) {
        /*
            r2 = this;
            java.lang.String r0 = "GservicesValue"
            java.lang.String r1 = "GservicesValue.override(): test should probably call initForTests() first"
            android.util.Log.w(r0, r1)
            r2.zzd = r3
            java.lang.Object r3 = zzc
            monitor-enter(r3)
            monitor-enter(r3)     // Catch:{ all -> 0x0010 }
            monitor-exit(r3)     // Catch:{ all -> 0x0012 }
            monitor-exit(r3)     // Catch:{ all -> 0x0010 }
            return
        L_0x0010:
            r0 = move-exception
            goto L_0x0015
        L_0x0012:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0012 }
            throw r0     // Catch:{ all -> 0x0010 }
        L_0x0015:
            monitor-exit(r3)     // Catch:{ all -> 0x0010 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.config.GservicesValue.override(java.lang.Object):void");
    }

    @KeepForSdk
    public void resetOverride() {
        this.zzd = null;
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(String str);
}
