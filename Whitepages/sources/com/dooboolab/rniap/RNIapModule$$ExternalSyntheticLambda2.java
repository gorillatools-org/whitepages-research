package com.dooboolab.rniap;

import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.PurchasesResponseListener;
import com.facebook.react.bridge.Promise;
import java.util.List;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda2 implements PurchasesResponseListener {
    public final /* synthetic */ RNIapModule f$0;
    public final /* synthetic */ Promise f$1;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda2(RNIapModule rNIapModule, Promise promise) {
        this.f$0 = rNIapModule;
        this.f$1 = promise;
    }

    public final void onQueryPurchasesResponse(BillingResult billingResult, List list) {
        RNIapModule.sendUnconsumedPurchases$lambda$52$lambda$51(this.f$0, this.f$1, billingResult, list);
    }
}
