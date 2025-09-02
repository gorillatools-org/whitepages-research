package com.android.billingclient.api;

public final /* synthetic */ class zzam implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ PurchaseHistoryResponseListener zzb;

    public /* synthetic */ zzam(BillingClientImpl billingClientImpl, PurchaseHistoryResponseListener purchaseHistoryResponseListener) {
        this.zza = billingClientImpl;
        this.zzb = purchaseHistoryResponseListener;
    }

    public final void run() {
        this.zza.zzaa(this.zzb);
    }
}
