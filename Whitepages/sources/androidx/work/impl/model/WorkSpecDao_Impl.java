package androidx.work.impl.model;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import androidx.work.impl.model.WorkSpec;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class WorkSpecDao_Impl implements WorkSpecDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfWorkSpec;
    private final SharedSQLiteStatement __preparedStmtOfDelete;
    private final SharedSQLiteStatement __preparedStmtOfIncrementGeneration;
    private final SharedSQLiteStatement __preparedStmtOfIncrementPeriodCount;
    private final SharedSQLiteStatement __preparedStmtOfIncrementWorkSpecRunAttemptCount;
    private final SharedSQLiteStatement __preparedStmtOfMarkWorkSpecScheduled;
    private final SharedSQLiteStatement __preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast;
    private final SharedSQLiteStatement __preparedStmtOfResetScheduledState;
    private final SharedSQLiteStatement __preparedStmtOfResetWorkSpecRunAttemptCount;
    private final SharedSQLiteStatement __preparedStmtOfSetLastEnqueuedTime;
    private final SharedSQLiteStatement __preparedStmtOfSetOutput;
    private final SharedSQLiteStatement __preparedStmtOfSetState;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfWorkSpec;

    public WorkSpecDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfWorkSpec = new EntityInsertionAdapter(roomDatabase) {
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkSpec` (`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`last_enqueue_time`,`minimum_retention_duration`,`schedule_requested_at`,`run_in_foreground`,`out_of_quota_policy`,`period_count`,`generation`,`required_network_type`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, WorkSpec workSpec) {
                String str = workSpec.id;
                if (str == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, str);
                }
                WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                supportSQLiteStatement.bindLong(2, (long) WorkTypeConverters.stateToInt(workSpec.state));
                String str2 = workSpec.workerClassName;
                if (str2 == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, str2);
                }
                String str3 = workSpec.inputMergerClassName;
                if (str3 == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, str3);
                }
                byte[] byteArrayInternal = Data.toByteArrayInternal(workSpec.input);
                if (byteArrayInternal == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindBlob(5, byteArrayInternal);
                }
                byte[] byteArrayInternal2 = Data.toByteArrayInternal(workSpec.output);
                if (byteArrayInternal2 == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindBlob(6, byteArrayInternal2);
                }
                supportSQLiteStatement.bindLong(7, workSpec.initialDelay);
                supportSQLiteStatement.bindLong(8, workSpec.intervalDuration);
                supportSQLiteStatement.bindLong(9, workSpec.flexDuration);
                supportSQLiteStatement.bindLong(10, (long) workSpec.runAttemptCount);
                supportSQLiteStatement.bindLong(11, (long) WorkTypeConverters.backoffPolicyToInt(workSpec.backoffPolicy));
                supportSQLiteStatement.bindLong(12, workSpec.backoffDelayDuration);
                supportSQLiteStatement.bindLong(13, workSpec.lastEnqueueTime);
                supportSQLiteStatement.bindLong(14, workSpec.minimumRetentionDuration);
                supportSQLiteStatement.bindLong(15, workSpec.scheduleRequestedAt);
                supportSQLiteStatement.bindLong(16, workSpec.expedited ? 1 : 0);
                supportSQLiteStatement.bindLong(17, (long) WorkTypeConverters.outOfQuotaPolicyToInt(workSpec.outOfQuotaPolicy));
                supportSQLiteStatement.bindLong(18, (long) workSpec.getPeriodCount());
                supportSQLiteStatement.bindLong(19, (long) workSpec.getGeneration());
                Constraints constraints = workSpec.constraints;
                if (constraints != null) {
                    supportSQLiteStatement.bindLong(20, (long) WorkTypeConverters.networkTypeToInt(constraints.getRequiredNetworkType()));
                    supportSQLiteStatement.bindLong(21, constraints.requiresCharging() ? 1 : 0);
                    supportSQLiteStatement.bindLong(22, constraints.requiresDeviceIdle() ? 1 : 0);
                    supportSQLiteStatement.bindLong(23, constraints.requiresBatteryNotLow() ? 1 : 0);
                    supportSQLiteStatement.bindLong(24, constraints.requiresStorageNotLow() ? 1 : 0);
                    supportSQLiteStatement.bindLong(25, constraints.getContentTriggerUpdateDelayMillis());
                    supportSQLiteStatement.bindLong(26, constraints.getContentTriggerMaxDelayMillis());
                    byte[] ofTriggersToByteArray = WorkTypeConverters.setOfTriggersToByteArray(constraints.getContentUriTriggers());
                    if (ofTriggersToByteArray == null) {
                        supportSQLiteStatement.bindNull(27);
                    } else {
                        supportSQLiteStatement.bindBlob(27, ofTriggersToByteArray);
                    }
                } else {
                    supportSQLiteStatement.bindNull(20);
                    supportSQLiteStatement.bindNull(21);
                    supportSQLiteStatement.bindNull(22);
                    supportSQLiteStatement.bindNull(23);
                    supportSQLiteStatement.bindNull(24);
                    supportSQLiteStatement.bindNull(25);
                    supportSQLiteStatement.bindNull(26);
                    supportSQLiteStatement.bindNull(27);
                }
            }
        };
        this.__updateAdapterOfWorkSpec = new EntityDeletionOrUpdateAdapter(roomDatabase) {
            public String createQuery() {
                return "UPDATE OR ABORT `WorkSpec` SET `id` = ?,`state` = ?,`worker_class_name` = ?,`input_merger_class_name` = ?,`input` = ?,`output` = ?,`initial_delay` = ?,`interval_duration` = ?,`flex_duration` = ?,`run_attempt_count` = ?,`backoff_policy` = ?,`backoff_delay_duration` = ?,`last_enqueue_time` = ?,`minimum_retention_duration` = ?,`schedule_requested_at` = ?,`run_in_foreground` = ?,`out_of_quota_policy` = ?,`period_count` = ?,`generation` = ?,`required_network_type` = ?,`requires_charging` = ?,`requires_device_idle` = ?,`requires_battery_not_low` = ?,`requires_storage_not_low` = ?,`trigger_content_update_delay` = ?,`trigger_max_content_delay` = ?,`content_uri_triggers` = ? WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, WorkSpec workSpec) {
                String str = workSpec.id;
                if (str == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, str);
                }
                WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                supportSQLiteStatement.bindLong(2, (long) WorkTypeConverters.stateToInt(workSpec.state));
                String str2 = workSpec.workerClassName;
                if (str2 == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, str2);
                }
                String str3 = workSpec.inputMergerClassName;
                if (str3 == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, str3);
                }
                byte[] byteArrayInternal = Data.toByteArrayInternal(workSpec.input);
                if (byteArrayInternal == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindBlob(5, byteArrayInternal);
                }
                byte[] byteArrayInternal2 = Data.toByteArrayInternal(workSpec.output);
                if (byteArrayInternal2 == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindBlob(6, byteArrayInternal2);
                }
                supportSQLiteStatement.bindLong(7, workSpec.initialDelay);
                supportSQLiteStatement.bindLong(8, workSpec.intervalDuration);
                supportSQLiteStatement.bindLong(9, workSpec.flexDuration);
                supportSQLiteStatement.bindLong(10, (long) workSpec.runAttemptCount);
                supportSQLiteStatement.bindLong(11, (long) WorkTypeConverters.backoffPolicyToInt(workSpec.backoffPolicy));
                supportSQLiteStatement.bindLong(12, workSpec.backoffDelayDuration);
                supportSQLiteStatement.bindLong(13, workSpec.lastEnqueueTime);
                supportSQLiteStatement.bindLong(14, workSpec.minimumRetentionDuration);
                supportSQLiteStatement.bindLong(15, workSpec.scheduleRequestedAt);
                supportSQLiteStatement.bindLong(16, workSpec.expedited ? 1 : 0);
                supportSQLiteStatement.bindLong(17, (long) WorkTypeConverters.outOfQuotaPolicyToInt(workSpec.outOfQuotaPolicy));
                supportSQLiteStatement.bindLong(18, (long) workSpec.getPeriodCount());
                supportSQLiteStatement.bindLong(19, (long) workSpec.getGeneration());
                Constraints constraints = workSpec.constraints;
                if (constraints != null) {
                    supportSQLiteStatement.bindLong(20, (long) WorkTypeConverters.networkTypeToInt(constraints.getRequiredNetworkType()));
                    supportSQLiteStatement.bindLong(21, constraints.requiresCharging() ? 1 : 0);
                    supportSQLiteStatement.bindLong(22, constraints.requiresDeviceIdle() ? 1 : 0);
                    supportSQLiteStatement.bindLong(23, constraints.requiresBatteryNotLow() ? 1 : 0);
                    supportSQLiteStatement.bindLong(24, constraints.requiresStorageNotLow() ? 1 : 0);
                    supportSQLiteStatement.bindLong(25, constraints.getContentTriggerUpdateDelayMillis());
                    supportSQLiteStatement.bindLong(26, constraints.getContentTriggerMaxDelayMillis());
                    byte[] ofTriggersToByteArray = WorkTypeConverters.setOfTriggersToByteArray(constraints.getContentUriTriggers());
                    if (ofTriggersToByteArray == null) {
                        supportSQLiteStatement.bindNull(27);
                    } else {
                        supportSQLiteStatement.bindBlob(27, ofTriggersToByteArray);
                    }
                } else {
                    supportSQLiteStatement.bindNull(20);
                    supportSQLiteStatement.bindNull(21);
                    supportSQLiteStatement.bindNull(22);
                    supportSQLiteStatement.bindNull(23);
                    supportSQLiteStatement.bindNull(24);
                    supportSQLiteStatement.bindNull(25);
                    supportSQLiteStatement.bindNull(26);
                    supportSQLiteStatement.bindNull(27);
                }
                String str4 = workSpec.id;
                if (str4 == null) {
                    supportSQLiteStatement.bindNull(28);
                } else {
                    supportSQLiteStatement.bindString(28, str4);
                }
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM workspec WHERE id=?";
            }
        };
        this.__preparedStmtOfSetState = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET state=? WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementPeriodCount = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET period_count=period_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfSetOutput = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET output=? WHERE id=?";
            }
        };
        this.__preparedStmtOfSetLastEnqueuedTime = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET last_enqueue_time=? WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementWorkSpecRunAttemptCount = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfResetWorkSpecRunAttemptCount = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=0 WHERE id=?";
            }
        };
        this.__preparedStmtOfMarkWorkSpecScheduled = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=? WHERE id=?";
            }
        };
        this.__preparedStmtOfResetScheduledState = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)";
            }
        };
        this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
            }
        };
        this.__preparedStmtOfIncrementGeneration = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET generation=generation+1 WHERE id=?";
            }
        };
    }

    public void insertWorkSpec(WorkSpec workSpec) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWorkSpec.insert(workSpec);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void updateWorkSpec(WorkSpec workSpec) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfWorkSpec.handle(workSpec);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDelete.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDelete.release(acquire);
        }
    }

    public int setState(WorkInfo$State workInfo$State, String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetState.acquire();
        acquire.bindLong(1, (long) WorkTypeConverters.stateToInt(workInfo$State));
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetState.release(acquire);
        }
    }

    public void incrementPeriodCount(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfIncrementPeriodCount.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfIncrementPeriodCount.release(acquire);
        }
    }

    public void setOutput(String str, Data data) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetOutput.acquire();
        byte[] byteArrayInternal = Data.toByteArrayInternal(data);
        if (byteArrayInternal == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindBlob(1, byteArrayInternal);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetOutput.release(acquire);
        }
    }

    public void setLastEnqueuedTime(String str, long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetLastEnqueuedTime.acquire();
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetLastEnqueuedTime.release(acquire);
        }
    }

    public int incrementWorkSpecRunAttemptCount(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.release(acquire);
        }
    }

    public int resetWorkSpecRunAttemptCount(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfResetWorkSpecRunAttemptCount.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfResetWorkSpecRunAttemptCount.release(acquire);
        }
    }

    public int markWorkSpecScheduled(String str, long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMarkWorkSpecScheduled.acquire();
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMarkWorkSpecScheduled.release(acquire);
        }
    }

    public int resetScheduledState() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfResetScheduledState.acquire();
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfResetScheduledState.release(acquire);
        }
    }

    public void pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast.release(acquire);
        }
    }

    public WorkSpec getWorkSpec(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        WorkSpec workSpec;
        String str2;
        String str3;
        String str4;
        byte[] bArr;
        byte[] bArr2;
        boolean z;
        int i;
        boolean z2;
        int i2;
        boolean z3;
        int i3;
        boolean z4;
        int i4;
        boolean z5;
        int i5;
        byte[] bArr3;
        String str5 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE id=?", 1);
        if (str5 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str5);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, RemoteConfigConstants.ResponseFieldKey.STATE);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "input");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "last_enqueue_time");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "period_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "generation");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
                if (query.moveToFirst()) {
                    if (query.isNull(columnIndexOrThrow)) {
                        str2 = null;
                    } else {
                        str2 = query.getString(columnIndexOrThrow);
                    }
                    WorkInfo$State intToState = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                    if (query.isNull(columnIndexOrThrow3)) {
                        str3 = null;
                    } else {
                        str3 = query.getString(columnIndexOrThrow3);
                    }
                    if (query.isNull(columnIndexOrThrow4)) {
                        str4 = null;
                    } else {
                        str4 = query.getString(columnIndexOrThrow4);
                    }
                    if (query.isNull(columnIndexOrThrow5)) {
                        bArr = null;
                    } else {
                        bArr = query.getBlob(columnIndexOrThrow5);
                    }
                    Data fromByteArray = Data.fromByteArray(bArr);
                    if (query.isNull(columnIndexOrThrow6)) {
                        bArr2 = null;
                    } else {
                        bArr2 = query.getBlob(columnIndexOrThrow6);
                    }
                    Data fromByteArray2 = Data.fromByteArray(bArr2);
                    long j = query.getLong(columnIndexOrThrow7);
                    long j2 = query.getLong(columnIndexOrThrow8);
                    long j3 = query.getLong(columnIndexOrThrow9);
                    int i6 = query.getInt(columnIndexOrThrow10);
                    BackoffPolicy intToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(columnIndexOrThrow11));
                    long j4 = query.getLong(columnIndexOrThrow12);
                    long j5 = query.getLong(columnIndexOrThrow13);
                    long j6 = query.getLong(columnIndexOrThrow14);
                    long j7 = query.getLong(columnIndexOrThrow15);
                    if (query.getInt(columnIndexOrThrow16) != 0) {
                        i = columnIndexOrThrow17;
                        z = true;
                    } else {
                        i = columnIndexOrThrow17;
                        z = false;
                    }
                    OutOfQuotaPolicy intToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(i));
                    int i7 = query.getInt(columnIndexOrThrow18);
                    int i8 = query.getInt(columnIndexOrThrow19);
                    NetworkType intToNetworkType = WorkTypeConverters.intToNetworkType(query.getInt(columnIndexOrThrow20));
                    if (query.getInt(columnIndexOrThrow21) != 0) {
                        i2 = columnIndexOrThrow22;
                        z2 = true;
                    } else {
                        i2 = columnIndexOrThrow22;
                        z2 = false;
                    }
                    if (query.getInt(i2) != 0) {
                        i3 = columnIndexOrThrow23;
                        z3 = true;
                    } else {
                        i3 = columnIndexOrThrow23;
                        z3 = false;
                    }
                    if (query.getInt(i3) != 0) {
                        i4 = columnIndexOrThrow24;
                        z4 = true;
                    } else {
                        i4 = columnIndexOrThrow24;
                        z4 = false;
                    }
                    if (query.getInt(i4) != 0) {
                        i5 = columnIndexOrThrow25;
                        z5 = true;
                    } else {
                        i5 = columnIndexOrThrow25;
                        z5 = false;
                    }
                    long j8 = query.getLong(i5);
                    long j9 = query.getLong(columnIndexOrThrow26);
                    if (query.isNull(columnIndexOrThrow27)) {
                        bArr3 = null;
                    } else {
                        bArr3 = query.getBlob(columnIndexOrThrow27);
                    }
                    workSpec = new WorkSpec(str2, intToState, str3, str4, fromByteArray, fromByteArray2, j, j2, j3, new Constraints(intToNetworkType, z2, z3, z4, z5, j8, j9, WorkTypeConverters.byteArrayToSetOfTriggers(bArr3)), i6, intToBackoffPolicy, j4, j5, j6, j7, z, intToOutOfQuotaPolicy, i7, i8);
                } else {
                    workSpec = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return workSpec;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public List getWorkSpecIdAndStatesForName(String str) {
        String str2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(0)) {
                    str2 = null;
                } else {
                    str2 = query.getString(0);
                }
                arrayList.add(new WorkSpec.IdAndState(str2, WorkTypeConverters.intToState(query.getInt(1))));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public WorkInfo$State getState(String str) {
        Integer num;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT state FROM workspec WHERE id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        WorkInfo$State workInfo$State = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                if (query.isNull(0)) {
                    num = null;
                } else {
                    num = Integer.valueOf(query.getInt(0));
                }
                if (num != null) {
                    WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                    workInfo$State = WorkTypeConverters.intToState(num.intValue());
                }
            }
            return workInfo$State;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List getInputsFromPrerequisites(String str) {
        byte[] bArr;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT output FROM workspec WHERE id IN\n             (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(0)) {
                    bArr = null;
                } else {
                    bArr = query.getBlob(0);
                }
                arrayList.add(Data.fromByteArray(bArr));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List getUnfinishedWorkWithTag(String str) {
        String str2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(0)) {
                    str2 = null;
                } else {
                    str2 = query.getString(0);
                }
                arrayList.add(str2);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List getUnfinishedWorkWithName(String str) {
        String str2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(0)) {
                    str2 = null;
                } else {
                    str2 = query.getString(0);
                }
                arrayList.add(str2);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public boolean hasUnfinishedWork() {
        boolean z = false;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst() && query.getInt(0) != 0) {
                z = true;
            }
            return z;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List getEligibleWorkForScheduling(int i) {
        RoomSQLiteQuery roomSQLiteQuery;
        String str;
        String str2;
        String str3;
        byte[] bArr;
        byte[] bArr2;
        boolean z;
        int i2;
        boolean z2;
        int i3;
        boolean z3;
        int i4;
        boolean z4;
        int i5;
        boolean z5;
        int i6;
        byte[] bArr3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY last_enqueue_time LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND state NOT IN (2, 3, 5))", 1);
        acquire.bindLong(1, (long) i);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, RemoteConfigConstants.ResponseFieldKey.STATE);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "input");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "last_enqueue_time");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "period_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "generation");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
                int i7 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow)) {
                        str = null;
                    } else {
                        str = query.getString(columnIndexOrThrow);
                    }
                    WorkInfo$State intToState = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                    if (query.isNull(columnIndexOrThrow3)) {
                        str2 = null;
                    } else {
                        str2 = query.getString(columnIndexOrThrow3);
                    }
                    if (query.isNull(columnIndexOrThrow4)) {
                        str3 = null;
                    } else {
                        str3 = query.getString(columnIndexOrThrow4);
                    }
                    if (query.isNull(columnIndexOrThrow5)) {
                        bArr = null;
                    } else {
                        bArr = query.getBlob(columnIndexOrThrow5);
                    }
                    Data fromByteArray = Data.fromByteArray(bArr);
                    if (query.isNull(columnIndexOrThrow6)) {
                        bArr2 = null;
                    } else {
                        bArr2 = query.getBlob(columnIndexOrThrow6);
                    }
                    Data fromByteArray2 = Data.fromByteArray(bArr2);
                    long j = query.getLong(columnIndexOrThrow7);
                    long j2 = query.getLong(columnIndexOrThrow8);
                    long j3 = query.getLong(columnIndexOrThrow9);
                    int i8 = query.getInt(columnIndexOrThrow10);
                    BackoffPolicy intToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(columnIndexOrThrow11));
                    long j4 = query.getLong(columnIndexOrThrow12);
                    long j5 = query.getLong(columnIndexOrThrow13);
                    int i9 = i7;
                    long j6 = query.getLong(i9);
                    int i10 = columnIndexOrThrow;
                    int i11 = columnIndexOrThrow15;
                    long j7 = query.getLong(i11);
                    columnIndexOrThrow15 = i11;
                    int i12 = columnIndexOrThrow16;
                    if (query.getInt(i12) != 0) {
                        columnIndexOrThrow16 = i12;
                        i2 = columnIndexOrThrow17;
                        z = true;
                    } else {
                        columnIndexOrThrow16 = i12;
                        i2 = columnIndexOrThrow17;
                        z = false;
                    }
                    OutOfQuotaPolicy intToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(i2));
                    columnIndexOrThrow17 = i2;
                    int i13 = columnIndexOrThrow18;
                    int i14 = query.getInt(i13);
                    columnIndexOrThrow18 = i13;
                    int i15 = columnIndexOrThrow19;
                    int i16 = query.getInt(i15);
                    columnIndexOrThrow19 = i15;
                    int i17 = columnIndexOrThrow20;
                    NetworkType intToNetworkType = WorkTypeConverters.intToNetworkType(query.getInt(i17));
                    columnIndexOrThrow20 = i17;
                    int i18 = columnIndexOrThrow21;
                    if (query.getInt(i18) != 0) {
                        columnIndexOrThrow21 = i18;
                        i3 = columnIndexOrThrow22;
                        z2 = true;
                    } else {
                        columnIndexOrThrow21 = i18;
                        i3 = columnIndexOrThrow22;
                        z2 = false;
                    }
                    if (query.getInt(i3) != 0) {
                        columnIndexOrThrow22 = i3;
                        i4 = columnIndexOrThrow23;
                        z3 = true;
                    } else {
                        columnIndexOrThrow22 = i3;
                        i4 = columnIndexOrThrow23;
                        z3 = false;
                    }
                    if (query.getInt(i4) != 0) {
                        columnIndexOrThrow23 = i4;
                        i5 = columnIndexOrThrow24;
                        z4 = true;
                    } else {
                        columnIndexOrThrow23 = i4;
                        i5 = columnIndexOrThrow24;
                        z4 = false;
                    }
                    if (query.getInt(i5) != 0) {
                        columnIndexOrThrow24 = i5;
                        i6 = columnIndexOrThrow25;
                        z5 = true;
                    } else {
                        columnIndexOrThrow24 = i5;
                        i6 = columnIndexOrThrow25;
                        z5 = false;
                    }
                    long j8 = query.getLong(i6);
                    columnIndexOrThrow25 = i6;
                    int i19 = columnIndexOrThrow26;
                    long j9 = query.getLong(i19);
                    columnIndexOrThrow26 = i19;
                    int i20 = columnIndexOrThrow27;
                    if (query.isNull(i20)) {
                        bArr3 = null;
                    } else {
                        bArr3 = query.getBlob(i20);
                    }
                    columnIndexOrThrow27 = i20;
                    arrayList.add(new WorkSpec(str, intToState, str2, str3, fromByteArray, fromByteArray2, j, j2, j3, new Constraints(intToNetworkType, z2, z3, z4, z5, j8, j9, WorkTypeConverters.byteArrayToSetOfTriggers(bArr3)), i8, intToBackoffPolicy, j4, j5, j6, j7, z, intToOutOfQuotaPolicy, i14, i16));
                    columnIndexOrThrow = i10;
                    i7 = i9;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public List getAllEligibleWorkSpecsForScheduling(int i) {
        RoomSQLiteQuery roomSQLiteQuery;
        String str;
        String str2;
        String str3;
        byte[] bArr;
        byte[] bArr2;
        boolean z;
        int i2;
        boolean z2;
        int i3;
        boolean z3;
        int i4;
        boolean z4;
        int i5;
        boolean z5;
        int i6;
        byte[] bArr3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=0 ORDER BY last_enqueue_time LIMIT ?", 1);
        acquire.bindLong(1, (long) i);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, RemoteConfigConstants.ResponseFieldKey.STATE);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "input");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "last_enqueue_time");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "period_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "generation");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
                int i7 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow)) {
                        str = null;
                    } else {
                        str = query.getString(columnIndexOrThrow);
                    }
                    WorkInfo$State intToState = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                    if (query.isNull(columnIndexOrThrow3)) {
                        str2 = null;
                    } else {
                        str2 = query.getString(columnIndexOrThrow3);
                    }
                    if (query.isNull(columnIndexOrThrow4)) {
                        str3 = null;
                    } else {
                        str3 = query.getString(columnIndexOrThrow4);
                    }
                    if (query.isNull(columnIndexOrThrow5)) {
                        bArr = null;
                    } else {
                        bArr = query.getBlob(columnIndexOrThrow5);
                    }
                    Data fromByteArray = Data.fromByteArray(bArr);
                    if (query.isNull(columnIndexOrThrow6)) {
                        bArr2 = null;
                    } else {
                        bArr2 = query.getBlob(columnIndexOrThrow6);
                    }
                    Data fromByteArray2 = Data.fromByteArray(bArr2);
                    long j = query.getLong(columnIndexOrThrow7);
                    long j2 = query.getLong(columnIndexOrThrow8);
                    long j3 = query.getLong(columnIndexOrThrow9);
                    int i8 = query.getInt(columnIndexOrThrow10);
                    BackoffPolicy intToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(columnIndexOrThrow11));
                    long j4 = query.getLong(columnIndexOrThrow12);
                    long j5 = query.getLong(columnIndexOrThrow13);
                    int i9 = i7;
                    long j6 = query.getLong(i9);
                    int i10 = columnIndexOrThrow;
                    int i11 = columnIndexOrThrow15;
                    long j7 = query.getLong(i11);
                    columnIndexOrThrow15 = i11;
                    int i12 = columnIndexOrThrow16;
                    if (query.getInt(i12) != 0) {
                        columnIndexOrThrow16 = i12;
                        i2 = columnIndexOrThrow17;
                        z = true;
                    } else {
                        columnIndexOrThrow16 = i12;
                        i2 = columnIndexOrThrow17;
                        z = false;
                    }
                    OutOfQuotaPolicy intToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(i2));
                    columnIndexOrThrow17 = i2;
                    int i13 = columnIndexOrThrow18;
                    int i14 = query.getInt(i13);
                    columnIndexOrThrow18 = i13;
                    int i15 = columnIndexOrThrow19;
                    int i16 = query.getInt(i15);
                    columnIndexOrThrow19 = i15;
                    int i17 = columnIndexOrThrow20;
                    NetworkType intToNetworkType = WorkTypeConverters.intToNetworkType(query.getInt(i17));
                    columnIndexOrThrow20 = i17;
                    int i18 = columnIndexOrThrow21;
                    if (query.getInt(i18) != 0) {
                        columnIndexOrThrow21 = i18;
                        i3 = columnIndexOrThrow22;
                        z2 = true;
                    } else {
                        columnIndexOrThrow21 = i18;
                        i3 = columnIndexOrThrow22;
                        z2 = false;
                    }
                    if (query.getInt(i3) != 0) {
                        columnIndexOrThrow22 = i3;
                        i4 = columnIndexOrThrow23;
                        z3 = true;
                    } else {
                        columnIndexOrThrow22 = i3;
                        i4 = columnIndexOrThrow23;
                        z3 = false;
                    }
                    if (query.getInt(i4) != 0) {
                        columnIndexOrThrow23 = i4;
                        i5 = columnIndexOrThrow24;
                        z4 = true;
                    } else {
                        columnIndexOrThrow23 = i4;
                        i5 = columnIndexOrThrow24;
                        z4 = false;
                    }
                    if (query.getInt(i5) != 0) {
                        columnIndexOrThrow24 = i5;
                        i6 = columnIndexOrThrow25;
                        z5 = true;
                    } else {
                        columnIndexOrThrow24 = i5;
                        i6 = columnIndexOrThrow25;
                        z5 = false;
                    }
                    long j8 = query.getLong(i6);
                    columnIndexOrThrow25 = i6;
                    int i19 = columnIndexOrThrow26;
                    long j9 = query.getLong(i19);
                    columnIndexOrThrow26 = i19;
                    int i20 = columnIndexOrThrow27;
                    if (query.isNull(i20)) {
                        bArr3 = null;
                    } else {
                        bArr3 = query.getBlob(i20);
                    }
                    columnIndexOrThrow27 = i20;
                    arrayList.add(new WorkSpec(str, intToState, str2, str3, fromByteArray, fromByteArray2, j, j2, j3, new Constraints(intToNetworkType, z2, z3, z4, z5, j8, j9, WorkTypeConverters.byteArrayToSetOfTriggers(bArr3)), i8, intToBackoffPolicy, j4, j5, j6, j7, z, intToOutOfQuotaPolicy, i14, i16));
                    columnIndexOrThrow = i10;
                    i7 = i9;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public List getScheduledWork() {
        RoomSQLiteQuery roomSQLiteQuery;
        String str;
        String str2;
        String str3;
        byte[] bArr;
        byte[] bArr2;
        boolean z;
        int i;
        boolean z2;
        int i2;
        boolean z3;
        int i3;
        boolean z4;
        int i4;
        boolean z5;
        int i5;
        byte[] bArr3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at<>-1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, RemoteConfigConstants.ResponseFieldKey.STATE);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "input");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "last_enqueue_time");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "period_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "generation");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
                int i6 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow)) {
                        str = null;
                    } else {
                        str = query.getString(columnIndexOrThrow);
                    }
                    WorkInfo$State intToState = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                    if (query.isNull(columnIndexOrThrow3)) {
                        str2 = null;
                    } else {
                        str2 = query.getString(columnIndexOrThrow3);
                    }
                    if (query.isNull(columnIndexOrThrow4)) {
                        str3 = null;
                    } else {
                        str3 = query.getString(columnIndexOrThrow4);
                    }
                    if (query.isNull(columnIndexOrThrow5)) {
                        bArr = null;
                    } else {
                        bArr = query.getBlob(columnIndexOrThrow5);
                    }
                    Data fromByteArray = Data.fromByteArray(bArr);
                    if (query.isNull(columnIndexOrThrow6)) {
                        bArr2 = null;
                    } else {
                        bArr2 = query.getBlob(columnIndexOrThrow6);
                    }
                    Data fromByteArray2 = Data.fromByteArray(bArr2);
                    long j = query.getLong(columnIndexOrThrow7);
                    long j2 = query.getLong(columnIndexOrThrow8);
                    long j3 = query.getLong(columnIndexOrThrow9);
                    int i7 = query.getInt(columnIndexOrThrow10);
                    BackoffPolicy intToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(columnIndexOrThrow11));
                    long j4 = query.getLong(columnIndexOrThrow12);
                    long j5 = query.getLong(columnIndexOrThrow13);
                    int i8 = i6;
                    long j6 = query.getLong(i8);
                    int i9 = columnIndexOrThrow;
                    int i10 = columnIndexOrThrow15;
                    long j7 = query.getLong(i10);
                    columnIndexOrThrow15 = i10;
                    int i11 = columnIndexOrThrow16;
                    if (query.getInt(i11) != 0) {
                        columnIndexOrThrow16 = i11;
                        i = columnIndexOrThrow17;
                        z = true;
                    } else {
                        columnIndexOrThrow16 = i11;
                        i = columnIndexOrThrow17;
                        z = false;
                    }
                    OutOfQuotaPolicy intToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(i));
                    columnIndexOrThrow17 = i;
                    int i12 = columnIndexOrThrow18;
                    int i13 = query.getInt(i12);
                    columnIndexOrThrow18 = i12;
                    int i14 = columnIndexOrThrow19;
                    int i15 = query.getInt(i14);
                    columnIndexOrThrow19 = i14;
                    int i16 = columnIndexOrThrow20;
                    NetworkType intToNetworkType = WorkTypeConverters.intToNetworkType(query.getInt(i16));
                    columnIndexOrThrow20 = i16;
                    int i17 = columnIndexOrThrow21;
                    if (query.getInt(i17) != 0) {
                        columnIndexOrThrow21 = i17;
                        i2 = columnIndexOrThrow22;
                        z2 = true;
                    } else {
                        columnIndexOrThrow21 = i17;
                        i2 = columnIndexOrThrow22;
                        z2 = false;
                    }
                    if (query.getInt(i2) != 0) {
                        columnIndexOrThrow22 = i2;
                        i3 = columnIndexOrThrow23;
                        z3 = true;
                    } else {
                        columnIndexOrThrow22 = i2;
                        i3 = columnIndexOrThrow23;
                        z3 = false;
                    }
                    if (query.getInt(i3) != 0) {
                        columnIndexOrThrow23 = i3;
                        i4 = columnIndexOrThrow24;
                        z4 = true;
                    } else {
                        columnIndexOrThrow23 = i3;
                        i4 = columnIndexOrThrow24;
                        z4 = false;
                    }
                    if (query.getInt(i4) != 0) {
                        columnIndexOrThrow24 = i4;
                        i5 = columnIndexOrThrow25;
                        z5 = true;
                    } else {
                        columnIndexOrThrow24 = i4;
                        i5 = columnIndexOrThrow25;
                        z5 = false;
                    }
                    long j8 = query.getLong(i5);
                    columnIndexOrThrow25 = i5;
                    int i18 = columnIndexOrThrow26;
                    long j9 = query.getLong(i18);
                    columnIndexOrThrow26 = i18;
                    int i19 = columnIndexOrThrow27;
                    if (query.isNull(i19)) {
                        bArr3 = null;
                    } else {
                        bArr3 = query.getBlob(i19);
                    }
                    columnIndexOrThrow27 = i19;
                    arrayList.add(new WorkSpec(str, intToState, str2, str3, fromByteArray, fromByteArray2, j, j2, j3, new Constraints(intToNetworkType, z2, z3, z4, z5, j8, j9, WorkTypeConverters.byteArrayToSetOfTriggers(bArr3)), i7, intToBackoffPolicy, j4, j5, j6, j7, z, intToOutOfQuotaPolicy, i13, i15));
                    columnIndexOrThrow = i9;
                    i6 = i8;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public List getRunningWork() {
        RoomSQLiteQuery roomSQLiteQuery;
        String str;
        String str2;
        String str3;
        byte[] bArr;
        byte[] bArr2;
        boolean z;
        int i;
        boolean z2;
        int i2;
        boolean z3;
        int i3;
        boolean z4;
        int i4;
        boolean z5;
        int i5;
        byte[] bArr3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, RemoteConfigConstants.ResponseFieldKey.STATE);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "input");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "last_enqueue_time");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "period_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "generation");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
                int i6 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow)) {
                        str = null;
                    } else {
                        str = query.getString(columnIndexOrThrow);
                    }
                    WorkInfo$State intToState = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                    if (query.isNull(columnIndexOrThrow3)) {
                        str2 = null;
                    } else {
                        str2 = query.getString(columnIndexOrThrow3);
                    }
                    if (query.isNull(columnIndexOrThrow4)) {
                        str3 = null;
                    } else {
                        str3 = query.getString(columnIndexOrThrow4);
                    }
                    if (query.isNull(columnIndexOrThrow5)) {
                        bArr = null;
                    } else {
                        bArr = query.getBlob(columnIndexOrThrow5);
                    }
                    Data fromByteArray = Data.fromByteArray(bArr);
                    if (query.isNull(columnIndexOrThrow6)) {
                        bArr2 = null;
                    } else {
                        bArr2 = query.getBlob(columnIndexOrThrow6);
                    }
                    Data fromByteArray2 = Data.fromByteArray(bArr2);
                    long j = query.getLong(columnIndexOrThrow7);
                    long j2 = query.getLong(columnIndexOrThrow8);
                    long j3 = query.getLong(columnIndexOrThrow9);
                    int i7 = query.getInt(columnIndexOrThrow10);
                    BackoffPolicy intToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(columnIndexOrThrow11));
                    long j4 = query.getLong(columnIndexOrThrow12);
                    long j5 = query.getLong(columnIndexOrThrow13);
                    int i8 = i6;
                    long j6 = query.getLong(i8);
                    int i9 = columnIndexOrThrow;
                    int i10 = columnIndexOrThrow15;
                    long j7 = query.getLong(i10);
                    columnIndexOrThrow15 = i10;
                    int i11 = columnIndexOrThrow16;
                    if (query.getInt(i11) != 0) {
                        columnIndexOrThrow16 = i11;
                        i = columnIndexOrThrow17;
                        z = true;
                    } else {
                        columnIndexOrThrow16 = i11;
                        i = columnIndexOrThrow17;
                        z = false;
                    }
                    OutOfQuotaPolicy intToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(i));
                    columnIndexOrThrow17 = i;
                    int i12 = columnIndexOrThrow18;
                    int i13 = query.getInt(i12);
                    columnIndexOrThrow18 = i12;
                    int i14 = columnIndexOrThrow19;
                    int i15 = query.getInt(i14);
                    columnIndexOrThrow19 = i14;
                    int i16 = columnIndexOrThrow20;
                    NetworkType intToNetworkType = WorkTypeConverters.intToNetworkType(query.getInt(i16));
                    columnIndexOrThrow20 = i16;
                    int i17 = columnIndexOrThrow21;
                    if (query.getInt(i17) != 0) {
                        columnIndexOrThrow21 = i17;
                        i2 = columnIndexOrThrow22;
                        z2 = true;
                    } else {
                        columnIndexOrThrow21 = i17;
                        i2 = columnIndexOrThrow22;
                        z2 = false;
                    }
                    if (query.getInt(i2) != 0) {
                        columnIndexOrThrow22 = i2;
                        i3 = columnIndexOrThrow23;
                        z3 = true;
                    } else {
                        columnIndexOrThrow22 = i2;
                        i3 = columnIndexOrThrow23;
                        z3 = false;
                    }
                    if (query.getInt(i3) != 0) {
                        columnIndexOrThrow23 = i3;
                        i4 = columnIndexOrThrow24;
                        z4 = true;
                    } else {
                        columnIndexOrThrow23 = i3;
                        i4 = columnIndexOrThrow24;
                        z4 = false;
                    }
                    if (query.getInt(i4) != 0) {
                        columnIndexOrThrow24 = i4;
                        i5 = columnIndexOrThrow25;
                        z5 = true;
                    } else {
                        columnIndexOrThrow24 = i4;
                        i5 = columnIndexOrThrow25;
                        z5 = false;
                    }
                    long j8 = query.getLong(i5);
                    columnIndexOrThrow25 = i5;
                    int i18 = columnIndexOrThrow26;
                    long j9 = query.getLong(i18);
                    columnIndexOrThrow26 = i18;
                    int i19 = columnIndexOrThrow27;
                    if (query.isNull(i19)) {
                        bArr3 = null;
                    } else {
                        bArr3 = query.getBlob(i19);
                    }
                    columnIndexOrThrow27 = i19;
                    arrayList.add(new WorkSpec(str, intToState, str2, str3, fromByteArray, fromByteArray2, j, j2, j3, new Constraints(intToNetworkType, z2, z3, z4, z5, j8, j9, WorkTypeConverters.byteArrayToSetOfTriggers(bArr3)), i7, intToBackoffPolicy, j4, j5, j6, j7, z, intToOutOfQuotaPolicy, i13, i15));
                    columnIndexOrThrow = i9;
                    i6 = i8;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public List getRecentlyCompletedWork(long j) {
        RoomSQLiteQuery roomSQLiteQuery;
        String str;
        String str2;
        String str3;
        byte[] bArr;
        byte[] bArr2;
        boolean z;
        int i;
        boolean z2;
        int i2;
        boolean z3;
        int i3;
        boolean z4;
        int i4;
        boolean z5;
        int i5;
        byte[] bArr3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE last_enqueue_time >= ? AND state IN (2, 3, 5) ORDER BY last_enqueue_time DESC", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, RemoteConfigConstants.ResponseFieldKey.STATE);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "input");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "last_enqueue_time");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "period_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "generation");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
                int i6 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow)) {
                        str = null;
                    } else {
                        str = query.getString(columnIndexOrThrow);
                    }
                    WorkInfo$State intToState = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                    if (query.isNull(columnIndexOrThrow3)) {
                        str2 = null;
                    } else {
                        str2 = query.getString(columnIndexOrThrow3);
                    }
                    if (query.isNull(columnIndexOrThrow4)) {
                        str3 = null;
                    } else {
                        str3 = query.getString(columnIndexOrThrow4);
                    }
                    if (query.isNull(columnIndexOrThrow5)) {
                        bArr = null;
                    } else {
                        bArr = query.getBlob(columnIndexOrThrow5);
                    }
                    Data fromByteArray = Data.fromByteArray(bArr);
                    if (query.isNull(columnIndexOrThrow6)) {
                        bArr2 = null;
                    } else {
                        bArr2 = query.getBlob(columnIndexOrThrow6);
                    }
                    Data fromByteArray2 = Data.fromByteArray(bArr2);
                    long j2 = query.getLong(columnIndexOrThrow7);
                    long j3 = query.getLong(columnIndexOrThrow8);
                    long j4 = query.getLong(columnIndexOrThrow9);
                    int i7 = query.getInt(columnIndexOrThrow10);
                    BackoffPolicy intToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(columnIndexOrThrow11));
                    long j5 = query.getLong(columnIndexOrThrow12);
                    long j6 = query.getLong(columnIndexOrThrow13);
                    int i8 = i6;
                    long j7 = query.getLong(i8);
                    int i9 = columnIndexOrThrow;
                    int i10 = columnIndexOrThrow15;
                    long j8 = query.getLong(i10);
                    columnIndexOrThrow15 = i10;
                    int i11 = columnIndexOrThrow16;
                    if (query.getInt(i11) != 0) {
                        columnIndexOrThrow16 = i11;
                        i = columnIndexOrThrow17;
                        z = true;
                    } else {
                        columnIndexOrThrow16 = i11;
                        i = columnIndexOrThrow17;
                        z = false;
                    }
                    OutOfQuotaPolicy intToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(i));
                    columnIndexOrThrow17 = i;
                    int i12 = columnIndexOrThrow18;
                    int i13 = query.getInt(i12);
                    columnIndexOrThrow18 = i12;
                    int i14 = columnIndexOrThrow19;
                    int i15 = query.getInt(i14);
                    columnIndexOrThrow19 = i14;
                    int i16 = columnIndexOrThrow20;
                    NetworkType intToNetworkType = WorkTypeConverters.intToNetworkType(query.getInt(i16));
                    columnIndexOrThrow20 = i16;
                    int i17 = columnIndexOrThrow21;
                    if (query.getInt(i17) != 0) {
                        columnIndexOrThrow21 = i17;
                        i2 = columnIndexOrThrow22;
                        z2 = true;
                    } else {
                        columnIndexOrThrow21 = i17;
                        i2 = columnIndexOrThrow22;
                        z2 = false;
                    }
                    if (query.getInt(i2) != 0) {
                        columnIndexOrThrow22 = i2;
                        i3 = columnIndexOrThrow23;
                        z3 = true;
                    } else {
                        columnIndexOrThrow22 = i2;
                        i3 = columnIndexOrThrow23;
                        z3 = false;
                    }
                    if (query.getInt(i3) != 0) {
                        columnIndexOrThrow23 = i3;
                        i4 = columnIndexOrThrow24;
                        z4 = true;
                    } else {
                        columnIndexOrThrow23 = i3;
                        i4 = columnIndexOrThrow24;
                        z4 = false;
                    }
                    if (query.getInt(i4) != 0) {
                        columnIndexOrThrow24 = i4;
                        i5 = columnIndexOrThrow25;
                        z5 = true;
                    } else {
                        columnIndexOrThrow24 = i4;
                        i5 = columnIndexOrThrow25;
                        z5 = false;
                    }
                    long j9 = query.getLong(i5);
                    columnIndexOrThrow25 = i5;
                    int i18 = columnIndexOrThrow26;
                    long j10 = query.getLong(i18);
                    columnIndexOrThrow26 = i18;
                    int i19 = columnIndexOrThrow27;
                    if (query.isNull(i19)) {
                        bArr3 = null;
                    } else {
                        bArr3 = query.getBlob(i19);
                    }
                    columnIndexOrThrow27 = i19;
                    arrayList.add(new WorkSpec(str, intToState, str2, str3, fromByteArray, fromByteArray2, j2, j3, j4, new Constraints(intToNetworkType, z2, z3, z4, z5, j9, j10, WorkTypeConverters.byteArrayToSetOfTriggers(bArr3)), i7, intToBackoffPolicy, j5, j6, j7, j8, z, intToOutOfQuotaPolicy, i13, i15));
                    columnIndexOrThrow = i9;
                    i6 = i8;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public static List getRequiredConverters() {
        return Collections.emptyList();
    }
}
