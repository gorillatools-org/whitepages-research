package androidx.work.impl.model;

import kotlin.jvm.internal.Intrinsics;

public final class Dependency {
    private final String prerequisiteId;
    private final String workSpecId;

    public Dependency(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "workSpecId");
        Intrinsics.checkNotNullParameter(str2, "prerequisiteId");
        this.workSpecId = str;
        this.prerequisiteId = str2;
    }

    public final String getWorkSpecId() {
        return this.workSpecId;
    }

    public final String getPrerequisiteId() {
        return this.prerequisiteId;
    }
}
