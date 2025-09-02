package com.google.firebase.sessions;

import kotlin.jvm.internal.Intrinsics;

public final class ProcessDetails {
    private final int importance;
    private final boolean isDefaultProcess;
    private final int pid;
    private final String processName;

    public static /* synthetic */ ProcessDetails copy$default(ProcessDetails processDetails, String str, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = processDetails.processName;
        }
        if ((i3 & 2) != 0) {
            i = processDetails.pid;
        }
        if ((i3 & 4) != 0) {
            i2 = processDetails.importance;
        }
        if ((i3 & 8) != 0) {
            z = processDetails.isDefaultProcess;
        }
        return processDetails.copy(str, i, i2, z);
    }

    public final String component1() {
        return this.processName;
    }

    public final int component2() {
        return this.pid;
    }

    public final int component3() {
        return this.importance;
    }

    public final boolean component4() {
        return this.isDefaultProcess;
    }

    public final ProcessDetails copy(String str, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "processName");
        return new ProcessDetails(str, i, i2, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProcessDetails)) {
            return false;
        }
        ProcessDetails processDetails = (ProcessDetails) obj;
        return Intrinsics.areEqual((Object) this.processName, (Object) processDetails.processName) && this.pid == processDetails.pid && this.importance == processDetails.importance && this.isDefaultProcess == processDetails.isDefaultProcess;
    }

    public int hashCode() {
        int hashCode = ((((this.processName.hashCode() * 31) + Integer.hashCode(this.pid)) * 31) + Integer.hashCode(this.importance)) * 31;
        boolean z = this.isDefaultProcess;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    public String toString() {
        return "ProcessDetails(processName=" + this.processName + ", pid=" + this.pid + ", importance=" + this.importance + ", isDefaultProcess=" + this.isDefaultProcess + ')';
    }

    public ProcessDetails(String str, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "processName");
        this.processName = str;
        this.pid = i;
        this.importance = i2;
        this.isDefaultProcess = z;
    }

    public final String getProcessName() {
        return this.processName;
    }

    public final int getPid() {
        return this.pid;
    }

    public final int getImportance() {
        return this.importance;
    }

    public final boolean isDefaultProcess() {
        return this.isDefaultProcess;
    }
}
