package androidx.work.impl.model;

import kotlin.jvm.internal.Intrinsics;

public final class SystemIdInfo {
    private final int generation;
    public final int systemId;
    public final String workSpecId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SystemIdInfo)) {
            return false;
        }
        SystemIdInfo systemIdInfo = (SystemIdInfo) obj;
        return Intrinsics.areEqual((Object) this.workSpecId, (Object) systemIdInfo.workSpecId) && this.generation == systemIdInfo.generation && this.systemId == systemIdInfo.systemId;
    }

    public int hashCode() {
        return (((this.workSpecId.hashCode() * 31) + Integer.hashCode(this.generation)) * 31) + Integer.hashCode(this.systemId);
    }

    public String toString() {
        return "SystemIdInfo(workSpecId=" + this.workSpecId + ", generation=" + this.generation + ", systemId=" + this.systemId + ')';
    }

    public SystemIdInfo(String str, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "workSpecId");
        this.workSpecId = str;
        this.generation = i;
        this.systemId = i2;
    }

    public final int getGeneration() {
        return this.generation;
    }
}
