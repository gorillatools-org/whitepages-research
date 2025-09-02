package com.dooboolab.rniap;

import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.PurchasesResponseListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableNativeArray;
import java.util.List;
import kotlin.jvm.internal.Ref$IntRef;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda4 implements PurchasesResponseListener {
    public final /* synthetic */ WritableNativeArray f$0;
    public final /* synthetic */ Ref$IntRef f$1;
    public final /* synthetic */ Promise f$2;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda4(WritableNativeArray writableNativeArray, Ref$IntRef ref$IntRef, Promise promise) {
        this.f$0 = writableNativeArray;
        this.f$1 = ref$IntRef;
        this.f$2 = promise;
    }

    public final void onQueryPurchasesResponse(BillingResult billingResult, List list) {
        RNIapModule.getAvailableItems$lambda$34$lambda$33(this.f$0, this.f$1, this.f$2, billingResult, list);
    }
}
