package androidx.work.impl.utils;

import androidx.work.Logger;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.WorkManagerImpl;

public class StopWorkRunnable implements Runnable {
    private static final String TAG = Logger.tagWithPrefix("StopWorkRunnable");
    private final boolean mStopInForeground;
    private final StartStopToken mToken;
    private final WorkManagerImpl mWorkManagerImpl;

    public StopWorkRunnable(WorkManagerImpl workManagerImpl, StartStopToken startStopToken, boolean z) {
        this.mWorkManagerImpl = workManagerImpl;
        this.mToken = startStopToken;
        this.mStopInForeground = z;
    }

    public void run() {
        boolean z;
        if (this.mStopInForeground) {
            z = this.mWorkManagerImpl.getProcessor().stopForegroundWork(this.mToken);
        } else {
            z = this.mWorkManagerImpl.getProcessor().stopWork(this.mToken);
        }
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "StopWorkRunnable for " + this.mToken.getId().getWorkSpecId() + "; Processor.stopWork = " + z);
    }
}
