package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.ComplianceData;

final class AutoValue_ComplianceData extends ComplianceData {
    private final ExternalPrivacyContext privacyContext;
    private final ComplianceData.ProductIdOrigin productIdOrigin;

    private AutoValue_ComplianceData(ExternalPrivacyContext externalPrivacyContext, ComplianceData.ProductIdOrigin productIdOrigin2) {
        this.privacyContext = externalPrivacyContext;
        this.productIdOrigin = productIdOrigin2;
    }

    public ExternalPrivacyContext getPrivacyContext() {
        return this.privacyContext;
    }

    public ComplianceData.ProductIdOrigin getProductIdOrigin() {
        return this.productIdOrigin;
    }

    public String toString() {
        return "ComplianceData{privacyContext=" + this.privacyContext + ", productIdOrigin=" + this.productIdOrigin + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ComplianceData)) {
            return false;
        }
        ComplianceData complianceData = (ComplianceData) obj;
        ExternalPrivacyContext externalPrivacyContext = this.privacyContext;
        if (externalPrivacyContext != null ? externalPrivacyContext.equals(complianceData.getPrivacyContext()) : complianceData.getPrivacyContext() == null) {
            ComplianceData.ProductIdOrigin productIdOrigin2 = this.productIdOrigin;
            if (productIdOrigin2 == null) {
                if (complianceData.getProductIdOrigin() == null) {
                    return true;
                }
            } else if (productIdOrigin2.equals(complianceData.getProductIdOrigin())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        ExternalPrivacyContext externalPrivacyContext = this.privacyContext;
        int i = 0;
        int hashCode = ((externalPrivacyContext == null ? 0 : externalPrivacyContext.hashCode()) ^ 1000003) * 1000003;
        ComplianceData.ProductIdOrigin productIdOrigin2 = this.productIdOrigin;
        if (productIdOrigin2 != null) {
            i = productIdOrigin2.hashCode();
        }
        return hashCode ^ i;
    }

    static final class Builder extends ComplianceData.Builder {
        private ExternalPrivacyContext privacyContext;
        private ComplianceData.ProductIdOrigin productIdOrigin;

        Builder() {
        }

        public ComplianceData.Builder setPrivacyContext(ExternalPrivacyContext externalPrivacyContext) {
            this.privacyContext = externalPrivacyContext;
            return this;
        }

        public ComplianceData.Builder setProductIdOrigin(ComplianceData.ProductIdOrigin productIdOrigin2) {
            this.productIdOrigin = productIdOrigin2;
            return this;
        }

        public ComplianceData build() {
            return new AutoValue_ComplianceData(this.privacyContext, this.productIdOrigin);
        }
    }
}
