package androidx.work.impl.model;

import kotlin.jvm.internal.Intrinsics;

public final class WorkTag {
    private final String tag;
    private final String workSpecId;

    public WorkTag(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(str2, "workSpecId");
        this.tag = str;
        this.workSpecId = str2;
    }

    public final String getTag() {
        return this.tag;
    }

    public final String getWorkSpecId() {
        return this.workSpecId;
    }
}
