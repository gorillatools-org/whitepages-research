package com.dooboolab.rniap;

import android.app.Activity;
import android.util.Log;
import com.android.billingclient.api.AccountIdentifiers;
import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingConfig;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.GetBillingConfigParams;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchaseHistoryRecord;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.QueryPurchaseHistoryParams;
import com.android.billingclient.api.QueryPurchasesParams;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.PromiseImpl;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.config.a;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$IntRef;

@ReactModule(name = "RNIapModule")
public final class RNIapModule extends ReactContextBaseJavaModule implements PurchasesUpdatedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PROMISE_BUY_ITEM = "PROMISE_BUY_ITEM";
    public static final String TAG = "RNIapModule";
    /* access modifiers changed from: private */
    public BillingClient billingClientCache;
    private final BillingClient.Builder builder;
    private final GoogleApiAvailability googleApiAvailability;
    private final ReactApplicationContext reactContext;
    private final Map<String, ProductDetails> skus;

    @ReactMethod
    public final void addListener(String str) {
        Intrinsics.checkNotNullParameter(str, a.h);
    }

    @ReactMethod
    public final void removeListeners(double d) {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RNIapModule(ReactApplicationContext reactApplicationContext, BillingClient.Builder builder2, GoogleApiAvailability googleApiAvailability2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reactApplicationContext, (i & 2) != 0 ? BillingClient.newBuilder(reactApplicationContext).enablePendingPurchases() : builder2, (i & 4) != 0 ? GoogleApiAvailability.getInstance() : googleApiAvailability2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RNIapModule(ReactApplicationContext reactApplicationContext, BillingClient.Builder builder2, GoogleApiAvailability googleApiAvailability2) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        Intrinsics.checkNotNullParameter(builder2, "builder");
        Intrinsics.checkNotNullParameter(googleApiAvailability2, "googleApiAvailability");
        this.reactContext = reactApplicationContext;
        this.builder = builder2;
        this.googleApiAvailability = googleApiAvailability2;
        this.skus = new LinkedHashMap();
        reactApplicationContext.addLifecycleEventListener(new RNIapModule$lifecycleEventListener$1(this));
    }

    public String getName() {
        return TAG;
    }

    public final void ensureConnection(Promise promise, Function1 function1) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Intrinsics.checkNotNullParameter(function1, "callback");
        BillingClient billingClient = this.billingClientCache;
        if (billingClient == null || !billingClient.isReady()) {
            initConnection(new PromiseImpl(new RNIapModule$$ExternalSyntheticLambda16(this, function1, promise), new RNIapModule$$ExternalSyntheticLambda17(promise)));
        } else {
            function1.invoke(billingClient);
        }
    }

    /* access modifiers changed from: private */
    public static final void ensureConnection$lambda$0(RNIapModule rNIapModule, Function1 function1, Promise promise, Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        if (!(objArr.length == 0)) {
            Boolean bool = objArr[0];
            if (bool instanceof Boolean) {
                Intrinsics.checkNotNull(bool, "null cannot be cast to non-null type kotlin.Boolean");
                if (bool.booleanValue()) {
                    BillingClient billingClient = rNIapModule.billingClientCache;
                    if (billingClient == null || !billingClient.isReady()) {
                        PromiseUtlisKt.safeReject(promise, "E_NOT_PREPARED", "Unable to auto-initialize connection");
                        return;
                    } else {
                        function1.invoke(billingClient);
                        return;
                    }
                }
            }
        }
        PromiseUtlisKt.safeReject(promise, "E_UNKNOWN", "ensureConnection - incorrect parameter in resolve");
        Log.i(TAG, "Incorrect parameter in resolve");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004a A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void ensureConnection$lambda$1(com.facebook.react.bridge.Promise r4, java.lang.Object[] r5) {
        /*
            java.lang.String r0 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            int r0 = r5.length
            r1 = 0
            r2 = 1
            if (r0 <= r2) goto L_0x0025
            r0 = r5[r1]
            boolean r3 = r0 instanceof java.lang.String
            if (r3 == 0) goto L_0x0025
            r3 = r5[r2]
            boolean r3 = r3 instanceof java.lang.String
            if (r3 == 0) goto L_0x0025
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.String"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            java.lang.String r0 = (java.lang.String) r0
            r5 = r5[r2]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r1)
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x0048
        L_0x0025:
            int r0 = r5.length
            if (r0 != 0) goto L_0x0029
            goto L_0x002a
        L_0x0029:
            r2 = r1
        L_0x002a:
            if (r2 != 0) goto L_0x0046
            r5 = r5[r1]
            boolean r0 = r5 instanceof com.facebook.react.bridge.WritableNativeMap
            if (r0 == 0) goto L_0x0046
            java.lang.String r0 = "null cannot be cast to non-null type com.facebook.react.bridge.WritableNativeMap"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r0)
            com.facebook.react.bridge.WritableNativeMap r5 = (com.facebook.react.bridge.WritableNativeMap) r5
            java.lang.String r0 = "code"
            java.lang.String r0 = r5.getString(r0)
            java.lang.String r1 = "message"
            java.lang.String r5 = r5.getString(r1)
            goto L_0x0048
        L_0x0046:
            r0 = 0
            r5 = r0
        L_0x0048:
            if (r0 == 0) goto L_0x0050
            if (r5 == 0) goto L_0x0050
            com.dooboolab.rniap.PromiseUtlisKt.safeReject(r4, r0, r5)
            goto L_0x005e
        L_0x0050:
            java.lang.String r5 = "E_UNKNOWN"
            java.lang.String r0 = "ensureConnection - incorrect parameter in reject"
            com.dooboolab.rniap.PromiseUtlisKt.safeReject(r4, r5, r0)
            java.lang.String r4 = "RNIapModule"
            java.lang.String r5 = "Incorrect parameters in reject"
            android.util.Log.i(r4, r5)
        L_0x005e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dooboolab.rniap.RNIapModule.ensureConnection$lambda$1(com.facebook.react.bridge.Promise, java.lang.Object[]):void");
    }

    @ReactMethod
    public final void isFeatureSupported(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "feature");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        ensureConnection(promise, new RNIapModule$$ExternalSyntheticLambda20(str, promise));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0045, code lost:
        com.dooboolab.rniap.PromiseUtlisKt.safeReject(r2, "Invalid Feature name");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004c, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004f, code lost:
        com.dooboolab.rniap.PromiseUtlisKt.safeResolve(r2, r3.isFeatureSupported(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0058, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.Unit isFeatureSupported$lambda$2(java.lang.String r1, com.facebook.react.bridge.Promise r2, com.android.billingclient.api.BillingClient r3) {
        /*
            java.lang.String r0 = "billingClient"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            int r0 = r1.hashCode()
            switch(r0) {
                case -91953012: goto L_0x003d;
                case 755711666: goto L_0x0031;
                case 808641238: goto L_0x0025;
                case 1739975758: goto L_0x0019;
                case 1785301586: goto L_0x000d;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x0045
        L_0x000d:
            java.lang.String r0 = "SUBSCRIPTIONS_UPDATE"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0016
            goto L_0x0045
        L_0x0016:
            java.lang.String r1 = "subscriptionsUpdate"
            goto L_0x004f
        L_0x0019:
            java.lang.String r0 = "PRICE_CHANGE_CONFIRMATION"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0022
            goto L_0x0045
        L_0x0022:
            java.lang.String r1 = "priceChangeConfirmation"
            goto L_0x004f
        L_0x0025:
            java.lang.String r0 = "SUBSCRIPTIONS"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x002e
            goto L_0x0045
        L_0x002e:
            java.lang.String r1 = "subscriptions"
            goto L_0x004f
        L_0x0031:
            java.lang.String r0 = "PRODUCT_DETAILS"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x003a
            goto L_0x0045
        L_0x003a:
            java.lang.String r1 = "fff"
            goto L_0x004f
        L_0x003d:
            java.lang.String r0 = "IN_APP_MESSAGING"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x004d
        L_0x0045:
            java.lang.String r1 = "Invalid Feature name"
            com.dooboolab.rniap.PromiseUtlisKt.safeReject(r2, r1)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x004d:
            java.lang.String r1 = "bbb"
        L_0x004f:
            com.android.billingclient.api.BillingResult r1 = r3.isFeatureSupported(r1)
            com.dooboolab.rniap.PromiseUtlisKt.safeResolve(r2, r1)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dooboolab.rniap.RNIapModule.isFeatureSupported$lambda$2(java.lang.String, com.facebook.react.bridge.Promise, com.android.billingclient.api.BillingClient):kotlin.Unit");
    }

    @ReactMethod
    public final void initConnection(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        if (this.googleApiAvailability.isGooglePlayServicesAvailable(this.reactContext) != 0) {
            Log.i(TAG, "Google Play Services are not available on this device");
            PromiseUtlisKt.safeReject(promise, "E_NOT_PREPARED", "Google Play Services are not available on this device");
            return;
        }
        BillingClient billingClient = this.billingClientCache;
        if (billingClient == null || !billingClient.isReady()) {
            BillingClient build = this.builder.setListener(this).build();
            this.billingClientCache = build;
            build.startConnection(new RNIapModule$initConnection$1$1(this, promise));
            return;
        }
        Log.i(TAG, "Already initialized, you should only call initConnection() once when your app starts");
        PromiseUtlisKt.safeResolve(promise, Boolean.TRUE);
    }

    @ReactMethod
    public final void endConnection(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        BillingClient billingClient = this.billingClientCache;
        if (billingClient != null) {
            billingClient.endConnection();
        }
        this.billingClientCache = null;
        this.skus.clear();
        PromiseUtils.INSTANCE.rejectAllPendingPromises();
        PromiseUtlisKt.safeResolve(promise, Boolean.TRUE);
    }

    static /* synthetic */ void consumeItems$default(RNIapModule rNIapModule, List list, Promise promise, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        rNIapModule.consumeItems(list, promise, i);
    }

    private final void consumeItems(List<? extends Purchase> list, Promise promise, int i) {
        for (Purchase rNIapModule$$ExternalSyntheticLambda7 : list) {
            ensureConnection(promise, new RNIapModule$$ExternalSyntheticLambda7(rNIapModule$$ExternalSyntheticLambda7, i, promise));
        }
    }

    /* access modifiers changed from: private */
    public static final Unit consumeItems$lambda$5(Purchase purchase, int i, Promise promise, BillingClient billingClient) {
        Intrinsics.checkNotNullParameter(billingClient, "billingClient");
        ConsumeParams build = ConsumeParams.newBuilder().setPurchaseToken(purchase.getPurchaseToken()).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        billingClient.consumeAsync(build, new RNIapModule$$ExternalSyntheticLambda22(i, promise));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final void consumeItems$lambda$5$lambda$4(int i, Promise promise, BillingResult billingResult, String str) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        if (billingResult.getResponseCode() != i) {
            PlayUtils.INSTANCE.rejectPromiseWithBillingError(promise, billingResult.getResponseCode());
        } else {
            PromiseUtlisKt.safeResolve(promise, Boolean.TRUE);
        }
    }

    @ReactMethod
    public final void flushFailedPurchasesCachedAsPending(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        ensureConnection(promise, new RNIapModule$$ExternalSyntheticLambda23(this, promise));
    }

    /* access modifiers changed from: private */
    public static final Unit flushFailedPurchasesCachedAsPending$lambda$8(RNIapModule rNIapModule, Promise promise, BillingClient billingClient) {
        Intrinsics.checkNotNullParameter(billingClient, "billingClient");
        billingClient.queryPurchasesAsync(QueryPurchasesParams.newBuilder().setProductType("inapp").build(), new RNIapModule$$ExternalSyntheticLambda14(rNIapModule, promise));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final void flushFailedPurchasesCachedAsPending$lambda$8$lambda$7(RNIapModule rNIapModule, Promise promise, BillingResult billingResult, List list) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        if (rNIapModule.isValidResult(billingResult, promise)) {
            if (list == null) {
                PromiseUtlisKt.safeResolve(promise, Boolean.FALSE);
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (Object next : list) {
                if (((Purchase) next).getPurchaseState() == 2) {
                    arrayList.add(next);
                }
            }
            if (arrayList.isEmpty()) {
                PromiseUtlisKt.safeResolve(promise, Boolean.FALSE);
            } else {
                rNIapModule.consumeItems(arrayList, promise, 8);
            }
        }
    }

    @ReactMethod
    public final void getItemsByType(String str, ReadableArray readableArray, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(readableArray, "skuArr");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        ensureConnection(promise, new RNIapModule$$ExternalSyntheticLambda12(readableArray, promise, str, this));
    }

    /* access modifiers changed from: private */
    public static final Unit getItemsByType$lambda$20(ReadableArray readableArray, Promise promise, String str, RNIapModule rNIapModule, BillingClient billingClient) {
        String string;
        Intrinsics.checkNotNullParameter(billingClient, "billingClient");
        ArrayList arrayList = new ArrayList();
        int size = readableArray.size();
        for (int i = 0; i < size; i++) {
            if (readableArray.getType(i) == ReadableType.String && (string = readableArray.getString(i)) != null) {
                QueryProductDetailsParams.Product build = QueryProductDetailsParams.Product.newBuilder().setProductId(string).setProductType(str).build();
                Intrinsics.checkNotNullExpressionValue(build, "build(...)");
                arrayList.add(build);
            }
        }
        if (arrayList.isEmpty()) {
            PromiseUtlisKt.safeReject(promise, "EMPTY_SKU_LIST", "The SKU list is empty.");
            return Unit.INSTANCE;
        }
        QueryProductDetailsParams build2 = QueryProductDetailsParams.newBuilder().setProductList(arrayList).build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        billingClient.queryProductDetailsAsync(build2, new RNIapModule$$ExternalSyntheticLambda13(rNIapModule, promise));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final void getItemsByType$lambda$20$lambda$19(RNIapModule rNIapModule, Promise promise, BillingResult billingResult, List list) {
        RNIapModule rNIapModule2 = rNIapModule;
        Promise promise2 = promise;
        BillingResult billingResult2 = billingResult;
        Intrinsics.checkNotNullParameter(billingResult2, "billingResult");
        Intrinsics.checkNotNullParameter(list, "skuDetailsList");
        if (rNIapModule2.isValidResult(billingResult2, promise2)) {
            WritableArray createArray = Arguments.createArray();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ProductDetails productDetails = (ProductDetails) it.next();
                rNIapModule2.skus.put(productDetails.getProductId(), productDetails);
                WritableMap createMap = Arguments.createMap();
                createMap.putString("productId", productDetails.getProductId());
                createMap.putString("id", productDetails.getProductId());
                createMap.putString("title", productDetails.getTitle());
                createMap.putString("description", productDetails.getDescription());
                createMap.putString("productType", productDetails.getProductType());
                createMap.putString("type", productDetails.getProductType());
                createMap.putString("name", productDetails.getName());
                createMap.putString("displayName", productDetails.getName());
                createMap.putString(k.a.b, "android");
                ProductDetails.OneTimePurchaseOfferDetails oneTimePurchaseOfferDetails = productDetails.getOneTimePurchaseOfferDetails();
                if (oneTimePurchaseOfferDetails != null) {
                    WritableMap createMap2 = Arguments.createMap();
                    createMap2.putString("priceCurrencyCode", oneTimePurchaseOfferDetails.getPriceCurrencyCode());
                    createMap2.putString("formattedPrice", oneTimePurchaseOfferDetails.getFormattedPrice());
                    createMap2.putString("priceAmountMicros", String.valueOf(oneTimePurchaseOfferDetails.getPriceAmountMicros()));
                    createMap.putMap("oneTimePurchaseOfferDetails", createMap2);
                    createMap.putString(FirebaseAnalytics.Param.CURRENCY, oneTimePurchaseOfferDetails.getPriceCurrencyCode());
                    createMap.putString("displayPrice", oneTimePurchaseOfferDetails.getFormattedPrice());
                }
                List<ProductDetails.SubscriptionOfferDetails> subscriptionOfferDetails = productDetails.getSubscriptionOfferDetails();
                if (subscriptionOfferDetails != null) {
                    WritableArray createArray2 = Arguments.createArray();
                    for (ProductDetails.SubscriptionOfferDetails subscriptionOfferDetails2 : subscriptionOfferDetails) {
                        WritableMap createMap3 = Arguments.createMap();
                        createMap3.putString("basePlanId", subscriptionOfferDetails2.getBasePlanId());
                        createMap3.putString("offerId", subscriptionOfferDetails2.getOfferId());
                        createMap3.putString("offerToken", subscriptionOfferDetails2.getOfferToken());
                        WritableArray createArray3 = Arguments.createArray();
                        List<String> offerTags = subscriptionOfferDetails2.getOfferTags();
                        Intrinsics.checkNotNullExpressionValue(offerTags, "getOfferTags(...)");
                        for (String pushString : offerTags) {
                            createArray3.pushString(pushString);
                        }
                        createMap3.putArray("offerTags", createArray3);
                        WritableArray createArray4 = Arguments.createArray();
                        List<ProductDetails.PricingPhase> pricingPhaseList = subscriptionOfferDetails2.getPricingPhases().getPricingPhaseList();
                        Intrinsics.checkNotNullExpressionValue(pricingPhaseList, "getPricingPhaseList(...)");
                        for (ProductDetails.PricingPhase pricingPhase : pricingPhaseList) {
                            WritableMap createMap4 = Arguments.createMap();
                            createMap4.putString("formattedPrice", pricingPhase.getFormattedPrice());
                            createMap4.putString("priceCurrencyCode", pricingPhase.getPriceCurrencyCode());
                            createMap4.putString("billingPeriod", pricingPhase.getBillingPeriod());
                            createMap4.putInt("billingCycleCount", pricingPhase.getBillingCycleCount());
                            createMap4.putString("priceAmountMicros", String.valueOf(pricingPhase.getPriceAmountMicros()));
                            createMap4.putInt("recurrenceMode", pricingPhase.getRecurrenceMode());
                            createArray4.pushMap(createMap4);
                            RNIapModule rNIapModule3 = rNIapModule;
                        }
                        WritableMap createMap5 = Arguments.createMap();
                        createMap5.putArray("pricingPhaseList", createArray4);
                        createMap3.putMap("pricingPhases", createMap5);
                        createArray2.pushMap(createMap3);
                        RNIapModule rNIapModule4 = rNIapModule;
                    }
                    createMap.putArray("subscriptionOfferDetails", createArray2);
                }
                createArray.pushMap(createMap);
                rNIapModule2 = rNIapModule;
            }
            PromiseUtlisKt.safeResolve(promise2, createArray);
        }
    }

    /* access modifiers changed from: private */
    public final boolean isValidResult(BillingResult billingResult, Promise promise) {
        Log.d(TAG, "responseCode: " + billingResult.getResponseCode());
        if (billingResult.getResponseCode() == 0) {
            return true;
        }
        PlayUtils.INSTANCE.rejectPromiseWithBillingError(promise, billingResult.getResponseCode());
        return false;
    }

    @ReactMethod
    public final void getAvailableItemsByType(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Log.d(TAG, "getAvailableItemsByType called for type: " + str);
        ensureConnection(promise, new RNIapModule$$ExternalSyntheticLambda1(str, this, promise));
    }

    /* access modifiers changed from: private */
    public static final Unit getAvailableItemsByType$lambda$25(String str, RNIapModule rNIapModule, Promise promise, BillingClient billingClient) {
        Intrinsics.checkNotNullParameter(billingClient, "billingClient");
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        String str2 = "subs";
        if (!Intrinsics.areEqual((Object) str, (Object) str2)) {
            str2 = "inapp";
        }
        billingClient.queryPurchasesAsync(QueryPurchasesParams.newBuilder().setProductType(str2).build(), new RNIapModule$$ExternalSyntheticLambda24(str, rNIapModule, promise, writableNativeArray));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final void getAvailableItemsByType$lambda$25$lambda$24(String str, RNIapModule rNIapModule, Promise promise, WritableNativeArray writableNativeArray, BillingResult billingResult, List list) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        StringBuilder sb = new StringBuilder();
        sb.append("queryPurchasesAsync response for ");
        sb.append(str);
        sb.append(": ");
        sb.append(billingResult.getResponseCode());
        sb.append(", purchases count: ");
        sb.append(list != null ? list.size() : 0);
        Log.d(TAG, sb.toString());
        if (!rNIapModule.isValidResult(billingResult, promise)) {
            Log.e(TAG, "Invalid result for getAvailableItemsByType: " + billingResult.getDebugMessage());
            return;
        }
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Purchase purchase = (Purchase) it.next();
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString("productId", (String) purchase.getProducts().get(0));
                writableNativeMap.putString("id", (String) purchase.getProducts().get(0));
                WritableArray createArray = Arguments.createArray();
                List<String> products = purchase.getProducts();
                Intrinsics.checkNotNullExpressionValue(products, "getProducts(...)");
                for (String pushString : products) {
                    createArray.pushString(pushString);
                }
                writableNativeMap.putArray("productIds", createArray);
                WritableArray createArray2 = Arguments.createArray();
                List<String> products2 = purchase.getProducts();
                Intrinsics.checkNotNullExpressionValue(products2, "getProducts(...)");
                for (String pushString2 : products2) {
                    createArray2.pushString(pushString2);
                }
                writableNativeMap.putArray("ids", createArray2);
                writableNativeMap.putString("transactionId", purchase.getOrderId());
                writableNativeMap.putDouble("transactionDate", (double) purchase.getPurchaseTime());
                writableNativeMap.putString("transactionReceipt", purchase.getOriginalJson());
                writableNativeMap.putString("orderId", purchase.getOrderId());
                writableNativeMap.putString("purchaseToken", purchase.getPurchaseToken());
                writableNativeMap.putString("purchaseTokenAndroid", purchase.getPurchaseToken());
                writableNativeMap.putString("developerPayloadAndroid", purchase.getDeveloperPayload());
                writableNativeMap.putString("signatureAndroid", purchase.getSignature());
                writableNativeMap.putInt("purchaseStateAndroid", purchase.getPurchaseState());
                writableNativeMap.putBoolean("isAcknowledgedAndroid", purchase.isAcknowledged());
                writableNativeMap.putString("packageNameAndroid", purchase.getPackageName());
                AccountIdentifiers accountIdentifiers = purchase.getAccountIdentifiers();
                String str2 = null;
                writableNativeMap.putString("obfuscatedAccountIdAndroid", accountIdentifiers != null ? accountIdentifiers.getObfuscatedAccountId() : null);
                AccountIdentifiers accountIdentifiers2 = purchase.getAccountIdentifiers();
                if (accountIdentifiers2 != null) {
                    str2 = accountIdentifiers2.getObfuscatedProfileId();
                }
                writableNativeMap.putString("obfuscatedProfileIdAndroid", str2);
                if (Intrinsics.areEqual((Object) str, (Object) "subs")) {
                    writableNativeMap.putBoolean("autoRenewingAndroid", purchase.isAutoRenewing());
                }
                writableNativeMap.putString(k.a.b, "android");
                writableNativeArray.pushMap(writableNativeMap);
            }
        }
        Log.d(TAG, "getAvailableItemsByType returning " + writableNativeArray.toArrayList().size() + " items for type: " + str);
        PromiseUtlisKt.safeResolve(promise, writableNativeArray);
    }

    @ReactMethod
    public final void getAvailableItems(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        ensureConnection(promise, new RNIapModule$$ExternalSyntheticLambda9(promise));
    }

    /* access modifiers changed from: private */
    public static final Unit getAvailableItems$lambda$34(Promise promise, BillingClient billingClient) {
        Intrinsics.checkNotNullParameter(billingClient, "billingClient");
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        CollectionsKt.mutableListOf("inapp", "subs");
        Ref$IntRef ref$IntRef = new Ref$IntRef();
        billingClient.queryPurchasesAsync(QueryPurchasesParams.newBuilder().setProductType("inapp").build(), new RNIapModule$$ExternalSyntheticLambda3(writableNativeArray, ref$IntRef, promise));
        billingClient.queryPurchasesAsync(QueryPurchasesParams.newBuilder().setProductType("subs").build(), new RNIapModule$$ExternalSyntheticLambda4(writableNativeArray, ref$IntRef, promise));
        return Unit.INSTANCE;
    }

    private static final void getAvailableItems$lambda$34$checkCompletion(Ref$IntRef ref$IntRef, WritableNativeArray writableNativeArray, Promise promise) {
        int i = ref$IntRef.element + 1;
        ref$IntRef.element = i;
        if (i >= 2) {
            Log.d(TAG, "getAvailableItems returning " + writableNativeArray.toArrayList().size() + " items");
            PromiseUtlisKt.safeResolve(promise, writableNativeArray);
        }
    }

    /* access modifiers changed from: private */
    public static final void getAvailableItems$lambda$34$lambda$29(WritableNativeArray writableNativeArray, Ref$IntRef ref$IntRef, Promise promise, BillingResult billingResult, List list) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        if (billingResult.getResponseCode() != 0) {
            Log.w(TAG, "Failed to query INAPP purchases: " + billingResult.getDebugMessage());
        } else if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Purchase purchase = (Purchase) it.next();
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString("productId", (String) purchase.getProducts().get(0));
                writableNativeMap.putString("id", (String) purchase.getProducts().get(0));
                WritableArray createArray = Arguments.createArray();
                List<String> products = purchase.getProducts();
                Intrinsics.checkNotNullExpressionValue(products, "getProducts(...)");
                for (String pushString : products) {
                    createArray.pushString(pushString);
                }
                writableNativeMap.putArray("productIds", createArray);
                WritableArray createArray2 = Arguments.createArray();
                List<String> products2 = purchase.getProducts();
                Intrinsics.checkNotNullExpressionValue(products2, "getProducts(...)");
                for (String pushString2 : products2) {
                    createArray2.pushString(pushString2);
                }
                writableNativeMap.putArray("ids", createArray2);
                writableNativeMap.putString("transactionId", purchase.getOrderId());
                writableNativeMap.putDouble("transactionDate", (double) purchase.getPurchaseTime());
                writableNativeMap.putString("transactionReceipt", purchase.getOriginalJson());
                writableNativeMap.putString("orderId", purchase.getOrderId());
                writableNativeMap.putString("purchaseToken", purchase.getPurchaseToken());
                writableNativeMap.putString("purchaseTokenAndroid", purchase.getPurchaseToken());
                writableNativeMap.putString("developerPayloadAndroid", purchase.getDeveloperPayload());
                writableNativeMap.putString("signatureAndroid", purchase.getSignature());
                writableNativeMap.putInt("purchaseStateAndroid", purchase.getPurchaseState());
                writableNativeMap.putBoolean("isAcknowledgedAndroid", purchase.isAcknowledged());
                writableNativeMap.putString("packageNameAndroid", purchase.getPackageName());
                AccountIdentifiers accountIdentifiers = purchase.getAccountIdentifiers();
                String str = null;
                writableNativeMap.putString("obfuscatedAccountIdAndroid", accountIdentifiers != null ? accountIdentifiers.getObfuscatedAccountId() : null);
                AccountIdentifiers accountIdentifiers2 = purchase.getAccountIdentifiers();
                if (accountIdentifiers2 != null) {
                    str = accountIdentifiers2.getObfuscatedProfileId();
                }
                writableNativeMap.putString("obfuscatedProfileIdAndroid", str);
                writableNativeMap.putString(k.a.b, "android");
                writableNativeArray.pushMap(writableNativeMap);
            }
        }
        getAvailableItems$lambda$34$checkCompletion(ref$IntRef, writableNativeArray, promise);
    }

    /* access modifiers changed from: private */
    public static final void getAvailableItems$lambda$34$lambda$33(WritableNativeArray writableNativeArray, Ref$IntRef ref$IntRef, Promise promise, BillingResult billingResult, List list) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        if (billingResult.getResponseCode() != 0) {
            Log.w(TAG, "Failed to query SUBS purchases: " + billingResult.getDebugMessage());
        } else if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Purchase purchase = (Purchase) it.next();
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString("productId", (String) purchase.getProducts().get(0));
                writableNativeMap.putString("id", (String) purchase.getProducts().get(0));
                WritableArray createArray = Arguments.createArray();
                List<String> products = purchase.getProducts();
                Intrinsics.checkNotNullExpressionValue(products, "getProducts(...)");
                for (String pushString : products) {
                    createArray.pushString(pushString);
                }
                writableNativeMap.putArray("productIds", createArray);
                WritableArray createArray2 = Arguments.createArray();
                List<String> products2 = purchase.getProducts();
                Intrinsics.checkNotNullExpressionValue(products2, "getProducts(...)");
                for (String pushString2 : products2) {
                    createArray2.pushString(pushString2);
                }
                writableNativeMap.putArray("ids", createArray2);
                writableNativeMap.putString("transactionId", purchase.getOrderId());
                writableNativeMap.putDouble("transactionDate", (double) purchase.getPurchaseTime());
                writableNativeMap.putString("transactionReceipt", purchase.getOriginalJson());
                writableNativeMap.putString("orderId", purchase.getOrderId());
                writableNativeMap.putString("purchaseToken", purchase.getPurchaseToken());
                writableNativeMap.putString("purchaseTokenAndroid", purchase.getPurchaseToken());
                writableNativeMap.putString("developerPayloadAndroid", purchase.getDeveloperPayload());
                writableNativeMap.putString("signatureAndroid", purchase.getSignature());
                writableNativeMap.putInt("purchaseStateAndroid", purchase.getPurchaseState());
                writableNativeMap.putBoolean("isAcknowledgedAndroid", purchase.isAcknowledged());
                writableNativeMap.putString("packageNameAndroid", purchase.getPackageName());
                AccountIdentifiers accountIdentifiers = purchase.getAccountIdentifiers();
                String str = null;
                writableNativeMap.putString("obfuscatedAccountIdAndroid", accountIdentifiers != null ? accountIdentifiers.getObfuscatedAccountId() : null);
                AccountIdentifiers accountIdentifiers2 = purchase.getAccountIdentifiers();
                if (accountIdentifiers2 != null) {
                    str = accountIdentifiers2.getObfuscatedProfileId();
                }
                writableNativeMap.putString("obfuscatedProfileIdAndroid", str);
                writableNativeMap.putBoolean("autoRenewingAndroid", purchase.isAutoRenewing());
                writableNativeMap.putString(k.a.b, "android");
                writableNativeArray.pushMap(writableNativeMap);
            }
        }
        getAvailableItems$lambda$34$checkCompletion(ref$IntRef, writableNativeArray, promise);
    }

    @ReactMethod
    public final void getPurchaseHistoryByType(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        ensureConnection(promise, new RNIapModule$$ExternalSyntheticLambda0(str, this, promise));
    }

    /* access modifiers changed from: private */
    public static final Unit getPurchaseHistoryByType$lambda$38(String str, RNIapModule rNIapModule, Promise promise, BillingClient billingClient) {
        Intrinsics.checkNotNullParameter(billingClient, "billingClient");
        QueryPurchaseHistoryParams.Builder newBuilder = QueryPurchaseHistoryParams.newBuilder();
        String str2 = "subs";
        if (!Intrinsics.areEqual((Object) str, (Object) str2)) {
            str2 = "inapp";
        }
        billingClient.queryPurchaseHistoryAsync(newBuilder.setProductType(str2).build(), new RNIapModule$$ExternalSyntheticLambda6(rNIapModule, promise));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final void getPurchaseHistoryByType$lambda$38$lambda$37(RNIapModule rNIapModule, Promise promise, BillingResult billingResult, List list) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        if (rNIapModule.isValidResult(billingResult, promise)) {
            Log.d(TAG, String.valueOf(list));
            WritableArray createArray = Arguments.createArray();
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    PurchaseHistoryRecord purchaseHistoryRecord = (PurchaseHistoryRecord) it.next();
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("productId", (String) purchaseHistoryRecord.getProducts().get(0));
                    createMap.putString("id", (String) purchaseHistoryRecord.getProducts().get(0));
                    WritableArray createArray2 = Arguments.createArray();
                    List<String> products = purchaseHistoryRecord.getProducts();
                    Intrinsics.checkNotNullExpressionValue(products, "getProducts(...)");
                    for (String pushString : products) {
                        createArray2.pushString(pushString);
                    }
                    createMap.putArray("productIds", createArray2);
                    createMap.putArray("ids", createArray2);
                    createMap.putDouble("transactionDate", (double) purchaseHistoryRecord.getPurchaseTime());
                    createMap.putString("transactionReceipt", purchaseHistoryRecord.getOriginalJson());
                    createMap.putString("purchaseToken", purchaseHistoryRecord.getPurchaseToken());
                    createMap.putString("purchaseTokenAndroid", purchaseHistoryRecord.getPurchaseToken());
                    createMap.putString("dataAndroid", purchaseHistoryRecord.getOriginalJson());
                    createMap.putString("signatureAndroid", purchaseHistoryRecord.getSignature());
                    String developerPayload = purchaseHistoryRecord.getDeveloperPayload();
                    if (developerPayload == null) {
                        developerPayload = "";
                    }
                    createMap.putString("developerPayload", developerPayload);
                    createMap.putString(k.a.b, "android");
                    createArray.pushMap(createMap);
                }
            }
            PromiseUtlisKt.safeResolve(promise, createArray);
        }
    }

    @ReactMethod
    public final void buyItemByType(String str, ReadableArray readableArray, String str2, int i, String str3, String str4, ReadableArray readableArray2, boolean z, Promise promise) {
        Promise promise2 = promise;
        String str5 = str;
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(readableArray, "skuArr");
        Intrinsics.checkNotNullParameter(readableArray2, "offerTokenArr");
        Intrinsics.checkNotNullParameter(promise2, BaseJavaModule.METHOD_TYPE_PROMISE);
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            PromiseUtlisKt.safeReject(promise2, "E_UNKNOWN", "getCurrentActivity returned null");
            return;
        }
        ensureConnection(promise2, new RNIapModule$$ExternalSyntheticLambda15(promise, str, readableArray, readableArray2, this, z, str2, i, str3, str4, currentActivity));
    }

    /* access modifiers changed from: private */
    public static final Unit buyItemByType$lambda$42(Promise promise, String str, ReadableArray readableArray, ReadableArray readableArray2, RNIapModule rNIapModule, boolean z, String str2, int i, String str3, String str4, Activity activity, BillingClient billingClient) {
        int i2;
        Promise promise2 = promise;
        String str5 = str;
        RNIapModule rNIapModule2 = rNIapModule;
        String str6 = str2;
        int i3 = i;
        String str7 = str3;
        String str8 = str4;
        Intrinsics.checkNotNullParameter(billingClient, "billingClient");
        PromiseUtils.INSTANCE.addPromiseForKey(PROMISE_BUY_ITEM, promise2);
        if (!Intrinsics.areEqual((Object) str5, (Object) "subs") || readableArray.size() == readableArray2.size()) {
            ArrayList<Object> arrayList = readableArray.toArrayList();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            Iterator<T> it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(String.valueOf(it.next()));
            }
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it2 = arrayList2.iterator();
            int i4 = 0;
            while (it2.hasNext()) {
                Object next = it2.next();
                int i5 = i4 + 1;
                if (i4 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str9 = (String) next;
                Iterator it3 = it2;
                ProductDetails productDetails = rNIapModule2.skus.get(str9);
                if (productDetails == null) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("debugMessage", "The sku was not found. Please fetch products first by calling getItems");
                    createMap.putString("code", PROMISE_BUY_ITEM);
                    createMap.putString(StackTraceHelper.MESSAGE_KEY, "The sku was not found. Please fetch products first by calling getItems");
                    createMap.putString("productId", str9);
                    rNIapModule2.sendEvent(rNIapModule2.reactContext, "purchase-error", createMap);
                    PromiseUtlisKt.safeReject(promise2, PROMISE_BUY_ITEM, "The sku was not found. Please fetch products first by calling getItems");
                    return Unit.INSTANCE;
                }
                BillingFlowParams.ProductDetailsParams.Builder productDetails2 = BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(productDetails);
                Intrinsics.checkNotNullExpressionValue(productDetails2, "setProductDetails(...)");
                if (Intrinsics.areEqual((Object) str5, (Object) "subs")) {
                    String string = readableArray2.getString(i4);
                    if (string != null) {
                        productDetails2 = productDetails2.setOfferToken(string);
                    }
                } else {
                    ReadableArray readableArray3 = readableArray2;
                }
                arrayList3.add(productDetails2.build());
                it2 = it3;
                i4 = i5;
            }
            BillingFlowParams.Builder newBuilder = BillingFlowParams.newBuilder();
            Intrinsics.checkNotNullExpressionValue(newBuilder, "newBuilder(...)");
            newBuilder.setProductDetailsParamsList(arrayList3).setIsOfferPersonalized(z);
            BillingFlowParams.SubscriptionUpdateParams.Builder newBuilder2 = BillingFlowParams.SubscriptionUpdateParams.newBuilder();
            Intrinsics.checkNotNullExpressionValue(newBuilder2, "newBuilder(...)");
            if (str6 != null) {
                newBuilder2.setOldPurchaseToken(str6);
                if (Intrinsics.areEqual((Object) str5, (Object) "subs") && i3 != -1) {
                    int i6 = 1;
                    if (i3 != 1) {
                        i6 = 2;
                        if (i3 != 2) {
                            i6 = 3;
                            if (i3 != 3) {
                                i6 = 5;
                                if (i3 != 5) {
                                    i6 = 6;
                                    if (i3 != 6) {
                                        i2 = 0;
                                        newBuilder2.setSubscriptionReplacementMode(i2);
                                    }
                                }
                            }
                        }
                    }
                    i2 = i6;
                    newBuilder2.setSubscriptionReplacementMode(i2);
                }
                BillingFlowParams.SubscriptionUpdateParams build = newBuilder2.build();
                Intrinsics.checkNotNullExpressionValue(build, "build(...)");
                newBuilder.setSubscriptionUpdateParams(build);
            }
            if (str7 != null) {
                newBuilder.setObfuscatedAccountId(str7);
            }
            String str10 = str4;
            if (str10 != null) {
                newBuilder.setObfuscatedProfileId(str10);
            }
            BillingFlowParams build2 = newBuilder.build();
            Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
            int responseCode = billingClient.launchBillingFlow(activity, build2).getResponseCode();
            if (responseCode != 0) {
                BillingResponse billingResponseData = PlayUtils.INSTANCE.getBillingResponseData(responseCode);
                PromiseUtlisKt.safeReject(promise2, billingResponseData.getCode(), billingResponseData.getMessage());
            }
            return Unit.INSTANCE;
        }
        String str11 = "The number of skus (" + readableArray.size() + ") must match: the number of offerTokens (" + readableArray2.size() + ") for Subscriptions";
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putString("debugMessage", str11);
        createMap2.putString("code", PROMISE_BUY_ITEM);
        createMap2.putString(StackTraceHelper.MESSAGE_KEY, str11);
        rNIapModule2.sendEvent(rNIapModule2.reactContext, "purchase-error", createMap2);
        PromiseUtlisKt.safeReject(promise2, PROMISE_BUY_ITEM, str11);
        return Unit.INSTANCE;
    }

    @ReactMethod
    public final void acknowledgePurchase(String str, String str2, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "token");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        ensureConnection(promise, new RNIapModule$$ExternalSyntheticLambda19(str, this, promise));
    }

    /* access modifiers changed from: private */
    public static final Unit acknowledgePurchase$lambda$44(String str, RNIapModule rNIapModule, Promise promise, BillingClient billingClient) {
        Intrinsics.checkNotNullParameter(billingClient, "billingClient");
        AcknowledgePurchaseParams build = AcknowledgePurchaseParams.newBuilder().setPurchaseToken(str).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        billingClient.acknowledgePurchase(build, new RNIapModule$$ExternalSyntheticLambda8(rNIapModule, promise));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final void acknowledgePurchase$lambda$44$lambda$43(RNIapModule rNIapModule, Promise promise, BillingResult billingResult) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        if (rNIapModule.isValidResult(billingResult, promise)) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("responseCode", billingResult.getResponseCode());
            createMap.putString("debugMessage", billingResult.getDebugMessage());
            BillingResponse billingResponseData = PlayUtils.INSTANCE.getBillingResponseData(billingResult.getResponseCode());
            createMap.putString("code", billingResponseData.getCode());
            createMap.putString(StackTraceHelper.MESSAGE_KEY, billingResponseData.getMessage());
            PromiseUtlisKt.safeResolve(promise, createMap);
        }
    }

    @ReactMethod
    public final void consumeProduct(String str, String str2, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "token");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        ConsumeParams build = ConsumeParams.newBuilder().setPurchaseToken(str).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        ensureConnection(promise, new RNIapModule$$ExternalSyntheticLambda18(build, this, promise));
    }

    /* access modifiers changed from: private */
    public static final Unit consumeProduct$lambda$46(ConsumeParams consumeParams, RNIapModule rNIapModule, Promise promise, BillingClient billingClient) {
        Intrinsics.checkNotNullParameter(billingClient, "billingClient");
        billingClient.consumeAsync(consumeParams, new RNIapModule$$ExternalSyntheticLambda10(rNIapModule, promise));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final void consumeProduct$lambda$46$lambda$45(RNIapModule rNIapModule, Promise promise, BillingResult billingResult, String str) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        if (rNIapModule.isValidResult(billingResult, promise)) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("responseCode", billingResult.getResponseCode());
            createMap.putString("debugMessage", billingResult.getDebugMessage());
            BillingResponse billingResponseData = PlayUtils.INSTANCE.getBillingResponseData(billingResult.getResponseCode());
            createMap.putString("code", billingResponseData.getCode());
            createMap.putString(StackTraceHelper.MESSAGE_KEY, billingResponseData.getMessage());
            createMap.putString("purchaseToken", str);
            createMap.putString("purchaseTokenAndroid", str);
            PromiseUtlisKt.safeResolve(promise, createMap);
        }
    }

    public void onPurchasesUpdated(BillingResult billingResult, List<? extends Purchase> list) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        int responseCode = billingResult.getResponseCode();
        if (responseCode != 0) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("responseCode", responseCode);
            createMap.putString("debugMessage", billingResult.getDebugMessage());
            PlayUtils playUtils = PlayUtils.INSTANCE;
            BillingResponse billingResponseData = playUtils.getBillingResponseData(responseCode);
            createMap.putString("code", billingResponseData.getCode());
            createMap.putString(StackTraceHelper.MESSAGE_KEY, billingResponseData.getMessage());
            sendEvent(this.reactContext, "purchase-error", createMap);
            playUtils.rejectPromisesWithBillingError(PROMISE_BUY_ITEM, responseCode);
        } else if (list != null) {
            WritableArray createArray = Arguments.createArray();
            Intrinsics.checkNotNullExpressionValue(createArray, "createArray(...)");
            for (Purchase purchase : list) {
                WritableMap createMap2 = Arguments.createMap();
                createMap2.putString("productId", (String) purchase.getProducts().get(0));
                createMap2.putString("id", (String) purchase.getProducts().get(0));
                WritableArray createArray2 = Arguments.createArray();
                List<String> products = purchase.getProducts();
                Intrinsics.checkNotNullExpressionValue(products, "getProducts(...)");
                for (String pushString : products) {
                    createArray2.pushString(pushString);
                }
                createMap2.putArray("productIds", createArray2);
                WritableArray createArray3 = Arguments.createArray();
                List<String> products2 = purchase.getProducts();
                Intrinsics.checkNotNullExpressionValue(products2, "getProducts(...)");
                for (String pushString2 : products2) {
                    createArray3.pushString(pushString2);
                }
                createMap2.putArray("ids", createArray3);
                createMap2.putString("transactionId", purchase.getOrderId());
                createMap2.putDouble("transactionDate", (double) purchase.getPurchaseTime());
                createMap2.putString("transactionReceipt", purchase.getOriginalJson());
                createMap2.putString("purchaseToken", purchase.getPurchaseToken());
                createMap2.putString("purchaseTokenAndroid", purchase.getPurchaseToken());
                createMap2.putString("dataAndroid", purchase.getOriginalJson());
                createMap2.putString("signatureAndroid", purchase.getSignature());
                createMap2.putBoolean("autoRenewingAndroid", purchase.isAutoRenewing());
                createMap2.putBoolean("isAcknowledgedAndroid", purchase.isAcknowledged());
                createMap2.putInt("purchaseStateAndroid", purchase.getPurchaseState());
                createMap2.putString("packageNameAndroid", purchase.getPackageName());
                createMap2.putString("developerPayloadAndroid", purchase.getDeveloperPayload());
                AccountIdentifiers accountIdentifiers = purchase.getAccountIdentifiers();
                if (accountIdentifiers != null) {
                    createMap2.putString("obfuscatedAccountIdAndroid", accountIdentifiers.getObfuscatedAccountId());
                    createMap2.putString("obfuscatedProfileIdAndroid", accountIdentifiers.getObfuscatedProfileId());
                }
                createMap2.putString(k.a.b, "android");
                createArray.pushMap(createMap2.copy());
                sendEvent(this.reactContext, "purchase-updated", createMap2);
            }
            PromiseUtils.INSTANCE.resolvePromisesForKey(PROMISE_BUY_ITEM, createArray);
        } else {
            WritableMap createMap3 = Arguments.createMap();
            createMap3.putInt("responseCode", billingResult.getResponseCode());
            createMap3.putString("debugMessage", billingResult.getDebugMessage());
            createMap3.putString("extraMessage", "The purchases are null. This is a normal behavior if you have requested DEFERRED proration. If not please report an issue.");
            sendEvent(this.reactContext, "purchase-updated", createMap3);
            PromiseUtils.INSTANCE.resolvePromisesForKey(PROMISE_BUY_ITEM, (Object) null);
        }
    }

    private final void sendUnconsumedPurchases(Promise promise) {
        ensureConnection(promise, new RNIapModule$$ExternalSyntheticLambda5(promise, this));
    }

    /* access modifiers changed from: private */
    public static final Unit sendUnconsumedPurchases$lambda$52(Promise promise, RNIapModule rNIapModule, BillingClient billingClient) {
        Intrinsics.checkNotNullParameter(billingClient, "billingClient");
        String[] strArr = {"inapp", "subs"};
        for (int i = 0; i < 2; i++) {
            billingClient.queryPurchasesAsync(QueryPurchasesParams.newBuilder().setProductType(strArr[i]).build(), new RNIapModule$$ExternalSyntheticLambda2(rNIapModule, promise));
        }
        PromiseUtlisKt.safeResolve(promise, Boolean.TRUE);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final void sendUnconsumedPurchases$lambda$52$lambda$51(RNIapModule rNIapModule, Promise promise, BillingResult billingResult, List list) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        Intrinsics.checkNotNullParameter(list, "list");
        if (rNIapModule.isValidResult(billingResult, promise)) {
            ArrayList arrayList = new ArrayList();
            for (Object next : list) {
                if (!((Purchase) next).isAcknowledged()) {
                    arrayList.add(next);
                }
            }
            rNIapModule.onPurchasesUpdated(billingResult, arrayList);
        }
    }

    @ReactMethod
    public final void startListening(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        sendUnconsumedPurchases(promise);
    }

    @ReactMethod
    public final void getPackageName(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        promise.resolve(getReactApplicationContext().getPackageName());
    }

    @ReactMethod
    public final void getStorefront(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        ensureConnection(promise, new RNIapModule$$ExternalSyntheticLambda21(promise));
    }

    /* access modifiers changed from: private */
    public static final Unit getStorefront$lambda$54(Promise promise, BillingClient billingClient) {
        Intrinsics.checkNotNullParameter(billingClient, "billingClient");
        billingClient.getBillingConfigAsync(GetBillingConfigParams.newBuilder().build(), new RNIapModule$$ExternalSyntheticLambda11(promise));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final void getStorefront$lambda$54$lambda$53(Promise promise, BillingResult billingResult, BillingConfig billingConfig) {
        Intrinsics.checkNotNullParameter(billingResult, "result");
        String str = "";
        if (billingResult.getResponseCode() == 0) {
            String countryCode = billingConfig != null ? billingConfig.getCountryCode() : null;
            if (countryCode != null) {
                str = countryCode;
            }
            PromiseUtlisKt.safeResolve(promise, str);
            return;
        }
        String debugMessage = billingResult.getDebugMessage();
        if (debugMessage != null) {
            str = debugMessage;
        }
        PromiseUtlisKt.safeReject(promise, String.valueOf(billingResult.getResponseCode()), str);
    }

    private final void sendEvent(ReactContext reactContext2, String str, WritableMap writableMap) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext2.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
