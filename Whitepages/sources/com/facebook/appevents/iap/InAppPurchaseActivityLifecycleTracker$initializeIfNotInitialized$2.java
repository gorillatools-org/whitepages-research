package com.facebook.appevents.iap;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.facebook.FacebookSdk;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class InAppPurchaseActivityLifecycleTracker$initializeIfNotInitialized$2 implements Application.ActivityLifecycleCallbacks {
    public void onActivityCreated(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(bundle, "outState");
    }

    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    InAppPurchaseActivityLifecycleTracker$initializeIfNotInitialized$2() {
    }

    public void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        try {
            FacebookSdk.getExecutor().execute(new InAppPurchaseActivityLifecycleTracker$initializeIfNotInitialized$2$$ExternalSyntheticLambda1());
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public static final void onActivityResumed$lambda$0() {
        Context applicationContext = FacebookSdk.getApplicationContext();
        ArrayList purchasesInapp = InAppPurchaseEventManager.getPurchasesInapp(applicationContext, InAppPurchaseActivityLifecycleTracker.inAppBillingObj);
        InAppPurchaseActivityLifecycleTracker inAppPurchaseActivityLifecycleTracker = InAppPurchaseActivityLifecycleTracker.INSTANCE;
        inAppPurchaseActivityLifecycleTracker.logPurchase(applicationContext, purchasesInapp, false);
        inAppPurchaseActivityLifecycleTracker.logPurchase(applicationContext, InAppPurchaseEventManager.getPurchasesSubs(applicationContext, InAppPurchaseActivityLifecycleTracker.inAppBillingObj), true);
    }

    public void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        try {
            if (Intrinsics.areEqual((Object) InAppPurchaseActivityLifecycleTracker.hasBillingActivity, (Object) Boolean.TRUE) && Intrinsics.areEqual((Object) activity.getLocalClassName(), (Object) "com.android.billingclient.api.ProxyBillingActivity")) {
                FacebookSdk.getExecutor().execute(new InAppPurchaseActivityLifecycleTracker$initializeIfNotInitialized$2$$ExternalSyntheticLambda0());
            }
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public static final void onActivityStopped$lambda$1() {
        Context applicationContext = FacebookSdk.getApplicationContext();
        ArrayList purchasesInapp = InAppPurchaseEventManager.getPurchasesInapp(applicationContext, InAppPurchaseActivityLifecycleTracker.inAppBillingObj);
        if (purchasesInapp.isEmpty()) {
            purchasesInapp = InAppPurchaseEventManager.getPurchaseHistoryInapp(applicationContext, InAppPurchaseActivityLifecycleTracker.inAppBillingObj);
        }
        InAppPurchaseActivityLifecycleTracker.INSTANCE.logPurchase(applicationContext, purchasesInapp, false);
    }
}
