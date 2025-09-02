package androidx.work.impl.model;

import kotlin.jvm.internal.Intrinsics;

public final class WorkName {
    private final String name;
    private final String workSpecId;

    public WorkName(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "workSpecId");
        this.name = str;
        this.workSpecId = str2;
    }

    public final String getName() {
        return this.name;
    }

    public final String getWorkSpecId() {
        return this.workSpecId;
    }
}
