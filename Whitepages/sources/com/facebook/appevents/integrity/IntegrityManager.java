package com.facebook.appevents.integrity;

import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppGateKeepersManager;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class IntegrityManager {
    public static final IntegrityManager INSTANCE = new IntegrityManager();
    private static boolean enabled;
    private static boolean isSampleEnabled;

    private IntegrityManager() {
    }

    public static final void enable() {
        Class<IntegrityManager> cls = IntegrityManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                enabled = true;
                isSampleEnabled = FetchedAppGateKeepersManager.getGateKeeperForKey("FBSDKFeatureIntegritySample", FacebookSdk.getApplicationId(), false);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void processParameters(Map map) {
        Class<IntegrityManager> cls = IntegrityManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(map, "parameters");
                if (enabled && !map.isEmpty()) {
                    try {
                        List<String> list = CollectionsKt.toList(map.keySet());
                        JSONObject jSONObject = new JSONObject();
                        for (String str : list) {
                            Object obj = map.get(str);
                            if (obj != null) {
                                String str2 = (String) obj;
                                IntegrityManager integrityManager = INSTANCE;
                                if (!integrityManager.shouldFilter(str)) {
                                    if (integrityManager.shouldFilter(str2)) {
                                    }
                                }
                                map.remove(str);
                                if (!isSampleEnabled) {
                                    str2 = "";
                                }
                                jSONObject.put(str, str2);
                            } else {
                                throw new IllegalStateException("Required value was null.");
                            }
                        }
                        if (jSONObject.length() != 0) {
                            String jSONObject2 = jSONObject.toString();
                            Intrinsics.checkNotNullExpressionValue(jSONObject2, "restrictiveParamJson.toString()");
                            map.put("_onDeviceParams", jSONObject2);
                        }
                    } catch (Exception unused) {
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final boolean shouldFilter(String str) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            return !Intrinsics.areEqual((Object) "none", (Object) getIntegrityPredictionResult(str));
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        r7 = r7[0];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String getIntegrityPredictionResult(java.lang.String r7) {
        /*
            r6 = this;
            boolean r0 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r6)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            r0 = 30
            float[] r2 = new float[r0]     // Catch:{ all -> 0x0016 }
            r3 = 0
            r4 = r3
        L_0x000e:
            if (r4 >= r0) goto L_0x0018
            r5 = 0
            r2[r4] = r5     // Catch:{ all -> 0x0016 }
            int r4 = r4 + 1
            goto L_0x000e
        L_0x0016:
            r7 = move-exception
            goto L_0x002f
        L_0x0018:
            com.facebook.appevents.ml.ModelManager$Task r0 = com.facebook.appevents.ml.ModelManager.Task.MTML_INTEGRITY_DETECT     // Catch:{ all -> 0x0016 }
            float[][] r2 = new float[][]{r2}     // Catch:{ all -> 0x0016 }
            java.lang.String[] r7 = new java.lang.String[]{r7}     // Catch:{ all -> 0x0016 }
            java.lang.String[] r7 = com.facebook.appevents.ml.ModelManager.predict(r0, r2, r7)     // Catch:{ all -> 0x0016 }
            if (r7 == 0) goto L_0x002c
            r7 = r7[r3]     // Catch:{ all -> 0x0016 }
            if (r7 != 0) goto L_0x002e
        L_0x002c:
            java.lang.String r7 = "none"
        L_0x002e:
            return r7
        L_0x002f:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r7, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.integrity.IntegrityManager.getIntegrityPredictionResult(java.lang.String):java.lang.String");
    }
}
