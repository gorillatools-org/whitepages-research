package com.android.billingclient.api;

public final /* synthetic */ class zzq implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ BillingResult zzb;

    public /* synthetic */ zzq(BillingClientImpl billingClientImpl, BillingResult billingResult) {
        this.zza = billingClientImpl;
        this.zzb = billingResult;
    }

    public final void run() {
        this.zza.zzS(this.zzb);
    }
}
