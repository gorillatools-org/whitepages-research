package com.dooboolab.rniap;

import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.PurchasesResponseListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableNativeArray;
import java.util.List;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda24 implements PurchasesResponseListener {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ RNIapModule f$1;
    public final /* synthetic */ Promise f$2;
    public final /* synthetic */ WritableNativeArray f$3;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda24(String str, RNIapModule rNIapModule, Promise promise, WritableNativeArray writableNativeArray) {
        this.f$0 = str;
        this.f$1 = rNIapModule;
        this.f$2 = promise;
        this.f$3 = writableNativeArray;
    }

    public final void onQueryPurchasesResponse(BillingResult billingResult, List list) {
        RNIapModule.getAvailableItemsByType$lambda$25$lambda$24(this.f$0, this.f$1, this.f$2, this.f$3, billingResult, list);
    }
}
