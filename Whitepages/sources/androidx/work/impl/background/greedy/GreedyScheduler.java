package androidx.work.impl.background.greedy;

import android.content.Context;
import android.text.TextUtils;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.WorkInfo$State;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Scheduler;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.StartStopTokens;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.constraints.WorkConstraintsTrackerImpl;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.ProcessUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GreedyScheduler implements Scheduler, WorkConstraintsCallback, ExecutionListener {
    private static final String TAG = Logger.tagWithPrefix("GreedyScheduler");
    private final Set mConstrainedWorkSpecs = new HashSet();
    private final Context mContext;
    private DelayedWorkTracker mDelayedWorkTracker;
    Boolean mInDefaultProcess;
    private final Object mLock;
    private boolean mRegisteredExecutionListener;
    private final StartStopTokens mStartStopTokens = new StartStopTokens();
    private final WorkConstraintsTracker mWorkConstraintsTracker;
    private final WorkManagerImpl mWorkManagerImpl;

    public boolean hasLimitedSchedulingSlots() {
        return false;
    }

    public GreedyScheduler(Context context, Configuration configuration, Trackers trackers, WorkManagerImpl workManagerImpl) {
        this.mContext = context;
        this.mWorkManagerImpl = workManagerImpl;
        this.mWorkConstraintsTracker = new WorkConstraintsTrackerImpl(trackers, (WorkConstraintsCallback) this);
        this.mDelayedWorkTracker = new DelayedWorkTracker(this, configuration.getRunnableScheduler());
        this.mLock = new Object();
    }

    public void schedule(WorkSpec... workSpecArr) {
        if (this.mInDefaultProcess == null) {
            checkDefaultProcess();
        }
        if (!this.mInDefaultProcess.booleanValue()) {
            Logger.get().info(TAG, "Ignoring schedule request in a secondary process");
            return;
        }
        registerExecutionListenerIfNeeded();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (WorkSpec workSpec : workSpecArr) {
            if (!this.mStartStopTokens.contains(WorkSpecKt.generationalId(workSpec))) {
                long calculateNextRunTime = workSpec.calculateNextRunTime();
                long currentTimeMillis = System.currentTimeMillis();
                if (workSpec.state == WorkInfo$State.ENQUEUED) {
                    if (currentTimeMillis < calculateNextRunTime) {
                        DelayedWorkTracker delayedWorkTracker = this.mDelayedWorkTracker;
                        if (delayedWorkTracker != null) {
                            delayedWorkTracker.schedule(workSpec);
                        }
                    } else if (workSpec.hasConstraints()) {
                        if (workSpec.constraints.requiresDeviceIdle()) {
                            Logger.get().debug(TAG, "Ignoring " + workSpec + ". Requires device idle.");
                        } else if (workSpec.constraints.hasContentUriTriggers()) {
                            Logger.get().debug(TAG, "Ignoring " + workSpec + ". Requires ContentUri triggers.");
                        } else {
                            hashSet.add(workSpec);
                            hashSet2.add(workSpec.id);
                        }
                    } else if (!this.mStartStopTokens.contains(WorkSpecKt.generationalId(workSpec))) {
                        Logger.get().debug(TAG, "Starting work for " + workSpec.id);
                        this.mWorkManagerImpl.startWork(this.mStartStopTokens.tokenFor(workSpec));
                    }
                }
            }
        }
        synchronized (this.mLock) {
            try {
                if (!hashSet.isEmpty()) {
                    String join = TextUtils.join(",", hashSet2);
                    Logger.get().debug(TAG, "Starting tracking for " + join);
                    this.mConstrainedWorkSpecs.addAll(hashSet);
                    this.mWorkConstraintsTracker.replace(this.mConstrainedWorkSpecs);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void checkDefaultProcess() {
        this.mInDefaultProcess = Boolean.valueOf(ProcessUtils.isDefaultProcess(this.mContext, this.mWorkManagerImpl.getConfiguration()));
    }

    public void cancel(String str) {
        if (this.mInDefaultProcess == null) {
            checkDefaultProcess();
        }
        if (!this.mInDefaultProcess.booleanValue()) {
            Logger.get().info(TAG, "Ignoring schedule request in non-main process");
            return;
        }
        registerExecutionListenerIfNeeded();
        Logger logger = Logger.get();
        String str2 = TAG;
        logger.debug(str2, "Cancelling work ID " + str);
        DelayedWorkTracker delayedWorkTracker = this.mDelayedWorkTracker;
        if (delayedWorkTracker != null) {
            delayedWorkTracker.unschedule(str);
        }
        for (StartStopToken stopWork : this.mStartStopTokens.remove(str)) {
            this.mWorkManagerImpl.stopWork(stopWork);
        }
    }

    public void onAllConstraintsMet(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WorkGenerationalId generationalId = WorkSpecKt.generationalId((WorkSpec) it.next());
            if (!this.mStartStopTokens.contains(generationalId)) {
                Logger logger = Logger.get();
                String str = TAG;
                logger.debug(str, "Constraints met: Scheduling work ID " + generationalId);
                this.mWorkManagerImpl.startWork(this.mStartStopTokens.tokenFor(generationalId));
            }
        }
    }

    public void onAllConstraintsNotMet(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WorkGenerationalId generationalId = WorkSpecKt.generationalId((WorkSpec) it.next());
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "Constraints not met: Cancelling work ID " + generationalId);
            StartStopToken remove = this.mStartStopTokens.remove(generationalId);
            if (remove != null) {
                this.mWorkManagerImpl.stopWork(remove);
            }
        }
    }

    public void onExecuted(WorkGenerationalId workGenerationalId, boolean z) {
        this.mStartStopTokens.remove(workGenerationalId);
        removeConstraintTrackingFor(workGenerationalId);
    }

    private void removeConstraintTrackingFor(WorkGenerationalId workGenerationalId) {
        synchronized (this.mLock) {
            try {
                Iterator it = this.mConstrainedWorkSpecs.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    WorkSpec workSpec = (WorkSpec) it.next();
                    if (WorkSpecKt.generationalId(workSpec).equals(workGenerationalId)) {
                        Logger logger = Logger.get();
                        String str = TAG;
                        logger.debug(str, "Stopping tracking for " + workGenerationalId);
                        this.mConstrainedWorkSpecs.remove(workSpec);
                        this.mWorkConstraintsTracker.replace(this.mConstrainedWorkSpecs);
                        break;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void registerExecutionListenerIfNeeded() {
        if (!this.mRegisteredExecutionListener) {
            this.mWorkManagerImpl.getProcessor().addExecutionListener(this);
            this.mRegisteredExecutionListener = true;
        }
    }
}
