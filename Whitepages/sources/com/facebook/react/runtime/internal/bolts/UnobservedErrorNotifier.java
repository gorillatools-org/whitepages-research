package com.facebook.react.runtime.internal.bolts;

import com.facebook.react.runtime.internal.bolts.Task;

public final class UnobservedErrorNotifier {
    private Task<?> task;

    public UnobservedErrorNotifier(Task<?> task2) {
        this.task = task2;
    }

    /* access modifiers changed from: protected */
    public final void finalize() {
        Task.UnobservedExceptionHandler unobservedExceptionHandler;
        Task<?> task2 = this.task;
        if (task2 != null && (unobservedExceptionHandler = Task.getUnobservedExceptionHandler()) != null) {
            unobservedExceptionHandler.unobservedException(task2, new UnobservedTaskException(task2.getError()));
        }
    }

    public final void setObserved() {
        this.task = null;
    }
}
