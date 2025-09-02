package androidx.work.impl;

import androidx.work.WorkRequest;
import kotlin.jvm.functions.Function0;

public final /* synthetic */ class WorkerUpdater$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ WorkManagerImpl f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ OperationImpl f$2;
    public final /* synthetic */ Function0 f$3;
    public final /* synthetic */ WorkRequest f$4;

    public /* synthetic */ WorkerUpdater$$ExternalSyntheticLambda0(WorkManagerImpl workManagerImpl, String str, OperationImpl operationImpl, Function0 function0, WorkRequest workRequest) {
        this.f$0 = workManagerImpl;
        this.f$1 = str;
        this.f$2 = operationImpl;
        this.f$3 = function0;
        this.f$4 = workRequest;
    }

    public final void run() {
        WorkerUpdater.enqueueUniquelyNamedPeriodic$lambda$3(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
