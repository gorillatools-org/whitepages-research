package androidx.work.impl.utils;

import android.text.TextUtils;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import java.util.List;

public class EnqueueRunnable implements Runnable {
    private static final String TAG = Logger.tagWithPrefix("EnqueueRunnable");
    private final OperationImpl mOperation;
    private final WorkContinuationImpl mWorkContinuation;

    public EnqueueRunnable(WorkContinuationImpl workContinuationImpl) {
        this(workContinuationImpl, new OperationImpl());
    }

    public EnqueueRunnable(WorkContinuationImpl workContinuationImpl, OperationImpl operationImpl) {
        this.mWorkContinuation = workContinuationImpl;
        this.mOperation = operationImpl;
    }

    public void run() {
        try {
            if (!this.mWorkContinuation.hasCycles()) {
                if (addToDatabase()) {
                    PackageManagerHelper.setComponentEnabled(this.mWorkContinuation.getWorkManagerImpl().getApplicationContext(), RescheduleReceiver.class, true);
                    scheduleWorkInBackground();
                }
                this.mOperation.markState(Operation.SUCCESS);
                return;
            }
            throw new IllegalStateException("WorkContinuation has cycles (" + this.mWorkContinuation + ")");
        } catch (Throwable th) {
            this.mOperation.markState(new Operation.State.FAILURE(th));
        }
    }

    public Operation getOperation() {
        return this.mOperation;
    }

    public boolean addToDatabase() {
        WorkDatabase workDatabase = this.mWorkContinuation.getWorkManagerImpl().getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            boolean processContinuation = processContinuation(this.mWorkContinuation);
            workDatabase.setTransactionSuccessful();
            return processContinuation;
        } finally {
            workDatabase.endTransaction();
        }
    }

    public void scheduleWorkInBackground() {
        WorkManagerImpl workManagerImpl = this.mWorkContinuation.getWorkManagerImpl();
        Schedulers.schedule(workManagerImpl.getConfiguration(), workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }

    private static boolean processContinuation(WorkContinuationImpl workContinuationImpl) {
        List<WorkContinuationImpl> parents = workContinuationImpl.getParents();
        boolean z = false;
        if (parents != null) {
            for (WorkContinuationImpl workContinuationImpl2 : parents) {
                if (!workContinuationImpl2.isEnqueued()) {
                    z |= processContinuation(workContinuationImpl2);
                } else {
                    Logger logger = Logger.get();
                    String str = TAG;
                    logger.warning(str, "Already enqueued work ids (" + TextUtils.join(", ", workContinuationImpl2.getIds()) + ")");
                }
            }
        }
        return enqueueContinuation(workContinuationImpl) | z;
    }

    private static boolean enqueueContinuation(WorkContinuationImpl workContinuationImpl) {
        boolean enqueueWorkWithPrerequisites = enqueueWorkWithPrerequisites(workContinuationImpl.getWorkManagerImpl(), workContinuationImpl.getWork(), (String[]) WorkContinuationImpl.prerequisitesFor(workContinuationImpl).toArray(new String[0]), workContinuationImpl.getName(), workContinuationImpl.getExistingWorkPolicy());
        workContinuationImpl.markEnqueued();
        return enqueueWorkWithPrerequisites;
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x015b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean enqueueWorkWithPrerequisites(androidx.work.impl.WorkManagerImpl r18, java.util.List r19, java.lang.String[] r20, java.lang.String r21, androidx.work.ExistingWorkPolicy r22) {
        /*
            r0 = r20
            r1 = r21
            r2 = r22
            long r3 = java.lang.System.currentTimeMillis()
            androidx.work.impl.WorkDatabase r5 = r18.getWorkDatabase()
            r7 = 0
            if (r0 == 0) goto L_0x0016
            int r8 = r0.length
            if (r8 <= 0) goto L_0x0016
            r8 = 1
            goto L_0x0017
        L_0x0016:
            r8 = r7
        L_0x0017:
            if (r8 == 0) goto L_0x0064
            int r9 = r0.length
            r10 = r7
            r12 = r10
            r13 = r12
            r11 = 1
        L_0x001e:
            if (r10 >= r9) goto L_0x0067
            r14 = r0[r10]
            androidx.work.impl.model.WorkSpecDao r15 = r5.workSpecDao()
            androidx.work.impl.model.WorkSpec r15 = r15.getWorkSpec(r14)
            if (r15 != 0) goto L_0x004c
            androidx.work.Logger r0 = androidx.work.Logger.get()
            java.lang.String r1 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Prerequisite "
            r2.append(r3)
            r2.append(r14)
            java.lang.String r3 = " doesn't exist; not enqueuing"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.error(r1, r2)
            return r7
        L_0x004c:
            androidx.work.WorkInfo$State r14 = r15.state
            androidx.work.WorkInfo$State r15 = androidx.work.WorkInfo$State.SUCCEEDED
            if (r14 != r15) goto L_0x0054
            r15 = 1
            goto L_0x0055
        L_0x0054:
            r15 = r7
        L_0x0055:
            r11 = r11 & r15
            androidx.work.WorkInfo$State r15 = androidx.work.WorkInfo$State.FAILED
            if (r14 != r15) goto L_0x005c
            r13 = 1
            goto L_0x0061
        L_0x005c:
            androidx.work.WorkInfo$State r15 = androidx.work.WorkInfo$State.CANCELLED
            if (r14 != r15) goto L_0x0061
            r12 = 1
        L_0x0061:
            int r10 = r10 + 1
            goto L_0x001e
        L_0x0064:
            r12 = r7
            r13 = r12
            r11 = 1
        L_0x0067:
            boolean r9 = android.text.TextUtils.isEmpty(r21)
            if (r9 != 0) goto L_0x014e
            if (r8 != 0) goto L_0x014e
            androidx.work.impl.model.WorkSpecDao r10 = r5.workSpecDao()
            java.util.List r10 = r10.getWorkSpecIdAndStatesForName(r1)
            boolean r14 = r10.isEmpty()
            if (r14 != 0) goto L_0x014e
            androidx.work.ExistingWorkPolicy r14 = androidx.work.ExistingWorkPolicy.APPEND
            if (r2 == r14) goto L_0x0085
            androidx.work.ExistingWorkPolicy r14 = androidx.work.ExistingWorkPolicy.APPEND_OR_REPLACE
            if (r2 != r14) goto L_0x0088
        L_0x0085:
            r14 = r18
            goto L_0x00cd
        L_0x0088:
            androidx.work.ExistingWorkPolicy r14 = androidx.work.ExistingWorkPolicy.KEEP
            if (r2 != r14) goto L_0x00a7
            java.util.Iterator r2 = r10.iterator()
        L_0x0090:
            boolean r14 = r2.hasNext()
            if (r14 == 0) goto L_0x00a7
            java.lang.Object r14 = r2.next()
            androidx.work.impl.model.WorkSpec$IdAndState r14 = (androidx.work.impl.model.WorkSpec.IdAndState) r14
            androidx.work.WorkInfo$State r14 = r14.state
            androidx.work.WorkInfo$State r15 = androidx.work.WorkInfo$State.ENQUEUED
            if (r14 == r15) goto L_0x00a6
            androidx.work.WorkInfo$State r15 = androidx.work.WorkInfo$State.RUNNING
            if (r14 != r15) goto L_0x0090
        L_0x00a6:
            return r7
        L_0x00a7:
            r14 = r18
            androidx.work.impl.utils.CancelWorkRunnable r2 = androidx.work.impl.utils.CancelWorkRunnable.forName(r1, r14, r7)
            r2.run()
            androidx.work.impl.model.WorkSpecDao r2 = r5.workSpecDao()
            java.util.Iterator r10 = r10.iterator()
        L_0x00b8:
            boolean r15 = r10.hasNext()
            if (r15 == 0) goto L_0x00ca
            java.lang.Object r15 = r10.next()
            androidx.work.impl.model.WorkSpec$IdAndState r15 = (androidx.work.impl.model.WorkSpec.IdAndState) r15
            java.lang.String r15 = r15.id
            r2.delete(r15)
            goto L_0x00b8
        L_0x00ca:
            r6 = 1
            goto L_0x0151
        L_0x00cd:
            androidx.work.impl.model.DependencyDao r8 = r5.dependencyDao()
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.util.Iterator r10 = r10.iterator()
        L_0x00da:
            boolean r16 = r10.hasNext()
            if (r16 == 0) goto L_0x0114
            java.lang.Object r16 = r10.next()
            r6 = r16
            androidx.work.impl.model.WorkSpec$IdAndState r6 = (androidx.work.impl.model.WorkSpec.IdAndState) r6
            java.lang.String r7 = r6.id
            boolean r7 = r8.hasDependents(r7)
            if (r7 != 0) goto L_0x010e
            androidx.work.WorkInfo$State r7 = r6.state
            r17 = r8
            androidx.work.WorkInfo$State r8 = androidx.work.WorkInfo$State.SUCCEEDED
            if (r7 != r8) goto L_0x00fa
            r8 = 1
            goto L_0x00fb
        L_0x00fa:
            r8 = 0
        L_0x00fb:
            r8 = r8 & r11
            androidx.work.WorkInfo$State r11 = androidx.work.WorkInfo$State.FAILED
            if (r7 != r11) goto L_0x0102
            r13 = 1
            goto L_0x0107
        L_0x0102:
            androidx.work.WorkInfo$State r11 = androidx.work.WorkInfo$State.CANCELLED
            if (r7 != r11) goto L_0x0107
            r12 = 1
        L_0x0107:
            java.lang.String r6 = r6.id
            r15.add(r6)
            r11 = r8
            goto L_0x0110
        L_0x010e:
            r17 = r8
        L_0x0110:
            r8 = r17
            r7 = 0
            goto L_0x00da
        L_0x0114:
            androidx.work.ExistingWorkPolicy r6 = androidx.work.ExistingWorkPolicy.APPEND_OR_REPLACE
            if (r2 != r6) goto L_0x0140
            if (r12 != 0) goto L_0x011c
            if (r13 == 0) goto L_0x0140
        L_0x011c:
            androidx.work.impl.model.WorkSpecDao r2 = r5.workSpecDao()
            java.util.List r6 = r2.getWorkSpecIdAndStatesForName(r1)
            java.util.Iterator r6 = r6.iterator()
        L_0x0128:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x013a
            java.lang.Object r7 = r6.next()
            androidx.work.impl.model.WorkSpec$IdAndState r7 = (androidx.work.impl.model.WorkSpec.IdAndState) r7
            java.lang.String r7 = r7.id
            r2.delete(r7)
            goto L_0x0128
        L_0x013a:
            java.util.List r15 = java.util.Collections.emptyList()
            r12 = 0
            r13 = 0
        L_0x0140:
            java.lang.Object[] r0 = r15.toArray(r0)
            java.lang.String[] r0 = (java.lang.String[]) r0
            int r2 = r0.length
            if (r2 <= 0) goto L_0x014b
            r8 = 1
            goto L_0x014c
        L_0x014b:
            r8 = 0
        L_0x014c:
            r6 = 0
            goto L_0x0151
        L_0x014e:
            r14 = r18
            goto L_0x014c
        L_0x0151:
            java.util.Iterator r2 = r19.iterator()
        L_0x0155:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x01e2
            java.lang.Object r7 = r2.next()
            androidx.work.WorkRequest r7 = (androidx.work.WorkRequest) r7
            androidx.work.impl.model.WorkSpec r10 = r7.getWorkSpec()
            if (r8 == 0) goto L_0x017c
            if (r11 != 0) goto L_0x017c
            if (r13 == 0) goto L_0x0170
            androidx.work.WorkInfo$State r15 = androidx.work.WorkInfo$State.FAILED
            r10.state = r15
            goto L_0x017e
        L_0x0170:
            if (r12 == 0) goto L_0x0177
            androidx.work.WorkInfo$State r15 = androidx.work.WorkInfo$State.CANCELLED
            r10.state = r15
            goto L_0x017e
        L_0x0177:
            androidx.work.WorkInfo$State r15 = androidx.work.WorkInfo$State.BLOCKED
            r10.state = r15
            goto L_0x017e
        L_0x017c:
            r10.lastEnqueueTime = r3
        L_0x017e:
            androidx.work.WorkInfo$State r15 = r10.state
            r19 = r2
            androidx.work.WorkInfo$State r2 = androidx.work.WorkInfo$State.ENQUEUED
            if (r15 != r2) goto L_0x0187
            r6 = 1
        L_0x0187:
            androidx.work.impl.model.WorkSpecDao r2 = r5.workSpecDao()
            java.util.List r15 = r18.getSchedulers()
            androidx.work.impl.model.WorkSpec r10 = androidx.work.impl.utils.EnqueueUtilsKt.wrapInConstraintTrackingWorkerIfNeeded(r15, r10)
            r2.insertWorkSpec(r10)
            if (r8 == 0) goto L_0x01b9
            int r2 = r0.length
            r10 = 0
        L_0x019a:
            if (r10 >= r2) goto L_0x01b9
            r15 = r0[r10]
            r17 = r0
            androidx.work.impl.model.Dependency r0 = new androidx.work.impl.model.Dependency
            r20 = r2
            java.lang.String r2 = r7.getStringId()
            r0.<init>(r2, r15)
            androidx.work.impl.model.DependencyDao r2 = r5.dependencyDao()
            r2.insertDependency(r0)
            int r10 = r10 + 1
            r2 = r20
            r0 = r17
            goto L_0x019a
        L_0x01b9:
            r17 = r0
            androidx.work.impl.model.WorkTagDao r0 = r5.workTagDao()
            java.lang.String r2 = r7.getStringId()
            java.util.Set r10 = r7.getTags()
            r0.insertTags(r2, r10)
            if (r9 != 0) goto L_0x01dc
            androidx.work.impl.model.WorkNameDao r0 = r5.workNameDao()
            androidx.work.impl.model.WorkName r2 = new androidx.work.impl.model.WorkName
            java.lang.String r7 = r7.getStringId()
            r2.<init>(r1, r7)
            r0.insert(r2)
        L_0x01dc:
            r2 = r19
            r0 = r17
            goto L_0x0155
        L_0x01e2:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.utils.EnqueueRunnable.enqueueWorkWithPrerequisites(androidx.work.impl.WorkManagerImpl, java.util.List, java.lang.String[], java.lang.String, androidx.work.ExistingWorkPolicy):boolean");
    }
}
