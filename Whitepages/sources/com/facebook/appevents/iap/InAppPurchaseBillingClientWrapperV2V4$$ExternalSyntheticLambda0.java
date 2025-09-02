package com.facebook.appevents.iap;

import com.facebook.appevents.iap.InAppPurchaseUtils;

public final /* synthetic */ class InAppPurchaseBillingClientWrapperV2V4$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ InAppPurchaseBillingClientWrapperV2V4 f$0;
    public final /* synthetic */ InAppPurchaseUtils.IAPProductType f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ InAppPurchaseBillingClientWrapperV2V4$$ExternalSyntheticLambda0(InAppPurchaseBillingClientWrapperV2V4 inAppPurchaseBillingClientWrapperV2V4, InAppPurchaseUtils.IAPProductType iAPProductType, Runnable runnable) {
        this.f$0 = inAppPurchaseBillingClientWrapperV2V4;
        this.f$1 = iAPProductType;
        this.f$2 = runnable;
    }

    public final void run() {
        InAppPurchaseBillingClientWrapperV2V4.queryPurchaseHistory$lambda$2(this.f$0, this.f$1, this.f$2);
    }
}
