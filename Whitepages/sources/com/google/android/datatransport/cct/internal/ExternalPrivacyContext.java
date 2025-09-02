package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AutoValue_ExternalPrivacyContext;

public abstract class ExternalPrivacyContext {

    public static abstract class Builder {
        public abstract ExternalPrivacyContext build();

        public abstract Builder setPrequest(ExternalPRequestContext externalPRequestContext);
    }

    public abstract ExternalPRequestContext getPrequest();

    public static Builder builder() {
        return new AutoValue_ExternalPrivacyContext.Builder();
    }
}
