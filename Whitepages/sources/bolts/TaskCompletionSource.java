package bolts;

public class TaskCompletionSource {
    private final Task task = new Task();

    public Task getTask() {
        return this.task;
    }

    public boolean trySetCancelled() {
        return this.task.trySetCancelled();
    }

    public boolean trySetResult(Object obj) {
        return this.task.trySetResult(obj);
    }

    public boolean trySetError(Exception exc) {
        return this.task.trySetError(exc);
    }

    public void setCancelled() {
        if (!trySetCancelled()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    public void setResult(Object obj) {
        if (!trySetResult(obj)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }

    public void setError(Exception exc) {
        if (!trySetError(exc)) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }
}
