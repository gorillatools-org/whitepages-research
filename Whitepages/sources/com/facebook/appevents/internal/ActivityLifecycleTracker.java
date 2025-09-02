package com.facebook.appevents.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.aam.MetadataIndexer;
import com.facebook.appevents.codeless.CodelessManager;
import com.facebook.appevents.iap.InAppPurchaseManager;
import com.facebook.appevents.suggestedevents.SuggestedEventsManager;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import java.lang.ref.WeakReference;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class ActivityLifecycleTracker {
    public static final ActivityLifecycleTracker INSTANCE = new ActivityLifecycleTracker();
    /* access modifiers changed from: private */
    public static final String TAG;
    /* access modifiers changed from: private */
    public static int activityReferences;
    private static String appId;
    private static WeakReference currActivity;
    private static long currentActivityAppearTime;
    private static volatile ScheduledFuture currentFuture;
    private static final Object currentFutureLock = new Object();
    private static volatile SessionInfo currentSession;
    private static final AtomicInteger foregroundActivityCount = new AtomicInteger(0);
    private static final ScheduledExecutorService iapExecutor = Executors.newSingleThreadScheduledExecutor();
    private static String previousActivityName;
    private static final ScheduledExecutorService singleThreadExecutor = Executors.newSingleThreadScheduledExecutor();
    private static final AtomicBoolean tracking = new AtomicBoolean(false);

    private ActivityLifecycleTracker() {
    }

    static {
        String canonicalName = ActivityLifecycleTracker.class.getCanonicalName();
        if (canonicalName == null) {
            canonicalName = "com.facebook.appevents.internal.ActivityLifecycleTracker";
        }
        TAG = canonicalName;
    }

    public static final void startTracking(Application application, String str) {
        Intrinsics.checkNotNullParameter(application, "application");
        if (tracking.compareAndSet(false, true)) {
            FeatureManager.checkFeature(FeatureManager.Feature.CodelessEvents, new ActivityLifecycleTracker$$ExternalSyntheticLambda0());
            appId = str;
            application.registerActivityLifecycleCallbacks(new ActivityLifecycleTracker$startTracking$2());
        }
    }

    /* access modifiers changed from: private */
    public static final void startTracking$lambda$0(boolean z) {
        if (z) {
            CodelessManager.enable();
        } else {
            CodelessManager.disable();
        }
    }

    public static final boolean isInBackground() {
        return activityReferences == 0;
    }

    public static final UUID getCurrentSessionGuid() {
        SessionInfo sessionInfo;
        if (currentSession == null || (sessionInfo = currentSession) == null) {
            return null;
        }
        return sessionInfo.getSessionId();
    }

    public static final void onActivityCreated(Activity activity) {
        singleThreadExecutor.execute(new ActivityLifecycleTracker$$ExternalSyntheticLambda4());
    }

    /* access modifiers changed from: private */
    public static final void onActivityCreated$lambda$1() {
        if (currentSession == null) {
            currentSession = SessionInfo.Companion.getStoredSessionInfo();
        }
    }

    public static final void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        currActivity = new WeakReference(activity);
        foregroundActivityCount.incrementAndGet();
        INSTANCE.cancelCurrentTask();
        long currentTimeMillis = System.currentTimeMillis();
        currentActivityAppearTime = currentTimeMillis;
        String activityName = Utility.getActivityName(activity);
        CodelessManager.onActivityResumed(activity);
        MetadataIndexer.onActivityResumed(activity);
        SuggestedEventsManager.trackActivity(activity);
        String str = previousActivityName;
        if (str != null && StringsKt.contains$default((CharSequence) str, (CharSequence) "ProxyBillingActivity", false, 2, (Object) null) && !Intrinsics.areEqual((Object) activityName, (Object) "ProxyBillingActivity")) {
            iapExecutor.execute(new ActivityLifecycleTracker$$ExternalSyntheticLambda1());
        }
        singleThreadExecutor.execute(new ActivityLifecycleTracker$$ExternalSyntheticLambda2(currentTimeMillis, activityName, activity.getApplicationContext()));
        previousActivityName = activityName;
    }

    /* access modifiers changed from: private */
    public static final void onActivityResumed$lambda$2() {
        InAppPurchaseManager.startTracking();
    }

    /* access modifiers changed from: private */
    public static final void onActivityResumed$lambda$3(long j, String str, Context context) {
        SessionInfo sessionInfo;
        Intrinsics.checkNotNullParameter(str, "$activityName");
        SessionInfo sessionInfo2 = currentSession;
        Long sessionLastEventTime = sessionInfo2 != null ? sessionInfo2.getSessionLastEventTime() : null;
        if (currentSession == null) {
            currentSession = new SessionInfo(Long.valueOf(j), (Long) null, (UUID) null, 4, (DefaultConstructorMarker) null);
            String str2 = appId;
            Intrinsics.checkNotNullExpressionValue(context, "appContext");
            SessionLogger.logActivateApp(str, (SourceApplicationInfo) null, str2, context);
        } else if (sessionLastEventTime != null) {
            long longValue = j - sessionLastEventTime.longValue();
            if (longValue > ((long) (INSTANCE.getSessionTimeoutInSeconds() * 1000))) {
                SessionLogger.logDeactivateApp(str, currentSession, appId);
                String str3 = appId;
                Intrinsics.checkNotNullExpressionValue(context, "appContext");
                SessionLogger.logActivateApp(str, (SourceApplicationInfo) null, str3, context);
                currentSession = new SessionInfo(Long.valueOf(j), (Long) null, (UUID) null, 4, (DefaultConstructorMarker) null);
            } else if (longValue > 1000 && (sessionInfo = currentSession) != null) {
                sessionInfo.incrementInterruptionCount();
            }
        }
        SessionInfo sessionInfo3 = currentSession;
        if (sessionInfo3 != null) {
            sessionInfo3.setSessionLastEventTime(Long.valueOf(j));
        }
        SessionInfo sessionInfo4 = currentSession;
        if (sessionInfo4 != null) {
            sessionInfo4.writeSessionToDisk();
        }
    }

    /* access modifiers changed from: private */
    public final void onActivityPaused(Activity activity) {
        AtomicInteger atomicInteger = foregroundActivityCount;
        if (atomicInteger.decrementAndGet() < 0) {
            atomicInteger.set(0);
            Log.w(TAG, "Unexpected activity pause without a matching activity resume. Logging data may be incorrect. Make sure you call activateApp from your Application's onCreate method");
        }
        cancelCurrentTask();
        long currentTimeMillis = System.currentTimeMillis();
        String activityName = Utility.getActivityName(activity);
        CodelessManager.onActivityPaused(activity);
        singleThreadExecutor.execute(new ActivityLifecycleTracker$$ExternalSyntheticLambda3(currentTimeMillis, activityName));
    }

    /* access modifiers changed from: private */
    public static final void onActivityPaused$lambda$7(long j, String str) {
        Intrinsics.checkNotNullParameter(str, "$activityName");
        if (currentSession == null) {
            currentSession = new SessionInfo(Long.valueOf(j), (Long) null, (UUID) null, 4, (DefaultConstructorMarker) null);
        }
        SessionInfo sessionInfo = currentSession;
        if (sessionInfo != null) {
            sessionInfo.setSessionLastEventTime(Long.valueOf(j));
        }
        if (foregroundActivityCount.get() <= 0) {
            ActivityLifecycleTracker$$ExternalSyntheticLambda5 activityLifecycleTracker$$ExternalSyntheticLambda5 = new ActivityLifecycleTracker$$ExternalSyntheticLambda5(j, str);
            synchronized (currentFutureLock) {
                currentFuture = singleThreadExecutor.schedule(activityLifecycleTracker$$ExternalSyntheticLambda5, (long) INSTANCE.getSessionTimeoutInSeconds(), TimeUnit.SECONDS);
                Unit unit = Unit.INSTANCE;
            }
        }
        long j2 = currentActivityAppearTime;
        long j3 = 0;
        if (j2 > 0) {
            j3 = (j - j2) / ((long) 1000);
        }
        AutomaticAnalyticsLogger.logActivityTimeSpentEvent(str, j3);
        SessionInfo sessionInfo2 = currentSession;
        if (sessionInfo2 != null) {
            sessionInfo2.writeSessionToDisk();
        }
    }

    /* access modifiers changed from: private */
    public static final void onActivityPaused$lambda$7$lambda$5(long j, String str) {
        Intrinsics.checkNotNullParameter(str, "$activityName");
        if (currentSession == null) {
            currentSession = new SessionInfo(Long.valueOf(j), (Long) null, (UUID) null, 4, (DefaultConstructorMarker) null);
        }
        if (foregroundActivityCount.get() <= 0) {
            SessionLogger.logDeactivateApp(str, currentSession, appId);
            SessionInfo.Companion.clearSavedSessionFromDisk();
            currentSession = null;
        }
        synchronized (currentFutureLock) {
            currentFuture = null;
            Unit unit = Unit.INSTANCE;
        }
    }

    /* access modifiers changed from: private */
    public final void onActivityDestroyed(Activity activity) {
        CodelessManager.onActivityDestroyed(activity);
    }

    private final int getSessionTimeoutInSeconds() {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if (appSettingsWithoutQuery == null) {
            return Constants.getDefaultAppEventsSessionTimeoutInSeconds();
        }
        return appSettingsWithoutQuery.getSessionTimeoutInSeconds();
    }

    private final void cancelCurrentTask() {
        ScheduledFuture scheduledFuture;
        synchronized (currentFutureLock) {
            try {
                if (!(currentFuture == null || (scheduledFuture = currentFuture) == null)) {
                    scheduledFuture.cancel(false);
                }
                currentFuture = null;
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static final Activity getCurrentActivity() {
        WeakReference weakReference = currActivity;
        if (weakReference == null || weakReference == null) {
            return null;
        }
        return (Activity) weakReference.get();
    }
}
