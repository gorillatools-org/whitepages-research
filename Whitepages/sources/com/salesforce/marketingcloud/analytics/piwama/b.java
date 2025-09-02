package com.salesforce.marketingcloud.analytics.piwama;

import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class b implements c {
    private final Date a;

    public b(Date date) {
        Intrinsics.checkNotNullParameter(date, "timestamp");
        this.a = date;
    }

    public String a() {
        return "app_close";
    }

    public int b() {
        return 0;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject);
        return jSONObject;
    }

    public String d() {
        return "track_event";
    }

    public Date e() {
        return this.a;
    }
}
