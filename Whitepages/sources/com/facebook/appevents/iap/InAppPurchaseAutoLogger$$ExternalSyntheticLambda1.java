package com.facebook.appevents.iap;

import android.content.Context;
import com.facebook.appevents.iap.InAppPurchaseUtils;

public final /* synthetic */ class InAppPurchaseAutoLogger$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ InAppPurchaseUtils.BillingClientVersion f$0;
    public final /* synthetic */ Context f$1;

    public /* synthetic */ InAppPurchaseAutoLogger$$ExternalSyntheticLambda1(InAppPurchaseUtils.BillingClientVersion billingClientVersion, Context context) {
        this.f$0 = billingClientVersion;
        this.f$1 = context;
    }

    public final void run() {
        InAppPurchaseAutoLogger.startIapLogging$lambda$2(this.f$0, this.f$1);
    }
}
