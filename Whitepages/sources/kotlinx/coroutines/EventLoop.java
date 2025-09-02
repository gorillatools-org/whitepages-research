package kotlinx.coroutines;

import kotlin.collections.ArrayDeque;

public abstract class EventLoop extends CoroutineDispatcher {
    private boolean shared;
    private ArrayDeque unconfinedQueue;
    private long useCount;

    private final long delta(boolean z) {
        return z ? 4294967296L : 1;
    }

    public abstract long processNextEvent();

    public boolean shouldBeProcessedFromContext() {
        return false;
    }

    public abstract void shutdown();

    /* access modifiers changed from: protected */
    public long getNextTime() {
        ArrayDeque arrayDeque = this.unconfinedQueue;
        if (arrayDeque != null && !arrayDeque.isEmpty()) {
            return 0;
        }
        return Long.MAX_VALUE;
    }

    public final boolean processUnconfinedEvent() {
        DispatchedTask dispatchedTask;
        ArrayDeque arrayDeque = this.unconfinedQueue;
        if (arrayDeque == null || (dispatchedTask = (DispatchedTask) arrayDeque.removeFirstOrNull()) == null) {
            return false;
        }
        dispatchedTask.run();
        return true;
    }

    public final void dispatchUnconfined(DispatchedTask dispatchedTask) {
        ArrayDeque arrayDeque = this.unconfinedQueue;
        if (arrayDeque == null) {
            arrayDeque = new ArrayDeque();
            this.unconfinedQueue = arrayDeque;
        }
        arrayDeque.addLast(dispatchedTask);
    }

    public final boolean isUnconfinedLoopActive() {
        return this.useCount >= delta(true);
    }

    public final boolean isUnconfinedQueueEmpty() {
        ArrayDeque arrayDeque = this.unconfinedQueue;
        if (arrayDeque != null) {
            return arrayDeque.isEmpty();
        }
        return true;
    }

    public static /* synthetic */ void incrementUseCount$default(EventLoop eventLoop, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                z = false;
            }
            eventLoop.incrementUseCount(z);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incrementUseCount");
    }

    public final void incrementUseCount(boolean z) {
        this.useCount += delta(z);
        if (!z) {
            this.shared = true;
        }
    }

    public static /* synthetic */ void decrementUseCount$default(EventLoop eventLoop, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                z = false;
            }
            eventLoop.decrementUseCount(z);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decrementUseCount");
    }

    public final void decrementUseCount(boolean z) {
        long delta = this.useCount - delta(z);
        this.useCount = delta;
        if (delta <= 0 && this.shared) {
            shutdown();
        }
    }
}
