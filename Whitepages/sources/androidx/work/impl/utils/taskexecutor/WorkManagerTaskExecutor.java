package androidx.work.impl.utils.taskexecutor;

import android.os.Handler;
import android.os.Looper;
import androidx.work.impl.utils.SerialExecutorImpl;
import java.util.concurrent.Executor;

public class WorkManagerTaskExecutor implements TaskExecutor {
    private final SerialExecutorImpl mBackgroundExecutor;
    private final Executor mMainThreadExecutor = new Executor() {
        public void execute(Runnable runnable) {
            WorkManagerTaskExecutor.this.mMainThreadHandler.post(runnable);
        }
    };
    final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());

    public WorkManagerTaskExecutor(Executor executor) {
        this.mBackgroundExecutor = new SerialExecutorImpl(executor);
    }

    public Executor getMainThreadExecutor() {
        return this.mMainThreadExecutor;
    }

    public SerialExecutorImpl getSerialTaskExecutor() {
        return this.mBackgroundExecutor;
    }
}
