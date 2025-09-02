package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

final class AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment_RolloutVariant extends CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant {
    private final String rolloutId;
    private final String variantId;

    private AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment_RolloutVariant(String str, String str2) {
        this.rolloutId = str;
        this.variantId = str2;
    }

    public String getRolloutId() {
        return this.rolloutId;
    }

    public String getVariantId() {
        return this.variantId;
    }

    public String toString() {
        return "RolloutVariant{rolloutId=" + this.rolloutId + ", variantId=" + this.variantId + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant)) {
            return false;
        }
        CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant rolloutVariant = (CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant) obj;
        if (!this.rolloutId.equals(rolloutVariant.getRolloutId()) || !this.variantId.equals(rolloutVariant.getVariantId())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.rolloutId.hashCode() ^ 1000003) * 1000003) ^ this.variantId.hashCode();
    }

    static final class Builder extends CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.Builder {
        private String rolloutId;
        private String variantId;

        Builder() {
        }

        public CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.Builder setRolloutId(String str) {
            if (str != null) {
                this.rolloutId = str;
                return this;
            }
            throw new NullPointerException("Null rolloutId");
        }

        public CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.Builder setVariantId(String str) {
            if (str != null) {
                this.variantId = str;
                return this;
            }
            throw new NullPointerException("Null variantId");
        }

        public CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant build() {
            String str;
            String str2 = this.rolloutId;
            if (str2 != null && (str = this.variantId) != null) {
                return new AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment_RolloutVariant(str2, str);
            }
            StringBuilder sb = new StringBuilder();
            if (this.rolloutId == null) {
                sb.append(" rolloutId");
            }
            if (this.variantId == null) {
                sb.append(" variantId");
            }
            throw new IllegalStateException("Missing required properties:" + sb);
        }
    }
}
