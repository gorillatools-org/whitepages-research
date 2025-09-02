package com.google.android.datatransport.cct.internal;

import android.util.SparseArray;
import com.google.android.datatransport.cct.internal.AutoValue_ComplianceData;

public abstract class ComplianceData {

    public static abstract class Builder {
        public abstract ComplianceData build();

        public abstract Builder setPrivacyContext(ExternalPrivacyContext externalPrivacyContext);

        public abstract Builder setProductIdOrigin(ProductIdOrigin productIdOrigin);
    }

    public abstract ExternalPrivacyContext getPrivacyContext();

    public abstract ProductIdOrigin getProductIdOrigin();

    public enum ProductIdOrigin {
        NOT_SET(0),
        EVENT_OVERRIDE(5);
        
        private static final SparseArray valueMap = null;
        private final int value;

        static {
            ProductIdOrigin productIdOrigin;
            ProductIdOrigin productIdOrigin2;
            SparseArray sparseArray = new SparseArray();
            valueMap = sparseArray;
            sparseArray.put(0, productIdOrigin);
            sparseArray.put(5, productIdOrigin2);
        }

        private ProductIdOrigin(int i) {
            this.value = i;
        }
    }

    public static Builder builder() {
        return new AutoValue_ComplianceData.Builder();
    }
}
