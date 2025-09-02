package com.dooboolab.rniap;

import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeResponseListener;
import com.facebook.react.bridge.Promise;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda10 implements ConsumeResponseListener {
    public final /* synthetic */ RNIapModule f$0;
    public final /* synthetic */ Promise f$1;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda10(RNIapModule rNIapModule, Promise promise) {
        this.f$0 = rNIapModule;
        this.f$1 = promise;
    }

    public final void onConsumeResponse(BillingResult billingResult, String str) {
        RNIapModule.consumeProduct$lambda$46$lambda$45(this.f$0, this.f$1, billingResult, str);
    }
}
