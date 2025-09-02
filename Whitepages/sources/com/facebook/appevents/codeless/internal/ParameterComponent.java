package com.facebook.appevents.codeless.internal;

import com.salesforce.marketingcloud.config.a;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

public final class ParameterComponent {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String name;
    private final List path;
    private final String pathType;
    private final String value;

    public ParameterComponent(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "component");
        String string = jSONObject.getString("name");
        Intrinsics.checkNotNullExpressionValue(string, "component.getString(PARAMETER_NAME_KEY)");
        this.name = string;
        String optString = jSONObject.optString("value");
        Intrinsics.checkNotNullExpressionValue(optString, "component.optString(PARAMETER_VALUE_KEY)");
        this.value = optString;
        String optString2 = jSONObject.optString("path_type", "absolute");
        Intrinsics.checkNotNullExpressionValue(optString2, "component.optString(Cons…tants.PATH_TYPE_ABSOLUTE)");
        this.pathType = optString2;
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray(a.j);
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                Intrinsics.checkNotNullExpressionValue(jSONObject2, "jsonPathArray.getJSONObject(i)");
                arrayList.add(new PathComponent(jSONObject2));
            }
        }
        this.path = arrayList;
    }

    public final String getName() {
        return this.name;
    }

    public final String getValue() {
        return this.value;
    }

    public final List getPath() {
        return this.path;
    }

    public final String getPathType() {
        return this.pathType;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
