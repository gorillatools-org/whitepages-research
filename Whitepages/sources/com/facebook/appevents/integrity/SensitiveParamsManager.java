package com.facebook.appevents.integrity;

import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

public final class SensitiveParamsManager {
    public static final SensitiveParamsManager INSTANCE = new SensitiveParamsManager();
    private static HashSet defaultSensitiveParameters = new HashSet();
    private static boolean enabled;
    private static Map sensitiveParameters = new HashMap();

    private SensitiveParamsManager() {
    }

    public static final void enable() {
        Class<SensitiveParamsManager> cls = SensitiveParamsManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                INSTANCE.loadSensitiveParameters();
                if (!defaultSensitiveParameters.isEmpty() || !sensitiveParameters.isEmpty()) {
                    enabled = true;
                } else {
                    enabled = false;
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void loadSensitiveParameters() {
        HashSet convertJSONArrayToHashSet;
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false);
                if (queryAppSettings != null) {
                    try {
                        defaultSensitiveParameters = new HashSet();
                        sensitiveParameters = new HashMap();
                        JSONArray sensitiveParams = queryAppSettings.getSensitiveParams();
                        if (sensitiveParams != null && sensitiveParams.length() != 0) {
                            int length = sensitiveParams.length();
                            for (int i = 0; i < length; i++) {
                                JSONObject jSONObject = sensitiveParams.getJSONObject(i);
                                boolean has = jSONObject.has("key");
                                boolean has2 = jSONObject.has("value");
                                if (has && has2) {
                                    String string = jSONObject.getString("key");
                                    JSONArray jSONArray = jSONObject.getJSONArray("value");
                                    if (!(jSONArray == null || (convertJSONArrayToHashSet = Utility.convertJSONArrayToHashSet(jSONArray)) == null)) {
                                        if (string.equals("_MTSDK_Default_")) {
                                            defaultSensitiveParameters = convertJSONArrayToHashSet;
                                        } else {
                                            Map map = sensitiveParameters;
                                            Intrinsics.checkNotNullExpressionValue(string, "sensitiveParamsScope");
                                            map.put(string, convertJSONArrayToHashSet);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:16|17|18|(4:21|(2:23|32)(1:31)|30|19)|24|25|(2:27|33)(1:36)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0064 */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006a A[Catch:{ all -> 0x0026 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void processFilterSensitiveParams(android.os.Bundle r6, java.lang.String r7) {
        /*
            java.lang.Class<com.facebook.appevents.integrity.SensitiveParamsManager> r0 = com.facebook.appevents.integrity.SensitiveParamsManager.class
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r0)
            if (r1 == 0) goto L_0x0009
            return
        L_0x0009:
            java.lang.String r1 = "eventName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r1)     // Catch:{ all -> 0x0026 }
            boolean r1 = enabled     // Catch:{ all -> 0x0026 }
            if (r1 == 0) goto L_0x0073
            if (r6 != 0) goto L_0x0015
            goto L_0x0073
        L_0x0015:
            java.util.HashSet r1 = defaultSensitiveParameters     // Catch:{ all -> 0x0026 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0026 }
            if (r1 == 0) goto L_0x0028
            java.util.Map r1 = sensitiveParameters     // Catch:{ all -> 0x0026 }
            boolean r1 = r1.containsKey(r7)     // Catch:{ all -> 0x0026 }
            if (r1 != 0) goto L_0x0028
            return
        L_0x0026:
            r6 = move-exception
            goto L_0x0074
        L_0x0028:
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ all -> 0x0026 }
            r1.<init>()     // Catch:{ all -> 0x0026 }
            java.util.Map r2 = sensitiveParameters     // Catch:{ Exception -> 0x0064 }
            java.lang.Object r7 = r2.get(r7)     // Catch:{ Exception -> 0x0064 }
            java.util.HashSet r7 = (java.util.HashSet) r7     // Catch:{ Exception -> 0x0064 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Exception -> 0x0064 }
            java.util.Set r3 = r6.keySet()     // Catch:{ Exception -> 0x0064 }
            java.util.Collection r3 = (java.util.Collection) r3     // Catch:{ Exception -> 0x0064 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0064 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ Exception -> 0x0064 }
        L_0x0044:
            boolean r3 = r2.hasNext()     // Catch:{ Exception -> 0x0064 }
            if (r3 == 0) goto L_0x0064
            java.lang.Object r3 = r2.next()     // Catch:{ Exception -> 0x0064 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0064 }
            com.facebook.appevents.integrity.SensitiveParamsManager r4 = INSTANCE     // Catch:{ Exception -> 0x0064 }
            java.lang.String r5 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)     // Catch:{ Exception -> 0x0064 }
            boolean r4 = r4.shouldFilterOut(r3, r7)     // Catch:{ Exception -> 0x0064 }
            if (r4 == 0) goto L_0x0044
            r6.remove(r3)     // Catch:{ Exception -> 0x0064 }
            r1.put(r3)     // Catch:{ Exception -> 0x0064 }
            goto L_0x0044
        L_0x0064:
            int r7 = r1.length()     // Catch:{ all -> 0x0026 }
            if (r7 <= 0) goto L_0x0073
            java.lang.String r7 = "_filteredKey"
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0026 }
            r6.putString(r7, r1)     // Catch:{ all -> 0x0026 }
        L_0x0073:
            return
        L_0x0074:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r6, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.integrity.SensitiveParamsManager.processFilterSensitiveParams(android.os.Bundle, java.lang.String):void");
    }

    private final boolean shouldFilterOut(String str, HashSet hashSet) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            if (!defaultSensitiveParameters.contains(str)) {
                if (hashSet == null) {
                    return false;
                }
                if (hashSet.isEmpty()) {
                    return false;
                }
                if (hashSet.contains(str)) {
                    return true;
                }
                return false;
            }
            return true;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }
}
