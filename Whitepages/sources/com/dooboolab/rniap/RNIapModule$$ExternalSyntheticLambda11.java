package com.dooboolab.rniap;

import com.android.billingclient.api.BillingConfig;
import com.android.billingclient.api.BillingConfigResponseListener;
import com.android.billingclient.api.BillingResult;
import com.facebook.react.bridge.Promise;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda11 implements BillingConfigResponseListener {
    public final /* synthetic */ Promise f$0;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda11(Promise promise) {
        this.f$0 = promise;
    }

    public final void onBillingConfigResponse(BillingResult billingResult, BillingConfig billingConfig) {
        RNIapModule.getStorefront$lambda$54$lambda$53(this.f$0, billingResult, billingConfig);
    }
}
