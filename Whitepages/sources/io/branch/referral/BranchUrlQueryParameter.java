package io.branch.referral;

import java.util.Date;
import kotlin.jvm.internal.Intrinsics;

public final class BranchUrlQueryParameter {
    private boolean isDeepLink;
    private String name;
    private Date timestamp;
    private long validityWindow;
    private String value;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BranchUrlQueryParameter)) {
            return false;
        }
        BranchUrlQueryParameter branchUrlQueryParameter = (BranchUrlQueryParameter) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) branchUrlQueryParameter.name) && Intrinsics.areEqual((Object) this.value, (Object) branchUrlQueryParameter.value) && Intrinsics.areEqual((Object) this.timestamp, (Object) branchUrlQueryParameter.timestamp) && this.isDeepLink == branchUrlQueryParameter.isDeepLink && this.validityWindow == branchUrlQueryParameter.validityWindow;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.value;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Date date = this.timestamp;
        if (date != null) {
            i = date.hashCode();
        }
        int i2 = (hashCode2 + i) * 31;
        boolean z = this.isDeepLink;
        if (z) {
            z = true;
        }
        return ((i2 + (z ? 1 : 0)) * 31) + Long.hashCode(this.validityWindow);
    }

    public String toString() {
        return "BranchUrlQueryParameter(name=" + this.name + ", value=" + this.value + ", timestamp=" + this.timestamp + ", isDeepLink=" + this.isDeepLink + ", validityWindow=" + this.validityWindow + ')';
    }

    public BranchUrlQueryParameter(String str, String str2, Date date, boolean z, long j) {
        this.name = str;
        this.value = str2;
        this.timestamp = date;
        this.isDeepLink = z;
        this.validityWindow = j;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ BranchUrlQueryParameter(java.lang.String r4, java.lang.String r5, java.util.Date r6, boolean r7, long r8, int r10, kotlin.jvm.internal.DefaultConstructorMarker r11) {
        /*
            r3 = this;
            r11 = r10 & 1
            r0 = 0
            if (r11 == 0) goto L_0x0007
            r11 = r0
            goto L_0x0008
        L_0x0007:
            r11 = r4
        L_0x0008:
            r4 = r10 & 2
            if (r4 == 0) goto L_0x000e
            r1 = r0
            goto L_0x000f
        L_0x000e:
            r1 = r5
        L_0x000f:
            r4 = r10 & 4
            if (r4 == 0) goto L_0x0014
            goto L_0x0015
        L_0x0014:
            r0 = r6
        L_0x0015:
            r4 = r10 & 8
            if (r4 == 0) goto L_0x001a
            r7 = 0
        L_0x001a:
            r2 = r7
            r4 = r10 & 16
            if (r4 == 0) goto L_0x0021
            r8 = 0
        L_0x0021:
            r9 = r8
            r4 = r3
            r5 = r11
            r6 = r1
            r7 = r0
            r8 = r2
            r4.<init>(r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.BranchUrlQueryParameter.<init>(java.lang.String, java.lang.String, java.util.Date, boolean, long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getValue() {
        return this.value;
    }

    public final void setValue(String str) {
        this.value = str;
    }

    public final Date getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(Date date) {
        this.timestamp = date;
    }

    public final boolean isDeepLink() {
        return this.isDeepLink;
    }

    public final void setDeepLink(boolean z) {
        this.isDeepLink = z;
    }

    public final long getValidityWindow() {
        return this.validityWindow;
    }

    public final void setValidityWindow(long j) {
        this.validityWindow = j;
    }
}
