package com.amplitude.reactnative;

import android.content.Context;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.salesforce.marketingcloud.storage.db.k;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "AmplitudeReactNative")
public final class AmplitudeReactNativeModule extends ReactContextBaseJavaModule {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String DEVICE_ID_KEY = "device_id";
    public static final String LAST_EVENT_ID_KEY = "last_event_id";
    public static final String LAST_EVENT_TIME_KEY = "last_event_time";
    public static final String PREVIOUS_SESSION_ID_KEY = "previous_session_id";
    public static final String USER_ID_KEY = "user_id";
    private AndroidContextProvider androidContextProvider;
    private final ReactApplicationContext reactContext;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AmplitudeReactNativeModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        this.reactContext = reactApplicationContext;
    }

    public String getName() {
        return "AmplitudeReactNative";
    }

    @ReactMethod
    private final void getApplicationContext(ReadableMap readableMap, Promise promise) {
        boolean z = readableMap.hasKey("adid") ? readableMap.getBoolean("adid") : false;
        if (this.androidContextProvider == null) {
            Context applicationContext = this.reactContext.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            this.androidContextProvider = new AndroidContextProvider(applicationContext, z);
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        AndroidContextProvider androidContextProvider2 = this.androidContextProvider;
        Intrinsics.checkNotNull(androidContextProvider2);
        writableNativeMap.putString("version", androidContextProvider2.getVersionName());
        AndroidContextProvider androidContextProvider3 = this.androidContextProvider;
        Intrinsics.checkNotNull(androidContextProvider3);
        writableNativeMap.putString(k.a.b, androidContextProvider3.getPlatform());
        AndroidContextProvider androidContextProvider4 = this.androidContextProvider;
        Intrinsics.checkNotNull(androidContextProvider4);
        writableNativeMap.putString("language", androidContextProvider4.getLanguage());
        AndroidContextProvider androidContextProvider5 = this.androidContextProvider;
        Intrinsics.checkNotNull(androidContextProvider5);
        writableNativeMap.putString("country", androidContextProvider5.getCountry());
        AndroidContextProvider androidContextProvider6 = this.androidContextProvider;
        Intrinsics.checkNotNull(androidContextProvider6);
        writableNativeMap.putString("osName", androidContextProvider6.getOsName());
        AndroidContextProvider androidContextProvider7 = this.androidContextProvider;
        Intrinsics.checkNotNull(androidContextProvider7);
        writableNativeMap.putString("osVersion", androidContextProvider7.getOsVersion());
        AndroidContextProvider androidContextProvider8 = this.androidContextProvider;
        Intrinsics.checkNotNull(androidContextProvider8);
        writableNativeMap.putString("deviceBrand", androidContextProvider8.getBrand());
        AndroidContextProvider androidContextProvider9 = this.androidContextProvider;
        Intrinsics.checkNotNull(androidContextProvider9);
        writableNativeMap.putString("deviceManufacturer", androidContextProvider9.getManufacturer());
        AndroidContextProvider androidContextProvider10 = this.androidContextProvider;
        Intrinsics.checkNotNull(androidContextProvider10);
        writableNativeMap.putString("deviceModel", androidContextProvider10.getModel());
        AndroidContextProvider androidContextProvider11 = this.androidContextProvider;
        Intrinsics.checkNotNull(androidContextProvider11);
        writableNativeMap.putString("carrier", androidContextProvider11.getCarrier());
        if (z) {
            AndroidContextProvider androidContextProvider12 = this.androidContextProvider;
            Intrinsics.checkNotNull(androidContextProvider12);
            writableNativeMap.putString("adid", androidContextProvider12.getAdvertisingId());
        }
        AndroidContextProvider androidContextProvider13 = this.androidContextProvider;
        Intrinsics.checkNotNull(androidContextProvider13);
        writableNativeMap.putString("appSetId", androidContextProvider13.getAppSetId());
        promise.resolve(writableNativeMap);
    }

    @ReactMethod
    private final void getLegacySessionData(String str, Promise promise) {
        try {
            LegacyDatabaseStorageProvider legacyDatabaseStorageProvider = LegacyDatabaseStorageProvider.INSTANCE;
            Context applicationContext = this.reactContext.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            LegacyDatabaseStorage storage = legacyDatabaseStorageProvider.getStorage(applicationContext, str);
            String legacyValue = getLegacyValue(storage, "device_id");
            String legacyValue2 = getLegacyValue(storage, USER_ID_KEY);
            Long legacyLongValue = getLegacyLongValue(storage, PREVIOUS_SESSION_ID_KEY);
            Long legacyLongValue2 = getLegacyLongValue(storage, LAST_EVENT_TIME_KEY);
            Long legacyLongValue3 = getLegacyLongValue(storage, LAST_EVENT_ID_KEY);
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            if (legacyValue != null) {
                writableNativeMap.putString("deviceId", legacyValue);
            }
            if (legacyValue2 != null) {
                writableNativeMap.putString("userId", legacyValue2);
            }
            if (legacyLongValue != null) {
                writableNativeMap.putDouble("sessionId", (double) legacyLongValue.longValue());
            }
            if (legacyLongValue2 != null) {
                writableNativeMap.putDouble("lastEventTime", (double) legacyLongValue2.longValue());
            }
            if (legacyLongValue3 != null) {
                writableNativeMap.putDouble("lastEventId", (double) legacyLongValue3.longValue());
            }
            promise.resolve(writableNativeMap);
        } catch (Exception e) {
            LogcatLogger logger = LogcatLogger.Companion.getLogger();
            logger.error("can't get legacy session data: " + e);
        }
    }

    private final String getLegacyValue(LegacyDatabaseStorage legacyDatabaseStorage, String str) {
        try {
            return legacyDatabaseStorage.getValue(str);
        } catch (Exception e) {
            LogcatLogger logger = LogcatLogger.Companion.getLogger();
            logger.error("can't get legacy " + str + ": " + e);
            return null;
        }
    }

    private final Long getLegacyLongValue(LegacyDatabaseStorage legacyDatabaseStorage, String str) {
        try {
            return legacyDatabaseStorage.getLongValue(str);
        } catch (Exception e) {
            LogcatLogger logger = LogcatLogger.Companion.getLogger();
            logger.error("can't get legacy " + str + ": " + e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0065 A[Catch:{ Exception -> 0x0033 }, LOOP:0: B:23:0x005f->B:25:0x0065, LOOP_END] */
    @com.facebook.react.bridge.ReactMethod
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void getLegacyEvents(java.lang.String r4, java.lang.String r5, com.facebook.react.bridge.Promise r6) {
        /*
            r3 = this;
            com.amplitude.reactnative.LegacyDatabaseStorageProvider r0 = com.amplitude.reactnative.LegacyDatabaseStorageProvider.INSTANCE     // Catch:{ Exception -> 0x0033 }
            com.facebook.react.bridge.ReactApplicationContext r1 = r3.reactContext     // Catch:{ Exception -> 0x0033 }
            android.content.Context r1 = r1.getApplicationContext()     // Catch:{ Exception -> 0x0033 }
            java.lang.String r2 = "getApplicationContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)     // Catch:{ Exception -> 0x0033 }
            com.amplitude.reactnative.LegacyDatabaseStorage r4 = r0.getStorage(r1, r4)     // Catch:{ Exception -> 0x0033 }
            int r0 = r5.hashCode()     // Catch:{ Exception -> 0x0033 }
            r1 = -267163891(0xfffffffff013670d, float:-1.824755E29)
            if (r0 == r1) goto L_0x0043
            r1 = -135762164(0xfffffffff7e86f0c, float:-9.428634E33)
            if (r0 == r1) goto L_0x0035
            r1 = 96891546(0x5c6729a, float:1.8661928E-35)
            if (r0 == r1) goto L_0x0025
            goto L_0x004b
        L_0x0025:
            java.lang.String r0 = "event"
            boolean r0 = r5.equals(r0)     // Catch:{ Exception -> 0x0033 }
            if (r0 != 0) goto L_0x002e
            goto L_0x004b
        L_0x002e:
            java.util.List r4 = r4.readEvents()     // Catch:{ Exception -> 0x0033 }
            goto L_0x0054
        L_0x0033:
            r4 = move-exception
            goto L_0x0077
        L_0x0035:
            java.lang.String r0 = "identify"
            boolean r0 = r5.equals(r0)     // Catch:{ Exception -> 0x0033 }
            if (r0 != 0) goto L_0x003e
            goto L_0x004b
        L_0x003e:
            java.util.List r4 = r4.readIdentifies()     // Catch:{ Exception -> 0x0033 }
            goto L_0x0054
        L_0x0043:
            java.lang.String r0 = "interceptedIdentify"
            boolean r0 = r5.equals(r0)     // Catch:{ Exception -> 0x0033 }
            if (r0 != 0) goto L_0x0050
        L_0x004b:
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()     // Catch:{ Exception -> 0x0033 }
            goto L_0x0054
        L_0x0050:
            java.util.List r4 = r4.readInterceptedIdentifies()     // Catch:{ Exception -> 0x0033 }
        L_0x0054:
            com.facebook.react.bridge.WritableNativeArray r0 = new com.facebook.react.bridge.WritableNativeArray     // Catch:{ Exception -> 0x0033 }
            r0.<init>()     // Catch:{ Exception -> 0x0033 }
            java.lang.Iterable r4 = (java.lang.Iterable) r4     // Catch:{ Exception -> 0x0033 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Exception -> 0x0033 }
        L_0x005f:
            boolean r1 = r4.hasNext()     // Catch:{ Exception -> 0x0033 }
            if (r1 == 0) goto L_0x0073
            java.lang.Object r1 = r4.next()     // Catch:{ Exception -> 0x0033 }
            org.json.JSONObject r1 = (org.json.JSONObject) r1     // Catch:{ Exception -> 0x0033 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0033 }
            r0.pushString(r1)     // Catch:{ Exception -> 0x0033 }
            goto L_0x005f
        L_0x0073:
            r6.resolve(r0)     // Catch:{ Exception -> 0x0033 }
            goto L_0x00a1
        L_0x0077:
            com.amplitude.reactnative.LogcatLogger$Companion r0 = com.amplitude.reactnative.LogcatLogger.Companion
            com.amplitude.reactnative.LogcatLogger r0 = r0.getLogger()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "can't get legacy "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = "s: "
            r1.append(r5)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r0.error(r4)
            com.facebook.react.bridge.WritableNativeArray r4 = new com.facebook.react.bridge.WritableNativeArray
            r4.<init>()
            r6.resolve(r4)
        L_0x00a1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.reactnative.AmplitudeReactNativeModule.getLegacyEvents(java.lang.String, java.lang.String, com.facebook.react.bridge.Promise):void");
    }

    @ReactMethod
    private final void removeLegacyEvent(String str, String str2, int i) {
        try {
            LegacyDatabaseStorageProvider legacyDatabaseStorageProvider = LegacyDatabaseStorageProvider.INSTANCE;
            Context applicationContext = this.reactContext.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            LegacyDatabaseStorage storage = legacyDatabaseStorageProvider.getStorage(applicationContext, str);
            int hashCode = str2.hashCode();
            if (hashCode != -267163891) {
                if (hashCode != -135762164) {
                    if (hashCode == 96891546) {
                        if (str2.equals("event")) {
                            storage.removeEvent(i);
                        }
                    }
                } else if (str2.equals("identify")) {
                    storage.removeIdentify(i);
                }
            } else if (str2.equals("interceptedIdentify")) {
                storage.removeInterceptedIdentify(i);
            }
        } catch (Exception e) {
            LogcatLogger logger = LogcatLogger.Companion.getLogger();
            logger.error("can't remove legacy " + str2 + " with id=" + i + ": " + e);
        }
    }
}
