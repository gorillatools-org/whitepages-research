package com.salesforce.marketingcloud.messages;

import com.salesforce.marketingcloud.location.LatLon;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    public static final LatLon a(JSONObject jSONObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        JSONObject jSONObject2 = jSONObject.getJSONObject("refreshCenter");
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "getJSONObject(...)");
        return new LatLon(jSONObject2);
    }

    public static final int b(JSONObject jSONObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        return jSONObject.getInt("refreshRadius");
    }
}
