package com.facebook.appevents.codeless.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class PathComponent {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String className;
    private final String description;
    private final String hint;
    private final int id;
    private final int index;
    private final int matchBitmask;
    private final String tag;
    private final String text;

    public enum MatchBitmaskType {
        ID(1),
        TEXT(2),
        TAG(4),
        DESCRIPTION(8),
        HINT(16);
        
        private final int value;

        private MatchBitmaskType(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }
    }

    public PathComponent(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "component");
        String string = jSONObject.getString("class_name");
        Intrinsics.checkNotNullExpressionValue(string, "component.getString(PATH_CLASS_NAME_KEY)");
        this.className = string;
        this.index = jSONObject.optInt(FirebaseAnalytics.Param.INDEX, -1);
        this.id = jSONObject.optInt("id");
        String optString = jSONObject.optString("text");
        Intrinsics.checkNotNullExpressionValue(optString, "component.optString(PATH_TEXT_KEY)");
        this.text = optString;
        String optString2 = jSONObject.optString("tag");
        Intrinsics.checkNotNullExpressionValue(optString2, "component.optString(PATH_TAG_KEY)");
        this.tag = optString2;
        String optString3 = jSONObject.optString("description");
        Intrinsics.checkNotNullExpressionValue(optString3, "component.optString(PATH_DESCRIPTION_KEY)");
        this.description = optString3;
        String optString4 = jSONObject.optString("hint");
        Intrinsics.checkNotNullExpressionValue(optString4, "component.optString(PATH_HINT_KEY)");
        this.hint = optString4;
        this.matchBitmask = jSONObject.optInt("match_bitmask");
    }

    public final String getClassName() {
        return this.className;
    }

    public final int getIndex() {
        return this.index;
    }

    public final int getId() {
        return this.id;
    }

    public final String getText() {
        return this.text;
    }

    public final String getTag() {
        return this.tag;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getHint() {
        return this.hint;
    }

    public final int getMatchBitmask() {
        return this.matchBitmask;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
