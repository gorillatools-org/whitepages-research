package com.facebook.appevents.integrity;

import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

public final class StdParamsEnforcementManager {
    public static final StdParamsEnforcementManager INSTANCE = new StdParamsEnforcementManager();
    private static boolean enabled;
    private static Map enumRestrictionsConfig = new HashMap();
    private static Map regexRestrictionsConfig = new HashMap();

    private StdParamsEnforcementManager() {
    }

    public static final void enable() {
        boolean z;
        Class<StdParamsEnforcementManager> cls = StdParamsEnforcementManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                if (!enabled) {
                    INSTANCE.loadConfigs();
                    if (regexRestrictionsConfig.isEmpty()) {
                        if (enumRestrictionsConfig.isEmpty()) {
                            z = false;
                            enabled = z;
                        }
                    }
                    z = true;
                    enabled = z;
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void loadConfigs() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false);
                if (queryAppSettings != null) {
                    configureSchemaRestrictions(queryAppSettings.getSchemaRestrictions());
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:31|32) */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        enumRestrictionsConfig.remove(r5);
        regexRestrictionsConfig.remove(r5);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0084 */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0019 A[Catch:{ all -> 0x0065 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0091 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void configureSchemaRestrictions(org.json.JSONArray r12) {
        /*
            r11 = this;
            java.lang.String r0 = "key"
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r11)
            if (r1 == 0) goto L_0x0009
            return
        L_0x0009:
            if (r12 == 0) goto L_0x0095
            boolean r1 = enabled     // Catch:{ all -> 0x0065 }
            if (r1 == 0) goto L_0x0011
            goto L_0x0095
        L_0x0011:
            int r1 = r12.length()     // Catch:{ all -> 0x0065 }
            r2 = 0
            r3 = r2
        L_0x0017:
            if (r3 >= r1) goto L_0x0091
            org.json.JSONObject r4 = r12.getJSONObject(r3)     // Catch:{ all -> 0x0065 }
            java.lang.String r5 = r4.getString(r0)     // Catch:{ all -> 0x0065 }
            if (r5 == 0) goto L_0x008e
            int r6 = r5.length()     // Catch:{ all -> 0x0065 }
            if (r6 != 0) goto L_0x002a
            goto L_0x008e
        L_0x002a:
            java.lang.String r6 = "value"
            org.json.JSONArray r4 = r4.getJSONArray(r6)     // Catch:{ Exception -> 0x0084 }
            int r6 = r4.length()     // Catch:{ Exception -> 0x0084 }
            r7 = r2
        L_0x0035:
            if (r7 >= r6) goto L_0x008e
            org.json.JSONObject r8 = r4.getJSONObject(r7)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r9 = "require_exact_match"
            boolean r8 = r8.getBoolean(r9)     // Catch:{ Exception -> 0x0084 }
            org.json.JSONObject r9 = r4.getJSONObject(r7)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r10 = "potential_matches"
            org.json.JSONArray r9 = r9.getJSONArray(r10)     // Catch:{ Exception -> 0x0084 }
            java.util.HashSet r9 = r11.loadSet(r9)     // Catch:{ Exception -> 0x0084 }
            if (r8 == 0) goto L_0x006b
            java.util.Map r8 = enumRestrictionsConfig     // Catch:{ Exception -> 0x0084 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)     // Catch:{ Exception -> 0x0084 }
            java.util.Map r10 = enumRestrictionsConfig     // Catch:{ Exception -> 0x0084 }
            java.lang.Object r10 = r10.get(r5)     // Catch:{ Exception -> 0x0084 }
            java.util.HashSet r10 = (java.util.HashSet) r10     // Catch:{ Exception -> 0x0084 }
            if (r10 == 0) goto L_0x0067
            r10.addAll(r9)     // Catch:{ Exception -> 0x0084 }
            r9 = r10
            goto L_0x0067
        L_0x0065:
            r12 = move-exception
            goto L_0x0092
        L_0x0067:
            r8.put(r5, r9)     // Catch:{ Exception -> 0x0084 }
            goto L_0x0081
        L_0x006b:
            java.util.Map r8 = regexRestrictionsConfig     // Catch:{ Exception -> 0x0084 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)     // Catch:{ Exception -> 0x0084 }
            java.util.Map r10 = regexRestrictionsConfig     // Catch:{ Exception -> 0x0084 }
            java.lang.Object r10 = r10.get(r5)     // Catch:{ Exception -> 0x0084 }
            java.util.HashSet r10 = (java.util.HashSet) r10     // Catch:{ Exception -> 0x0084 }
            if (r10 == 0) goto L_0x007e
            r10.addAll(r9)     // Catch:{ Exception -> 0x0084 }
            r9 = r10
        L_0x007e:
            r8.put(r5, r9)     // Catch:{ Exception -> 0x0084 }
        L_0x0081:
            int r7 = r7 + 1
            goto L_0x0035
        L_0x0084:
            java.util.Map r4 = enumRestrictionsConfig     // Catch:{ all -> 0x0065 }
            r4.remove(r5)     // Catch:{ all -> 0x0065 }
            java.util.Map r4 = regexRestrictionsConfig     // Catch:{ all -> 0x0065 }
            r4.remove(r5)     // Catch:{ all -> 0x0065 }
        L_0x008e:
            int r3 = r3 + 1
            goto L_0x0017
        L_0x0091:
            return
        L_0x0092:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r12, r11)
        L_0x0095:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.integrity.StdParamsEnforcementManager.configureSchemaRestrictions(org.json.JSONArray):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:8|9|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        return new java.util.HashSet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0014, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0016 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.HashSet loadSet(org.json.JSONArray r3) {
        /*
            r2 = this;
            boolean r0 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r2)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.util.HashSet r3 = com.facebook.internal.Utility.convertJSONArrayToHashSet(r3)     // Catch:{ Exception -> 0x0016 }
            if (r3 != 0) goto L_0x001b
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ Exception -> 0x0016 }
            r3.<init>()     // Catch:{ Exception -> 0x0016 }
            goto L_0x001b
        L_0x0014:
            r3 = move-exception
            goto L_0x001c
        L_0x0016:
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ all -> 0x0014 }
            r3.<init>()     // Catch:{ all -> 0x0014 }
        L_0x001b:
            return r3
        L_0x001c:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r3, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.integrity.StdParamsEnforcementManager.loadSet(org.json.JSONArray):java.util.HashSet");
    }

    public static final void processFilterParamSchemaBlocking(Bundle bundle) {
        Class<StdParamsEnforcementManager> cls = StdParamsEnforcementManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                if (!enabled) {
                    return;
                }
                if (bundle != null) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    for (String next : bundle.keySet()) {
                        String valueOf = String.valueOf(bundle.get(next));
                        boolean z = false;
                        boolean z2 = regexRestrictionsConfig.get(next) != null;
                        if (enumRestrictionsConfig.get(next) != null) {
                            z = true;
                        }
                        if (z2 || z) {
                            StdParamsEnforcementManager stdParamsEnforcementManager = INSTANCE;
                            boolean isAnyRegexMatched = stdParamsEnforcementManager.isAnyRegexMatched(valueOf, (Set) regexRestrictionsConfig.get(next));
                            boolean isAnyEnumMatched = stdParamsEnforcementManager.isAnyEnumMatched(valueOf, (Set) enumRestrictionsConfig.get(next));
                            if (!isAnyRegexMatched && !isAnyEnumMatched) {
                                Intrinsics.checkNotNullExpressionValue(next, "key");
                                arrayList.add(next);
                            }
                        }
                    }
                    for (String remove : arrayList) {
                        bundle.remove(remove);
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final boolean isAnyRegexMatched(String str, Set set) {
        if (CrashShieldHandler.isObjectCrashing(this) || set == null) {
            return false;
        }
        try {
            Iterable<String> iterable = set;
            if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
                return false;
            }
            for (String regex : iterable) {
                if (new Regex(regex).matches(str)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    private final boolean isAnyEnumMatched(String str, Set set) {
        if (CrashShieldHandler.isObjectCrashing(this) || set == null) {
            return false;
        }
        try {
            Iterable<String> iterable = set;
            if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
                return false;
            }
            for (String lowerCase : iterable) {
                Locale locale = Locale.ROOT;
                String lowerCase2 = lowerCase.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                String lowerCase3 = str.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                if (Intrinsics.areEqual((Object) lowerCase2, (Object) lowerCase3)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }
}
