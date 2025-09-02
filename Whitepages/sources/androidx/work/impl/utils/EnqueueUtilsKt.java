package androidx.work.impl.utils;

import androidx.work.impl.model.WorkSpec;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public abstract class EnqueueUtilsKt {
    public static final WorkSpec wrapInConstraintTrackingWorkerIfNeeded(List list, WorkSpec workSpec) {
        Intrinsics.checkNotNullParameter(list, "schedulers");
        Intrinsics.checkNotNullParameter(workSpec, "workSpec");
        return workSpec;
    }
}
