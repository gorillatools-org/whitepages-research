package com.dooboolab.rniap;

import android.app.Activity;
import com.android.billingclient.api.BillingClient;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableArray;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda15 implements Function1 {
    public final /* synthetic */ Promise f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Activity f$10;
    public final /* synthetic */ ReadableArray f$2;
    public final /* synthetic */ ReadableArray f$3;
    public final /* synthetic */ RNIapModule f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ String f$6;
    public final /* synthetic */ int f$7;
    public final /* synthetic */ String f$8;
    public final /* synthetic */ String f$9;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda15(Promise promise, String str, ReadableArray readableArray, ReadableArray readableArray2, RNIapModule rNIapModule, boolean z, String str2, int i, String str3, String str4, Activity activity) {
        this.f$0 = promise;
        this.f$1 = str;
        this.f$2 = readableArray;
        this.f$3 = readableArray2;
        this.f$4 = rNIapModule;
        this.f$5 = z;
        this.f$6 = str2;
        this.f$7 = i;
        this.f$8 = str3;
        this.f$9 = str4;
        this.f$10 = activity;
    }

    public final Object invoke(Object obj) {
        return RNIapModule.buyItemByType$lambda$42(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, (BillingClient) obj);
    }
}
