package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import com.facebook.GraphRequest;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.AppEventsLoggerImpl;
import com.facebook.appevents.AppEventsManager;
import com.facebook.appevents.internal.AppEventsLoggerUtility;
import com.facebook.appevents.ondeviceprocessing.OnDeviceProcessingManager;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.FetchedAppGateKeepersManager;
import com.facebook.internal.LockOnGetVariable;
import com.facebook.internal.Logger;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.internal.instrument.InstrumentManager;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

public final class FacebookSdk {
    public static final FacebookSdk INSTANCE = new FacebookSdk();
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final String TAG = FacebookSdk.class.getCanonicalName();
    private static volatile String appClientToken;
    private static Context applicationContext;
    private static volatile String applicationId;
    private static volatile String applicationName;
    public static boolean bypassAppSwitch;
    private static LockOnGetVariable cacheDir;
    private static int callbackRequestCodeOffset = 64206;
    private static volatile Boolean codelessDebugLogEnabled;
    private static Executor executor;
    private static volatile String facebookDomain = "facebook.com";
    private static String graphApiVersion = ServerProtocol.getDefaultAPIVersion();
    private static GraphRequestCreator graphRequestCreator = new FacebookSdk$$ExternalSyntheticLambda0();
    public static boolean hasCustomTabsPrefetching;
    public static boolean ignoreAppSwitchToLoggedOut;
    private static volatile String instagramDomain = "instagram.com";
    private static volatile boolean isDebugEnabledField;
    private static boolean isFullyInitialized;
    private static boolean isLegacyTokenUpgradeSupported;
    private static final HashSet loggingBehaviors = SetsKt.hashSetOf(LoggingBehavior.DEVELOPER_ERRORS);
    private static AtomicLong onProgressThreshold = new AtomicLong(65536);
    private static final AtomicBoolean sdkInitialized = new AtomicBoolean(false);

    public interface GraphRequestCreator {
        GraphRequest createPostRequest(AccessToken accessToken, String str, JSONObject jSONObject, GraphRequest.Callback callback);
    }

    public interface InitializeCallback {
        void onInitialized();
    }

    private FacebookSdk() {
    }

    /* access modifiers changed from: private */
    public static final GraphRequest graphRequestCreator$lambda$0(AccessToken accessToken, String str, JSONObject jSONObject, GraphRequest.Callback callback) {
        return GraphRequest.Companion.newPostRequest(accessToken, str, jSONObject, callback);
    }

    public static final Executor getExecutor() {
        ReentrantLock reentrantLock = LOCK;
        reentrantLock.lock();
        try {
            if (executor == null) {
                executor = AsyncTask.THREAD_POOL_EXECUTOR;
            }
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            Executor executor2 = executor;
            if (executor2 != null) {
                return executor2;
            }
            throw new IllegalStateException("Required value was null.");
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public static final long getOnProgressThreshold() {
        Validate.sdkInitialized();
        return onProgressThreshold.get();
    }

    public static final boolean isDebugEnabled() {
        return isDebugEnabledField;
    }

    public static final boolean isLegacyTokenUpgradeSupported() {
        return isLegacyTokenUpgradeSupported;
    }

    public static final String getGraphApiVersion() {
        String str = TAG;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("getGraphApiVersion: %s", Arrays.copyOf(new Object[]{graphApiVersion}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        Utility.logd(str, format);
        return graphApiVersion;
    }

    public static final synchronized boolean isFullyInitialized() {
        boolean z;
        synchronized (FacebookSdk.class) {
            z = isFullyInitialized;
        }
        return z;
    }

    public static final String getFacebookDomain() {
        return facebookDomain;
    }

    public static final String getFacebookGamingDomain() {
        return "fb.gg";
    }

    public static final String getInstagramDomain() {
        return instagramDomain;
    }

    public static final synchronized void sdkInitialize(Context context) {
        synchronized (FacebookSdk.class) {
            Intrinsics.checkNotNullParameter(context, "applicationContext");
            sdkInitialize(context, (InitializeCallback) null);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final synchronized void sdkInitialize(android.content.Context r4, com.facebook.FacebookSdk.InitializeCallback r5) {
        /*
            java.lang.Class<com.facebook.FacebookSdk> r0 = com.facebook.FacebookSdk.class
            monitor-enter(r0)
            java.lang.String r1 = "applicationContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r1)     // Catch:{ all -> 0x0016 }
            java.util.concurrent.atomic.AtomicBoolean r1 = sdkInitialized     // Catch:{ all -> 0x0016 }
            boolean r2 = r1.get()     // Catch:{ all -> 0x0016 }
            if (r2 == 0) goto L_0x001b
            if (r5 == 0) goto L_0x0019
            r5.onInitialized()     // Catch:{ all -> 0x0016 }
            goto L_0x0019
        L_0x0016:
            r4 = move-exception
            goto L_0x0118
        L_0x0019:
            monitor-exit(r0)
            return
        L_0x001b:
            r2 = 0
            com.facebook.internal.Validate.hasFacebookActivity(r4, r2)     // Catch:{ all -> 0x0016 }
            com.facebook.internal.Validate.hasInternetPermissions(r4, r2)     // Catch:{ all -> 0x0016 }
            android.content.Context r2 = r4.getApplicationContext()     // Catch:{ all -> 0x0016 }
            java.lang.String r3 = "applicationContext.applicationContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ all -> 0x0016 }
            applicationContext = r2     // Catch:{ all -> 0x0016 }
            com.facebook.appevents.AppEventsLogger$Companion r2 = com.facebook.appevents.AppEventsLogger.Companion     // Catch:{ all -> 0x0016 }
            r2.getAnonymousAppDeviceGUID(r4)     // Catch:{ all -> 0x0016 }
            android.content.Context r4 = applicationContext     // Catch:{ all -> 0x0016 }
            r2 = 0
            if (r4 != 0) goto L_0x003d
            java.lang.String r4 = "applicationContext"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)     // Catch:{ all -> 0x0016 }
            r4 = r2
        L_0x003d:
            loadDefaultsFromMetadata$facebook_core_release(r4)     // Catch:{ all -> 0x0016 }
            java.lang.String r4 = applicationId     // Catch:{ all -> 0x0016 }
            if (r4 == 0) goto L_0x0110
            int r4 = r4.length()     // Catch:{ all -> 0x0016 }
            if (r4 == 0) goto L_0x0110
            java.lang.String r4 = appClientToken     // Catch:{ all -> 0x0016 }
            if (r4 == 0) goto L_0x0108
            int r4 = r4.length()     // Catch:{ all -> 0x0016 }
            if (r4 == 0) goto L_0x0108
            r4 = 1
            r1.set(r4)     // Catch:{ all -> 0x0016 }
            boolean r4 = getAutoInitEnabled()     // Catch:{ all -> 0x0016 }
            if (r4 == 0) goto L_0x0061
            fullyInitialize()     // Catch:{ all -> 0x0016 }
        L_0x0061:
            android.content.Context r4 = applicationContext     // Catch:{ all -> 0x0016 }
            if (r4 != 0) goto L_0x006b
            java.lang.String r4 = "applicationContext"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)     // Catch:{ all -> 0x0016 }
            r4 = r2
        L_0x006b:
            boolean r4 = r4 instanceof android.app.Application     // Catch:{ all -> 0x0016 }
            if (r4 == 0) goto L_0x0087
            boolean r4 = com.facebook.UserSettingsManager.getAutoLogAppEventsEnabled()     // Catch:{ all -> 0x0016 }
            if (r4 == 0) goto L_0x0087
            android.content.Context r4 = applicationContext     // Catch:{ all -> 0x0016 }
            if (r4 != 0) goto L_0x007f
            java.lang.String r4 = "applicationContext"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)     // Catch:{ all -> 0x0016 }
            r4 = r2
        L_0x007f:
            android.app.Application r4 = (android.app.Application) r4     // Catch:{ all -> 0x0016 }
            java.lang.String r1 = applicationId     // Catch:{ all -> 0x0016 }
            com.facebook.appevents.internal.ActivityLifecycleTracker.startTracking(r4, r1)     // Catch:{ all -> 0x0016 }
            goto L_0x008a
        L_0x0087:
            com.facebook.appevents.iap.InAppPurchaseLoggerManager.updateLatestPossiblePurchaseTime()     // Catch:{ all -> 0x0016 }
        L_0x008a:
            com.facebook.appevents.internal.AppLinkManager$Companion r4 = com.facebook.appevents.internal.AppLinkManager.Companion     // Catch:{ all -> 0x0016 }
            com.facebook.appevents.internal.AppLinkManager r4 = r4.getInstance()     // Catch:{ all -> 0x0016 }
            if (r4 == 0) goto L_0x00a1
            android.content.Context r1 = applicationContext     // Catch:{ all -> 0x0016 }
            if (r1 != 0) goto L_0x009c
            java.lang.String r1 = "applicationContext"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)     // Catch:{ all -> 0x0016 }
            r1 = r2
        L_0x009c:
            android.app.Application r1 = (android.app.Application) r1     // Catch:{ all -> 0x0016 }
            r4.setupLifecycleListener(r1)     // Catch:{ all -> 0x0016 }
        L_0x00a1:
            com.facebook.internal.FetchedAppSettingsManager.loadAppSettingsAsync()     // Catch:{ all -> 0x0016 }
            com.facebook.internal.NativeProtocol.updateAllAvailableProtocolVersionsAsync()     // Catch:{ all -> 0x0016 }
            com.facebook.internal.BoltsMeasurementEventListener$Companion r4 = com.facebook.internal.BoltsMeasurementEventListener.Companion     // Catch:{ all -> 0x0016 }
            android.content.Context r1 = applicationContext     // Catch:{ all -> 0x0016 }
            if (r1 != 0) goto L_0x00b3
            java.lang.String r1 = "applicationContext"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)     // Catch:{ all -> 0x0016 }
            goto L_0x00b4
        L_0x00b3:
            r2 = r1
        L_0x00b4:
            r4.getInstance(r2)     // Catch:{ all -> 0x0016 }
            com.facebook.internal.LockOnGetVariable r4 = new com.facebook.internal.LockOnGetVariable     // Catch:{ all -> 0x0016 }
            com.facebook.FacebookSdk$$ExternalSyntheticLambda1 r1 = new com.facebook.FacebookSdk$$ExternalSyntheticLambda1     // Catch:{ all -> 0x0016 }
            r1.<init>()     // Catch:{ all -> 0x0016 }
            r4.<init>(r1)     // Catch:{ all -> 0x0016 }
            cacheDir = r4     // Catch:{ all -> 0x0016 }
            com.facebook.internal.FeatureManager$Feature r4 = com.facebook.internal.FeatureManager.Feature.Instrument     // Catch:{ all -> 0x0016 }
            com.facebook.FacebookSdk$$ExternalSyntheticLambda2 r1 = new com.facebook.FacebookSdk$$ExternalSyntheticLambda2     // Catch:{ all -> 0x0016 }
            r1.<init>()     // Catch:{ all -> 0x0016 }
            com.facebook.internal.FeatureManager.checkFeature(r4, r1)     // Catch:{ all -> 0x0016 }
            com.facebook.internal.FeatureManager$Feature r4 = com.facebook.internal.FeatureManager.Feature.AppEvents     // Catch:{ all -> 0x0016 }
            com.facebook.FacebookSdk$$ExternalSyntheticLambda3 r1 = new com.facebook.FacebookSdk$$ExternalSyntheticLambda3     // Catch:{ all -> 0x0016 }
            r1.<init>()     // Catch:{ all -> 0x0016 }
            com.facebook.internal.FeatureManager.checkFeature(r4, r1)     // Catch:{ all -> 0x0016 }
            com.facebook.internal.FeatureManager$Feature r4 = com.facebook.internal.FeatureManager.Feature.ChromeCustomTabsPrefetching     // Catch:{ all -> 0x0016 }
            com.facebook.FacebookSdk$$ExternalSyntheticLambda4 r1 = new com.facebook.FacebookSdk$$ExternalSyntheticLambda4     // Catch:{ all -> 0x0016 }
            r1.<init>()     // Catch:{ all -> 0x0016 }
            com.facebook.internal.FeatureManager.checkFeature(r4, r1)     // Catch:{ all -> 0x0016 }
            com.facebook.internal.FeatureManager$Feature r4 = com.facebook.internal.FeatureManager.Feature.IgnoreAppSwitchToLoggedOut     // Catch:{ all -> 0x0016 }
            com.facebook.FacebookSdk$$ExternalSyntheticLambda5 r1 = new com.facebook.FacebookSdk$$ExternalSyntheticLambda5     // Catch:{ all -> 0x0016 }
            r1.<init>()     // Catch:{ all -> 0x0016 }
            com.facebook.internal.FeatureManager.checkFeature(r4, r1)     // Catch:{ all -> 0x0016 }
            com.facebook.internal.FeatureManager$Feature r4 = com.facebook.internal.FeatureManager.Feature.BypassAppSwitch     // Catch:{ all -> 0x0016 }
            com.facebook.FacebookSdk$$ExternalSyntheticLambda6 r1 = new com.facebook.FacebookSdk$$ExternalSyntheticLambda6     // Catch:{ all -> 0x0016 }
            r1.<init>()     // Catch:{ all -> 0x0016 }
            com.facebook.internal.FeatureManager.checkFeature(r4, r1)     // Catch:{ all -> 0x0016 }
            java.util.concurrent.FutureTask r4 = new java.util.concurrent.FutureTask     // Catch:{ all -> 0x0016 }
            com.facebook.FacebookSdk$$ExternalSyntheticLambda7 r1 = new com.facebook.FacebookSdk$$ExternalSyntheticLambda7     // Catch:{ all -> 0x0016 }
            r1.<init>(r5)     // Catch:{ all -> 0x0016 }
            r4.<init>(r1)     // Catch:{ all -> 0x0016 }
            java.util.concurrent.Executor r5 = getExecutor()     // Catch:{ all -> 0x0016 }
            r5.execute(r4)     // Catch:{ all -> 0x0016 }
            monitor-exit(r0)
            return
        L_0x0108:
            com.facebook.FacebookException r4 = new com.facebook.FacebookException     // Catch:{ all -> 0x0016 }
            java.lang.String r5 = "A valid Facebook app client token must be set in the AndroidManifest.xml or set by calling FacebookSdk.setClientToken before initializing the sdk."
            r4.<init>((java.lang.String) r5)     // Catch:{ all -> 0x0016 }
            throw r4     // Catch:{ all -> 0x0016 }
        L_0x0110:
            com.facebook.FacebookException r4 = new com.facebook.FacebookException     // Catch:{ all -> 0x0016 }
            java.lang.String r5 = "A valid Facebook app id must be set in the AndroidManifest.xml or set by calling FacebookSdk.setApplicationId before initializing the sdk."
            r4.<init>((java.lang.String) r5)     // Catch:{ all -> 0x0016 }
            throw r4     // Catch:{ all -> 0x0016 }
        L_0x0118:
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookSdk.sdkInitialize(android.content.Context, com.facebook.FacebookSdk$InitializeCallback):void");
    }

    /* access modifiers changed from: private */
    public static final File sdkInitialize$lambda$3() {
        Context context = applicationContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("applicationContext");
            context = null;
        }
        return context.getCacheDir();
    }

    /* access modifiers changed from: private */
    public static final void sdkInitialize$lambda$4(boolean z) {
        if (z) {
            InstrumentManager.start();
        }
    }

    /* access modifiers changed from: private */
    public static final void sdkInitialize$lambda$5(boolean z) {
        if (z) {
            AppEventsManager.start();
        }
    }

    /* access modifiers changed from: private */
    public static final void sdkInitialize$lambda$6(boolean z) {
        if (z) {
            hasCustomTabsPrefetching = true;
        }
    }

    /* access modifiers changed from: private */
    public static final void sdkInitialize$lambda$7(boolean z) {
        if (z) {
            ignoreAppSwitchToLoggedOut = true;
        }
    }

    /* access modifiers changed from: private */
    public static final void sdkInitialize$lambda$8(boolean z) {
        if (z) {
            bypassAppSwitch = true;
        }
    }

    /* access modifiers changed from: private */
    public static final Void sdkInitialize$lambda$9(InitializeCallback initializeCallback) {
        AccessTokenManager.Companion.getInstance().loadCurrentAccessToken();
        ProfileManager.Companion.getInstance().loadCurrentProfile();
        if (AccessToken.Companion.isCurrentAccessTokenActive()) {
            Profile.Companion companion = Profile.Companion;
            if (companion.getCurrentProfile() == null) {
                companion.fetchProfileForCurrentAccessToken();
            }
        }
        if (initializeCallback != null) {
            initializeCallback.onInitialized();
        }
        AppEventsLogger.Companion companion2 = AppEventsLogger.Companion;
        companion2.initializeLib(getApplicationContext(), applicationId);
        UserSettingsManager.logIfAutoAppLinkEnabled();
        Context applicationContext2 = getApplicationContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext().applicationContext");
        companion2.newLogger(applicationContext2).flush();
        return null;
    }

    public static final boolean isInitialized() {
        return sdkInitialized.get();
    }

    public static final void fullyInitialize() {
        isFullyInitialized = true;
    }

    public static final boolean isLoggingBehaviorEnabled(LoggingBehavior loggingBehavior) {
        boolean z;
        Intrinsics.checkNotNullParameter(loggingBehavior, "behavior");
        HashSet hashSet = loggingBehaviors;
        synchronized (hashSet) {
            z = isDebugEnabled() && hashSet.contains(loggingBehavior);
        }
        return z;
    }

    public static final String getGraphDomain() {
        AccessToken currentAccessToken = AccessToken.Companion.getCurrentAccessToken();
        return Utility.getGraphDomainFromTokenDomain(currentAccessToken != null ? currentAccessToken.getGraphDomain() : null);
    }

    public static final Context getApplicationContext() {
        Validate.sdkInitialized();
        Context context = applicationContext;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("applicationContext");
        return null;
    }

    public static final void publishInstallAsync(Context context, String str) {
        Class<FacebookSdk> cls = FacebookSdk.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(str, "applicationId");
                Context applicationContext2 = context.getApplicationContext();
                if (applicationContext2 != null) {
                    if (!FetchedAppGateKeepersManager.getGateKeeperForKey("app_events_killswitch", getApplicationId(), false)) {
                        getExecutor().execute(new FacebookSdk$$ExternalSyntheticLambda8(applicationContext2, str));
                    }
                    if (FeatureManager.isEnabled(FeatureManager.Feature.OnDeviceEventProcessing) && OnDeviceProcessingManager.isOnDeviceProcessingEnabled()) {
                        OnDeviceProcessingManager.sendInstallEventAsync(str, "com.facebook.sdk.attributionTracking");
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void publishInstallAsync$lambda$15(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "$applicationContext");
        Intrinsics.checkNotNullParameter(str, "$applicationId");
        INSTANCE.publishInstallAndWaitForResponse(context, str);
    }

    private final void publishInstallAndWaitForResponse(Context context, String str) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.Companion.getAttributionIdentifiers(context);
                SharedPreferences sharedPreferences = context.getSharedPreferences("com.facebook.sdk.attributionTracking", 0);
                String str2 = str + "ping";
                long j = sharedPreferences.getLong(str2, 0);
                JSONObject jSONObjectForGraphAPICall = AppEventsLoggerUtility.getJSONObjectForGraphAPICall(AppEventsLoggerUtility.GraphAPIActivityType.MOBILE_INSTALL_EVENT, attributionIdentifiers, AppEventsLogger.Companion.getAnonymousAppDeviceGUID(context), getLimitEventAndDataUsage(context), context);
                String installReferrer = AppEventsLoggerImpl.Companion.getInstallReferrer();
                if (installReferrer != null) {
                    jSONObjectForGraphAPICall.put("install_referrer", installReferrer);
                }
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%s/activities", Arrays.copyOf(new Object[]{str}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                GraphRequest createPostRequest = graphRequestCreator.createPostRequest((AccessToken) null, format, jSONObjectForGraphAPICall, (GraphRequest.Callback) null);
                if (j == 0 && createPostRequest.executeAndWait().getError() == null) {
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putLong(str2, System.currentTimeMillis());
                    edit.apply();
                    Logger.Companion companion = Logger.Companion;
                    LoggingBehavior loggingBehavior = LoggingBehavior.APP_EVENTS;
                    String str3 = TAG;
                    Intrinsics.checkNotNullExpressionValue(str3, "TAG");
                    companion.log(loggingBehavior, str3, "MOBILE_APP_INSTALL has been logged");
                }
            } catch (JSONException e) {
                throw new FacebookException("An error occurred while publishing install.", e);
            } catch (Exception e2) {
                Utility.logd("Facebook-publish", e2);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final String getSdkVersion() {
        return "18.1.3";
    }

    public static final boolean getLimitEventAndDataUsage(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Validate.sdkInitialized();
        return context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("limitEventUsage", false);
    }

    public static final void loadDefaultsFromMetadata$facebook_core_release(Context context) {
        if (context != null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                Intrinsics.checkNotNullExpressionValue(applicationInfo, "try {\n                co…     return\n            }");
                if (applicationInfo.metaData != null) {
                    if (applicationId == null) {
                        Object obj = applicationInfo.metaData.get("com.facebook.sdk.ApplicationId");
                        if (obj instanceof String) {
                            String str = (String) obj;
                            Locale locale = Locale.ROOT;
                            Intrinsics.checkNotNullExpressionValue(locale, "ROOT");
                            String lowerCase = str.toLowerCase(locale);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                            if (StringsKt.startsWith$default(lowerCase, "fb", false, 2, (Object) null)) {
                                String substring = str.substring(2);
                                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                                applicationId = substring;
                            } else {
                                applicationId = str;
                            }
                        } else if (obj instanceof Number) {
                            throw new FacebookException("App Ids cannot be directly placed in the manifest.They must be prefixed by 'fb' or be placed in the string resource file.");
                        }
                    }
                    if (applicationName == null) {
                        applicationName = applicationInfo.metaData.getString("com.facebook.sdk.ApplicationName");
                    }
                    if (appClientToken == null) {
                        appClientToken = applicationInfo.metaData.getString("com.facebook.sdk.ClientToken");
                    }
                    if (callbackRequestCodeOffset == 64206) {
                        callbackRequestCodeOffset = applicationInfo.metaData.getInt("com.facebook.sdk.CallbackOffset", 64206);
                    }
                    if (codelessDebugLogEnabled == null) {
                        codelessDebugLogEnabled = Boolean.valueOf(applicationInfo.metaData.getBoolean("com.facebook.sdk.CodelessDebugLogEnabled", false));
                    }
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
    }

    public static final String getApplicationId() {
        Validate.sdkInitialized();
        String str = applicationId;
        if (str != null) {
            return str;
        }
        throw new FacebookException("A valid Facebook app id must be set in the AndroidManifest.xml or set by calling FacebookSdk.setApplicationId before initializing the sdk.");
    }

    public static final String getApplicationName() {
        Validate.sdkInitialized();
        return applicationName;
    }

    public static final String getClientToken() {
        Validate.sdkInitialized();
        String str = appClientToken;
        if (str != null) {
            return str;
        }
        throw new FacebookException("A valid Facebook client token must be set in the AndroidManifest.xml or set by calling FacebookSdk.setClientToken before initializing the sdk. Visit https://developers.facebook.com/docs/android/getting-started#add-app_id for more information.");
    }

    public static final boolean getAutoInitEnabled() {
        return UserSettingsManager.getAutoInitEnabled();
    }

    public static final boolean getAutoLogAppEventsEnabled() {
        return UserSettingsManager.getAutoLogAppEventsEnabled();
    }

    public static final boolean getCodelessSetupEnabled() {
        return UserSettingsManager.getCodelessSetupEnabled();
    }

    public static final boolean getAdvertiserIDCollectionEnabled() {
        return UserSettingsManager.getAdvertiserIDCollectionEnabled();
    }

    public static final int getCallbackRequestCodeOffset() {
        Validate.sdkInitialized();
        return callbackRequestCodeOffset;
    }
}
