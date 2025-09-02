package com.android.billingclient.api;

public final class PendingPurchasesParams {
    private final boolean enableOneTimeProducts;
    private final boolean enablePrepaidPlans;

    public static final class Builder {
        private boolean enableOneTimeProducts;
        private boolean enablePrepaidPlans;

        private Builder() {
        }

        public PendingPurchasesParams build() {
            if (this.enableOneTimeProducts) {
                return new PendingPurchasesParams(true, this.enablePrepaidPlans);
            }
            throw new IllegalArgumentException("Pending purchases for one-time products must be supported.");
        }

        public Builder enableOneTimeProducts() {
            this.enableOneTimeProducts = true;
            return this;
        }
    }

    private PendingPurchasesParams(boolean z, boolean z2) {
        this.enableOneTimeProducts = z;
        this.enablePrepaidPlans = z2;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* access modifiers changed from: package-private */
    public boolean isEnabledForOneTimeProducts() {
        return this.enableOneTimeProducts;
    }

    /* access modifiers changed from: package-private */
    public boolean isEnabledForPrepaidPlans() {
        return this.enablePrepaidPlans;
    }
}
