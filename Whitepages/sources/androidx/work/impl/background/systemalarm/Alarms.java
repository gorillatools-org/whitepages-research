package androidx.work.impl.background.systemalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import androidx.work.Logger;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.SystemIdInfoKt;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.utils.IdGenerator;

abstract class Alarms {
    private static final String TAG = Logger.tagWithPrefix("Alarms");

    public static void setAlarm(Context context, WorkDatabase workDatabase, WorkGenerationalId workGenerationalId, long j) {
        SystemIdInfoDao systemIdInfoDao = workDatabase.systemIdInfoDao();
        SystemIdInfo systemIdInfo = systemIdInfoDao.getSystemIdInfo(workGenerationalId);
        if (systemIdInfo != null) {
            cancelExactAlarm(context, workGenerationalId, systemIdInfo.systemId);
            setExactAlarm(context, workGenerationalId, systemIdInfo.systemId, j);
            return;
        }
        int nextAlarmManagerId = new IdGenerator(workDatabase).nextAlarmManagerId();
        systemIdInfoDao.insertSystemIdInfo(SystemIdInfoKt.systemIdInfo(workGenerationalId, nextAlarmManagerId));
        setExactAlarm(context, workGenerationalId, nextAlarmManagerId, j);
    }

    public static void cancelAlarm(Context context, WorkDatabase workDatabase, WorkGenerationalId workGenerationalId) {
        SystemIdInfoDao systemIdInfoDao = workDatabase.systemIdInfoDao();
        SystemIdInfo systemIdInfo = systemIdInfoDao.getSystemIdInfo(workGenerationalId);
        if (systemIdInfo != null) {
            cancelExactAlarm(context, workGenerationalId, systemIdInfo.systemId);
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "Removing SystemIdInfo for workSpecId (" + workGenerationalId + ")");
            systemIdInfoDao.removeSystemIdInfo(workGenerationalId);
        }
    }

    private static void cancelExactAlarm(Context context, WorkGenerationalId workGenerationalId, int i) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent service = PendingIntent.getService(context, i, CommandHandler.createDelayMetIntent(context, workGenerationalId), 603979776);
        if (service != null && alarmManager != null) {
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "Cancelling existing alarm with (workSpecId, systemId) (" + workGenerationalId + ", " + i + ")");
            alarmManager.cancel(service);
        }
    }

    private static void setExactAlarm(Context context, WorkGenerationalId workGenerationalId, int i, long j) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent service = PendingIntent.getService(context, i, CommandHandler.createDelayMetIntent(context, workGenerationalId), 201326592);
        if (alarmManager != null) {
            Api19Impl.setExact(alarmManager, 0, j, service);
        }
    }

    static class Api19Impl {
        static void setExact(AlarmManager alarmManager, int i, long j, PendingIntent pendingIntent) {
            alarmManager.setExact(i, j, pendingIntent);
        }
    }
}
