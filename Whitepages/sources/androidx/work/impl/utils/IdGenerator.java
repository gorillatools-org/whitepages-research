package androidx.work.impl.utils;

import androidx.work.impl.WorkDatabase;
import java.util.concurrent.Callable;
import kotlin.jvm.internal.Intrinsics;

public final class IdGenerator {
    private final WorkDatabase workDatabase;

    public IdGenerator(WorkDatabase workDatabase2) {
        Intrinsics.checkNotNullParameter(workDatabase2, "workDatabase");
        this.workDatabase = workDatabase2;
    }

    public final int nextJobSchedulerIdWithRange(int i, int i2) {
        Object runInTransaction = this.workDatabase.runInTransaction((Callable) new IdGenerator$$ExternalSyntheticLambda1(this, i, i2));
        Intrinsics.checkNotNullExpressionValue(runInTransaction, "workDatabase.runInTransa…            id\n        })");
        return ((Number) runInTransaction).intValue();
    }

    /* access modifiers changed from: private */
    public static final Integer nextJobSchedulerIdWithRange$lambda$0(IdGenerator idGenerator, int i, int i2) {
        Intrinsics.checkNotNullParameter(idGenerator, "this$0");
        int access$nextId = IdGeneratorKt.nextId(idGenerator.workDatabase, "next_job_scheduler_id");
        if (i > access$nextId || access$nextId > i2) {
            IdGeneratorKt.updatePreference(idGenerator.workDatabase, "next_job_scheduler_id", i + 1);
        } else {
            i = access$nextId;
        }
        return Integer.valueOf(i);
    }

    public final int nextAlarmManagerId() {
        Object runInTransaction = this.workDatabase.runInTransaction((Callable) new IdGenerator$$ExternalSyntheticLambda0(this));
        Intrinsics.checkNotNullExpressionValue(runInTransaction, "workDatabase.runInTransa…ANAGER_ID_KEY)\n        })");
        return ((Number) runInTransaction).intValue();
    }

    /* access modifiers changed from: private */
    public static final Integer nextAlarmManagerId$lambda$1(IdGenerator idGenerator) {
        Intrinsics.checkNotNullParameter(idGenerator, "this$0");
        return Integer.valueOf(IdGeneratorKt.nextId(idGenerator.workDatabase, "next_alarm_manager_id"));
    }
}
