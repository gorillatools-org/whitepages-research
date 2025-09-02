package com.facebook.appevents.iap;

import com.facebook.appevents.iap.InAppPurchaseUtils;

public interface InAppPurchaseBillingClientWrapper {
    void queryPurchaseHistory(InAppPurchaseUtils.IAPProductType iAPProductType, Runnable runnable);
}
