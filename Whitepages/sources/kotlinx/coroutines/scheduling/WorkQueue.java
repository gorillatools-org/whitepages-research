package kotlinx.coroutines.scheduling;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.channels.ChannelSegment$$ExternalSyntheticBackportWithForwarding0;

public final class WorkQueue {
    private static final AtomicIntegerFieldUpdater blockingTasksInBuffer$FU;
    private static final AtomicIntegerFieldUpdater consumerIndex$FU;
    private static final AtomicReferenceFieldUpdater lastScheduledTask$FU;
    private static final AtomicIntegerFieldUpdater producerIndex$FU;
    private volatile int blockingTasksInBuffer;
    private final AtomicReferenceArray buffer = new AtomicReferenceArray(128);
    private volatile int consumerIndex;
    private volatile Object lastScheduledTask;
    private volatile int producerIndex;

    static {
        Class<WorkQueue> cls = WorkQueue.class;
        lastScheduledTask$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "lastScheduledTask");
        producerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "producerIndex");
        consumerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "consumerIndex");
        blockingTasksInBuffer$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "blockingTasksInBuffer");
    }

    private final int getBufferSize() {
        return producerIndex$FU.get(this) - consumerIndex$FU.get(this);
    }

    public final int getSize$kotlinx_coroutines_core() {
        return lastScheduledTask$FU.get(this) != null ? getBufferSize() + 1 : getBufferSize();
    }

    public final Task poll() {
        Task task = (Task) lastScheduledTask$FU.getAndSet(this, (Object) null);
        return task == null ? pollBuffer() : task;
    }

    public final Task add(Task task, boolean z) {
        if (z) {
            return addLast(task);
        }
        Task task2 = (Task) lastScheduledTask$FU.getAndSet(this, task);
        if (task2 == null) {
            return null;
        }
        return addLast(task2);
    }

    private final void decrementIfBlocking(Task task) {
        if (task != null && task.taskContext.getTaskMode() == 1) {
            blockingTasksInBuffer$FU.decrementAndGet(this);
        }
    }

    private final Task addLast(Task task) {
        if (getBufferSize() == 127) {
            return task;
        }
        if (task.taskContext.getTaskMode() == 1) {
            blockingTasksInBuffer$FU.incrementAndGet(this);
        }
        int i = producerIndex$FU.get(this) & 127;
        while (this.buffer.get(i) != null) {
            Thread.yield();
        }
        this.buffer.lazySet(i, task);
        producerIndex$FU.incrementAndGet(this);
        return null;
    }

    public final long trySteal(int i, Ref$ObjectRef ref$ObjectRef) {
        Task task;
        if (i == 3) {
            task = pollBuffer();
        } else {
            task = stealWithExclusiveMode(i);
        }
        if (task == null) {
            return tryStealLastScheduled(i, ref$ObjectRef);
        }
        ref$ObjectRef.element = task;
        return -1;
    }

    private final Task stealWithExclusiveMode(int i) {
        int i2 = consumerIndex$FU.get(this);
        int i3 = producerIndex$FU.get(this);
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        while (i2 != i3) {
            if (z && blockingTasksInBuffer$FU.get(this) == 0) {
                return null;
            }
            int i4 = i2 + 1;
            Task tryExtractFromTheMiddle = tryExtractFromTheMiddle(i2, z);
            if (tryExtractFromTheMiddle != null) {
                return tryExtractFromTheMiddle;
            }
            i2 = i4;
        }
        return null;
    }

    public final Task pollBlocking() {
        return pollWithExclusiveMode(true);
    }

    private final Task pollWithExclusiveMode(boolean z) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Task task;
        do {
            atomicReferenceFieldUpdater = lastScheduledTask$FU;
            task = (Task) atomicReferenceFieldUpdater.get(this);
            if (task != null) {
                boolean z2 = true;
                if (task.taskContext.getTaskMode() != 1) {
                    z2 = false;
                }
                if (z2 == z) {
                }
            }
            int i = consumerIndex$FU.get(this);
            int i2 = producerIndex$FU.get(this);
            while (i != i2) {
                if (z && blockingTasksInBuffer$FU.get(this) == 0) {
                    return null;
                }
                i2--;
                Task tryExtractFromTheMiddle = tryExtractFromTheMiddle(i2, z);
                if (tryExtractFromTheMiddle != null) {
                    return tryExtractFromTheMiddle;
                }
            }
            return null;
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, task, (Object) null));
        return task;
    }

    private final Task tryExtractFromTheMiddle(int i, boolean z) {
        int i2 = i & 127;
        Task task = (Task) this.buffer.get(i2);
        if (task != null) {
            boolean z2 = true;
            if (task.taskContext.getTaskMode() != 1) {
                z2 = false;
            }
            if (z2 == z && ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(this.buffer, i2, task, (Object) null)) {
                if (z) {
                    blockingTasksInBuffer$FU.decrementAndGet(this);
                }
                return task;
            }
        }
        return null;
    }

    public final void offloadAllWorkTo(GlobalQueue globalQueue) {
        Task task = (Task) lastScheduledTask$FU.getAndSet(this, (Object) null);
        if (task != null) {
            globalQueue.addLast(task);
        }
        do {
        } while (pollTo(globalQueue));
    }

    private final long tryStealLastScheduled(int i, Ref$ObjectRef ref$ObjectRef) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Task task;
        do {
            atomicReferenceFieldUpdater = lastScheduledTask$FU;
            task = (Task) atomicReferenceFieldUpdater.get(this);
            if (task == null) {
                return -2;
            }
            int i2 = 1;
            if (task.taskContext.getTaskMode() != 1) {
                i2 = 2;
            }
            if ((i2 & i) == 0) {
                return -2;
            }
            long nanoTime = TasksKt.schedulerTimeSource.nanoTime() - task.submissionTime;
            long j = TasksKt.WORK_STEALING_TIME_RESOLUTION_NS;
            if (nanoTime < j) {
                return j - nanoTime;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, task, (Object) null));
        ref$ObjectRef.element = task;
        return -1;
    }

    private final boolean pollTo(GlobalQueue globalQueue) {
        Task pollBuffer = pollBuffer();
        if (pollBuffer == null) {
            return false;
        }
        globalQueue.addLast(pollBuffer);
        return true;
    }

    private final Task pollBuffer() {
        Task task;
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = consumerIndex$FU;
            int i = atomicIntegerFieldUpdater.get(this);
            if (i - producerIndex$FU.get(this) == 0) {
                return null;
            }
            int i2 = i & 127;
            if (atomicIntegerFieldUpdater.compareAndSet(this, i, i + 1) && (task = (Task) this.buffer.getAndSet(i2, (Object) null)) != null) {
                decrementIfBlocking(task);
                return task;
            }
        }
    }
}
