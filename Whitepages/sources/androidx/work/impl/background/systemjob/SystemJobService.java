package androidx.work.impl.background.systemjob;

import android.app.Application;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.net.Network;
import android.net.Uri;
import android.os.PersistableBundle;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.StartStopTokens;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkGenerationalId;
import java.util.HashMap;
import java.util.Map;

public class SystemJobService extends JobService implements ExecutionListener {
    private static final String TAG = Logger.tagWithPrefix("SystemJobService");
    private final Map mJobParameters = new HashMap();
    private final StartStopTokens mStartStopTokens = new StartStopTokens();
    private WorkManagerImpl mWorkManagerImpl;

    public void onCreate() {
        super.onCreate();
        try {
            WorkManagerImpl instance = WorkManagerImpl.getInstance(getApplicationContext());
            this.mWorkManagerImpl = instance;
            instance.getProcessor().addExecutionListener(this);
        } catch (IllegalStateException unused) {
            if (Application.class.equals(getApplication().getClass())) {
                Logger.get().warning(TAG, "Could not find WorkManager instance; this may be because an auto-backup is in progress. Ignoring JobScheduler commands for now. Please make sure that you are initializing WorkManager if you have manually disabled WorkManagerInitializer.");
                return;
            }
            throw new IllegalStateException("WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().");
        }
    }

    public void onDestroy() {
        super.onDestroy();
        WorkManagerImpl workManagerImpl = this.mWorkManagerImpl;
        if (workManagerImpl != null) {
            workManagerImpl.getProcessor().removeExecutionListener(this);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0070, code lost:
        r2 = android.os.Build.VERSION.SDK_INT;
        r3 = new androidx.work.WorkerParameters.RuntimeExtras();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007b, code lost:
        if (androidx.work.impl.background.systemjob.SystemJobService.Api24Impl.getTriggeredContentUris(r8) == null) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007d, code lost:
        r3.triggeredContentUris = java.util.Arrays.asList(androidx.work.impl.background.systemjob.SystemJobService.Api24Impl.getTriggeredContentUris(r8));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x008b, code lost:
        if (androidx.work.impl.background.systemjob.SystemJobService.Api24Impl.getTriggeredContentAuthorities(r8) == null) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x008d, code lost:
        r3.triggeredContentAuthorities = java.util.Arrays.asList(androidx.work.impl.background.systemjob.SystemJobService.Api24Impl.getTriggeredContentAuthorities(r8));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0099, code lost:
        if (r2 < 28) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009b, code lost:
        r3.network = androidx.work.impl.background.systemjob.SystemJobService.Api28Impl.getNetwork(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a1, code lost:
        r7.mWorkManagerImpl.startWork(r7.mStartStopTokens.tokenFor(r0), r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ac, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onStartJob(android.app.job.JobParameters r8) {
        /*
            r7 = this;
            androidx.work.impl.WorkManagerImpl r0 = r7.mWorkManagerImpl
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0015
            androidx.work.Logger r0 = androidx.work.Logger.get()
            java.lang.String r3 = TAG
            java.lang.String r4 = "WorkManager is not initialized; requesting retry."
            r0.debug(r3, r4)
            r7.jobFinished(r8, r1)
            return r2
        L_0x0015:
            androidx.work.impl.model.WorkGenerationalId r0 = workGenerationalIdFromJobParameters(r8)
            if (r0 != 0) goto L_0x0027
            androidx.work.Logger r8 = androidx.work.Logger.get()
            java.lang.String r0 = TAG
            java.lang.String r1 = "WorkSpec id not found!"
            r8.error(r0, r1)
            return r2
        L_0x0027:
            java.util.Map r3 = r7.mJobParameters
            monitor-enter(r3)
            java.util.Map r4 = r7.mJobParameters     // Catch:{ all -> 0x004e }
            boolean r4 = r4.containsKey(r0)     // Catch:{ all -> 0x004e }
            if (r4 == 0) goto L_0x0050
            androidx.work.Logger r8 = androidx.work.Logger.get()     // Catch:{ all -> 0x004e }
            java.lang.String r1 = TAG     // Catch:{ all -> 0x004e }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x004e }
            r4.<init>()     // Catch:{ all -> 0x004e }
            java.lang.String r5 = "Job is already being executed by SystemJobService: "
            r4.append(r5)     // Catch:{ all -> 0x004e }
            r4.append(r0)     // Catch:{ all -> 0x004e }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x004e }
            r8.debug(r1, r0)     // Catch:{ all -> 0x004e }
            monitor-exit(r3)     // Catch:{ all -> 0x004e }
            return r2
        L_0x004e:
            r8 = move-exception
            goto L_0x00ad
        L_0x0050:
            androidx.work.Logger r2 = androidx.work.Logger.get()     // Catch:{ all -> 0x004e }
            java.lang.String r4 = TAG     // Catch:{ all -> 0x004e }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x004e }
            r5.<init>()     // Catch:{ all -> 0x004e }
            java.lang.String r6 = "onStartJob for "
            r5.append(r6)     // Catch:{ all -> 0x004e }
            r5.append(r0)     // Catch:{ all -> 0x004e }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x004e }
            r2.debug(r4, r5)     // Catch:{ all -> 0x004e }
            java.util.Map r2 = r7.mJobParameters     // Catch:{ all -> 0x004e }
            r2.put(r0, r8)     // Catch:{ all -> 0x004e }
            monitor-exit(r3)     // Catch:{ all -> 0x004e }
            int r2 = android.os.Build.VERSION.SDK_INT
            androidx.work.WorkerParameters$RuntimeExtras r3 = new androidx.work.WorkerParameters$RuntimeExtras
            r3.<init>()
            android.net.Uri[] r4 = androidx.work.impl.background.systemjob.SystemJobService.Api24Impl.getTriggeredContentUris(r8)
            if (r4 == 0) goto L_0x0087
            android.net.Uri[] r4 = androidx.work.impl.background.systemjob.SystemJobService.Api24Impl.getTriggeredContentUris(r8)
            java.util.List r4 = java.util.Arrays.asList(r4)
            r3.triggeredContentUris = r4
        L_0x0087:
            java.lang.String[] r4 = androidx.work.impl.background.systemjob.SystemJobService.Api24Impl.getTriggeredContentAuthorities(r8)
            if (r4 == 0) goto L_0x0097
            java.lang.String[] r4 = androidx.work.impl.background.systemjob.SystemJobService.Api24Impl.getTriggeredContentAuthorities(r8)
            java.util.List r4 = java.util.Arrays.asList(r4)
            r3.triggeredContentAuthorities = r4
        L_0x0097:
            r4 = 28
            if (r2 < r4) goto L_0x00a1
            android.net.Network r8 = androidx.work.impl.background.systemjob.SystemJobService.Api28Impl.getNetwork(r8)
            r3.network = r8
        L_0x00a1:
            androidx.work.impl.WorkManagerImpl r8 = r7.mWorkManagerImpl
            androidx.work.impl.StartStopTokens r2 = r7.mStartStopTokens
            androidx.work.impl.StartStopToken r0 = r2.tokenFor((androidx.work.impl.model.WorkGenerationalId) r0)
            r8.startWork(r0, r3)
            return r1
        L_0x00ad:
            monitor-exit(r3)     // Catch:{ all -> 0x004e }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.background.systemjob.SystemJobService.onStartJob(android.app.job.JobParameters):boolean");
    }

    public boolean onStopJob(JobParameters jobParameters) {
        if (this.mWorkManagerImpl == null) {
            Logger.get().debug(TAG, "WorkManager is not initialized; requesting retry.");
            return true;
        }
        WorkGenerationalId workGenerationalIdFromJobParameters = workGenerationalIdFromJobParameters(jobParameters);
        if (workGenerationalIdFromJobParameters == null) {
            Logger.get().error(TAG, "WorkSpec id not found!");
            return false;
        }
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "onStopJob for " + workGenerationalIdFromJobParameters);
        synchronized (this.mJobParameters) {
            this.mJobParameters.remove(workGenerationalIdFromJobParameters);
        }
        StartStopToken remove = this.mStartStopTokens.remove(workGenerationalIdFromJobParameters);
        if (remove != null) {
            this.mWorkManagerImpl.stopWork(remove);
        }
        return !this.mWorkManagerImpl.getProcessor().isCancelled(workGenerationalIdFromJobParameters.getWorkSpecId());
    }

    public void onExecuted(WorkGenerationalId workGenerationalId, boolean z) {
        JobParameters jobParameters;
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, workGenerationalId.getWorkSpecId() + " executed on JobScheduler");
        synchronized (this.mJobParameters) {
            jobParameters = (JobParameters) this.mJobParameters.remove(workGenerationalId);
        }
        this.mStartStopTokens.remove(workGenerationalId);
        if (jobParameters != null) {
            jobFinished(jobParameters, z);
        }
    }

    private static WorkGenerationalId workGenerationalIdFromJobParameters(JobParameters jobParameters) {
        try {
            PersistableBundle extras = jobParameters.getExtras();
            if (extras == null || !extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                return null;
            }
            return new WorkGenerationalId(extras.getString("EXTRA_WORK_SPEC_ID"), extras.getInt("EXTRA_WORK_SPEC_GENERATION"));
        } catch (NullPointerException unused) {
            return null;
        }
    }

    static class Api24Impl {
        static Uri[] getTriggeredContentUris(JobParameters jobParameters) {
            return jobParameters.getTriggeredContentUris();
        }

        static String[] getTriggeredContentAuthorities(JobParameters jobParameters) {
            return jobParameters.getTriggeredContentAuthorities();
        }
    }

    static class Api28Impl {
        static Network getNetwork(JobParameters jobParameters) {
            return jobParameters.getNetwork();
        }
    }
}
