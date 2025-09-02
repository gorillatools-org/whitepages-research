package com.google.android.datatransport.runtime.firebase.transport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class LogSourceMetrics {
    private static final LogSourceMetrics DEFAULT_INSTANCE = new Builder().build();
    private final List log_event_dropped_;
    private final String log_source_;

    LogSourceMetrics(String str, List list) {
        this.log_source_ = str;
        this.log_event_dropped_ = list;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getLogSource() {
        return this.log_source_;
    }

    public List getLogEventDroppedList() {
        return this.log_event_dropped_;
    }

    public static final class Builder {
        private List log_event_dropped_ = new ArrayList();
        private String log_source_ = "";

        Builder() {
        }

        public LogSourceMetrics build() {
            return new LogSourceMetrics(this.log_source_, Collections.unmodifiableList(this.log_event_dropped_));
        }

        public Builder setLogSource(String str) {
            this.log_source_ = str;
            return this;
        }

        public Builder setLogEventDroppedList(List list) {
            this.log_event_dropped_ = list;
            return this;
        }
    }
}
