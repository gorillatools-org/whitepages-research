package com.google.firebase.crashlytics.internal.metadata;

import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class EventMetadata {
    private final Map<String, String> additionalCustomKeys;
    private final String sessionId;
    private final long timestamp;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public EventMetadata(String str, long j) {
        this(str, j, (Map) null, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "sessionId");
    }

    public static /* synthetic */ EventMetadata copy$default(EventMetadata eventMetadata, String str, long j, Map<String, String> map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = eventMetadata.sessionId;
        }
        if ((i & 2) != 0) {
            j = eventMetadata.timestamp;
        }
        if ((i & 4) != 0) {
            map = eventMetadata.additionalCustomKeys;
        }
        return eventMetadata.copy(str, j, map);
    }

    public final String component1() {
        return this.sessionId;
    }

    public final long component2() {
        return this.timestamp;
    }

    public final Map<String, String> component3() {
        return this.additionalCustomKeys;
    }

    public final EventMetadata copy(String str, long j, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(str, "sessionId");
        Intrinsics.checkNotNullParameter(map, "additionalCustomKeys");
        return new EventMetadata(str, j, map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EventMetadata)) {
            return false;
        }
        EventMetadata eventMetadata = (EventMetadata) obj;
        return Intrinsics.areEqual((Object) this.sessionId, (Object) eventMetadata.sessionId) && this.timestamp == eventMetadata.timestamp && Intrinsics.areEqual((Object) this.additionalCustomKeys, (Object) eventMetadata.additionalCustomKeys);
    }

    public int hashCode() {
        return (((this.sessionId.hashCode() * 31) + Long.hashCode(this.timestamp)) * 31) + this.additionalCustomKeys.hashCode();
    }

    public String toString() {
        return "EventMetadata(sessionId=" + this.sessionId + ", timestamp=" + this.timestamp + ", additionalCustomKeys=" + this.additionalCustomKeys + ')';
    }

    public EventMetadata(String str, long j, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(str, "sessionId");
        Intrinsics.checkNotNullParameter(map, "additionalCustomKeys");
        this.sessionId = str;
        this.timestamp = j;
        this.additionalCustomKeys = map;
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EventMetadata(String str, long j, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, (i & 4) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Map<String, String> getAdditionalCustomKeys() {
        return this.additionalCustomKeys;
    }
}
