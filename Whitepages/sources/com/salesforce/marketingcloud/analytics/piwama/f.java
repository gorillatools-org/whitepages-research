package com.salesforce.marketingcloud.analytics.piwama;

import com.salesforce.marketingcloud.analytics.PiCart;
import com.salesforce.marketingcloud.analytics.b;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class f implements c {
    private final PiCart a;
    private final Date b;

    public f(PiCart piCart, Date date) {
        Intrinsics.checkNotNullParameter(piCart, "piCart");
        Intrinsics.checkNotNullParameter(date, "timestamp");
        this.a = piCart;
        this.b = date;
    }

    public String a() {
        return "";
    }

    public int b() {
        return b.r;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject);
        if (!this.a.cartItems().isEmpty()) {
            jSONObject.put("cart", this.a.m633toJson());
        } else {
            jSONObject.put("clear_cart", true);
        }
        return jSONObject;
    }

    public String d() {
        return "track_cart";
    }

    public Date e() {
        return this.b;
    }
}
