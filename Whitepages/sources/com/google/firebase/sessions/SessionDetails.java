package com.google.firebase.sessions;

import kotlin.jvm.internal.Intrinsics;

public final class SessionDetails {
    private final String firstSessionId;
    private final String sessionId;
    private final int sessionIndex;
    private final long sessionStartTimestampUs;

    public static /* synthetic */ SessionDetails copy$default(SessionDetails sessionDetails, String str, String str2, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = sessionDetails.sessionId;
        }
        if ((i2 & 2) != 0) {
            str2 = sessionDetails.firstSessionId;
        }
        String str3 = str2;
        if ((i2 & 4) != 0) {
            i = sessionDetails.sessionIndex;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            j = sessionDetails.sessionStartTimestampUs;
        }
        return sessionDetails.copy(str, str3, i3, j);
    }

    public final String component1() {
        return this.sessionId;
    }

    public final String component2() {
        return this.firstSessionId;
    }

    public final int component3() {
        return this.sessionIndex;
    }

    public final long component4() {
        return this.sessionStartTimestampUs;
    }

    public final SessionDetails copy(String str, String str2, int i, long j) {
        Intrinsics.checkNotNullParameter(str, "sessionId");
        Intrinsics.checkNotNullParameter(str2, "firstSessionId");
        return new SessionDetails(str, str2, i, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionDetails)) {
            return false;
        }
        SessionDetails sessionDetails = (SessionDetails) obj;
        return Intrinsics.areEqual((Object) this.sessionId, (Object) sessionDetails.sessionId) && Intrinsics.areEqual((Object) this.firstSessionId, (Object) sessionDetails.firstSessionId) && this.sessionIndex == sessionDetails.sessionIndex && this.sessionStartTimestampUs == sessionDetails.sessionStartTimestampUs;
    }

    public int hashCode() {
        return (((((this.sessionId.hashCode() * 31) + this.firstSessionId.hashCode()) * 31) + Integer.hashCode(this.sessionIndex)) * 31) + Long.hashCode(this.sessionStartTimestampUs);
    }

    public String toString() {
        return "SessionDetails(sessionId=" + this.sessionId + ", firstSessionId=" + this.firstSessionId + ", sessionIndex=" + this.sessionIndex + ", sessionStartTimestampUs=" + this.sessionStartTimestampUs + ')';
    }

    public SessionDetails(String str, String str2, int i, long j) {
        Intrinsics.checkNotNullParameter(str, "sessionId");
        Intrinsics.checkNotNullParameter(str2, "firstSessionId");
        this.sessionId = str;
        this.firstSessionId = str2;
        this.sessionIndex = i;
        this.sessionStartTimestampUs = j;
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final String getFirstSessionId() {
        return this.firstSessionId;
    }

    public final int getSessionIndex() {
        return this.sessionIndex;
    }

    public final long getSessionStartTimestampUs() {
        return this.sessionStartTimestampUs;
    }
}
