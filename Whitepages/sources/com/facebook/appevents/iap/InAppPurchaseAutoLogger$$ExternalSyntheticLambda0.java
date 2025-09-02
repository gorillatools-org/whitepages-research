package com.facebook.appevents.iap;

import android.content.Context;
import com.facebook.appevents.iap.InAppPurchaseUtils;
import kotlin.jvm.internal.Ref$ObjectRef;

public final /* synthetic */ class InAppPurchaseAutoLogger$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Ref$ObjectRef f$0;
    public final /* synthetic */ InAppPurchaseUtils.BillingClientVersion f$1;
    public final /* synthetic */ Context f$2;

    public /* synthetic */ InAppPurchaseAutoLogger$$ExternalSyntheticLambda0(Ref$ObjectRef ref$ObjectRef, InAppPurchaseUtils.BillingClientVersion billingClientVersion, Context context) {
        this.f$0 = ref$ObjectRef;
        this.f$1 = billingClientVersion;
        this.f$2 = context;
    }

    public final void run() {
        InAppPurchaseAutoLogger.startIapLogging$lambda$1(this.f$0, this.f$1, this.f$2);
    }
}
