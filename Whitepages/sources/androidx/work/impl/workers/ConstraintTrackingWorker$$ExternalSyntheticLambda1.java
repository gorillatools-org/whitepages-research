package androidx.work.impl.workers;

import com.google.common.util.concurrent.ListenableFuture;

public final /* synthetic */ class ConstraintTrackingWorker$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ConstraintTrackingWorker f$0;
    public final /* synthetic */ ListenableFuture f$1;

    public /* synthetic */ ConstraintTrackingWorker$$ExternalSyntheticLambda1(ConstraintTrackingWorker constraintTrackingWorker, ListenableFuture listenableFuture) {
        this.f$0 = constraintTrackingWorker;
        this.f$1 = listenableFuture;
    }

    public final void run() {
        ConstraintTrackingWorker.setupAndRunConstraintTrackingWork$lambda$2(this.f$0, this.f$1);
    }
}
