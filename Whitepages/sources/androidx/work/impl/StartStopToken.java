package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;
import kotlin.jvm.internal.Intrinsics;

public final class StartStopToken {
    private final WorkGenerationalId id;

    public StartStopToken(WorkGenerationalId workGenerationalId) {
        Intrinsics.checkNotNullParameter(workGenerationalId, "id");
        this.id = workGenerationalId;
    }

    public final WorkGenerationalId getId() {
        return this.id;
    }
}
