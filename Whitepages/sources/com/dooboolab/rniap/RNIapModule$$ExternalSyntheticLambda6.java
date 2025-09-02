package com.dooboolab.rniap;

import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.PurchaseHistoryResponseListener;
import com.facebook.react.bridge.Promise;
import java.util.List;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda6 implements PurchaseHistoryResponseListener {
    public final /* synthetic */ RNIapModule f$0;
    public final /* synthetic */ Promise f$1;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda6(RNIapModule rNIapModule, Promise promise) {
        this.f$0 = rNIapModule;
        this.f$1 = promise;
    }

    public final void onPurchaseHistoryResponse(BillingResult billingResult, List list) {
        RNIapModule.getPurchaseHistoryByType$lambda$38$lambda$37(this.f$0, this.f$1, billingResult, list);
    }
}
