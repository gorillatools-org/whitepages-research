package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

public final class zzil extends zzjr {
    /* access modifiers changed from: private */
    public static final AtomicLong zza = new AtomicLong(Long.MIN_VALUE);
    /* access modifiers changed from: private */
    public zzik zzb;
    /* access modifiers changed from: private */
    public zzik zzc;
    private final PriorityBlockingQueue zzd = new PriorityBlockingQueue();
    private final BlockingQueue zze = new LinkedBlockingQueue();
    private final Thread.UncaughtExceptionHandler zzf = new zzii(this, "Thread death: Uncaught exception on worker thread");
    private final Thread.UncaughtExceptionHandler zzg = new zzii(this, "Thread death: Uncaught exception on network thread");
    /* access modifiers changed from: private */
    public final Object zzh = new Object();
    /* access modifiers changed from: private */
    public final Semaphore zzi = new Semaphore(2);
    /* access modifiers changed from: private */
    public volatile boolean zzj;

    zzil(zzio zzio) {
        super(zzio);
    }

    private final void zzz(zzij zzij) {
        synchronized (this.zzh) {
            try {
                PriorityBlockingQueue priorityBlockingQueue = this.zzd;
                priorityBlockingQueue.add(zzij);
                zzik zzik = this.zzb;
                if (zzik == null) {
                    zzik zzik2 = new zzik(this, "Measurement Worker", priorityBlockingQueue);
                    this.zzb = zzik2;
                    zzik2.setUncaughtExceptionHandler(this.zzf);
                    this.zzb.start();
                } else {
                    zzik.zza();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zzaY() {
        if (Thread.currentThread() != this.zzc) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzc() {
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:12|13|14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0027, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r3 = r1.zzu.zzaW().zzk();
        r3.zza("Interrupted waiting for " + r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000e, code lost:
        r2 = r2.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        if (r2 != null) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        r1.zzu.zzaW().zzk().zza("Timed out waiting for ".concat(r5));
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zze(java.util.concurrent.atomic.AtomicReference r2, long r3, java.lang.String r5, java.lang.Runnable r6) {
        /*
            r1 = this;
            monitor-enter(r2)
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ all -> 0x0028 }
            com.google.android.gms.measurement.internal.zzil r0 = r0.zzaX()     // Catch:{ all -> 0x0028 }
            r0.zzq(r6)     // Catch:{ all -> 0x0028 }
            r2.wait(r3)     // Catch:{ InterruptedException -> 0x002a }
            monitor-exit(r2)     // Catch:{ all -> 0x0028 }
            java.lang.Object r2 = r2.get()
            if (r2 != 0) goto L_0x0027
            com.google.android.gms.measurement.internal.zzio r3 = r1.zzu
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zzk()
            java.lang.String r4 = "Timed out waiting for "
            java.lang.String r4 = r4.concat(r5)
            r3.zza(r4)
        L_0x0027:
            return r2
        L_0x0028:
            r3 = move-exception
            goto L_0x004b
        L_0x002a:
            com.google.android.gms.measurement.internal.zzio r3 = r1.zzu     // Catch:{ all -> 0x0028 }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ all -> 0x0028 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zzk()     // Catch:{ all -> 0x0028 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0028 }
            r4.<init>()     // Catch:{ all -> 0x0028 }
            java.lang.String r6 = "Interrupted waiting for "
            r4.append(r6)     // Catch:{ all -> 0x0028 }
            r4.append(r5)     // Catch:{ all -> 0x0028 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0028 }
            r3.zza(r4)     // Catch:{ all -> 0x0028 }
            monitor-exit(r2)     // Catch:{ all -> 0x0028 }
            r2 = 0
            return r2
        L_0x004b:
            monitor-exit(r2)     // Catch:{ all -> 0x0028 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzil.zze(java.util.concurrent.atomic.AtomicReference, long, java.lang.String, java.lang.Runnable):java.lang.Object");
    }

    public final Future zzf(Callable callable) throws IllegalStateException {
        zzv();
        Preconditions.checkNotNull(callable);
        zzij zzij = new zzij(this, callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzb) {
            if (!this.zzd.isEmpty()) {
                this.zzu.zzaW().zzk().zza("Callable skipped the worker queue.");
            }
            zzij.run();
        } else {
            zzz(zzij);
        }
        return zzij;
    }

    public final void zzg() {
        if (Thread.currentThread() != this.zzb) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final Future zzh(Callable callable) throws IllegalStateException {
        zzv();
        Preconditions.checkNotNull(callable);
        zzij zzij = new zzij(this, callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzb) {
            zzij.run();
        } else {
            zzz(zzij);
        }
        return zzij;
    }

    public final void zzn() {
        if (Thread.currentThread() == this.zzb) {
            throw new IllegalStateException("Call not expected from worker thread");
        }
    }

    public final void zzp(Runnable runnable) throws IllegalStateException {
        zzv();
        Preconditions.checkNotNull(runnable);
        zzij zzij = new zzij(this, runnable, false, "Task exception on network thread");
        synchronized (this.zzh) {
            try {
                BlockingQueue blockingQueue = this.zze;
                blockingQueue.add(zzij);
                zzik zzik = this.zzc;
                if (zzik == null) {
                    zzik zzik2 = new zzik(this, "Measurement Network", blockingQueue);
                    this.zzc = zzik2;
                    zzik2.setUncaughtExceptionHandler(this.zzg);
                    this.zzc.start();
                } else {
                    zzik.zza();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zzq(Runnable runnable) throws IllegalStateException {
        zzv();
        Preconditions.checkNotNull(runnable);
        zzz(new zzij(this, runnable, false, "Task exception on worker thread"));
    }

    public final void zzr(Runnable runnable) throws IllegalStateException {
        zzv();
        Preconditions.checkNotNull(runnable);
        zzz(new zzij(this, runnable, true, "Task exception on worker thread"));
    }

    public final boolean zzt() {
        return Thread.currentThread() == this.zzc;
    }

    public final boolean zzu() {
        return Thread.currentThread() == this.zzb;
    }
}
