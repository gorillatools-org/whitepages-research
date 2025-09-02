package com.android.billingclient.api;

public final /* synthetic */ class zzao implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ ProductDetailsResponseListener zzb;

    public /* synthetic */ zzao(BillingClientImpl billingClientImpl, ProductDetailsResponseListener productDetailsResponseListener) {
        this.zza = billingClientImpl;
        this.zzb = productDetailsResponseListener;
    }

    public final void run() {
        this.zza.zzZ(this.zzb);
    }
}
