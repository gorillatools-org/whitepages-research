package okhttp3.internal.concurrent;

import java.util.logging.Level;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

public final class TaskRunner$runnable$1 implements Runnable {
    final /* synthetic */ TaskRunner this$0;

    TaskRunner$runnable$1(TaskRunner taskRunner) {
        this.this$0 = taskRunner;
    }

    public void run() {
        Task awaitTaskToRun;
        long j;
        while (true) {
            synchronized (this.this$0) {
                awaitTaskToRun = this.this$0.awaitTaskToRun();
            }
            if (awaitTaskToRun != null) {
                TaskQueue queue$okhttp = awaitTaskToRun.getQueue$okhttp();
                Intrinsics.checkNotNull(queue$okhttp);
                boolean isLoggable = TaskRunner.Companion.getLogger().isLoggable(Level.FINE);
                if (isLoggable) {
                    j = queue$okhttp.getTaskRunner$okhttp().getBackend().nanoTime();
                    TaskLoggerKt.log(awaitTaskToRun, queue$okhttp, "starting");
                } else {
                    j = -1;
                }
                try {
                    this.this$0.runTask(awaitTaskToRun);
                    Unit unit = Unit.INSTANCE;
                    if (isLoggable) {
                        TaskLoggerKt.log(awaitTaskToRun, queue$okhttp, "finished run in " + TaskLoggerKt.formatDuration(queue$okhttp.getTaskRunner$okhttp().getBackend().nanoTime() - j));
                    }
                } catch (Throwable th) {
                    if (isLoggable) {
                        TaskLoggerKt.log(awaitTaskToRun, queue$okhttp, "failed a run in " + TaskLoggerKt.formatDuration(queue$okhttp.getTaskRunner$okhttp().getBackend().nanoTime() - j));
                    }
                    throw th;
                }
            } else {
                return;
            }
        }
    }
}
