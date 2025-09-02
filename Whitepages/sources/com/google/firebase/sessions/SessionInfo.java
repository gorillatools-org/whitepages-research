package com.google.firebase.sessions;

import kotlin.jvm.internal.Intrinsics;

public final class SessionInfo {
    private final DataCollectionStatus dataCollectionStatus;
    private final long eventTimestampUs;
    private final String firebaseAuthenticationToken;
    private final String firebaseInstallationId;
    private final String firstSessionId;
    private final String sessionId;
    private final int sessionIndex;

    public static /* synthetic */ SessionInfo copy$default(SessionInfo sessionInfo, String str, String str2, int i, long j, DataCollectionStatus dataCollectionStatus2, String str3, String str4, int i2, Object obj) {
        SessionInfo sessionInfo2 = sessionInfo;
        return sessionInfo.copy((i2 & 1) != 0 ? sessionInfo2.sessionId : str, (i2 & 2) != 0 ? sessionInfo2.firstSessionId : str2, (i2 & 4) != 0 ? sessionInfo2.sessionIndex : i, (i2 & 8) != 0 ? sessionInfo2.eventTimestampUs : j, (i2 & 16) != 0 ? sessionInfo2.dataCollectionStatus : dataCollectionStatus2, (i2 & 32) != 0 ? sessionInfo2.firebaseInstallationId : str3, (i2 & 64) != 0 ? sessionInfo2.firebaseAuthenticationToken : str4);
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
        return this.eventTimestampUs;
    }

    public final DataCollectionStatus component5() {
        return this.dataCollectionStatus;
    }

    public final String component6() {
        return this.firebaseInstallationId;
    }

    public final String component7() {
        return this.firebaseAuthenticationToken;
    }

    public final SessionInfo copy(String str, String str2, int i, long j, DataCollectionStatus dataCollectionStatus2, String str3, String str4) {
        Intrinsics.checkNotNullParameter(str, "sessionId");
        Intrinsics.checkNotNullParameter(str2, "firstSessionId");
        DataCollectionStatus dataCollectionStatus3 = dataCollectionStatus2;
        Intrinsics.checkNotNullParameter(dataCollectionStatus3, "dataCollectionStatus");
        String str5 = str3;
        Intrinsics.checkNotNullParameter(str5, "firebaseInstallationId");
        String str6 = str4;
        Intrinsics.checkNotNullParameter(str6, "firebaseAuthenticationToken");
        return new SessionInfo(str, str2, i, j, dataCollectionStatus3, str5, str6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionInfo)) {
            return false;
        }
        SessionInfo sessionInfo = (SessionInfo) obj;
        return Intrinsics.areEqual((Object) this.sessionId, (Object) sessionInfo.sessionId) && Intrinsics.areEqual((Object) this.firstSessionId, (Object) sessionInfo.firstSessionId) && this.sessionIndex == sessionInfo.sessionIndex && this.eventTimestampUs == sessionInfo.eventTimestampUs && Intrinsics.areEqual((Object) this.dataCollectionStatus, (Object) sessionInfo.dataCollectionStatus) && Intrinsics.areEqual((Object) this.firebaseInstallationId, (Object) sessionInfo.firebaseInstallationId) && Intrinsics.areEqual((Object) this.firebaseAuthenticationToken, (Object) sessionInfo.firebaseAuthenticationToken);
    }

    public int hashCode() {
        return (((((((((((this.sessionId.hashCode() * 31) + this.firstSessionId.hashCode()) * 31) + Integer.hashCode(this.sessionIndex)) * 31) + Long.hashCode(this.eventTimestampUs)) * 31) + this.dataCollectionStatus.hashCode()) * 31) + this.firebaseInstallationId.hashCode()) * 31) + this.firebaseAuthenticationToken.hashCode();
    }

    public String toString() {
        return "SessionInfo(sessionId=" + this.sessionId + ", firstSessionId=" + this.firstSessionId + ", sessionIndex=" + this.sessionIndex + ", eventTimestampUs=" + this.eventTimestampUs + ", dataCollectionStatus=" + this.dataCollectionStatus + ", firebaseInstallationId=" + this.firebaseInstallationId + ", firebaseAuthenticationToken=" + this.firebaseAuthenticationToken + ')';
    }

    public SessionInfo(String str, String str2, int i, long j, DataCollectionStatus dataCollectionStatus2, String str3, String str4) {
        Intrinsics.checkNotNullParameter(str, "sessionId");
        Intrinsics.checkNotNullParameter(str2, "firstSessionId");
        Intrinsics.checkNotNullParameter(dataCollectionStatus2, "dataCollectionStatus");
        Intrinsics.checkNotNullParameter(str3, "firebaseInstallationId");
        Intrinsics.checkNotNullParameter(str4, "firebaseAuthenticationToken");
        this.sessionId = str;
        this.firstSessionId = str2;
        this.sessionIndex = i;
        this.eventTimestampUs = j;
        this.dataCollectionStatus = dataCollectionStatus2;
        this.firebaseInstallationId = str3;
        this.firebaseAuthenticationToken = str4;
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

    public final long getEventTimestampUs() {
        return this.eventTimestampUs;
    }

    public final DataCollectionStatus getDataCollectionStatus() {
        return this.dataCollectionStatus;
    }

    public final String getFirebaseInstallationId() {
        return this.firebaseInstallationId;
    }

    public final String getFirebaseAuthenticationToken() {
        return this.firebaseAuthenticationToken;
    }
}
