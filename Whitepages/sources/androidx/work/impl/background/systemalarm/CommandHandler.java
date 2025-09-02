package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.StartStopTokens;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHandler implements ExecutionListener {
    private static final String TAG = Logger.tagWithPrefix("CommandHandler");
    private final Context mContext;
    private final Object mLock = new Object();
    private final Map mPendingDelayMet = new HashMap();
    private final StartStopTokens mStartStopTokens;

    static Intent createScheduleWorkIntent(Context context, WorkGenerationalId workGenerationalId) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_SCHEDULE_WORK");
        return writeWorkGenerationalId(intent, workGenerationalId);
    }

    private static Intent writeWorkGenerationalId(Intent intent, WorkGenerationalId workGenerationalId) {
        intent.putExtra("KEY_WORKSPEC_ID", workGenerationalId.getWorkSpecId());
        intent.putExtra("KEY_WORKSPEC_GENERATION", workGenerationalId.getGeneration());
        return intent;
    }

    static WorkGenerationalId readWorkGenerationalId(Intent intent) {
        return new WorkGenerationalId(intent.getStringExtra("KEY_WORKSPEC_ID"), intent.getIntExtra("KEY_WORKSPEC_GENERATION", 0));
    }

    static Intent createDelayMetIntent(Context context, WorkGenerationalId workGenerationalId) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_DELAY_MET");
        return writeWorkGenerationalId(intent, workGenerationalId);
    }

    static Intent createStopWorkIntent(Context context, WorkGenerationalId workGenerationalId) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_STOP_WORK");
        return writeWorkGenerationalId(intent, workGenerationalId);
    }

    static Intent createConstraintsChangedIntent(Context context) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_CONSTRAINTS_CHANGED");
        return intent;
    }

    static Intent createExecutionCompletedIntent(Context context, WorkGenerationalId workGenerationalId, boolean z) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_EXECUTION_COMPLETED");
        intent.putExtra("KEY_NEEDS_RESCHEDULE", z);
        return writeWorkGenerationalId(intent, workGenerationalId);
    }

    CommandHandler(Context context, StartStopTokens startStopTokens) {
        this.mContext = context;
        this.mStartStopTokens = startStopTokens;
    }

    public void onExecuted(WorkGenerationalId workGenerationalId, boolean z) {
        synchronized (this.mLock) {
            try {
                DelayMetCommandHandler delayMetCommandHandler = (DelayMetCommandHandler) this.mPendingDelayMet.remove(workGenerationalId);
                this.mStartStopTokens.remove(workGenerationalId);
                if (delayMetCommandHandler != null) {
                    delayMetCommandHandler.onExecuted(z);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasPendingCommands() {
        boolean z;
        synchronized (this.mLock) {
            z = !this.mPendingDelayMet.isEmpty();
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public void onHandleIntent(Intent intent, int i, SystemAlarmDispatcher systemAlarmDispatcher) {
        String action = intent.getAction();
        if ("ACTION_CONSTRAINTS_CHANGED".equals(action)) {
            handleConstraintsChanged(intent, i, systemAlarmDispatcher);
        } else if ("ACTION_RESCHEDULE".equals(action)) {
            handleReschedule(intent, i, systemAlarmDispatcher);
        } else if (!hasKeys(intent.getExtras(), "KEY_WORKSPEC_ID")) {
            Logger logger = Logger.get();
            String str = TAG;
            logger.error(str, "Invalid request for " + action + " , requires " + "KEY_WORKSPEC_ID" + " .");
        } else if ("ACTION_SCHEDULE_WORK".equals(action)) {
            handleScheduleWorkIntent(intent, i, systemAlarmDispatcher);
        } else if ("ACTION_DELAY_MET".equals(action)) {
            handleDelayMet(intent, i, systemAlarmDispatcher);
        } else if ("ACTION_STOP_WORK".equals(action)) {
            handleStopWork(intent, systemAlarmDispatcher);
        } else if ("ACTION_EXECUTION_COMPLETED".equals(action)) {
            handleExecutionCompleted(intent, i);
        } else {
            Logger logger2 = Logger.get();
            String str2 = TAG;
            logger2.warning(str2, "Ignoring intent " + intent);
        }
    }

    private void handleScheduleWorkIntent(Intent intent, int i, SystemAlarmDispatcher systemAlarmDispatcher) {
        WorkGenerationalId readWorkGenerationalId = readWorkGenerationalId(intent);
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Handling schedule work for " + readWorkGenerationalId);
        WorkDatabase workDatabase = systemAlarmDispatcher.getWorkManager().getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            WorkSpec workSpec = workDatabase.workSpecDao().getWorkSpec(readWorkGenerationalId.getWorkSpecId());
            if (workSpec == null) {
                Logger logger2 = Logger.get();
                logger2.warning(str, "Skipping scheduling " + readWorkGenerationalId + " because it's no longer in the DB");
            } else if (workSpec.state.isFinished()) {
                Logger logger3 = Logger.get();
                logger3.warning(str, "Skipping scheduling " + readWorkGenerationalId + "because it is finished.");
                workDatabase.endTransaction();
            } else {
                long calculateNextRunTime = workSpec.calculateNextRunTime();
                if (!workSpec.hasConstraints()) {
                    Logger logger4 = Logger.get();
                    logger4.debug(str, "Setting up Alarms for " + readWorkGenerationalId + "at " + calculateNextRunTime);
                    Alarms.setAlarm(this.mContext, workDatabase, readWorkGenerationalId, calculateNextRunTime);
                } else {
                    Logger logger5 = Logger.get();
                    logger5.debug(str, "Opportunistically setting an alarm for " + readWorkGenerationalId + "at " + calculateNextRunTime);
                    Alarms.setAlarm(this.mContext, workDatabase, readWorkGenerationalId, calculateNextRunTime);
                    systemAlarmDispatcher.getTaskExecutor().getMainThreadExecutor().execute(new SystemAlarmDispatcher.AddRunnable(systemAlarmDispatcher, createConstraintsChangedIntent(this.mContext), i));
                }
                workDatabase.setTransactionSuccessful();
                workDatabase.endTransaction();
            }
        } finally {
            workDatabase.endTransaction();
        }
    }

    private void handleDelayMet(Intent intent, int i, SystemAlarmDispatcher systemAlarmDispatcher) {
        synchronized (this.mLock) {
            try {
                WorkGenerationalId readWorkGenerationalId = readWorkGenerationalId(intent);
                Logger logger = Logger.get();
                String str = TAG;
                logger.debug(str, "Handing delay met for " + readWorkGenerationalId);
                if (!this.mPendingDelayMet.containsKey(readWorkGenerationalId)) {
                    DelayMetCommandHandler delayMetCommandHandler = new DelayMetCommandHandler(this.mContext, i, systemAlarmDispatcher, this.mStartStopTokens.tokenFor(readWorkGenerationalId));
                    this.mPendingDelayMet.put(readWorkGenerationalId, delayMetCommandHandler);
                    delayMetCommandHandler.handleProcessWork();
                } else {
                    Logger logger2 = Logger.get();
                    logger2.debug(str, "WorkSpec " + readWorkGenerationalId + " is is already being handled for ACTION_DELAY_MET");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void handleStopWork(Intent intent, SystemAlarmDispatcher systemAlarmDispatcher) {
        List<StartStopToken> list;
        Bundle extras = intent.getExtras();
        String string = extras.getString("KEY_WORKSPEC_ID");
        if (extras.containsKey("KEY_WORKSPEC_GENERATION")) {
            int i = extras.getInt("KEY_WORKSPEC_GENERATION");
            list = new ArrayList<>(1);
            StartStopToken remove = this.mStartStopTokens.remove(new WorkGenerationalId(string, i));
            if (remove != null) {
                list.add(remove);
            }
        } else {
            list = this.mStartStopTokens.remove(string);
        }
        for (StartStopToken startStopToken : list) {
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "Handing stopWork work for " + string);
            systemAlarmDispatcher.getWorkManager().stopWork(startStopToken);
            Alarms.cancelAlarm(this.mContext, systemAlarmDispatcher.getWorkManager().getWorkDatabase(), startStopToken.getId());
            systemAlarmDispatcher.onExecuted(startStopToken.getId(), false);
        }
    }

    private void handleConstraintsChanged(Intent intent, int i, SystemAlarmDispatcher systemAlarmDispatcher) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Handling constraints changed " + intent);
        new ConstraintsCommandHandler(this.mContext, i, systemAlarmDispatcher).handleConstraintsChanged();
    }

    private void handleReschedule(Intent intent, int i, SystemAlarmDispatcher systemAlarmDispatcher) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Handling reschedule " + intent + ", " + i);
        systemAlarmDispatcher.getWorkManager().rescheduleEligibleWork();
    }

    private void handleExecutionCompleted(Intent intent, int i) {
        WorkGenerationalId readWorkGenerationalId = readWorkGenerationalId(intent);
        boolean z = intent.getExtras().getBoolean("KEY_NEEDS_RESCHEDULE");
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Handling onExecutionCompleted " + intent + ", " + i);
        onExecuted(readWorkGenerationalId, z);
    }

    private static boolean hasKeys(Bundle bundle, String... strArr) {
        if (bundle == null || bundle.isEmpty()) {
            return false;
        }
        for (String str : strArr) {
            if (bundle.get(str) == null) {
                return false;
            }
        }
        return true;
    }
}
