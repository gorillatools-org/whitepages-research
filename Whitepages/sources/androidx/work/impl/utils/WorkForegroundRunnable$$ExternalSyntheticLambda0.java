package androidx.work.impl.utils;

import androidx.work.impl.utils.futures.SettableFuture;

public final /* synthetic */ class WorkForegroundRunnable$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ WorkForegroundRunnable f$0;
    public final /* synthetic */ SettableFuture f$1;

    public /* synthetic */ WorkForegroundRunnable$$ExternalSyntheticLambda0(WorkForegroundRunnable workForegroundRunnable, SettableFuture settableFuture) {
        this.f$0 = workForegroundRunnable;
        this.f$1 = settableFuture;
    }

    public final void run() {
        this.f$0.lambda$run$0(this.f$1);
    }
}
