package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.instrumentation.FrescoInstrumenter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JobScheduler {
    private final Runnable mDoJobRunnable = new Runnable() {
        public void run() {
            JobScheduler.this.doJob();
        }
    };
    EncodedImage mEncodedImage = null;
    private final Executor mExecutor;
    private final JobRunnable mJobRunnable;
    long mJobStartTime = 0;
    JobState mJobState = JobState.IDLE;
    long mJobSubmitTime = 0;
    private final int mMinimumJobIntervalMs;
    int mStatus = 0;
    private final Runnable mSubmitJobRunnable = new Runnable() {
        public void run() {
            JobScheduler.this.submitJob();
        }
    };

    public interface JobRunnable {
        void run(EncodedImage encodedImage, int i);
    }

    enum JobState {
        IDLE,
        QUEUED,
        RUNNING,
        RUNNING_AND_PENDING
    }

    static class JobStartExecutorSupplier {
        private static ScheduledExecutorService sJobStarterExecutor;

        static ScheduledExecutorService get() {
            if (sJobStarterExecutor == null) {
                sJobStarterExecutor = Executors.newSingleThreadScheduledExecutor();
            }
            return sJobStarterExecutor;
        }
    }

    public JobScheduler(Executor executor, JobRunnable jobRunnable, int i) {
        this.mExecutor = executor;
        this.mJobRunnable = jobRunnable;
        this.mMinimumJobIntervalMs = i;
    }

    public void clearJob() {
        EncodedImage encodedImage;
        synchronized (this) {
            encodedImage = this.mEncodedImage;
            this.mEncodedImage = null;
            this.mStatus = 0;
        }
        EncodedImage.closeSafely(encodedImage);
    }

    public boolean updateJob(EncodedImage encodedImage, int i) {
        EncodedImage encodedImage2;
        if (!shouldProcess(encodedImage, i)) {
            return false;
        }
        synchronized (this) {
            encodedImage2 = this.mEncodedImage;
            this.mEncodedImage = EncodedImage.cloneOrNull(encodedImage);
            this.mStatus = i;
        }
        EncodedImage.closeSafely(encodedImage2);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003e, code lost:
        if (r3 == false) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0040, code lost:
        enqueueJob(r5 - r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0044, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean scheduleJob() {
        /*
            r7 = this;
            long r0 = android.os.SystemClock.uptimeMillis()
            monitor-enter(r7)
            com.facebook.imagepipeline.image.EncodedImage r2 = r7.mEncodedImage     // Catch:{ all -> 0x0012 }
            int r3 = r7.mStatus     // Catch:{ all -> 0x0012 }
            boolean r2 = shouldProcess(r2, r3)     // Catch:{ all -> 0x0012 }
            r3 = 0
            if (r2 != 0) goto L_0x0014
            monitor-exit(r7)     // Catch:{ all -> 0x0012 }
            return r3
        L_0x0012:
            r0 = move-exception
            goto L_0x0045
        L_0x0014:
            int[] r2 = com.facebook.imagepipeline.producers.JobScheduler.AnonymousClass3.$SwitchMap$com$facebook$imagepipeline$producers$JobScheduler$JobState     // Catch:{ all -> 0x0012 }
            com.facebook.imagepipeline.producers.JobScheduler$JobState r4 = r7.mJobState     // Catch:{ all -> 0x0012 }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x0012 }
            r2 = r2[r4]     // Catch:{ all -> 0x0012 }
            r4 = 1
            if (r2 == r4) goto L_0x002c
            r5 = 3
            if (r2 == r5) goto L_0x0025
            goto L_0x0029
        L_0x0025:
            com.facebook.imagepipeline.producers.JobScheduler$JobState r2 = com.facebook.imagepipeline.producers.JobScheduler.JobState.RUNNING_AND_PENDING     // Catch:{ all -> 0x0012 }
            r7.mJobState = r2     // Catch:{ all -> 0x0012 }
        L_0x0029:
            r5 = 0
            goto L_0x003d
        L_0x002c:
            long r2 = r7.mJobStartTime     // Catch:{ all -> 0x0012 }
            int r5 = r7.mMinimumJobIntervalMs     // Catch:{ all -> 0x0012 }
            long r5 = (long) r5     // Catch:{ all -> 0x0012 }
            long r2 = r2 + r5
            long r5 = java.lang.Math.max(r2, r0)     // Catch:{ all -> 0x0012 }
            r7.mJobSubmitTime = r0     // Catch:{ all -> 0x0012 }
            com.facebook.imagepipeline.producers.JobScheduler$JobState r2 = com.facebook.imagepipeline.producers.JobScheduler.JobState.QUEUED     // Catch:{ all -> 0x0012 }
            r7.mJobState = r2     // Catch:{ all -> 0x0012 }
            r3 = r4
        L_0x003d:
            monitor-exit(r7)     // Catch:{ all -> 0x0012 }
            if (r3 == 0) goto L_0x0044
            long r5 = r5 - r0
            r7.enqueueJob(r5)
        L_0x0044:
            return r4
        L_0x0045:
            monitor-exit(r7)     // Catch:{ all -> 0x0012 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.JobScheduler.scheduleJob():boolean");
    }

    /* renamed from: com.facebook.imagepipeline.producers.JobScheduler$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$imagepipeline$producers$JobScheduler$JobState;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.facebook.imagepipeline.producers.JobScheduler$JobState[] r0 = com.facebook.imagepipeline.producers.JobScheduler.JobState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$imagepipeline$producers$JobScheduler$JobState = r0
                com.facebook.imagepipeline.producers.JobScheduler$JobState r1 = com.facebook.imagepipeline.producers.JobScheduler.JobState.IDLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$imagepipeline$producers$JobScheduler$JobState     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.imagepipeline.producers.JobScheduler$JobState r1 = com.facebook.imagepipeline.producers.JobScheduler.JobState.QUEUED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$imagepipeline$producers$JobScheduler$JobState     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.imagepipeline.producers.JobScheduler$JobState r1 = com.facebook.imagepipeline.producers.JobScheduler.JobState.RUNNING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$facebook$imagepipeline$producers$JobScheduler$JobState     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.facebook.imagepipeline.producers.JobScheduler$JobState r1 = com.facebook.imagepipeline.producers.JobScheduler.JobState.RUNNING_AND_PENDING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.JobScheduler.AnonymousClass3.<clinit>():void");
        }
    }

    private void enqueueJob(long j) {
        Runnable decorateRunnable = FrescoInstrumenter.decorateRunnable(this.mSubmitJobRunnable, "JobScheduler_enqueueJob");
        if (j > 0) {
            JobStartExecutorSupplier.get().schedule(decorateRunnable, j, TimeUnit.MILLISECONDS);
        } else {
            decorateRunnable.run();
        }
    }

    /* access modifiers changed from: private */
    public void submitJob() {
        this.mExecutor.execute(FrescoInstrumenter.decorateRunnable(this.mDoJobRunnable, "JobScheduler_submitJob"));
    }

    /* access modifiers changed from: private */
    public void doJob() {
        EncodedImage encodedImage;
        int i;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (this) {
            encodedImage = this.mEncodedImage;
            i = this.mStatus;
            this.mEncodedImage = null;
            this.mStatus = 0;
            this.mJobState = JobState.RUNNING;
            this.mJobStartTime = uptimeMillis;
        }
        try {
            if (shouldProcess(encodedImage, i)) {
                this.mJobRunnable.run(encodedImage, i);
            }
        } finally {
            EncodedImage.closeSafely(encodedImage);
            onJobFinished();
        }
    }

    private void onJobFinished() {
        boolean z;
        long j;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (this) {
            try {
                if (this.mJobState == JobState.RUNNING_AND_PENDING) {
                    j = Math.max(this.mJobStartTime + ((long) this.mMinimumJobIntervalMs), uptimeMillis);
                    this.mJobSubmitTime = uptimeMillis;
                    this.mJobState = JobState.QUEUED;
                    z = true;
                } else {
                    this.mJobState = JobState.IDLE;
                    j = 0;
                    z = false;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (z) {
            enqueueJob(j - uptimeMillis);
        }
    }

    private static boolean shouldProcess(EncodedImage encodedImage, int i) {
        return BaseConsumer.isLast(i) || BaseConsumer.statusHasFlag(i, 4) || EncodedImage.isValid(encodedImage);
    }

    public synchronized long getQueuedTime() {
        return this.mJobStartTime - this.mJobSubmitTime;
    }
}
