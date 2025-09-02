package com.google.firebase.crashlytics.internal.metadata;

final class AutoValue_RolloutAssignment extends RolloutAssignment {
    private final String parameterKey;
    private final String parameterValue;
    private final String rolloutId;
    private final long templateVersion;
    private final String variantId;

    AutoValue_RolloutAssignment(String str, String str2, String str3, String str4, long j) {
        if (str != null) {
            this.rolloutId = str;
            if (str2 != null) {
                this.parameterKey = str2;
                if (str3 != null) {
                    this.parameterValue = str3;
                    if (str4 != null) {
                        this.variantId = str4;
                        this.templateVersion = j;
                        return;
                    }
                    throw new NullPointerException("Null variantId");
                }
                throw new NullPointerException("Null parameterValue");
            }
            throw new NullPointerException("Null parameterKey");
        }
        throw new NullPointerException("Null rolloutId");
    }

    public String getRolloutId() {
        return this.rolloutId;
    }

    public String getParameterKey() {
        return this.parameterKey;
    }

    public String getParameterValue() {
        return this.parameterValue;
    }

    public String getVariantId() {
        return this.variantId;
    }

    public long getTemplateVersion() {
        return this.templateVersion;
    }

    public String toString() {
        return "RolloutAssignment{rolloutId=" + this.rolloutId + ", parameterKey=" + this.parameterKey + ", parameterValue=" + this.parameterValue + ", variantId=" + this.variantId + ", templateVersion=" + this.templateVersion + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RolloutAssignment)) {
            return false;
        }
        RolloutAssignment rolloutAssignment = (RolloutAssignment) obj;
        if (!this.rolloutId.equals(rolloutAssignment.getRolloutId()) || !this.parameterKey.equals(rolloutAssignment.getParameterKey()) || !this.parameterValue.equals(rolloutAssignment.getParameterValue()) || !this.variantId.equals(rolloutAssignment.getVariantId()) || this.templateVersion != rolloutAssignment.getTemplateVersion()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.templateVersion;
        return ((((((((this.rolloutId.hashCode() ^ 1000003) * 1000003) ^ this.parameterKey.hashCode()) * 1000003) ^ this.parameterValue.hashCode()) * 1000003) ^ this.variantId.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }
}
