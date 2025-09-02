package com.android.billingclient.api;

public final /* synthetic */ class zzx implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ BillingConfigResponseListener zzb;

    public /* synthetic */ zzx(BillingClientImpl billingClientImpl, BillingConfigResponseListener billingConfigResponseListener) {
        this.zza = billingClientImpl;
        this.zzb = billingConfigResponseListener;
    }

    public final void run() {
        this.zza.zzW(this.zzb);
    }
}
