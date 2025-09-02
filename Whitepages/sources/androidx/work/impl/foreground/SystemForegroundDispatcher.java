package androidx.work.impl.foreground;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import androidx.work.ForegroundInfo;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.constraints.WorkConstraintsTrackerImpl;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class SystemForegroundDispatcher implements WorkConstraintsCallback, ExecutionListener {
    static final String TAG = Logger.tagWithPrefix("SystemFgDispatcher");
    private Callback mCallback;
    final WorkConstraintsTracker mConstraintsTracker;
    private Context mContext;
    WorkGenerationalId mCurrentForegroundId;
    final Map mForegroundInfoById;
    final Object mLock = new Object();
    private final TaskExecutor mTaskExecutor;
    final Set mTrackedWorkSpecs;
    /* access modifiers changed from: private */
    public WorkManagerImpl mWorkManagerImpl;
    final Map mWorkSpecById;

    interface Callback {
        void cancelNotification(int i);

        void notify(int i, Notification notification);

        void startForeground(int i, int i2, Notification notification);

        void stop();
    }

    public void onAllConstraintsMet(List list) {
    }

    SystemForegroundDispatcher(Context context) {
        this.mContext = context;
        WorkManagerImpl instance = WorkManagerImpl.getInstance(context);
        this.mWorkManagerImpl = instance;
        this.mTaskExecutor = instance.getWorkTaskExecutor();
        this.mCurrentForegroundId = null;
        this.mForegroundInfoById = new LinkedHashMap();
        this.mTrackedWorkSpecs = new HashSet();
        this.mWorkSpecById = new HashMap();
        this.mConstraintsTracker = new WorkConstraintsTrackerImpl(this.mWorkManagerImpl.getTrackers(), (WorkConstraintsCallback) this);
        this.mWorkManagerImpl.getProcessor().addExecutionListener(this);
    }

    public void onExecuted(WorkGenerationalId workGenerationalId, boolean z) {
        Map.Entry entry;
        synchronized (this.mLock) {
            try {
                WorkSpec workSpec = (WorkSpec) this.mWorkSpecById.remove(workGenerationalId);
                if (workSpec != null ? this.mTrackedWorkSpecs.remove(workSpec) : false) {
                    this.mConstraintsTracker.replace(this.mTrackedWorkSpecs);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        ForegroundInfo foregroundInfo = (ForegroundInfo) this.mForegroundInfoById.remove(workGenerationalId);
        if (workGenerationalId.equals(this.mCurrentForegroundId) && this.mForegroundInfoById.size() > 0) {
            Iterator it = this.mForegroundInfoById.entrySet().iterator();
            Object next = it.next();
            while (true) {
                entry = (Map.Entry) next;
                if (!it.hasNext()) {
                    break;
                }
                next = it.next();
            }
            this.mCurrentForegroundId = (WorkGenerationalId) entry.getKey();
            if (this.mCallback != null) {
                ForegroundInfo foregroundInfo2 = (ForegroundInfo) entry.getValue();
                this.mCallback.startForeground(foregroundInfo2.getNotificationId(), foregroundInfo2.getForegroundServiceType(), foregroundInfo2.getNotification());
                this.mCallback.cancelNotification(foregroundInfo2.getNotificationId());
            }
        }
        Callback callback = this.mCallback;
        if (foregroundInfo != null && callback != null) {
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "Removing Notification (id: " + foregroundInfo.getNotificationId() + ", workSpecId: " + workGenerationalId + ", notificationType: " + foregroundInfo.getForegroundServiceType());
            callback.cancelNotification(foregroundInfo.getNotificationId());
        }
    }

    /* access modifiers changed from: package-private */
    public void setCallback(Callback callback) {
        if (this.mCallback != null) {
            Logger.get().error(TAG, "A callback already exists.");
        } else {
            this.mCallback = callback;
        }
    }

    /* access modifiers changed from: package-private */
    public void onStartCommand(Intent intent) {
        String action = intent.getAction();
        if ("ACTION_START_FOREGROUND".equals(action)) {
            handleStartForeground(intent);
            handleNotify(intent);
        } else if ("ACTION_NOTIFY".equals(action)) {
            handleNotify(intent);
        } else if ("ACTION_CANCEL_WORK".equals(action)) {
            handleCancelWork(intent);
        } else if ("ACTION_STOP_FOREGROUND".equals(action)) {
            handleStop(intent);
        }
    }

    /* access modifiers changed from: package-private */
    public void onDestroy() {
        this.mCallback = null;
        synchronized (this.mLock) {
            this.mConstraintsTracker.reset();
        }
        this.mWorkManagerImpl.getProcessor().removeExecutionListener(this);
    }

    private void handleStartForeground(Intent intent) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.info(str, "Started foreground service " + intent);
        final String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        this.mTaskExecutor.executeOnTaskThread(new Runnable() {
            public void run() {
                WorkSpec runningWorkSpec = SystemForegroundDispatcher.this.mWorkManagerImpl.getProcessor().getRunningWorkSpec(stringExtra);
                if (runningWorkSpec != null && runningWorkSpec.hasConstraints()) {
                    synchronized (SystemForegroundDispatcher.this.mLock) {
                        SystemForegroundDispatcher.this.mWorkSpecById.put(WorkSpecKt.generationalId(runningWorkSpec), runningWorkSpec);
                        SystemForegroundDispatcher.this.mTrackedWorkSpecs.add(runningWorkSpec);
                        SystemForegroundDispatcher systemForegroundDispatcher = SystemForegroundDispatcher.this;
                        systemForegroundDispatcher.mConstraintsTracker.replace(systemForegroundDispatcher.mTrackedWorkSpecs);
                    }
                }
            }
        });
    }

    private void handleNotify(Intent intent) {
        int i = 0;
        int intExtra = intent.getIntExtra("KEY_NOTIFICATION_ID", 0);
        int intExtra2 = intent.getIntExtra("KEY_FOREGROUND_SERVICE_TYPE", 0);
        String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        WorkGenerationalId workGenerationalId = new WorkGenerationalId(stringExtra, intent.getIntExtra("KEY_GENERATION", 0));
        Notification notification = (Notification) intent.getParcelableExtra("KEY_NOTIFICATION");
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Notifying with (id:" + intExtra + ", workSpecId: " + stringExtra + ", notificationType :" + intExtra2 + ")");
        if (notification != null && this.mCallback != null) {
            this.mForegroundInfoById.put(workGenerationalId, new ForegroundInfo(intExtra, notification, intExtra2));
            if (this.mCurrentForegroundId == null) {
                this.mCurrentForegroundId = workGenerationalId;
                this.mCallback.startForeground(intExtra, intExtra2, notification);
                return;
            }
            this.mCallback.notify(intExtra, notification);
            if (intExtra2 != 0 && Build.VERSION.SDK_INT >= 29) {
                for (Map.Entry value : this.mForegroundInfoById.entrySet()) {
                    i |= ((ForegroundInfo) value.getValue()).getForegroundServiceType();
                }
                ForegroundInfo foregroundInfo = (ForegroundInfo) this.mForegroundInfoById.get(this.mCurrentForegroundId);
                if (foregroundInfo != null) {
                    this.mCallback.startForeground(foregroundInfo.getNotificationId(), i, foregroundInfo.getNotification());
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void handleStop(Intent intent) {
        Logger.get().info(TAG, "Stopping foreground service");
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.stop();
        }
    }

    private void handleCancelWork(Intent intent) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.info(str, "Stopping foreground work for " + intent);
        String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        if (stringExtra != null && !TextUtils.isEmpty(stringExtra)) {
            this.mWorkManagerImpl.cancelWorkById(UUID.fromString(stringExtra));
        }
    }

    public void onAllConstraintsNotMet(List list) {
        if (!list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                WorkSpec workSpec = (WorkSpec) it.next();
                String str = workSpec.id;
                Logger logger = Logger.get();
                String str2 = TAG;
                logger.debug(str2, "Constraints unmet for WorkSpec " + str);
                this.mWorkManagerImpl.stopForegroundWork(WorkSpecKt.generationalId(workSpec));
            }
        }
    }

    public static Intent createStartForegroundIntent(Context context, WorkGenerationalId workGenerationalId, ForegroundInfo foregroundInfo) {
        Intent intent = new Intent(context, SystemForegroundService.class);
        intent.setAction("ACTION_START_FOREGROUND");
        intent.putExtra("KEY_WORKSPEC_ID", workGenerationalId.getWorkSpecId());
        intent.putExtra("KEY_GENERATION", workGenerationalId.getGeneration());
        intent.putExtra("KEY_NOTIFICATION_ID", foregroundInfo.getNotificationId());
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", foregroundInfo.getForegroundServiceType());
        intent.putExtra("KEY_NOTIFICATION", foregroundInfo.getNotification());
        return intent;
    }

    public static Intent createNotifyIntent(Context context, WorkGenerationalId workGenerationalId, ForegroundInfo foregroundInfo) {
        Intent intent = new Intent(context, SystemForegroundService.class);
        intent.setAction("ACTION_NOTIFY");
        intent.putExtra("KEY_NOTIFICATION_ID", foregroundInfo.getNotificationId());
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", foregroundInfo.getForegroundServiceType());
        intent.putExtra("KEY_NOTIFICATION", foregroundInfo.getNotification());
        intent.putExtra("KEY_WORKSPEC_ID", workGenerationalId.getWorkSpecId());
        intent.putExtra("KEY_GENERATION", workGenerationalId.getGeneration());
        return intent;
    }

    public static Intent createStopForegroundIntent(Context context) {
        Intent intent = new Intent(context, SystemForegroundService.class);
        intent.setAction("ACTION_STOP_FOREGROUND");
        return intent;
    }
}
