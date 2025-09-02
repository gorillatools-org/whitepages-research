package com.google.firebase.sessions.settings;

import kotlin.jvm.internal.Intrinsics;

public final class SessionConfigs {
    private final Integer cacheDuration;
    private final Long cacheUpdatedTime;
    private final Boolean sessionEnabled;
    private final Integer sessionRestartTimeout;
    private final Double sessionSamplingRate;

    public static /* synthetic */ SessionConfigs copy$default(SessionConfigs sessionConfigs, Boolean bool, Double d, Integer num, Integer num2, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = sessionConfigs.sessionEnabled;
        }
        if ((i & 2) != 0) {
            d = sessionConfigs.sessionSamplingRate;
        }
        Double d2 = d;
        if ((i & 4) != 0) {
            num = sessionConfigs.sessionRestartTimeout;
        }
        Integer num3 = num;
        if ((i & 8) != 0) {
            num2 = sessionConfigs.cacheDuration;
        }
        Integer num4 = num2;
        if ((i & 16) != 0) {
            l = sessionConfigs.cacheUpdatedTime;
        }
        return sessionConfigs.copy(bool, d2, num3, num4, l);
    }

    public final Boolean component1() {
        return this.sessionEnabled;
    }

    public final Double component2() {
        return this.sessionSamplingRate;
    }

    public final Integer component3() {
        return this.sessionRestartTimeout;
    }

    public final Integer component4() {
        return this.cacheDuration;
    }

    public final Long component5() {
        return this.cacheUpdatedTime;
    }

    public final SessionConfigs copy(Boolean bool, Double d, Integer num, Integer num2, Long l) {
        return new SessionConfigs(bool, d, num, num2, l);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionConfigs)) {
            return false;
        }
        SessionConfigs sessionConfigs = (SessionConfigs) obj;
        return Intrinsics.areEqual((Object) this.sessionEnabled, (Object) sessionConfigs.sessionEnabled) && Intrinsics.areEqual((Object) this.sessionSamplingRate, (Object) sessionConfigs.sessionSamplingRate) && Intrinsics.areEqual((Object) this.sessionRestartTimeout, (Object) sessionConfigs.sessionRestartTimeout) && Intrinsics.areEqual((Object) this.cacheDuration, (Object) sessionConfigs.cacheDuration) && Intrinsics.areEqual((Object) this.cacheUpdatedTime, (Object) sessionConfigs.cacheUpdatedTime);
    }

    public int hashCode() {
        Boolean bool = this.sessionEnabled;
        int i = 0;
        int hashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Double d = this.sessionSamplingRate;
        int hashCode2 = (hashCode + (d == null ? 0 : d.hashCode())) * 31;
        Integer num = this.sessionRestartTimeout;
        int hashCode3 = (hashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.cacheDuration;
        int hashCode4 = (hashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Long l = this.cacheUpdatedTime;
        if (l != null) {
            i = l.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        return "SessionConfigs(sessionEnabled=" + this.sessionEnabled + ", sessionSamplingRate=" + this.sessionSamplingRate + ", sessionRestartTimeout=" + this.sessionRestartTimeout + ", cacheDuration=" + this.cacheDuration + ", cacheUpdatedTime=" + this.cacheUpdatedTime + ')';
    }

    public SessionConfigs(Boolean bool, Double d, Integer num, Integer num2, Long l) {
        this.sessionEnabled = bool;
        this.sessionSamplingRate = d;
        this.sessionRestartTimeout = num;
        this.cacheDuration = num2;
        this.cacheUpdatedTime = l;
    }

    public final Boolean getSessionEnabled() {
        return this.sessionEnabled;
    }

    public final Double getSessionSamplingRate() {
        return this.sessionSamplingRate;
    }

    public final Integer getSessionRestartTimeout() {
        return this.sessionRestartTimeout;
    }

    public final Integer getCacheDuration() {
        return this.cacheDuration;
    }

    public final Long getCacheUpdatedTime() {
        return this.cacheUpdatedTime;
    }
}
