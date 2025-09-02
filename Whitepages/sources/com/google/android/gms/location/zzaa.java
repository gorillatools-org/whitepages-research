package com.google.android.gms.location;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzaa implements Continuation {
    private final TaskCompletionSource zza;

    zzaa(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final Object then(Task task) {
        TaskCompletionSource taskCompletionSource = this.zza;
        if (!task.isSuccessful()) {
            if (task.getException() != null) {
                Exception exception = task.getException();
                if (exception != null) {
                    taskCompletionSource.setException(exception);
                }
            } else {
                taskCompletionSource.trySetResult(null);
            }
        }
        return taskCompletionSource.getTask();
    }
}
