package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.os.PowerManager;
import androidx.work.Logger;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTrackerImpl;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.WorkTimer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

public class DelayMetCommandHandler implements WorkConstraintsCallback, WorkTimer.TimeLimitExceededListener {
    private static final String TAG = Logger.tagWithPrefix("DelayMetCommandHandler");
    private final Context mContext;
    private int mCurrentState = 0;
    private final SystemAlarmDispatcher mDispatcher;
    private boolean mHasConstraints = false;
    private final Object mLock = new Object();
    private final Executor mMainThreadExecutor;
    private final Executor mSerialExecutor;
    private final int mStartId;
    private final StartStopToken mToken;
    private PowerManager.WakeLock mWakeLock;
    private final WorkConstraintsTrackerImpl mWorkConstraintsTracker;
    private final WorkGenerationalId mWorkGenerationalId;

    DelayMetCommandHandler(Context context, int i, SystemAlarmDispatcher systemAlarmDispatcher, StartStopToken startStopToken) {
        this.mContext = context;
        this.mStartId = i;
        this.mDispatcher = systemAlarmDispatcher;
        this.mWorkGenerationalId = startStopToken.getId();
        this.mToken = startStopToken;
        Trackers trackers = systemAlarmDispatcher.getWorkManager().getTrackers();
        this.mSerialExecutor = systemAlarmDispatcher.getTaskExecutor().getSerialTaskExecutor();
        this.mMainThreadExecutor = systemAlarmDispatcher.getTaskExecutor().getMainThreadExecutor();
        this.mWorkConstraintsTracker = new WorkConstraintsTrackerImpl(trackers, (WorkConstraintsCallback) this);
    }

    public void onAllConstraintsMet(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (WorkSpecKt.generationalId((WorkSpec) it.next()).equals(this.mWorkGenerationalId)) {
                this.mSerialExecutor.execute(new DelayMetCommandHandler$$ExternalSyntheticLambda1(this));
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void startWork() {
        if (this.mCurrentState == 0) {
            this.mCurrentState = 1;
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "onAllConstraintsMet for " + this.mWorkGenerationalId);
            if (this.mDispatcher.getProcessor().startWork(this.mToken)) {
                this.mDispatcher.getWorkTimer().startTimer(this.mWorkGenerationalId, 600000, this);
            } else {
                cleanUp();
            }
        } else {
            Logger logger2 = Logger.get();
            String str2 = TAG;
            logger2.debug(str2, "Already started work for " + this.mWorkGenerationalId);
        }
    }

    /* access modifiers changed from: package-private */
    public void onExecuted(boolean z) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "onExecuted " + this.mWorkGenerationalId + ", " + z);
        cleanUp();
        if (z) {
            this.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, CommandHandler.createScheduleWorkIntent(this.mContext, this.mWorkGenerationalId), this.mStartId));
        }
        if (this.mHasConstraints) {
            this.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, CommandHandler.createConstraintsChangedIntent(this.mContext), this.mStartId));
        }
    }

    public void onTimeLimitExceeded(WorkGenerationalId workGenerationalId) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Exceeded time limits on execution for " + workGenerationalId);
        this.mSerialExecutor.execute(new DelayMetCommandHandler$$ExternalSyntheticLambda0(this));
    }

    public void onAllConstraintsNotMet(List list) {
        this.mSerialExecutor.execute(new DelayMetCommandHandler$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    public void handleProcessWork() {
        String workSpecId = this.mWorkGenerationalId.getWorkSpecId();
        Context context = this.mContext;
        this.mWakeLock = WakeLocks.newWakeLock(context, workSpecId + " (" + this.mStartId + ")");
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Acquiring wakelock " + this.mWakeLock + "for WorkSpec " + workSpecId);
        this.mWakeLock.acquire();
        WorkSpec workSpec = this.mDispatcher.getWorkManager().getWorkDatabase().workSpecDao().getWorkSpec(workSpecId);
        if (workSpec == null) {
            this.mSerialExecutor.execute(new DelayMetCommandHandler$$ExternalSyntheticLambda0(this));
            return;
        }
        boolean hasConstraints = workSpec.hasConstraints();
        this.mHasConstraints = hasConstraints;
        if (!hasConstraints) {
            Logger logger2 = Logger.get();
            logger2.debug(str, "No constraints for " + workSpecId);
            onAllConstraintsMet(Collections.singletonList(workSpec));
            return;
        }
        this.mWorkConstraintsTracker.replace(Collections.singletonList(workSpec));
    }

    /* access modifiers changed from: private */
    public void stopWork() {
        String workSpecId = this.mWorkGenerationalId.getWorkSpecId();
        if (this.mCurrentState < 2) {
            this.mCurrentState = 2;
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "Stopping work for WorkSpec " + workSpecId);
            this.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, CommandHandler.createStopWorkIntent(this.mContext, this.mWorkGenerationalId), this.mStartId));
            if (this.mDispatcher.getProcessor().isEnqueued(this.mWorkGenerationalId.getWorkSpecId())) {
                Logger logger2 = Logger.get();
                logger2.debug(str, "WorkSpec " + workSpecId + " needs to be rescheduled");
                this.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, CommandHandler.createScheduleWorkIntent(this.mContext, this.mWorkGenerationalId), this.mStartId));
                return;
            }
            Logger logger3 = Logger.get();
            logger3.debug(str, "Processor does not have WorkSpec " + workSpecId + ". No need to reschedule");
            return;
        }
        Logger logger4 = Logger.get();
        String str2 = TAG;
        logger4.debug(str2, "Already stopped work for " + workSpecId);
    }

    private void cleanUp() {
        synchronized (this.mLock) {
            try {
                this.mWorkConstraintsTracker.reset();
                this.mDispatcher.getWorkTimer().stopTimer(this.mWorkGenerationalId);
                PowerManager.WakeLock wakeLock = this.mWakeLock;
                if (wakeLock != null && wakeLock.isHeld()) {
                    Logger logger = Logger.get();
                    String str = TAG;
                    logger.debug(str, "Releasing wakelock " + this.mWakeLock + "for WorkSpec " + this.mWorkGenerationalId);
                    this.mWakeLock.release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
