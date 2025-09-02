package kotlinx.coroutines.scheduling;

final class TaskContextImpl implements TaskContext {
    private final int taskMode;

    public void afterTask() {
    }

    public TaskContextImpl(int i) {
        this.taskMode = i;
    }

    public int getTaskMode() {
        return this.taskMode;
    }
}
