package com.facebook.appevents.suggestedevents;

import android.content.SharedPreferences;
import com.facebook.FacebookSdk;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

public final class PredictionHistoryManager {
    public static final PredictionHistoryManager INSTANCE = new PredictionHistoryManager();
    private static final Map clickedViewPaths = new LinkedHashMap();
    private static final AtomicBoolean initialized = new AtomicBoolean(false);
    private static SharedPreferences shardPreferences;

    private PredictionHistoryManager() {
    }

    private final void initAndWait() {
        String str = "";
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                AtomicBoolean atomicBoolean = initialized;
                if (!atomicBoolean.get()) {
                    SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.internal.SUGGESTED_EVENTS_HISTORY", 0);
                    Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getApplicationContext()\n…RE, Context.MODE_PRIVATE)");
                    shardPreferences = sharedPreferences;
                    Map map = clickedViewPaths;
                    if (sharedPreferences == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("shardPreferences");
                        sharedPreferences = null;
                    }
                    String string = sharedPreferences.getString("SUGGESTED_EVENTS_HISTORY", str);
                    if (string != null) {
                        str = string;
                    }
                    map.putAll(Utility.jsonStrToMap(str));
                    atomicBoolean.set(true);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final void addPrediction(String str, String str2) {
        Class<PredictionHistoryManager> cls = PredictionHistoryManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(str, "pathID");
                Intrinsics.checkNotNullParameter(str2, "predictedEvent");
                if (!initialized.get()) {
                    INSTANCE.initAndWait();
                }
                Map map = clickedViewPaths;
                map.put(str, str2);
                SharedPreferences sharedPreferences = shardPreferences;
                if (sharedPreferences == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("shardPreferences");
                    sharedPreferences = null;
                }
                sharedPreferences.edit().putString("SUGGESTED_EVENTS_HISTORY", Utility.mapToJsonStr(MapsKt.toMap(map))).apply();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: org.json.JSONObject} */
    /* JADX WARNING: type inference failed for: r2v2, types: [org.json.JSONObject] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: Can't wrap try/catch for region: R(10:4|5|6|7|(1:9)|18|12|13|14|15) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003a */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String getPathID(android.view.View r4, java.lang.String r5) {
        /*
            java.lang.String r0 = "text"
            java.lang.Class<com.facebook.appevents.suggestedevents.PredictionHistoryManager> r1 = com.facebook.appevents.suggestedevents.PredictionHistoryManager.class
            boolean r2 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r1)
            r3 = 0
            if (r2 == 0) goto L_0x000c
            return r3
        L_0x000c:
            java.lang.String r2 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r2)     // Catch:{ all -> 0x0033 }
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)     // Catch:{ all -> 0x0033 }
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ all -> 0x0033 }
            r2.<init>()     // Catch:{ all -> 0x0033 }
            r2.put(r0, r5)     // Catch:{ JSONException -> 0x003a }
            org.json.JSONArray r5 = new org.json.JSONArray     // Catch:{ JSONException -> 0x003a }
            r5.<init>()     // Catch:{ JSONException -> 0x003a }
        L_0x0021:
            if (r4 == 0) goto L_0x0035
            java.lang.Class r0 = r4.getClass()     // Catch:{ JSONException -> 0x003a }
            java.lang.String r0 = r0.getSimpleName()     // Catch:{ JSONException -> 0x003a }
            r5.put(r0)     // Catch:{ JSONException -> 0x003a }
            android.view.ViewGroup r4 = com.facebook.appevents.codeless.internal.ViewHierarchy.getParentOfView(r4)     // Catch:{ JSONException -> 0x003a }
            goto L_0x0021
        L_0x0033:
            r4 = move-exception
            goto L_0x0043
        L_0x0035:
            java.lang.String r4 = "classname"
            r2.put(r4, r5)     // Catch:{ JSONException -> 0x003a }
        L_0x003a:
            java.lang.String r4 = r2.toString()     // Catch:{ all -> 0x0033 }
            java.lang.String r4 = com.facebook.internal.Utility.sha256hash(r4)     // Catch:{ all -> 0x0033 }
            return r4
        L_0x0043:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r4, r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.suggestedevents.PredictionHistoryManager.getPathID(android.view.View, java.lang.String):java.lang.String");
    }

    public static final String queryEvent(String str) {
        Class<PredictionHistoryManager> cls = PredictionHistoryManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "pathID");
            Map map = clickedViewPaths;
            if (map.containsKey(str)) {
                return (String) map.get(str);
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }
}
