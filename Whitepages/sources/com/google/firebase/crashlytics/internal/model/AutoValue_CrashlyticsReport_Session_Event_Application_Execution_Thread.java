package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.List;

final class AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread extends CrashlyticsReport.Session.Event.Application.Execution.Thread {
    private final List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> frames;
    private final int importance;
    private final String name;

    private AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread(String str, int i, List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> list) {
        this.name = str;
        this.importance = i;
        this.frames = list;
    }

    public String getName() {
        return this.name;
    }

    public int getImportance() {
        return this.importance;
    }

    public List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> getFrames() {
        return this.frames;
    }

    public String toString() {
        return "Thread{name=" + this.name + ", importance=" + this.importance + ", frames=" + this.frames + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Event.Application.Execution.Thread)) {
            return false;
        }
        CrashlyticsReport.Session.Event.Application.Execution.Thread thread = (CrashlyticsReport.Session.Event.Application.Execution.Thread) obj;
        if (!this.name.equals(thread.getName()) || this.importance != thread.getImportance() || !this.frames.equals(thread.getFrames())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((this.name.hashCode() ^ 1000003) * 1000003) ^ this.importance) * 1000003) ^ this.frames.hashCode();
    }

    static final class Builder extends CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder {
        private List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> frames;
        private int importance;
        private String name;
        private byte set$0;

        Builder() {
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder setName(String str) {
            if (str != null) {
                this.name = str;
                return this;
            }
            throw new NullPointerException("Null name");
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder setImportance(int i) {
            this.importance = i;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder setFrames(List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> list) {
            if (list != null) {
                this.frames = list;
                return this;
            }
            throw new NullPointerException("Null frames");
        }

        public CrashlyticsReport.Session.Event.Application.Execution.Thread build() {
            String str;
            List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> list;
            if (this.set$0 == 1 && (str = this.name) != null && (list = this.frames) != null) {
                return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread(str, this.importance, list);
            }
            StringBuilder sb = new StringBuilder();
            if (this.name == null) {
                sb.append(" name");
            }
            if ((1 & this.set$0) == 0) {
                sb.append(" importance");
            }
            if (this.frames == null) {
                sb.append(" frames");
            }
            throw new IllegalStateException("Missing required properties:" + sb);
        }
    }
}
