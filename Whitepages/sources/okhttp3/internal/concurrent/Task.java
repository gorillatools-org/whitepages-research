package okhttp3.internal.concurrent;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class Task {
    private final boolean cancelable;
    private final String name;
    private long nextExecuteNanoTime;
    private TaskQueue queue;

    public abstract long runOnce();

    public Task(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "name");
        this.name = str;
        this.cancelable = z;
        this.nextExecuteNanoTime = -1;
    }

    public final String getName() {
        return this.name;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Task(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? true : z);
    }

    public final boolean getCancelable() {
        return this.cancelable;
    }

    public final TaskQueue getQueue$okhttp() {
        return this.queue;
    }

    public final void setQueue$okhttp(TaskQueue taskQueue) {
        this.queue = taskQueue;
    }

    public final long getNextExecuteNanoTime$okhttp() {
        return this.nextExecuteNanoTime;
    }

    public final void setNextExecuteNanoTime$okhttp(long j) {
        this.nextExecuteNanoTime = j;
    }

    public final void initQueue$okhttp(TaskQueue taskQueue) {
        Intrinsics.checkNotNullParameter(taskQueue, "queue");
        TaskQueue taskQueue2 = this.queue;
        if (taskQueue2 != taskQueue) {
            if (taskQueue2 == null) {
                this.queue = taskQueue;
                return;
            }
            throw new IllegalStateException("task is in multiple queues");
        }
    }

    public String toString() {
        return this.name;
    }
}
