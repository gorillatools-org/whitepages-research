package okhttp3.internal.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;

public final class TaskQueue {
    private Task activeTask;
    private boolean cancelActiveTask;
    private final List<Task> futureTasks = new ArrayList();
    private final String name;
    private boolean shutdown;
    private final TaskRunner taskRunner;

    public TaskQueue(TaskRunner taskRunner2, String str) {
        Intrinsics.checkNotNullParameter(taskRunner2, "taskRunner");
        Intrinsics.checkNotNullParameter(str, "name");
        this.taskRunner = taskRunner2;
        this.name = str;
    }

    public final TaskRunner getTaskRunner$okhttp() {
        return this.taskRunner;
    }

    public final String getName$okhttp() {
        return this.name;
    }

    public final boolean getShutdown$okhttp() {
        return this.shutdown;
    }

    public final void setShutdown$okhttp(boolean z) {
        this.shutdown = z;
    }

    public final Task getActiveTask$okhttp() {
        return this.activeTask;
    }

    public final void setActiveTask$okhttp(Task task) {
        this.activeTask = task;
    }

    public final List<Task> getFutureTasks$okhttp() {
        return this.futureTasks;
    }

    public final boolean getCancelActiveTask$okhttp() {
        return this.cancelActiveTask;
    }

    public final void setCancelActiveTask$okhttp(boolean z) {
        this.cancelActiveTask = z;
    }

    public final List<Task> getScheduledTasks() {
        List<Task> list;
        synchronized (this.taskRunner) {
            list = CollectionsKt.toList(this.futureTasks);
        }
        return list;
    }

    public static /* synthetic */ void schedule$default(TaskQueue taskQueue, Task task, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        taskQueue.schedule(task, j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void schedule(okhttp3.internal.concurrent.Task r3, long r4) {
        /*
            r2 = this;
            java.lang.String r0 = "task"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            okhttp3.internal.concurrent.TaskRunner r0 = r2.taskRunner
            monitor-enter(r0)
            boolean r1 = r2.shutdown     // Catch:{ all -> 0x0026 }
            if (r1 == 0) goto L_0x0043
            boolean r4 = r3.getCancelable()     // Catch:{ all -> 0x0026 }
            if (r4 == 0) goto L_0x002a
            okhttp3.internal.concurrent.TaskRunner$Companion r4 = okhttp3.internal.concurrent.TaskRunner.Companion     // Catch:{ all -> 0x0026 }
            java.util.logging.Logger r4 = r4.getLogger()     // Catch:{ all -> 0x0026 }
            java.util.logging.Level r5 = java.util.logging.Level.FINE     // Catch:{ all -> 0x0026 }
            boolean r4 = r4.isLoggable(r5)     // Catch:{ all -> 0x0026 }
            if (r4 == 0) goto L_0x0028
            java.lang.String r4 = "schedule canceled (queue is shutdown)"
            okhttp3.internal.concurrent.TaskLoggerKt.log(r3, r2, r4)     // Catch:{ all -> 0x0026 }
            goto L_0x0028
        L_0x0026:
            r3 = move-exception
            goto L_0x0053
        L_0x0028:
            monitor-exit(r0)
            return
        L_0x002a:
            okhttp3.internal.concurrent.TaskRunner$Companion r4 = okhttp3.internal.concurrent.TaskRunner.Companion     // Catch:{ all -> 0x0026 }
            java.util.logging.Logger r4 = r4.getLogger()     // Catch:{ all -> 0x0026 }
            java.util.logging.Level r5 = java.util.logging.Level.FINE     // Catch:{ all -> 0x0026 }
            boolean r4 = r4.isLoggable(r5)     // Catch:{ all -> 0x0026 }
            if (r4 == 0) goto L_0x003d
            java.lang.String r4 = "schedule failed (queue is shutdown)"
            okhttp3.internal.concurrent.TaskLoggerKt.log(r3, r2, r4)     // Catch:{ all -> 0x0026 }
        L_0x003d:
            java.util.concurrent.RejectedExecutionException r3 = new java.util.concurrent.RejectedExecutionException     // Catch:{ all -> 0x0026 }
            r3.<init>()     // Catch:{ all -> 0x0026 }
            throw r3     // Catch:{ all -> 0x0026 }
        L_0x0043:
            r1 = 0
            boolean r3 = r2.scheduleAndDecide$okhttp(r3, r4, r1)     // Catch:{ all -> 0x0026 }
            if (r3 == 0) goto L_0x004f
            okhttp3.internal.concurrent.TaskRunner r3 = r2.taskRunner     // Catch:{ all -> 0x0026 }
            r3.kickCoordinator$okhttp(r2)     // Catch:{ all -> 0x0026 }
        L_0x004f:
            kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0026 }
            monitor-exit(r0)
            return
        L_0x0053:
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.concurrent.TaskQueue.schedule(okhttp3.internal.concurrent.Task, long):void");
    }

    public static /* synthetic */ void schedule$default(TaskQueue taskQueue, String str, long j, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function0, "block");
        taskQueue.schedule(new TaskQueue$schedule$2(function0, str, str), j);
    }

    public final void schedule(String str, long j, Function0 function0) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function0, "block");
        schedule(new TaskQueue$schedule$2(function0, str, str), j);
    }

    public static /* synthetic */ void execute$default(TaskQueue taskQueue, String str, long j, boolean z, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        if ((i & 4) != 0) {
            z = true;
        }
        boolean z2 = z;
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function0, "block");
        taskQueue.schedule(new TaskQueue$execute$1(function0, str, z2, str, z2), j);
    }

    public final void execute(String str, long j, boolean z, Function0 function0) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function0, "block");
        schedule(new TaskQueue$execute$1(function0, str, z, str, z), j);
    }

    public final CountDownLatch idleLatch() {
        synchronized (this.taskRunner) {
            if (this.activeTask != null || !this.futureTasks.isEmpty()) {
                Task task = this.activeTask;
                if (task instanceof AwaitIdleTask) {
                    CountDownLatch latch = ((AwaitIdleTask) task).getLatch();
                    return latch;
                }
                for (Task next : this.futureTasks) {
                    if (next instanceof AwaitIdleTask) {
                        CountDownLatch latch2 = ((AwaitIdleTask) next).getLatch();
                        return latch2;
                    }
                }
                AwaitIdleTask awaitIdleTask = new AwaitIdleTask();
                if (scheduleAndDecide$okhttp(awaitIdleTask, 0, false)) {
                    this.taskRunner.kickCoordinator$okhttp(this);
                }
                CountDownLatch latch3 = awaitIdleTask.getLatch();
                return latch3;
            }
            CountDownLatch countDownLatch = new CountDownLatch(0);
            return countDownLatch;
        }
    }

    private static final class AwaitIdleTask extends Task {
        private final CountDownLatch latch = new CountDownLatch(1);

        public AwaitIdleTask() {
            super(Util.okHttpName + " awaitIdle", false);
        }

        public final CountDownLatch getLatch() {
            return this.latch;
        }

        public long runOnce() {
            this.latch.countDown();
            return -1;
        }
    }

    public final boolean scheduleAndDecide$okhttp(Task task, long j, boolean z) {
        String str;
        Intrinsics.checkNotNullParameter(task, "task");
        task.initQueue$okhttp(this);
        long nanoTime = this.taskRunner.getBackend().nanoTime();
        long j2 = nanoTime + j;
        int indexOf = this.futureTasks.indexOf(task);
        if (indexOf != -1) {
            if (task.getNextExecuteNanoTime$okhttp() <= j2) {
                if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
                    TaskLoggerKt.log(task, this, "already scheduled");
                }
                return false;
            }
            this.futureTasks.remove(indexOf);
        }
        task.setNextExecuteNanoTime$okhttp(j2);
        if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
            if (z) {
                str = "run again after " + TaskLoggerKt.formatDuration(j2 - nanoTime);
            } else {
                str = "scheduled after " + TaskLoggerKt.formatDuration(j2 - nanoTime);
            }
            TaskLoggerKt.log(task, this, str);
        }
        Iterator<Task> it = this.futureTasks.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (it.next().getNextExecuteNanoTime$okhttp() - nanoTime > j) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            i = this.futureTasks.size();
        }
        this.futureTasks.add(i, task);
        if (i == 0) {
            return true;
        }
        return false;
    }

    public final boolean cancelAllAndDecide$okhttp() {
        Task task = this.activeTask;
        if (task != null) {
            Intrinsics.checkNotNull(task);
            if (task.getCancelable()) {
                this.cancelActiveTask = true;
            }
        }
        boolean z = false;
        for (int size = this.futureTasks.size() - 1; size >= 0; size--) {
            if (this.futureTasks.get(size).getCancelable()) {
                Task task2 = this.futureTasks.get(size);
                if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
                    TaskLoggerKt.log(task2, this, "canceled");
                }
                this.futureTasks.remove(size);
                z = true;
            }
        }
        return z;
    }

    public String toString() {
        return this.name;
    }

    public final void cancelAll() {
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            synchronized (this.taskRunner) {
                try {
                    if (cancelAllAndDecide$okhttp()) {
                        this.taskRunner.kickCoordinator$okhttp(this);
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Thread ");
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getName());
        sb.append(" MUST NOT hold lock on ");
        sb.append(this);
        throw new AssertionError(sb.toString());
    }

    public final void shutdown() {
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            synchronized (this.taskRunner) {
                try {
                    this.shutdown = true;
                    if (cancelAllAndDecide$okhttp()) {
                        this.taskRunner.kickCoordinator$okhttp(this);
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Thread ");
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getName());
        sb.append(" MUST NOT hold lock on ");
        sb.append(this);
        throw new AssertionError(sb.toString());
    }
}
