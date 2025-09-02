package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

@KeepForSdk
public class TaskUtil {
    @KeepForSdk
    public static void setResultOrApiException(Status status, TaskCompletionSource<Void> taskCompletionSource) {
        setResultOrApiException(status, (Object) null, taskCompletionSource);
    }

    @KeepForSdk
    @Deprecated
    public static Task<Void> toVoidTaskThatFailsOnFalse(Task<Boolean> task) {
        return task.continueWith(new zacx());
    }

    @ResultIgnorabilityUnspecified
    @KeepForSdk
    public static <ResultT> boolean trySetResultOrApiException(Status status, ResultT resultt, TaskCompletionSource<ResultT> taskCompletionSource) {
        if (status.isSuccess()) {
            return taskCompletionSource.trySetResult(resultt);
        }
        return taskCompletionSource.trySetException(ApiExceptionUtil.fromStatus(status));
    }

    @KeepForSdk
    public static <ResultT> void setResultOrApiException(Status status, ResultT resultt, TaskCompletionSource<ResultT> taskCompletionSource) {
        if (status.isSuccess()) {
            taskCompletionSource.setResult(resultt);
        } else {
            taskCompletionSource.setException(ApiExceptionUtil.fromStatus(status));
        }
    }
}
