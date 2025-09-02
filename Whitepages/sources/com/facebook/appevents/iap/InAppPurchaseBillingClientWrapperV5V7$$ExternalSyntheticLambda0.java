package com.facebook.appevents.iap;

import com.facebook.appevents.iap.InAppPurchaseUtils;

public final /* synthetic */ class InAppPurchaseBillingClientWrapperV5V7$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ InAppPurchaseBillingClientWrapperV5V7 f$0;
    public final /* synthetic */ InAppPurchaseUtils.IAPProductType f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ InAppPurchaseBillingClientWrapperV5V7$$ExternalSyntheticLambda0(InAppPurchaseBillingClientWrapperV5V7 inAppPurchaseBillingClientWrapperV5V7, InAppPurchaseUtils.IAPProductType iAPProductType, Runnable runnable) {
        this.f$0 = inAppPurchaseBillingClientWrapperV5V7;
        this.f$1 = iAPProductType;
        this.f$2 = runnable;
    }

    public final void run() {
        InAppPurchaseBillingClientWrapperV5V7.queryPurchaseHistory$lambda$1(this.f$0, this.f$1, this.f$2);
    }
}
