package com.facebook.appevents.internal;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.appevents.OperationalData;
import com.facebook.appevents.OperationalDataEnum;
import com.facebook.appevents.iap.InAppPurchase;
import com.facebook.appevents.iap.InAppPurchaseManager;
import com.facebook.appevents.iap.InAppPurchaseUtils;
import com.facebook.hermes.intl.Constants;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.salesforce.marketingcloud.config.a;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AutomaticAnalyticsLogger {
    public static final AutomaticAnalyticsLogger INSTANCE = new AutomaticAnalyticsLogger();
    private static final String TAG = AutomaticAnalyticsLogger.class.getCanonicalName();
    private static final InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(FacebookSdk.getApplicationContext());

    private AutomaticAnalyticsLogger() {
    }

    public static final void logActivateAppEvent() {
        Context applicationContext = FacebookSdk.getApplicationContext();
        String applicationId = FacebookSdk.getApplicationId();
        if (!FacebookSdk.getAutoLogAppEventsEnabled()) {
            return;
        }
        if (applicationContext instanceof Application) {
            AppEventsLogger.Companion.activateApp((Application) applicationContext, applicationId);
        } else {
            Log.w(TAG, "Automatic logging of basic events will not happen, because FacebookSdk.getApplicationContext() returns object that is not instance of android.app.Application. Make sure you call FacebookSdk.sdkInitialize() from Application class and pass application context.");
        }
    }

    public static final void logActivityTimeSpentEvent(String str, long j) {
        Context applicationContext = FacebookSdk.getApplicationContext();
        FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false);
        if (queryAppSettings != null && queryAppSettings.getAutomaticLoggingEnabled() && j > 0) {
            InternalAppEventsLogger internalAppEventsLogger2 = new InternalAppEventsLogger(applicationContext);
            Bundle bundle = new Bundle(1);
            bundle.putCharSequence("fb_aa_time_spent_view_name", str);
            internalAppEventsLogger2.logEvent("fb_aa_time_spent_on_view", (double) j, bundle);
        }
    }

    public static /* synthetic */ void logPurchase$default(String str, String str2, boolean z, InAppPurchaseUtils.BillingClientVersion billingClientVersion, boolean z2, int i, Object obj) {
        if ((i & 16) != 0) {
            z2 = false;
        }
        logPurchase(str, str2, z, billingClientVersion, z2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void logPurchase(java.lang.String r7, java.lang.String r8, boolean r9, com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion r10, boolean r11) {
        /*
            java.lang.String r0 = "purchase"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "skuDetails"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            boolean r0 = isImplicitPurchaseLoggingEnabled()
            if (r0 != 0) goto L_0x0011
            return
        L_0x0011:
            com.facebook.appevents.internal.AutomaticAnalyticsLogger r0 = INSTANCE
            java.util.List r7 = r0.getPurchaseLoggingParameters(r7, r8, r10)
            if (r7 != 0) goto L_0x001a
            return
        L_0x001a:
            boolean r10 = r7.isEmpty()
            if (r10 == 0) goto L_0x0021
            return
        L_0x0021:
            java.lang.String r10 = "fb_mobile_purchase"
            r0 = 0
            if (r9 == 0) goto L_0x0046
            java.lang.String r1 = "app_events_if_auto_log_subs"
            java.lang.String r2 = com.facebook.FacebookSdk.getApplicationId()
            boolean r1 = com.facebook.internal.FetchedAppGateKeepersManager.getGateKeeperForKey(r1, r2, r0)
            if (r1 == 0) goto L_0x0046
            if (r11 == 0) goto L_0x0038
            java.lang.String r8 = "SubscriptionRestore"
        L_0x0036:
            r2 = r8
            goto L_0x004c
        L_0x0038:
            com.facebook.appevents.iap.InAppPurchaseEventManager r11 = com.facebook.appevents.iap.InAppPurchaseEventManager.INSTANCE
            boolean r8 = r11.hasFreeTrialPeirod(r8)
            if (r8 == 0) goto L_0x0043
            java.lang.String r8 = "StartTrial"
            goto L_0x0036
        L_0x0043:
            java.lang.String r8 = "Subscribe"
            goto L_0x0036
        L_0x0046:
            if (r11 == 0) goto L_0x004b
            java.lang.String r8 = "fb_mobile_purchase_restored"
            goto L_0x0036
        L_0x004b:
            r2 = r10
        L_0x004c:
            if (r9 == 0) goto L_0x005b
            com.facebook.internal.FeatureManager$Feature r8 = com.facebook.internal.FeatureManager.Feature.AndroidManualImplicitSubsDedupe
            boolean r8 = com.facebook.internal.FeatureManager.isEnabled(r8)
            if (r8 == 0) goto L_0x005b
            android.os.Bundle r8 = getSubscriptionDedupeParameters(r7, r2)
            goto L_0x006b
        L_0x005b:
            if (r9 != 0) goto L_0x006a
            com.facebook.internal.FeatureManager$Feature r8 = com.facebook.internal.FeatureManager.Feature.AndroidManualImplicitPurchaseDedupe
            boolean r8 = com.facebook.internal.FeatureManager.isEnabled(r8)
            if (r8 == 0) goto L_0x006a
            android.os.Bundle r8 = getPurchaseDedupeParameters(r7)
            goto L_0x006b
        L_0x006a:
            r8 = 0
        L_0x006b:
            com.facebook.appevents.iap.InAppPurchaseDedupeConfig r9 = com.facebook.appevents.iap.InAppPurchaseDedupeConfig.INSTANCE
            java.lang.Object r11 = r7.get(r0)
            com.facebook.appevents.internal.AutomaticAnalyticsLogger$PurchaseLoggingParameters r11 = (com.facebook.appevents.internal.AutomaticAnalyticsLogger.PurchaseLoggingParameters) r11
            android.os.Bundle r11 = r11.getParam()
            java.lang.Object r1 = r7.get(r0)
            com.facebook.appevents.internal.AutomaticAnalyticsLogger$PurchaseLoggingParameters r1 = (com.facebook.appevents.internal.AutomaticAnalyticsLogger.PurchaseLoggingParameters) r1
            com.facebook.appevents.OperationalData r1 = r1.getOperationalData()
            r9.addDedupeParameters(r8, r11, r1)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r10)
            if (r8 != 0) goto L_0x00b8
            com.facebook.appevents.InternalAppEventsLogger r1 = internalAppEventsLogger
            java.lang.Object r8 = r7.get(r0)
            com.facebook.appevents.internal.AutomaticAnalyticsLogger$PurchaseLoggingParameters r8 = (com.facebook.appevents.internal.AutomaticAnalyticsLogger.PurchaseLoggingParameters) r8
            java.math.BigDecimal r3 = r8.getPurchaseAmount()
            java.lang.Object r8 = r7.get(r0)
            com.facebook.appevents.internal.AutomaticAnalyticsLogger$PurchaseLoggingParameters r8 = (com.facebook.appevents.internal.AutomaticAnalyticsLogger.PurchaseLoggingParameters) r8
            java.util.Currency r4 = r8.getCurrency()
            java.lang.Object r8 = r7.get(r0)
            com.facebook.appevents.internal.AutomaticAnalyticsLogger$PurchaseLoggingParameters r8 = (com.facebook.appevents.internal.AutomaticAnalyticsLogger.PurchaseLoggingParameters) r8
            android.os.Bundle r5 = r8.getParam()
            java.lang.Object r7 = r7.get(r0)
            com.facebook.appevents.internal.AutomaticAnalyticsLogger$PurchaseLoggingParameters r7 = (com.facebook.appevents.internal.AutomaticAnalyticsLogger.PurchaseLoggingParameters) r7
            com.facebook.appevents.OperationalData r6 = r7.getOperationalData()
            r1.logEventImplicitly(r2, r3, r4, r5, r6)
            goto L_0x00e5
        L_0x00b8:
            com.facebook.appevents.InternalAppEventsLogger r8 = internalAppEventsLogger
            java.lang.Object r9 = r7.get(r0)
            com.facebook.appevents.internal.AutomaticAnalyticsLogger$PurchaseLoggingParameters r9 = (com.facebook.appevents.internal.AutomaticAnalyticsLogger.PurchaseLoggingParameters) r9
            java.math.BigDecimal r9 = r9.getPurchaseAmount()
            java.lang.Object r10 = r7.get(r0)
            com.facebook.appevents.internal.AutomaticAnalyticsLogger$PurchaseLoggingParameters r10 = (com.facebook.appevents.internal.AutomaticAnalyticsLogger.PurchaseLoggingParameters) r10
            java.util.Currency r10 = r10.getCurrency()
            java.lang.Object r11 = r7.get(r0)
            com.facebook.appevents.internal.AutomaticAnalyticsLogger$PurchaseLoggingParameters r11 = (com.facebook.appevents.internal.AutomaticAnalyticsLogger.PurchaseLoggingParameters) r11
            android.os.Bundle r11 = r11.getParam()
            java.lang.Object r7 = r7.get(r0)
            com.facebook.appevents.internal.AutomaticAnalyticsLogger$PurchaseLoggingParameters r7 = (com.facebook.appevents.internal.AutomaticAnalyticsLogger.PurchaseLoggingParameters) r7
            com.facebook.appevents.OperationalData r7 = r7.getOperationalData()
            r8.logPurchaseImplicitly(r9, r10, r11, r7)
        L_0x00e5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.internal.AutomaticAnalyticsLogger.logPurchase(java.lang.String, java.lang.String, boolean, com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion, boolean):void");
    }

    public static final synchronized Bundle getPurchaseDedupeParameters(List list) {
        Bundle performDedupe;
        synchronized (AutomaticAnalyticsLogger.class) {
            Intrinsics.checkNotNullParameter(list, "purchaseLoggingParametersList");
            PurchaseLoggingParameters purchaseLoggingParameters = (PurchaseLoggingParameters) list.get(0);
            performDedupe = InAppPurchaseManager.performDedupe(CollectionsKt.listOf(new InAppPurchase("fb_mobile_purchase", purchaseLoggingParameters.getPurchaseAmount().doubleValue(), purchaseLoggingParameters.getCurrency())), System.currentTimeMillis(), true, CollectionsKt.listOf(new Pair(purchaseLoggingParameters.getParam(), purchaseLoggingParameters.getOperationalData())));
        }
        return performDedupe;
    }

    public static final synchronized Bundle getSubscriptionDedupeParameters(List list, String str) {
        Bundle performDedupe;
        synchronized (AutomaticAnalyticsLogger.class) {
            try {
                Intrinsics.checkNotNullParameter(list, "purchaseLoggingParametersList");
                Intrinsics.checkNotNullParameter(str, a.h);
                ArrayList arrayList = new ArrayList();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    PurchaseLoggingParameters purchaseLoggingParameters = (PurchaseLoggingParameters) it.next();
                    arrayList.add(new InAppPurchase(str, purchaseLoggingParameters.getPurchaseAmount().doubleValue(), purchaseLoggingParameters.getCurrency()));
                }
                long currentTimeMillis = System.currentTimeMillis();
                Iterable<PurchaseLoggingParameters> iterable = list;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (PurchaseLoggingParameters purchaseLoggingParameters2 : iterable) {
                    arrayList2.add(new Pair(purchaseLoggingParameters2.getParam(), purchaseLoggingParameters2.getOperationalData()));
                }
                performDedupe = InAppPurchaseManager.performDedupe(arrayList, currentTimeMillis, true, arrayList2);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return performDedupe;
    }

    public static final boolean isImplicitPurchaseLoggingEnabled() {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        return appSettingsWithoutQuery != null && FacebookSdk.getAutoLogAppEventsEnabled() && appSettingsWithoutQuery.getIAPAutomaticLoggingEnabled();
    }

    private final List getPurchaseLoggingParameters(String str, String str2, InAppPurchaseUtils.BillingClientVersion billingClientVersion) {
        return getPurchaseLoggingParameters(str, str2, new HashMap(), billingClientVersion);
    }

    private final PurchaseLoggingParameters getPurchaseParametersGPBLV2V4(String str, Bundle bundle, OperationalData operationalData, JSONObject jSONObject, JSONObject jSONObject2) {
        if (Intrinsics.areEqual((Object) str, (Object) InAppPurchaseUtils.IAPProductType.SUBS.getType())) {
            OperationalData.Companion companion = OperationalData.Companion;
            OperationalDataEnum operationalDataEnum = OperationalDataEnum.IAPParameters;
            String bool = Boolean.toString(jSONObject.optBoolean("autoRenewing", false));
            Intrinsics.checkNotNullExpressionValue(bool, "toString(\n              …      )\n                )");
            OperationalData.Companion companion2 = companion;
            OperationalDataEnum operationalDataEnum2 = operationalDataEnum;
            Bundle bundle2 = bundle;
            OperationalData operationalData2 = operationalData;
            companion2.addParameter(operationalDataEnum2, "fb_iap_subs_auto_renewing", bool, bundle2, operationalData2);
            String optString = jSONObject2.optString("subscriptionPeriod");
            Intrinsics.checkNotNullExpressionValue(optString, "skuDetailsJSON.optString…_IAP_SUBSCRIPTION_PERIOD)");
            companion2.addParameter(operationalDataEnum2, "fb_iap_subs_period", optString, bundle2, operationalData2);
            String optString2 = jSONObject2.optString("freeTrialPeriod");
            Intrinsics.checkNotNullExpressionValue(optString2, "skuDetailsJSON.optString…GP_IAP_FREE_TRIAL_PERIOD)");
            companion2.addParameter(operationalDataEnum2, "fb_free_trial_period", optString2, bundle2, operationalData2);
            String optString3 = jSONObject2.optString("introductoryPriceCycles");
            Intrinsics.checkNotNullExpressionValue(optString3, "introductoryPriceCycles");
            if (optString3.length() > 0) {
                companion.addParameter(operationalDataEnum, "fb_intro_price_cycles", optString3, bundle, operationalData);
            }
            String optString4 = jSONObject2.optString("introductoryPricePeriod");
            Intrinsics.checkNotNullExpressionValue(optString4, "introductoryPricePeriod");
            if (optString4.length() > 0) {
                companion.addParameter(operationalDataEnum, "fb_intro_period", optString4, bundle, operationalData);
            }
            String optString5 = jSONObject2.optString("introductoryPriceAmountMicros");
            Intrinsics.checkNotNullExpressionValue(optString5, "introductoryPriceAmountMicros");
            if (optString5.length() > 0) {
                companion.addParameter(operationalDataEnum, "fb_intro_price_amount_micros", optString5, bundle, operationalData);
            }
        }
        BigDecimal bigDecimal = new BigDecimal(((double) jSONObject2.getLong("price_amount_micros")) / 1000000.0d);
        Currency instance = Currency.getInstance(jSONObject2.getString("price_currency_code"));
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(skuDetailsJS…RICE_CURRENCY_CODE_V2V4))");
        return new PurchaseLoggingParameters(bigDecimal, instance, bundle, operationalData);
    }

    private final List getPurchaseParametersGPBLV5V7(String str, Bundle bundle, OperationalData operationalData, JSONObject jSONObject) {
        Bundle bundle2 = bundle;
        JSONObject jSONObject2 = jSONObject;
        if (Intrinsics.areEqual((Object) str, (Object) InAppPurchaseUtils.IAPProductType.SUBS.getType())) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject2.getJSONArray("subscriptionOfferDetails");
            if (jSONArray == null) {
                return null;
            }
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject3 = jSONObject2.getJSONArray("subscriptionOfferDetails").getJSONObject(i);
                if (jSONObject3 == null) {
                    return null;
                }
                Bundle bundle3 = new Bundle(bundle2);
                OperationalData copy = operationalData.copy();
                String string = jSONObject3.getString("basePlanId");
                OperationalData.Companion companion = OperationalData.Companion;
                OperationalDataEnum operationalDataEnum = OperationalDataEnum.IAPParameters;
                Intrinsics.checkNotNullExpressionValue(string, "basePlanId");
                OperationalData operationalData2 = copy;
                Bundle bundle4 = bundle3;
                companion.addParameter(operationalDataEnum, "fb_iap_base_plan", string, bundle4, operationalData2);
                JSONArray jSONArray2 = jSONObject3.getJSONArray("pricingPhases");
                JSONObject jSONObject4 = jSONArray2.getJSONObject(jSONArray2.length() - 1);
                if (jSONObject4 == null) {
                    return null;
                }
                String optString = jSONObject4.optString("billingPeriod");
                Intrinsics.checkNotNullExpressionValue(optString, "subscriptionJSON.optStri…IOD\n                    )");
                companion.addParameter(operationalDataEnum, "fb_iap_subs_period", optString, bundle4, operationalData2);
                if (!jSONObject4.has("recurrenceMode") || jSONObject4.getInt("recurrenceMode") == 3) {
                    companion.addParameter(operationalDataEnum, "fb_iap_subs_auto_renewing", Constants.CASEFIRST_FALSE, bundle4, operationalData2);
                } else {
                    companion.addParameter(operationalDataEnum, "fb_iap_subs_auto_renewing", "true", bundle4, operationalData2);
                }
                BigDecimal bigDecimal = new BigDecimal(((double) jSONObject4.getLong("priceAmountMicros")) / 1000000.0d);
                Currency instance = Currency.getInstance(jSONObject4.getString("priceCurrencyCode"));
                Intrinsics.checkNotNullExpressionValue(instance, "getInstance(subscription…RICE_CURRENCY_CODE_V5V7))");
                arrayList.add(new PurchaseLoggingParameters(bigDecimal, instance, bundle4, operationalData2));
            }
            return arrayList;
        }
        JSONObject jSONObject5 = jSONObject2.getJSONObject("oneTimePurchaseOfferDetails");
        if (jSONObject5 == null) {
            return null;
        }
        BigDecimal bigDecimal2 = new BigDecimal(((double) jSONObject5.getLong("priceAmountMicros")) / 1000000.0d);
        Currency instance2 = Currency.getInstance(jSONObject5.getString("priceCurrencyCode"));
        Intrinsics.checkNotNullExpressionValue(instance2, "getInstance(oneTimePurch…RICE_CURRENCY_CODE_V5V7))");
        return CollectionsKt.mutableListOf(new PurchaseLoggingParameters(bigDecimal2, instance2, bundle2, operationalData));
    }

    private final List getPurchaseLoggingParameters(String str, String str2, Map map, InAppPurchaseUtils.BillingClientVersion billingClientVersion) {
        List list = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject(str2);
            Bundle bundle = new Bundle(1);
            OperationalData operationalData = new OperationalData();
            if (billingClientVersion != null) {
                OperationalData.Companion.addParameter(OperationalDataEnum.IAPParameters, "fb_iap_sdk_supported_library_versions", billingClientVersion.getType(), bundle, operationalData);
            }
            OperationalData.Companion companion = OperationalData.Companion;
            OperationalDataEnum operationalDataEnum = OperationalDataEnum.IAPParameters;
            String string = jSONObject.getString("productId");
            Intrinsics.checkNotNullExpressionValue(string, "purchaseJSON.getString(C…stants.GP_IAP_PRODUCT_ID)");
            companion.addParameter(operationalDataEnum, "fb_iap_product_id", string, bundle, operationalData);
            String string2 = jSONObject.getString("productId");
            Intrinsics.checkNotNullExpressionValue(string2, "purchaseJSON.getString(C…stants.GP_IAP_PRODUCT_ID)");
            companion.addParameter(operationalDataEnum, "fb_content_id", string2, bundle, operationalData);
            companion.addParameter(operationalDataEnum, "android_dynamic_ads_content_id", "client_implicit", bundle, operationalData);
            String string3 = jSONObject.getString("purchaseTime");
            Intrinsics.checkNotNullExpressionValue(string3, "purchaseJSON.getString(C…nts.GP_IAP_PURCHASE_TIME)");
            companion.addParameter(operationalDataEnum, "fb_iap_purchase_time", string3, bundle, operationalData);
            String string4 = jSONObject.getString("purchaseToken");
            Intrinsics.checkNotNullExpressionValue(string4, "purchaseJSON.getString(C…ts.GP_IAP_PURCHASE_TOKEN)");
            companion.addParameter(operationalDataEnum, "fb_iap_purchase_token", string4, bundle, operationalData);
            String optString = jSONObject.optString(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
            Intrinsics.checkNotNullExpressionValue(optString, "purchaseJSON.optString(C…ants.GP_IAP_PACKAGE_NAME)");
            companion.addParameter(operationalDataEnum, "fb_iap_package_name", optString, bundle, operationalData);
            String optString2 = jSONObject2.optString("title");
            Intrinsics.checkNotNullExpressionValue(optString2, "skuDetailsJSON.optString(Constants.GP_IAP_TITLE)");
            companion.addParameter(operationalDataEnum, "fb_iap_product_title", optString2, bundle, operationalData);
            String optString3 = jSONObject2.optString("description");
            Intrinsics.checkNotNullExpressionValue(optString3, "skuDetailsJSON.optString…tants.GP_IAP_DESCRIPTION)");
            companion.addParameter(operationalDataEnum, "fb_iap_product_description", optString3, bundle, operationalData);
            String optString4 = jSONObject2.optString("type");
            Intrinsics.checkNotNullExpressionValue(optString4, "type");
            companion.addParameter(operationalDataEnum, "fb_iap_product_type", optString4, bundle, operationalData);
            String specificBillingLibraryVersion = InAppPurchaseManager.getSpecificBillingLibraryVersion();
            if (specificBillingLibraryVersion != null) {
                companion.addParameter(operationalDataEnum, "fb_iap_client_library_version", specificBillingLibraryVersion, bundle, operationalData);
            }
            for (Map.Entry entry : map.entrySet()) {
                OperationalData.Companion.addParameter(OperationalDataEnum.IAPParameters, (String) entry.getKey(), (String) entry.getValue(), bundle, operationalData);
            }
            if (jSONObject2.has("price_amount_micros")) {
                list = CollectionsKt.mutableListOf(getPurchaseParametersGPBLV2V4(optString4, bundle, operationalData, jSONObject, jSONObject2));
            } else if (jSONObject2.has("subscriptionOfferDetails") || jSONObject2.has("oneTimePurchaseOfferDetails")) {
                try {
                    return getPurchaseParametersGPBLV5V7(optString4, bundle, operationalData, jSONObject2);
                } catch (JSONException e) {
                    e = e;
                    Log.e(TAG, "Error parsing in-app purchase/subscription data.", e);
                    return null;
                } catch (Exception e2) {
                    e = e2;
                    Log.e(TAG, "Failed to get purchase logging parameters,", e);
                    return null;
                }
            }
            return list;
        } catch (JSONException e3) {
            e = e3;
            Log.e(TAG, "Error parsing in-app purchase/subscription data.", e);
            return null;
        } catch (Exception e4) {
            e = e4;
            Log.e(TAG, "Failed to get purchase logging parameters,", e);
            return null;
        }
    }

    public static final class PurchaseLoggingParameters {
        private Currency currency;
        private OperationalData operationalData;
        private Bundle param;
        private BigDecimal purchaseAmount;

        public PurchaseLoggingParameters(BigDecimal bigDecimal, Currency currency2, Bundle bundle, OperationalData operationalData2) {
            Intrinsics.checkNotNullParameter(bigDecimal, "purchaseAmount");
            Intrinsics.checkNotNullParameter(currency2, FirebaseAnalytics.Param.CURRENCY);
            Intrinsics.checkNotNullParameter(bundle, "param");
            Intrinsics.checkNotNullParameter(operationalData2, "operationalData");
            this.purchaseAmount = bigDecimal;
            this.currency = currency2;
            this.param = bundle;
            this.operationalData = operationalData2;
        }

        public final BigDecimal getPurchaseAmount() {
            return this.purchaseAmount;
        }

        public final Currency getCurrency() {
            return this.currency;
        }

        public final Bundle getParam() {
            return this.param;
        }

        public final OperationalData getOperationalData() {
            return this.operationalData;
        }
    }
}
