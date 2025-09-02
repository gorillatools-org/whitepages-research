package com.dooboolab.rniap;

import com.android.billingclient.api.BillingClient;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableArray;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda12 implements Function1 {
    public final /* synthetic */ ReadableArray f$0;
    public final /* synthetic */ Promise f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ RNIapModule f$3;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda12(ReadableArray readableArray, Promise promise, String str, RNIapModule rNIapModule) {
        this.f$0 = readableArray;
        this.f$1 = promise;
        this.f$2 = str;
        this.f$3 = rNIapModule;
    }

    public final Object invoke(Object obj) {
        return RNIapModule.getItemsByType$lambda$20(this.f$0, this.f$1, this.f$2, this.f$3, (BillingClient) obj);
    }
}
