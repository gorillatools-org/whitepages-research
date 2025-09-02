package com.dooboolab.rniap;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.Purchase;
import com.facebook.react.bridge.Promise;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda7 implements Function1 {
    public final /* synthetic */ Purchase f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Promise f$2;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda7(Purchase purchase, int i, Promise promise) {
        this.f$0 = purchase;
        this.f$1 = i;
        this.f$2 = promise;
    }

    public final Object invoke(Object obj) {
        return RNIapModule.consumeItems$lambda$5(this.f$0, this.f$1, this.f$2, (BillingClient) obj);
    }
}
