package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

public final class TaskExecutors {
    public static final Executor MAIN_THREAD = new zzu();
    static final Executor zza = new zzt();

    private TaskExecutors() {
    }
}
