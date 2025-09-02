package com.android.billingclient.api;

import org.json.JSONObject;

public final class zzct {
    private final String zza;
    private final String zzb;

    zzct(JSONObject jSONObject) {
        this.zza = jSONObject.getString("rentalPeriod");
        String optString = jSONObject.optString("rentalExpirationPeriod");
        this.zzb = true == optString.isEmpty() ? null : optString;
    }
}
