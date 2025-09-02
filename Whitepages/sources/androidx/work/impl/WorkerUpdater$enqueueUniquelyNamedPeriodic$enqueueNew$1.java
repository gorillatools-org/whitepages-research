package androidx.work.impl;

import androidx.work.ExistingWorkPolicy;
import androidx.work.WorkRequest;
import androidx.work.impl.utils.EnqueueRunnable;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class WorkerUpdater$enqueueUniquelyNamedPeriodic$enqueueNew$1 extends Lambda implements Function0 {
    final /* synthetic */ String $name;
    final /* synthetic */ OperationImpl $operation;
    final /* synthetic */ WorkManagerImpl $this_enqueueUniquelyNamedPeriodic;
    final /* synthetic */ WorkRequest $workRequest;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WorkerUpdater$enqueueUniquelyNamedPeriodic$enqueueNew$1(WorkRequest workRequest, WorkManagerImpl workManagerImpl, String str, OperationImpl operationImpl) {
        super(0);
        this.$workRequest = workRequest;
        this.$this_enqueueUniquelyNamedPeriodic = workManagerImpl;
        this.$name = str;
        this.$operation = operationImpl;
    }

    public final void invoke() {
        new EnqueueRunnable(new WorkContinuationImpl(this.$this_enqueueUniquelyNamedPeriodic, this.$name, ExistingWorkPolicy.KEEP, CollectionsKt.listOf(this.$workRequest)), this.$operation).run();
    }
}
