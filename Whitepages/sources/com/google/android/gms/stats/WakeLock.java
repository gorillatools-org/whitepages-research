package com.google.android.gms.stats;

import android.content.Context;
import android.os.PowerManager;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.internal.stats.zzb;
import com.google.android.gms.internal.stats.zzc;
import com.google.android.gms.internal.stats.zzh;
import com.google.android.gms.internal.stats.zzi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@ShowFirstParty
@KeepForSdk
public class WakeLock {
    private static final long zzb = TimeUnit.DAYS.toMillis(366);
    private static volatile ScheduledExecutorService zzc = null;
    private static final Object zzd = new Object();
    private static volatile zzd zze = new zzb();
    zzb zza;
    private final Object zzf = new Object();
    private final PowerManager.WakeLock zzg;
    private int zzh = 0;
    private Future<?> zzi;
    private long zzj;
    private final Set<zze> zzk = new HashSet();
    private boolean zzl = true;
    private int zzm;
    private Clock zzn = DefaultClock.getInstance();
    private WorkSource zzo;
    private final String zzp;
    private final String zzq;
    private final Context zzr;
    private final Map<String, zzc> zzs = new HashMap();
    private AtomicInteger zzt = new AtomicInteger(0);
    private final ScheduledExecutorService zzu;

    @KeepForSdk
    public WakeLock(Context context, int i, String str) {
        String str2;
        String packageName = context.getPackageName();
        Preconditions.checkNotNull(context, "WakeLock: context must not be null");
        Preconditions.checkNotEmpty(str, "WakeLock: wakeLockName must not be empty");
        this.zzr = context.getApplicationContext();
        this.zzq = str;
        this.zza = null;
        if (!"com.google.android.gms".equals(context.getPackageName())) {
            String valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
                str2 = "*gcore*:".concat(valueOf);
            } else {
                str2 = new String("*gcore*:");
            }
            this.zzp = str2;
        } else {
            this.zzp = str;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager != null) {
            PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(i, str);
            this.zzg = newWakeLock;
            if (WorkSourceUtil.hasWorkSourcePermission(context)) {
                WorkSource fromPackage = WorkSourceUtil.fromPackage(context, Strings.isEmptyOrWhitespace(packageName) ? context.getPackageName() : packageName);
                this.zzo = fromPackage;
                if (fromPackage != null) {
                    zze(newWakeLock, fromPackage);
                }
            }
            ScheduledExecutorService scheduledExecutorService = zzc;
            if (scheduledExecutorService == null) {
                synchronized (zzd) {
                    try {
                        scheduledExecutorService = zzc;
                        if (scheduledExecutorService == null) {
                            zzh.zza();
                            scheduledExecutorService = Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1));
                            zzc = scheduledExecutorService;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            this.zzu = scheduledExecutorService;
            return;
        }
        StringBuilder sb = new StringBuilder(29);
        sb.append("expected a non-null reference", 0, 29);
        throw new zzi(sb.toString());
    }

    public static /* synthetic */ void zza(WakeLock wakeLock) {
        synchronized (wakeLock.zzf) {
            try {
                if (wakeLock.isHeld()) {
                    Log.e("WakeLock", String.valueOf(wakeLock.zzp).concat(" ** IS FORCE-RELEASED ON TIMEOUT **"));
                    wakeLock.zzc();
                    if (wakeLock.isHeld()) {
                        wakeLock.zzh = 1;
                        wakeLock.zzd(0);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final String zzb(String str) {
        if (this.zzl) {
            TextUtils.isEmpty((CharSequence) null);
        }
        return null;
    }

    private final void zzc() {
        if (!this.zzk.isEmpty()) {
            ArrayList arrayList = new ArrayList(this.zzk);
            this.zzk.clear();
            if (arrayList.size() > 0) {
                zze zze2 = (zze) arrayList.get(0);
                throw null;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a6, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzd(int r6) {
        /*
            r5 = this;
            java.lang.Object r6 = r5.zzf
            monitor-enter(r6)
            boolean r0 = r5.isHeld()     // Catch:{ all -> 0x000b }
            if (r0 != 0) goto L_0x000e
            monitor-exit(r6)     // Catch:{ all -> 0x000b }
            return
        L_0x000b:
            r0 = move-exception
            goto L_0x00a7
        L_0x000e:
            boolean r0 = r5.zzl     // Catch:{ all -> 0x000b }
            r1 = 0
            if (r0 == 0) goto L_0x001e
            int r0 = r5.zzh     // Catch:{ all -> 0x000b }
            int r0 = r0 + -1
            r5.zzh = r0     // Catch:{ all -> 0x000b }
            if (r0 > 0) goto L_0x001c
            goto L_0x0020
        L_0x001c:
            monitor-exit(r6)     // Catch:{ all -> 0x000b }
            return
        L_0x001e:
            r5.zzh = r1     // Catch:{ all -> 0x000b }
        L_0x0020:
            r5.zzc()     // Catch:{ all -> 0x000b }
            java.util.Map<java.lang.String, com.google.android.gms.stats.zzc> r0 = r5.zzs     // Catch:{ all -> 0x000b }
            java.util.Collection r0 = r0.values()     // Catch:{ all -> 0x000b }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x000b }
        L_0x002d:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x000b }
            if (r2 == 0) goto L_0x003c
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x000b }
            com.google.android.gms.stats.zzc r2 = (com.google.android.gms.stats.zzc) r2     // Catch:{ all -> 0x000b }
            r2.zza = r1     // Catch:{ all -> 0x000b }
            goto L_0x002d
        L_0x003c:
            java.util.Map<java.lang.String, com.google.android.gms.stats.zzc> r0 = r5.zzs     // Catch:{ all -> 0x000b }
            r0.clear()     // Catch:{ all -> 0x000b }
            java.util.concurrent.Future<?> r0 = r5.zzi     // Catch:{ all -> 0x000b }
            r2 = 0
            if (r0 == 0) goto L_0x004f
            r0.cancel(r1)     // Catch:{ all -> 0x000b }
            r5.zzi = r2     // Catch:{ all -> 0x000b }
            r3 = 0
            r5.zzj = r3     // Catch:{ all -> 0x000b }
        L_0x004f:
            r5.zzm = r1     // Catch:{ all -> 0x000b }
            android.os.PowerManager$WakeLock r0 = r5.zzg     // Catch:{ all -> 0x000b }
            boolean r0 = r0.isHeld()     // Catch:{ all -> 0x000b }
            if (r0 == 0) goto L_0x0094
            android.os.PowerManager$WakeLock r0 = r5.zzg     // Catch:{ RuntimeException -> 0x0067 }
            r0.release()     // Catch:{ RuntimeException -> 0x0067 }
            com.google.android.gms.internal.stats.zzb r0 = r5.zza     // Catch:{ all -> 0x000b }
            if (r0 == 0) goto L_0x00a5
            r5.zza = r2     // Catch:{ all -> 0x000b }
            goto L_0x00a5
        L_0x0065:
            r0 = move-exception
            goto L_0x008d
        L_0x0067:
            r0 = move-exception
            java.lang.Class r1 = r0.getClass()     // Catch:{ all -> 0x0065 }
            java.lang.Class<java.lang.RuntimeException> r3 = java.lang.RuntimeException.class
            boolean r1 = r1.equals(r3)     // Catch:{ all -> 0x0065 }
            if (r1 == 0) goto L_0x008c
            java.lang.String r1 = "WakeLock"
            java.lang.String r3 = r5.zzp     // Catch:{ all -> 0x0065 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0065 }
            java.lang.String r4 = " failed to release!"
            java.lang.String r3 = r3.concat(r4)     // Catch:{ all -> 0x0065 }
            android.util.Log.e(r1, r3, r0)     // Catch:{ all -> 0x0065 }
            com.google.android.gms.internal.stats.zzb r0 = r5.zza     // Catch:{ all -> 0x000b }
            if (r0 == 0) goto L_0x00a5
            r5.zza = r2     // Catch:{ all -> 0x000b }
            goto L_0x00a5
        L_0x008c:
            throw r0     // Catch:{ all -> 0x0065 }
        L_0x008d:
            com.google.android.gms.internal.stats.zzb r1 = r5.zza     // Catch:{ all -> 0x000b }
            if (r1 == 0) goto L_0x0093
            r5.zza = r2     // Catch:{ all -> 0x000b }
        L_0x0093:
            throw r0     // Catch:{ all -> 0x000b }
        L_0x0094:
            java.lang.String r0 = "WakeLock"
            java.lang.String r1 = r5.zzp     // Catch:{ all -> 0x000b }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x000b }
            java.lang.String r2 = " should be held!"
            java.lang.String r1 = r1.concat(r2)     // Catch:{ all -> 0x000b }
            android.util.Log.e(r0, r1)     // Catch:{ all -> 0x000b }
        L_0x00a5:
            monitor-exit(r6)     // Catch:{ all -> 0x000b }
            return
        L_0x00a7:
            monitor-exit(r6)     // Catch:{ all -> 0x000b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.stats.WakeLock.zzd(int):void");
    }

    private static void zze(PowerManager.WakeLock wakeLock, WorkSource workSource) {
        try {
            wakeLock.setWorkSource(workSource);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            Log.wtf("WakeLock", e.toString());
        }
    }

    @KeepForSdk
    public void acquire(long j) {
        this.zzt.incrementAndGet();
        long j2 = Long.MAX_VALUE;
        long max = Math.max(Math.min(Long.MAX_VALUE, zzb), 1);
        if (j > 0) {
            max = Math.min(j, max);
        }
        synchronized (this.zzf) {
            try {
                if (!isHeld()) {
                    this.zza = zzb.zza(false, (zzc) null);
                    this.zzg.acquire();
                    this.zzn.elapsedRealtime();
                }
                this.zzh++;
                this.zzm++;
                zzb((String) null);
                zzc zzc2 = this.zzs.get((Object) null);
                if (zzc2 == null) {
                    zzc2 = new zzc((zzb) null);
                    this.zzs.put((Object) null, zzc2);
                }
                zzc2.zza++;
                long elapsedRealtime = this.zzn.elapsedRealtime();
                if (Long.MAX_VALUE - elapsedRealtime > max) {
                    j2 = elapsedRealtime + max;
                }
                if (j2 > this.zzj) {
                    this.zzj = j2;
                    Future<?> future = this.zzi;
                    if (future != null) {
                        future.cancel(false);
                    }
                    this.zzi = this.zzu.schedule(new zza(this), max, TimeUnit.MILLISECONDS);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public boolean isHeld() {
        boolean z;
        synchronized (this.zzf) {
            z = this.zzh > 0;
        }
        return z;
    }

    @KeepForSdk
    public void release() {
        if (this.zzt.decrementAndGet() < 0) {
            Log.e("WakeLock", String.valueOf(this.zzp).concat(" release without a matched acquire!"));
        }
        synchronized (this.zzf) {
            try {
                zzb((String) null);
                if (this.zzs.containsKey((Object) null)) {
                    zzc zzc2 = this.zzs.get((Object) null);
                    if (zzc2 != null) {
                        int i = zzc2.zza - 1;
                        zzc2.zza = i;
                        if (i == 0) {
                            this.zzs.remove((Object) null);
                        }
                    }
                } else {
                    Log.w("WakeLock", String.valueOf(this.zzp).concat(" counter does not exist"));
                }
                zzd(0);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public void setReferenceCounted(boolean z) {
        synchronized (this.zzf) {
            this.zzl = z;
        }
    }
}
