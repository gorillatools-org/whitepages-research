package com.google.firebase.sessions.api;

import kotlin.jvm.internal.Intrinsics;

public interface SessionSubscriber {

    public enum Name {
        CRASHLYTICS,
        PERFORMANCE,
        MATT_SAYS_HI
    }

    Name getSessionSubscriberName();

    boolean isDataCollectionEnabled();

    void onSessionChanged(SessionDetails sessionDetails);

    public static final class SessionDetails {
        private final String sessionId;

        public static /* synthetic */ SessionDetails copy$default(SessionDetails sessionDetails, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = sessionDetails.sessionId;
            }
            return sessionDetails.copy(str);
        }

        public final String component1() {
            return this.sessionId;
        }

        public final SessionDetails copy(String str) {
            Intrinsics.checkNotNullParameter(str, "sessionId");
            return new SessionDetails(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof SessionDetails) && Intrinsics.areEqual((Object) this.sessionId, (Object) ((SessionDetails) obj).sessionId);
        }

        public int hashCode() {
            return this.sessionId.hashCode();
        }

        public String toString() {
            return "SessionDetails(sessionId=" + this.sessionId + ')';
        }

        public SessionDetails(String str) {
            Intrinsics.checkNotNullParameter(str, "sessionId");
            this.sessionId = str;
        }

        public final String getSessionId() {
            return this.sessionId;
        }
    }
}
