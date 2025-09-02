package com.android.billingclient.api;

import java.util.concurrent.Callable;

public final /* synthetic */ class zzu implements Callable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ AcknowledgePurchaseParams zzb;
    public final /* synthetic */ AcknowledgePurchaseResponseListener zzc;

    public /* synthetic */ zzu(BillingClientImpl billingClientImpl, AcknowledgePurchaseParams acknowledgePurchaseParams, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        this.zza = billingClientImpl;
        this.zzb = acknowledgePurchaseParams;
        this.zzc = acknowledgePurchaseResponseListener;
    }

    public final Object call() {
        this.zza.zzk(this.zzb, this.zzc);
        return null;
    }
}
