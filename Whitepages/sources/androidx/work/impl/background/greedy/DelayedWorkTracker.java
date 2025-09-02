package androidx.work.impl.background.greedy;

import androidx.work.Logger;
import androidx.work.RunnableScheduler;
import androidx.work.impl.model.WorkSpec;
import java.util.HashMap;
import java.util.Map;

public class DelayedWorkTracker {
    static final String TAG = Logger.tagWithPrefix("DelayedWorkTracker");
    final GreedyScheduler mGreedyScheduler;
    private final RunnableScheduler mRunnableScheduler;
    private final Map mRunnables = new HashMap();

    public DelayedWorkTracker(GreedyScheduler greedyScheduler, RunnableScheduler runnableScheduler) {
        this.mGreedyScheduler = greedyScheduler;
        this.mRunnableScheduler = runnableScheduler;
    }

    public void schedule(final WorkSpec workSpec) {
        Runnable runnable = (Runnable) this.mRunnables.remove(workSpec.id);
        if (runnable != null) {
            this.mRunnableScheduler.cancel(runnable);
        }
        AnonymousClass1 r0 = new Runnable() {
            public void run() {
                Logger logger = Logger.get();
                String str = DelayedWorkTracker.TAG;
                logger.debug(str, "Scheduling work " + workSpec.id);
                DelayedWorkTracker.this.mGreedyScheduler.schedule(workSpec);
            }
        };
        this.mRunnables.put(workSpec.id, r0);
        long currentTimeMillis = System.currentTimeMillis();
        this.mRunnableScheduler.scheduleWithDelay(workSpec.calculateNextRunTime() - currentTimeMillis, r0);
    }

    public void unschedule(String str) {
        Runnable runnable = (Runnable) this.mRunnables.remove(str);
        if (runnable != null) {
            this.mRunnableScheduler.cancel(runnable);
        }
    }
}
