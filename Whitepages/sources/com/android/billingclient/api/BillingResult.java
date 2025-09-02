package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzb;

public final class BillingResult {
    /* access modifiers changed from: private */
    public int zza;
    /* access modifiers changed from: private */
    public String zzb;

    public static class Builder {
        private int zza;
        private String zzb = "";

        /* synthetic */ Builder(zzcd zzcd) {
        }

        public BillingResult build() {
            BillingResult billingResult = new BillingResult();
            billingResult.zza = this.zza;
            billingResult.zzb = this.zzb;
            return billingResult;
        }

        public Builder setDebugMessage(String str) {
            this.zzb = str;
            return this;
        }

        public Builder setResponseCode(int i) {
            this.zza = i;
            return this;
        }
    }

    public static Builder newBuilder() {
        return new Builder((zzcd) null);
    }

    public String getDebugMessage() {
        return this.zzb;
    }

    public int getResponseCode() {
        return this.zza;
    }

    public String toString() {
        String zzh = zzb.zzh(this.zza);
        String str = this.zzb;
        return "Response Code: " + zzh + ", Debug Message: " + str;
    }
}
