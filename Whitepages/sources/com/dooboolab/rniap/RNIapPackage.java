package com.dooboolab.rniap;

import com.android.billingclient.api.BillingClient;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactApplicationContext;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class RNIapPackage implements ReactPackage {
    public List createViewManagers(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.emptyList();
    }

    public List createNativeModules(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new RNIapModule(reactApplicationContext, (BillingClient.Builder) null, (GoogleApiAvailability) null, 6, (DefaultConstructorMarker) null));
        return arrayList;
    }
}
