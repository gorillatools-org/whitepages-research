package androidx.work.impl.utils;

import android.content.Context;
import android.os.Build;
import androidx.work.ForegroundInfo;
import androidx.work.ForegroundUpdater;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;

public class WorkForegroundRunnable implements Runnable {
    static final String TAG = Logger.tagWithPrefix("WorkForegroundRunnable");
    final Context mContext;
    final ForegroundUpdater mForegroundUpdater;
    final SettableFuture mFuture = SettableFuture.create();
    final TaskExecutor mTaskExecutor;
    final WorkSpec mWorkSpec;
    final ListenableWorker mWorker;

    public WorkForegroundRunnable(Context context, WorkSpec workSpec, ListenableWorker listenableWorker, ForegroundUpdater foregroundUpdater, TaskExecutor taskExecutor) {
        this.mContext = context;
        this.mWorkSpec = workSpec;
        this.mWorker = listenableWorker;
        this.mForegroundUpdater = foregroundUpdater;
        this.mTaskExecutor = taskExecutor;
    }

    public ListenableFuture getFuture() {
        return this.mFuture;
    }

    public void run() {
        if (!this.mWorkSpec.expedited || Build.VERSION.SDK_INT >= 31) {
            this.mFuture.set((Object) null);
            return;
        }
        final SettableFuture create = SettableFuture.create();
        this.mTaskExecutor.getMainThreadExecutor().execute(new WorkForegroundRunnable$$ExternalSyntheticLambda0(this, create));
        create.addListener(new Runnable() {
            public void run() {
                if (!WorkForegroundRunnable.this.mFuture.isCancelled()) {
                    try {
                        ForegroundInfo foregroundInfo = (ForegroundInfo) create.get();
                        if (foregroundInfo != null) {
                            Logger logger = Logger.get();
                            String str = WorkForegroundRunnable.TAG;
                            logger.debug(str, "Updating notification for " + WorkForegroundRunnable.this.mWorkSpec.workerClassName);
                            WorkForegroundRunnable workForegroundRunnable = WorkForegroundRunnable.this;
                            workForegroundRunnable.mFuture.setFuture(workForegroundRunnable.mForegroundUpdater.setForegroundAsync(workForegroundRunnable.mContext, workForegroundRunnable.mWorker.getId(), foregroundInfo));
                            return;
                        }
                        throw new IllegalStateException("Worker was marked important (" + WorkForegroundRunnable.this.mWorkSpec.workerClassName + ") but did not provide ForegroundInfo");
                    } catch (Throwable th) {
                        WorkForegroundRunnable.this.mFuture.setException(th);
                    }
                }
            }
        }, this.mTaskExecutor.getMainThreadExecutor());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run$0(SettableFuture settableFuture) {
        if (!this.mFuture.isCancelled()) {
            settableFuture.setFuture(this.mWorker.getForegroundInfoAsync());
        } else {
            settableFuture.cancel(true);
        }
    }
}
