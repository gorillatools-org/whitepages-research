package com.dooboolab.rniap;

import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingResult;
import com.facebook.react.bridge.Promise;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda8 implements AcknowledgePurchaseResponseListener {
    public final /* synthetic */ RNIapModule f$0;
    public final /* synthetic */ Promise f$1;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda8(RNIapModule rNIapModule, Promise promise) {
        this.f$0 = rNIapModule;
        this.f$1 = promise;
    }

    public final void onAcknowledgePurchaseResponse(BillingResult billingResult) {
        RNIapModule.acknowledgePurchase$lambda$44$lambda$43(this.f$0, this.f$1, billingResult);
    }
}
