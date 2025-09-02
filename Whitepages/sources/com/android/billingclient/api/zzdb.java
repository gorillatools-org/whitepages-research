package com.android.billingclient.api;

import android.os.Bundle;
import com.android.billingclient.api.BillingResult;
import com.google.android.gms.internal.play_billing.zzb;
import java.util.ArrayList;

abstract class zzdb {
    static zzda zza(Bundle bundle, String str, String str2) {
        BillingResult billingResult = zzce.zzj;
        if (bundle == null) {
            zzb.zzk("BillingClient", String.format("%s got null owned items list", new Object[]{str2}));
            return new zzda(billingResult, 54);
        }
        int zzb = zzb.zzb(bundle, "BillingClient");
        String zzg = zzb.zzg(bundle, "BillingClient");
        BillingResult.Builder newBuilder = BillingResult.newBuilder();
        newBuilder.setResponseCode(zzb);
        newBuilder.setDebugMessage(zzg);
        BillingResult build = newBuilder.build();
        if (zzb != 0) {
            zzb.zzk("BillingClient", String.format("%s failed. Response code: %s", new Object[]{str2, Integer.valueOf(zzb)}));
            return new zzda(build, 23);
        } else if (!bundle.containsKey("INAPP_PURCHASE_ITEM_LIST") || !bundle.containsKey("INAPP_PURCHASE_DATA_LIST") || !bundle.containsKey("INAPP_DATA_SIGNATURE_LIST")) {
            zzb.zzk("BillingClient", String.format("Bundle returned from %s doesn't contain required fields.", new Object[]{str2}));
            return new zzda(billingResult, 55);
        } else {
            ArrayList<String> stringArrayList = bundle.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
            ArrayList<String> stringArrayList2 = bundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
            ArrayList<String> stringArrayList3 = bundle.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
            if (stringArrayList == null) {
                zzb.zzk("BillingClient", String.format("Bundle returned from %s contains null SKUs list.", new Object[]{str2}));
                return new zzda(billingResult, 56);
            } else if (stringArrayList2 == null) {
                zzb.zzk("BillingClient", String.format("Bundle returned from %s contains null purchases list.", new Object[]{str2}));
                return new zzda(billingResult, 57);
            } else if (stringArrayList3 != null) {
                return new zzda(zzce.zzl, 1);
            } else {
                zzb.zzk("BillingClient", String.format("Bundle returned from %s contains null signatures list.", new Object[]{str2}));
                return new zzda(billingResult, 58);
            }
        }
    }
}
