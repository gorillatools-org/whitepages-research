package com.android.billingclient.api;

import android.app.Activity;
import android.content.Context;
import com.android.billingclient.api.PendingPurchasesParams;
import java.util.concurrent.ExecutorService;

public abstract class BillingClient {

    public static final class Builder {
        private volatile PendingPurchasesParams zzb;
        private final Context zzc;
        private volatile PurchasesUpdatedListener zzd;
        private volatile boolean zzj;
        private volatile boolean zzk;

        /* synthetic */ Builder(Context context, zzp zzp) {
            this.zzc = context;
        }

        public BillingClient build() {
            if (this.zzc == null) {
                throw new IllegalArgumentException("Please provide a valid Context.");
            } else if (this.zzd == null) {
                if (this.zzj || this.zzk) {
                    return new BillingClientImpl((String) null, this.zzc, (zzcc) null, (ExecutorService) null);
                }
                throw new IllegalArgumentException("Please provide a valid listener for purchases updates.");
            } else if (this.zzb == null || !this.zzb.isEnabledForOneTimeProducts()) {
                throw new IllegalArgumentException("Pending purchases for one-time products must be supported.");
            } else if (this.zzd != null) {
                return new BillingClientImpl((String) null, this.zzb, this.zzc, this.zzd, (zzc) null, (zzcc) null, (ExecutorService) null);
            } else {
                return new BillingClientImpl((String) null, this.zzb, this.zzc, (zzck) null, (zzcc) null, (ExecutorService) null);
            }
        }

        public Builder enablePendingPurchases() {
            PendingPurchasesParams.Builder newBuilder = PendingPurchasesParams.newBuilder();
            newBuilder.enableOneTimeProducts();
            enablePendingPurchases(newBuilder.build());
            return this;
        }

        public Builder enablePendingPurchases(PendingPurchasesParams pendingPurchasesParams) {
            this.zzb = pendingPurchasesParams;
            return this;
        }

        public Builder setListener(PurchasesUpdatedListener purchasesUpdatedListener) {
            this.zzd = purchasesUpdatedListener;
            return this;
        }
    }

    public static Builder newBuilder(Context context) {
        return new Builder(context, (zzp) null);
    }

    public abstract void acknowledgePurchase(AcknowledgePurchaseParams acknowledgePurchaseParams, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener);

    public abstract void consumeAsync(ConsumeParams consumeParams, ConsumeResponseListener consumeResponseListener);

    public abstract void endConnection();

    public abstract void getBillingConfigAsync(GetBillingConfigParams getBillingConfigParams, BillingConfigResponseListener billingConfigResponseListener);

    public abstract BillingResult isFeatureSupported(String str);

    public abstract boolean isReady();

    public abstract BillingResult launchBillingFlow(Activity activity, BillingFlowParams billingFlowParams);

    public abstract void queryProductDetailsAsync(QueryProductDetailsParams queryProductDetailsParams, ProductDetailsResponseListener productDetailsResponseListener);

    public abstract void queryPurchaseHistoryAsync(QueryPurchaseHistoryParams queryPurchaseHistoryParams, PurchaseHistoryResponseListener purchaseHistoryResponseListener);

    public abstract void queryPurchasesAsync(QueryPurchasesParams queryPurchasesParams, PurchasesResponseListener purchasesResponseListener);

    public abstract void startConnection(BillingClientStateListener billingClientStateListener);
}
