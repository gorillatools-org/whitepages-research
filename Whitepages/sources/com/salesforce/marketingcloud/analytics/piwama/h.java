package com.salesforce.marketingcloud.analytics.piwama;

import android.text.TextUtils;
import com.salesforce.marketingcloud.analytics.b;
import com.salesforce.marketingcloud.internal.m;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class h implements c {
    private final Date a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;

    public h(String str, String str2, String str3, String str4, Date date) throws IllegalArgumentException {
        Intrinsics.checkNotNullParameter(str, "url");
        Intrinsics.checkNotNullParameter(date, "timestamp");
        this.a = date;
        this.b = a(str, "url", true);
        String str5 = null;
        this.c = str2 != null ? a(str2, "title", false) : null;
        this.d = str3 != null ? a(str3, "item", false) : null;
        this.e = str4 != null ? a(str4, "search", false) : str5;
    }

    public String a() {
        return "";
    }

    public int b() {
        return b.s;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("api_endpoint", d());
        jSONObject.put("timestamp", m.a(e()));
        jSONObject.put("url", this.b);
        if (!TextUtils.isEmpty(this.c)) {
            jSONObject.put("title", this.c);
        }
        if (!TextUtils.isEmpty(this.d)) {
            jSONObject.put("item", this.d);
        }
        if (!TextUtils.isEmpty(this.e)) {
            jSONObject.put("search", this.e);
        }
        return jSONObject;
    }

    public String d() {
        return "track_view";
    }

    public Date e() {
        return this.a;
    }

    public final String f() {
        return this.d;
    }

    public final String g() {
        return this.e;
    }

    public final String h() {
        return this.c;
    }

    public final String i() {
        return this.b;
    }
}
