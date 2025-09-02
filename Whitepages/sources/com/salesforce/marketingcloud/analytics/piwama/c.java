package com.salesforce.marketingcloud.analytics.piwama;

import com.salesforce.marketingcloud.internal.m;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

public interface c {
    String a();

    void a(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        jSONObject.put("analyticType", b());
        jSONObject.put("api_endpoint", d());
        if (a().length() > 0) {
            jSONObject.put("event_name", a());
        }
        jSONObject.put("timestamp", m.a(e()));
    }

    int b();

    JSONObject c();

    String d();

    Date e();

    String a(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(str2, "fieldName");
        String obj = StringsKt.trim(str).toString();
        int length = obj.length();
        if (length == 0) {
            throw new IllegalArgumentException("PiEvent must contain a " + str2 + ".");
        } else if (length <= 1024) {
            return obj;
        } else {
            String substring = obj.substring(0, 1024);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return StringsKt.trim(substring).toString();
        }
    }
}
