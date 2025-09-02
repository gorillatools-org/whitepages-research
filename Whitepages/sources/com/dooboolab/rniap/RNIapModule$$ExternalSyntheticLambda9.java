package com.dooboolab.rniap;

import com.android.billingclient.api.BillingClient;
import com.facebook.react.bridge.Promise;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda9 implements Function1 {
    public final /* synthetic */ Promise f$0;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda9(Promise promise) {
        this.f$0 = promise;
    }

    public final Object invoke(Object obj) {
        return RNIapModule.getAvailableItems$lambda$34(this.f$0, (BillingClient) obj);
    }
}
