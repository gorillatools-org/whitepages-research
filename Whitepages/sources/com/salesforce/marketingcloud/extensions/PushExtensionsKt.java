package com.salesforce.marketingcloud.extensions;

import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.config.b;
import com.salesforce.marketingcloud.g;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class PushExtensionsKt {

    static final class a extends Lambda implements Function0 {
        public static final a a = new a();

        a() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Could not add event to active events map.";
        }
    }

    @MCKeep
    public static final Integer getIntOrNull(JSONObject jSONObject, String str) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        if (str != null) {
            try {
                return Integer.valueOf(jSONObject.getInt(str));
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    @MCKeep
    public static final String getStringOrNull(JSONObject jSONObject, String str) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        if (str != null) {
            try {
                return jSONObject.getString(str);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    @MCKeep
    public static final JSONArray toJSONArray(Map<String, b> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        JSONArray jSONArray = new JSONArray();
        for (Map.Entry next : map.entrySet()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.salesforce.marketingcloud.config.a.i, next.getKey());
            String f = ((b) next.getValue()).f();
            if (f != null) {
                jSONObject.put(com.salesforce.marketingcloud.config.a.j, f);
            }
            jSONObject.put(com.salesforce.marketingcloud.config.a.k, ((b) next.getValue()).e());
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    @MCKeep
    public static final Map<String, String> toMap(JSONArray jSONArray) {
        Intrinsics.checkNotNullParameter(jSONArray, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (jSONArray.length() != 0) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    Object obj = jSONArray.get(i);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                    JSONObject jSONObject = (JSONObject) obj;
                    String stringOrNull = getStringOrNull(jSONObject, com.salesforce.marketingcloud.config.a.h);
                    if (stringOrNull != null) {
                        String lowerCase = stringOrNull.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        linkedHashMap.put(lowerCase, getStringOrNull(jSONObject, com.salesforce.marketingcloud.config.a.e));
                    }
                } catch (Exception e) {
                    g.a.e("~!toMap", (Throwable) e, (Function0) a.a);
                }
            }
        }
        return linkedHashMap;
    }
}
