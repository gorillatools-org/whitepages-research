package com.facebook.appevents.suggestedevents;

import android.util.Patterns;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.regex.Pattern;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class FeatureExtractor {
    public static final FeatureExtractor INSTANCE = new FeatureExtractor();
    private static Map eventInfo;
    private static boolean initialized;
    private static Map languageInfo;
    private static JSONObject rules;
    private static Map textTypeInfo;

    private FeatureExtractor() {
    }

    public static final boolean isInitialized() {
        Class<FeatureExtractor> cls = FeatureExtractor.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            return initialized;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    public static final void initialize(File file) {
        Class<FeatureExtractor> cls = FeatureExtractor.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                rules = new JSONObject();
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                fileInputStream.close();
                rules = new JSONObject(new String(bArr, Charsets.UTF_8));
                try {
                    languageInfo = MapsKt.mapOf(TuplesKt.to("ENGLISH", "1"), TuplesKt.to("GERMAN", "2"), TuplesKt.to("SPANISH", "3"), TuplesKt.to("JAPANESE", "4"));
                    eventInfo = MapsKt.mapOf(TuplesKt.to("VIEW_CONTENT", "0"), TuplesKt.to("SEARCH", "1"), TuplesKt.to("ADD_TO_CART", "2"), TuplesKt.to("ADD_TO_WISHLIST", "3"), TuplesKt.to("INITIATE_CHECKOUT", "4"), TuplesKt.to("ADD_PAYMENT_INFO", "5"), TuplesKt.to("PURCHASE", "6"), TuplesKt.to("LEAD", "7"), TuplesKt.to("COMPLETE_REGISTRATION", "8"));
                    textTypeInfo = MapsKt.mapOf(TuplesKt.to("BUTTON_TEXT", "1"), TuplesKt.to("PAGE_TITLE", "2"), TuplesKt.to("RESOLVED_DOCUMENT_LINK", "3"), TuplesKt.to("BUTTON_ID", "4"));
                    initialized = true;
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, cls);
                }
            } catch (Exception unused) {
            }
        }
    }

    public static final String getTextFeature(String str, String str2, String str3) {
        Class<FeatureExtractor> cls = FeatureExtractor.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "buttonText");
            Intrinsics.checkNotNullParameter(str2, "activityName");
            Intrinsics.checkNotNullParameter(str3, "appName");
            String lowerCase = (str3 + " | " + str2 + ", " + str).toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            return lowerCase;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final float[] getDenseFeatures(JSONObject jSONObject, String str) {
        Class<FeatureExtractor> cls = FeatureExtractor.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(jSONObject, "viewHierarchy");
            Intrinsics.checkNotNullParameter(str, "appName");
            if (!initialized) {
                return null;
            }
            float[] fArr = new float[30];
            for (int i = 0; i < 30; i++) {
                fArr[i] = 0.0f;
            }
            try {
                String lowerCase = str.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                JSONObject jSONObject2 = new JSONObject(jSONObject.optJSONObject("view").toString());
                String optString = jSONObject.optString("screenname");
                JSONArray jSONArray = new JSONArray();
                FeatureExtractor featureExtractor = INSTANCE;
                featureExtractor.pruneTree(jSONObject2, jSONArray);
                featureExtractor.sum(fArr, featureExtractor.parseFeatures(jSONObject2));
                JSONObject interactedNode = featureExtractor.getInteractedNode(jSONObject2);
                if (interactedNode == null) {
                    return null;
                }
                Intrinsics.checkNotNullExpressionValue(optString, "screenName");
                String jSONObject3 = jSONObject2.toString();
                Intrinsics.checkNotNullExpressionValue(jSONObject3, "viewTree.toString()");
                featureExtractor.sum(fArr, featureExtractor.nonparseFeatures(interactedNode, jSONArray, optString, jSONObject3, lowerCase));
                return fArr;
            } catch (JSONException unused) {
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    private final float[] parseFeatures(JSONObject jSONObject) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            float[] fArr = new float[30];
            for (int i = 0; i < 30; i++) {
                fArr[i] = 0.0f;
            }
            String optString = jSONObject.optString("text");
            Intrinsics.checkNotNullExpressionValue(optString, "node.optString(TEXT_KEY)");
            String lowerCase = optString.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            String optString2 = jSONObject.optString("hint");
            Intrinsics.checkNotNullExpressionValue(optString2, "node.optString(HINT_KEY)");
            String lowerCase2 = optString2.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
            String optString3 = jSONObject.optString("classname");
            Intrinsics.checkNotNullExpressionValue(optString3, "node.optString(CLASS_NAME_KEY)");
            String lowerCase3 = optString3.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
            int optInt = jSONObject.optInt("inputtype", -1);
            String[] strArr = {lowerCase, lowerCase2};
            if (matchIndicators(new String[]{"$", "amount", FirebaseAnalytics.Param.PRICE, "total"}, strArr)) {
                fArr[0] = fArr[0] + 1.0f;
            }
            if (matchIndicators(new String[]{"password", "pwd"}, strArr)) {
                fArr[1] = fArr[1] + 1.0f;
            }
            if (matchIndicators(new String[]{"tel", "phone"}, strArr)) {
                fArr[2] = fArr[2] + 1.0f;
            }
            if (matchIndicators(new String[]{"search"}, strArr)) {
                fArr[4] = fArr[4] + 1.0f;
            }
            if (optInt >= 0) {
                fArr[5] = fArr[5] + 1.0f;
            }
            if (optInt == 2 || optInt == 3) {
                fArr[6] = fArr[6] + 1.0f;
            }
            if (optInt == 32 || Patterns.EMAIL_ADDRESS.matcher(lowerCase).matches()) {
                fArr[7] = fArr[7] + 1.0f;
            }
            if (StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) "checkbox", false, 2, (Object) null)) {
                fArr[8] = fArr[8] + 1.0f;
            }
            if (matchIndicators(new String[]{"complete", "confirm", "done", "submit"}, new String[]{lowerCase})) {
                fArr[10] = fArr[10] + 1.0f;
            }
            if (StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) "radio", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) "button", false, 2, (Object) null)) {
                fArr[12] = fArr[12] + 1.0f;
            }
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("childviews");
                int length = optJSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                    Intrinsics.checkNotNullExpressionValue(jSONObject2, "childViews.getJSONObject(i)");
                    sum(fArr, parseFeatures(jSONObject2));
                }
            } catch (JSONException unused) {
            }
            return fArr;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final float[] nonparseFeatures(JSONObject jSONObject, JSONArray jSONArray, String str, String str2, String str3) {
        String str4 = str2;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            float[] fArr = new float[30];
            for (int i = 0; i < 30; i++) {
                fArr[i] = 0.0f;
            }
            int length = jSONArray.length();
            fArr[3] = length > 1 ? ((float) length) - 1.0f : 0.0f;
            try {
                int length2 = jSONArray.length();
                for (int i2 = 0; i2 < length2; i2++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                    Intrinsics.checkNotNullExpressionValue(jSONObject2, "siblings.getJSONObject(i)");
                    if (isButton(jSONObject2)) {
                        fArr[9] = fArr[9] + 1.0f;
                    }
                }
            } catch (JSONException unused) {
            }
            fArr[13] = -1.0f;
            fArr[14] = -1.0f;
            String str5 = str + '|' + str3;
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            updateHintAndTextRecursively(jSONObject, sb2, sb);
            String sb3 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb3, "hintSB.toString()");
            String sb4 = sb2.toString();
            Intrinsics.checkNotNullExpressionValue(sb4, "textSB.toString()");
            fArr[15] = regexMatched("ENGLISH", "COMPLETE_REGISTRATION", "BUTTON_TEXT", sb4) ? 1.0f : 0.0f;
            fArr[16] = regexMatched("ENGLISH", "COMPLETE_REGISTRATION", "PAGE_TITLE", str5) ? 1.0f : 0.0f;
            fArr[17] = regexMatched("ENGLISH", "COMPLETE_REGISTRATION", "BUTTON_ID", sb3) ? 1.0f : 0.0f;
            fArr[18] = StringsKt.contains$default((CharSequence) str4, (CharSequence) "password", false, 2, (Object) null) ? 1.0f : 0.0f;
            fArr[19] = regexMatched("(?i)(confirm.*password)|(password.*(confirmation|confirm)|confirmation)", str4) ? 1.0f : 0.0f;
            fArr[20] = regexMatched("(?i)(sign in)|login|signIn", str4) ? 1.0f : 0.0f;
            fArr[21] = regexMatched("(?i)(sign.*(up|now)|registration|register|(create|apply).*(profile|account)|open.*account|account.*(open|creation|application)|enroll|join.*now)", str4) ? 1.0f : 0.0f;
            fArr[22] = regexMatched("ENGLISH", "PURCHASE", "BUTTON_TEXT", sb4) ? 1.0f : 0.0f;
            fArr[24] = regexMatched("ENGLISH", "PURCHASE", "PAGE_TITLE", str5) ? 1.0f : 0.0f;
            fArr[25] = regexMatched("(?i)add to(\\s|\\Z)|update(\\s|\\Z)|cart", sb4) ? 1.0f : 0.0f;
            fArr[27] = regexMatched("(?i)add to(\\s|\\Z)|update(\\s|\\Z)|cart|shop|buy", str5) ? 1.0f : 0.0f;
            fArr[28] = regexMatched("ENGLISH", "LEAD", "BUTTON_TEXT", sb4) ? 1.0f : 0.0f;
            fArr[29] = regexMatched("ENGLISH", "LEAD", "PAGE_TITLE", str5) ? 1.0f : 0.0f;
            return fArr;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.util.Map] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean regexMatched(java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            r4 = this;
            boolean r0 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r4)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            org.json.JSONObject r0 = rules     // Catch:{ all -> 0x0014 }
            r2 = 0
            if (r0 != 0) goto L_0x0016
            java.lang.String r0 = "rules"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)     // Catch:{ all -> 0x0014 }
            r0 = r2
            goto L_0x0016
        L_0x0014:
            r5 = move-exception
            goto L_0x0077
        L_0x0016:
            java.lang.String r3 = "rulesForLanguage"
            org.json.JSONObject r0 = r0.optJSONObject(r3)     // Catch:{ all -> 0x0014 }
            if (r0 == 0) goto L_0x006f
            java.util.Map r3 = languageInfo     // Catch:{ all -> 0x0014 }
            if (r3 != 0) goto L_0x0028
            java.lang.String r3 = "languageInfo"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)     // Catch:{ all -> 0x0014 }
            r3 = r2
        L_0x0028:
            java.lang.Object r5 = r3.get(r5)     // Catch:{ all -> 0x0014 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x0014 }
            org.json.JSONObject r5 = r0.optJSONObject(r5)     // Catch:{ all -> 0x0014 }
            if (r5 == 0) goto L_0x006f
            java.lang.String r0 = "rulesForEvent"
            org.json.JSONObject r5 = r5.optJSONObject(r0)     // Catch:{ all -> 0x0014 }
            if (r5 == 0) goto L_0x006f
            java.util.Map r0 = eventInfo     // Catch:{ all -> 0x0014 }
            if (r0 != 0) goto L_0x0046
            java.lang.String r0 = "eventInfo"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)     // Catch:{ all -> 0x0014 }
            r0 = r2
        L_0x0046:
            java.lang.Object r6 = r0.get(r6)     // Catch:{ all -> 0x0014 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x0014 }
            org.json.JSONObject r5 = r5.optJSONObject(r6)     // Catch:{ all -> 0x0014 }
            if (r5 == 0) goto L_0x006f
            java.lang.String r6 = "positiveRules"
            org.json.JSONObject r5 = r5.optJSONObject(r6)     // Catch:{ all -> 0x0014 }
            if (r5 == 0) goto L_0x006f
            java.util.Map r6 = textTypeInfo     // Catch:{ all -> 0x0014 }
            if (r6 != 0) goto L_0x0064
            java.lang.String r6 = "textTypeInfo"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)     // Catch:{ all -> 0x0014 }
            goto L_0x0065
        L_0x0064:
            r2 = r6
        L_0x0065:
            java.lang.Object r6 = r2.get(r7)     // Catch:{ all -> 0x0014 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x0014 }
            java.lang.String r2 = r5.optString(r6)     // Catch:{ all -> 0x0014 }
        L_0x006f:
            if (r2 != 0) goto L_0x0072
            goto L_0x0076
        L_0x0072:
            boolean r1 = r4.regexMatched(r2, r8)     // Catch:{ all -> 0x0014 }
        L_0x0076:
            return r1
        L_0x0077:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r5, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.suggestedevents.FeatureExtractor.regexMatched(java.lang.String, java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    private final boolean regexMatched(String str, String str2) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            return Pattern.compile(str).matcher(str2).find();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    private final boolean matchIndicators(String[] strArr, String[] strArr2) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            for (String str : strArr) {
                for (String contains$default : strArr2) {
                    if (StringsKt.contains$default((CharSequence) contains$default, (CharSequence) str, false, 2, (Object) null)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    private final boolean pruneTree(JSONObject jSONObject, JSONArray jSONArray) {
        boolean z;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            if (jSONObject.optBoolean("is_interacted")) {
                return true;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("childviews");
            int length = optJSONArray.length();
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                } else if (optJSONArray.getJSONObject(i).optBoolean("is_interacted")) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            boolean z2 = z;
            JSONArray jSONArray2 = new JSONArray();
            if (z) {
                int length2 = optJSONArray.length();
                for (int i2 = 0; i2 < length2; i2++) {
                    jSONArray.put(optJSONArray.getJSONObject(i2));
                }
            } else {
                int length3 = optJSONArray.length();
                for (int i3 = 0; i3 < length3; i3++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i3);
                    Intrinsics.checkNotNullExpressionValue(jSONObject2, "child");
                    if (pruneTree(jSONObject2, jSONArray)) {
                        jSONArray2.put(jSONObject2);
                        z2 = true;
                    }
                }
                jSONObject.put("childviews", jSONArray2);
            }
            return z2;
        } catch (JSONException unused) {
            return false;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    private final void sum(float[] fArr, float[] fArr2) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                int length = fArr.length;
                for (int i = 0; i < length; i++) {
                    fArr[i] = fArr[i] + fArr2[i];
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final boolean isButton(JSONObject jSONObject) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            return ((jSONObject.optInt("classtypebitmask") & 1) << 5) > 0;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    private final void updateHintAndTextRecursively(JSONObject jSONObject, StringBuilder sb, StringBuilder sb2) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                String optString = jSONObject.optString("text", "");
                Intrinsics.checkNotNullExpressionValue(optString, "view.optString(TEXT_KEY, \"\")");
                String lowerCase = optString.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                String optString2 = jSONObject.optString("hint", "");
                Intrinsics.checkNotNullExpressionValue(optString2, "view.optString(HINT_KEY, \"\")");
                String lowerCase2 = optString2.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                if (lowerCase.length() > 0) {
                    sb.append(lowerCase);
                    sb.append(" ");
                }
                if (lowerCase2.length() > 0) {
                    sb2.append(lowerCase2);
                    sb2.append(" ");
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("childviews");
                if (optJSONArray != null) {
                    int length = optJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        try {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                            Intrinsics.checkNotNullExpressionValue(jSONObject2, "currentChildView");
                            updateHintAndTextRecursively(jSONObject2, sb, sb2);
                        } catch (JSONException unused) {
                        }
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final JSONObject getInteractedNode(JSONObject jSONObject) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            if (jSONObject.optBoolean("is_interacted")) {
                return jSONObject;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("childviews");
            if (optJSONArray == null) {
                return null;
            }
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                Intrinsics.checkNotNullExpressionValue(jSONObject2, "children.getJSONObject(i)");
                JSONObject interactedNode = getInteractedNode(jSONObject2);
                if (interactedNode != null) {
                    return interactedNode;
                }
            }
            return null;
        } catch (JSONException unused) {
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }
}
