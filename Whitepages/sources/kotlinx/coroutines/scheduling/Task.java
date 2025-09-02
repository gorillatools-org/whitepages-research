package kotlinx.coroutines.scheduling;

public abstract class Task implements Runnable {
    public long submissionTime;
    public TaskContext taskContext;

    public Task(long j, TaskContext taskContext2) {
        this.submissionTime = j;
        this.taskContext = taskContext2;
    }

    public Task() {
        this(0, TasksKt.NonBlockingContext);
    }
}
