package com.dooboolab.rniap;

import com.android.billingclient.api.BillingClient;
import com.facebook.react.bridge.Promise;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda23 implements Function1 {
    public final /* synthetic */ RNIapModule f$0;
    public final /* synthetic */ Promise f$1;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda23(RNIapModule rNIapModule, Promise promise) {
        this.f$0 = rNIapModule;
        this.f$1 = promise;
    }

    public final Object invoke(Object obj) {
        return RNIapModule.flushFailedPurchasesCachedAsPending$lambda$8(this.f$0, this.f$1, (BillingClient) obj);
    }
}
