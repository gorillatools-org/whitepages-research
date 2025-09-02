package com.facebook.appevents.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.Logger;
import kotlin.jvm.internal.Intrinsics;

public final class ActivityLifecycleTracker$startTracking$2 implements Application.ActivityLifecycleCallbacks {
    ActivityLifecycleTracker$startTracking$2() {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Logger.Companion.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityCreated");
        AppEventUtility.assertIsMainThread();
        ActivityLifecycleTracker.onActivityCreated(activity);
    }

    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ActivityLifecycleTracker.activityReferences = ActivityLifecycleTracker.activityReferences + 1;
        Logger.Companion.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityStarted");
    }

    public void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Logger.Companion.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityResumed");
        AppEventUtility.assertIsMainThread();
        ActivityLifecycleTracker.onActivityResumed(activity);
    }

    public void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Logger.Companion.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityPaused");
        AppEventUtility.assertIsMainThread();
        ActivityLifecycleTracker.INSTANCE.onActivityPaused(activity);
    }

    public void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Logger.Companion.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityStopped");
        AppEventsLogger.Companion.onContextStop();
        ActivityLifecycleTracker.activityReferences = ActivityLifecycleTracker.activityReferences - 1;
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(bundle, "outState");
        Logger.Companion.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivitySaveInstanceState");
    }

    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Logger.Companion.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityDestroyed");
        ActivityLifecycleTracker.INSTANCE.onActivityDestroyed(activity);
    }
}
