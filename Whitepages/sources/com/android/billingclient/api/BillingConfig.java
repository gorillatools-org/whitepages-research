package com.android.billingclient.api;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import org.json.JSONObject;

public final class BillingConfig {
    private final String countryCode;
    private final String jsonString;
    private final JSONObject parsedJson;

    BillingConfig(String str) {
        this.jsonString = str;
        JSONObject jSONObject = new JSONObject(str);
        this.parsedJson = jSONObject;
        this.countryCode = jSONObject.optString(RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE);
    }

    public String getCountryCode() {
        return this.countryCode;
    }
}
