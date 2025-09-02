package com.android.billingclient.api;

public final class GetBillingConfigParams {

    public static final class Builder {
        private Builder() {
        }

        public GetBillingConfigParams build() {
            return new GetBillingConfigParams();
        }
    }

    private GetBillingConfigParams() {
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
