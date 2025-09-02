package com.android.billingclient.api;

public final class ConsumeParams {
    /* access modifiers changed from: private */
    public String zza;

    public static final class Builder {
        private String zza;

        /* synthetic */ Builder(zzcf zzcf) {
        }

        public ConsumeParams build() {
            String str = this.zza;
            if (str != null) {
                ConsumeParams consumeParams = new ConsumeParams((zzcg) null);
                consumeParams.zza = str;
                return consumeParams;
            }
            throw new IllegalArgumentException("Purchase token must be set");
        }

        public Builder setPurchaseToken(String str) {
            this.zza = str;
            return this;
        }
    }

    /* synthetic */ ConsumeParams(zzcg zzcg) {
    }

    public static Builder newBuilder() {
        return new Builder((zzcf) null);
    }

    public String getPurchaseToken() {
        return this.zza;
    }
}
