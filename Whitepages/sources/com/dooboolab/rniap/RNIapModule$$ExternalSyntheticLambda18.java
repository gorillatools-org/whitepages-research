package com.dooboolab.rniap;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.ConsumeParams;
import com.facebook.react.bridge.Promise;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda18 implements Function1 {
    public final /* synthetic */ ConsumeParams f$0;
    public final /* synthetic */ RNIapModule f$1;
    public final /* synthetic */ Promise f$2;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda18(ConsumeParams consumeParams, RNIapModule rNIapModule, Promise promise) {
        this.f$0 = consumeParams;
        this.f$1 = rNIapModule;
        this.f$2 = promise;
    }

    public final Object invoke(Object obj) {
        return RNIapModule.consumeProduct$lambda$46(this.f$0, this.f$1, this.f$2, (BillingClient) obj);
    }
}
