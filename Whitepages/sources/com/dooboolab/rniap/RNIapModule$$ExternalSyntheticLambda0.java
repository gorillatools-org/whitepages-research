package com.dooboolab.rniap;

import com.android.billingclient.api.BillingClient;
import com.facebook.react.bridge.Promise;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ RNIapModule f$1;
    public final /* synthetic */ Promise f$2;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda0(String str, RNIapModule rNIapModule, Promise promise) {
        this.f$0 = str;
        this.f$1 = rNIapModule;
        this.f$2 = promise;
    }

    public final Object invoke(Object obj) {
        return RNIapModule.getPurchaseHistoryByType$lambda$38(this.f$0, this.f$1, this.f$2, (BillingClient) obj);
    }
}
