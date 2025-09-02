package androidx.work.impl.utils;

import androidx.work.Logger;
import androidx.work.RunnableScheduler;
import androidx.work.impl.model.WorkGenerationalId;
import java.util.HashMap;
import java.util.Map;

public class WorkTimer {
    private static final String TAG = Logger.tagWithPrefix("WorkTimer");
    final Map mListeners = new HashMap();
    final Object mLock = new Object();
    final RunnableScheduler mRunnableScheduler;
    final Map mTimerMap = new HashMap();

    public interface TimeLimitExceededListener {
        void onTimeLimitExceeded(WorkGenerationalId workGenerationalId);
    }

    public WorkTimer(RunnableScheduler runnableScheduler) {
        this.mRunnableScheduler = runnableScheduler;
    }

    public void startTimer(WorkGenerationalId workGenerationalId, long j, TimeLimitExceededListener timeLimitExceededListener) {
        synchronized (this.mLock) {
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "Starting timer for " + workGenerationalId);
            stopTimer(workGenerationalId);
            WorkTimerRunnable workTimerRunnable = new WorkTimerRunnable(this, workGenerationalId);
            this.mTimerMap.put(workGenerationalId, workTimerRunnable);
            this.mListeners.put(workGenerationalId, timeLimitExceededListener);
            this.mRunnableScheduler.scheduleWithDelay(j, workTimerRunnable);
        }
    }

    public void stopTimer(WorkGenerationalId workGenerationalId) {
        synchronized (this.mLock) {
            try {
                if (((WorkTimerRunnable) this.mTimerMap.remove(workGenerationalId)) != null) {
                    Logger logger = Logger.get();
                    String str = TAG;
                    logger.debug(str, "Stopping timer for " + workGenerationalId);
                    this.mListeners.remove(workGenerationalId);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static class WorkTimerRunnable implements Runnable {
        private final WorkGenerationalId mWorkGenerationalId;
        private final WorkTimer mWorkTimer;

        WorkTimerRunnable(WorkTimer workTimer, WorkGenerationalId workGenerationalId) {
            this.mWorkTimer = workTimer;
            this.mWorkGenerationalId = workGenerationalId;
        }

        public void run() {
            synchronized (this.mWorkTimer.mLock) {
                try {
                    if (((WorkTimerRunnable) this.mWorkTimer.mTimerMap.remove(this.mWorkGenerationalId)) != null) {
                        TimeLimitExceededListener timeLimitExceededListener = (TimeLimitExceededListener) this.mWorkTimer.mListeners.remove(this.mWorkGenerationalId);
                        if (timeLimitExceededListener != null) {
                            timeLimitExceededListener.onTimeLimitExceeded(this.mWorkGenerationalId);
                        }
                    } else {
                        Logger.get().debug("WrkTimerRunnable", String.format("Timer with %s is already marked as complete.", new Object[]{this.mWorkGenerationalId}));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
