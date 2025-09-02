package com.dooboolab.rniap;

import com.android.billingclient.api.BillingClient;
import com.facebook.react.bridge.Promise;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda20 implements Function1 {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Promise f$1;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda20(String str, Promise promise) {
        this.f$0 = str;
        this.f$1 = promise;
    }

    public final Object invoke(Object obj) {
        return RNIapModule.isFeatureSupported$lambda$2(this.f$0, this.f$1, (BillingClient) obj);
    }
}
