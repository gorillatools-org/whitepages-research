package com.facebook.appevents;

import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.cloudbridge.AppEventsCAPIManager;
import com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformerWebRequests;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Logger;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AppEventQueue {
    public static final AppEventQueue INSTANCE = new AppEventQueue();
    private static final int NUM_LOG_EVENTS_TO_TRY_TO_FLUSH_AFTER = 100;
    private static final String TAG = AppEventQueue.class.getName();
    private static volatile AppEventCollection appEventCollection = new AppEventCollection();
    private static final Runnable flushRunnable = new AppEventQueue$$ExternalSyntheticLambda0();
    private static ScheduledFuture scheduledFuture;
    private static final ScheduledExecutorService singleThreadExecutor = Executors.newSingleThreadScheduledExecutor();

    private AppEventQueue() {
    }

    /* access modifiers changed from: private */
    public static final void flushRunnable$lambda$0() {
        Class<AppEventQueue> cls = AppEventQueue.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                scheduledFuture = null;
                if (AppEventsLogger.Companion.getFlushBehavior() != AppEventsLogger.FlushBehavior.EXPLICIT_ONLY) {
                    flushAndWait(FlushReason.TIMER);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void persistToDisk() {
        Class<AppEventQueue> cls = AppEventQueue.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                singleThreadExecutor.execute(new AppEventQueue$$ExternalSyntheticLambda4());
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void persistToDisk$lambda$1() {
        Class<AppEventQueue> cls = AppEventQueue.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                AppEventStore.persistEvents(appEventCollection);
                appEventCollection = new AppEventCollection();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void flush(FlushReason flushReason) {
        Class<AppEventQueue> cls = AppEventQueue.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(flushReason, "reason");
                singleThreadExecutor.execute(new AppEventQueue$$ExternalSyntheticLambda2(flushReason));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void flush$lambda$2(FlushReason flushReason) {
        Class<AppEventQueue> cls = AppEventQueue.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(flushReason, "$reason");
                flushAndWait(flushReason);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void add(AccessTokenAppIdPair accessTokenAppIdPair, AppEvent appEvent) {
        Class<AppEventQueue> cls = AppEventQueue.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(accessTokenAppIdPair, "accessTokenAppId");
                Intrinsics.checkNotNullParameter(appEvent, "appEvent");
                singleThreadExecutor.execute(new AppEventQueue$$ExternalSyntheticLambda1(accessTokenAppIdPair, appEvent));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void add$lambda$3(AccessTokenAppIdPair accessTokenAppIdPair, AppEvent appEvent) {
        Class<AppEventQueue> cls = AppEventQueue.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(accessTokenAppIdPair, "$accessTokenAppId");
                Intrinsics.checkNotNullParameter(appEvent, "$appEvent");
                appEventCollection.addEvent(accessTokenAppIdPair, appEvent);
                if (AppEventsLogger.Companion.getFlushBehavior() != AppEventsLogger.FlushBehavior.EXPLICIT_ONLY && appEventCollection.getEventCount() > NUM_LOG_EVENTS_TO_TRY_TO_FLUSH_AFTER) {
                    flushAndWait(FlushReason.EVENT_THRESHOLD);
                } else if (scheduledFuture == null) {
                    scheduledFuture = singleThreadExecutor.schedule(flushRunnable, 15, TimeUnit.SECONDS);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final Set getKeySet() {
        Class<AppEventQueue> cls = AppEventQueue.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return appEventCollection.keySet();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void flushAndWait(com.facebook.appevents.FlushReason r4) {
        /*
            java.lang.Class<com.facebook.appevents.AppEventQueue> r0 = com.facebook.appevents.AppEventQueue.class
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r0)
            if (r1 == 0) goto L_0x0009
            return
        L_0x0009:
            java.lang.String r1 = "reason"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r1)     // Catch:{ all -> 0x0044 }
            com.facebook.appevents.PersistedEvents r1 = com.facebook.appevents.AppEventDiskStore.readAndClearStore()     // Catch:{ all -> 0x0044 }
            com.facebook.appevents.AppEventCollection r2 = appEventCollection     // Catch:{ all -> 0x0044 }
            r2.addPersistedEvents(r1)     // Catch:{ all -> 0x0044 }
            com.facebook.appevents.AppEventCollection r1 = appEventCollection     // Catch:{ Exception -> 0x0047 }
            com.facebook.appevents.FlushStatistics r4 = sendEventsToServer(r4, r1)     // Catch:{ Exception -> 0x0047 }
            if (r4 == 0) goto L_0x0046
            android.content.Intent r1 = new android.content.Intent     // Catch:{ all -> 0x0044 }
            java.lang.String r2 = "com.facebook.sdk.APP_EVENTS_FLUSHED"
            r1.<init>(r2)     // Catch:{ all -> 0x0044 }
            java.lang.String r2 = "com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED"
            int r3 = r4.getNumEvents()     // Catch:{ all -> 0x0044 }
            r1.putExtra(r2, r3)     // Catch:{ all -> 0x0044 }
            java.lang.String r2 = "com.facebook.sdk.APP_EVENTS_FLUSH_RESULT"
            com.facebook.appevents.FlushResult r4 = r4.getResult()     // Catch:{ all -> 0x0044 }
            r1.putExtra(r2, r4)     // Catch:{ all -> 0x0044 }
            android.content.Context r4 = com.facebook.FacebookSdk.getApplicationContext()     // Catch:{ all -> 0x0044 }
            androidx.localbroadcastmanager.content.LocalBroadcastManager r4 = androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(r4)     // Catch:{ all -> 0x0044 }
            r4.sendBroadcast(r1)     // Catch:{ all -> 0x0044 }
            goto L_0x0046
        L_0x0044:
            r4 = move-exception
            goto L_0x0050
        L_0x0046:
            return
        L_0x0047:
            r4 = move-exception
            java.lang.String r1 = TAG     // Catch:{ all -> 0x0044 }
            java.lang.String r2 = "Caught unexpected exception while flushing app events: "
            android.util.Log.w(r1, r2, r4)     // Catch:{ all -> 0x0044 }
            return
        L_0x0050:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r4, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.AppEventQueue.flushAndWait(com.facebook.appevents.FlushReason):void");
    }

    public static final FlushStatistics sendEventsToServer(FlushReason flushReason, AppEventCollection appEventCollection2) {
        Class<AppEventQueue> cls = AppEventQueue.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(flushReason, "reason");
            Intrinsics.checkNotNullParameter(appEventCollection2, "appEventCollection");
            FlushStatistics flushStatistics = new FlushStatistics();
            List<GraphRequest> buildRequests = buildRequests(appEventCollection2, flushStatistics);
            if (buildRequests.isEmpty()) {
                return null;
            }
            Logger.Companion companion = Logger.Companion;
            LoggingBehavior loggingBehavior = LoggingBehavior.APP_EVENTS;
            String str = TAG;
            Intrinsics.checkNotNullExpressionValue(str, "TAG");
            companion.log(loggingBehavior, str, "Flushing %d events due to %s.", Integer.valueOf(flushStatistics.getNumEvents()), flushReason.toString());
            for (GraphRequest executeAndWait : buildRequests) {
                executeAndWait.executeAndWait();
            }
            return flushStatistics;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final List buildRequests(AppEventCollection appEventCollection2, FlushStatistics flushStatistics) {
        Class<AppEventQueue> cls = AppEventQueue.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(appEventCollection2, "appEventCollection");
            Intrinsics.checkNotNullParameter(flushStatistics, "flushResults");
            boolean limitEventAndDataUsage = FacebookSdk.getLimitEventAndDataUsage(FacebookSdk.getApplicationContext());
            ArrayList arrayList = new ArrayList();
            for (AccessTokenAppIdPair accessTokenAppIdPair : appEventCollection2.keySet()) {
                SessionEventsState sessionEventsState = appEventCollection2.get(accessTokenAppIdPair);
                if (sessionEventsState != null) {
                    GraphRequest buildRequestForSession = buildRequestForSession(accessTokenAppIdPair, sessionEventsState, limitEventAndDataUsage, flushStatistics);
                    if (buildRequestForSession != null) {
                        arrayList.add(buildRequestForSession);
                        if (AppEventsCAPIManager.INSTANCE.isEnabled$facebook_core_release()) {
                            AppEventsConversionsAPITransformerWebRequests.transformGraphRequestAndSendToCAPIGEndPoint(buildRequestForSession);
                        }
                    }
                } else {
                    throw new IllegalStateException("Required value was null.");
                }
            }
            return arrayList;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final GraphRequest buildRequestForSession(AccessTokenAppIdPair accessTokenAppIdPair, SessionEventsState sessionEventsState, boolean z, FlushStatistics flushStatistics) {
        Class<AppEventQueue> cls = AppEventQueue.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(accessTokenAppIdPair, "accessTokenAppId");
            Intrinsics.checkNotNullParameter(sessionEventsState, "appEvents");
            Intrinsics.checkNotNullParameter(flushStatistics, "flushState");
            String applicationId = accessTokenAppIdPair.getApplicationId();
            boolean z2 = false;
            FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(applicationId, false);
            GraphRequest.Companion companion = GraphRequest.Companion;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%s/activities", Arrays.copyOf(new Object[]{applicationId}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            GraphRequest newPostRequest = companion.newPostRequest((AccessToken) null, format, (JSONObject) null, (GraphRequest.Callback) null);
            newPostRequest.setForceApplicationRequest(true);
            Bundle parameters = newPostRequest.getParameters();
            if (parameters == null) {
                parameters = new Bundle();
            }
            parameters.putString("access_token", accessTokenAppIdPair.getAccessTokenString());
            String pushNotificationsRegistrationId = InternalAppEventsLogger.Companion.getPushNotificationsRegistrationId();
            if (pushNotificationsRegistrationId != null) {
                parameters.putString("device_token", pushNotificationsRegistrationId);
            }
            String installReferrer = AppEventsLoggerImpl.Companion.getInstallReferrer();
            if (installReferrer != null) {
                parameters.putString("install_referrer", installReferrer);
            }
            newPostRequest.setParameters(parameters);
            if (queryAppSettings != null) {
                z2 = queryAppSettings.supportsImplicitLogging();
            }
            int populateRequest = sessionEventsState.populateRequest(newPostRequest, FacebookSdk.getApplicationContext(), z2, z);
            if (populateRequest == 0) {
                return null;
            }
            flushStatistics.setNumEvents(flushStatistics.getNumEvents() + populateRequest);
            newPostRequest.setCallback(new AppEventQueue$$ExternalSyntheticLambda3(accessTokenAppIdPair, newPostRequest, sessionEventsState, flushStatistics));
            return newPostRequest;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static final void buildRequestForSession$lambda$4(AccessTokenAppIdPair accessTokenAppIdPair, GraphRequest graphRequest, SessionEventsState sessionEventsState, FlushStatistics flushStatistics, GraphResponse graphResponse) {
        Class<AppEventQueue> cls = AppEventQueue.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(accessTokenAppIdPair, "$accessTokenAppId");
                Intrinsics.checkNotNullParameter(graphRequest, "$postRequest");
                Intrinsics.checkNotNullParameter(sessionEventsState, "$appEvents");
                Intrinsics.checkNotNullParameter(flushStatistics, "$flushState");
                Intrinsics.checkNotNullParameter(graphResponse, "response");
                handleResponse(accessTokenAppIdPair, graphRequest, graphResponse, sessionEventsState, flushStatistics);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void handleResponse(AccessTokenAppIdPair accessTokenAppIdPair, GraphRequest graphRequest, GraphResponse graphResponse, SessionEventsState sessionEventsState, FlushStatistics flushStatistics) {
        String str;
        Class<AppEventQueue> cls = AppEventQueue.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(accessTokenAppIdPair, "accessTokenAppId");
                Intrinsics.checkNotNullParameter(graphRequest, "request");
                Intrinsics.checkNotNullParameter(graphResponse, "response");
                Intrinsics.checkNotNullParameter(sessionEventsState, "appEvents");
                Intrinsics.checkNotNullParameter(flushStatistics, "flushState");
                FacebookRequestError error = graphResponse.getError();
                String str2 = "Success";
                FlushResult flushResult = FlushResult.SUCCESS;
                if (error != null) {
                    if (error.getErrorCode() == -1) {
                        str2 = "Failed: No Connectivity";
                        flushResult = FlushResult.NO_CONNECTIVITY;
                    } else {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        str2 = String.format("Failed:\n  Response: %s\n  Error %s", Arrays.copyOf(new Object[]{graphResponse.toString(), error.toString()}, 2));
                        Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
                        flushResult = FlushResult.SERVER_ERROR;
                    }
                }
                if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.APP_EVENTS)) {
                    try {
                        str = new JSONArray((String) graphRequest.getTag()).toString(2);
                        Intrinsics.checkNotNullExpressionValue(str, "{\n            val jsonAr…y.toString(2)\n          }");
                    } catch (JSONException unused) {
                        str = "<Can't encode events for debug logging>";
                    }
                    Logger.Companion companion = Logger.Companion;
                    LoggingBehavior loggingBehavior = LoggingBehavior.APP_EVENTS;
                    String str3 = TAG;
                    Intrinsics.checkNotNullExpressionValue(str3, "TAG");
                    companion.log(loggingBehavior, str3, "Flush completed\nParams: %s\n  Result: %s\n  Events JSON: %s", String.valueOf(graphRequest.getGraphObject()), str2, str);
                }
                sessionEventsState.clearInFlightAndStats(error != null);
                FlushResult flushResult2 = FlushResult.NO_CONNECTIVITY;
                if (flushResult == flushResult2) {
                    FacebookSdk.getExecutor().execute(new AppEventQueue$$ExternalSyntheticLambda5(accessTokenAppIdPair, sessionEventsState));
                }
                if (flushResult != FlushResult.SUCCESS && flushStatistics.getResult() != flushResult2) {
                    flushStatistics.setResult(flushResult);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void handleResponse$lambda$5(AccessTokenAppIdPair accessTokenAppIdPair, SessionEventsState sessionEventsState) {
        Class<AppEventQueue> cls = AppEventQueue.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(accessTokenAppIdPair, "$accessTokenAppId");
                Intrinsics.checkNotNullParameter(sessionEventsState, "$appEvents");
                AppEventStore.persistEvents(accessTokenAppIdPair, sessionEventsState);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }
}
