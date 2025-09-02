package androidx.work.impl.model;

import kotlin.jvm.internal.Intrinsics;

public abstract class SystemIdInfoKt {
    public static final SystemIdInfo systemIdInfo(WorkGenerationalId workGenerationalId, int i) {
        Intrinsics.checkNotNullParameter(workGenerationalId, "generationalId");
        return new SystemIdInfo(workGenerationalId.getWorkSpecId(), workGenerationalId.getGeneration(), i);
    }
}
