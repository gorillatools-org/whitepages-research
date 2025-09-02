package com.android.billingclient.api;

public interface BillingConfigResponseListener {
    void onBillingConfigResponse(BillingResult billingResult, BillingConfig billingConfig);
}
