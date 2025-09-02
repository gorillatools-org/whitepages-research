package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.ExternalPrivacyContext;

final class AutoValue_ExternalPrivacyContext extends ExternalPrivacyContext {
    private final ExternalPRequestContext prequest;

    private AutoValue_ExternalPrivacyContext(ExternalPRequestContext externalPRequestContext) {
        this.prequest = externalPRequestContext;
    }

    public ExternalPRequestContext getPrequest() {
        return this.prequest;
    }

    public String toString() {
        return "ExternalPrivacyContext{prequest=" + this.prequest + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExternalPrivacyContext)) {
            return false;
        }
        ExternalPRequestContext externalPRequestContext = this.prequest;
        ExternalPRequestContext prequest2 = ((ExternalPrivacyContext) obj).getPrequest();
        if (externalPRequestContext != null) {
            return externalPRequestContext.equals(prequest2);
        }
        if (prequest2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        ExternalPRequestContext externalPRequestContext = this.prequest;
        return (externalPRequestContext == null ? 0 : externalPRequestContext.hashCode()) ^ 1000003;
    }

    static final class Builder extends ExternalPrivacyContext.Builder {
        private ExternalPRequestContext prequest;

        Builder() {
        }

        public ExternalPrivacyContext.Builder setPrequest(ExternalPRequestContext externalPRequestContext) {
            this.prequest = externalPRequestContext;
            return this;
        }

        public ExternalPrivacyContext build() {
            return new AutoValue_ExternalPrivacyContext(this.prequest);
        }
    }
}
