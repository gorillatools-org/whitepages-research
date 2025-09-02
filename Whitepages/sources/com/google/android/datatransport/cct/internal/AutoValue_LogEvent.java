package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.LogEvent;
import java.util.Arrays;

final class AutoValue_LogEvent extends LogEvent {
    private final ComplianceData complianceData;
    private final Integer eventCode;
    private final long eventTimeMs;
    private final long eventUptimeMs;
    private final ExperimentIds experimentIds;
    private final NetworkConnectionInfo networkConnectionInfo;
    private final byte[] sourceExtension;
    private final String sourceExtensionJsonProto3;
    private final long timezoneOffsetSeconds;

    private AutoValue_LogEvent(long j, Integer num, ComplianceData complianceData2, long j2, byte[] bArr, String str, long j3, NetworkConnectionInfo networkConnectionInfo2, ExperimentIds experimentIds2) {
        this.eventTimeMs = j;
        this.eventCode = num;
        this.complianceData = complianceData2;
        this.eventUptimeMs = j2;
        this.sourceExtension = bArr;
        this.sourceExtensionJsonProto3 = str;
        this.timezoneOffsetSeconds = j3;
        this.networkConnectionInfo = networkConnectionInfo2;
        this.experimentIds = experimentIds2;
    }

    public long getEventTimeMs() {
        return this.eventTimeMs;
    }

    public Integer getEventCode() {
        return this.eventCode;
    }

    public ComplianceData getComplianceData() {
        return this.complianceData;
    }

    public long getEventUptimeMs() {
        return this.eventUptimeMs;
    }

    public byte[] getSourceExtension() {
        return this.sourceExtension;
    }

    public String getSourceExtensionJsonProto3() {
        return this.sourceExtensionJsonProto3;
    }

    public long getTimezoneOffsetSeconds() {
        return this.timezoneOffsetSeconds;
    }

    public NetworkConnectionInfo getNetworkConnectionInfo() {
        return this.networkConnectionInfo;
    }

    public ExperimentIds getExperimentIds() {
        return this.experimentIds;
    }

    public String toString() {
        return "LogEvent{eventTimeMs=" + this.eventTimeMs + ", eventCode=" + this.eventCode + ", complianceData=" + this.complianceData + ", eventUptimeMs=" + this.eventUptimeMs + ", sourceExtension=" + Arrays.toString(this.sourceExtension) + ", sourceExtensionJsonProto3=" + this.sourceExtensionJsonProto3 + ", timezoneOffsetSeconds=" + this.timezoneOffsetSeconds + ", networkConnectionInfo=" + this.networkConnectionInfo + ", experimentIds=" + this.experimentIds + "}";
    }

    public boolean equals(Object obj) {
        Integer num;
        ComplianceData complianceData2;
        String str;
        NetworkConnectionInfo networkConnectionInfo2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogEvent)) {
            return false;
        }
        LogEvent logEvent = (LogEvent) obj;
        if (this.eventTimeMs == logEvent.getEventTimeMs() && ((num = this.eventCode) != null ? num.equals(logEvent.getEventCode()) : logEvent.getEventCode() == null) && ((complianceData2 = this.complianceData) != null ? complianceData2.equals(logEvent.getComplianceData()) : logEvent.getComplianceData() == null) && this.eventUptimeMs == logEvent.getEventUptimeMs()) {
            if (Arrays.equals(this.sourceExtension, logEvent instanceof AutoValue_LogEvent ? ((AutoValue_LogEvent) logEvent).sourceExtension : logEvent.getSourceExtension()) && ((str = this.sourceExtensionJsonProto3) != null ? str.equals(logEvent.getSourceExtensionJsonProto3()) : logEvent.getSourceExtensionJsonProto3() == null) && this.timezoneOffsetSeconds == logEvent.getTimezoneOffsetSeconds() && ((networkConnectionInfo2 = this.networkConnectionInfo) != null ? networkConnectionInfo2.equals(logEvent.getNetworkConnectionInfo()) : logEvent.getNetworkConnectionInfo() == null)) {
                ExperimentIds experimentIds2 = this.experimentIds;
                if (experimentIds2 == null) {
                    if (logEvent.getExperimentIds() == null) {
                        return true;
                    }
                } else if (experimentIds2.equals(logEvent.getExperimentIds())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.eventTimeMs;
        int i = (((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003;
        Integer num = this.eventCode;
        int i2 = 0;
        int hashCode = (i ^ (num == null ? 0 : num.hashCode())) * 1000003;
        ComplianceData complianceData2 = this.complianceData;
        int hashCode2 = complianceData2 == null ? 0 : complianceData2.hashCode();
        long j2 = this.eventUptimeMs;
        int hashCode3 = (((((hashCode ^ hashCode2) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ Arrays.hashCode(this.sourceExtension)) * 1000003;
        String str = this.sourceExtensionJsonProto3;
        int hashCode4 = str == null ? 0 : str.hashCode();
        long j3 = this.timezoneOffsetSeconds;
        int i3 = (((hashCode3 ^ hashCode4) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003;
        NetworkConnectionInfo networkConnectionInfo2 = this.networkConnectionInfo;
        int hashCode5 = (i3 ^ (networkConnectionInfo2 == null ? 0 : networkConnectionInfo2.hashCode())) * 1000003;
        ExperimentIds experimentIds2 = this.experimentIds;
        if (experimentIds2 != null) {
            i2 = experimentIds2.hashCode();
        }
        return hashCode5 ^ i2;
    }

    static final class Builder extends LogEvent.Builder {
        private ComplianceData complianceData;
        private Integer eventCode;
        private Long eventTimeMs;
        private Long eventUptimeMs;
        private ExperimentIds experimentIds;
        private NetworkConnectionInfo networkConnectionInfo;
        private byte[] sourceExtension;
        private String sourceExtensionJsonProto3;
        private Long timezoneOffsetSeconds;

        Builder() {
        }

        public LogEvent.Builder setEventTimeMs(long j) {
            this.eventTimeMs = Long.valueOf(j);
            return this;
        }

        public LogEvent.Builder setEventCode(Integer num) {
            this.eventCode = num;
            return this;
        }

        public LogEvent.Builder setComplianceData(ComplianceData complianceData2) {
            this.complianceData = complianceData2;
            return this;
        }

        public LogEvent.Builder setEventUptimeMs(long j) {
            this.eventUptimeMs = Long.valueOf(j);
            return this;
        }

        /* access modifiers changed from: package-private */
        public LogEvent.Builder setSourceExtension(byte[] bArr) {
            this.sourceExtension = bArr;
            return this;
        }

        /* access modifiers changed from: package-private */
        public LogEvent.Builder setSourceExtensionJsonProto3(String str) {
            this.sourceExtensionJsonProto3 = str;
            return this;
        }

        public LogEvent.Builder setTimezoneOffsetSeconds(long j) {
            this.timezoneOffsetSeconds = Long.valueOf(j);
            return this;
        }

        public LogEvent.Builder setNetworkConnectionInfo(NetworkConnectionInfo networkConnectionInfo2) {
            this.networkConnectionInfo = networkConnectionInfo2;
            return this;
        }

        public LogEvent.Builder setExperimentIds(ExperimentIds experimentIds2) {
            this.experimentIds = experimentIds2;
            return this;
        }

        public LogEvent build() {
            String str = "";
            if (this.eventTimeMs == null) {
                str = str + " eventTimeMs";
            }
            if (this.eventUptimeMs == null) {
                str = str + " eventUptimeMs";
            }
            if (this.timezoneOffsetSeconds == null) {
                str = str + " timezoneOffsetSeconds";
            }
            if (str.isEmpty()) {
                return new AutoValue_LogEvent(this.eventTimeMs.longValue(), this.eventCode, this.complianceData, this.eventUptimeMs.longValue(), this.sourceExtension, this.sourceExtensionJsonProto3, this.timezoneOffsetSeconds.longValue(), this.networkConnectionInfo, this.experimentIds);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
