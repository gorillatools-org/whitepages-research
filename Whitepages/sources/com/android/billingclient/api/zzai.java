package com.android.billingclient.api;

public final /* synthetic */ class zzai implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ ConsumeResponseListener zzb;
    public final /* synthetic */ ConsumeParams zzc;

    public /* synthetic */ zzai(BillingClientImpl billingClientImpl, ConsumeResponseListener consumeResponseListener, ConsumeParams consumeParams) {
        this.zza = billingClientImpl;
        this.zzb = consumeResponseListener;
        this.zzc = consumeParams;
    }

    public final void run() {
        this.zza.zzT(this.zzb, this.zzc);
    }
}
