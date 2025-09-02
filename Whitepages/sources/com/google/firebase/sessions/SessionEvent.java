package com.google.firebase.sessions;

import com.google.firebase.encoders.annotations.Encodable;
import kotlin.jvm.internal.Intrinsics;

@Encodable
public final class SessionEvent {
    private final ApplicationInfo applicationInfo;
    private final EventType eventType;
    private final SessionInfo sessionData;

    public static /* synthetic */ SessionEvent copy$default(SessionEvent sessionEvent, EventType eventType2, SessionInfo sessionInfo, ApplicationInfo applicationInfo2, int i, Object obj) {
        if ((i & 1) != 0) {
            eventType2 = sessionEvent.eventType;
        }
        if ((i & 2) != 0) {
            sessionInfo = sessionEvent.sessionData;
        }
        if ((i & 4) != 0) {
            applicationInfo2 = sessionEvent.applicationInfo;
        }
        return sessionEvent.copy(eventType2, sessionInfo, applicationInfo2);
    }

    public final EventType component1() {
        return this.eventType;
    }

    public final SessionInfo component2() {
        return this.sessionData;
    }

    public final ApplicationInfo component3() {
        return this.applicationInfo;
    }

    public final SessionEvent copy(EventType eventType2, SessionInfo sessionInfo, ApplicationInfo applicationInfo2) {
        Intrinsics.checkNotNullParameter(eventType2, "eventType");
        Intrinsics.checkNotNullParameter(sessionInfo, "sessionData");
        Intrinsics.checkNotNullParameter(applicationInfo2, "applicationInfo");
        return new SessionEvent(eventType2, sessionInfo, applicationInfo2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionEvent)) {
            return false;
        }
        SessionEvent sessionEvent = (SessionEvent) obj;
        return this.eventType == sessionEvent.eventType && Intrinsics.areEqual((Object) this.sessionData, (Object) sessionEvent.sessionData) && Intrinsics.areEqual((Object) this.applicationInfo, (Object) sessionEvent.applicationInfo);
    }

    public int hashCode() {
        return (((this.eventType.hashCode() * 31) + this.sessionData.hashCode()) * 31) + this.applicationInfo.hashCode();
    }

    public String toString() {
        return "SessionEvent(eventType=" + this.eventType + ", sessionData=" + this.sessionData + ", applicationInfo=" + this.applicationInfo + ')';
    }

    public SessionEvent(EventType eventType2, SessionInfo sessionInfo, ApplicationInfo applicationInfo2) {
        Intrinsics.checkNotNullParameter(eventType2, "eventType");
        Intrinsics.checkNotNullParameter(sessionInfo, "sessionData");
        Intrinsics.checkNotNullParameter(applicationInfo2, "applicationInfo");
        this.eventType = eventType2;
        this.sessionData = sessionInfo;
        this.applicationInfo = applicationInfo2;
    }

    public final EventType getEventType() {
        return this.eventType;
    }

    public final SessionInfo getSessionData() {
        return this.sessionData;
    }

    public final ApplicationInfo getApplicationInfo() {
        return this.applicationInfo;
    }
}
