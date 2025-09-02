package com.facebook.appevents.internal;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.facebook.FacebookSdk;
import java.util.UUID;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SessionInfo {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private Long diskRestoreTime;
    /* access modifiers changed from: private */
    public int interruptionCount;
    private UUID sessionId;
    private Long sessionLastEventTime;
    private final Long sessionStartTime;
    private SourceApplicationInfo sourceApplicationInfo;

    public SessionInfo(Long l, Long l2, UUID uuid) {
        Intrinsics.checkNotNullParameter(uuid, "sessionId");
        this.sessionStartTime = l;
        this.sessionLastEventTime = l2;
        this.sessionId = uuid;
    }

    public final Long getSessionLastEventTime() {
        return this.sessionLastEventTime;
    }

    public final void setSessionLastEventTime(Long l) {
        this.sessionLastEventTime = l;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SessionInfo(java.lang.Long r1, java.lang.Long r2, java.util.UUID r3, int r4, kotlin.jvm.internal.DefaultConstructorMarker r5) {
        /*
            r0 = this;
            r4 = r4 & 4
            if (r4 == 0) goto L_0x000d
            java.util.UUID r3 = java.util.UUID.randomUUID()
            java.lang.String r4 = "randomUUID()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
        L_0x000d:
            r0.<init>(r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.internal.SessionInfo.<init>(java.lang.Long, java.lang.Long, java.util.UUID, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final UUID getSessionId() {
        return this.sessionId;
    }

    public final void setSessionId(UUID uuid) {
        Intrinsics.checkNotNullParameter(uuid, "<set-?>");
        this.sessionId = uuid;
    }

    public final int getInterruptionCount() {
        return this.interruptionCount;
    }

    public final void setDiskRestoreTime(Long l) {
        this.diskRestoreTime = l;
    }

    public final Long getDiskRestoreTime() {
        Long l = this.diskRestoreTime;
        if (l == null) {
            return 0L;
        }
        return l;
    }

    public final SourceApplicationInfo getSourceApplicationInfo() {
        return this.sourceApplicationInfo;
    }

    public final void setSourceApplicationInfo(SourceApplicationInfo sourceApplicationInfo2) {
        this.sourceApplicationInfo = sourceApplicationInfo2;
    }

    public final void incrementInterruptionCount() {
        this.interruptionCount++;
    }

    public final long getSessionLength() {
        Long l;
        if (this.sessionStartTime == null || (l = this.sessionLastEventTime) == null) {
            return 0;
        }
        if (l != null) {
            return l.longValue() - this.sessionStartTime.longValue();
        }
        throw new IllegalStateException("Required value was null.");
    }

    public final void writeSessionToDisk() {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
        Long l = this.sessionStartTime;
        long j = 0;
        edit.putLong("com.facebook.appevents.SessionInfo.sessionStartTime", l != null ? l.longValue() : 0);
        Long l2 = this.sessionLastEventTime;
        if (l2 != null) {
            j = l2.longValue();
        }
        edit.putLong("com.facebook.appevents.SessionInfo.sessionEndTime", j);
        edit.putInt("com.facebook.appevents.SessionInfo.interruptionCount", this.interruptionCount);
        edit.putString("com.facebook.appevents.SessionInfo.sessionId", this.sessionId.toString());
        edit.apply();
        SourceApplicationInfo sourceApplicationInfo2 = this.sourceApplicationInfo;
        if (sourceApplicationInfo2 != null && sourceApplicationInfo2 != null) {
            sourceApplicationInfo2.writeSourceApplicationInfoToDisk();
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SessionInfo getStoredSessionInfo() {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext());
            long j = defaultSharedPreferences.getLong("com.facebook.appevents.SessionInfo.sessionStartTime", 0);
            long j2 = defaultSharedPreferences.getLong("com.facebook.appevents.SessionInfo.sessionEndTime", 0);
            String string = defaultSharedPreferences.getString("com.facebook.appevents.SessionInfo.sessionId", (String) null);
            if (j == 0 || j2 == 0 || string == null) {
                return null;
            }
            SessionInfo sessionInfo = new SessionInfo(Long.valueOf(j), Long.valueOf(j2), (UUID) null, 4, (DefaultConstructorMarker) null);
            sessionInfo.interruptionCount = defaultSharedPreferences.getInt("com.facebook.appevents.SessionInfo.interruptionCount", 0);
            sessionInfo.setSourceApplicationInfo(SourceApplicationInfo.Companion.getStoredSourceApplicatioInfo());
            sessionInfo.setDiskRestoreTime(Long.valueOf(System.currentTimeMillis()));
            UUID fromString = UUID.fromString(string);
            Intrinsics.checkNotNullExpressionValue(fromString, "fromString(sessionIDStr)");
            sessionInfo.setSessionId(fromString);
            return sessionInfo;
        }

        public final void clearSavedSessionFromDisk() {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
            edit.remove("com.facebook.appevents.SessionInfo.sessionStartTime");
            edit.remove("com.facebook.appevents.SessionInfo.sessionEndTime");
            edit.remove("com.facebook.appevents.SessionInfo.interruptionCount");
            edit.remove("com.facebook.appevents.SessionInfo.sessionId");
            edit.apply();
            SourceApplicationInfo.Companion.clearSavedSourceApplicationInfoFromDisk();
        }
    }
}
