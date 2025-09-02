package androidx.room;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

public final class TransactionExecutor implements Executor {
    private Runnable active;
    private final Executor executor;
    private final Object syncLock = new Object();
    private final ArrayDeque tasks = new ArrayDeque();

    public TransactionExecutor(Executor executor2) {
        Intrinsics.checkNotNullParameter(executor2, "executor");
        this.executor = executor2;
    }

    public void execute(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "command");
        synchronized (this.syncLock) {
            try {
                this.tasks.offer(new TransactionExecutor$$ExternalSyntheticLambda0(runnable, this));
                if (this.active == null) {
                    scheduleNext();
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void execute$lambda$1$lambda$0(Runnable runnable, TransactionExecutor transactionExecutor) {
        Intrinsics.checkNotNullParameter(runnable, "$command");
        Intrinsics.checkNotNullParameter(transactionExecutor, "this$0");
        try {
            runnable.run();
        } finally {
            transactionExecutor.scheduleNext();
        }
    }

    public final void scheduleNext() {
        synchronized (this.syncLock) {
            try {
                Object poll = this.tasks.poll();
                Runnable runnable = (Runnable) poll;
                this.active = runnable;
                if (poll != null) {
                    this.executor.execute(runnable);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
