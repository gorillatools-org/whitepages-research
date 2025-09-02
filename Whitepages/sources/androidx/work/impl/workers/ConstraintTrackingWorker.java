package androidx.work.impl.workers;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTrackerImpl;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.futures.SettableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class ConstraintTrackingWorker extends ListenableWorker implements WorkConstraintsCallback {
    private volatile boolean areConstraintsUnmet;
    private ListenableWorker delegate;
    private final SettableFuture future = SettableFuture.create();
    private final Object lock = new Object();
    private final WorkerParameters workerParameters;

    public void onAllConstraintsMet(List list) {
        Intrinsics.checkNotNullParameter(list, "workSpecs");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConstraintTrackingWorker(Context context, WorkerParameters workerParameters2) {
        super(context, workerParameters2);
        Intrinsics.checkNotNullParameter(context, "appContext");
        Intrinsics.checkNotNullParameter(workerParameters2, "workerParameters");
        this.workerParameters = workerParameters2;
    }

    /* access modifiers changed from: private */
    public static final void startWork$lambda$0(ConstraintTrackingWorker constraintTrackingWorker) {
        Intrinsics.checkNotNullParameter(constraintTrackingWorker, "this$0");
        constraintTrackingWorker.setupAndRunConstraintTrackingWork();
    }

    public ListenableFuture startWork() {
        getBackgroundExecutor().execute(new ConstraintTrackingWorker$$ExternalSyntheticLambda0(this));
        SettableFuture settableFuture = this.future;
        Intrinsics.checkNotNullExpressionValue(settableFuture, "future");
        return settableFuture;
    }

    private final void setupAndRunConstraintTrackingWork() {
        if (!this.future.isCancelled()) {
            String string = getInputData().getString("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME");
            Logger logger = Logger.get();
            Intrinsics.checkNotNullExpressionValue(logger, "get()");
            if (string == null || string.length() == 0) {
                logger.error(ConstraintTrackingWorkerKt.TAG, "No worker to delegate to.");
                SettableFuture settableFuture = this.future;
                Intrinsics.checkNotNullExpressionValue(settableFuture, "future");
                boolean unused = ConstraintTrackingWorkerKt.setFailed(settableFuture);
                return;
            }
            ListenableWorker createWorkerWithDefaultFallback = getWorkerFactory().createWorkerWithDefaultFallback(getApplicationContext(), string, this.workerParameters);
            this.delegate = createWorkerWithDefaultFallback;
            if (createWorkerWithDefaultFallback == null) {
                logger.debug(ConstraintTrackingWorkerKt.TAG, "No worker to delegate to.");
                SettableFuture settableFuture2 = this.future;
                Intrinsics.checkNotNullExpressionValue(settableFuture2, "future");
                boolean unused2 = ConstraintTrackingWorkerKt.setFailed(settableFuture2);
                return;
            }
            WorkManagerImpl instance = WorkManagerImpl.getInstance(getApplicationContext());
            Intrinsics.checkNotNullExpressionValue(instance, "getInstance(applicationContext)");
            WorkSpecDao workSpecDao = instance.getWorkDatabase().workSpecDao();
            String uuid = getId().toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "id.toString()");
            WorkSpec workSpec = workSpecDao.getWorkSpec(uuid);
            if (workSpec == null) {
                SettableFuture settableFuture3 = this.future;
                Intrinsics.checkNotNullExpressionValue(settableFuture3, "future");
                boolean unused3 = ConstraintTrackingWorkerKt.setFailed(settableFuture3);
                return;
            }
            Trackers trackers = instance.getTrackers();
            Intrinsics.checkNotNullExpressionValue(trackers, "workManagerImpl.trackers");
            WorkConstraintsTrackerImpl workConstraintsTrackerImpl = new WorkConstraintsTrackerImpl(trackers, (WorkConstraintsCallback) this);
            workConstraintsTrackerImpl.replace(CollectionsKt.listOf(workSpec));
            String uuid2 = getId().toString();
            Intrinsics.checkNotNullExpressionValue(uuid2, "id.toString()");
            if (workConstraintsTrackerImpl.areAllConstraintsMet(uuid2)) {
                String access$getTAG$p = ConstraintTrackingWorkerKt.TAG;
                logger.debug(access$getTAG$p, "Constraints met for delegate " + string);
                try {
                    ListenableWorker listenableWorker = this.delegate;
                    Intrinsics.checkNotNull(listenableWorker);
                    ListenableFuture startWork = listenableWorker.startWork();
                    Intrinsics.checkNotNullExpressionValue(startWork, "delegate!!.startWork()");
                    startWork.addListener(new ConstraintTrackingWorker$$ExternalSyntheticLambda1(this, startWork), getBackgroundExecutor());
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                String access$getTAG$p2 = ConstraintTrackingWorkerKt.TAG;
                logger.debug(access$getTAG$p2, "Constraints not met for delegate " + string + ". Requesting retry.");
                SettableFuture settableFuture4 = this.future;
                Intrinsics.checkNotNullExpressionValue(settableFuture4, "future");
                boolean unused4 = ConstraintTrackingWorkerKt.setRetry(settableFuture4);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void setupAndRunConstraintTrackingWork$lambda$2(ConstraintTrackingWorker constraintTrackingWorker, ListenableFuture listenableFuture) {
        Intrinsics.checkNotNullParameter(constraintTrackingWorker, "this$0");
        Intrinsics.checkNotNullParameter(listenableFuture, "$innerFuture");
        synchronized (constraintTrackingWorker.lock) {
            try {
                if (constraintTrackingWorker.areConstraintsUnmet) {
                    SettableFuture settableFuture = constraintTrackingWorker.future;
                    Intrinsics.checkNotNullExpressionValue(settableFuture, "future");
                    boolean unused = ConstraintTrackingWorkerKt.setRetry(settableFuture);
                } else {
                    constraintTrackingWorker.future.setFuture(listenableFuture);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void onStopped() {
        super.onStopped();
        ListenableWorker listenableWorker = this.delegate;
        if (listenableWorker != null && !listenableWorker.isStopped()) {
            listenableWorker.stop();
        }
    }

    public void onAllConstraintsNotMet(List list) {
        Intrinsics.checkNotNullParameter(list, "workSpecs");
        Logger logger = Logger.get();
        String access$getTAG$p = ConstraintTrackingWorkerKt.TAG;
        logger.debug(access$getTAG$p, "Constraints changed for " + list);
        synchronized (this.lock) {
            this.areConstraintsUnmet = true;
            Unit unit = Unit.INSTANCE;
        }
    }
}
