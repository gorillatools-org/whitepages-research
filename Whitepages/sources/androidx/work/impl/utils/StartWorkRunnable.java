package androidx.work.impl.utils;

import androidx.work.WorkerParameters;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.WorkManagerImpl;

public class StartWorkRunnable implements Runnable {
    private WorkerParameters.RuntimeExtras mRuntimeExtras;
    private WorkManagerImpl mWorkManagerImpl;
    private StartStopToken mWorkSpecId;

    public StartWorkRunnable(WorkManagerImpl workManagerImpl, StartStopToken startStopToken, WorkerParameters.RuntimeExtras runtimeExtras) {
        this.mWorkManagerImpl = workManagerImpl;
        this.mWorkSpecId = startStopToken;
        this.mRuntimeExtras = runtimeExtras;
    }

    public void run() {
        this.mWorkManagerImpl.getProcessor().startWork(this.mWorkSpecId, this.mRuntimeExtras);
    }
}
