package com.android.billingclient.api;

import android.os.Bundle;
import com.android.billingclient.api.BillingResult;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzi;
import org.json.JSONException;

final class zzbk extends zzi {
    final BillingConfigResponseListener zza;
    final zzcc zzb;
    final int zzc;

    /* synthetic */ zzbk(BillingConfigResponseListener billingConfigResponseListener, zzcc zzcc, int i, zzbj zzbj) {
        this.zza = billingConfigResponseListener;
        this.zzb = zzcc;
        this.zzc = i;
    }

    public final void zza(Bundle bundle) {
        if (bundle == null) {
            zzcc zzcc = this.zzb;
            BillingResult billingResult = zzce.zzj;
            zzcc.zzb(zzcb.zza(63, 13, billingResult), this.zzc);
            this.zza.onBillingConfigResponse(billingResult, (BillingConfig) null);
            return;
        }
        int zzb2 = zzb.zzb(bundle, "BillingClient");
        String zzg = zzb.zzg(bundle, "BillingClient");
        BillingResult.Builder newBuilder = BillingResult.newBuilder();
        newBuilder.setResponseCode(zzb2);
        newBuilder.setDebugMessage(zzg);
        if (zzb2 != 0) {
            zzb.zzk("BillingClient", "getBillingConfig() failed. Response code: " + zzb2);
            BillingResult build = newBuilder.build();
            this.zzb.zzb(zzcb.zza(23, 13, build), this.zzc);
            this.zza.onBillingConfigResponse(build, (BillingConfig) null);
        } else if (!bundle.containsKey("BILLING_CONFIG")) {
            zzb.zzk("BillingClient", "getBillingConfig() returned a bundle with neither an error nor a billing config response");
            newBuilder.setResponseCode(6);
            BillingResult build2 = newBuilder.build();
            this.zzb.zzb(zzcb.zza(64, 13, build2), this.zzc);
            this.zza.onBillingConfigResponse(build2, (BillingConfig) null);
        } else {
            try {
                this.zza.onBillingConfigResponse(newBuilder.build(), new BillingConfig(bundle.getString("BILLING_CONFIG")));
            } catch (JSONException e) {
                zzb.zzl("BillingClient", "Got a JSON exception trying to decode BillingConfig. \n Exception: ", e);
                zzcc zzcc2 = this.zzb;
                BillingResult billingResult2 = zzce.zzj;
                zzcc2.zzb(zzcb.zza(65, 13, billingResult2), this.zzc);
                this.zza.onBillingConfigResponse(billingResult2, (BillingConfig) null);
            }
        }
    }
}
