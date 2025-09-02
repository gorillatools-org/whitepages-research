package com.dooboolab.rniap;

import com.android.billingclient.api.BillingClient;
import com.facebook.react.bridge.Promise;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda5 implements Function1 {
    public final /* synthetic */ Promise f$0;
    public final /* synthetic */ RNIapModule f$1;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda5(Promise promise, RNIapModule rNIapModule) {
        this.f$0 = promise;
        this.f$1 = rNIapModule;
    }

    public final Object invoke(Object obj) {
        return RNIapModule.sendUnconsumedPurchases$lambda$52(this.f$0, this.f$1, (BillingClient) obj);
    }
}
