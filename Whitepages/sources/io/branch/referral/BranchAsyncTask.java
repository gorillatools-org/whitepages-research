package io.branch.referral;

import android.os.AsyncTask;

public abstract class BranchAsyncTask extends AsyncTask {
    public final AsyncTask executeTask(Object... objArr) {
        try {
            return executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, objArr);
        } catch (Exception e) {
            BranchLogger.w("Caught Exception in AsyncTask: " + e.getMessage());
            return execute(objArr);
        }
    }
}
