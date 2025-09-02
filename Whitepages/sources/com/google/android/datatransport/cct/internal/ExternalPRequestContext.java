package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AutoValue_ExternalPRequestContext;

public abstract class ExternalPRequestContext {

    public static abstract class Builder {
        public abstract ExternalPRequestContext build();

        public abstract Builder setOriginAssociatedProductId(Integer num);
    }

    public abstract Integer getOriginAssociatedProductId();

    public static Builder builder() {
        return new AutoValue_ExternalPRequestContext.Builder();
    }
}
