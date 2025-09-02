package com.dooboolab.rniap;

import android.util.Log;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.facebook.react.bridge.Promise;
import kotlin.jvm.internal.Intrinsics;

public final class RNIapModule$initConnection$1$1 implements BillingClientStateListener {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ RNIapModule this$0;

    RNIapModule$initConnection$1$1(RNIapModule rNIapModule, Promise promise) {
        this.this$0 = rNIapModule;
        this.$promise = promise;
    }

    public void onBillingSetupFinished(BillingResult billingResult) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        if (this.this$0.isValidResult(billingResult, this.$promise)) {
            PromiseUtlisKt.safeResolve(this.$promise, Boolean.TRUE);
        }
    }

    public void onBillingServiceDisconnected() {
        Log.i(RNIapModule.TAG, "Billing service disconnected");
    }
}
