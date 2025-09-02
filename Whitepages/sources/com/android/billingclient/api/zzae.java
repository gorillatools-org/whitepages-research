package com.android.billingclient.api;

public final /* synthetic */ class zzae implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ PurchasesResponseListener zzb;

    public /* synthetic */ zzae(BillingClientImpl billingClientImpl, PurchasesResponseListener purchasesResponseListener) {
        this.zza = billingClientImpl;
        this.zzb = purchasesResponseListener;
    }

    public final void run() {
        this.zza.zzab(this.zzb);
    }
}
