package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzb;
import java.util.concurrent.Future;

public final /* synthetic */ class zzy implements Runnable {
    public final /* synthetic */ Future zza;
    public final /* synthetic */ Runnable zzb;

    public /* synthetic */ zzy(Future future, Runnable runnable) {
        this.zza = future;
        this.zzb = runnable;
    }

    public final void run() {
        Future future = this.zza;
        if (!future.isDone() && !future.isCancelled()) {
            Runnable runnable = this.zzb;
            future.cancel(true);
            zzb.zzk("BillingClient", "Async task is taking too long, cancel it!");
            if (runnable != null) {
                runnable.run();
            }
        }
    }
}
