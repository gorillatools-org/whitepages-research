package com.salesforce.marketingcloud.messages.inbox;

import com.salesforce.marketingcloud.internal.m;
import com.salesforce.marketingcloud.messages.inbox.InboxMessage;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class b {
    public static final InboxMessage.Media a(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        String optString = jSONObject.optString("androidUrl");
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        String b = m.b(optString);
        String optString2 = jSONObject.optString("alt");
        Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
        String b2 = m.b(optString2);
        if (b == null && b2 == null) {
            return null;
        }
        if (b == null) {
            b = "";
        }
        return new InboxMessage.Media(b, b2);
    }

    public static final JSONObject a(InboxMessage.Media media) {
        Intrinsics.checkNotNullParameter(media, "<this>");
        JSONObject jSONObject = new JSONObject();
        if (media.getUrl() != null) {
            jSONObject.put("androidUrl", media.getUrl());
        }
        if (media.getAltText() != null) {
            jSONObject.put("alt", media.getAltText());
        }
        return jSONObject;
    }
}
