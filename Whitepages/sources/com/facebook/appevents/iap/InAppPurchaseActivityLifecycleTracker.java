package com.facebook.appevents.iap;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.iap.InAppPurchaseUtils;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

public final class InAppPurchaseActivityLifecycleTracker {
    public static final InAppPurchaseActivityLifecycleTracker INSTANCE = new InAppPurchaseActivityLifecycleTracker();
    private static final String TAG = InAppPurchaseActivityLifecycleTracker.class.getCanonicalName();
    private static InAppPurchaseUtils.BillingClientVersion billingClientVersion;
    private static Application.ActivityLifecycleCallbacks callbacks;
    /* access modifiers changed from: private */
    public static Boolean hasBillingActivity;
    private static Boolean hasBillingService;
    /* access modifiers changed from: private */
    public static Object inAppBillingObj;
    private static Intent intent;
    private static final AtomicBoolean isTracking = new AtomicBoolean(false);
    private static ServiceConnection serviceConnection;

    private InAppPurchaseActivityLifecycleTracker() {
    }

    public static final void startIapLogging(InAppPurchaseUtils.BillingClientVersion billingClientVersion2) {
        Intrinsics.checkNotNullParameter(billingClientVersion2, "billingClientVersion");
        InAppPurchaseActivityLifecycleTracker inAppPurchaseActivityLifecycleTracker = INSTANCE;
        inAppPurchaseActivityLifecycleTracker.initializeIfNotInitialized();
        if (!Intrinsics.areEqual((Object) hasBillingService, (Object) Boolean.FALSE) && AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()) {
            billingClientVersion = billingClientVersion2;
            inAppPurchaseActivityLifecycleTracker.startTracking();
        }
    }

    private final void initializeIfNotInitialized() {
        if (hasBillingService == null) {
            boolean z = false;
            Boolean valueOf = Boolean.valueOf(InAppPurchaseUtils.getClass("com.android.vending.billing.IInAppBillingService$Stub") != null);
            hasBillingService = valueOf;
            if (!Intrinsics.areEqual((Object) valueOf, (Object) Boolean.FALSE)) {
                if (InAppPurchaseUtils.getClass("com.android.billingclient.api.ProxyBillingActivity") != null) {
                    z = true;
                }
                hasBillingActivity = Boolean.valueOf(z);
                InAppPurchaseEventManager.clearSkuDetailsCache();
                Intent intent2 = new Intent("com.android.vending.billing.InAppBillingService.BIND").setPackage("com.android.vending");
                Intrinsics.checkNotNullExpressionValue(intent2, "Intent(\"com.android.vend…ge(\"com.android.vending\")");
                intent = intent2;
                serviceConnection = new InAppPurchaseActivityLifecycleTracker$initializeIfNotInitialized$1();
                callbacks = new InAppPurchaseActivityLifecycleTracker$initializeIfNotInitialized$2();
            }
        }
    }

    private final void startTracking() {
        if (isTracking.compareAndSet(false, true)) {
            Context applicationContext = FacebookSdk.getApplicationContext();
            if (applicationContext instanceof Application) {
                Application application = (Application) applicationContext;
                Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = callbacks;
                ServiceConnection serviceConnection2 = null;
                if (activityLifecycleCallbacks == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("callbacks");
                    activityLifecycleCallbacks = null;
                }
                application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
                Intent intent2 = intent;
                if (intent2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("intent");
                    intent2 = null;
                }
                ServiceConnection serviceConnection3 = serviceConnection;
                if (serviceConnection3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("serviceConnection");
                } else {
                    serviceConnection2 = serviceConnection3;
                }
                applicationContext.bindService(intent2, serviceConnection2, 1);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void logPurchase(Context context, ArrayList arrayList, boolean z) {
        if (!arrayList.isEmpty()) {
            HashMap hashMap = new HashMap();
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                try {
                    String string = new JSONObject(str).getString("productId");
                    Intrinsics.checkNotNullExpressionValue(string, "sku");
                    Intrinsics.checkNotNullExpressionValue(str, FirebaseAnalytics.Event.PURCHASE);
                    hashMap.put(string, str);
                    arrayList2.add(string);
                } catch (JSONException e) {
                    Log.e(TAG, "Error parsing in-app purchase data.", e);
                }
            }
            for (Map.Entry entry : InAppPurchaseEventManager.getSkuDetails(context, arrayList2, inAppBillingObj, z).entrySet()) {
                String str2 = (String) entry.getValue();
                String str3 = (String) hashMap.get((String) entry.getKey());
                if (str3 != null) {
                    AutomaticAnalyticsLogger.logPurchase$default(str3, str2, z, billingClientVersion, false, 16, (Object) null);
                }
            }
        }
    }
}
