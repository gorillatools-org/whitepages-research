package androidx.work.impl.utils;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.ApplicationExitInfo;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteTableLockedException;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.util.Consumer;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.WorkInfo$State;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkDatabasePathHelper;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ForceStopRunnable implements Runnable {
    private static final String TAG = Logger.tagWithPrefix("ForceStopRunnable");
    private static final long TEN_YEARS = TimeUnit.DAYS.toMillis(3650);
    private final Context mContext;
    private final PreferenceUtils mPreferenceUtils;
    private int mRetryCount = 0;
    private final WorkManagerImpl mWorkManager;

    public ForceStopRunnable(Context context, WorkManagerImpl workManagerImpl) {
        this.mContext = context.getApplicationContext();
        this.mWorkManager = workManagerImpl;
        this.mPreferenceUtils = workManagerImpl.getPreferenceUtils();
    }

    public void run() {
        try {
            if (!multiProcessChecks()) {
                this.mWorkManager.onForceStopRunnableCompleted();
                return;
            }
            while (true) {
                WorkDatabasePathHelper.migrateDatabase(this.mContext);
                Logger.get().debug(TAG, "Performing cleanup operations.");
                forceStopRunnable();
                break;
            }
            this.mWorkManager.onForceStopRunnableCompleted();
        } catch (SQLiteException e) {
            Logger.get().error(TAG, "Unexpected SQLite exception during migrations");
            IllegalStateException illegalStateException = new IllegalStateException("Unexpected SQLite exception during migrations", e);
            Consumer initializationExceptionHandler = this.mWorkManager.getConfiguration().getInitializationExceptionHandler();
            if (initializationExceptionHandler != null) {
                initializationExceptionHandler.accept(illegalStateException);
            } else {
                throw illegalStateException;
            }
        } catch (SQLiteAccessPermException | SQLiteCantOpenDatabaseException | SQLiteConstraintException | SQLiteDatabaseCorruptException | SQLiteDatabaseLockedException | SQLiteDiskIOException | SQLiteTableLockedException e2) {
            int i = this.mRetryCount + 1;
            this.mRetryCount = i;
            if (i >= 3) {
                Logger logger = Logger.get();
                String str = TAG;
                logger.error(str, "The file system on the device is in a bad state. WorkManager cannot access the app's internal data store.", e2);
                IllegalStateException illegalStateException2 = new IllegalStateException("The file system on the device is in a bad state. WorkManager cannot access the app's internal data store.", e2);
                Consumer initializationExceptionHandler2 = this.mWorkManager.getConfiguration().getInitializationExceptionHandler();
                if (initializationExceptionHandler2 != null) {
                    Logger.get().debug(str, "Routing exception to the specified exception handler", illegalStateException2);
                    initializationExceptionHandler2.accept(illegalStateException2);
                } else {
                    throw illegalStateException2;
                }
            } else {
                Logger logger2 = Logger.get();
                String str2 = TAG;
                logger2.debug(str2, "Retrying after " + (((long) i) * 300), e2);
                sleep(((long) this.mRetryCount) * 300);
            }
        } catch (Throwable th) {
            this.mWorkManager.onForceStopRunnableCompleted();
            throw th;
        }
    }

    public boolean isForceStopped() {
        try {
            int i = Build.VERSION.SDK_INT;
            PendingIntent pendingIntent = getPendingIntent(this.mContext, i >= 31 ? 570425344 : 536870912);
            if (i >= 30) {
                if (pendingIntent != null) {
                    pendingIntent.cancel();
                }
                List m = ((ActivityManager) this.mContext.getSystemService("activity")).getHistoricalProcessExitReasons((String) null, 0, 0);
                if (m != null && !m.isEmpty()) {
                    long lastForceStopEventMillis = this.mPreferenceUtils.getLastForceStopEventMillis();
                    for (int i2 = 0; i2 < m.size(); i2++) {
                        ApplicationExitInfo m2 = ForceStopRunnable$$ExternalSyntheticApiModelOutline1.m(m.get(i2));
                        if (m2.getReason() == 10 && m2.getTimestamp() >= lastForceStopEventMillis) {
                            return true;
                        }
                    }
                }
            } else if (pendingIntent == null) {
                setAlarm(this.mContext);
                return true;
            }
            return false;
        } catch (SecurityException e) {
            e = e;
            Logger.get().warning(TAG, "Ignoring exception", e);
            return true;
        } catch (IllegalArgumentException e2) {
            e = e2;
            Logger.get().warning(TAG, "Ignoring exception", e);
            return true;
        }
    }

    public void forceStopRunnable() {
        boolean cleanUp = cleanUp();
        if (shouldRescheduleWorkers()) {
            Logger.get().debug(TAG, "Rescheduling Workers.");
            this.mWorkManager.rescheduleEligibleWork();
            this.mWorkManager.getPreferenceUtils().setNeedsReschedule(false);
        } else if (isForceStopped()) {
            Logger.get().debug(TAG, "Application was force-stopped, rescheduling.");
            this.mWorkManager.rescheduleEligibleWork();
            this.mPreferenceUtils.setLastForceStopEventMillis(System.currentTimeMillis());
        } else if (cleanUp) {
            Logger.get().debug(TAG, "Found unfinished work, scheduling it.");
            Schedulers.schedule(this.mWorkManager.getConfiguration(), this.mWorkManager.getWorkDatabase(), this.mWorkManager.getSchedulers());
        }
    }

    public boolean cleanUp() {
        boolean reconcileJobs = SystemJobScheduler.reconcileJobs(this.mContext, this.mWorkManager);
        WorkDatabase workDatabase = this.mWorkManager.getWorkDatabase();
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        WorkProgressDao workProgressDao = workDatabase.workProgressDao();
        workDatabase.beginTransaction();
        try {
            List<WorkSpec> runningWork = workSpecDao.getRunningWork();
            boolean z = runningWork != null && !runningWork.isEmpty();
            if (z) {
                for (WorkSpec workSpec : runningWork) {
                    workSpecDao.setState(WorkInfo$State.ENQUEUED, workSpec.id);
                    workSpecDao.markWorkSpecScheduled(workSpec.id, -1);
                }
            }
            workProgressDao.deleteAll();
            workDatabase.setTransactionSuccessful();
            workDatabase.endTransaction();
            if (z || reconcileJobs) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            workDatabase.endTransaction();
            throw th;
        }
    }

    public boolean shouldRescheduleWorkers() {
        return this.mWorkManager.getPreferenceUtils().getNeedsReschedule();
    }

    public boolean multiProcessChecks() {
        Configuration configuration = this.mWorkManager.getConfiguration();
        if (TextUtils.isEmpty(configuration.getDefaultProcessName())) {
            Logger.get().debug(TAG, "The default process name was not specified.");
            return true;
        }
        boolean isDefaultProcess = ProcessUtils.isDefaultProcess(this.mContext, configuration);
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Is default app process = " + isDefaultProcess);
        return isDefaultProcess;
    }

    public void sleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
        }
    }

    private static PendingIntent getPendingIntent(Context context, int i) {
        return PendingIntent.getBroadcast(context, -1, getIntent(context), i);
    }

    static Intent getIntent(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, BroadcastReceiver.class));
        intent.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        return intent;
    }

    static void setAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent pendingIntent = getPendingIntent(context, Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728);
        long currentTimeMillis = System.currentTimeMillis() + TEN_YEARS;
        if (alarmManager != null) {
            alarmManager.setExact(0, currentTimeMillis, pendingIntent);
        }
    }

    public static class BroadcastReceiver extends android.content.BroadcastReceiver {
        private static final String TAG = Logger.tagWithPrefix("ForceStopRunnable$Rcvr");

        public void onReceive(Context context, Intent intent) {
            if (intent != null && "ACTION_FORCE_STOP_RESCHEDULE".equals(intent.getAction())) {
                Logger.get().verbose(TAG, "Rescheduling alarm that keeps track of force-stops.");
                ForceStopRunnable.setAlarm(context);
            }
        }
    }
}
