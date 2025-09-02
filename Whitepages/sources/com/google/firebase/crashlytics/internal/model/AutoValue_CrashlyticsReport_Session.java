package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.annotations.Encodable;
import java.util.List;

final class AutoValue_CrashlyticsReport_Session extends CrashlyticsReport.Session {

    /* renamed from: app  reason: collision with root package name */
    private final CrashlyticsReport.Session.Application f6app;
    private final String appQualitySessionId;
    private final boolean crashed;
    private final CrashlyticsReport.Session.Device device;
    private final Long endedAt;
    private final List<CrashlyticsReport.Session.Event> events;
    private final String generator;
    private final int generatorType;
    private final String identifier;
    private final CrashlyticsReport.Session.OperatingSystem os;
    private final long startedAt;
    private final CrashlyticsReport.Session.User user;

    private AutoValue_CrashlyticsReport_Session(String str, String str2, String str3, long j, Long l, boolean z, CrashlyticsReport.Session.Application application, CrashlyticsReport.Session.User user2, CrashlyticsReport.Session.OperatingSystem operatingSystem, CrashlyticsReport.Session.Device device2, List<CrashlyticsReport.Session.Event> list, int i) {
        this.generator = str;
        this.identifier = str2;
        this.appQualitySessionId = str3;
        this.startedAt = j;
        this.endedAt = l;
        this.crashed = z;
        this.f6app = application;
        this.user = user2;
        this.os = operatingSystem;
        this.device = device2;
        this.events = list;
        this.generatorType = i;
    }

    public String getGenerator() {
        return this.generator;
    }

    @Encodable.Ignore
    public String getIdentifier() {
        return this.identifier;
    }

    public String getAppQualitySessionId() {
        return this.appQualitySessionId;
    }

    public long getStartedAt() {
        return this.startedAt;
    }

    public Long getEndedAt() {
        return this.endedAt;
    }

    public boolean isCrashed() {
        return this.crashed;
    }

    public CrashlyticsReport.Session.Application getApp() {
        return this.f6app;
    }

    public CrashlyticsReport.Session.User getUser() {
        return this.user;
    }

    public CrashlyticsReport.Session.OperatingSystem getOs() {
        return this.os;
    }

    public CrashlyticsReport.Session.Device getDevice() {
        return this.device;
    }

    public List<CrashlyticsReport.Session.Event> getEvents() {
        return this.events;
    }

    public int getGeneratorType() {
        return this.generatorType;
    }

    public String toString() {
        return "Session{generator=" + this.generator + ", identifier=" + this.identifier + ", appQualitySessionId=" + this.appQualitySessionId + ", startedAt=" + this.startedAt + ", endedAt=" + this.endedAt + ", crashed=" + this.crashed + ", app=" + this.f6app + ", user=" + this.user + ", os=" + this.os + ", device=" + this.device + ", events=" + this.events + ", generatorType=" + this.generatorType + "}";
    }

    public boolean equals(Object obj) {
        String str;
        Long l;
        CrashlyticsReport.Session.User user2;
        CrashlyticsReport.Session.OperatingSystem operatingSystem;
        CrashlyticsReport.Session.Device device2;
        List<CrashlyticsReport.Session.Event> list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session)) {
            return false;
        }
        CrashlyticsReport.Session session = (CrashlyticsReport.Session) obj;
        if (!this.generator.equals(session.getGenerator()) || !this.identifier.equals(session.getIdentifier()) || ((str = this.appQualitySessionId) != null ? !str.equals(session.getAppQualitySessionId()) : session.getAppQualitySessionId() != null) || this.startedAt != session.getStartedAt() || ((l = this.endedAt) != null ? !l.equals(session.getEndedAt()) : session.getEndedAt() != null) || this.crashed != session.isCrashed() || !this.f6app.equals(session.getApp()) || ((user2 = this.user) != null ? !user2.equals(session.getUser()) : session.getUser() != null) || ((operatingSystem = this.os) != null ? !operatingSystem.equals(session.getOs()) : session.getOs() != null) || ((device2 = this.device) != null ? !device2.equals(session.getDevice()) : session.getDevice() != null) || ((list = this.events) != null ? !list.equals(session.getEvents()) : session.getEvents() != null) || this.generatorType != session.getGeneratorType()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (((this.generator.hashCode() ^ 1000003) * 1000003) ^ this.identifier.hashCode()) * 1000003;
        String str = this.appQualitySessionId;
        int i = 0;
        int hashCode2 = str == null ? 0 : str.hashCode();
        long j = this.startedAt;
        int i2 = (((hashCode ^ hashCode2) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        Long l = this.endedAt;
        int hashCode3 = (((((i2 ^ (l == null ? 0 : l.hashCode())) * 1000003) ^ (this.crashed ? 1231 : 1237)) * 1000003) ^ this.f6app.hashCode()) * 1000003;
        CrashlyticsReport.Session.User user2 = this.user;
        int hashCode4 = (hashCode3 ^ (user2 == null ? 0 : user2.hashCode())) * 1000003;
        CrashlyticsReport.Session.OperatingSystem operatingSystem = this.os;
        int hashCode5 = (hashCode4 ^ (operatingSystem == null ? 0 : operatingSystem.hashCode())) * 1000003;
        CrashlyticsReport.Session.Device device2 = this.device;
        int hashCode6 = (hashCode5 ^ (device2 == null ? 0 : device2.hashCode())) * 1000003;
        List<CrashlyticsReport.Session.Event> list = this.events;
        if (list != null) {
            i = list.hashCode();
        }
        return ((hashCode6 ^ i) * 1000003) ^ this.generatorType;
    }

    public CrashlyticsReport.Session.Builder toBuilder() {
        return new Builder(this);
    }

    static final class Builder extends CrashlyticsReport.Session.Builder {

        /* renamed from: app  reason: collision with root package name */
        private CrashlyticsReport.Session.Application f7app;
        private String appQualitySessionId;
        private boolean crashed;
        private CrashlyticsReport.Session.Device device;
        private Long endedAt;
        private List<CrashlyticsReport.Session.Event> events;
        private String generator;
        private int generatorType;
        private String identifier;
        private CrashlyticsReport.Session.OperatingSystem os;
        private byte set$0;
        private long startedAt;
        private CrashlyticsReport.Session.User user;

        Builder() {
        }

        private Builder(CrashlyticsReport.Session session) {
            this.generator = session.getGenerator();
            this.identifier = session.getIdentifier();
            this.appQualitySessionId = session.getAppQualitySessionId();
            this.startedAt = session.getStartedAt();
            this.endedAt = session.getEndedAt();
            this.crashed = session.isCrashed();
            this.f7app = session.getApp();
            this.user = session.getUser();
            this.os = session.getOs();
            this.device = session.getDevice();
            this.events = session.getEvents();
            this.generatorType = session.getGeneratorType();
            this.set$0 = 7;
        }

        public CrashlyticsReport.Session.Builder setGenerator(String str) {
            if (str != null) {
                this.generator = str;
                return this;
            }
            throw new NullPointerException("Null generator");
        }

        public CrashlyticsReport.Session.Builder setIdentifier(String str) {
            if (str != null) {
                this.identifier = str;
                return this;
            }
            throw new NullPointerException("Null identifier");
        }

        public CrashlyticsReport.Session.Builder setAppQualitySessionId(String str) {
            this.appQualitySessionId = str;
            return this;
        }

        public CrashlyticsReport.Session.Builder setStartedAt(long j) {
            this.startedAt = j;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public CrashlyticsReport.Session.Builder setEndedAt(Long l) {
            this.endedAt = l;
            return this;
        }

        public CrashlyticsReport.Session.Builder setCrashed(boolean z) {
            this.crashed = z;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        public CrashlyticsReport.Session.Builder setApp(CrashlyticsReport.Session.Application application) {
            if (application != null) {
                this.f7app = application;
                return this;
            }
            throw new NullPointerException("Null app");
        }

        public CrashlyticsReport.Session.Builder setUser(CrashlyticsReport.Session.User user2) {
            this.user = user2;
            return this;
        }

        public CrashlyticsReport.Session.Builder setOs(CrashlyticsReport.Session.OperatingSystem operatingSystem) {
            this.os = operatingSystem;
            return this;
        }

        public CrashlyticsReport.Session.Builder setDevice(CrashlyticsReport.Session.Device device2) {
            this.device = device2;
            return this;
        }

        public CrashlyticsReport.Session.Builder setEvents(List<CrashlyticsReport.Session.Event> list) {
            this.events = list;
            return this;
        }

        public CrashlyticsReport.Session.Builder setGeneratorType(int i) {
            this.generatorType = i;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        public CrashlyticsReport.Session build() {
            String str;
            String str2;
            CrashlyticsReport.Session.Application application;
            if (this.set$0 == 7 && (str = this.generator) != null && (str2 = this.identifier) != null && (application = this.f7app) != null) {
                return new AutoValue_CrashlyticsReport_Session(str, str2, this.appQualitySessionId, this.startedAt, this.endedAt, this.crashed, application, this.user, this.os, this.device, this.events, this.generatorType);
            }
            StringBuilder sb = new StringBuilder();
            if (this.generator == null) {
                sb.append(" generator");
            }
            if (this.identifier == null) {
                sb.append(" identifier");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" startedAt");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" crashed");
            }
            if (this.f7app == null) {
                sb.append(" app");
            }
            if ((this.set$0 & 4) == 0) {
                sb.append(" generatorType");
            }
            throw new IllegalStateException("Missing required properties:" + sb);
        }
    }
}
