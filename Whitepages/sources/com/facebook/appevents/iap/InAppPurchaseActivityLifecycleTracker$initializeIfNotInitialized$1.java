package com.facebook.appevents.iap;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.facebook.FacebookSdk;
import kotlin.jvm.internal.Intrinsics;

public final class InAppPurchaseActivityLifecycleTracker$initializeIfNotInitialized$1 implements ServiceConnection {
    public void onServiceDisconnected(ComponentName componentName) {
        Intrinsics.checkNotNullParameter(componentName, "name");
    }

    InAppPurchaseActivityLifecycleTracker$initializeIfNotInitialized$1() {
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Intrinsics.checkNotNullParameter(componentName, "name");
        Intrinsics.checkNotNullParameter(iBinder, "service");
        InAppPurchaseActivityLifecycleTracker inAppPurchaseActivityLifecycleTracker = InAppPurchaseActivityLifecycleTracker.INSTANCE;
        InAppPurchaseActivityLifecycleTracker.inAppBillingObj = InAppPurchaseEventManager.asInterface(FacebookSdk.getApplicationContext(), iBinder);
    }
}
