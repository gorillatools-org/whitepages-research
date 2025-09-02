package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

final class AutoValue_CrashlyticsReport_Session_User extends CrashlyticsReport.Session.User {
    private final String identifier;

    private AutoValue_CrashlyticsReport_Session_User(String str) {
        this.identifier = str;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String toString() {
        return "User{identifier=" + this.identifier + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CrashlyticsReport.Session.User) {
            return this.identifier.equals(((CrashlyticsReport.Session.User) obj).getIdentifier());
        }
        return false;
    }

    public int hashCode() {
        return this.identifier.hashCode() ^ 1000003;
    }

    static final class Builder extends CrashlyticsReport.Session.User.Builder {
        private String identifier;

        Builder() {
        }

        public CrashlyticsReport.Session.User.Builder setIdentifier(String str) {
            if (str != null) {
                this.identifier = str;
                return this;
            }
            throw new NullPointerException("Null identifier");
        }

        public CrashlyticsReport.Session.User build() {
            String str = this.identifier;
            if (str != null) {
                return new AutoValue_CrashlyticsReport_Session_User(str);
            }
            throw new IllegalStateException("Missing required properties:" + " identifier");
        }
    }
}
