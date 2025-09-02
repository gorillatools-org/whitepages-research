package com.facebook.appevents.iap;

import com.facebook.appevents.iap.InAppPurchaseUtils;
import java.util.List;

public final /* synthetic */ class InAppPurchaseBillingClientWrapperV5V7$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ InAppPurchaseBillingClientWrapperV5V7 f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ InAppPurchaseUtils.IAPProductType f$2;
    public final /* synthetic */ List f$3;

    public /* synthetic */ InAppPurchaseBillingClientWrapperV5V7$$ExternalSyntheticLambda1(InAppPurchaseBillingClientWrapperV5V7 inAppPurchaseBillingClientWrapperV5V7, Runnable runnable, InAppPurchaseUtils.IAPProductType iAPProductType, List list) {
        this.f$0 = inAppPurchaseBillingClientWrapperV5V7;
        this.f$1 = runnable;
        this.f$2 = iAPProductType;
        this.f$3 = list;
    }

    public final void run() {
        InAppPurchaseBillingClientWrapperV5V7.queryProductDetailsAsync$lambda$2(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
