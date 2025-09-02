package com.facebook.appevents.aam;

import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

public final class MetadataRule {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Set rules = new CopyOnWriteArraySet();
    private final List keyRules;
    private final String name;
    private final String valRule;

    public /* synthetic */ MetadataRule(String str, List list, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, str2);
    }

    private MetadataRule(String str, List list, String str2) {
        this.name = str;
        this.valRule = str2;
        this.keyRules = list;
    }

    public static final /* synthetic */ Set access$getRules$cp() {
        Class<MetadataRule> cls = MetadataRule.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return rules;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public final String getName() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return this.name;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public final String getValRule() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return this.valRule;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set getRules() {
            return new HashSet(MetadataRule.access$getRules$cp());
        }

        public final void updateRules(String str) {
            Intrinsics.checkNotNullParameter(str, "rulesFromServer");
            try {
                MetadataRule.access$getRules$cp().clear();
                constructRules(new JSONObject(str));
            } catch (JSONException unused) {
            }
        }

        public final Set getEnabledRuleNames() {
            HashSet hashSet = new HashSet();
            for (MetadataRule name : MetadataRule.access$getRules$cp()) {
                hashSet.add(name.getName());
            }
            return hashSet;
        }

        private final void constructRules(JSONObject jSONObject) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject optJSONObject = jSONObject.optJSONObject(next);
                if (optJSONObject != null) {
                    String optString = optJSONObject.optString("k");
                    String optString2 = optJSONObject.optString("v");
                    Intrinsics.checkNotNullExpressionValue(optString, "k");
                    if (optString.length() != 0) {
                        Set access$getRules$cp = MetadataRule.access$getRules$cp();
                        Intrinsics.checkNotNullExpressionValue(next, "key");
                        List split$default = StringsKt.split$default((CharSequence) optString, new String[]{","}, false, 0, 6, (Object) null);
                        Intrinsics.checkNotNullExpressionValue(optString2, "v");
                        access$getRules$cp.add(new MetadataRule(next, split$default, optString2, (DefaultConstructorMarker) null));
                    }
                }
            }
        }
    }

    public final List getKeyRules() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return new ArrayList(this.keyRules);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }
}
