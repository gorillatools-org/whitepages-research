package com.google.android.gms.tasks;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

final class zzo implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzp zzb;

    zzo(zzp zzp, Task task) {
        this.zzb = zzp;
        this.zza = task;
    }

    public final void run() {
        try {
            Task then = this.zzb.zzb.then(this.zza.getResult());
            if (then == null) {
                this.zzb.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            zzp zzp = this.zzb;
            Executor executor = TaskExecutors.zza;
            then.addOnSuccessListener(executor, zzp);
            then.addOnFailureListener(executor, (OnFailureListener) this.zzb);
            then.addOnCanceledListener(executor, (OnCanceledListener) this.zzb);
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                this.zzb.onFailure((Exception) e.getCause());
            } else {
                this.zzb.onFailure(e);
            }
        } catch (CancellationException unused) {
            this.zzb.onCanceled();
        } catch (Exception e2) {
            this.zzb.onFailure(e2);
        }
    }
}
