package androidx.work.impl.utils;

import androidx.work.Logger;
import androidx.work.ProgressUpdater;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

public class WorkProgressUpdater implements ProgressUpdater {
    static final String TAG = Logger.tagWithPrefix("WorkProgressUpdater");
    final TaskExecutor mTaskExecutor;
    final WorkDatabase mWorkDatabase;

    public WorkProgressUpdater(WorkDatabase workDatabase, TaskExecutor taskExecutor) {
        this.mWorkDatabase = workDatabase;
        this.mTaskExecutor = taskExecutor;
    }
}
