package com.facebook.appevents.codeless.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.salesforce.marketingcloud.config.a;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class EventBinding {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String activityName;
    private final String appVersion;
    private final String componentId;
    private final String eventName;
    private final MappingMethod method;
    private final List parameters;
    private final List path;
    private final String pathType;
    private final ActionType type;

    public enum ActionType {
        CLICK,
        SELECTED,
        TEXT_CHANGED
    }

    public enum MappingMethod {
        MANUAL,
        INFERENCE
    }

    public EventBinding(String str, MappingMethod mappingMethod, ActionType actionType, String str2, List list, List list2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, a.h);
        Intrinsics.checkNotNullParameter(mappingMethod, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(actionType, "type");
        Intrinsics.checkNotNullParameter(str2, RemoteConfigConstants.RequestFieldKey.APP_VERSION);
        Intrinsics.checkNotNullParameter(list, a.j);
        Intrinsics.checkNotNullParameter(list2, "parameters");
        Intrinsics.checkNotNullParameter(str3, "componentId");
        Intrinsics.checkNotNullParameter(str4, "pathType");
        Intrinsics.checkNotNullParameter(str5, "activityName");
        this.eventName = str;
        this.method = mappingMethod;
        this.type = actionType;
        this.appVersion = str2;
        this.path = list;
        this.parameters = list2;
        this.componentId = str3;
        this.pathType = str4;
        this.activityName = str5;
    }

    public final String getEventName() {
        return this.eventName;
    }

    public final String getActivityName() {
        return this.activityName;
    }

    public final List getViewPath() {
        List unmodifiableList = Collections.unmodifiableList(this.path);
        Intrinsics.checkNotNullExpressionValue(unmodifiableList, "unmodifiableList(path)");
        return unmodifiableList;
    }

    public final List getViewParameters() {
        List unmodifiableList = Collections.unmodifiableList(this.parameters);
        Intrinsics.checkNotNullExpressionValue(unmodifiableList, "unmodifiableList(parameters)");
        return unmodifiableList;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List parseArray(JSONArray jSONArray) {
            ArrayList arrayList = new ArrayList();
            if (jSONArray != null) {
                try {
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        Intrinsics.checkNotNullExpressionValue(jSONObject, "array.getJSONObject(i)");
                        arrayList.add(getInstanceFromJson(jSONObject));
                    }
                } catch (IllegalArgumentException | JSONException unused) {
                }
            }
            return arrayList;
        }

        public final EventBinding getInstanceFromJson(JSONObject jSONObject) {
            Intrinsics.checkNotNullParameter(jSONObject, "mapping");
            String string = jSONObject.getString("event_name");
            String string2 = jSONObject.getString(FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullExpressionValue(string2, "mapping.getString(\"method\")");
            Locale locale = Locale.ENGLISH;
            Intrinsics.checkNotNullExpressionValue(locale, "ENGLISH");
            String upperCase = string2.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
            MappingMethod valueOf = MappingMethod.valueOf(upperCase);
            String string3 = jSONObject.getString("event_type");
            Intrinsics.checkNotNullExpressionValue(string3, "mapping.getString(\"event_type\")");
            Intrinsics.checkNotNullExpressionValue(locale, "ENGLISH");
            String upperCase2 = string3.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase(locale)");
            ActionType valueOf2 = ActionType.valueOf(upperCase2);
            String string4 = jSONObject.getString(k.a.q);
            JSONArray jSONArray = jSONObject.getJSONArray(a.j);
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                Intrinsics.checkNotNullExpressionValue(jSONObject2, "jsonPath");
                arrayList.add(new PathComponent(jSONObject2));
            }
            String optString = jSONObject.optString("path_type", "absolute");
            JSONArray optJSONArray = jSONObject.optJSONArray("parameters");
            ArrayList arrayList2 = new ArrayList();
            if (optJSONArray != null) {
                int length2 = optJSONArray.length();
                for (int i2 = 0; i2 < length2; i2++) {
                    JSONObject jSONObject3 = optJSONArray.getJSONObject(i2);
                    Intrinsics.checkNotNullExpressionValue(jSONObject3, "jsonParameter");
                    arrayList2.add(new ParameterComponent(jSONObject3));
                }
            }
            String optString2 = jSONObject.optString("component_id");
            String optString3 = jSONObject.optString("activity_name");
            Intrinsics.checkNotNullExpressionValue(string, a.h);
            Intrinsics.checkNotNullExpressionValue(string4, RemoteConfigConstants.RequestFieldKey.APP_VERSION);
            Intrinsics.checkNotNullExpressionValue(optString2, "componentId");
            Intrinsics.checkNotNullExpressionValue(optString, "pathType");
            Intrinsics.checkNotNullExpressionValue(optString3, "activityName");
            return new EventBinding(string, valueOf, valueOf2, string4, arrayList, arrayList2, optString2, optString, optString3);
        }
    }
}
