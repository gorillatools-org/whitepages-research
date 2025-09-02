package com.salesforce.marketingcloud.analytics.piwama;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Date;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

public final class e implements c {
    private final Date a;
    private final boolean b;
    private final List<String> c;

    public e(Date date, boolean z, List<String> list) {
        Intrinsics.checkNotNullParameter(date, "timestamp");
        Intrinsics.checkNotNullParameter(list, "objectIds");
        this.a = date;
        this.b = z;
        this.c = list;
    }

    public String a() {
        return FirebaseAnalytics.Event.APP_OPEN;
    }

    public int b() {
        return 0;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("open_from_push", this.b);
        if (!this.c.isEmpty()) {
            jSONObject2.put("message_ids", new JSONArray(this.c));
        }
        Unit unit = Unit.INSTANCE;
        jSONObject.put("details", jSONObject2);
        return jSONObject;
    }

    public String d() {
        return "track_event";
    }

    public Date e() {
        return this.a;
    }
}
