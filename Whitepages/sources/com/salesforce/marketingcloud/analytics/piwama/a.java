package com.salesforce.marketingcloud.analytics.piwama;

import android.text.TextUtils;
import com.amplitude.reactnative.AmplitudeReactNativeModule;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.storage.c;
import com.salesforce.marketingcloud.storage.j;
import org.json.JSONException;
import org.json.JSONObject;

class a extends j {
    private static final Object[] v = {""};

    a(MarketingCloudConfig marketingCloudConfig, j jVar) {
        super(marketingCloudConfig, jVar);
    }

    /* access modifiers changed from: package-private */
    public JSONObject a(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("api_key", "849f26e2-2df6-11e4-ab12-14109fdc48df");
            jSONObject2.put("app_id", this.b.applicationId());
            String b = this.a.c().b(c.g, (String) null);
            if (!TextUtils.isEmpty(b)) {
                jSONObject2.put(AmplitudeReactNativeModule.USER_ID_KEY, b);
            }
            String b2 = this.a.c().b(c.f, (String) null);
            if (!TextUtils.isEmpty(b2)) {
                jSONObject2.put("session_id", b2);
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("app_name", this.b.appPackageName());
            jSONObject3.put("user_info", jSONObject);
            jSONObject2.put("payload", jSONObject3);
            return jSONObject2;
        } catch (JSONException e) {
            g.b(i.m, e, "Failed to construct PiWama payload JSON Object.", new Object[0]);
            return new JSONObject();
        }
    }

    /* access modifiers changed from: package-private */
    public Object[] b() {
        return v;
    }
}
