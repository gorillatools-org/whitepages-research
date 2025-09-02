package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzai;
import java.util.concurrent.Callable;

final class zzau implements Callable {
    final /* synthetic */ String zza;
    final /* synthetic */ PurchasesResponseListener zzb;
    final /* synthetic */ BillingClientImpl zzc;

    zzau(BillingClientImpl billingClientImpl, String str, PurchasesResponseListener purchasesResponseListener) {
        this.zza = str;
        this.zzb = purchasesResponseListener;
        this.zzc = billingClientImpl;
    }

    public final /* bridge */ /* synthetic */ Object call() {
        zzcz zzag = BillingClientImpl.zzag(this.zzc, this.zza, 9);
        if (zzag.zzb() != null) {
            this.zzb.onQueryPurchasesResponse(zzag.zza(), zzag.zzb());
            return null;
        }
        this.zzb.onQueryPurchasesResponse(zzag.zza(), zzai.zzk());
        return null;
    }
}
