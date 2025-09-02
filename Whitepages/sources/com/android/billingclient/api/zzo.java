package com.android.billingclient.api;

import android.content.Context;
import android.content.IntentFilter;

final class zzo {
    private final Context zza;
    /* access modifiers changed from: private */
    public final PurchasesUpdatedListener zzb;
    /* access modifiers changed from: private */
    public final zzcc zze;
    private final zzn zzf = new zzn(this, true);
    private final zzn zzg = new zzn(this, false);
    private boolean zzh;

    zzo(Context context, PurchasesUpdatedListener purchasesUpdatedListener, zzck zzck, zzc zzc, UserChoiceBillingListener userChoiceBillingListener, zzcc zzcc) {
        this.zza = context;
        this.zzb = purchasesUpdatedListener;
        this.zze = zzcc;
    }

    /* access modifiers changed from: package-private */
    public final PurchasesUpdatedListener zzd() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        this.zzf.zzc(this.zza);
        this.zzg.zzc(this.zza);
    }

    /* access modifiers changed from: package-private */
    public final void zzg(boolean z) {
        IntentFilter intentFilter = new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED");
        IntentFilter intentFilter2 = new IntentFilter("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED");
        intentFilter2.addAction("com.android.vending.billing.ALTERNATIVE_BILLING");
        this.zzh = z;
        this.zzg.zza(this.zza, intentFilter2);
        if (this.zzh) {
            this.zzf.zzb(this.zza, intentFilter, "com.google.android.finsky.permission.PLAY_BILLING_LIBRARY_BROADCAST");
        } else {
            this.zzf.zza(this.zza, intentFilter);
        }
    }
}
