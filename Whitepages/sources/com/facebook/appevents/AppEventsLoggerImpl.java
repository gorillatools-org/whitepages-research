package com.facebook.appevents;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.gps.ara.GpsAraTriggersManager;
import com.facebook.appevents.gps.pa.PACustomAudienceClient;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import com.facebook.appevents.ondeviceprocessing.OnDeviceProcessingManager;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.InstallReferrerUtil;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.salesforce.marketingcloud.messages.iam.j;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class AppEventsLoggerImpl {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG;
    private static String anonymousAppDeviceGUID;
    private static ScheduledThreadPoolExecutor backgroundExecutor;
    private static AppEventsLogger.FlushBehavior flushBehaviorField = AppEventsLogger.FlushBehavior.AUTO;
    private static boolean isActivateAppEventRequested;
    private static String pushNotificationsRegistrationIdField;
    private static final Object staticLock = new Object();
    private AccessTokenAppIdPair accessTokenAppId;
    private final String contextName;

    public AppEventsLoggerImpl(String str, String str2, AccessToken accessToken) {
        Intrinsics.checkNotNullParameter(str, "activityName");
        Validate.sdkInitialized();
        this.contextName = str;
        accessToken = accessToken == null ? AccessToken.Companion.getCurrentAccessToken() : accessToken;
        if (accessToken == null || accessToken.isExpired() || (str2 != null && !Intrinsics.areEqual((Object) str2, (Object) accessToken.getApplicationId()))) {
            str2 = str2 == null ? Utility.getMetadataApplicationId(FacebookSdk.getApplicationContext()) : str2;
            if (str2 != null) {
                this.accessTokenAppId = new AccessTokenAppIdPair((String) null, str2);
            } else {
                throw new IllegalStateException("Required value was null.");
            }
        } else {
            this.accessTokenAppId = new AccessTokenAppIdPair(accessToken);
        }
        Companion.initializeTimersIfNeeded();
    }

    public static final /* synthetic */ String access$getAnonymousAppDeviceGUID$cp() {
        Class<AppEventsLoggerImpl> cls = AppEventsLoggerImpl.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return anonymousAppDeviceGUID;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ ScheduledThreadPoolExecutor access$getBackgroundExecutor$cp() {
        Class<AppEventsLoggerImpl> cls = AppEventsLoggerImpl.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return backgroundExecutor;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ AppEventsLogger.FlushBehavior access$getFlushBehaviorField$cp() {
        Class<AppEventsLoggerImpl> cls = AppEventsLoggerImpl.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return flushBehaviorField;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ String access$getPushNotificationsRegistrationIdField$cp() {
        Class<AppEventsLoggerImpl> cls = AppEventsLoggerImpl.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return pushNotificationsRegistrationIdField;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ Object access$getStaticLock$cp() {
        Class<AppEventsLoggerImpl> cls = AppEventsLoggerImpl.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return staticLock;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ boolean access$isActivateAppEventRequested$cp() {
        Class<AppEventsLoggerImpl> cls = AppEventsLoggerImpl.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            return isActivateAppEventRequested;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    public static final /* synthetic */ void access$setActivateAppEventRequested$cp(boolean z) {
        Class<AppEventsLoggerImpl> cls = AppEventsLoggerImpl.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                isActivateAppEventRequested = z;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final /* synthetic */ void access$setAnonymousAppDeviceGUID$cp(String str) {
        Class<AppEventsLoggerImpl> cls = AppEventsLoggerImpl.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                anonymousAppDeviceGUID = str;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final /* synthetic */ void access$setBackgroundExecutor$cp(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        Class<AppEventsLoggerImpl> cls = AppEventsLoggerImpl.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                backgroundExecutor = scheduledThreadPoolExecutor;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public AppEventsLoggerImpl(Context context, String str, AccessToken accessToken) {
        this(Utility.getActivityName(context), str, accessToken);
    }

    public final void logEvent(String str, Bundle bundle) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                logEvent$default(this, str, (Double) null, bundle, false, ActivityLifecycleTracker.getCurrentSessionGuid(), (OperationalData) null, 32, (Object) null);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final void logEvent(String str, double d, Bundle bundle) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                logEvent$default(this, str, Double.valueOf(d), bundle, false, ActivityLifecycleTracker.getCurrentSessionGuid(), (OperationalData) null, 32, (Object) null);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final void logEventFromSE(String str, String str2) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("_is_suggested_event", "1");
                bundle.putString("_button_text", str2);
                logEvent(str, bundle);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final void logPurchaseImplicitly(BigDecimal bigDecimal, Currency currency, Bundle bundle, OperationalData operationalData) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                logPurchase(bigDecimal, currency, bundle, true, operationalData);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final void logPurchase(BigDecimal bigDecimal, Currency currency, Bundle bundle, boolean z, OperationalData operationalData) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            if (bigDecimal == null) {
                try {
                    Companion.notifyDeveloperError("purchaseAmount cannot be null");
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, this);
                }
            } else if (currency == null) {
                Companion.notifyDeveloperError("currency cannot be null");
            } else {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                Bundle bundle2 = bundle;
                bundle2.putString("fb_currency", currency.getCurrencyCode());
                logEvent("fb_mobile_purchase", Double.valueOf(bigDecimal.doubleValue()), bundle2, z, ActivityLifecycleTracker.getCurrentSessionGuid(), operationalData);
                Companion.eagerFlush();
            }
        }
    }

    public final void flush() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                AppEventQueue.flush(FlushReason.EXPLICIT);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final void logEventImplicitly(String str, Double d, Bundle bundle) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                logEvent$default(this, str, d, bundle, true, ActivityLifecycleTracker.getCurrentSessionGuid(), (OperationalData) null, 32, (Object) null);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final void logEventImplicitly(String str, BigDecimal bigDecimal, Currency currency, Bundle bundle, OperationalData operationalData) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            if (bigDecimal == null || currency == null) {
                Utility.logd(TAG, "purchaseAmount and currency cannot be null");
                return;
            }
            if (bundle == null) {
                try {
                    bundle = new Bundle();
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, this);
                    return;
                }
            }
            Bundle bundle2 = bundle;
            bundle2.putString("fb_currency", currency.getCurrencyCode());
            logEvent(str, Double.valueOf(bigDecimal.doubleValue()), bundle2, true, ActivityLifecycleTracker.getCurrentSessionGuid(), operationalData);
        }
    }

    public static /* synthetic */ void logEvent$default(AppEventsLoggerImpl appEventsLoggerImpl, String str, Double d, Bundle bundle, boolean z, UUID uuid, OperationalData operationalData, int i, Object obj) {
        Class<AppEventsLoggerImpl> cls = AppEventsLoggerImpl.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            if ((i & 32) != 0) {
                operationalData = null;
            }
            try {
                appEventsLoggerImpl.logEvent(str, d, bundle, z, uuid, operationalData);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ba A[SYNTHETIC, Splitter:B:41:0x00ba] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c8 A[Catch:{ JSONException -> 0x00f1, FacebookException -> 0x00ef, all -> 0x003f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void logEvent(java.lang.String r16, java.lang.Double r17, android.os.Bundle r18, boolean r19, java.util.UUID r20, com.facebook.appevents.OperationalData r21) {
        /*
            r15 = this;
            r1 = r15
            r0 = r16
            r2 = r18
            r7 = r19
            r3 = r21
            java.lang.String r4 = "fb_mobile_purchase"
            boolean r5 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r15)
            if (r5 == 0) goto L_0x0012
            return
        L_0x0012:
            if (r0 == 0) goto L_0x0142
            int r5 = r16.length()     // Catch:{ all -> 0x003f }
            if (r5 != 0) goto L_0x001c
            goto L_0x0142
        L_0x001c:
            r5 = 0
            if (r7 != 0) goto L_0x003c
            boolean r6 = com.facebook.appevents.internal.AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()     // Catch:{ all -> 0x003f }
            if (r6 == 0) goto L_0x003c
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r4)     // Catch:{ all -> 0x003f }
            java.lang.String r8 = "StartTrial"
            java.lang.String r9 = "Subscribe"
            if (r6 != 0) goto L_0x0042
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r9)     // Catch:{ all -> 0x003f }
            if (r6 != 0) goto L_0x0042
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r8)     // Catch:{ all -> 0x003f }
            if (r6 == 0) goto L_0x003c
            goto L_0x0042
        L_0x003c:
            r6 = r17
            goto L_0x00ac
        L_0x003f:
            r0 = move-exception
            goto L_0x013f
        L_0x0042:
            java.lang.String r6 = TAG     // Catch:{ all -> 0x003f }
            java.lang.String r10 = "You are logging purchase events while auto-logging of in-app purchase is enabled in the SDK. Make sure you don't log duplicate events"
            android.util.Log.w(r6, r10)     // Catch:{ all -> 0x003f }
            com.facebook.internal.FeatureManager$Feature r6 = com.facebook.internal.FeatureManager.Feature.AndroidManualImplicitPurchaseDedupe     // Catch:{ all -> 0x003f }
            boolean r6 = com.facebook.internal.FeatureManager.isEnabled(r6)     // Catch:{ all -> 0x003f }
            if (r6 == 0) goto L_0x0057
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r4)     // Catch:{ all -> 0x003f }
            if (r4 != 0) goto L_0x006b
        L_0x0057:
            com.facebook.internal.FeatureManager$Feature r4 = com.facebook.internal.FeatureManager.Feature.AndroidManualImplicitSubsDedupe     // Catch:{ all -> 0x003f }
            boolean r4 = com.facebook.internal.FeatureManager.isEnabled(r4)     // Catch:{ all -> 0x003f }
            if (r4 == 0) goto L_0x003c
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r9)     // Catch:{ all -> 0x003f }
            if (r4 != 0) goto L_0x006b
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r8)     // Catch:{ all -> 0x003f }
            if (r4 == 0) goto L_0x003c
        L_0x006b:
            com.facebook.appevents.iap.InAppPurchaseDedupeConfig r4 = com.facebook.appevents.iap.InAppPurchaseDedupeConfig.INSTANCE     // Catch:{ all -> 0x003f }
            r6 = r17
            java.lang.Double r8 = r4.getValueOfManualEvent(r6, r2)     // Catch:{ all -> 0x003f }
            java.util.Currency r9 = r4.getCurrencyOfManualEvent(r2)     // Catch:{ all -> 0x003f }
            if (r8 == 0) goto L_0x00ac
            if (r9 == 0) goto L_0x00ac
            com.facebook.appevents.iap.InAppPurchase r10 = new com.facebook.appevents.iap.InAppPurchase     // Catch:{ all -> 0x003f }
            double r11 = r8.doubleValue()     // Catch:{ all -> 0x003f }
            r10.<init>(r0, r11, r9)     // Catch:{ all -> 0x003f }
            java.util.List r8 = kotlin.collections.CollectionsKt.listOf(r10)     // Catch:{ all -> 0x003f }
            long r9 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x003f }
            kotlin.Pair r11 = new kotlin.Pair     // Catch:{ all -> 0x003f }
            r11.<init>(r2, r3)     // Catch:{ all -> 0x003f }
            java.util.List r11 = kotlin.collections.CollectionsKt.listOf(r11)     // Catch:{ all -> 0x003f }
            android.os.Bundle r8 = com.facebook.appevents.iap.InAppPurchaseManager.performDedupe(r8, r9, r5, r11)     // Catch:{ all -> 0x003f }
            kotlin.Pair r2 = r4.addDedupeParameters(r8, r2, r3)     // Catch:{ all -> 0x003f }
            java.lang.Object r3 = r2.component1()     // Catch:{ all -> 0x003f }
            android.os.Bundle r3 = (android.os.Bundle) r3     // Catch:{ all -> 0x003f }
            java.lang.Object r2 = r2.component2()     // Catch:{ all -> 0x003f }
            com.facebook.appevents.OperationalData r2 = (com.facebook.appevents.OperationalData) r2     // Catch:{ all -> 0x003f }
            r14 = r3
            r3 = r2
            r2 = r14
        L_0x00ac:
            java.lang.String r4 = "app_events_killswitch"
            java.lang.String r8 = com.facebook.FacebookSdk.getApplicationId()     // Catch:{ all -> 0x003f }
            boolean r4 = com.facebook.internal.FetchedAppGateKeepersManager.getGateKeeperForKey(r4, r8, r5)     // Catch:{ all -> 0x003f }
            java.lang.String r11 = "AppEvents"
            if (r4 == 0) goto L_0x00c8
            com.facebook.internal.Logger$Companion r2 = com.facebook.internal.Logger.Companion     // Catch:{ all -> 0x003f }
            com.facebook.LoggingBehavior r3 = com.facebook.LoggingBehavior.APP_EVENTS     // Catch:{ all -> 0x003f }
            java.lang.String r4 = "KillSwitch is enabled and fail to log app event: %s"
            java.lang.Object[] r0 = new java.lang.Object[]{r16}     // Catch:{ all -> 0x003f }
            r2.log((com.facebook.LoggingBehavior) r3, (java.lang.String) r11, (java.lang.String) r4, (java.lang.Object[]) r0)     // Catch:{ all -> 0x003f }
            return
        L_0x00c8:
            boolean r4 = com.facebook.appevents.integrity.BlocklistEventsManager.isInBlocklist(r16)     // Catch:{ all -> 0x003f }
            if (r4 == 0) goto L_0x00cf
            return
        L_0x00cf:
            com.facebook.appevents.AppEventsLoggerImpl$Companion r12 = Companion     // Catch:{ all -> 0x003f }
            kotlin.Pair r2 = r12.addImplicitPurchaseParameters(r2, r3, r7)     // Catch:{ all -> 0x003f }
            java.lang.Object r3 = r2.component1()     // Catch:{ all -> 0x003f }
            r8 = r3
            android.os.Bundle r8 = (android.os.Bundle) r8     // Catch:{ all -> 0x003f }
            java.lang.Object r2 = r2.component2()     // Catch:{ all -> 0x003f }
            r10 = r2
            com.facebook.appevents.OperationalData r10 = (com.facebook.appevents.OperationalData) r10     // Catch:{ all -> 0x003f }
            com.facebook.appevents.integrity.ProtectedModeManager r2 = com.facebook.appevents.integrity.ProtectedModeManager.INSTANCE     // Catch:{ JSONException -> 0x00f1, FacebookException -> 0x00ef }
            boolean r2 = r2.protectedModeIsApplied(r8)     // Catch:{ JSONException -> 0x00f1, FacebookException -> 0x00ef }
            if (r2 != 0) goto L_0x00f3
            com.facebook.appevents.integrity.SensitiveParamsManager.processFilterSensitiveParams(r8, r0)     // Catch:{ JSONException -> 0x00f1, FacebookException -> 0x00ef }
            goto L_0x00f3
        L_0x00ef:
            r0 = move-exception
            goto L_0x011b
        L_0x00f1:
            r0 = move-exception
            goto L_0x012d
        L_0x00f3:
            com.facebook.appevents.integrity.BannedParamManager.processFilterBannedParams(r8)     // Catch:{ JSONException -> 0x00f1, FacebookException -> 0x00ef }
            com.facebook.appevents.integrity.MACARuleMatchingManager.processParameters(r8, r0)     // Catch:{ JSONException -> 0x00f1, FacebookException -> 0x00ef }
            com.facebook.appevents.integrity.StdParamsEnforcementManager.processFilterParamSchemaBlocking(r8)     // Catch:{ JSONException -> 0x00f1, FacebookException -> 0x00ef }
            com.facebook.appevents.integrity.ProtectedModeManager.processParametersForProtectedMode(r8)     // Catch:{ JSONException -> 0x00f1, FacebookException -> 0x00ef }
            com.facebook.appevents.AppEvent r13 = new com.facebook.appevents.AppEvent     // Catch:{ JSONException -> 0x00f1, FacebookException -> 0x00ef }
            java.lang.String r3 = r1.contextName     // Catch:{ JSONException -> 0x00f1, FacebookException -> 0x00ef }
            boolean r9 = com.facebook.appevents.internal.ActivityLifecycleTracker.isInBackground()     // Catch:{ JSONException -> 0x00f1, FacebookException -> 0x00ef }
            r2 = r13
            r4 = r16
            r5 = r17
            r6 = r8
            r7 = r19
            r8 = r9
            r9 = r20
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ JSONException -> 0x00f1, FacebookException -> 0x00ef }
            com.facebook.appevents.AccessTokenAppIdPair r0 = r1.accessTokenAppId     // Catch:{ JSONException -> 0x00f1, FacebookException -> 0x00ef }
            r12.logEvent(r13, r0)     // Catch:{ JSONException -> 0x00f1, FacebookException -> 0x00ef }
            goto L_0x013e
        L_0x011b:
            com.facebook.internal.Logger$Companion r2 = com.facebook.internal.Logger.Companion     // Catch:{ all -> 0x003f }
            com.facebook.LoggingBehavior r3 = com.facebook.LoggingBehavior.APP_EVENTS     // Catch:{ all -> 0x003f }
            java.lang.String r4 = "Invalid app event: %s"
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x003f }
            java.lang.Object[] r0 = new java.lang.Object[]{r0}     // Catch:{ all -> 0x003f }
            r2.log((com.facebook.LoggingBehavior) r3, (java.lang.String) r11, (java.lang.String) r4, (java.lang.Object[]) r0)     // Catch:{ all -> 0x003f }
            goto L_0x013e
        L_0x012d:
            com.facebook.internal.Logger$Companion r2 = com.facebook.internal.Logger.Companion     // Catch:{ all -> 0x003f }
            com.facebook.LoggingBehavior r3 = com.facebook.LoggingBehavior.APP_EVENTS     // Catch:{ all -> 0x003f }
            java.lang.String r4 = "JSON encoding for app event failed: '%s'"
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x003f }
            java.lang.Object[] r0 = new java.lang.Object[]{r0}     // Catch:{ all -> 0x003f }
            r2.log((com.facebook.LoggingBehavior) r3, (java.lang.String) r11, (java.lang.String) r4, (java.lang.Object[]) r0)     // Catch:{ all -> 0x003f }
        L_0x013e:
            return
        L_0x013f:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r0, r15)
        L_0x0142:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.AppEventsLoggerImpl.logEvent(java.lang.String, java.lang.Double, android.os.Bundle, boolean, java.util.UUID, com.facebook.appevents.OperationalData):void");
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AppEventsLogger.FlushBehavior getFlushBehavior() {
            AppEventsLogger.FlushBehavior access$getFlushBehaviorField$cp;
            synchronized (AppEventsLoggerImpl.access$getStaticLock$cp()) {
                access$getFlushBehaviorField$cp = AppEventsLoggerImpl.access$getFlushBehaviorField$cp();
            }
            return access$getFlushBehaviorField$cp;
        }

        public final String getPushNotificationsRegistrationId() {
            String access$getPushNotificationsRegistrationIdField$cp;
            synchronized (AppEventsLoggerImpl.access$getStaticLock$cp()) {
                access$getPushNotificationsRegistrationIdField$cp = AppEventsLoggerImpl.access$getPushNotificationsRegistrationIdField$cp();
            }
            return access$getPushNotificationsRegistrationIdField$cp;
        }

        public final void activateApp(Application application, String str) {
            Intrinsics.checkNotNullParameter(application, "application");
            if (FacebookSdk.isInitialized()) {
                AnalyticsUserIDStore.initStore();
                UserDataStore.initStore();
                if (str == null) {
                    str = FacebookSdk.getApplicationId();
                }
                FacebookSdk.publishInstallAsync(application, str);
                ActivityLifecycleTracker.startTracking(application, str);
                if (FeatureManager.isEnabled(FeatureManager.Feature.GPSPACAProcessing)) {
                    PACustomAudienceClient.INSTANCE.joinCustomAudience(str, "fb_mobile_app_install");
                }
                if (FeatureManager.isEnabled(FeatureManager.Feature.GPSARATriggers)) {
                    GpsAraTriggersManager.INSTANCE.registerTriggerAsync(str, new AppEvent(j.h, "MOBILE_INSTALL_EVENT", (Double) null, (Bundle) null, false, ActivityLifecycleTracker.isInBackground(), ActivityLifecycleTracker.getCurrentSessionGuid(), (OperationalData) null));
                    return;
                }
                return;
            }
            throw new FacebookException("The Facebook sdk must be initialized before calling activateApp");
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x0079  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x007b  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final kotlin.Pair addImplicitPurchaseParameters(android.os.Bundle r17, com.facebook.appevents.OperationalData r18, boolean r19) {
            /*
                r16 = this;
                r6 = r17
                boolean r0 = com.facebook.appevents.internal.AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()
                java.lang.String r7 = "0"
                java.lang.String r8 = "1"
                if (r0 == 0) goto L_0x000e
                r3 = r8
                goto L_0x000f
            L_0x000e:
                r3 = r7
            L_0x000f:
                com.facebook.appevents.OperationalData$Companion r15 = com.facebook.appevents.OperationalData.Companion
                com.facebook.appevents.OperationalDataEnum r14 = com.facebook.appevents.OperationalDataEnum.IAPParameters
                java.lang.String r2 = "is_implicit_purchase_logging_enabled"
                r0 = r15
                r1 = r14
                r4 = r17
                r5 = r18
                kotlin.Pair r0 = r0.addParameterAndReturn(r1, r2, r3, r4, r5)
                java.lang.String r1 = "fb_iap_product_id"
                java.lang.Object r1 = r15.getParameter(r14, r1, r6, r5)
                boolean r2 = r1 instanceof java.lang.String
                r3 = 0
                if (r2 == 0) goto L_0x002e
                java.lang.String r1 = (java.lang.String) r1
                r4 = r1
                goto L_0x002f
            L_0x002e:
                r4 = r3
            L_0x002f:
                if (r19 != 0) goto L_0x0064
                if (r6 == 0) goto L_0x0039
                java.lang.String r1 = "fb_content_id"
                java.lang.String r3 = r6.getString(r1)
            L_0x0039:
                if (r3 != 0) goto L_0x0064
                if (r4 == 0) goto L_0x0064
                java.lang.String r2 = "fb_content_id"
                r0 = r15
                r1 = r14
                r3 = r4
                r4 = r17
                r5 = r18
                kotlin.Pair r0 = r0.addParameterAndReturn(r1, r2, r3, r4, r5)
                java.lang.Object r1 = r0.getFirst()
                r13 = r1
                android.os.Bundle r13 = (android.os.Bundle) r13
                java.lang.Object r0 = r0.getSecond()
                com.facebook.appevents.OperationalData r0 = (com.facebook.appevents.OperationalData) r0
                java.lang.String r11 = "android_dynamic_ads_content_id"
                java.lang.String r12 = "client_manual"
                r9 = r15
                r10 = r14
                r1 = r14
                r14 = r0
                kotlin.Pair r0 = r9.addParameterAndReturn(r10, r11, r12, r13, r14)
                goto L_0x0065
            L_0x0064:
                r1 = r14
            L_0x0065:
                java.lang.Object r2 = r0.getFirst()
                r13 = r2
                android.os.Bundle r13 = (android.os.Bundle) r13
                java.lang.Object r0 = r0.getSecond()
                r14 = r0
                com.facebook.appevents.OperationalData r14 = (com.facebook.appevents.OperationalData) r14
                boolean r0 = com.facebook.UserSettingsManager.getAutoLogAppEventsEnabled()
                if (r0 == 0) goto L_0x007b
                r12 = r8
                goto L_0x007c
            L_0x007b:
                r12 = r7
            L_0x007c:
                java.lang.String r11 = "is_autolog_app_events_enabled"
                r9 = r15
                r10 = r1
                kotlin.Pair r0 = r9.addParameterAndReturn(r10, r11, r12, r13, r14)
                java.lang.Object r1 = r0.getFirst()
                android.os.Bundle r1 = (android.os.Bundle) r1
                java.lang.Object r0 = r0.getSecond()
                com.facebook.appevents.OperationalData r0 = (com.facebook.appevents.OperationalData) r0
                kotlin.Pair r2 = new kotlin.Pair
                r2.<init>(r1, r0)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.AppEventsLoggerImpl.Companion.addImplicitPurchaseParameters(android.os.Bundle, com.facebook.appevents.OperationalData, boolean):kotlin.Pair");
        }

        public final void initializeLib(Context context, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (FacebookSdk.getAutoLogAppEventsEnabled()) {
                AppEventsLoggerImpl appEventsLoggerImpl = new AppEventsLoggerImpl(context, str, (AccessToken) null);
                ScheduledThreadPoolExecutor access$getBackgroundExecutor$cp = AppEventsLoggerImpl.access$getBackgroundExecutor$cp();
                if (access$getBackgroundExecutor$cp != null) {
                    access$getBackgroundExecutor$cp.execute(new AppEventsLoggerImpl$Companion$$ExternalSyntheticLambda1(context, appEventsLoggerImpl));
                    return;
                }
                throw new IllegalStateException("Required value was null.");
            }
        }

        /* access modifiers changed from: private */
        public static final void initializeLib$lambda$4(Context context, AppEventsLoggerImpl appEventsLoggerImpl) {
            Intrinsics.checkNotNullParameter(context, "$context");
            Intrinsics.checkNotNullParameter(appEventsLoggerImpl, "$logger");
            Bundle bundle = new Bundle();
            String[] strArr = {"com.facebook.core.Core", "com.facebook.login.Login", "com.facebook.share.Share", "com.facebook.places.Places", "com.facebook.messenger.Messenger", "com.facebook.applinks.AppLinks", "com.facebook.marketing.Marketing", "com.facebook.gamingservices.GamingServices", "com.facebook.all.All", "com.android.billingclient.api.BillingClient", "com.android.vending.billing.IInAppBillingService"};
            String[] strArr2 = {"core_lib_included", "login_lib_included", "share_lib_included", "places_lib_included", "messenger_lib_included", "applinks_lib_included", "marketing_lib_included", "gamingservices_lib_included", "all_lib_included", "billing_client_lib_included", "billing_service_lib_included"};
            int i = 0;
            for (int i2 = 0; i2 < 11; i2++) {
                String str = strArr[i2];
                String str2 = strArr2[i2];
                try {
                    Class.forName(str);
                    bundle.putInt(str2, 1);
                    i |= 1 << i2;
                } catch (ClassNotFoundException unused) {
                }
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0);
            if (sharedPreferences.getInt("kitsBitmask", 0) != i) {
                sharedPreferences.edit().putInt("kitsBitmask", i).apply();
                appEventsLoggerImpl.logEventImplicitly("fb_sdk_initialize", (Double) null, bundle);
            }
        }

        public final void onContextStop() {
            AppEventQueue.persistToDisk();
        }

        public final String getInstallReferrer() {
            InstallReferrerUtil.tryUpdateReferrerInfo(new AppEventsLoggerImpl$Companion$getInstallReferrer$1());
            return FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getString("install_referrer", (String) null);
        }

        public final void setInstallReferrer(String str) {
            SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.sdk.appEventPreferences", 0);
            if (str != null) {
                sharedPreferences.edit().putString("install_referrer", str).apply();
            }
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
            r3 = new com.facebook.appevents.AppEventsLoggerImpl$Companion$$ExternalSyntheticLambda0();
            r2 = com.facebook.appevents.AppEventsLoggerImpl.access$getBackgroundExecutor$cp();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
            if (r2 == null) goto L_0x002f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
            r2.scheduleAtFixedRate(r3, 0, 86400, java.util.concurrent.TimeUnit.SECONDS);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x002e, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0036, code lost:
            throw new java.lang.IllegalStateException("Required value was null.");
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void initializeTimersIfNeeded() {
            /*
                r9 = this;
                java.lang.Object r0 = com.facebook.appevents.AppEventsLoggerImpl.access$getStaticLock$cp()
                monitor-enter(r0)
                java.util.concurrent.ScheduledThreadPoolExecutor r1 = com.facebook.appevents.AppEventsLoggerImpl.access$getBackgroundExecutor$cp()     // Catch:{ all -> 0x0037 }
                if (r1 == 0) goto L_0x000d
                monitor-exit(r0)
                return
            L_0x000d:
                java.util.concurrent.ScheduledThreadPoolExecutor r1 = new java.util.concurrent.ScheduledThreadPoolExecutor     // Catch:{ all -> 0x0037 }
                r2 = 1
                r1.<init>(r2)     // Catch:{ all -> 0x0037 }
                com.facebook.appevents.AppEventsLoggerImpl.access$setBackgroundExecutor$cp(r1)     // Catch:{ all -> 0x0037 }
                kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0037 }
                monitor-exit(r0)
                com.facebook.appevents.AppEventsLoggerImpl$Companion$$ExternalSyntheticLambda0 r3 = new com.facebook.appevents.AppEventsLoggerImpl$Companion$$ExternalSyntheticLambda0
                r3.<init>()
                java.util.concurrent.ScheduledThreadPoolExecutor r2 = com.facebook.appevents.AppEventsLoggerImpl.access$getBackgroundExecutor$cp()
                if (r2 == 0) goto L_0x002f
                r6 = 86400(0x15180, double:4.26873E-319)
                java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.SECONDS
                r4 = 0
                r2.scheduleAtFixedRate(r3, r4, r6, r8)
                return
            L_0x002f:
                java.lang.String r0 = "Required value was null."
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                r1.<init>(r0)
                throw r1
            L_0x0037:
                r1 = move-exception
                monitor-exit(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.AppEventsLoggerImpl.Companion.initializeTimersIfNeeded():void");
        }

        /* access modifiers changed from: private */
        public static final void initializeTimersIfNeeded$lambda$6() {
            HashSet<String> hashSet = new HashSet<>();
            for (AccessTokenAppIdPair applicationId : AppEventQueue.getKeySet()) {
                hashSet.add(applicationId.getApplicationId());
            }
            for (String queryAppSettings : hashSet) {
                FetchedAppSettingsManager.queryAppSettings(queryAppSettings, true);
            }
        }

        /* access modifiers changed from: private */
        public final void logEvent(AppEvent appEvent, AccessTokenAppIdPair accessTokenAppIdPair) {
            AppEventQueue.add(accessTokenAppIdPair, appEvent);
            if (FeatureManager.isEnabled(FeatureManager.Feature.OnDevicePostInstallEventProcessing) && OnDeviceProcessingManager.isOnDeviceProcessingEnabled()) {
                OnDeviceProcessingManager.sendCustomEventAsync(accessTokenAppIdPair.getApplicationId(), appEvent);
            }
            if (FeatureManager.isEnabled(FeatureManager.Feature.GPSARATriggers)) {
                GpsAraTriggersManager.INSTANCE.registerTriggerAsync(accessTokenAppIdPair.getApplicationId(), appEvent);
            }
            if (FeatureManager.isEnabled(FeatureManager.Feature.GPSPACAProcessing)) {
                PACustomAudienceClient.INSTANCE.joinCustomAudience(accessTokenAppIdPair.getApplicationId(), appEvent);
            }
            if (!appEvent.getIsImplicit() && !AppEventsLoggerImpl.access$isActivateAppEventRequested$cp()) {
                if (Intrinsics.areEqual((Object) appEvent.getName(), (Object) "fb_mobile_activate_app")) {
                    AppEventsLoggerImpl.access$setActivateAppEventRequested$cp(true);
                } else {
                    Logger.Companion.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Warning: Please call AppEventsLogger.activateApp(...)from the long-lived activity's onResume() methodbefore logging other app events.");
                }
            }
        }

        public final void eagerFlush() {
            if (getFlushBehavior() != AppEventsLogger.FlushBehavior.EXPLICIT_ONLY) {
                AppEventQueue.flush(FlushReason.EAGER_FLUSHING_EVENT);
            }
        }

        /* access modifiers changed from: private */
        public final void notifyDeveloperError(String str) {
            Logger.Companion.log(LoggingBehavior.DEVELOPER_ERRORS, "AppEvents", str);
        }

        public final Executor getAnalyticsExecutor() {
            if (AppEventsLoggerImpl.access$getBackgroundExecutor$cp() == null) {
                initializeTimersIfNeeded();
            }
            ScheduledThreadPoolExecutor access$getBackgroundExecutor$cp = AppEventsLoggerImpl.access$getBackgroundExecutor$cp();
            if (access$getBackgroundExecutor$cp != null) {
                return access$getBackgroundExecutor$cp;
            }
            throw new IllegalStateException("Required value was null.");
        }

        public final String getAnonymousAppDeviceGUID(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (AppEventsLoggerImpl.access$getAnonymousAppDeviceGUID$cp() == null) {
                synchronized (AppEventsLoggerImpl.access$getStaticLock$cp()) {
                    try {
                        if (AppEventsLoggerImpl.access$getAnonymousAppDeviceGUID$cp() == null) {
                            AppEventsLoggerImpl.access$setAnonymousAppDeviceGUID$cp(context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getString("anonymousAppDeviceGUID", (String) null));
                            if (AppEventsLoggerImpl.access$getAnonymousAppDeviceGUID$cp() == null) {
                                AppEventsLoggerImpl.access$setAnonymousAppDeviceGUID$cp("XZ" + UUID.randomUUID());
                                context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putString("anonymousAppDeviceGUID", AppEventsLoggerImpl.access$getAnonymousAppDeviceGUID$cp()).apply();
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            String access$getAnonymousAppDeviceGUID$cp = AppEventsLoggerImpl.access$getAnonymousAppDeviceGUID$cp();
            if (access$getAnonymousAppDeviceGUID$cp != null) {
                return access$getAnonymousAppDeviceGUID$cp;
            }
            throw new IllegalStateException("Required value was null.");
        }
    }

    static {
        String canonicalName = AppEventsLoggerImpl.class.getCanonicalName();
        if (canonicalName == null) {
            canonicalName = "com.facebook.appevents.AppEventsLoggerImpl";
        }
        TAG = canonicalName;
    }
}
