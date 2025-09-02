package androidx.work.impl;

import android.content.Context;
import androidx.work.Configuration;
import androidx.work.Data;
import androidx.work.InputMerger;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.WorkInfo$State;
import androidx.work.WorkerParameters;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.PackageManagerHelper;
import androidx.work.impl.utils.SynchronousExecutor;
import androidx.work.impl.utils.WorkForegroundRunnable;
import androidx.work.impl.utils.WorkForegroundUpdater;
import androidx.work.impl.utils.WorkProgressUpdater;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class WorkerWrapper implements Runnable {
    static final String TAG = Logger.tagWithPrefix("WorkerWrapper");
    Context mAppContext;
    private Configuration mConfiguration;
    private DependencyDao mDependencyDao;
    private ForegroundProcessor mForegroundProcessor;
    SettableFuture mFuture = SettableFuture.create();
    private volatile boolean mInterrupted;
    ListenableWorker.Result mResult = ListenableWorker.Result.failure();
    private WorkerParameters.RuntimeExtras mRuntimeExtras;
    private List mSchedulers;
    private List mTags;
    private WorkDatabase mWorkDatabase;
    private String mWorkDescription;
    WorkSpec mWorkSpec;
    private WorkSpecDao mWorkSpecDao;
    private final String mWorkSpecId;
    TaskExecutor mWorkTaskExecutor;
    ListenableWorker mWorker;
    final SettableFuture mWorkerResultFuture = SettableFuture.create();

    WorkerWrapper(Builder builder) {
        this.mAppContext = builder.mAppContext;
        this.mWorkTaskExecutor = builder.mWorkTaskExecutor;
        this.mForegroundProcessor = builder.mForegroundProcessor;
        WorkSpec workSpec = builder.mWorkSpec;
        this.mWorkSpec = workSpec;
        this.mWorkSpecId = workSpec.id;
        this.mSchedulers = builder.mSchedulers;
        this.mRuntimeExtras = builder.mRuntimeExtras;
        this.mWorker = builder.mWorker;
        this.mConfiguration = builder.mConfiguration;
        WorkDatabase workDatabase = builder.mWorkDatabase;
        this.mWorkDatabase = workDatabase;
        this.mWorkSpecDao = workDatabase.workSpecDao();
        this.mDependencyDao = this.mWorkDatabase.dependencyDao();
        this.mTags = builder.mTags;
    }

    public WorkGenerationalId getWorkGenerationalId() {
        return WorkSpecKt.generationalId(this.mWorkSpec);
    }

    public ListenableFuture getFuture() {
        return this.mFuture;
    }

    public void run() {
        this.mWorkDescription = createWorkDescription(this.mTags);
        runWorker();
    }

    public WorkSpec getWorkSpec() {
        return this.mWorkSpec;
    }

    private void runWorker() {
        Data merge;
        if (!tryCheckForInterruptionAndResolve()) {
            this.mWorkDatabase.beginTransaction();
            try {
                WorkSpec workSpec = this.mWorkSpec;
                if (workSpec.state != WorkInfo$State.ENQUEUED) {
                    resolveIncorrectStatus();
                    this.mWorkDatabase.setTransactionSuccessful();
                    Logger logger = Logger.get();
                    String str = TAG;
                    logger.debug(str, this.mWorkSpec.workerClassName + " is not in ENQUEUED state. Nothing more to do");
                } else if ((workSpec.isPeriodic() || this.mWorkSpec.isBackedOff()) && System.currentTimeMillis() < this.mWorkSpec.calculateNextRunTime()) {
                    Logger.get().debug(TAG, String.format("Delaying execution for %s because it is being executed before schedule.", new Object[]{this.mWorkSpec.workerClassName}));
                    resolve(true);
                    this.mWorkDatabase.setTransactionSuccessful();
                    this.mWorkDatabase.endTransaction();
                } else {
                    this.mWorkDatabase.setTransactionSuccessful();
                    this.mWorkDatabase.endTransaction();
                    if (this.mWorkSpec.isPeriodic()) {
                        merge = this.mWorkSpec.input;
                    } else {
                        InputMerger createInputMergerWithDefaultFallback = this.mConfiguration.getInputMergerFactory().createInputMergerWithDefaultFallback(this.mWorkSpec.inputMergerClassName);
                        if (createInputMergerWithDefaultFallback == null) {
                            Logger logger2 = Logger.get();
                            String str2 = TAG;
                            logger2.error(str2, "Could not create Input Merger " + this.mWorkSpec.inputMergerClassName);
                            setFailedAndResolve();
                            return;
                        }
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(this.mWorkSpec.input);
                        arrayList.addAll(this.mWorkSpecDao.getInputsFromPrerequisites(this.mWorkSpecId));
                        merge = createInputMergerWithDefaultFallback.merge(arrayList);
                    }
                    Data data = merge;
                    UUID fromString = UUID.fromString(this.mWorkSpecId);
                    List list = this.mTags;
                    WorkerParameters.RuntimeExtras runtimeExtras = this.mRuntimeExtras;
                    WorkSpec workSpec2 = this.mWorkSpec;
                    WorkerParameters workerParameters = new WorkerParameters(fromString, data, list, runtimeExtras, workSpec2.runAttemptCount, workSpec2.getGeneration(), this.mConfiguration.getExecutor(), this.mWorkTaskExecutor, this.mConfiguration.getWorkerFactory(), new WorkProgressUpdater(this.mWorkDatabase, this.mWorkTaskExecutor), new WorkForegroundUpdater(this.mWorkDatabase, this.mForegroundProcessor, this.mWorkTaskExecutor));
                    if (this.mWorker == null) {
                        this.mWorker = this.mConfiguration.getWorkerFactory().createWorkerWithDefaultFallback(this.mAppContext, this.mWorkSpec.workerClassName, workerParameters);
                    }
                    ListenableWorker listenableWorker = this.mWorker;
                    if (listenableWorker == null) {
                        Logger logger3 = Logger.get();
                        String str3 = TAG;
                        logger3.error(str3, "Could not create Worker " + this.mWorkSpec.workerClassName);
                        setFailedAndResolve();
                    } else if (listenableWorker.isUsed()) {
                        Logger logger4 = Logger.get();
                        String str4 = TAG;
                        logger4.error(str4, "Received an already-used Worker " + this.mWorkSpec.workerClassName + "; Worker Factory should return new instances");
                        setFailedAndResolve();
                    } else {
                        this.mWorker.setUsed();
                        if (!trySetRunning()) {
                            resolveIncorrectStatus();
                        } else if (!tryCheckForInterruptionAndResolve()) {
                            WorkForegroundRunnable workForegroundRunnable = new WorkForegroundRunnable(this.mAppContext, this.mWorkSpec, this.mWorker, workerParameters.getForegroundUpdater(), this.mWorkTaskExecutor);
                            this.mWorkTaskExecutor.getMainThreadExecutor().execute(workForegroundRunnable);
                            final ListenableFuture future = workForegroundRunnable.getFuture();
                            this.mWorkerResultFuture.addListener(new WorkerWrapper$$ExternalSyntheticLambda0(this, future), new SynchronousExecutor());
                            future.addListener(new Runnable() {
                                public void run() {
                                    if (!WorkerWrapper.this.mWorkerResultFuture.isCancelled()) {
                                        try {
                                            future.get();
                                            Logger logger = Logger.get();
                                            String str = WorkerWrapper.TAG;
                                            logger.debug(str, "Starting work for " + WorkerWrapper.this.mWorkSpec.workerClassName);
                                            WorkerWrapper workerWrapper = WorkerWrapper.this;
                                            workerWrapper.mWorkerResultFuture.setFuture(workerWrapper.mWorker.startWork());
                                        } catch (Throwable th) {
                                            WorkerWrapper.this.mWorkerResultFuture.setException(th);
                                        }
                                    }
                                }
                            }, this.mWorkTaskExecutor.getMainThreadExecutor());
                            final String str5 = this.mWorkDescription;
                            this.mWorkerResultFuture.addListener(new Runnable() {
                                public void run() {
                                    try {
                                        ListenableWorker.Result result = (ListenableWorker.Result) WorkerWrapper.this.mWorkerResultFuture.get();
                                        if (result == null) {
                                            Logger logger = Logger.get();
                                            String str = WorkerWrapper.TAG;
                                            logger.error(str, WorkerWrapper.this.mWorkSpec.workerClassName + " returned a null result. Treating it as a failure.");
                                        } else {
                                            Logger logger2 = Logger.get();
                                            String str2 = WorkerWrapper.TAG;
                                            logger2.debug(str2, WorkerWrapper.this.mWorkSpec.workerClassName + " returned a " + result + ".");
                                            WorkerWrapper.this.mResult = result;
                                        }
                                    } catch (CancellationException e) {
                                        Logger logger3 = Logger.get();
                                        String str3 = WorkerWrapper.TAG;
                                        logger3.info(str3, str5 + " was cancelled", e);
                                    } catch (InterruptedException e2) {
                                        e = e2;
                                        Logger logger4 = Logger.get();
                                        String str4 = WorkerWrapper.TAG;
                                        logger4.error(str4, str5 + " failed because it threw an exception/error", e);
                                    } catch (ExecutionException e3) {
                                        e = e3;
                                        Logger logger42 = Logger.get();
                                        String str42 = WorkerWrapper.TAG;
                                        logger42.error(str42, str5 + " failed because it threw an exception/error", e);
                                    } catch (Throwable th) {
                                        WorkerWrapper.this.onWorkFinished();
                                        throw th;
                                    }
                                    WorkerWrapper.this.onWorkFinished();
                                }
                            }, this.mWorkTaskExecutor.getSerialTaskExecutor());
                        }
                    }
                }
            } finally {
                this.mWorkDatabase.endTransaction();
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$runWorker$0(ListenableFuture listenableFuture) {
        if (this.mWorkerResultFuture.isCancelled()) {
            listenableFuture.cancel(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void onWorkFinished() {
        if (!tryCheckForInterruptionAndResolve()) {
            this.mWorkDatabase.beginTransaction();
            try {
                WorkInfo$State state = this.mWorkSpecDao.getState(this.mWorkSpecId);
                this.mWorkDatabase.workProgressDao().delete(this.mWorkSpecId);
                if (state == null) {
                    resolve(false);
                } else if (state == WorkInfo$State.RUNNING) {
                    handleResult(this.mResult);
                } else if (!state.isFinished()) {
                    rescheduleAndResolve();
                }
                this.mWorkDatabase.setTransactionSuccessful();
                this.mWorkDatabase.endTransaction();
            } catch (Throwable th) {
                this.mWorkDatabase.endTransaction();
                throw th;
            }
        }
        List<Scheduler> list = this.mSchedulers;
        if (list != null) {
            for (Scheduler cancel : list) {
                cancel.cancel(this.mWorkSpecId);
            }
            Schedulers.schedule(this.mConfiguration, this.mWorkDatabase, this.mSchedulers);
        }
    }

    public void interrupt() {
        this.mInterrupted = true;
        tryCheckForInterruptionAndResolve();
        this.mWorkerResultFuture.cancel(true);
        if (this.mWorker == null || !this.mWorkerResultFuture.isCancelled()) {
            Logger.get().debug(TAG, "WorkSpec " + this.mWorkSpec + " is already done. Not interrupting.");
            return;
        }
        this.mWorker.stop();
    }

    private void resolveIncorrectStatus() {
        WorkInfo$State state = this.mWorkSpecDao.getState(this.mWorkSpecId);
        if (state == WorkInfo$State.RUNNING) {
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "Status for " + this.mWorkSpecId + " is RUNNING; not doing any work and rescheduling for later execution");
            resolve(true);
            return;
        }
        Logger logger2 = Logger.get();
        String str2 = TAG;
        logger2.debug(str2, "Status for " + this.mWorkSpecId + " is " + state + " ; not doing any work");
        resolve(false);
    }

    private boolean tryCheckForInterruptionAndResolve() {
        if (!this.mInterrupted) {
            return false;
        }
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Work interrupted for " + this.mWorkDescription);
        WorkInfo$State state = this.mWorkSpecDao.getState(this.mWorkSpecId);
        if (state == null) {
            resolve(false);
        } else {
            resolve(!state.isFinished());
        }
        return true;
    }

    private void resolve(boolean z) {
        this.mWorkDatabase.beginTransaction();
        try {
            if (!this.mWorkDatabase.workSpecDao().hasUnfinishedWork()) {
                PackageManagerHelper.setComponentEnabled(this.mAppContext, RescheduleReceiver.class, false);
            }
            if (z) {
                this.mWorkSpecDao.setState(WorkInfo$State.ENQUEUED, this.mWorkSpecId);
                this.mWorkSpecDao.markWorkSpecScheduled(this.mWorkSpecId, -1);
            }
            if (!(this.mWorkSpec == null || this.mWorker == null || !this.mForegroundProcessor.isEnqueuedInForeground(this.mWorkSpecId))) {
                this.mForegroundProcessor.stopForeground(this.mWorkSpecId);
            }
            this.mWorkDatabase.setTransactionSuccessful();
            this.mWorkDatabase.endTransaction();
            this.mFuture.set(Boolean.valueOf(z));
        } catch (Throwable th) {
            this.mWorkDatabase.endTransaction();
            throw th;
        }
    }

    private void handleResult(ListenableWorker.Result result) {
        if (result instanceof ListenableWorker.Result.Success) {
            Logger logger = Logger.get();
            String str = TAG;
            logger.info(str, "Worker result SUCCESS for " + this.mWorkDescription);
            if (this.mWorkSpec.isPeriodic()) {
                resetPeriodicAndResolve();
            } else {
                setSucceededAndResolve();
            }
        } else if (result instanceof ListenableWorker.Result.Retry) {
            Logger logger2 = Logger.get();
            String str2 = TAG;
            logger2.info(str2, "Worker result RETRY for " + this.mWorkDescription);
            rescheduleAndResolve();
        } else {
            Logger logger3 = Logger.get();
            String str3 = TAG;
            logger3.info(str3, "Worker result FAILURE for " + this.mWorkDescription);
            if (this.mWorkSpec.isPeriodic()) {
                resetPeriodicAndResolve();
            } else {
                setFailedAndResolve();
            }
        }
    }

    private boolean trySetRunning() {
        boolean z;
        this.mWorkDatabase.beginTransaction();
        try {
            if (this.mWorkSpecDao.getState(this.mWorkSpecId) == WorkInfo$State.ENQUEUED) {
                this.mWorkSpecDao.setState(WorkInfo$State.RUNNING, this.mWorkSpecId);
                this.mWorkSpecDao.incrementWorkSpecRunAttemptCount(this.mWorkSpecId);
                z = true;
            } else {
                z = false;
            }
            this.mWorkDatabase.setTransactionSuccessful();
            this.mWorkDatabase.endTransaction();
            return z;
        } catch (Throwable th) {
            this.mWorkDatabase.endTransaction();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public void setFailedAndResolve() {
        this.mWorkDatabase.beginTransaction();
        try {
            iterativelyFailWorkAndDependents(this.mWorkSpecId);
            this.mWorkSpecDao.setOutput(this.mWorkSpecId, ((ListenableWorker.Result.Failure) this.mResult).getOutputData());
            this.mWorkDatabase.setTransactionSuccessful();
        } finally {
            this.mWorkDatabase.endTransaction();
            resolve(false);
        }
    }

    private void iterativelyFailWorkAndDependents(String str) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(str);
        while (!linkedList.isEmpty()) {
            String str2 = (String) linkedList.remove();
            if (this.mWorkSpecDao.getState(str2) != WorkInfo$State.CANCELLED) {
                this.mWorkSpecDao.setState(WorkInfo$State.FAILED, str2);
            }
            linkedList.addAll(this.mDependencyDao.getDependentWorkIds(str2));
        }
    }

    private void rescheduleAndResolve() {
        this.mWorkDatabase.beginTransaction();
        try {
            this.mWorkSpecDao.setState(WorkInfo$State.ENQUEUED, this.mWorkSpecId);
            this.mWorkSpecDao.setLastEnqueuedTime(this.mWorkSpecId, System.currentTimeMillis());
            this.mWorkSpecDao.markWorkSpecScheduled(this.mWorkSpecId, -1);
            this.mWorkDatabase.setTransactionSuccessful();
        } finally {
            this.mWorkDatabase.endTransaction();
            resolve(true);
        }
    }

    private void resetPeriodicAndResolve() {
        this.mWorkDatabase.beginTransaction();
        try {
            this.mWorkSpecDao.setLastEnqueuedTime(this.mWorkSpecId, System.currentTimeMillis());
            this.mWorkSpecDao.setState(WorkInfo$State.ENQUEUED, this.mWorkSpecId);
            this.mWorkSpecDao.resetWorkSpecRunAttemptCount(this.mWorkSpecId);
            this.mWorkSpecDao.incrementPeriodCount(this.mWorkSpecId);
            this.mWorkSpecDao.markWorkSpecScheduled(this.mWorkSpecId, -1);
            this.mWorkDatabase.setTransactionSuccessful();
        } finally {
            this.mWorkDatabase.endTransaction();
            resolve(false);
        }
    }

    private void setSucceededAndResolve() {
        this.mWorkDatabase.beginTransaction();
        try {
            this.mWorkSpecDao.setState(WorkInfo$State.SUCCEEDED, this.mWorkSpecId);
            this.mWorkSpecDao.setOutput(this.mWorkSpecId, ((ListenableWorker.Result.Success) this.mResult).getOutputData());
            long currentTimeMillis = System.currentTimeMillis();
            for (String str : this.mDependencyDao.getDependentWorkIds(this.mWorkSpecId)) {
                if (this.mWorkSpecDao.getState(str) == WorkInfo$State.BLOCKED && this.mDependencyDao.hasCompletedAllPrerequisites(str)) {
                    Logger logger = Logger.get();
                    String str2 = TAG;
                    logger.info(str2, "Setting status to enqueued for " + str);
                    this.mWorkSpecDao.setState(WorkInfo$State.ENQUEUED, str);
                    this.mWorkSpecDao.setLastEnqueuedTime(str, currentTimeMillis);
                }
            }
            this.mWorkDatabase.setTransactionSuccessful();
            this.mWorkDatabase.endTransaction();
            resolve(false);
        } catch (Throwable th) {
            this.mWorkDatabase.endTransaction();
            resolve(false);
            throw th;
        }
    }

    private String createWorkDescription(List list) {
        StringBuilder sb = new StringBuilder("Work [ id=");
        sb.append(this.mWorkSpecId);
        sb.append(", tags={ ");
        Iterator it = list.iterator();
        boolean z = true;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(str);
        }
        sb.append(" } ]");
        return sb.toString();
    }

    public static class Builder {
        Context mAppContext;
        Configuration mConfiguration;
        ForegroundProcessor mForegroundProcessor;
        WorkerParameters.RuntimeExtras mRuntimeExtras = new WorkerParameters.RuntimeExtras();
        List mSchedulers;
        /* access modifiers changed from: private */
        public final List mTags;
        WorkDatabase mWorkDatabase;
        WorkSpec mWorkSpec;
        TaskExecutor mWorkTaskExecutor;
        ListenableWorker mWorker;

        public Builder(Context context, Configuration configuration, TaskExecutor taskExecutor, ForegroundProcessor foregroundProcessor, WorkDatabase workDatabase, WorkSpec workSpec, List list) {
            this.mAppContext = context.getApplicationContext();
            this.mWorkTaskExecutor = taskExecutor;
            this.mForegroundProcessor = foregroundProcessor;
            this.mConfiguration = configuration;
            this.mWorkDatabase = workDatabase;
            this.mWorkSpec = workSpec;
            this.mTags = list;
        }

        public Builder withSchedulers(List list) {
            this.mSchedulers = list;
            return this;
        }

        public Builder withRuntimeExtras(WorkerParameters.RuntimeExtras runtimeExtras) {
            if (runtimeExtras != null) {
                this.mRuntimeExtras = runtimeExtras;
            }
            return this;
        }

        public WorkerWrapper build() {
            return new WorkerWrapper(this);
        }
    }
}
