package com.salesforce.marketingcloud.analytics.etanalytics;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.analytics.AnalyticsManager;
import com.salesforce.marketingcloud.http.c;
import com.salesforce.marketingcloud.http.d;
import com.salesforce.marketingcloud.internal.g;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.storage.db.k;
import com.salesforce.marketingcloud.storage.j;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public class c implements c.C0017c, b.C0003b {
    final MarketingCloudConfig d;
    final String e;
    final j f;
    final com.salesforce.marketingcloud.http.c g;
    final b h;
    private final l i;

    class a extends g {
        a(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            List<com.salesforce.marketingcloud.analytics.b> c = c.this.f.m().c(c.this.f.b());
            if (!c.isEmpty()) {
                com.salesforce.marketingcloud.http.a aVar = com.salesforce.marketingcloud.http.a.ET_ANALYTICS;
                c cVar = c.this;
                MarketingCloudConfig marketingCloudConfig = cVar.d;
                com.salesforce.marketingcloud.storage.c c2 = cVar.f.c();
                c cVar2 = c.this;
                com.salesforce.marketingcloud.http.b a = aVar.a(marketingCloudConfig, c2, cVar2.a(cVar2.d.applicationId(), c.this.e, c).toString());
                a.a(com.salesforce.marketingcloud.analytics.c.a(c));
                c.this.g.a(a);
                return;
            }
            c.this.h.d(a.C0001a.ET_ANALYTICS);
        }
    }

    public c(MarketingCloudConfig marketingCloudConfig, String str, j jVar, com.salesforce.marketingcloud.http.c cVar, b bVar, l lVar) {
        this.d = (MarketingCloudConfig) com.salesforce.marketingcloud.util.j.a(marketingCloudConfig, "Config is null");
        this.e = (String) com.salesforce.marketingcloud.util.j.a(str, "DeviceId is null");
        this.f = (j) com.salesforce.marketingcloud.util.j.a(jVar, "MCStorage is null");
        this.g = (com.salesforce.marketingcloud.http.c) com.salesforce.marketingcloud.util.j.a(cVar, "RequestManager is null");
        this.h = (b) com.salesforce.marketingcloud.util.j.a(bVar, "AlarmScheduler is null");
        this.i = lVar;
        cVar.a(com.salesforce.marketingcloud.http.a.ET_ANALYTICS, (c.C0017c) this);
        bVar.a((b.C0003b) this, a.C0001a.ET_ANALYTICS);
    }

    /* access modifiers changed from: package-private */
    public JSONArray a(String str, String str2, List<com.salesforce.marketingcloud.analytics.b> list) {
        JSONArray jSONArray = new JSONArray();
        for (com.salesforce.marketingcloud.analytics.b next : list) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("etAppId", str);
                jSONObject.put("deviceId", str2);
                jSONObject.put("eventDate", com.salesforce.marketingcloud.util.l.a(next.b()));
                jSONObject.put("value", next.g());
                jSONObject.put("analyticTypes", new JSONArray(Collections.singletonList(Integer.valueOf(next.a()))));
                jSONObject.put("objectIds", new JSONArray(next.i()));
                String c = next.c();
                if (!TextUtils.isEmpty(c)) {
                    JSONObject jSONObject2 = new JSONObject(c);
                    String optString = jSONObject2.optString("requestId");
                    if (!TextUtils.isEmpty(optString)) {
                        jSONObject.put("requestId", optString);
                    }
                    JSONObject jSONObject3 = jSONObject2.optJSONObject("propertyBag") != null ? jSONObject2.getJSONObject("propertyBag") : new JSONObject();
                    jSONObject3.put(k.a.b, "Android");
                    jSONObject.put("propertyBag", jSONObject3);
                }
                jSONArray.put(jSONObject);
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.b(AnalyticsManager.TAG, e2, "Failed to update EtAnalyticItem or convert it to JSON for transmission.", new Object[0]);
            }
        }
        return jSONArray;
    }

    public void b() {
        this.g.a(com.salesforce.marketingcloud.http.a.ET_ANALYTICS);
        b bVar = this.h;
        a.C0001a aVar = a.C0001a.ET_ANALYTICS;
        bVar.d(aVar);
        this.h.e(aVar);
    }

    public void a(a.C0001a aVar) {
        if (aVar == a.C0001a.ET_ANALYTICS) {
            a();
        }
    }

    public void a(com.salesforce.marketingcloud.http.b bVar, d dVar) {
        if (dVar.g()) {
            this.h.c(a.C0001a.ET_ANALYTICS);
            if (bVar.q() != null) {
                this.i.b().execute(new com.salesforce.marketingcloud.analytics.d(this.f.m(), com.salesforce.marketingcloud.analytics.c.a(bVar.q())));
                return;
            }
            return;
        }
        com.salesforce.marketingcloud.g.c(AnalyticsManager.TAG, "Request failed: %d - %s", Integer.valueOf(dVar.b()), dVar.e());
        this.h.b(a.C0001a.ET_ANALYTICS);
    }

    public void a() {
        this.i.b().execute(new a("send_analytics", new Object[0]));
    }
}
