package androidx.work.impl.model;

import kotlin.jvm.internal.Intrinsics;

public final class WorkGenerationalId {
    private final int generation;
    private final String workSpecId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WorkGenerationalId)) {
            return false;
        }
        WorkGenerationalId workGenerationalId = (WorkGenerationalId) obj;
        return Intrinsics.areEqual((Object) this.workSpecId, (Object) workGenerationalId.workSpecId) && this.generation == workGenerationalId.generation;
    }

    public int hashCode() {
        return (this.workSpecId.hashCode() * 31) + Integer.hashCode(this.generation);
    }

    public String toString() {
        return "WorkGenerationalId(workSpecId=" + this.workSpecId + ", generation=" + this.generation + ')';
    }

    public WorkGenerationalId(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "workSpecId");
        this.workSpecId = str;
        this.generation = i;
    }

    public final int getGeneration() {
        return this.generation;
    }

    public final String getWorkSpecId() {
        return this.workSpecId;
    }
}
