package com.facebook.react.runtime.internal.bolts;

public final class TaskCompletionSource<TResult> {
    private final Task<TResult> task = new Task<>();

    public final Task<TResult> getTask() {
        return this.task;
    }

    public final boolean trySetCancelled() {
        return this.task.trySetCancelled();
    }

    public final boolean trySetResult(TResult tresult) {
        return this.task.trySetResult(tresult);
    }

    public final boolean trySetError(Exception exc) {
        return this.task.trySetError(exc);
    }

    public final void setCancelled() {
        if (!trySetCancelled()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    public final void setResult(TResult tresult) {
        if (!trySetResult(tresult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }

    public final void setError(Exception exc) {
        if (!trySetError(exc)) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }
}
