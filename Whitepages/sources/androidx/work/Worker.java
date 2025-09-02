package androidx.work;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.impl.utils.futures.SettableFuture;
import com.google.common.util.concurrent.ListenableFuture;

public abstract class Worker extends ListenableWorker {
    SettableFuture mFuture;

    public abstract ListenableWorker.Result doWork();

    public Worker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    public final ListenableFuture startWork() {
        this.mFuture = SettableFuture.create();
        getBackgroundExecutor().execute(new Runnable() {
            public void run() {
                try {
                    Worker.this.mFuture.set(Worker.this.doWork());
                } catch (Throwable th) {
                    Worker.this.mFuture.setException(th);
                }
            }
        });
        return this.mFuture;
    }

    public ListenableFuture getForegroundInfoAsync() {
        final SettableFuture create = SettableFuture.create();
        getBackgroundExecutor().execute(new Runnable() {
            public void run() {
                try {
                    create.set(Worker.this.getForegroundInfo());
                } catch (Throwable th) {
                    create.setException(th);
                }
            }
        });
        return create;
    }

    public ForegroundInfo getForegroundInfo() {
        throw new IllegalStateException("Expedited WorkRequests require a Worker to provide an implementation for \n `getForegroundInfo()`");
    }
}
