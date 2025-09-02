package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

final class zzij extends FutureTask implements Comparable {
    final boolean zza;
    final /* synthetic */ zzil zzb;
    private final long zzc;
    private final String zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzij(zzil zzil, Runnable runnable, boolean z, String str) {
        super(runnable, (Object) null);
        this.zzb = zzil;
        Preconditions.checkNotNull(str);
        long andIncrement = zzil.zza.getAndIncrement();
        this.zzc = andIncrement;
        this.zzd = str;
        this.zza = z;
        if (andIncrement == Long.MAX_VALUE) {
            zzil.zzu.zzaW().zze().zza("Tasks index overflow");
        }
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzij zzij = (zzij) obj;
        boolean z = zzij.zza;
        boolean z2 = this.zza;
        if (z2 == z) {
            long j = this.zzc;
            int i = (j > zzij.zzc ? 1 : (j == zzij.zzc ? 0 : -1));
            if (i < 0) {
                return -1;
            }
            if (i <= 0) {
                this.zzb.zzu.zzaW().zzh().zzb("Two tasks share the same index. index", Long.valueOf(j));
                return 0;
            }
        } else if (z2) {
            return -1;
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public final void setException(Throwable th) {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
        this.zzb.zzu.zzaW().zze().zzb(this.zzd, th);
        if ((th instanceof zzih) && (defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()) != null) {
            defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzij(zzil zzil, Callable callable, boolean z, String str) {
        super(callable);
        this.zzb = zzil;
        Preconditions.checkNotNull("Task exception on worker thread");
        long andIncrement = zzil.zza.getAndIncrement();
        this.zzc = andIncrement;
        this.zzd = "Task exception on worker thread";
        this.zza = z;
        if (andIncrement == Long.MAX_VALUE) {
            zzil.zzu.zzaW().zze().zza("Tasks index overflow");
        }
    }
}
