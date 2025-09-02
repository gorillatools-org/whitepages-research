package com.android.billingclient.api;

import com.android.billingclient.api.ProductDetails;
import org.json.JSONObject;

public final class zzcv {
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final ProductDetails.PricingPhase zzf;

    zzcv(JSONObject jSONObject) {
        ProductDetails.PricingPhase pricingPhase;
        this.zza = jSONObject.getString("productId");
        this.zzb = jSONObject.optString("title");
        this.zzc = jSONObject.optString("name");
        this.zzd = jSONObject.optString("description");
        this.zze = jSONObject.optString("basePlanId");
        JSONObject optJSONObject = jSONObject.optJSONObject("pricingPhase");
        if (optJSONObject == null) {
            pricingPhase = null;
        } else {
            pricingPhase = new ProductDetails.PricingPhase(optJSONObject);
        }
        this.zzf = pricingPhase;
    }
}
