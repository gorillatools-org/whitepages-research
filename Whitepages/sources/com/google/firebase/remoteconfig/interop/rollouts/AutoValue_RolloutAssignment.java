package com.google.firebase.remoteconfig.interop.rollouts;

import com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment;

final class AutoValue_RolloutAssignment extends RolloutAssignment {
    private final String parameterKey;
    private final String parameterValue;
    private final String rolloutId;
    private final long templateVersion;
    private final String variantId;

    private AutoValue_RolloutAssignment(String str, String str2, String str3, String str4, long j) {
        this.rolloutId = str;
        this.variantId = str2;
        this.parameterKey = str3;
        this.parameterValue = str4;
        this.templateVersion = j;
    }

    public String getRolloutId() {
        return this.rolloutId;
    }

    public String getVariantId() {
        return this.variantId;
    }

    public String getParameterKey() {
        return this.parameterKey;
    }

    public String getParameterValue() {
        return this.parameterValue;
    }

    public long getTemplateVersion() {
        return this.templateVersion;
    }

    public String toString() {
        return "RolloutAssignment{rolloutId=" + this.rolloutId + ", variantId=" + this.variantId + ", parameterKey=" + this.parameterKey + ", parameterValue=" + this.parameterValue + ", templateVersion=" + this.templateVersion + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RolloutAssignment)) {
            return false;
        }
        RolloutAssignment rolloutAssignment = (RolloutAssignment) obj;
        if (!this.rolloutId.equals(rolloutAssignment.getRolloutId()) || !this.variantId.equals(rolloutAssignment.getVariantId()) || !this.parameterKey.equals(rolloutAssignment.getParameterKey()) || !this.parameterValue.equals(rolloutAssignment.getParameterValue()) || this.templateVersion != rolloutAssignment.getTemplateVersion()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.templateVersion;
        return ((((((((this.rolloutId.hashCode() ^ 1000003) * 1000003) ^ this.variantId.hashCode()) * 1000003) ^ this.parameterKey.hashCode()) * 1000003) ^ this.parameterValue.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }

    static final class Builder extends RolloutAssignment.Builder {
        private String parameterKey;
        private String parameterValue;
        private String rolloutId;
        private byte set$0;
        private long templateVersion;
        private String variantId;

        Builder() {
        }

        public RolloutAssignment.Builder setRolloutId(String str) {
            if (str != null) {
                this.rolloutId = str;
                return this;
            }
            throw new NullPointerException("Null rolloutId");
        }

        public RolloutAssignment.Builder setVariantId(String str) {
            if (str != null) {
                this.variantId = str;
                return this;
            }
            throw new NullPointerException("Null variantId");
        }

        public RolloutAssignment.Builder setParameterKey(String str) {
            if (str != null) {
                this.parameterKey = str;
                return this;
            }
            throw new NullPointerException("Null parameterKey");
        }

        public RolloutAssignment.Builder setParameterValue(String str) {
            if (str != null) {
                this.parameterValue = str;
                return this;
            }
            throw new NullPointerException("Null parameterValue");
        }

        public RolloutAssignment.Builder setTemplateVersion(long j) {
            this.templateVersion = j;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public RolloutAssignment build() {
            if (this.set$0 == 1 && this.rolloutId != null && this.variantId != null && this.parameterKey != null && this.parameterValue != null) {
                return new AutoValue_RolloutAssignment(this.rolloutId, this.variantId, this.parameterKey, this.parameterValue, this.templateVersion);
            }
            StringBuilder sb = new StringBuilder();
            if (this.rolloutId == null) {
                sb.append(" rolloutId");
            }
            if (this.variantId == null) {
                sb.append(" variantId");
            }
            if (this.parameterKey == null) {
                sb.append(" parameterKey");
            }
            if (this.parameterValue == null) {
                sb.append(" parameterValue");
            }
            if ((1 & this.set$0) == 0) {
                sb.append(" templateVersion");
            }
            throw new IllegalStateException("Missing required properties:" + sb);
        }
    }
}
