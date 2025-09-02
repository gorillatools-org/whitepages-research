package com.salesforce.marketingcloud.analytics.piwama;

import android.text.TextUtils;
import com.amplitude.reactnative.AmplitudeReactNativeModule;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.storage.c;
import com.salesforce.marketingcloud.storage.j;
import org.json.JSONException;
import org.json.JSONObject;

class k extends j {
    k(MarketingCloudConfig marketingCloudConfig, j jVar) {
        super(marketingCloudConfig, jVar);
    }

    /* access modifiers changed from: package-private */
    public JSONObject a(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("app_name", this.b.appPackageName());
            jSONObject3.put("app_id", this.b.applicationId());
            String b = this.a.c().b(c.g, (String) null);
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put(AmplitudeReactNativeModule.USER_ID_KEY, b);
            }
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
        return new Object[]{"?session_id=" + this.a.c().b(c.f, "")};
    }
}
