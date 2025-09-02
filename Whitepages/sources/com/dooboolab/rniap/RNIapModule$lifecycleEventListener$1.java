package com.dooboolab.rniap;

import com.android.billingclient.api.BillingClient;
import com.facebook.react.bridge.LifecycleEventListener;

public final class RNIapModule$lifecycleEventListener$1 implements LifecycleEventListener {
    final /* synthetic */ RNIapModule this$0;

    public void onHostPause() {
    }

    public void onHostResume() {
    }

    RNIapModule$lifecycleEventListener$1(RNIapModule rNIapModule) {
        this.this$0 = rNIapModule;
    }

    public void onHostDestroy() {
        BillingClient access$getBillingClientCache$p = this.this$0.billingClientCache;
        if (access$getBillingClientCache$p != null) {
            access$getBillingClientCache$p.endConnection();
        }
        this.this$0.billingClientCache = null;
    }
}
