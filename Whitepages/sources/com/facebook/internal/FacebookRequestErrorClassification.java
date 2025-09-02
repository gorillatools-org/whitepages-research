package com.facebook.internal;

import com.facebook.FacebookRequestError;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

public final class FacebookRequestErrorClassification {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static FacebookRequestErrorClassification defaultInstance;
    private final Map loginRecoverableErrors;
    private final String loginRecoverableRecoveryMessage;
    private final Map otherErrors;
    private final String otherRecoveryMessage;
    private final Map transientErrors;
    private final String transientRecoveryMessage;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.facebook.FacebookRequestError$Category[] r0 = com.facebook.FacebookRequestError.Category.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.FacebookRequestError$Category r1 = com.facebook.FacebookRequestError.Category.OTHER     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.FacebookRequestError$Category r1 = com.facebook.FacebookRequestError.Category.LOGIN_RECOVERABLE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.FacebookRequestError$Category r1 = com.facebook.FacebookRequestError.Category.TRANSIENT     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.FacebookRequestErrorClassification.WhenMappings.<clinit>():void");
        }
    }

    public FacebookRequestErrorClassification(Map map, Map map2, Map map3, String str, String str2, String str3) {
        this.otherErrors = map;
        this.transientErrors = map2;
        this.loginRecoverableErrors = map3;
        this.otherRecoveryMessage = str;
        this.transientRecoveryMessage = str2;
        this.loginRecoverableRecoveryMessage = str3;
    }

    public final String getRecoveryMessage(FacebookRequestError.Category category) {
        int i = category == null ? -1 : WhenMappings.$EnumSwitchMapping$0[category.ordinal()];
        if (i == 1) {
            return this.otherRecoveryMessage;
        }
        if (i == 2) {
            return this.loginRecoverableRecoveryMessage;
        }
        if (i != 3) {
            return null;
        }
        return this.transientRecoveryMessage;
    }

    public final FacebookRequestError.Category classify(int i, int i2, boolean z) {
        Set set;
        Set set2;
        Set set3;
        if (z) {
            return FacebookRequestError.Category.TRANSIENT;
        }
        Map map = this.otherErrors;
        if (map != null && map.containsKey(Integer.valueOf(i)) && ((set3 = (Set) this.otherErrors.get(Integer.valueOf(i))) == null || set3.contains(Integer.valueOf(i2)))) {
            return FacebookRequestError.Category.OTHER;
        }
        Map map2 = this.loginRecoverableErrors;
        if (map2 != null && map2.containsKey(Integer.valueOf(i)) && ((set2 = (Set) this.loginRecoverableErrors.get(Integer.valueOf(i))) == null || set2.contains(Integer.valueOf(i2)))) {
            return FacebookRequestError.Category.LOGIN_RECOVERABLE;
        }
        Map map3 = this.transientErrors;
        if (map3 == null || !map3.containsKey(Integer.valueOf(i)) || ((set = (Set) this.transientErrors.get(Integer.valueOf(i))) != null && !set.contains(Integer.valueOf(i2)))) {
            return FacebookRequestError.Category.OTHER;
        }
        return FacebookRequestError.Category.TRANSIENT;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final synchronized FacebookRequestErrorClassification getDefaultErrorClassification() {
            FacebookRequestErrorClassification access$getDefaultInstance$cp;
            try {
                if (FacebookRequestErrorClassification.defaultInstance == null) {
                    FacebookRequestErrorClassification.defaultInstance = getDefaultErrorClassificationImpl();
                }
                access$getDefaultInstance$cp = FacebookRequestErrorClassification.defaultInstance;
                Intrinsics.checkNotNull(access$getDefaultInstance$cp, "null cannot be cast to non-null type com.facebook.internal.FacebookRequestErrorClassification");
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
            return access$getDefaultInstance$cp;
        }

        private final FacebookRequestErrorClassification getDefaultErrorClassificationImpl() {
            return new FacebookRequestErrorClassification((Map) null, MapsKt.hashMapOf(TuplesKt.to(2, (Object) null), TuplesKt.to(4, (Object) null), TuplesKt.to(9, (Object) null), TuplesKt.to(17, (Object) null), TuplesKt.to(341, (Object) null)), MapsKt.hashMapOf(TuplesKt.to(102, (Object) null), TuplesKt.to(190, (Object) null), TuplesKt.to(412, (Object) null)), (String) null, (String) null, (String) null);
        }

        private final Map parseJSONDefinition(JSONObject jSONObject) {
            int optInt;
            HashSet hashSet;
            JSONArray optJSONArray = jSONObject.optJSONArray(FirebaseAnalytics.Param.ITEMS);
            if (optJSONArray == null || optJSONArray.length() == 0) {
                return null;
            }
            HashMap hashMap = new HashMap();
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (!(optJSONObject == null || (optInt = optJSONObject.optInt("code")) == 0)) {
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("subcodes");
                    if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
                        hashSet = null;
                    } else {
                        hashSet = new HashSet();
                        int length2 = optJSONArray2.length();
                        for (int i2 = 0; i2 < length2; i2++) {
                            int optInt2 = optJSONArray2.optInt(i2);
                            if (optInt2 != 0) {
                                hashSet.add(Integer.valueOf(optInt2));
                            }
                        }
                    }
                    hashMap.put(Integer.valueOf(optInt), hashSet);
                }
            }
            return hashMap;
        }

        public final FacebookRequestErrorClassification createFromJSON(JSONArray jSONArray) {
            String optString;
            if (jSONArray == null) {
                return null;
            }
            int length = jSONArray.length();
            Map map = null;
            Map map2 = null;
            Map map3 = null;
            String str = null;
            String str2 = null;
            String str3 = null;
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (!(optJSONObject == null || (optString = optJSONObject.optString("name")) == null)) {
                    if (StringsKt.equals(optString, "other", true)) {
                        str = optJSONObject.optString("recovery_message", (String) null);
                        map = parseJSONDefinition(optJSONObject);
                    } else if (StringsKt.equals(optString, "transient", true)) {
                        str2 = optJSONObject.optString("recovery_message", (String) null);
                        map2 = parseJSONDefinition(optJSONObject);
                    } else if (StringsKt.equals(optString, "login_recoverable", true)) {
                        str3 = optJSONObject.optString("recovery_message", (String) null);
                        map3 = parseJSONDefinition(optJSONObject);
                    }
                }
            }
            return new FacebookRequestErrorClassification(map, map2, map3, str, str2, str3);
        }
    }
}
