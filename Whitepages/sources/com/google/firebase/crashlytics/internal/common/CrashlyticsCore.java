package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.RemoteConfigDeferredProxy;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbSource;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorkers;
import com.google.firebase.crashlytics.internal.metadata.LogFileManager;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import com.google.firebase.crashlytics.internal.stacktrace.MiddleOutFallbackStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.RemoveRepeatsStrategy;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CrashlyticsCore {
    static final String CRASHLYTICS_REQUIRE_BUILD_ID = "com.crashlytics.RequireBuildId";
    static final boolean CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT = true;
    static final String CRASH_MARKER_FILE_NAME = "crash_marker";
    static final int DEFAULT_MAIN_HANDLER_TIMEOUT_SEC = 3;
    private static final String INITIALIZATION_MARKER_FILE_NAME = "initialization_marker";
    static final int MAX_STACK_SIZE = 1024;
    private static final String MISSING_BUILD_ID_MSG = "The Crashlytics build ID is missing. This occurs when the Crashlytics Gradle plugin is missing from your app's build configuration. Please review the Firebase Crashlytics onboarding instructions at https://firebase.google.com/docs/crashlytics/get-started?platform=android#add-plugin";
    static final int NUM_STACK_REPETITIONS_ALLOWED = 10;
    private static final String ON_DEMAND_DROPPED_KEY = "com.crashlytics.on-demand.dropped-exceptions";
    private static final String ON_DEMAND_RECORDED_KEY = "com.crashlytics.on-demand.recorded-exceptions";
    private final AnalyticsEventLogger analyticsEventLogger;

    /* renamed from: app  reason: collision with root package name */
    private final FirebaseApp f5app;
    public final BreadcrumbSource breadcrumbSource;
    private final Context context;
    private CrashlyticsController controller;
    private CrashlyticsFileMarker crashMarker;
    private final CrashlyticsWorkers crashlyticsWorkers;
    private final DataCollectionArbiter dataCollectionArbiter;
    private boolean didCrashOnPreviousExecution;
    private final FileStore fileStore;
    private final IdManager idManager;
    private CrashlyticsFileMarker initializationMarker;
    private final CrashlyticsNativeComponent nativeComponent;
    private final OnDemandCounter onDemandCounter = new OnDemandCounter();
    private final RemoteConfigDeferredProxy remoteConfigDeferredProxy;
    private final CrashlyticsAppQualitySessionsSubscriber sessionsSubscriber;
    private final long startTime = System.currentTimeMillis();

    public CrashlyticsCore(FirebaseApp firebaseApp, IdManager idManager2, CrashlyticsNativeComponent crashlyticsNativeComponent, DataCollectionArbiter dataCollectionArbiter2, BreadcrumbSource breadcrumbSource2, AnalyticsEventLogger analyticsEventLogger2, FileStore fileStore2, CrashlyticsAppQualitySessionsSubscriber crashlyticsAppQualitySessionsSubscriber, RemoteConfigDeferredProxy remoteConfigDeferredProxy2, CrashlyticsWorkers crashlyticsWorkers2) {
        this.f5app = firebaseApp;
        this.dataCollectionArbiter = dataCollectionArbiter2;
        this.context = firebaseApp.getApplicationContext();
        this.idManager = idManager2;
        this.nativeComponent = crashlyticsNativeComponent;
        this.breadcrumbSource = breadcrumbSource2;
        this.analyticsEventLogger = analyticsEventLogger2;
        this.fileStore = fileStore2;
        this.sessionsSubscriber = crashlyticsAppQualitySessionsSubscriber;
        this.remoteConfigDeferredProxy = remoteConfigDeferredProxy2;
        this.crashlyticsWorkers = crashlyticsWorkers2;
    }

    public boolean onPreExecute(AppData appData, SettingsProvider settingsProvider) {
        SettingsProvider settingsProvider2 = settingsProvider;
        if (isBuildIdValid(appData.buildId, CommonUtils.getBooleanResourceValue(this.context, CRASHLYTICS_REQUIRE_BUILD_ID, true))) {
            String sessionId = new CLSUUID().getSessionId();
            try {
                this.crashMarker = new CrashlyticsFileMarker(CRASH_MARKER_FILE_NAME, this.fileStore);
                this.initializationMarker = new CrashlyticsFileMarker(INITIALIZATION_MARKER_FILE_NAME, this.fileStore);
                UserMetadata userMetadata = new UserMetadata(sessionId, this.fileStore, this.crashlyticsWorkers);
                LogFileManager logFileManager = new LogFileManager(this.fileStore);
                MiddleOutFallbackStrategy middleOutFallbackStrategy = new MiddleOutFallbackStrategy(1024, new RemoveRepeatsStrategy(10));
                this.remoteConfigDeferredProxy.setupListener(userMetadata);
                Context context2 = this.context;
                IdManager idManager2 = this.idManager;
                FileStore fileStore2 = this.fileStore;
                OnDemandCounter onDemandCounter2 = this.onDemandCounter;
                CrashlyticsAppQualitySessionsSubscriber crashlyticsAppQualitySessionsSubscriber = this.sessionsSubscriber;
                CrashlyticsAppQualitySessionsSubscriber crashlyticsAppQualitySessionsSubscriber2 = crashlyticsAppQualitySessionsSubscriber;
                OnDemandCounter onDemandCounter3 = onDemandCounter2;
                UserMetadata userMetadata2 = userMetadata;
                SessionReportingCoordinator create = SessionReportingCoordinator.create(context2, idManager2, fileStore2, appData, logFileManager, userMetadata, middleOutFallbackStrategy, settingsProvider, onDemandCounter3, crashlyticsAppQualitySessionsSubscriber2, this.crashlyticsWorkers);
                Context context3 = this.context;
                IdManager idManager3 = this.idManager;
                DataCollectionArbiter dataCollectionArbiter2 = this.dataCollectionArbiter;
                FileStore fileStore3 = this.fileStore;
                CrashlyticsFileMarker crashlyticsFileMarker = this.crashMarker;
                this.controller = new CrashlyticsController(context3, idManager3, dataCollectionArbiter2, fileStore3, crashlyticsFileMarker, appData, userMetadata2, logFileManager, create, this.nativeComponent, this.analyticsEventLogger, this.sessionsSubscriber, this.crashlyticsWorkers);
                boolean didPreviousInitializationFail = didPreviousInitializationFail();
                checkForPreviousCrash();
                this.controller.enableExceptionHandling(sessionId, Thread.getDefaultUncaughtExceptionHandler(), settingsProvider2);
                if (!didPreviousInitializationFail || !CommonUtils.canTryConnection(this.context)) {
                    Logger.getLogger().d("Successfully configured exception handler.");
                    return true;
                }
                Logger.getLogger().d("Crashlytics did not finish previous background initialization. Initializing synchronously.");
                finishInitSynchronously(settingsProvider2);
                return false;
            } catch (Exception e) {
                Logger.getLogger().e("Crashlytics was not started due to an exception during initialization", e);
                this.controller = null;
                return false;
            }
        } else {
            throw new IllegalStateException(MISSING_BUILD_ID_MSG);
        }
    }

    public Task<Void> doBackgroundInitializationAsync(SettingsProvider settingsProvider) {
        return this.crashlyticsWorkers.common.submit((Runnable) new CrashlyticsCore$$ExternalSyntheticLambda0(this, settingsProvider));
    }

    /* access modifiers changed from: private */
    /* renamed from: doBackgroundInitialization */
    public void lambda$finishInitSynchronously$9(SettingsProvider settingsProvider) {
        CrashlyticsWorkers.checkBackgroundThread();
        markInitializationStarted();
        try {
            this.breadcrumbSource.registerBreadcrumbHandler(new CrashlyticsCore$$ExternalSyntheticLambda1(this));
            this.controller.saveVersionControlInfo();
            if (settingsProvider.getSettingsSync().featureFlagData.collectReports) {
                if (!this.controller.finalizeSessions(settingsProvider)) {
                    Logger.getLogger().w("Previous sessions could not be finalized.");
                }
                this.controller.submitAllReports(settingsProvider.getSettingsAsync());
                markInitializationComplete();
                return;
            }
            Logger.getLogger().d("Collection of crash reports disabled in Crashlytics settings.");
            throw new RuntimeException("Collection of crash reports disabled in Crashlytics settings.");
        } catch (Exception e) {
            Logger.getLogger().e("Crashlytics encountered a problem during asynchronous initialization.", e);
        } catch (Throwable th) {
            markInitializationComplete();
            throw th;
        }
    }

    public boolean isCrashlyticsCollectionEnabled() {
        return this.dataCollectionArbiter.isAutomaticDataCollectionEnabled();
    }

    public void setCrashlyticsCollectionEnabled(Boolean bool) {
        this.dataCollectionArbiter.setCrashlyticsDataCollectionEnabled(bool);
    }

    public Task<Boolean> checkForUnsentReports() {
        return this.controller.checkForUnsentReports();
    }

    public Task<Void> sendUnsentReports() {
        return this.controller.sendUnsentReports();
    }

    public Task<Void> deleteUnsentReports() {
        return this.controller.deleteUnsentReports();
    }

    public static String getVersion() {
        return "19.4.2";
    }

    public void logException(Throwable th, Map<String, String> map) {
        this.crashlyticsWorkers.common.submit((Runnable) new CrashlyticsCore$$ExternalSyntheticLambda6(this, th, map));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$logException$1(Throwable th, Map map) {
        this.controller.writeNonFatalException(Thread.currentThread(), th, map);
    }

    public void log(String str) {
        this.crashlyticsWorkers.common.submit((Runnable) new CrashlyticsCore$$ExternalSyntheticLambda9(this, System.currentTimeMillis() - this.startTime, str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$log$2(long j, String str) {
        this.controller.writeToLog(j, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$log$3(long j, String str) {
        this.crashlyticsWorkers.diskWrite.submit((Runnable) new CrashlyticsCore$$ExternalSyntheticLambda7(this, j, str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setUserId$4(String str) {
        this.controller.setUserId(str);
    }

    public void setUserId(String str) {
        this.crashlyticsWorkers.common.submit((Runnable) new CrashlyticsCore$$ExternalSyntheticLambda3(this, str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setCustomKey$5(String str, String str2) {
        this.controller.setCustomKey(str, str2);
    }

    public void setCustomKey(String str, String str2) {
        this.crashlyticsWorkers.common.submit((Runnable) new CrashlyticsCore$$ExternalSyntheticLambda4(this, str, str2));
    }

    public void setCustomKeys(Map<String, String> map) {
        if (!map.isEmpty()) {
            this.crashlyticsWorkers.common.submit((Runnable) new CrashlyticsCore$$ExternalSyntheticLambda10(this, map));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setCustomKeys$6(Map map) {
        this.controller.setCustomKeys(map);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setInternalKey$7(String str, String str2) {
        this.controller.setInternalKey(str, str2);
    }

    public void setInternalKey(String str, String str2) {
        this.crashlyticsWorkers.common.submit((Runnable) new CrashlyticsCore$$ExternalSyntheticLambda5(this, str, str2));
    }

    public void logFatalException(Throwable th) {
        Logger logger = Logger.getLogger();
        logger.d("Recorded on-demand fatal events: " + this.onDemandCounter.getRecordedOnDemandExceptions());
        Logger logger2 = Logger.getLogger();
        logger2.d("Dropped on-demand fatal events: " + this.onDemandCounter.getDroppedOnDemandExceptions());
        this.crashlyticsWorkers.common.submit((Runnable) new CrashlyticsCore$$ExternalSyntheticLambda2(this, th));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$logFatalException$8(Throwable th) {
        this.controller.setInternalKey(ON_DEMAND_RECORDED_KEY, Integer.toString(this.onDemandCounter.getRecordedOnDemandExceptions()));
        this.controller.setInternalKey(ON_DEMAND_DROPPED_KEY, Integer.toString(this.onDemandCounter.getDroppedOnDemandExceptions()));
        this.controller.logFatalException(Thread.currentThread(), th);
    }

    /* access modifiers changed from: package-private */
    public CrashlyticsController getController() {
        return this.controller;
    }

    private void finishInitSynchronously(SettingsProvider settingsProvider) {
        Future<?> submit = this.crashlyticsWorkers.common.getExecutor().submit(new CrashlyticsCore$$ExternalSyntheticLambda11(this, settingsProvider));
        Logger.getLogger().d("Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
        try {
            submit.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Logger.getLogger().e("Crashlytics was interrupted during initialization.", e);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e2) {
            Logger.getLogger().e("Crashlytics encountered a problem during initialization.", e2);
        } catch (TimeoutException e3) {
            Logger.getLogger().e("Crashlytics timed out during initialization.", e3);
        }
    }

    /* access modifiers changed from: package-private */
    public void markInitializationStarted() {
        CrashlyticsWorkers.checkBackgroundThread();
        this.initializationMarker.create();
        Logger.getLogger().v("Initialization marker file was created.");
    }

    /* access modifiers changed from: package-private */
    public void markInitializationComplete() {
        CrashlyticsWorkers.checkBackgroundThread();
        try {
            if (!this.initializationMarker.remove()) {
                Logger.getLogger().w("Initialization marker file was not properly removed.");
            }
        } catch (Exception e) {
            Logger.getLogger().e("Problem encountered deleting Crashlytics initialization marker.", e);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean didPreviousInitializationFail() {
        return this.initializationMarker.isPresent();
    }

    private void checkForPreviousCrash() {
        try {
            this.didCrashOnPreviousExecution = Boolean.TRUE.equals((Boolean) this.crashlyticsWorkers.common.getExecutor().submit(new CrashlyticsCore$$ExternalSyntheticLambda8(this)).get(3, TimeUnit.SECONDS));
        } catch (Exception unused) {
            this.didCrashOnPreviousExecution = false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$checkForPreviousCrash$10() throws Exception {
        return Boolean.valueOf(this.controller.didCrashOnPreviousExecution());
    }

    public boolean didCrashOnPreviousExecution() {
        return this.didCrashOnPreviousExecution;
    }

    static boolean isBuildIdValid(String str, boolean z) {
        if (!z) {
            Logger.getLogger().v("Configured not to require a build ID.");
            return true;
        } else if (!TextUtils.isEmpty(str)) {
            return true;
        } else {
            Log.e(Logger.TAG, ".");
            Log.e(Logger.TAG, ".     |  | ");
            Log.e(Logger.TAG, ".     |  |");
            Log.e(Logger.TAG, ".     |  |");
            Log.e(Logger.TAG, ".   \\ |  | /");
            Log.e(Logger.TAG, ".    \\    /");
            Log.e(Logger.TAG, ".     \\  /");
            Log.e(Logger.TAG, ".      \\/");
            Log.e(Logger.TAG, ".");
            Log.e(Logger.TAG, MISSING_BUILD_ID_MSG);
            Log.e(Logger.TAG, ".");
            Log.e(Logger.TAG, ".      /\\");
            Log.e(Logger.TAG, ".     /  \\");
            Log.e(Logger.TAG, ".    /    \\");
            Log.e(Logger.TAG, ".   / |  | \\");
            Log.e(Logger.TAG, ".     |  |");
            Log.e(Logger.TAG, ".     |  |");
            Log.e(Logger.TAG, ".     |  |");
            Log.e(Logger.TAG, ".");
            return false;
        }
    }
}
