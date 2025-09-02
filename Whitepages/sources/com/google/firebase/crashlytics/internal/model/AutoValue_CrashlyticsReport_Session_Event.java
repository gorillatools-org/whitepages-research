package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

final class AutoValue_CrashlyticsReport_Session_Event extends CrashlyticsReport.Session.Event {

    /* renamed from: app  reason: collision with root package name */
    private final CrashlyticsReport.Session.Event.Application f8app;
    private final CrashlyticsReport.Session.Event.Device device;
    private final CrashlyticsReport.Session.Event.Log log;
    private final CrashlyticsReport.Session.Event.RolloutsState rollouts;
    private final long timestamp;
    private final String type;

    private AutoValue_CrashlyticsReport_Session_Event(long j, String str, CrashlyticsReport.Session.Event.Application application, CrashlyticsReport.Session.Event.Device device2, CrashlyticsReport.Session.Event.Log log2, CrashlyticsReport.Session.Event.RolloutsState rolloutsState) {
        this.timestamp = j;
        this.type = str;
        this.f8app = application;
        this.device = device2;
        this.log = log2;
        this.rollouts = rolloutsState;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getType() {
        return this.type;
    }

    public CrashlyticsReport.Session.Event.Application getApp() {
        return this.f8app;
    }

    public CrashlyticsReport.Session.Event.Device getDevice() {
        return this.device;
    }

    public CrashlyticsReport.Session.Event.Log getLog() {
        return this.log;
    }

    public CrashlyticsReport.Session.Event.RolloutsState getRollouts() {
        return this.rollouts;
    }

    public String toString() {
        return "Event{timestamp=" + this.timestamp + ", type=" + this.type + ", app=" + this.f8app + ", device=" + this.device + ", log=" + this.log + ", rollouts=" + this.rollouts + "}";
    }

    public boolean equals(Object obj) {
        CrashlyticsReport.Session.Event.Log log2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Event)) {
            return false;
        }
        CrashlyticsReport.Session.Event event = (CrashlyticsReport.Session.Event) obj;
        if (this.timestamp == event.getTimestamp() && this.type.equals(event.getType()) && this.f8app.equals(event.getApp()) && this.device.equals(event.getDevice()) && ((log2 = this.log) != null ? log2.equals(event.getLog()) : event.getLog() == null)) {
            CrashlyticsReport.Session.Event.RolloutsState rolloutsState = this.rollouts;
            if (rolloutsState == null) {
                if (event.getRollouts() == null) {
                    return true;
                }
            } else if (rolloutsState.equals(event.getRollouts())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.timestamp;
        int hashCode = (((((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.type.hashCode()) * 1000003) ^ this.f8app.hashCode()) * 1000003) ^ this.device.hashCode()) * 1000003;
        CrashlyticsReport.Session.Event.Log log2 = this.log;
        int i = 0;
        int hashCode2 = (hashCode ^ (log2 == null ? 0 : log2.hashCode())) * 1000003;
        CrashlyticsReport.Session.Event.RolloutsState rolloutsState = this.rollouts;
        if (rolloutsState != null) {
            i = rolloutsState.hashCode();
        }
        return hashCode2 ^ i;
    }

    public CrashlyticsReport.Session.Event.Builder toBuilder() {
        return new Builder(this);
    }

    static final class Builder extends CrashlyticsReport.Session.Event.Builder {

        /* renamed from: app  reason: collision with root package name */
        private CrashlyticsReport.Session.Event.Application f9app;
        private CrashlyticsReport.Session.Event.Device device;
        private CrashlyticsReport.Session.Event.Log log;
        private CrashlyticsReport.Session.Event.RolloutsState rollouts;
        private byte set$0;
        private long timestamp;
        private String type;

        Builder() {
        }

        private Builder(CrashlyticsReport.Session.Event event) {
            this.timestamp = event.getTimestamp();
            this.type = event.getType();
            this.f9app = event.getApp();
            this.device = event.getDevice();
            this.log = event.getLog();
            this.rollouts = event.getRollouts();
            this.set$0 = 1;
        }

        public CrashlyticsReport.Session.Event.Builder setTimestamp(long j) {
            this.timestamp = j;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public CrashlyticsReport.Session.Event.Builder setType(String str) {
            if (str != null) {
                this.type = str;
                return this;
            }
            throw new NullPointerException("Null type");
        }

        public CrashlyticsReport.Session.Event.Builder setApp(CrashlyticsReport.Session.Event.Application application) {
            if (application != null) {
                this.f9app = application;
                return this;
            }
            throw new NullPointerException("Null app");
        }

        public CrashlyticsReport.Session.Event.Builder setDevice(CrashlyticsReport.Session.Event.Device device2) {
            if (device2 != null) {
                this.device = device2;
                return this;
            }
            throw new NullPointerException("Null device");
        }

        public CrashlyticsReport.Session.Event.Builder setLog(CrashlyticsReport.Session.Event.Log log2) {
            this.log = log2;
            return this;
        }

        public CrashlyticsReport.Session.Event.Builder setRollouts(CrashlyticsReport.Session.Event.RolloutsState rolloutsState) {
            this.rollouts = rolloutsState;
            return this;
        }

        public CrashlyticsReport.Session.Event build() {
            String str;
            CrashlyticsReport.Session.Event.Application application;
            CrashlyticsReport.Session.Event.Device device2;
            if (this.set$0 == 1 && (str = this.type) != null && (application = this.f9app) != null && (device2 = this.device) != null) {
                return new AutoValue_CrashlyticsReport_Session_Event(this.timestamp, str, application, device2, this.log, this.rollouts);
            }
            StringBuilder sb = new StringBuilder();
            if ((1 & this.set$0) == 0) {
                sb.append(" timestamp");
            }
            if (this.type == null) {
                sb.append(" type");
            }
            if (this.f9app == null) {
                sb.append(" app");
            }
            if (this.device == null) {
                sb.append(" device");
            }
            throw new IllegalStateException("Missing required properties:" + sb);
        }
    }
}
