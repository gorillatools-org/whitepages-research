package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzal;
import com.google.android.gms.internal.play_billing.zzga;
import com.google.android.gms.internal.play_billing.zzge;
import com.google.android.gms.internal.play_billing.zzgo;
import com.google.android.gms.internal.play_billing.zzhl;

interface zzcc {
    public static final zzal zza = zzal.zzc("com.android.vending.billing.PURCHASES_UPDATED", zzgo.PURCHASES_UPDATED_ACTION, "com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED", zzgo.LOCAL_PURCHASES_UPDATED_ACTION, "com.android.vending.billing.ALTERNATIVE_BILLING", zzgo.ALTERNATIVE_BILLING_ACTION);

    void zza(zzga zzga);

    void zzb(zzga zzga, int i);

    void zzc(zzge zzge);

    void zzd(zzge zzge, int i);

    void zze(zzhl zzhl);
}
