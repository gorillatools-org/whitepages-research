package androidx.work.impl;

import androidx.work.BackoffPolicy;
import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Operation;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import androidx.work.impl.utils.EnqueueUtilsKt;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

public abstract class WorkerUpdater {
    private static final WorkManager.UpdateResult updateWorkImpl(Processor processor, WorkDatabase workDatabase, Configuration configuration, List list, WorkSpec workSpec, Set set) {
        String str = workSpec.id;
        WorkSpec workSpec2 = workDatabase.workSpecDao().getWorkSpec(str);
        if (workSpec2 == null) {
            throw new IllegalArgumentException("Worker with " + str + " doesn't exist");
        } else if (workSpec2.state.isFinished()) {
            return WorkManager.UpdateResult.NOT_APPLIED;
        } else {
            if (!(workSpec2.isPeriodic() ^ workSpec.isPeriodic())) {
                boolean isEnqueued = processor.isEnqueued(str);
                if (!isEnqueued) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        ((Scheduler) it.next()).cancel(str);
                    }
                }
                workDatabase.runInTransaction((Runnable) new WorkerUpdater$$ExternalSyntheticLambda1(workDatabase, workSpec, workSpec2, list, str, set, isEnqueued));
                if (!isEnqueued) {
                    Schedulers.schedule(configuration, workDatabase, list);
                }
                if (isEnqueued) {
                    return WorkManager.UpdateResult.APPLIED_FOR_NEXT_RUN;
                }
                return WorkManager.UpdateResult.APPLIED_IMMEDIATELY;
            }
            WorkerUpdater$updateWorkImpl$type$1 workerUpdater$updateWorkImpl$type$1 = WorkerUpdater$updateWorkImpl$type$1.INSTANCE;
            throw new UnsupportedOperationException("Can't update " + ((String) workerUpdater$updateWorkImpl$type$1.invoke(workSpec2)) + " Worker to " + ((String) workerUpdater$updateWorkImpl$type$1.invoke(workSpec)) + " Worker. Update operation must preserve worker's type.");
        }
    }

    /* access modifiers changed from: private */
    public static final void updateWorkImpl$lambda$1(WorkDatabase workDatabase, WorkSpec workSpec, WorkSpec workSpec2, List list, String str, Set set, boolean z) {
        WorkSpec workSpec3 = workSpec2;
        List list2 = list;
        String str2 = str;
        Set set2 = set;
        Intrinsics.checkNotNullParameter(workDatabase, "$workDatabase");
        Intrinsics.checkNotNullParameter(workSpec, "$newWorkSpec");
        Intrinsics.checkNotNullParameter(workSpec3, "$oldWorkSpec");
        Intrinsics.checkNotNullParameter(list2, "$schedulers");
        Intrinsics.checkNotNullParameter(str2, "$workSpecId");
        Intrinsics.checkNotNullParameter(set2, "$tags");
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        WorkTagDao workTagDao = workDatabase.workTagDao();
        WorkSpecDao workSpecDao2 = workSpecDao;
        workSpecDao2.updateWorkSpec(EnqueueUtilsKt.wrapInConstraintTrackingWorkerIfNeeded(list2, WorkSpec.copy$default(workSpec, (String) null, workSpec3.state, (String) null, (String) null, (Data) null, (Data) null, 0, 0, 0, (Constraints) null, workSpec3.runAttemptCount, (BackoffPolicy) null, 0, workSpec3.lastEnqueueTime, 0, 0, false, (OutOfQuotaPolicy) null, 0, workSpec2.getGeneration() + 1, 515069, (Object) null)));
        WorkTagDao workTagDao2 = workTagDao;
        workTagDao2.deleteByWorkSpecId(str2);
        workTagDao2.insertTags(str2, set2);
        if (!z) {
            workSpecDao2.markWorkSpecScheduled(str2, -1);
            workDatabase.workProgressDao().delete(str2);
        }
    }

    public static final Operation enqueueUniquelyNamedPeriodic(WorkManagerImpl workManagerImpl, String str, WorkRequest workRequest) {
        Intrinsics.checkNotNullParameter(workManagerImpl, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(workRequest, "workRequest");
        OperationImpl operationImpl = new OperationImpl();
        workManagerImpl.getWorkTaskExecutor().getSerialTaskExecutor().execute(new WorkerUpdater$$ExternalSyntheticLambda0(workManagerImpl, str, operationImpl, new WorkerUpdater$enqueueUniquelyNamedPeriodic$enqueueNew$1(workRequest, workManagerImpl, str, operationImpl), workRequest));
        return operationImpl;
    }

    /* access modifiers changed from: private */
    public static final void enqueueUniquelyNamedPeriodic$lambda$3(WorkManagerImpl workManagerImpl, String str, OperationImpl operationImpl, Function0 function0, WorkRequest workRequest) {
        String str2 = str;
        OperationImpl operationImpl2 = operationImpl;
        Intrinsics.checkNotNullParameter(workManagerImpl, "$this_enqueueUniquelyNamedPeriodic");
        Intrinsics.checkNotNullParameter(str2, "$name");
        Intrinsics.checkNotNullParameter(operationImpl2, "$operation");
        Intrinsics.checkNotNullParameter(function0, "$enqueueNew");
        Intrinsics.checkNotNullParameter(workRequest, "$workRequest");
        WorkSpecDao workSpecDao = workManagerImpl.getWorkDatabase().workSpecDao();
        List workSpecIdAndStatesForName = workSpecDao.getWorkSpecIdAndStatesForName(str2);
        if (workSpecIdAndStatesForName.size() > 1) {
            failWorkTypeChanged(operationImpl2, "Can't apply UPDATE policy to the chains of work.");
            return;
        }
        WorkSpec.IdAndState idAndState = (WorkSpec.IdAndState) CollectionsKt.firstOrNull(workSpecIdAndStatesForName);
        if (idAndState == null) {
            function0.invoke();
            return;
        }
        WorkSpec workSpec = workSpecDao.getWorkSpec(idAndState.id);
        if (workSpec == null) {
            operationImpl2.markState(new Operation.State.FAILURE(new IllegalStateException("WorkSpec with " + idAndState.id + ", that matches a name \"" + str2 + "\", wasn't found")));
        } else if (!workSpec.isPeriodic()) {
            failWorkTypeChanged(operationImpl2, "Can't update OneTimeWorker to Periodic Worker. Update operation must preserve worker's type.");
        } else if (idAndState.state == WorkInfo$State.CANCELLED) {
            workSpecDao.delete(idAndState.id);
            function0.invoke();
        } else {
            WorkSpec copy$default = WorkSpec.copy$default(workRequest.getWorkSpec(), idAndState.id, (WorkInfo$State) null, (String) null, (String) null, (Data) null, (Data) null, 0, 0, 0, (Constraints) null, 0, (BackoffPolicy) null, 0, 0, 0, 0, false, (OutOfQuotaPolicy) null, 0, 0, 1048574, (Object) null);
            try {
                Processor processor = workManagerImpl.getProcessor();
                Intrinsics.checkNotNullExpressionValue(processor, "processor");
                WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
                Intrinsics.checkNotNullExpressionValue(workDatabase, "workDatabase");
                Configuration configuration = workManagerImpl.getConfiguration();
                Intrinsics.checkNotNullExpressionValue(configuration, "configuration");
                List schedulers = workManagerImpl.getSchedulers();
                Intrinsics.checkNotNullExpressionValue(schedulers, "schedulers");
                updateWorkImpl(processor, workDatabase, configuration, schedulers, copy$default, workRequest.getTags());
                operationImpl2.markState(Operation.SUCCESS);
            } catch (Throwable th) {
                operationImpl2.markState(new Operation.State.FAILURE(th));
            }
        }
    }

    private static final void failWorkTypeChanged(OperationImpl operationImpl, String str) {
        operationImpl.markState(new Operation.State.FAILURE(new UnsupportedOperationException(str)));
    }
}
