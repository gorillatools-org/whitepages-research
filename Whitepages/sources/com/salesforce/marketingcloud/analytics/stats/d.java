package com.salesforce.marketingcloud.analytics.stats;

import com.salesforce.marketingcloud.config.a;
import com.salesforce.marketingcloud.storage.db.k;
import com.salesforce.marketingcloud.util.l;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    final JSONObject a;

    private d(String str) throws JSONException {
        this.a = new JSONObject(str);
    }

    static d b(String str, String str2, Date date, String str3, String str4) throws JSONException {
        d dVar = new d(str, str2, date);
        dVar.f(str3);
        dVar.b(str4);
        return dVar;
    }

    private void c(String str) throws JSONException {
        this.a.put("buttonId", str);
    }

    private void d(String str) throws JSONException {
        if (str != null) {
            this.a.put(a.e, str);
        }
    }

    private void e(String str) throws JSONException {
        this.a.put("id", str);
    }

    private void f(String str) throws JSONException {
        this.a.put("messageId", str);
    }

    private void g(String str) throws JSONException {
        this.a.put("name", str);
    }

    private void h(String str) throws JSONException {
        this.a.put("outcomeType", str);
    }

    private void i(String str) throws JSONException {
        this.a.put("triggerId", str);
    }

    public String a() {
        return this.a.toString();
    }

    private d(String str, String str2, Date date) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        this.a = jSONObject;
        jSONObject.put("applicationId", str);
        jSONObject.put("deviceId", str2);
        jSONObject.put("eventDateUtc", l.a(date));
    }

    static d a(String str, String str2, Date date, String str3, String str4) throws JSONException {
        d dVar = new d(str, str2, date);
        dVar.f(str3);
        dVar.b(str4);
        return dVar;
    }

    private void b(String str) throws JSONException {
        if (str != null) {
            this.a.put("activityInstanceId", str);
        }
    }

    private void c(JSONObject jSONObject) throws JSONException {
        this.a.put("metaData", jSONObject);
    }

    public static d a(String str) throws JSONException {
        return new d(str);
    }

    private void b(JSONObject jSONObject) throws JSONException {
        this.a.put("information", jSONObject);
    }

    static d a(String str, String str2, Date date, String str3, String str4, long j, int i, String str5) throws JSONException {
        d dVar = new d(str, str2, date);
        dVar.f(str3);
        dVar.b(str4);
        dVar.a(j);
        dVar.a(i);
        dVar.c(str5);
        return dVar;
    }

    private void b() throws JSONException {
        this.a.put(k.a.b, "Android");
    }

    static d a(String str, String str2, Date date, String str3, String str4, JSONObject jSONObject) throws JSONException {
        d dVar = new d(str, str2, date);
        dVar.b(jSONObject);
        dVar.b();
        if (str3 != null) {
            dVar.f(str3);
        }
        if (str4 != null) {
            dVar.b(str4);
        }
        return dVar;
    }

    /* access modifiers changed from: package-private */
    public void b(int i) throws JSONException {
        this.a.put("timeInApp", i);
    }

    private void a(JSONObject jSONObject) throws JSONException {
        this.a.put(k.a.h, jSONObject);
    }

    private void a(int i) throws JSONException {
        this.a.put("dismissReason", i);
    }

    /* access modifiers changed from: package-private */
    public void a(long j) throws JSONException {
        this.a.put("duration", j);
    }

    private void a(List<String> list) throws JSONException {
        this.a.put("reasons", new JSONArray(list));
    }

    static d a(String str, String str2, Date date, JSONObject jSONObject) throws JSONException {
        d dVar = new d(str, str2, date);
        dVar.b(jSONObject);
        dVar.b();
        return dVar;
    }

    static d a(String str, String str2, Date date, String str3, String str4, JSONObject jSONObject, JSONObject jSONObject2, String str5) throws JSONException {
        d dVar = new d(str, str2, date);
        dVar.e(str4);
        dVar.g(str3);
        dVar.a(jSONObject);
        dVar.c(jSONObject2);
        dVar.d(str5);
        return dVar;
    }

    static d a(String str, String str2, Date date, String str3, String str4, String str5, String str6) throws JSONException {
        d dVar = new d(str, str2, date);
        dVar.f(str3);
        dVar.b(str4);
        dVar.i(str5);
        dVar.h(str6);
        return dVar;
    }

    static d a(String str, String str2, Date date, String str3, String str4, List<String> list) throws JSONException {
        d dVar = new d(str, str2, date);
        dVar.f(str3);
        dVar.b(str4);
        dVar.a(list);
        dVar.b();
        return dVar;
    }
}
