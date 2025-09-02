package com.facebook.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.internal.gatekeeper.GateKeeper;
import com.facebook.internal.gatekeeper.GateKeeperRuntimeCache;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

public final class FetchedAppGateKeepersManager {
    public static final FetchedAppGateKeepersManager INSTANCE = new FetchedAppGateKeepersManager();
    private static final String TAG = Reflection.getOrCreateKotlinClass(FetchedAppGateKeepersManager.class).getSimpleName();
    private static final ConcurrentLinkedQueue callbacks = new ConcurrentLinkedQueue();
    private static final Map fetchedAppGateKeepers = new ConcurrentHashMap();
    private static GateKeeperRuntimeCache gateKeeperRuntimeCache;
    private static final AtomicBoolean isLoading = new AtomicBoolean(false);
    private static Long timestamp;

    public interface Callback {
        void onCompleted();
    }

    private FetchedAppGateKeepersManager() {
    }

    public final void loadAppGateKeepersAsync() {
        loadAppGateKeepersAsync((Callback) null);
    }

    public static final synchronized void loadAppGateKeepersAsync(Callback callback) {
        synchronized (FetchedAppGateKeepersManager.class) {
            if (callback != null) {
                try {
                    callbacks.add(callback);
                } catch (JSONException e) {
                    Utility.logd("FacebookSDK", (Exception) e);
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            String applicationId = FacebookSdk.getApplicationId();
            FetchedAppGateKeepersManager fetchedAppGateKeepersManager = INSTANCE;
            if (!fetchedAppGateKeepersManager.isTimestampValid(timestamp) || !fetchedAppGateKeepers.containsKey(applicationId)) {
                Context applicationContext = FacebookSdk.getApplicationContext();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("com.facebook.internal.APP_GATEKEEPERS.%s", Arrays.copyOf(new Object[]{applicationId}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                if (applicationContext != null) {
                    JSONObject jSONObject = null;
                    String string = applicationContext.getSharedPreferences("com.facebook.internal.preferences.APP_GATEKEEPERS", 0).getString(format, (String) null);
                    if (!Utility.isNullOrEmpty(string)) {
                        jSONObject = new JSONObject(string);
                        if (jSONObject != null) {
                            parseAppGateKeepersFromJSON$facebook_core_release(applicationId, jSONObject);
                        }
                    }
                    Executor executor = FacebookSdk.getExecutor();
                    if (executor != null) {
                        if (isLoading.compareAndSet(false, true)) {
                            executor.execute(new FetchedAppGateKeepersManager$$ExternalSyntheticLambda0(applicationId, applicationContext, format));
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            fetchedAppGateKeepersManager.pollCallbacks();
        }
    }

    /* access modifiers changed from: private */
    public static final void loadAppGateKeepersAsync$lambda$0(String str, Context context, String str2) {
        Intrinsics.checkNotNullParameter(str, "$applicationId");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(str2, "$gateKeepersKey");
        FetchedAppGateKeepersManager fetchedAppGateKeepersManager = INSTANCE;
        JSONObject appGateKeepersQueryResponse = fetchedAppGateKeepersManager.getAppGateKeepersQueryResponse(str);
        if (appGateKeepersQueryResponse.length() != 0) {
            parseAppGateKeepersFromJSON$facebook_core_release(str, appGateKeepersQueryResponse);
            context.getSharedPreferences("com.facebook.internal.preferences.APP_GATEKEEPERS", 0).edit().putString(str2, appGateKeepersQueryResponse.toString()).apply();
            timestamp = Long.valueOf(System.currentTimeMillis());
        }
        fetchedAppGateKeepersManager.pollCallbacks();
        isLoading.set(false);
    }

    private final void pollCallbacks() {
        Handler handler = new Handler(Looper.getMainLooper());
        while (true) {
            ConcurrentLinkedQueue concurrentLinkedQueue = callbacks;
            if (!concurrentLinkedQueue.isEmpty()) {
                Callback callback = (Callback) concurrentLinkedQueue.poll();
                if (callback != null) {
                    handler.post(new FetchedAppGateKeepersManager$$ExternalSyntheticLambda1(callback));
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void pollCallbacks$lambda$1(Callback callback) {
        callback.onCompleted();
    }

    public static final JSONObject queryAppGateKeepers(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "applicationId");
        if (!z) {
            Map map = fetchedAppGateKeepers;
            if (map.containsKey(str)) {
                JSONObject jSONObject = (JSONObject) map.get(str);
                return jSONObject == null ? new JSONObject() : jSONObject;
            }
        }
        JSONObject appGateKeepersQueryResponse = INSTANCE.getAppGateKeepersQueryResponse(str);
        Context applicationContext = FacebookSdk.getApplicationContext();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("com.facebook.internal.APP_GATEKEEPERS.%s", Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        applicationContext.getSharedPreferences("com.facebook.internal.preferences.APP_GATEKEEPERS", 0).edit().putString(format, appGateKeepersQueryResponse.toString()).apply();
        return parseAppGateKeepersFromJSON$facebook_core_release(str, appGateKeepersQueryResponse);
    }

    public final Map getGateKeepersForApplication(String str) {
        loadAppGateKeepersAsync();
        if (str != null) {
            Map map = fetchedAppGateKeepers;
            if (map.containsKey(str)) {
                GateKeeperRuntimeCache gateKeeperRuntimeCache2 = gateKeeperRuntimeCache;
                List<GateKeeper> dumpGateKeepers = gateKeeperRuntimeCache2 != null ? gateKeeperRuntimeCache2.dumpGateKeepers(str) : null;
                if (dumpGateKeepers != null) {
                    HashMap hashMap = new HashMap();
                    for (GateKeeper gateKeeper : dumpGateKeepers) {
                        hashMap.put(gateKeeper.getName(), Boolean.valueOf(gateKeeper.getValue()));
                    }
                    return hashMap;
                }
                HashMap hashMap2 = new HashMap();
                JSONObject jSONObject = (JSONObject) map.get(str);
                if (jSONObject == null) {
                    jSONObject = new JSONObject();
                }
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    Intrinsics.checkNotNullExpressionValue(next, "key");
                    hashMap2.put(next, Boolean.valueOf(jSONObject.optBoolean(next)));
                }
                GateKeeperRuntimeCache gateKeeperRuntimeCache3 = gateKeeperRuntimeCache;
                if (gateKeeperRuntimeCache3 == null) {
                    gateKeeperRuntimeCache3 = new GateKeeperRuntimeCache();
                }
                ArrayList arrayList = new ArrayList(hashMap2.size());
                for (Map.Entry entry : hashMap2.entrySet()) {
                    arrayList.add(new GateKeeper((String) entry.getKey(), ((Boolean) entry.getValue()).booleanValue()));
                }
                gateKeeperRuntimeCache3.setGateKeepers(str, arrayList);
                gateKeeperRuntimeCache = gateKeeperRuntimeCache3;
                return hashMap2;
            }
        }
        return new HashMap();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0012, code lost:
        r1 = (java.lang.Boolean) r2.get(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean getGateKeeperForKey(java.lang.String r1, java.lang.String r2, boolean r3) {
        /*
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            com.facebook.internal.FetchedAppGateKeepersManager r0 = INSTANCE
            java.util.Map r2 = r0.getGateKeepersForApplication(r2)
            boolean r0 = r2.containsKey(r1)
            if (r0 != 0) goto L_0x0012
            goto L_0x001e
        L_0x0012:
            java.lang.Object r1 = r2.get(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            if (r1 == 0) goto L_0x001e
            boolean r3 = r1.booleanValue()
        L_0x001e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.FetchedAppGateKeepersManager.getGateKeeperForKey(java.lang.String, java.lang.String, boolean):boolean");
    }

    private final JSONObject getAppGateKeepersQueryResponse(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(k.a.b, "android");
        bundle.putString(k.a.r, FacebookSdk.getSdkVersion());
        bundle.putString("fields", "gatekeepers");
        GraphRequest.Companion companion = GraphRequest.Companion;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("app/%s", Arrays.copyOf(new Object[]{"mobile_sdk_gk"}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        GraphRequest newGraphPathRequest = companion.newGraphPathRequest((AccessToken) null, format, (GraphRequest.Callback) null);
        newGraphPathRequest.setParameters(bundle);
        JSONObject jsonObject = newGraphPathRequest.executeAndWait().getJsonObject();
        return jsonObject == null ? new JSONObject() : jsonObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        r8 = r8.optJSONArray(com.google.firebase.messaging.Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final synchronized org.json.JSONObject parseAppGateKeepersFromJSON$facebook_core_release(java.lang.String r7, org.json.JSONObject r8) {
        /*
            java.lang.Class<com.facebook.internal.FetchedAppGateKeepersManager> r0 = com.facebook.internal.FetchedAppGateKeepersManager.class
            monitor-enter(r0)
            java.lang.String r1 = "applicationId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r1)     // Catch:{ all -> 0x0018 }
            java.util.Map r1 = fetchedAppGateKeepers     // Catch:{ all -> 0x0018 }
            java.lang.Object r1 = r1.get(r7)     // Catch:{ all -> 0x0018 }
            org.json.JSONObject r1 = (org.json.JSONObject) r1     // Catch:{ all -> 0x0018 }
            if (r1 != 0) goto L_0x001a
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ all -> 0x0018 }
            r1.<init>()     // Catch:{ all -> 0x0018 }
            goto L_0x001a
        L_0x0018:
            r7 = move-exception
            goto L_0x0069
        L_0x001a:
            r2 = 0
            if (r8 == 0) goto L_0x002a
            java.lang.String r3 = "data"
            org.json.JSONArray r8 = r8.optJSONArray(r3)     // Catch:{ all -> 0x0018 }
            if (r8 == 0) goto L_0x002a
            org.json.JSONObject r8 = r8.optJSONObject(r2)     // Catch:{ all -> 0x0018 }
            goto L_0x002b
        L_0x002a:
            r8 = 0
        L_0x002b:
            if (r8 != 0) goto L_0x0032
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ all -> 0x0018 }
            r8.<init>()     // Catch:{ all -> 0x0018 }
        L_0x0032:
            java.lang.String r3 = "gatekeepers"
            org.json.JSONArray r8 = r8.optJSONArray(r3)     // Catch:{ all -> 0x0018 }
            if (r8 != 0) goto L_0x003f
            org.json.JSONArray r8 = new org.json.JSONArray     // Catch:{ all -> 0x0018 }
            r8.<init>()     // Catch:{ all -> 0x0018 }
        L_0x003f:
            int r3 = r8.length()     // Catch:{ all -> 0x0018 }
        L_0x0043:
            if (r2 >= r3) goto L_0x0062
            org.json.JSONObject r4 = r8.getJSONObject(r2)     // Catch:{ JSONException -> 0x0059 }
            java.lang.String r5 = "key"
            java.lang.String r5 = r4.getString(r5)     // Catch:{ JSONException -> 0x0059 }
            java.lang.String r6 = "value"
            boolean r4 = r4.getBoolean(r6)     // Catch:{ JSONException -> 0x0059 }
            r1.put(r5, r4)     // Catch:{ JSONException -> 0x0059 }
            goto L_0x005f
        L_0x0059:
            r4 = move-exception
            java.lang.String r5 = "FacebookSDK"
            com.facebook.internal.Utility.logd((java.lang.String) r5, (java.lang.Exception) r4)     // Catch:{ all -> 0x0018 }
        L_0x005f:
            int r2 = r2 + 1
            goto L_0x0043
        L_0x0062:
            java.util.Map r8 = fetchedAppGateKeepers     // Catch:{ all -> 0x0018 }
            r8.put(r7, r1)     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)
            return r1
        L_0x0069:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.FetchedAppGateKeepersManager.parseAppGateKeepersFromJSON$facebook_core_release(java.lang.String, org.json.JSONObject):org.json.JSONObject");
    }

    private final boolean isTimestampValid(Long l) {
        return l != null && System.currentTimeMillis() - l.longValue() < 3600000;
    }
}
