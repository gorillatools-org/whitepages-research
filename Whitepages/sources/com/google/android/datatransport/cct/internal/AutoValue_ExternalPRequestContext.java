package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.ExternalPRequestContext;

final class AutoValue_ExternalPRequestContext extends ExternalPRequestContext {
    private final Integer originAssociatedProductId;

    private AutoValue_ExternalPRequestContext(Integer num) {
        this.originAssociatedProductId = num;
    }

    public Integer getOriginAssociatedProductId() {
        return this.originAssociatedProductId;
    }

    public String toString() {
        return "ExternalPRequestContext{originAssociatedProductId=" + this.originAssociatedProductId + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExternalPRequestContext)) {
            return false;
        }
        Integer num = this.originAssociatedProductId;
        Integer originAssociatedProductId2 = ((ExternalPRequestContext) obj).getOriginAssociatedProductId();
        if (num != null) {
            return num.equals(originAssociatedProductId2);
        }
        if (originAssociatedProductId2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Integer num = this.originAssociatedProductId;
        return (num == null ? 0 : num.hashCode()) ^ 1000003;
    }

    static final class Builder extends ExternalPRequestContext.Builder {
        private Integer originAssociatedProductId;

        Builder() {
        }

        public ExternalPRequestContext.Builder setOriginAssociatedProductId(Integer num) {
            this.originAssociatedProductId = num;
            return this;
        }

        public ExternalPRequestContext build() {
            return new AutoValue_ExternalPRequestContext(this.originAssociatedProductId);
        }
    }
}
