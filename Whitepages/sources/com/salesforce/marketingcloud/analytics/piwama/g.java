package com.salesforce.marketingcloud.analytics.piwama;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.analytics.PiOrder;
import com.salesforce.marketingcloud.analytics.b;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class g implements c {
    private final PiOrder a;
    private final Date b;

    public g(PiOrder piOrder, Date date) {
        Intrinsics.checkNotNullParameter(piOrder, "piOrder");
        Intrinsics.checkNotNullParameter(date, "timestamp");
        this.a = piOrder;
        this.b = date;
    }

    public String a() {
        return "";
    }

    public int b() {
        return b.q;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject);
        PiOrder piOrder = this.a;
        jSONObject.put(FirebaseAnalytics.Param.SHIPPING, piOrder.shipping());
        jSONObject.put("order_number", piOrder.orderNumber());
        jSONObject.put(FirebaseAnalytics.Param.DISCOUNT, piOrder.discount());
        jSONObject.put("cart", piOrder.cart().m633toJson());
        return jSONObject;
    }

    public String d() {
        return "track_conversion";
    }

    public Date e() {
        return this.b;
    }
}
