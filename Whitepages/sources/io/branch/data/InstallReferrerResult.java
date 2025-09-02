package io.branch.data;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class InstallReferrerResult {
    private String appStore;
    private boolean isClickThrough;
    private long latestClickTimestamp;
    private long latestInstallTimestamp;
    private String latestRawReferrer;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InstallReferrerResult)) {
            return false;
        }
        InstallReferrerResult installReferrerResult = (InstallReferrerResult) obj;
        return Intrinsics.areEqual((Object) this.appStore, (Object) installReferrerResult.appStore) && this.latestInstallTimestamp == installReferrerResult.latestInstallTimestamp && Intrinsics.areEqual((Object) this.latestRawReferrer, (Object) installReferrerResult.latestRawReferrer) && this.latestClickTimestamp == installReferrerResult.latestClickTimestamp && this.isClickThrough == installReferrerResult.isClickThrough;
    }

    public int hashCode() {
        String str = this.appStore;
        int i = 0;
        int hashCode = (((str == null ? 0 : str.hashCode()) * 31) + Long.hashCode(this.latestInstallTimestamp)) * 31;
        String str2 = this.latestRawReferrer;
        if (str2 != null) {
            i = str2.hashCode();
        }
        int hashCode2 = (((hashCode + i) * 31) + Long.hashCode(this.latestClickTimestamp)) * 31;
        boolean z = this.isClickThrough;
        if (z) {
            z = true;
        }
        return hashCode2 + (z ? 1 : 0);
    }

    public String toString() {
        return "InstallReferrerResult(appStore=" + this.appStore + ", latestInstallTimestamp=" + this.latestInstallTimestamp + ", latestRawReferrer=" + this.latestRawReferrer + ", latestClickTimestamp=" + this.latestClickTimestamp + ", isClickThrough=" + this.isClickThrough + ')';
    }

    public InstallReferrerResult(String str, long j, String str2, long j2, boolean z) {
        this.appStore = str;
        this.latestInstallTimestamp = j;
        this.latestRawReferrer = str2;
        this.latestClickTimestamp = j2;
        this.isClickThrough = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ InstallReferrerResult(String str, long j, String str2, long j2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, str2, j2, (i & 16) != 0 ? true : z);
    }

    public final String getAppStore() {
        return this.appStore;
    }

    public final long getLatestInstallTimestamp() {
        return this.latestInstallTimestamp;
    }

    public final String getLatestRawReferrer() {
        return this.latestRawReferrer;
    }

    public final long getLatestClickTimestamp() {
        return this.latestClickTimestamp;
    }

    public final boolean isClickThrough() {
        return this.isClickThrough;
    }
}
