package androidx.work.impl;

import androidx.work.impl.model.WorkSpec;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class WorkerUpdater$updateWorkImpl$type$1 extends Lambda implements Function1 {
    public static final WorkerUpdater$updateWorkImpl$type$1 INSTANCE = new WorkerUpdater$updateWorkImpl$type$1();

    WorkerUpdater$updateWorkImpl$type$1() {
        super(1);
    }

    public final String invoke(WorkSpec workSpec) {
        Intrinsics.checkNotNullParameter(workSpec, "spec");
        return workSpec.isPeriodic() ? "Periodic" : "OneTime";
    }
}
